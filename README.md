This plugin generates default GitHub Actions workflows, documentation templates and configuration files for @alejandrohdezma's Scala libraries repositories.

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-ci" % "1.10.1")
```

## Usage

Once the plugin has been installed just execute `sbt generateCiFiles` to automatically add all the documentation, workflows and settings files to the project. You just need to ensure the `sbt generateCiFiles` command is added to any of the propagated workflows so for example when Scala Steward is used to update this plugin's version in another project, new changes get propagated.


## What files does it generate?

### [docs/AUTHORS.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/AUTHORS.md) (copied as docs/AUTHORS.md)

Contains both the list of contributors and project collaborators.

Need the `COLLABORATORS` and `CONTRIBUTORS` `mdocVariables`.

See https://github.com/alejandrohdezma/sbt-github for information on how to get this variables.


### [docs/CODE_OF_CONDUCT.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/CODE_OF_CONDUCT.md) (copied as docs/CODE_OF_CONDUCT.md)

Code of conduct for the repository. Links to the Scala Code of Conduct.


### [docs/CONTRIBUTING.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/CONTRIBUTING.md) (copied as docs/CONTRIBUTING.md)

Explains how a user can contribute to the project.

Need the `NAME`, `REPO`, `ORG_NAME` and `ORG_EMAIL` `mdocVariables`.

See https://github.com/alejandrohdezma/sbt-github for information on how to get this variables.


### [docs/LICENSE.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/LICENSE.md) (copied as docs/LICENSE.md)




### [docs/NOTICE.md](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/NOTICE.md) (copied as docs/NOTICE.md)

Contains the copyright notices for the organization/owner.

Need the `NAME`, `YEAR_RANGE`, `ORG_NAME` and `LICENSE` `mdocVariables`.

See https://github.com/alejandrohdezma/sbt-github for information on how to get this variables.


### [.github/release-drafter.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/release-drafter.yml) (copied as .github/release-drafter.yml)




### [.github/workflows/ci.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml) (copied as .github/workflows/ci.yml)

Runs `sbt ci-test` on the project (this task should be added to the project as a command alias containing the 
necessary steps to compile, check formatters, launch tests, upload coverage...).

It will launch on pushes to the `main` branch as well as `pull_request` events.

An example of this `ci-test` alias can be found in https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

It will also do the following, depending on the event:

- On `push` events:
  - It will draft the next release following the configuration in `.github/release-drafter.yml`.
  - It will update PRs that have the `auto-merge` feature enabled and are out-of-sync with the `main` branch.
- On `pull_request` events:
  - It will automatically approve `Scala Steward` PRs.
  - On Scala Steward PRs it will launch formatters and this plugin's `generateCiFiles` task and push the results to 
    the same PR.


### [.github/workflows/release.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/release.yml) (copied as .github/workflows/release.yml)

This workflow performs two tasks:

- Creates a release of the project by running `sbt ci-publish` (this task should be added to the project as a command 
  alias containing the necessary steps to do a release). An example of the `ci-publish` alias can be found in
  https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

- Runs `sbt ci-docs` on the project and pushes a commit with the changes (the `ci-docs` task should be added to the 
  project as a command alias containing the necessary steps to update documentation: re-generate docs files, 
  publish websites, update headers...). And example of the `ci-docs` alias can be found in
  https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt.

This workflow will launch on pushed tags. Alternatively one can launch it manually using a "workflow dispatch" to
create a snapshot release.


### [.gitignore](https://github.com/alejandrohdezma/sbt-ci/blob/main/.gitignore) (copied as .gitignore)

Default .gitignore for the project.



## Using this project as a template

This project is prepared to be used as a template. For a minimum set of changes you just need to:

- Update the list of resources to propagate in `build.sbt` (check for `resourcesToPropagate` setting).
- Change the `organization` setting to your own in `build.sbt`.
- Release the new plugin to Sonatype or another kind of Maven repository.

`README.md` should be auto-updated with the list of new resources once you execute `sbt ci-docs`. You can add some descriptions (as comments at the top of the file) to the propagated resources that will then be added to this file.