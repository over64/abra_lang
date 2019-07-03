package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _13WhileLoopTest extends FunSuite {
  test("while: condition") {
    compile(
      """
        def + = self: Int, other: Int do llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def < = self: Int, other: Int do llvm
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          x = 0
          while x < 255 do
            x = x + 1 .
          x .
      """)
  }

  test("while: break") {
    compile(
      """
        def + = self: Int, other: Int do llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def > = self: Int, other: Int do llvm
          %1 = icmp sgt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          x = 0
          while true do
            x = x + 1
            break .
          x .
      """)
  }

  test("while: continue") {
    compile(
      """
        def + = self: Int, other: Int do llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def < = self: Int, other: Int do llvm
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          x = 0
          y = 0
          while x < 255 do
            x = x + 1
            continue
            y = y + 1 .
          y .
      """)
  }

}
