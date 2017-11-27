package typecheck

import m3.parse.Ast0._
import m3.typecheck.{FnScope, Namespace, TypeChecker}
import org.scalatest.FunSuite
import m3.typecheck.Util._

/**
  * Created by over on 24.10.17.
  */
class SpecTest extends FunSuite {
  test("spec type") {
    // type Int = llvm i32 .
    // ref type String = llvm i8* .
    // type C[T] = (w: T)
    // type B[K] = (y: K, z: Int)
    // type A[K, V] = (x: B[C[K]], xx: V)
    val declInt = ScalarDecl(ref = false, Seq.empty, "Int", "i32")
    val declString = ScalarDecl(ref = false, Seq.empty, "String", "i8*")
    val declC = StructDecl(Seq(GenericType("T")), "C", Seq(
      FieldDecl(isSelf = false, "w", ScalarTh(params = Seq.empty, "T", None))
    ))
    val declB = StructDecl(Seq(GenericType("K")), "B", Seq(
      FieldDecl(isSelf = false, "y", ScalarTh(params = Seq.empty, "K", None)),
      FieldDecl(isSelf = false, "z", ScalarTh(params = Seq.empty, "Int", None))
    ))
    val declA = StructDecl(Seq(GenericType("K"), GenericType("V")), "A", Seq(
      FieldDecl(isSelf = false, "x",
        ScalarTh(Seq(
          ScalarTh(Seq(
            ScalarTh(Seq(),
              "K", None)
          ), "C", None)
        ), "B", None)),
      FieldDecl(isSelf = false, "xx", ScalarTh(params = Seq.empty, "V", None))
    ))

    val namespace = new Namespace("", Seq(), Seq(declInt, declString, declC, declB, declA),
      Map.empty)

    println(ScalarTh(Seq(ScalarTh(Seq.empty, "Int", None), ScalarTh(Seq.empty, "String", None)), "A", None)
      .toLow(namespace))
    namespace.lowTypes.values.foreach(println(_))
  }

  test("spec def") {
    // def map[T, U] = \self: Seq[T], mapper: \T -> U ->
    //   val ret = seq.make[U](self.length)
    //   var i = 0
    //
    //   while i < self.length do
    //     ret(i) = mapper(self(i))
    //     i = i + 1 .
    //
    //   ret .Seq[U]
    // def main =
    //   val s = seq.of(1, 2, 3)
    //   s.map(\x -> x.toString) # map[Int, String](s, \x -> x.toString)

    val tInt = ScalarDecl(ref = false, Seq(), "Int", "i32")
    val thInt = ScalarTh(Seq(), "Int", None)
    val thString = ScalarTh(Seq(), "String", None)
    val thT = ScalarTh(Seq(), "T", None)
    val thU = ScalarTh(Seq(), "U", None)
    val thSeqT = ScalarTh(Seq(ScalarTh(Seq(), "T", None)), "Seq", None)
    val thSeqU = ScalarTh(Seq(ScalarTh(Seq(), "U", None)), "Seq", None)
    val thMapper = FnTh(Seq.empty, Seq(ScalarTh(Seq(), "T", None)), ScalarTh(Seq.empty, "U", None))

    val defMap = Def(
      params = Seq(GenericType("T"), GenericType("U")),
      name = "map",
      lambda = Lambda(
        args = Seq(
          Arg("self", Some(thSeqT)),
          Arg("mapper", Some(thMapper))
        ),
        body = AbraCode(Seq(
          Val(mutable = false, "ret", None, SelfCall(Seq(thU), "make", self = lId("seq"), args = Seq(
            Prop(lId("self"), lId("length"))
          ))),
          Val(mutable = true, "i", None, lInt("0")),
          While(
            cond = SelfCall(Seq.empty, "<", self = lId("i"), args = Seq(
              Prop(lId("self"), lId("length"))
            )),
            _do = Seq(
              SelfCall(Seq.empty, "set", lId("ret"), args = Seq(
                lId("i"),
                Call(Seq.empty, lId("mapper"), args = Seq(
                  SelfCall(Seq.empty, "get", lId("self"), args = Seq(lId("i")))
                )),
                Store(to = Seq(lId("i")), what = SelfCall(Seq.empty, "+", lId("i"), args = Seq(lInt("1"))))
              ))
            )),
          lId("ret")
        ))),
      retTh = Some(thSeqU))

    //    def prettify(ast: String): String = {
    //      var mutated = ast
    //      var iteration = 1
    //      while(mutated.indexOf("(") != -1) {
    //        mutated = mutated.replaceFirst("\\(", "\n" + ("\t" * iteration))
    //        iteration += 1
    //      }
    //      mutated
    //    }

    val namespace = new Namespace(pkg = "", defs = Seq(), types = Seq(tInt), mods = Map())
    val ast = defMap.spec(Seq(thInt, thString), namespace)
    print(ast)
  }
  test("spec low type") {
    // type Seq10[T] = llvm [T x 10] .
    val tInt = ScalarDecl(ref = false, Seq.empty, "Int", "i32")
    val tSeq10 = ScalarDecl(ref = false, Seq(GenericType("T")), "Seq10", "[%T x 10]")
    val thSeq10Int = ScalarTh(Seq(ScalarTh(Seq.empty, "Int", None)), "Seq10", None)

    val namespace = new Namespace(pkg = "", defs = Seq.empty, types = Seq(tInt, tSeq10), mods = Map.empty)
    println(thSeq10Int.toLow(namespace))
    println(namespace.lowTypes)
  }

  test("spec low def") {
    // type Mem[T] = llvm %T* .
    // def get[T] = \self: Mem[T], idx: Int -> llvm
    //   %1 = getelementptr %T* self, i64 0, i64 %idx
    //   %2 = load %T, %T* %1
    //   ret %T %2
    // .T
    val tInt = ScalarDecl(ref = false, Seq.empty, "Int", "i32")
    val tMem = ScalarDecl(ref = false, Seq(GenericType("T")), "Mem", "%T*")
    val thInt = ScalarTh(Seq.empty, "Int", None)
    val thMemT = ScalarTh(Seq(ScalarTh(Seq.empty, "T", None)), "Mem", None)

    val defGet = Def(
      params = Seq(GenericType("T")),
      name = "get",
      lambda = Lambda(
        args = Seq(
          Arg("self", Some(thMemT)),
          Arg("idx", Some(thInt))
        ),
        body = llVm(
          """
            |  %1 = getelementptr %T* self, i64 0, i64 %idx
            |  %2 = load %T, %T* %1
            |  ret %T %2 """.stripMargin)
      ),
      retTh = Some(ScalarTh(Seq.empty, "T", None))
    )
    val namespace = new Namespace(pkg = "", defs = Seq.empty, types = Seq(tInt, tMem), mods = Map.empty)
    println(defGet.spec(Seq(thInt), namespace))
    println(namespace.lowTypes)
  }

  test("call generic") {
    val gT = GenericType("T")

    val thT = ScalarTh(Seq.empty, "T", pkg = None)
    val thInt = ScalarTh(Seq.empty, "Int", pkg = None)
    val thString = ScalarTh(Seq.empty, "thString", pkg = None)

    val tNil = ScalarDecl(ref = false, Seq(), "Nil", "void")
    val tInt = ScalarDecl(ref = false, Seq(), "Int", "i32")
    val tString = ScalarDecl(ref = true, Seq(), "String", "i8*")

    // def bar[T] = \x: T, y: Int, z: T ->
    //   z .T
    val defBar = Def(
      params = Seq(gT),
      name = "bar",
      lambda = Lambda(
        args = Seq(
          Arg("x", Some(thT)),
          Arg("y", Some(thInt)),
          Arg("z", Some(thT))),
        body = AbraCode(Seq(
          Ret(Some(lId("z")))
        ))),
      retTh = Some(thT))

    // val query = sql' select * from table where id = 'x' 'sql
    // val pyCode = py'
    //   x = 1
    //   y = 2
    //   z = some(x, y)
    //   print(z)
    // 'py
    // def main = bar('hello', 1, 'world')
    val defMain = Def(
      params = Seq.empty,
      name = "main",
      lambda = Lambda(
        args = Seq(),
        body = AbraCode(Seq(
          Call(Seq.empty, lId("bar"), Seq(lString("hello"), lInt("1"), lString("world")))
        ))),
      retTh = None)

    val namespace = new Namespace(pkg = "", defs = Seq(defBar, defMain), types = Seq(tNil, tInt, tString), mods = Map.empty)
    val (header, lowDef) = TypeChecker.evalDef(namespace, new FnScope(None), FnAdvice(Seq.empty, None), defMain)

    println(header)
    println(lowDef)
    println(namespace.lowDefs)
  }
}