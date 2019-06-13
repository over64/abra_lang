package m3.typecheck

import m3.parse.Ast0._
import m3.parse.ParseMeta._
import m3.parse.{AstInfo, Level}
import m3.typecheck.Scope2._
import m3.typecheck.TCE.NoSuchSelfDef
import m3.typecheck.TCMeta._
import m3.typecheck.Utils._
import m3.typecheck.Builtin._

import scala.collection.mutable

case class PassContext(deep: Int, level: Level, module: Module, defStack: List[(Option[TypeHint], String)] = List.empty) {
  def deeperExpr() = this.copy(deep = this.deep + 1)

  def deeperDef(selfTh: Option[TypeHint], name: String): PassContext =
    this.copy(deep = this.deep + 1, defStack = (selfTh, name) :: this.defStack)

  def log(msg: String): Unit = println("\t" * deep + msg)
}

class MismatchLocal extends Exception
class TypeChecker2(ctx: PassContext,
                   genericSpec: (GenericTh, TypeHint) => TypeHint = (adv, th) => adv,
                   withTransaction: (() => Unit) => Unit = { callback => callback() }) {

  def isEqualSeq(expected: Seq[TypeHint], has: Seq[TypeHint]): Unit = {
    if (expected.length != has.length) throw new MismatchLocal
    (expected zip has).foreach { case (e, h) => isEqual(e, h) }
  }

  def isEqual(self: TypeHint, other: TypeHint): Unit =
    (self, other) match {
      case (th, AnyTh) => // ok
      case (AnyTh, th) => // ok
      case (adv: ScalarTh, th: ScalarTh) =>
        val (_, advMod, advDecl) = resolveType(ctx.level, ctx.module, adv)
        val (_, thMod, sthDecl) = resolveType(ctx.level, ctx.module, th)

        if (advMod.pkg != thMod.pkg || adv.name != th.name) throw new MismatchLocal
        if (adv.params.length != th.params.length) throw TCE.ParamsCountMismatch(th.location)
        isEqualSeq(adv.params, th.params)
      case (adv: UnionTh, th: UnionTh) =>
        isEqualSeq(adv.seq, th.seq)
      case (adv: StructTh, th: StructTh) =>
        isEqualSeq(adv.seq.map(_.typeHint), th.seq.map(_.typeHint))
      case (adv: FnTh, th: FnTh) =>
        isEqualSeq(adv.args, th.args)
        isEqual(adv.ret, th.ret)
      case (adv: GenericTh, th) =>
        genericSpec(adv, th) match {
          case specified: GenericTh =>
            if (th.isInstanceOf[GenericTh]) {
              val gth = th.asInstanceOf[GenericTh]
              if (specified.typeName != gth.typeName) throw new MismatchLocal
            } else throw new MismatchLocal
          case specifiedTh =>
            isEqual(specifiedTh, th)
        }
      case (adv, th) => throw new MismatchLocal
    }

  def checkUnionMember(ud: UnionDecl, adv: ScalarTh, th: TypeHint): Unit = {

    if (ud.params.length != adv.params.length) throw TCE.ParamsCountMismatch(adv.location)

    val specMapInternal = makeSpecMap(ud.params, adv.params)
    ud.variants.exists { udVariant =>
      try {
        withTransaction(() => isEqual(udVariant.spec(specMapInternal), th)); true
      } catch {
        case ex: Exception => false
      }
    }
  }

  def doCheck(expected: TypeHint, has: TypeHint): Unit =
    (expected, has) match {
      case (th, AnyTh) =>
      case (AnyTh, th) =>
      case (arraySize, sth: ScalarTh) if arraySize == thArraySize =>
        doCheck(thArraySizes, sth)
      case (array1: ScalarTh, array2: ScalarTh) if array1.isArray && array2.isArray =>
        val (_, _, decl1) = resolveType(ctx.level, ctx.module, array1)
        val (_, _, decl2) = resolveType(ctx.level, ctx.module, array2)

        decl1.getBuiltinArrayLen match {
          case None => // ok
          case has =>
            if (has != decl2.getBuiltinArrayLen)
              throw new MismatchLocal
        }

        isEqualSeq(array1.params, array2.params)
      case (adv: ScalarTh, sth: ScalarTh) =>
        val (_, advMod, advDecl) = resolveType(ctx.level, ctx.module, adv)
        val (_, sthMod, sthDecl) = resolveType(ctx.level, ctx.module, sth)
        if (advMod.pkg == sthMod.pkg && adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          isEqualSeq(adv.params, sth.params)
        }
        else {
          resolveType(ctx.level, ctx.module, adv) match {
            case (_, _, ud: UnionDecl) => checkUnionMember(ud, adv, sth)
            case _ => throw new MismatchLocal
          }
        }
      case (adv: ScalarTh, uth: UnionTh) => throw new MismatchLocal
      case (adv: ScalarTh, th) =>
        resolveType(ctx.level, ctx.module, adv) match {
          case (_, _, ud: UnionDecl) => checkUnionMember(ud, adv, th)
          case _ => throw new MismatchLocal
        }
      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant =>
          try {
            withTransaction(() => isEqual(advVariant, th)); true
          } catch {
            case ex: MismatchLocal => false
          }
        }
      case (adv: UnionTh, th: UnionTh) =>
        if (adv.seq.length != th.seq.length) throw new MismatchLocal
        (adv.seq zip th.seq).forall {
          case (v1, v2) =>
            try {
              doCheck(v1, v2); true
            } catch {
              case ex: MismatchLocal => false
            }
        }
      case (adv, th) => isEqual(expected, has)
    }

  def check(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
    try {
      withTransaction(() => doCheck(expected, has))
    } catch {
      case ex: MismatchLocal =>
        if (expected == thArraySize)
          throw TCE.ArraySizeExpected(location, thArraySizes, has)
        else
          throw TCE.TypeMismatch(location, expected, has)
    }
  }
}

class TypeInfer(val ctx: PassContext,
                val onSpec: (TypeInfer, GenericTh, TypeHint) => Unit = (self, gth, th) => {}) {

  var specMap = mutable.HashMap[GenericTh, TypeHint]()

  def withTransaction(lambda: () => Unit): Unit = {
    val backupSpecMap = mutable.HashMap[GenericTh, TypeHint](specMap.toSeq: _*)

    try {
      lambda()
    } catch {
      case ex: Exception =>
        specMap = backupSpecMap
        throw ex;
    }
  }

  def infer(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
    new TypeChecker2(
      ctx,
      genericSpec = (adv, th) => {
        specMap.get(adv) match {
          case Some(specified) => specified
          case None => specMap.put(adv, th); onSpec(this, adv, th); th
        }
      },
      withTransaction).check(location, expected, has)
  }
}

case class Equation(selfType: (Seq[AstInfo], GenericTh),
                    fnName: String,
                    args: Seq[(Seq[AstInfo], TypeHint)],
                    ret: (Seq[AstInfo], TypeHint)) {
  override def toString: String = selfType + " :: " + fnName + args.mkString("(", ", ", ")") + " -> " + ret
}

class Equations(val typeParams: mutable.ListBuffer[GenericTh] = mutable.ListBuffer()) {
  val eqSeq = mutable.ListBuffer[Equation]()
  var idSeq = 0

  def nextAnonType(): GenericTh = {
    idSeq += 1
    GenericTh("a" + idSeq, isAnon = true)
  }

  def addEq(eq: Equation): Unit =
    eqSeq += eq

  override def toString: String = if (eqSeq.length < 2) eqSeq.mkString("[ ", "", " ]") else eqSeq.mkString("[\n\t", "\n\t", "\n]")
}

class TypeCheckPass {
  trait InferTask {
    def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint)
  }

  def checkTypeDecl(ctx: PassContext): Unit = {
    ctx.module.types.values.foreach { td =>
      if (Builtin.isDeclaredBuiltIn(td.name))
        throw TCE.BuiltinTypeRedeclare(td.location, td.name)

      ctx.module.imports.seq.find(ie => ie.withTypes.contains(td.name)) match {
        case Some(ie) => throw TCE.TypeAlreadyLocalDefined(ie.location, td.name)
        case None =>
      }

      td match {
        case sc: ScalarDecl =>
        case st: StructDecl =>
          val fieldNames = st.fields.map(_.name)
          if (fieldNames.length != fieldNames.toSet.size) throw TCE.FieldNameNotUnique(st.location)
          st.fields.foreach(f => f.th.assertCorrect(ctx, st.params))
        case un: UnionDecl =>
          if (un.variants.length != un.variants.toSet.size)
            throw TCE.UnionMembersNotUnique(un.location)
      }
    }
  }

  class EqInfer(val ctx: PassContext, val callLocation: Seq[AstInfo], myEq: Equations) {
    case class ApplyIndex(var idx: Int)

    def printInstance(eq: Equation, fth: FnTh) = {
      val instanceLike = fth.args.head + s" :: ${eq.fnName}" + fth.args.drop(1).mkString("(", ", ", ")") + " -> " + fth.ret
      s"$eq   like   $instanceLike"
    }

    val eqMap = mutable.HashMap[Equation, (FnTh, ApplyIndex, EqInfer)]()
    val tInfer = new TypeInfer(ctx)

    def anonSpec(th: TypeHint): TypeHint =
      th.spec(tInfer.specMap)

    def infer(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
      tInfer.infer(location, expected, has)

      var retry = true
      while (retry) {
        retry = false
        myEq.eqSeq.foreach { eq =>
          if (eqMap.get(eq).isEmpty) {
            val (selfLocation, selfType) = eq.selfType
            val specSelf = anonSpec(selfType)

            if (!specSelf.isInstanceOf[GenericTh] && !specSelf.containsAny) {
              ctx.log(s"instance eq $eq over $specSelf")

              val (fnTh, fnEq) = Invoker.findSelfDef(ctx, selfLocation, specSelf, eq.fnName)

              if (fnTh.args.drop(1).length != eq.args.length)
                throw TCE.ArgsCountMismatch(selfLocation, fnTh.args.drop(1).length, eq.args.length)

              ctx.log("instance   " + printInstance(eq, fnTh))

              val next = new EqInfer(ctx.deeperExpr(), selfLocation, fnEq)

              ctx.log(s"infer     ${printInstance(eq, fnTh)}   where   self ${fnTh.args.head} => $specSelf")
              next.infer(location, fnTh.args.head, specSelf)

              eqMap.put(eq, (fnTh, ApplyIndex(1), next))
            }
          }
        }

        var changed = true
        while (changed) {
          changed = false

          eqMap.foreach { case (eq, (fnTh, applyIdx, next)) =>
            def idxToArg(idx: Int) =
              idx match {
                case 1 => "ret"
                case i => s"arg(${i - 1})"
              }

            ((fnTh.args.head +: fnTh.ret +: fnTh.args.drop(1)) zip (eq.selfType +: eq.ret +: eq.args))
              .zipWithIndex
              .drop(applyIdx.idx).take(1)
              .foreach { case ((argTh, eqTh), idx) =>
                eqTh match {
                  case (_, generic: GenericTh) if generic.isAnon =>
                    val argSpec = next.anonSpec(argTh)
                    if (!argSpec.containsAny) {
                      ctx.log(s"anon       ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $eqTh => $argSpec")
                      tInfer.infer(location, generic, argSpec)
                      applyIdx.idx += 1
                      retry = true
                      changed = true
                    }
                  case (glocation, generic: GenericTh) =>
                    tInfer.specMap.get(generic).foreach { specified =>
                      ctx.log(s"infer      ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $eqTh @ $specified => $argTh")
                      next.infer(glocation ++ location, argTh, specified)
                      applyIdx.idx += 1
                      changed = true
                    }
                  case (othetLocation, otherTh) =>
                    val otherSpec = next.anonSpec(otherTh)
                    if (!otherSpec.containsAny) {
                      ctx.log(s"infer      ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $otherSpec => $argTh")
                      tInfer.infer(othetLocation ++ location, argTh, otherSpec)
                      applyIdx.idx += 1
                      changed = true
                    }
                }
              }
          }
        }
      }
    }

    def drainEq(idSeq: Int): (Int, Seq[Equation]) = {
      var anonSeq = idSeq
      val local = myEq.eqSeq.filter(eq => !eqMap.contains(eq)).map { eq =>
        def spec[T <: TypeHint](locAndTh: (Seq[AstInfo], T)) = {
          val (location, th) = locAndTh
          (location ++ callLocation, th.spec(tInfer.specMap, gth => {
            anonSeq += 1
            val anon = GenericTh("a" + anonSeq, true)
            tInfer.specMap.put(gth, anon)
            anon
          }).asInstanceOf[T])
        }

        Equation(
          spec(eq.selfType),
          eq.fnName,
          eq.args.map(th => spec(th)),
          spec(eq.ret)
        )
      }

      eqMap.values.map(_._3).foldLeft((anonSeq, local)) { case ((idSeq, eqList), next) =>
        def addCallLocation[T <: TypeHint](eqMember: (Seq[AstInfo], T)) =
          (eqMember._1 ++ callLocation, eqMember._2)

        val (nextIdSeq, nextList) = next.drainEq(idSeq)
        val nextListWithLoc = nextList.map { eq =>
          Equation(
            addCallLocation(eq.selfType),
            eq.fnName,
            eq.args.map(arg => addCallLocation(arg)),
            addCallLocation(eq.ret)
          )
        }
        (nextIdSeq, eqList ++ nextListWithLoc)
      }
    }
  }

  object Invoker {
    def isSelfApplicable(ctx: PassContext, selfTh: TypeHint, selfArgTh: TypeHint): Boolean =
      try {
        new TypeInfer(ctx.deeperExpr()).infer(Seq(selfTh.location), selfTh, selfArgTh); true
      } catch {
        case ex: Exception => false
      }

    def findSelfDef(ctx: PassContext, location: Seq[AstInfo], selfTh: TypeHint, fnName: String): (FnTh, Equations) = {
      selfTh match {
        case gth: GenericTh =>
          throw new RuntimeException("Unexpected to be here")
        case _ =>
          ctx.module.selfDefs.get(fnName).flatMap { defs =>
            defs.find(d => isSelfApplicable(ctx, d.lambda.args.head.typeHint, selfTh))
          } match {
            case Some(fn) =>
              if (fn.getTypeHintOpt == None) passDef(ctx.deeperDef(Some(selfTh), fnName), fn)
              (fn.getTypeHint, fn.getEquations)
            case None =>
              ctx.module.imports.seq.flatMap { ie =>
                val mod = ctx.level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
                mod.selfDefs.getOrElse(fnName, Seq()).map(fn => (ie, fn))
              }.find { case (ie, fn) => isSelfApplicable(ctx, fn.lambda.args.head.typeHint.moveToMod(Seq(ie)), selfTh) } match {
                case Some((ie, fn)) =>
                  (fn.getTypeHint, fn.getEquations)
                case None =>
                  (resolveBuiltinSelfDef(location, selfTh, fnName), new Equations())
              }
          }
      }
    }

    def findSelfDefOpt(ctx: PassContext, location: Seq[AstInfo], selfTh: TypeHint, fnName: String): Option[(FnTh, Equations)] =
      try {
        Some(findSelfDef(ctx, location, selfTh, fnName))
      } catch {
        case _: NoSuchSelfDef => None
      }

    def invokePrototype(ctx: PassContext, eqCaller: Equations, eqCallee: Equations, location: AstInfo,
                        retTh: TypeHint, fnTh: FnTh, args: Seq[InferTask]): TypeHint = {

      if (fnTh.args.length != args.length)
        throw TCE.ArgsCountMismatch(Seq(location), fnTh.args.length, args.length)

      val eqInfer = new EqInfer(ctx, Seq(location), eqCallee)

      if (fnTh.ret.isInstanceOf[GenericTh] && fnTh.ret.asInstanceOf[GenericTh].isAnon)
        eqInfer.infer(Seq(location), fnTh.ret, retTh)
      else
        eqInfer.infer(Seq(location), retTh, fnTh.ret)

      (fnTh.args zip args).foreach { case (defArgTh, argTask) =>
        //println(eqInfer.tInfer.specMap)
        val (argLocation, argTh) = argTask.infer(eqInfer.ctx, eqCaller, eqInfer.anonSpec(defArgTh))
        //println(argTh)
        eqInfer.infer(Seq(argLocation), defArgTh, argTh)
      }

      val (newIdSeq, eqList) = eqInfer.drainEq(eqCaller.idSeq)

      eqCaller.idSeq = newIdSeq
      eqCaller.eqSeq ++= eqList

      eqInfer.anonSpec(fnTh.ret)
    }

    def invokeDef(ctx: PassContext, eqCaller: Equations, location: AstInfo,
                  retTh: TypeHint, toCall: Def, args: Seq[InferTask]): TypeHint =
      invokePrototype(ctx, eqCaller, toCall.getEquations, location, retTh, toCall.getTypeHint, args)

    def invokeSelfDef(ctx: PassContext, eqCaller: Equations, location: AstInfo,
                      retTh: TypeHint, fnName: String, self: InferTask, args: Seq[InferTask]): TypeHint = {

      val (selfLocation, selfTh) = self.infer(ctx.deeperExpr(), eqCaller, AnyTh)

      selfTh match {
        // FIXME: try to resolve generic function locally at first?
        // FIXME: variant 2 - declare self function on ScalarTh only
        case gth: GenericTh =>
          val argsTh = args.map(argTask => argTask.infer(ctx.deeperExpr(), eqCaller, AnyTh))
            .map { case (loc, th) => (Seq(loc), th) }
          val eqRet = if (retTh != AnyTh) retTh else eqCaller.nextAnonType()

          eqCaller.addEq(Equation((Seq(selfLocation), gth), fnName, argsTh, (Seq(location), eqRet)))
          eqRet
        case _ =>
          val (fnTh, fnEq) = findSelfDef(ctx, Seq(location), selfTh, fnName)
          invokePrototype(ctx, eqCaller, fnEq, location, retTh, fnTh, new InferTask {
            override def infer(ctx: PassContext, eq: Equations, th: TypeHint) = (selfLocation, selfTh)
          } +: args)
      }
    }
  }


  def passExpr(ctx: PassContext, scope: Scope2.BlockScope, eq: Equations, th: TypeHint, expr: Expression): TypeHint = {
    def foldFields(from: TypeHint, fields: Seq[lId]): TypeHint =
      fields.foldLeft(from) {
        case (sth: ScalarTh, fieldId) =>
          resolveType(ctx.level, ctx.module, sth) match {
            case (_, _, sd: StructDecl) =>
              sd.fields
                .find(fd => fd.name == fieldId.value)
                .getOrElse(throw TCE.NoSuchField(fieldId.location, sth, fieldId.value))
                .th.spec(makeSpecMap(sd.params, sth.params))
            case (_, _, td) => throw TCE.NoSuchField(fieldId.location, sth, fieldId.value)
          }
        case (sth: StructTh, fieldId) =>
          sth.seq
            .find(f => f.name == fieldId.value)
            .getOrElse(throw TCE.NoSuchField(fieldId.location, sth, fieldId.value)).typeHint
        case (th, fieldId) =>
          throw TCE.NoSuchField(fieldId.location, th, fieldId.value)
      }

    def tryInt(lit: Literal, fn: String => Unit, th: TypeHint,
               or: TCE.IntegerLiteralOutOfRange => TypeHint = ex => throw ex): TypeHint =
      try {
        fn(lit.value); th
      } catch {
        case _: NumberFormatException => or(TCE.IntegerLiteralOutOfRange(lit.location, Some(th)))
      }

    def tryFloat(lit: Literal, fn: String => Any, th: TypeHint,
                 or: TCE.FloatingLiteralOutOfRange => TypeHint = ex => throw ex): TypeHint =
      fn(lit.value) match {
        case f: Float if f == Float.PositiveInfinity || f == Float.NegativeInfinity =>
          or(TCE.FloatingLiteralOutOfRange(lit.location, Some(th)))
        case d: Double if d == Double.PositiveInfinity || d == Double.NegativeInfinity =>
          or(TCE.FloatingLiteralOutOfRange(lit.location, Some(th)))
        case _ => th
      }

    ctx.log(s"pass expr $expr")
    val exprTh = expr match {
      case lit: lInt =>
        def default() =
          tryInt(lit, _.toInt, thInt, { _ =>
            tryInt(lit, _.toLong, thLong, { _ =>
              throw TCE.IntegerLiteralOutOfRange(lit.location, None)
            })
          })

        if (th == thLong) tryInt(lit, _.toLong, thLong)
        else if (th == thInt) tryInt(lit, _.toInt, thInt)
        else if (th == thShort) tryInt(lit, _.toShort, thShort)
        else if (th == thByte) tryInt(lit, _.toByte, thByte)
        else default()
      case lit: lFloat =>
        def default() =
          tryFloat(lit, _.toFloat, thFloat, { _ =>
            tryFloat(lit, _.toDouble, thDouble, { _ =>
              throw TCE.FloatingLiteralOutOfRange(lit.location, None)
            })
          })

        if (th == thDouble) tryFloat(lit, _.toDouble, thDouble)
        else if (th == thFloat) tryFloat(lit, _.toFloat, thFloat)
        else default()
      case _: lBoolean => thBool
      case _: lString => thString
      case _: lNone => thNil
      case id: lId =>
        scope.findVar(id.value) match {
          case Some((varTh, vt)) =>
            id.setVarLocation(vt)
            varTh
          case None =>
            val fn = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchSymbol(id.location, id.value))

            if (fn.getTypeHintOpt == None) passDef(ctx.deeperDef(None, id.value), fn)
            fn.getTypeHint
        }
      case Ret(opt) =>
        val retTh = opt.map(exp => passExpr(ctx.deeperExpr(), scope, eq, th, exp)).getOrElse(thNil)
        scope.addRetType(retTh)
        thUnreachable
      case call@Call(expr, args) =>
        expr match {
          case id: lId =>
            scope.findVar(id.value) match {
              case Some((varTh, _)) =>
                varTh match {
                  case fth: FnTh => Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, fth, args.map { arg =>
                    new InferTask {
                      override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                        (arg.location, passExpr(ctx, scope, eq, th, arg))
                    }
                  })
                  case selfTh => Invoker.invokeSelfDef(ctx, eq, call.location, th, "get",
                    new InferTask {
                      override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                        (id.location, passExpr(ctx, scope, eq, th, id))
                    },
                    args.map { arg =>
                      new InferTask {
                        override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                          (arg.location, passExpr(ctx, scope, eq, th, arg))
                      }
                    })
                }
              case None =>
                val toCall = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchCallable(id.location, id.value))
                if (toCall.getTypeHintOpt == None)
                  passDef(ctx.deeperDef(None, id.value), toCall)

                Invoker.invokeDef(ctx, eq, call.location, th, toCall, args.map { arg =>
                  new InferTask {
                    override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                      (arg.location, passExpr(ctx, scope, eq, th, arg))
                  }
                })
            }
          case lambda: Lambda =>
            val lambdaTh = passExpr(ctx, scope, eq, FnTh(Seq.empty, lambda.args.map(_ => AnyTh), th), lambda).asInstanceOf[FnTh]

            Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, lambdaTh, args.map { arg =>
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (arg.location, passExpr(ctx, scope, eq, th, arg))
              }
            })
          case expr =>
            throw TCE.ExpressionNotCallable(expr.location)
        }
      case call@SelfCall(fnName, self, args) =>
        val argTasks = args.map { arg =>
          new InferTask {
            override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
              (arg.location, passExpr(ctx.deeperExpr(), scope, eq, th, arg))
          }
        }

        self match {
          case id: lId if ctx.module.imports.seq.exists(ie => ie.modName == id.value) =>
            val ie = ctx.module.imports.seq.find(ie => ie.modName == id.value).get
            val mod = ctx.level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
            val toCall = mod.defs.getOrElse(fnName, throw TCE.NoSuchDef(Seq(call.location), fnName))
            Invoker.invokePrototype(ctx, eq, toCall.getEquations, call.location, th, toCall.getTypeHint, argTasks).moveToMod(Seq(ie))
          case _expr =>
            val selfTh = passExpr(ctx.deeperExpr(), scope, eq, AnyTh, _expr)
            val selfTask = new InferTask {
              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                (self.location, selfTh)
            }

            def default() =
              Invoker.invokeSelfDef(ctx, eq, call.location, th, fnName, selfTask, argTasks)

            def callField(fth: FnTh) =
              Invoker.invokePrototype(ctx, eq, new Equations(), call.location, th, fth, argTasks)

            selfTh match {
              case sth: ScalarTh =>
                resolveType(ctx.level, ctx.module, sth) match {
                  case (_, _, sd: StructDecl) =>
                    sd.fields.find(fd => fd.name == fnName) match {
                      case Some(field) =>
                        field.th.spec(makeSpecMap(sd.params, sth.params)) match {
                          case fth: FnTh =>
                            callField(fth)
                          case gettableTh if Invoker.findSelfDefOpt(ctx, Seq(call.location), gettableTh, "get") != None =>
                            val (selfFth, selfEq) = Invoker.findSelfDef(ctx, Seq(call.location), gettableTh, "get")
                            Invoker.invokePrototype(ctx, eq, selfEq, call.location, th, selfFth, new InferTask {
                              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) = (self.location, gettableTh)
                            } +: argTasks)
                          case _ => default()
                        }
                      case _ => default()
                    }
                  case _ => default()
                }
              case sth: StructTh =>
                sth.seq.find(fd => fd.name == fnName) match {
                  case Some(field) =>
                    field.typeHint match {
                      case fth: FnTh => callField(fth)
                      case _ => default()
                    }
                  case _ => default()
                }
              case _ => default()
            }
        }
      case cons@Cons(sth, args) =>
        val sd = resolveType(ctx.level, ctx.module, sth) match {
          case (_, _, sd: StructDecl) => sd
          case _ => throw TCE.StructTypeRequired(cons.location)
        }

        if (sd.isBuiltinArray) {
          val argsTh = args.map(arg => passExpr(ctx, scope, eq, sth.params.headOption.getOrElse(AnyTh), arg))
          val thSet = argsTh.toSet

          val inferedElTh =
            if (thSet.size == 1) thSet.head
            else UnionTh(thSet.toSeq)


          sth.params.headOption match {
            case Some(elTh) =>
              new TypeChecker2(ctx).doCheck(elTh, inferedElTh)
            case None =>
          }

          sd.getBuiltinArrayLen match {
            case Some(len) =>
              ScalarTh(Seq(inferedElTh), "Array" + len, Seq.empty)
            case None =>
              ScalarTh(Seq(inferedElTh), "Array", Seq.empty)
          }
        } else {
          val realParams =
            if (th.isInstanceOf[ScalarTh]) { // FIXME: check types more pedantic
              th.asInstanceOf[ScalarTh].params
            } else if (sth.params.nonEmpty) {
              if (sth.params.length != sd.params.length) throw TCE.ParamsCountMismatch(sth.location)
              sth.params
            } else
              sd.params

          val realTh = ScalarTh(realParams, sth.name, sth.mod)
          realTh.setLocation(sth.location)
          val specMap = makeSpecMap(sd.params, realTh.params)
          val consTh = FnTh(Seq.empty, sd.fields.map(f => f.th.spec(specMap)), realTh)

          Invoker.invokePrototype(ctx, eq, new Equations(), cons.location, th, consTh, args.map { arg =>
            new InferTask {
              override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                (arg.location, passExpr(ctx, scope, eq, th, arg))
            }
          })
        }
      case Tuple(seq) =>
        val expectedSeq =
          th match {
            case sth: StructTh if sth.seq.length == seq.length => sth.seq.map(_.typeHint)
            case _ => seq.map(_ => AnyTh)
          }

        StructTh((expectedSeq zip seq).zipWithIndex.map { case ((expected, fieldExpr), idx) =>
          FieldTh("x" + idx, passExpr(ctx, scope, eq, expected, fieldExpr))
        })
      case Prop(from, props) =>
        val eth = passExpr(ctx, scope, eq, AnyTh, from)
        foldFields(eth, props)
      case store@Store(varTh, to, what) =>
        varTh match {
          case gth: GenericTh if !eq.typeParams.contains(gth) => eq.typeParams += gth
          case _ =>
        }
        varTh.assertCorrect(ctx, eq.typeParams)
        // x: Int = 5 # ok
        // x = 6 # ok
        // x.y: Int = 8 # fail
        // x.y = 8 # ok
        varTh match {
          case AnyTh =>
            scope.findVar(to.head.value) match {
              case None =>
                if (to.length != 1) throw TCE.NoSuchVar(to.head.location)

                val whatTh = passExpr(ctx.deeperExpr(), scope, eq, varTh, what)
                scope.addLocal(to.head.value, whatTh)
                store.setDeclTh(whatTh)
              case Some((toVarTh, _)) =>
                val toTh = foldFields(toVarTh, to.drop(1))
                passExpr(ctx.deeperExpr(), scope, eq, toTh, what)
            }
          case th =>
            if (to.length != 1) throw TCE.TypeHintUnexpected(th.location)

            if (scope.findVar(to.head.value) != None)
              throw TCE.VarAlreadyDeclared(to.head.location, to.head.value)

            passExpr(ctx.deeperExpr(), scope, eq, th, what)
            scope.addLocal(to.head.value, th)
            store.setDeclTh(th)
        }
        thNil
      case lambda@Lambda(args, body) =>
        val argsTh = lambda.args.map(_.typeHint)
        val (inferedArgsTh, retTh) = th match {
          case fth: FnTh if fth.args.length == argsTh.length =>
            val inferedArgsTh = (fth.args zip argsTh).map {
              case (AnyTh, argTh) => argTh
              case (expectedTh, AnyTh) => expectedTh
              case (expectedTh, argTh) =>
                try {
                  new TypeChecker2(ctx).isEqual(expectedTh, argTh); argTh
                } catch {
                  case ex: MismatchLocal => throw TCE.TypeMismatch(Seq(argTh.location), expectedTh, argTh)
                }
            }
            (inferedArgsTh, fth.ret)
          case AnyTh =>
            val inferedArgsTh = args.map { arg =>
              if (arg.typeHint.containsAny) throw TCE.TypeHintRequired(arg.location)
              arg.typeHint
            }
            (inferedArgsTh, AnyTh)
          case _ =>
            throw TCE.TypeMismatch(Seq(lambda.location), th, FnTh(Seq.empty, argsTh, AnyTh))
        }

        val inferedRetTh = body match {
          case AbraCode(seq) =>
            val lambdaScope = LambdaScope(
              scope, (args.map(_.name) zip inferedArgsTh).toMap)
            val lambdaBlock = new Scope2.BlockScope(lambdaScope)

            seq.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), lambdaBlock, eq, AnyTh, expr))
            seq.lastOption.foreach { last =>
              lambdaBlock.addRetType(passExpr(ctx.deeperExpr(), lambdaBlock, eq, AnyTh, last))
            }

            lambdaScope.retTypes.distinct match {
              case Seq() => thNil
              case Seq(th) => th
              case seq => UnionTh(seq)
            }
          case _ =>
            throw TCE.LambdaWithNativeCode(lambda.location)
        }

        FnTh(Seq.empty, argsTh, inferedRetTh)
      case andOr: AndOr =>
        passExpr(ctx, scope, eq, thBool, andOr.left)
        passExpr(ctx, scope, eq, thBool, andOr.right)
        thBool
      case While(cond, _do) =>
        passExpr(ctx, scope, eq, thBool, cond)
        val block = new Scope2.WhileScope(scope)
        _do.foreach { expr => passExpr(ctx, block, eq, AnyTh, expr) }
        thNil
      case bc@(Break() | Continue()) =>
        def findWhileScope(sc: Scope2.Scope): Option[Scope2.WhileScope] =
          sc match {
            case ws: Scope2.WhileScope => Some(ws)
            case ds: Scope2.DefScope => None
            case ls: Scope2.LambdaScope => None
            case bs: Scope2.BlockScope => findWhileScope(bs.parent)
          }

        findWhileScope(scope).getOrElse(throw TCE.NoWhileForBreakOrContinue(bc.location))
        thNil
      case If(cond, _do, _else) =>
        passExpr(ctx, scope, eq, thBool, cond)

        val doBlock = new Scope2.BlockScope(scope)
        _do.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), doBlock, eq, AnyTh, expr))
        val doTh = _do.lastOption.map { last =>
          passExpr(ctx.deeperExpr(), doBlock, eq, AnyTh, last)
        }.getOrElse(thNil)

        val elseBlock = new Scope2.BlockScope(scope)
        _else.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), elseBlock, eq, AnyTh, expr))
        val elseTh = _else.lastOption.map { last =>
          passExpr(ctx.deeperExpr(), elseBlock, eq, AnyTh, last)
        }.getOrElse(thNil)

        Seq(doTh, elseTh).filter(th => th != thUnreachable).distinct match {
          case Seq(one) => one
          case many => UnionTh(many)
        }
      case Unless(expr, isSeq) =>
        val tc = new TypeChecker2(ctx)
        val exprTh = passExpr(ctx, scope, eq, AnyTh, expr)

        val exprUnionVariants = exprTh match {
          case uth: UnionTh => uth.seq
          case sth: ScalarTh =>
            resolveType(ctx.level, ctx.module, sth) match {
              case (_, _, ud: UnionDecl) => ud.variants.map(v => v.spec(makeSpecMap(ud.params, sth.params)))
              case _ => throw TCE.ExpectedUnionType(expr.location, exprTh)
            }
          case _ => throw TCE.ExpectedUnionType(expr.location, exprTh)
        }

        isSeq.indices.foreach { i =>
          isSeq.indices.drop(i + 1).foreach { j =>
            if (isSeq(i).typeRef == isSeq(j).typeRef)
              throw TCE.CaseAlreadyCovered(isSeq(j).location, isSeq(j).typeRef, isSeq(i).location)
          }
        }

        val mappedSeq = isSeq.map { is =>
          exprUnionVariants.find { th =>
            try {
              tc.check(Seq(is.typeRef.location), th, is.typeRef); true
            } catch {
              case _: TCE.TypeMismatch => false
            }
          }.getOrElse(throw TCE.UnlessExpectedOneOf(is.typeRef.location, exprUnionVariants, is.typeRef))

          val blockScope = new Scope2.BlockScope(scope)
          is.vName.foreach(vName => blockScope.addLocal(vName.value, is.typeRef))

          is._do.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), blockScope, eq, AnyTh, expr))
          is._do.lastOption.map { last =>
            passExpr(ctx.deeperExpr(), blockScope, eq, AnyTh, last)
          }.getOrElse(thNil)
        }

        val coveredType = isSeq.map(is => is.typeRef).toSet
        val mappedType = mappedSeq.filter(x => x != thUnreachable).toSet
        val differentialType = exprUnionVariants.toSet -- coveredType
        val overallType = (differentialType ++ mappedType).toSeq

        overallType match {
          case Seq(one) => one
          case many => UnionTh(many)
        }
    }

    expr.setTypeHint(exprTh)
    new TypeChecker2(ctx).check(Seq(expr.location), th, exprTh)
    exprTh
  }

  def passDef(ctx: PassContext, fn: Def): Unit = {
    if (ctx.defStack.tail.contains(ctx.defStack.head))
      throw TCE.RetTypeHintRequired(fn.location)

    fn.lambda.args.headOption match {
      case Some(head) if head.name == "self" =>
        ctx.log(s"pass function ${head.typeHint}::${fn.name}")
      case _ =>
        ctx.log(s"pass function ${fn.name}")
    }

    val eq = new Equations(fn.params)

    val args = fn.lambda.args.map { arg =>
      arg.typeHint.assertCorrect(ctx, eq.typeParams)
      (arg.name, arg.typeHint)
    }.toMap
    fn.retTh.assertCorrect(ctx, eq.typeParams)

    val defScope = DefScope(args)

    fn.lambda.body match {
      case llVm(_) =>
        if (fn.retTh.containsAny) throw TCE.RetTypeHintRequired(fn.location)

        fn.setEquations(eq)
        fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh))
      case AbraCode(seq) =>
        val declaredRetTh =
          if (fn.retTh != AnyTh) {
            fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh))
            fn.setEquations(new Equations())
            Some(fn.retTh)
          } else None

        val bodyScope = new Scope2.BlockScope(defScope)

        seq.dropRight(1).foreach(expr => passExpr(ctx.deeperExpr(), bodyScope, eq, AnyTh, expr))
        seq.lastOption.foreach { last =>
          bodyScope.addRetType(passExpr(ctx.deeperExpr(), bodyScope, eq, fn.retTh, last))
        }

        val retTh = defScope.retTypes.distinct match {
          case Seq() => thNil
          case Seq(th) => th
          case seq => UnionTh(seq)
        }

        declaredRetTh match {
          case Some(declared) =>
            new TypeChecker2(ctx).check(Seq(declared.location), declared, retTh)
          case None =>
            fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), retTh))
        }

        fn.setEquations(eq)
    }
    ctx.log(s"** computed eq: ${fn.getEquations}")
    ctx.log("****")
  }

  def checkDoubleSelfDeclare(ctx: PassContext): Unit = {
    ctx.module.selfDefs.values.foreach { selfDefs =>
      selfDefs.foreach { selfDef =>
        val selfTh = selfDef.lambda.args.head.typeHint

        selfDefs.find { other =>
          other.location != selfDef.location && Invoker.isSelfApplicable(ctx, selfTh, other.lambda.args.head.typeHint)
        } match {
          case Some(other) =>
            throw TCE.DoubleSelfDefDeclaration(other.location, selfTh)
          case None =>
        }
      }
    }
  }

  def pass(root: Level) =
    root.eachModule((level, module) => {
      val ctx = PassContext(0, level, module)

      checkTypeDecl(ctx)
      checkDoubleSelfDeclare(ctx)

      module.defs.values.foreach { fn =>
        if (fn.getEquationsOpt == None)
          passDef(ctx.deeperDef(None, fn.name), fn)
      }

      module.selfDefs.values.foreach { defs =>
        defs.foreach { fn =>
          if (fn.getEquationsOpt == None)
            passDef(ctx.deeperDef(Some(fn.lambda.args(0).typeHint), fn.name), fn)
        }
      }
    })
}