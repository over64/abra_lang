

import scala.collection.mutable

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


  //work with var?
  //  case v: AST.Val =>
  //  val sv = gen(out, v.init) match {
  //    case c: Const => out.println(s"  store i32 ${c}, i32* %${v.name}, align 4")
  //    case Stored(name) =>
  //      nTmpVal += 1
  //      out.println(s"  %$nTmpVal = load i32* %$name, align 4")
  //      out.println(s"  store i32 %${nTmpVal.toString}, i32* %${v.name}, align 4")
  //    case Result(name) => out.println(s"  store i32 %${name}, i32* %${v.name}, align 4")
  //  }

  sealed trait LlvmAST

  sealed trait Operable extends LlvmAST

  case class Const(v: Int) extends Operable

  case class Result(name: String) extends Operable

  case class Stored(name: String) extends LlvmAST

  case class Nope() extends LlvmAST


  var nTmpVal = 0;
  val symbols = mutable.Map[String, LlvmAST]()

  def gen(out: Pipeline, ast: AST.Lang): LlvmAST = {
    ast match {
      case id: AST.Ident =>
        symbols(id.name)
      case v: AST.IConst =>
        Const(v.value)
      case v: AST.Val =>
        val sv = gen(out, v.init) match {
          case c: Const => c
          case Stored(name) =>
            nTmpVal += 1
            out.println(s"  %$nTmpVal = load i32* %$name, align 4")
            Result(nTmpVal.toString)
          case r: Result => r
        }
        symbols(v.name) = sv
        sv
      case AST.Assignment(i, expr) =>
        val l = gen(out, expr)
        val s = symbols(i.name)
        s match {
          case c: Const => throw new RuntimeException("reassing to val")
          case r: Result => throw new RuntimeException("reassing to val")
          case Stored(name) =>
            l match {
              case Const(c) => out.println(s"  store i32 $c, i32* %$name, align 4")
              case Stored(innerName) =>
                nTmpVal += 1
                out.println(s"  %$nTmpVal = load i32* %$innerName, align 4")
                out.println(s"  store i32 %${nTmpVal.toString}, i32* %$name, align 4")
              case Result(innerName) => out.println(s"  store i32 %$innerName, i32* %$name, align 4")
            }
        }
        Nope()
      case v: AST.Var =>
        out.println(s"  %${v.name} = alloca i32, align 4")
        gen(out, v.init) match {
          case Const(c) => out.println(s"  store i32 $c, i32* %${v.name}, align 4")
          case Stored(name) =>
            nTmpVal += 1
            out.println(s"  %$nTmpVal = load i32* %$name, align 4")
            out.println(s"  store i32 %${nTmpVal.toString}, i32* %${v.name}, align 4")
          case Result(name) => out.println(s"  store i32 %${name}, i32* %${v.name}, align 4")
        }
        val sv = Stored(v.name)
        symbols(v.name) = sv
        sv
      case AST.InfixCall(op, l, r) =>
        val v1 = gen(out, l) match {
          case Const(c) => c.toString
          case Stored(name) =>
            nTmpVal += 1
            out.println(s"  %$nTmpVal = load i32* %$name, align 4")
            s"%$nTmpVal"
          case Result(name) => s"%$name"
        }

        val v2 = gen(out, r) match {
          case Const(c) => c.toString
          case Stored(name) =>
            nTmpVal += 1
            out.println(s"  %$nTmpVal = load i32* %$name, align 4")
            s"%$nTmpVal"
          case Result(name) => s"%$name"
        }

        nTmpVal += 1

        op match {
          case "*" => out.println(s"  %$nTmpVal = mul nsw i32 $v1, $v2")
          case "/" => out.println(s"  %$nTmpVal = sdiv i32 $v1, $v2")
          case "+" => out.println(s"  %$nTmpVal = add nsw i32 $v1, $v2")
          case "-" => out.println(s"  %$nTmpVal = sub nsw i32 $v1, $v2")
        }
        Result(nTmpVal.toString)
    }
  }

  def genBitcode(out: Pipeline, ast: List[AST.Lang]) = {
    out.println(
      """; ModuleID = 'main.c'
target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
target triple = "x86_64-pc-linux-gnu"

@.str = private unnamed_addr constant [3 x i8] c"%d\00", align 1

; Function Attrs: nounwind uwtable
define i32 @main() #0 {""")

    ast.map(gen(out, _)).last match {
      case Result(name) => out.println(
        s"""  call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.str, i32 0, i32 0), i32 %${name})""")
      case Stored(name) =>
        out.println(s"  %res = load i32* %$name, align 4")
        out.println( s"""  call i32 (i8*, ...)* @printf(i8* getelementptr inbounds ([3 x i8]* @.str, i32 0, i32 0), i32 %res)""")
    }

    out.println(
      s"""  ret i32 0
}
declare i32 @printf(i8*, ...) #1""")
  }

}
