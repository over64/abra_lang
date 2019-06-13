package m3.codegen

import java.io.PrintStream

import m3.codegen.IrUtils._
import m3.parse.Ast0._
import m3.parse.Level
import m3.typecheck.Builtin
import m3.typecheck.TCMeta.{ParseNodeTCMetaImplicit, TypeDeclTCMetaImplicit}

import scala.collection.mutable.{HashMap, ListBuffer}

trait OutConf {
  def withStream[T](module: Module, op: PrintStream => T): T
}

case class ModContext(out: PrintStream,
                      level: Level,
                      module: Module,
                      typeHints: ListBuffer[TypeHint] = ListBuffer(),
                      invokeInc: ListBuffer[TypeHint] = ListBuffer(),
                      invokeDec: ListBuffer[TypeHint] = ListBuffer(),
                      strings: ListBuffer[String] = ListBuffer(),
                      defs: HashMap[String, DContext] = HashMap()) {
  def write(line: String): Unit =
    out.println(line)

  def addString(str: String): Int = {
    strings += str
    strings.length - 1
  }
}

case class DContext(fn: Def,
                    vars: HashMap[String, TypeHint] = HashMap(),
                    slots: ListBuffer[TypeHint] = ListBuffer(),
                    code: ListBuffer[String] = ListBuffer(),
                    var needAlloc: Boolean = false,
                    var needInc: Boolean = false,
                    var needDec: Boolean = false,
                    var needFree: Boolean = false,
                    var regSeq: Int = 0,
                    var branchSeq: Int = 0,
                    var exprDeep: Int = 1) {

  def addSlot(th: TypeHint): String = {
    slots += th
    s"%__stack_slot${slots.length - 1}__"
  }

  def nextReg(label: String): String = {
    regSeq += 1
    label + regSeq
  }

  def nextBranch(label: String): String = {
    branchSeq += 1
    label + branchSeq
  }

  def write(line: String): Unit =
    code += ("  " * exprDeep + line)

  def deeper(callback: DContext => Unit): Unit = {
    exprDeep += 1
    callback(this)
    exprDeep -= 1
  }
}

sealed trait RequireDest
case object AsStoreSrc extends RequireDest
case object AsCallArg extends RequireDest
case object AsRetVal extends RequireDest

case class EResult(value: String, isPtr: Boolean, isAnon: Boolean)

class IrGenPass {
  def passExpr(mctx: ModContext, dctx: DContext, expr: Expression): EResult = {
    mctx.typeHints += expr.getTypeHint[TypeHint]

    def performCons(cons: Expression, args: Seq[Expression]): EResult = {
      val th = cons.getTypeHint[TypeHint]
      val irType = th.toValue

      if (th.isValueType(mctx.level, mctx.module)) {
        val slot = dctx.addSlot(th)

        val (base, fieldsTh) = th.isIrArray(mctx.level, mctx.module) match {
          case Some(sd) =>
            val r = "%" + dctx.nextReg("")
            dctx.write(s"$r = getelementptr $irType, $irType* $slot, i64 0, i32 0")
            dctx.write(s"store i64 ${sd.getBuiltinArrayLen.get}, i64* $r")
            val elTh = th.asInstanceOf[ScalarTh].params.head
            ("i32 1, i64 ", (0 to args.length).map(_ => elTh))
          case None =>
            ("i32 ", th.structFields(mctx.level, mctx.module).map(_.typeHint))
        }

        for (((field, fieldTh), idx) <- (args zip fieldsTh).zipWithIndex) {
          val fRes = passExpr(mctx, dctx, field)

          val r = "%" + dctx.nextReg("")
          dctx.write(s"$r = getelementptr $irType, $irType* $slot, i64 0, $base$idx")

          Abi.store(mctx, dctx, false, false, fieldTh, field.getTypeHint, r, fRes.value)
        }

        EResult(slot, true, true)
      } else {
        val r0 = "%" + dctx.nextReg("")
        dctx.write(s"$r0 = load i8* (i64)*,  i8* (i64)** @evaAlloc")

        val (to, toIrType, base, fieldsTh) =
          th.isIrArray(mctx.level, mctx.module) match {
            case Some(sd) =>
              val elTh = th.asInstanceOf[ScalarTh].params.head
              val elIrType = elTh.toValue
              val r1, r2, r3, r4, r5, r6 = "%" + dctx.nextReg("")

              dctx.write(s"$r1 = getelementptr $elIrType, $elIrType* null, i64 ${args.length}")
              dctx.write(s"$r2 = ptrtoint $elIrType* $r1 to i64")
              dctx.write(s"$r3 = add i64 $r2, 8")
              dctx.write(s"$r4 = call i8* $r0(i64 $r3)")
              dctx.write(s"$r5 = getelementptr i8, i8* $r4, i64 8")
              dctx.write(s"$r6 = bitcast i8* $r5 to $elIrType*")

              (r6, elIrType, "", (0 to args.length).map(i => elTh))
            case None =>
              val dataIrType = "%" + (th.toString + ".body").escaped
              val r1, r2, r3, r4 = "%" + dctx.nextReg("")

              dctx.write(s"$r1 = getelementptr $dataIrType, $dataIrType* null, i64 1")
              dctx.write(s"$r2 = ptrtoint $dataIrType* $r1 to i64")
              dctx.write(s"$r3 = call i8* $r0(i64 $r2)")
              dctx.write(s"$r4 = bitcast i8* $r3 to $irType")

              (r4, dataIrType, "i64 0, ", th.structFields(mctx.level, mctx.module).map(_.typeHint))
          }

        (args zip fieldsTh).zipWithIndex.foreach { case ((field, fieldTh), idx) =>
          val fRes = passExpr(mctx, dctx, field)
          val fieldDestPtr = "%" + dctx.nextReg("")
          dctx.write(s"$fieldDestPtr = getelementptr $toIrType, $toIrType* $to, ${base}i32 $idx")
          Abi.store(mctx, dctx, false, !fRes.isAnon, fieldTh, field.getTypeHint, fieldDestPtr, fRes.value)
        }
        EResult(to, false, true)
      }
    }

    expr match {
      case lInt(value) => EResult(value, false, true)
      case lit@lFloat(value) =>
        val striped =
          if (lit.getTypeHint[TypeHint] == Builtin.thFloat)
            new java.lang.Float(value).toDouble
          else
            new java.lang.Double(value).toDouble

        EResult("0x" + java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(striped)), false, true)
      case lBoolean(value) =>
        EResult((if (value == "true") 1 else 0).toString, false, true)
      case lNone() => EResult("__no_result__", false, true)
      case str: lString =>
        val strId = mctx.addString(str.value)
        val r = "%" + dctx.nextReg("")
        dctx.write(s"$r = call %String @$$cons.String$strId()")
        EResult(r, false, true)
      case t@Tuple(seq) => performCons(t, seq)
      case cons@Cons(sth, args) => performCons(cons, args)
      case call@Call(e, args) =>
        val id = e.asInstanceOf[lId]
        val toCall = mctx.module.defs(id.value)
        val fth = toCall.getTypeHint[FnTh]

        val argsResults =
          (fth.args zip args).map { case (argTh, arg) =>
            Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, arg), AsCallArg, argTh, arg.getTypeHint)
          }

        val argsIr = (fth.args zip argsResults).map { case (argTh, argRes) =>
          s"${AbiTh.toCallArg(mctx, argTh)} ${argRes.value}"
        }.mkString(", ")

        if (fth.ret == Builtin.thNil) {
          dctx.write(s"call void @${id.value}($argsIr)")
          EResult("__no_result__", false, true)
        } else {
          val r = "%" + dctx.nextReg("")
          dctx.write(s"$r = call ${AbiTh.toRetVal(mctx, fth.ret)} @${id.value}($argsIr)")
          EResult(r, false, true)
        }
    }
  }

  def passDef(mctx: ModContext, fn: Def): Unit = {
    val dctx = DContext(fn)
    val fth = fn.getTypeHint[FnTh]
    val retTh = fth.ret

    fth.args.foreach(th => mctx.typeHints += th)
    mctx.typeHints += retTh

    fn.lambda.body match {
      case llVm(code) => dctx.write(code)
      case AbraCode(seq) =>
        seq.dropRight(1).foreach(expr => passExpr(mctx, dctx, expr))

        val (eth, eRes) = seq.lastOption.map(expr =>
          (expr.getTypeHint[TypeHint], passExpr(mctx, dctx, expr)))
          .getOrElse((Builtin.thNil, EResult("__no_result__", false, true)))

        if (retTh == Builtin.thNil || retTh == Builtin.thUnreachable)
          dctx.write(s"ret void")
        else {
          val sRes = Abi.syncValue(mctx, dctx, eRes, AsRetVal, retTh, eth)
          dctx.write(s"ret ${AbiTh.toRetVal(mctx, retTh)} ${sRes.value}")
        }
    }

    mctx.defs.put(fn.name, dctx)
  }

  def pass(root: Level, outConf: OutConf): Unit = {
    root.eachModule((level, module) => {
      outConf.withStream(module, { out =>

        val mctx = ModContext(out, level, module)

        module.defs.values.foreach(fn => passDef(mctx, fn))
        module.selfDefs.values.foreach(defs => defs.foreach(fn => passDef(mctx, fn)))

        mctx.write("declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)")
        mctx.write("@evaAlloc = external thread_local(initialexec) global i8* (i64)*")
        mctx.write("@evaInc   = external thread_local(initialexec) global void (i8*)*")
        mctx.write("@evaDec   = external thread_local(initialexec) global i64 (i8*)*")
        mctx.write("@evaFree  = external thread_local(initialexec) global void (i8*)*")

        val typeHintOrder = ListBuffer[TypeHint]()
        mctx.typeHints.distinct.foreach(th => th.orderTypeHints(level, module, typeHintOrder))

        val typeDecl = ListBuffer[(TypeHint, String)]()
        typeHintOrder.distinct.foreach(th => th.toDecl(level, module, typeDecl))

        typeDecl.foreach { case (th, decl) => mctx.write(decl) }

        ConsUtils.genStringConstuctors(mctx)

        mctx.invokeInc.distinct.foreach(th => RC.genAcquireRelease(mctx, Inc, th))
        mctx.invokeDec.distinct.foreach(th => RC.genAcquireRelease(mctx, Dec, th))

        mctx.defs.values.foreach { dctx =>
          val fth = dctx.fn.getTypeHint[FnTh]
          val args = dctx.fn.lambda.args.map(arg => s"${AbiTh.toCallArg(mctx, arg.typeHint)} %${arg.name}").mkString(", ")
          mctx.write(s"define ${AbiTh.toRetVal(mctx, fth.ret)} @${dctx.fn.name} ($args) {")

          dctx.slots.zipWithIndex.foreach { case (slotTh, idx) =>
            mctx.write(s"  %__stack_slot${idx}__ = alloca ${slotTh.toValue}")
          }

          dctx.code.foreach(line => mctx.write(line))
          mctx.write("}")
        }
      })
    })
  }
}