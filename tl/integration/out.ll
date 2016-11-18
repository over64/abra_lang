; ModuleID = 'closures.out.ll'

%tclosure1 = type { void (%tclosure1*)*, i32* }

declare i32 @memcmp(i8*, i8*, i64)

; Function Attrs: argmemonly nounwind
declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1) #0

declare i1 @"abra.int.$eqeq_for_Int"(i32, i32)

declare i1 @"abra.int.$more_for_Int"(i32, i32)

declare i32 @abra.int.or_for_Int(i32, i32)

declare i32 @"abra.int.$plus_for_Int"(i32, i32)

declare i1 @"abra.int.$less_for_Int"(i32, i32)

declare i32 @"abra.int.$minus_for_Int"(i32, i32)

declare i32 @"abra.int.$mul_for_Int"(i32, i32)

define void @anonFn1(%tclosure1* %closure) {
  %1 = getelementptr %tclosure1, %tclosure1* %closure, i32 0, i32 1
  %2 = load i32*, i32** %1
  store i32 1, i32* %2
  ret void
}

define void @tl.integration.closures.foo({ void (i8*)* }* %fn) {
  %1 = getelementptr { void (i8*)* }, { void (i8*)* }* %fn, i32 0, i32 0
  %2 = load void (i8*)*, void (i8*)** %1
  %3 = bitcast { void (i8*)* }* %fn to i8*
  call void %2(i8* %3)
  ret void
}

define i32 @main() {
  %x_1 = alloca i32
  %anon1_2 = alloca %tclosure1
  store i32 0, i32* %x_1
  %1 = getelementptr %tclosure1, %tclosure1* %anon1_2, i32 0, i32 0
  store void (%tclosure1*)* @anonFn1, void (%tclosure1*)** %1
  %2 = getelementptr %tclosure1, %tclosure1* %anon1_2, i32 0, i32 1
  store i32* %x_1, i32** %2
  %3 = bitcast %tclosure1* %anon1_2 to { void (i8*)* }*
  call void @tl.integration.closures.foo({ void (i8*)* }* %3)
  %4 = load i32, i32* %x_1
  ret i32 %4
}

attributes #0 = { argmemonly nounwind }
