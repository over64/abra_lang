package codegen

import org.scalatest.FunSuite

class _20ModulesAdvancedTest extends FunSuite {
  test("a collections demo") {
    CodeGenUtil.runModules({
      case "prelude" => """
        def + = self: Int, other: Int native
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def - = self: Int, other: Int native
          %1 = sub nsw i32 %self, %other
          ret i32 %1 .Int

        def * = self: Int, other: Int native
          %1 = mul nsw i32 %self, %other
          ret i32 %1 .Int

        def > = self: Int, other: Int native
          %1 = icmp sgt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def < = self: Int, other: Int native
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool
        """
      case "unsafeArray" => """
        def alloc = len: index native
          %allocFn = load i8* (i64)*, i8* (i64)** @evaAlloc
          %ptr = getelementptr $t, $t* null, $index %len
          %size = ptrtoint $t* %ptr to i64
          %mem = call i8* %allocFn(i64 %size)
          %array = bitcast i8* %mem to $t*
          %rv1 = insertvalue $retTypeof() undef, $index %len, 0
          %rv2 = insertvalue $retTypeof() %rv1, $t* %array, 1
          ret $retTypeof() %rv2 .Array[t]

        def len = slf: array native
          %1 = extractvalue $array %slf, 0
          ret $index %1 .index

        def get = slf: array, idx: index native
          %ptr = extractvalue $array %slf, 1
          %elPtr = getelementptr $t, $t* %ptr, $index %idx
          %value = load $t, $t* %elPtr
          ret $t %value .t

        def set = slf: array, idx: index, value: t native
          %ptr = extractvalue $array %slf, 1
          %elPtr = getelementptr $t, $t* %ptr, $index %idx
          %old = load $t, $t* %elPtr
          ;meta rc_inc[t](value)
          ;meta rc_dec[t](old)
          store $t %value, $t* %elPtr
          ret void .None

        def setInit = slf: array, idx: index, value: t native
          %ptr = extractvalue $array %slf, 1
          %elPtr = getelementptr $t, $t* %ptr, $index %idx
          ;meta rc_inc[t](value)
          store $t %value, $t* %elPtr
          ret void .None
        """
      case "array" => """
        import prelude, unsafeArray

        def set = self: Array[t], idx: Int, value: t do
          unsafeArray.set(self, idx, value) .None

        def get = self: Array[t], idx: Int do
          unsafeArray.get(self, idx) .t

        def len = self: Array[t] do
          unsafeArray.len(self) .Int

        def mk = len: Int, init: (Int) -> t do
          array: Array[t] = unsafeArray.alloc(len)
          i = 0
          while i < len do
            unsafeArray.setInit(array, i, init(i))
            i = i + 1 .
          array .

        type ArrayIter[t] = (array: Array[t], idx: Int)

        def iter = self: Array[t] do
          ArrayIter(self, 0) .

        def next = self: ArrayIter[t] do
          if self.idx < self.array.len() do
            self.idx = self.idx + 1
            self.array.get(self.idx - 1)
          else none ..t | None
        """
      case "seq" => """
        type MapIter[iterator, t, u] = (iter: iterator, mapper: (t) -> u)

        def map = self: iterator, mapper: (t) -> u do
          if false do
            value: t | None = self.next() .
          MapIter(self, mapper) .

        def next = self: MapIter[iterator, t, u] do
          value: t | None = self.iter.next()
          value unless is forMap: t do
            self.mapper(forMap) ..u | None


        type FilterIter[iterator, t] = (iter: iterator, predicate: (t) -> Bool)

        def filter = self: iterator, predicate: (t) -> Bool do
          if false do
            value: t | None = self.next() .
          FilterIter(self, predicate) .

        def next = self: FilterIter[iterator, t] do
          while true do
            value: t | None = self.iter.next()
            value unless
              is forFilter: t do
                if self.predicate(forFilter) do
                  return value .
              is None do return none ...t | None

        def forEach = self: iterator, callback: (t) -> None do
          while true do
            value: t | None = self.next()
            value unless
              is exist: t do callback(exist)
              is None do return ...None
        """
      case "io" => """
        native
          @.printfInt = private constant [3 x i8] c"%d\00", align 1
          declare i32 @printf(i8*,...) .

        def printInt = i: Int native
          %format = bitcast [3 x i8]* @.printfInt to i8*
          call i32 (i8*,...) @printf(i8* %format, i32 %i)
          ret void .None
        """
      case "main" => """
        import prelude, array, seq, io

        def main =
          nums = array.mk(10, |i| i)
          nums.iter()
            .map(|x| x * 2)
            .filter(|x| x > 10)
            .forEach(|x| io.printInt(x))

          42 .
        """
    }, 42, stdout = Some("12141618\n"))
  }
}