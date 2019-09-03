package codegen2

import org.scalatest.FunSuite

class _16GenericsTest extends FunSuite {
  test("generics: simple") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int do llvm
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def add = x: num, y: num do
           x + y .

         def main =
           add(1, 41) .
      """, 42)
  }
}
