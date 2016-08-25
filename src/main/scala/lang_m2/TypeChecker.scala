package lang_m2

import lang_m2.Ast0._
import lang_m2.Ast1.FnPointer
import lang_m2.CE

class TypeChecker extends TypeCheckerUtil {

  def evalBlockExpression(ctx: InferContext,
                          forInit: Boolean,
                          typeAdvice: Option[TypeHint],
                          expression: BlockExpression): (InferContext, InferedExp) =
    expression match {
      case v: Val =>
        val (_ctx, initExp) = evalBlockExpression(ctx, forInit = true, v.typeHint, v.init)

        v.typeHint.map { th => assertTypeEquals(v.init, th, initExp.th) }

        val __ctx = _ctx.addVar(v.name, ValInfo(v.mutable, false, initExp.th))
        val lowType = mapTypeHintToLow(ctx, initExp.th)

        val infExp = InferedExp(thUnit,
          Seq(Ast1.Var(v.name, lowType))
            ++ initExp.stats
            :+ Ast1.Store(Ast1.lLocal(v.name), Seq(), lowType, initExp.init.get),
          None)

        (__ctx, infExp)
      case block@Block(args, seq) =>
        val th = typeAdvice.map {
          case fth: FnTypeHint => Some(fth)
          case _ => None
        }.getOrElse(None)

        val (_ctx, infFn) = evalFunction(ctx, true, Fn(nextAnonFn, th, block, None))

        (_ctx, InferedExp(infFn.th, Seq(), Some(Ast1.lGlobal(infFn.name))))
      case self@Call(fnName, Tuple(args)) =>
        var res: Option[(InferContext, InferedExp)] = None

        // try to find in local vars and params
        if (ctx.varMap.get(fnName).isDefined) {
          val vi = ctx.varMap(fnName)
          vi.th match {
            case th: FnTypeHint =>
              val (_ctx, infArgs) = th.seq.zip(args).foldLeft((ctx, Seq[InferedExp]())) {
                case ((_ctx, seq), (argTh, argExpr)) =>
                  val (__ctx, infExp) = evalBlockExpression(_ctx, forInit = true, Some(argTh.typeHint), argExpr)
                  (__ctx, seq :+ infExp)
              }
              val allMatches = th.seq.zip(infArgs).forall {
                case (th, infArg) => th.typeHint.name == infArg.th.name
              }

              if (allMatches) {
                val lowFnName = if (vi.isParam) Ast1.lParam(fnName) else Ast1.lLocal(fnName)
                val beforeStats = infArgs.flatMap { arg => arg.stats }
                val argsInits = infArgs.map { arg => arg.init.get }
                val lowCall = Ast1.Call(lowFnName, mapTypeHintToLow(ctx, th).asInstanceOf[FnPointer], argsInits)

                val (stats, init) =
                  if (forInit) (beforeStats, Some(lowCall))
                  else (beforeStats :+ lowCall, None)

                res = Some((_ctx, InferedExp(th.ret, stats, init)))
              }
            case _ =>
          }
        }

        val functions = ctx.fnMap.getOrElse(fnName, Seq())

        // 2. try each function
        val mapped: Seq[Option[(InferContext, InferedExp)]] = functions.map { function =>
          val fnArgs = inferFnArgs(function)

          if (fnArgs.length != args.length) None
          else if (fnArgs.isEmpty) {
            val (_ctx, infFn) = evalFunction(ctx, true, function)
            val lowCall = Ast1.Call(Ast1.lGlobal(fnName), Ast1.FnPointer(Seq(), mapTypeHintToLow(_ctx, infFn.ret)), Seq())

            if (forInit) Some((_ctx, InferedExp(infFn.ret, Seq(), Some(lowCall))))
            else Some((_ctx, InferedExp(infFn.ret, Seq(lowCall), None)))
          }
          else {
            // check first arg
            val (_ctx, inferedFirstArg) = evalBlockExpression(ctx, forInit = true, Some(fnArgs.head.typeHint), args.head)
            if (fnArgs.head.typeHint.name != inferedFirstArg.th.name) None
            else {
              //infer last args
              val (__ctx, infLastArgs) = fnArgs.zip(args).drop(1).foldLeft((_ctx, Seq[InferedExp]())) {
                case ((___ctx, seq), (exprTh, expr)) =>
                  val (____ctx, infExpr) = evalBlockExpression(___ctx, forInit = true, Some(exprTh.typeHint), expr)
                  (____ctx, seq :+ infExpr)
              }
              // check last args
              val allMatches = fnArgs.zip(infLastArgs).drop(1).forall {
                case (th, infArg) => th.typeHint.name == infArg.th.name
              }
              if (!allMatches) None
              else {
                val (___ctx, infFn) = evalFunction(__ctx, true, function)
                val infArgs = Seq(inferedFirstArg) ++ infLastArgs
                val beforeStats = infArgs.flatMap { arg => arg.stats }
                val argsInits = infArgs.map { arg => arg.init.get }
                val lowCall = Ast1.Call(Ast1.lGlobal(infFn.lowFn.name), infFn.lowFn._type, argsInits)

                val (stats, init) =
                  if (forInit) (beforeStats, Some(lowCall))
                  else (beforeStats :+ lowCall, None)

                Some((___ctx, InferedExp(infFn.ret, stats, init)))
              }
            }
          }
        }

        mapped.find(_.isDefined).map { newRes =>
          res = newRes
        }

        res match {
          case None => throw new CompileEx(self, CE.NoFnToCall(fnName))
          case Some(res) => res
        }
      case lInt(value) =>
        val infTh = typeAdvice.map {
          case sth@ScalarTypeHint(typeName) =>
            ctx.typeMap(typeName) match {
              case ScalarType(name, ll) =>
                if (ll.matches("i\\d+") && ll != "i1") sth
                else thInt
              case _ => thInt
            }
          case _ => thInt
        }.getOrElse(thInt)

        (ctx, InferedExp(infTh, Seq(), Some(Ast1.lInt(value))))
      case lFloat(value) =>
        val infTh = typeAdvice.map {
          case sth@ScalarTypeHint(typeName) =>
            ctx.typeMap(typeName) match {
              case ScalarType(name, ll) =>
                if (Seq("half", "float", "double", "fp128", "x86_fp80", "ppc_fp128").contains(ll)) sth
                else thFloat
              case _ => thFloat
            }
          case _ => thFloat
        }.getOrElse(thFloat)

        (ctx, InferedExp(infTh, Seq(), Some(Ast1.lFloat(value))))
      case lBoolean(value) =>
        (ctx, InferedExp(thBool, Seq(), Some(Ast1.lInt(value))))
      case lString(value) =>
        (ctx, InferedExp(thString, Seq(), Some(Ast1.lString(s"${nextAnonVar}", HexUtil.singleByteNullTerminated(value.getBytes)))))
      case self@lId(varName) =>
        val vi = ctx.varMap.getOrElse(varName, throw new CompileEx(self, CE.VarNotFound(varName)))

        val literal =
          if (vi.isParam) Ast1.lParam(varName)
          else Ast1.lLocal(varName)

        (ctx, InferedExp(vi.th, Seq(), Some(literal)))
      case Prop(from, prop) =>
        val (_ctx, infFrom) = evalBlockExpression(ctx, forInit = true, None, from)

        val realField = infFrom.th match {
          case ScalarTypeHint(typeName) =>
            ctx.typeMap(typeName) match {
              case factor: FactorType =>
                factor.fields.find(field => field.name == prop.value)
              case scalar: ScalarType => None
            }
          case fn: FnTypeHint => None
        }

        if (realField.isDefined) {
          val field = realField.get
          val lowFromType = mapTypeHintToLow(ctx, infFrom.th)
          (_ctx, InferedExp(field.typeHint, Seq(), Some(Ast1.Access(infFrom.init.get, lowFromType, field.name))))
        } else {
          // Fixme: no cheats, bro. Test that needed function exists
          evalBlockExpression(ctx, forInit = true, typeAdvice, Call(prop.value, Tuple(Seq(from))))
        }
      case Store(to, what) =>
        // FIXME: validate fields and ret
        val (_ctx, condExp) = evalBlockExpression(ctx, forInit = true, Some(thBool), what)
        val varName = to.head.value
        val _var = _ctx.varMap.getOrElse(to.head.value, throw new CompileEx(to.head, CE.VarNotFound(varName)))

        if (!_var.mutable)
          throw new CompileEx(to.head, CE.ReassignToVal())

        val srcType = ctx.typeMap(_var.th.name)

        to.drop(1).foldLeft[Type](srcType) {
          case (res, prop) =>
            res match {
              case FactorType(name, fields) =>
                fields.find(f => f.name == prop.value) match {
                  case Some(field) => ctx.typeMap(field.typeHint.name)
                  case None => throw new CompileEx(prop, CE.PropNotFound(prop.value, name))
                }
              case _type@_ => throw new CompileEx(prop, CE.PropNotFound(prop.value, _type.name))
            }
        }

        val lowVarType = mapTypeHintToLow(_ctx, _var.th)
        val lowStore = Ast1.Store(if (_var.isParam) Ast1.lParam(varName) else Ast1.lLocal(varName), to.drop(1).map(_.value), lowVarType, condExp.init.get)

        (_ctx, InferedExp(thUnit, condExp.stats :+ lowStore, None))
      case self@Cond(ifCond, _then, _else) =>
        val anonVarName = nextAnonVar

        def retMapper(ctx: InferContext, last: Option[InferedExp]): (InferContext, InferedExp) =
          last.map { last =>
            val lastStatInit = if (last.init.isDefined && last.init.get.isInstanceOf[Ast1.Stat])
              Seq(last.init.get.asInstanceOf[Ast1.Stat])
            else Seq()

            if (last.th.name == thUnit.name)
              (ctx, InferedExp(thUnit, last.stats ++ lastStatInit, None))
            else {
              if (forInit) {
                val lowType = mapTypeHintToLow(ctx, last.th)
                (ctx, InferedExp(last.th, last.stats :+ Ast1.Store(Ast1.lLocal(anonVarName), Seq(), lowType, last.init.get), None))
              } else
                (ctx, InferedExp(last.th, last.stats ++ lastStatInit, None))
            }
          }.getOrElse((ctx, InferedExp(thUnit, Seq(), None)))

        val (_ctx, condExp) = evalBlockExpression(ctx, forInit = true, Some(thBool), ifCond)
        assertTypeEquals(ifCond, thBool, condExp.th)

        val (__ctx, ifTh, ifBranch) = evalBlock2(_ctx, typeAdvice, _then.seq, retMapper)
        val (___ctx, elseTh, elseBranch) = _else.map { _else =>
          evalBlock2(__ctx, typeAdvice, _else.seq, retMapper)
        }.getOrElse((__ctx, thUnit, Ast1.Block(Seq())))

        val lowIf = Ast1.Cond(condExp.init.get, ifBranch.seq, elseBranch.seq)

        if (forInit) {
          if (ifTh.name != elseTh.name)
            throw new CompileEx(self, CE.BranchTypesNotEqual())
          val lowType = mapTypeHintToLow(___ctx, ifTh)
          (___ctx, InferedExp(ifTh, Seq(Ast1.Var(anonVarName, lowType)) :+ lowIf, Some(Ast1.lLocal(anonVarName))))
        }
        else
          (___ctx, InferedExp(ifTh, Seq(lowIf), None))
      case While(cond, _then) =>
        val (_ctx, condExp) = evalBlockExpression(ctx, forInit = true, Some(thBool), cond)

        assertTypeEquals(cond, thBool, condExp.th)

        val (__ctx, th, block) = evalBlock2(_ctx, typeAdvice, _then.seq, {
          case (ctx, infExp) =>
            if (infExp.isDefined)
              (ctx, infExp.get)
            else
              (ctx, InferedExp(thUnit, Seq(), None))
        })

        (__ctx, InferedExp(thUnit, Seq(Ast1.While(condExp.init.get, block.seq)), None))
    }

  def evalBlock2(ctx: InferContext,
                 typeAdvice: Option[TypeHint],
                 expressions: Seq[BlockExpression],
                 retMapper: (InferContext, Option[InferedExp]) => (InferContext, InferedExp)): (InferContext, TypeHint, Ast1.Block) = {
    val start = (ctx, Seq[Ast1.Stat]())

    val (_ctx, stats) = expressions.dropRight(1).foldLeft(start) {
      case ((_ctx, seq), blockExpr) =>
        val (__ctx, infExp) = evalBlockExpression(_ctx, false, None, blockExpr)
        (__ctx, seq ++ infExp.stats)
    }

    val ctxAndInfExp = expressions.lastOption.map { blockExpr =>
      evalBlockExpression(_ctx, forInit = true, typeAdvice, blockExpr)
    }

    val realCtx = ctxAndInfExp.map(_._1).getOrElse(_ctx)
    val (__ctx, lastExp) = retMapper(realCtx, ctxAndInfExp.map(_._2))
    val lowBlock = Ast1.Block(stats ++ lastExp.stats)

    (__ctx, lastExp.th, lowBlock)
  }

  def genVoid(infExp: InferedExp): InferedExp = {
    val lastStats = infExp.init match {
      case None => Seq()
      case Some(init) => if (init.isInstanceOf[Ast1.Stat]) Seq(init.asInstanceOf[Ast1.Stat]) else Seq()
    }
    InferedExp(thUnit, infExp.stats ++ lastStats ++ Seq(Ast1.RetVoid()), None)
  }

  def evalFunction(ctx: InferContext,
                   isGlobal: Boolean,
                   fn: Fn): (InferContext, InferedFn) = {

    val infArgs: Seq[FnTypeHintField] = inferFnArgs(fn)

    val firstArgTh = infArgs.headOption.map(_.typeHint)

    val alreadyInfered = ctx.findInfered(fn.name, firstArgTh)
    if (alreadyInfered != None)
      return (ctx, alreadyInfered.get)

    val expectedRet: Option[TypeHint] = fn.typeHint match {
      case Some(FnTypeHint(_, retTh)) =>
        fn.retTypeHint match {
          case Some(rth) =>
            if (rth.name != retTh.name)
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

    val (inferedRet, lowBody, _ctx): (TypeHint, Ast1.FnBody, InferContext) = fn.body match {
      case LlInline(value) => // ret должен быть указан явно
        expectedRet.map { ret =>
          (ret, Ast1.IrInline(value), ctx)
        }.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
      case self@Block(args, seq) =>
        val ctxWithArgs = infArgs.foldLeft(ctx) {
          case (ctx, arg) => ctx.addVar(arg.name, ValInfo(false, true, arg.typeHint))
        }

        val (__ctx, th, body) = evalBlock2(ctxWithArgs.stackPush(fn.name, firstArgTh), expectedRet, seq, retMapper = {
          case (_ctx, Some(infExp)) =>
            if (infExp.th == thUnit)
              (_ctx, genVoid(infExp))
            else if (expectedRet.isDefined && expectedRet.get == thUnit)
              (_ctx, genVoid(infExp))
            else {
              val lowType = mapTypeHintToLow(_ctx, infExp.th)
              val seq = infExp.th match {
                case th: ScalarTypeHint =>
                  ctx.typeMap(th.name) match {
                    case fact: FactorType =>
                      Seq(Ast1.Store(Ast1.lParam("ret"), Seq(), lowType, infExp.init.get), Ast1.RetVoid())
                    case _ => Seq(Ast1.Ret(mapTypeHintToLow(ctx, th), infExp.init.get))
                  }
                case th: FnTypeHint => Seq(Ast1.Ret(mapTypeHintToLow(ctx, th), infExp.init.get))
              }

              (_ctx, InferedExp(infExp.th, infExp.stats ++ seq, None))
            }
          case (_ctx, None) =>
            (_ctx, InferedExp(thUnit, Seq(Ast1.RetVoid()), None))
        })

        expectedRet.map { eth =>
          assertTypeEquals(self, eth, th)
        }

        (th, body, __ctx.stackPop)
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
            args.headOption.map {
              arg =>
                val _type = arg.typeHint.getOrElse(throw new CompileEx(arg, CE.NeedExplicitTypeDefinition()))
                if (arg.name == "self") fn.name + s"_for_${
                  _type.name
                }"
                else fn.name
            }.getOrElse(fn.name)
        }
    }

    val th = FnTypeHint(infArgs, inferedRet)
    val lowSignature = Ast1.FnPointer(
      args = infArgs.map {
        arg => Ast1.Field(arg.name, mapTypeHintToLow(_ctx, arg.typeHint))
      },
      ret = mapTypeHintToLow(_ctx, inferedRet))

    val lowFn = Ast1.Fn(lowFnName, lowSignature, lowBody)

    val infFn = InferedFn(fn.name, infArgs.map(_.typeHint), inferedRet, lowFn, th)
    (_ctx.addInferedFn(fn.name, infFn), infFn)
  }

  def transform(src: Module, sourceMap: SourceMap): TypeCheckResult = {
    val typeDefs = src.seq.filter {
      el => el.isInstanceOf[Type]
    }.map(_.asInstanceOf[Type])

    val functions = src.seq.filter {
      el => el.isInstanceOf[Fn]
    }.map(_.asInstanceOf[Fn])

    try {

      val constructors = typeDefs.filter(_.isInstanceOf[FactorType]).map { td =>
        val factorType = td.asInstanceOf[FactorType]
        Macro.genConstructor(factorType)
      }

      val typeMap = typeDefs.map(td => (td.name, td)).toMap
      checkTypeMap(typeMap)

      val fnMap = (functions ++ constructors).groupBy(fn => fn.name)
      val ctx = InferContext(typeMap, fnMap)

      val structs = typeMap.values.filter(_.isInstanceOf[FactorType]).map { td =>
        val factorType = td.asInstanceOf[FactorType]
        mapTypeHintToLow(ctx, ScalarTypeHint(factorType.name)).asInstanceOf[Ast1.Struct]
      }.toSeq

      val bbb = functions.foldLeft(ctx) {
        case (___ctx, fn) => evalFunction(___ctx, true, fn)._1
      }

      TypeCheckSuccess(Ast1.Module(structs, bbb.evaluatedMap.values.flatten.map(_.lowFn).toSeq))
    } catch {
      case ex: CompileEx =>
        val at = sourceMap.find(ex.node).getOrElse(AstInfo("__no_source", 0, 0))
        TypeCheckFail(at, ex.error)
    }
  }
}
