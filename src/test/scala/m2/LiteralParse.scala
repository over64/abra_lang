package m2

import grammar2.M2Parser
import lang_m2.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class LiteralParse extends FunSuite {
  val literalParser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.literal() }
  }

  test("parse literals") {
    import literalParser._
    withInput("true", lBoolean(AstInfo(1, 0), "true"))
    withInput("false", lBoolean(AstInfo(1, 0), "false"))
    withInput("1", lInt(AstInfo(1, 0), "1"))
    withInput("1438653984654365843659435", lInt(AstInfo(1, 0), "1438653984654365843659435"))
    withInput("1438653984654365843659435.0", lFloat(AstInfo(1, 0), "1438653984654365843659435.0"))
    withInput("'abcd'", lString(AstInfo(1, 0), "abcd"))
    withInput("self", lId(AstInfo(1, 0), "self"))
  }

//  val literalSeqParser = new Util {
//    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.literalSeq() }
//  }
//
//  test("parse literalSeq") {
//    import literalSeqParser._
//    withInput("1.true.'123'.some", Prop(
//      lInt(AstInfo(1, 0), "1"), Seq(
//        lBoolean(AstInfo(1, 2), "true"),
//        lString(AstInfo(1, 7), "123"),
//        lId(AstInfo(1, 13), "some"))
//    ))
//  }
}
