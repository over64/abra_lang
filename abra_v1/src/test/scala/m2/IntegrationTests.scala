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
    assertRunEquals("tl/integration/00function_declarations.abra", Seq("integration"))(exit = Some(1))
  }
  test("literals") {
    assertRunEquals("tl/integration/01literals.abra", Seq("integration"))(exit = Some(42))
  }
  test("usual self and apply calls") {
    assertRunEquals("tl/integration/02usual_self_and_apply_calls.abra", Seq("integration"))(exit = Some(99))
  }
  test("type declarations") {
    assertRunEquals("tl/integration/03type_declarations.abra", Seq("integration"))(exit = Some(1))
  }
  test("access and store") {
    assertRunEquals("tl/integration/04access_and_store.abra", Seq("integration"))(exit = Some(3))
  }
  test("get and set") {
    assertRunEquals("tl/integration/05get_and_set.abra", Seq("integration"))(exit = Some(3))
  }
//  test("closures no params") {
//    assertRunEquals("tl/integration/06closures_no_params.abra", Seq("integration"))(exit = Some(6))
//  }
  test("closure struct") {
    assertRunEquals("tl/integration/07closure_struct.abra", Seq("integration"))(exit = Some(2))
  }
  test("disclosure struct") {
    assertRunEquals("tl/integration/08disclosure_struct.abra", Seq("integration"))(exit = Some(2))
  }
  test("closures with params") {
    assertRunEquals("tl/integration/09closures_with_params.abra", Seq("integration"))(exit = Some(3))
  }
  test("nested closures") {
    assertRunEquals("tl/integration/10nested_closures.abra", Seq("integration"))(exit = Some(7))
  }
  test("if else") {
    assertRunEquals("tl/integration/11if_else.abra", Seq("integration"))(exit = Some(6))
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

  test("pattern matching -> two branches") {
    assertRunEquals("tl/integration/pattern_matching/two_branches.abra", Seq("integration", "pattern_matching"))(exit = Some(2))
  }

  test("pattern matching -> expression") {
    assertRunEquals("tl/integration/pattern_matching/expression.abra", Seq("integration", "pattern_matching"))(exit = Some(3))
  }

  test("pattern matching -> var") {
    assertRunEquals("tl/integration/pattern_matching/var.abra", Seq("integration", "pattern_matching"))(exit = Some(3))
  }

  test("pattern matching -> bind var") {
    assertRunEquals("tl/integration/pattern_matching/bind_var.abra", Seq("integration", "pattern_matching"))(exit = Some(3))
  }

  test("pattern matching -> guard") {
    assertRunEquals("tl/integration/pattern_matching/guard.abra", Seq("integration", "pattern_matching"))(exit = Some(1))
  }

  test("pattern matching -> return unit") {
    assertRunEquals("tl/integration/pattern_matching/return_unit.abra", Seq("integration", "pattern_matching"))(exit = Some(1))
  }

  test("pattern matching -> destruct -> literal") {
    assertRunEquals("tl/integration/pattern_matching/destruct_literal.abra", Seq("integration", "pattern_matching"))(exit = Some(1))
  }

  test("pattern matching -> destruct -> named") {
    assertRunEquals("tl/integration/pattern_matching/destruct_named.abra", Seq("integration", "pattern_matching"))(exit = Some(1))
  }

  test("pattern matching -> destruct -> multi") {
    assertRunEquals("tl/integration/pattern_matching/destruct_multi.abra", Seq("integration", "pattern_matching"))(exit = Some(2))
  }

  test("pattern matching -> destruct -> deep named") {
    assertRunEquals("tl/integration/pattern_matching/destruct_deep_named.abra", Seq("integration", "pattern_matching"))(exit = Some(5))
  }

  test("unions -> val") {
    assertRunEquals("tl/integration/unions/val.abra", Seq("integration", "unions"))(exit = Some(0))
  }

  test("unions -> conditional val") {
    assertRunEquals("tl/integration/unions/conditional_val.abra", Seq("integration", "unions"))(exit = Some(0))
  }

  test("unions -> store") {
    assertRunEquals("tl/integration/unions/store.abra", Seq("integration", "unions"))(exit = Some(0))
  }

  test("unions -> match") {
    assertRunEquals("tl/integration/unions/match.abra", Seq("integration", "unions"))(exit = Some(3))
  }

  test("unions -> return from function") {
    assertRunEquals("tl/integration/unions/return_from_function.abra", Seq("integration", "unions"))(exit = Some(0))
  }
}
