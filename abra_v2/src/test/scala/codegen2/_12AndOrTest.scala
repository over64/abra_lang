package codegen2
import org.scalatest.FunSuite

class _12AndOrTest extends FunSuite {
  test("and: left false") {
    CodeGenUtil.run(
      """
        llvm
          declare void @exit(i32) .

        def panic = code: Int do llvm
          call void @exit(i32 %code)
          ret i8 0 .Bool

        def main =
          false && panic(666) .
      """, 0)
  }

  test("and: left true right true") {
    CodeGenUtil.run("""def main = true && true .""", 1)
  }

  test("and: left true right false") {
    CodeGenUtil.run("""def main = true && false .""", 0)
  }

  test("or: left true") {
    CodeGenUtil.run(
      """
        llvm
          declare void @exit(i32) .

        def panic = code: Int do llvm
          call void @exit(i32 %code)
          ret i8 0 .Bool

        def main =
          true || panic(666) .
      """, 1)
  }

  test("or: left false right true") {
    CodeGenUtil.run("""def main = false || true .""", 1)
  }

  test("or: left false right false") {
    CodeGenUtil.run("""def main = false || false .""", 0)
  }
}
