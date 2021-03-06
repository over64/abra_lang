package codegen
import org.scalatest.FunSuite

class _14WhileLoopTest extends FunSuite {
  test("while: condition") {
    CodeGenUtil.run(
      """
        def + = self: Int, other: Int native
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def < = self: Int, other: Int native
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          x = 0
          while x < 255 do
            x = x + 1 .
          x .
      """, exitCode = 255)
  }

  test("while: break") {
    CodeGenUtil.run(
      """
        def + = self: Int, other: Int native
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def > = self: Int, other: Int native
          %1 = icmp sgt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          x = 0
          while true do
            x = x + 1
            break .
          x .
      """, 1)
  }

  test("while: continue") {
    CodeGenUtil.run(
      """
        def + = self: Int, other: Int native
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def < = self: Int, other: Int native
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def main =
          x = 0
          y = 0
          while x < 255 do
            x = x + 1
            continue .
          y .
      """, 0)
  }

}
