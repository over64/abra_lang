; ModuleID = 'main2.c'
source_filename = "main2.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"


declare i32 @__gxx_personality_v0(...)

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @foo(i32) personality i32 (...)* @__gxx_personality_v0 {
  %2 = alloca i32, align 4
  %3 = alloca i32, align 4
  store i32 %0, i32* %3, align 4
  %4 = load i32, i32* %3, align 4
  %5 = icmp ne i32 %4, 0
  br i1 %5, label %6, label %7

; <label>:6:                                      ; preds = %1
  ;store i32 42, i32* %2, align 4
  ;br label %8
  ret i32 42

; <label>:7:                                      ; preds = %1
  ;store i32 0, i32* %2, align 4
  ;br label %8
  resume i32 0

; <label>:8:                                      ; preds = %7, %6
  ;%9 = load i32, i32* %2, align 4
  ;ret i32 %9
}

; Function Attrs: noinline nounwind optnone uwtable
define dso_local i32 @bar(i32) personality i32 (...)* @__gxx_personality_v0 {
  %2 = alloca i32, align 4
  store i32 %0, i32* %2, align 4
  %3 = load i32, i32* %2, align 4
  %4 = invoke i32 @foo(i32 %3) to label %ok unwind label %fail
ok:
  ret i32 %4
fail:
  %err = landingpad i32 cleanup
  resume i32 %err
}

attributes #0 = { noinline nounwind optnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }

!llvm.module.flags = !{!0}
!llvm.ident = !{!1}

!0 = !{i32 1, !"wchar_size", i32 4}
!1 = !{!"clang version 7.0.1-4 (tags/RELEASE_701/final)"}
