package m3

import m3.Ast0._
import m3.parse.ParseMeta._
import m3.typecheck.Utils.typeDecl
import m3.typecheck._

import scala.collection.mutable

object ThUtil {
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
        val (advMod, _) = typeDecl(adv)
        val (thMod, _) = typeDecl(th)

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

  def isEqual(self: TypeHint, other: TypeHint): Boolean =
    try {
      checkEqual(self, other, (adv, _) => Specified(adv))
      true
    } catch {
      case _: MismatchLocal => false
    }

  def makeSpecMap(gen: Seq[GenericTh], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    mutable.HashMap((gen zip params): _*)
  }

  def spec(self: TypeHint, specMap: mutable.HashMap[GenericTh, TypeHint],
           onNotFound: GenericTh => TypeHint = gth => AnyTh): TypeHint = {
    val res = self match {
      case oldTh@ScalarTh(params, name, pkg) =>
        specMap.get(GenericTh(name)) match {
          case Some(th) => th
          case None =>

            val newTh = ScalarTh(params.map(p => spec(p, specMap, onNotFound)), name, pkg)
            TCMeta.setSthModule(newTh, TCMeta.getSthModule(oldTh))
            newTh
        }
      case StructTh(fields) =>
        StructTh(fields.map { field =>
          FieldTh(field.name, spec(field.typeHint, specMap, onNotFound))
        })
      case UnionTh(variants) =>
        UnionTh(variants.map { th =>
          spec(th, specMap, onNotFound)
        })
      case FnTh(args, ret) =>
        FnTh(args.map(arg => spec(arg, specMap, onNotFound)), spec(ret, specMap, onNotFound))
      case gth: GenericTh =>
        // make deep spec???
        specMap.getOrElse(gth, onNotFound(gth))
      case AnyTh => AnyTh
    }

    self.getLocation.foreach(loc => res.setLocation(self.location))
    res
  }

  def containsAny(self: TypeHint): Boolean =
    self match {
      case ScalarTh(params, name, pkg) =>
        params.exists(p => containsAny(p))
      case StructTh(fields) =>
        fields.exists(f => containsAny(f.typeHint))
      case UnionTh(variants) =>
        variants.exists(v => containsAny(v))
      case FnTh(args, ret) =>
        args.exists(a => containsAny(a)) || containsAny(ret)
      case gth: GenericTh => false
      case AnyTh => true
    }

  def isUnion(self: TypeHint) = self match {
    case UnionTh(seq) => Some(seq)
    case sth: ScalarTh =>
      typeDecl(sth) match {
        case (m, ud: UnionDecl) =>
          val sm = makeSpecMap(ud.params, sth.params)
          Some(ud.variants.map(v => spec(v, sm)))
        case _ => None
      }
    case _ => None
  }

  def isArray(self: TypeHint): Boolean = self match {
    case sth: ScalarTh => Builtin.isArrayThName(sth.name)
    case _ => false
  }

  // merge with isArray
  def getArrayLen(self: TypeHint): Long =
    try {
      self.asInstanceOf[ScalarTh].name
        .replace("Array", "").toLong
    } catch {
      case ex: NumberFormatException =>
        var x = 1
        throw ex
    }

  def findGenerics(self: TypeHint, dest: mutable.ListBuffer[GenericTh]): Unit = self match {
    case ScalarTh(params, name, pkg) =>
      params.foreach(p => findGenerics(p, dest))
    case StructTh(fields) =>
      fields.foreach(f => findGenerics(f.typeHint, dest))
    case UnionTh(variants) =>
      variants.foreach(v => findGenerics(v, dest))
    case FnTh(args, ret) =>
      args.foreach(a => findGenerics(a, dest))
      findGenerics(ret, dest)
    case gth: GenericTh => if (!dest.contains(gth)) dest += gth
    case AnyTh =>
  }
}