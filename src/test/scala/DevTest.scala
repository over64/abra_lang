import lang_new._
import org.scalatest.FunSuite

class DevTest extends FunSuite {
  test("dev test") {
    val typeMap = Map(
      TypeIdent("Int") -> LlvmTypeDef(0, TypeIdent("Int"), "i32"),
      TypeIdent("A") -> AbraTypeDef(0, TypeIdent("A"), Seq(
        Param(VarIdent(0, "x"), TypeIdent("B")),
        Param(VarIdent(0, "y"), TypeIdent("Int")),
        Param(VarIdent(0, "z"), TypeIdent("Int"))
      )),
      TypeIdent("B") -> AbraTypeDef(0, TypeIdent("B"), Seq(
        Param(VarIdent(0, "b1"), TypeIdent("Int")),
        Param(VarIdent(0, "b2"), TypeIdent("Int"))
      ))
    )
    val symTab = scala.collection.mutable.HashMap(VarIdent(0, "v") -> TypeIdent("A"))
    val access = Access(0, Seq(VarIdent(0, "v"), VarIdent(0, "x"), VarIdent(0, "b2")))

    val gep = new TypeChecker(Module(Seq())).evalAccess(access, typeMap, symTab)
    println(gep)
  }

  test("eval fn test") {
    val typeMap = Map(
      TypeIdent("Int") -> LlvmTypeDef(0, TypeIdent("Int"), "i32"),
      TypeIdent("A") -> AbraTypeDef(0, TypeIdent("A"), Seq(
        Param(VarIdent(0, "x"), TypeIdent("B")),
        Param(VarIdent(0, "y"), TypeIdent("Int")),
        Param(VarIdent(0, "z"), TypeIdent("Int"))
      )),
      TypeIdent("B") -> AbraTypeDef(0, TypeIdent("B"), Seq(
        Param(VarIdent(0, "b1"), TypeIdent("Int")),
        Param(VarIdent(0, "b2"), TypeIdent("Int"))
      ))
    )
    val symTab = scala.collection.mutable.HashMap(
      VarIdent(0, "v") -> TypeIdent("A"),
      VarIdent(0, "f") -> TypeIdent("Int")
    )
    val fnTab = Map(
      FnIdent("+") -> LlvmFn(0, FnIdent("+"), Seq(
        Param(VarIdent(0, "self"), TypeIdent("Int")),
        Param(VarIdent(0, "other"), TypeIdent("Int"))),
        "add nsw 1 1",
        Some(TypeIdent("Int"))
      )
    )

    val access = Access(0, Seq(VarIdent(0, "v"), VarIdent(0, "x"), VarIdent(0, "b2")))
    val call1 = FnCall(0, FnIdent("+"), Seq(Uint(0, 1), VarIdent(0, "f")))
    val call2 = FnCall(0, FnIdent("+"), Seq(call1, access))

    val eval = new TypeChecker(Module(Seq())).evalFnCall(call2, typeMap, symTab, fnTab)
    println(eval)
  }
}
