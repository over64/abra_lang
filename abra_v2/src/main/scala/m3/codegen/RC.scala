package m3.codegen

import m3.codegen.IrUtils.{NullableU, RichString, SimpleU, ThIrExtension}
import m3.parse.Ast0._
import m3.typecheck.TCMeta.TypeDeclTCMetaImplicit
import m3.typecheck.Utils.ThExtension
import m3.typecheck.{Builtin, Utils}

object RC {
  def incFnName(th: TypeHint) = (th + "$inc").escaped

  def decFnName(th: TypeHint) = (th + "$dec").escaped

  def genIncDec(mctx: ModContext,
                mode: GenMode,
                th: TypeHint,
                unpackSelf: String => Unit,
                writeDest: String => Unit): Unit = {

    val irType = th.toValue

    mode match {
      case Inc =>
        mctx.write(s"define private void @${incFnName(th)} ($irType %self) { ")
        mctx.write(s"  %$$inc = load void (i8*)*, void (i8*)** @evaInc")
        unpackSelf(irType)
        mctx.write(s"  call void %$$inc(i8* %data)")
        mctx.write(s"  ret void")
        mctx.write("}")
      case Dec =>
        mctx.write(s"define private void @${decFnName(th)} ($irType %self) { ")
        mctx.write(s"  %$$dec = load i64 (i8*)*, i64 (i8*)** @evaDec")
        unpackSelf(irType)
        mctx.write(s"  %refcount = call i64 %$$dec(i8* %data)")
        mctx.write(s"  %cond = icmp eq i64 %refcount, 0")
        mctx.write(s"  br i1 %cond, label %free, label %end")
        mctx.write(s"  free:")
        writeDest(irType)
        mctx.write(s"    %$$free = load void (i8*)*, void (i8*)** @evaFree")
        mctx.write(s"    call void %$$free(i8* %data)")
        mctx.write(s"    br label %end")
        mctx.write(s"  end:")
        mctx.write(s"  ret void")
        mctx.write("}")
    }
  }

  def forString(mctx: ModContext, mode: GenMode): Unit =
    genIncDec(mctx, mode, Builtin.thString,
      irType => mctx.write(s"%data = getelementptr i8, i8* %self, i64 0"),
      irType => None)

  def forRefArray(mctx: ModContext, mode: GenMode, arrayTh: ScalarTh): Unit = {
    val elTh = arrayTh.params(0)
    val elIrType = elTh.toValue

    genIncDec(mctx, mode, arrayTh,
      irType => {
        mctx.write(s"  %dataOffset = bitcast $elIrType* %self to i8*")
        mctx.write(s"  %data = getelementptr i8, i8* %dataOffset, i64 -8")
      },
      irType => {
        if (elTh.isRefType(mctx.level, mctx.module)) {
          mctx.write(s"      %lenPtr = bitcast i8* %data to i64*")
          mctx.write(s"      %len = load i64, i64* %lenPtr")
          mctx.write(s"      %i = alloca i64")
          mctx.write(s"      store i64 0, i64* %i")
          mctx.write(s"      br label %ehead")
          mctx.write(s"    ehead:")
          mctx.write(s"      %iv = load i64, i64* %i")
          mctx.write(s"      %econd = icmp eq i64 %iv, %len")
          mctx.write(s"      br i1 %econd, label %eend, label %efree")
          mctx.write(s"    efree:")
          mctx.write(s"      %tPtr = getelementptr $elIrType, $elIrType* %self, i64 %iv")

          elTh.isUnion(mctx.level, mctx.module) match {
            case SimpleU =>
              mctx.write(s"      call void @${decFnName(elTh)}($elIrType* %tPtr)")
            case _ =>
              mctx.write(s"      %tValue = load $elIrType, $elIrType* %tPtr")
              mctx.write(s"      call void @${decFnName(elTh)}($elIrType %tValue)")
          }

          mctx.write(s"      %ii = add nsw i64 %iv, 1")
          mctx.write(s"      store i64 %ii, i64* %i")
          mctx.write(s"      br label %ehead")
          mctx.write(s"    eend:")
        }
      })
  }

  def forScalarRef(mctx: ModContext, mode: GenMode, th: TypeHint, sd: ScalarDecl): Unit =
    genIncDec(mctx, mode, th,
      irType => mctx.write(s"  %data = bitcast $irType %self to i8*"),
      irType => None
    )

  def forStruct(mctx: ModContext, mode: GenMode, th: TypeHint, fields: Seq[FieldTh]): Unit = {
    val irTypeBody = (th + ".body").escaped

    genIncDec(mctx, mode, th,
      irType => mctx.write(s"  %data = bitcast $irType %self to i8*"),
      irType => {
        fields.zipWithIndex.filter { case (f, i) => f.typeHint.isRefType(mctx.level, mctx.module) }.foreach {
          case (f, idx) =>
            val typeRef = f.typeHint.toValue
            mctx.write(s"    %${f.name} = getelementptr %$irTypeBody, $irType %self, i64 0, i32 $idx")

            f.typeHint.isUnion(mctx.level, mctx.module) match {
              case SimpleU =>
                mctx.write(s"    call void @${decFnName(f.typeHint)}($typeRef* %${f.name})")
              case _ =>
                mctx.write(s"    %${f.name}.v = load $typeRef, $typeRef* %${f.name}")
                mctx.write(s"    call void @${decFnName(f.typeHint)}($typeRef %${f.name}.v)")
            }
        }
      })
  }

  def forNullableUnion(mctx: ModContext, mode: GenMode, th: TypeHint, variant: TypeHint): Unit = {

    val irType = th.toValue
    val dname = if (mode == Inc) incFnName(th) else decFnName(th)

    mctx.write(s"""define private void @$dname ($irType %self) { """)

    val vIrType = variant.toValue
    mctx.write(s"  %1 = icmp eq $irType %self, null ")
    mctx.write(s"  br i1 %1, label %end, label %do")
    mctx.write(s"  do:")

    val f = if (mode == Inc) incFnName(variant) else decFnName(variant)

    mctx.write(s"    %variant = bitcast $irType %self to $vIrType")
    mctx.write(s"    call void @$f($vIrType %variant)")
    mctx.write(s"    br label %end")

    mctx.write(s"  end:")
    mctx.write(s"  ret void")
    mctx.write(s"}")
  }

  def forUnion(mctx: ModContext, mode: GenMode, th: TypeHint, variants: Seq[TypeHint]): Unit = {
    val irType = th.toValue
    val dname = if (mode == Inc) incFnName(th) else decFnName(th)

    mctx.write(s"""define private void @$dname ($irType* %self) { """)

    mctx.write(s"  %tagPtr = getelementptr $irType, $irType* %self, i64 0, i32 0 ")
    mctx.write(s"  %tag = load i64, i64* %tagPtr")
    mctx.write(s"  switch i64 %tag, label %end [")

    val needSeq = variants.zipWithIndex.map { case (v, idx) =>
      val need = v.isRefType(mctx.level, mctx.module)
      if (need)
        mctx.write(s"    i64 $idx, label %br$idx")

      need
    }

    mctx.write("  ]")

    (variants.zipWithIndex zip needSeq).foreach {
      case ((vth, idx), true) =>
        mctx.write(s"  br$idx:")


        val vIrType = vth.toValue
        val f = if (mode == Inc) incFnName(vth) else decFnName(vth)

        mctx.write(s"  %cast$idx = bitcast $irType* %self to {i64, $vIrType}*")
        mctx.write(s"  %x${idx}Ptr = getelementptr {i64, $vIrType}, {i64, $vIrType}* %cast$idx, i64 0, i32 1")

        vth.isUnion(mctx.level, mctx.module) match {
          case SimpleU =>
            mctx.write(s"    call void @$f($vIrType* %x${idx}Ptr)")
          case _ =>
            mctx.write(s"    %x$idx = load $vIrType, $vIrType* %x${idx}Ptr")
            mctx.write(s"    call void @$f($vIrType %x$idx)")
        }

        mctx.write(s"  br label %end")
      case _ =>
    }

    mctx.write(s"  end:")
    mctx.write(s"  ret void")
    mctx.write("}")
  }

  // need to impl th.classify?
  def genAcquireRelease(mctx: ModContext, mode: GenMode, th: TypeHint): Unit =
    if (th.isRefType(mctx.level, mctx.module))
      th match {
        case Builtin.thNil =>
        case Builtin.thString =>
          forString(mctx, mode)
        case sth: ScalarTh =>
          Utils.resolveType(mctx.level, mctx.module, sth) match {
            case (_, _, sd: ScalarDecl) =>
              forScalarRef(mctx, mode, sth, sd)
            case (_, _, sd: StructDecl) if sd.isBuiltinArray &&
              (sd.getBuiltinArrayLen == None || sth.params(0).isRefType(mctx.level, mctx.module)) =>
              forRefArray(mctx, mode, sth)
            case (ieSeq, _, sd: StructDecl) =>
              val specMap = Utils.makeSpecMap(sd.params, sth.params)
              val fields = sd.fields.map(f => FieldTh(f.name, f.th.moveToMod(ieSeq).spec(specMap)))
              forStruct(mctx, mode, sth, fields)
            case (ieSeq, _, ud: UnionDecl) =>
              sth.isUnion(mctx.level, mctx.module) match {
                case NullableU(variant) =>
                  forNullableUnion(mctx, mode, sth, variant)
                case _ =>
                  val variants = ud.variants.map(th => th.moveToMod(ieSeq))
                  forUnion(mctx, mode, sth, variants)
              }
            case _ =>
          }
        case uth: UnionTh =>
          uth.isUnion(mctx.level, mctx.module) match {
            case NullableU(variant) =>
              forNullableUnion(mctx, mode, uth, variant)
            case _ =>
              forUnion(mctx, mode, uth, uth.seq)
          }
        case _ =>
      }
}
