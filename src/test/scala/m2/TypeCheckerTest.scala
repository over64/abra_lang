package m2

import java.io.{FileOutputStream, PrintStream}

import grammar2.M2Parser
import lang_m2.Ast0._
import lang_m2._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class TypeCheckerTest extends FunSuite {
  val moduleParser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }

  def dotypeCheck(src: String) = {
    val (ast0, sourceMap) = moduleParser.parse[Module](src)
    println(ast0)

    val typeChecker = new TypeChecker()
    val typeCheckerResult = typeChecker.transform(ast0, sourceMap)
    typeCheckerResult match {
      case TypeCheckSuccess(ast1) =>
        println(ast1)
        val out = new PrintStream(System.out)
        new IrGen(out).gen(ast1)
      case TypeCheckFail(at, error) =>
        throw new Exception(s"at ${at.fname}:${at.line}:${at.line} -> \n\t$error")
    }
  }

  test("type def test") {
    dotypeCheck(
      """
        |type Vec3 = (x: Float, y: Float, z: Float)
        |type Fd = (self handle: Int)
        |type FnPtr = (name: String, ptr: () -> Unit)
      """.stripMargin)
  }

  test("simple test") {
    dotypeCheck(
      """
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
      """.stripMargin)
  }

  test("a store and access test") {
    dotypeCheck(
      """
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
      """.stripMargin)
  }

  test("strings test (Hello, world)") {
    dotypeCheck(
      """
        |def print: (self: String) -> Unit = llvm  {
        |  %1 = call i32 @puts(i8* %self)
        |  ret void
        |}
        |
        |def main =
        |  'こんにちは、世界!'.print
      """.stripMargin)
  }

  test("a conditions test") {
    dotypeCheck(
      """
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
      """.stripMargin)
  }

  test("a while loop test") {
    dotypeCheck(
      """
        |def +: (self: Int, other: Int) -> Int = llvm {
        |  %1 = add nsw i32 %other, %self
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
      """.stripMargin)
  }




  test("macro: equals gen test") {
    dotypeCheck(
      """
        |type Foo = (a: Int, b: Float, c: Int)
        |
        |def main = {
        |   val foo1 = Foo(1, 1.0, 1)
        |   val foo2 = Foo(1, 1.0, 0)
        |   foo1 != foo2
        |}: Boolean
      """.stripMargin)
  }

  test("lazy && and || boolean test") {
    dotypeCheck(
      """
        |def print: (self: String) -> Unit = llvm  {
        |  %1 = call i32 @puts(i8* %self)
        |  ret void
        |}
        |
        |def foo = {
        |   'foo evaluated'.print
        |   false
        |}: Boolean
        |
        |def bar = {
        |   'bar evaluated'.print
        |   true
        |}: Boolean
        |
        |def main = {
        |   foo() && bar()
        |   foo() || bar()
        |}: Unit
      """.stripMargin)
  }
}
