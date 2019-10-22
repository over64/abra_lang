package m3._03codegen

import java.io.PrintStream

import m3._03codegen.IrUtils._
import m3.Ast0._
import m3.{Builtin, Level, ThUtil, TypeInfer}
import m3._02typecheck.TCMeta.{CallTypeTCMetaImplicit, PolymorphicTCMetaImplicit, VarTypeTCMetaImplicit}
import m3._02typecheck.Utils.RichDef
import m3._02typecheck._

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, HashMap}

trait OutConf {
  def withStream[T](module: Module, op: PrintStream => T): T
}

case class ModContext(out: PrintStream,
                      level: Level,
                      modules: Seq[Module],
                      typeHints: ArrayBuffer[TypeHint] = ArrayBuffer(),
                      rcDef: HashMap[(TypeHint, RCMode), StringBuilder] = HashMap(),
                      strings: ArrayBuffer[HexUtil.EncodeResult] = ArrayBuffer(),
                      defs: HashMap[String, DContext] = HashMap(),
                      prototypes: ArrayBuffer[String] = ArrayBuffer()) {

  def write(line: String): Unit =
    out.println(line)

  def addString(str: String): (Int, Int) = {
    val encoded = HexUtil.singleByteNullTerminated(str.getBytes())
    strings += encoded
    (strings.length - 1, encoded.bytesLen)
  }
}

sealed trait ScopeKind
case object OtherKind extends ScopeKind
case class WhileKind(condBr: String, endBr: String) extends ScopeKind

case class Scope(parent: Option[Scope],
                 kind: ScopeKind,
                 aliases: HashMap[String, String] = HashMap(),
                 freeSet: ArrayBuffer[(TypeHint, EResult)] = ArrayBuffer()) {

  def findWhile(): WhileKind = {
    kind match {
      case wk: WhileKind => wk
      case _ =>
        parent match {
          case None => throw new RuntimeException("cannot find while scope")
          case Some(p) => p.findWhile()
        }
    }
  }

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
                    closureSlots: ArrayBuffer[String] = ArrayBuffer(),
                    slots: ArrayBuffer[TypeHint] = ArrayBuffer(),
                    var scope: Scope = Scope(None, OtherKind),
                    code: ArrayBuffer[String] = ArrayBuffer(),
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

  /* на данном уровне мы не должны делать специализаций типов? */
  def specialized(th: TypeHint): TypeHint =
    ThUtil.spec(th, specMap)

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

case class EResult(value: String, isPtr: Boolean, isAnon: Boolean)

class Pass {
  def passExpr(mctx: ModContext, dctx: DContext, expr: Expression): EResult = {
    mctx.typeHints += dctx.meta.typeHint(expr)

    def performCons(cons: Expression, args: Seq[Expression]): EResult = {
      val th = dctx.meta.typeHint(cons)
      val irType = th.toValue(mctx)

      if (th.isValueType(mctx)) {
        val slot = dctx.addSlot(th)
        val fieldsTh = th.structFields(mctx).map(_.typeHint)

        for (((field, fieldTh), idx) <- (args zip fieldsTh).zipWithIndex) {
          val fRes = passExpr(mctx, dctx, field)

          val r = "%" + dctx.nextReg("")
          dctx.write(s"$r = getelementptr $irType, $irType* $slot, i64 0, i32 $idx")

          val fResSync = Abi.syncValue(mctx, dctx, fRes, AsStoreSrc, fieldTh, fieldTh)
          Abi.store(mctx, dctx, false, false, fieldTh, dctx.meta.typeHint(field), r, fResSync.value)
        }

        EResult(slot, true, true)
      } else {
        dctx.needAlloc = true
        val toIrType = th.toValue(mctx, suffix = ".body")
        val r1, r2, r3, to = "%" + dctx.nextReg("")

        dctx.write(s"$r1 = getelementptr $toIrType, $toIrType* null, i64 1")
        dctx.write(s"$r2 = ptrtoint $toIrType* $r1 to i64")
        dctx.write(s"$r3 = call i8* %__alloc(i64 $r2)")
        dctx.write(s"$to = bitcast i8* $r3 to $irType")

        val fieldsTh = th.structFields(mctx).map(_.typeHint)

        (args zip fieldsTh).zipWithIndex.foreach { case ((field, fieldTh), idx) =>
          val fRes = passExpr(mctx, dctx, field)
          val feth = dctx.meta.typeHint(field)
          val syncfRes = Abi.syncValue(mctx, dctx, fRes, AsStoreSrc, feth, feth)
          val fieldDestPtr = "%" + dctx.nextReg("")
          dctx.write(s"$fieldDestPtr = getelementptr $toIrType, $toIrType* $to, i64 0, i32 $idx")
          Abi.store(mctx, dctx, false, !syncfRes.isAnon, fieldTh, dctx.meta.typeHint(field), fieldDestPtr, syncfRes.value)
        }
        EResult(to, false, true)
      }
    }

    case class Branch(beginLabel: String, before: DContext => Unit,
                      th: TypeHint, seq: Seq[Expression])

    def performBranches(resultTh: TypeHint, branches: Seq[Branch], endLabel: String): EResult = {
      val branchesTh = branches.map(_.th)

      if (resultTh == Builtin.thNil) {
        branches.foreach { br =>
          dctx.write(br.beginLabel + ":")
          dctx.deeper(OtherKind, { dctx =>
            br.before(dctx)
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
        dctx.needAlloc = true
        val (id, len) = mctx.addString(str.value)
        val r = "%" + dctx.nextReg("")
        dctx.write(s"$r = call %String @__alloc_string(i8* (i64)* %__alloc, i8* getelementptr inbounds ([$len x i8], [$len x i8]* @strconst$$$id, i32 0, i32 0), i64 $len)")
        EResult(r, false, true)
      case id: lId =>
        id.getVarLocation match {
          case VarDefLocal(fn) =>
            val fth = dctx.meta.typeHintAs[FnTh](fn)
            val irFnName = (mctx.modules.head.pkg + "." + fn.name).escaped
            val r1, r2, r3 = "%" + dctx.nextReg("")

            val irArgs = fth.args.map(th => AbiTh.toCallArg(mctx, th))
            val closureArgs = irArgs :+ "i8*"

            dctx.write(s"$r1 = bitcast ${fth.ret.toValue(mctx)} ${irArgs.mkString("(", ", ", ")")}* @$irFnName to ${fth.ret.toValue(mctx)} ${closureArgs.mkString("(", ", ", ")")}*") // FIXME: so long line
            dctx.write(s"$r2 = insertvalue ${fth.toValue(mctx)} undef, ${fth.ret.toValue(mctx)} (${closureArgs.mkString(", ")})* $r1, 0")
            dctx.write(s"$r3 = insertvalue ${fth.toValue(mctx)} $r2, i8* null, 1")

            EResult(r2, false, true)
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
      case cons@Cons(_, args) => performCons(cons, args)
      case self@(_: SelfCall | _: Call) =>
        def doIrCall(mctx: ModContext, dctx: DContext, fth: FnTh, callArgs: Seq[Expression], envArg: Option[String], fName: String): EResult = {
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

        self.getCallType match {
          case CallFnPtr =>
            val (obj, args) = self match {
              case call: Call => (call.expr, call.args)
              case call: SelfCall => (call.self, call.args)
            }

            val objRes = passExpr(mctx, dctx, obj)

            val (fth, fnPtrRes) = self match {
              case _: Call => (dctx.meta.typeHintAs[FnTh](obj), objRes)
              case sc: SelfCall =>
                val (pth, pRes) = Abi.getProperty(mctx, dctx, dctx.meta.typeHint(obj), objRes, Seq(lId(sc.fnName)))
                (pth.asInstanceOf[FnTh], pRes)
            }

            val sync = Abi.syncValue(mctx, dctx, fnPtrRes, AsRetVal, fth, fth)

            val r1, r2 = "%" + dctx.nextReg("")
            dctx.write(s"$r1 = extractvalue ${fth.toValue(mctx)} ${sync.value}, 0")
            dctx.write(s"$r2 = extractvalue ${fth.toValue(mctx)} ${sync.value}, 1")
            doIrCall(mctx, dctx, fth, args, Some(r2), r1)
          case cd: CallDef =>
            cd match {
              case cs: CallStatic =>
                val fn = cs.fn
                val callSpecMap = {
                  import TCMeta.ParseNodeTCMetaImplicit
                  self.getCallSpecMap
                }
                val mappedSpecMap = callSpecMap.map[GenericTh, TypeHint] {
                  case (k, v) => (k, ThUtil.spec(v, dctx.specMap))
                }
                val callAppliedTh = {
                  import TCMeta.ParseNodeTCMetaImplicit
                  ThUtil.spec(fn.getTypeHint, mappedSpecMap).asInstanceOf[FnTh]
                }
                val fnTh = {
                  import TCMeta.ParseNodeTCMetaImplicit
                  fn.getTypeHint[FnTh]
                }
                val isGeneric = fn.params.nonEmpty
                val genericArgs = if (isGeneric) "[" + fn.params.map(p => ThUtil.spec(p, mappedSpecMap)).mkString(", ") + "]" else ""

                cs match {
                  case ths: ThisModule =>
                    val (fnName, args) = ths match {
                      case _: CallLocal =>
                        val call = self.asInstanceOf[Call]
                        ("@" + (mctx.modules.head.pkg + "." + fn.name + genericArgs).escaped, call.args)
                      case _: SelfCallLocal =>
                        val args = self match {
                          case Call(e, args) => e +: args
                          case SelfCall(_, self, args) => self +: args
                        }

                        ("@" + (mctx.modules.head.pkg + "." + fnTh.args.head + "." + fn.name + genericArgs).escaped, args)
                    }

                    if (isGeneric)
                      passDef(mctx, DContext(mappedSpecMap, dctx.resolvedSelfDefs ++ self.getResolvedSelfDefs(), fn, false))
                    else if (mctx.modules.length != 1) {
                      val irArgs = callAppliedTh.args.map(ath => AbiTh.toCallArg(mctx, dctx.specialized(ath)))
                      mctx.prototypes += s"${AbiTh.toRetVal(mctx, callAppliedTh.ret)} $fnName (${irArgs.mkString(", ")})"
                    }

                    doIrCall(mctx, dctx, callAppliedTh, args, None, fnName)
                  case other: OtherModule =>
                    val module = other.module
                    val (fnName, args) = other match {
                      case _: CallImport =>
                        val call = self.asInstanceOf[SelfCall]
                        ("@" + (module.pkg + "." + call.fnName + genericArgs).escaped, call.args)
                      case _: SelfCallImport =>
                        val args = self match {
                          case Call(e, args) => e +: args
                          case SelfCall(_, self, args) => self +: args
                        }

                        ("@" + (module.pkg + "." + fnTh.args.head + "." + fn.name + genericArgs).escaped, args)
                    }

                    if (isGeneric || CGMeta.isIntermodInline(fn))
                      passDef(mctx.copy(modules = module +: mctx.modules), DContext(mappedSpecMap, dctx.resolvedSelfDefs ++ self.getResolvedSelfDefs(), fn, false))
                    else {
                      val irArgs = callAppliedTh.args.map(ath => AbiTh.toCallArg(mctx, dctx.specialized(ath)))
                      mctx.prototypes += s"${AbiTh.toRetVal(mctx, callAppliedTh.ret)} $fnName (${irArgs.mkString(", ")})"
                    }

                    doIrCall(mctx, dctx, callAppliedTh, args, None, fnName)
                }
              case SelfCallPolymorphic(callAppliedTh) =>
                val sc = self.asInstanceOf[SelfCall]
                val selfTh = dctx.meta.typeHint(sc.self)

                val (mod, fn) = dctx.resolvedSelfDefs((selfTh, sc.fnName))
                val fnTh = {
                  import TCMeta.ParseNodeTCMetaImplicit
                  fn.getTypeHint[FnTh]
                }

                /* Необходимо как-то избавиться от использования TypeInfer на уровне кодогенерации */
                val tInter = new TypeInfer(mctx.level, mctx.modules.head)
                tInter.infer(Seq.empty, fn.lambda.args.head.typeHint, selfTh)
                val mappedSpecMap = tInter.specMap

                val isGeneric = fn.params.nonEmpty
                val genericArgs = if (isGeneric) "[" + fn.params.map(p => ThUtil.spec(p, mappedSpecMap)).mkString(", ") + "]" else ""
                val selfFnName = "@" + (mod.pkg + "." + fnTh.args.head + "." + sc.fnName + genericArgs).escaped

                if (isGeneric)
                  passDef(mctx.copy(modules = Seq(mod)), DContext(mappedSpecMap, dctx.resolvedSelfDefs, fn, false))

                doIrCall(mctx.copy(modules = Seq(mctx.modules.last)), dctx, ThUtil.spec(callAppliedTh, dctx.specMap).asInstanceOf[FnTh], sc.self +: sc.args, None, selfFnName)
            }
        }
      case Prop(from, props) =>
        val res = passExpr(mctx, dctx, from)
        val (propTh, propRes) = Abi.getProperty(mctx, dctx, dctx.meta.typeHint(from), res, props)
        RC.doRC(mctx, dctx, Inc, propTh, propRes.isPtr, propRes.value)
        propRes
      case store@Store(_, to, what) =>
        val whatTh = dctx.meta.typeHint(what)
        val wRes = Abi.syncValue(mctx, dctx, passExpr(mctx, dctx, what), AsStoreSrc, whatTh, whatTh)

        dctx.meta.storeDeclTh(store) match {
          case Some(newVarTh) =>
            val vName = to.head.value
            val alias = dctx.addSymbol(vName)
            dctx.vars.put(alias, newVarTh)
            dctx.scope.aliases.put(vName, alias)
            dctx.scope.freeSet += ((newVarTh, EResult("%" + alias, true, false)))

            Abi.store(mctx, dctx, false, !wRes.isAnon, dctx.meta.typeHint(to.head), whatTh, "%" + alias, wRes.value)
          case None =>
            val toRes = passExpr(mctx, dctx, to.head)
            val (destTh, dRes) =
              if (to.length == 1) (dctx.meta.typeHint(to.head), toRes)
              else Abi.getProperty(mctx, dctx, dctx.meta.typeHint(to.head), toRes, to.drop(1))

            Abi.store(mctx, dctx, true, !wRes.isAnon, destTh, whatTh, dRes.value, wRes.value)
        }

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
      case self: AndOr =>
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

        self match {
          case And(lhs, rhs) => performAndOr(lhs, rhs, isAnd = true)
          case Or(lhs, rhs) => performAndOr(lhs, rhs, isAnd = false)
        }
      case While(cond, _do) =>
        val brId = dctx.nextBranch("")
        val (condBr, blockBr, endBr) = (s"wCond$brId", s"wBlock$brId", s"end$brId")
        dctx.write(s"br label %$condBr")
        dctx.write(s"$condBr:")
        dctx.deeper(OtherKind, { dctx =>
          val (_, _, res) = performBlock(mctx, dctx, Seq(cond))
          val condRes = Abi.syncValue(mctx, dctx, res, AsStoreSrc, Builtin.thBool, Builtin.thBool)
          val r1 = "%" + dctx.nextReg("")
          dctx.write(s"$r1 = icmp eq i8 ${condRes.value}, 1")
          dctx.write(s"br i1 $r1, label %$blockBr, label %$endBr")
        })
        dctx.write(s"$blockBr:")
        dctx.deeper(WhileKind(condBr, endBr), { dctx =>
          val (isTerm, _, _) = performBlock(mctx, dctx, _do)
          if (!isTerm)
            dctx.write(s"br label %$condBr")
        })
        dctx.write(s"$endBr:")
        EResult("__no__result", false, false)
      case self@If(cond, _do, _else) =>
        val ifTh = dctx.meta.typeHint(self)
        val (doTh, elseTh) = dctx.meta.ifBranchTh(self)
        val brId = dctx.nextBranch("")
        val (condBr, doBr, elseBr, endBr) = (s"if$brId", s"do$brId", s"else$brId", s"end$brId")

        dctx.write(s"br label %$condBr")
        dctx.write(s"$condBr:")

        dctx.deeper(OtherKind, { dctx =>
          val (_, _, res) = performBlock(mctx, dctx, Seq(cond))
          val condRes = Abi.syncValue(mctx, dctx, res, AsStoreSrc, Builtin.thBool, Builtin.thBool)
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
          val condRes = passExpr(mctx, dctx, exp)
          val res = Abi.syncValue(mctx, dctx, condRes, AsCallArg, expTh, expTh)

          val matched = isSeq.zipWithIndex.map { case (is, idx) =>
            val isTypeRef = dctx.specialized(is.typeRef)
            Branch(s"unless${brId}_matched$idx", dctx => {

              is.vName.foreach { vName =>
                val r = "%" + dctx.nextReg("")

                val destVar = expTh.classify(mctx) match {
                  case NullableUnion(_) =>
                    val slot = dctx.addSlot(isTypeRef)
                    dctx.write(s"$r = bitcast ${expTh.toValue(mctx)} ${res.value} to ${isTypeRef.toValue(mctx)}")
                    dctx.write(s"store ${isTypeRef.toValue(mctx)} $r, ${isTypeRef.toValue(mctx)}* $slot")
                    dctx.scope.aliases.put(vName.value, slot.stripPrefix("%"))
                    slot
                  case _ =>
                    val alias = dctx.addSymbol(vName.value)
                    dctx.scope.aliases.put(vName.value, alias)
                    dctx.write(s"$r = bitcast ${expTh.toValue(mctx)}* ${res.value} to {i64, ${isTypeRef.toValue(mctx)}}*")
                    dctx.write(s"%$alias =  getelementptr {i64, ${isTypeRef.toValue(mctx)}}, {i64, ${isTypeRef.toValue(mctx)}}* $r, i64 0, i32 1")
                    alias
                }

                if (res.isAnon)
                  dctx.scope.freeSet += ((isTypeRef, EResult(destVar, true, true)))
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

            if (th == Builtin.thNil) {
              Branch(s"unless${brId}_unmatched$idx", dctx => {}, th, Seq.empty)
            } else
              Branch(s"unless${brId}_unmatched$idx", dctx => {
                val alias = dctx.addSymbol("unmatched")
                // FIXME: ineffective to make stack slot if expr is NullableUnion
                dctx.scope.aliases.put("unmatched", alias)
                expTh.classify(mctx) match {
                  case NullableUnion(_) =>
                    val r = "%" + dctx.nextReg("")
                    dctx.write(s"%$r =  bitcast ${expTh.toValue(mctx)} ${res.value} to ${th.toValue(mctx)}")
                  case _ =>
                    val r = "%" + dctx.nextReg("")
                    dctx.write(s"$r = bitcast ${expTh.toValue(mctx)}* ${res.value} to {i64, ${th.toValue(mctx)}}*")
                    dctx.write(s"%$alias =  getelementptr {i64, ${th.toValue(mctx)}}, {i64, ${th.toValue(mctx)}}* $r, i64 0, i32 1")
                }
              }, th, Seq(id))
          }

          val (switchOver, switchType) = expTh.classify(mctx) match {
            case NullableUnion(_) =>
              val r1 = "%" + dctx.nextReg("")
              dctx.write(s"$r1 = icmp eq ${expTh.toValue(mctx)} ${res.value}, null ")
              (r1, "i1")
            case _ =>
              val r1, r2 = "%" + dctx.nextReg("")
              dctx.write(s"$r1 = getelementptr ${expTh.toValue(mctx)}, ${expTh.toValue(mctx)}* ${res.value}, i64 0, i32 0")
              dctx.write(s"$r2 = load i64, i64* $r1")
              (r2, "i64")
          }

          dctx.write(s"switch $switchType $switchOver, label %$endBr [")
          expTh.asUnion(mctx).zipWithIndex.foreach { case (variant, idx) =>
            val branch = matched.find(br => br.th == variant)
              .getOrElse(unmatched.find(br => br.th == variant).get)
            dctx.write(s"  $switchType $idx, label %${branch.beginLabel}")
          }
          dctx.write(s"]")

          (res, matched ++ unmatched)
        })

        performBranches(unlessTh, branches, endBr)
    }
  }

  def performBlock(mctx: ModContext, dctx: DContext, seq: Seq[Expression]): (Boolean, TypeHint, EResult) = {
    seq.dropRight(1).foreach { expr =>
      val eth = dctx.meta.typeHint(expr)
      val res = passExpr(mctx, dctx, expr)

      if (res.isAnon && eth.isRefType(mctx))
        dctx.scope.freeSet += ((eth, res))
    }

    def freeFull(scope: Scope, filterRet: Option[String]): Unit = {
      scope.parent.foreach(p => freeFull(p, filterRet))
      freeOne(scope, filterRet)
    }

    def freeUntilWhile(scope: Scope, filterRet: Option[String]): Unit = {
      scope.parent.foreach { p =>
        if (!p.kind.isInstanceOf[WhileKind])
          freeUntilWhile(p, filterRet)
      }

      freeOne(scope, filterRet)
    }

    def freeOne(scope: Scope, filterRet: Option[String]) =
      scope.freeSet.foreach { case (freeTh, freeRes) =>
        if (Some(freeRes.value) != filterRet)
          RC.doRC(mctx, dctx, Dec, freeTh, freeRes.isPtr, freeRes.value)
      }

    val (isTerm, eth, eRes) = seq.lastOption match {
      case Some(Ret(exprOpt)) =>
        val (th, res) = exprOpt match {
          case None =>
            freeFull(dctx.scope, None)
            dctx.write("ret void")
            (Builtin.thNil, EResult("__no_result__", false, false))
          case Some(expr) =>
            val eRes = passExpr(mctx, dctx, expr)
            val eTh = dctx.meta.typeHint(expr)

            // increment rc if we returning fnArg
            if (!eRes.isAnon && !dctx.scope.freeSet.exists { case (_, res) => res.value == eRes.value })
              RC.doRC(mctx, dctx, Inc, eTh, eRes.isPtr, eRes.value)
            freeFull(dctx.scope, Some(eRes.value))

            val fnRetTh = dctx.meta.typeHintAs[FnTh](dctx.fn).ret
            val sync = Abi.syncValue(mctx, dctx, eRes, AsRetVal, fnRetTh, eTh)

            dctx.write(s"ret ${fnRetTh.toValue(mctx)} ${sync.value}")
            (eTh, eRes)
        }
        dctx.nextBranch("")
        (true, th, res)
      case Some(Break()) =>
        freeUntilWhile(dctx.scope, None)
        dctx.nextBranch("")
        val wk = dctx.scope.findWhile()
        dctx.write(s"br label %${wk.endBr}")
        (true, Builtin.thNil, EResult("__no_result__", false, true))
      case Some(Continue()) =>
        freeUntilWhile(dctx.scope, None)
        dctx.nextBranch("")
        val wk = dctx.scope.findWhile()
        dctx.write(s"br label %${wk.condBr}")
        (true, Builtin.thNil, EResult("__no_result__", false, true))
      case Some(expr) =>
        val eRes = passExpr(mctx, dctx, expr)
        val eTh = dctx.meta.typeHint(expr)

        // increment rc if we returning fnArg
        if (!eRes.isAnon && !dctx.scope.freeSet.exists { case (_, res) => res.value == eRes.value })
          RC.doRC(mctx, dctx, Inc, eTh, eRes.isPtr, eRes.value)
        freeOne(dctx.scope, Some(eRes.value))

        (false, eTh, eRes)
      case _ =>
        freeOne(dctx.scope, None)
        (false, Builtin.thNil, EResult("__no_result__", false, true))
    }

    (isTerm, eth, eRes)
  }

  def passDef(mctx: ModContext, dctx: DContext): Unit = {
    val fth = dctx.meta.typeHintAs[FnTh](dctx.fn)
    val retTh = fth.ret

    val params = dctx.fn.params
    val irParams = if (params.isEmpty) "" else params.map(p => ThUtil.spec(p, dctx.specMap)).mkString("[", ", ", "]")

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
            } else if (cmd.startsWith("intermod_inline"))
              CGMeta.setIntermodInline(dctx.fn)

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
            dctx.fn.lambda.args.foreach { arg =>
              r = r.replace(s"$$argTypeof(${arg.name})", dctx.specialized(arg.typeHint).toValue(mctx))
            }

            dctx.write(r)
          }

        }
      case AbraCode(seq) =>
        val (isTerm, eth, eRes) = performBlock(mctx, dctx, seq)

        if (!isTerm)
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
    val m1 = System.currentTimeMillis()

    root.eachModule((level, module) => {
      outConf.withStream(module, { out =>

        val mctx = ModContext(out, level, Seq(module))

        module.defs.values.filter(d => !d.isGeneric)
          .foreach(fn => passDef(mctx, DContext(mutable.HashMap.empty, mutable.HashMap.empty, fn, false)))

        module.selfDefs.values.foreach(defs =>
          defs.filter(d => !d.isGeneric).
            foreach(fn => passDef(mctx, DContext(mutable.HashMap.empty, mutable.HashMap.empty, fn, false))))

        mctx.write("declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)")
        mctx.write("@evaAlloc = external thread_local(initialexec) global i8*  (i64)*")
        mctx.write("@evaInc   = external thread_local(initialexec) global void (i8*)*")
        mctx.write("@evaDec   = external thread_local(initialexec) global i64  (i8*)*")
        mctx.write("@evaFree  = external thread_local(initialexec) global void (i8*)*")

        mctx.modules.head.lowCode.foreach { native =>
          mctx.write(native.code)
        }

        val typeHintOrder = ArrayBuffer[TypeHint]()
        mctx.typeHints.distinct.foreach(th => th.orderTypeHints(level, module, typeHintOrder))

        val typeDecl = ArrayBuffer[(TypeHint, String)]()
        typeHintOrder.distinct.foreach(th => th.toDecl(mctx, typeDecl))

        typeDecl.foreach { case (th, decl) => mctx.write(decl) }


        if (mctx.strings.nonEmpty) {
          mctx.write(s"define private %String @__alloc_string(i8* (i64)* %allocator, i8* %data, i64 %len) {")
          mctx.write(s"  %1 = call i8* %allocator(i64 %len)")
          mctx.write(s"  call void @llvm.memcpy.p0i8.p0i8.i64(i8* %1, i8* %data, i64 %len, i32 1, i1 false)")
          mctx.write(s"  ret %String %1")
          mctx.write(s"}")

          mctx.strings.zipWithIndex.foreach { case (encoded, id) =>
            mctx.write(s"""@strconst$$$id = private unnamed_addr constant [${encoded.bytesLen} x i8] c"${encoded.str}" """)
          }
        }

        mctx.rcDef.foreach { case (_, code) => mctx.write(code.toString()) }

        mctx.prototypes.distinct.foreach { proto => mctx.write(s"declare $proto") }

        mctx.defs.foreach { case (fnName, dctx) =>
          val fth = dctx.meta.typeHintAs[FnTh](dctx.fn)
          val irArgs = (fth.args zip dctx.fn.lambda.args).map {
            case (ath, arg) => s"${AbiTh.toCallArg(mctx, dctx.specialized(ath))} %${arg.name}"
          }
          val realArgs = if (dctx.isClosure) irArgs :+ "i8* %__env" else irArgs
          val define =
            if (dctx.isClosure || CGMeta.isIntermodInline(dctx.fn) || dctx.fn.isGeneric) "define private"
            else "define"

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

          if (dctx.needAlloc) mctx.write(s"  %__alloc = load i8* (i64)*,  i8* (i64)** @evaAlloc")

          dctx.code.foreach(line => mctx.write(line))
          mctx.write("}")
        }
      })
    })

    val m2 = System.currentTimeMillis()
    println(s"__CodeGen pass elapsed: ${m2 - m1}ms")
  }
}