package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 01.05.17.
  */
class _04StatementsParse extends FunSuite {
  val parserBlockBody = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.blockBody() }
  }

  import parserBlockBody._

  //  test("variable") {
  //    withStr("val x = 1", Val(false, "x", None, lInt("1")))
  //    withStr("var x = 1", Val(true, "x", None, lInt("1")))
  //    withStr("var x: Int = 1", Val(true, "x", Some(ScalarTh(Seq(), "Int", "", false)), lInt("1")))
  //  }

  test("store") {
    withStr("x = 1", Store(Seq(lId("x")), lInt("1")))
    withStr("x.y.z = 1", Store(Seq(lId("x"), lId("y"), lId("z")), lInt("1")))
    withStr("m(0, 0) = 1", SelfCall(Seq.empty, "set", lId("m"), Seq(lInt("0"), lInt("0"), lInt("1"))))
  }

  test("while") {
    withStr("while true { 1 }", While(lBoolean("true"), Seq(lInt("1"))))
    withStr("while true { 1 ; 1 }", While(lBoolean("true"), Seq(lInt("1"), lInt("1"))))
  }
}