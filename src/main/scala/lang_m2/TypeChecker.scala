package lang_m2

import lang_m2.Ast0._
import lang_m2.Ast1.FnPointer

import scala.collection.mutable
import TypeCheckerUtil._

class TypeChecker {
  val thUnit = ScalarTypeHint("Unit")
  val thInt = ScalarTypeHint("Int")
  val thFloat = ScalarTypeHint("Float")
  val thBool = ScalarTypeHint("Boolean")
  val thString = ScalarTypeHint("String")

  var annoVars = 0
  var anonFns = 0

  def nextAnonVar = {
    annoVars += 1
    s"anon$annoVars"
  }

  def nextAnonFn = {
    annoVars += 1
    s"anonFn$annoVars"
  }

  def evalBlockExpression(result: mutable.ListBuffer[InferedFn],
                          scope: Scope,
                          forInit: Boolean,
                          typeAdvice: Option[TypeHint],
                          expression: BlockExpression): InferedExp =
    expression match {
      case v: Val =>
        val initExp = evalBlockExpression(result, scope, forInit = true, v.typeHint, v.init)
        v.typeHint.map { th => assertTypeEquals(v.init, th, initExp.th) }

        val classifier = initExp.th match {
          case FnTypeHint(seq, _) => seq.headOption.map(_.typeHint)
          case _ => None
        }

        scope.addSymbol(v, v.name, classifier, initExp.th, LocalSymbol, v.mutable)

        val lowType = scope.toLow(initExp.th)
        val infExp = InferedExp(thUnit,
          Seq(Ast1.Var(v.name, lowType))
            ++ initExp.stats
            :+ Ast1.Store(Ast1.lLocal(v.name), Seq(), lowType, initExp.init.get),
          None)

        infExp
      case block@Block(args, seq) =>
        val th = typeAdvice.map {
          case fth: FnTypeHint => Some(fth)
          case _ => None
        }.getOrElse(None)

        val fnName = nextAnonFn
        val infFn = evalFunction(result, scope, Fn(fnName, th, block, None))
        InferedExp(infFn.th, Seq(), Some(Ast1.lGlobal(fnName)))
      case self@Call(fnName, Tuple(args)) =>
        val candidates = scope.findOverloadedFunctions(fnName)

        if (candidates.isEmpty && fnName != "get")
          try {
            // foo(bar) evals to foo(bar) => try as get(foo, bar)
            return evalBlockExpression(result, scope, forInit, typeAdvice, Call("get", Tuple(Seq(lId(fnName)) ++ args)))
          } catch {
            case ex: CompileEx =>
              try {
                // x.y.z(indecies...) evals to z(x.y, indecies...) => try as get(x.y.z, indecies...)
                if (args.length >= 2)
                  return evalBlockExpression(result, scope, forInit, typeAdvice, Call("get", Tuple(Prop(args.head, lId(fnName)) +: args.drop(1))))
                else throw new CompileEx(self, CE.NoFnToCall(fnName))
              } catch {
                case ex: CompileEx =>
                  throw new CompileEx(self, CE.NoFnToCall(fnName))
              }
          }

        val firstArg = args.headOption
        val mappedFirstArgs: Set[(Boolean, Option[InferedExp])] = candidates.map { firstArgTh =>
          (firstArgTh, firstArg) match {
            case (None, None) => (true, None)
            case (Some(firstArgTh), Some(firstArg)) =>
              val argExp = evalBlockExpression(result, scope, forInit = true, Some(firstArgTh), firstArg)
              if (argExp.th == firstArgTh) (true, Some(argExp))
              else (false, None)
            case _ => (false, None)
          }
        }

        val inferedFirstArg: Option[InferedExp] = mappedFirstArgs.find(_._1 == true).map(_._2)
          .getOrElse(throw new CompileEx(self, CE.NoFnToCall(fnName)))

        val fnTh = scope.findFn(fnName, inferedFirstArg.map(_.th)).getOrElse {
          scope.findRawFn(fnName, inferedFirstArg.map(_.th)).map { fn =>
            evalFunction(result, scope.global, fn).th
          }.getOrElse(throw new CompileEx(self, CE.NoFnToCall(fnName)))
        }

        val infLastArgs = args.zip(fnTh.seq).drop(1).map {
          case (arg, argTh) =>
            evalBlockExpression(result, scope, forInit = true, Some(argTh.typeHint), arg)
        }

        infLastArgs.zip(fnTh.seq.drop(1)).foreach {
          case (infArg, argTh) => if (infArg.th != argTh.typeHint) throw new CompileEx(self, CE.NoFnToCall(fnName))
        }

        val infArgs = inferedFirstArg.map(arg => Seq(arg)).getOrElse(Seq()) ++ infLastArgs
        val location = scope.findVar(fnName).map(_.location).getOrElse(GlobalSymbol)

        val lowFnName = fnTh.seq.headOption.map { arg =>
          if (arg.name == "self") s"${fnName}_for_${arg.typeHint.name}"
          else fnName
        }.getOrElse(fnName)

        val literal = lowLiteral(location, lowFnName)

        val lowCall = Ast1.Call(literal, scope.toLow(fnTh).asInstanceOf[FnPointer], infArgs.map(_.init.get))
        val (stats, init) =
          if (forInit) (infArgs.flatMap(_.stats), Some(lowCall))
          else (infArgs.flatMap(_.stats) :+ lowCall, None)

        InferedExp(fnTh.ret, stats, init)
      case lInt(value) =>
        val infTh = typeAdvice.map {
          case sth: ScalarTypeHint =>
            scope.resolveType(sth) match {
              case ScalarType(name, ll) =>
                if (ll.matches("i\\d+") && ll != "i1") sth
                else thInt
              case _ => thInt
            }
          case _ => thInt
        }.getOrElse(thInt)

        InferedExp(infTh, Seq(), Some(Ast1.lInt(value)))
      case lFloat(value) =>
        val infTh = typeAdvice.map {
          case sth: ScalarTypeHint =>
            scope.resolveType(sth) match {
              case ScalarType(name, ll) =>
                if (Seq("half", "float", "double", "fp128", "x86_fp80", "ppc_fp128").contains(ll)) sth
                else thFloat
              case _ => thFloat
            }
          case _ => thFloat
        }.getOrElse(thFloat)

        InferedExp(infTh, Seq(), Some(Ast1.lFloat(value)))
      case lBoolean(value) =>
        InferedExp(thBool, Seq(), Some(Ast1.lInt(value)))
      case lString(value) =>
        InferedExp(thString, Seq(), Some(Ast1.lString(s"${nextAnonVar}", HexUtil.singleByteNullTerminated(value.getBytes))))
      case self@lId(varName) =>
        val vi = scope.findVar(varName).getOrElse(throw new CompileEx(self, CE.VarNotFound(varName)))

        val literal = lowLiteral(vi.location, varName)

        InferedExp(vi.th, Seq(), Some(literal))
      case Prop(from, prop) =>
        val infFrom = evalBlockExpression(result, scope, forInit = true, None, from)

        val realField = infFrom.th match {
          case sth: ScalarTypeHint =>
            scope.resolveType(sth) match {
              case factor: FactorType =>
                factor.fields.find(field => field.name == prop.value)
              case scalar: ScalarType => None
            }
          case fn: FnTypeHint => None
        }

        if (realField.isDefined) {
          val field = realField.get
          val lowFromType = scope.toLow(infFrom.th)
          InferedExp(field.typeHint, Seq(), Some(Ast1.Access(infFrom.init.get, lowFromType, field.name)))
        } else {
          // Fixme: no cheats, bro. Test that needed function exists
          evalBlockExpression(result, scope, forInit, typeAdvice, Call(prop.value, Tuple(Seq(from))))
        }
      case Store(to, what) =>
        // FIXME: validate fields and ret
        val whatExp = evalBlockExpression(result, scope, forInit = true, Some(thBool), what)
        val varName = to.head.value
        val _var = scope.findVar(varName).getOrElse(throw new CompileEx(to.head, CE.VarNotFound(varName)))

        if (!_var.isMutable)
          throw new CompileEx(to.head, CE.ReassignToVal())

        val srcType = scope.resolveType(_var.th)

        to.drop(1).foldLeft[Type](srcType) {
          case (res, prop) =>
            res match {
              case FactorType(name, fields) =>
                fields.find(f => f.name == prop.value) match {
                  case Some(field) => scope.resolveType(field.typeHint)
                  case None => throw new CompileEx(prop, CE.PropNotFound(prop.value, name))
                }
              case _type@_ => throw new CompileEx(prop, CE.PropNotFound(prop.value, _type.name))
            }
        }

        val lowVarType = scope.toLow(_var.th)
        val lowTo = lowLiteral(_var.location, varName)
        val lowStore = Ast1.Store(lowTo, to.drop(1).map(_.value), lowVarType, whatExp.init.get)

        InferedExp(thUnit, whatExp.stats :+ lowStore, None)
      case self@Cond(ifCond, _then, _else) =>
        val anonVarName = nextAnonVar

        def retMapper(scope: Scope, last: Option[InferedExp]): InferedExp =
          last.map { last =>
            val lastStatInit =
              if (last.init.isDefined && last.init.get.isInstanceOf[Ast1.Stat]) Seq(last.init.get.asInstanceOf[Ast1.Stat])
              else Seq()

            if (last.th == thUnit) InferedExp(thUnit, last.stats ++ lastStatInit, None)
            else {
              if (forInit) {
                val lowType = scope.toLow(last.th)
                InferedExp(last.th, last.stats :+ Ast1.Store(Ast1.lLocal(anonVarName), Seq(), lowType, last.init.get), None)
              }
              else InferedExp(last.th, last.stats ++ lastStatInit, None)
            }
          }.getOrElse(InferedExp(thUnit, Seq(), None))

        val condExp = evalBlockExpression(result, scope, forInit = true, Some(thBool), ifCond)
        assertTypeEquals(ifCond, thBool, condExp.th)

        val (ifTh, ifBranch) = evalBlock2(result, scope, typeAdvice, _then.seq, retMapper)
        val (elseTh, elseBranch) = _else.map { _else =>
          evalBlock2(result, scope, typeAdvice, _else.seq, retMapper)
        }.getOrElse(thUnit, Ast1.Block(Seq()))

        val lowIf = Ast1.Cond(condExp.init.get, ifBranch.seq, elseBranch.seq)

        if (forInit) {
          if (ifTh != elseTh) throw new CompileEx(self, CE.BranchTypesNotEqual())
          val lowType = scope.toLow(ifTh)
          InferedExp(ifTh, Seq(Ast1.Var(anonVarName, lowType)) :+ lowIf, Some(Ast1.lLocal(anonVarName)))
        }
        else InferedExp(ifTh, Seq(lowIf), None)

      case BoolAnd(left, right) =>
        val leftInf = evalBlockExpression(result, scope, forInit = true, Some(thBool), left)
        assertTypeEquals(left, thBool, leftInf.th)
        val rightInf = evalBlockExpression(result, scope, forInit = true, Some(thBool), right)
        assertTypeEquals(left, thBool, rightInf.th)

        if (forInit)
          InferedExp(thBool, Seq(), Some(Ast1.BoolAnd(leftInf.init.get, rightInf.init.get)))
        else
          InferedExp(thBool, Seq(Ast1.BoolAnd(leftInf.init.get, rightInf.init.get)), None)
      case BoolOr(left, right) =>
        //FIXME: deduplicate code
        val leftInf = evalBlockExpression(result, scope, forInit = true, Some(thBool), left)
        assertTypeEquals(left, thBool, leftInf.th)
        val rightInf = evalBlockExpression(result, scope, forInit = true, Some(thBool), right)
        assertTypeEquals(left, thBool, rightInf.th)

        if (forInit)
          InferedExp(thBool, Seq(), Some(Ast1.BoolOr(leftInf.init.get, rightInf.init.get)))
        else
          InferedExp(thBool, Seq(Ast1.BoolOr(leftInf.init.get, rightInf.init.get)), None)
      case While(cond, _then) =>
        val condExp = evalBlockExpression(result, scope, forInit = true, Some(thBool), cond)

        assertTypeEquals(cond, thBool, condExp.th)

        val (th, block) = evalBlock2(result, scope, typeAdvice, _then.seq, retMapper = {
          case (scope, infExp) =>
            if (infExp.isDefined) infExp.get
            else InferedExp(thUnit, Seq(), None)
        })

        InferedExp(thUnit, Seq(Ast1.While(condExp.init.get, block.seq)), None)
    }

  def evalBlock2(result: mutable.ListBuffer[InferedFn],
                 scope: Scope,
                 typeAdvice: Option[TypeHint],
                 expressions: Seq[BlockExpression],
                 retMapper: (Scope, Option[InferedExp]) => InferedExp): (TypeHint, Ast1.Block) = {
    val childScope = scope.mkChild()
    val stats = expressions.dropRight(1).flatMap { blockExpr =>
      evalBlockExpression(result, childScope, forInit = false, None, blockExpr).stats
    }

    val lastInfExp = expressions.lastOption.map { blockExpr =>
      evalBlockExpression(result, childScope, forInit = true, None, blockExpr)
    }

    val lastExp = retMapper(scope, lastInfExp)
    val lowBlock = Ast1.Block(stats ++ lastExp.stats)

    (lastExp.th, lowBlock)
  }

  def genVoid(infExp: InferedExp): InferedExp = {
    val lastStats = infExp.init match {
      case None => Seq()
      case Some(init) => if (init.isInstanceOf[Ast1.Stat]) Seq(init.asInstanceOf[Ast1.Stat]) else Seq()
    }
    InferedExp(thUnit, infExp.stats ++ lastStats ++ Seq(Ast1.RetVoid()), None)
  }

  def evalFunction(result: mutable.ListBuffer[InferedFn], scope: Scope, fn: Fn): InferedFn = {
    val infArgs: Seq[FnTypeHintField] = inferFnArgs(fn)
    val firstArgTh = infArgs.headOption.map(_.typeHint)

    //    val myTh = scope.findFn(fn.name, firstArgTh)
    //    if (myTh != None)
    //      return result.find(_.th == myTh).get

    val expectedRet: Option[TypeHint] = fn.typeHint match {
      case Some(FnTypeHint(_, retTh)) =>
        fn.retTypeHint match {
          case Some(rth) =>
            if (rth != retTh)
              throw new CompileEx(rth, CE.ExprTypeMismatch(retTh, rth))
          case _ =>
        }
        Some(retTh)
      case None =>
        fn.retTypeHint match {
          case th@Some(_) => th
          case None => None
        }
    }

    val (inferedRet, lowBody): (TypeHint, Ast1.FnBody) = fn.body match {
      case LlInline(value) => // ret должен быть указан явно
        expectedRet.map { ret =>
          (ret, Ast1.IrInline(value))
        }.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
      case self@Block(args, seq) =>
        val childScope = scope.mkChild()
        infArgs.foreach { arg =>
          val classifier = arg.typeHint match {
            case FnTypeHint(seq, _) => seq.headOption.map(_.typeHint)
            case _ => None
          }
          childScope.addSymbol(arg, arg.name, classifier, arg.typeHint, ParamSymbol, isMutable = false)
        }

        val (th, body) = evalBlock2(result, childScope, expectedRet, seq, retMapper = {
          case (scope, Some(infExp)) =>
            if (infExp.th == thUnit)
              genVoid(infExp)
            else if (expectedRet.isDefined && expectedRet.get == thUnit)
              genVoid(infExp)
            else {
              val lowType = scope.toLow(infExp.th)
              val seq = infExp.th match {
                case th: ScalarTypeHint =>
                  scope.resolveType(th) match {
                    case fact: FactorType =>
                      Seq(Ast1.Store(Ast1.lParam("ret"), Seq(), lowType, infExp.init.get), Ast1.RetVoid())
                    case _ => Seq(Ast1.Ret(scope.toLow(th), infExp.init.get))
                  }
                case th: FnTypeHint => Seq(Ast1.Ret(scope.toLow(th), infExp.init.get))
              }

              InferedExp(infExp.th, infExp.stats ++ seq, None)
            }
          case (scope, None) =>
            InferedExp(thUnit, Seq(Ast1.RetVoid()), None)
        })

        expectedRet.map { eth =>
          assertTypeEquals(self, eth, th)
        }

        (th, body)
    }

    val lowFnName = fn.typeHint match {
      case Some(th) => th.seq.headOption.map { first =>
        if (first.name == "self") fn.name + s"_for_${first.typeHint.name}"
        else fn.name
      }.getOrElse(fn.name)
      case None =>
        fn.body match {
          case inline: LlInline => throw new CompileEx(fn, CE.NeedExplicitTypeDefinition())
          case Block(args, _) =>
            args.headOption.map { arg =>
              val _type = arg.typeHint.getOrElse(throw new CompileEx(arg, CE.NeedExplicitTypeDefinition()))
              if (arg.name == "self") fn.name + s"_for_${_type.name}"
              else fn.name
            }.getOrElse(fn.name)
        }
    }

    val th = FnTypeHint(infArgs, inferedRet)
    val lowSignature = Ast1.FnPointer(
      args = infArgs.map {
        arg => Ast1.Field(arg.name, scope.toLow(arg.typeHint))
      },
      ret = scope.toLow(inferedRet))

    val lowFn = Ast1.Fn(lowFnName, lowSignature, lowBody)
    val infFn = InferedFn(th, lowFn)

    result += infFn
    scope.addGlobalFn(fn, fn.name, firstArgTh, th)
    infFn
  }

  def transform(src: Module, sourceMap: SourceMap): TypeCheckResult = {
    val typeDefs = src.seq.filter(_.isInstanceOf[Type]).map(_.asInstanceOf[Type])
    val functions = src.seq.filter(_.isInstanceOf[Fn]).map(_.asInstanceOf[Fn])

    try {
      val typeMap = typeDefs.map(td => (td.name, td)).toMap

      val preScope = new Scope(None, typeMap, Map(), new mutable.HashMap())
      val macroFunctions = typeDefs.filter(_.isInstanceOf[FactorType]).flatMap { td =>
        val factorType = td.asInstanceOf[FactorType]
        Seq(
          Macro.genConstructor(preScope, factorType),
          Macro.genEquals(preScope, factorType),
          Macro.genNotEquals(preScope, factorType))
      }

      val fnMap = (functions ++ macroFunctions).groupBy(fn => fn.name)

      val globalScope = new Scope(None, typeMap, fnMap)

      val structs = typeMap.values.filter(_.isInstanceOf[FactorType]).map { td =>
        val factorType = td.asInstanceOf[FactorType]
        globalScope.toLow(ScalarTypeHint(factorType.name)).asInstanceOf[Ast1.Struct]
      }.toSeq

      val result = mutable.ListBuffer[InferedFn]()

      functions.foreach { fn =>
        evalFunction(result, globalScope, fn)
      }

      TypeCheckSuccess(Ast1.Module(structs, result.map(_.lowFn)))
    } catch {
      case ex: CompileEx =>
        val at = sourceMap.find(ex.node).getOrElse(AstInfo("__no_source", 0, 0))
        TypeCheckFail(at, ex.error)
    }
  }
}
