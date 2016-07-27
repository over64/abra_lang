package m2

import java.io.File

import grammar2.{M2Parser, M2Lexer}
import lang_m2.Ast0.ParseNode
import lang_m2.Visitor
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.{ANTLRFileStream, CommonTokenStream, ANTLRInputStream}
import org.scalatest.Matchers

trait Util extends Matchers {
  def whatToParse: M2Parser => ParseTree

  def withInput(reader: ANTLRInputStream, res: ParseNode): Unit = {
    val lexer = new M2Lexer(reader)

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = whatToParse(parser)
    val ast = new Visitor().visit(tree)

    ast shouldEqual res
  }

  def withInput(input: String, res: ParseNode): Unit =
    withInput(new ANTLRInputStream(input), res)

  def withInput(file: File, res: ParseNode): Unit =
    withInput(new ANTLRFileStream(file.getAbsolutePath), res)

  def parse(input: String): ParseNode = {
    val lexer = new M2Lexer(new ANTLRInputStream(input))

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)

    val tree = whatToParse(parser)
    new Visitor().visit(tree)
  }
}
