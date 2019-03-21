package typecheck

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
        import modA .
        def main =
          value: modA.Some | Int = 42 .
        """
    })

    val main = ast.function("main")
    assertTh("() -> None", main)
    assertTh("modA.Some | Int", main.varDecl("value"))
  }

  test("import module: with type") {
    val ast = astForModules({
      case "modA" => """
        type Some = (x: Int)
        """
      case "main" => """
        import modA with Some .
        def main =
          value: Some | Int = 42 .
        """
    })

    val main = ast.function("main")
    assertTh("() -> None", main)
    assertTh("Some | Int", main.varDecl("value"))
  }

  test("import module(fail): with type clash") {
    assertThrows[TCE.TypeAlreadyLocalDefined] {
      astForModules({
        case "modA" => """
        type Some = (x: Int)
        """
        case "main" => """
        import modA with Some .
        type Some = (f: Float)
        def main = .
        """
      })
    }
  }
}
