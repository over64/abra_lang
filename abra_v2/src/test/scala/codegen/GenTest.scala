package codegen

import grammar.{M2Lexer, M2Parser}
import m3.codegen.CodeGen
import m3.parse.Ast0._
import m3.parse.Visitor
import org.antlr.v4.runtime.{ANTLRInputStream, CommonTokenStream}
import org.scalatest.FunSuite

/**
  * Created by over on 27.09.17.
  */
class GenTest extends FunSuite {
  implicit class AbraCode(str: String) {
    def code: Module = {
      val lexer = new M2Lexer(new ANTLRInputStream(str))

      val tokens = new CommonTokenStream(lexer)
      val parser = new M2Parser(tokens)

      val tree = parser.module()
      val visitor = new Visitor("test.abra", "test")
      visitor.visit(tree).asInstanceOf[Module]
    }
  }

  def withCode(module: Module): Unit =
    CodeGen.compile(module, System.out)

  test("simple main") {
    withCode("def main = 42".code)
  }
}
