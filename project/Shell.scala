import sbt._
import scala.sys.process.Process

//TODO: Implement windows impl
/**
* scala.sys.process.Process package provides utilities to interact with shell programs
 * https://alvinalexander.com/scala/how-to-run-external-commands-processes-different-directory-scala/
 * use ! to execute the process and !! to return in string
 */
object Shell {

  /** Starts the process represented by this builder, blocks until it exits, and
   * returns the exit code.  Standard output and error are sent to the console.
   */
  def invokeProcess(script: String, dir: File): Int = {
    Process(script, dir)
  } !

  /** Starts the process represented by this builder.  Standard output and error
   * are sent to the console.*/
  def process(script: String, dir: File): Process = {
    Process(script, dir).run
  }
}
