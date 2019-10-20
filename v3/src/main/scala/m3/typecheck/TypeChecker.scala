package m3.typecheck

import m3.parse.Ast0._
import m3.parse.ParseMeta._
import m3.parse.{AstInfo, Level}
import m3.typecheck.Builtin.{thArraySizes, _}
import m3.typecheck.TCMeta._
import m3.typecheck.Utils.{makeSpecMap, typeDecl, _}

sealed trait SpecResult
case object NewSpec extends SpecResult
case class Specified(th: TypeHint) extends SpecResult
class MismatchLocal extends Exception

class TypeChecker(level: Level, module: Module,
                  genericSpec: (GenericTh, TypeHint) => SpecResult = (adv, th) => Specified(adv),
                  withTransaction: (() => Unit) => Unit = { callback => callback() }) {

  def isEqualSeq(expected: Seq[TypeHint], has: Seq[TypeHint]): Unit = {
    if (expected.length != has.length) throw new MismatchLocal
    (expected zip has).foreach { case (e, h) => isEqual(e, h) }
  }

  def isEqual(self: TypeHint, other: TypeHint): Unit =
    (self, other) match {
      case (th, AnyTh) => // ok
      case (AnyTh, th) => // ok
      case (adv: ScalarTh, th: ScalarTh) =>
        val (advMod, _) = typeDecl(adv)
        val (thMod, _) = typeDecl(th)

        if (advMod.pkg != thMod.pkg || adv.name != th.name) throw new MismatchLocal
        if (adv.params.length != th.params.length) throw TCE.ParamsCountMismatch(th.location)
        isEqualSeq(adv.params, th.params)
      case (adv: UnionTh, th: UnionTh) =>
        isEqualSeq(adv.seq, th.seq)
      case (adv: StructTh, th: StructTh) =>
        isEqualSeq(adv.seq.map(_.typeHint), th.seq.map(_.typeHint))
      case (adv: FnTh, th: FnTh) =>
        isEqualSeq(adv.args, th.args)
        isEqual(adv.ret, th.ret)
      case (adv: GenericTh, th) =>
        genericSpec(adv, th) match {
          case NewSpec =>
          case Specified(specified: GenericTh) =>
            if (th.isInstanceOf[GenericTh]) {
              val gth = th.asInstanceOf[GenericTh]
              if (specified.typeName != gth.typeName) throw new MismatchLocal
            } else throw new MismatchLocal
          case Specified(specifiedTh) =>
            isEqual(specifiedTh, th)
        }
      case (adv, th) => throw new MismatchLocal
    }

  def checkUnionMember(ud: UnionDecl, adv: ScalarTh, th: TypeHint): Unit = {

    if (ud.params.length != adv.params.length) throw TCE.ParamsCountMismatch(adv.location)

    val specMapInternal = makeSpecMap(ud.params, adv.params)
    ud.variants.exists { udVariant =>
      try {
        withTransaction(() => isEqual(udVariant.spec(specMapInternal), th));
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
      case (array1: ScalarTh, array2: ScalarTh) if array1.isArray && array2.isArray =>
        val (_, decl1) = typeDecl(array1)
        val (_, decl2) = typeDecl(array2)

        decl1.getBuiltinArrayLen match {
          case None => // ok
          case has =>
            if (has != decl2.getBuiltinArrayLen)
              throw new MismatchLocal
        }

        isEqualSeq(array1.params, array2.params)
      case (adv: ScalarTh, sth: ScalarTh) =>
        val (advMod, advDecl) = typeDecl(adv)
        val (sthMod, sthDecl) = typeDecl(sth)
        if (advMod.pkg == sthMod.pkg && adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          isEqualSeq(adv.params, sth.params)
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
            withTransaction(() => isEqual(advVariant, th));
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
      case (adv, th) => isEqual(expected, has)
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
