ThisBuild / scalaVersion                  := "2.12.15"
ThisBuild / organization                  := "com.alejandrohdezma"
ThisBuild / pluginCrossBuild / sbtVersion := "1.2.8"

addCommandAlias("ci-test", "fix --check; mdoc; scripted")
addCommandAlias("ci-docs", "github; mdoc; headerCreateAll")
addCommandAlias("ci-publish", "github; ci-release")

//So `generateCiFiles` doesn't fail in its own build
addCommandAlias("generateCiFiles", "show name")

lazy val documentation = project
  .enablePlugins(MdocPlugin)
  .settings(mdocOut := file("."))

lazy val `sbt-ci` = module
  .enablePlugins(SbtPlugin)
  .settings(scriptedBatchExecution := false)
  .settings(scriptedBufferLog := false)
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
  .settings(scriptedLaunchOpts += s"-Dplugin.organization=${organization.value}")
  .settings(scriptedLaunchOpts += s"-Dplugin.name=${name.value}")
  .settings(scriptedLaunchOpts += s"-Dgenerated.files=${generatedResources.value.mkString(",")}")
  .enablePlugins(BuildInfoPlugin)
  .settings(buildInfoKeys += generatedResources)
  .settings(buildInfoKeys += BuildInfoKey.map(repository) { case (k, v) => k -> v.map(_.name).getOrElse("unknown") })
  .settings(buildInfoPackage := "sbt.ci")
  .enablePlugins(ResourceGeneratorPlugin)
  .settings(resourcesToPropagate += file("docs/AUTHORS.md") -> "docs")
  .settings(resourcesToPropagate += file("docs/CODE_OF_CONDUCT.md") -> "docs")
  .settings(resourcesToPropagate += file("docs/CONTRIBUTING.md") -> "docs")
  .settings(resourcesToPropagate += file("docs/LICENSE.md") -> "docs")
  .settings(resourcesToPropagate += file("docs/NOTICE.md") -> "docs")
  .settings(resourcesToPropagate += file(".github/release-drafter.yml") -> ".github")
  .settings(resourcesToPropagate += file(".github/workflows/ci.yml") -> ".github/workflows")
  .settings(resourcesToPropagate += file(".github/workflows/release.yml") -> ".github/workflows")
  .settings(resourcesToPropagate += file(".gitignore") -> "/")
