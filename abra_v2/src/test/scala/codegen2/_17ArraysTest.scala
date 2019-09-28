package codegen2

import org.scalatest.FunSuite

class _17ArraysTest extends FunSuite {
  val unsafeCode =
    """
      def + = self: Int, other: Int do llvm
        %1 = add nsw i32 %self, %other
        ret i32 %1 .Int

      def < = self: Int, other: Int do llvm
        %1 = icmp slt i32 %self, %other
        %2 = zext i1 %1 to i8
        ret i8 %2 .Bool

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
  test("array: unsafe") {
    CodeGenUtil.run(
      unsafeCode +
        """
        def main =
          array: Array[String] = alloc(100)

          i = 0
          while i < 100 do
            setInit(array, i, 'hello')
            i = i + 1 .

          j = 0
          while j < 100 do
            set(array, j, 'world')
            j = j + 1 .

          len(array) .Int
      """, 100)
  }

  val safeCode = unsafeCode +
    """
      def set = self: Array[t], idx: Int, value: t do
        set(self, idx, value) .None

      def get = self: Array[t], idx: Int do
        get(self, idx) .t

      def len = self: Array[t] do
        len(self) .Int

      def mk = len: Int, init: (Int) -> t do
        array: Array[t] = alloc(len)
        i = 0
        while i < len do
          setInit(array, i, init(i))
          i = i + 1 .
        array .
      """

  test("array: safe") {
    CodeGenUtil.run(
      safeCode +
        """
      def main =
        array = mk(100, lambda i -> 'hello')

        j = 0
        while j < 100 do
          array(j) = 'world'
          j = j + 1 .

        array.len() .
      """, 100)
  }

  test("array: static size & value type") {
    CodeGenUtil.run(
      safeCode +
        """
        def mkBuffer = llvm
           ret $retTypeof() undef .Array10[Int]

        def main =
          buff = mkBuffer()
          sum = 0

          i = 0
          while i < 10 do
            buff(i) = i
            i = i + 1 .

          j = 0
          while j < 10 do
            sum = sum + buff(j)
            j = j + 1 .

          sum .
      """, 45)

    // TODO:
    //  - fixed size arrays
    //   - impl for ref types
    //   - make implicit conv in typecheck
  }
}
