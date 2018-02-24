package m3.typecheck

import m3.codegen.{Ast2, ConstGen, HexUtil}
import m3.parse.Ast0._
import m3.typecheck.Util._

import scala.collection.mutable

/**
  * Created by over on 27.09.17.
  */
object TypeChecker {
  def evalExpr(namespace: Namespace, scope: BlockScope, th: Option[ThAdvice], expr: Expression): (TypeHint, String, Seq[Ast2.Stat]) = expr match {
    case lId(value) =>
      scope.findVarOpt(value) match {
        case Some(vi) => (vi.th, value, Seq.empty)
        case None =>
          val d = namespace.defs.find(d => d.name == value)
            .getOrElse(throw new RuntimeException(s"no local var, argument or global function with name $value"))
          val header = namespace.inferCompatibleDef(d, null, {
            case (expected, fn) => evalDef(namespace, scope.mkChild(p => new FnScope(None)), expected, fn)
          })
          (header.th, header.name, Seq.empty)
      }
    case x: Literal =>
      val vName = "$l" + namespace.nextAnonId()
      val (litTh, lit) = x match {
        case lInt(value) =>
          val id = ConstGen.int(namespace.lowMod, value)
          (thInt, Ast2.Call(id, Seq.empty))
        case lFloat(value: String) =>
          val id = ConstGen.float(namespace.lowMod, value)
          (thFloat, Ast2.Call(id, Seq.empty))
        case lBoolean(value: String) =>
          val id = ConstGen.bool(namespace.lowMod, value)
          (thBool, Ast2.Call(id, Seq.empty))
        case lString(value: String) =>
          val id = ConstGen.string(namespace.lowMod, value)
          (thString, Ast2.Call(id, Seq.empty))
      }
      val realTh = inferCompatibleType(namespace, th, litTh)

      scope.addLocal(mut = false, vName, realTh)
      (realTh, vName, Seq(Ast2.Store(init = true, Ast2.Id(vName, Seq.empty), lit)))


    case Prop(from, props) =>
      val (eth, evName, lowCode) = evalExpr(namespace, scope, None, from)

      val actualTh = props.foldLeft(eth) {
        case (fth, fieldId) =>
          fth match {
            case sth: ScalarTh =>
              namespace.types.find(td => td.name == sth.name)
                .getOrElse(throw new RuntimeException(s"no such type ${sth.name}"))
              match {
                case sd: StructDecl =>
                  sd.fields.find(fd => fd.name == fieldId.value).get.th
                case _ => throw new RuntimeException(s"no such field ${fieldId.value} on type $fth")
              }
            case sth: StructTh =>
              sth.seq.find(f => f.name == fieldId.value).get.typeHint
            case _ => throw new RuntimeException(s"no such field $fieldId on type $fth")
          }
      }
      val vName = "$l" + namespace.nextAnonId()
      scope.addLocal(mut = false, vName, actualTh)
      (actualTh, vName, lowCode :+ Ast2.Store(init = true,
        Ast2.Id(vName),
        Ast2.Id(evName, props.map(_.value))))
    case Tuple(seq) =>
      val actualTh = th.getOrElse(throw new RuntimeException("typehint required")).toTh
      val argTasks = seq.map { arg =>
        new InferTask {
          override def infer(expected: Option[ThAdvice]) = {
            evalExpr(namespace, scope, expected, arg)
          }
        }
      }
      actualTh match {
        case sth: StructTh =>
          namespace.invokeAnonConstructor(scope, sth, argTasks.iterator)
        case _ => throw new RuntimeException(s"unexpected value of $actualTh here")
      }
    case SelfCall(params, fnName, self, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: Option[ThAdvice]) = {
          evalExpr(namespace, scope, expected, arg)
        }
      })
      self match {
        case id: lId =>
          // 1. find mod
          //   1.1 find fnName in mod
          // 2. find var
          //   2.1 find self for fnName on var
          val vi = scope.findVar(id.value)
          val selfTask = new InferTask {
            override def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat]) = (vi.th, id.value, Seq.empty)
          }
          namespace.invokeSelfDef(scope, fnName, params, vi.th, (selfTask +: argTasks).iterator, th, {
            case (expected, fn) => evalDef(namespace, scope.mkChild(p => new FnScope(None)), expected, fn)
          })
        case _expr: Expression =>
          val (selfTh, vName, lowStats) = evalExpr(namespace, scope, None, _expr)
          val selfTask = new InferTask {
            override def infer(expected: Option[ThAdvice]): (TypeHint, String, Seq[Ast2.Stat]) = (selfTh, vName, lowStats)
          }
          namespace.invokeSelfDef(scope, fnName, params, selfTh, (selfTask +: argTasks).iterator, th, {
            case (expected, fn) => evalDef(namespace, scope.mkChild(p => new FnScope(None)), expected, fn)
          })
      }
    case Call(params, expr, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: Option[ThAdvice]) = {
          evalExpr(namespace, scope, expected, arg)
        }
      })
      expr match {
        case id: lId =>
          // is it constructor?
          if (id.value.head.isUpper) {
            namespace.invokeConstructor(scope, id.value, params, argTasks.iterator)
          } else {
            // 1. find function
            // 2. find var
            if (namespace.hasDef(id.value)) {
              namespace.invokeDef(scope, id.value, params, argTasks.iterator, th, {
                case (expected, fn) => evalDef(namespace, scope.mkChild(p => new FnScope(None)), expected, fn)
              })
            } else {
              val vi = scope.findVarOpt(id.value)
                .getOrElse(throw new RuntimeException(s"no such var of function with name ${id.value}"))

              val fnTh =
                vi.th match {
                  case fth: FnTh => fth
                  case _ => throw new RuntimeException(s"${id.value} is not callable")
                }

              namespace.invokeLambda(scope, id.value, fnTh, argTasks.iterator)
            }
          }

        case _expr: Expression =>
          // 1. eval
          // 2. if th == FnTh -> call
          // 3. if arg.empty -> err not callable
          // 4. if !arg.empty -> find self get
          val (th, vName, stats) = evalExpr(namespace, scope, None, _expr)
          null
      }
    case lambda: Lambda =>
      val _def = Def(Seq.empty, "$def" + namespace.nextAnonId(), lambda, None)

      val advice = th match {
        case Some(fn: FnAdvice) => fn
        case _ => FnAdvice(lambda.args.map(arg => None), None)
      }

      val (header, lowDef) = evalDef(namespace, scope.mkChild(p => new FnScope(Some(p))), advice, _def)
      namespace.anonDefs.append(_def)
      namespace.lowMod.defineDef(lowDef)

      val vName = "$l" + namespace.nextAnonId()
      scope.addLocal(mut = false, vName, header.th)

      (header.th, vName, Seq(Ast2.Store(init = true, Ast2.Id(vName), Ast2.Id(_def.name))))
    case andOr: AndOr =>
      val lowId = Ast2.Id("$and" + namespace.nextAnonId())

      val (leftTh, leftName, leftStats) = evalExpr(namespace, scope, Some(adviceBool), andOr.left)
      if (leftTh != thBool) throw new RuntimeException(s"expected $thBool has $leftTh")

      val (rightTh, rightName, rightStats) = evalExpr(namespace, scope, Some(adviceBool), andOr.right)
      if (rightTh != thBool) throw new RuntimeException(s"expected $thBool has $rightTh")

      val leftFullStats = leftStats :+ Ast2.Store(init = true, lowId, Ast2.Id(leftName))
      val rightFullStats = rightStats :+ Ast2.Store(init = true, lowId, Ast2.Id(rightName))

      val low =
        andOr match {
          case and: And => Ast2.And(lowId, leftFullStats, rightFullStats)
          case or: Or => Ast2.Or(lowId, leftFullStats, rightFullStats)
        }

      (thBool, lowId.v, Seq(low))
    case If(cond, _do, _else) =>
      def evalBlock(seq: Seq[Expression]): (TypeHint, String, Seq[Ast2.Stat]) = {
        val blockScope = scope.mkChild(p => new BlockScope(Some(p)))
        val (expressions, last) = (seq.dropRight(1), seq.last)

        val stats =
          expressions.map { expr => evalExpr(namespace, blockScope, None, expr)._3 }.flatten
        val (lastTh, vName, lastStat) = evalExpr(namespace, blockScope, th, last)

        (lastTh, vName, stats ++ lastStat)
      }

      val (condTh, condName, condStats) = evalExpr(namespace, scope, Some(adviceBool), cond)
      if (condTh != thBool) throw new RuntimeException(s"expected $thBool has $condTh")

      val (doTh, doName, doStats) = evalBlock(_do)
      val (elseTh, elseName, elseStats) = evalBlock(_else)

      val actualTh =
        if (doTh != elseTh) UnionTh(Seq(doTh, elseTh))
        else doTh

      val resultVar = "_i" + namespace.nextAnonId()
      scope.addLocal(mut = false, resultVar, actualTh)

      (actualTh,
        resultVar,
        condStats :+ Ast2.If(
          Ast2.Id(condName),
          doStats :+ Ast2.Store(init = true, Ast2.Id(resultVar), Ast2.Id(doName)),
          elseStats :+ Ast2.Store(init = true, Ast2.Id(resultVar), Ast2.Id(elseName))))
    case While(cond, _do) =>
      val (condTh, condName, condStats) = evalExpr(namespace, scope, Some(adviceBool), cond)
      val stats =
        _do.foldLeft(Seq[Ast2.Stat]()) {
          case (stats, expr) => stats ++ evalExpr(namespace, scope, None, expr)._3
        }

      (thNil, null, Seq(Ast2.While(Ast2.Id(condName), condStats, stats)))
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
            evalExpr(namespace, scope, th.toAdviceOpt(mutable.HashMap()), what)

          scope.addLocal(mut = true, toVarName, th)

          //FIXME: check types compatibility
          (th, toVarName, whatStats :+ Ast2.Store(init = true, Ast2.Id(toVarName), Ast2.Id(whatName)))
        case None =>
          scope.findVarOpt(toVarName) match {
            case None =>
              if (to.length != 1) throw new RuntimeException(s"variable with name $toVarName not found")

              val (whatTh, whatName, whatStats) =
                evalExpr(namespace, scope, None, what)

              scope.addLocal(mut = true, toVarName, whatTh)

              (whatTh, toVarName, whatStats :+ Ast2.Store(init = true, Ast2.Id(toVarName), Ast2.Id(whatName)))
            case Some(toVar) =>
              val toTh =
                to.drop(1).foldLeft(toVar.th) {
                  case (fth, fieldId) =>
                    fth match {
                      case sth: ScalarTh =>
                        namespace.types.find(td => td.name == sth.name)
                          .getOrElse(throw new RuntimeException(s"no such type ${sth.name}"))
                        match {
                          case sd: StructDecl =>
                            sd.fields.find(fd => fd.name == fieldId.value).get.th
                          case _ => throw new RuntimeException(s"no such field $fieldId on type $fth")
                        }
                      case sth: StructTh =>
                        sth.seq.find(f => f.name == fieldId.value).get.typeHint
                      case _ => throw new RuntimeException(s"no such field $fieldId on type $fth")
                    }
                }

              val (whatTh, whatName, whatStats) =
                evalExpr(namespace, scope, toTh.toAdviceOpt(mutable.HashMap.empty), what)

              if (whatTh != toTh)
                toTh match {
                  case sth: ScalarTh =>
                    namespace.types.find(td => td.name == sth.name)
                      .getOrElse(throw new RuntimeException(s"no such type $sth")) match {
                      case ud: UnionDecl =>
                        if (!ud.variants.contains(whatTh))
                          throw new RuntimeException(s"expected $toTh has $whatTh")
                      case _ => throw new RuntimeException(s"expected $toTh has $whatTh")
                    }
                  case uth: UnionTh =>
                    if (!uth.seq.contains(whatTh))
                      throw new RuntimeException(s"expected $toTh has $whatTh")
                  case _ => throw new RuntimeException(s"expected $toTh has $whatTh")
                }

              (toTh, toVarName, whatStats :+ Ast2.Store(init = false,
                Ast2.Id(toVarName, to.drop(1).map(v => v.value)),
                Ast2.Id(whatName)))
          }
      }
    case Ret(optExpr) =>
      optExpr match {
        case Some(expr) =>
          val (actualTh, vName, lowStats) = evalExpr(namespace, scope, th, expr)
          scope.setRet(actualTh)
          (thNil, "", lowStats :+ Ast2.Ret(Some(vName)))
        case None =>
          (thNil, "", Seq(Ast2.Ret(None)))
      }
  }

  def evalDef(namespace: Namespace, scope: FnScope, advice: FnAdvice, fn: Def): (DefHeader, Ast2.Def) = {
    val argsTh = (advice.args zip fn.lambda.args).map {
      case (adv, arg) =>
        (adv, arg.typeHint) match {
          case (_, Some(th)) => th
          case (Some(ad), None) =>
            ad.toThOpt.getOrElse(throw new RuntimeException(s"Expected type hint for arg ${arg.name}"))
          case (None, None) => throw new RuntimeException(s"Expected type hint for arg ${arg.name}")
        }
    }

    (fn.lambda.args zip argsTh).foreach {
      case (arg, th) => scope.addParam(arg.name, th)
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

          val stats = expressions.map(expr => evalExpr(namespace, bodyScope, th = None, expr)._3).flatten
          val (_, vName, lastStats) = evalExpr(namespace, bodyScope,
            fn.retTh.flatMap(th => th.toAdviceOpt(mutable.HashMap.empty)), realLast)

          val lowRetStat = lastStats.last.asInstanceOf[Ast2.Ret]

          val freeStats = bodyScope.vars
            .filter {
              case (vName, _) => Some(vName) != lowRetStat.lit
            }
            .map {
              case (vName, _) => Ast2.Free(Ast2.Id(vName))
            }

          val vars = downToLow(namespace, scope.down(root = true))
          val closure = upToClosure(scope.closures.toSeq)

          val retTh = scope.retTypes match {
            case Seq() => thNil
            case Seq(th) => th
            case seq => UnionTh(seq)
          }

          (closure, retTh, Ast2.AbraCode(vars, stats ++ lastStats.dropRight(1) ++ freeStats ++ lastStats.takeRight(1)))
      }

    val header = DefHeader(namespace.pkg, fn.name, FnTh(closure.map(_._2), argsTh, retTh))
    val lowType = header.th.toLow(namespace)
    val lowArgs = fn.lambda.args.map(_.name)
    val lowClosure = closure.map(_._1)

    val lowDef = Ast2.Def(fn.name, lowType, lowClosure, lowArgs, code, isAnon = false)

    (header, lowDef)
  }

  def isNeedEvalDef(namespace: Namespace, fn: Def): Boolean = {
    val from = if (fn.isSelf) namespace.inferedSelfDefs else namespace.inferedDefs
    !from.contains(DefSpec(fn.name, Seq.empty))
  }

  def infer(namespace: Namespace): Unit = {
    // non generic functions only
    namespace.defs.filter(d => !d.isGeneric).foreach { fn =>
      if (isNeedEvalDef(namespace, fn)) {
        val (header, lowDef) = evalDef(namespace, new FnScope(None), FnAdvice(
          args = fn.lambda.args.map(x => None),
          ret = None
        ), fn)
        val to = if (fn.isSelf) namespace.inferedSelfDefs else namespace.inferedDefs
        to.put(fn.signature, header)
        namespace.lowMod.defineDef(lowDef)
      }
    }
  }
}
