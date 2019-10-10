package codegen
import org.scalatest.FunSuite

class _09ClosureLocalOpTest extends FunSuite {
  def generic(th: String, value: String) = {
    CodeGenUtil.run(
      s"""
        def dummy = x: $th do none .
        def main =
          x: $th = $value

          z = ||
            y = x       -- as store src
            x = $value  -- as store dest
            dummy(x)    -- as call arg
            x .()       -- as ret val

          42 .""", exitCode = 42)
  }

  val test = "closure local ops"

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
