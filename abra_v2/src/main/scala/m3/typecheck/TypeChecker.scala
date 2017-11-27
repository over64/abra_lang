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
      (scope.findVar(value).th, value, Seq.empty)
    case x: Literal =>
      val vName = "$l" + namespace.nextAnonId()
      val (actualTh, lit) = x match {
        case lInt(value) =>
          val fnName = "cInt" + namespace.nextAnonId().toString
          val _def = ConstGen.genBoolConst(fnName, value)
          namespace.lowDefs.put(fnName, _def)
          (thInt, Ast2.Call(Ast2.Id(fnName), Seq.empty))
        case lFloat(value: String) =>
          val fnName = "cFloat" + namespace.nextAnonId().toString
          val _def = ConstGen.genBoolConst(fnName, value)
          namespace.lowDefs.put(fnName, _def)
          (thFloat, Ast2.Call(Ast2.Id(fnName), Seq.empty))
        case lBoolean(value: String) =>
          val fnName = "cBool" + namespace.nextAnonId().toString
          val _def = ConstGen.genBoolConst(fnName, value)
          namespace.lowDefs.put(fnName, _def)
          (thBool, Ast2.Call(Ast2.Id(fnName), Seq.empty))
        case lString(value: String) =>
          val fnName = "cInt" + namespace.nextAnonId()
          val (_def, code) = ConstGen.genStringConst(fnName, value)
          namespace.lowCode.append(code)
          namespace.lowDefs.put(fnName, _def)
          (thString, Ast2.Call(Ast2.Id(fnName), Seq.empty))
      }
      scope.addLocal(mut = false, vName, actualTh)
      (actualTh, vName, Seq(Ast2.Init(Ast2.Id(vName, Seq.empty), lit)))
    case Prop(from, prop) => null
    case Tuple(seq) =>
      // val z: (x: Int, y: Int) = (1, 2)
      null
    case SelfCall(params, fnName, self, args) =>
      self match {
        case id: lId =>
          // 1. find mod
          //   1.1 find fnName in mod
          // 2. find var
          //   2.1 find self for fnName on var
          null
        case _expr: Expression =>
          // find self fn where name == fnName
          null
      }
    case Call(params, expr, args) =>
      val argTasks = args.map(arg => new InferTask {
        override def infer(expected: Option[ThAdvice]) = {
          evalExpr(namespace, scope, expected, arg)
        }
      })
      expr match {
        case id: lId =>
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
      namespace.lowDefs.put(_def.name, lowDef)

      val vName = "$l" + namespace.nextAnonId()
      scope.addLocal(mut = false, vName, header.th)

      (header.th, vName, Seq(Ast2.Init(Ast2.Id(vName), Ast2.Id(_def.name))))
    case andOr: AndOr =>
      val lowId = Ast2.Id("$and" + namespace.nextAnonId())

      val (leftTh, leftName, leftStats) = evalExpr(namespace, scope, Some(adviceBool), andOr.left)
      if (leftTh != thBool) throw new RuntimeException(s"expected $thBool has $leftTh")

      val (rightTh, rightName, rightStats) = evalExpr(namespace, scope, Some(adviceBool), andOr.right)
      if (rightTh != thBool) throw new RuntimeException(s"expected $thBool has $rightTh")

      val leftFullStats = leftStats :+ Ast2.Init(lowId, Ast2.Id(leftName))
      val rightFullStats = rightStats :+ Ast2.Init(lowId, Ast2.Id(rightName))

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
        condStats :+ Ast2.Cond(
          Ast2.Id(condName),
          doStats :+ Ast2.Init(Ast2.Id(resultVar), Ast2.Id(doName)),
          elseStats :+ Ast2.Init(Ast2.Id(resultVar), Ast2.Id(elseName))))
    case Match(on, cases) => null
    case While(cond, _do) =>
      val (condTh, condName, condStats) = evalExpr(namespace, scope, Some(adviceBool), cond)
      val stats =
        _do.foldLeft(Seq[Ast2.Stat]()) {
          case (stats, expr) => stats ++ evalExpr(namespace, scope, None, expr)._3
        }

      (thNil, null, Seq(Ast2.While(Ast2.Id(condName), condStats, stats)))
    case Store(to, what) => null
      val toVarName = to.head.value
      val toVar = scope.findVar(toVarName)
      val toTh =
        to.drop(1).foldLeft(toVar.th) {
          case (th, id) => th match {
            case StructTh(fields) =>
              val f = fields.find(f => f.name == id.value).getOrElse(throw new RuntimeException(s"no such field ${id.value} in ${th}"))
              f.typeHint
            case ScalarTh(params, name, pkg) => null
            case _ => throw new RuntimeException(s"no such field ${id.value} in ${th}")
          }
        }

      val (whatTh, whatName, whatStats) =
        evalExpr(namespace, scope, toTh.toAdviceOpt(mutable.HashMap.empty), what)

      if (whatTh != toTh) throw new RuntimeException(s"expected $toTh has $whatTh")

      (toTh, toVarName, whatStats :+ Ast2.Init(Ast2.Id(toVarName), Ast2.Id(whatName)))
    case Ret(optExpr) =>
      optExpr match {
        case Some(expr) =>
          val (actualTh, vName, lowStats) = evalExpr(namespace, scope, th, expr)
          scope.setRet(actualTh)
          (thNil, "", lowStats :+ Ast2.Ret(Some(vName)))
        case None =>
          (thNil, "", Seq(Ast2.Ret(None)))
      }
    case Val(mut, name, typeHint, init) =>
      val advice = typeHint.flatMap(th => th.toAdviceOpt(mutable.HashMap.empty))
      val (actualTh, vName, stats) = evalExpr(namespace, scope, advice, init)

      scope.addLocal(mut, name, actualTh)

      (actualTh, name, stats :+ Ast2.Init(Ast2.Id(name), Ast2.Id(vName)))
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
          val (expressions, last) = (seq.dropRight(1), seq.last)

          val realLast = last match {
            case ret: Ret => ret
            case other => Ret(Some(other))
          }

          val stats = expressions.map(expr => evalExpr(namespace, bodyScope, th = None, expr)._3)
          val (_, vName, lastStat) = evalExpr(namespace, bodyScope,
            fn.retTh.flatMap(th => th.toAdviceOpt(mutable.HashMap.empty)), realLast)

          val vars = downToLow(namespace, scope.down(root = true))
          val closure = upToClosure(scope.closures.toSeq)

          val retTh = scope.retTypes match {
            case Seq() => thNil
            case Seq(th) => th
            case seq => UnionTh(seq)
          }

          (closure, retTh, Ast2.AbraCode(vars, (stats :+ lastStat).flatten))
      }

    val header = DefHeader(namespace.pkg, fn.name, FnTh(closure.map(_._2), argsTh, retTh))
    val lowArgs = (fn.lambda.args zip argsTh).map {
      case (arg, th) => Ast2.Field(arg.name, th.toLow(namespace))
    }
    val lowClosure = closure.map {
      case (vName, CLocal(th)) => Ast2.ClosureField(vName, Ast2.Local(th.toLow(namespace)))
      case (vName, CParam(th)) => Ast2.ClosureField(vName, Ast2.Param(th.toLow(namespace)))
    }

    val lowDef = Ast2.Def(fn.name, lowClosure, lowArgs, retTh.toLow(namespace), code, isAnon = false)

    (header, lowDef)
  }

  def isNeedEvalDef(namespace: Namespace, fn: Def): Boolean = {
    val from = if (fn.isSelf) namespace.inferedSelfDefs else namespace.inferedDefs
    !from.contains(DefSpec(fn.name, Seq.empty))
  }

  def infer(namespace: Namespace): Unit = {
    val root = new FnScope(None)
    // non generic functions only
    namespace.defs.filter(d => !d.isGeneric).foreach { fn =>
      if (isNeedEvalDef(namespace, fn)) {
        val (header, lowDef) = evalDef(namespace, root, FnAdvice(
          args = fn.lambda.args.map(x => None),
          ret = None
        ), fn)
        val to = if (fn.isSelf) namespace.inferedSelfDefs else namespace.inferedDefs
        to.put(fn.signature, header)
        namespace.lowDefs.put(fn.name, lowDef)
      }
    }
  }
}
