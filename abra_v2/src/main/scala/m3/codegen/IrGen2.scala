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
                    val irType = fnType.closure(idx).ref.toValue(ctx.types)

                    ctx.out.println(s"""\t%$r1 = getelementptr %\"${fnType.name}\", %"${fnType.name}"* %$$closure, i64 0, i32 ${idx + 1}""")
                    ctx.out.println(s"\t%$r2 = load $irType*, $irType** %$r1")
                    ("%" + r2, ref, true)
                  case Param(ref) =>
                    if (ref.isRegisterFit(ctx.types)) {
                      val r1 = dctx.nextReg().toString
                      val r2 = dctx.nextReg().toString
                      val irType = fnType.closure(idx).ref.toValue(ctx.types)

                      ctx.out.println(s"""\t%$r1 = getelementptr %"${fnType.name}", %"${fnType.name}"* %$$closure, i64 0, i32 ${idx + 1}""")
                      ctx.out.println(s"\t%$r2 = load $irType, $irType* %$r1")
                      ("%" + r2, ref, false)
                    } else {
                      val r1 = dctx.nextReg().toString
                      val r2 = dctx.nextReg().toString
                      val irType = fnType.closure(idx).ref.toValue(ctx.types)

                      ctx.out.println(s"""\t%$r1 = getelementptr %"${fnType.name}", %"${fnType.name}"* %$$closure, i64 0, i32 ${idx + 1}""")
                      ctx.out.println(s"\t%$r2 = load $irType*, $irType** %$r1")
                      ("%" + r2, ref, true)
                    }
                }
              case None =>
                // find global
                ("@" + "\"" + name + "\"", ctx.protos(name), false)
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
          case Struct(name, fields) => fieldsTo(fields, tref, seq, prop)
          case Union(name, variants) => variantsTo(variants, tref, seq, prop)
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
    val (_tref, _isPtr, _vName) = evalId(ctx, dctx, call.id)
    val fnType = ctx.types(_tref.name).asInstanceOf[Fn]

    val (tref, vName) =
      if (fnType.closure.isEmpty)
        requireValue(ctx, dctx, (_tref, _isPtr, _vName))
      else {
        val irType = _tref.toValue(ctx.types)
        val r1 = dctx.nextReg().toString
        val r2 = dctx.nextReg().toString
        ctx.out.println(s"\t%$r1 = getelementptr $irType, $irType* ${_vName}, i64 0, i32 0")

        val disclosureType = Fn("", Seq.empty, fnType.args :+ TypeRef(fnType.name), fnType.ret).toDecl(ctx.types)
        ctx.out.println(s"\t%$r2 = load $disclosureType, $disclosureType* %$r1")
        (disclosureType, "%" + r2)
      }

    val argsToMap =
      if (fnType.closure.isEmpty) call.args
      else call.args :+ call.id

    val irArgs = argsToMap.map { arg =>
      val (tref, isPtr, name) = evalId(ctx, dctx, arg)
      if (tref.isRegisterFit(ctx.types)) {
        val (_, argName) = requireValue(ctx, dctx, (tref, isPtr, name))
        s"${tref.toValue(ctx.types)} $argName"
      }
      else s"${tref.toPtr(ctx.types)} $name"
    }

    val retType = fnType.ret.toValue(ctx.types)

    if (fnType.ret.isVoid(ctx.types)) {
      ctx.out.println(s"\tcall $retType $vName(${irArgs.mkString(", ")})")
      (fnType.ret, "")
    } else {
      val retId = "%" + dctx.nextReg()
      ctx.out.println(s"\t${retId} = call $retType $vName(${irArgs.mkString(", ")})")
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

    if (whatTref.isVoid(ctx.types)) return

    // делаем инкремент что store
    if (needAcquier && whatTref.isNeedBeforeAfterStore(ctx.types)) {
      val fRelease = "\"" + whatTref.name + ".$acquire" + "\""
      val irType = whatTref.toValue(ctx.types)
      ctx.out.println(s"\tcall void @$fRelease($irType $what)")
    }

    // вычисляем куда store
    val (toTref, to) = requirePtr(evalId(ctx, dctx, dest))

    if (toTref.isVoid(ctx.types)) return

    // делаем декремент куда store
    if (!init)
      if (toTref.isNeedBeforeAfterStore(ctx.types)) {
        val fRelease = "\"" + toTref.name + ".$release" + "\""
        val irType = toTref.toValue(ctx.types)
        val r2 = dctx.nextReg()
        ctx.out.println(s"\t%$r2 = load $irType, $irType* $to")
        ctx.out.println(s"\tcall void @$fRelease($irType %$r2)")
      }

    // делаем store
    // ищем реальный адрес куда store с учетом изврата с union
    if (toTref == whatTref) {
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
              val irType = toTref.toValue(ctx.types)
              val r1 = dctx.nextReg()
              ctx.out.println(s"\t%$r1 = getelementptr $irType, $irType* $to, i64 0, i32 0")
              ctx.out.println(s"\tstore i8 $tag, i8* %$r1")

              val (tref, _, v) = evalGep(ctx, dctx, toTref, to, Seq(tag.toString))

              val toIrType = tref.toValue(ctx.types)
              ctx.out.println(s"\tstore $toIrType $what, $toIrType* $v")
              ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ store end")
          }
        case _ => throw new RuntimeException("unexpected")
      }
  }
  def evalStat(ctx: IrContext,
               dctx: DefContext,
               stat: Stat): Unit = stat match {
    case Cons(ref, args) =>
      evalCall(ctx, dctx, Call(Id("\"" + ref.name + ".$cons\""), args))
    case Store(init, dest, src) => evalStore(ctx, dctx, dest, src, init)
    case Closure(dest, src) =>
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ mk closure begin")
      val _def = ctx.defs(src)

      val (destName, _, _) = findSymbol(ctx, dctx, dest)
      val closureType = ctx.types(_def.ref.name).asInstanceOf[Fn]
      val closureIr = "\"" + _def.ref.name + "\""
      val r1 = dctx.nextReg().toString
      ctx.out.println(s"\t%$r1 = getelementptr %$closureIr, %$closureIr* $destName, i64 0, i32 0")

      val fnType = Fn(closureType.name, Seq.empty, closureType.args :+ TypeRef(closureType.name), closureType.ret)
      val fnIr = fnType.toDecl(ctx.types)
      ctx.out.println(s"\tstore $fnIr @$src, $fnIr* %$r1")

      (closureType.closure zip _def.closure).zipWithIndex.foreach { case ((ct, argName), idx) =>
        ct match {
          case Local(ref) =>
            val irType = ref.toValue(ctx.types)
            val (_, vName) = requirePtr(evalId(ctx, dctx, Id(argName)))
            val r1 = dctx.nextReg().toString
            ctx.out.println(s"\t%$r1 = getelementptr %$closureIr, %$closureIr* $destName, i64 0, i32 ${idx + 1}")
            ctx.out.println(s"\tstore $irType* $vName, $irType** %$r1")
          case Param(ref) =>
            if (ref.isRegisterFit(ctx.types)) {
              val irType = ref.toValue(ctx.types)
              val (_, vName) = requireValue(ctx, dctx, evalId(ctx, dctx, Id(argName)))
              val r1 = dctx.nextReg().toString
              ctx.out.println(s"\t%$r1 = getelementptr %$closureIr, %$closureIr* $destName, i64 0, i32 ${idx + 1}")
              ctx.out.println(s"\tstore $irType $vName, $irType* %$r1")
            } else {
              val irType = ref.toValue(ctx.types)
              val (_, vName) = requirePtr(evalId(ctx, dctx, Id(argName)))
              val r1 = dctx.nextReg().toString
              ctx.out.println(s"\t%$r1 = getelementptr %$closureIr, %$closureIr* $destName, i64 0, i32 ${idx + 1}")
              ctx.out.println(s"\tstore $irType* $vName, $irType** %$r1")
            }
        }
      }
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ mk closure end")
    case Free(id) =>
      val tref = dctx.vars(id.v)
      if (tref.isNeedBeforeAfterStore(ctx.types)) {
        val fRelease = "\"" + tref.name + ".$release" + "\""
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
    case Or(id, left, right) =>
      left.foreach(r => evalStat(ctx, dctx, r))
      val (rightBr, endBr) = ("right" + dctx.nextBranch(), "end" + dctx.nextBranch())
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 0")
      ctx.out.println(s"\tbr i1 %$r1, label %$rightBr, label %$endBr")
      ctx.out.println(s"$rightBr:")
      right.foreach(r => evalStat(ctx, dctx, r))
      ctx.out.println(s"\tbr label %$endBr")
      ctx.out.println(s"$endBr:")
    case And(id, left, right) =>
      left.foreach(r => evalStat(ctx, dctx, r))
      val (rightBr, endBr) = ("right" + dctx.nextBranch(), "end" + dctx.nextBranch())
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 1")
      ctx.out.println(s"\tbr i1 %$r1, label %$rightBr, label %$endBr")
      ctx.out.println(s"$rightBr:")
      right.foreach(r => evalStat(ctx, dctx, r))
      ctx.out.println(s"\tbr label %$endBr")
      ctx.out.println(s"$endBr:")
    case While(id, head, body) =>
      val (headBr, bodyBr, endBr) = ("headW" + dctx.nextBranch(), "bodyW" + dctx.nextBranch(), "endW" + dctx.nextBranch())
      ctx.out.println(s"\tbr label %$headBr")
      ctx.out.println(s"$headBr:")
      head.foreach(s => evalStat(ctx, dctx, s))
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 1")
      ctx.out.println(s"\tbr i1 %$r1, label %$bodyBr, label %$endBr")
      ctx.out.println(s"$bodyBr:")
      body.foreach(s => evalStat(ctx, dctx, s))
      ctx.out.println(s"\tbr label %$headBr")
      ctx.out.println(s"$endBr:")
    case If(id, onTrue, onFalse) =>
      val (trueBr, falseBr, endBr) = ("trueIf" + dctx.nextBranch(), "falseIf" + dctx.nextBranch(), "endIf" + dctx.nextBranch())
      val (_, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val r1 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = icmp eq i8 $exp, 1")
      ctx.out.println(s"\tbr i1 %$r1, label %$trueBr, label %$falseBr")

      ctx.out.println(s"$trueBr:")
      onTrue.foreach(s => evalStat(ctx, dctx, s))
      ctx.out.println(s"\tbr label %$endBr")

      ctx.out.println(s"$falseBr:")
      onFalse.foreach(s => evalStat(ctx, dctx, s))
      ctx.out.println(s"\tbr label %$endBr")
      ctx.out.println(s"$endBr:")
    case When(id, isSeq, _else) =>
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ when begin")
      val (tref, exp) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
      val uIrType = tref.toValue(ctx.types)
      val tag = dctx.nextReg()
      ctx.out.println(s"\t%$tag = extractvalue $uIrType $exp, 0 ")

      val uType = ctx.types(tref.name).asInstanceOf[Union]

      val brElse = "else" + dctx.nextBranch()
      val brEnd = "end" + dctx.nextBranch()

      ctx.out.println(s"\tswitch i8 %$tag, label %$brElse [")
      val isAndBranch = isSeq.map { is =>
        val br = "is" + dctx.nextBranch()
        val tagValue = uType.fieldTagValue(is.ref)
        ctx.out.println(s"\t\ti8 $tagValue, label %$br")
        (is, tagValue, br)
      }
      ctx.out.println(s"\t]")

      isAndBranch.foreach {
        case (is, idx, br) =>
          ctx.out.println(s"$br:")
          evalStat(ctx, dctx, Store(init = true, Id(is.v), Id(id.v, id.props :+ idx.toString)))
          is.seq.foreach(stat => evalStat(ctx, dctx, stat))
          evalStat(ctx, dctx, Free(Id(is.v)))
          ctx.out.println(s"\tbr label %$brEnd")
      }
      ctx.out.println(s"$brElse:")
      _else.seq.foreach(stat => evalStat(ctx, dctx, stat))
      ctx.out.println(s"\tbr label %$brEnd")

      ctx.out.println(s"$brEnd:")
      ctx.out.println(s";@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ when end")
  }

  def evalDef(ctx: IrContext, fn: Def) = {
    val fnType = ctx.types(fn.ref.name).asInstanceOf[Fn]
    val argsIr = (fn.args zip fnType.args).map {
      case (argName, argTypeRef) =>
        if (argTypeRef.isRegisterFit(ctx.types)) argTypeRef.toValue(ctx.types) + " " + irLocalName(argName)
        else argTypeRef.toPtr(ctx.types) + " " + irLocalName(argName)
    }

    val realArgs =
      if (fnType.closure.isEmpty) argsIr
      else argsIr :+ (s"""%"${fnType.name}"* %$$closure""")

    ctx.out.println(s"""define ${fnType.ret.toValue(ctx.types)} @"${fn.name}" (${realArgs.mkString(", ")}) { """)
    fn.code match {
      case LLCode(value) => ctx.out.println(value)
      case AbraCode(vars, stats) =>
        vars.foreach {
          case (vName, typeRef) =>
            if (!typeRef.isVoid(ctx.types))
              ctx.out.println(s"\t${irLocalName(vName)} = alloca ${typeRef.toValue(ctx.types)}")
        }

        val dctx = DefContext(fn, vars)
        stats.foreach(stat => evalStat(ctx, dctx, stat))
    }
    ctx.out.println("}")
  }

  def gen(out: PrintStream,
          lowCode: Seq[String],
          types: mutable.HashMap[String, Type],
          defs: mutable.HashMap[String, Def]) = {
    val protos = mutable.HashMap[String, TypeRef]()
    defs.map {
      case (k, v) => protos.put(k, v.ref)
    }

    val ctx = IrContext(out, types, protos, defs)

    ctx.out.println(
      """
        |declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)
        |declare i8* @malloc(i64)
        |declare void @free(i8*)
        |define i8*  @rcAlloc(i64 %size) {
        |    %realSize = add nsw i64 %size, 8
        |    %block = call i8* @malloc(i64 %realSize)
        |    %1 = bitcast i8* %block to i64*
        |    store i64 1, i64* %1
        |    %ptr = getelementptr i8, i8* %block, i64 8
        |    ret i8* %ptr
        |}
        |define void @rcInc(i8* %obj) {
        |    %1 = bitcast i8* %obj to i64*
        |    %2 = getelementptr i64, i64* %1, i64 -1
        |    %rc = load i64, i64* %2
        |    %newRc = add nsw i64 %rc, 1
        |    store i64 %newRc, i64* %2
        |    ret void
        |}
        |define i8 @rcRelease(i8* %obj) {
        |    %1 = getelementptr i8, i8* %obj, i64 -8
        |    %2 = bitcast i8* %1 to i64*
        |    %3 = getelementptr i64, i64* %2, i64 0
        |    %rc = load i64, i64* %3
        |    %newRc = add nsw i64 %rc, -1
        |    %4 = icmp eq i64 %newRc, 0
        |
        |    br i1 %4, label %free, label %store
        |  free:
        |    call void @free(i8* %1)
        |    ret i8 1
        |  store:
        |    store i64 %newRc, i64* %3
        |    ret i8 0
        |}
      """.stripMargin)


    ctx.types.values.filter {
      case l: Low => false
      case fn: Fn if fn.closure.isEmpty => false
      case _ => true
    }.foreach(t => out.println(s"""%"${t.name}" = type """ + t.toDecl(types)))

    ctx.types.values.toBuffer[Type].foreach {
      case s: Struct =>
        StoreUtil.genConstructor(ctx, s)
      case _ =>
    }
    ctx.types.values.toBuffer[Type].foreach(t =>
      StoreUtil.genAcquireRelease(ctx, t))

    lowCode.foreach { code => ctx.out.println(code) }

    ctx.out.println()

    defs.values.foreach(fn => evalDef(ctx, fn))
  }
}
