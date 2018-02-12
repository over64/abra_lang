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

  test("call") {
    withStr("a(1,2)", Call(Seq.empty, lId("a"), Seq(lInt("1"), lInt("2"))))
    withStr("1(1,2)", Call(Seq.empty, lInt("1"), Seq(lInt("1"), lInt("2"))))
  }

  test("lambda") {
    withStr("f 1 .", Lambda(Seq(), AbraCode(Seq(lInt("1")))))
    withStr("f self: Int -> self",
      Lambda(Seq(Arg("self", Some(ScalarTh(Seq.empty, "Int", None)))), AbraCode(Seq(lId("self")))))
  }

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

  test("cond") {
    withStr("if true do 1 .", If(lBoolean("true"), Seq(lInt("1")), Seq()))
    withStr("if 1 == 1 do 1 .", If(SelfCall(Seq.empty, "==", lInt("1"), Seq(lInt("1"))), Seq(lInt("1")), Seq()))
    withStr("if true do 1 else 2 .", If(lBoolean("true"), Seq(lInt("1")), Seq(lInt("2"))))

    withStr("if true do f x -> x .", If(lBoolean("true"), Seq(Lambda(Seq(Arg("x", None)), AbraCode(Seq(lId("x"))))), Seq()))
    withStr("if true do 1 else f x -> x .", If(lBoolean("true"),
      Seq(lInt("1")),
      Seq(Lambda(Seq(Arg("x", None)), AbraCode(Seq(lId("x")))))))
  }


}

