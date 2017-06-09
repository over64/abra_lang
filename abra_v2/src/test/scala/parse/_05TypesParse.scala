package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 01.05.17.
  */
class _05TypesParse extends FunSuite {
  val parserType = new Util {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.`type`() }
  }

  import parserType._

  test("scalar") {
    withStr("type Int = lltype { i32 }", ScalarDecl("Int", "i32"))
    withStr("type Int32 = lltype { i32 }", ScalarDecl("Int32", "i32"))
    withStr("type I32Bar = lltype { i8* }", ScalarDecl("I32Bar", "i8*"))
    withStr("type FooBar = lltype { [i32 x 5] }", ScalarDecl("FooBar", "[i32 x 5]"))
  }

  test("struct") {
    withStr("type S = (x: Int)", StructDecl(Seq(), "S", Seq(FieldDecl(false, "x", ScalarTh(Seq(), "Int", "", false)))))
    withStr("type Seq[T] = (length: Int, ptr: Ptr)", StructDecl(Seq(TypeParam("T")), "Seq", Seq(
      FieldDecl(false, "length", ScalarTh(Seq(), "Int", "", false)),
      FieldDecl(false, "ptr", ScalarTh(Seq(), "Ptr", "", false))
    )))
  }

  test("union") {
    withStr("type StringOpt = String | None", UnionDecl(Seq(), "StringOpt", Seq(
      ScalarTh(Seq(), "String", "", false),
      ScalarTh(Seq(), "None", "", false))))

    withStr("type Option[T] = T | None", UnionDecl(Seq(TypeParam("T")), "Option", Seq(
      ScalarTh(Seq(), "T", "", false),
      ScalarTh(Seq(), "None", "", false)
    )))
  }
}