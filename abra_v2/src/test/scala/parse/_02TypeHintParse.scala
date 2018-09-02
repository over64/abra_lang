package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 29.04.17.
  */
class _02TypeHintParse extends FunSuite {
  val parser = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.typeHint() }
  }

  import parser._

  test("scalar") {
    withStr("Int", ScalarTh(Seq.empty, "Int", Seq.empty))
    withStr("Map[K, V]", ScalarTh(Seq(
      ScalarTh(Seq.empty, "K", Seq.empty),
      ScalarTh(Seq.empty, "V", Seq.empty)),
      "Map", Seq.empty))
  }

  test("fn") {
    withStr("() -> None", FnTh(closure = Seq.empty, args = Seq.empty, ScalarTh(Seq.empty, "None", Seq.empty)))

    withStr("(Int) -> None", FnTh(
      closure = Seq.empty,
      args = Seq(ScalarTh(Seq.empty, "Int", Seq.empty)),
      ret = ScalarTh(Seq.empty, "None", Seq.empty)))

    withStr("(Int, Float) -> None", FnTh(
      closure = Seq.empty,
      args = Seq(
        ScalarTh(Seq.empty, "Int", Seq.empty),
        ScalarTh(Seq.empty, "Float", Seq.empty)),
      ret = ScalarTh(Seq.empty, "None", Seq.empty)))
  }

  test("struct") {
    withStr("(x: Int, y: Seq[Int])", StructTh(Seq(
      FieldTh("x", ScalarTh(Seq.empty, "Int", Seq.empty)),
      FieldTh("y", ScalarTh(Seq(ScalarTh(Seq.empty, "Int", Seq.empty)), "Seq", Seq.empty)))))
  }

  test("union") {
    withStr("Int | Float", UnionTh(Seq(
      ScalarTh(Seq.empty, "Int", Seq.empty),
      ScalarTh(Seq.empty, "Float", Seq.empty)
    )))
  }
}
