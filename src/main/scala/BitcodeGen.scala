import java.io.PrintWriter

import ASTGen.{IVal, InfixCall, LangAST}

/**
  * Created by over on 18.11.15.
  */
object BitcodeGen {

  //  ; ModuleID = 'main.c'
  //  target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
  //  target triple = "x86_64-pc-linux-gnu"
  //
  //  @.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1
  //
  //  ; Function Attrs: nounwind uwtable
  //  define i32 @main() #0 {
  //    %a = alloca i32, align 4
  //    %b = alloca i32, align 4
  //    store i32 1, i32* %a, align 4
  //    store i32 2, i32* %b, align 4
  //    %1 = load i32* %a, align 4
  //    %2 = load i32* %b, align 4
  //    %3 = add nsw i32 %1, %2
  //    %4 = call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.str, i32 0, i32 0), i32 %3)
  //    ret i32 0
  //  }
  //
  //  declare i32 @printf(i8*, ...) #1
  //
  //  attributes #0 = { nounwind uwtable "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "unsafe-fp-math"="false" "use-soft-float"="false" }
  //  attributes #1 = { "less-precise-fpmad"="false" "no-frame-pointer-elim"="true" "no-frame-pointer-elim-non-leaf" "no-infs-fp-math"="false" "no-nans-fp-math"="false" "stack-protector-buffer-size"="8" "unsafe-fp-math"="false" "use-soft-float"="false" }
  //
  //  !llvm.ident = !{!0}
  //
  //  !0 = !{!"Debian clang version 3.6.2-3 (tags/RELEASE_362/final) (based on LLVM 3.6.2)"}

  sealed trait LlvmAST

  case class StoredVal(name: String) extends LlvmAST

  case class TmpVal(name: String) extends LlvmAST

  var nTmpVal = 0;

  def gen(out: PrintWriter, ast: LangAST): LlvmAST = {
    ast match {
      case InfixCall(op, l, r) =>
        val v1 = gen(out, l) match {
          case StoredVal(name) =>
            nTmpVal += 1
            out.println(s"  %$nTmpVal = load i32* %$name, align 4")
            TmpVal(nTmpVal.toString)
          case tmp: TmpVal => tmp
        }

        val v2 = gen(out, r) match {
          case StoredVal(name) =>
            nTmpVal += 1
            out.println(s"  %$nTmpVal = load i32* %$name, align 4")
            TmpVal(nTmpVal.toString)
          case tmp: TmpVal => tmp
        }

        nTmpVal += 1

        op match {
          case "*" => out.println(s"  %$nTmpVal = mul nsw i32 %${v1.name}, %${v2.name}")
          case "/" => out.println(s"  %$nTmpVal = sdiv i32 %${v1.name}, %${v2.name}")
          case "+" => out.println(s"  %$nTmpVal = add nsw i32 %${v1.name}, %${v2.name}")
          case "-" => out.println(s"  %$nTmpVal = sub nsw i32 %${v1.name}, %${v2.name}")
        }
        TmpVal(nTmpVal.toString)
      case v: IVal =>
        out.println(s"  %${v.name} = alloca i32, align 4")
        out.println(s"  store i32 ${v.value}, i32* %${v.name}, align 4")
        StoredVal(v.name)
    }
  }

  def genBitcode(out: PrintWriter, ast: LangAST) = {
    out.println(
      """; ModuleID = 'main.c'
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1

; Function Attrs: nounwind uwtable
define i32 @main() #0 {""")

    val tmpVal = gen(out, ast).asInstanceOf[TmpVal]

    out.println(
      s"""  call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.str, i32 0, i32 0), i32 %${tmpVal.name})
  ret i32 0
}
declare i32 @printf(i8*, ...) #1""".stripMargin)
  }

}
