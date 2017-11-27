package codegen

import m3.codegen.Ast2._
import m3.codegen.ConstGen
import org.scalatest.FunSuite

import scala.collection.mutable

class FullTest extends FunSuite with LowUtil {
  val testBase = "/tmp/"
  val nil = ScalarRef("Nil")
  val bool = ScalarRef("Bool")
  val int = ScalarRef("Int")
  val float = ScalarRef("Float")
  val string = ScalarRef("String")

  val vec2 = ScalarRef("Vec2")
  val tVec2 = Struct("Vec2", Seq(Field("x", int), Field("y", int)))
  val node = ScalarRef("Node")
  val tNode = Struct("Node", Seq(Field("v", int), Field("next", UnionRef(Seq(node, nil)))))

  val u1 = ScalarRef("U1")
  val u2 = ScalarRef("U2")
  val tU1 = Union("U1", Seq(string, int))
  val tU2 = Union("U2", Seq(u1, string, nil))

  val anonVec2 = StructRef(Seq(Field("x", int), Field("y", int)))
  val anonRefStruct = StructRef(Seq(Field("x", int), Field("y", string)))
  val anonU1 = UnionRef(Seq(int, nil))

  val tNil = Low(ref = false, "Nil", "void")
  val tBool = Low(ref = false, "Bool", "i8")
  val tInt = Low(ref = false, "Int", "i32")
  val tFloat = Low(ref = false, "Float", "float")
  val tString = Low(ref = true, "String", "i8*")

  val types = Seq[Type](tNil, tBool, tInt, tFloat, tString, tVec2, tNode, tU1, tU2)
  val typeMap = mutable.HashMap(types.map(t => (t.name, t)): _*)

  test("ref type check") {
    import m3.codegen.IrUtil._
    assert(node.isRef(typeMap) === true)
    assert(vec2.isRef(typeMap) === false)
    assert(string.isRef(typeMap) === true)
    assert(u1.isRef(typeMap) === false)
    assert(u2.isRef(typeMap) === false)
    assert(anonRefStruct.isRef(typeMap) === true)
    assert(anonVec2.isRef(typeMap) === false)
    assert(anonU1.isRef(typeMap) === false)
  }

  test("store: literals") {
    val cBool01 = ConstGen.genBoolConst("cBool01", "true")
    val cInt01 = ConstGen.genIntConst("cInt01", "42")
    val cFloat01 = ConstGen.genFloatConst("cFloat01", "3.14")
    val (cString01, lowCode) = ConstGen.genStringConst("cString01", "hello!")

    val defMain = Def("main", Seq.empty, Seq.empty, int, AbraCode(
      vars = Map(
        "lBool" -> bool,
        "lInt" -> int,
        "lFloat" -> float,
        "lString" -> string),
      stats = Seq(
        // literals
        Init(Id("lBool"), Call(Id("cBool01"), Seq.empty)),
        Init(Id("lInt"), Call(Id("cInt01"), Seq.empty)),
        Init(Id("lFloat"), Call(Id("cFloat01"), Seq.empty)),
        Init(Id("lString"), Call(Id("cString01"), Seq.empty)),

        Free(Id("lString")),
        Ret(Some("lInt"))
      )))

    (Seq(lowCode), typeMap, mutable.HashMap(
      "main" -> defMain,
      "cBool01" -> cBool01,
      "cInt01" -> cInt01,
      "cFloat01" -> cFloat01,
      "cString01" -> cString01
    )).assertRunEquals(exit = Some(42))
  }

  test("store: value struct field") {
    val cInt01 = ConstGen.genIntConst("cInt01", "33")
    val cInt02 = ConstGen.genIntConst("cInt02", "42")

    val defMain = Def("main", Seq.empty, Seq.empty, int, AbraCode(
      vars = Map(
        "lInt" -> int,
        "sVec2" -> vec2,
        "ret" -> int),
      stats = Seq(
        Init(Id("lInt"), Call(Id("cInt01"), Seq.empty)),
        Store(Id("sVec2", Seq("x")), Call(Id("cInt02"), Seq.empty)),
        Store(Id("sVec2", Seq("y")), Id("lInt")),

        Init(Id("ret"), Id("sVec2", Seq("y"))),
        Ret(Some("ret"))
      )))

    (Seq(), typeMap, mutable.HashMap(
      "main" -> defMain,
      "cInt01" -> cInt01,
      "cInt02" -> cInt02
    )).assertRunEquals(exit = Some(33))
  }

  test("store: ref struct field") {
    val cInt01 = ConstGen.genIntConst("cInt01", "13")
    val (cString01, lowCode) = ConstGen.genStringConst("cString01", "hi")

    val defInit = Def("anonInit", Seq.empty, Seq(
      Field("x", int),
      Field("y", string)),
      anonRefStruct, LLCode(
        """
          |    %ptr = getelementptr { i32, i8* }, { i32, i8* }* null, i64 1
          |    %size = ptrtoint { i32, i8* }* %ptr to i64
          |    %1 = call i8* @rcAlloc(i64 %size)
          |    %2 = bitcast i8* %1 to { i32, i8* }*
          |    %fx = getelementptr { i32, i8* }, { i32, i8* }* %2, i32 0, i32 0
          |    store i32 %x, i32* %fx
          |    %fy = getelementptr { i32, i8* }, { i32, i8* }* %2, i32 0, i32 1
          |    store i8* %y, i8** %fy
          |    ret { i32, i8* }* %2
        """.stripMargin))
    val defMain = Def("main", Seq.empty, Seq.empty, int, AbraCode(
      vars = Map(
        "x" -> int,
        "y" -> string,
        "z" -> anonRefStruct,
        "anonRet" -> int),
      stats = Seq(
        Init(Id("x"), Call(Id("cInt01"), Seq.empty)),
        Init(Id("y"), Call(Id("cString01"), Seq.empty)),
        Init(Id("z"), Call(Id("anonInit"), Seq(Id("x"), Id("y")))),
        Store(Id("z", Seq("x")), Id("x")),
        Store(Id("z", Seq("y")), Id("y")),

        Init(Id("anonRet"), Id("z", Seq("x"))),

        Free(Id("y")),
        Free(Id("z")),

        Ret(Some("anonRet"))
      )))

    (Seq(lowCode), typeMap, mutable.HashMap(
      "main" -> defMain,
      "anonInit" -> defInit,
      "cInt01" -> cInt01,
      "cString01" -> cString01
    )).assertRunEquals(exit = Some(13))
  }

  test("store: unions") {
    val cInt01 = ConstGen.genIntConst("cInt01", "42")
    val (cString01, lowCode) = ConstGen.genStringConst("cString01", "hi")

    val defMain = Def("main", Seq.empty, Seq.empty, int, AbraCode(
      vars = Map(
        "r" -> int,
        "s" -> string,
        "u1" -> u1,
        "u2" -> u2),
      stats = Seq(
        Init(Id("u1"), Call(Id("cInt01"), Seq.empty)),
        Init(Id("u2"), Call(Id("cString01"), Seq.empty)),
        Store(Id("u2"), Id("u1")),

        Init(Id("r"), Call(Id("cInt01"), Seq.empty)),
        Free(Id("u1")),
        Free(Id("u2")),
        Ret(Some("r"))
      )))

    (Seq(lowCode), typeMap, mutable.HashMap(
      "main" -> defMain,
      "cInt01" -> cInt01,
      "cString01" -> cString01
    )).assertRunEquals(exit = Some(42))
  }
}
