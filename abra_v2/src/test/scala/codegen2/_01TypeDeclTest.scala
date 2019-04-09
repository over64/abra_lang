package codegen2

import org.scalatest.FunSuite
import codegen2.CodeGenUtil._

class _01TypeDeclTest extends FunSuite {
  test("simple type decl") {
    compile(
      """
        type S1 = (a: Int, b: Double)
        type S2 = (a: Int, b: String)
        type R = (v: Int, next: R)
        type A[t, u] = (value: t, some: u)
        type U2 = Int | U1
        type U1 = Int | String
        type Node = (v: Int, next: Node | None)

        def some = x1: Int, x2: Long, x3: S1, x4: S2, x5: R,
                   x6: Array[String], x7: A[Int, Int], x8: A[String, Float], x9: U2,
                   x10: Node, x11: (x1: Short, x2: Byte) do
          .None
      """)
  }

  test("intermodule type decl") {
    compileModules({
      case "libB" => """
        type B = (x: Int, y: Int)
        """
      case "libA" => """
        import libB .
        type A = (b: libB.B, x: Int)
        """
      case "main" => """
        import libA with A .
        type M = (a: A, x: Int)
        def local = m: M do none .
        """
    })
  }
}