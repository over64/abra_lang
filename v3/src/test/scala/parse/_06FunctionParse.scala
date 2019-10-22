package parse

import grammar.M2Parser
import m3.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

import scala.collection.immutable.ArraySeq

/**
  * Created by over on 01.05.17.
  */
class _06FunctionParse extends FunSuite {
  val parserFunction = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.`def`() }
  }

  import parserFunction._

  test("llvm function") {
    withStr("def + = x: Int, y: Int native ;asm .Int", Def(
      name = "+",
      lambda = Lambda(ArraySeq(
        Arg("x", ScalarTh(ArraySeq(), "Int", None, "prelude")),
        Arg("y", ScalarTh(ArraySeq(), "Int", None, "prelude"))),
        body = llVm(";asm")),
      retTh = ScalarTh(ArraySeq(), "Int", None, "prelude")))
  }
}