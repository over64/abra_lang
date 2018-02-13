package parse

import grammar.M2Parser
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite

/**
  * Created by over on 01.05.17.
  */
class _05TypesParse extends FunSuite {
  val parserType = new ParseUtil {
    override def whatToParse: (M2Parser) => ParseTree = { parser => parser.`type`() }
  }

  import parserType._

  test("scalar") {
    withStr("type Int = llvm i32 .", ScalarDecl(false, Seq.empty, "Int", "i32"))
    withStr("type Int32 = llvm i32 .", ScalarDecl(false, Seq.empty, "Int32", "i32"))
    withStr("ref type I32Bar = llvm i8* .", ScalarDecl(true, Seq.empty, "I32Bar", "i8*"))
    withStr("type FooBar = llvm [i32 x 5] .", ScalarDecl(false, Seq.empty, "FooBar", "[i32 x 5]"))
  }

    test("struct") {
      withStr("type S = (x: Int)", StructDecl(Seq(), "S", Seq(FieldDecl(false, "x", ScalarTh(Seq(), "Int", None)))))
      withStr("type Seq[T] = (length: Int, ptr: Ptr)", StructDecl(Seq(GenericType("T")), "Seq", Seq(
        FieldDecl(false, "length", ScalarTh(Seq(), "Int", None)),
        FieldDecl(false, "ptr", ScalarTh(Seq(), "Ptr", None))
      )))
    }

    test("union") {
      withStr("type StringOpt = String | None", UnionDecl(Seq(), "StringOpt", Seq(
        ScalarTh(Seq(), "String", None),
        ScalarTh(Seq(), "None", None))))

      withStr("type Option[T] = T | None", UnionDecl(Seq(GenericType("T")), "Option", Seq(
        ScalarTh(Seq(), "T", None),
        ScalarTh(Seq(), "None", None)
      )))
    }
}