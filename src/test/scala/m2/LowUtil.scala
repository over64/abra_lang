package m2

import java.io.{FileOutputStream, InputStream, PrintStream}
import java.util.Scanner

import lang_m2.Ast1.Module
import lang_m2.IrGen

/**
  * Created by over on 11.09.16.
  */
trait LowUtil {
  val testBase: String

  implicit class TestModule(module: Module) {
    def assertRunEquals(exit: Int, stdout: Option[String] = None, stderr: Option[String] = None, additionalLL: Option[String] = None) = {
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

      val file = new FileOutputStream(s"$testBase/test.out.ll")

      additionalLL.map(ll => file.write(ll.getBytes))

      new IrGen(new PrintStream(file)).gen(module)
      file.close()

      run("llc-3.8", s"$testBase/test.out.ll") { (exit, stdout, stderr) =>
        if (exit != 0) {
          print(stderr)
          throw new Exception("llc error")
        }
      }
      run("gcc", s"$testBase/test.out.s", "-o", s"$testBase/test") { (exit, stdout, stderr) =>
        if (exit != 0) {
          print(stderr)
          throw new Exception("gcc error")
        }
      }
      run(s"$testBase/test") { (realExit, realStdout, realStderr) =>
        if (exit != realExit) throw new Exception(s"expected exit code $exit has $realExit")

        stdout.map { stdout =>
          if (stdout != realStdout) throw new Exception(s"expected stdout: $stdout has $realStdout")
        }
        stderr.map { stderr =>
          if (stderr != realStdout) throw new Exception(s"expected stdout: $stderr has $realStderr")
        }
      }
    }
  }
}
