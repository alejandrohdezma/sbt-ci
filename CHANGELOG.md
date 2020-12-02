# Changelog

## [v1.1.4](https://github.com/alejandrohdezma/sbt-ci/tree/v1.1.4) (2020-12-02)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v1.1.3...v1.1.4)

🐛 **Bug Fixes**

- `.github/scripts/setup-gpg` should have execute permissions [\#67](https://github.com/alejandrohdezma/sbt-ci/pull/67) ([alejandrohdezma](https://github.com/alejandrohdezma))

📈 **Dependency updates**

- Bump ridedott/merge-me-action from v1.8.66 to v1.8.67 [\#66](https://github.com/alejandrohdezma/sbt-ci/pull/66) ([dependabot[bot]](https://github.com/apps/dependabot))
- Bump ridedott/merge-me-action from v1.8.64 to v1.8.66 [\#65](https://github.com/alejandrohdezma/sbt-ci/pull/65) ([dependabot[bot]](https://github.com/apps/dependabot))
- Bump ridedott/merge-me-action from v1.8.61 to v1.8.64 [\#64](https://github.com/alejandrohdezma/sbt-ci/pull/64) ([dependabot[bot]](https://github.com/apps/dependabot))

## [v1.1.3](https://github.com/alejandrohdezma/sbt-ci/tree/v1.1.3) (2020-11-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v1.1.2...v1.1.3)

🐛 **Bug Fixes**

- We should use ADMIN\_GITHUB\_TOKEN for pushing workflows [\#63](https://github.com/alejandrohdezma/sbt-ci/pull/63) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v1.1.2](https://github.com/alejandrohdezma/sbt-ci/tree/v1.1.2) (2020-11-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v1.1.1...v1.1.2)

🐛 **Bug Fixes**

- Correct ghaction-import-gpg input names [\#62](https://github.com/alejandrohdezma/sbt-ci/pull/62) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Use `sbt` instead of `sbtn` [\#61](https://github.com/alejandrohdezma/sbt-ci/pull/61) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v1.1.1](https://github.com/alejandrohdezma/sbt-ci/tree/v1.1.1) (2020-11-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v1.1.0...v1.1.1)

🐛 **Bug Fixes**

- License file should not include header [\#60](https://github.com/alejandrohdezma/sbt-ci/pull/60) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v1.1.0](https://github.com/alejandrohdezma/sbt-ci/tree/v1.1.0) (2020-11-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v1.0.0...v1.1.0)

🚀 **Features**

- Add workflow for auto-rebasing PRs [\#58](https://github.com/alejandrohdezma/sbt-ci/pull/58) ([alejandrohdezma](https://github.com/alejandrohdezma))

🐛 **Bug Fixes**

- The project should be checked out before spreading settings [\#57](https://github.com/alejandrohdezma/sbt-ci/pull/57) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Spread settings should not be launched if commit contains "skip ci" [\#56](https://github.com/alejandrohdezma/sbt-ci/pull/56) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v1.0.0](https://github.com/alejandrohdezma/sbt-ci/tree/v1.0.0) (2020-11-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.29.0...v1.0.0)

## [v0.29.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.29.0) (2020-10-05)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.28.0...v0.29.0)

## [v0.28.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.28.0) (2020-10-02)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.27.0...v0.28.0)

**Merged pull requests:**

- Checkout differently on PRs/pushes [\#42](https://github.com/alejandrohdezma/sbt-ci/pull/42) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.27.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.27.0) (2020-09-16)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.26.0...v0.27.0)

**Merged pull requests:**

- Remove `ref` input from `checkout` in `ci.yml` [\#41](https://github.com/alejandrohdezma/sbt-ci/pull/41) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Add metals files to `.gitignore` [\#40](https://github.com/alejandrohdezma/sbt-ci/pull/40) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.26.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.26.0) (2020-07-30)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.25.0...v0.26.0)

**Closed issues:**

- Dependabot needs permission to see repo-manager [\#35](https://github.com/alejandrohdezma/sbt-ci/issues/35)

**Merged pull requests:**

- Add `release-drafter` to every repository \(including .github\) [\#39](https://github.com/alejandrohdezma/sbt-ci/pull/39) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Some fixes to `.github` and its propagated files [\#38](https://github.com/alejandrohdezma/sbt-ci/pull/38) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Use correct owner for `scala-steward-action` [\#37](https://github.com/alejandrohdezma/sbt-ci/pull/37) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Add `ADMIN\_GITHUB\_TOKEN` to `ci-test` step [\#36](https://github.com/alejandrohdezma/sbt-ci/pull/36) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.25.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.25.0) (2020-05-31)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.24.0...v0.25.0)

**Merged pull requests:**

- Run formatter/linter before running ci-test in Steward PRs and push [\#34](https://github.com/alejandrohdezma/sbt-ci/pull/34) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Allow triggering `docs` workflow with a `repository\_dispatch` [\#33](https://github.com/alejandrohdezma/sbt-ci/pull/33) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Allow running pre-post conditions before/after `ci-\*` commands  [\#32](https://github.com/alejandrohdezma/sbt-ci/pull/32) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.24.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.24.0) (2020-05-20)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.23.0...v0.24.0)

**Merged pull requests:**

- Remove Scala Steward workflow from managed repos [\#31](https://github.com/alejandrohdezma/sbt-ci/pull/31) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Remove Release Drafter workflow \(we'll use the app from now on\) [\#30](https://github.com/alejandrohdezma/sbt-ci/pull/30) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.23.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.23.0) (2020-05-02)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.22.0...v0.23.0)

**Merged pull requests:**

- Update comment for `.scalafix.conf`/`.scalafmt.conf` in `.gitignore` [\#29](https://github.com/alejandrohdezma/sbt-ci/pull/29) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.22.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.22.0) (2020-05-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.21.0...v0.22.0)

**Merged pull requests:**

- Change Scala Steward cron to run at at 5AM on Mon/Wed/Fri [\#28](https://github.com/alejandrohdezma/sbt-ci/pull/28) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.21.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.21.0) (2020-05-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.20.0...v0.21.0)

## [v0.20.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.20.0) (2020-05-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.19.0...v0.20.0)

## [v0.19.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.19.0) (2020-05-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.18.0...v0.19.0)

**Merged pull requests:**

- Use different action for auto-merging scala-steward PRs [\#27](https://github.com/alejandrohdezma/sbt-ci/pull/27) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.18.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.18.0) (2020-05-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.17.0...v0.18.0)

## [v0.17.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.17.0) (2020-04-30)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.16.0...v0.17.0)

**Merged pull requests:**

- Add `scala-steward` action [\#26](https://github.com/alejandrohdezma/sbt-ci/pull/26) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.16.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.16.0) (2020-04-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.15.0...v0.16.0)

**Merged pull requests:**

- Spread repository settings [\#25](https://github.com/alejandrohdezma/sbt-ci/pull/25) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Add CODEOWNERS [\#24](https://github.com/alejandrohdezma/sbt-ci/pull/24) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.15.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.15.0) (2020-04-21)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.14.0...v0.15.0)

**Merged pull requests:**

- Use `ci-publish` instead to `ci-release` in `release.yml` [\#23](https://github.com/alejandrohdezma/sbt-ci/pull/23) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.14.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.14.0) (2020-04-20)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.13.0...v0.14.0)

**Merged pull requests:**

- Ensure `changelog.yml` workflow is removed [\#22](https://github.com/alejandrohdezma/sbt-ci/pull/22) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.13.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.13.0) (2020-04-20)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.12.0...v0.13.0)

**Merged pull requests:**

- Merge docs and changelog generation into docs.yml [\#21](https://github.com/alejandrohdezma/sbt-ci/pull/21) ([alejandrohdezma](https://github.com/alejandrohdezma))
- This auto-merge rule is no longer needed [\#20](https://github.com/alejandrohdezma/sbt-ci/pull/20) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Run `sbt ci-test` in auto-update and only create PR if it fails [\#19](https://github.com/alejandrohdezma/sbt-ci/pull/19) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Ensure docs and changelog do not trigger new workflows [\#18](https://github.com/alejandrohdezma/sbt-ci/pull/18) ([alejandrohdezma](https://github.com/alejandrohdezma))
- There's no need to checkout tags on `ci.yml` [\#16](https://github.com/alejandrohdezma/sbt-ci/pull/16) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Ensure the ADMIN\_GITHUB\_TOKEN is used for pushing [\#15](https://github.com/alejandrohdezma/sbt-ci/pull/15) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Ensure `master` branch is used for pushing [\#14](https://github.com/alejandrohdezma/sbt-ci/pull/14) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Automatically push commit on docs and changelog instead of create PR [\#13](https://github.com/alejandrohdezma/sbt-ci/pull/13) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Regenerate docs and changelog only on releases [\#12](https://github.com/alejandrohdezma/sbt-ci/pull/12) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Use `on: release` instead of `on: push: tags` [\#11](https://github.com/alejandrohdezma/sbt-ci/pull/11) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.12.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.12.0) (2020-04-13)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.11.0...v0.12.0)

**Merged pull requests:**

- Auto-add `breaking-change` label to PRs with specific branch [\#10](https://github.com/alejandrohdezma/sbt-ci/pull/10) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.11.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.11.0) (2020-04-12)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.10.1...v0.11.0)

**Merged pull requests:**

- Add new `tests` label [\#9](https://github.com/alejandrohdezma/sbt-ci/pull/9) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.10.1](https://github.com/alejandrohdezma/sbt-ci/tree/v0.10.1) (2020-04-07)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.10.0...v0.10.1)

## [v0.10.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.10.0) (2020-04-06)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.9.0...v0.10.0)

**Merged pull requests:**

- Avoid error appearing in latest changelog version [\#8](https://github.com/alejandrohdezma/sbt-ci/pull/8) ([alejandrohdezma](https://github.com/alejandrohdezma))

## [v0.9.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.9.0) (2020-04-02)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.8.0...v0.9.0)

**Merged pull requests:**

- Use new label for scala-steward's PRs and include them in section [\#7](https://github.com/alejandrohdezma/sbt-ci/pull/7) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Automatically merge automatic PRs from alejandrohdezma/.github [\#6](https://github.com/alejandrohdezma/sbt-ci/pull/6) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Only expose GITHUB\_TOKEN while generating changelog [\#5](https://github.com/alejandrohdezma/sbt-ci/pull/5) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Use `squash` instead of `merge` for all auto-PRs [\#4](https://github.com/alejandrohdezma/sbt-ci/pull/4) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Add more metals files to .gitignore [\#3](https://github.com/alejandrohdezma/sbt-ci/pull/3) ([alejandrohdezma](https://github.com/alejandrohdezma))
- Exports secret as env var [\#2](https://github.com/alejandrohdezma/sbt-ci/pull/2) ([juanpedromoreno](https://github.com/juanpedromoreno))
- Update auto-update.yml [\#1](https://github.com/alejandrohdezma/sbt-ci/pull/1) ([juanpedromoreno](https://github.com/juanpedromoreno))

## [v0.8.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.8.0) (2020-03-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.7.0...v0.8.0)

## [v0.7.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.7.0) (2020-03-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.6.0...v0.7.0)

## [v0.6.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.6.0) (2020-03-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.5.0...v0.6.0)

## [v0.5.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.5.0) (2020-03-01)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.4.0...v0.5.0)

## [v0.4.0](https://github.com/alejandrohdezma/sbt-ci/tree/v0.4.0) (2020-02-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.3...v0.4.0)

## [v0.3](https://github.com/alejandrohdezma/sbt-ci/tree/v0.3) (2020-02-29)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.2...v0.3)

## [v0.2](https://github.com/alejandrohdezma/sbt-ci/tree/v0.2) (2020-02-28)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/v0.1...v0.2)

## [v0.1](https://github.com/alejandrohdezma/sbt-ci/tree/v0.1) (2020-02-14)

[Full Changelog](https://github.com/alejandrohdezma/sbt-ci/compare/3c8b199378e28403f4879eea67ab1be4c3f29d1d...v0.1)



\* *This Changelog was automatically generated by [github_changelog_generator](https://github.com/github-changelog-generator/github-changelog-generator)*
