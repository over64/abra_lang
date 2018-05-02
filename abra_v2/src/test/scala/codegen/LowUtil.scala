package codegen

import java.io.{FileOutputStream, InputStream, PrintStream}
import java.nio.file.{Files, Paths}
import java.util.Scanner

import m3.codegen.IrGen2
import m3.codegen.IrUtil.Mod


trait LowUtil {
  val testBase: String

  implicit class TestModule(module: Mod) {
    def assertRunEquals(exit: Option[Int], stdout: Option[String] = None, stderr: Option[String] = None, additionalLL: Option[String] = None) = {
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

      IrGen2.gen(new PrintStream(file), module.lowCode, module.types, module.defs)
      file.close()

      Files.copy(Paths.get(s"$testBase/test.out.ll"), System.out)

      run("llc-3.9", "-filetype=obj", s"$testBase/test.out.ll") { (exit, stdout, stderr) =>
        if (exit != 0) {
          print(stderr)
          throw new Exception("llc error")
        }
      }
      run("clang-3.9", s"$testBase/test.out.o", "-o", s"$testBase/test") { (exit, stdout, stderr) =>
        if (exit != 0) {
          print(stderr)
          throw new Exception("clang error")
        }
      }
      run(s"$testBase/test") { (realExit, realStdout, realStderr) =>

        println(realStdout)
        println(realStderr)

        exit.map { exit =>
          if (exit != realExit) throw new Exception(s"expected exit code $exit has $realExit")
        }

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
