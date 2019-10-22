package parse

import grammar.M2Parser
import m3.Ast0._
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
    withStr("Int", ScalarTh(Seq.empty, "Int", None, "prelude"))
    withStr("Map[K, V]", ScalarTh(Seq(
      ScalarTh(Seq.empty, "K", None, "test"),
      ScalarTh(Seq.empty, "V", None, "test")),
      "Map", None, "test"))
  }

  test("fn") {
    withStr("() -> None", FnTh(args = Seq.empty, ScalarTh(Seq.empty, "None", None, "prelude")))

    withStr("(Int) -> None", FnTh(
      args = Seq(ScalarTh(Seq.empty, "Int", None, "prelude")),
      ret = ScalarTh(Seq.empty, "None", None, "prelude")))

    withStr("(Int, Float) -> None", FnTh(
      args = Seq(
        ScalarTh(Seq.empty, "Int", None, "prelude"),
        ScalarTh(Seq.empty, "Float", None, "prelude")),
      ret = ScalarTh(Seq.empty, "None", None, "prelude")))
  }

  test("struct") {
    withStr("(x: Int, y: Seq[Int])", StructTh(Seq(
      FieldTh("x", ScalarTh(Seq.empty, "Int", None, "prelude")),
      FieldTh("y", ScalarTh(Seq(ScalarTh(Seq.empty, "Int", None, "prelude")), "Seq", None, "test")))))
  }

  test("union") {
    withStr("Int | Float", UnionTh(Seq(
      ScalarTh(Seq.empty, "Int", None, "prelude"),
      ScalarTh(Seq.empty, "Float", None, "prelude")
    )))
  }

  test("union with union") {
    withStr("(Int | None) | None", UnionTh(Seq(
      UnionTh(Seq(
        ScalarTh(Seq.empty, "Int", None, "prelude"),
        ScalarTh(Seq.empty, "None", None, "prelude")
      )),
      ScalarTh(Seq.empty, "None", None, "prelude")
    )))
  }
}
