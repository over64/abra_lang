package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 29.04.17.
  */
class _03PatternMatchingParse extends FunSuite {
  val parserMatchOver = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.matchOver() }
  }

  val parserMatchCase = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.matchCase() }
  }

  {
    import parserMatchOver._

    test("matchOver: matchDash") {
      withStr("_", Dash)
    }

    test("matchOver: bindVar") {
      withStr("y", BindVar(lId("y")))
    }

    test("matchOver: matchExpression: matchId") {
      withStr("$x", lId("x"))
      withStr("$self", lId("self"))
    }

    test("matchOver: matchExpression: literal") {
      withStr("true", lBoolean("true"))
    }

    test("matchOver: matchExpression: matchBracketsExpr") {
      withStr("${ 1 }", lInt("1"))
      withStr("${1 + 1}", SelfCall("+", lInt("1"), Seq(lInt("1"))))
    }

    test("matchOver: destruct") {
      withStr("Bar(1)", Destruct(None, ScalarTh(Seq(), "Bar", "", false), Seq(lInt("1"))))
      withStr("Bar(1, 2)", Destruct(None, ScalarTh(Seq(), "Bar", "", false), Seq(lInt("1"), lInt("2"))))
      withStr("x = Bar(1, 2)", Destruct(Some(lId("x")), ScalarTh(Seq(), "Bar", "", false), Seq(lInt("1"), lInt("2"))))
    }
  }

  test("matchCase") {
    import parserMatchCase._
    withStr("of 1 -> 1", Case(lInt("1"), None, Seq(lInt("1"))))
    withStr("of x -> { 1; 1 }", Case(BindVar(lId("x")), None, Seq(lInt("1"), lInt("1"))))
    withStr("of 1 if true -> 1", Case(lInt("1"), Some(lBoolean("true")), Seq(lInt("1"))))
  }
}
