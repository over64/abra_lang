package m2

import java.nio.file.Paths

import lang_m2.Compiler.Config
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
        .compile(level = 0, Config(include = Seq(Paths.get("tl"))), currentPkg = Seq("tl", "integration"),
          sourcePath = Paths.get(fname), isMain = true)
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

  test("function declarations") {
    assertRunEquals("tl/integration/function_declarations.abra")(exit = Some(1))
  }
  test("literals") {
    assertRunEquals("tl/integration/literals.abra")(exit = Some(42))
  }
  test("usual self and apply calls") {
    assertRunEquals("tl/integration/usual_self_and_apply_calls.abra")(exit = Some(99))
  }
  test("type declarations") {
    assertRunEquals("tl/integration/type_declarations.abra")(exit = Some(1))
  }
  test("get and set") {
    assertRunEquals("tl/integration/get_and_set.abra")(exit = Some(3))
  }
  test("closures no params") {
    assertRunEquals("tl/integration/closures_no_params.abra")(exit = Some(4))
  }
  test("closures with params") {
    assertRunEquals("tl/integration/closures_with_params.abra")(exit = Some(3))
  }
  test("nested closures") {
    assertRunEquals("tl/integration/nested_closures.abra")(exit = Some(7))
  }
  test("modules") {
    assertRunEquals("tl/integration/modules/moduleA.abra")(exit = Some(1))
  }
}
