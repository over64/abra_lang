package typecheck

import m3.Ast0.{FnTh, ScalarTh}
import m3._02typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._
import m3._02typecheck.TCMeta.ParseNodeTCMetaImplicit

import scala.collection.immutable.ArraySeq

class _17ModCallTest extends FunSuite {
  test("mod call: simple") {
    val ast = astForModules({
      case "modA" => """
        def some = x: Int, y: Int do
          42 .
        """
      case "main" => """
        import modA
        def main =
          modA.some(1, 2) .
        """
    })

    val main = ast.function("main")
    assertTh("() -> Int", main)
  }

  test("mod call: foreign type return") {
    val ast = astForModules({
      case "modA" => """
        type Bar = (value: Int)
        def some = x: Int, y: Int do
          Bar(42) .
        """
      case "main" => """
        import modA
        def main =
          modA.some(1, 2) .
        """
    })

    val main = ast.function("main")
    assertThRaw(FnTh(ArraySeq.empty, ScalarTh(ArraySeq.empty, "Bar", None, "modA")), main)
  }

  test("mod call: imported foreign type return") {
    val ast = astForModules({
      case "modA" => """
        type Bar = (value: Int)
        def some = x: Int, y: Int do
          Bar(42) .
        """
      case "main" => """
        import modA with Bar
        def main =
          modA.some(1, 2) .
        """
    })

    val main = ast.function("main")
    assertThRaw(FnTh(ArraySeq.empty, ScalarTh(ArraySeq.empty, "Bar", None, "modA")), main)
  }

  test("mod call: no such def") {
    assertThrows[TCE.NoSuchDef] {
      astForModules({
        case "modA" => """ """
        case "main" => """
        import modA
        def main =
          modA.some(1, 2) .
        """
      })
    }
  }

  test("mod self-call: simple") {
    val ast = astForModules({
      case "modA" =>
        """
        type Bar = (x: Int)
        def passThrough = self: Bar do self ."""
      case "main" => """
        import modA with Bar
        def main =
          Bar(42).passThrough() .
        """
    })

    val main = ast.function("main")
    assertThRaw(FnTh(ArraySeq.empty, ScalarTh(ArraySeq.empty, "Bar", None, "modA")), main)
  }

  test("mod self-extension: user-defined") {
    val ast = astForModules({
      case "modC" => """type Bar = (x: Int)"""
      case "modA" =>
        """
        import modC with Bar
        def passThrough = self: Bar do self ."""
      case "main" => """
        import modC with Bar, modA

        def main =
          b = Bar(42)
          b.passThrough() .
        """
    })

    val main = ast.function("main")
    assertThRaw(FnTh(ArraySeq.empty, ScalarTh(ArraySeq.empty, "Bar", None, "modC")), main)

  }

  test("mod self-extension: type clash") {
    assertThrows[TCE.NoSuchSelfDef] {
      astForModules({
        case "modA" =>
          """
        type Bar = (x: Int)
        def passThrough = self: Bar do self ."""
        case "main" => """
        import modA
        type Bar = (x: Int)

        def main =
          b = Bar(42)
          b.passThrough() .
        """
      })
    }
  }
}