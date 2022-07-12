This plugin generates default GitHub Actions workflows, documentation templates and configuration files for @alejandrohdezma's Scala libraries repositories.

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-ci" % "2.5.0")
```

## Usage

Once the plugin has been installed just execute `sbt generateCiFiles` to automatically add all the documentation, workflows and settings files to the project. You just need to ensure the `sbt generateCiFiles` command is added to any of the propagated workflows so for example when Scala Steward is used to update this plugin's version in another project, new changes get propagated.


## What files does it generate?

### :octocat: [docs/CODE_OF_CONDUCT.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/CODE_OF_CONDUCT.md) (copied as docs/CODE_OF_CONDUCT.md)

Code of conduct for the repository. Links to the Scala Code of Conduct.


### :octocat: [docs/CONTRIBUTING.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/CONTRIBUTING.md) (copied as docs/CONTRIBUTING.md)

Explains how a user can contribute to the project.

Need the `NAME`, `REPO`, `ORG_NAME` and `ORG_EMAIL` `mdocVariables`.

See https://github.com/alejandrohdezma/sbt-github for information on how to get this variables.


### :octocat: [docs/LICENSE.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/LICENSE.md) (copied as docs/LICENSE.md)




### :octocat: [.github/release.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/release.yml) (copied as .github/release.yml)

This file contains the template for the "auto-generated release notes"


### :octocat: [.github/FUNDING.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/FUNDING.yml) (copied as .github/FUNDING.yml)




### :octocat: [.github/workflows/ci.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml) (copied as .github/workflows/ci.yml)

This workflow behaves different depending on the event:

- On `push` events:
  - It will update PRs that are out-of-sync with the `main` branch.
- On `pull_request` events:
  - Runs `sbt ci-test` on the project on differnt JDKs (this task should be added to the project as a command alias
    containing the necessary steps to compile, check formatters, launch tests, upload coverage...).

An example of this `ci-test` alias can be found in https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

It will also do the following:

- It will automatically label PRs based on head branch.
- It will automatically enable auto-merge on `Scala Steward` PRs.
- On Scala Steward PRs it will launch formatters and this plugin's `generateCiFiles` task and push the results to the
  same PR.


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

`README.md` should be auto-updated with the list of new resources once you execute `sbt ci-docs`. You can add some descriptions (as comments at the top of the file) to the propagated resources that will then be added to this file.

## Contributors to this project 

| <a href="https://github.com/alejandrohdezma"><img alt="alejandrohdezma" src="https://avatars.githubusercontent.com/u/9027541?v=4&s=120" width="120px" /></a> | <a href="https://github.com/juanpedromoreno"><img alt="juanpedromoreno" src="https://avatars.githubusercontent.com/u/4879373?v=4&s=120" width="120px" /></a> |
| :--: | :--: |
| <a href="https://github.com/alejandrohdezma"><sub><b>alejandrohdezma</b></sub></a> | <a href="https://github.com/juanpedromoreno"><sub><b>juanpedromoreno</b></sub></a> |