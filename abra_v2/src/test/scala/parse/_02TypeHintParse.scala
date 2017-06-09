package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 29.04.17.
  */
class _02TypeHintParse extends FunSuite {
  val parser = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.typeHint() }
  }

  import parser._

  test("scalar") {
    withStr("Int", ScalarTh(Seq(), "Int", "", pointer = false))
    withStr("*Int", ScalarTh(Seq(), "Int", "", pointer = true))
    withStr("Map[K, V]", ScalarTh(Seq(TypeParam("K"), TypeParam("V")), "Map", "", pointer = false))
  }

  test("fn") {
    withStr("() -> None", FnTh(Seq(), ScalarTh(Seq(), "None", "", false)))
    withStr("(Int) -> None", FnTh(
      args = Seq(ScalarTh(Seq(), "Int", "", false)),
      ret = ScalarTh(Seq(), "None", "", false)))

    withStr("(Int, Float) -> None", FnTh(
      args = Seq(
        ScalarTh(Seq(), "Int", "", false),
        ScalarTh(Seq(), "Float", "", false)),
      ret = ScalarTh(Seq(), "None", "", false)))
  }

  test("struct") {
    withStr("(*Int, Seq[Int])", StructTh(Seq(
      ScalarTh(Seq(), "Int", "", true),
      ScalarTh(Seq(TypeParam("Int")), "Seq", "", false))))
  }

  test("union") {
    withStr("Int | *Float", UnionTh(Seq(
      ScalarTh(Seq(), "Int", "", false),
      ScalarTh(Seq(), "Float", "", true)
    )))
  }
}
