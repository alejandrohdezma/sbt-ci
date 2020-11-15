ThisBuild / scalaVersion                  := "2.12.12"
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
  .settings(scriptedLaunchOpts += s"-Dplugin.version=${version.value}")
  .settings(Compile / unmanagedResources += file(".gitignore"))
  .settings(Compile / unmanagedResources += file(".gitignore"))
  .settings(Compile / unmanagedResources += file("docs/AUTHORS.md"))
  .settings(Compile / unmanagedResources += file("docs/CODE_OF_CONDUCT.md"))
  .settings(Compile / unmanagedResources += file("docs/CONTRIBUTING.md"))
  .settings(Compile / unmanagedResources += file("docs/LICENSE.md"))
  .settings(Compile / unmanagedResources += file("docs/NOTICE.md"))
  .settings(Compile / unmanagedResources += file(".github/pr-labeler.yml"))
  .settings(Compile / unmanagedResources += file(".github/release-drafter.yml"))
  .settings(Compile / unmanagedResources += file(".github/settings.yml"))
  .settings(Compile / unmanagedResources += file(".github/workflows/ci.yml"))
  .settings(Compile / unmanagedResources += file(".github/workflows/docs.yml"))
  .settings(Compile / unmanagedResources += file(".github/workflows/draft-next-release.yml"))
  .settings(Compile / unmanagedResources += file(".github/workflows/release.yml"))
  .settings(Compile / unmanagedResources += file(".github/workflows/scala-steward.yml"))
