package m3.typecheck

import m3.codegen.Ast2
import m3.parse.Ast0._
import m3.typecheck.Util._

import scala.collection.mutable

object Invoker {

  trait InferTask {
    def infer(expected: TypeHint): (TypeHint, String, Seq[Ast2.Stat])
  }

  def checkUnionMember(ctx: TContext, specMap: mutable.HashMap[GenericTh, TypeHint],
                       ud: UnionDecl, params: Seq[TypeHint], th: TypeHint): Boolean = {

    if (ud.params.length != params.length) throw new RuntimeException(s"expected ${ud.params.length} for $ud has ${params.length}")

    val specMapInternal = makeSpecMap(ud.params, params)
    ud.variants.exists { udVariant =>
      udVariant.spec(specMapInternal).isEqual(ctx, specMap, th)
    }
  }

  def checkAndInfer(ctx: TContext, specMap: mutable.HashMap[GenericTh, TypeHint], expected: TypeHint, has: TypeHint): Boolean =
    (expected, has) match {
      case (th, AnyTh) => true
      case (th, gth: GenericTh) => true
      case (adv: ScalarTh, sth: ScalarTh) =>
        if (adv.name != sth.name) {
          ctx.findType(adv.name, adv.ie.toSeq) match {
            case (_, ud: UnionDecl) => checkUnionMember(ctx, specMap, ud, adv.params, sth)
            case _ => false
          }
        } else isEqualSeq(ctx, specMap, adv.params, sth.params)

      case (adv: ScalarTh, sth: StructTh) => // FIXME: bad copy paste?
        ctx.findType(adv.name, adv.ie.toSeq) match {
          case (_, ud: UnionDecl) => checkUnionMember(ctx, specMap, ud, adv.params, sth)
          case _ => false
        }
      case (adv: ScalarTh, fth: FnTh) => // FIXME: bad copy paste?
        ctx.findType(adv.name, adv.ie.toSeq) match {
          case (_, ud: UnionDecl) => checkUnionMember(ctx, specMap, ud, adv.params, fth)
          case _ => false
        }
      case (adv: ScalarTh, uth: UnionTh) =>
        ctx.findType(adv.name, adv.ie.toSeq) match {
          case (_, ud: UnionDecl) =>
            if (ud.params.length != adv.params.length) throw new RuntimeException(s"expected ${ud.params.length} for $ud has ${adv.params.length}")

            uth.seq.forall { variant =>
              ud.variants.exists { udVariant =>
                udVariant.isEqual(ctx, specMap, variant)
              }
            }
          case _ => false
        }
      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant => advVariant.isEqual(ctx, specMap, th) }
      case (adv: UnionTh, th: UnionTh) =>
        th.seq.forall { variant => checkAndInfer(ctx, specMap, adv, variant) }
      case (adv: StructTh, th: StructTh) =>
        isEqualSeq(ctx, specMap, adv.seq.map(_.typeHint), th.seq.map(_.typeHint))
      case (adv: FnTh, th: FnTh) =>
        isEqualSeq(ctx, specMap, adv.args, th.args) && checkAndInfer(ctx, specMap, adv.ret, th.ret)
      case (adv: GenericTh, th) =>
        specMap.get(adv) match {
          case None => specMap.put(adv, th); true
          case _ => false
        }
      case (AnyTh, th) => true
      case (adv, th) => false
    }

  def invokeArgs(ctx: TContext,
                 scope: BlockScope,
                 specMap: mutable.HashMap[GenericTh, TypeHint],
                 defArgs: Iterator[TypeHint],
                 args: Iterator[InferTask]): (Seq[Ast2.Stat], Seq[(String, TypeHint)]) = {
    val argsStats = mutable.ListBuffer[Ast2.Stat]()
    val argsVars = mutable.ListBuffer[(String, TypeHint)]()

    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val arg = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()
      val argTh = defArg.spec(specMap)
      val (th, vName, stats) = arg.infer(argTh)

      if (!checkAndInfer(ctx, specMap, argTh, th))
        throw new RuntimeException(s"expected $argTh has $th")

      argsStats ++= stats

      val argThSpec = defArg.spec(specMap)
      val bridgedArg =
        if (!argThSpec.isEqual(ctx, new mutable.HashMap(), th)) {
          val newName = "_bridge" + ctx.nextAnonId()
          scope.addLocal(ctx, newName, argThSpec)
          argsStats += Ast2.Store(init = true, Ast2.Id(newName), Ast2.Id(vName))
          newName
        } else vName

      argsVars += ((bridgedArg, th))
    }

    if (args.hasNext) throw new RuntimeException("too much args")
    (argsStats, argsVars)
  }

  def checkArgs(ctx: TContext,
                specMap: mutable.HashMap[GenericTh, TypeHint],
                defArgs: Iterator[TypeHint],
                args: Iterator[TypeHint]): Unit = {
    while (defArgs.hasNext) {
      val defArg = defArgs.next()
      val th = if (!args.hasNext) throw new RuntimeException("too least args") else args.next()
      val argTh = defArg.spec(specMap)

      if (!checkAndInfer(ctx, specMap, argTh, th))
        throw new RuntimeException(s"expected $argTh has $th")
    }

    if (args.hasNext) throw new RuntimeException("too much args")
  }

  def makeCall(ctx: TContext, scope: BlockScope, lowName: String,
               retTh: TypeHint, argsStats: Seq[Ast2.Stat], argsVars: Seq[(String, TypeHint)]) = {
    val anonVar = "$c" + ctx.nextAnonId()
    scope.addLocal(ctx, anonVar, retTh)

    (
      retTh,
      anonVar,
      argsStats :+ Ast2.Store(
        init = true,
        Ast2.Id(anonVar),
        Ast2.Call(
          Ast2.Id(lowName),
          argsVars.map { case (vName, _) => Ast2.Id(vName) }))
    )
  }

  def invokeConstructor(ctx: TContext,
                        scope: BlockScope,
                        typeName: String,
                        args: Iterator[InferTask],
                        ret: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = {
    // Vec[Int] vs Vec[t]
    val consType =
      ctx.findType(typeName, Seq()) match {
        case (_, sd: StructDecl) => sd
        case _ => throw new RuntimeException(s"$typeName is not struct type")
      }
    val consTh = ScalarTh(consType.params, consType.name, None)

    val specMap = mutable.HashMap.empty[GenericTh, TypeHint]
    if (!checkAndInfer(ctx, specMap, consTh, ret))
      throw new RuntimeException(s"expected $consTh has $ret")

    val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
      consType.fields.map(f => f.th).toIterator, args)

    val flatSpecs = consType.params.map(cp => specMap(cp))
    val retTh = ScalarTh(flatSpecs, consType.name, None)

    // code generation will be performed on codegen part
    val virtualArgs = consType.fields.map(f => Arg(f.name, f.th))
    val virtualDef = Def(consType.name, Lambda(virtualArgs, AbraCode(Seq.empty)), retTh)
    val _def = virtualDef.spec(specMap, ctx)

    makeCall(ctx, scope, _def.name + ".$cons", retTh, argsStats, argsVars)
  }

  def invokeAnonConstructor(ctx: TContext,
                            scope: BlockScope,
                            forType: StructTh,
                            args: Iterator[InferTask]): (TypeHint, String, Seq[Ast2.Stat]) = {
    val specMap = mutable.HashMap[GenericTh, TypeHint]()
    val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
      forType.seq.map(f => f.typeHint).toIterator, args)

    // code generation will be performed on codegen part
    makeCall(ctx, scope, forType.toLow(ctx).name + ".$cons", forType, argsStats, argsVars)
  }

  def invokeLambda(ctx: TContext,
                   scope: BlockScope,
                   name: String,
                   th: FnTh,
                   args: Iterator[InferTask]) = {

    val specMap = mutable.HashMap[GenericTh, TypeHint]()
    val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
      th.args.toIterator, args)

    makeCall(ctx, scope, name, th.ret, argsStats, argsVars)
  }

  def invoke(ctx: TContext,
             scope: BlockScope,
             toCall: Def,
             args: Iterator[InferTask],
             ret: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = {

    val specMap = new mutable.HashMap[GenericTh, TypeHint]()
    if (!checkAndInfer(ctx, specMap, ret, toCall.retTh))
      throw new RuntimeException(s"expected $ret has ${toCall.retTh}")
    val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
      toCall.lambda.args.map(a => a.typeHint).toIterator, args)

    specMap.values.foreach {
      case gth: GenericTh =>
        throw new RuntimeException(s"Internal compiler error. Generic type $gth not specialized")
      case _ =>
    }

    val header = TypeChecker.evalDef(ctx, scope.mkChild(p => new FnScope(None)), toCall, specMap)
    makeCall(ctx, scope, header.lowName, header.th.ret, argsStats, argsVars)
  }

  def invokeFromMod(ctx: TContext,
                    modName: String,
                    mod: ModHeader,
                    scope: BlockScope,
                    toCall: Def,
                    args: Iterator[InferTask],
                    ret: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = {
    val caleeMod = "$calee" + ctx.deep
    println("callee: " + caleeMod)

    val specMap = new mutable.HashMap[GenericTh, TypeHint]()
    if (!checkAndInfer(ctx, specMap, toCall.retTh, ret))
      throw new RuntimeException(s"expected ${toCall.retTh} has $ret")

    val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
      toCall.lambda.args.map(a => a.typeHint.moveToMod(modName)).toIterator, args)

    val fixedMap = specMap.map {
      case (k, gth: GenericTh) =>
        throw new RuntimeException(s"Internal compiler error. Generic type $gth not specialized")
      case (k, th) =>
        (k, th.moveToMod(caleeMod))
    }

    val clonedHeaders = mutable.HashMap(mod.headers.toSeq: _*)

    val evalOn = TContext(
      ctx.idSeq,
      ctx.inferStack,
      ctx.lowMod,
      ctx.deep + 1,
      ctx.pkg,
      mod.imports ++ Map((caleeMod, ctx.toHeader)),
      mod.typeImports,
      mod.lowCode,
      mod.types,
      mod.inlineDefs,
      mod.inlineSelfDefs,
      clonedHeaders)

    val header = TypeChecker.evalDef(evalOn, scope.mkChild(p => new FnScope(None)), toCall, fixedMap)
    ctx.idSeq = evalOn.idSeq

    clonedHeaders.foreach { case (k, v) => mod.headers.put(k, v) }
    makeCall(ctx, scope, header.lowName, header.th.ret.swapMod(caleeMod, modName), argsStats, argsVars)
  }

  def evalFromMod(ctx: TContext,
                  mod: ModHeader,
                  toCall: Def,
                  args: Iterator[TypeHint],
                  ret: TypeHint): Unit = {
    val caleeMod = "$calee" + ctx.deep
    println("callee: " + caleeMod)

    val specMap = new mutable.HashMap[GenericTh, TypeHint]()
    if (!checkAndInfer(ctx, specMap, toCall.retTh, ret))
      throw new RuntimeException(s"expected ${toCall.retTh} has $ret")
    checkArgs(ctx, specMap, toCall.lambda.args.map(a => a.typeHint).toIterator, args)

    val fixedMap = specMap.map {
      case (k, gth: GenericTh) =>
        throw new RuntimeException(s"Internal compiler error. Generic type $gth not specialized")
      case (k, th) =>
        (k, th.moveToMod(caleeMod))
    }

    val clonedHeaders = mutable.HashMap(mod.headers.toSeq: _*)

    val evalOn = TContext(
      ctx.idSeq,
      ctx.inferStack,
      ctx.lowMod,
      ctx.deep + 1,
      ctx.pkg,
      mod.imports ++ Map((caleeMod, ctx.toHeader)),
      mod.typeImports,
      mod.lowCode,
      mod.types,
      mod.inlineDefs,
      mod.inlineSelfDefs,
      clonedHeaders)

    TypeChecker.evalDef(evalOn, new FnScope(None), toCall, fixedMap)

    ctx.idSeq = evalOn.idSeq
    clonedHeaders.foreach { case (k, v) => mod.headers.put(k, v) }
  }


  def invokeDef(ctx: TContext,
                scope: BlockScope,
                name: String,
                args: Iterator[InferTask],
                ret: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = {
    val fn = ctx.defs.getOrElse(name, throw new RuntimeException(s"no such function with name $name"))
    invoke(ctx, scope, fn, args, ret)
  }

  def isSelfApplicable(ctx: TContext, selfTh: TypeHint, selfArgTh: TypeHint): Boolean = {
    val specMap = new mutable.HashMap[GenericTh, TypeHint]()
    checkAndInfer(ctx, specMap, selfTh.spec(specMap), selfArgTh)
  }

  def invokeSelfDef(ctx: TContext,
                    scope: BlockScope,
                    name: String,
                    selfType: TypeHint,
                    args: Iterator[InferTask],
                    ret: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = {

    def invokeApplicableInMod(mod: ModHeader, name: String, selfType: TypeHint) = {
      val candidates = mod.imports.reverse.flatMap {
        case (modName, header) =>
          header.inlineSelfDefs.toSeq.flatMap {
            case (_, defs) => defs.map(d => (modName, header, d))
          }
      }
      candidates.find {
        case (modName, header, d) => d.name == name && isSelfApplicable(ctx, d.lambda.args.head.typeHint, selfType)
      } match {
        case None =>
          // mod.imports.reverse.flatMap(_._2.headers.values).find { dheader =>
          mod.imports.reverse.flatMap {
            case (modName, mod) => mod.headers.values.map(d => (modName, d))
          }.find {
            case (modName, dheader) =>
              dheader.name == name &&
                dheader.isSelf &&
                isSelfApplicable(ctx, dheader.th.args.head.moveToMod(modName), selfType)
          } match {
            case None =>
              throw new RuntimeException(s"no self function with name $name for type $selfType")
            case Some((modName, header)) =>
              ctx.lowMod.protos.put(header.lowName, header.th.moveToMod(modName).toLow(ctx))

              val specMap = mutable.HashMap[GenericTh, TypeHint]()
              val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
                header.th.args.map(_.moveToMod(modName)).toIterator, args)
              makeCall(ctx, scope, header.lowName, header.th.ret.moveToMod(modName), argsStats, argsVars)
          }
        case Some((modName, header, toCall)) =>
          invokeFromMod(ctx, modName, header, scope, toCall, args, ret)
      }
    }

    ctx.selfDefs.get(name).flatMap { defs =>
      defs.find(d => isSelfApplicable(ctx, d.lambda.args.head.typeHint, selfType))
    } match {
      case Some(fn) =>
        invoke(ctx, scope, fn, args, ret)
      case None =>
        invokeApplicableInMod(ctx.toHeader, name, selfType)
    }
  }

  def invokeMod(ctx: TContext,
                scope: BlockScope,
                modName: String,
                fnName: String,
                args: Iterator[InferTask],
                ret: TypeHint): (TypeHint, String, Seq[Ast2.Stat]) = {
    val mod = ctx.findImport(modName).getOrElse(throw new RuntimeException(s"no such module with name $modName"))

    mod.headers.get(mod.pkg + "." + fnName) match {
      case Some(header) =>
        ctx.lowMod.protos.put(header.lowName, header.th.moveToMod(modName).toLow(ctx))
        val specMap = mutable.HashMap[GenericTh, TypeHint]()
        val (argsStats, argsVars) = invokeArgs(ctx, scope, specMap,
          header.th.args.map(_.moveToMod(modName)).toIterator, args)
        makeCall(ctx, scope, header.lowName, header.th.ret.moveToMod(modName), argsStats, argsVars)
      case None =>
        val toCall = mod.inlineDefs.getOrElse(fnName, throw new RuntimeException(s"no such function $fnName in module $modName"))
        invokeFromMod(ctx, modName, mod, scope, toCall, args, ret)
    }
  }
}
