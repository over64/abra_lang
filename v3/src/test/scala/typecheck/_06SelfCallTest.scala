package typecheck

import m3._02typecheck.TCE
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._


class _06SelfCallTest extends FunSuite {
  test("self call: simple") {
    val ast = astForCode(
      """
         def + = self: Int, other: Int native
           %1 = contains nsw i32 %self, %other
           ret i32 %1 .Int

         def main =
           1 + 1 .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("self call: self functions with same name") {
    val ast = astForCode(
      """
         def + = self: Int, other: Int native
           ; native code
           .Int

         def + = self: Long, other: Long native
           ; native code
           .Long

         def one = native
           ; assembly stub .Long

         def main =
           1 + 1
           one() + one() .
      """)

    assertTh("() -> Long", ast.function("main"))
  }

  test("self call: get") {
    val ast = astForCode(
      """
         type FunctionLike = (x: Int)

         def mk = native
            ; assembly .FunctionLike

         def get = self: FunctionLike, x: Int, y: Float do
           .

         def main =
           like = mk()
           like(1, 1.0) .
      """)

    assertTh("() -> None", ast.function("main"))
  }

  test("self call: set") {
    val ast = astForCode(
      """
         type ArrayLike = (x: Int)

         def mk = native
            ; assembly .ArrayLike

         def set = self: ArrayLike, x1: Int, x2: Float, value: Int native
           ; assembly .None

         def main =
           like = mk()
           like(1, 1.0) = 42 .
      """)

    assertTh("() -> None", ast.function("main"))
  }

  test("self call: get from struct field") {
    val ast = astForCode(
      """
         type Gettable = (x: Int)
         def get = self: Gettable, x: Int, y: Float do 'hello' .String

         type Wrapper = (g: Gettable)
         def mk = native ; assembly .Wrapper

         def main =
           wrapper = mk()
           wrapper.g(1, 1.0) .
      """)

    assertTh("() -> String", ast.function("main"))
  }

  test("self call: get from struct field - go deeper") {
    val ast = astForCode(
      """
         type Gettable = (x: Int)
         def get = self: Gettable, x: Int, y: Float do .

         type Wrapper = (g: Gettable)
         type Wrapper2 = (w1: Wrapper)
         def mk = native ; assembly .Wrapper2

         def main =
           w2 = mk()
           w2.w1.g(1, 1.0) .
      """)

    assertTh("() -> None", ast.function("main"))
  }

  test("self call fail: self function redeclaration") {
    assertThrows[TCE.DoubleSelfDefDeclaration] {
      astForCode(
        """
         def + = self: Int, other: Int native
           ; assembly stub .Int
         def + = self: Int, other: Int native
           ; assembly stub .Int
      """)
    }
  }

  test("self call fail: generic self function redeclaration") {
    assertThrows[TCE.DoubleSelfDefDeclaration] {
      astForCode(
        """
         type Seq[u] = (value: u, field: Int)

         def + = self: Seq[t] do .
         def + = self: Seq[u] do .
      """)
    }
  }
}

