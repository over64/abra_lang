package typecheck

import m3.parse.Ast0._
import m3.typecheck._
import org.scalatest.FunSuite
import m3.typecheck.Util._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by over on 24.10.17.
  */
class _00SpecTest extends FunSuite {
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

//  test("spec type") {
//    // type Int = llvm i32 .
//    // ref type String = llvm i8* .
//    // type C[t] = (w: t)
//    // type B[k] = (y: k, z: Int)
//    // type A[k, v] = (x: B[C[k]], xx: v)
//
//    val declInt = ScalarDecl("universe", ref = false, Seq.empty, "Int", "i32")
//    val declString = ScalarDecl("universe", ref = false, Seq.empty, "String", "i8*")
//    val declC = StructDecl("universe", Seq(GenericTh("T")), "C", Seq(
//      FieldDecl(isSelf = false, "w", ScalarTh(params = Seq.empty, "T", Seq.empty))
//    ))
//    val declB = StructDecl("universe", Seq(GenericTh("K")), "B", Seq(
//      FieldDecl(isSelf = false, "y", ScalarTh(params = Seq.empty, "K", Seq.empty)),
//      FieldDecl(isSelf = false, "z", ScalarTh(params = Seq.empty, "Int", Seq.empty))
//    ))
//    val declA = StructDecl("universe", Seq(GenericTh("K"), GenericTh("V")), "A", Seq(
//      FieldDecl(isSelf = false, "x",
//        ScalarTh(Seq(
//          ScalarTh(Seq(
//            ScalarTh(Seq(),
//              "K", Seq.empty)
//          ), "C", Seq.empty)
//        ), "B", Seq.empty)),
//      FieldDecl(isSelf = false, "xx", ScalarTh(params = Seq.empty, "V", Seq.empty))
//    ))
//
//    val ctx = TContext.forTest(
//      types = Seq(declInt, declString, declC, declB, declA),
//      defs = Map())
//
//    println(ScalarTh(Seq(ScalarTh(Seq.empty, "Int", Seq.empty), ScalarTh(Seq.empty, "String", Seq.empty)), "A", Seq.empty)
//      .toLow(ctx))
//    ctx.lowMod.types.values.foreach(println(_))
//  }
//
//  test("spec def") {
//    // def map = self: Seq[t], mapper: (t) -> u do
//    //   ret: u = seq.make(self.length)
//    //   i = 0
//    //
//    //   while i < self.length do
//    //     ret(i) = mapper(self(i))
//    //     i = i + 1 .
//    //
//    //   ret .Seq[u]
//    //
//    // def main =
//    //   val s = seq.of(1, 2, 3)
//    //   s.map(lambda x -> x.toString)
//
//    val tInt = ScalarDecl("universe", ref = false, Seq(), "Int", "i32")
//    val thInt = ScalarTh(Seq(), "Int", Seq.empty)
//    val thString = ScalarTh(Seq(), "String", Seq.empty)
//    val thT = ScalarTh(Seq(), "T", Seq.empty)
//    val thU = ScalarTh(Seq(), "U", Seq.empty)
//    val thSeqT = ScalarTh(Seq(ScalarTh(Seq(), "T", Seq.empty)), "Seq", Seq.empty)
//    val thSeqU = ScalarTh(Seq(ScalarTh(Seq(), "U", Seq.empty)), "Seq", Seq.empty)
//    val thMapper = FnTh(Seq.empty, Seq(ScalarTh(Seq(), "T", Seq.empty)), ScalarTh(Seq.empty, "U", Seq.empty))
//
//    val defMap = Def(
//      name = "map",
//      lambda = Lambda(
//        args = Seq(
//          Arg("self", thSeqT),
//          Arg("mapper", thMapper)
//        ),
//        body = AbraCode(Seq(
//          Store(thU, Seq(lId("ret")), SelfCall("make", self = lId("seq"), args = Seq(
//            Prop(lId("self"), Seq(lId("length")))
//          ))),
//          Store(AnyTh, Seq(lId("i")), lInt("0")),
//          While(
//            cond = SelfCall("<", self = lId("i"), args = Seq(
//              Prop(lId("self"), Seq(lId("length")))
//            )),
//            _do = Seq(
//              SelfCall("set", lId("ret"), args = Seq(
//                lId("i"),
//                Call(lId("mapper"), args = Seq(
//                  SelfCall("get", lId("self"), args = Seq(lId("i")))
//                )),
//                Store(AnyTh, to = Seq(lId("i")), what = SelfCall("+", lId("i"), args = Seq(lInt("1"))))
//              ))
//            )),
//          lId("ret")
//        ))),
//      retTh = thSeqU)
//
//    //    def prettify(ast: String): String = {
//    //      var mutated = ast
//    //      var iteration = 1
//    //      while(mutated.indexOf("(") != -1) {
//    //        mutated = mutated.replaceFirst("\\(", "\n" + ("\t" * iteration))
//    //        iteration += 1
//    //      }
//    //      mutated
//    //    }
//
//    val ctx = TContext.forTest(types = Seq(tInt), defs = Map())
//    val ast = defMap.spec(Seq(thInt, thString), ctx)
//    print(ast)
//  }
//  test("spec low type") {
//    // type Seq10[T] = llvm [T x 10] .
//    val tInt = ScalarDecl("universe", ref = false, Seq.empty, "Int", "i32")
//    val tSeq10 = ScalarDecl("universe", ref = false, Seq(GenericTh("T")), "Seq10", "[%T x 10]")
//    val thSeq10Int = ScalarTh(Seq(ScalarTh(Seq.empty, "Int", Seq.empty)), "Seq10", Seq.empty)
//
//    val ctx = TContext.forTest(types = Seq(tInt, tSeq10), defs = Map())
//
//    println(thSeq10Int.toLow(ctx))
//    println(ctx.lowMod.types)
//  }
//
//  test("spec low def") {
//    // type Mem[t] = llvm %t* .
//    // def get = self: Mem[t], idx: Int do llvm
//    //   %1 = getelementptr %T* self, i64 0, i64 %idx
//    //   %2 = load %t, %t* %1
//    //   ret %t %2
//    // .t
//    val tInt = ScalarDecl("universe", ref = false, Seq.empty, "Int", "i32")
//    val tMem = ScalarDecl("universe", ref = false, Seq(GenericTh("t")), "Mem", "%t*")
//    val thInt = ScalarTh(Seq.empty, "Int", Seq.empty)
//    val thMemT = ScalarTh(Seq(ScalarTh(Seq.empty, "T", Seq.empty)), "Mem", Seq.empty)
//
//    val defGet = Def(
//      name = "get",
//      lambda = Lambda(
//        args = Seq(
//          Arg("self", thMemT),
//          Arg("idx", thInt)
//        ),
//        body = llVm(
//          """
//            |  %1 = getelementptr %t* self, i64 0, i64 %idx
//            |  %2 = load %T, %t* %1
//            |  ret %t %2 """.stripMargin)
//      ),
//      retTh = GenericTh("t")
//    )
//    val ctx = TContext.forTest(types = Seq(tInt, tMem), defs = Map())
//    println(defGet.spec(Seq(thInt), ctx))
//    println(ctx.lowMod.types)
//  }
//
//  test("call generic") {
//    val gT = GenericTh("T")
//
//    val thT = ScalarTh(Seq.empty, "T", mod = Seq.empty)
//    val thInt = ScalarTh(Seq.empty, "Int", mod = Seq.empty)
//
//    val tNil = ScalarDecl("universe", ref = false, Seq(), "Nil", "void")
//    val tInt = ScalarDecl("universe", ref = false, Seq(), "Int", "i32")
//    val tString = ScalarDecl("universe", ref = true, Seq(), "String", "i8*")
//
//    // def bar[T] = \x: T, y: Int, z: T ->
//    //   z .T
//    val defBar = Def(
//      name = "bar",
//      lambda = Lambda(
//        args = Seq(
//          Arg("x", thT),
//          Arg("y", thInt),
//          Arg("z", thT)
//        ),
//        body = AbraCode(Seq(
//          Ret(Some(lId("z")))
//        ))),
//      retTh = thT)
//
//    // val query = sql' select * from table where id = 'x' 'sql
//    // val pyCode = py'
//    //   x = 1
//    //   y = 2
//    //   z = some(x, y)
//    //   print(z)
//    // 'py
//    // def main = bar('hello', 1, 'world')
//    val defMain = Def(
//      params = Seq.empty,
//      name = "main",
//      lambda = Lambda(
//        args = Seq(),
//        body = AbraCode(Seq(
//          Call(Seq.empty, lId("bar"), Seq(lString("hello"), lInt("1"), lString("world")))
//        ))),
//      retTh = None)
//
//    val ctx = TContext.forTest(
//      defs = Map(
//        "bar" -> defBar,
//        "main" -> defMain),
//      types = Seq(tNil, tInt, tString))
//
//    val header = TypeChecker.evalDef(ctx, new FnScope(None), defMain, Seq.empty)
//
//    println(header)
//    println(ctx.lowMod.defs)
//  }
}