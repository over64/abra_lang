package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _08ParamOpTest extends FunSuite {
  def generic(th: String, value: String): Unit =
    compile(
      s"""
        def callArg = x: $th do none .
        def xxx = x: $th do
          y = x
          callArg(x)
          x .

        def main =
          xxx($value)
          42 .""")

  val test = "param ops"

  Seq(
    ("value scalar", "Int", "42"),
    ("ref scalar", "String", "'hello'"),
    ("value struct", "(x0: Int, x1: Int)", "(1, 1)"),
    ("ref struct", "(x0: Int, x1: String)", "(1, 'hello')"),
    ("value union (value)", "Int | None", "42"),
    ("value union (none)", "Int | None", "none"),
    ("ref union (value)", "Int | String | None", "42"),
    ("ref union (ref)", "Int | String | None", "'hello'"),
    ("ref union (none)", "Int | String | None", "none"),
    ("nullable union (ref)", "String | None", "'hello'"),
    ("nullable union (none)", "String | None", "none")
  ).foreach { case (testName, th, value) =>
    test(test + ":" + testName) {
      generic(th, value)
    }
  }
}
