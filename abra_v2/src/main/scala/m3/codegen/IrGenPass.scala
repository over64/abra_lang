package m3.codegen

import java.io.PrintStream

import m3.codegen.IrUtils._
import m3.parse.Ast0._
import m3.parse.Level
import m3.typecheck.Utils.RichDef
import m3.typecheck.Utils.ThExtension
import m3.typecheck._
import TCMeta.TypeDeclTCMetaImplicit
import TCMeta.VarTypeTCMetaImplicit
import TCMeta.CallTypeTCMetaImplicit
import TCMeta.PolymorphicTCMetaImplicit
import TCMeta.DefTCMetaImplicit

import scala.collection.mutable
import scala.collection.mutable.{HashMap, ListBuffer}

trait OutConf {
  def withStream[T](module: Module, op: PrintStream => T): T
}

case class ModContext(out: PrintStream,
                      level: Level,
                      modules: Seq[Module],
                      typeHints: ListBuffer[TypeHint] = ListBuffer(),
                      rcDef: HashMap[(TypeHint, RCMode), StringBuilder] = HashMap(),
                      strings: ListBuffer[String] = ListBuffer(),
                      defs: HashMap[String, DContext] = HashMap(),
                      prototypes: mutable.ListBuffer[String] = ListBuffer()) {

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
                 freeSet: ListBuffer[(TypeHint, EResult)] = ListBuffer()) {

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

case class DContext(specMap: mutable.HashMap[GenericTh, TypeHint],
                    resolvedSelfDefs: mutable.HashMap[(TypeHint, String), (Module, Def)],
                    fn: Def,
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

  def specialized(th: TypeHint): TypeHint =
    th.spec(specMap)

  object meta {

    import TCMeta._

    def typeHintAs[T <: TypeHint](self: ParseNode): T = {
      specialized(self.getTypeHint[T]).asInstanceOf[T]
    }

    def typeHint(self: ParseNode): TypeHint = {
      specialized(self.getTypeHint[TypeHint])
    }

    def closure(self: Lambda) =
      self.getClosure.map { case (name, th, vt) => (name, specialized(th), vt) }

    //    def callAppliedTh(self: ParseNode) =
    //      specialized(self.getCallAppliedTh).asInstanceOf[FnTh]

    def storeDeclTh(self: Store) =
      self.getDeclTh[TypeHint].map(x => specialized(x))

    def ifBranchTh(self: If) = {
      val (a, b) = self.getBranchTh
      (specialized(a), specialized(b))
    }

    def unlessUncovered(self: Unless) =
      self.getUncovered.map(th => specialized(th))
  }
}

sealed trait RequireDest

case object AsStoreSrc extends RequireDest
case object AsCallArg extends RequireDest
case object AsRetVal extends RequireDest

case class EResult(value: String, isPtr: Boolean, isAnon: Boolean)

class IrGenPass {
  def performCall(mctx: ModContext, dctx: DContext, fth: FnTh, callArgs: Seq[Expression], envArg: Option[String], fName: String): EResult = {
    val argsResults =
      (fth.args zip callArgs).map { case (argTh, arg) =>
        Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, arg), AsCallArg, argTh, dctx.meta.typeHint(arg))
      }

    val argsIr = (fth.args zip argsResults).map { case (argTh, argRes) =>
      if (argRes.isAnon && argTh.isRefType(mctx))
        dctx.scope.freeSet += ((argTh, argRes))


      s"${AbiTh.toCallArg(mctx, argTh)} ${argRes.value}"
    }
    val argsWithEnvIr = envArg match {
      case None => argsIr
      case Some(env) => argsIr :+ s"i8* $env"
    }

    val res = if (fth.ret == Builtin.thNil || fth.ret == Builtin.thUnreachable) {
      dctx.write(s"call void $fName(${argsWithEnvIr.mkString(", ")})")
      EResult("__no_result__", false, true)
    } else {
      val r = "%" + dctx.nextReg("")
      dctx.write(s"$r = call ${AbiTh.toRetVal(mctx, fth.ret)} $fName(${argsWithEnvIr.mkString(", ")})")
      EResult(r, false, true)
    }

    res
  }


  def passExpr(mctx: ModContext, dctx: DContext, expr: Expression): EResult = {
    mctx.typeHints += dctx.meta.typeHint(expr)

    def performCons(cons: Expression, args: Seq[Expression]): EResult = {
      val th = dctx.meta.typeHint(cons)
      val irType = th.toValue(mctx)

      if (th.isValueType(mctx)) {
        val slot = dctx.addSlot(th)

        val (base, fieldsTh) = th.isIrArray(mctx) match {
          case Some(sd) =>
            val r = "%" + dctx.nextReg("")
            dctx.write(s"$r = getelementptr $irType, $irType* $slot, i64 0, i32 0")
            dctx.write(s"store i64 ${sd.getBuiltinArrayLen.get}, i64* $r")
            val elTh = th.asInstanceOf[ScalarTh].params.head
            ("i32 1, i64 ", (0 to args.length).map(_ => elTh))
          case None =>
            ("i32 ", th.structFields(mctx).map(_.typeHint))
        }

        for (((field, fieldTh), idx) <- (args zip fieldsTh).zipWithIndex) {
          val fRes = passExpr(mctx, dctx, field)

          val r = "%" + dctx.nextReg("")
          dctx.write(s"$r = getelementptr $irType, $irType* $slot, i64 0, $base$idx")

          val fResSync = Abi.syncValue(mctx, dctx, fRes, AsStoreSrc, fieldTh, fieldTh)
          Abi.store(mctx, dctx, false, false, fieldTh, dctx.meta.typeHint(field), r, fResSync.value)
        }

        EResult(slot, true, true)
      } else {
        val r0 = "%" + dctx.nextReg("")
        dctx.write(s"$r0 = load i8* (i64)*,  i8* (i64)** @evaAlloc")

        val (to, toIrType, base, fieldsTh) =
          th.isIrArray(mctx) match {
            case Some(sd) =>
              val elTh = th.asInstanceOf[ScalarTh].params.head
              val elIrType = elTh.toValue(mctx)
              val r1, r2, r3, r4, r5, r6 = "%" + dctx.nextReg("")

              dctx.write(s"$r1 = getelementptr $elIrType, $elIrType* null, i64 ${args.length}")
              dctx.write(s"$r2 = ptrtoint $elIrType* $r1 to i64")
              dctx.write(s"$r3 = add i64 $r2, 8")
              dctx.write(s"$r4 = call i8* $r0(i64 $r3)")
              dctx.write(s"$r5 = getelementptr i8, i8* $r4, i64 8")
              dctx.write(s"$r6 = bitcast i8* $r5 to $elIrType*")

              (r6, elIrType, "", (0 to args.length).map(i => elTh))
            case None =>
              val dataIrType = th.toValue(mctx, suffix = ".body")
              val r1, r2, r3, r4 = "%" + dctx.nextReg("")

              dctx.write(s"$r1 = getelementptr $dataIrType, $dataIrType* null, i64 1")
              dctx.write(s"$r2 = ptrtoint $dataIrType* $r1 to i64")
              dctx.write(s"$r3 = call i8* $r0(i64 $r2)")
              dctx.write(s"$r4 = bitcast i8* $r3 to $irType")

              (r4, dataIrType, "i64 0, ", th.structFields(mctx).map(_.typeHint))
          }

        (args zip fieldsTh).zipWithIndex.foreach { case ((field, fieldTh), idx) =>
          val fRes = passExpr(mctx, dctx, field)
          val fieldDestPtr = "%" + dctx.nextReg("")
          dctx.write(s"$fieldDestPtr = getelementptr $toIrType, $toIrType* $to, ${base}i32 $idx")
          Abi.store(mctx, dctx, false, !fRes.isAnon, fieldTh, dctx.meta.typeHint(field), fieldDestPtr, fRes.value)
        }
        EResult(to, false, true)
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

    case class Branch(beginLabel: String, before: DContext => Unit,
                      th: TypeHint, seq: Seq[Expression])

    def performBranches(resultTh: TypeHint, branches: Seq[Branch], endLabel: String): EResult = {
      val branchesTh = branches.map(_.th)

      if (resultTh == Builtin.thNil) {
        branches.foreach { br =>
          dctx.write(br.beginLabel + ":")
          br.before(dctx)
          dctx.deeper(OtherKind, { dctx =>
            val (isTerm, _, _) = performBlock(mctx, dctx, br.seq)
            if (!isTerm) dctx.write(s"br label %$endLabel")
          })
        }

        dctx.write(endLabel + ":")
        EResult("__no_result__", false, false)
      } else if (branchesTh.toSet.size == 1) {
        val results = branches.map { br =>
          dctx.write(br.beginLabel + ":")
          dctx.deeper(OtherKind, { dctx =>
            br.before(dctx)
            val (isTerm, eth, res) = performBlock(mctx, dctx, br.seq)
            val sync = Abi.syncValue(mctx, dctx, res, AsStoreSrc, eth, eth)
            if (!isTerm) dctx.write(s"br label %$endLabel")
            sync
          })
        }

        dctx.write(endLabel + ":")
        val r = "%" + dctx.nextReg("")
        dctx.write(s"$r = phi ${AbiTh.toStoreSrc(mctx, resultTh)} ")
        (branches zip results).zipWithIndex.foreach { case ((br, res), idx) =>
          dctx.write(s"  [${res.value}, %${br.beginLabel}]${if (idx != branches.length - 1) "," else ""}")
        }

        val isPtr = resultTh.classify(mctx) match {
          case RefUnion(_) => true
          case _ => false
        }

        EResult(r, isPtr, true)
      } else {
        val slot = dctx.addSlot(resultTh)
        branches.foreach { br =>
          dctx.write(br.beginLabel + ":")
          dctx.deeper(OtherKind, { dctx =>
            br.before(dctx)
            val (isTerm, eth, res) = performBlock(mctx, dctx, br.seq)
            val sync = Abi.syncValue(mctx, dctx, res, AsStoreSrc, eth, eth)
            Abi.store(mctx, dctx, needDec = false, needInc = !sync.isAnon, resultTh, eth, slot, sync.value)
            if (!isTerm) dctx.write(s"br label %$endLabel")
          })
        }

        dctx.write(endLabel + ":")
        EResult(slot, true, true)
      }
    }

    expr match {
      case lInt(value) => EResult(value, false, true)
      case lit@lFloat(value) =>
        val striped =
          if (dctx.meta.typeHint(lit) == Builtin.thFloat)
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
            dctx.meta.typeHint(id).classify(mctx) match {
              case RefUnion(_) | ValueUnion(_) | ValueStruct(_) =>
                EResult("%" + id.value, true, false)
              case _ =>
                EResult("%" + id.value, false, false)
            }
          case VarClosureLocal(_) =>
            val closure = dctx.meta.closure(dctx.fn.lambda)
            val closureTh = AbiTh.toClosure(mctx, closure)
            val ((_, idTh, vt), idx) = closure.zipWithIndex.find { case ((name, _, _), _) => name == id.value }.get

            val r1, r2 = "%" + dctx.nextReg("")
            dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* %__closure, i64 0, i32 $idx")
            dctx.write(s"$r2 = load ${idTh.toValue(mctx)}*, ${idTh.toValue(mctx)}** $r1")
            EResult(r2, true, false)
          case VarClosureParam(_) =>
            val closure = dctx.meta.closure(dctx.fn.lambda)
            val closureTh = AbiTh.toClosure(mctx, closure)
            val ((_, idTh, vt), idx) = closure.zipWithIndex.find { case ((name, _, _), _) => name == id.value }.get

            val r1, r2 = "%" + dctx.nextReg("")

            idTh.classify(mctx) match {
              case RefUnion(_) | ValueUnion(_) | ValueStruct(_) =>
                dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* %__closure, i64 0, i32 $idx")
                dctx.write(s"$r2 = load ${idTh.toValue(mctx)}*, ${idTh.toValue(mctx)}** $r1")
                EResult(r2, true, false)
              case _ =>
                dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* %__closure, i64 0, i32 $idx")
                dctx.write(s"$r2 = load ${idTh.toValue(mctx)}, ${idTh.toValue(mctx)}* $r1")
                EResult(r2, false, false)
            }
        }
      case t@Tuple(seq) => performCons(t, seq)
      case cons@Cons(sth, args) => performCons(cons, args)
      case call@Call(e, args) =>
        val callSpecMap = {
          import TCMeta.ParseNodeTCMetaImplicit
          call.getCallSpecMap
        }

        val (fName, callArgs, envArg, fth) =
          call.getCallType match {
            case CallLocal(fn) =>
              var x = 1
              val mappedSpecMap = callSpecMap.map {
                case (k, v) => (k, v.spec(dctx.specMap))
              }
              val callAppliedTh = {
                import TCMeta.ParseNodeTCMetaImplicit
                fn.getTypeHint[TypeHint].spec(mappedSpecMap).asInstanceOf[FnTh]
              }
              val id = e.asInstanceOf[lId]
              if (fn.isGeneric) {
                var x = 1
                val mappedResolvedSelfDefs =
                  mutable.HashMap(fn.getEquations.eqSeq.map { eq =>
                    val d = call.getResolvedSelfDefs().getOrElse((eq.selfType._2, eq.fnName), {
                      dctx.resolvedSelfDefs((eq.selfType._2.spec(callSpecMap), eq.fnName))
                    })
                    ((eq.selfType._2.asInstanceOf[TypeHint], eq.fnName), d)
                  }: _*)

                passDef(mctx, DContext(mappedSpecMap, mappedResolvedSelfDefs, fn, false))

                val genericArgs = "[" + fn.params.map(p => p.spec(mappedSpecMap)).mkString(", ") + "]"
                ("@" + (mctx.modules.head.pkg + "." + id.value + genericArgs).escaped, args, None, callAppliedTh)
              } else
                ("@" + (mctx.modules.head.pkg + "." + id.value).escaped, args, None, callAppliedTh)
            case SelfCallLocal(fn) =>
              val callAppliedTh = {
                import TCMeta.ParseNodeTCMetaImplicit
                fn.getTypeHint[TypeHint].spec(callSpecMap).asInstanceOf[FnTh]
              }


              val fnTh = {
                import TCMeta.ParseNodeTCMetaImplicit
                fn.getTypeHint[FnTh]
              }

              if (fn.isGeneric) {
                passDef(mctx, DContext(callSpecMap, call.getResolvedSelfDefs(), fn, false))

                val genericArgs = "[" + fn.params.map(p => p.spec(callSpecMap)).mkString(", ") + "]"
                ("@" + (mctx.modules.head.pkg + "." + fnTh.args.head + ".get" + genericArgs).escaped, e +: args, None, callAppliedTh)
              } else
                ("@" + (mctx.modules.head.pkg + "." + fnTh.args.head + ".get").escaped, e +: args, None, callAppliedTh)
            case CallFnPtr =>
              val fnPtrRes = passExpr(mctx, dctx, e)
              val fth = dctx.meta.typeHintAs[FnTh](e)
              val sync = Abi.syncValue(mctx, dctx, fnPtrRes, AsRetVal, fth, fth)

              val r1, r2 = "%" + dctx.nextReg("")
              dctx.write(s"$r1 = extractvalue ${fth.toValue(mctx)} ${sync.value}, 0")
              dctx.write(s"$r2 = extractvalue ${fth.toValue(mctx)} ${sync.value}, 1")
              (r1, args, Some(r2), fth)
          }

        performCall(mctx, dctx, fth, callArgs, envArg, fName)
      case call@SelfCall(fnName, self, args) =>
        call.getCallType match {
          case CallFnPtr =>
            val objRes = passExpr(mctx, dctx, self)
            val (fth, fnPtrRes) = Abi.getProperty(mctx, dctx, dctx.meta.typeHint(self), objRes, Seq(lId(fnName)))
            val sync = Abi.syncValue(mctx, dctx, fnPtrRes, AsRetVal, fth, fth)

            val r1, r2 = "%" + dctx.nextReg("")
            dctx.write(s"$r1 = extractvalue ${fth.toValue(mctx)} ${sync.value}, 0")
            dctx.write(s"$r2 = extractvalue ${fth.toValue(mctx)} ${sync.value}, 1")
            performCall(mctx, dctx, fth.asInstanceOf[FnTh], args, Some(r2), r1)
          case CallImport(module, fn) =>
            val callSpecMap = {
              import TCMeta.ParseNodeTCMetaImplicit
              call.getCallSpecMap
            }

            val mappedSpecMap = callSpecMap.map {
              case (k, v) => (k, v.spec(dctx.specMap))
            }
            val callAppliedTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              fn.getTypeHint[TypeHint].spec(mappedSpecMap).asInstanceOf[FnTh]
            }

            val isGeneric = fn.params.nonEmpty
            val genericArgs = if (isGeneric) "[" + fn.params.map(p => p.spec(callSpecMap)).mkString(", ") + "]" else ""
            val protoName = "@" + (module.pkg + "." + fnName + genericArgs).escaped
            val irArgs = callAppliedTh.args.map(ath => AbiTh.toCallArg(mctx, dctx.specialized(ath)))

            if (isGeneric) {
              val mappedResolvedSelfDefs =
                mutable.HashMap(fn.getEquations.eqSeq.map { eq =>
                  val d = call.getResolvedSelfDefs().getOrElse((eq.selfType._2, eq.fnName), {
                    dctx.resolvedSelfDefs((eq.selfType._2.spec(callSpecMap), eq.fnName))
                  })
                  ((eq.selfType._2.asInstanceOf[TypeHint], eq.fnName), d)
                }: _*)

              passDef(mctx.copy(modules = module +: mctx.modules), DContext(mappedSpecMap, mappedResolvedSelfDefs, fn, false))
            } else
              mctx.prototypes += s"${AbiTh.toRetVal(mctx, callAppliedTh.ret)} $protoName (${irArgs.mkString(", ")})"

            performCall(mctx, dctx, callAppliedTh, args, None, protoName)
          case SelfCallLocal(fn) =>
            val callSpecMap = {
              import TCMeta.ParseNodeTCMetaImplicit
              call.getCallSpecMap
            }
            val mappedSpecMap = callSpecMap.map {
              case (k, v) => (k, v.spec(dctx.specMap))
            }
            val callAppliedTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              fn.getTypeHint[TypeHint].spec(mappedSpecMap).asInstanceOf[FnTh]
            }

            val fnTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              fn.getTypeHint[FnTh]
            }

            val isGeneric = fn.params.nonEmpty
            val genericArgs = if (isGeneric) "[" + fn.params.map(p => p.spec(callSpecMap)).mkString(", ") + "]" else ""
            val selfFnName = "@" + (mctx.modules.head.pkg + "." + fnTh.args.head + "." + fnName + genericArgs).escaped

            if (isGeneric) {
              val mappedResolvedSelfDefs =
                mutable.HashMap(fn.getEquations.eqSeq.map { eq =>
                  val d = call.getResolvedSelfDefs().getOrElse((eq.selfType._2, eq.fnName), {
                    dctx.resolvedSelfDefs((eq.selfType._2.spec(callSpecMap), eq.fnName))
                  })
                  ((eq.selfType._2.asInstanceOf[TypeHint], eq.fnName), d)
                }: _*)

              passDef(mctx, DContext(mappedSpecMap, mappedResolvedSelfDefs, fn, false))
            } else if (mctx.modules.length != 1) {
              val irArgs = callAppliedTh.args.map(ath => AbiTh.toCallArg(mctx, dctx.specialized(ath)))
              mctx.prototypes += s"${AbiTh.toRetVal(mctx, callAppliedTh.ret)} $selfFnName (${irArgs.mkString(", ")})"
            }

            performCall(mctx, dctx, callAppliedTh, self +: args, None, selfFnName)
          case SelfCallPolymorphic(callAppliedTh) =>
            val selfTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              self.getTypeHint[TypeHint]
            }

            val (mod, fn) = dctx.resolvedSelfDefs((selfTh, fnName))
            val fnTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              fn.getTypeHint[FnTh]
            }

            val isGeneric = fn.params.nonEmpty
            if (isGeneric)
              passDef(mctx.copy(modules = Seq(mod)), DContext(dctx.specMap, dctx.resolvedSelfDefs, fn, false))

            val genericArgs = if (isGeneric) "[" + fn.params.map(p => p.spec(dctx.specMap)).mkString(", ") + "]" else ""
            val selfFnName = "@" + (mod.pkg + "." + fnTh.args.head + "." + fnName + genericArgs).escaped
            performCall(mctx.copy(modules = Seq(mctx.modules.last)), dctx, callAppliedTh.spec(dctx.specMap).asInstanceOf[FnTh], self +: args, None, selfFnName)
          case SelfCallImport(module, fn) =>
            val callSpecMap = {
              import TCMeta.ParseNodeTCMetaImplicit
              call.getCallSpecMap
            }
            val callAppliedTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              fn.getTypeHint[TypeHint].spec(callSpecMap).asInstanceOf[FnTh]
            }

            val fnTh = {
              import TCMeta.ParseNodeTCMetaImplicit
              fn.getTypeHint[FnTh]
            }

            val isGeneric = fn.params.nonEmpty
            val genericArgs = if (isGeneric) "[" + fn.params.map(p => p.spec(callSpecMap)).mkString(", ") + "]" else ""
            val selfFnName = "@" + (module.pkg + "." + fnTh.args.head + "." + fnName + genericArgs).escaped

            if (isGeneric)
              passDef(mctx.copy(modules = module +: mctx.modules), DContext(callSpecMap, call.getResolvedSelfDefs(), fn, false))
            else {
              val irArgs = callAppliedTh.args.map(ath => AbiTh.toCallArg(mctx, dctx.specialized(ath)))
              mctx.prototypes += s"${AbiTh.toRetVal(mctx, callAppliedTh.ret)} $selfFnName (${irArgs.mkString(", ")})"
            }

            performCall(mctx, dctx, callAppliedTh, self +: args, None, selfFnName)
        }
      case Prop(from, props) =>
        val res = passExpr(mctx, dctx, from)
        Abi.getProperty(mctx, dctx, dctx.meta.typeHint(from), res, props)._2
      case store@Store(_, to, what) =>
        var x = 1
        val (destTh, dRes) =
          dctx.meta.storeDeclTh(store) match {
            case Some(newVarTh) =>
              val vName = to.head.value
              val alias = dctx.addSymbol(vName)
              dctx.vars.put(alias, newVarTh)
              dctx.scope.aliases.put(vName, alias)
              dctx.scope.freeSet += ((newVarTh, EResult("%" + alias, true, false)))

              (dctx.meta.typeHint(to.head), EResult("%" + alias, true, true))
            case None =>
              val toRes = passExpr(mctx, dctx, to.head)
              if (to.length == 1)
                (dctx.meta.typeHint(to.head), toRes)
              else
                Abi.getProperty(mctx, dctx, dctx.meta.typeHint(to.head), toRes, to.drop(1))
          }

        val whatTh = dctx.meta.typeHint(what)
        val wRes = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, what),
          AsStoreSrc, whatTh, whatTh)
        Abi.store(mctx, dctx, !dRes.isAnon, !wRes.isAnon, destTh, whatTh, dRes.value, wRes.value)
        EResult("__no_result__", false, true)
      case lambda: Lambda =>
        val fth = dctx.meta.typeHintAs[FnTh](lambda)
        val lambdaName = dctx.nextLambdaName()
        val fn = {
          import TCMeta.ParseNodeTCMetaImplicit
          val d = Def(lambdaName, lambda, fth.ret)
          d.setTypeHint(fth)
          d
        }

        passDef(mctx, DContext(dctx.specMap, dctx.resolvedSelfDefs, fn, true))

        val closure = dctx.meta.closure(lambda)
        val closureTh = AbiTh.toClosure(mctx, closure)
        val slot = dctx.addClosureSlot(closureTh)

        closure.zipWithIndex.foreach { case ((vName, vTh, vt), idx) =>
          vt match {
            case VarClosureLocal(nested) =>
              val ptr =
                if (nested) {
                  val nestedId = lId(vName)
                  nestedId.setVarLocation(VarClosureLocal(false))
                  val _ = {
                    import TCMeta.ParseNodeTCMetaImplicit
                    nestedId.setTypeHint(vTh)
                  }
                  passExpr(mctx, dctx, nestedId).value
                } else
                  "%" + vName

              val r1 = "%" + dctx.nextReg("")
              dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* $slot, i64 0, i32 $idx")
              dctx.write(s"store ${vTh.toValue(mctx)}* $ptr, ${vTh.toValue(mctx)}** $r1")
            case VarClosureParam(nested) =>
              val src =
                if (nested) {
                  val nestedId = lId(vName)
                  nestedId.setVarLocation(VarClosureParam(false))
                  val _ = {
                    import TCMeta.ParseNodeTCMetaImplicit
                    nestedId.setTypeHint(vTh)
                  }
                  passExpr(mctx, dctx, nestedId).value
                } else
                  "%" + vName

              val r1 = "%" + dctx.nextReg("")
              vTh.classify(mctx) match {
                case RefUnion(_) | ValueUnion(_) | ValueStruct(_) =>
                  dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* $slot, i64 0, i32 $idx")
                  dctx.write(s"store ${vTh.toValue(mctx)}* $src, ${vTh.toValue(mctx)}** $r1")
                case _ =>
                  dctx.write(s"$r1 = getelementptr $closureTh, $closureTh* $slot, i64 0, i32 $idx")
                  dctx.write(s"store ${vTh.toValue(mctx)} $src, ${vTh.toValue(mctx)}* $r1")
              }
          }
        }

        val r1, r2, r3 = "%" + dctx.nextReg("")
        val irArgs = fth.args.map(th => AbiTh.toCallArg(mctx, th)) :+ "i8*"
        dctx.write(s"$r1 = insertvalue ${fth.toValue(mctx)} undef, ${fth.ret.toValue(mctx)} (${irArgs.mkString(", ")})* @$lambdaName, 0")
        dctx.write(s"$r2 = bitcast $closureTh* $slot to i8*")
        dctx.write(s"$r3 = insertvalue ${fth.toValue(mctx)} $r1, i8* $r2, 1")

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
        val ifTh = dctx.meta.typeHint(self)
        val (doTh, elseTh) = dctx.meta.ifBranchTh(self)
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

        performBranches(ifTh, Seq(
          Branch(doBr, { _ => }, doTh, _do),
          Branch(elseBr, { _ => }, elseTh, _else)
        ), endBr)
      case self@Unless(exp, isSeq) =>
        val unlessTh = dctx.meta.typeHint(self)
        val expTh = dctx.meta.typeHint(exp)
        val brId = dctx.nextBranch("")
        val (unlessBr, endBr) = (s"unless$brId", s"end$brId")

        dctx.write(s"br label %$unlessBr")
        dctx.write(s"$unlessBr:")
        val (expRes, branches) = dctx.deeper(OtherKind, { dctx =>
          val res = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, exp), AsCallArg, expTh, expTh)

          val matched = isSeq.zipWithIndex.map { case (is, idx) =>
            val isTypeRef = dctx.specialized(is.typeRef)
            Branch(s"unless${brId}_matched$idx", dctx => {
              is.vName.foreach { vName =>
                val alias = dctx.addSymbol(vName.value)
                dctx.scope.aliases.put(vName.value, alias)
                val r = "%" + dctx.nextReg("")
                dctx.write(s"$r =  bitcast ${expTh.toValue(mctx)}* ${res.value} to {i64, ${isTypeRef.toValue(mctx)}}*")
                dctx.write(s"%$alias =  getelementptr {i64, ${isTypeRef.toValue(mctx)}}, {i64, ${isTypeRef.toValue(mctx)}}* $r, i64 0, i32 1")
              }
            }, isTypeRef, is._do)
          }

          val unmatched = dctx.meta.unlessUncovered(self).zipWithIndex.map { case (th, idx) =>
            val id = lId("unmatched")
            val _ = {
              import TCMeta.ParseNodeTCMetaImplicit
              id.setTypeHint(th)
            }
            id.setVarLocation(VarLocal)

            Branch(s"unless${brId}_unmatched$idx", dctx => {
              val alias = dctx.addSymbol("unmatched")
              dctx.scope.aliases.put("unmatched", alias)
              val r = "%" + dctx.nextReg("")
              dctx.write(s"$r =  bitcast ${expTh.toValue(mctx)}* ${res.value} to {i64, ${th.toValue(mctx)}}*")
              dctx.write(s"%$alias =  getelementptr {i64, ${th.toValue(mctx)}}, {i64, ${th.toValue(mctx)}}* $r, i64 0, i32 1")
            }, th, Seq(id))
          }

          val r1, r2 = "%" + dctx.nextReg("")
          dctx.write(s"$r1 = getelementptr ${expTh.toValue(mctx)}, ${expTh.toValue(mctx)}* ${res.value}, i64 0, i32 0")
          dctx.write(s"$r2 = load i64, i64* $r1")
          dctx.write(s"switch i64 $r2, label %$endBr [")
          expTh.asUnion(mctx).zipWithIndex.foreach { case (variant, idx) =>
            val branch = matched.find(br => br.th == variant)
              .getOrElse(unmatched.find(br => br.th == variant).get)
            dctx.write(s"  i64 $idx, label %${branch.beginLabel}")
          }
          dctx.write(s"]")
          (res, matched ++ unmatched)
        })

        performBranches(unlessTh, branches, endBr)
      case ret@Ret(exprOpt) =>
        exprOpt match {
          case None =>
            dctx.write("ret void")
            dctx.nextBranch("")
          case Some(expr) =>
            val eRes = passExpr(mctx, dctx, expr)
            val eTh = dctx.meta.typeHint(expr)
            val fnRetTh = dctx.meta.typeHintAs[FnTh](dctx.fn).ret
            val sync = Abi.syncValue(mctx, dctx, eRes, AsRetVal, fnRetTh, eTh)
            dctx.write(s"ret ${fnRetTh.toValue(mctx)} ${sync.value}")
            dctx.nextBranch("")
        }
        EResult("__no__result", false, false)
    }
  }

  def performBlock(mctx: ModContext, dctx: DContext, seq: Seq[Expression]): (Boolean, TypeHint, EResult) = {
    val isTerm = seq.lastOption.map(e => e.isInstanceOf[Ret]).getOrElse(false)

    seq.dropRight(1).foreach { expr =>
      val eth = dctx.meta.typeHint(expr)
      val res = passExpr(mctx, dctx, expr)

      if (res.isAnon && eth.isRefType(mctx))
        dctx.scope.freeSet += ((eth, res))
    }

    val (eth, eRes) = seq.lastOption.map { expr =>
      val retTh = dctx.meta.typeHint(expr)
      val retRes = passExpr(mctx, dctx, expr)

      // if fn arg
      if (!retRes.isAnon && !dctx.scope.freeSet.exists { case (_, res) => res.value == retRes.value })
        RC.doRC(mctx, dctx, Inc, retTh, retRes.isPtr, retRes.value)

      (retTh, retRes)
    }.getOrElse((Builtin.thNil, EResult("__no_result__", false, true)))

    dctx.scope.freeSet.foreach { case (freeTh, freeRes) =>
      if (freeRes.value != eRes.value) {
        RC.doRC(mctx, dctx, Dec, freeTh, freeRes.isPtr, freeRes.value)
      }
    }

    (isTerm, eth, eRes)
  }

  def passDef(mctx: ModContext, dctx: DContext): Unit = {
    val fth = dctx.meta.typeHintAs[FnTh](dctx.fn)
    val retTh = fth.ret

    val params = dctx.fn.params
    val irParams = if (params.isEmpty) "" else params.map(p => p.spec(dctx.specMap)).mkString("[", ", ", "]")

    val fnName =
      dctx.fn.lambda.args.headOption match {
        case Some(arg) if arg.name == "self" => (mctx.modules.head.pkg + "." + arg.typeHint + "." + dctx.fn.name + irParams).escaped
        case _ =>
          if (dctx.fn.name == "main") "main"
          else if (dctx.isClosure) (dctx.fn.name + irParams).escaped
          else (mctx.modules.head.pkg + "." + dctx.fn.name + irParams).escaped
      }

    if (mctx.defs.contains(fnName)) return

    fth.args.foreach(th => mctx.typeHints += th)
    mctx.typeHints += retTh

    dctx.fn.lambda.body match {
      case llVm(code) =>

        code.split("\n").map(line => line.trim).foreach { line =>
          if (line.startsWith(";meta ")) {
            val cmd = line.stripPrefix(";meta ")

            if (cmd.startsWith("rc_")) {
              val pattern = """rc_(inc|dec)\[([a-zA-Z0-9]+)\]\(([a-zA-Z0-9]+)\)""".r
              cmd match {
                case pattern(incOrDec, typeName, symName) =>
                  val mode = if (incOrDec == "inc") Inc else Dec
                  val th = dctx.specMap(GenericTh(typeName, false))
                  RC.doRC(mctx, dctx, mode, th, false, "%" + symName)

              }
            }
          } else {
            var r = dctx.fn.params.foldLeft(line) { case (l, gth) =>
              try {
                l.replace("$" + gth, dctx.specMap(gth).toValue(mctx))
              } catch {
                case ex: NoSuchElementException =>
                  var x = 1
                  throw ex
              }
            }

            r = r.replace("$retTypeof()", dctx.specialized(dctx.fn.retTh).toValue(mctx))
            dctx.write(r)
          }

        }
      case AbraCode(seq) =>
        val (isTerm, eth, eRes) = performBlock(mctx, dctx, seq)

        if (retTh == Builtin.thNil || retTh == Builtin.thUnreachable) {
          if (!isTerm)
            dctx.write(s"ret void")
        } else {
          if (!isTerm) {
            val sRes = Abi.syncValue(mctx, dctx, eRes, AsRetVal, retTh, eth)
            dctx.write(s"ret ${AbiTh.toRetVal(mctx, retTh)} ${sRes.value}")
          }
        }
    }

    mctx.defs.put(fnName, dctx)
  }

  def pass(root: Level, outConf: OutConf): Unit = {
    root.eachModule((level, module) => {
      outConf.withStream(module, { out =>

        val mctx = ModContext(out, level, Seq(module))

        module.defs.values.filter(d => !d.isGeneric)
          .foreach(fn => passDef(mctx, DContext(mutable.HashMap.empty, mutable.HashMap.empty, fn, false)))

        module.selfDefs.values.foreach(defs =>
          defs.filter(d => !d.isGeneric).
            foreach(fn => passDef(mctx, DContext(mutable.HashMap.empty, mutable.HashMap.empty, fn, false))))

        mctx.write("declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)")
        mctx.write("@evaAlloc = external thread_local(initialexec) global i8* (i64)*")
        mctx.write("@evaInc   = external thread_local(initialexec) global void (i8*)*")
        mctx.write("@evaDec   = external thread_local(initialexec) global i64 (i8*)*")
        mctx.write("@evaFree  = external thread_local(initialexec) global void (i8*)*")

        mctx.modules.head.lowCode.foreach { native =>
          mctx.write(native.code)
        }

        val typeHintOrder = ListBuffer[TypeHint]()
        mctx.typeHints.distinct.foreach(th => th.orderTypeHints(level, module, typeHintOrder))

        val typeDecl = ListBuffer[(TypeHint, String)]()
        typeHintOrder.distinct.foreach(th => th.toDecl(mctx, typeDecl))

        typeDecl.foreach { case (th, decl) => mctx.write(decl) }

        ConsUtils.genStringConstuctors(mctx)

        mctx.rcDef.foreach { case (_, code) => mctx.write(code.toString()) }

        mctx.prototypes.distinct.foreach { proto => mctx.write(s"declare $proto") }

        mctx.defs.foreach { case (fnName, dctx) =>
          val fth = dctx.meta.typeHintAs[FnTh](dctx.fn)
          val irArgs = (fth.args zip dctx.fn.lambda.args).map {
            case (ath, arg) => s"${AbiTh.toCallArg(mctx, dctx.specialized(ath))} %${arg.name}"
          }
          val realArgs = if (dctx.isClosure) irArgs :+ "i8* %__env" else irArgs
          val define = if (dctx.isClosure) "define private" else "define"

          mctx.write(s"$define ${AbiTh.toRetVal(mctx, fth.ret)} @$fnName (${realArgs.mkString(", ")}) {")

          if (dctx.isClosure)
            mctx.write(s"  %__closure = bitcast i8* %__env to ${AbiTh.toClosure(mctx, dctx.meta.closure(dctx.fn.lambda))}*")

          dctx.vars.foreach { case (vName, th) =>
            mctx.write(s"  %$vName = alloca ${th.toValue(mctx)}")
          }

          dctx.closureSlots.zipWithIndex.foreach { case (th, idx) =>
            mctx.write(s"  %__closure_slot${idx}__ = alloca $th")
          }

          dctx.slots.zipWithIndex.foreach { case (slotTh, idx) =>
            mctx.write(s"  %__stack_slot${idx}__ = alloca ${slotTh.toValue(mctx)}")
          }

          dctx.code.foreach(line => mctx.write(line))
          mctx.write("}")
        }
      })
    })
  }
}