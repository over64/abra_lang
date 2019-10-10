package m3.codegen

import java.io.PrintStream

import m3.codegen.Ast2._
import m3.codegen.IrUtil._

import scala.collection.mutable

/**
  * Created by over on 23.10.17.
  */
object IrGen2 {
  def findSymbol(ctx: IrContext,
                 dctx: DefContext,
                 name: String): (String, TypeRef, Boolean) =
  // find local
    dctx.vars.get(name) match {
      case Some(tref) => ("%" + name, tref, true)
      case None =>
        val fnType = ctx.types(dctx.fn.ref.name).asInstanceOf[Fn]
        // find param
        dctx.fn.args.zipWithIndex.find { case (argName, idx) => argName == name } match {
          case Some((argName, idx)) => ("%" + name, fnType.args(idx), !fnType.args(idx).isRegisterFit(ctx.types))
          case None =>
            // find closure
            dctx.fn.closure.zipWithIndex.find { case (cName, idx) => cName == name } match {
              case Some((cName, idx)) =>
                fnType.closure(idx) match {
                  case Local(ref) =>
                    val r1 = dctx.nextReg().toString
                    val r2 = dctx.nextReg().toString
                    val closureDecl = fnType.closureDecl(ctx.types, false)
                    val irType = fnType.closure(idx).ref.toValue(ctx.types)

                    ctx.out.println(s"""\t%$r1 = getelementptr $closureDecl, $closureDecl* %local.cl, i64 0, i32 $idx""")
                    ctx.out.println(s"\t%$r2 = load $irType*, $irType** %$r1")
                    ("%" + r2, ref, true)
                  case Param(ref) =>
                    if (ref.isRegisterFit(ctx.types)) {
                      val r1 = dctx.nextReg().toString
                      val r2 = dctx.nextReg().toString
                      val closureDecl = fnType.closureDecl(ctx.types, false)
                      val irType = fnType.closure(idx).ref.toValue(ctx.types)

                      ctx.out.println(s"""\t%$r1 = getelementptr $closureDecl, $closureDecl* %local.cl, i64 0, i32 $idx""")
                      ctx.out.println(s"\t%$r2 = load $irType, $irType* %$r1")
                      ("%" + r2, ref, false)
                    } else {
                      val r1 = dctx.nextReg().toString
                      val r2 = dctx.nextReg().toString
                      val closureDecl = fnType.closureDecl(ctx.types, false)
                      val irType = fnType.closure(idx).ref.toValue(ctx.types)

                      ctx.out.println(s"""\t%$r1 = getelementptr $closureDecl, $closureDecl* %local.cl, i64 0, i32 $idx""")
                      ctx.out.println(s"\t%$r2 = load $irType*, $irType** %$r1")
                      ("%" + r2, ref, true)
                    }
                }
              case None =>
                // find global
                ("@" + "\"" + name + "\"", ctx.protos.getOrElse(name, throw new RuntimeException("no such symbol " + name)), false)
            }
        }
    }

  def evalGep(ctx: IrContext,
              dctx: DefContext,
              tref: TypeRef,
              dataPtr: String,
              props: Seq[String]): (TypeRef, Boolean, String) = {
    def fieldsTo(fields: Seq[Field], tref: TypeRef, seq: Seq[Int], prop: String) = {
      val (field, i) = fields.zipWithIndex.find {
        case (f, i) => f.name == prop
      }.get
      (field.ref, seq :+ i)
    }

    def variantsTo(variants: Seq[TypeRef], tref: TypeRef, seq: Seq[Int], prop: String) =
      (variants(prop.toInt), seq :+ (prop.toInt + 1))

    val (resultTref, elements) = props.foldLeft((tref, Seq[Int](0))) {
      case ((tref, seq), prop) =>
        ctx.types(tref.name) match {
          case Struct(pkg, name, fields) => fieldsTo(fields, tref, seq, prop)
          case Union(pkg, name, variants) => variantsTo(variants, tref, seq, prop)
          case _ => throw new RuntimeException("oops")
        }
    }

    val irType =
      if (tref.isRef(ctx.types)) tref.toValue(ctx.types).stripSuffix("*")
      else tref.toValue(ctx.types)
    val reg = dctx.nextReg().toString

    ctx.out.println(s"\t%$reg = getelementptr $irType, $irType* $dataPtr, ${elements.map(e => "i32 " + e).mkString(", ")}")
    (resultTref, true, "%" + reg)
  }

  def evalId(ctx: IrContext,
             dctx: DefContext,
             id: Id): (TypeRef, Boolean, String) = {
    val (loaded, initialTref, isPtrToType) = findSymbol(ctx, dctx, id.v)
    if (id.props.isEmpty) return (initialTref, isPtrToType, loaded)

    if (initialTref.isValue(ctx.types) && !isPtrToType) throw new RuntimeException("cannot do GEP on value")

    val dataPtr =
      if (initialTref.isRef(ctx.types) && isPtrToType) {
        val reg = dctx.nextReg().toString
        val irType = initialTref.toValue(ctx.types)
        ctx.out.println(s"\t%$reg = load $irType, $irType* $loaded")
        "%" + reg
      } else loaded

    evalGep(ctx, dctx, initialTref, dataPtr, id.props)
  }

  def requirePtr(data: (TypeRef, Boolean, String)): (TypeRef, String) = {
    val (tref, isPtr, name) = data
    if (isPtr == false) throw new RuntimeException(s"expected pointer for $name")
    (tref, name)
  }

  def requireValue(ctx: IrContext, dctx: DefContext, data: (TypeRef, Boolean, String)): (TypeRef, String) = {
    val (tref, isPtr, name) = data
    if (isPtr == false || tref.isVoid(ctx.types)) return (tref, name)

    val irType = tref.toValue(ctx.types)
    val reg = dctx.nextReg().toString
    ctx.out.println(s"\t%$reg = load $irType, $irType* $name")
    (tref, "%" + reg)
  }

  def evalCall(ctx: IrContext, dctx: DefContext, call: Call): (TypeRef, String) = {
    val (tref, vName, isGlobalCall) =
      if (call.id.props.isEmpty && ctx.protos.contains(call.id.v))
        (ctx.protos(call.id.v), "@" + "\"" + call.id.v + "\"", true)
      else {
        val (_tref, _vName) = requireValue(ctx, dctx, evalId(ctx, dctx, call.id))
        (_tref, _vName, false)
      }

    val fnType = ctx.types(tref.name).asInstanceOf[Fn]

    val irArgs = (fnType.args zip call.args).map {
      case (argTypeRef, arg) =>
        val (tref, isPtr, name) = evalId(ctx, dctx, arg)

        if (tref.isRegisterFit(ctx.types)) {
          val (_, argName) = requireValue(ctx, dctx, (tref, isPtr, name))
          s"${tref.toValue(ctx.types)} $argName"
        }
        else s"${tref.toPtr(ctx.types)} $name"
    }

    val (fnPtr, fixedArgs) =
      if (isGlobalCall) (vName, irArgs)
      else {
        val r1 = dctx.nextReg().toString
        val r2 = dctx.nextReg().toString
        ctx.out.println(s"\t%$r1 = extractvalue ${tref.toValue(ctx.types)} $vName, 0")
        ctx.out.println(s"\t%$r2 = extractvalue ${tref.toValue(ctx.types)} $vName, 1")

        ("%" + r1, irArgs :+ ("i8* %" + r2))
      }

    val retType = fnType.ret.toValue(ctx.types)

    if (fnType.ret.isVoid(ctx.types)) {
      ctx.out.println(s"\tcall $retType $fnPtr(${fixedArgs.mkString(", ")})")
      (fnType.ret, "")
    } else {
      val retId = "%" + dctx.nextReg()
      ctx.out.println(s"\t$retId = call $retType $fnPtr(${fixedArgs.mkString(", ")})")
      (fnType.ret, retId)
    }
  }

  def evalStore(ctx: IrContext,
                dctx: DefContext,
                dest: Id, src: Storable, init: Boolean): Unit = {
    ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ store begin")
    // вычисляем что store
    val (whatTref, what, needAcquier) =
      src match {
        case id: Id =>
          val (whatTref, what) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
          (whatTref, what, true)
        case call: Call =>
          val (whatTref, what) = evalCall(ctx, dctx, call)
          (whatTref, what, false)
        case Cons(ref, args) =>
          val (whatTref, what) = evalCall(ctx, dctx, Call(Id(ref.name + ".$cons"), args))
          (ref, what, false)
      }

    //    if (whatTref.isVoid(ctx.types)) {
    //      ctx.out.println(s";@@ void store eliminated")
    //      return
    //    }

    // вычисляем куда store
    val (toTref, to) = requirePtr(evalId(ctx, dctx, dest))

    if (toTref.isVoid(ctx.types)) {
      ctx.out.println(s";@@ void store eliminated")
      return
    }

    // делаем инкремент что store
    if (needAcquier && whatTref.isNeedBeforeAfterStore(ctx.types)) {
      val fAcquire = "\"" + whatTref.fullName(ctx.types) + ".acquire" + "\""
      val irType = whatTref.toValue(ctx.types)
      ctx.out.println(s"\tcall void @$fAcquire($irType $what)")
    }

    // делаем декремент куда store
    if (!init)
      if (toTref.isNeedBeforeAfterStore(ctx.types)) {
        val fRelease = "\"" + toTref.fullName(ctx.types) + ".release" + "\""
        val irType = toTref.toValue(ctx.types)
        val r2 = dctx.nextReg()
        ctx.out.println(s"\t%$r2 = load $irType, $irType* $to")
        ctx.out.println(s"\tcall void @$fRelease($irType %$r2)")
      }

    // делаем store
    if (toTref.isFn(ctx.types)
      && dest.props.isEmpty
      && dctx.vars.contains(dest.v)
      && src.isInstanceOf[Id]
      && src.asInstanceOf[Id].props.isEmpty
      && ctx.protos.contains(src.asInstanceOf[Id].v)) {
      // инициализируем локальное замыкание
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ mk closure begin")
      val _def = ctx.defs(src.asInstanceOf[Id].v)

      val closureType = ctx.types(_def.ref.name).asInstanceOf[Fn]
      val valIr = _def.ref.toValue(ctx.types)
      val fnIr = closureType.toGlobal(ctx.types, false)
      val closureIr = closureType.closureDecl(ctx.types, false)

      val r1 = dctx.nextReg().toString
      ctx.out.println(s"\t%$r1 = getelementptr $valIr, $valIr* $to, i64 0, i32 0")

      if (closureType.closure.isEmpty) {
        val closuredFnIr = closureType.toGlobal(ctx.types, true)
        val r2 = dctx.nextReg().toString
        ctx.out.println(s"\t%$r2 = bitcast $fnIr $what to $closuredFnIr")
        ctx.out.println(s"\tstore $closuredFnIr %$r2, $closuredFnIr* %$r1")
      } else
        ctx.out.println(s"\tstore $fnIr $what, $fnIr* %$r1")

      val r2 = dctx.nextReg().toString
      ctx.out.println(s"\t%$r2 = getelementptr $valIr, $valIr* $to, i64 0, i32 1")

      if (closureType.closure.isEmpty)
        ctx.out.println(s"\tstore i8* null, i8** %$r2")
      else {
        val r3 = dctx.nextReg().toString
        ctx.out.println(s"\t%$r3 = bitcast $closureIr* $to.cl to i8*")
        ctx.out.println(s"\tstore i8* %$r3, i8** %$r2")
      }

      (closureType.closure zip _def.closure).zipWithIndex.foreach { case ((ct, argName), idx) =>
        ct match {
          case Local(ref) =>
            val irType = ref.toValue(ctx.types)
            val (_, vName) = requirePtr(evalId(ctx, dctx, Id(argName)))
            val r1 = dctx.nextReg().toString
            ctx.out.println(s"\t%$r1 = getelementptr $closureIr, $closureIr* $to.cl, i64 0, i32 $idx")
            ctx.out.println(s"\tstore $irType* $vName, $irType** %$r1")
          case Param(ref) =>
            val irType = ref.toValue(ctx.types)
            val r1 = dctx.nextReg().toString

            ctx.out.println(s"\t%$r1 = getelementptr $closureIr, $closureIr* $to.cl, i64 0, i32 $idx")

            if (ref.isRegisterFit(ctx.types)) {
              val (_, vName) = requireValue(ctx, dctx, evalId(ctx, dctx, Id(argName)))
              ctx.out.println(s"\tstore $irType $vName, $irType* %$r1")
            } else {
              val (_, vName) = requirePtr(evalId(ctx, dctx, Id(argName)))
              ctx.out.println(s"\tstore $irType* $vName, $irType** %$r1")
            }
        }
      }
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ mk closure end")

    } else if (toTref == whatTref) {
      val irType = toTref.toValue(ctx.types)
      ctx.out.println(s"\tstore $irType $what, $irType* $to")
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ store end")
    } else
      ctx.types(toTref.name) match {
        case u: Union =>
          u.fieldTagValue(whatTref) match {
            case -1 =>
              // u1 is part of u4
              // store u1 -> u4 must be possible
              // U1 = String | Int
              // U4 = Bool | Int | String
              val uWhat = ctx.types(whatTref.name).asInstanceOf[Union]
              val uIrType = whatTref.toValue(ctx.types)
              val r1 = dctx.nextReg()
              val brEnd = "brEnd" + dctx.nextBranch()
              ctx.out.println(s"\t%$r1 = extractvalue $uIrType $what, 0 ")
              ctx.out.println(s"\tswitch i8 %$r1, label %$brEnd [")

              val toGen: Seq[(String, TypeRef)] =
                uWhat.variants.map { v =>
                  val idx = uWhat.fieldTagValue(v)
                  val br = "br" + dctx.nextBranch()
                  ctx.out.println(s"\t\ti8 $idx, label %$br")
                  (br, v)
                }

              ctx.out.println(s"\t]")

              toGen.foreach {
                case (branch, typeRef) =>
                  val whatTagIdx = uWhat.fieldTagValue(typeRef)
                  val whatIrIdx = uWhat.fieldIrIndex(typeRef)
                  val toTagIdx = u.fieldTagValue(typeRef)
                  val toIrIdx = uWhat.fieldIrIndex(typeRef)
                  val whatIrType = whatTref.toValue(ctx.types)
                  val toIrType = toTref.toValue(ctx.types)
                  val irType = typeRef.toValue(ctx.types)
                  val r1 = dctx.nextReg()

                  ctx.out.println(s"$branch:")
                  ctx.out.println(s"\t%$r1 = getelementptr $toIrType, $toIrType* $to, i64 0, i32 0")
                  ctx.out.println(s"\tstore i8 $toTagIdx, i8* %$r1")

                  val (_, _, toPtr) = evalGep(ctx, dctx, toTref, to, Seq(toTagIdx.toString))

                  val r2 = dctx.nextReg()
                  ctx.out.println(s"\t%$r2 = extractvalue $whatIrType $what, $whatIrIdx")
                  ctx.out.println(s"\tstore $irType %$r2, $irType* $toPtr")
                  ctx.out.println(s"\tbr label %$brEnd")
              }
              ctx.out.println(s"$brEnd:    ;@@ store end")
            case tag =>
              u.isNullableUnion(ctx.types) match {
                case Some(tref) =>
                  val toIrType = TypeRef(u.name).toValue(ctx.types)
                  val whatIrType = if (whatTref.isVoid(ctx.types)) toIrType else whatTref.toValue(ctx.types)
                  val whatStore = if (whatTref.isVoid(ctx.types)) "null" else what
                  ctx.out.println(s"\tstore $whatIrType $whatStore, $toIrType* $to")
                case None =>
                  val irType = toTref.toValue(ctx.types)
                  val r1 = dctx.nextReg()
                  ctx.out.println(s"\t%$r1 = getelementptr $irType, $irType* $to, i64 0, i32 0")
                  ctx.out.println(s"\tstore i8 $tag, i8* %$r1")

                  if (!whatTref.isVoid(ctx.types)) {
                    val (tref, _, v) = evalGep(ctx, dctx, toTref, to, Seq(tag.toString))

                    val toIrType = tref.toValue(ctx.types)
                    ctx.out.println(s"\tstore $toIrType $what, $toIrType* $v")
                  }
              }
              ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ store end")
          }
        case _ =>
          if (!toTref.isVoid(ctx.types)) throw new RuntimeException(s"unexpected store $src -> $dest")
      }
  }

  def evalStat(ctx: IrContext,
               dctx: DefContext,
               wctx: Option[WhileContext],
               stat: Stat): Unit = stat match {
    case Cons(ref, args) =>
      evalCall(ctx, dctx, Call(Id("\"" + ref.name + ".$cons\""), args))
    case Store(init, dest, src) => evalStore(ctx, dctx, dest, src, init)
    case Free(id) =>
      val tref = dctx.vars(id.v)
      if (tref.isNeedBeforeAfterStore(ctx.types)) {
        val fRelease = "\"" + tref.fullName(ctx.types) + ".release" + "\""
        val irType = tref.toValue(ctx.types)
        val r1 = dctx.nextReg()
        ctx.out.println(s"\t%$r1 = load $irType, $irType* %${id.v}")
        ctx.out.println(s"\tcall void @$fRelease($irType %$r1)")
      }
    case call: Call =>
      evalCall(ctx, dctx, call)
    case Ret(idOpt) =>
      idOpt match {
        case Some(id) =>
          val (typeRef, what) = requireValue(ctx, dctx, evalId(ctx, dctx, Id(id, Seq.empty)))
          if (typeRef.isVoid(ctx.types))
            ctx.out.println("\tret void")
          else
            ctx.out.println(s"\tret ${typeRef.toValue(ctx.types)} $what")
        case None => ctx.out.println("\tret void")
      }
    case bc@(Break() | Continue()) =>
      val labels = wctx.getOrElse(throw new RuntimeException("Internal compier error! Expected while context"))
      dctx.nextReg()
      bc match {
        case Break() => ctx.out.println(s"\tbr label %" + labels.endBr)
        case Continue() => ctx.out.println(s"\tbr label %" + labels.headBr)
      }
    case Or(id, left, right) =>
      left.foreach(r => evalStat(ctx, dctx, wctx, r))
      val (rightBr, endBr) = ("right" + dctx.nextBranch(), "end" + dctx.nextBranch())
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 0")
      ctx.out.println(s"\tbr i1 %$r1, label %$rightBr, label %$endBr")
      ctx.out.println(s"$rightBr:")
      right.foreach(r => evalStat(ctx, dctx, wctx, r))
      ctx.out.println(s"\tbr label %$endBr")
      ctx.out.println(s"$endBr:")
    case And(id, left, right) =>
      left.foreach(r => evalStat(ctx, dctx, wctx, r))
      val (rightBr, endBr) = ("right" + dctx.nextBranch(), "end" + dctx.nextBranch())
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 1")
      ctx.out.println(s"\tbr i1 %$r1, label %$rightBr, label %$endBr")
      ctx.out.println(s"$rightBr:")
      right.foreach(r => evalStat(ctx, dctx, wctx, r))
      ctx.out.println(s"\tbr label %$endBr")
      ctx.out.println(s"$endBr:")
    case While(id, head, body) =>
      val (headBr, bodyBr, endBr) = ("headW" + dctx.nextBranch(), "bodyW" + dctx.nextBranch(), "endW" + dctx.nextBranch())
      ctx.out.println(s"\tbr label %$headBr")
      ctx.out.println(s"$headBr:")
      head.foreach(s => evalStat(ctx, dctx, wctx, s))
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 1")
      ctx.out.println(s"\tbr i1 %$r1, label %$bodyBr, label %$endBr")
      ctx.out.println(s"$bodyBr:")
      body.foreach(s => evalStat(ctx, dctx, Some(WhileContext(headBr, endBr)), s))
      ctx.out.println(s"\tbr label %$headBr")
      ctx.out.println(s"$endBr:")
    case If(id, onTrue, onFalse) =>
      val (trueBr, falseBr, endBr) = ("trueIf" + dctx.nextBranch(), "falseIf" + dctx.nextBranch(), "endIf" + dctx.nextBranch())
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 1")
      ctx.out.println(s"\tbr i1 %$r1, label %$trueBr, label %$falseBr")

      ctx.out.println(s"$trueBr:")
      onTrue.foreach(s => evalStat(ctx, dctx, wctx, s))
      ctx.out.println(s"\tbr label %$endBr")

      ctx.out.println(s"$falseBr:")
      onFalse.foreach(s => evalStat(ctx, dctx, wctx, s))
      ctx.out.println(s"\tbr label %$endBr")
      ctx.out.println(s"$endBr:")
    case Unless(dest, src, isSeq) =>
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ unless begin")
      val (tref, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, src))
      val uIrType = tref.toValue(ctx.types)
      var tag: Int = 0
      val u = ctx.types(tref.name).asInstanceOf[Union]

      u.isNullableUnion(ctx.types) match {
        case Some(tref) =>
          val preTag = dctx.nextReg()
          tag = dctx.nextReg()
          ctx.out.println(s"\t%$preTag = icmp eq $uIrType $exp, null")
          ctx.out.println(s"\t%$tag = zext i1 %$preTag to i8")
        case None =>
          tag = dctx.nextReg()
          ctx.out.println(s"\t%$tag = extractvalue $uIrType $exp, 0 ")
      }

      val uType = ctx.types(tref.name).asInstanceOf[Union]

      val brEnd = "end" + dctx.nextBranch()
      val isBranches = uType.variants.indices.map(i => (i, "is" + dctx.nextBranch()))

      ctx.out.println(s"\tswitch i8 %$tag, label %$brEnd [")
      isBranches.foreach {
        case (tagValue, br) => ctx.out.println(s"\t\ti8 $tagValue, label %$br")
      }
      ctx.out.println(s"\t]")

      isBranches.foreach { case (tagValue, br) =>
        ctx.out.println(s"$br:")

        val variant = uType.variants(tagValue)

        // FIXME!!! very bad! generalize store for NullableUnion
        isSeq.find(_.ref == variant) match {
          case Some(is) =>
            is.v.foreach { isVName =>
              u.isNullableUnion(ctx.types) match {
                case Some(tref) =>
                  val destIrType = tref.toPtr(ctx.types)
                  val r = dctx.nextReg()
                  ctx.out.println(s"\t%$r = load $uIrType, $uIrType*  %${src.v}")
                  val fAcquire = "\"" + tref.fullName(ctx.types) + ".acquire" + "\""
                  ctx.out.println(s"\tcall void @$fAcquire($uIrType %$r)")
                  ctx.out.println(s"\tstore $uIrType %$r, $destIrType %${isVName}")
                case None =>
                  evalStat(ctx, dctx, wctx, Store(init = true, Id(isVName), Id(src.v, src.props :+ tagValue.toString)))
              }
            }

            is.seq.foreach(stat => evalStat(ctx, dctx, wctx, stat))
            ctx.out.println(s"\tbr label %$brEnd")
          case None =>
            if (!variant.isVoid(ctx.types))
              u.isNullableUnion(ctx.types) match {
                case Some(tref) =>
                  val destIrType = tref.toPtr(ctx.types)
                  val r = dctx.nextReg()
                  ctx.out.println(s"\t%$r = load $uIrType, $uIrType*  %${src.v}")
                  val fAcquire = "\"" + tref.fullName(ctx.types) + ".acquire" + "\""
                  ctx.out.println(s"\tcall void @$fAcquire($uIrType %$r)")
                  ctx.out.println(s"\tstore $uIrType %$r, $destIrType %${dest}")
                case None =>
                  evalStat(ctx, dctx, wctx, Store(init = true, Id(dest), Id(src.v, src.props :+ tagValue.toString)))
              }
            ctx.out.println(s"\tbr label %$brEnd")
        }
      }

      ctx.out.println(s"$brEnd:")
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ unless end")
  }

  def writeProto(ctx: IrContext, external: Boolean, fnName: String, fnType: Fn, args: Seq[String]): Unit = {
    val argsIr = (args zip fnType.args).map {
      case (argName, argTypeRef) =>
        if (argTypeRef.isRegisterFit(ctx.types)) argTypeRef.toValue(ctx.types) + " " + irLocalName(argName)
        else argTypeRef.toPtr(ctx.types) + " " + irLocalName(argName)
    }

    val realArgs =
      if (fnType.closure.isEmpty) argsIr
      else argsIr :+ (s"""i8* %fn.closure""")

    val declType = if (external) "declare" else "define"

    ctx.out.println(s"""$declType ${fnType.ret.toValue(ctx.types)} @"$fnName" (${realArgs.mkString(", ")})""")
  }

  def evalDef(ctx: IrContext, fn: Def) = {
    writeProto(ctx, false, fn.name, ctx.types(fn.ref.name).asInstanceOf[Fn], fn.args)
    ctx.out.println("{")
    fn.code match {
      case LLCode(value) => ctx.out.println(value)
      case AbraCode(vars, stats) =>
        if (fn.closure.nonEmpty) {
          val fnType = ctx.types(fn.ref.name).asInstanceOf[Fn]
          ctx.out.println(s"\t%local.cl = bitcast i8* %fn.closure to ${fnType.closureDecl(ctx.types, true)}")
        }
        vars.foreach {
          case (vName, typeRef) =>
            if (!typeRef.isVoid(ctx.types)) {
              ctx.types(typeRef.name) match {
                case fn: Fn if fn.closure.nonEmpty =>
                  ctx.out.println(s"\t${irLocalName(vName)}.cl = alloca ${fn.closureDecl(ctx.types, false)}")
                case _ =>
              }
              ctx.out.println(s"\t${irLocalName(vName)} = alloca ${typeRef.toValue(ctx.types)}")
            }

        }

        val dctx = DefContext(fn, vars)
        stats.foreach(stat => evalStat(ctx, dctx, None, stat))
    }
    ctx.out.println("}")
  }

  def gen(out: PrintStream,
          lowCode: Seq[String],
          types: mutable.HashMap[String, Type],
          defs: mutable.HashMap[String, Def],
          protos: mutable.HashMap[String, TypeRef]) = {

    val ctx = IrContext(out, types, protos, defs)
    ctx.out.println(
      """
        declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)
        @rcAlloc = external thread_local(initialexec) global i8* (i64)*
        @rcInc = external thread_local(initialexec) global void (i8*)*
        @rcRelease = external thread_local(initialexec) global void (i8*, void (i8*)*)*
      """.stripMargin)

    // hack llvm: forward references to non-struct type
    ctx.types.values.filter {
      case l: Low => l.llValue != "void" && !l.llValue.contains("%")
      case _ => false
    }.foreach(t => out.println(s"""%"${t.name}" = type """ + t.toDecl(types)))

    ctx.types.values.filter {
      case l: Low => l.llValue != "void" && l.llValue.contains("%")
      case _ => false
    }.foreach(t => out.println(s"""%"${t.name}" = type """ + t.toDecl(types)))
    // end hack

    ctx.types.values.filter {
      case u: Union => true
      case _ => false
    }.foreach(t => out.println(s"""%"${t.name}" = type """ + t.toDecl(types)))

    ctx.types.values.filter {
      case s: Struct => true
      case _ => false
    }.foreach(t => out.println(s"""%"${t.name}" = type """ + t.toDecl(types)))

    ctx.protos.foreach {
      case (pname, ref) =>
        val fnType = ctx.types(ref.name).asInstanceOf[Fn]
        val args = (0 to fnType.args.length).map(i => "x" + i)
        writeProto(ctx, true, pname, fnType, args)
    }
    ctx.out.println()

    lowCode.foreach { code => ctx.out.println(code) }
    ctx.out.println()

    ctx.types.values.toBuffer[Type].foreach {
      case s: Struct =>
        StoreUtil.genConstructor(ctx, s)
      case _ =>
    }
    ctx.types.values.toBuffer[Type].foreach(t => StoreUtil.genAcquireRelease(ctx, t))

    defs.map {
      case (k, v) => ctx.protos.put(k, v.ref)
    }
    defs.values.foreach(fn => evalDef(ctx, fn))
  }
}
