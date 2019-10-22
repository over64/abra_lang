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

  def checkEqualSeq(expected: Seq[TypeHint], has: Seq[TypeHint],
                    genericSpec: (GenericTh, TypeHint) => SpecResult): Unit = {
    if (expected.length != has.length) throw new MismatchLocal
    (expected zip has).foreach { case (e, h) => checkEqual(e, h, genericSpec) }
  }

  def checkEqual(self: TypeHint, other: TypeHint,
                 genericSpec: (GenericTh, TypeHint) => SpecResult): Unit =

    (self, other) match {
      case (_, AnyTh) => // ok
      case (AnyTh, _) => // ok
      case (adv: ScalarTh, th: ScalarTh) =>
        val (advMod, _) = typeDecl(level, adv)
        val (thMod, _) = typeDecl(level, th)

        if (advMod.pkg != thMod.pkg || adv.name != th.name) throw new MismatchLocal
        if (adv.params.length != th.params.length) throw TCE.ParamsCountMismatch(th.location)

        checkEqualSeq(adv.params, th.params, genericSpec)
      case (adv: UnionTh, th: UnionTh) =>
        checkEqualSeq(adv.seq, th.seq, genericSpec)
      case (adv: StructTh, th: StructTh) =>
        checkEqualSeq(adv.seq.map(_.typeHint), th.seq.map(_.typeHint), genericSpec)
      case (adv: FnTh, th: FnTh) =>
        checkEqualSeq(adv.args, th.args, genericSpec)
        checkEqual(adv.ret, th.ret, genericSpec)
      case (adv: GenericTh, th) =>
        genericSpec(adv, th) match {
          case NewSpec =>
          case Specified(specified: GenericTh) =>
            th match {
              case gth: GenericTh =>
                if (specified.typeName != gth.typeName) throw new MismatchLocal
              case _ =>
                throw new MismatchLocal
            }
          case Specified(specifiedTh) => checkEqual(specifiedTh, th, genericSpec)
        }
      case _ => throw new MismatchLocal
    }

  def checkUnionMember(ud: UnionDecl, adv: ScalarTh, th: TypeHint): Unit = {

    if (ud.params.length != adv.params.length) throw TCE.ParamsCountMismatch(adv.location)

    val specMapInternal = ThUtil.makeSpecMap(ud.params, adv.params)
    ud.variants.exists { udVariant =>
      try {
        withTransaction(() => checkEqual(ThUtil.spec(udVariant, specMapInternal), th, genericSpec));
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
        val (_, decl1) = typeDecl(level, array1)
        val (_, decl2) = typeDecl(level, array2)

        decl1.getBuiltinArrayLen match {
          case None => // ok
          case has =>
            if (has != decl2.getBuiltinArrayLen)
              throw new MismatchLocal
        }

        checkEqualSeq(array1.params, array2.params, genericSpec)
      case (adv: ScalarTh, sth: ScalarTh) =>
        val (advMod, advDecl) = typeDecl(level, adv)
        val (sthMod, sthDecl) = typeDecl(level, sth)
        if (advMod.pkg == sthMod.pkg && adv.name == sth.name) {
          if (adv.params.length != sth.params.length) throw TCE.ParamsCountMismatch(sth.location)
          checkEqualSeq(adv.params, sth.params, genericSpec)
        }
        else {
          typeDecl(level, adv) match {
            case (_, ud: UnionDecl) => checkUnionMember(ud, adv, sth)
            case _ => throw new MismatchLocal
          }
        }
      case (adv: ScalarTh, uth: UnionTh) => throw new MismatchLocal
      case (adv: ScalarTh, th) =>
        typeDecl(level, adv) match {
          case (_, ud: UnionDecl) => checkUnionMember(ud, adv, th)
          case _ => throw new MismatchLocal
        }
      case (adv: UnionTh, th: ScalarTh) =>
        adv.seq.exists { advVariant =>
          try {
            withTransaction(() => checkEqual(advVariant, th, genericSpec))
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
      case (adv, th) => checkEqual(expected, has, genericSpec)
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
