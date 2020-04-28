import java.io.PrintWriter
import play.sbt.PlayRunHook
import sbt._

import scala.io.Source
import scala.sys.process.Process

/**
 * Hooks into Play's dev mode
 * https://www.playframework.com/documentation/2.8.x/sbtCookbook#Hooking-into-Plays-dev-mode
 *
 */
object NpmHooks {

  val install: String = "npm install"
  val serve: String = "npm run serve"
  val build: String = "npm run build"

  def apply(base: File): PlayRunHook = {

    // File paths to front-end
    val frontendBase = base / "vue-ui"
    val packageJsonPath = frontendBase / "package.json"

    // Locally stored file hash of package.json
    val frontEndTarget = frontendBase / "target"
    val packageJsonHashPath = frontendBase / "package.json.hash"

    object NpmBuildHook extends PlayRunHook {


      var process: Option[Process] = None

      /**
      * Run npm install if changes are detected in package.json
       * called before the play application is started, but after all “before run” tasks have been completed.
       */
      override def beforeStarted(): Unit = {

        // The hashCode() method is utilized to find the hash code of the stated string.
        // https://www.java2novice.com/java_interview_questions/hashcode/
        val bufferedSource =  Source.fromFile(packageJsonPath)
        val currentPackageJsonHash = bufferedSource.mkString.hashCode.toString
        val oldPackageJsonHash = getStoredPackageJsonHash.get

        if(!currentPackageJsonHash.equals(oldPackageJsonHash)) {
          println("Found new/changed package.json. Invoking new npm install....")
          Shell.invokeProcess(install, frontendBase)
          updateStoredPackageJsonHash(currentPackageJsonHash)
        }
        bufferedSource.close()
      }

      /**
      * Run npm serve
       * called after the play application has been started.
       */
      override def afterStarted(): Unit = {

        println("> npm serve")
        process = Option(
          Shell.process(serve, frontendBase)
        )
      }

      /**
      * clean up
       * called after the play process has been stopped.
       */
      override def afterStopped(): Unit = {
        process.foreach(_.destroy())
        process = None
      }

      /**
      * Get the stored package json hash
       * @return
       */
      def getStoredPackageJsonHash: Option[String] = {
        val bufferedSource =  Source.fromFile(packageJsonPath)
        if(packageJsonHashPath.exists()) {
          val hash = bufferedSource.getLines().mkString
          Some(hash)
        } else {
          // throw an exception here
          Some(null)
        }
      }

      /**
      * Update the old stored package json with the latest one
       * @param hash
       */
      def updateStoredPackageJsonHash(hash: String): Unit = {
        val dir = frontEndTarget
        if(!dir.exists) dir.mkdirs

        val pw = new PrintWriter(packageJsonHashPath)
        try {
          pw.write(hash)
        }finally pw.close()
      }
    }

    NpmBuildHook
  }
}
