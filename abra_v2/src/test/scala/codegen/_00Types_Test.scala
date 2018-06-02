package codegen

import m3.codegen.Ast2._
import m3.codegen.ConstGen
import org.scalatest.FunSuite

import scala.collection.mutable

object _00Types_Test extends FunSuite {
  val nil = TypeRef("Nil")
  val bool = TypeRef("Bool")
  val int = TypeRef("Int")
  val float = TypeRef("Float")
  val string = TypeRef("String")

  val vec2 = TypeRef("Vec2")
  val tVec2 = Struct("test", "Vec2", Seq(Field("x", int), Field("y", int)))
  val node = TypeRef("Node")
  val tNode = Struct("test", "Node", Seq(Field("v", int), Field("next", TypeRef("Node | Nil"))))

  val tIntAndInt = Struct("test", "(Int, Int)", Seq(Field("x", int), Field("y", int)))
  val tIntAndString = Struct("test", "(Int, String)", Seq(Field("x", int), Field("y", string)))

  val u1 = TypeRef("U1")
  val u2 = TypeRef("U2")
  val u3 = TypeRef("U3")
  val u4 = TypeRef("U4")

  val tU1 = Union("test", "U1", Seq(string, int))
  val tU2 = Union("test", "U2", Seq(nil, u1, string))
  val tU3 = Union("test", "U3", Seq(bool, int))
  val tU4 = Union("test", "U4", Seq(bool, int, string))

  val tNodeOrNil = Union("test", "Node | Nil", Seq(node, nil))
  val tIntOrNil = Union("test", "Int | Nil", Seq(node, nil))

  val intAndInt = TypeRef("(Int, Int)")
  val intAndString = TypeRef("(Int, String)")
  val intOrNil = TypeRef("Int | Nil")

  val tNil = Low("test", ref = false, "Nil", "void")
  val tBool = Low("test", ref = false, "Bool", "i8")
  val tInt = Low("test", ref = false, "Int", "i32")
  val tFloat = Low("test", ref = false, "Float", "float")
  val tString = Low("test", ref = true, "String", "i8*")

  val tDefMain = Fn("\\ -> Int", Seq.empty, Seq.empty, int)

  test("ref type check") {
    import m3.codegen.IrUtil._
    val types = Seq[Type](tNil, tBool, tInt, tFloat, tString, tVec2, tNode, tU1, tU2, tIntAndInt, tIntAndString, tIntOrNil, tNodeOrNil, tDefMain) ++
      ConstGen.types
    val typeMap = mutable.HashMap(types.map(t => (t.name, t)): _*)
    assert(node.isRef(typeMap) === true)
    assert(vec2.isRef(typeMap) === false)
    assert(string.isRef(typeMap) === true)
    assert(u1.isRef(typeMap) === false)
    assert(u2.isRef(typeMap) === false)
    assert(intAndString.isRef(typeMap) === true)
    assert(intAndInt.isRef(typeMap) === false)
    assert(intOrNil.isRef(typeMap) === false)
  }
}
