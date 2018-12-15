; ModuleID = 'main.c'
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

%struct.eh_table = type { i16, i16 }

; @tbl = constant %struct.eh_table { i8* blockaddress(@bar, %1), i8* null }, align 8

; Function Attrs: nounwind uwtable
define void @bar() prefix %struct.eh_table {
    i16 trunc(
	i64 sub(
	    i64 ptrtoint(i8* blockaddress(@bar, %1) to i64),
	    i64 ptrtoint(i8* bitcast( void ()* @bar to i8*) to i64)) to i16),
    i16 0
} {
  br label %1

; <label>:1                                       ; preds = %0
  ret void

}

; Function Attrs: nounwind uwtable
define i32 @main() #0 {
  %1 = alloca i32, align 4
  store i32 0, i32* %1, align 4
  ret i32 0
}

attributes #0 = { nounwind uwtable "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.ident = !{!0}

!0 = !{!"clang version 3.8.0-2 (tags/RELEASE_380/final)"}
