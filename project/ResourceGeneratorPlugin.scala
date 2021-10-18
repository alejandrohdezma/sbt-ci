import sbt._

import sbt.Keys._

/** This plugin copies the resources listed in `resourcesToPropagate` to `target/resources-to-propagate` and adds that
  * folder as a resources directory. Then exposes the list of final resources in `generatedResources`.
  */
object ResourceGeneratorPlugin extends AutoPlugin {

  object autoImport {

    val resourcesToPropagate = settingKey[List[(File, String)]] {
      "List of resources to propagate and their destination folders"
    }

    val generatedResources = settingKey[List[String]] {
      "List of final resources to propagate"
    }

  }

  import autoImport._

  override def projectSettings: Seq[Setting[_]] = List(
    generatedResources := resourcesToPropagate.value.map {
      case (resource, "/")         => resource.name
      case (resource, destination) => s"$destination/${resource.name}"
    },
    resourcesToPropagate                   := Nil,
    Compile / unmanagedResourceDirectories += target.value / "resources-to-propagate",
    Compile / resourceGenerators += Def.task {
      resourcesToPropagate.value.map { case (resource, destinationFolder) =>
        val destination = target.value / "resources-to-propagate" /
          destinationFolder / resource.name

        println(s"Copying $resource to $destination")

        IO.copyFile(resource, destination)

        destination
      }
    }.taskValue
  )

}
