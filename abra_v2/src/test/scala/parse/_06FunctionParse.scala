package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 01.05.17.
  */
class _06FunctionParse extends FunSuite {
  val parserFunction = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.function() }
  }

  import parserFunction._

//  test("lldef") {
//    withStr("def + = lldef { x: Int, y: Int -> ;asm }: Int", Fn(
//      tparams = Seq(),
//      name = "+",
//      body = LlDef(Seq(
//        FnArg("x", Some(ScalarTh(Seq(), "Int", "", false))),
//        FnArg("y", Some(ScalarTh(Seq(), "Int", "", false)))),
//        code = " ;asm "),
//      retTh = Some(ScalarTh(Seq(), "Int", "", false))))
//  }
}