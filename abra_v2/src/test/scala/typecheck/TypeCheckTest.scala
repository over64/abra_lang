package typecheck

import m3.parse.Ast0._
import m3.typecheck.Util.FnAdvice
import m3.typecheck.{DefSpec, FnScope, Namespace, TypeChecker}
import org.scalatest.FunSuite

class TypeCheckTest extends FunSuite {
  val tInt = ScalarDecl(ref = false, Seq(), "Int", "i32")
  val tString = ScalarDecl(ref = true, Seq(), "String", "i8*")
  val tBool = ScalarDecl(ref = true, Seq(), "Bool", "i8")

  val thInt = ScalarTh(Seq.empty, "Int", pkg = None)
  val thT = ScalarTh(Seq.empty, "T", pkg = None)
  val thU = ScalarTh(Seq.empty, "U", pkg = None)

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

    val namespace = new Namespace(pkg = "", Seq(), defs = Seq(defBar), types = Seq(tInt, tBool, tString), mods = Map.empty)
    val advice = DefSpec("bar", Seq.empty)
    val (header, lowDef) = TypeChecker.evalDef(namespace, new FnScope(None), advice, defBar)
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
