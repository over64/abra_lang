package m3.typecheck

import m3.codegen.{Ast2, ConstGen}
import m3.parse.Ast0._
import m3.typecheck.Invoker.InferTask
import m3.typecheck.Util._

import scala.collection.mutable

/**
  * Created by over on 27.09.17.
  */
object TypeChecker {
  def evalExpr(ctx: TContext, scope: BlockScope, th: Option[ThAdvice], expr: Expression): (TypeHint, String, Seq[Ast2.Stat]) = expr match {
    case lId(value) =>
      scope.findVarOpt(value) match {
        case Some(vi) => (vi.th, value, Seq.empty)
        case None =>
          val cont = ctx.defs.getOrElse(value, throw new RuntimeException(s"no local var, argument or global function with name $value"))
          val header = evalDef(ctx, scope.mkChild(p => new FnScope(None)), cont, Seq.empty)
          val vName = "$l" + ctx.nextAnonId()
          scope.addLocal(ctx, vName, header.th)
          (header.th, vName, Seq(Ast2.Store(init = true, Ast2.Id(vName, Seq.empty), Ast2.Id(header.lowName))))
      }
    case x: Literal =>
      val vName = "$l" + ctx.nextAnonId()
      x match {
        case none: lNone =>
          scope.addLocal(ctx, vName, thNil)
          (thNil, vName, Seq.empty)
        case _ =>
          val (litTh, lit) = x match {
            case lInt(value) =>
              val id = ConstGen.int(ctx.lowMod, value, ctx.pkg)
              (thInt, Ast2.Call(id, Seq.empty))
            case lFloat(value: String) =>
              val id = ConstGen.float(ctx.lowMod, value, ctx.pkg)
              (thFloat, Ast2.Call(id, Seq.empty))
            case lBoolean(value: String) =>
              val id = ConstGen.bool(ctx.lowMod, value, ctx.pkg)
              (thBool, Ast2.Call(id, Seq.empty))
            case lString(value: String) =>
              val id = ConstGen.string(ctx.lowMod, value, ctx.pkg)
              (thString, Ast2.Call(id, Seq.empty))
          }

          scope.addLocal(ctx, vName, litTh)
          (litTh, vName, Seq(Ast2.Store(init = true, Ast2.Id(vName, Seq.empty), lit)))
      }
    case Prop(from, props) =>
      val (eth, evName, lowCode) = evalExpr(ctx, scope, th, from)

      val actualTh = props.foldLeft(eth) {
        case (fth, fieldId) =>
          fth match {
            case sth: ScalarTh =>
              ctx.findType(sth.name, sth.mod) match {
                case (_, sd: StructDecl) =>
                  sd.fields.find(fd => fd.name == fieldId.value).getOrElse(throw new RuntimeException(s"no such field $fieldId"))
                    .th.spec(makeSpecMap(sd.params, sth.params))
                case _ => throw new RuntimeException(s"no such field ${fieldId.value} on type $fth")
              }
            case sth: StructTh =>
              sth.seq.find(f => f.name == fieldId.value).get.typeHint
            case _ => throw new RuntimeException(s"no such field $fieldId on type $fth")
          }
      }

      val vName = "$l" + ctx.nextAnonId()
      scope.addLocal(ctx, vName, actualTh)
      (actualTh, vName, lowCode :+ Ast2.Store(init = true,
        Ast2.Id(vName),
        Ast2.Id(evName, props.map(_.value))))
    case Tuple(seq) =>
      val actualTh = th.getOrElse(throw new RuntimeException("typehint required")).toTh
      val argTasks = seq.map { arg =>
        new InferTask {
          override def infer(expected: Option[ThAdvice]) = {
            evalExpr(ctx, scope, expected, arg)
          }
        }
      }
      actualTh match {
        case sth: StructTh =>
          Invoker.invokeAnonConstructor(ctx, scope, sth, argTasks.iterator)
        case _ => throw new RuntimeException(s"unexpected value of $actualTh here")
      }
    case SelfCall(params, fnName, self, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: Option[ThAdvice]) = {
          evalExpr(ctx, scope, expected, arg)
        }
      })
      self match {
        case id: lId =>
          if (ctx.imports.exists { case (modName, _) => modName == id.value }) {
            Invoker.invokeMod(ctx, scope, id.value, fnName, params, argTasks.iterator, th)
          } else {
            val vi = scope.findVar(id.value)
            val selfTask = new InferTask {
              override def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat]) = (vi.th, id.value, Seq.empty)
            }
            Invoker.invokeSelfDef(ctx, scope, fnName, params, vi.th, (selfTask +: argTasks).iterator, th)
          }
        case _expr: Expression =>
          val (selfTh, vName, lowStats) = evalExpr(ctx, scope, None, _expr)
          val selfTask = new InferTask {
            override def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat]) = (selfTh, vName, lowStats)
          }
          Invoker.invokeSelfDef(ctx, scope, fnName, params, selfTh, (selfTask +: argTasks).iterator, th)
      }
    case Call(params, expr, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: Option[ThAdvice]) = {
          evalExpr(ctx, scope, expected, arg)
        }
      })
      expr match {
        case id: lId =>
          // is it constructor?
          if (id.value.head.isUpper) {
            Invoker.invokeConstructor(ctx, scope, id.value, params, argTasks.iterator)
          } else {
            // 1. find function
            // 2. find var
            if (ctx.defs.contains(id.value))
              Invoker.invokeDef(ctx, scope, id.value, params, argTasks.iterator, th)
            else {
              val vi = scope.findVarOpt(id.value)
                .getOrElse(throw new RuntimeException(s"no such var of function with name ${id.value}"))

              vi.th match {
                case fth: FnTh =>
                  Invoker.invokeLambda(ctx, scope, id.value, fth, argTasks.iterator)
                case selfTh =>
                  val selfTask = new InferTask {
                    override def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat]) = (selfTh, id.value, Seq.empty)
                  }
                  Invoker.invokeSelfDef(ctx, scope, "get", params, selfTh, (selfTask +: argTasks).iterator, th)
              }
            }
          }

        case _expr: Expression =>
          // 1. eval
          // 2. if th == FnTh -> call
          // 3. if arg.empty -> err not callable
          // 4. if !arg.empty -> find self get
          val (th, vName, stats) = evalExpr(ctx, scope, None, _expr)
          th match {
            case fth: FnTh =>
              val (retTh, retVName, callStats) = Invoker.invokeLambda(ctx, scope, vName, fth, argTasks.iterator)
              (retTh, retVName, stats ++ callStats)
            case _ => throw new RuntimeException("other need to call")
          }
      }
    case lambda: Lambda =>
      val _def = Def(Seq.empty, "$def" + ctx.nextAnonId(), lambda, None)

      //      def mapAndCheck()
      //
      //      val argsTh = th match {
      //        case Some(fna: FnAdvice) =>
      //          if (fna.args.length != lambda.args.length)
      //            throw new RuntimeException(s"expected ${fna.args.length} has ${lambda.args.length}")
      //          (fna.args zip lambda.args).map {
      //            case (Some(argAdvice), Arg(name, Some(argTh))) =>
      //              argAdvice.toThOpt match {
      //                case Some(advTh) =>
      //                  if (advTh != argTh) throw new RuntimeException(s"expected $advTh has $argTh")
      //                  argTh
      //                case None => argTh
      //              }
      //            case (Some(argAdvice), Arg(name, None)) => argAdvice.toTh
      //            case (None, Arg(name, Some(argTh))) => argTh
      //            case (None, Arg(name, None)) => throw new RuntimeException("expected type hint")
      //          }
      //        case _ => FnAdvice(lambda.args.map(arg => None), None)
      //      }

      val advice = th match {
        case Some(fn: FnAdvice) => fn
        case _ => FnAdvice(lambda.args.map(arg => None), None)
      }

      val header = evalDef(ctx, scope.mkChild(p => new FnScope(Some(p))), _def, Seq.empty)

      val vName = "$l" + ctx.nextAnonId()
      scope.addLocal(ctx, vName, header.th)

      (header.th, vName, Seq(Ast2.Store(init = true, Ast2.Id(vName), Ast2.Id(header.lowName))))
    case andOr: AndOr =>
      val lowId = Ast2.Id("$andOr" + ctx.nextAnonId())
      scope.addLocal(ctx, lowId.v, thBool)

      val (leftTh, leftName, leftStats) = evalExpr(ctx, scope, Some(adviceBool), andOr.left)
      if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), thBool, leftTh))
        throw new RuntimeException(s"expected $thBool has $leftTh")

      val (rightTh, rightName, rightStats) = evalExpr(ctx, scope, Some(adviceBool), andOr.right)
      if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), thBool, rightTh))
        throw new RuntimeException(s"expected $thBool has $rightTh")

      val leftFullStats = leftStats :+ Ast2.Store(init = true, lowId, Ast2.Id(leftName))
      val rightFullStats = rightStats :+ Ast2.Store(init = true, lowId, Ast2.Id(rightName))

      val low =
        andOr match {
          case and: And => Ast2.And(lowId, leftFullStats, rightFullStats)
          case or: Or => Ast2.Or(lowId, leftFullStats, rightFullStats)
        }

      (thBool, lowId.v, Seq(low))
    case If(cond, _do, _else) =>
      def evalBlock(seq: Seq[Expression]): (TypeHint, String, Seq[Ast2.Stat], Seq[Ast2.Stat]) = {
        val blockScope = scope.mkChild(p => new BlockScope(Some(p)))
        val (expressions, last) = seq match {
          case Seq() => (Seq.empty, lNone())
          case s => (s.dropRight(1), s.last)
        }

        val stats =
          expressions.map { expr => evalExpr(ctx, blockScope, None, expr)._3 }.flatten
        val (lastTh, vName, lastStat) = evalExpr(ctx, blockScope, th, last)

        val freeStats = blockScope.vars.map {
          case (vName, _) => Ast2.Free(Ast2.Id(vName))
        }.toSeq

        (lastTh, vName, stats ++ lastStat, freeStats)
      }

      val (condTh, condName, condStats) = evalExpr(ctx, scope, Some(adviceBool), cond)
      if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), thBool, condTh))
        throw new RuntimeException(s"expected $thBool has $condTh")

      val (doTh, doName, doStats, doFree) = evalBlock(_do)
      val (elseTh, elseName, elseStats, elseFree) = evalBlock(_else)

      val actualTh =
        if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), doTh, elseTh))
          UnionTh(Seq(doTh, elseTh))
        else doTh

      val resultVar = "_i" + ctx.nextAnonId()
      scope.addLocal(ctx, resultVar, actualTh)

      val (doStore, elseStore) =
        if (actualTh == thNil) (Seq(), Seq())
        else
          (Seq(Ast2.Store(init = true, Ast2.Id(resultVar), Ast2.Id(doName))),
            Seq(Ast2.Store(init = true, Ast2.Id(resultVar), Ast2.Id(elseName))))

      (actualTh,
        resultVar,
        condStats :+ Ast2.If(
          Ast2.Id(condName),
          doStats ++ doStore ++ doFree,
          elseStats ++ elseStore ++ elseFree))
    case While(cond, _do) =>
      val (condTh, condName, condStats) = evalExpr(ctx, scope, Some(adviceBool), cond)
      val blockScope = scope.mkChild(p => new BlockScope(Some(p)))
      val stats =
        _do.foldLeft(Seq[Ast2.Stat]()) {
          case (stats, expr) => stats ++ evalExpr(ctx, blockScope, None, expr)._3
        }

      val freeStats = blockScope.vars.map {
        case (vName, _) => Ast2.Free(Ast2.Id(vName))
      }

      (thNil, null, Seq(Ast2.While(Ast2.Id(condName), condStats, stats ++ freeStats)))
    case When(expr, isSeq, whenElse) =>
      def evalIs(is: Is): (TypeHint, String, Seq[Ast2.Stat], Seq[Ast2.Stat]) = {
        val blockScope = scope.mkChild(p => new BlockScope(Some(p)))
        blockScope.addLocal(ctx, is.vName.value, is.typeRef)

        val (expressions, last) = is._do match {
          case Seq() => (Seq.empty, lNone())
          case s => (s.dropRight(1), s.last)
        }

        val stats =
          expressions.flatMap { expr => evalExpr(ctx, blockScope, None, expr)._3 }
        val (lastTh, vName, lastStat) = evalExpr(ctx, blockScope, th, last)

        val freeStats = blockScope.vars
          .filter({ case (vName, _) => vName != is.vName.value })
          .map { case (vName, _) => Ast2.Free(Ast2.Id(vName)) }.toSeq

        (lastTh, vName, stats ++ lastStat, freeStats)
      }

      def evalElse(seq: Seq[Expression]): (TypeHint, String, Seq[Ast2.Stat], Seq[Ast2.Stat]) = {
        val blockScope = scope.mkChild(p => new BlockScope(Some(p)))

        val (expressions, last) = seq match {
          case Seq() => (Seq.empty, lNone())
          case s => (s.dropRight(1), s.last)
        }

        val stats =
          expressions.flatMap { expr => evalExpr(ctx, blockScope, None, expr)._3 }
        val (lastTh, vName, lastStat) = evalExpr(ctx, blockScope, th, last)

        val freeStats = blockScope.vars
          .map { case (vName, _) => Ast2.Free(Ast2.Id(vName)) }.toSeq

        (lastTh, vName, stats ++ lastStat, freeStats)
      }

      def inferSuperType(variants: Seq[TypeHint]): UnionTh =
        UnionTh(variants.distinct)

      val (exprTh, exprName, exprStats) = evalExpr(ctx, scope, Some(adviceBool), expr)
      val retValName = "_w" + ctx.nextAnonId()
      val lowIsSeq =
        isSeq.map { is =>
          if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), exprTh, is.typeRef))
            throw new RuntimeException(s"expected ${is.typeRef} as union member of $exprTh but no")

          val (bth, bvName, stats, freeStats) = evalIs(is)
          val storeRet = Seq(Ast2.Store(init = true, Ast2.Id(retValName), Ast2.Id(bvName)))
          (bth, Ast2.Is(is.vName.value, is.typeRef.toLow(ctx), stats ++ storeRet ++ freeStats))
        }

      val (overallType, elseStats) = whenElse match {
        case Some(WhenElse(elseSeq)) =>
          val (elseTh, bvName, stats, freeStats) = evalElse(elseSeq)
          val storeRet = Seq(Ast2.Store(init = true, Ast2.Id(retValName), Ast2.Id(bvName)))
          val elseStats = stats ++ storeRet ++ freeStats

          (inferSuperType(lowIsSeq.map(_._1) :+ elseTh), elseStats)
        case None =>
          (inferSuperType(lowIsSeq.map(_._1)), Seq())
      }

      // check compatibility
      val retValTh =
        th match {
          case None => overallType
          case Some(advice) =>
            val expectedTh = advice.toTh
            expectedTh match {
              case sth: ScalarTh =>
                ctx.findTypeOpt(sth.name, sth.mod) match {
                  case None => throw new RuntimeException(s"no such type $sth")
                  case Some(ud: UnionDecl) =>
                    overallType.seq.foreach { variant =>
                      if (!ud.variants.contains(variant))
                        throw new RuntimeException(s"expected $sth has $overallType")
                    }
                  case _ => throw new RuntimeException(s"expected $sth has $overallType")
                }
                sth
              case uth: UnionTh =>
                overallType.seq.foreach { variant =>
                  if (!uth.seq.contains(variant))
                    throw new RuntimeException(s"expected $uth has $overallType")
                }
                uth
            }
        }

      scope.addLocal(ctx, retValName, retValTh)
      (retValTh, retValName, exprStats ++ Seq(Ast2.When(Ast2.Id(exprName), lowIsSeq.map(_._2), elseStats)))
    case Store(typeHint, to, what) =>
      // x: Int = 5 # ok
      // x = 6 # ok
      // x.y: Int = 8 # fail
      // x.y = 8 # ok
      val toVarName = to.head.value

      typeHint match {
        case Some(th) =>
          if (to.length != 1) throw new RuntimeException("type hint unexpected here")

          if (scope.findVarOpt(to.head.value) != None)
            throw new RuntimeException(s"var with name $toVarName already declared")

          val (whatTh, whatName, whatStats) =
            evalExpr(ctx, scope, th.toAdviceOpt(mutable.HashMap()), what)

          scope.addLocal(ctx, toVarName, th)

          //FIXME: check types compatibility
          (thNil, toVarName, whatStats :+ Ast2.Store(init = true, Ast2.Id(toVarName), Ast2.Id(whatName)))
        case None =>
          scope.findVarOpt(toVarName) match {
            case None =>
              if (to.length != 1) throw new RuntimeException(s"variable with name $toVarName not found")

              val (whatTh, whatName, whatStats) =
                evalExpr(ctx, scope, None, what)

              scope.addLocal(ctx, toVarName, whatTh)

              (thNil, toVarName, whatStats :+ Ast2.Store(init = true, Ast2.Id(toVarName), Ast2.Id(whatName)))
            case Some(toVar) =>
              val toTh =
                to.drop(1).foldLeft(toVar.th) {
                  case (fth, fieldId) =>
                    fth match {
                      case sth: ScalarTh =>
                        ctx.findType(sth.name, sth.mod) match {
                          case (_, sd: StructDecl) =>
                            sd.fields.find(fd => fd.name == fieldId.value).get
                              .th.spec(makeSpecMap(sd.params, sth.params))
                          case _ => throw new RuntimeException(s"no such field $fieldId on type $fth")
                        }
                      case sth: StructTh =>
                        sth.seq.find(f => f.name == fieldId.value).get.typeHint
                      case _ => throw new RuntimeException(s"no such field $fieldId on type $fth")
                    }
                }

              val (whatTh, whatName, whatStats) =
                evalExpr(ctx, scope, toTh.toAdviceOpt(mutable.HashMap.empty), what)

              if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), toTh, whatTh))
                throw new RuntimeException(s"expected $toTh has $whatTh")

              (thNil, toVarName, whatStats :+ Ast2.Store(init = false,
                Ast2.Id(toVarName, to.drop(1).map(v => v.value)),
                Ast2.Id(whatName)))
          }
      }
    case Ret(optExpr) =>
      optExpr match {
        case Some(expr) =>
          val (actualTh, vName, lowStats) = evalExpr(ctx, scope, th, expr)

          if (actualTh == thNil)
            (thNil, "", lowStats :+ Ast2.Ret(None))
          else {
            scope.setRet(actualTh)
            (thNil, "", lowStats :+ Ast2.Ret(Some(vName)))
          }
        case None =>
          (thNil, "", Seq(Ast2.Ret(None)))
      }
  }

  def evalDef(ctx: TContext, scope: FnScope, _def: Def, specs: Seq[TypeHint]): DefHeader = {
    val deep = ctx.inferStack.length + ctx.deep + 1
    val fn = if (specs.isEmpty) _def else _def.spec(specs, ctx)
    val lowName = fn.lowName(ctx)

    ctx.headers.get(lowName) match {
      case Some(header) => return header
      case None => println("\t" * deep + s"eval $lowName")
    }

    if (ctx.inferStack.contains(fn.name))
      throw new RuntimeException(s"expected type hint for recursive function ${fn.name}")
    ctx.inferStack.push(lowName)

    val alreadyDefined =
      fn.typeHint match {
        case Some(th) => ctx.headers.put(lowName, DefHeader(ctx.pkg, fn.name, lowName, th, fn.isSelf)); true
        case None => false
      }

    val argsTh = fn.lambda.args.map { arg =>
      arg.typeHint.getOrElse(throw new RuntimeException(s"Expected type hint for arg ${arg.name}"))
    }

    (fn.lambda.args zip argsTh).foreach {
      case (arg, th) => scope.addParam(ctx, arg.name, th)
    }

    val (closure, retTh, code) =
      fn.lambda.body match {
        case llVm(code) =>
          val th = fn.retTh.getOrElse(throw new RuntimeException("Expected type hint for return value of LLVM function"))
          (Seq[(String, ClosureType)](), th, Ast2.LLCode(code))
        case AbraCode(seq) =>
          val bodyScope = scope.mkChild({ parent => new BlockScope(Some(parent)) })
          val (expressions, last) =
            if (seq.isEmpty) (Seq.empty, Ret(None))
            else (seq.dropRight(1), seq.last)

          val realLast = last match {
            case ret: Ret => ret
            case other => Ret(Some(other))
          }

          val stats = expressions.map(expr => evalExpr(ctx, bodyScope, None, expr)._3).flatten
          val (_, vName, lastStats) = evalExpr(ctx, bodyScope,
            fn.retTh.flatMap(th => th.toAdviceOpt(mutable.HashMap.empty)), realLast)

          val lowRetStat = lastStats.last.asInstanceOf[Ast2.Ret]

          val freeStats = bodyScope.vars
            .filter { case (vName, _) => Some(vName) != lowRetStat.lit }
            .map { case (vName, _) => Ast2.Free(Ast2.Id(vName)) }

          val vars = downToLow(ctx, scope.down(root = true))
          val closure = upToClosure(scope.closures.toSeq)

          val retTh = scope.retTypes match {
            case Seq() => thNil
            case Seq(th) => th
            case seq => UnionTh(seq)
          }

          (closure, retTh, Ast2.AbraCode(vars, stats ++ lastStats.dropRight(1) ++ freeStats ++ lastStats.takeRight(1)))
      }

    val fnTh = FnTh(closure.map(_._2), argsTh, retTh)
    val lowType = fnTh.toLow(ctx)
    val lowArgs = fn.lambda.args.map(_.name)
    val lowClosure = closure.map(_._1)
    val lowDef = Ast2.Def(lowName, lowType, lowClosure, lowArgs, code, isAnon = false)

    ctx.inferStack.pop()
    ctx.lowMod.defineDef(lowDef)
    if (!alreadyDefined)
      ctx.headers.put(lowName, DefHeader(ctx.pkg, fn.name, lowName, fnTh, fn.isSelf))

    DefHeader(ctx.pkg, fn.name, lowName, fnTh, fn.isSelf)
  }

  def infer(ctx: TContext): Unit = {
    ctx.lowCode.foreach { l => ctx.lowMod.addLow(l.code) }
    ctx.defs.foreach { case (_, fn) =>
      if (fn.isNotGeneric)
        evalDef(ctx, new FnScope(None), fn, Seq.empty)
    }
    ctx.selfDefs.foreach { case (_, seq) =>
      seq.foreach { fn =>
        if (fn.isNotGeneric)
          evalDef(ctx, new FnScope(None), fn, Seq.empty)
      }
    }
  }
}
