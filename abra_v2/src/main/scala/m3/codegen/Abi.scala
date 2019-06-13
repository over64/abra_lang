package m3.codegen

import m3.codegen.IrUtils.{NoUnion, NullableU, SimpleU, ThIrExtension}
import m3.parse.Ast0._
import m3.typecheck.Builtin
import IrUtils.RichString

sealed trait GenMode
case object Inc extends GenMode
case object Dec extends GenMode

object AbiTh {
  def toStoreSrc(mctx: ModContext, th: TypeHint): String =
    th.classify(mctx.level, mctx.module) match {
      case NullableUnion(_) | RefStruct(_) | RefScalar | ValueScalar => th.toValue
      case RefUnion(_) => th.toValue + "*"
      case ValueUnion(_) | ValueStruct(_) => th.toValue
    }

  def toRetVal(mctx: ModContext, th: TypeHint): String =
    th.toValue

  def toCallArg(mctx: ModContext, th: TypeHint): String =
    th.classify(mctx.level, mctx.module) match {
      case NullableUnion(_) | RefStruct(_) | RefScalar | ValueScalar => th.toValue
      case RefUnion(_) => th.toValue + "*"
      case ValueUnion(_) | ValueStruct(_) => th.toValue + "*"
    }
}

object Abi {

  def syncValue(mctx: ModContext, dctx: DContext,
                res: EResult, as: RequireDest,
                requireTh: TypeHint, hasTh: TypeHint): EResult = {

    def loadResValue(th: TypeHint, res: EResult): EResult =
      if (!res.isPtr) res
      else {
        val r = "%" + dctx.nextReg("")
        dctx.write(s"$r = load ${th.toValue}, ${th.toValue}* ${res.value}")
        EResult(r, false, res.isAnon)
      }

    def storeResAsPtr(destTh: TypeHint, srcTh: TypeHint, res: EResult): EResult =
      if (res.isPtr) res
      else {
        val slot = dctx.addSlot(requireTh)
        store(mctx, dctx, false, false, destTh, srcTh, slot, loadResValue(srcTh, res).value)
        EResult(slot, true, res.isAnon)
      }

    val sameThRes =
      if (requireTh != hasTh)
        requireTh.classify(mctx.level, mctx.module) match {
          case NullableUnion(_) =>
            if (hasTh == Builtin.thNil) EResult("null", false, res.isAnon)
            else res
          case RefUnion(_) => storeResAsPtr(requireTh, hasTh, res)
          case ValueUnion(_) => storeResAsPtr(requireTh, hasTh, res)
          case _ =>
            throw new RuntimeException("unreachable")
        }
      else res

    requireTh.classify(mctx.level, mctx.module) match {
      case NullableUnion(_) | RefStruct(_) | RefScalar | ValueScalar =>
        loadResValue(requireTh, sameThRes)
      case RefUnion(_) =>
        as match {
          case AsStoreSrc | AsCallArg => storeResAsPtr(requireTh, requireTh, sameThRes)
          case _ => loadResValue(requireTh, sameThRes)
        }
      case ValueUnion(_) | ValueStruct(_) =>
        as match {
          case AsCallArg => storeResAsPtr(requireTh, requireTh, sameThRes)
          case _ => loadResValue(requireTh, sameThRes)
        }
    }
  }


  def performStore(mctx: ModContext, dctx: DContext, destTh: TypeHint, srcTh: TypeHint,
                   destPtr: String, src: String): Unit = {

    val srcIrType = srcTh.toValue
    val destIrType = destTh.toValue

    if (destTh == srcTh)
      dctx.write(s"store $srcIrType $src, $destIrType* $destPtr")
    else {
      /* member to union */
      val variants = destTh.asUnion(mctx.level, mctx.module)

      destTh.isUnion(mctx.level, mctx.module) match {
        case NullableU(_) =>
          if (srcTh == Builtin.thNil)
            dctx.write(s"store $destIrType null, $destIrType* $destPtr")
          else
            dctx.write(s"store $srcIrType $src, $destIrType* $destPtr")
        case _ =>
          val memberIdx = variants.indexOf(srcTh)
          if (memberIdx == -1)
            throw new RuntimeException("oops!")

          if (srcTh == Builtin.thNil) {
            val r1 = "%" + dctx.nextReg("")
            dctx.write(s"$r1 = getelementptr $destIrType, $destIrType* $destPtr, i64 0, i32 0")
            dctx.write(s"store i64 $memberIdx, i64* $r1")
          } else {
            val r1, r2, r3 = "%" + dctx.nextReg("")

            dctx.write(s"$r1 = bitcast $destIrType* $destPtr to {i64, $srcIrType}*")
            dctx.write(s"$r2 = getelementptr {i64, $srcIrType}, {i64, $srcIrType}* $r1, i64 0, i32 0")
            dctx.write(s"store i64 $memberIdx, i64* $r2")
            dctx.write(s"$r3 = getelementptr {i64, $srcIrType}, {i64, $srcIrType}*  $r1, i64 0, i32 1")
            dctx.write(s"store $srcIrType $src, $srcIrType* $r3")
          }
      }
    }
  }

  def storeValue(mctx: ModContext, dctx: DContext, needDec: Boolean,
                 destTh: TypeHint, srcTh: TypeHint,
                 destPtr: String, src: String): Unit = {

    if (destTh.isRefType(mctx.level, mctx.module) && needDec)
      dctx.write(s"call void @${RC.decFnName(srcTh)}(${destTh.toValue} $destPtr)")

    performStore(mctx, dctx, destTh, srcTh, destPtr, src)
  }

  def storeRefValue(mctx: ModContext, dctx: DContext, needDec: Boolean, needInc: Boolean,
                    destTh: TypeHint, srcTh: TypeHint,
                    destPtr: String, src: String): Unit = {

    if (srcTh.isRefType(mctx.level, mctx.module) && needInc)
      dctx.write(s"call void @${RC.incFnName(srcTh)}(${srcTh.toValue} $src)")

    if (destTh.isRefType(mctx.level, mctx.module) && needDec)
      dctx.write(s"call void @${RC.decFnName(srcTh)}(${destTh.toValue} $destPtr)")

    performStore(mctx, dctx, destTh, srcTh, destPtr, src)
  }

  def storeNullableUnion(mctx: ModContext, dctx: DContext,
                         needDec: Boolean, needInc: Boolean,
                         destTh: TypeHint, srcTh: TypeHint,
                         destPtr: String, src: String): Unit = {

    if (srcTh.isRefType(mctx.level, mctx.module) && needInc)
      dctx.write(s"call void @${RC.incFnName(srcTh)}(${srcTh.toValue} $src)")

    if (destTh.isRefType(mctx.level, mctx.module) && needDec)
      dctx.write(s"call void @${RC.decFnName(srcTh)}(${destTh.toValue} $destPtr)")

    performStore(mctx, dctx, destTh, srcTh, destPtr, src)
  }

  def storeUnion(mctx: ModContext, dctx: DContext,
                 needDec: Boolean, needInc: Boolean,
                 destTh: TypeHint, srcTh: TypeHint,
                 destPtr: String, srcPtr: String): Unit = {

    val srcIrType = srcTh.toValue

    if (srcTh.isRefType(mctx.level, mctx.module) && needInc)
      dctx.write(s"call void @${RC.incFnName(srcTh)}($srcIrType $srcPtr)")

    if (destTh.isRefType(mctx.level, mctx.module) && needDec)
      dctx.write(s"call void @${RC.decFnName(srcTh)}(${destTh.toValue} $destPtr)")

    val r1 = "%" + dctx.nextReg("")
    dctx.write(s"$r1 = load $srcIrType, $srcIrType* $srcPtr")

    performStore(mctx, dctx, destTh, srcTh, destPtr, r1)
  }

  def store(mctx: ModContext,
            dctx: DContext,
            needDec: Boolean,
            needInc: Boolean,
            destTh: TypeHint, srcTh: TypeHint,
            destPtr: String, src: String): Unit =

    srcTh.isUnion(mctx.level, mctx.module) match {
      case SimpleU =>
        storeUnion(mctx, dctx, needDec, needInc, destTh, srcTh, destPtr, src)
      case NullableU(_) =>
        storeNullableUnion(mctx, dctx, needDec, needInc, destTh, srcTh, destPtr, src)
      case NoUnion =>
        if (srcTh.isValueType(mctx.level, mctx.module))
          storeValue(mctx, dctx, needDec, destTh, srcTh, destPtr, src)
        else
          storeRefValue(mctx, dctx, needDec, needInc, destTh, srcTh, destPtr, src)
    }
}