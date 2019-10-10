package codegen2

import org.scalatest.FunSuite

class _17GenericsTest extends FunSuite {
  test("call") {
    CodeGenUtil.run(
      """
         def add = x: num, y: num do
           42 .

         def main =
           x: Long = 0
           add(x, x)
           add(1, 41)
           add(1, 41) .
      """, 42)
  }

  test("self call") {
    CodeGenUtil.run(
      """
         def add = self: num, y: num do
           42 .

         def main =
           1.add(41) .
      """, 42)
  }

  test("call with polymorphic args") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int native
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def + = self: Long, other: Long native
           %1 = add nsw i64 %self, %other
           ret i64 %1 .Long

         def add = x: num, y: num do
           x + y .

         def main =
           x: Long = 0
           add(x, x)
           add(1, 41)
           add(1, 41) .
      """, 42)
  }

  test("call with polymorphic args deep") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int native
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def - = self: Int, other: Int native
           %1 = sub nsw i32 %self, %other
           ret i32 %1 .Int

         def sub = x: num1, y: num1 do
           x - y .

         def addSub = x: num, y: num do
           x + y
           sub(x, y) .

         def main =
           addSub(43, 1) .
      """, 42)
  }

  test("self call with polymorphic args") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int native
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def add = self: num, y: num do
           self + y .

         def main =
           41.add(1) .
      """, 42)
  }

  test("self call with polymorphic args deep") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int native
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def sub = self: Int, other: Int native
           %1 = sub nsw i32 %self, %other
           ret i32 %1 .Int

         def addSub = self: num, y: num do
           self + y
           self.sub(y) .

         -- incorrect replacement ???
         -- expected Int::sub ???
         -- def sub = self: num1, y: num1 do
         --   self - y .

         def main =
           43.addSub(1) .
      """, 42)
  }
}
