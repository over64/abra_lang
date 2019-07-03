package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _12AndOrTest extends FunSuite {
  test("and: left false") {
    compile(
      """
        llvm
          declare void @exit(i32) .

        def panic = code: Int do llvm
          call void @exit(i32 %code)
          ret i8 0 .Bool

        def main =
          false && panic(666) .
      """)
  }

  test("and: left true right true") {
    compile("""def main = true && true .""")
  }

  test("and: left true right false") {
    compile("""def main = true && false .""")
  }

  test("or: left true") {
    compile(
      """
        llvm
          declare void @exit(i32) .

        def panic = code: Int do llvm
          call void @exit(i32 %code)
          ret i8 0 .Bool

        def main =
          true || panic(666) .
      """)
  }

  test("or: left false right true") {
    compile("""def main = false || true .""")
  }

  test("or: left false right false") {
    compile("""def main = false || false .""")
  }
}
