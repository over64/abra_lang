; ModuleID = 'virtual_sizes.ll'
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

%struct.Vtable0 = type { i32, i32, i32 }
%struct.SAny = type { double, i32 }
%struct.S0 = type { double, i32, i8*, i32, i8 }

@.str = private unnamed_addr constant [13 x i8] c"abra is cool\00", align 1
@cool = global i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i64 0, i64 0), align 8
@.str.1 = private unnamed_addr constant [23 x i8] c"verify error at any->x\00", align 1
@.str.2 = private unnamed_addr constant [23 x i8] c"verify error at any->y\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [24 x i8] c"verify error at any->a1\00", align 1
@.str.5 = private unnamed_addr constant [24 x i8] c"verify error at any->a2\00", align 1
@.str.6 = private unnamed_addr constant [24 x i8] c"verify error at any->a3\00", align 1

; Function Attrs: nounwind uwtable
define void @check(%struct.Vtable0* nocapture readonly %vt, %struct.SAny* nocapture readonly %any) #0 {
  %1 = getelementptr inbounds %struct.SAny, %struct.SAny* %any, i64 0, i32 0
  %2 = load double, double* %1, align 8
  %3 = fcmp une double %2, 1.000000e+00
  br i1 %3, label %4, label %6

; <label>:4                                       ; preds = %0
  %5 = tail call i32 (i8*, ...) @printf(i8* nonnull getelementptr inbounds ([23 x i8], [23 x i8]* @.str.1, i64 0, i64 0))
  br label %6

; <label>:6                                       ; preds = %4, %0
  %7 = getelementptr inbounds %struct.SAny, %struct.SAny* %any, i64 0, i32 1
  %8 = load i32, i32* %7, align 8
  %9 = icmp eq i32 %8, 2
  br i1 %9, label %12, label %10

; <label>:10                                      ; preds = %6
  %11 = tail call i32 (i8*, ...) @printf(i8* nonnull getelementptr inbounds ([23 x i8], [23 x i8]* @.str.2, i64 0, i64 0))
  br label %12

; <label>:12                                      ; preds = %6, %10
  %13 = bitcast %struct.SAny* %any to i8*
  %14 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i64 0, i32 0
  %15 = load i32, i32* %14, align 4
  %16 = sext i32 %15 to i64
  %17 = getelementptr inbounds i8, i8* %13, i64 %16
  %18 = bitcast i8* %17 to i8**
  %19 = load i8*, i8** %18, align 8
  %20 = load i8*, i8** @cool, align 8
  %21 = tail call i32 @strcmp(i8* %19, i8* %20) #3
  %22 = icmp eq i32 %21, 0
  br i1 %22, label %26, label %23

; <label>:23                                      ; preds = %12
  %24 = tail call i32 (i8*, ...) @printf(i8* nonnull getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i64 0, i64 0), i32 %21)
  %25 = tail call i32 (i8*, ...) @printf(i8* nonnull getelementptr inbounds ([24 x i8], [24 x i8]* @.str.4, i64 0, i64 0))
  br label %26

; <label>:26                                      ; preds = %12, %23
  %27 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i64 0, i32 1
  %28 = load i32, i32* %27, align 4
  %29 = sext i32 %28 to i64
  %30 = getelementptr inbounds i8, i8* %13, i64 %29
  %31 = load i8, i8* %30, align 1
  %32 = icmp eq i8 %31, 3
  br i1 %32, label %35, label %33

; <label>:33                                      ; preds = %26
  %34 = tail call i32 (i8*, ...) @printf(i8* nonnull getelementptr inbounds ([24 x i8], [24 x i8]* @.str.5, i64 0, i64 0))
  br label %35

; <label>:35                                      ; preds = %26, %33
  %36 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i64 0, i32 2
  %37 = load i32, i32* %36, align 4
  %38 = sext i32 %37 to i64
  %39 = getelementptr inbounds i8, i8* %13, i64 %38
  %40 = load i8, i8* %39, align 1
  %41 = icmp eq i8 %40, 107
  br i1 %41, label %44, label %42

; <label>:42                                      ; preds = %35
  %43 = tail call i32 (i8*, ...) @printf(i8* nonnull getelementptr inbounds ([24 x i8], [24 x i8]* @.str.6, i64 0, i64 0))
  br label %44

; <label>:44                                      ; preds = %35, %42
  ret void
}

; Function Attrs: nounwind
declare i32 @printf(i8* nocapture readonly, ...) #1

; Function Attrs: nounwind readonly
declare i32 @strcmp(i8* nocapture, i8* nocapture) #2

; Function Attrs: nounwind uwtable
define i32 @main() #0 {
  %s0 = alloca %struct.S0, align 8
  %vt = alloca %struct.Vtable0, align 4
  %1 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i64 0, i32 0
  store double 1.000000e+00, double* %1, align 8
  %2 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i64 0, i32 1
  store i32 2, i32* %2, align 8
  %3 = load i64, i64* bitcast (i8** @cool to i64*), align 8
  %4 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i64 0, i32 2
  %5 = bitcast i8** %4 to i64*
  store i64 %3, i64* %5, align 8
  %6 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i64 0, i32 3
  store i32 3, i32* %6, align 8
  %7 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i64 0, i32 4
  store i8 107, i8* %7, align 4
  %8 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i64 0, i32 0
  store i32 16, i32* %8, align 4
  %9 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i64 0, i32 1
  store i32 24, i32* %9, align 4
  %10 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i64 0, i32 2
  store i32 28, i32* %10, align 4
  %11 = bitcast %struct.S0* %s0 to %struct.SAny*
  call void @check(%struct.Vtable0* nonnull %vt, %struct.SAny* %11)
  ret i32 0
}

attributes #0 = { nounwind uwtable "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind readonly "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly }

!llvm.ident = !{!0}

!0 = !{!"clang version 3.8.1-12~bpo8+1 (tags/RELEASE_381/final)"}
