# @DESCRIPTION@

[![][github-action-badge]][github-action] [![][maven-badge]][maven] [![][steward-badge]][steward]

```scala mdoc:toc

```

## Introduction

This plugin generates default Github Actions workflows, documentation templates, configuration files, secrets and repository settings for [@alejandrohdezma](https://github.com/alejandrohdezma)'s Scala libraries repositories.

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("com.alejandrohdezma" % "sbt-ci" % "@VERSION@")
```

## Usage

Once the plugin has been installed just execute `sbt generateCiFiles` to automatically add all the documentation, workflows and settings files to the project. Once these files are committed it will automatically update the plugin (and therefore the files) by using Scala Steward.

## What files does it generate?

### Github Actions workflows

The Github Actions workflow can be found in the [`.github/workflows`](https://github.com/alejandrohdezma/sbt-ci/tree/main/.github/workflows) directory.
If any of them need settings, they will live under [`.github`](https://github.com/alejandrohdezma/sbt-ci/tree/main/.github).

| File                                                                                                                   | Copied as...                               | Enabled on...               | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   |
| ---------------------------------------------------------------------------------------------------------------------- | ------------------------------------------ | --------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| [ci.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml)                                 | `.github/workflows/ci.yml`                 | Pushes to main and PRs      | Runs `sbt ci-test` on the project. This task should be added to the project as a command alias containing the necessary steps to compile, check formatters, launch tests and upload coverage (if necessary). An example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt#L5). Also labels PRs automatically depending on the base branch following [this configuration file](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/pr-labeler.yml) (also generated as `.github/pr-labeler.yml`). On Scala Steward PRs it will launch formatters and this plugin's `generateCiFiles` task and push the results to the same PR. Finally it will merge Scala Steward that have succeed. |
| [docs.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/docs.yml)                             | `.github/workflows/docs.yml`               | Releases and manually       | Runs `sbt ci-docs` on the project and pushes a commit with the changes. The `ci-docs` task should be added to the project as a command alias containing the necessary steps to update documentation (re-generate docs files, publish websites, update headers...). And example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt#L6).                                                                                                                                                                                                                                                                                                                                                       |
| [release.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/release.yml)                       | `.github/workflows/release.yml`            | Releases and pushes to main | Creates a release of the project by running `sbt ci-publish`. This task should be added to the project as a command alias containing the necessary steps to do a release. An example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/main/build.sbt#L7).                                                                                                                                                                                                                                                                                                                                                                                                                                                 |
| [draft-next-release.yml](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/draft-next-release.yml) | `.github/workflows/draft-next-release.yml` | Merging pull-requests       | This workflow drafts your next release notes as pull requests are merged into main. Given the current configuration, it creates categories depending on the PRs labels. An example of a generated release body can be found [here](https://github.com/alejandrohdezma/sbt-github/releases/tag/v0.7.1).                                                                                                                                                                                                                                                                                                                                                                                                                                        |

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

## What settings spread?

The following repository settings will be enforced on every repository:

- **Wikis** will be **disabled**.
- **Branches** will be **deleted after merge** them.
- The **default branch** will be **main**.
- **Squash merging** will be **enabled**.
- **Merge commits** will be **enabled**.
- **Rebase merging** will be **disabled**.

It will also spread a set of **labels** to be used on issues & pull-requests (detailed [here](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/settings.yml#L26-L74)).

Lastly it will add branch protection on the **main** branch requiring:

- At least **1** pull request review from a code-owner.
- Pull-request branches should be **up-to-date** with **main**.
- The [ci.yml workflow](https://github.com/alejandrohdezma/sbt-ci/blob/main/.github/workflows/ci.yml) should pass correctly.

> Admins will be allowed to bypass this protection and merge PRs on any condition.

[github-action]: https://github.com/alejandrohdezma/sbt-ci/actions
[github-action-badge]: https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Falejandrohdezma%2Fsbt-ci%2Fbadge%3Fref%3Dmain&style=flat
[maven]: https://search.maven.org/search?q=g:%20com.alejandrohdezma%20AND%20a:sbt-ci
[maven-badge]: https://maven-badges.herokuapp.com/maven-central/com.alejandrohdezma/sbt-ci/badge.svg?kill_cache=1
[steward]: https://scala-steward.org
[steward-badge]: https://img.shields.io/badge/Scala_Steward-helping-brightgreen.svg?style=flat&logo=data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAA4AAAAQCAMAAAARSr4IAAAAVFBMVEUAAACHjojlOy5NWlrKzcYRKjGFjIbp293YycuLa3pYY2LSqql4f3pCUFTgSjNodYRmcXUsPD/NTTbjRS+2jomhgnzNc223cGvZS0HaSD0XLjbaSjElhIr+AAAAAXRSTlMAQObYZgAAAHlJREFUCNdNyosOwyAIhWHAQS1Vt7a77/3fcxxdmv0xwmckutAR1nkm4ggbyEcg/wWmlGLDAA3oL50xi6fk5ffZ3E2E3QfZDCcCN2YtbEWZt+Drc6u6rlqv7Uk0LdKqqr5rk2UCRXOk0vmQKGfc94nOJyQjouF9H/wCc9gECEYfONoAAAAASUVORK5CYII=
