package m2

import java.nio.file.Paths

import lang_m2.CompilerKernel
import org.scalatest.FunSuite
import lang_m2.Utils.{run => binRun}

/**
  * Created by over on 11.09.16.
  */
class IntegrationTests extends FunSuite {

  def assertRunEquals(fname: String)(exit: Option[Int], stdout: Option[String] = None, stderr: Option[String] = None) = {
    val binPath =
      new CompilerKernel()
        .compile(level = 0, include = Seq(Paths.get("tl")), currentPkg = Seq("tl", "integration"), sourcePath = Paths.get(fname), isMain = true)
        .binLocation


    binRun(binPath.toString) { (realExit, realStdout, realStderr) =>
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

  test("hello, world") {
    assertRunEquals("tl/integration/hw.abra")(exit = None, stdout = Some("Привет, мир!"))
  }

  test("floats") {
    assertRunEquals("tl/integration/floats.abra")(exit = Some(11))
  }

  test("fn pointer") {
    assertRunEquals("tl/integration/fnpointer.abra")(exit = Some(10))
  }
  test("fake arrays") {
    assertRunEquals("tl/integration/arrays.abra")(exit = Some(186))
  }
}
