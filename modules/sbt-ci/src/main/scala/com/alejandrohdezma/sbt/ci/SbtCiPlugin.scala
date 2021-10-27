/*
 * Copyright 2020-2021 Alejandro Hernández <https://github.com/alejandrohdezma>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alejandrohdezma.sbt.ci

import scala.io.Source
import scala.util.Try

import sbt.Keys._
import sbt._
import sbt.ci.BuildInfo
import sbt.plugins.JvmPlugin

/** This plugin generates (or updates) a bunch of files common to several projects. */
object SbtCiPlugin extends AutoPlugin {

  object autoImport {

    val generateCiFiles = taskKey[Unit](s"Generates all the files included in the `${BuildInfo.name}` plugin")

    val filesToGenerate = taskKey[Seq[String]] {
      s"List of files to generate. Defaults to ${BuildInfo.generatedResources.mkString(", ")}"
    }

  }

  import autoImport._

  override def requires: Plugins = JvmPlugin

  override def trigger = allRequirements

  @SuppressWarnings(Array("scalafix:Disable.blocking.io"))
  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    filesToGenerate := BuildInfo.generatedResources,
    generateCiFiles := {
      file(".github/release-drafter.yml").delete()
      file(".github/pr-labeler.yml").delete()
      filesToGenerate.value.foreach { from =>
        val to = file(from)

        Try {
          val content = stripDescription(to, Source.fromResource(from, getClass().getClassLoader()).mkString)

          if (to.name.equalsIgnoreCase("LICENSE.md")) IO.write(to, content.drop(1))
          else if (from.endsWith(".yml")) IO.write(to, ymlHeader + "\n" + content)
          else if (from.endsWith(".md")) IO.write(to, mdHeader + "\n" + content)
          else IO.write(to, ymlHeader + "\n" + content)

          streams.value.log.info(s"Generated file $to from `${BuildInfo.name}`")
        }.recover { case t =>
          streams.value.log.error(s"Generation of $to failed")
          streams.value.log.trace(t)
        }.getOrElse(())
      }
    }
  )

  private def stripDescription(resource: File, content: String) = resource.ext match {
    case "md" => content.split("\n").dropWhile(_.startsWith("[comment]: <>")).mkString("\n")
    case _    => content.split("\n").dropWhile(_.startsWith("#")).mkString("\n")
  }

  private val ymlHeader =
    s"""# Don't edit this file!
       |# It is automatically updated after every release of https://github.com/${BuildInfo.repository}
       |# If you want to suggest a change, please open a PR or issue in that repository""".stripMargin

  private val mdHeader =
    s"""[comment]: <> (Don't edit this file!)
       |[comment]: <> (It is automatically updated after every release of https://github.com/${BuildInfo.repository})
       |[comment]: <> (If you want to suggest a change, please open a PR or issue in that repository)""".stripMargin

}
