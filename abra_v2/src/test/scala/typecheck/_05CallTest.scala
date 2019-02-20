package typecheck

import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _05CallTest extends FunSuite {
  test("simple call") {
    val ast = astForCode(
      """
         def foo = x: Int, y: Int do 42 .
         def main =
           foo(1, 2) .
      """)

    assertTh("(Int, Int) -> Int", ast.function("foo"))
    assertTh("() -> Int", ast.function("main"))
  }

  test("simple self call") {
    val ast = astForCode(
      """
         type Int = llvm i32 .
         type Float = llvm float .

         def + = self: Int, other: Int do llvm
           %1 = contains nsw i32 %self, %other
           ret i32 %1 .Int

         def main =
           1 + 1 .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("simple store") {
    val ast = astForCode(
      """
         def main =
           x = 1 .
      """)

    assertTh("Int", ast.function("main").varDecl("x"))
    assertTh("() -> None", ast.function("main"))
  }

  test("simple lambda") {
    val ast = astForCode(
      """
         def apply = x: Int, lmb: (Int) -> Int do
           lmb(x) .

         def main =
           apply(42, lambda x -> x) .
      """)

    assertTh("() -> Int", ast.function("main"))
  }

  test("simple generic") {
    val ast = astForCode(
      """
         type Int = llvm i32 .

         def + = self: Int, other: Int do llvm
             %1 = contains nsw i32 %self, %other
             ret i32 %1 .Int

         def contains = x: num, y: num do
             x + y .

         def main =
             contains(1, 1) .
      """)

    assertTh("() -> Int", ast.function("main"))
    println(ast.defs("contains").getEquations)
    astPrint(ast)
  }
  // def foo = s: Seq[Int] do
  //     # code .
  // def bad = s: Seq[a] do
  //     foo(s) # err: expected Seq[Int] has Seq[a]
  //            # Int => a wft?

  test("deep generic") {
    val ast = astForCode(
      """
         type Int = llvm i32 .
         type Long = llvm i64 .

         def + = self: Int, other: Int do llvm
             %1 = contains nsw i32 %self, %other
             ret i32 %1 .Int

         def - = self: Int, other: Int do llvm
             %1 = sub nsw i32 %self, %other
             ret i32 %1 .Int

         def minus = self: Int, other: t do
            self - other .

         # self call :: num.add(num) -> a1
         def add = x: num, y: num do
             x + y .

         # self call :: t1.minus(t2) -> t2
         # call      :: add(t2, t2)
         def subAdd = x: t1, y: t2 do
             z: t2 = x - y
             add(z, y) .

         def bar =
             subAdd(1, 1) .
      """)

    println(
      """
        |z = loadConf()
        |    ↑
        |
        |main.abra:20:5 tce002: No such function 'loadConf'
        |
        |
        |           ↓ any2
        |           ~~~~~
        |add(x - y, y + 1)
        |    ~~~~~
        |    ↑ any1
        |
        |main.abra:31:5 tce001: Type mismatch. Expected any1 has any2
      """.stripMargin)
    //println("\033[0;40m\033[0;37mhello\033[0m")
    println("\nadd:\n" + ast.defs("add").getEquations + "\n" + ast.defs("add").getTypeHint)
    println("\nsubAdd:\n" + ast.defs("subAdd").getEquations + "\n" + ast.defs("subAdd").getTypeHint)
    println("\nbar:\n" + ast.defs("bar").getEquations + "\n" + ast.defs("bar").getTypeHint)

    astPrint("\n" + ast)
  }

  test("polymorphic self replace") {
    val ast = astForCode(
      """
        type None = llvm void .
        type Bool = llvm i8 .
        type Int = llvm i32 .

        type Seq[t] = llvm %t* .
        type Log    = llvm i32 .
        type F2     = llvm i32 .
        type F3     = llvm i32 .

        def mkSeqInt = llvm
          ; nop .Seq[Int]

        def mkLog = llvm
           ; nop .Log

        def mkF2 = llvm
           ; nop .F2

        def mkF3 = llvm
           ; nop .F3

        # t :: some() -> logger
        # logger :: debug(String, fake) -> a2
        def contains = self: Seq[t], value: t, xx: fake, log: logger do
          value.some()
          log.debug('query from Seq', xx)
          true .

        # logger3 :: doDump(String) -> a1
        def dump = log: logger3 do
          log.doDump('hello') .

        # collection :: contains(b, logger1) -> Bool
        def in = self: b, coll: collection, log: logger1, f: fake1 do
          dump(log)
          coll.contains(self, f, log) .Bool

        def debug = self: Log, str: String, f3: F3 do
          none .

        def doDump = self: Log, str: String do
          none .

        def some = self: Int do
          none .

        # logger2 :: debug(String, F2) -> a1
        def bar = log: logger2 do
          seq = mkSeqInt()
          f = mkF2()
          10.in(seq, log, f) .

        def main =
          bar(mkLog()) .
      """)

    println("\ncontains:\n" + ast.selfDefs("contains")(0).getEquations + "\n" + ast.selfDefs("contains")(0).getTypeHint)
    println("\nin:\n" + ast.selfDefs("in")(0).getEquations + "\n" + ast.selfDefs("in")(0).getTypeHint)
    println("\nbar:\n" + ast.defs("bar").getEquations + "\n" + ast.defs("bar").getTypeHint)
    println("\nmain:\n" + ast.defs("main").getEquations + "\n" + ast.defs("main").getTypeHint)
  }
}
