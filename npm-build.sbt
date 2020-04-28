/**
* sbt invoked npm commands
*/

// Process Exit codes
val SUCCESS = 0
val ERROR = 1

// register NpmHooks to separate build.sbt
PlayKeys.playRunHooks += baseDirectory.map(NpmHooks.apply).value

// https://www.scala-sbt.org/release/docs/Tasks.html
lazy val `frontend-prod-build` = TaskKey[Unit]("prod build for front-end artifacts.")

`frontend-prod-build` := {
  val frontendRoot = baseDirectory.value / "vue-ui"

  println("> npm install")
  val nodeModuleInstallResult = Shell.invokeProcess(NpmHooks.install, frontendRoot)

  println("> npm build")
  val isBuildSuccess = if (nodeModuleInstallResult == SUCCESS) Shell.invokeProcess(NpmHooks.build, frontendRoot) else ERROR

  if (isBuildSuccess != SUCCESS) throw new Exception("Npm Build process failed!")
}

// Execute front-end prod build task prior to play dist/stage execution
dist := (dist dependsOn `frontend-prod-build`).value

stage := (stage dependsOn dist).value