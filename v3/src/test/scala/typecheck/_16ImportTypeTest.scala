package typecheck

import m3.Ast0.{ScalarTh, UnionTh}
import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _16ImportTypeTest extends FunSuite {
  test("import module: use type with mod prefix") {
    val ast = astForModules({
      case "modA" => """
        type Some = (x: Int)
        """
      case "main" => """
        import modA
        def main =
          value: modA.Some | Int = 42 .
        """
    })

    val main = ast.function("main")
    assertTh("() -> None", main)
    assertThRaw(
      UnionTh(Seq(
        ScalarTh(Seq.empty, "Some", Some("modA"), "modA"),
        ScalarTh(Seq.empty, "Int", None, "prelude"))),
      main.varDecl("value"))
  }

  test("import module: with type") {
    val ast = astForModules({
      case "modA" => """
        type Some = (x: Int)
        """
      case "main" => """
        import modA with Some
        def main =
          value: Some | Int = 42 .
        """
    })

    val main = ast.function("main")
    assertTh("() -> None", main)
    assertThRaw(
      UnionTh(Seq(
        ScalarTh(Seq.empty, "Some", None, "modA"),
        ScalarTh(Seq.empty, "Int", None, "prelude"))),
      main.varDecl("value"))
  }

  test("import type from module: deep 3") {
    val ast = astForModules({
      case "libB" => """
        type B = (x: Int, y: Int)
        """
      case "libA" => """
        import libB
        type A = (b: libB.B, x: Int)
        """
      case "main" => """
        import libA with A
        type M = (a: A, x: Int)
        def local = m: M do none .
        """
    })

    val local = ast.function("local")
    assertTh("(M) -> None", local)
  }

  test("import module(fail): with type clash") {
    assertThrows[TCE.TypeAlreadyLocalDefined] {
      astForModules({
        case "modA" => """
        type Some = (x: Int)
        """
        case "main" => """
        import modA with Some
        type Some = (f: Float)
        def main = .
        """
      })
    }
  }
}
