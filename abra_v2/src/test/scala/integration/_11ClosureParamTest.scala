package integration

import org.scalatest.FunSuite


class _11ClosureParamTest extends FunSuite with IntegrationUtil {
  test("closure param") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type Vec2   = (x: Int, y: Int)
        type S      = (x: String, y: Int)
        type U1     = Int | String

        def baz = x1: Int, x2: String, x3: Vec2, x4: S, x5: U1 do .

        def bar = x1: Int, x2: String, x3: Vec2, x4: S, x5: U1 do
           lambda
             x3.x = 42
             x4.x = 'world'
             baz(x1, x2, x3, x4, x5) .()
        .

        def main =
          v = Vec2(1, 1)
          s = S('hello', 1)
          bar(1, 'hi', v, s, 'union 1')
          v.x .
        """, exit = Some(42))
  }
}