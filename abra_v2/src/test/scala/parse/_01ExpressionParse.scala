package parse

/**
  * Created by over on 29.04.17.
  */
package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

class _01ExpressionParse extends FunSuite {
  val parser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.expression() }
  }

  import parser._

  test("literals") {
    withStr("1", lInt("1"))
    withStr("0x0", lInt("0"))
    withStr("1.0", lFloat("1.0"))
    withStr("true", lBoolean("true"))
    withStr("''", lString(""))
  }

  test("id") {
    withStr("a", lId("a"))
    withStr("a32", lId("a32"))
    withStr("aBcd", lId("aBcd"))
    withStr("self", lId("self"))
  }

  test("tuple") {
    withStr("()", Tuple(Seq()))
    withStr("(0, a)", Tuple(Seq(lInt("0"), lId("a"))))
  }

  test("paren") {
    withStr("(a)", lId("a")) // simple
  }

  test("self call") {
    withStr("a.b()", SelfCall(Seq.empty, "b", lId("a"), Seq()))
    withStr("a.b(1)", SelfCall(Seq.empty, "b", lId("a"), Seq(lInt("1"))))
    withStr("a.*()", SelfCall(Seq.empty, "*", lId("a"), Seq()))
    withStr("a./()", SelfCall(Seq.empty, "/", lId("a"), Seq()))
    withStr("a.+()", SelfCall(Seq.empty, "+", lId("a"), Seq()))
    withStr("a.-()", SelfCall(Seq.empty, "-", lId("a"), Seq()))
    withStr("a.<()", SelfCall(Seq.empty, "<", lId("a"), Seq()))
    withStr("a.>()", SelfCall(Seq.empty, ">", lId("a"), Seq()))
    withStr("a.<=()", SelfCall(Seq.empty, "<=", lId("a"), Seq()))
    withStr("a.>=()", SelfCall(Seq.empty, ">=", lId("a"), Seq()))
    withStr("a.==()", SelfCall(Seq.empty, "==", lId("a"), Seq()))
    withStr("a.!=()", SelfCall(Seq.empty, "!=", lId("a"), Seq()))
  }

  test("prop") {
    withStr("a.b", Prop(lId("a"), lId("b")))
    withStr("a.b.c", Prop(Prop(lId("a"), lId("b")), lId("c")))
  }

  //  test("call") {
  //    withStr("a(1,2)", Call(lId("a"), Seq(lInt("1"), lInt("2"))))
  //    withStr("1(1,2)", Call(lInt("1"), Seq(lInt("1"), lInt("2"))))
  //  }

  //  test("haskell-like lambda") {
  //    withStr("\\ -> 1 .", Lambda(Seq(), None, AbraExpressions(Seq(lInt("1")))))
  //    withStr("\\self: Int -> self",
  //      Lambda(Seq(FnArg("self", Some(ScalarTh(None, "Int", "")))), None, AbraExpressions(Seq(lId("self")))))
  //  }

  test("unary call") {
    withStr("!a", SelfCall(Seq.empty, "!", lId("a"), Seq()))
    withStr("!true", SelfCall(Seq.empty, "!", lBoolean("true"), Seq()))
  }

  test("infix call") {
    withStr("a * b", SelfCall(Seq.empty, "*", lId("a"), Seq(lId("b"))))
    withStr("a / b", SelfCall(Seq.empty, "/", lId("a"), Seq(lId("b"))))

    withStr("a + b", SelfCall(Seq.empty, "+", lId("a"), Seq(lId("b"))))
    withStr("a - b", SelfCall(Seq.empty, "-", lId("a"), Seq(lId("b"))))
    withStr("1 to 10", SelfCall(Seq.empty, "to", lInt("1"), Seq(lInt("10"))))


    withStr("a < b", SelfCall(Seq.empty, "<", lId("a"), Seq(lId("b"))))
    withStr("a > b", SelfCall(Seq.empty, ">", lId("a"), Seq(lId("b"))))
    withStr("a <= b", SelfCall(Seq.empty, "<=", lId("a"), Seq(lId("b"))))
    withStr("a >= b", SelfCall(Seq.empty, ">=", lId("a"), Seq(lId("b"))))

    withStr("a == b", SelfCall(Seq.empty, "==", lId("a"), Seq(lId("b"))))
    withStr("a != b", SelfCall(Seq.empty, "!=", lId("a"), Seq(lId("b"))))

    withStr("a && b", And(lId("a"), lId("b")))
    withStr("true || false", Or(lBoolean("true"), lBoolean("false")))
  }

  //  test("cond") {
  //    withStr("if true then 1", Cond(lBoolean("true"), Seq(lInt("1")), Seq()))
  //    withStr("if 1 == 1 then 1", Cond(SelfCall("==", lInt("1"), Seq(lInt("1"))), Seq(lInt("1")), Seq()))
  //    withStr("if true then 1 else 2", Cond(lBoolean("true"), Seq(lInt("1")), Seq(lInt("2"))))
  //
  //    withStr("if true then { 1 }", Cond(lBoolean("true"), Seq(lInt("1")), Seq()))
  //    withStr("if true then ({ 1 })", Cond(lBoolean("true"), Seq(Lambda(Seq(), Seq(lInt("1")))), Seq()))
  //
  //    withStr("if true then { x -> x }", Cond(lBoolean("true"), Seq(Lambda(Seq(FnArg("x", None)), Seq(lId("x")))), Seq()))
  //    withStr("if true then \\x -> x", Cond(lBoolean("true"), Seq(Lambda(Seq(FnArg("x", None)), Seq(lId("x")))), Seq()))
  //    withStr("if true then 1 else { x -> x }", Cond(lBoolean("true"),
  //      Seq(lInt("1")),
  //      Seq(Lambda(Seq(FnArg("x", None)), Seq(lId("x"))))))
  //  }

  test("match") {
    withStr("match 1 of 1 -> true", Match(lInt("1"), Seq(
      Case(lInt("1"), None, Seq(lBoolean("true")))
    )))

    withStr("match 1 of 1 -> { true; true } of 2 -> false", Match(lInt("1"), Seq(
      Case(lInt("1"), None, Seq(lBoolean("true"), lBoolean("true"))),
      Case(lInt("2"), None, Seq(lBoolean("false")))
    )))
  }
}

