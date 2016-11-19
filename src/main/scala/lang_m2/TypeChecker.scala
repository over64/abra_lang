package lang_m2

import lang_m2.Ast0._
import lang_m2.TypeCheckerUtil._

class TypeChecker {
  var annoVars = 0
  var anonFns = 0
  var lowVars = 0
  var closureNames = 0

  def nextAnonVar = {
    annoVars += 1
    s"anon$annoVars"
  }

  def nextAnonFn = {
    anonFns += 1
    s"anonFn$anonFns"
  }

  def nextLowVar(name: String) = {
    lowVars += 1
    s"${name}_$lowVars"
  }


  def nextClosureName = {
    closureNames += 1
    s"tclosure$closureNames"
  }

  sealed trait FnKind
  case object Anon extends FnKind
  case object Self extends FnKind
  case object NonSelf extends FnKind

  def evalBlockExpression(implicit namespace: Namespace,
                          scope: Scope,
                          forInit: Boolean,
                          typeAdvice: Option[Type],
                          expression: BlockExpression): InferedExp = {

    def genericCall(fnPtr: FnPointer, lowLocation: Ast1.lId, firstArg: Option[InferedExp],
                    lastArgs: Seq[Expression], forInit: Boolean, onArgsMismatch: => CompileEx): InferedExp = {
      val argsCount = firstArg.map(arg => 1).getOrElse(0) + lastArgs.length
      if (fnPtr.args.length != argsCount)
        throw onArgsMismatch

      val infLastArgs = fnPtr.args.zip(lastArgs).map {
        case (argField, argExpr) => evalBlockExpression(namespace, scope, forInit = true, Some(argField.ftype), argExpr)
      }

      val infArgs = firstArg.toSeq ++ infLastArgs

      infArgs.zip(fnPtr.args).foreach {
        case (infArg, argField) =>
          argField.ftype match {
            case fnPtr: FnPointer => if (infArg.infType != argField.ftype) throw onArgsMismatch
            case ds: Disclosure =>
              infArg.infType match {
                case fnType: FnType => if (fnType.fnPointer != ds.fnPointer) throw onArgsMismatch
                case _ => throw onArgsMismatch
              }
            case _ => if (infArg.infType != argField.ftype) throw onArgsMismatch
          }
      }

      val lowCall = Ast1.Call(lowLocation, infArgs.map(_.init.get))
      val firstArgStats = firstArg.map(_.stats).getOrElse(Seq())
      val (stats, init) =
        if (forInit) (infLastArgs.flatMap(_.stats) ++ firstArgStats, Some(lowCall))
        else (infLastArgs.flatMap(_.stats) ++ firstArgStats :+ lowCall, None)

      InferedExp(fnPtr.ret, stats, init)
    }

    expression match {
      case v: Val =>
        val expectedVarType = v.typeHint.map(_.toType(isParam = false))
        val initExp = evalBlockExpression(namespace, scope, forInit = true, expectedVarType, v.init)
        expectedVarType.map { etype => assertTypeEquals(v.init, etype, initExp.infType) }

        val lowName = nextLowVar(v.name)
        val location = scope.addVar(v, v.name, initExp.infType, v.mutable, LocalSymbol(lowName))

        val infExp = InferedExp(tUnit, initExp.stats
          :+ Ast1.Store(Ast1.lLocal(lowName), Seq(), initExp.init.get),
          None)

        infExp
      case block@Block(args, seq) =>
        val fnRet = typeAdvice.map {
          case fnPtr: FnType => Some(typeToTypeHint(fnPtr).asInstanceOf[FnTypeHint])
          case _ => None
        }.getOrElse(None)

        val infFn = evalFunction(namespace, RawFn("", Fn(nextAnonFn, fnRet, block, None)), kind = Anon, closureScope = Some(scope))
        val lowName = nextLowVar("anon")
        scope.addVar(block, lowName, infFn.ftype, isMutable = false, LocalSymbol(lowName))

        val lowType = namespace.toLow(infFn.ftype)

        InferedExp(infFn.ftype,
          Seq(Ast1.StoreEnclosure(Ast1.lLocal(lowName), Ast1.lGlobal(infFn.lowFn.name))),
          Some(Ast1.lLocal(lowName)))
      case call@ApplyCall(self) =>
        val inferedSelf = evalBlockExpression(namespace, scope, forInit = true, None, self)
        inferedSelf.infType match {
          case fp: FnType =>
            val inferedCall = genericCall(fp.fnPointer, inferedSelf.init.get.asInstanceOf[Ast1.lId],
              firstArg = None, lastArgs = Seq(), forInit, onArgsMismatch = null)
            InferedExp(inferedCall.infType, inferedSelf.stats ++ inferedCall.stats, inferedCall.init)
          case _ =>
            val callableFn = namespace.findSelfFn("apply", inferedSelf.infType, inferCallback = fn => evalFunction(namespace, fn, kind = Self))
              .getOrElse(throw new CompileEx(call, CE.NoFnToCall("apply")))
            genericCall(callableFn.ftype.fnPointer, Ast1.lGlobal(s"${callableFn._package}apply_for_${inferedSelf.infType.name}"),
              firstArg = Some(inferedSelf), lastArgs = Seq(), forInit, onArgsMismatch = null)
        }
      case call@GetCall(self, args) =>
        val inferedSelf = evalBlockExpression(namespace, scope, forInit = true, None, self)
        inferedSelf.infType match {
          case fp: FnType =>
            val inferedCall = genericCall(fp.fnPointer, inferedSelf.init.get.asInstanceOf[Ast1.lId],
              firstArg = None, lastArgs = args, forInit, onArgsMismatch = null)
            InferedExp(inferedCall.infType, inferedSelf.stats ++ inferedCall.stats, inferedCall.init)
          case _ =>
            val callableFn = namespace.findSelfFn("get", inferedSelf.infType, inferCallback = fn => evalFunction(namespace, fn, kind = Self))
              .getOrElse(throw new CompileEx(call, CE.NoFnToCall("get")))
            genericCall(callableFn.ftype.fnPointer, Ast1.lGlobal(s"${callableFn._package}get_for_${inferedSelf.infType.name}"),
              firstArg = Some(inferedSelf), lastArgs = args, forInit, onArgsMismatch = new CompileEx(self, CE.NoFnToCall("get")))
        }
      case call@SelfCall(fnName, self, args) =>
        // FIXME: нельзя просто так кастовать к ScalarTypeHint
        val inferedSelf = evalBlockExpression(namespace, scope, forInit = true, None, self)
        val callableFn = namespace.findSelfFn(fnName, inferedSelf.infType, inferCallback = fn => evalFunction(namespace, fn, kind = Self))

        callableFn.map { callableFn =>
          genericCall(callableFn.ftype.fnPointer, Ast1.lGlobal(s"${callableFn._package}${fnName}_for_${inferedSelf.infType.name}"),
            firstArg = Some(inferedSelf), lastArgs = args, forInit, onArgsMismatch = new CompileEx(self, CE.NoFnToCall(fnName)))
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
        val found = namespace.findFn(fnName, inferCallback = fn => evalFunction(namespace, fn, kind = NonSelf), _package)

        found.map { fn =>
          genericCall(fn.ftype.fnPointer, Ast1.lGlobal(s"${_package}$fnName"),
            firstArg = None, lastArgs = args, forInit, onArgsMismatch = new CompileEx(self, CE.NoFnToCall(fnName)))
        }.getOrElse {
          throw new CompileEx(self, CE.NoFnToCall(fnName))
        }
      case self@Call(fnName, args) =>
        var found: Option[(SymbolLocation, FnType)] = None
        scope.findVar(fnName).map { si =>
          if (si.stype.isInstanceOf[FnType])
            found = Some((si.location, si.stype.asInstanceOf[FnType]))
        }
        namespace.findFn(fnName, inferCallback = fn => evalFunction(namespace, fn, kind = NonSelf)).map { callableFn =>
          found = Some((GlobalSymbol(s"${callableFn._package}$fnName"), callableFn.ftype))
        }

        found.map { case (location, fnType) =>
          genericCall(fnType.fnPointer, lowLiteral(location),
            firstArg = None, lastArgs = args, forInit, onArgsMismatch = new CompileEx(self, CE.NoFnToCall(fnName)))
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
        val literal = lowLiteral(vi.location)

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
          InferedExp(field.ftype, Seq(), Some(Ast1.Access(infFrom.init.get.asInstanceOf[Ast1.lId], field.name)))
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
        val lowTo = lowLiteral(_var.location)
        val destType = to.drop(1).foldLeft[Type](_var.stype) {
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

        val lowStore = Ast1.Store(lowTo, to.drop(1).map(_.value), whatExp.init.get)
        InferedExp(tUnit, whatExp.stats :+ lowStore, None)
      case self@Cond(ifCond, _then, _else) =>
        val anonVarName = nextAnonVar

        def retMapper(scope: Namespace, last: Option[InferedExp]): InferedExp =
          last.map { last =>
            val lastStatInit =
              if (last.init.isDefined && last.init.get.isInstanceOf[Ast1.Stat]) Seq(last.init.get.asInstanceOf[Ast1.Stat])
              else Seq()

            if (last.infType == tUnit) InferedExp(tUnit, last.stats ++ lastStatInit, None)
            else if (forInit)
              InferedExp(last.infType, last.stats :+ Ast1.Store(Ast1.lLocal(anonVarName), Seq(), last.init.get), None)
            else
              InferedExp(last.infType, last.stats ++ lastStatInit, None)

          }.getOrElse(InferedExp(tUnit, Seq(), None))

        val condExp = evalBlockExpression(namespace, scope, forInit = true, Some(tBool), ifCond)
        assertTypeEquals(ifCond, tBool, condExp.infType)

        val (ifType, ifBranch) = evalBlock2(namespace, scope, typeAdvice, _then.seq, retMapper)
        val (elseType, elseBranch) = _else.map { _else =>
          evalBlock2(namespace, scope, typeAdvice, _else.seq, retMapper)
        }.getOrElse(tUnit, Seq())

        val lowIf = Ast1.Cond(condExp.init.get, ifBranch, elseBranch)

        if (forInit) {
          if (ifType != elseType) throw new CompileEx(self, CE.BranchTypesNotEqual())

          scope.addVar(null, anonVarName, ifType, isMutable = true, LocalSymbol(anonVarName))
          InferedExp(ifType, Seq(lowIf), Some(Ast1.lLocal(anonVarName)))
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

        InferedExp(tUnit, Seq(Ast1.While(condExp.init.get, block)), None)
    }
  }

  def evalBlock2(namespace: Namespace,
                 scope: Scope,
                 typeAdvice: Option[Type],
                 expressions: Seq[BlockExpression],
                 retMapper: (Namespace, Option[InferedExp]) => InferedExp): (Type, Seq[Ast1.Stat]) = {
    val childScope = scope.mkChild { parent =>
      new BlockScope(Some(parent))
    }
    val stats = expressions.dropRight(1).flatMap { blockExpr =>
      evalBlockExpression(namespace, childScope, forInit = false, None, blockExpr).stats
    }

    val lastInfExp = expressions.lastOption.map { blockExpr =>
      evalBlockExpression(namespace, childScope, forInit = if (typeAdvice != Some(tUnit)) true else false, typeAdvice, blockExpr)
    }

    val lastExp = retMapper(namespace, lastInfExp)

    (lastExp.infType, stats ++ lastExp.stats)
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
    val isConstructor = fn.name.head.isUpper

    val expectedRet: Option[Type] = fn.typeHint match {
      case Some(FnTypeHint(_, retTh)) =>
        fn.retTypeHint.map { rth =>
          if (rth != retTh) throw new CompileEx(rth, CE.ExprTypeMismatch(retTh.name, rth.name))
        }
        Some(retTh.toType(isParam = false))
      case None =>
        fn.retTypeHint.map { rth => rth.toType(isParam = false) }
    }

    val childScope = closureScope.map { cs =>
      cs.mkChild { parent =>
        new FnScope(Some(parent))
      }
    }.getOrElse(new FnScope(closureScope))

    val (inferedRet, lowBody): (Type, Ast1.FnBody) = fn.body match {
      case LlInline(value) => // ret должен быть указан явно
        expectedRet.map { ret =>
          (ret, Ast1.IrInline(value))
        }.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
      case self@Block(args, seq) =>
        infArgs.foreach { arg =>
          childScope.addParam(arg, arg.name, arg.typeHint.toType(isParam = !isConstructor))
        }

        val (etype, body) = evalBlock2(namespace, childScope, expectedRet, seq, retMapper = {
          case (scope, Some(infExp)) =>
            if (infExp.infType == tUnit)
              genVoid(infExp)
            else if (expectedRet.isDefined && expectedRet.get == tUnit)
              genVoid(infExp)
            else {
              val seq = infExp.infType match {
                case fact: FactorType => Seq(Ast1.Store(Ast1.lParam("ret"), Seq(), infExp.init.get), Ast1.RetVoid())
                case fnPtr: FnType => Seq(Ast1.Ret(infExp.init.get))
                case scalar: ScalarType => Seq(Ast1.Ret(infExp.init.get))
              }

              InferedExp(infExp.infType, infExp.stats ++ seq, None)
            }
          case (scope, None) =>
            InferedExp(tUnit, Seq(Ast1.RetVoid()), None)
        })

        expectedRet.map { expectedRet =>
          assertTypeEquals(self, expectedRet, etype)
        }

        val vars = childScope.traceVars.map { si =>
          (si.location.lowName, toLow(si.stype))
        }.toMap

        (etype, Ast1.Block(vars, body))
    }

    val lowFnName = kind match {
      case Self => s"${rawFn._package}${fn.name}_for_${infArgs.head.typeHint.name}"
      case NonSelf => if (fn.name == "main") fn.name else s"${rawFn._package}${fn.name}"
      case Anon => s"${fn.name}"
    }

    val fnPointer = FnPointer(infArgs.map { infArg =>
      TypeField(false, infArg.name, infArg.typeHint.toType(isParam = !isConstructor))
    }, inferedRet)

    val ftype =
      if (childScope.closuredVars.isEmpty) fnPointer
      else Closure(nextClosureName, fnPointer, childScope.closuredVars)

    val lowSignature = namespace.toLow(ftype).asInstanceOf[Ast1.FnType]

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
