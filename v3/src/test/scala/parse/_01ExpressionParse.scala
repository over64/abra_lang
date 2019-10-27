package parse

/**
  * Created by over on 29.04.17.
  */
package parse

import grammar.M2Parser
import m3.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

import scala.collection.immutable.ArraySeq

class _01ExpressionParse extends FunSuite {
  val parser = new ParseUtil {
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
    withStr("()", Tuple(ArraySeq()))
    withStr("(0, a)", Tuple(ArraySeq(lInt("0"), lId("a"))))
  }

  test("paren") {
    withStr("(a)", lId("a")) // simple
  }

  test("self call") {
    withStr("a.b()", SelfCall( "b", lId("a"), ArraySeq()))
    withStr("a.b(1)", SelfCall( "b", lId("a"), ArraySeq(lInt("1"))))
    withStr("a.*()", SelfCall( "*", lId("a"), ArraySeq()))
    withStr("a./()", SelfCall( "/", lId("a"), ArraySeq()))
    withStr("a.+()", SelfCall( "+", lId("a"), ArraySeq()))
    withStr("a.-()", SelfCall( "-", lId("a"), ArraySeq()))
    withStr("a.<()", SelfCall( "<", lId("a"), ArraySeq()))
    withStr("a.>()", SelfCall( ">", lId("a"), ArraySeq()))
    withStr("a.<=()", SelfCall( "<=", lId("a"), ArraySeq()))
    withStr("a.>=()", SelfCall( ">=", lId("a"), ArraySeq()))
    withStr("a.==()", SelfCall( "==", lId("a"), ArraySeq()))
    withStr("a.!=()", SelfCall( "!=", lId("a"), ArraySeq()))
  }

  test("prop") {
    // Antlr is PZDC parser?
    withStr("a.b .", Prop(lId("a"), ArraySeq(lId("b"))))
    withStr("a.b.c .", Prop(Prop(lId("a"), ArraySeq(lId("b"))), ArraySeq(lId("c"))))
  }

  test("call") {
    withStr("a(1,2)", Call( lId("a"), ArraySeq(lInt("1"), lInt("2"))))
    withStr("1(1,2)", Call( lInt("1"), ArraySeq(lInt("1"), lInt("2"))))
  }

  test("lambda") {
    withStr("|| 1 .", Lambda(ArraySeq(), AbraCode(ArraySeq(lInt("1")))))
    withStr("|self: Int| self .",
      Lambda(ArraySeq(Arg("self", ScalarTh(ArraySeq.empty, "Int", None, "prelude"))), AbraCode(ArraySeq(lId("self")))))
  }

  test("unary call") {
    withStr("!a", SelfCall( "!", lId("a"), ArraySeq()))
    withStr("!true", SelfCall( "!", lBoolean("true"), ArraySeq()))
  }

  test("infix call") {
    withStr("a * b", SelfCall( "*", lId("a"), ArraySeq(lId("b"))))
    withStr("a / b", SelfCall( "/", lId("a"), ArraySeq(lId("b"))))

    withStr("a + b", SelfCall( "+", lId("a"), ArraySeq(lId("b"))))
    withStr("a - b", SelfCall( "-", lId("a"), ArraySeq(lId("b"))))
    withStr("1 to 10", SelfCall( "to", lInt("1"), ArraySeq(lInt("10"))))


    withStr("a < b", SelfCall( "<", lId("a"), ArraySeq(lId("b"))))
    withStr("a > b", SelfCall( ">", lId("a"), ArraySeq(lId("b"))))
    withStr("a <= b", SelfCall( "<=", lId("a"), ArraySeq(lId("b"))))
    withStr("a >= b", SelfCall( ">=", lId("a"), ArraySeq(lId("b"))))

    withStr("a == b", SelfCall( "==", lId("a"), ArraySeq(lId("b"))))
    withStr("a != b", SelfCall( "!=", lId("a"), ArraySeq(lId("b"))))

    withStr("a && b", And(lId("a"), lId("b")))
    withStr("true || false", Or(lBoolean("true"), lBoolean("false")))
  }

  test("cond") {
    withStr("if true do 1 .", If(lBoolean("true"), ArraySeq(lInt("1")), ArraySeq()))
    withStr("if 1 == 1 do 1 .", If(SelfCall( "==", lInt("1"), ArraySeq(lInt("1"))), ArraySeq(lInt("1")), ArraySeq()))
    withStr("if true do 1 else 2 .", If(lBoolean("true"), ArraySeq(lInt("1")), ArraySeq(lInt("2"))))

    withStr("if true do |x| x .", If(lBoolean("true"), ArraySeq(Lambda(ArraySeq(Arg("x", AnyTh)), AbraCode(ArraySeq(lId("x"))))), ArraySeq()))
    withStr("if true do 1 else |x| x .", If(lBoolean("true"),
      ArraySeq(lInt("1")),
      ArraySeq(Lambda(ArraySeq(Arg("x", AnyTh)), AbraCode(ArraySeq(lId("x")))))))
  }


}

