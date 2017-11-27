package codegen

import m3.codegen.Ast2._
import org.scalatest.FunSuite

/**
  * Created by over on 08.10.17.
  */
class NewTest extends FunSuite {
  //  val nil = Low(ref = false, llValue = "void")
  //  val bool = Low(ref = false, llValue = "i8")
  //  val int = Low(ref = false, llValue = "i32")
  //  val float = Low(ref = false, llValue = "float")
  //  val string = Low(ref = true, llValue = "i8*")

  val nil = ScalarRef("Nil")
  val bool = ScalarRef("Bool")
  val int = ScalarRef("Int")
  val float = ScalarRef("Float")
  val string = ScalarRef("String")

  val vec2 = ScalarRef("Vec2")
  val tVec2 = Struct("Vec2", Seq(Field("x", int), Field("y", int)))
  val node = ScalarRef("Node")
  val tNode = Struct("Node", Seq(Field("v", int), Field("next", node)))

  val u1 = ScalarRef("U1")
  val tU1 = Union("U1", Seq(int, node, nil))

  val anonVec2 = StructRef(Seq(Field("x", int), Field("y", nil)))
  val anonRefStruct = StructRef(Seq(Field("x", int), Field("y", string)))
  val anonU1 = UnionRef(Seq(int, nil))

//  test("store: local to local") {
//    Def("main", Seq.empty, Seq.empty, nil, AbraCode(
//      vars = Map(
//        // check for low value alloca
//        "lBool" -> int,
//        "lInt" -> int,
//        "lFloat" -> int,
//        "lLow" -> int,
//        // check for low ref alloca
//        "lString" -> string,
//        "lLowRef" -> string,
//        "sVec2" -> vec2, // check for struct value alloca
//        "sNode" -> node, // check for struct ref alloca
//        "u1" -> u1, // check for union alloca
//        "anonS" -> anonVec2, // check for anon struct alloca
//        "anonRefStruct" -> anonRefStruct, // check for anon reference struct alloca
//        "anonU" -> anonU1), // check for anon union alloca
//      stats = Seq(
//        // literals
//        Init(Id("lBool"), BConst("true")),
//        Init(Id("lInt"), IConst("42")),
//        Init(Id("lFloat"), FConst("3.14")),
//        Init(Id("lString"), SConst("hello")),
//        // self-store
//        Init(Id("lLow"), Id("lLow")),
//        Init(Id("lLowRef"), Id("lLowRef")),
//        Init(Id("sVec2"), Id("sVec2")),
//        Init(Id("sNode"), Id("sNode")),
//        Init(Id("u1"), Id("u1")),
//        Init(Id("anonS"), Id("anonS")),
//        Init(Id("anonRefStruct"), Id("anonRefStruct")),
//        Init(Id("anonU"), Id("anonU")),
//        // struct field store: literal, value, ref
//        Init(Id("sVec2", Seq("x")), IConst("42")),
//        Init(Id("sVec2", Seq("x")), Id("lInt")),
//        Init(Id("anonRefStruct", Seq("x")), Id("lInt")),
//        Init(Id("anonRefStruct", Seq("y")), Id("lString")),
//        Init(Id("sNode", Seq("next")), Id("sNode")),
//        // store variant: literal, value, ref
//        Init(Id("u1"), IConst("42")),
//        Init(Id("u1"), Id("lInt")),
//        Init(Id("u1"), Id("sNode"))
//        // check for rcDec on ref types
//      )))
//  }
//
//  test("store: local to param") {
//    Def("main",
//      Seq.empty,
//      Seq(
//        Field("x0", vec2),
//        Field("x1", node)
//      ),
//      nil,
//      code = AbraCode(
//        vars = Map(
//          "lInt" -> int,
//          "lNode" -> node),
//        stats = Seq(
//          // literals
//          Init(Id("x0", Seq("x")), IConst("1")),
//          Init(Id("x0", Seq("x")), Id("lInt")),
//          Init(Id("x1", Seq("v")), IConst("1")),
//          Init(Id("x1", Seq("next")), Id("lNode"))
//        )))
//  }

  test("store: local to closure_local") {

  }

  test("store: local to closure_param") {

  }

  test("store: param to local") {

  }

  test("store: param to param") {

  }

  test("store: param to closure_local") {

  }

  test("store: param to closure_param") {

  }

  test("closure_local to local") {

  }

  test("closure_local to param") {

  }

  test("closure_local to closure_local") {

  }

  test("closure_local to closure_param") {

  }

  test("closure_param to local") {

  }

  test("closure_param to param") {

  }

  test("closure_param to closure_local") {

  }

  test("closure_param to closure_param") {

  }

  test("call: local args pass") {
    Def("bar",
      Seq.empty,
      Seq(
        Field("x3", int), // check low value
        Field("x4", string), // check low ref
        Field("x5", vec2), // check value struct
        Field("x6", node), // check ref struct
        Field("x7", u1), // check union
        Field("x8", anonVec2), // check anon struct
        Field("x9", anonRefStruct), // check anon ref struct
        Field("x10", anonU1)), // check anon union
      ret = nil,
      code = AbraCode(
        vars = Map.empty, stats = Seq.empty))
    Def("main",
      Seq.empty,
      Seq.empty,
      nil,
      AbraCode(
        vars = Map(
          "lInt" -> int, // check for low value alloca
          "lString" -> string, // check for low ref alloca
          "sVec2" -> vec2, // check for struct value alloca
          "sNode" -> node, // check for struct ref alloca
          "u1" -> u1, // check for union alloca
          "anonS" -> anonVec2, // check for anon struct alloca
          "anonRefStruct" -> anonRefStruct, // check for anon ref struct alloca
          "anonU" -> anonU1), // check for anon union alloca
        stats = Seq(
          Call(Id("bar"), Seq(
            Id("lInt"),
            Id("lString"),
            Id("sVec2"),
            Id("sNode"),
            Id("u1"),
            Id("anonS"),
            Id("anonRefStruct"),
            Id("anonU")
          ))
        )))

    // don't forget rcDec
  }

  test("call: param args pass") {

  }

  test("call: closure_local args pass") {

  }

  test("call: closure_param args pass") {

  }

  test("return: local") {

  }

  test("return: param") {

  }

  test("return: closure_local") {

  }

  test("return: closure_param") {

  }
}
