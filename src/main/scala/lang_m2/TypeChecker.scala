package lang_m2

import Ast0._
import lang_m2.Ast1.Ret

class TypeChecker extends TypeCheckerUtil {

  def evalExpression(ctx: InferContext,
                     expectedType: Option[TypeHint],
                     expression: Expression): ExprEvalResult = {
    expectedType.map { et =>
      if (ctx.typeMap.get(et.name) == None) throw new Exception(error(et.info, s"could not find type with name ${et.name}"))
    }

    expression match {
      case lInt(info, value) =>
        expectedType.map { et =>
          et match {
            case sth@ScalarTypeHint(info2, typeName) =>
              ctx.typeMap(typeName) match {
                case ScalarType(_, name, ll) =>
                  if (ll.matches("i\\d+") && ll != "i1") SuccessEval(InferedExpr(sth, Ast1.lInt(value)), ctx)
                  else ErrorEval(error(info2, s"cannot assign integer value as ${name}"))
                case factor: FactorType => ErrorEval(error(info2, s"cannot assign integer value as ${factor.name}"))
              }
            case fnType: FnTypeHint => ErrorEval(error(fnType.info, s"cannot assign integer value as ${fnType.name}"))
          }
        }.getOrElse {
          SuccessEval(InferedExpr(thInt, Ast1.lInt(value)), ctx)
        }

      case Call(info, fnName, Tuple(_, args)) =>
        val candidates = ctx.fnMap.get(fnName).getOrElse(throw new Exception(error(info, s"function with name $fnName not found")))
        val _ctx = candidates.foldLeft(ctx) {
          case (ctx, candidate) =>
            evalFunction(ctx, true, candidate)
        }
        val infCandidates = _ctx.evaluatedMap(fnName)

        val callSeq: Seq[Option[(InferedExpr, InferContext)]] = infCandidates.map { candidate =>
          if (candidate.args.length != args.length) None
          else if (expectedType.map { et => et.name != candidate.ret.name }.getOrElse(false)) None
          else
            try {
              val (_ctx, infArgs) = candidate.args.zip(args).foldLeft((ctx, Seq[InferedExpr]())) {
                case ((ctx, seq), (argTh, arg)) =>
                  evalExpression(ctx, Some(argTh), arg) match {
                    case SuccessEval(infExpr, _ctx) => (_ctx, seq :+ infExpr)
                    case ErrorEval(msg) => throw new CancelEval
                  }
              }
              Some((InferedExpr(candidate.ret, Ast1.Call(candidate.lowFn.ptr, infArgs.map(_.init))), ctx))
            } catch {
              case ex: CancelEval => None
            }

        }
        callSeq.find(_.isDefined).map { c =>
          val (infCall, __ctx) = c.get
          SuccessEval(infCall, __ctx)
        }.getOrElse(ErrorEval("no fn to call"))
    }
  }

  def evalBlock(ctx: InferContext,
                expectedType: Option[TypeHint],
                expressions: Seq[BlockExpression]): (TypeHint, Ast1.Block, InferContext) = {
    def foldRoutine(expectedType: Option[TypeHint], isLast: Boolean)
                   (arg: (InferContext, Seq[InferedBlockExpr]), expr: BlockExpression): (InferContext, Seq[InferedBlockExpr]) =
      arg match {
        case (ctx, seq) =>
          expr match {
            case v: Val =>
              expectedType.map(et => if (et.name != "Unit") throw new Exception(error(v.info, s"expexted expression of type ${et.name} has Unit")))

              val (infExpr, _ctx) = evalExpression(ctx, v.typeHint, v.init) match {
                case SuccessEval(infExpr, __ctx) => (infExpr, __ctx)
                case ErrorEval(msg) => throw new Exception(msg)
              }

              val lowType = mapTypeHintToLow(ctx, infExpr.th)
              val ret = if (isLast) Seq(Ast1.RetVoid()) else Seq()

              (_ctx.addVar(v.name, ValInfo(v.mutable, infExpr.th)),
                seq :+ InferedBlockExpr(thUnit, Seq(
                  Ast1.Var(v.name, lowType),
                  Ast1.Store(Ast1.Access(v.name, lowType, Seq()), infExpr.init)
                ) ++ ret))
            case Store(info, to, expr) =>
              expectedType.map(et => throw new Exception(error(info, s"expexted expression of type ${et.name} has Unit")))
              (ctx, seq)
            case w: While =>
              expectedType.map(et => throw new Exception(error(w.info, s"expexted expression of type ${et.name} has Unit")))
              (ctx, seq)
            case call: Call =>
              val (infExpr, _ctx) = evalExpression(ctx, None, call) match {
                case SuccessEval(infExpr, __ctx) => (infExpr, __ctx)
                case ErrorEval(msg) => throw new Exception(msg)
              }

              (_ctx, seq :+ InferedBlockExpr(infExpr.th, Seq(infExpr.init.asInstanceOf[Ast1.Call])))
            case access: Access => (ctx, seq)
            case other: Expression =>
              if (isLast) {
                val (_ctx, infExpr) = evalExpression(ctx, expectedType, other) match {
                  case SuccessEval(infExpr, __ctx) => (__ctx, infExpr)
                  case ErrorEval(msg) => throw new Exception(msg)
                }

                (_ctx, seq :+ InferedBlockExpr(infExpr.th, Seq(
                  Ast1.Ret(mapTypeHintToLow(_ctx, infExpr.th), infExpr.init))))
              }
              else {
                println(s"drop $other");
                (ctx, seq)
              }
          }
      }

    val (_ctx, infExpressions) = expressions.dropRight(1).foldLeft((ctx, Seq[InferedBlockExpr]()))(foldRoutine(None, false))

    val (__ctx, lastExprs) = expressions.lastOption.map { expr =>
      foldRoutine(expectedType, true)((_ctx, Seq()), expr)
    }.getOrElse {
      (_ctx, Seq(InferedBlockExpr(ScalarTypeHint(null, "Unit"), Seq(Ast1.RetVoid()))))
    }

    val totalExpressions = infExpressions ++ lastExprs
    val retTh = totalExpressions.last.th
    val statSeq = totalExpressions.flatMap(infExpr => infExpr.seq)

    (retTh, Ast1.Block(statSeq), __ctx)
  }

  def evalFunction(ctx: InferContext,
                   isGlobal: Boolean,
                   fn: Fn): InferContext = {
    if (ctx.evaluatedMap.contains(fn.name)) return ctx

    val infArgs: Seq[FnTypeHintField] = fn.typeHint match {
      case Some(th) =>
        fn.body match {
          case Block(_, args, _) =>
            th.seq.zip(args).foreach {
              case (protoHint, blockArg) =>
                blockArg.typeHint.map { th =>
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
            args.map { arg =>
              val th = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
              FnTypeHintField(null, arg.name, th)
            }
        }
    }

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
        val (th, body, __ctx) = evalBlock(ctx.stackPush(fn.name), expectedRet, seq)
        (th, body, __ctx.stackPop)
    }

    val lowFnName = fn.typeHint match {
      case Some(th) => th.seq.headOption.map {
        first => if (first.name == "self") fn.name + s"_for_${first.typeHint.name}" else fn.name
      }.getOrElse(fn.name)
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) =>
            args.headOption.map { arg =>
              val _type = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
              if (arg.name == "self") fn.name + s"_for_${_type.name}" else fn.name
            }.getOrElse(fn.name)
        }
    }
    val lowSignature = Ast1.Signature(
      args = infArgs.map { arg => Ast1.Field(arg.name, mapTypeHintToLow(_ctx, arg.typeHint)) },
      ret = mapTypeHintToLow(_ctx, inferedRet))

    val lowFn = Ast1.Fn(
      ptr = if (isGlobal) Ast1.GlobalFnPtr(lowFnName, lowSignature) else Ast1.VarFnPtr(lowFnName, lowSignature),
      body = lowBody)

    _ctx.addInferedFn(fn.name, InferedFn(fn.name, infArgs.map(_.typeHint), inferedRet, lowFn))
  }

  def transform(src: Module): Ast1.Module = {
    val typeDefs = src.seq.filter { el => el.isInstanceOf[Type] }.map(_.asInstanceOf[Type])
    val functions = src.seq.filter { el => el.isInstanceOf[Fn] }.map(_.asInstanceOf[Fn])
    val typeMap = typeDefs.map(td => (td.name, td)).toMap
    val fnMap = functions.groupBy(fn => fn.name)

    checkTypeMap(typeMap)

    val ctx = InferContext(typeMap, fnMap)

    val structs = typeMap.values.filter(_.isInstanceOf[FactorType]).map { td =>
      val factorType = td.asInstanceOf[FactorType]
      mapTypeHintToLow(ctx, ScalarTypeHint(null, factorType.name)).asInstanceOf[Ast1.Struct]
    }.toSeq

    val _ctx = functions.foldLeft(ctx) {
      case (ctx, fn) => evalFunction(ctx, true, fn)
    }

    Ast1.Module(structs, _ctx.evaluatedMap.values.flatten.map(_.lowFn).toSeq)
  }
}
