package codegen2

import codegen2.CodeGenUtil.compile
import org.scalatest.FunSuite

class _07LocalOpTest extends FunSuite {
  def generic(th: String, value: String): Unit = {
    val code =
      s"""
        def retVal =
          x: $th = $value
          x .

        def callArg = x: $th do
          none .

        def main =
          x: $th = $value
          y = x  # as store src
          y = x  # as store dest
          retVal()
          callArg(x)
          42 ."""

    println(code)
    compile(code)
  }

  val test = "local ops"

  Seq(
    ("value scalar", "Int", "42")
    , ("ref scalar", "String", "'hello'")
    , ("value struct", "(x0: Int, x1: Int)", "(1, 1)")
    , ("ref struct", "(x0: Int, x1: String)", "(1, 'hello')")
    , ("value union (value)", "Int | None", "42")
    , ("value union (none)", "Int | None", "none")
    , ("ref union (value)", "Int | String | None", "42")
    , ("ref union (ref)", "Int | String | None", "'hello'")
    , ("ref union (none)", "Int | String | None", "none")
    , ("nullable union (ref)", "String | None", "'hello'")
    , ("nullable union (none)", "String | None", "none")

  ).foreach { case (testName, th, value) =>
    test(test + ":" + testName) {
      generic(th, value)
    }
  }
}
