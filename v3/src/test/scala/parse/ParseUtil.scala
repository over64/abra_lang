package parse

import grammar.{M2LexerForIDE, M2Parser}
import m3.Ast0.ParseNode
import m3.parse.Visitor
import org.antlr.v4.runtime._
import org.antlr.v4.runtime.misc.ParseCancellationException
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.Matchers

trait ParseUtil extends Matchers {
  def whatToParse: M2Parser => ParseTree

  def withStr[T <: ParseNode](str: String, res: T): Unit = {
    val ast = parse[T](str)
    ast shouldEqual res
  }

  def parse[T <: ParseNode](input: String): T = {
    val lexer = new M2LexerForIDE(new ANTLRInputStream(input))

    val tokens = new CommonTokenStream(lexer)
    val parser = new M2Parser(tokens)
    parser.setErrorHandler(new BailErrorStrategy)

    try {
      val tree = whatToParse(parser)
      val visitor = new Visitor(input, "test.eva", "test", None)
      visitor.visit(tree).asInstanceOf[T]
    } catch {
      case ex: ParseCancellationException =>
        val cause = ex.getCause.asInstanceOf[InputMismatchException]
        throw new RuntimeException("parse error: " + cause.getOffendingToken)
    }
  }

  def parseStr[T <: ParseNode](input: String): T =
    parse(input)
}
