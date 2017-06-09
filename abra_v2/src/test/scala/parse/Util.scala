package parse

import java.io.File

import grammar.{M2Lexer, M2Parser}
import m3.parse.Ast0.ParseNode
import m3.parse.{SourceMap, Visitor}
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{ANTLRFileStream, ANTLRInputStream, CommonTokenStream}
import org.scalatest.Matchers

trait Util extends Matchers {
  def whatToParse: M2Parser => ParseTree

  def withStr[T <: ParseNode](str: String, res: T): Unit = {
    val (ast, srcMap) = parse[T](new ANTLRInputStream(str))
    ast shouldEqual res
  }

  def withFile[T <: ParseNode](file: File, res: T): Unit = {
    val (ast, srcMap) = parse[T](new ANTLRFileStream(file.getAbsolutePath))
    ast shouldEqual res
  }

  def parse[T <: ParseNode](input: ANTLRInputStream): (T, SourceMap) = {
    val lexer = new M2Lexer(input)

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = whatToParse(parser)
    val visitor = new Visitor("test.abra", "test")
    (visitor.visit(tree).asInstanceOf[T], visitor.sourceMap)
  }
}
