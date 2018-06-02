package typecheck

import m3.parse.Ast0._
import m3.typecheck.Util.FnAdvice
import m3.typecheck._
import org.scalatest.FunSuite

import scala.collection.mutable

class TypeCheckTest extends FunSuite {
  val tInt = ScalarDecl("universe", ref = false, Seq(), "Int", "i32")
  val tString = ScalarDecl("universe", ref = true, Seq(), "String", "i8*")
  val tBool = ScalarDecl("universe", ref = true, Seq(), "Bool", "i8")

  val thInt = ScalarTh(Seq.empty, "Int", mod = None)
  val thT = ScalarTh(Seq.empty, "T", mod = None)
  val thU = ScalarTh(Seq.empty, "U", mod = None)

  val gT = GenericType("T")
  val gU = GenericType("U")

  test("conditions") {
    // if true do 1 else 'hello'
    val defBar = Def(
      params = Seq.empty,
      name = "bar",
      lambda = Lambda(
        args = Seq.empty,
        AbraCode(Seq(
          If(
            cond = lBoolean("true"),
            _do = Seq(lInt("1")),
            _else = Seq(lString("hello")))))),
      retTh = None)

    val bar = DefCont(defBar, mutable.ListBuffer.empty)
    val ctx = new TContext()
    val namespace = new Namespace(pkg = "",
      defs = Map("bar" -> bar),
      types = Seq(tInt, tBool, tString))
    val (header, lowDef) = TypeChecker.evalDef(ctx, namespace, new FnScope(None), bar, Seq.empty)
    println(header)
    println(lowDef)
  }

  def prettyPrint(any: Any): Unit = {
    var showLine = true
    var tabSize = 4
    var level = 0
    var inGeneric = false
    any.toString.foreach {
      case '[' => inGeneric = true; print('[')
      case ']' => inGeneric = false; print(']')
      case '(' =>
        level += 1
        println()
        if (showLine) {
          print(("|" + " " * (tabSize - 1)) * (level - 1))
          print("|" + "-" * (tabSize - 1))
        } else {
          print(" " * tabSize * level)
        }
      case ')' =>
        level -= 1
      case ',' =>
        if (!inGeneric) {
          println()
          if (showLine) {
            print(("|" + " " * (tabSize - 1)) * (level - 1))
            print("|" + "-" * (tabSize - 1))
          } else {
            print(" " * tabSize * level)
          }
        } else print(',')
      case ' ' =>
      case f => print(f)
    }
    println()
  }

  //FIXME: enable when generics will be ready
  //  test("lambda") {
  //    // def bar[T, U] = \t: T, mapper: \T -> U ->
  //    //   mapper(t) .
  //    // def main =
  //    //   val i = 42
  //    //   bar(1, \x -> i) .
  //
  //    val defBar = Def(
  //      params = Seq(gT, gU),
  //      name = "bar",
  //      lambda = Lambda(
  //        args = Seq(
  //          Arg("t", Some(thT)),
  //          Arg(
  //            "mapper",
  //            Some(FnTh(
  //              closure = Seq.empty,
  //              args = Seq(thT),
  //              ret = thU)))),
  //        body = AbraCode(Seq(
  //          Call(Seq.empty, lId("mapper"), Seq(lId("t")))))),
  //      retTh = None)
  //
  //    val defMain = Def(
  //      params = Seq.empty,
  //      name = "main",
  //      lambda = Lambda(
  //        args = Seq.empty,
  //        body = AbraCode(Seq(
  //          Store(None, Seq(lId("i")), lInt("42")),
  //          Call(Seq.empty, lId("bar"), Seq(
  //            lInt("1"),
  //            Lambda(
  //              Seq(Arg("x", None)),
  //              AbraCode(Seq(lId("i"))))
  //          ))
  //        ))),
  //      retTh = None)
  //
  //    val namespace = new Namespace(pkg = "", Seq(), defs = Seq(defBar, defMain), types = Seq(tInt), mods = Map.empty)
  //    prettyPrint(TypeChecker.evalDef(namespace, new FnScope(None), DefSpec("main", Seq.empty), defMain))
  //    prettyPrint(namespace.lowMod.defs)
  //  }
}
