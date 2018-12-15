; ModuleID = 'main5.ll'
source_filename = "main5.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@err = external thread_local local_unnamed_addr global i32, align 4
@.str = private unnamed_addr constant [13 x i8] c"value is %d\0A\00", align 1
@str.2 = private unnamed_addr constant [17 x i8] c"an error occured\00"
@anchor_cst = private unnamed_addr constant [17 x i8] c"an error occured\00"

; Function Attrs: noinline nounwind uwtable
define i32 @getValueNI() local_unnamed_addr #0 {
  %1 = tail call i32 @rand() #4
  %2 = and i32 %1, 1
  %3 = icmp eq i32 %2, 0
  br i1 %3, label %6, label %4, !prof !1

; <label>:4:                                      ; preds = %0
  %5 = tail call i32 @fakeExit() #4
  br label %6

; <label>:6:                                      ; preds = %4, %0
  %7 = phi i32 [ 1, %4 ], [ 0, %0 ]
  %8 = phi i32 [ %5, %4 ], [ 42, %0 ]
  ret i32 %8
}

; Function Attrs: noinline nounwind uwtable
declare i32 @getValueNI1() local_unnamed_addr #0

; Function Attrs: nounwind
declare i32 @rand() local_unnamed_addr #1

declare i32 @fakeExit() local_unnamed_addr #2

declare i32 @fakeGetErr() local_unnamed_addr #2

; Function Attrs: nounwind uwtable
define void @bar() local_unnamed_addr #3 {
  %1 = tail call i32 @getValueNI1()
  %ptr = inttoptr i32 1 to i32*
  %2 = load i32, i32* %ptr
  %3 = icmp eq i32 %2, 0
  br i1 %3, label %6, label %4, !prof !2

; <label>:4:                                      ; preds = %0
  %5 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i64 0, i64 0), i32 %1)
  br label %8

; <label>:6:                                      ; preds = %0
  %7 = tail call i32 @puts(i8* getelementptr inbounds ([17 x i8], [17 x i8]* @str.2, i64 0, i64 0))
  br label %8

; <label>:8:                                      ; preds = %6, %4
  ret void
}

; Function Attrs: nounwind
declare i32 @printf(i8* nocapture readonly, ...) local_unnamed_addr #1

; Function Attrs: nounwind uwtable
define i32 @getValue() local_unnamed_addr #3 {
  %1 = tail call i32 @rand() #4
  %2 = and i32 %1, 1
  %3 = icmp eq i32 %2, 0
  br i1 %3, label %6, label %4, !prof !1

; <label>:4:                                      ; preds = %0
  %5 = tail call i32 @fakeExit() #4
  br label %6

; <label>:6:                                      ; preds = %4, %0
  %7 = phi i32 [ 1, %4 ], [ 0, %0 ]
  %8 = phi i32 [ %5, %4 ], [ 42, %0 ]
  store i32 %7, i32* @err, align 4, !tbaa !3
  ret i32 %8
}

; Function Attrs: nounwind uwtable
define i32 @main() local_unnamed_addr #3 {
  %1 = tail call i64 @time(i64* null) #4
  %2 = trunc i64 %1 to i32
  tail call void @srand(i32 %2) #4
  %3 = tail call i32 @rand() #4
  %4 = and i32 %3, 1
  %5 = icmp eq i32 %4, 0
  br i1 %5, label %9, label %6, !prof !1

; <label>:6:                                      ; preds = %0
  %7 = tail call i32 @fakeExit() #4
  store i32 1, i32* @err, align 4, !tbaa !3
  %8 = tail call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i64 0, i64 0), i32 %7)
  br label %11

; <label>:9:                                      ; preds = %0
  store i32 0, i32* @err, align 4, !tbaa !3
  %10 = tail call i32 @puts(i8* getelementptr inbounds ([17 x i8], [17 x i8]* @str.2, i64 0, i64 0))
  br label %11

; <label>:11:                                     ; preds = %9, %6
  ret i32 0
}

; Function Attrs: nounwind
declare void @srand(i32) local_unnamed_addr #1

; Function Attrs: nounwind
declare i64 @time(i64*) local_unnamed_addr #1

; Function Attrs: nounwind
declare i32 @puts(i8* nocapture readonly) #4

attributes #0 = { noinline nounwind uwtable "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind uwtable "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind }

!llvm.ident = !{!0}

!0 = !{!"clang version 3.9.1-9 (tags/RELEASE_391/rc2)"}
!1 = !{!"branch_weights", i32 2000, i32 1}
!2 = !{!"branch_weights", i32 1, i32 2000}
!3 = !{!4, !4, i64 0}
!4 = !{!"int", !5, i64 0}
!5 = !{!"omnipotent char", !6, i64 0}
!6 = !{!"Simple C/C++ TBAA"}
