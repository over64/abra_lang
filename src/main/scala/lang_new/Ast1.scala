package lang_new

object Ast1 {
  case class LlModule(body: Seq[LlModBody]) {
    val prologue =
      """
        |; ModuleID = 'main4.c'
        |target datalayout = "e-m:e-i64:64-f80:128-n8:16:32:64-S128"
        |target triple = "x86_64-pc-linux-gnu"
        |declare i32 @printf(i8*, ...)
      """.stripMargin
    override def toString = {
      val lines = body.flatMap { b => b.toString.split("\n") }
      lines.mkString(s"$prologue\n", "\n", "")
    }
  }

  sealed trait LlModBody
  sealed trait LlFnBody {
    val resultName: String
    val retType: String
  }

  sealed trait LlType
  case class LlSimpleType(name: String) extends LlType
  case class LlField(name: String, typeName: String) {
    override def toString = s"$typeName $name"
  }
  case class LlStructType(name: String, fields: Seq[LlField]) extends LlType with LlModBody {
    override def toString = s"$name = type { ${fields.map(_.typeName).mkString(", ")} }"
  }
  case class LlInlineStatement(s: String) extends LlFnBody {
    override def toString = s

    // Fixme: hohoho
    override val resultName: String = ""
    override val retType: String = ""
  }

  case class LlFn(name: String, retType: String, args: Seq[LlField], body: Seq[LlFnBody]) extends LlModBody {
    val lines = body.flatMap { b => b.toString.split("\n") }
    val ret: String = body.last match {
      case inline: LlInlineStatement => ""
      case other: LlFnBody =>
        if (retType == "void") "ret void"
        else s"ret ${other.retType} ${other.resultName}"
    }
    val withRet = lines ++ Seq(ret)

    override def toString = s"define $retType $name(${args.mkString(", ")}) { ${withRet.mkString("\n\t", "\n\t", "\n")}}"
  }

  case class LlVar(name: String, ttype: String, align: Int = 8)
  case class LlTmp(name: String, pointer: Boolean)
  case class LLlGep(resultName: String, retType: String, varName: String, varType: String, fields: Seq[Int]) extends LlFnBody {
    override def toString =
      s"$resultName = getelementptr $varType, $varType* $varName, ${fields.map { i => s"i32 $i" } mkString (", ")}"
  }

  case class LlLoad(resultName: String, retType: String, varName: String) extends LlFnBody {
    override def toString = s"$resultName = load $retType, $retType* $varName, align 8"
  }
  case class LlStore(varType: String, from: String, to: String, align: Int = 8) extends LlFnBody {
    override val resultName: String = ""
    override val retType: String = ""

    override def toString = s"store  $varType $from, $varType* $to, align $align"
  }
  case class LlAlloca(val resultName: String, typeName: String, align: Int = 8) extends LlFnBody {
    override val retType: String = typeName + "*"

    override def toString = s"$resultName = alloca $typeName, align $align"
  }

  case class LlCallArg(ttype: String, value: String) {
    override def toString = s"$ttype $value"
  }
  case class LlCall(resultName: String, fnName: String, retType: String, before: Seq[LlFnBody], args: Seq[LlCallArg]) extends LlFnBody {
    override def toString = {
      val lines = before.map(_.toString) ++ Seq(s"${if(retType == "void") "" else s"$resultName = " }call $retType $fnName(${args.mkString(", ")})")
      lines.mkString("\n")
    }
  }
  case class LlIConst(value: Int) extends LlFnBody {
    override val resultName: String = value.toString
    override val retType: String = "i32"

    override def toString = ""
  }
}
