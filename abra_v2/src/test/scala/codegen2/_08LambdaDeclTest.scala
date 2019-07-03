package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _08LambdaDeclTest extends FunSuite {
  test("lambda decl") {
    compile(
      """
        def main =
          z = lambda x: Int, y: Int ->
            42 ..
      """)
  }
}
