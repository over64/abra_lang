package codegen2

import org.scalatest.FunSuite

class _20ModulesAdvancedTest extends FunSuite {
  test("a collections demo") {
    CodeGenUtil.runModules({
      case "prelude" => """
        def + = self: Int, other: Int do llvm
          %1 = add nsw i32 %self, %other
          ret i32 %1 .Int

        def - = self: Int, other: Int do llvm
          %1 = sub nsw i32 %self, %other
          ret i32 %1 .Int

        def * = self: Int, other: Int do llvm
          %1 = mul nsw i32 %self, %other
          ret i32 %1 .Int

        def > = self: Int, other: Int do llvm
          %1 = icmp sgt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool

        def < = self: Int, other: Int do llvm
          %1 = icmp slt i32 %self, %other
          %2 = zext i1 %1 to i8
          ret i8 %2 .Bool
        """
      case "unsafeArray" => """
        def alloc = len: index do llvm
          %allocFn = load i8* (i64)*, i8* (i64)** @evaAlloc
          %ptr = getelementptr $t, $t* null, $index %len
          %size = ptrtoint $t* %ptr to i64
          %mem = call i8* %allocFn(i64 %size)
          %array = bitcast i8* %mem to $t*
          %rv1 = insertvalue $retTypeof() undef, $index %len, 0
          %rv2 = insertvalue $retTypeof() %rv1, $t* %array, 1
          ret $retTypeof() %rv2 .Array[t]

        def len = slf: array do llvm
          %1 = extractvalue $array %slf, 0
          ret $index %1 .index

        def get = slf: array, idx: index do llvm
          %ptr = extractvalue $array %slf, 1
          %elPtr = getelementptr $t, $t* %ptr, $index %idx
          %value = load $t, $t* %elPtr
          ret $t %value .t

        def set = slf: array, idx: index, value: t do llvm
          %ptr = extractvalue $array %slf, 1
          %elPtr = getelementptr $t, $t* %ptr, $index %idx
          %old = load $t, $t* %elPtr
          ;meta rc_inc[t](value)
          ;meta rc_dec[t](old)
          store $t %value, $t* %elPtr
          ret void .None

        def setInit = slf: array, idx: index, value: t do llvm
          %ptr = extractvalue $array %slf, 1
          %elPtr = getelementptr $t, $t* %ptr, $index %idx
          ;meta rc_inc[t](value)
          store $t %value, $t* %elPtr
          ret void .None
        """
      case "array" => """
        import
          prelude
          unsafeArray .

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
        """
      case "io" => """

        """
      case "main" => """
        import
          prelude
          array
          seq
          io .

        def main =
          array1 = array.mk(10, lambda i -> i)
          it = array1.iter()
            .map(lambda x -> x * 2)
            .filter(lambda x -> x > 10)

          it.next()
          it.next()
          it.next()

          it.next() unless is None do -1 ..
        """
    }, 42)
  }
}