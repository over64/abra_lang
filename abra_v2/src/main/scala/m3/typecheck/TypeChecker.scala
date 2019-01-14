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
  def evalExpr(ctx: TContext, scope: BlockScope, th: TypeHint, expr: Expression): (TypeHint, String, Seq[Ast2.Stat]) = expr match {
    case lId(value) =>
      scope.findVarOpt(value) match {
        case Some(vi) => (vi.th, value, Seq.empty)
        case None =>
          val cont = ctx.defs.getOrElse(value, throw new RuntimeException(s"no local var, argument or global function with name $value"))
          val header = evalDef(ctx, scope.mkChild(p => new FnScope(None)), cont, mutable.HashMap.empty)
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
                  sd.fields.find(fd => fd.name == fieldId.value).getOrElse(throw new RuntimeException(s"no such field $fieldId")).th
                    .moveToModSeq(sth.mod)
                    .spec(makeSpecMap(sd.params, sth.params))
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
      val argTasks = seq.map { arg =>
        new InferTask {
          override def infer(expected: TypeHint) = {
            evalExpr(ctx, scope, expected, arg)
          }
        }
      }
      th match {
        case sth: StructTh =>
          Invoker.invokeAnonConstructor(ctx, scope, sth, argTasks.iterator)
        case _ => throw new RuntimeException(s"unexpected value of $th here")
      }
    case SelfCall(fnName, self, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: TypeHint) = {
          evalExpr(ctx, scope, expected, arg)
        }
      })
      self match {
        case id: lId =>
          if (ctx.imports.exists { case (modName, _) => modName == id.value })
            return Invoker.invokeMod(ctx, scope, id.value, fnName, argTasks.iterator, th)

          def callField(fth: FnTh) = {
            val lambdaVarName = "$l" + ctx.nextAnonId().toString
            val lambdaVar = scope.addLocal(ctx, lambdaVarName, fth)
            val (exprTh, vName, stats) = Invoker.invokeLambda(ctx, scope, lambdaVarName, fth, argTasks.toIterator)
            (exprTh, vName, Ast2.Store(init = true, Ast2.Id(lambdaVarName), Ast2.Id(id.value, Seq(fnName))) +: stats)
          }

          val vi = scope.findVar(id.value)
          vi.th match {
            case sth: ScalarTh =>
              ctx.findType(sth.name, sth.mod) match {
                case (_, sd: StructDecl) =>
                  sd.fields.find(fd => fd.name == fnName) match {
                    case Some(field) =>
                      field.th.spec(makeSpecMap(sd.params, sth.params)) match {
                        case fth: FnTh => return callField(fth)
                        case _ =>
                      }
                    case _ =>
                  }
                case _ =>
              }
            case structTh: StructTh =>
              structTh.seq.find(fd => fd.name == fnName) match {
                case Some(field) =>
                  field.typeHint match {
                    case fth: FnTh => return callField(fth)
                    case _ =>
                  }
                case _ =>
              }
            case _ =>
          }

          val selfTask = new InferTask {
            override def infer(expected: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = (vi.th, id.value, Seq.empty)
          }
          Invoker.invokeSelfDef(ctx, scope, fnName, vi.th, (selfTask +: argTasks).iterator, th)
        case _expr: Expression =>
          val (selfTh, vName, lowStats) = evalExpr(ctx, scope, AnyTh, _expr)
          val selfTask = new InferTask {
            override def infer(expected: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = (selfTh, vName, lowStats)
          }
          Invoker.invokeSelfDef(ctx, scope, fnName, selfTh, (selfTask +: argTasks).iterator, th)
      }
    case Cons(sth, args) =>
      Invoker.invokeConstructor(ctx, scope, sth.name, args.map(arg => new InferTask {
        override def infer(expected: TypeHint) = {
          evalExpr(ctx, scope, expected, arg)
        }
      }).iterator, th)
    case Call(expr, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: TypeHint) = {
          evalExpr(ctx, scope, expected, arg)
        }
      })
      expr match {
        case id: lId =>
          // 1. find function
          // 2. find var
          if (ctx.defs.contains(id.value))
            Invoker.invokeDef(ctx, scope, id.value, argTasks.iterator, th)
          else {
            val vi = scope.findVarOpt(id.value)
              .getOrElse(throw new RuntimeException(s"no such var of function with name ${id.value}"))

            vi.th match {
              case fth: FnTh =>
                Invoker.invokeLambda(ctx, scope, id.value, fth, argTasks.iterator)
              case selfTh =>
                val selfTask = new InferTask {
                  override def infer(expected: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = (selfTh, id.value, Seq.empty)
                }
                Invoker.invokeSelfDef(ctx, scope, "get", selfTh, (selfTask +: argTasks).iterator, th)
            }
          }
        case _expr: Expression =>
          // 1. eval
          // 2. if th == FnTh -> call
          // 3. if arg.empty -> err not callable
          // 4. if !arg.empty -> find self get
          val (th, vName, stats) = evalExpr(ctx, scope, AnyTh, _expr)
          th match {
            case fth: FnTh =>
              val (retTh, retVName, callStats) = Invoker.invokeLambda(ctx, scope, vName, fth, argTasks.iterator)
              (retTh, retVName, stats ++ callStats)
            case _ => throw new RuntimeException("other need to call")
          }
      }
    case lambda: Lambda =>
      val lambdaTh = FnTh(Seq.empty, lambda.args.map(_.typeHint), AnyTh)
      if (!Invoker.checkAndInfer(ctx, mutable.HashMap.empty, th, lambdaTh)) {
        throw new RuntimeException(s"expected $th has $lambdaTh")
      }

      // FIXME: implement case for UnionTh
      val (argsTh, retTh) = th match {
        case fth: FnTh if fth.args.length == lambda.args.length =>
          (fth.args.zip(lambda.args).map {
            case (AnyTh, arg) => arg.typeHint
            case (ght: GenericTh, arg) => arg.typeHint
            case (th, _) => th
          }, fth.ret)
        case _ => (lambda.args.map(_.typeHint), AnyTh)
      }
      val _def = Def(
        "$def" + ctx.nextAnonId(),
        Lambda((lambda.args zip argsTh).map { case (arg, th) => Arg(arg.name, th) }, lambda.body),
        retTh)
      val header = evalDef(ctx, scope.mkChild(p => new FnScope(Some(p))), _def, mutable.HashMap.empty)

      val vName = "$l" + ctx.nextAnonId()
      scope.addLocal(ctx, vName, header.th)

      (header.th, vName, Seq(Ast2.Store(init = true, Ast2.Id(vName), Ast2.Id(header.lowName))))
    case andOr: AndOr =>
      val lowId = Ast2.Id("$andOr" + ctx.nextAnonId())
      scope.addLocal(ctx, lowId.v, thBool)

      val (leftTh, leftName, leftStats) = evalExpr(ctx, scope, thBool, andOr.left)
      if (!thBool.isEqual(ctx, new mutable.HashMap(), leftTh))
        throw new RuntimeException(s"expected $thBool has $leftTh")

      val (rightTh, rightName, rightStats) = evalExpr(ctx, scope, thBool, andOr.right)
      if (!thBool.isEqual(ctx, new mutable.HashMap(), rightTh))
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
          expressions.map { expr => evalExpr(ctx, blockScope, AnyTh, expr)._3 }.flatten
        val (lastTh, vName, lastStat) = evalExpr(ctx, blockScope, th, last)

        val freeStats = blockScope.vars.map {
          case (vName, _) => Ast2.Free(Ast2.Id(vName))
        }.toSeq

        (lastTh, vName, stats ++ lastStat, freeStats)
      }

      val (condTh, condName, condStats) = evalExpr(ctx, scope, thBool, cond)
      if (!thBool.isEqual(ctx, new mutable.HashMap(), condTh))
        throw new RuntimeException(s"expected $thBool has $condTh")

      val (doTh, doName, doStats, doFree) = evalBlock(_do)
      val (elseTh, elseName, elseStats, elseFree) = evalBlock(_else)

      val actualTh =
        if (doTh == thUndef && elseTh == thUndef) thNil
        else if (doTh == thUndef) elseTh
        else if (elseTh == thUndef) doTh
        else if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), doTh, elseTh))
          UnionTh(Seq(doTh, elseTh))
        else doTh

      val resultVar = "_i" + ctx.nextAnonId()
      scope.addLocal(ctx, resultVar, actualTh)

      val doStore = if (doTh != thUndef) Seq(Ast2.Store(init = true, Ast2.Id(resultVar), Ast2.Id(doName))) else Seq.empty
      val elseStore = if (elseTh != thUndef) Seq(Ast2.Store(init = true, Ast2.Id(resultVar), Ast2.Id(elseName))) else Seq.empty

      (actualTh,
        resultVar,
        condStats :+ Ast2.If(
          Ast2.Id(condName),
          doStats ++ doStore ++ doFree,
          elseStats ++ elseStore ++ elseFree))
    case While(cond, _do) =>
      val (condTh, condName, condStats) = evalExpr(ctx, scope, thBool, cond)
      val whileScope = scope.mkChild(p => new WhileScope(Some(p)))
      val stats =
        _do.foldLeft(Seq[Ast2.Stat]()) {
          case (stats, expr) => stats ++ evalExpr(ctx, whileScope, AnyTh, expr)._3
        }

      val freeStats = whileScope.vars.map {
        case (vName, _) => Ast2.Free(Ast2.Id(vName))
      }

      (thNil, null, Seq(Ast2.While(Ast2.Id(condName), condStats, stats ++ freeStats)))
    case Unless(expr, isSeq) =>
      // x: Int | String | Float | None | Baz[t] = ...
      //
      // y: Bar | Int | String = x unless
      //   is Float | None do return
      //   is Baz[t] do 0
      //   is s: String do s * 2
      //
      // is covered:
      //   Float | None => Undef
      //   Baz[t]       => Int
      //   String       => String
      // not covered:
      //   Int
      // infered:       Int | String
      // user required: Bar | Int | String
      val (exprTh, exprName, exprStats) = evalExpr(ctx, scope, AnyTh, expr)
      val exprUnionVariants = exprTh match {
        case uth: UnionTh => uth.seq
        case sth: ScalarTh =>
          ctx.findType(sth.name, sth.mod) match {
            case (_, ud: UnionDecl) => ud.variants.map(v => v.spec(makeSpecMap(ud.params, sth.params)))
            case _ => Seq(sth)
          }
        case th@_ => Seq(th)
      }
      val retValName = "_w" + ctx.nextAnonId()

      def evalIs(is: Is): (TypeHint, String, Seq[Ast2.Stat], Seq[Ast2.Stat]) = {
        val blockScope = scope.mkChild(p => new BlockScope(Some(p)))

        is.vName.foreach(vName => blockScope.addLocal(ctx, vName.value, is.typeRef))

        val (expressions, last) = is._do match {
          case Seq() => (Seq.empty, lNone())
          case s => (s.dropRight(1), s.last)
        }

        val stats =
          expressions.flatMap { expr => evalExpr(ctx, blockScope, AnyTh, expr)._3 }
        val (lastTh, vName, lastStat) = evalExpr(ctx, blockScope, th, last)

        val freeStats = blockScope.vars
          //.filter({ case (vName, _) => Some(vName) != is.vName.map(_.value) }) // free performed on backend side
          .map { case (vName, _) => Ast2.Free(Ast2.Id(vName)) }.toSeq

        (lastTh, vName, stats ++ lastStat, freeStats)
      }

      val lowIsSeq = isSeq.map { is =>
        if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), exprTh, is.typeRef))
          throw new RuntimeException(s"expected ${is.typeRef} as union member of $exprTh but no")

        val (bth, bvName, stats, freeStats) = evalIs(is)

        val storeRet =
          if (bth == thUndef) Seq()
          else Seq(Ast2.Store(init = true, Ast2.Id(retValName), Ast2.Id(bvName)))

        (is.typeRef, bth, Ast2.Is(is.vName.map(_.value), is.typeRef.toLow(ctx), stats ++ storeRet ++ freeStats))
      }

      def inferSuperType(variants: Seq[TypeHint]): TypeHint = {
        if (variants.length == 1) return variants.head

        val result = new mutable.ListBuffer[TypeHint]()
        val tmp = new mutable.HashMap[GenericTh, TypeHint]()

        variants.foreach { v =>
          if (result.forall(th => !Invoker.checkAndInfer(ctx, tmp, th, v)))
            result += v
        }

        UnionTh(result)
      }

      def inferDifferentialType(has: Seq[TypeHint], covered: TypeHint): UnionTh = {
        val result = new mutable.ListBuffer[TypeHint]()
        val tmp = new mutable.HashMap[GenericTh, TypeHint]()

        has.foreach { v =>
          if (!Invoker.checkAndInfer(ctx, tmp, covered, v))
            result += v
        }

        UnionTh(result)
      }


      val coveredType = inferSuperType(lowIsSeq.map(_._1))
      val mappedType = lowIsSeq.map(_._2).filter(x => x != thUndef)
      val differentialType = inferDifferentialType(exprUnionVariants, coveredType)
      val overallType = inferSuperType(differentialType.seq ++ mappedType)

      val retValTh = th match {
        case AnyTh => overallType
        case expectedTh =>
          if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), expectedTh, overallType))
            throw new RuntimeException(s"expected $expectedTh has $overallType")
          expectedTh
      }

      scope.addLocal(ctx, retValName, retValTh)
      (retValTh, retValName, exprStats ++ Seq(Ast2.Unless(retValName, Ast2.Id(exprName), lowIsSeq.map(_._3))))
    case Store(typeHint, to, what) =>
      // x: Int = 5 # ok
      // x = 6 # ok
      // x.y: Int = 8 # fail
      // x.y = 8 # ok
      val toVarName = to.head.value

      typeHint match {
        case AnyTh =>
          scope.findVarOpt(toVarName) match {
            case None =>
              if (to.length != 1) throw new RuntimeException(s"variable with name $toVarName not found")

              val (whatTh, whatName, whatStats) =
                evalExpr(ctx, scope, AnyTh, what)

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
                evalExpr(ctx, scope, toTh, what)

              if (!Invoker.checkAndInfer(ctx, new mutable.HashMap(), toTh, whatTh))
                throw new RuntimeException(s"expected $toTh has $whatTh")

              (thNil, toVarName, whatStats :+ Ast2.Store(init = false,
                Ast2.Id(toVarName, to.drop(1).map(v => v.value)),
                Ast2.Id(whatName)))
          }
        case th =>
          if (to.length != 1) throw new RuntimeException("type hint unexpected here")

          if (scope.findVarOpt(to.head.value) != None)
            throw new RuntimeException(s"var with name $toVarName already declared")

          val (whatTh, whatName, whatStats) =
            evalExpr(ctx, scope, th, what)

          scope.addLocal(ctx, toVarName, th)

          //FIXME: check types compatibility
          (thNil, toVarName, whatStats :+ Ast2.Store(init = true, Ast2.Id(toVarName), Ast2.Id(whatName)))
      }
    case Ret(optExpr) =>
      optExpr match {
        case Some(expr) =>
          val (actualTh, vName, lowStats) = evalExpr(ctx, scope, th, expr)

          scope.setRet(actualTh)

          if (actualTh == thNil) {
            (thUndef, null, lowStats :+ Ast2.Ret(None))
          } else {
            (thUndef, null, lowStats :+ Ast2.Ret(Some(vName)))
          }
        case None =>
          (thUndef, null, Seq(Ast2.Ret(None)))
      }

    case bc@(Break() | Continue()) =>
      def findWhileScope(sc: Scope): Option[WhileScope] =
        sc match {
          case ws: WhileScope => Some(ws)
          case fs: FnScope => None
          case bs: BlockScope => bs.parent.flatMap(sc => findWhileScope(sc))
        }

      findWhileScope(scope).getOrElse(throw new RuntimeException("no while for break or continue"))

      bc match {
        case Break() =>
          (thUndef, null, Seq(Ast2.Break()))
        case Continue() =>
          (thUndef, null, Seq(Ast2.Continue()))
      }
  }

  def evalDef(ctx: TContext, scope: FnScope, _def: Def, specMap: mutable.HashMap[GenericTh, TypeHint]): DefHeader = {
    val deep = ctx.inferStack.length + ctx.deep + 1
    val fn = if (specMap.isEmpty) _def else _def.spec(specMap, ctx)
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
      arg.typeHint.assertNotAny(s"Expected type hint for arg ${arg.name}")
    }

    (fn.lambda.args zip argsTh).foreach {
      case (arg, th) => scope.addParam(ctx, arg.name, th)
    }

    val (closure, retTh, code) =
      fn.lambda.body match {
        case llVm(code) =>
          fn.retTh.assertNotAny("Expected type hint for return value of LLVM function")
          (Seq[(String, ClosureType)](), fn.retTh, Ast2.LLCode(code))
        case AbraCode(seq) =>
          val bodyScope = scope.mkChild({ parent => new BlockScope(Some(parent)) })
          val (expressions, last) =
            if (seq.isEmpty) (Seq.empty, Ret(None))
            else (seq.dropRight(1), seq.last)

          val realLast = last match {
            case ret: Ret => ret
            case other => Ret(Some(other))
          }

          val stats = expressions.flatMap(expr => evalExpr(ctx, bodyScope, AnyTh, expr)._3)
          val (_, vName, lastStats) = evalExpr(ctx, bodyScope, fn.retTh, realLast)

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
        evalDef(ctx, new FnScope(None), fn, mutable.HashMap.empty)
    }
    ctx.selfDefs.foreach { case (_, seq) =>
      seq.foreach { fn =>
        if (fn.isNotGeneric)
          evalDef(ctx, new FnScope(None), fn, mutable.HashMap.empty)
      }
    }
  }
}
