package codegen2

import org.scalatest.FunSuite
import codegen2.CodeGenUtil._

class _01TypeDeclTest extends FunSuite {
  test("simple type decl") {
    CodeGenUtil.run(
      """
        type S1 = (a: Int, b: Double)
        type S2 = (a: Int, b: String)
        type S3 = (x: String, y: String, z: String)
        type R = (v: Int, next: R)
        type A[t, u] = (value: t, some: u)
        type U2 = Int | U1
        type U1 = Int | String
        type Node = (v: Int, next: Node | None)
        type Node2 = (v: Int, next: Node2 | Node | Int | None)
        type Vec3i = llvm <4 x i32> .
        type U3 = Int | Vec3i

        def main = x1: Int, x2: Long, x3: S1, x4: S2, x5: R,
                   x6: Array[String], x7: A[Int, Int], x8: A[String, Float], x9: U2,
                   x10: Node, x11: Node2, x12: (x1: Short, x2: Byte),
                   x13: S3, x14: Array5[Int], x15: Vec3i, x16: U3,
                   x17: Array[Int] do
           42
          .Int
      """, 42)
  }

  test("intermodule type decl") {
    CodeGenUtil.runModules({
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
        def main = 42 .
        """
    }, 42)
  }
}