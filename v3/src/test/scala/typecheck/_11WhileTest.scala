package typecheck

import m3.typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _11WhileTest extends FunSuite {
  test("while loop") {
    val ast = astForCode(
      """
         def main =
           while true do
             none ..
      """)

    assertTh("() -> None", ast.function("main"))
  }

  test("while loop: break") {
    val ast = astForCode(
      """
         def main =
           while true do
             break ..
      """)

    assertTh("() -> None", ast.function("main"))
  }

  test("while loop: continue") {
    val ast = astForCode(
      """
         def main =
           while true do
             continue ..
      """)

    assertTh("() -> None", ast.function("main"))
  }

  test("while loop(fail): non bool condition") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
         def main =
           while 1 do -- C-style
             none ..
      """)
    }
  }

  test("while loop(fail): no loop for break or continue") {
    assertThrows[TCE.NoWhileForBreakOrContinue] {
      astForCode(
        """
         def main =
           break .
      """)
    }
  }
}
