; ModuleID = 'virtual_sizes.c'
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-unknown-linux-gnu"

%struct.Vtable0 = type { i32, i32, i32 }
%struct.SAny = type { double, i32 }
%struct.S0 = type { double, i32, i8*, i32, i8 }

@.str = private unnamed_addr constant [13 x i8] c"abra is cool\00", align 1
@cool = global i8* getelementptr inbounds ([13 x i8], [13 x i8]* @.str, i32 0, i32 0), align 8
@.str.1 = private unnamed_addr constant [23 x i8] c"verify error at any->x\00", align 1
@.str.2 = private unnamed_addr constant [23 x i8] c"verify error at any->y\00", align 1
@.str.3 = private unnamed_addr constant [4 x i8] c"%d\0A\00", align 1
@.str.4 = private unnamed_addr constant [24 x i8] c"verify error at any->a1\00", align 1
@.str.5 = private unnamed_addr constant [24 x i8] c"verify error at any->a2\00", align 1
@.str.6 = private unnamed_addr constant [24 x i8] c"verify error at any->a3\00", align 1

; Function Attrs: nounwind uwtable
define void @check(%struct.Vtable0* %vt, %struct.SAny* %any) #0 {
  %1 = alloca %struct.Vtable0*, align 8
  %2 = alloca %struct.SAny*, align 8
  %raw = alloca i8*, align 8
  %a1Ptr = alloca i8**, align 8
  %a1 = alloca i8*, align 8
  %a2 = alloca i32, align 4
  %a3 = alloca i32, align 4
  store %struct.Vtable0* %vt, %struct.Vtable0** %1, align 8
  store %struct.SAny* %any, %struct.SAny** %2, align 8
  %3 = load %struct.SAny*, %struct.SAny** %2, align 8
  %4 = getelementptr inbounds %struct.SAny, %struct.SAny* %3, i32 0, i32 0
  %5 = load double, double* %4, align 8
  %6 = fcmp une double %5, 1.000000e+00
  br i1 %6, label %7, label %9

; <label>:7                                       ; preds = %0
  %8 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([23 x i8], [23 x i8]* @.str.1, i32 0, i32 0))
  br label %9

; <label>:9                                       ; preds = %7, %0
  %10 = load %struct.SAny*, %struct.SAny** %2, align 8
  %11 = getelementptr inbounds %struct.SAny, %struct.SAny* %10, i32 0, i32 1
  %12 = load i32, i32* %11, align 8
  %13 = icmp ne i32 %12, 2
  br i1 %13, label %14, label %16

; <label>:14                                      ; preds = %9
  %15 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([23 x i8], [23 x i8]* @.str.2, i32 0, i32 0))
  br label %16

; <label>:16                                      ; preds = %14, %9
  %17 = load %struct.SAny*, %struct.SAny** %2, align 8
  %18 = bitcast %struct.SAny* %17 to i8*
  store i8* %18, i8** %raw, align 8
  %19 = load i8*, i8** %raw, align 8
  %20 = load %struct.Vtable0*, %struct.Vtable0** %1, align 8
  %21 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %20, i32 0, i32 0
  %22 = load i32, i32* %21, align 4
  %23 = sext i32 %22 to i64
  %24 = getelementptr inbounds i8, i8* %19, i64 %23
  %25 = bitcast i8* %24 to i8**
  store i8** %25, i8*** %a1Ptr, align 8
  %26 = load i8**, i8*** %a1Ptr, align 8
  %27 = load i8*, i8** %26, align 8
  store i8* %27, i8** %a1, align 8
  %28 = load i8*, i8** %a1, align 8
  %29 = load i8*, i8** @cool, align 8
  %30 = call i32 @strcmp(i8* %28, i8* %29) #3
  %31 = icmp ne i32 %30, 0
  br i1 %31, label %32, label %38

; <label>:32                                      ; preds = %16
  %33 = load i8*, i8** %a1, align 8
  %34 = load i8*, i8** @cool, align 8
  %35 = call i32 @strcmp(i8* %33, i8* %34) #3
  %36 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.str.3, i32 0, i32 0), i32 %35)
  %37 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([24 x i8], [24 x i8]* @.str.4, i32 0, i32 0))
  br label %38

; <label>:38                                      ; preds = %32, %16
  %39 = load i8*, i8** %raw, align 8
  %40 = load %struct.Vtable0*, %struct.Vtable0** %1, align 8
  %41 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %40, i32 0, i32 1
  %42 = load i32, i32* %41, align 4
  %43 = sext i32 %42 to i64
  %44 = getelementptr inbounds i8, i8* %39, i64 %43
  %45 = load i8, i8* %44, align 1
  %46 = sext i8 %45 to i32
  store i32 %46, i32* %a2, align 4
  %47 = load i32, i32* %a2, align 4
  %48 = icmp ne i32 %47, 3
  br i1 %48, label %49, label %51

; <label>:49                                      ; preds = %38
  %50 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([24 x i8], [24 x i8]* @.str.5, i32 0, i32 0))
  br label %51

; <label>:51                                      ; preds = %49, %38
  %52 = load i8*, i8** %raw, align 8
  %53 = load %struct.Vtable0*, %struct.Vtable0** %1, align 8
  %54 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %53, i32 0, i32 2
  %55 = load i32, i32* %54, align 4
  %56 = sext i32 %55 to i64
  %57 = getelementptr inbounds i8, i8* %52, i64 %56
  %58 = load i8, i8* %57, align 1
  %59 = sext i8 %58 to i32
  store i32 %59, i32* %a3, align 4
  %60 = load i32, i32* %a3, align 4
  %61 = icmp ne i32 %60, 107
  br i1 %61, label %62, label %64

; <label>:62                                      ; preds = %51
  %63 = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([24 x i8], [24 x i8]* @.str.6, i32 0, i32 0))
  br label %64

; <label>:64                                      ; preds = %62, %51
  ret void
}

declare i32 @printf(i8*, ...) #1

; Function Attrs: nounwind readonly
declare i32 @strcmp(i8*, i8*) #2

; Function Attrs: nounwind uwtable
define i32 @main() #0 {
  %1 = alloca i32, align 4
  %s0 = alloca %struct.S0, align 8
  %vt = alloca %struct.Vtable0, align 4
  store i32 0, i32* %1, align 4
  %2 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i32 0, i32 0
  store double 1.000000e+00, double* %2, align 8
  %3 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i32 0, i32 1
  store i32 2, i32* %3, align 8
  %4 = load i8*, i8** @cool, align 8
  %5 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i32 0, i32 2
  store i8* %4, i8** %5, align 8
  %6 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i32 0, i32 3
  store i32 3, i32* %6, align 8
  %7 = getelementptr inbounds %struct.S0, %struct.S0* %s0, i32 0, i32 4
  store i8 107, i8* %7, align 4
  %8 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i32 0, i32 0
  store i32 16, i32* %8, align 4
  %9 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i32 0, i32 1
  store i32 24, i32* %9, align 4
  %10 = getelementptr inbounds %struct.Vtable0, %struct.Vtable0* %vt, i32 0, i32 2
  store i32 28, i32* %10, align 4
  %11 = bitcast %struct.S0* %s0 to %struct.SAny*
  call void @check(%struct.Vtable0* %vt, %struct.SAny* %11)
  ret i32 0
}

attributes #0 = { nounwind uwtable "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind readonly "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { nounwind readonly }

!llvm.ident = !{!0}

!0 = !{!"clang version 3.8.1-12~bpo8+1 (tags/RELEASE_381/final)"}
