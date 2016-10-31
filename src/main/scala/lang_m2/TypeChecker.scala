package lang_m2

import lang_m2.Ast0._
import lang_m2.Ast1.FnPointer
import lang_m2.TypeCheckerUtil._

class TypeChecker {
  var annoVars = 0
  var anonFns = 0
  var lowVars = 0

  def nextAnonVar = {
    annoVars += 1
    s"anon$annoVars"
  }

  def nextAnonFn = {
    anonFns += 1
    s"anonFn$anonFns"
  }

  def nextLowVar = {
    lowVars += 1
    lowVars
  }

  sealed trait FnKind
  case object Anon extends FnKind
  case object Self extends FnKind
  case object NonSelf extends FnKind

  def evalBlockExpression(implicit namespace: Namespace,
                          scope: Scope,
                          forInit: Boolean,
                          typeAdvice: Option[Type],
                          expression: BlockExpression): InferedExp =
    expression match {
      case v: Val =>
        val expectedVarType = v.typeHint.map(_.toType)
        val initExp = evalBlockExpression(namespace, scope, forInit = true, expectedVarType, v.init)
        expectedVarType.map { etype => assertTypeEquals(v.init, etype, initExp.infType) }

        val lowName = scope.addVar(v, v.name, initExp.infType, v.mutable, LocalSymbol, nextLowVar)

        val lowType = namespace.toLow(initExp.infType)
        val infExp = InferedExp(tUnit, initExp.stats
            :+ Ast1.Store(Ast1.lLocal(lowName), Seq(), lowType, initExp.init.get),
          None)

        infExp
      case block@Block(args, seq) =>
        val fnRet = typeAdvice.map {
          case fnPtr: FnType => Some(typeToTypeHint(fnPtr).asInstanceOf[FnTypeHint])
          case _ => None
        }.getOrElse(None)

        val infFn = evalFunction(namespace, RawFn("", Fn(nextAnonFn, fnRet, block, None)), kind = Anon, closureScope = Some(scope))
        val lowName = scope.addVar(null, nextAnonVar, infFn.ftype, isMutable = false, LocalSymbol, nextLowVar)

        val lowType = namespace.toLow(infFn.ftype)

        InferedExp(infFn.ftype,
          Seq(Ast1.Var(lowName, lowType), Ast1.Store(Ast1.lLocal(lowName), Seq(), lowType, Ast1.lGlobal(infFn.lowFn.name))),
          Some(Ast1.lLocal(lowName)))
      case call@ApplyCall(self) =>
        val inferedSelf = evalBlockExpression(namespace, scope, forInit = true, None, self)
        inferedSelf.infType match {
          case fp@FnType(name, args, ret, closure) =>
            val lowCall = Ast1.Call(inferedSelf.init.get.asInstanceOf[Ast1.lId], namespace.toLow(fp).asInstanceOf[Ast1.FnPointer], Seq())

            val (stats, init) =
              if (forInit) (inferedSelf.stats, Some(lowCall))
              else (inferedSelf.stats :+ lowCall, None)
            InferedExp(ret, stats, init)
          case _ =>
            val callableFn = namespace.findSelfFn("apply", inferedSelf.infType, inferCallback = fn => evalFunction(namespace, fn, kind = Self))
              .getOrElse(throw new CompileEx(call, CE.NoFnToCall("apply")))

            val infArgs = Seq(inferedSelf)
            val literal = lowLiteral(GlobalSymbol, s"${callableFn._package}apply_for_${inferedSelf.infType.name}")
            val lowCall = Ast1.Call(literal, namespace.toLow(callableFn.ftype).asInstanceOf[FnPointer], infArgs.map(_.init.get))

            val (stats, init) =
              if (forInit) (infArgs.flatMap(_.stats), Some(lowCall))
              else (infArgs.flatMap(_.stats) :+ lowCall, None)

            InferedExp(callableFn.ftype.ret, stats, init)
        }
      // FIXME: add support for function application like for apply call
      // {i: Int, j: Int, k: Int -> i + j + k}(1, 2, 3)
      case call@GetCall(self, args) =>
        val inferedSelf = evalBlockExpression(namespace, scope, forInit = true, None, self)
        val callableFn = namespace.findSelfFn("get", inferedSelf.infType, inferCallback = fn => evalFunction(namespace, fn, kind = Self))
          .getOrElse(throw new CompileEx(call, CE.NoFnToCall("get")))

        val inferedLastArgs = callableFn.ftype.args.drop(1).zip(args).map {
          case (argField, argExpr) => evalBlockExpression(namespace, scope, forInit = true, Some(argField.ftype), argExpr)
        }

        inferedLastArgs.zip(callableFn.ftype.args.drop(1)).foreach {
          case (infArg, argField) => if (infArg.infType != argField.ftype) throw new CompileEx(self, CE.NoFnToCall("get"))
        }

        val infArgs = inferedSelf +: inferedLastArgs
        val literal = lowLiteral(GlobalSymbol, s"${callableFn._package}get_for_${inferedSelf.infType.name}")

        val lowCall = Ast1.Call(literal, namespace.toLow(callableFn.ftype).asInstanceOf[FnPointer], infArgs.map(_.init.get))
        val (stats, init) =
          if (forInit) (infArgs.flatMap(_.stats), Some(lowCall))
          else (infArgs.flatMap(_.stats) :+ lowCall, None)

        InferedExp(callableFn.ftype.ret, stats, init)
      case call@SelfCall(fnName, self, args) =>
        // FIXME: нельзя просто так кастовать к ScalarTypeHint
        val inferedSelf = evalBlockExpression(namespace, scope, forInit = true, None, self)
        val callableFn = namespace.findSelfFn(fnName, inferedSelf.infType, inferCallback = fn => evalFunction(namespace, fn, kind = Self))

        callableFn.map { callableFn =>
          // FIXME: propagate to another call cases
          if (callableFn.ftype.args.drop(1).length != args.length)
            throw new CompileEx(self, CE.NoFnToCall(fnName))


          val inferedLastArgs = callableFn.ftype.args.drop(1).zip(args).map {
            case (argField, argExpr) => evalBlockExpression(namespace, scope, forInit = true, Some(argField.ftype), argExpr)
          }

          inferedLastArgs.zip(callableFn.ftype.args.drop(1)).foreach {
            case (infArg, argField) => if (infArg.infType != argField.ftype) throw new CompileEx(self, CE.NoFnToCall(fnName))
          }

          val infArgs = inferedSelf +: inferedLastArgs
          val literal = lowLiteral(GlobalSymbol, s"${callableFn._package}${fnName}_for_${inferedSelf.infType.name}")

          val lowCall = Ast1.Call(literal, namespace.toLow(callableFn.ftype).asInstanceOf[FnPointer], infArgs.map(_.init.get))
          val (stats, init) =
            if (forInit) (infArgs.flatMap(_.stats), Some(lowCall))
            else (infArgs.flatMap(_.stats) :+ lowCall, None)

          InferedExp(callableFn.ftype.ret, stats, init)
        }.getOrElse {
          inferedSelf.infType match {
            case ft: FactorType =>
              val fieldExists = ft.fields.find(_.name == fnName).isDefined
              if (!fieldExists) throw new CompileEx(call, CE.NoFnToCall(fnName))

              if (args.isEmpty)
                evalBlockExpression(namespace, scope, forInit, typeAdvice, ApplyCall(Prop(self, lId(fnName))))
              else
                evalBlockExpression(namespace, scope, forInit, typeAdvice, GetCall(Prop(self, lId(fnName)), args))
            case _ => throw new CompileEx(call, CE.NoFnToCall(fnName))
          }
        }
      case self@ModCall(_package, fnName, args) =>
        val found =
          namespace.findFn(fnName, inferCallback = fn => evalFunction(namespace, fn, kind = NonSelf), _package)

        found.map { fn =>
          val infArgs = fn.ftype.args.zip(args).map {
            case (argField, argExpr) => evalBlockExpression(namespace, scope, forInit = true, Some(argField.ftype), argExpr)
          }

          infArgs.zip(fn.ftype.args).foreach {
            case (infArg, argField) => if (infArg.infType != argField.ftype) throw new CompileEx(self, CE.NoFnToCall(fnName))
          }

          val literal = lowLiteral(GlobalSymbol, s"${_package}$fnName")

          val lowCall = Ast1.Call(literal, namespace.toLow(fn.ftype).asInstanceOf[FnPointer], infArgs.map(_.init.get))
          val (stats, init) =
            if (forInit) (infArgs.flatMap(_.stats), Some(lowCall))
            else (infArgs.flatMap(_.stats) :+ lowCall, None)

          InferedExp(fn.ftype.ret, stats, init)
        }.getOrElse {
          throw new CompileEx(self, CE.NoFnToCall(fnName))
        }
      case self@Call(fnName, args) =>
        var found: Option[(String, SymbolLocation, (String, FnType))] = None
        scope.findVar(fnName).map { si =>
          if (si.stype.isInstanceOf[FnType])
            found = Some((si.lowName, si.location, ("", si.stype.asInstanceOf[FnType])))
        }
        namespace.findFn(fnName, inferCallback = fn => evalFunction(namespace, fn, kind = NonSelf)).map { callableFn =>
          found = Some((fnName, GlobalSymbol, (callableFn._package, callableFn.ftype)))
        }

        found.map { case (fnName, location, (_package, fnType)) =>
          val infArgs = fnType.args.zip(args).map {
            case (argField, argExpr) => evalBlockExpression(namespace, scope, forInit = true, Some(argField.ftype), argExpr)
          }

          infArgs.zip(fnType.args).foreach {
            case (infArg, argField) => if (infArg.infType != argField.ftype) throw new CompileEx(self, CE.NoFnToCall(fnName))
          }

          val literal = lowLiteral(location, s"${_package}$fnName")

          val lowCall = Ast1.Call(literal, namespace.toLow(fnType).asInstanceOf[FnPointer], infArgs.map(_.init.get))
          val (stats, init) =
            if (forInit) (infArgs.flatMap(_.stats), Some(lowCall))
            else (infArgs.flatMap(_.stats) :+ lowCall, None)

          InferedExp(fnType.ret, stats, init)
        }.getOrElse {
          if (args.isEmpty)
            evalBlockExpression(namespace, scope, forInit, typeAdvice, ApplyCall(lId(fnName)))
          else
            evalBlockExpression(namespace, scope, forInit, typeAdvice, GetCall(lId(fnName), args))
        }
      case lInt(value) =>
        val infType = typeAdvice.map {
          case st@ScalarType(_, name, ll) if (ll.matches("i\\d+") && ll != "i1") => st
          case _ => tInt
        }.getOrElse(tInt)

        InferedExp(infType, Seq(), Some(Ast1.lInt(value)))
      case lFloat(value) =>
        val infType = typeAdvice.map {
          case st@ScalarType(_, name, ll) if (Seq("half", "float", "double", "fp128", "x86_fp80", "ppc_fp128").contains(ll)) => st
          case _ => tFloat
        }.getOrElse(tFloat)

        InferedExp(infType, Seq(), Some(Ast1.lFloat(value)))
      case lBoolean(value) =>
        InferedExp(tBool, Seq(), Some(Ast1.lInt(value)))
      case lString(value) =>
        InferedExp(tString, Seq(), Some(Ast1.lString(s"${nextAnonVar}", HexUtil.singleByteNullTerminated(value.getBytes))))
      case self@lId(varName) =>
        val vi = scope.findVar(varName).getOrElse({
          println(scope)
          throw new CompileEx(self, CE.VarNotFound(varName))
        })
        val literal = lowLiteral(vi.location, vi.lowName)

        InferedExp(vi.stype, Seq(), Some(literal))
      case Prop(from, prop) =>
        val infFrom = evalBlockExpression(namespace, scope, forInit = true, None, from)

        val realField = infFrom.infType match {
          case factor: FactorType =>
            factor.fields.find(field => field.name == prop.value)
          case scalar: ScalarType => None
        }

        if (realField.isDefined) {
          val field = realField.get
          val lowFromType = namespace.toLow(infFrom.infType)
          InferedExp(field.ftype, Seq(), Some(Ast1.Access(infFrom.init.get, lowFromType, field.name)))
        } else {
          // Fixme: no cheats, bro. Test that needed function exists
          evalBlockExpression(namespace, scope, forInit, typeAdvice, SelfCall(prop.value, from, Seq()))
        }
      case Store(to, what) =>
        // FIXME: validate fields and ret
        // FIXME: как делать store в структуру, у которой полем является указательн на функцию?
        val whatExp = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), what)
        val varName = to.head.value
        val _var = scope.findVar(varName).getOrElse(throw new CompileEx(to.head, CE.VarNotFound(varName)))

        if (to.length == 1 && !_var.isMutable)
          throw new CompileEx(to.head, CE.ReassignToVal())

        to.drop(1).foldLeft[Type](_var.stype) {
          case (res, prop) =>
            res match {
              case FactorType(_, name, fields) =>
                fields.find(f => f.name == prop.value) match {
                  case Some(field) => field.ftype
                  case None => throw new CompileEx(prop, CE.PropNotFound(prop.value, name))
                }
              case _type@_ => throw new CompileEx(prop, CE.PropNotFound(prop.value, _type.name))
            }
        }

        val lowVarType = namespace.toLow(_var.stype)
        val lowTo = lowLiteral(_var.location, _var.lowName)
        val lowStore = Ast1.Store(lowTo, to.drop(1).map(_.value), lowVarType, whatExp.init.get)

        InferedExp(tUnit, whatExp.stats :+ lowStore, None)
      case self@Cond(ifCond, _then, _else) =>
        val anonVarName = nextAnonVar

        def retMapper(scope: Namespace, last: Option[InferedExp]): InferedExp =
          last.map { last =>
            val lastStatInit =
              if (last.init.isDefined && last.init.get.isInstanceOf[Ast1.Stat]) Seq(last.init.get.asInstanceOf[Ast1.Stat])
              else Seq()

            if (last.infType == tUnit) InferedExp(tUnit, last.stats ++ lastStatInit, None)
            else {
              if (forInit) {
                val lowType = scope.toLow(last.infType)
                InferedExp(last.infType, last.stats :+ Ast1.Store(Ast1.lLocal(anonVarName), Seq(), lowType, last.init.get), None)
              }
              else InferedExp(last.infType, last.stats ++ lastStatInit, None)
            }
          }.getOrElse(InferedExp(tUnit, Seq(), None))

        val condExp = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), ifCond)
        assertTypeEquals(ifCond, tBool, condExp.infType)

        val (ifType, ifBranch) = evalBlock2(namespace, scope, typeAdvice, _then.seq, retMapper)
        val (elseType, elseBranch) = _else.map { _else =>
          evalBlock2(namespace, scope, typeAdvice, _else.seq, retMapper)
        }.getOrElse(tUnit, Ast1.Block(Seq()))

        val lowIf = Ast1.Cond(condExp.init.get, ifBranch.stats, elseBranch.stats)

        if (forInit) {
          if (ifType != elseType) throw new CompileEx(self, CE.BranchTypesNotEqual())
          val lowType = namespace.toLow(ifType)
          InferedExp(ifType, Seq(Ast1.Var(anonVarName, lowType)) :+ lowIf, Some(Ast1.lLocal(anonVarName)))
        }
        else InferedExp(ifType, Seq(lowIf), None)

      case BoolAnd(left, right) =>
        val leftInf = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), left)
        assertTypeEquals(left, tBool, leftInf.infType)
        val rightInf = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), right)
        assertTypeEquals(left, tBool, rightInf.infType)

        if (forInit)
          InferedExp(tBool, Seq(), Some(Ast1.BoolAnd(leftInf.init.get, rightInf.init.get)))
        else
          InferedExp(tBool, Seq(Ast1.BoolAnd(leftInf.init.get, rightInf.init.get)), None)
      case BoolOr(left, right) =>
        //FIXME: deduplicate code
        val leftInf = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), left)
        assertTypeEquals(left, tBool, leftInf.infType)
        val rightInf = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), right)
        assertTypeEquals(left, tBool, rightInf.infType)

        if (forInit)
          InferedExp(tBool, Seq(), Some(Ast1.BoolOr(leftInf.init.get, rightInf.init.get)))
        else
          InferedExp(tBool, Seq(Ast1.BoolOr(leftInf.init.get, rightInf.init.get)), None)
      case While(cond, _then) =>
        val condExp = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), cond)

        assertTypeEquals(cond, tBool, condExp.infType)

        val (th, block) = evalBlock2(namespace, scope, Some(tUnit), _then.seq, retMapper = {
          case (scope, infExp) =>
            if (infExp.isDefined) infExp.get
            else InferedExp(tUnit, Seq(), None)
        })

        InferedExp(tUnit, Seq(Ast1.While(condExp.init.get, block.stats)), None)
    }

  def evalBlock2(namespace: Namespace,
                 scope: Scope,
                 typeAdvice: Option[Type],
                 expressions: Seq[BlockExpression],
                 retMapper: (Namespace, Option[InferedExp]) => InferedExp): (Type, Ast1.Block) = {
    val childScope = new BlockScope(Some(scope))
    val stats = expressions.dropRight(1).flatMap { blockExpr =>
      evalBlockExpression(namespace, childScope, forInit = false, None, blockExpr).stats
    }

    val lastInfExp = expressions.lastOption.map { blockExpr =>
      evalBlockExpression(namespace, childScope, forInit = if (typeAdvice != Some(tUnit)) true else false, typeAdvice, blockExpr)
    }

    val lastExp = retMapper(namespace, lastInfExp)
    val lowBlock = Ast1.Block(stats ++ lastExp.stats)

    (lastExp.infType, lowBlock)
  }

  def genVoid(infExp: InferedExp): InferedExp = {
    val lastStats = infExp.init match {
      case None => Seq()
      case Some(init) => if (init.isInstanceOf[Ast1.Stat]) Seq(init.asInstanceOf[Ast1.Stat]) else Seq()
    }
    InferedExp(tUnit, infExp.stats ++ lastStats ++ Seq(Ast1.RetVoid()), None)
  }

  def evalFunction(implicit namespace: Namespace, rawFn: RawFn, kind: FnKind, closureScope: Option[Scope] = None): InferedFn = {
    val fn = rawFn.fn
    val infArgs = inferFnArgs(fn)
    val firstArgTh = infArgs.headOption.map(_.typeHint)


    val expectedRet: Option[Type] = fn.typeHint match {
      case Some(FnTypeHint(_, retTh)) =>
        fn.retTypeHint match {
          case Some(rth) =>
            if (rth != retTh)
              throw new CompileEx(rth, CE.ExprTypeMismatch(retTh.name, rth.name))
          case _ =>
        }
        Some(retTh.toType)
      case None =>
        fn.retTypeHint match {
          case Some(th) => Some(th.toType)
          case None => None
        }
    }

    val childScope = new FnScope(closureScope)

    val (inferedRet, lowBody): (Type, Ast1.FnBody) = fn.body match {
      case LlInline(value) => // ret должен быть указан явно
        expectedRet.map { ret =>
          (ret, Ast1.IrInline(value))
        }.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
      case self@Block(args, seq) =>
        infArgs.foreach { arg =>
          childScope.addVar(arg, arg.name, arg.typeHint.toType, isMutable = false, ParamSymbol, nextLowVar)
        }

        val (etype, body) = evalBlock2(namespace, childScope, expectedRet, seq, retMapper = {
          case (scope, Some(infExp)) =>
            if (infExp.infType == tUnit)
              genVoid(infExp)
            else if (expectedRet.isDefined && expectedRet.get == tUnit)
              genVoid(infExp)
            else {
              val lowType = scope.toLow(infExp.infType)
              val seq = infExp.infType match {
                case fact: FactorType => Seq(Ast1.Store(Ast1.lParam("ret"), Seq(), lowType, infExp.init.get), Ast1.RetVoid())
                case fnPtr: FnType => Seq(Ast1.Ret(scope.toLow(fnPtr), infExp.init.get))
                case scalar: ScalarType => Seq(Ast1.Ret(scope.toLow(scalar), infExp.init.get))
              }

              InferedExp(infExp.infType, infExp.stats ++ seq, None)
            }
          case (scope, None) =>
            InferedExp(tUnit, Seq(Ast1.RetVoid()), None)
        })

        expectedRet.map { expectedRet =>
          assertTypeEquals(self, expectedRet, etype)
        }

        (etype, body)
    }

    val lowFnName = kind match {
      case Self => s"${rawFn._package}${fn.name}_for_${infArgs.head.typeHint.name}"
      case NonSelf => if (fn.name == "main") fn.name else s"${rawFn._package}${fn.name}"
      case Anon => s"${fn.name}"
    }

    val ftype =
      if (childScope.closuredVars.isEmpty)
        FnType(infArgs.map { infArg =>
          TypeField(false, infArg.name, infArg.typeHint.toType)
        }, inferedRet)
      else
        FnType(s"tclosure$anonFns", infArgs.map { infArg =>
          TypeField(false, infArg.name, infArg.typeHint.toType)
        }, inferedRet, childScope.closuredVars)

    val lowSignature = namespace.toLow(ftype).asInstanceOf[Ast1.FnPointer]

    val lowFn = Ast1.Fn(lowFnName, lowSignature, lowBody)
    val infFn = InferedFn(rawFn._package, ftype, lowFn)

    kind match {
      case Self => namespace.inferSelfFn(fn.name, infArgs.head.typeHint.asInstanceOf[ScalarTypeHint], infFn)
      case NonSelf => namespace.inferFn(fn.name, infFn)
      case Anon => namespace.inferAnonFn(fn.name, infFn)
    }

    infFn
  }

  def transform(namespace: Namespace, sourceMap: SourceMap): Namespace = {
    namespace.extensions.values.foreach { fnMap =>
      fnMap.values.foreach { fnCont =>
        fnCont.fnInfo match {
          case fn: RawFn => evalFunction(namespace, fn, kind = Self).lowFn
          case _ =>
        }
      }
    }

    namespace.functions.values.foreach { fnMap =>
      fnMap.values.foreach { fnCont =>
        fnCont.fnInfo match {
          case fn: RawFn => evalFunction(namespace, fn, kind = NonSelf).lowFn
          case _ =>
        }
      }
    }

    namespace
  }
}
