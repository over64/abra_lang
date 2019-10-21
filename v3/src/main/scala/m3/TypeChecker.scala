package m3

import m3.Ast0._
import m3.Builtin.{thArraySizes, _}
import m3.parse.Level
import m3.parse.ParseMeta._
import m3.typecheck.TCE
import m3.typecheck.TCMeta._
import m3.typecheck.Utils.{typeDecl, _}

sealed trait SpecResult
case object NewSpec extends SpecResult
case class Specified(th: TypeHint) extends SpecResult
class MismatchLocal extends Exception

class TypeChecker(level: Level, module: Module,
                  genericSpec: (GenericTh, TypeHint) => SpecResult = (adv, th) => Specified(adv),
                  withTransaction: (() => Unit) => Unit = { callback => callback() }) {

  def checkUnionMember(ud: UnionDecl, adv: ScalarTh, th: TypeHint): Unit = {

    if (ud.params.length != adv.params.length) throw TCE.ParamsCountMismatch(adv.location)

    val specMapInternal = ThUtil.makeSpecMap(ud.params, adv.params)
    ud.variants.exists { udVariant =>
      try {
        withTransaction(() => ThUtil.checkEqual(ThUtil.spec(udVariant, specMapInternal), th, genericSpec));
        true
      } catch {
        case ex: Exception => false
      }
    }
  }

  def doCheck(expected: TypeHint, has: TypeHint): Unit =
    (expected, has) match {
      case (th, AnyTh) =>
      case (AnyTh, th) =>
      case (arraySize, sth: ScalarTh) if arraySize == thArraySize =>
        doCheck(thArraySizes, sth)
      case (array1: ScalarTh, array2: ScalarTh) if ThUtil.isArray(array1) && ThUtil.isArray(array2) =>
        val (_, decl1) = typeDecl(array1)
        val (_, decl2) = typeDecl(array2)

        decl1.getBuiltinArrayLen match {
          case None => // ok
          case has =>
            if (has != decl2.getBuiltinArrayLen)
              throw new MismatchLocal
        }

        ThUtil.checkEqualSeq(array1.params, array2.params, genericSpec)
      case (adv: ScalarTh, sth: ScalarTh) =>
        val (advMod, advDecl) = typeDecl(adv)
        val (sthMod, sthDecl) = typeDecl(sth)
        if (advMod.pkg == sthMod.pkg && adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          ThUtil.checkEqualSeq(adv.params, sth.params, genericSpec)
        }
        else {
          typeDecl(adv) match {
            case (_, ud: UnionDecl) => checkUnionMember(ud, adv, sth)
            case _ => throw new MismatchLocal
          }
        }
      case (adv: ScalarTh, uth: UnionTh) => throw new MismatchLocal
      case (adv: ScalarTh, th) =>
        typeDecl(adv) match {
          case (_, ud: UnionDecl) => checkUnionMember(ud, adv, th)
          case _ => throw new MismatchLocal
        }
      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant =>
          try {
            withTransaction(() => ThUtil.checkEqual(advVariant, th, genericSpec))
            true
          } catch {
            case ex: MismatchLocal => false
          }
        }
      case (adv: UnionTh, th: UnionTh) =>
        if (adv.seq.length != th.seq.length) throw new MismatchLocal
        (adv.seq zip th.seq).forall {
          case (v1, v2) =>
            try {
              doCheck(v1, v2);
              true
            } catch {
              case ex: MismatchLocal => false
            }
        }
      case (adv, th) => ThUtil.checkEqual(expected, has, genericSpec)
    }

  def check(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
    try {
      withTransaction(() => doCheck(expected, has))
    } catch {
      case ex: MismatchLocal =>
        if (expected == thArraySize)
          throw TCE.ArraySizeExpected(location, thArraySizes, has)
        else
          throw TCE.TypeMismatch(location, expected, has)
    }
  }
}
