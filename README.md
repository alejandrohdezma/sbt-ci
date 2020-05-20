# Default workflow, documentation, root files and settings for scala libraries

* [Introduction](#introduction)
* [What files spread?](#what-files-spread)
    + [Github Actions workflows](#github-actions-workflows)
    + [Documentation templates](#documentation-templates)
    + [Root files](#root-files)
* [How are files spread?](#how-are-files-spread)
* [What settings spread?](#what-settings-spread)
* [What secrets spread?](#what-secrets-spread)
* [How to spread a new secret?](#how-to-spread-a-new-secret)
* [How to add a new repository?](#how-to-add-a-new-repository)
* [Scala Steward](#scala-steward-)
* [How to trigger spreading?](#how-to-trigger-spreading)

## Introduction

Contains and spreads default Github Actions workflows, documentation templates, configuration files, secrets and repository settings for [@alejandrohdezma](https://github.com/alejandrohdezma)'s Scala libraries repositories.

## What files spread?

### Github Actions workflows

The Github Actions workflow can be found in the [`workflows`](https://github.com/alejandrohdezma/.github/tree/master/workflows) directory. If any of them need settings, they will live under [`workflows/settings`](https://github.com/alejandrohdezma/.github/tree/master/workflows/settings).

| File                                                                                                        | Copied as...                            | Enabled on...                   | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        |
|-------------------------------------------------------------------------------------------------------------|-----------------------------------------|---------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [ci.yml](https://github.com/alejandrohdezma/.github/blob/master/workflows/ci.yml)                           | `.github/workflows/ci.yml`              | Pushes to master and PRs        | Runs `sbt ci-test` on the project. This task should be added to the project as a command alias containing the necessary steps to compile, check formatters, launch tests and upload coverage (if necessary). An example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/master/build.sbt#L6). Also labels PRs automatically depending on the base branch following [this configuration file](https://github.com/alejandrohdezma/.github/blob/master/workflows/settings/pr-labeler.yml) (also copied to remote repository as `.github/pr-labeler.yml`). Finally merges `scala-steward` PRs that have succeed.                                                                                                                                                                                                                                                                                                                                                                                                 |
| [docs.yml](https://github.com/alejandrohdezma/.github/blob/master/workflows/docs.yml)                       | `.github/workflows/docs.yml`            | Releases                        | Runs `sbt ci-docs` on the project, runs the changelog generation and pushes a commit with the changes. The `ci-docs` task should be added to the project as a command alias containing the necessary steps to update documentation (re-generate docs files, publish micro-sites, update headers...). And example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/master/build.sbt#L7). For the generation of the `CHANGELOG.md` file it will use [this configuration](https://github.com/alejandrohdezma/.github/blob/master/workflows/docs.yml#L45-L68). An example of a generated changelog file can be found [here](https://github.com/alejandrohdezma/sbt-fix/blob/master/CHANGELOG.md). |
| [release.yml](https://github.com/alejandrohdezma/.github/blob/master/workflows/release.yml)                 | `.github/workflows/release.yml`         | Releases and pushes to master   | Creates a release of the project by running `sbt ci-publish`. This task should be added to the project as a command alias containing the necessary steps to do a release. An example of this alias can be found [here](https://github.com/alejandrohdezma/sbt-github/blob/master/build.sbt#L8).                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| [release-drafter.yml](https://github.com/alejandrohdezma/.github/blob/master/workflows/release-drafter.yml) | `.github/workflows/settings/release-drafter.yml` | Merging pull-requests                | Settings for [Release Drafter](https://github.com/apps/release-drafter). This app drafts your next release notes as pull requests are merged into master. Given current configuration, it creates categories depending on the PRs labels. An example of generated release body can be found [here](https://github.com/alejandrohdezma/sbt-github/releases/tag/v0.7.1).                                                                                                                                                                                                                                                                               |

> Some workflows need specific secrets to be enabled in the repository. These secrets will be automatically added to a repository once it is added to the [auto-update](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/auto-update.yml#L18) workflow.

### Documentation templates

These documentation templates expect to be used in conjunction with [mdoc](https://scalameta.org/mdoc/docs/installation.html). Some of them use special `mdocVariables` that can be automatically included by using [sbt-github-mdoc](https://github.com/alejandrohdezma/sbt-github#mdoc-integration) plugin. All of these templates should be compiled and processed into their final files when launching `sbt ci-docs` in the [`docs.yml`](https://github.com/alejandrohdezma/.github/blob/master/workflows/docs.yml) workflow.

| File                                                                                                            | Copied as...              | Needs `mdocVariables`?                       | Description                                                                                                    | Compiled example                                                                                  |
|-----------------------------------------------------------------------------------------------------------------|---------------------------|----------------------------------------------|----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| [`AUTHORS.md`](https://github.com/alejandrohdezma/.github/blob/master/documentation/AUTHORS.md)                 | `docs/AUTHORS.md`         | `COLLABORATORS` & `CONTRIBUTORS`             | Contains both the list of contributors and project collaborators.                                              | [`AUTHORS.md`](https://github.com/alejandrohdezma/sbt-fix/blob/master/AUTHORS.md)                 |
| [`CODE_OF_CONDUCT.md`](https://github.com/alejandrohdezma/.github/blob/master/documentation/CODE_OF_CONDUCT.md) | `docs/CODE_OF_CONDUCT.md` | Nope                                         | Code of conduct for the repository. Links to the [Scala Code of Conduct](https://www.scala-lang.org/conduct/). | [`CODE_OF_CONDUCT.md`](https://github.com/alejandrohdezma/sbt-fix/blob/master/CODE_OF_CONDUCT.md) |
| [`CONTRIBUTING.md`](https://github.com/alejandrohdezma/.github/blob/master/documentation/CONTRIBUTING.md)       | `docs/CONTRIBUTING.md`    | `NAME`, `REPO`, `ORG_NAME` & `ORG_EMAIL`     | Explains how a user can contribute to the project.                                                             | [`CONTRIBUTING.md`](https://github.com/alejandrohdezma/sbt-fix/blob/master/CONTRIBUTING.md)       |
| [`LICENSE.md`](https://github.com/alejandrohdezma/.github/blob/master/documentation/LICENSE.md)                 | `docs/LICENSE.md`         | `YEAR_RANGE` & `COPYRIGHT_OWNER`             | Default license for the project.                                                                               | [`LICENSE.md`](https://github.com/alejandrohdezma/sbt-fix/blob/master/LICENSE.md)                 |
| [`NOTICE.md`](https://github.com/alejandrohdezma/.github/blob/master/documentation/NOTICE.md)                   | `docs/NOTICE.md`          | `NAME`, `YEAR_RANGE`, `ORG_NAME` & `LICENSE` | Contains the copyright notices for the organization/owner.                                                     | [`NOTICE.md`](https://github.com/alejandrohdezma/sbt-fix/blob/master/NOTICE.md)                   |

### Root files

These files will be copied to the root directory of the remote project:

| File           | Description                                                      |
|----------------|------------------------------------------------------------------|
| `.gitignore`   | Typical git ignoring configurations for a Scala project.         |

## How are files spread?

After each release of this repository the following steps run for each repository indicated in [auto-update](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/auto-update.yml#L18):

1. The new changes (including new files) are copied to the repository.
2. `sbt ci-test` is run on the repository.
    - If the check succeeds a commit with the changes will be pushed to the `master` branch.
    - If it fails a PR with the changes will be created.
    
> Note: the actual command run is `sbt ci-test -Dskip.coverage=true` since this build should not try to upload coverage. You can use the presence of this system property to disable coverage upload (if used). This is automatically done if using [Codecov](https://codecov.io/) via [`sbt-codecov`](https://github.com/alejandrohdezma/sbt-codecov)

## What settings spread?

The following repository settings will be enforced on every repository:

- **Wikis** will be **disabled**.
- **Branches** will be **deleted after merge** them.
- The **default branch** will be **master**.
- **Squash merging** will be **enabled**.
- **Merge commits** will be **enabled**.
- **Rebase merging** will be **disabled**.

It will also spread a set of **labels** to be used on issues & pull-requests (detailed [here](https://github.com/alejandrohdezma/.github/blob/master/.github/settings.yml#L26-L74)).

Lastly it will add a branch protection on the **master** branch requiring:

- At least **1** pull request review from a code-owner.
- Pull-request branches should be **up-to-date** with **master**.
- Both the [ci.yml workflow](https://github.com/alejandrohdezma/.github/blob/master/workflows/ci.yml) and the [pr-labeler.yml workflow](https://github.com/alejandrohdezma/.github/blob/master/workflows/pr-labeler.yml) should pass correctly.

> Admins will be allowed to bypass this protection and merge PRs on any condition.

## What secrets spread?

The following secrets will be spread to every repository:

- `PGP_PASSPHRASE`: The passphrase of the GPG key that will be used to signed artifacts.
- `PGP_SECRET`: The base64 encoded secret of the GPG key that will be used to signed artifacts.
- `SONATYPE_PASSWORD`: The password you will use to log into [Sonatype](https://oss.sonatype.org/).
- `SONATYPE_USERNAME`: The username you will use to log into [Sonatype](https://oss.sonatype.org/).
- `ADMIN_GITHUB_TOKEN`: A Github token with administrator permissions on the repository. It can be used to automatically merge PRs or other tasks where the default `GITHUB_TOKEN` is not enough.

> Most of these secrets are meant to be used by [sbt-ci-release](https://github.com/olafurpg/sbt-ci-release).

## How to spread a new secret?

1. Add it as you would normally do in any repository through [settings page](https://github.com/alejandrohdezma/.github/settings/secrets).
2. Edit [`.github/workflows/auto-update.yml`](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/auto-update.yml#L86) workflow and add the following line:

    ```diff
    env:
      PGP_PASSPHRASE: ${{ secrets.PGP_PASSPHRASE }}
      PGP_SECRET: ${{ secrets.PGP_SECRET }}
      SONATYPE_PASSWORD: ${{ secrets.SONATYPE_PASSWORD }}
      SONATYPE_USERNAME: ${{ secrets.SONATYPE_USERNAME }}
      ADMIN_GITHUB_TOKEN: ${{ secrets.ADMIN_GITHUB_TOKEN }}
    + YOUR_SECRET_NAME: ${{ secrets.YOUR_SECRET_NAME }}
    ```

## How to add a new repository?

Go to the [auto-update](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/auto-update.yml#L14) workflow and add the repository to the `repo` matrix.

> If you want to be able to trigger Scala Steward on the repository (along with the other repositories), add it also to the `repo` matrix in [scala-steward](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/scala-steward.yml#L12).

## [Scala Steward](https://github.com/fthomas/scala-steward) <img src="https://raw.githubusercontent.com/fthomas/scala-steward/master/data/images/scala-steward-logo-circle-0.png" height=20/>

This repository contains a workflow to launch Scala Steward in all the repositories annotated in the [auto-update](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/auto-update.yml#L14) workflow.

This workflow will launch in two conditions:

- Automatically on Monday, Wednesday and Friday at 5:00 UTC.
- Manually from a `repository_dispatch`. For that, execute the following from your local machine:

    ```
    curl -d "{\"event_type\": \"scala-steward\"}" -H "Content-Type: application/json" -H "Authorization: token ${GITHUB_TOKEN}" "https://api.github.com/repos/alejandrohdezma/.github/dispatches"
    ```

    > Remember to have a valid github token exported as `GITHUB_TOKEN` in your local environment:
    >
    > ```bash
    > export GITHUB_TOKEN="your_github_token"
    > ```

## How to trigger spreading?

Adding a new repository to the [auto-update](https://github.com/alejandrohdezma/.github/blob/master/.github/workflows/auto-update.yml) workflow will automatically trigger spreading of the latest repository release & secrets.

Also, spreading will be also triggered when a release is created.

On the other hand, after updating a secret, you can trigger spreading by executing the following command from your local machine:

```
curl -d "{\"event_type\": \"auto-update\"}" -H "Content-Type: application/json" -H "Authorization: token ${GITHUB_TOKEN}" "https://api.github.com/repos/alejandrohdezma/.github/dispatches"
```

> Remember to have a valid github token exported as `GITHUB_TOKEN` in your local environment:
>
> ```bash
> export GITHUB_TOKEN="your_github_token"
> ```
