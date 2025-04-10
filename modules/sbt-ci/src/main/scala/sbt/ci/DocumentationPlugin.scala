/*
 * Copyright 2020-2025 Alejandro Hernández <https://github.com/alejandrohdezma>
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

import sbt.Keys._
import sbt._

import mdoc.MdocPlugin
import mdoc.MdocPlugin.autoImport._

/** This plugin adds a bunch of default settings for projects using mdoc.
  *
  * Specifically it sets so documentation templates are read from `.github/docs` and written to the root directory.
  *
  * @see
  *   https://scalameta.org/mdoc/
  */
object DocumentationPlugin extends AutoPlugin {

  override def trigger = allRequirements

  override def requires: Plugins = MdocPlugin

  override def projectSettings = Seq(
    scalacOptions -= "-Wnonunit-statement",
    mdocIn        := file(".github") / "docs",
    mdocOut       := file(".")
  )

}
