TaskKey[Unit]("checkPermissions", "Checks that the permissions in `.github/scripts` are correct") := {
  assert(file(".github/scripts/gpg-setup.sh").canExecute())
}
