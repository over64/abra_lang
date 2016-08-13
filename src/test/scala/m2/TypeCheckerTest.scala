package m2

import grammar2.M2Parser
import lang_m2.Ast0._
import lang_m2.{IrGen, TypeChecker, Ast1}
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class TypeCheckerTest extends FunSuite {
  val moduleParser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }
  val typeChecker = new TypeChecker()
  test("type def test") {
    val src =
      """
        |type Unit = llvm { void }
        |type Boolean = llvm { i1 }
        |type Int = llvm { i32 }
        |type Float = llvm { f32 }
        |type String = llvm { i8* }
        |type Vec3 = (x: Float, y: Float, z: Float)
        |type Fd = (self handle: Int)
        |type FnPtr = (name: String, ptr: () -> Unit)
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }

  test("simple test") {
    val src =
      """
        |type Unit = llvm { void }
        |type Boolean = llvm { i1 }
        |type Float = llvm { float }
        |type Int = llvm { i32 }
        |
        |def +: (self: Int, other: Int) -> Int = llvm {
        |  %1 = add nsw i32 %other, %self
        |  ret i32 %1
        |}
        |def *: (self: Int, other: Int) -> Int = llvm {
        |  %1 = mul nsw i32 %other, %self
        |  ret i32 %1
        |}
        |def twice = \self: Int -> self + self
        |
        |type Vec3 = (x: Int, y: Int, z: Int)
        |
        |def + = \self: Vec3, other: Vec3 ->
        |  Vec3(self.x + other.x, self.y + other.y, self.z + other.z)
        |
        |def main = {
        |  val a = 1 + 2.twice * 3
        |  val v1 = Vec3(1, 2, 1)
        |  val v2 = Vec3(3, 2, 1)
        |  val v3 = v1 + v2
        |  a + v3.x
        |}: Int
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }

  test("a store and access test") {
    val src =
      """
        |type Int = llvm { i32 }
        |type Vec3 = (x: Int, y: Int, z: Int)
        |
        |def +: (self: Int, other: Int) -> Int = llvm {
        |  %1 = add nsw i32 %other, %self
        |  ret i32 %1
        |}
        |
        |def main = {
        |  var a = Vec3(1, 1, 1)
        |  a.x = a.y + a.z
        |  a.x
        |}: Int
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }

  test("float test") {
    val src =
      """
        |type Unit = llvm { void }
        |type Float = llvm { float }
        |type Int = llvm { i32 }
        |
        |def +: (self: Float, other: Float) -> Float = llvm {
        |  %1 = fadd float %other, %self
        |  ret float %1
        |}
        |def *: (self: Float, other: Float) -> Float = llvm {
        |  %1 = fmul float %other, %self
        |  ret float %1
        |}
        |
        |def toInt: (self: Float) -> Int = llvm {
        |  %1 = fptosi float %self to i32
        |  ret i32 %1
        |}
        |
        |def main = {
        | val pi = 3.14
        | val a = pi * pi + 2.1
        |
        | a.toInt
        |}: Int
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }

  test("strings test (Hello, world)") {
    val src =
      """
        |type Unit = llvm { void }
        |type String = llvm { i8* }
        |
        |def print: (self: String) -> Unit = llvm  {
        |  %1 = call i32 @puts(i8* %self)
        |  ret void
        |}
        |
        |def main = \ ->
        |  'Привет, мир!'.print
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }

  test("a conditions test") {
    val src =
      """
        |type Unit = llvm { void }
        |type Boolean = llvm { i1 }
        |type Int = llvm { i32 }
        |
        |def >: (self: Int, other: Int) -> Boolean = llvm {
        |  %1 = icmp sgt i32 %self, %other
        |  ret i1 %1
        |}
        |
        |def foo = {
        |}: Unit
        |
        |def main = {
        |  val a = 11
        |  if a > 11 then foo() else 1
        |
        |  val b = if a > 11 then true else false
        |  b
        |}: Boolean
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }

  test("a while loop test") {
    val src =
      """
        |type Unit = llvm { void }
        |type Boolean = llvm { i1 }
        |type Int = llvm { i32 }
        |
        |def +: (self: Int, other: Int) -> Int = llvm {
        |  %1 = add nsw i32 %other, %self
        |  ret i32 %1
        |}
        |def *: (self: Int, other: Int) -> Int = llvm {
        |  %1 = mul nsw i32 %other, %self
        |  ret i32 %1
        |}
        |def <: (self: Int, other: Int) -> Boolean = llvm {
        |  %1 = icmp slt i32 %self, %other
        |  ret i1 %1
        |}
        |
        |def main = {
        |  var a = 0
        |  while a < 100 { a = a + 1 }
        |  a
        |}: Int
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }
}
