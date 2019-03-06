package typecheck

import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _08ConsTest extends FunSuite {
  test("cons: simple struct") {
    val ast = astForCode(
      """
         type Vec2 = (x: Float, y: Float)
         def main =
           vec = Vec2(1.0, 1.0) .
      """)

    assertTh("Vec2", ast.function("main").varDecl("vec"))
    assertTh("() -> None", ast.function("main"))
  }

  test("cons: generic struct (no params)") {
    val ast = astForCode(
      """
         type Vec2[t, u] = (x: t, y: u)
         def main =
           vec = Vec2(1.0, 1) .
      """)

    assertTh("Vec2[Float, Int]", ast.function("main").varDecl("vec"))
  }

  test("cons: generic struct (with params)") {
    val ast = astForCode(
      """
         type Vec2[t, u] = (x: t, y: u)

         def main =
           vec = Vec2[Float | None, Int](1.0, 1) .
      """)

    assertTh("Vec2[Float | None, Int]", ast.function("main").varDecl("vec"))
  }
}