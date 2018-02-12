package typecheck

import java.io.{File, FileOutputStream, InputStream, PrintStream}
import java.nio.file.{Files, Path, Paths}
import java.util.Scanner

import codegen.LowUtil
import grammar.{M2Lexer, M2LexerForIDE, M2Parser}
import m3.codegen.Ast2.Low
import m3.codegen.IrGen2
import m3.parse.Ast0.{Module, ScalarDecl}
import m3.parse.Visitor
import m3.typecheck.{Namespace, TypeChecker}
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import org.scalatest.FunSuite

/**
  * Created by over on 23.10.17.
  */
class EvalFullTest extends FunSuite with LowUtil {
  val testBase = "/tmp/"

  def prettyPrint(any: Any): Unit = {
    var showLine = true
    var tabSize = 4
    var level = 0
    var inGeneric = false
    any.toString.foreach {
      case '[' => inGeneric = true; print('[')
      case ']' => inGeneric = false; print(']')
      case '(' =>
        level += 1
        println()
        if (showLine) {
          print(("|" + " " * (tabSize - 1)) * (level - 1))
          print("|" + "-" * (tabSize - 1))
        } else {
          print(" " * tabSize * level)
        }
      case ')' =>
        level -= 1
      case ',' =>
        if (!inGeneric) {
          println()
          if (showLine) {
            print(("|" + " " * (tabSize - 1)) * (level - 1))
            print("|" + "-" * (tabSize - 1))
          } else {
            print(" " * tabSize * level)
          }
        } else print(',')
      case ' ' =>
      case f => print(f)
    }
    println()
  }

  test("main") {
    val lexer = new M2LexerForIDE(new ANTLRInputStream(
      """
        f + = self: Int, other: Int -> llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int
        type Int = llvm i32 .
        f main = 1 + 1 .
      """))

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)
    val tree = parser.module()

    val ast = new Visitor("test.abra", "test").visit(tree).asInstanceOf[Module]
    prettyPrint(ast)
    val namespace = new Namespace("test", ast.defs, ast.types, Map.empty)
    TypeChecker.infer(namespace)
    println(ast)
    println(namespace.inferedDefs)

    namespace.lowMod.assertRunEquals(exit = Some(2))
  }
}
