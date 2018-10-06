package integration

import org.scalatest.FunSuite

class _20GenericDefTest extends FunSuite with IntegrationUtil {
  test("simple def spec") {
    assertCodeEquals(
      """
    type Int  = llvm i32 .
    type Long  = llvm i64 .

    def + = self: Int, other: Int do llvm
      %1 = add nsw i32 %self, %other
      ret i32 %1 .Int

    def + = self: Long, other: Long do llvm
      %1 = add nsw i64 %self, %other
      ret i64 %1 .Long

    def long = i: Int do llvm
      %1 = sext i32 %i to i64
      ret i64 %1 .Long

    def add = t1: t, t2: t do
      t1 + t2 .t

    def main =
      add(long(1), long(2))
      add(1, 1) .
  """, exit = Some(2))
  }

  test("spec def with ret parameter only(by return value)") {
    assertCodeEquals(
      """
    type Int  = llvm i32 .

    def bar = llvm
      %1 = alloca %t
      %2 = load %t, %t* %1
      ret %t %2 .t

    def main =
      x: Int = bar()
      2 .
  """, exit = Some(2))
  }
}

