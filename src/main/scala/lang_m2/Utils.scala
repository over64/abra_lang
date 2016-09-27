package lang_m2

import java.io.InputStream
import java.util.Scanner

/**
  * Created by over on 27.09.16.
  */
object Utils {
  def run(args: String*)(onRun: (Int, String, String) => Unit): Unit = {
    def outToString(stream: InputStream): String = {
      val buff = new StringBuffer()
      val out = new Scanner(stream)
      while (out.hasNextLine)
        buff.append(out.nextLine())
      buff.toString
    }

    val program = Runtime.getRuntime.exec(args.toArray)
    val exitCode = program.waitFor()

    val stdout = outToString(program.getInputStream)
    val stderr = outToString(program.getErrorStream)

    onRun(exitCode, stdout, stderr)
  }
}
