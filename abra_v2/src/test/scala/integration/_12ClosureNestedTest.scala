package integration

import org.scalatest.FunSuite

class _12ClosureNestedTest extends FunSuite with IntegrationUtil {
  test("closure nested (full qualified evil test)") {
    assertCodeEquals(
      """
        type None   = llvm void .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type Vec2   = (x: Int, y: Int)
        type S      = (x: String, y: Int)
        type U1     = Int | String

        def baz = x1: Int, x2: String, x3: Vec2, x4: S, x5: U1,
                l1: Int, l2: String, l3: Vec2, l4: S, l5: U1 -> .

        def bar = x1: Int, x2: String, x3: Vec2, x4: S, x5: U1 ->
           l1 = 1
           l2 = 'ho'
           l3 = Vec2(1, 1)
           l4 = S('hh', 1)
           l5: U1 = 10

           lambda
             lambda
               l1   = 2
               l2   = 'ss'
               l3   = Vec2(10, 10)
               l4.x = 'hhh'
               l5   = 'hhhi'

               x3.x = 42
               x4.x = 'world'
               baz(x1, x2, x3, x4, x5,
                   l1, l2, l3, l4, l5)
             .()
           .()
        .

        def main =
          v = Vec2(1, 1)
          s = S('hello', 1)
          bar(1, 'hi', v, s, 'union 1')
          v.x .
        """, exit = Some(42))
  }
}