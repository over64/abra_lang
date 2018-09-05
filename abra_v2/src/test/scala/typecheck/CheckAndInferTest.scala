package typecheck

import grammar.M2Parser
import m3.codegen.IrUtil
import m3.parse.Ast0.{GenericType, TypeDecl, TypeHint}
import m3.typecheck.{Invoker, TContext}
import org.antlr.v4.runtime.tree.ParseTree
import org.scalatest.FunSuite
import parse.ParseUtil

import scala.collection.mutable

class CheckAndInferTest extends FunSuite {
  val typeHintParser = new ParseUtil {
    override def whatToParse: M2Parser => ParseTree = { parser => parser.typeHint() }
  }

  implicit class RichString(self: String) {
    def th = typeHintParser.parseStr[TypeHint](self)._1
  }

  val typeParser = new ParseUtil {
    override def whatToParse: M2Parser => ParseTree = { parser => parser.`type`() }
  }

  def parseTypes(types: Seq[String]) =
    types.map(t => typeParser.parseStr[TypeDecl](t)._1)

  val ctx = new TContext(0, mutable.Stack[String](), IrUtil.Mod(), 0, "test", Seq.empty, Map(), Seq.empty,
    parseTypes(Seq(
      "type None = llvm void .",
      "type Bool = llvm i8 .",
      "type Int = llvm i32 .",
      "type String = ref llvm i8* .",
      "type U1 = Int | String | Bool .",
      "type Value[T] = (v: T) ."
    )).map(td => (td.name, td)).toMap, Map(), Map(), new mutable.HashMap())

  test("check and infer: scalar & scalar") {
    val empty = new mutable.HashMap[GenericType, TypeHint]()
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int".th, has = "Int".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int".th, has = "None".th) === false)
  }

  test("check and infer: scalar & union") {
    val empty = new mutable.HashMap[GenericType, TypeHint]()
    assert(Invoker.checkAndInfer(ctx, empty, expected = "U1".th, has = "Int".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "U1".th, has = "None".th) === false)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "U1".th, has = "Int | Bool".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "U1".th, has = "Int | None".th) === false)
  }

  test("check and infer: union & scalar") {
    val empty = new mutable.HashMap[GenericType, TypeHint]()
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String".th, has = "Int".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String".th, has = "None".th) === false)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String | Bool".th, has = "U1".th) === false)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String | Bool | None".th, has = "U1".th) === false)
  }

  test("check and infer: union & union") {
    val empty = new mutable.HashMap[GenericType, TypeHint]()
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String | None".th, has = "Int | None".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String".th, has = "Int | String | None".th) === false)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Int | String".th, has = "Int | None".th) === false)
  }

  test("check and infer: struct & struct") {
    val empty = new mutable.HashMap[GenericType, TypeHint]()
    assert(Invoker.checkAndInfer(ctx, empty, expected = "(x: Int, y: String)".th, has = "(a: Int, b: String)".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "(x: Int, y: String)".th, has = "(a: Int, b: String, c: None)".th) === false)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "(x: Int, y: String)".th, has = "(a: Int, b: None)".th) === false)
  }

  test("check and infer: generic") {
    val empty = new mutable.HashMap[GenericType, TypeHint]()
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Value[Int]".th, has = "Value[Int]".th) === true)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Value[Int | String]".th, has = "Value[Int]".th) === false)
    assert(Invoker.checkAndInfer(ctx, empty, expected = "Value[Int | String]".th, has = "Value[String]".th) === false)
  }
}