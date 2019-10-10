package codegen
import org.scalatest.FunSuite

class _08LambdaDeclTest extends FunSuite {
  test("lambda decl & call local test") {
    CodeGenUtil.run(
      """
        def main =
          z = |x: Int, y: Int|
            42 .
          z(1, 1) .
      """, exitCode = 42)
  }
}
