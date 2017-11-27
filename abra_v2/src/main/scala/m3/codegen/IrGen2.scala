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
                 name: String): (TypeRef, Boolean) =
    dctx.vars.get(name) match {
      case Some(tref) => (tref, true)
      case None =>
        dctx.fn.args.find(arg => arg.name == name) match {
          case Some(field) => (field.ref, !field.ref.isRegisterFit(ctx.types))
          case None =>
            dctx.fn.closure.find(cf => cf.name == name) match {
              case Some(cf) =>
                cf.ctype match {
                  case Local(ref) => (ref, true)
                  case Param(ref) => (ref, !ref.isRegisterFit(ctx.types))
                }
              case None =>
                val d = ctx.defs(name)
                (FnRef(d.closure.map(_.ctype), d.args.map(_.ref), d.ret), false)
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
        tref match {
          case ScalarRef(name) =>
            ctx.types(name) match {
              case Struct(name, fields) => fieldsTo(fields, tref, seq, prop)
              case Union(name, variants) => variantsTo(variants, tref, seq, prop)
              case _ => throw new RuntimeException("oops")
            }
          case StructRef(fields) => fieldsTo(fields, tref, seq, prop)
          case UnionRef(variants) => variantsTo(variants, tref, seq, prop)
          case _ => throw new RuntimeException("oops")
        }
    }

    val irType =
      if (tref.isRef(ctx.types)) tref.toValue(ctx.types).stripSuffix("*")
      else tref.toValue(ctx.types)
    val reg = dctx.nextReg().toString

    ctx.out.println(s"\t%$reg = getelementptr $irType, $irType* ${irLocalName(dataPtr)}, ${elements.map(e => "i32 " + e).mkString(", ")}")
    (resultTref, true, reg)
  }

  def evalId(ctx: IrContext,
             dctx: DefContext,
             id: Id): (TypeRef, Boolean, String) = {
    val (initialTref, isPtrToType) = findSymbol(ctx, dctx, id.v)
    if (id.props.isEmpty) return (initialTref, isPtrToType, id.v)

    if (initialTref.isValue(ctx.types) && !isPtrToType) throw new RuntimeException("cannot do GEP on value")

    val dataPtr =
      if (initialTref.isRef(ctx.types) && isPtrToType) {
        val reg = dctx.nextReg().toString
        val irType = initialTref.toValue(ctx.types)
        ctx.out.println(s"\t%$reg = load $irType, $irType* ${irLocalName(id.v)}")
        reg
      } else id.v

    evalGep(ctx, dctx, initialTref, dataPtr, id.props)
  }

  def requirePtr(data: (TypeRef, Boolean, String)): (TypeRef, String) = {
    val (tref, isPtr, name) = data
    if (isPtr == false) throw new RuntimeException("expected pointer")
    (tref, name)
  }

  def requireValue(ctx: IrContext, dctx: DefContext, data: (TypeRef, Boolean, String)): (TypeRef, String) = {
    val (tref, isPtr, name) = data
    if (isPtr == false) return (tref, name)

    val irType = tref.toValue(ctx.types)
    val reg = dctx.nextReg().toString
    ctx.out.println(s"\t%$reg = load $irType, $irType* ${irLocalName(name)}")
    (tref, reg)
  }

  def evalCall(ctx: IrContext, dctx: DefContext, call: Call): Option[(TypeRef, String)] = {
    val (tref, vName) = requireValue(ctx, dctx, evalId(ctx, dctx, call.id))
    val fnType = tref match {
      case fn: FnRef => fn
      case _ => throw new RuntimeException("expected fn to call")
    }
    val irArgs = call.args.map { arg =>
      val (tref, isPtr, name) = evalId(ctx, dctx, arg)
      if (tref.isRegisterFit(ctx.types) && isPtr) {
        val (_, argName) = requireValue(ctx, dctx, (tref, isPtr, name))
        s"${tref.toValue(ctx.types)} %$argName"
      }
      else s"${tref.toPtr(ctx.types)} %$name"
    }

    val ret =
      if (fnType.ret.isVoid(ctx.types)) None
      else Some((fnType.ret, dctx.nextReg().toString))

    val retType = ret.map(r => r._1.toValue(ctx.types)).getOrElse("")
    val retId = ret.map(r => "%" + r._2 + " = ").getOrElse("")

    ctx.out.println(s"\t${retId}call $retType @$vName(${irArgs.mkString(", ")})")
    ret
  }

  def evalStore(ctx: IrContext,
                dctx: DefContext,
                dest: Id, src: Storable, init: Boolean) = {
    val (toTref, to) = requirePtr(evalId(ctx, dctx, dest))

    val (whatTref, what) =
      src match {
        case id: Id =>
          val (whatTref, what) = requireValue(ctx, dctx, evalId(ctx, dctx, id))
          val irType = toTref.toValue(ctx.types)

          if (whatTref.isRef(ctx.types)) {
            val r1 = dctx.nextReg()
            ctx.out.println(s"\t%$r1 = bitcast $irType ${irLocalName(what)} to i8*")
            ctx.out.println(s"\tcall void @rcInc(i8* %$r1)")
          }

          (whatTref, "%" + what)
        case call: Call =>
          val (whatTref, what) = evalCall(ctx, dctx, call).get
          val irType = toTref.toValue(ctx.types)

          if (toTref.isRef(ctx.types)) {
            val r1 = dctx.nextReg()
            ctx.out.println(s"\t%$r1 = bitcast $irType ${irLocalName(what)} to i8*")
            ctx.out.println(s"\tcall void @rcInc(i8* %$r1)")
          }

          (whatTref, "%" + what)
      }

    val (irType, realTo) =
      toTref match {
        case self@ScalarRef(name) =>
          if (self == whatTref) (toTref.toValue(ctx.types), to)
          else
            ctx.types(name) match {
              case Union(name, variants) =>
                val idx = variants.indexOf(whatTref)
                val (tref, _, v) = evalGep(ctx, dctx, toTref, to, Seq(idx.toString))

                val irType = toTref.toValue(ctx.types)
                val r1 = dctx.nextReg()
                ctx.out.println(s"\t%$r1 = getelementptr $irType, $irType* %$to, i64 0, i32 0")
                ctx.out.println(s"\tstore i8 $idx, i8* %$r1")

                (tref.toValue(ctx.types), v)
              case _ => (toTref.toValue(ctx.types), to)
            }
        case UnionRef(variants) => null
        case _ => (toTref.toValue(ctx.types), to)
      }


    if (!init && toTref.isRef(ctx.types)) {
      val r2 = dctx.nextReg()
      val r3 = dctx.nextReg()
      ctx.out.println(s"\t%$r2 = load $irType, $irType* ${irLocalName(realTo)}")
      ctx.out.println(s"\t%$r3 = bitcast $irType %$r2 to i8*")
      ctx.out.println(s"\tcall void @rcDec(i8* %$r3)")
    }

    ctx.out.println(s"\tstore $irType $what, $irType* ${irLocalName(realTo)}")
  }
  def evalStat(ctx: IrContext,
               dctx: DefContext,
               stat: Stat) = stat match {
    case Init(dest, src) => evalStore(ctx, dctx, dest, src, init = true)
    case Store(dest, src) => evalStore(ctx, dctx, dest, src, init = false)
    case Free(id) =>
      val tref = dctx.vars(id.v)
      if (tref.isRef(ctx.types)) {
        val irType = tref.toValue(ctx.types)
        val r1 = dctx.nextReg()
        val r2 = dctx.nextReg()
        ctx.out.println(s"\t%$r1 = load $irType, $irType* %${id.v}")
        ctx.out.println(s"\t%$r2 = bitcast $irType %$r1 to i8*")
        ctx.out.println(s"\tcall void @rcDec(i8* %$r2)")
      }
    case call: Call =>
      evalCall(ctx, dctx, call)
    case Ret(idOpt) =>
      idOpt match {
        case Some(id) =>
          val (typeRef, what) = requireValue(ctx, dctx, evalId(ctx, dctx, Id(id, Seq.empty)))
          ctx.out.println(s"\tret ${typeRef.toValue(ctx.types)} %$what")
        case None => ctx.out.println("\tret void")
      }
  }

  def evalDef(ctx: IrContext, fn: Def) = {
    var nextAnonStr = new Function0[String] {
      var id = 0
      override def apply() = {
        id += 1
        "@_anon_str" + id
      }
    }

    val argsIr = fn.args.map { f =>
      if (f.ref.isRegisterFit(ctx.types)) f.ref.toValue(ctx.types) + " " + irLocalName(f.name)
      else f.ref.toPtr(ctx.types) + " " + irLocalName(f.name)
    }.mkString(", ")

    ctx.out.println(s"define ${fn.ret.toValue(ctx.types)} ${irDefName(fn.name)} ($argsIr) { ")
    fn.code match {
      case LLCode(value) => ctx.out.println(value)
      case AbraCode(vars, stats) =>
        vars.foreach {
          case (vName, typeRef) =>
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
    val ctx = IrContext(out, types, defs)

    ctx.out.println(
      """
        |declare void @llvm.memcpy.p0i8.p0i8.i64(i8* nocapture, i8* nocapture readonly, i64, i32, i1)
        |declare i8* @malloc(i64)
        |declare void @free(i8*)
        |define i8*  @rcAlloc(i64 %size) {
        |    %realSize = add nsw i64 %size, 8
        |    %block = call i8* @malloc(i64 %realSize)
        |    %1 = bitcast i8* %block to i64*
        |    store i64 0, i64* %1
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
        |define void @rcDec(i8* %obj) {
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
        |    br label %end
        |
        |  store:
        |    store i64 %newRc, i64* %3
        |    br label %end
        |
        |  end:
        |    ret void
        |}
      """.stripMargin)

    lowCode.foreach { code => ctx.out.println(code) }

    types.values.filter {
      case l: Low => false
      case _ => true
    }.foreach(t => out.println(t.toDecl(types)))

    ctx.out.println()

    defs.values.foreach(fn => evalDef(ctx, fn))
  }
}
