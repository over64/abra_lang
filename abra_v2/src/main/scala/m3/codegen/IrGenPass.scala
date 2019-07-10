package m3.codegen

import java.io.PrintStream

import m3.codegen.IrUtils._
import m3.parse.Ast0._
import m3.parse.Level
import m3.typecheck.TCMeta._
import m3.typecheck._

import scala.collection.mutable
import scala.collection.mutable.{HashMap, ListBuffer}

trait OutConf {
  def withStream[T](module: Module, op: PrintStream => T): T
}

case class ModContext(out: PrintStream,
                      level: Level,
                      module: Module,
                      typeHints: ListBuffer[TypeHint] = ListBuffer(),
                      rcDef: HashMap[(TypeHint, RCMode), StringBuilder] = HashMap(),
                      strings: ListBuffer[String] = ListBuffer(),
                      defs: HashMap[String, DContext] = HashMap()) {
  def write(line: String): Unit =
    out.println(line)

  def addString(str: String): Int = {
    strings += str
    strings.length - 1
  }
}

sealed trait ScopeKind
case object OtherKind extends ScopeKind
case class WhileKind(condBr: String, endBr: String) extends ScopeKind
case class Scope(parent: Option[Scope],
                 kind: ScopeKind,
                 aliases: HashMap[String, String] = HashMap(),
                 freeSet: ListBuffer[String] = ListBuffer()) {

  def findAlias(name: String): Option[String] =
    aliases.get(name) match {
      case s: Some[String] => s
      case None => parent match {
        case Some(p) => p.findAlias(name)
        case None => None
      }
    }

  def getAlias(name: String) =
    findAlias(name).get
}

case class DContext(fn: Def,
                    isClosure: Boolean,
                    symbols: HashMap[String, Int] = HashMap(),
                    vars: HashMap[String, TypeHint] = HashMap(),
                    closureSlots: ListBuffer[String] = ListBuffer(),
                    slots: ListBuffer[TypeHint] = ListBuffer(),
                    var scope: Scope = Scope(None, OtherKind),
                    code: ListBuffer[String] = ListBuffer(),
                    var needAlloc: Boolean = false,
                    var needInc: Boolean = false,
                    var needDec: Boolean = false,
                    var needFree: Boolean = false,
                    var lambdaSeq: Int = 0,
                    var regSeq: Int = 0,
                    var branchSeq: Int = 0,
                    var exprDeep: Int = 1) {

  def addSymbol(name: String): String = {
    val newVersion =
      symbols.get(name) match {
        case Some(version) => version + 1
        case None => 0
      }
    symbols.put(name, newVersion)
    val suffix = if (newVersion == 0) "" else "_" + newVersion
    name + suffix
  }

  def nextLambdaName(): String = {
    lambdaSeq += 1
    s"${fn.name}$$lambda$lambdaSeq"
  }

  def addClosureSlot(th: String): String = {
    closureSlots += th
    s"%__closure_slot${closureSlots.length - 1}__"
  }

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

  def deeper[T](kind: ScopeKind, callback: DContext => T): T = {
    exprDeep += 1
    val (oldScope, newScope) = (scope, Scope(Some(scope), kind))
    scope = newScope
    val t = callback(this)
    scope = oldScope
    exprDeep -= 1
    t
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

    def performCall(fth: FnTh, callArgs: Seq[Expression], envArg: Option[String], fName: String): EResult = {
      val argsResults =
        (fth.args zip callArgs).map { case (argTh, arg) =>
          Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, arg), AsCallArg, argTh, arg.getTypeHint)
        }

      val argsIr = (fth.args zip argsResults).map { case (argTh, argRes) =>
        s"${AbiTh.toCallArg(mctx, argTh)} ${argRes.value}"
      }
      val argsWithEnvIr = envArg match {
        case None => argsIr
        case Some(env) => argsIr :+ s"i8* $env"
      }

      if (fth.ret == Builtin.thNil || fth.ret == Builtin.thUnreachable) {
        dctx.write(s"call void $fName(${argsWithEnvIr.mkString(", ")})")
        EResult("__no_result__", false, true)
      } else {
        val r = "%" + dctx.nextReg("")
        dctx.write(s"$r = call ${AbiTh.toRetVal(mctx, fth.ret)} $fName(${argsWithEnvIr.mkString(", ")})")
        EResult(r, false, true)
      }
    }

    def performAndOr(lhs: Expression, rhs: Expression, isAnd: Boolean): EResult = {
      val (brPrefix, brId) = (if (isAnd) "and" else "or", dctx.nextBranch(""))
      val (leftBr, rightBr, endBr) = (s"${brPrefix}Lhs$brId", s"${brPrefix}Rhs$brId", s"end$brId")

      dctx.write(s"br label %$leftBr")
      dctx.write(s"$leftBr:")

      dctx.deeper(OtherKind, { dctx =>
        val left = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, lhs), AsStoreSrc, Builtin.thBool, Builtin.thBool)
        val r1 = "%" + dctx.nextReg("")
        dctx.write(s"$r1 = icmp eq i8 ${left.value}, ${if (isAnd) "0" else "1"}")
        dctx.write(s"br i1 $r1, label %$endBr, label %$rightBr")
      })

      dctx.write(s"$rightBr:")

      val rightVal = dctx.deeper(OtherKind, { dctx =>
        val right = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, rhs), AsStoreSrc, Builtin.thBool, Builtin.thBool)
        dctx.write(s"br label %$endBr")
        right.value
      })

      dctx.write(s"$endBr:")
      val r2 = "%" + dctx.nextReg("")
      dctx.write(s"$r2 = phi i8 [ $rightVal, %$rightBr ], [ ${if (isAnd) "0" else "1"}, %$leftBr ]")
      EResult(r2, false, false)
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
      case id: lId =>
        id.getVarLocation match {
          case VarLocal => EResult("%" + dctx.scope.getAlias(id.value), true, false)
          case VarParam =>
            id.getTypeHint[TypeHint].classify(mctx.level, mctx.module) match {
              case RefUnion(_) | ValueUnion(_) | ValueStruct(_) =>
                EResult("%" + id.value, true, false)
              case _ =>
                EResult("%" + id.value, false, false)
            }
          case VarClosureLocal(_) =>
            val closure = dctx.fn.lambda.getClosure
            val closureTh = AbiTh.toClosure(mctx, closure)
            val ((_, idTh, vt), idx) = closure.zipWithIndex.find { case ((name, _, _), _) => name == id.value }.get

            val r1, r2 = "%" + dctx.nextReg("")
            dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* %__closure, i64 0, i32 $idx")
            dctx.write(s"$r2 = load ${idTh.toValue}*, ${idTh.toValue}** $r1")
            EResult(r2, true, false)
          case VarClosureParam(_) =>
            val closure = dctx.fn.lambda.getClosure
            val closureTh = AbiTh.toClosure(mctx, closure)
            val ((_, idTh, vt), idx) = closure.zipWithIndex.find { case ((name, _, _), _) => name == id.value }.get

            val r1, r2 = "%" + dctx.nextReg("")

            idTh.classify(mctx.level, mctx.module) match {
              case RefUnion(_) | ValueUnion(_) | ValueStruct(_) =>
                dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* %__closure, i64 0, i32 $idx")
                dctx.write(s"$r2 = load ${idTh.toValue}*, ${idTh.toValue}** $r1")
                EResult(r2, true, false)
              case _ =>
                dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* %__closure, i64 0, i32 $idx")
                dctx.write(s"$r2 = load ${idTh.toValue}, ${idTh.toValue}* $r1")
                EResult(r2, false, false)
            }
        }
      case t@Tuple(seq) => performCons(t, seq)
      case cons@Cons(sth, args) => performCons(cons, args)
      case call@Call(e, args) =>
        val (fName, callArgs, envArg, fth) =
          call.getCallType match {
            case CallModLocal =>
              val id = e.asInstanceOf[lId]
              //FIXME: use getCallFnTh
              val toCall = mctx.module.defs(id.value)
              ("@" + id.value, args, None, toCall.getTypeHint[FnTh])
            case SelfCallModLocal =>
              val selfTh = e.getTypeHint[TypeHint]
              ("@" + (selfTh + ".get").escaped, e +: args, None, call.getCallFnTh)
            case CallFnPtr =>
              val fnPtrRes = passExpr(mctx, dctx, e)
              val fth = e.getTypeHint[FnTh]
              val irArgs = fth.args.map(th => AbiTh.toCallArg(mctx, th)) :+ "i8*"
              // fixme: don't forget for sync
              val r1, r2 = "%" + dctx.nextReg("")
              dctx.write(s"$r1 = extractvalue ${fth.toValue} ${fnPtrRes.value}, 0")
              dctx.write(s"$r2 = extractvalue ${fth.toValue} ${fnPtrRes.value}, 1")
              (r1, args, Some(r2), fth)
          }

        performCall(fth, callArgs, envArg, fName)
      case call@SelfCall(fnName, self, args) =>
        val selfTh = self.getTypeHint[TypeHint]
        val fth = call.getCallFnTh
        val selfFnName = "@" + (selfTh + "." + fnName).escaped
        performCall(fth, self +: args, None, selfFnName)
      case Prop(from, props) =>
        // FIXME: gep
        passExpr(mctx, dctx, from)
      case store@Store(_, to, what) =>
        val destTh = to.head.getTypeHint[TypeHint]
        val dRes =
          store.getDeclTh[TypeHint] match {
            case Some(newVarTh) =>
              val vName = to.head.value
              val alias = dctx.addSymbol(vName)
              dctx.vars.put(alias, newVarTh)
              dctx.scope.aliases.put(vName, alias)
              EResult("%" + alias, true, true)
            case None =>
              val toRes = passExpr(mctx, dctx, to.head)
              if (!toRes.isPtr)
                throw new RuntimeException("internal compiler error")
              toRes
          }

        val whatTh = what.getTypeHint[TypeHint]
        val wRes = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, what),
          AsStoreSrc, whatTh, whatTh)
        Abi.store(mctx, dctx, !dRes.isAnon, !wRes.isAnon, destTh, whatTh, dRes.value, wRes.value)
        EResult("__no_result__", false, true)
      case lambda: Lambda =>
        val fth = lambda.getTypeHint[FnTh]
        val lambdaName = dctx.nextLambdaName()
        val fn = Def(lambdaName, lambda, fth.ret)
        fn.setTypeHint(fth)

        passDef(mctx, fn, true)

        val closure = lambda.getClosure
        val closureTh = AbiTh.toClosure(mctx, closure)
        val slot = dctx.addClosureSlot(closureTh)

        closure.zipWithIndex.foreach { case ((vName, vTh, vt), idx) =>
          vt match {
            case VarClosureLocal(nested) =>
              val ptr =
                if (nested) {
                  val nestedId = lId(vName)
                  nestedId.setVarLocation(VarClosureLocal(false))
                  nestedId.setTypeHint(vTh)
                  passExpr(mctx, dctx, nestedId).value
                } else
                  "%" + vName

              val r1 = "%" + dctx.nextReg("")
              dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* $slot, i64 0, i32 $idx")
              dctx.write(s"store ${vTh.toValue}* $ptr, ${vTh.toValue}** $r1")
            case VarClosureParam(nested) =>
              val src =
                if (nested) {
                  val nestedId = lId(vName)
                  nestedId.setVarLocation(VarClosureParam(false))
                  nestedId.setTypeHint(vTh)
                  passExpr(mctx, dctx, nestedId).value
                } else
                  "%" + vName

              val r1 = "%" + dctx.nextReg("")
              vTh.classify(mctx.level, mctx.module) match {
                case RefUnion(_) | ValueUnion(_) | ValueStruct(_) =>
                  dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* $slot, i64 0, i32 $idx")
                  dctx.write(s"store ${vTh.toValue}* $src, ${vTh.toValue}** $r1")
                case _ =>
                  dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* $slot, i64 0, i32 $idx")
                  dctx.write(s"store ${vTh.toValue} $src, ${vTh.toValue}* $r1")
              }
          }
        }

        val r1, r2, r3 = "%" + dctx.nextReg("")
        val irArgs = fth.args.map(th => AbiTh.toCallArg(mctx, th)) :+ "i8*"
        dctx.write(s"$r1 = insertvalue ${fth.toValue} undef, ${fth.ret.toValue} (${irArgs.mkString(", ")})* @$lambdaName, 0")
        dctx.write(s"$r2 = bitcast $closureTh* $slot to i8*")
        dctx.write(s"$r3 = insertvalue ${fth.toValue} $r1, i8* $r2, 1")

        EResult(r3, false, true)
      case And(lhs, rhs) => performAndOr(lhs, rhs, isAnd = true)
      case Or(lhs, rhs) => performAndOr(lhs, rhs, isAnd = false)
      case While(cond, _do) =>
        val brId = dctx.nextBranch("")
        val (condBr, blockBr, endBr) = (s"wCond$brId", s"wBlock$brId", s"end$brId")
        dctx.write(s"br label %$condBr")
        dctx.write(s"$condBr:")
        dctx.deeper(OtherKind, { dctx =>
          val condRes = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, cond), AsStoreSrc, Builtin.thBool, Builtin.thBool)
          val r1 = "%" + dctx.nextReg("")
          dctx.write(s"$r1 = icmp eq i8 ${condRes.value}, 1")
          dctx.write(s"br i1 $r1, label %$blockBr, label %$endBr")
        })
        dctx.write(s"$blockBr:")
        dctx.deeper(WhileKind(condBr, endBr), { dctx =>
          performBlock(mctx, dctx, _do)
          dctx.write(s"br label %$condBr")
        })
        dctx.write(s"$endBr:")
        EResult("__no__result", false, false)

      case Continue() =>
        // oops: instruction numbering magic in llvm ir?
        dctx.nextReg("")
        val wk = dctx.scope.kind.asInstanceOf[WhileKind]
        //FIXME: make freeSet free
        dctx.write(s"br label %${wk.condBr}")
        EResult("__not_result", false, false)
      case Break() =>
        // oops: instruction numbering magic in llvm ir?
        dctx.nextReg("")
        val wk = dctx.scope.kind.asInstanceOf[WhileKind]
        //FIXME: make freeSet free
        dctx.write(s"br label %${wk.endBr}")
        EResult("__not_result", false, false)
      case self@If(cond, _do, _else) =>
        val (doTh, elseTh) = self.getBranchTh
        val brId = dctx.nextBranch("")
        val (condBr, doBr, elseBr, endBr) = (s"if$brId", s"do$brId", s"else$brId", s"end$brId")

        dctx.write(s"br label %$condBr")
        dctx.write(s"$condBr:")

        dctx.deeper(OtherKind, { dctx =>
          val condRes = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, cond), AsStoreSrc, Builtin.thBool, Builtin.thBool)
          val r1 = "%" + dctx.nextReg("")
          dctx.write(s"$r1 = icmp eq i8 ${condRes.value}, 1")
          dctx.write(s"br i1 $r1, label %$doBr, label %$elseBr")
        })

        if (self.getTypeHint[TypeHint] == Builtin.thNil) {
          dctx.write(s"$doBr:")
          dctx.deeper(OtherKind, { dctx =>
            performBlock(mctx, dctx, _do)
            dctx.write(s"br label %$endBr")
          })

          dctx.write(s"$elseBr:")
          dctx.deeper(OtherKind, { dctx =>
            performBlock(mctx, dctx, _else)
            dctx.write(s"br label %$endBr")
          })

          dctx.write(s"$endBr:")
          EResult("__no_result__", false, false)
        } else if (doTh == elseTh) {
          dctx.write(s"$doBr:")
          val doRes = dctx.deeper(OtherKind, { dctx =>
            val (eth, res) = performBlock(mctx, dctx, _do)
            val sync = Abi.syncValue(mctx, dctx, res, AsStoreSrc, eth, eth)
            dctx.write(s"br label %$endBr")
            sync
          })

          dctx.write(s"$elseBr:")
          val elseRes = dctx.deeper(OtherKind, { dctx =>
            val (eth, res) = performBlock(mctx, dctx, _else)
            val sync = Abi.syncValue(mctx, dctx, res, AsStoreSrc, eth, eth)
            dctx.write(s"br label %$endBr")
            sync
          })

          val r = "%" + dctx.nextReg("")
          dctx.write(s"$endBr:")
          dctx.write(s"$r = phi ${AbiTh.toStoreSrc(mctx, doTh)} [${doRes.value}, %$doBr], [${elseRes.value}, %$elseBr]")
          val isPtr = doTh.classify(mctx.level, mctx.module) match {
            case RefUnion(_) => true
            case _ => false
          }
          EResult(r, isPtr, true)
        } else {
          val ifTh = self.getTypeHint[TypeHint]
          val slot = dctx.addSlot(ifTh)

          dctx.write(s"$doBr:")
          dctx.deeper(OtherKind, { dctx =>
            val (eth, res) = performBlock(mctx, dctx, _do)
            val sync = Abi.syncValue(mctx, dctx, res, AsStoreSrc, eth, eth)
            Abi.store(mctx, dctx, needDec = false, needInc = !sync.isAnon, ifTh, eth, slot, sync.value)
            dctx.write(s"br label %$endBr")
          })

          dctx.write(s"$elseBr:")
          dctx.deeper(OtherKind, { dctx =>
            val (eth, res) = performBlock(mctx, dctx, _else)
            val sync = Abi.syncValue(mctx, dctx, res, AsStoreSrc, eth, eth)
            Abi.store(mctx, dctx, needDec = false, needInc = !sync.isAnon, ifTh, eth, slot, sync.value)
            dctx.write(s"br label %$endBr")
          })

          dctx.write(s"$endBr:")
          EResult(slot, true, true)
        }
      //      case Unless(exp, isSeq) =>
      //        val expTh = exp.getTypeHint[TypeHint]
      //        val expRes = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, exp), AsStoreSrc, expTh, expTh)
      //
      //        if(expTh == Builtin.thNil) {
      //
      //        } else if() {
      //
      //        }
    }
  }

  def performBlock(mctx: ModContext, dctx: DContext, seq: Seq[Expression]): (TypeHint, EResult) = {
    seq.dropRight(1).foreach(expr => passExpr(mctx, dctx, expr))

    seq.lastOption.map(expr =>
      (expr.getTypeHint[TypeHint], passExpr(mctx, dctx, expr)))
      .getOrElse((Builtin.thNil, EResult("__no_result__", false, true)))
  }

  def passDef(mctx: ModContext, fn: Def, isClosure: Boolean): Unit = {
    val dctx = DContext(fn, isClosure)
    val fth = fn.getTypeHint[FnTh]
    val retTh = fth.ret

    val fnName =
      fn.lambda.args.headOption match {
        case Some(arg) if arg.name == "self" => (arg.typeHint + "." + fn.name).escaped
        case _ => fn.name
      }

    fth.args.foreach(th => mctx.typeHints += th)
    mctx.typeHints += retTh

    fn.lambda.body match {
      case llVm(code) => dctx.write(code)
      case AbraCode(seq) =>
        val (eth, eRes) = performBlock(mctx, dctx, seq)

        if (retTh == Builtin.thNil || retTh == Builtin.thUnreachable)
          dctx.write(s"ret void")
        else {
          val sRes = Abi.syncValue(mctx, dctx, eRes, AsRetVal, retTh, eth)
          dctx.write(s"ret ${AbiTh.toRetVal(mctx, retTh)} ${sRes.value}")
        }
    }

    mctx.defs.put(fnName, dctx)
  }

  def pass(root: Level, outConf: OutConf): Unit = {
    root.eachModule((level, module) => {
      outConf.withStream(module, { out =>

        val mctx = ModContext(out, level, module)

        module.defs.values.foreach(fn => passDef(mctx, fn, false))
        module.selfDefs.values.foreach(defs => defs.foreach(fn => passDef(mctx, fn, false)))

        mctx.write("declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)")
        mctx.write("@evaAlloc = external thread_local(initialexec) global i8* (i64)*")
        mctx.write("@evaInc   = external thread_local(initialexec) global void (i8*)*")
        mctx.write("@evaDec   = external thread_local(initialexec) global i64 (i8*)*")
        mctx.write("@evaFree  = external thread_local(initialexec) global void (i8*)*")

        mctx.module.lowCode.foreach { native =>
          mctx.write(native.code)
        }

        val typeHintOrder = ListBuffer[TypeHint]()
        mctx.typeHints.distinct.foreach(th => th.orderTypeHints(level, module, typeHintOrder))

        val typeDecl = ListBuffer[(TypeHint, String)]()
        typeHintOrder.distinct.foreach(th => th.toDecl(level, module, typeDecl))

        typeDecl.foreach { case (th, decl) => mctx.write(decl) }

        ConsUtils.genStringConstuctors(mctx)

        mctx.rcDef.foreach { case (_, code) => mctx.write(code.toString()) }

        mctx.defs.foreach { case (fnName, dctx) =>
          val fth = dctx.fn.getTypeHint[FnTh]
          val irArgs = dctx.fn.lambda.args.map(arg => s"${AbiTh.toCallArg(mctx, arg.typeHint)} %${arg.name}")
          val realArgs = if (dctx.isClosure) irArgs :+ "i8* %__env" else irArgs

          mctx.write(s"define ${AbiTh.toRetVal(mctx, fth.ret)} @$fnName (${realArgs.mkString(", ")}) {")

          if (dctx.isClosure)
            mctx.write(s"  %__closure = bitcast i8* %__env to ${AbiTh.toClosure(mctx, dctx.fn.lambda.getClosure)}*")

          dctx.vars.foreach { case (vName, th) =>
            mctx.write(s"  %$vName = alloca ${th.toValue}")
          }

          dctx.closureSlots.zipWithIndex.foreach { case (th, idx) =>
            mctx.write(s"  %__closure_slot${idx}__ = alloca $th")
          }

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