package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 01.05.17.
  */
class _04StatementsParse extends FunSuite {
  val parserBlockBody = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.blockBody() }
  }

  import parserBlockBody._

  test("store") {
    withStr("x: Int = 1", Store(Some(ScalarTh(Seq(), "Int", None)), Seq(lId("x")), lInt("1")))
    withStr("x = 1", Store(None, Seq(lId("x")), lInt("1")))
    withStr("x.y.z = 1", Store(None, Seq(lId("x"), lId("y"), lId("z")), lInt("1")))
    withStr("m(0, 0) = 1", SelfCall(Seq.empty, "set", lId("m"), Seq(lInt("0"), lInt("0"), lInt("1"))))
  }

  test("while") {
    withStr("while true do 1 .", While(lBoolean("true"), Seq(lInt("1"))))
    withStr("while true do 1 ; 1 .", While(lBoolean("true"), Seq(lInt("1"), lInt("1"))))
  }
}