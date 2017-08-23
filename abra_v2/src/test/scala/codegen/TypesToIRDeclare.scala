package codegen

import java.io.PrintWriter

import m3.codegen.Ast1
import org.scalatest.FunSuite
import m3.codegen.Ast1._
import m3.codegen.IrGen._

/**
  * Created by over on 14.05.17.
  */
class TypesToIRDeclare extends FunSuite {
  test("literals construction") {
    // val w = true
    // val x = 42
    // val y = 0.42
    // val z = 'abra'
    Call('Boolean, 'w, true)
    Call('Int, 'x, 42)
    Call('Float, 'y, 0.42f)
    Call('String, 'z, "abra")
  }

  test("call some function") {
    // foo(1, 2)
    Call('foo, 1, 2)
  }

  test("copy") {
    // val x = 1
    // val y = x
    Call('Int, 'x, 1) // x: Int
    Store('y, 'x) // y: Int
  }

  test("inner copy") {
    // val x = Bar(1, 2)
    // val y = x.x
    Call('Bar, 'x, 1, 2)
    Store('y, 'x ~> 'x)
  }

  test("union construction") {
    // type IntOrBar = Int | Bar
    // val x: IntOrBar = 2
    // val y: IntOrBar = Bar(1, 1)
    Call('IntOrBar, 'x, 1)
    Call('Bar, '$1, 1, 1)
    Call('IntOrBar, 'y, '$1)
  }

  test("struct copy") {
    // val x = Bar(1, 2)
    // val y = x

    Call('Bar, 'x, 1, 2)
    Store('y, 'x)
  }

  test("cond") {
    // val x = if true then 1 else 2
    Call('Boolean, '$0, true)
    Cond('$0,
      Seq(Call('Int, 'x, 1)),
      Seq(Call('Int, 'x, 2)))
  }

  test("or") {
    // true || bar()
    Or('$0,
      Seq(Call('Boolean, '$0, true)),
      Seq(Call('bar, '$0)))
  }

  test("and") {
    // false && bar()
    And('$0,
      Seq(Call('Boolean, '$0, false)),
      Seq(Call('bar, '$0)))
  }

  test("while") {
    // var i = 0
    // while i < 100 {
    //    i = i + 1;
    //    print(i)
    // }
    Call('Int, 'i, 0)
    While('$0,
      Seq(Call('<, '$0, 'i, 100)),
      Seq(
        Call('+, 'i, 1),
        Call('print, 'i)))
  }

  test("real seq") {
    """
      type Array[T] = (length: Long, Mem[T])
      type Seq[T] = (
          each: ((T) -> Boolean) -> None)
      
      def toSeq[T] = self: Array[T] ->
          Seq(self.length, fn ->
              var need = true
              var i = 0
      
              while need && i < self.length do
                  need = fn(self(i))
                  i = i + 1 end) end

      def map[T, U] = self: Seq[T], mapper: (T) -> U ->
          Seq[U](fn -> self.each(t -> fn(mapper(t)))) end

      def main = ->
        val a = array.of(1, 2, 3, 4, 5)
        a.toSeq.map(x -> (x + 1).toString).forEach(x -> println(x)) end
    """.stripMargin

    val T = 'T.virt
    val U = 'U.virt
    val tNone = Scalar("void")
    val tBoolean = Scalar("i8")
    val tLong = Scalar("i64")
    val tMem = Struct(T)('Mem)
    val tInt = Scalar("i32")
    val tString = Scalar("String")
    val tArray = Struct(T)('Array,
      'length is tLong,
      'mem is tMem(T))
    val tSeq = Struct(T)('Seq,
      'each is FnPtr(tNone, FnPtr(tBoolean, T)))
    val tPair = Struct(T, U)('Pair,
      'a is tArray(T),
      'b is tArray(U))

    Map(
      "println" -> Closure('println, tNone, Seq('s is tString), Seq(

      )),
      "of" -> Closure(T)('of, tArray(T), Seq('x0 is T, 'x1 is T, 'x2 is T, 'x3 is T, 'x4 is T), Seq(
        Cons(tMem(T), 'mem, 5),
        Call(T)('Mem ~> 'store, 'mem, 0, 'x0),
        Call(T)('Mem ~> 'store, 'mem, 1, 'x1),
        Call(T)('Mem ~> 'store, 'mem, 2, 'x2),
        Call(T)('Mem ~> 'store, 'mem, 3, 'x3),
        Call(T)('Mem ~> 'store, 'mem, 4, 'x4),
        Cons(tArray(T), 'mem, 5)
      )),
      "Array.toSeq" -> Closure(T)('Array ~> 'toSeq, tSeq(T), Seq('self is tArray(T)), Seq(
        Closure('$0, tNone, Seq('fn is FnPtr(tNone, FnPtr(tBoolean, T))), Seq(
          Cons(tBoolean, 'need, true),
          Cons(tInt, 'i, 0),
          While('$1,
            head = Seq(
              And('$1, Seq(Store('$1, 'need)), Seq(Call('<, '$1, 'i, 'self ~> 'length)))),
            body = Seq(
              Call(T)('Array ~> 'get, '$2, 'self, 'i),
              Call('fn, 'need, '$2),
              Call('+, 'i, 'i, 1)
            ))
        )),
        Call('Seq, '$ret, '$0)
      )),
      "Seq.map" -> Closure(T, U)('Seq ~> 'map, tSeq(U), Seq('self is tSeq(T), 'mapper is FnPtr(U, T)), Seq(
        Closure('$0, tNone, Seq('fn is FnPtr(tNone, FnPtr(tBoolean, U))), Seq(
          Closure('$1, tBoolean, Seq('t is T), Seq(
            Call('mapper, '$2, 't),
            Call('fn, '$ret, '$2)
          )),
          Call('self ~> 'each, '$1)
        )),
        Cons(tSeq(U), '$ret, '$0)
      )),
      "Seq.forEach" -> Closure(T)('Seq ~> 'forEach, tNone, Seq('self is tSeq(T), 'fn is FnPtr(tNone, T)), Seq(
      )),
      "main" -> Closure('main, tNone, Seq(), Seq(
        Call(tInt)('of, 'a, 1, 2, 3, 4, 5),
        Call(tInt)('Array ~> 'toSeq, '$0, 'a),
        Closure('$1, tString, Seq('x is tInt), Seq(
          Call('Int ~> '+, '$2, 'x, 1),
          Call('Int ~> 'toString, '$ret, '$2)
        )),
        Call(tInt, tString)('Seq ~> 'map, '$2, '$0, '$1),
        Closure('$3, tNone, Seq('x is tString), Seq(
          Call('println, 'x)
        )),
        Call(tString)('Seq ~> 'forEach, '$2, '$3)
      ))).evalDebug(System.out)
  }

  test("something complex") {
    // 1. форсим, чтобы генерик поля были в конце????
    // type S[K, V, U] = (x: Double, y: Int, a1: K, a2: V, a3: U)
    // val s = S(1.0, 2, "hello", 0, false) # S[String, Int, Boolean]
    // %struct.S$0 = { double, i32, i8*, i32, i8 }
    // def bar[K, V, U] = { s: S[K, U, V] ->
    //     s.x
    //     s.y
    //     s.a1
    //     s.a2
    //     s.a3
    // }: None
    //
    // type %vtable.$0 = { i32, i32, i32 } ; sizes for K, V, U
    // %struct.S$ = { double, i32, i8* } ; i8* for a1, a2, a3
    // void %bar(%vtable.$0* %vt, %struct.S$* %s) {
    //
    // }
    //
    //    type Nil = lltype { void }
    //    type Node[T] = (value: T, next: *List[T])
    //    type List[T] = Nil | Node[T]
    //
    //    def prepend[T] = { self: *List[T], value: T ->
    //      val middle = Node(self.value, self.next)
    //      self.value = value
    //      self.next = middle
    //    }: None
    //
    //    def forEach[T] = \self: List[T], fn: (T) -> None ->
    //    match self
    //    of Node(x, next) -> { fn(x); (*next).forEach(fn) }
    //    of Nil -> None
    //
    //    def main = {
    //      val list = Node(1, &Node(2, &Nil))
    //      (&list).prepend(0)
    //      list.forEach(\x -> io.print(x))
    //    }: None

    // %struct.Node$Int = type { i32, %union.List$Int* }
    // %union.List$Int = type { i8, %struct.Node$Int* }
  }


  // FIXME: можно ли брать указатель в этом случае
  // def bar(x: Bar) = {
  //   &x.x
  // }: *Int

}
