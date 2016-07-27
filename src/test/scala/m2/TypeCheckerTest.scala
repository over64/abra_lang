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
        |def +: (self: Int, other: Int) -> Int = llvm {
        |  %1 = add nsw i32 %other, %self
        |  ret i32 %1
        |}
        |def main = {
        |  val a = 1 + 1
        |}: Unit
      """.stripMargin

    val ast0 = moduleParser.parse(src).asInstanceOf[Module]
    println(ast0)
    val ast1 = typeChecker.transform(ast0)
    println(ast1)
    new IrGen().gen(ast1)
  }
}
