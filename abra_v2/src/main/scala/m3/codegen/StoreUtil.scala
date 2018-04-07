package m3.codegen

import m3.codegen.Ast2._
import m3.codegen.IrUtil._

object StoreUtil {

  def genConstructor(ctx: IrContext, s: Struct) = {
    val dtypeName = "\"" + s"\\${s.fields.map(f => f.ref.name).mkString(", ")} -> ${s.name}" + "\""
    val dtype = Fn(dtypeName, Seq.empty, s.fields.map(f => TypeRef(f.ref.name)), TypeRef(s.name))
    val dname = s"${s.name}.$$cons"

    ctx.types.put(dtypeName, dtype)

    val dctx = DefContext(
      Def(dname, TypeRef(dtypeName),
        closure = Seq.empty,
        args = s.fields.map(_.name),
        LLCode(""),
        isAnon = true),
      Map("r" -> TypeRef(s.name)))

    val argsIr = (s.fields.map(_.name) zip dtype.args).map {
      case (argName, argTypeRef) =>
        if (argTypeRef.isRegisterFit(ctx.types)) argTypeRef.toValue(ctx.types) + " " + irLocalName(argName)
        else argTypeRef.toPtr(ctx.types) + " " + irLocalName(argName)
    }.mkString(", ")

    ctx.out.println(s"""define ${dtype.ret.toValue(ctx.types)} @"$dname" ($argsIr) { """)

    val irType = TypeRef(s.name).toValue(ctx.types)

    ctx.out.println(s"\t%r = alloca $irType")

    if (TypeRef(s.name).isRef(ctx.types)) {
      val stripped = irType.stripSuffix("*")
      val r1, r2, r3, r4, r5 = dctx.nextReg()
      ctx.out.println(s"\t%$r1 = getelementptr $stripped, $stripped* null, i64 1")
      ctx.out.println(s"\t%$r2 = ptrtoint $stripped* %$r1 to i64")
      ctx.out.println(s"\t%$r3 = load i8* (i64)*,  i8* (i64)** @rcAlloc")
      ctx.out.println(s"\t%$r4 = call i8* %$r3(i64 %$r2)")
      ctx.out.println(s"\t%$r5 = bitcast i8* %$r4 to $irType")
      ctx.out.println(s"\tstore $irType %$r5, $irType* %r")
    }

    val lowStats = s.fields.map { f =>
      Store(init = true, Id("r", Seq(f.name)), Id(f.name))
    } :+ Ast2.Ret(Some("r"))

    lowStats.foreach(s => IrGen2.evalStat(ctx, dctx, s))

    ctx.out.println("}")

    ctx.types.put(dtype.name, dtype)
    ctx.protos.put(dname, TypeRef(dtype.name))
  }

  def genAcquireRelease(ctx: IrContext, t: Type) = {
    t match {
      case s: Struct if TypeRef(s.name).isRef(ctx.types) =>
        def genAcquire() = {
          val dname = s"${s.name}.$$acquire"
          val dtypeName = "\"" + s"\\${s.name} -> Nil" + "\""
          val dtype = Fn(dtypeName, Seq.empty, Seq(TypeRef(s.name)), TypeRef("Nil"))
          val irType = TypeRef(s.name).toValue(ctx.types)

          ctx.out.println(s"""define void @"$dname" ($irType %self) { """)
          ctx.out.println(s"\t%1 = bitcast $irType %self to i8*")
          ctx.out.println(s"\t%2 = load void (i8*)*, void (i8*)** @rcInc")
          ctx.out.println(s"\tcall void %2(i8* %1)")
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.types.put(dtype.name, dtype)
          ctx.protos.put(dname, TypeRef(dtype.name))
        }

        def genRelease() = {
          val dname = s"${s.name}.$$release"
          val dtypeName = "\"" + s"\\${s.name} -> Nil" + "\""
          val dtype = Fn(dtypeName, Seq.empty, Seq(TypeRef(s.name)), TypeRef("Nil"))
          val irType = TypeRef(s.name).toValue(ctx.types)
          val stripped = irType.stripSuffix("*")


          ctx.out.println(s"""define void @"$dname.inner" (i8* %obj) { """)
          ctx.out.println(s"\t%$$self = bitcast i8* %obj to $stripped*")
          s.fields.zipWithIndex.foreach {
            case (f, idx) =>
              if (f.ref.isNeedBeforeAfterStore(ctx.types)) {
                val typeRef = f.ref.toValue(ctx.types)
                ctx.out.println(s"\t%${f.name} = getelementptr $stripped, $stripped* %$$self, i64 0, i32 $idx")
                ctx.out.println(s"\t%${f.name}.v = load $typeRef, $typeRef* %${f.name}")
                val fRelease = "\"" + s"${f.ref.name}.$$release" + "\""
                ctx.out.println(s"\tcall void @$fRelease($typeRef %${f.name}.v)")
              }
          }
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.out.println(s"""define void @"$dname" ($irType %$$self) { """)
          ctx.out.println(s"\t%$$freeFn = load void (i8*, void (i8*)*)*, void (i8*, void (i8*)*)** @rcRelease")
          ctx.out.println(s"\t%obj = bitcast $stripped* %$$self to i8*")
          ctx.out.println(s"""\tcall void %$$freeFn(i8* %obj, void (i8*)* @"$dname.inner")""")
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.types.put(dtype.name, dtype)
          ctx.protos.put(dname, TypeRef(dtype.name))
        }

        genAcquire()
        genRelease()
      case u: Union =>
        def genAcquire() = {
          val dname = s"${u.name}.$$acquire"
          val dtypeName = "\"" + s"\\${u.name} -> Nil" + "\""
          val dtype = Fn(dtypeName, Seq.empty, Seq(TypeRef(u.name)), TypeRef("Nil"))
          val irType = TypeRef(u.name).toValue(ctx.types)

          ctx.out.println(s"""define void @"$dname" ($irType %self) { """)

          u.isNullableUnion(ctx.types) match {
            case Some(tref) =>
              ctx.out.println(s"\t%1 = icmp eq $irType %self, null ")
              ctx.out.println(s"\tbr i1 %1, label %end, label %free")
              ctx.out.println(s"free:")
              val fAquire = "\"" + s"${tref.name}.$$acquire" + "\""
              ctx.out.println(s"\tcall void @$fAquire($irType %self)")
              ctx.out.println(s"\tbr label %end")
              ctx.out.println(s"end:")
            case None =>
              val uIrType = TypeRef(u.name).toValue(ctx.types)
              ctx.out.println(s"\t%tag = extractvalue $uIrType %self, 0 ")
              ctx.out.println(s"\tswitch i8 %tag, label %end [")

              val toGen: Seq[(String, TypeRef)] =
                u.variants.map { v =>
                  val idx = u.fieldTagValue(v)
                  val br = "br" + idx
                  ctx.out.println(s"\t\ti8 $idx, label %$br")
                  (br, v)
                }

              ctx.out.println(s"\t]")

              toGen.foreach {
                case (branch, typeRef) =>
                  val tagIdx = u.fieldTagValue(typeRef)
                  val irIdx = u.fieldIrIndex(typeRef)

                  ctx.out.println(s"$branch:")
                  if (typeRef.isNeedBeforeAfterStore(ctx.types)) {
                    val irType = typeRef.toValue(ctx.types)
                    val fAquire = "\"" + s"${typeRef.name}.$$acquire" + "\""
                    ctx.out.println(s"\t%x$tagIdx = extractvalue $uIrType %self, $irIdx")
                    ctx.out.println(s"\tcall void @$fAquire($irType %x$tagIdx)")
                  }
                  ctx.out.println(s"\tbr label %end")
              }

              ctx.out.println(s"end:")
          }
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.types.put(dtype.name, dtype)
          ctx.protos.put(dname, TypeRef(dtype.name))
        }

        def genRelease() = {
          val dname = s"${u.name}.$$release"
          val dtypeName = "\"" + s"\\${u.name} -> Nil" + "\""
          val dtype = Fn(dtypeName, Seq.empty, Seq(TypeRef(u.name)), TypeRef("Nil"))
          val irType = TypeRef(u.name).toValue(ctx.types)

          ctx.out.println(s"""define void @"$dname" ($irType %self) { """)

          u.isNullableUnion(ctx.types) match {
            case Some(tref) =>
              ctx.out.println(s"\t%1 = icmp eq $irType %self, null ")
              ctx.out.println(s"\tbr i1 %1, label %end, label %free")
              ctx.out.println(s"free:")
              val fRelease = "\"" + s"${tref.name}.$$release" + "\""
              ctx.out.println(s"\tcall void @$fRelease($irType %self)")
              ctx.out.println(s"\tbr label %end")
              ctx.out.println(s"end:")
            case None =>
              val uIrType = TypeRef(u.name).toValue(ctx.types)
              ctx.out.println(s"\t%tag = extractvalue $uIrType %self, 0 ")
              ctx.out.println(s"\tswitch i8 %tag, label %end [")

              val toGen: Seq[(String, TypeRef)] =
                u.variants.map { v =>
                  val idx = u.fieldTagValue(v)
                  val br = "br" + idx
                  ctx.out.println(s"\t\ti8 $idx, label %$br")
                  (br, v)
                }

              ctx.out.println(s"\t]")

              toGen.foreach {
                case (branch, typeRef) =>
                  val tagIdx = u.fieldTagValue(typeRef)
                  val irIdx = u.fieldIrIndex(typeRef)

                  ctx.out.println(s"$branch:")
                  if (typeRef.isNeedBeforeAfterStore(ctx.types)) {
                    val irType = typeRef.toValue(ctx.types)
                    val fRelease = "\"" + s"${typeRef.name}.$$release" + "\""
                    ctx.out.println(s"\t%x$tagIdx = extractvalue $uIrType %self, $irIdx")
                    ctx.out.println(s"\tcall void @$fRelease($irType %x$tagIdx)")
                  }
                  ctx.out.println(s"\tbr label %end")
              }

              ctx.out.println(s"end:")
          }

          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.types.put(dtype.name, dtype)
          ctx.protos.put(dname, TypeRef(dtype.name))
        }

        genAcquire()
        genRelease()
      case l: Low if l.ref == true =>
        def genAcquire() = {
          val dname = s"${l.name}.$$acquire"
          val dtypeName = "\"" + s"\\${l.name} -> Nil" + "\""
          val dtype = Fn(dtypeName, Seq.empty, Seq(TypeRef(l.name)), TypeRef("Nil"))
          val irType = TypeRef(l.name).toValue(ctx.types)

          ctx.out.println(s"""define void @"$dname" ($irType %self) { """)
          ctx.out.println(s"\t%cast = bitcast $irType %self to i8*")
          ctx.out.println(s"\t%incFn = load void (i8*)*, void (i8*)** @rcInc")
          ctx.out.println(s"\tcall void %incFn(i8* %cast)")
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.types.put(dtype.name, dtype)
          ctx.protos.put(dname, TypeRef(dtype.name))
        }

        def genRelease() = {
          val dname = s"${l.name}.$$release"
          val dtypeName = "\"" + s"\\${l.name} -> Nil" + "\""
          val dtype = Fn(dtypeName, Seq.empty, Seq(TypeRef(l.name)), TypeRef("Nil"))
          val irType = TypeRef(l.name).toValue(ctx.types)
          val stripped = irType.stripSuffix("*")

          ctx.out.println(s"""define void @"$dname.inner" (i8* %obj) { """)
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.out.println(s"""define void @"$dname" ($irType %self) { """)
          ctx.out.println(s"\t%cast = bitcast $irType %self to i8*")
          ctx.out.println(s"\t%freeFn = load void (i8*, void (i8*)*)*, void (i8*, void (i8*)*)** @rcRelease")
          ctx.out.println(s"""\tcall void %freeFn(i8* %cast,  void (i8*)* @"$dname.inner")""")
          ctx.out.println(s"\tret void")
          ctx.out.println("}")

          ctx.types.put(dtype.name, dtype)
          ctx.protos.put(dname, TypeRef(dtype.name))
        }

        genAcquire()
        genRelease()
      case _ =>
    }
  }
}
