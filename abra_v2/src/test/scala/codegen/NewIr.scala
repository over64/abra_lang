package codegen

import m3.codegen.Ast1new._
import m3.codegen._
import org.scalatest.FunSuite

/**
  * Created by over on 27.07.17.
  */
// literals
//     int, float, string, nil, boolean
// variable declare
// store
// equals
// control flow
//     if-else
//     while
//     return
//
//
//
class NewIr extends FunSuite {
  def forModules(modules: Module*) = {
    val out = Out(new LineStream(0), new LineStream(0))
    val resolver = new ModuleSetResolver(modules)

    val (_, main) = resolver.closure(Seq.empty, "test", None, "main").get
    IrGenNew.gen(resolver.typeRef, resolver.closure, "test", main, out)

    out.types.flushTo(System.out)
    out.code.flushTo(System.out)
  }

  test("return small (register-fit) value") {
    // def main = 42
    forModules(Module(pkg = "test", typeMap = Map(), closureMap = Map(
      ("main", None) -> Closure("main", None, Seq.empty, None, AbraCode(Seq(
        Store(Id("$ret", Seq.empty), IConst("42")),
        Ret
      )))
    )))
  }

  test("llvm function call") {
    val tInt = SRef.lnspec("Int")
    forModules(Module(pkg = "test", typeMap = Map(), closureMap = Map(
      ("+", Some(tInt)) -> Closure("+", Some(tInt), Seq.empty, None, LLCode(
        // def + = lldef { self: Int, x: Int ->
        //   %1 = add nsw i32 %self, %x
        //   ret i32 %1
        // }: Int
        """
          | %1 = add nsw i32 %self, %x
          | ret i32 %1
        """.stripMargin)),
      ("main", None) -> Closure("main", None, Seq.empty, None, AbraCode(Seq(
        // def main = 1 + 1
        Call(Seq.empty, Id("+", Seq.empty), Seq(Id("$ret", Seq.empty), IConst("1"), IConst("1"))),
        Ret
      )))
    )))
  }
}
