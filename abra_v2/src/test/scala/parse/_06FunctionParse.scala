package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 01.05.17.
  */
class _06FunctionParse extends FunSuite {
  val parserFunction = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.`def`() }
  }

  import parserFunction._

  test("llvm function") {
    withStr("def + = x: Int, y: Int do llvm ;asm .Int", Def(
      name = "+",
      lambda = Lambda(Seq(
        Arg("x", ScalarTh(Seq(), "Int", None)),
        Arg("y", ScalarTh(Seq(), "Int", None))),
        body = llVm(";asm")),
      retTh = ScalarTh(Seq(), "Int", None)))
  }
}