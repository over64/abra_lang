package integration

import org.scalatest.FunSuite

class _15WhileTest extends FunSuite with IntegrationUtil {
  test("while loop test") {
    assertCodeEquals(
      """
        llvm declare i32 @puts(i8*) .

        type Bool   = llvm i8 .
        type Int    = llvm i32 .
        type String = ref llvm i8* .
        type None   = llvm void .

        def print = self: String do llvm
         %1 = call i32 @puts(i8* %self)
         ret void .None

        def + = self: Int, other: Int do llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def < = self: Int, other: Int do llvm
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          i = 0
          while i < 128 do
            'xxxx' # mem leak test
            i = i + 1 .
          i .
      """, exit = Some(128))
  }
}