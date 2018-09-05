package parse

import java.io.File

import grammar.{M2LexerForIDE, M2Parser}
import m3.parse.Ast0.ParseNode
import m3.parse.{SourceMap, Visitor}
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime._
import org.scalatest.Matchers

trait ParseUtil extends Matchers {
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
    val lexer = new M2LexerForIDE(input)

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)
    parser.setErrorHandler(new BailErrorStrategy)

    try {
      val tree = whatToParse(parser)
      val visitor = new Visitor("test.abra", "test")
      (visitor.visit(tree).asInstanceOf[T], visitor.sourceMap)
    } catch {
      case ex: ParseCancellationException =>
        val cause = ex.getCause.asInstanceOf[InputMismatchException]
        throw new RuntimeException("parse error: " + cause.getOffendingToken)
    }
  }

  def parseStr[T <: ParseNode](input: String): (T, SourceMap) =
    parse(new ANTLRInputStream(input))
}
