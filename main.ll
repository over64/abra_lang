; ModuleID = 'main.c'
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1

; Function Attrs: nounwind uwtable
define i32 @main() #0 {
  %a = alloca i32, align 4
  %anon1 = alloca i32, align 4
  store i32 5, i32* %anon1, align 4
  %1 = load i32* %anon1, align 4
  store i32 %1, i32* %a, align 4
  %anon2 = alloca i32, align 4
  store i32 5, i32* %anon2, align 4
  %2 = load i32* %anon2, align 4
  %anon3 = alloca i32, align 4
  store i32 1, i32* %anon3, align 4
  %3 = load i32* %anon3, align 4
  %anon4 = alloca i32, align 4
  store i32 4, i32* %anon4, align 4
  %4 = load i32* %anon4, align 4
  %5 = add nsw i32 %3, %4
  %anon5 = alloca i32, align 4
  store i32 5, i32* %anon5, align 4
  %6 = load i32* %anon5, align 4
  %7 = add nsw i32 %5, %6
  %anon6 = alloca i32, align 4
  store i32 3, i32* %anon6, align 4
  %8 = load i32* %anon6, align 4
  %9 = mul nsw i32 %7, %8
  %10 = add nsw i32 %2, %9
  %b = alloca i32, align 4
  %11 = load i32* %a, align 4
  %anon7 = alloca i32, align 4
  store i32 2, i32* %anon7, align 4
  %12 = load i32* %anon7, align 4
  %13 = mul nsw i32 %11, %12
  store i32 %13, i32* %b, align 4
  %anon8 = alloca i32, align 4
  store i32 5, i32* %anon8, align 4
  %res = load i32* %anon8, align 4
  call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.str, i32 0, i32 0), i32 %res)
  ret i32 0
}
declare i32 @printf(i8*, ...) #1