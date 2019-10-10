package codegen2

import org.scalatest.FunSuite

class _16UnlessTest extends FunSuite {
  test("unless: simple") {
    CodeGenUtil.run(
      """
         def > = self: Int, other: Int native
           %1 = icmp sgt i32 %self, %other
           %2 = zext i1 %1 to i8
           ret i8 %2 .Bool

         def main =
           x: Int | String | Bool = 42
           x unless
             is i: Int do i > 5
             is String do false ..
      """, 1)
  }
}
