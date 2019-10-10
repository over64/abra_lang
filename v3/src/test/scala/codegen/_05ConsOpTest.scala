package codegen
import org.scalatest.FunSuite

class _05ConsOpTest extends FunSuite {
  def generic(tname: String, tdecl: String, cons: String): Unit =
    CodeGenUtil.run(
      s"""
        type $tname = $tdecl
        def mk = $cons .
        def dummy = x: $tname do none .

        def main =
          mk()
          dummy($cons)
          x = $cons
          42 . """, exitCode = 42)

  test("cons op: value struct") {
    generic("Foo", "(x: Int, y: Int)", "Foo(1, 2)")
  }

  test("cons op: ref struct") {
    generic("Foo", "(x: Int, y: String)", "Foo(1, 'hello')")
  }
}
