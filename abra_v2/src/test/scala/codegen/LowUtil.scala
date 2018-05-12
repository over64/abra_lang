package codegen

import java.io.{FileOutputStream, InputStream, PrintStream}
import java.nio.file.{Files, Paths}
import java.util.Scanner

import m3.codegen.IrGen2
import m3.codegen.IrUtil.Mod


trait LowUtil {
  val testBase: String

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

  def buildRuntime(): String = {
    val outPath = s"abra_v2/abra/lib/runtime.ll"
    run("llc-3.9", "-filetype=obj", outPath) { (exit, stdout, stderr) =>
      if (exit != 0) {
        Files.copy(Paths.get(outPath), System.out)
        print(stderr)
        throw new Exception("llc error")
      }
    }

    s"abra_v2/abra/lib/runtime.o"
  }

  def runProgram(objName: String, deps: Seq[String], exit: Option[Int], stdout: Option[String] = None, stderr: Option[String] = None, isRelease: Boolean) = {
    val depFiles = deps ++ Seq(buildRuntime(), objName)
    val binName = objName.stripSuffix(".o")
    val args = Seq("clang-3.9") ++ depFiles ++ Seq("-o", binName, if (isRelease) "-O3" else "-O0")
    run(args: _*) { (exit, stdout, stderr) =>
      if (exit != 0) {
        print(stderr)
        throw new Exception("clang error")
      }
    }

    run(binName) { (realExit, realStdout, realStderr) =>

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

  implicit class TestModule(module: Mod) {
    def makeObj(modName: String, isRelease: Boolean) = {
      val outPath = s"$testBase/$modName.ll"
      val file = new FileOutputStream(outPath)
      IrGen2.gen(new PrintStream(file), module.lowCode, module.types, module.defs, module.protos)
      file.close()

      val llPath =
        if (isRelease) {
          val ll = outPath.stripSuffix(".ll") + ".opt.ll"
          run("opt-3.9", if (isRelease) "-O3" else "-O0", "-S", outPath, "-o", ll) { (exit, stdout, stderr) =>
            if (exit != 0) {
              Files.copy(Paths.get(outPath), System.out)
              print(stderr)
              throw new Exception("llc error")
            }
          }
          ll
        } else outPath

      run("llc-3.9", "-filetype=obj", llPath) { (exit, stdout, stderr) =>
        if (exit != 0) {
          Files.copy(Paths.get(outPath), System.out)
          print(stderr)
          throw new Exception("llc error")
        }
      }

      llPath.stripSuffix(".o")
    }

    def assertRunEquals(exit: Option[Int], stdout: Option[String] = None, stderr: Option[String] = None) = {
      val file = new FileOutputStream(s"$testBase/test.out.ll")
      IrGen2.gen(new PrintStream(file), module.lowCode, module.types, module.defs, module.protos)
      file.close()

      Files.copy(Paths.get(s"$testBase/test.out.ll"), System.out)

      run("llc-3.9", "-filetype=obj", s"$testBase/test.out.ll") { (exit, stdout, stderr) =>
        if (exit != 0) {
          print(stderr)
          throw new Exception("llc error")
        }
      }
      run("clang-3.9", buildRuntime(), s"$testBase/test.out.o", "-o", s"$testBase/test") { (exit, stdout, stderr) =>
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
