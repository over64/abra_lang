package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class _00LiteralParse extends FunSuite {
  val literalParser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.literal() }
  }


  import literalParser._

  test("none") {
    withStr("none", lNone())
  }

  test("boolean") {
    withStr("true", lBoolean("true"))
    withStr("false", lBoolean("false"))
  }

  test("int") {
    withStr("1", lInt("1"))
    withStr("-1", lInt("-1"))
    withStr("1438653984654365843659435", lInt("1438653984654365843659435"))
  }

  test("hex") {
    withStr("0x0", lInt("0"))
    withStr("0x10", lInt("16"))
    withStr("0xFF", lInt("255"))
    withStr("0xff", lInt("255"))
  }

  test("float") {
    withStr("1.0", lFloat("1.0"))
    withStr("-1.0", lFloat("-1.0"))
    withStr("1438653984654365843659435.0", lFloat("1438653984654365843659435.0"))
    withStr("10e-1", lFloat("10e-1"))
    withStr("10e+1", lFloat("10e+1"))
  }

  test("string") {
    withStr("''", lString(""))
    withStr("'abcd'", lString("abcd"))
    withStr("' \\b \\t \\n \\f \\r \\\\ \\' \" '", lString(" \\b \\t \\n \\f \\r \\\\ \\' \" "))
  }
}
