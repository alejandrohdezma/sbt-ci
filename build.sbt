ThisBuild / scalaVersion                  := _root_.scalafix.sbt.BuildInfo.scala212
ThisBuild / organization                  := "com.alejandrohdezma"
ThisBuild / pluginCrossBuild / sbtVersion := "1.2.8"
ThisBuild / versionPolicyIntention        := Compatibility.BinaryCompatible

addCommandAlias("ci-test", "fix --check; versionPolicyCheck; mdoc; publishLocal")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")
addCommandAlias("ci-publish", "versionCheck; github; ci-release")

val `sbt-mdoc` = "org.scalameta" % "sbt-mdoc" % "[2.0,)" % Provided // scala-steward:off

lazy val documentation = project
  .enablePlugins(MdocPlugin)
  .settings(mdocIn := file(".github") / "docs")
  .settings(mdocOut := file("."))
  .settings(mdocVariables += "PROPAGATED_RESOURCES" -> propagatedResouces.value)

lazy val `sbt-ci` = module
  .settings(addSbtPlugin(`sbt-mdoc`))
  .enablePlugins(SbtPlugin)
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoKeys += BuildInfoKey("repo", repository.value.map(_.name)))
  .settings(buildInfoPackage := "sbt.ci")
  .enablePlugins(ResourceGeneratorPlugin)
  .settings(resourcesToPropagate += ".github/docs/LICENSE.md" -> ".github/docs/LICENSE.md")
  .settings(resourcesToPropagate += ".github/release.yml" -> ".github/release.yml")
  .settings(resourcesToPropagate += ".github/workflows/ci.yml" -> ".github/workflows/ci.yml")
  .settings(resourcesToPropagate += ".github/workflows/release.yml" -> ".github/workflows/release.yml")
  .settings(resourcesToPropagate += ".gitignore" -> ".gitignore")

lazy val propagatedResouces = Def.setting {
  (`sbt-ci` / resourcesToPropagateDocs).value.map { case (resource, destination, description) =>
    val repo   = repository.value.map(_.name).getOrElse("")
    val branch = repository.value.map(_.defaultBranch).getOrElse("")

    s"### :octocat: [$resource](https://github.com/$repo/blob/$branch/$resource) (copied as $destination)\n\n$description"
  }.mkString("", "\n\n", "\n\n")
}
