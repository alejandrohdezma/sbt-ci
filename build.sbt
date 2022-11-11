ThisBuild / scalaVersion                  := _root_.scalafix.sbt.BuildInfo.scala212
ThisBuild / organization                  := "com.alejandrohdezma"
ThisBuild / pluginCrossBuild / sbtVersion := "1.2.8"

addCommandAlias("ci-test", "fix --check; mdoc; publishLocal")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")
addCommandAlias("ci-publish", "github; ci-release")

lazy val documentation = project
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := file("."))
  .settings(mdocVariables += "ORGANIZATION" -> organization.value)
  .settings(mdocVariables += "PROPAGATED_RESOURCES" -> propagatedResouces.value)

lazy val `sbt-ci` = module
  .enablePlugins(SbtPlugin)
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoKeys += BuildInfoKey("repo", repository.value.map(_.name)))
  .settings(buildInfoPackage := "sbt.ci")
  .enablePlugins(ResourceGeneratorPlugin)
  .settings(resourcesToPropagate += "docs/LICENSE.md" -> "docs/LICENSE.md")
  .settings(resourcesToPropagate += ".github/release.yml" -> ".github/release.yml")
  .settings(resourcesToPropagate += ".github/workflows/ci.yml" -> ".github/workflows/ci.yml")
  .settings(resourcesToPropagate += ".github/workflows/main.yml" -> ".github/workflows/main.yml")
  .settings(resourcesToPropagate += ".github/workflows/release.yml" -> ".github/workflows/release.yml")
  .settings(resourcesToPropagate += ".gitignore" -> ".gitignore")

lazy val propagatedResouces = Def.setting {
  (`sbt-ci` / resourcesToPropagateDocs).value.map { case (resource, destination, description) =>
    val repo   = repository.value.map(_.name).getOrElse("")
    val branch = repository.value.map(_.defaultBranch).getOrElse("")

    s"### :octocat: [$resource](https://github.com/$repo/blob/$branch/$resource) (copied as $destination)\n\n$description"
  }.mkString("", "\n\n", "\n\n")
}
