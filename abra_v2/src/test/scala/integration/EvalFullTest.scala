package integration

import codegen.LowUtil
import grammar.{M2LexerForIDE, M2Parser}
import m3.parse.Ast0.Module
import m3.parse.Visitor
import m3.typecheck.{Namespace, TypeChecker}
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import org.scalatest.FunSuite
import parse.ParseUtil

/**
  * Created by over on 23.10.17.
  */
class EvalFullTest extends FunSuite with LowUtil {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.module() }
  }
  val testBase = "/tmp/"

  def assertCodeEquals(code: String, exit: Option[Int] = None, stdout: Option[String] = None, stderr: Option[String] = None) = {
    val (ast, _) = parser.parse[Module](new ANTLRInputStream(code))
    val namespace = new Namespace("test", ast.lowCode, ast.defs, ast.types, Map.empty)
    TypeChecker.infer(namespace)
    namespace.lowMod.assertRunEquals(exit, stdout, stderr)
  }

  test("hello world") {
    assertCodeEquals(
      """
        llvm declare i32 @puts(i8*) .
        
        type None = llvm void .
        ref type String = llvm i8* .
        type Int = llvm i32 .

        f print = self: String -> llvm
          %1 = call i32 @puts(i8* %self)
          ret void .None

        f main = 'hello, world'.print(); 0 .
      """, exit = Some(0), stdout = Some("hello, world"))
  }

  test("low function definition") {
    assertCodeEquals(
      """
        f + = self: Int, other: Int -> llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int
        type Int = llvm i32 .
        f main = 1 + 1 .
      """, exit = Some(2))
  }
}
