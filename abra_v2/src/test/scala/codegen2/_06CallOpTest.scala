package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _06CallOpTest extends FunSuite {
  def generic(th: String, value: String): Unit = {
    val code =
      s"""
        def dummy = $value .$th
        def retVal = dummy() .
        def callArg = x: $th do none .

        def main =
          retVal()          # as ret val
          callArg(dummy())  # as call arg
          x = dummy()       # as store src
          42 ."""

    println(code)
    compile(code)
  }

  val test = "call ops"

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
  )
    .foreach { case (testName, th, value) =>
      test(test + ":" + testName) {
        generic(th, value)
      }
    }
}