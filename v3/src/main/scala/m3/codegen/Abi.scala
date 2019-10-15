package m3.codegen

import m3.codegen.IrUtils.{NullableU, ThIrExtension}
import m3.parse.Ast0._
import m3.typecheck.{Builtin, VarClosureLocal, VarClosureParam, VarType}
import m3.typecheck.Utils.ThExtension

sealed trait RequireDest
case object AsStoreSrc extends RequireDest
case object AsCallArg extends RequireDest
case object AsRetVal extends RequireDest

sealed trait RCMode
case object Inc extends RCMode
case object Dec extends RCMode

object AbiTh {
  def toStoreSrc(mctx: ModContext, th: TypeHint): String =
    th.classify(mctx) match {
      case NullableUnion(_) | RefStruct(_) | RefScalar | ValueScalar => th.toValue(mctx)
      case RefUnion(_) => th.toValue(mctx) + "*"
      case ValueUnion(_) | ValueStruct(_) => th.toValue(mctx)
    }

  def toRetVal(mctx: ModContext, th: TypeHint): String =
    th.toValue(mctx)

  def toCallArg(mctx: ModContext, th: TypeHint): String =
    th.classify(mctx) match {
      case NullableUnion(_) | RefStruct(_) | RefScalar | ValueScalar => th.toValue(mctx)
      case RefUnion(_) => th.toValue(mctx) + "*"
      case ValueUnion(_) | ValueStruct(_) => th.toValue(mctx) + "*"
    }

  def toClosure(mctx: ModContext,
                closure: Seq[(String, TypeHint, VarType)]): String = {
    val fields =
      closure.map { case (_, th, vt) =>
        vt match {
          case VarClosureLocal(_) => th.toValue(mctx) + "*"
          case VarClosureParam(_) =>
            th.classify(mctx) match {
              case RefUnion(_) | ValueUnion(_) | ValueStruct(_) => th.toValue(mctx) + "*"
              case _ => th.toValue(mctx)
            }
          case _ => throw new RuntimeException("unreachable")
        }
      }
    s"{ ${fields.mkString(", ")} }"
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
        dctx.write(s"$r = load ${th.toValue(mctx)}, ${th.toValue(mctx)}* ${res.value}")
        EResult(r, false, res.isAnon)
      }

    var x = 1

    val sameThRes =
      if (requireTh != hasTh)
        requireTh.classify(mctx) match {
          case NullableUnion(_) =>
            if (hasTh == Builtin.thNil) EResult("null", false, res.isAnon)
            else res
          case RefUnion(_) =>
            val slot = dctx.addSlot(requireTh)
            store(mctx, dctx, false, false, requireTh, hasTh, slot, loadResValue(hasTh, res).value)
            EResult(slot, true, res.isAnon)
          case ValueUnion(_) =>
            val slot = dctx.addSlot(requireTh)
            store(mctx, dctx, false, false, requireTh, hasTh, slot, loadResValue(hasTh, res).value)
            EResult(slot, true, res.isAnon)
          case RefStruct(_) if requireTh.isArray =>
            val r1, r2, r3 = "%" + dctx.nextReg("")
            val elTh = hasTh.asInstanceOf[ScalarTh].params(0)
            dctx.write(s"$r1 = bitcast ${hasTh.toValue(mctx)}* ${res.value} to ${elTh.toValue(mctx)}*")
            val len = hasTh.getArrayLen
            dctx.write(s"$r2 = insertvalue ${requireTh.toValue(mctx)} undef, i32 $len, 0")
            dctx.write(s"$r3 = insertvalue ${requireTh.toValue(mctx)} $r2, ${elTh.toValue(mctx)}* $r1, 1")
            EResult(r3, false, res.isAnon)
          case _ =>
            throw new RuntimeException("unreachable")
        }
      else res

    requireTh.classify(mctx) match {
      case NullableUnion(_) | RefStruct(_) | RefScalar | ValueScalar =>
        loadResValue(requireTh, sameThRes)
      case RefUnion(_) =>
        as match {
          case AsStoreSrc | AsCallArg =>
            if (sameThRes.isPtr) sameThRes
            else {
              val value = loadResValue(requireTh, sameThRes).value
              val slot = dctx.addSlot(requireTh)
              dctx.write(s"store ${requireTh.toValue(mctx)} $value, ${requireTh.toValue(mctx)}* $slot")
              EResult(slot, true, sameThRes.isAnon)
            }
          case _ => loadResValue(requireTh, sameThRes)
        }
      case ValueUnion(_) | ValueStruct(_) =>
        as match {
          case AsCallArg =>
            if (sameThRes.isPtr) sameThRes
            else {
              val value = loadResValue(requireTh, sameThRes).value
              val slot = dctx.addSlot(requireTh)
              dctx.write(s"store ${requireTh.toValue(mctx)} $value, ${requireTh.toValue(mctx)}* $slot")
              EResult(slot, true, sameThRes.isAnon)
            }
          case _ => loadResValue(requireTh, sameThRes)
        }
    }
  }

  def store(mctx: ModContext,
            dctx: DContext,
            needDec: Boolean,
            needInc: Boolean,
            destTh: TypeHint, srcTh: TypeHint,
            destPtr: String, src: String): Unit = {
    if (needInc)
      RC.doRC(mctx, dctx, Inc, srcTh, srcTh.classify(mctx) match { case RefUnion(_) => true case _ => false }, src) // ppc
    if (needDec)
      RC.doRC(mctx, dctx, Dec, destTh, true, destPtr)

    val srcIrType = srcTh.toValue(mctx)
    val destIrType = destTh.toValue(mctx)

    val realSrc =
      srcTh.classify(mctx) match {
        case RefUnion(_) =>
          val r = "%" + dctx.nextReg("")
          dctx.write(s"$r = load $srcIrType, $srcIrType* $src")
          r
        case _ => src
      }

    if (destTh == srcTh)
      dctx.write(s"store $srcIrType $realSrc, $destIrType* $destPtr")
    else {
      /* member to union */
      val variants = destTh.asUnion(mctx)

      destTh.isUnion(mctx) match {
        case NullableU(_) =>
          if (srcTh == Builtin.thNil)
            dctx.write(s"store $destIrType null, $destIrType* $destPtr")
          else
            dctx.write(s"store $srcIrType $realSrc, $destIrType* $destPtr")
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
            dctx.write(s"store $srcIrType $realSrc, $srcIrType* $r3")
          }
      }
    }
  }

  def getProperty(mctx: ModContext, dctx: DContext,
                  srcTh: TypeHint,
                  res: EResult, props: Seq[lId]): (TypeHint, EResult) = {

    val (destTh, fieldSeq) = props.foldLeft((srcTh, Seq[Int]())) { case ((th, seq), prop) =>
      srcTh.classify(mctx) match {
        case ValueStruct(fields) =>
          val got = fields.zipWithIndex.find { case (f, _) => f.name == props(0).value }.get
          (got._1.typeHint, seq :+ got._2)
        case RefStruct(fields) =>
          val got = fields.zipWithIndex.find { case (f, _) => f.name == props(0).value }.get
          (got._1.typeHint, seq :+ got._2)
      }
    }

    val sync = syncValue(mctx, dctx, res, AsCallArg, srcTh, srcTh)
    val r1 = "%" + dctx.nextReg("")
    val srcIrType =
      if (srcTh.isRefType(mctx)) srcTh.toValue(mctx, suffix = ".body")
      else srcTh.toValue(mctx)

    dctx.write(s"$r1 = getelementptr $srcIrType, $srcIrType* ${sync.value}, i64 0 ${fieldSeq.map(id => ", i32 " + id).mkString(", ")}")
    (destTh, EResult(r1, true, true))
  }
}