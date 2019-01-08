%struct.eh_table = type { i16, i16 }
@tbl.bar = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@bar, %eh_branch1) to i64),
      i64 ptrtoint(i8* bitcast(i32 (i32)* @bar to i8*) to i64)) to i16),
  i16 0}
@tbl.baz = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@baz, %eh_branch3) to i64),
      i64 ptrtoint(i8* bitcast(i32 (i32, i32)* @baz to i8*) to i64)) to i16),
  i16 0}
@tbl._main = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@_main, %eh_branch5) to i64),
      i64 ptrtoint(i8* bitcast(i32 ()* @_main to i8*) to i64)) to i16),
  i16 0}
@tbl.main = constant %struct.eh_table {
  i16 trunc(
    i64 sub(
      i64 ptrtoint(i8* blockaddress(@main, %eh_branch7) to i64),
      i64 ptrtoint(i8* bitcast(i32 ()* @main to i8*) to i64)) to i16),
  i16 0}
; ModuleID = 'program.c'
source_filename = "program.c"
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@XX_MARKER_XX = dso_local local_unnamed_addr global i32 9000, align 4, !dbg !0
@.str = private unnamed_addr constant [21 x i8] c"an error has occured\00", align 1

; Function Attrs: norecurse nounwind readnone uwtable
define dso_local void @"unused$212"() local_unnamed_addr #0 !dbg !11 {
  ret void, !dbg !14
}

; Function Attrs: nounwind uwtable
define dso_local i32 @bar(i32) local_unnamed_addr #1 !dbg !15 {
  call void @llvm.dbg.value(metadata i32 %0, metadata !19, metadata !DIExpression()), !dbg !20
  %2 = tail call i32 @rand() #5, !dbg !21
call void asm sideeffect ".Leh_label1:", ""()
call void asm sideeffect "movw %ax, .Leh_label1-bar", ""()
call void asm sideeffect ".short .Leh_label1-bar", ""()
  br label %eh_branch1
eh_branch1:
  %3 = and i32 %2, 1, !dbg !23
  %4 = icmp eq i32 %3, 0, !dbg !23
  br i1 %4, label %5, label %6, !dbg !24

; <label>:5:                                      ; preds = %1
  tail call void @raise(i8* getelementptr inbounds ([21 x i8], [21 x i8]* @.str, i64 0, i64 0)) #5, !dbg !25
call void asm sideeffect ".Leh_label2:", ""()
call void asm sideeffect "movw %ax, .Leh_label2-bar", ""()
call void asm sideeffect ".short .Leh_label2-bar", ""()
  br label %eh_branch2
eh_branch2:
  br label %6, !dbg !25

; <label>:6:                                      ; preds = %5, %1
  %7 = mul nsw i32 %0, %0, !dbg !26
  ret i32 %7, !dbg !27
}

; Function Attrs: nounwind
declare dso_local i32 @rand() local_unnamed_addr #2

declare dso_local void @raise(i8*) local_unnamed_addr #3

; Function Attrs: nounwind uwtable
define dso_local i32 @baz(i32, i32) local_unnamed_addr #1 !dbg !28 {
  call void @llvm.dbg.value(metadata i32 %0, metadata !32, metadata !DIExpression()), !dbg !34
  call void @llvm.dbg.value(metadata i32 %1, metadata !33, metadata !DIExpression()), !dbg !35
  call void @llvm.dbg.value(metadata i32 %0, metadata !19, metadata !DIExpression()) #5, !dbg !36
  %3 = tail call i32 @rand() #5, !dbg !38
call void asm sideeffect ".Leh_label3:", ""()
call void asm sideeffect "movw %ax, .Leh_label3-baz", ""()
call void asm sideeffect ".short .Leh_label3-baz", ""()
  br label %eh_branch3
eh_branch3:
  %4 = and i32 %3, 1, !dbg !39
  %5 = icmp eq i32 %4, 0, !dbg !39
  br i1 %5, label %6, label %7, !dbg !40

; <label>:6:                                      ; preds = %2
  tail call void @raise(i8* getelementptr inbounds ([21 x i8], [21 x i8]* @.str, i64 0, i64 0)) #5, !dbg !41
call void asm sideeffect ".Leh_label4:", ""()
call void asm sideeffect "movw %ax, .Leh_label4-baz", ""()
call void asm sideeffect ".short .Leh_label4-baz", ""()
  br label %eh_branch4
eh_branch4:
  br label %7, !dbg !41

; <label>:7:                                      ; preds = %2, %6
  %8 = mul nsw i32 %0, %0, !dbg !42
  %9 = add nsw i32 %8, %1, !dbg !43
  ret i32 %9, !dbg !44
}

; Function Attrs: nounwind uwtable
define dso_local i32 @_main() local_unnamed_addr #1 !dbg !45 {
  call void @llvm.dbg.value(metadata i32 2, metadata !32, metadata !DIExpression()) #5, !dbg !48
  call void @llvm.dbg.value(metadata i32 2, metadata !33, metadata !DIExpression()) #5, !dbg !50
  call void @llvm.dbg.value(metadata i32 2, metadata !19, metadata !DIExpression()) #5, !dbg !51
  %1 = tail call i32 @rand() #5, !dbg !53
call void asm sideeffect ".Leh_label5:", ""()
call void asm sideeffect "movw %ax, .Leh_label5-_main", ""()
call void asm sideeffect ".short .Leh_label5-_main", ""()
  br label %eh_branch5
eh_branch5:
  %2 = and i32 %1, 1, !dbg !54
  %3 = icmp eq i32 %2, 0, !dbg !54
  br i1 %3, label %4, label %5, !dbg !55

; <label>:4:                                      ; preds = %0
  tail call void @raise(i8* getelementptr inbounds ([21 x i8], [21 x i8]* @.str, i64 0, i64 0)) #5, !dbg !56
call void asm sideeffect ".Leh_label6:", ""()
call void asm sideeffect "movw %ax, .Leh_label6-_main", ""()
call void asm sideeffect ".short .Leh_label6-_main", ""()
  br label %eh_branch6
eh_branch6:
  br label %5, !dbg !56

; <label>:5:                                      ; preds = %0, %4
  ret i32 0, !dbg !57
}

; Function Attrs: nounwind uwtable
define dso_local i32 @main() local_unnamed_addr #1 !dbg !58 {
  call void @llvm.dbg.value(metadata i32 2, metadata !32, metadata !DIExpression()) #5, !dbg !59
  call void @llvm.dbg.value(metadata i32 2, metadata !33, metadata !DIExpression()) #5, !dbg !62
  call void @llvm.dbg.value(metadata i32 2, metadata !19, metadata !DIExpression()) #5, !dbg !63
  %1 = tail call i32 @rand() #5, !dbg !65
call void asm sideeffect ".Leh_label7:", ""()
call void asm sideeffect "movw %ax, .Leh_label7-main", ""()
call void asm sideeffect ".short .Leh_label7-main", ""()
  br label %eh_branch7
eh_branch7:
  %2 = and i32 %1, 1, !dbg !66
  %3 = icmp eq i32 %2, 0, !dbg !66
  br i1 %3, label %4, label %5, !dbg !67

; <label>:4:                                      ; preds = %0
  tail call void @raise(i8* getelementptr inbounds ([21 x i8], [21 x i8]* @.str, i64 0, i64 0)) #5, !dbg !68
call void asm sideeffect ".Leh_label8:", ""()
call void asm sideeffect "movw %ax, .Leh_label8-main", ""()
call void asm sideeffect ".short .Leh_label8-main", ""()
  br label %eh_branch8
eh_branch8:
  br label %5, !dbg !68

; <label>:5:                                      ; preds = %0, %4
  ret i32 0, !dbg !69
}

; Function Attrs: nounwind readnone speculatable
declare void @llvm.dbg.value(metadata, metadata, metadata) #4

attributes #0 = { norecurse nounwind readnone uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #1 = { nounwind uwtable "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-jump-tables"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #2 = { nounwind "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #3 = { "correctly-rounded-divide-sqrt-fp-math"="false" "disable-tail-calls"="false" "less-precise-fpmad"="false" "no-frame-pointer-elim"="false" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "no-signed-zeros-fp-math"="false" "no-trapping-math"="false" "stack-protector-buffer-size"="8" "target-cpu"="x86-64" "target-features"="+fxsr,+mmx,+sse,+sse2,+x87" "unsafe-fp-math"="false" "use-soft-float"="false" }
attributes #4 = { nounwind readnone speculatable }
attributes #5 = { nounwind }

!llvm.dbg.cu = !{!2}
!llvm.module.flags = !{!7, !8, !9}
!llvm.ident = !{!10}

!0 = !DIGlobalVariableExpression(var: !1, expr: !DIExpression())
!1 = distinct !DIGlobalVariable(name: "XX_MARKER_XX", scope: !2, file: !3, line: 3, type: !6, isLocal: false, isDefinition: true)
!2 = distinct !DICompileUnit(language: DW_LANG_C99, file: !3, producer: "clang version 7.0.1-+rc3-2 (tags/RELEASE_701/rc3)", isOptimized: true, runtimeVersion: 0, emissionKind: FullDebug, enums: !4, globals: !5)
!3 = !DIFile(filename: "program.c", directory: "/home/over/build/abra_lang/ex_handle/global_ex_table")
!4 = !{}
!5 = !{!0}
!6 = !DIBasicType(name: "int", size: 32, encoding: DW_ATE_signed)
!7 = !{i32 2, !"Dwarf Version", i32 4}
!8 = !{i32 2, !"Debug Info Version", i32 3}
!9 = !{i32 1, !"wchar_size", i32 4}
!10 = !{!"clang version 7.0.1-+rc3-2 (tags/RELEASE_701/rc3)"}
!11 = distinct !DISubprogram(name: "unused$212", scope: !3, file: !3, line: 7, type: !12, isLocal: false, isDefinition: true, scopeLine: 7, isOptimized: true, unit: !2, retainedNodes: !4)
!12 = !DISubroutineType(types: !13)
!13 = !{null}
!14 = !DILocation(line: 8, column: 1, scope: !11)
!15 = distinct !DISubprogram(name: "bar", scope: !3, file: !3, line: 10, type: !16, isLocal: false, isDefinition: true, scopeLine: 10, flags: DIFlagPrototyped, isOptimized: true, unit: !2, retainedNodes: !18)
!16 = !DISubroutineType(types: !17)
!17 = !{!6, !6}
!18 = !{!19}
!19 = !DILocalVariable(name: "x", arg: 1, scope: !15, file: !3, line: 10, type: !6)
!20 = !DILocation(line: 10, column: 13, scope: !15)
!21 = !DILocation(line: 11, column: 8, scope: !22)
!22 = distinct !DILexicalBlock(scope: !15, file: !3, line: 11, column: 8)
!23 = !DILocation(line: 11, column: 19, scope: !22)
!24 = !DILocation(line: 11, column: 8, scope: !15)
!25 = !DILocation(line: 12, column: 2, scope: !22)
!26 = !DILocation(line: 14, column: 14, scope: !15)
!27 = !DILocation(line: 14, column: 5, scope: !15)
!28 = distinct !DISubprogram(name: "baz", scope: !3, file: !3, line: 17, type: !29, isLocal: false, isDefinition: true, scopeLine: 17, flags: DIFlagPrototyped, isOptimized: true, unit: !2, retainedNodes: !31)
!29 = !DISubroutineType(types: !30)
!30 = !{!6, !6, !6}
!31 = !{!32, !33}
!32 = !DILocalVariable(name: "x", arg: 1, scope: !28, file: !3, line: 17, type: !6)
!33 = !DILocalVariable(name: "y", arg: 2, scope: !28, file: !3, line: 17, type: !6)
!34 = !DILocation(line: 17, column: 13, scope: !28)
!35 = !DILocation(line: 17, column: 20, scope: !28)
!36 = !DILocation(line: 10, column: 13, scope: !15, inlinedAt: !37)
!37 = distinct !DILocation(line: 18, column: 12, scope: !28)
!38 = !DILocation(line: 11, column: 8, scope: !22, inlinedAt: !37)
!39 = !DILocation(line: 11, column: 19, scope: !22, inlinedAt: !37)
!40 = !DILocation(line: 11, column: 8, scope: !15, inlinedAt: !37)
!41 = !DILocation(line: 12, column: 2, scope: !22, inlinedAt: !37)
!42 = !DILocation(line: 14, column: 14, scope: !15, inlinedAt: !37)
!43 = !DILocation(line: 18, column: 19, scope: !28)
!44 = !DILocation(line: 18, column: 5, scope: !28)
!45 = distinct !DISubprogram(name: "_main", scope: !3, file: !3, line: 21, type: !46, isLocal: false, isDefinition: true, scopeLine: 21, isOptimized: true, unit: !2, retainedNodes: !4)
!46 = !DISubroutineType(types: !47)
!47 = !{!6}
!48 = !DILocation(line: 17, column: 13, scope: !28, inlinedAt: !49)
!49 = distinct !DILocation(line: 22, column: 5, scope: !45)
!50 = !DILocation(line: 17, column: 20, scope: !28, inlinedAt: !49)
!51 = !DILocation(line: 10, column: 13, scope: !15, inlinedAt: !52)
!52 = distinct !DILocation(line: 18, column: 12, scope: !28, inlinedAt: !49)
!53 = !DILocation(line: 11, column: 8, scope: !22, inlinedAt: !52)
!54 = !DILocation(line: 11, column: 19, scope: !22, inlinedAt: !52)
!55 = !DILocation(line: 11, column: 8, scope: !15, inlinedAt: !52)
!56 = !DILocation(line: 12, column: 2, scope: !22, inlinedAt: !52)
!57 = !DILocation(line: 23, column: 5, scope: !45)
!58 = distinct !DISubprogram(name: "main", scope: !3, file: !3, line: 25, type: !46, isLocal: false, isDefinition: true, scopeLine: 25, isOptimized: true, unit: !2, retainedNodes: !4)
!59 = !DILocation(line: 17, column: 13, scope: !28, inlinedAt: !60)
!60 = distinct !DILocation(line: 22, column: 5, scope: !45, inlinedAt: !61)
!61 = distinct !DILocation(line: 26, column: 12, scope: !58)
!62 = !DILocation(line: 17, column: 20, scope: !28, inlinedAt: !60)
!63 = !DILocation(line: 10, column: 13, scope: !15, inlinedAt: !64)
!64 = distinct !DILocation(line: 18, column: 12, scope: !28, inlinedAt: !60)
!65 = !DILocation(line: 11, column: 8, scope: !22, inlinedAt: !64)
!66 = !DILocation(line: 11, column: 19, scope: !22, inlinedAt: !64)
!67 = !DILocation(line: 11, column: 8, scope: !15, inlinedAt: !64)
!68 = !DILocation(line: 12, column: 2, scope: !22, inlinedAt: !64)
!69 = !DILocation(line: 26, column: 5, scope: !58)
