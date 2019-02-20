package integration

import java.nio.file.{Files, Paths}

import org.scalatest.FunSuite

class _13SelfFunctionTest extends FunSuite with IntegrationUtil {
  test("infix self call") {
    assertCodeEquals(
      """
        type Int  = llvm i32 .

        def - = self: Int, other: Int do llvm
          %1 = sub nsw i32 %self, %other
          ret i32 %1 .Int

        def main = 1 - 1 .
      """, exit = Some(0))
  }

  test("direct self call") {
    assertCodeEquals(
      """
        type Int  = llvm i32 .

        def - = self: Int, other: Int do llvm
          %1 = sub nsw i32 %self, %other
          ret i32 %1 .Int

        def main = 1.-(1) .
      """, exit = Some(0))
  }

  test("self fn collision") {
    assertCodeEquals(
      """
        type Int  = llvm i32 .
        type Long  = llvm i64 .

        def - = self: Int, other: Int do llvm
          %1 = sub nsw i32 %self, %other
          ret i32 %1 .Int

        def - = self: Long, other: Long do llvm
          %1 = sub nsw i64 %self, %other
          ret i64 %1 .Long

        def one = llvm
          ret i64 1 .Long

        def main =
          one() - one()
          1 - 1 .
      """, exit = Some(0))
  }

  test("struct field call") {
    assertCodeEquals(
      """
        type Int  = llvm i32 .
        type Struct = (x: Int, callee: (Int) -> Int)

        def returner = x: Int do x .

        def main =
          st = Struct(1, returner)
          st.callee(2) .
      """, exit = Some(2))
  }

  test("anon struct field call") {
    assertCodeEquals(
      """
        type Int  = llvm i32 .
        def returner = x: Int do x .

        def main =
          st: (x: Int, callee: (Int) -> Int) = (1, returner)
          st.callee(2) .
      """, exit = Some(2))
  }
}