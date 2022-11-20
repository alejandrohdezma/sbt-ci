/*
 * Copyright 2020-2022 Alejandro Hernández <https://github.com/alejandrohdezma>
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

package sbt.ci

import java.nio.file._

import sbt.Keys.streams
import sbt._

import com.alejandrohdezma.resource.generator.ResourceGenerator

/** This plugin generates (or updates) a bunch of files common to several projects. */
object SbtCiPlugin extends AutoPlugin with ResourceGenerator[Unit] {

  object autoImport {

    val excludedFiles = settingKey[List[String]] {
      "List of glob patterns. Files matching any of the patterns in this list" +
        " will be excluded from generation"
    }

  }

  val generateCiFiles = taskKey[Unit](s"Generates the following files: ${resources.mkString(", ")}")

  override def repository: Option[String] = BuildInfo.repo

  override def trigger = allRequirements

  import autoImport._

  override def buildSettings = Seq(
    excludedFiles := Nil,
    generateCiFiles := {
      file(".github/FUNDING.yml").delete()
      file("CODE_OF_CONDUCT.md").delete()
      file("CONTRIBUTING.md").delete()
      file("docs/CODE_OF_CONDUCT.md").delete()
      file("docs/CONTRIBUTING.md").delete()
      file("docs/NOTICE.md").delete()
      file("docs/AUTHORS.md").delete()
      file("NOTICE.md").delete()
      file("AUTHORS.md").delete()

      generate(
        extras = (),
        excludeFile = globPatterns.value,
        logger = streams.value.log.info(_)
      )
    }
  )

  private val globPatterns = Def.setting {
    val fileSystem = FileSystems.getDefault()

    val matchers = excludedFiles.value
      .map("glob:" + _)
      .map(fileSystem.getPathMatcher(_))

    (path: Path, _: String) => matchers.find(_.matches(path)).nonEmpty
  }

}
