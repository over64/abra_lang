package m3.codegen

import m3.codegen.IrUtils.{RichString, SimpleU, ThIrExtension}
import m3.parse.Ast0._
import m3.typecheck.Builtin
import m3.typecheck.Utils.ThExtension

object RC {
  implicit class RichSB(self: StringBuilder) {
    def write(line: String): Unit =
      self.append(line + "\n")
  }

  def incFnName(th: TypeHint) = (th + "$inc").escaped

  def decFnName(th: TypeHint) = (th + "$dec").escaped

  def genIncDec(mctx: ModContext,
                mode: RCMode,
                buff: StringBuilder,
                th: TypeHint,
                unpackSelf: String => Unit,
                writeDest: String => Unit): Unit = {

    val irType = th.toValue

    mode match {
      case Inc =>
        buff.write(s"define private void @${incFnName(th)} ($irType %self) { ")
        buff.write(s"  %$$inc = load void (i8*)*, void (i8*)** @evaInc")
        unpackSelf(irType)
        buff.write(s"  call void %$$inc(i8* %data)")
        buff.write(s"  ret void")
        buff.write("}")
      case Dec =>
        buff.write(s"define private void @${decFnName(th)} ($irType %self) { ")
        buff.write(s"  %$$dec = load i64 (i8*)*, i64 (i8*)** @evaDec")
        unpackSelf(irType)
        buff.write(s"  %refcount = call i64 %$$dec(i8* %data)")
        buff.write(s"  %cond = icmp eq i64 %refcount, 0")
        buff.write(s"  br i1 %cond, label %free, label %end")
        buff.write(s"  free:")
        writeDest(irType)
        buff.write(s"    %$$free = load void (i8*)*, void (i8*)** @evaFree")
        buff.write(s"    call void %$$free(i8* %data)")
        buff.write(s"    br label %end")
        buff.write(s"  end:")
        buff.write(s"  ret void")
        buff.write("}")
    }
  }

  def forString(mctx: ModContext, mode: RCMode, buff: StringBuilder): Unit =
    genIncDec(mctx, mode, buff, Builtin.thString,
      irType => buff.write(s"  %data = getelementptr i8, i8* %self, i64 0"),
      irType => None)

  def forRefArray(mctx: ModContext, mode: RCMode, buff: StringBuilder, arrayTh: ScalarTh): Unit = {
    val elTh = arrayTh.params(0)
    genRcBase(mctx, mode, elTh)

    val elIrType = elTh.toValue

    genIncDec(mctx, mode, buff, arrayTh,
      irType => {
        buff.write(s"  %dataOffset = bitcast $elIrType* %self to i8*")
        buff.write(s"  %data = getelementptr i8, i8* %dataOffset, i64 -8")
      },
      irType => {
        if (elTh.isRefType(mctx.level, mctx.module)) {
          buff.write(s"      %lenPtr = bitcast i8* %data to i64*")
          buff.write(s"      %len = load i64, i64* %lenPtr")
          buff.write(s"      %i = alloca i64")
          buff.write(s"      store i64 0, i64* %i")
          buff.write(s"      br label %ehead")
          buff.write(s"    ehead:")
          buff.write(s"      %iv = load i64, i64* %i")
          buff.write(s"      %econd = icmp eq i64 %iv, %len")
          buff.write(s"      br i1 %econd, label %eend, label %efree")
          buff.write(s"    efree:")
          buff.write(s"      %tPtr = getelementptr $elIrType, $elIrType* %self, i64 %iv")

          elTh.isUnion(mctx.level, mctx.module) match {
            case SimpleU =>
              buff.write(s"      call void @${decFnName(elTh)}($elIrType* %tPtr)")
            case _ =>
              buff.write(s"      %tValue = load $elIrType, $elIrType* %tPtr")
              buff.write(s"      call void @${decFnName(elTh)}($elIrType %tValue)")
          }

          buff.write(s"      %ii = add nsw i64 %iv, 1")
          buff.write(s"      store i64 %ii, i64* %i")
          buff.write(s"      br label %ehead")
          buff.write(s"    eend:")
        }
      })
  }

  def forScalarRef(mctx: ModContext, mode: RCMode, buff: StringBuilder, th: TypeHint): Unit =
    genIncDec(mctx, mode, buff, th,
      irType => buff.write(s"  %data = bitcast $irType %self to i8*"),
      irType => None
    )

  def forStruct(mctx: ModContext, mode: RCMode, buff: StringBuilder, th: TypeHint, fields: Seq[FieldTh]): Unit = {
    val irTypeBody = (th + ".body").escaped

    genIncDec(mctx, mode, buff, th,
      irType => buff.write(s"  %data = bitcast $irType %self to i8*"),
      irType => {
        fields.zipWithIndex.filter { case (f, i) => f.typeHint.isRefType(mctx.level, mctx.module) }.foreach {
          case (f, idx) =>
            genRcBase(mctx, mode, f.typeHint)

            val typeRef = f.typeHint.toValue
            buff.write(s"    %${f.name} = getelementptr %$irTypeBody, $irType %self, i64 0, i32 $idx")

            f.typeHint.isUnion(mctx.level, mctx.module) match {
              case SimpleU =>
                buff.write(s"    call void @${decFnName(f.typeHint)}($typeRef* %${f.name})")
              case _ =>
                buff.write(s"    %${f.name}.v = load $typeRef, $typeRef* %${f.name}")
                buff.write(s"    call void @${decFnName(f.typeHint)}($typeRef %${f.name}.v)")
            }
        }
      })
  }

  def forNullableUnion(mctx: ModContext, mode: RCMode, buff: StringBuilder, th: TypeHint, variant: TypeHint): Unit = {
    genRcBase(mctx, mode, variant)

    val irType = th.toValue
    val dname = if (mode == Inc) incFnName(th) else decFnName(th)

    buff.write(s"""define private void @$dname ($irType %self) { """)

    val vIrType = variant.toValue
    buff.write(s"  %1 = icmp eq $irType %self, null ")
    buff.write(s"  br i1 %1, label %end, label %do")
    buff.write(s"  do:")

    val f = if (mode == Inc) incFnName(variant) else decFnName(variant)

    buff.write(s"    %variant = bitcast $irType %self to $vIrType")
    buff.write(s"    call void @$f($vIrType %variant)")
    buff.write(s"    br label %end")

    buff.write(s"  end:")
    buff.write(s"  ret void")
    buff.write(s"}")
  }

  def forUnion(mctx: ModContext, mode: RCMode, buff: StringBuilder, th: TypeHint, variants: Seq[TypeHint]): Unit = {
    val irType = th.toValue
    val dname = if (mode == Inc) incFnName(th) else decFnName(th)

    buff.write(s"""define private void @$dname ($irType* %self) { """)

    buff.write(s"  %tagPtr = getelementptr $irType, $irType* %self, i64 0, i32 0 ")
    buff.write(s"  %tag = load i64, i64* %tagPtr")
    buff.write(s"  switch i64 %tag, label %end [")

    val needSeq = variants.zipWithIndex.map { case (v, idx) =>
      genRcBase(mctx, mode, v)

      val need = v.isRefType(mctx.level, mctx.module)
      if (need)
        buff.write(s"    i64 $idx, label %br$idx")

      need
    }

    buff.write("  ]")

    (variants.zipWithIndex zip needSeq).foreach {
      case ((vth, idx), true) =>
        buff.write(s"  br$idx:")


        val vIrType = vth.toValue
        val f = if (mode == Inc) incFnName(vth) else decFnName(vth)

        buff.write(s"  %cast$idx = bitcast $irType* %self to {i64, $vIrType}*")
        buff.write(s"  %x${idx}Ptr = getelementptr {i64, $vIrType}, {i64, $vIrType}* %cast$idx, i64 0, i32 1")

        vth.isUnion(mctx.level, mctx.module) match {
          case SimpleU =>
            buff.write(s"    call void @$f($vIrType* %x${idx}Ptr)")
          case _ =>
            buff.write(s"    %x$idx = load $vIrType, $vIrType* %x${idx}Ptr")
            buff.write(s"    call void @$f($vIrType %x$idx)")
        }

        buff.write(s"  br label %end")
      case _ =>
    }

    buff.write(s"  end:")
    buff.write(s"  ret void")
    buff.write("}")
  }

  def genRc(mctx: ModContext, mode: RCMode, buff: StringBuilder, th: TypeHint): Unit =
    th.classify(mctx.level, mctx.module) match {
      case NullableUnion(variant) =>
        forNullableUnion(mctx, mode, buff, th, variant)
      case RefUnion(variants) =>
        forUnion(mctx, mode, buff, th, variants)
      case RefStruct(fields) =>
        if (th.isArray)
          forRefArray(mctx, mode, buff, th.asInstanceOf[ScalarTh])
        else
          forStruct(mctx, mode, buff, th, fields)
      case RefScalar =>
        if (th == Builtin.thString)
          forString(mctx, mode, buff)
        else
          forScalarRef(mctx, mode, buff, th)
      case _ =>
    }

  def genRcBase(mctx: ModContext, mode: RCMode, th: TypeHint): Unit = {
    if (!mctx.rcDef.contains((th, mode))) {
      val buff = new StringBuilder
      mctx.rcDef.put((th, mode), buff)
      genRc(mctx, mode, buff, th)
    }
  }

  def doRC(mctx: ModContext, dctx: DContext, mode: RCMode, th: TypeHint, value: String): Unit = {
    genRcBase(mctx, mode, th)

    th.classify(mctx.level, mctx.module) match {
      case RefUnion(_) =>
        val fnName = if (mode == Dec) decFnName(th) else incFnName(th)
        dctx.write(s"call void @$fnName(${th.toValue}* $value)")
      case NullableUnion(_) | RefStruct(_) | RefScalar =>
        if (mode == Dec) {
          val r = "%" + dctx.nextReg("")
          dctx.write(s"$r = load ${th.toValue}, ${th.toValue}* $value")
          dctx.write(s"call void @${decFnName(th)}(${th.toValue} $r)")
        } else
          dctx.write(s"call void @${incFnName(th)}(${th.toValue} $value)")
      case _ =>
    }
  }
}
