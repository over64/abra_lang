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

  def assertRunEquals(fname: String, currentPkg: Seq[String])(exit: Option[Int], stdout: Option[String] = None, stderr: Option[String] = None) = {
    val cfg = Config(include = Seq(Paths.get("tl")), targetDir = Paths.get("tl/integration/target/"))
    val binPath =
      new CompilerKernel()
        .compile(level = 0, cfg, currentPkg,
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
    assertRunEquals("tl/integration/function_declarations.abra", Seq("integration"))(exit = Some(1))
  }
  test("literals") {
    assertRunEquals("tl/integration/literals.abra", Seq("integration"))(exit = Some(42))
  }
  test("usual self and apply calls") {
    assertRunEquals("tl/integration/usual_self_and_apply_calls.abra", Seq("integration"))(exit = Some(99))
  }
  test("type declarations") {
    assertRunEquals("tl/integration/type_declarations.abra", Seq("integration"))(exit = Some(1))
  }
  test("access and store") {
    assertRunEquals("tl/integration/access_and_store.abra", Seq("integration"))(exit = Some(3))
  }
  test("get and set") {
    assertRunEquals("tl/integration/get_and_set.abra", Seq("integration"))(exit = Some(3))
  }
  test("closures no params") {
    assertRunEquals("tl/integration/closures_no_params.abra", Seq("integration"))(exit = Some(6))
  }
  test("closure struct") {
    assertRunEquals("tl/integration/closure_struct.abra", Seq("integration"))(exit = Some(2))
  }
  test("disclosure struct") {
    assertRunEquals("tl/integration/disclosure_struct.abra", Seq("integration"))(exit = Some(2))
  }
  test("closures with params") {
    assertRunEquals("tl/integration/closures_with_params.abra", Seq("integration"))(exit = Some(3))
  }
  test("nested closures") {
    assertRunEquals("tl/integration/nested_closures.abra", Seq("integration"))(exit = Some(7))
  }
  test("if else") {
    assertRunEquals("tl/integration/if_else.abra", Seq("integration"))(exit = Some(6))
  }
  test("modules") {
    assertRunEquals("tl/integration/modules/moduleA.abra", Seq("integration", "modules"))(exit = Some(1))
  }
  test("pattern matching -> dash") {
    assertRunEquals("tl/integration/pattern_matching/dash.abra", Seq("integration", "pattern_matching"))(exit = Some(1))
  }

  test("pattern matching -> literal") {
    assertRunEquals("tl/integration/pattern_matching/literal.abra", Seq("integration", "pattern_matching"))(exit = Some(2))
  }

  test("pattern matching -> expression") {
    assertRunEquals("tl/integration/pattern_matching/expression.abra", Seq("integration", "pattern_matching"))(exit = Some(3))
  }

  test("pattern matching -> var") {
    assertRunEquals("tl/integration/pattern_matching/var.abra", Seq("integration", "pattern_matching"))(exit = Some(3))
  }
}
