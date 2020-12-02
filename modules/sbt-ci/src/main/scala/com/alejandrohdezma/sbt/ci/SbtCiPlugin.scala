/*
 * Copyright 2020 Alejandro Hernández <https://github.com/alejandrohdezma>
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

import sbt.IO.chmod
import sbt.Keys._
import sbt._
import sbt.plugins.JvmPlugin

/**
 * This plugin generates (or updates) a bunch of files common to several projects.
 */
object SbtCiPlugin extends AutoPlugin {

  object autoImport {

    val generateCiFiles = taskKey[Unit]("Generates all the files included in the `sbt-ci` plugin")

  }

  import autoImport._

  override def requires: Plugins = JvmPlugin

  override def trigger = allRequirements

  override def globalSettings: Seq[Def.Setting[_]] = Seq(
    generateCiFiles := {
      implicit val logger = streams.value.log

      // Remove deprecated files
      file(".github/workflows/release-drafter.yml").delete() // scalafix:ok Disable.blocking.io

      // Root files

      copyResource(from = ".gitignore", to = file(".gitignore"))

      // Documentation templates

      copyResource(from = "AUTHORS.md", to = file("docs/AUTHORS.md"))
      copyResource(from = "CODE_OF_CONDUCT.md", to = file("docs/CODE_OF_CONDUCT.md"))
      copyResource(from = "CONTRIBUTING.md", to = file("docs/CONTRIBUTING.md"))
      copyResource(from = "LICENSE.md", to = file("docs/LICENSE.md"))
      copyResource(from = "NOTICE.md", to = file("docs/NOTICE.md"))

      // Worklfow settings

      copyResource(from = "pr-labeler.yml", to = file(".github/pr-labeler.yml"))
      copyResource(from = "release-drafter.yml", to = file(".github/release-drafter.yml"))
      copyResource(from = "settings.yml", to = file(".github/settings.yml"))

      // Worklfow scripts

      copyResource(from = "gpg-setup.sh", to = file(".github/scripts/gpg-setup.sh"))
      chmod("rwxr-xr-x", file(".github/scripts/gpg-setup.sh"))

      // Workflows

      copyResource(from = "auto-rebase.yml", to = file(".github/workflows/auto-rebase.yml"))
      copyResource(from = "ci.yml", to = file(".github/workflows/ci.yml"))
      copyResource(from = "docs.yml", to = file(".github/workflows/docs.yml"))
      copyResource(from = "draft-next-release.yml", to = file(".github/workflows/draft-next-release.yml"))
      copyResource(from = "release.yml", to = file(".github/workflows/release.yml"))
      copyResource(from = "scala-steward.yml", to = file(".github/workflows/scala-steward.yml"))
    }
  )

  private def copyResource(from: String, to: File)(implicit logger: Logger) = Try {
    val content = Source.fromResource(from, getClass().getClassLoader()).mkString // scalafix:ok Disable.blocking.io

    if (from.equalsIgnoreCase("LICENSE.md")) IO.write(to, content)
    else if (from.endsWith(".yml")) IO.write(to, ymlHeader + "\n\n" + content)
    else if (from.endsWith(".md")) IO.write(to, mdHeader + "\n\n" + content)
    else IO.write(to, ymlHeader + "\n\n" + content)

    logger.info(s"Generated file $to from `sbt-ci`")
  }.recover { case t =>
    logger.error(s"Generation of $to failed")
    logger.trace(t)
  }.getOrElse(())

  private val ymlHeader =
    """# Don't edit this file!
      |# It is automatically updated after every release of https://github.com/alejandrohdezma/sbt-ci
      |# If you want to suggest a change, please open a PR or issue in that repository""".stripMargin

  private val mdHeader =
    """[comment]: <> (Don't edit this file!)
      |[comment]: <> (It is automatically updated after every release of https://github.com/alejandrohdezma/sbt-ci)
      |[comment]: <> (If you want to suggest a change, please open a PR or issue in that repository)""".stripMargin

}
