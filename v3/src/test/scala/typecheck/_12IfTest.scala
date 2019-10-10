package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _12IfTest extends FunSuite {
  test("if: simple") {
    val ast = astForCode(
      """
         def main =
           if true do 1
           else none ..
      """)

    assertTh("() -> Int | None", ast.function("main"))
  }

  test("if: one branch") {
    val ast = astForCode(
      """
         def main =
           if true do 1 ..
      """)

    assertTh("() -> Int | None", ast.function("main"))
  }

  test("if(fail): non bool condition") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         def main =
           if 1 do 1 ..
      """)
    }
  }
}
