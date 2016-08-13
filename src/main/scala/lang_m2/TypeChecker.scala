package lang_m2

import lang_m2.Ast0._

class TypeChecker extends TypeCheckerUtil {

  def evalBlockExpression(ctx: InferContext,
                          forInit: Boolean,
                          typeAdvice: Option[TypeHint],
                          expression: BlockExpression): (InferContext, InferedExp) =
    expression match {
      case v: Val =>
        val (_ctx, initExp) = evalBlockExpression(ctx, forInit = true, v.typeHint, v.init)

        v.typeHint.map { th => assertTypeEquals(v.init.info, th, initExp.th) }

        val __ctx = _ctx.addVar(v.name, ValInfo(v.mutable, false, initExp.th))
        val lowType = mapTypeHintToLow(ctx, initExp.th)

        val infExp = InferedExp(thUnit,
          Seq(Ast1.Var(v.name, lowType))
            ++ initExp.stats
            :+ Ast1.Store(Ast1.lId(v.name), Seq(), lowType, initExp.init.get),
          None)

        (__ctx, infExp)
      case Call(info, fnName, Tuple(_, args)) =>
        val _ctx = ctx.fnMap.get(fnName).map { candidates =>
          candidates.foldLeft(ctx) {
            case (ctx, candidate) =>
              evalFunction(ctx, isGlobal = true, candidate)
          }
        }.getOrElse(ctx)

        val infCandidates = _ctx.evaluatedMap.getOrElse(fnName, throw new Exception(error(info, s"function with name $fnName not found")))

        // FIXME: make it less brainfuckable
        val callSeq: Seq[Option[(InferContext, InferedExp)]] = infCandidates.map { candidate =>
          if (candidate.args.length != args.length) None
          else
            try {
              val (__ctx, infArgs) = candidate.args.zip(args).foldLeft((_ctx, Seq[InferedExp]())) {
                case ((___ctx, seq), (argTh, arg)) =>
                  val (____ctx, argExp) = evalBlockExpression(ctx, forInit = true, Some(argTh), arg)
                  if (argExp.th.name != argTh.name)
                    throw new CancelEval
                  (____ctx, seq :+ argExp)
              }

              val lowCall = Ast1.Call(candidate.lowFn.ptr, infArgs.map(_.init.get))
              val (lowStats, lowInit) =
                if (forInit) (Seq(), Some(lowCall))
                else (Seq(lowCall), None)

              Some((__ctx, InferedExp(candidate.ret, lowStats, lowInit)))
            } catch {
              case ex: CancelEval => None
            }
        }

        callSeq.find(_.isDefined).map { c =>
          c.get
        }.getOrElse(throw new Exception("no fn to call"))
      case lInt(info, value) =>
        val infTh = typeAdvice.map {
          case sth@ScalarTypeHint(_, typeName) =>
            ctx.typeMap(typeName) match {
              case ScalarType(_, name, ll) =>
                if (ll.matches("i\\d+") && ll != "i1") sth
                else thInt
              case _ => thInt
            }
          case _ => thInt
        }.getOrElse(thInt)

        (ctx, InferedExp(infTh, Seq(), Some(Ast1.lInt(value))))
      case lFloat(info, value) =>
        val infTh = typeAdvice.map {
          case sth@ScalarTypeHint(_, typeName) =>
            ctx.typeMap(typeName) match {
              case ScalarType(_, name, ll) =>
                if (Seq("half", "float", "double", "fp128", "x86_fp80", "ppc_fp128").contains(ll)) sth
                else thFloat
              case _ => thFloat
            }
          case _ => thFloat
        }.getOrElse(thFloat)

        (ctx, InferedExp(infTh, Seq(), Some(Ast1.lFloat(value))))
      case lBoolean(info, value) =>
        (ctx, InferedExp(thBool, Seq(), Some(Ast1.lInt(value))))
      case lString(info, value) =>
        (ctx, InferedExp(thString, Seq(), Some(Ast1.lString(s"${nextAnonVar}", HexUtil.singleByteNullTerminated(value.getBytes)))))
      case lId(info, varName) =>
        val vi = ctx.varMap.getOrElse(varName, throw new Exception(error(info, s"variable with name $varName not found")))

        val literal =
          if (vi.isParam) Ast1.lParam(varName)
          else Ast1.lId(varName)

        (ctx, InferedExp(vi.th, Seq(), Some(literal)))
      case Prop(from, prop) =>
        val (_ctx, infFrom) = evalBlockExpression(ctx, forInit = true, None, from)

        val realField = infFrom.th match {
          case ScalarTypeHint(info, typeName) =>
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
          evalBlockExpression(ctx, forInit = true, typeAdvice, Call(prop.info, prop.value, Tuple(prop.info, Seq(from))))
        }
      case Store(info, to, what) =>
        // FIXME: validate fields and ret
        val (_ctx, condExp) = evalBlockExpression(ctx, forInit = true, Some(thBool), what)
        val varName = to.head.value
        val _var = _ctx.varMap.getOrElse(to.head.value, throw new Exception(error(to.head.info, s"variable with name ${to.head} not found")))

        if (!_var.mutable)
          throw new Exception(error(to.head.info, s"reassignment to val"))

        val srcType = ctx.typeMap(_var.th.name)

        to.drop(1).foldLeft[Type](srcType) {
          case (res, prop) =>
            res match {
              case FactorType(info, name, fields) =>
                fields.find(f => f.name == prop.value) match {
                  case Some(field) => ctx.typeMap(field.typeHint.name)
                  case None => throw new Exception(error(prop.info, s"cannot find field ${prop.value} in type $name"))
                }
              case _type@_ => throw new Exception(error(prop.info, s"cannot find field ${prop.value} in type ${_type.name}"))
            }
        }

        val lowVarType = mapTypeHintToLow(_ctx, _var.th)
        val lowStore = Ast1.Store(if (_var.isParam) Ast1.lParam(varName) else Ast1.lId(varName), to.drop(1).map(_.value), lowVarType, condExp.init.get)

        (_ctx, InferedExp(thUnit, condExp.stats :+ lowStore, None))
      case Cond(info, ifCond, _then, _else) =>
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
                (ctx, InferedExp(last.th, last.stats :+ Ast1.Store(Ast1.lId(anonVarName), Seq(), lowType, last.init.get), None))
              } else
                (ctx, InferedExp(last.th, last.stats ++ lastStatInit, None))
            }
          }.getOrElse((ctx, InferedExp(thUnit, Seq(), None)))

        val (_ctx, condExp) = evalBlockExpression(ctx, forInit = true, Some(thBool), ifCond)
        assertTypeEquals(ifCond.info, thBool, condExp.th)

        val (__ctx, ifTh, ifBranch) = evalBlock2(_ctx, typeAdvice, _then.seq, retMapper)
        val (___ctx, elseTh, elseBranch) = _else.map { _else =>
          evalBlock2(__ctx, typeAdvice, _else.seq, retMapper)
        }.getOrElse((__ctx, thUnit, Ast1.Block(Seq())))

        val lowIf = Ast1.Cond(condExp.init.get, ifBranch.seq, elseBranch.seq)

        if (forInit) {
          if (ifTh.name != elseTh.name)
            throw new Exception(error(info, s"expected equal types in if-else branches"))
          val lowType = mapTypeHintToLow(___ctx, ifTh)
          (___ctx, InferedExp(ifTh, Seq(Ast1.Var(anonVarName, lowType)) :+ lowIf, Some(Ast1.lId(anonVarName))))
        }
        else
          (___ctx, InferedExp(ifTh, Seq(lowIf), None))
      case While(info, cond, _then) =>
        val (_ctx, condExp) = evalBlockExpression(ctx, forInit = true, Some(thBool), cond)

        assertTypeEquals(cond.info, thBool, condExp.th)

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
                   fn: Fn): InferContext = {

    val infArgs: Seq[FnTypeHintField] = fn.typeHint match {
      case Some(th) =>
        fn.body match {
          case Block(_, args, _) =>
            th.seq.zip(args).foreach {
              case (protoHint, blockArg) =>
                blockArg.typeHint.map {
                  th =>
                    if (th.name != protoHint.typeHint.name)
                      throw new Exception(error(th.info, s"expected arg of type ${protoHint.typeHint.name} has ${th.name}"))
                }
            }
          case _ =>
        }
        th.seq
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) => // все аргументы должны быть с типами
            args.map {
              arg =>
                val th = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
                FnTypeHintField(null, arg.name, th)
            }
        }
    }

    val firstArgTh = infArgs.headOption.map(_.typeHint)
    if (ctx.isInfered(fn.name, firstArgTh) || ctx.isInfering(fn.name, firstArgTh)) return ctx

    val expectedRet: Option[TypeHint] = fn.typeHint match {
      case Some(FnTypeHint(info, _, retTh)) =>
        fn.retTypeHint match {
          case Some(rth) =>
            if (rth.name != retTh.name)
              throw new Exception(error(rth.info, s"expected ret of type ${retTh.name} has ${rth.name}"))
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
      case LlInline(info, value) => // ret должен быть указан явно
        expectedRet.map { ret =>
          (ret, Ast1.IrInline(value), ctx)
        }.getOrElse(throw new Exception(error(info, s"function with llvm body definition must have explicit return type declaration")))
      case Block(info, args, seq) =>
        val ctxWithArgs = infArgs.foldLeft(ctx) {
          case (ctx, arg) => ctx.addVar(arg.name, ValInfo(false, true, arg.typeHint))
        }

        val (__ctx, th, body) = evalBlock2(ctxWithArgs.stackPush(fn.name, firstArgTh), expectedRet, seq, retMapper = {
          case (_ctx, Some(infExp)) =>
            if (infExp.th.name == thUnit.name)
              (_ctx, genVoid(infExp))
            else if (expectedRet.isDefined && expectedRet.get.name == thUnit.name)
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
          assertTypeEquals(info, eth, th)
        }

        (th, body, __ctx.stackPop)
    }

    val lowFnName = fn.typeHint match {
      case Some(th) => th.seq.headOption.map {
        first => if (first.name == "self") fn.name + s"_for_${
          first.typeHint.name
        }"
        else fn.name
      }.getOrElse(fn.name)
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) =>
            args.headOption.map {
              arg =>
                val _type = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
                if (arg.name == "self") fn.name + s"_for_${
                  _type.name
                }"
                else fn.name
            }.getOrElse(fn.name)
        }
    }
    val lowSignature = Ast1.Signature(
      args = infArgs.map {
        arg => Ast1.Field(arg.name, mapTypeHintToLow(_ctx, arg.typeHint))
      },
      ret = mapTypeHintToLow(_ctx, inferedRet))

    val lowFn = Ast1.Fn(
      ptr = if (isGlobal) Ast1.GlobalFnPtr(lowFnName, lowSignature) else Ast1.VarFnPtr(lowFnName, lowSignature),
      body = lowBody)

    _ctx.addInferedFn(fn.name, InferedFn(fn.name, infArgs.map(_.typeHint), inferedRet, lowFn))
  }

  def transform(src: Module): Ast1.Module = {
    val typeDefs = src.seq.filter {
      el => el.isInstanceOf[Type]
    }.map(_.asInstanceOf[Type])
    val functions = src.seq.filter {
      el => el.isInstanceOf[Fn]
    }.map(_.asInstanceOf[Fn])
    val typeMap = typeDefs.map(td => (td.name, td)).toMap
    val fnMap = functions.groupBy(fn => fn.name)

    checkTypeMap(typeMap)

    val ctx = InferContext(typeMap, fnMap)

    val structs = typeMap.values.filter(_.isInstanceOf[FactorType]).map {
      td =>
        val factorType = td.asInstanceOf[FactorType]
        mapTypeHintToLow(ctx, ScalarTypeHint(null, factorType.name)).asInstanceOf[Ast1.Struct]
    }.toSeq

    val genConstructors = typeMap.values.filter(_.isInstanceOf[FactorType]).map { td =>
      val factorType = td.asInstanceOf[FactorType]

      val lowRetType = mapTypeHintToLow(ctx, ScalarTypeHint(null, factorType.name))
      val lowSignature = Ast1.Signature(
        args = factorType.fields.map {
          field => Ast1.Field(field.name, mapTypeHintToLow(ctx, field.typeHint))
        },
        ret = lowRetType)
      val lowFn = Ast1.Fn(Ast1.GlobalFnPtr(factorType.name, lowSignature), Ast1.Block(
        factorType.fields.map {
          field =>
            Ast1.Store(Ast1.lParam("ret"), Seq(field.name), lowRetType, Ast1.lParam(field.name))
        } :+ Ast1.RetVoid()
      ))
      InferedFn(factorType.name, factorType.fields.map(_.typeHint), ScalarTypeHint(null, factorType.name), lowFn)
    }

    val aaaa = genConstructors.foldLeft(ctx) {
      case (__ctx, constr) => __ctx.addInferedFn(constr.name, constr)
    }

    println(aaaa)

    val bbb = functions.foldLeft(aaaa) {
      case (___ctx, fn) => evalFunction(___ctx, true, fn)
    }

    Ast1.Module(structs, bbb.evaluatedMap.values.flatten.map(_.lowFn).toSeq)
  }
}
