# Default workflow, documentation and root files for scala libraries

[![][github-action-badge]][github-action] [![][maven-badge]][maven] [![][steward-badge]][steward]

---

- [Introduction](#introduction)
- [Installation](#installation)
- [Usage](#usage)
- [What files does it generate?](#what-files-does-it-generate)
  - [Github Actions workflows](#github-actions-workflows)
    - [`ci.yml`](#ciyml)
    - [`release.yml`](#releaseyml)
  - [Documentation templates](#documentation-templates)
  - [Root files](#root-files)
- [How are files generated?](#how-are-files-generated)

## Introduction

This plugin generates default Github Actions workflows, documentation templates and configuration files for [@alejandrohdezma](https://github.com/alejandrohdezma)'s Scala libraries repositories.

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-ci" % "1.7.6")
```

## Usage

Once the plugin has been installed just execute `sbt generateCiFiles` to automatically add all the documentation, workflows and settings files to the project. Once these files are committed it will automatically update the plugin (and therefore the files) by using Scala Steward.

## What files does it generate?

### Github Actions workflows

The Github Actions workflow can be found in the [`.github/workflows`](https://github.com/alejandrohdezma/sbt-ci/tree/main/.github/workflows) directory.
If any of them need settings, they will live under [`.github`](https://github.com/alejandrohdezma/sbt-ci/tree/main/.github).

#### [`ci.yml`](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml)

Copied to `.github/workflows/ci.yml`. Runs `sbt ci-test` on the project (this task should be added to the project as a command alias containing the necessary steps to compile, check formatters, launch tests, upload coverage...).

It will launch on pushes to the `main` branch as well as `pull_request` events.

An example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt#L5).

It will also do the following, depending on the event:

- On `push` events:
  - It will draft the next release following the configuration in [`release-drafter.yml`](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/release-drafter.yml).
  - It will update PRs that have the `auto-merge` feature enabled and are out-of-sync with the `main` branch.
- On `pull_request` events:
  - It will automatically approve `Scala Steward` PRs.
  - On Scala Steward PRs it will launch formatters and this plugin's `generateCiFiles` task and push the results to the same PR.

#### [`release.yml`](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/release.yml)

Copied to `.github/workflows/release.yml`.

- Creates a release of the project by running `sbt ci-publish` (this task should be added to the project as a command alias containing the necessary steps to do a release). An example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt#L7).
- Runs `sbt ci-docs` on the project and pushes a commit with the changes (the `ci-docs` task should be added to the project as a command alias containing the necessary steps to update documentation: re-generate docs files, publish websites, update headers...). And example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt#L6).

It will launch on new releases. Alternatively one can launch it manually using a "workflow dispatch" to create a snapshot release.

> All the workflows need specific secrets to be enabled in the repository.

### Documentation templates

These documentation templates are expected to be used in conjunction with [mdoc](https://scalameta.org/mdoc/docs/installation.html). Some of them use special `mdocVariables` that can be automatically included by using [sbt-github-mdoc](https://alejandrohdezma.github.io/sbt-github/sbt-mdoc) plugin. All of these templates should be compiled and processed into their final files when launching `sbt ci-docs` in the [`docs.yml`](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/docs.yml) workflow.

| File                                                                                                | Copied as...              | Needs `mdocVariables`?                       | Description                                                                                                    | Compiled example                                                                                |
| --------------------------------------------------------------------------------------------------- | ------------------------- | -------------------------------------------- | -------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| [`AUTHORS.md`](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/AUTHORS.md)                 | `docs/AUTHORS.md`         | `COLLABORATORS` & `CONTRIBUTORS`             | Contains both the list of contributors and project collaborators.                                              | [`AUTHORS.md`](https://github.com/alejandrohdezma/sbt-fix/blob/main/AUTHORS.md)                 |
| [`CODE_OF_CONDUCT.md`](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/CODE_OF_CONDUCT.md) | `docs/CODE_OF_CONDUCT.md` | Nope                                         | Code of conduct for the repository. Links to the [Scala Code of Conduct](https://www.scala-lang.org/conduct/). | [`CODE_OF_CONDUCT.md`](https://github.com/alejandrohdezma/sbt-fix/blob/main/CODE_OF_CONDUCT.md) |
| [`CONTRIBUTING.md`](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/CONTRIBUTING.md)       | `docs/CONTRIBUTING.md`    | `NAME`, `REPO`, `ORG_NAME` & `ORG_EMAIL`     | Explains how a user can contribute to the project.                                                             | [`CONTRIBUTING.md`](https://github.com/alejandrohdezma/sbt-fix/blob/main/CONTRIBUTING.md)       |
| [`LICENSE.md`](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/LICENSE.md)                 | `docs/LICENSE.md`         | `YEAR_RANGE` & `COPYRIGHT_OWNER`             | Default license for the project.                                                                               | [`LICENSE.md`](https://github.com/alejandrohdezma/sbt-fix/blob/main/LICENSE.md)                 |
| [`NOTICE.md`](https://github.com/alejandrohdezma/sbt-ci/blob/main/docs/NOTICE.md)                   | `docs/NOTICE.md`          | `NAME`, `YEAR_RANGE`, `ORG_NAME` & `LICENSE` | Contains the copyright notices for the organization/owner.                                                     | [`NOTICE.md`](https://github.com/alejandrohdezma/sbt-fix/blob/main/NOTICE.md)                   |

### Root files

These files will be copied to the root directory of the remote project:

| File         | Description                                              |
| ------------ | -------------------------------------------------------- |
| `.gitignore` | Typical git ignoring configurations for a Scala project. |

## How are files generated?

After each release of this plugin Scala Steward will update the plugin version in all the repositories that use it and when the [`ci.yml` workflow](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml) workflow gets launched, it will execute `sbt generateCiFiles` (creating, updating or deleting files handled by the plugin).

[github-action]: https://github.com/alejandrohdezma/sbt-ci/actions
[github-action-badge]: https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Falejandrohdezma%2Fsbt-ci%2Fbadge%3Fref%3Dmain&style=flat
[maven]: https://search.maven.org/search?q=g:%20com.alejandrohdezma%20AND%20a:sbt-ci
[maven-badge]: https://maven-badges.herokuapp.com/maven-central/com.alejandrohdezma/sbt-ci/badge.svg?kill_cache=1
[steward]: https://scala-steward.org
[steward-badge]: https://img.shields.io/badge/Scala_Steward-helping-brightgreen.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
