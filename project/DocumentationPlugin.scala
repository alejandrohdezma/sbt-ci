import sbt.AutoPlugin

import sbt._
import sbt.io.IO
import sbt.Keys._
import mdoc.MdocPlugin
import mdoc.MdocPlugin.autoImport._

/** This plugin adds a new `mdocVariables` called `PROPAGATED_RESOURCES` that contains the markdown list of propagated
  * resources, including links to files, destination on new projects and a brief description that is read from each file
  * in the form of a comment at the top of the file.
  *
  * The plugin activates itself if the `MdocPlugin` is enabled.
  */
object DocumentationPlugin extends AutoPlugin {

  override def requires: Plugins = MdocPlugin

  override def trigger: PluginTrigger = allRequirements

  object autoImport {

    val resourcesToDocument = settingKey[List[(File, String)]]("")

  }

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = Seq(
    mdocVariables += "PROPAGATED_RESOURCES" -> toMarkdown(resourcesToDocument.value)
  )

  private def toMarkdown(resources: Seq[(File, String)]): String =
    resources.map { case (resource, destination) =>
      val content = IO.readLines(resource)

      val fileUrl  = s"[$resource](https://github.com/@ORGANIZATION@/@NAME/blob/main/$resource)"
      val copiedAs = if (destination.contentEquals("/")) resource.name else s"$destination/${resource.name}"
      val description = resource.ext match {
        case "md" =>
          content
            .takeWhile(_.startsWith("[comment]: <>"))
            .map(_.stripPrefix("[comment]: <> (").stripSuffix(")"))
            .mkString("\n")
        case _ => content.takeWhile(_.startsWith("#")).map(_.stripPrefix("# ").stripPrefix("#")).mkString("\n")
      }

      s"### $fileUrl (copied as $copiedAs)\n\n$description\n\n"
    }.mkString("\n")

}
