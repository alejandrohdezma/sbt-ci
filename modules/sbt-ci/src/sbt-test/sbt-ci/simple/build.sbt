import java.nio.file._

TaskKey[Unit]("checkGeneratedFiles", "Checks files are generated") := {
  val resources = sys.props("generated.files").split(",")

  assert(resources.nonEmpty, s"Resources list is empty")

  resources.foreach { resource =>
    val path = Paths.get(resource)

    assert(Files.exists(path), s"Resource $path does not exist")
  }
}
