package codegen2
import org.scalatest.FunSuite

class _04LiteralOpTest extends FunSuite {
  test("literal ops: none") {
    CodeGenUtil.run(
      """
        def dummy = none . # as ret val
        def main =
          dummy()
          42 .
    """, exitCode = 42)
  }

  test("literal ops: bool") {
    CodeGenUtil.run(
      """
        def dummy = x: Bool do true . # as ret val
        def main =
          dummy(false) # as call arg
          x = false # as store src
          42 .
    """, exitCode = 42)
  }

  test("literal ops: int") {
    CodeGenUtil.run(
      """
        def dummy = x: Int do 42 .
        def main =
          dummy(42)
          x = 42
          42 .
      """, exitCode = 42)
  }

  test("literal ops: float") {
    CodeGenUtil.run(
      """
        def dummy = x: Float do 3.14 .
        def main =
          dummy(3.15)
          x = 3.15
          42 .
      """, exitCode = 42)
  }

  test("literal ops: string") {
    CodeGenUtil.run(
      """
        def dummy = x: String do 'hello' .
        def main =
          dummy('yolo')
          x = 'world'
          42 .
      """, exitCode = 42)
  }
}