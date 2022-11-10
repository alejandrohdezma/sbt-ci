package sbt.ci

import sbt.Keys.streams
import sbt._

import com.alejandrohdezma.resource.generator.ResourceGenerator

/** This plugin generates (or updates) a bunch of files common to several projects. */
object SbtCiPlugin extends AutoPlugin with ResourceGenerator[Unit] {

  val generateCiFiles = taskKey[Unit](s"Generates the following files: ${resources.mkString(", ")}")

  override def repository: Option[String] = BuildInfo.repo

  override def trigger = allRequirements

  override def globalSettings = Seq(
    generateCiFiles := generate((), streams.value.log.info(_))
  )

}
