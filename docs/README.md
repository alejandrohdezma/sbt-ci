@DESCRIPTION@

## Installation

Add the following line to your `plugins.sbt` file:

```sbt
addSbtPlugin("@ORGANIZATION@" % "@NAME@" % "@VERSION@")
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

## What files does it generate?

@PROPAGATED_RESOURCES@

## Using this project as a template

This project is prepared to be used as a template. For a minimum set of changes you just need to:

- Update the list of resources to propagate in `build.sbt` (check for `resourcesToPropagate` setting).
- Change the `organization` setting to your own in `build.sbt`.
- Release the new plugin to Sonatype or another kind of Maven repository.

`README.md` should be auto-updated with the list of new resources once you execute `sbt ci-docs`. You can add some descriptions (as comments at the top of the file) to the propagated resources that will then be added to this file. See [sbt-propagate docs](https://github.com/alejandrohdezma/sbt-propagate) for more information about what can be done.

## Contributors to this project 

@CONTRIBUTORS_TABLE@
