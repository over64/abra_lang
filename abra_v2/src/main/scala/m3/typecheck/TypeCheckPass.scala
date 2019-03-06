package m3.typecheck

import m3.parse.Ast0._
import m3.parse.{AstInfo, Level}
import m3.typecheck.Scope2._
import m3.typecheck.Util.makeSpecMap

import scala.collection.mutable

object Utils {
  implicit class ThExtension(self: TypeHint) {
    def assertCorrect(ctx: PassContext, params: Seq[GenericTh]): Unit =
      self match {
        case sth: ScalarTh =>
          val (_, decl) = resolveType(ctx.level, ctx.module, sth)

          if (decl.params.length != sth.params.length)
            throw TCE.ParamsCountMismatch(sth.location)

          sth.params.foreach(p => p.assertCorrect(ctx, params))
        case sth: StructTh =>
          val fieldNames = sth.seq.map(_.name)
          if (fieldNames.length != fieldNames.toSet.size)
            throw TCE.FieldNameNotUnique(sth.location)
          sth.seq.foreach(f => f.typeHint.assertCorrect(ctx, params))
        case uth: UnionTh =>
          if (uth.seq.length != uth.seq.toSet.size)
            throw TCE.UnionMembersNotUnique(uth.location)
          uth.seq.foreach(th => th.assertCorrect(ctx, params))
        case fth: FnTh =>
          fth.args.foreach(th => th.assertCorrect(ctx, params))
          fth.ret.assertCorrect(ctx, params)
        case gth: GenericTh =>
          if (!params.contains(gth))
            throw TCE.NoSuchParameter(gth.location, gth)
        case AnyTh =>
      }

    def spec(specMap: mutable.HashMap[GenericTh, TypeHint],
             onNotFound: GenericTh => TypeHint = gth => AnyTh): TypeHint = {
      val res = self match {
        case ScalarTh(params, name, pkg) =>
          specMap.get(GenericTh(name)) match {
            case Some(th) => th
            case None =>
              ScalarTh(params.map(p => p.spec(specMap, onNotFound)), name, pkg)
          }
        case StructTh(fields) =>
          StructTh(fields.map { field =>
            FieldTh(field.name, field.typeHint.spec(specMap, onNotFound))
          })
        case UnionTh(variants) =>
          UnionTh(variants.map { th =>
            th.spec(specMap, onNotFound)
          })
        case FnTh(closure, args, ret) =>
          FnTh(closure, args.map(arg => arg.spec(specMap, onNotFound)), ret.spec(specMap, onNotFound))
        case gth: GenericTh => specMap.getOrElse(gth, onNotFound(gth))
        case AnyTh => AnyTh
      }

      self.getLocation.foreach(loc => res.setLocation(self.location))
      res
    }

    def containsAny: Boolean =
      self match {
        case ScalarTh(params, name, pkg) =>
          params.exists(p => p.containsAny)
        case StructTh(fields) =>
          fields.exists(f => f.typeHint.containsAny)
        case UnionTh(variants) =>
          variants.exists(v => v.containsAny)
        case FnTh(closure, args, ret) =>
          args.exists(a => a.containsAny) || ret.containsAny
        case gth: GenericTh => false
        case AnyTh => true
      }

    def findGenerics(dest: mutable.ListBuffer[GenericTh]): Unit = self match {
      case ScalarTh(params, name, pkg) =>
        params.foreach(p => p.findGenerics(dest))
      case StructTh(fields) =>
        fields.foreach(f => f.typeHint.findGenerics(dest))
      case UnionTh(variants) =>
        variants.foreach(v => v.findGenerics(dest))
      case FnTh(closure, args, ret) =>
        args.foreach(a => a.findGenerics(dest))
        ret.findGenerics(dest)
      case gth: GenericTh => dest += gth
      case AnyTh =>
    }
  }

  implicit class RichDef(self: Def) {
    def params: mutable.ListBuffer[GenericTh] = {
      val dest = mutable.ListBuffer[GenericTh]()
      FnTh(Seq.empty, self.lambda.args.map(_.typeHint), self.retTh)
        .findGenerics(dest)
      dest
    }
  }

  def resolveType(level: Level, module: Module, th: ScalarTh): (Module, TypeDecl) = {
    val origModule = th.mod.foldLeft(module) {
      case (module, modName) =>
        val ie = module.imports.seq.find(ie => ie.modName == modName).getOrElse(throw TCE.NoSuchModulePath(th.location))
        level.findMod(ie.path).get
    }

    origModule.imports.seq
      .flatMap(ie => ie.withTypes.map(tName => (ie, tName)))
      .find { case (ie, tName) => tName == th.name }
    match {
      case Some((ie, _)) =>
        val mod = level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
        (mod, mod.types.getOrElse(th.name, throw TCE.NoSuchType(th.location, th.mod, th.name)))
      case None =>
        (origModule, origModule.types.getOrElse(th.name, throw TCE.NoSuchType(th.location, th.mod, th.name)))
    }
  }

  def makeSpecMap(gen: Seq[GenericTh], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    mutable.HashMap((gen zip params): _*)
  }
}

import m3.typecheck.Utils._

case class PassContext(deep: Int, level: Level, module: Module) {
  def deeper() = this.copy(deep = this.deep + 1)

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
        if (adv.name != th.name) throw new MismatchLocal
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
          case specified: GenericTh if th.isInstanceOf[GenericTh] =>
            val gth = th.asInstanceOf[GenericTh]
            if (specified.typeName != gth.typeName) throw new MismatchLocal
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
      case (adv: ScalarTh, sth: ScalarTh) =>
        if (adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          isEqualSeq(adv.params, sth.params)
        }
        else {
          resolveType(ctx.level, ctx.module, adv) match {
            case (_, ud: UnionDecl) => checkUnionMember(ud, adv, sth)
            case _ => throw new MismatchLocal
          }
        }
      case (adv: ScalarTh, uth: UnionTh) => throw new MismatchLocal
      case (adv: ScalarTh, th) =>
        resolveType(ctx.level, ctx.module, adv) match {
          case (_, ud: UnionDecl) => checkUnionMember(ud, adv, th)
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
      case ex: MismatchLocal => throw new TCE.TypeMismatch(location, expected, has)
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

  val generatedCode = AstInfo("generated", 0, 0, 0, 0)

  val thLong = ScalarTh(params = Seq.empty, "Long", Seq.empty)
  val thInt = ScalarTh(params = Seq.empty, "Int", Seq.empty)
  val thShort = ScalarTh(params = Seq.empty, "Short", Seq.empty)
  val thByte = ScalarTh(params = Seq.empty, "Byte", Seq.empty)

  val thDouble = ScalarTh(params = Seq.empty, "Double", Seq.empty)
  val thFloat = ScalarTh(params = Seq.empty, "Float", Seq.empty)
  val thBool = ScalarTh(params = Seq.empty, "Bool", Seq.empty)
  val thString = ScalarTh(params = Seq.empty, "String", Seq.empty)
  val thNil = ScalarTh(params = Seq.empty, "None", Seq.empty)
  val thUnreachable = ScalarTh(params = Seq.empty, "Unreachable", Seq.empty)

  thLong.setLocation(generatedCode)
  thInt.setLocation(generatedCode)
  thShort.setLocation(generatedCode)
  thByte.setLocation(generatedCode)
  thDouble.setLocation(generatedCode)
  thFloat.setLocation(generatedCode)
  thBool.setLocation(generatedCode)
  thString.setLocation(generatedCode)
  thNil.setLocation(generatedCode)
  thUnreachable.setLocation(generatedCode)


  def checkTypeDecl(ctx: PassContext): Unit = {
    ctx.module.types.values.foreach {
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

      myEq.eqSeq.foreach { eq =>
        if (eqMap.get(eq).isEmpty) {
          val (selfLocation, selfType) = eq.selfType
          val specSelf = anonSpec(selfType)

          if (!specSelf.isInstanceOf[GenericTh] && !specSelf.containsAny) {
            ctx.log(s"instance eq $eq")

            val fn = Invoker.findSelfDef(ctx, specSelf, eq.fnName)
            if (fn.getTypeHintOpt == None) passDef(ctx, fn)
            val fnTh: FnTh = fn.getTypeHint

            if (fnTh.args.drop(1).length != eq.args.length)
              throw new TCE.ArgsCountMismatch(selfLocation, fnTh.args.drop(1).length, eq.args.length)

            ctx.log("instance   " + printInstance(eq, fn.getTypeHint))

            val next = new EqInfer(ctx.deeper(), selfLocation, fn.getEquations)
            eqMap.put(eq, (fn.getTypeHint, ApplyIndex(0), next))
          }
        }
      }

      var changed = true
      while (changed) {
        changed = false

        eqMap.foreach { case (eq, (fnTh, applyIdx, next)) =>
          def idxToArg(idx: Int) =
            idx match {
              case 0 => "self"
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
                    ctx.log(s"anon       ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $eqTh => $argTh")
                    tInfer.specMap.put(generic, argSpec)
                    applyIdx.idx += 1
                    changed = true
                  }
                case (glocation, generic: GenericTh) =>
                  tInfer.specMap.get(generic).foreach { specified =>
                    ctx.log(s"infer      ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $eqTh @ $specified => $argTh")
                    next.infer(glocation ++ location, argTh, specified)
                    applyIdx.idx += 1
                    changed = true
                  }
                case other =>
                  val (othetLocation, otherTh) = other
                  val otherSpec = this.anonSpec(otherTh)
                  if (!otherSpec.containsAny) {
                    ctx.log(s"infer      ${printInstance(eq, fnTh)}   where   ${idxToArg(idx)} $otherSpec => $argTh")
                    next.infer(othetLocation ++ location, argTh, otherSpec)
                    applyIdx.idx += 1
                    changed = true
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
        new TypeInfer(ctx.deeper()).infer(Seq(selfTh.location), selfTh, selfArgTh); true
      } catch {
        case ex: Exception => false
      }

    def findSelfDef(ctx: PassContext, selfTh: TypeHint, fnName: String): Def = {
      selfTh match {
        case gth: GenericTh =>
          throw new RuntimeException("Unexpected to be here")
        case _ =>
          ctx.module.selfDefs.get(fnName).flatMap { defs =>
            defs.find(d => isSelfApplicable(ctx, d.lambda.args.head.typeHint, selfTh))
          } match {
            case Some(fn) => fn
            case None =>
              throw new RuntimeException("mod self call not impelemented")
          }
      }
    }

    def invokePrototype(ctx: PassContext, eqCaller: Equations, eqCallee: Equations, location: AstInfo,
                        retTh: TypeHint, fnTh: FnTh, args: Seq[InferTask]): TypeHint = {

      if (fnTh.args.length != args.length)
        throw TCE.ArgsCountMismatch(Seq(location), fnTh.args.length, args.length)

      val eqInfer = new EqInfer(ctx, Seq(location), eqCallee)

      eqInfer.infer(Seq(location), fnTh.ret, retTh)

      (fnTh.args zip args).foreach { case (defArgTh, argTask) =>
        val (argLocation, argTh) = argTask.infer(eqInfer.ctx, eqCaller, eqInfer.anonSpec(defArgTh))
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

      val (selfLocation, selfTh) = self.infer(ctx.deeper(), eqCaller, AnyTh)

      selfTh match {
        case gth: GenericTh =>
          val argsTh = args.map(argTask => argTask.infer(ctx.deeper(), eqCaller, AnyTh))
            .map { case (loc, th) => (Seq(loc), th) }
          val eqRet = if (retTh != AnyTh) retTh else eqCaller.nextAnonType()

          eqCaller.addEq(Equation((Seq(selfLocation), gth), fnName, argsTh, (Seq(location), eqRet)))
          eqRet
        case _ =>
          val fn = findSelfDef(ctx, selfTh, fnName)

          if (fn.getEquationsOpt == None) passDef(ctx.deeper(), fn)

          invokeDef(ctx, eqCaller, location, retTh, fn, new InferTask {
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
            case (_, sd: StructDecl) =>
              sd.fields
                .find(fd => fd.name == fieldId.value)
                .getOrElse(throw TCE.NoSuchField(fieldId.location, sth, fieldId.value))
                .th.spec(makeSpecMap(sd.params, sth.params))
            case (_, td) => throw TCE.NoSuchField(fieldId.location, sth, fieldId.value)
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

        th match {
          case sth: ScalarTh =>
            //FIXME: check mod == builtin
            val (mod, td) = resolveType(ctx.level, ctx.module, sth)
            td.name match {
              case "Long" => tryInt(lit, _.toLong, thLong)
              case "Int" => tryInt(lit, _.toInt, thInt)
              case "Short" => tryInt(lit, _.toShort, thShort)
              case "Byte" => tryInt(lit, _.toByte, thByte)
              case _ => default()
            }
          case _ => default()
        }
      case lit: lFloat =>
        def default() =
          tryFloat(lit, _.toFloat, thFloat, { _ =>
            tryFloat(lit, _.toDouble, thDouble, { _ =>
              throw TCE.FloatingLiteralOutOfRange(lit.location, None)
            })
          })

        th match {
          case sth: ScalarTh =>
            //FIXME: check mod == builtin
            val (mod, td) = resolveType(ctx.level, ctx.module, sth)
            td.name match {
              case "Double" => tryFloat(lit, _.toDouble, thDouble)
              case "Float" => tryFloat(lit, _.toFloat, thFloat)
              case _ => default()
            }
          case _ => default()
        }
      case _: lBoolean => thBool
      case _: lString => thString
      case _: lNone => thNil
      case id: lId =>
        scope.findVar(id.value) match {
          case Some((varTh, _)) => varTh
          case None =>
            val fn = ctx.module.defs.getOrElse(id.value, throw TCE.NoSuchSymbol(id.location, id.value))

            if (fn.retTh == AnyTh) passDef(ctx.deeper(), fn)

            FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh)
        }
      case Ret(opt) =>
        val retTh = opt.map(exp => passExpr(ctx.deeper(), scope, eq, th, exp)).getOrElse(thNil)
        scope.addRetType(retTh)
        retTh
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
                if (toCall.getEquationsOpt == None)
                  passDef(ctx.deeper(), toCall)

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
        self match {
          case id: lId =>
            Invoker.invokeSelfDef(ctx, eq, call.location, th, fnName,
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (self.location, passExpr(ctx, scope, eq, th, self))
              },
              args.map { arg =>
                new InferTask {
                  override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                    (arg.location, passExpr(ctx, scope, eq, th, arg))
                }
              })
          case _expr: Expression =>
            Invoker.invokeSelfDef(ctx, eq, call.location, th, fnName,
              new InferTask {
                override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                  (self.location, passExpr(ctx, scope, eq, th, self))
              },
              args.map { arg =>
                new InferTask {
                  override def infer(ctx: PassContext, eq: Equations, th: TypeHint): (AstInfo, TypeHint) =
                    (arg.location, passExpr(ctx, scope, eq, th, arg))
                }
              })
        }
      case cons@Cons(sth, args) =>
        val sd = resolveType(ctx.level, ctx.module, sth) match {
          case (_, sd: StructDecl) => sd
          case _ => throw TCE.StructTypeRequired(cons.location)
        }

        val realParams =
          if (sth.params.isEmpty)
            sd.params
          else {
            if (sth.params.length != sd.params.length) throw TCE.ParamsCountMismatch(sth.location)
            sth.params
          }

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
        val eth = passExpr(ctx, scope, eq, th, from)
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

                val whatTh = passExpr(ctx.deeper(), scope, eq, varTh, what)
                scope.addLocal(to.head.value, whatTh)
                store.setDeclTh(whatTh)
              case Some((toVarTh, _)) =>
                val toTh = foldFields(toVarTh, to.drop(1))
                val whatTh = passExpr(ctx.deeper(), scope, eq, toTh, what)
                new TypeChecker2(ctx).check(Seq(store.location), toTh, whatTh)
            }
          case th =>
            if (to.length != 1) throw TCE.TypeHintUnexpected(th.location)

            if (scope.findVar(to.head.value) != None)
              throw TCE.VarAlreadyDeclared(to.head.location, to.head.value)

            passExpr(ctx.deeper(), scope, eq, th, what)
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
              scope, (args.map(_.name) zip argsTh).toMap)
            val lambdaBlock = BlockScope(lambdaScope)

            if (seq.isEmpty)
              lambdaBlock.addRetType(thNil)
            else {
              seq.last match {
                case ret: Ret =>
                  seq.dropRight(1).foreach(expr => passExpr(ctx.deeper(), lambdaBlock, eq, AnyTh, expr))
                  passExpr(ctx.deeper(), lambdaBlock, eq, retTh, ret)
                case other =>
                  seq.dropRight(1).foreach(expr => passExpr(ctx.deeper(), lambdaBlock, eq, AnyTh, expr))
                  val ret = Ret(Some(other))
                  ret.setLocation(other.location)
                  passExpr(ctx.deeper(), lambdaBlock, eq, retTh, ret)
              }
            }

            // FIXME: infer super type?
            lambdaScope.retTypes match {
              case Seq() => thNil
              case Seq(th) => th
              case seq => UnionTh(seq)
            }
          case _ =>
            throw TCE.LambdaWithNativeCode(lambda.location)
        }

        FnTh(Seq.empty, argsTh, inferedRetTh)
    }

    expr.setTypeHint(exprTh)
    new TypeChecker2(ctx).check(Seq(expr.location), th, exprTh)

    th match {
      case sth: ScalarTh if resolveType(ctx.level, ctx.module, sth).isInstanceOf[UnionDecl] => th
      case uth: UnionTh => th
      case _ => exprTh
    }
  }

  def passDef(ctx: PassContext, fn: Def): Unit = {
    ctx.log(s"pass function ${fn.name}")

    val eq = new Equations(fn.params)

    val args = fn.lambda.args.map { arg =>
      arg.typeHint.assertCorrect(ctx, eq.typeParams)
      (arg.name, arg.typeHint)
    }.toMap
    fn.retTh.assertCorrect(ctx, eq.typeParams)

    val defScope = DefScope(args)

    fn.lambda.body match {
      case llVm(code) =>
        if (fn.retTh.containsAny) throw TCE.RetTypeHintRequired(fn.location)

        fn.setEquations(eq)
        fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), fn.retTh))
      case AbraCode(seq) =>
        val bodyScope = BlockScope(defScope)

        if (seq.isEmpty)
          bodyScope.addRetType(thNil)
        else {
          seq.last match {
            case ret: Ret =>
              seq.dropRight(1).foreach(expr => passExpr(ctx.deeper(), bodyScope, eq, AnyTh, expr))
              passExpr(ctx.deeper(), bodyScope, eq, fn.retTh, ret)
            case other =>
              seq.dropRight(1).foreach(expr => passExpr(ctx.deeper(), bodyScope, eq, AnyTh, expr))
              val ret = Ret(Some(other))
              ret.setLocation(other.location)
              passExpr(ctx.deeper(), bodyScope, eq, fn.retTh, ret)
          }
        }

        val retTh = defScope.retTypes match {
          case Seq() => thNil
          case Seq(th) => th
          case seq => UnionTh(seq) // FIXME: infer super type?
        }

        fn.setEquations(eq)
        fn.setTypeHint(FnTh(Seq.empty, fn.lambda.args.map(_.typeHint), retTh))
    }
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

      module.defs.values.foreach(fn =>
        if (fn.getEquationsOpt == None)
          passDef(ctx, fn))
    })
}