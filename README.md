This plugin generates default GitHub Actions workflows, documentation templates and configuration files for @alejandrohdezma's Scala libraries repositories.

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-ci" % "2.11.0")
```

## Usage

Once the plugin has been installed just execute `sbt generateCiFiles` to
automatically add all the documentation, workflows and settings files to the
project. You just need to ensure the `sbt generateCiFiles` is executed when
this plugin gets updated in your repository to bring in new changes. If you use
[Scala Steward](https://github.com/scala-steward-org/scala-steward)
to keep your repositories up-to-date it is super easy. Just add the following
lines to the `.scala-steward.conf` in the root of your repositories:

```conf
postUpdateHooks = [
  {
    command = ["sbt", "generateCiFiles"],
    commitMessage = "Run `sbt generateCiFiles`",
    groupId = "com.alejandrohdezma",
    artifactId = "sbt-ci"
  }
]
```

### Excluding files

You can exclude certain files by using the `excludedFiles` setting:

```sbt
ThisBuild / excludedFiles += "**/*.md"
```

## What files does it generate?

### :octocat: [docs/LICENSE.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/LICENSE.md) (copied as docs/LICENSE.md)



### :octocat: [.github/release.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/release.yml) (copied as .github/release.yml)

This file contains the template for the "auto-generated release notes"

### :octocat: [.github/workflows/ci.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml) (copied as .github/workflows/ci.yml)

Runs `sbt ci-test` on the project on differnt JDKs (this task should be added to the project as a command alias
containing the necessary steps to compile, check formatters, launch tests...).

An example of this `ci-test` alias can be found in https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

It will also do the following:

- It will automatically label PRs based on head branch.
- It will automatically enable auto-merge on `Scala Steward` PRs.

### :octocat: [.github/workflows/release.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/release.yml) (copied as .github/workflows/release.yml)

This workflow performs two tasks:

- Creates a release of the project by running `sbt ci-publish` (this task should be added to the project as a command
  alias containing the necessary steps to do a release). An example of the `ci-publish` alias can be found in
  https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

- Runs `sbt ci-docs` on the project and pushes a commit with the changes (the `ci-docs` task should be added to the
  project as a command alias containing the necessary steps to update documentation: re-generate docs files,
  publish websites, update headers...). An example of the `ci-docs` alias can be found in
  https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

This workflow will launch on pushed tags. Alternatively one can launch it manually using a "workflow dispatch" to
create a snapshot release (this won't trigger the documentation update).

### :octocat: [.gitignore](https://github.com/alejandrohdezma/sbt-ci/blob/main/.gitignore) (copied as .gitignore)

Default .gitignore for the project.



## Using this project as a template

This project is prepared to be used as a template. For a minimum set of changes you just need to:

- Update the list of resources to propagate in `build.sbt` (check for `resourcesToPropagate` setting).
- Change the `organization` setting to your own in `build.sbt`.
- Release the new plugin to Sonatype or another kind of Maven repository.

`README.md` should be auto-updated with the list of new resources once you execute `sbt ci-docs`. You can add some descriptions (as comments at the top of the file) to the propagated resources that will then be added to this file. See [sbt-propagate docs](https://github.com/alejandrohdezma/sbt-propagate) for more information about what can be done.

## Contributors to this project 

| <a href="https://github.com/alejandrohdezma"><img alt="alejandrohdezma" src="https://avatars.githubusercontent.com/u/9027541?v=4&s=120" width="120px" /></a> | <a href="https://github.com/juanpedromoreno"><img alt="juanpedromoreno" src="https://avatars.githubusercontent.com/u/4879373?v=4&s=120" width="120px" /></a> |
| :--: | :--: |
| <a href="https://github.com/alejandrohdezma"><sub><b>alejandrohdezma</b></sub></a> | <a href="https://github.com/juanpedromoreno"><sub><b>juanpedromoreno</b></sub></a> |
