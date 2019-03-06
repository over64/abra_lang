package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _09StoreTest extends FunSuite {
  test("store: local scalar") {
    val ast = astForCode(
      """
         def main =
           x: Int | String = 1
           y = 'hello' .
      """)

    val main = ast.function("main")

    assertTh("() -> None", main)
    assertTh("Int | String", main.varDecl("x"))
    assertTh("String", main.varDecl("y"))
  }

  test("store fail: redeclaration") {
    assertThrows[TCE.VarAlreadyDeclared] {
      astForCode(
        """
         def main =
           x: Int | String = 1
           x: Int | String = 1 .
      """)
    }
  }

  test("store fail: no such var") {
    assertThrows[TCE.NoSuchVar] {
      astForCode(
        """
         def main =
           x.x = 1 .
      """)
    }
  }

  test("store fail: type hint unexpected") {
    assertThrows[TCE.TypeHintUnexpected] {
      astForCode(
        """
         def main =
           x.x: Int = 1 .
      """)
    }
  }

  test("store fail: no such field") {
    assertThrows[TCE.NoSuchField] {
      astForCode(
        """
         type Vec2 = (x: Int, y: Int)
         def mkVec2 = llvm
           ;assembly .Vec2

         def main =
           vec: Vec2 = mkVec2()
           vec.z = 11 .
      """)
    }
  }

  test("store: to & from local field") {
    val ast = astForCode(
      """
         type Vec2 = (x: Float, y: Float)

         def mkVec2 = llvm
           ; some assembly .Vec2

         def main =
           vec = mkVec2()
           vec.x = 2.0
           y = vec.y .
      """)

    val main = ast.function("main")

    assertTh("() -> None", main)
    assertTh("Vec2", main.varDecl("vec"))
    assertTh("Float", main.varDecl("y"))
  }

  test("store: to & from param") {
    val ast = astForCode(
      """
         type Vec2 = (x: Float, y: Float)

         def main = vec: Vec2 do
           vec.x = 2.0
           y = vec.y .
      """)

    val main = ast.function("main")

    assertTh("(Vec2) -> None", main)
    assertTh("Float", main.varDecl("y"))
  }

  test("store: global function pointer to local") {
    val ast = astForCode(
      """
         def some = x: Int do x .

         def main =
           localSome = some .
      """)

    val main = ast.function("main")

    assertTh("() -> None", main)
  }

  test("store: to & from closure scalar") {
    val ast = astForCode(
      """
         def some = x: Int do x .

         def main =
           x = 42
           lambda
             y = x
             x = 43
             x .()
           .
      """)

    val main = ast.function("main")

    assertTh("Int", main.varDecl("x"))
    assertTh("() -> Int", main)
  }

  test("store: set") {
    val ast = astForCode(
      """
         type Mat3f = llvm [9 x float] .

         def set = self: Mat3f, x: Int, y: Int, z: Int, value: Float do .

         def zeroMat = llvm
           ; assembly .Mat3f

         def main =
           mat = zeroMat()
           mat(0, 1, 1) = 10.0 .
      """)

    val main = ast.function("main")

    assertTh("() -> None", main)
    assertTh("Mat3f", main.varDecl("mat"))
  }

  test("store fail: type mismatch") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         type Vec2 = (x: Int, y: Int)
         def mkVec2 = llvm
           ;assembly .Vec2

         def main =
           vec: Int = mkVec2() .
      """)
    }
  }

  test("store fail: no such type") {
    assertThrows[TCE.NoSuchType] {
      astForCode(
        """
         type Vec2 = (x: Int, y: Int)
         def mkVec2 = llvm
           ;assembly .Vec2

         def main =
           vec: SomeType = mkVec2() .
      """)
    }
  }
}
