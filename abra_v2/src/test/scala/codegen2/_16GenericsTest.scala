package codegen2

import org.scalatest.FunSuite

class _16GenericsTest extends FunSuite {
  test("generics: simple") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int do llvm
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def + = self: Long, other: Long do llvm
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

  test("generics: self call") {
    CodeGenUtil.run(
      """
         def + = self: Int, other: Int do llvm
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def add = self: num, y: num do
           self + y .

         def main =
           1.add(41) .
      """, 42)
    // TODO
    //  - add module meta: resolvedSelfDefs <selfTh, fnName> => <Def, Mod>
    //  - read from IrGenPass & make spec
    //  - ambiguous definition for generic functions eg:
    //     def foo = self: numeric do
    //     def foo = self: iterable do
    //  - scalarTh vs genericTh
    //    def map = self: Range ...
    //    def map = self: iterator ...
  }
}
