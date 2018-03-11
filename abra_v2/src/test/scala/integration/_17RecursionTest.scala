package integration

import org.scalatest.FunSuite

class _17RecursionTest extends FunSuite with IntegrationUtil {
  test("when expression") {
    assertCodeEquals(
      """
        type Bool = llvm i8 .
        type Int  = llvm i32 .

        def - = self: Int, other: Int -> llvm
          %1 = sub nsw i32 %self, %other
          ret i32 %1 .Int

        def * = self: Int, other: Int -> llvm
          %1 = mul nsw i32 %self, %other
          ret i32 %1 .Int

        def == = self: Int, other: Int -> llvm
          %1 = icmp eq i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def fact = x: Int ->
          if x == 1 do 1 else x * fact(x - 1) ..Int

        def main = fact(4) .
      """, exit = Some(24))
  }
}

