package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _04LiteralOpTest extends FunSuite {
  test("literal ops: none") {
    compile(
      """
        def dummy = none . # as ret val
        def main =
          dummy()
          42 .
    """)
  }

  test("literal ops: bool") {
    compile(
      """
        def dummy = x: Bool do true . # as ret val
        def main =
          dummy(false) # as call arg
          x = false # as store src
          42 .
    """)
  }

  test("literal ops: int") {
    compile(
      """
        def dummy = x: Int do 42 .
        def main =
          dummy(42)
          x = 42
          42 .
      """)
  }

  test("literal ops: float") {
    compile(
      """
        def dummy = x: Float do 3.14 .
        def main =
          dummy(3.15)
          x = 3.15
          42 .
      """)
  }

  test("literal ops: string") {
    compile(
      """
        def dummy = x: String do 'hello' .
        def main =
          dummy('yolo')
          x = 'world'
          42 .
      """)
  }
}