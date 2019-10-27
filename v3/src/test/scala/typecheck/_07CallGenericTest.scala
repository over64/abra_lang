package typecheck

import m3.Ast0.GenericTh
import m3._02typecheck.TCE
import m3._02typecheck.TCMeta._
import org.scalatest.FunSuite
import typecheck.TypeCheckUtil._

class _07CallGenericTest extends FunSuite {
  test("simple generic") {
    val ast = astForCode(
      """
         def + = self: Int, other: Int native
           ; native code
           .Int

         def contains = x: num, y: num do
             x + y .

         def main =
             contains(1, 1) .
      """)

    assertTh("() -> Int", ast.function("main"))
    println(ast.defs("contains").getEquations)
  }

  test("generic call: store to generic") {
    val ast = astForCode(
      """
         def some = x: a do
           y: b = x.function() .
      """)

    val some = ast.function("some")
    assertTh("(a) -> None", some)
    assert(some.getEquations.typeParams.contains(GenericTh("b")), "new generic param must be infered")
    println(some.getEquations)
  }

  test("deep generic") {
    val ast = astForCode(
      """
         def + = self: Int, other: Int native
             %1 = contains nsw i32 %self, %other
             ret i32 %1 .Int

         def - = self: Int, other: Int native
             %1 = sub nsw i32 %self, %other
             ret i32 %1 .Int

         -- self call :: num.add(num) -> a1
         def add = x: num, y: num do
             x + y .

         -- self call :: t1.minus(t2) -> t2
         -- call      :: add(t2, t2)
         def subAdd = x: t1, y: t2 do
             z: t2 = x - y
             add(z, y) .

         def bar =
             subAdd(1, 1) .
      """)

    //    println(
    //      """
    //        |z = loadConf()
    //        |    ↑
    //        |
    //        |main.eva:20:5 tce002: No such function 'loadConf'
    //        |
    //        |
    //        |           ↓ any2
    //        |           ~~~~~
    //        |add(x - y, y + 1)
    //        |    ~~~~~
    //        |    ↑ any1
    //        |
    //        |main.eva:31:5 tce001: Type mismatch. Expected any1 has any2
    //      """.stripMargin)
  }

  test("deep generic 2") {
    val ast = astForCode(
      """
        def + = self: Int, other: Int native
          ;native .Int

        def - = self: Int, other: Int native
          ;native .Int

        def sub = x: num, y: num do
          x - y .

        def addSub = x: num, y: num do
          x + y
          sub(x, y) .

        def main =
          addSub(43, 1) .
        """)

    val main = ast.function("main")
    assertTh("() -> Int", main)
  }

  test("deep generic over self 2") {
    val ast = astForCode(
      """
         def + = self: Int, other: Int native
           %1 = add nsw i32 %self, %other
           ret i32 %1 .Int

         def sub = self: Int, other: Int native
           %1 = sub nsw i32 %self, %other
           ret i32 %1 .Int

         def addSub = self: num, y: num do
           self + y
           self.sub(y) .

         -- incorrect replacement ???
         -- expected Int::sub ???
         -- def sub = self: num1, y: num1 do
         --   self - y .

         def main =
           43.addSub(1) .
      """)

    val main = ast.function("main")
    assertTh("() -> Int", main)
  }

  test("polymorphic self replace") {
    assertThrows[TCE.TypeMismatch] {
      astForCode(
        """
        type Seq[t] = native %t* .
        type Log    = native i32 .
        type F2     = native i32 .
        type F3     = native i32 .

        def debug = self: Log, str: String, f3: F3 do
          none .

        def doDump = self: Log, str: String do
          none .

        def some = self: Int do
          none .

        def mkSeqInt = native
          ; nop .Seq[Int]

        def mkLog = native
           ; nop .Log

        def mkF2 = native
           ; nop .F2

        def mkF3 = native
           ; nop .F3

        -- t :: some() -> a1
        -- logger :: debug(String, fake) -> a2
        def contains = self: Seq[t], value: t, xx: fake, log: logger do
          value.some()
          log.debug('query from Seq', xx)
          true .

        -- logger3 :: doDump(String) -> a1
        def dump = log: logger3 do
          log.doDump('hello') .

        -- logger1 :: doDump(String) -> a1
        -- collection :: contains(b, fake1, logger1) -> Bool
        def in = self: b, coll: collection, log: logger1, f: fake1 do
          dump(log)
          coll.contains(self, f, log) .Bool

        -- logger2 :: debug(String, F2) -> a1
        -- logger2 :: doDump(String) -> a2
        def bar = log: logger2 do
          seq = mkSeqInt()
          f = mkF2()
          10.in(seq, log, f) .

        def main =
          bar(mkLog()) .
      """)
    }
  }

  test("collections-like-2") {
    val ast = astForCode(
      """
        def + = self: Long, other: Long native
          ;native .Long

        def - = self: Long, other: Long native
          ;native .Long

        def * = self: Int, other: Int native
          ;native .Int

        def == = self: Int, other: Int native
          ;native .Bool

        def < = self: Long, other: Long native
          ;native .Bool

        type ArrayIter[t] = (array: Array[t], idx: Long)

        def iter = self: Array[t] do
          ArrayIter(self, 0) .

        def next = self: ArrayIter[t1] native
          ; native .t1 | None
        --  if self.idx < self.array.len() do
        --    self.idx = self.idx + 1
        --    self.array(self.idx - 1)
        --  else none ..

        type MapIter[iterator2, t2, u2] = (iter: iterator2, mapper: (t2) -> u2)

        -- iterator3 :: next() -> t3 | None
        def map = self: iterator3, mapper: (t3) -> u3 do
          if false do
            value: t3 | None = self.next() .
          MapIter(self, mapper) .

        def next = self: MapIter[iterator4, t4, u4] do
          value: t4 | None = self.iter.next()
          value unless is forMap: t4 do
            self.mapper(forMap) ..u4 | None


        type FilterIter[iterator5, t5] = (iter: iterator5, predicate: (t5) -> Bool)

        -- iterator6 :: next() -> t6 | None
        def filter = self: iterator6, predicate: (t6) -> Bool do
          if false do
            value: t6 | None = self.next() .
          FilterIter(self, predicate) .

        def next = self: FilterIter[iterator7, t7] do
          while true do
            value: t7 | None = self.iter.next()
            value unless
              is forFilter: t7 do
                if self.predicate(forFilter) do
                  return value .
              is None do return none ...t7 | None

        def mkArray = native
          ; .Array[Int]

        def main =
          array1 = mkArray()
          it = array1.iter()
            .map(|x| x * 2)
            .filter(|x| x == 0)

          it.next()
          it.next()
          it.next()

          it.next() unless is None do -1 ..
      """)

    println(ast.selfDefs("map").head.getEquations)
    val main = ast.function("main")
    assertTh("() -> Int", main)
  }

  test("foobar") {
    val ast = astForCode(
      """
        type Seq[t] = native i8* .
        def get = self: Seq[t], idx: Int native
          ; xxx .t

        type T1 = native i8* .

        def mkT1 = native
          ;xxx .T1

        -- e2 :: bar() -> Int
        def some = self: T1, x2: Seq[e2], x3: Int do
          gg: Int = x2(0).bar() .None

        -- t1 :: some(t2, t3) -> a1
        def f1 = x1: t1, x2: t2, x3: t3 do
          x1.some(x2, x3) .a1

        -- c2 :: bar() -> Int
        def f2 = x2: Seq[c2], x3: Int do
          f1(mkT1(), x2, x3) .
      """)

    val f2 = ast.function("f2")
    assertTh("(Seq[c2], Int) -> None", f2)
  }
}
