package m3

import m3.Ast0._
import m3._01parse.ParseMeta._
import m3._02typecheck.Utils.typeDecl

import scala.collection.immutable.ArraySeq
import scala.collection.mutable.{ArrayBuffer, Buffer, HashMap}

object ThUtil {
  def makeSpecMap(gen: ArraySeq[GenericTh], params: ArraySeq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    (gen zip params).to(HashMap)
  }

  def spec(self: TypeHint, specMap: HashMap[GenericTh, TypeHint],
           onNotFound: GenericTh => TypeHint = gth => AnyTh): TypeHint = {
    val res = self match {
      case oldTh@ScalarTh(params, name, pkg, declaredIn) =>
        specMap.get(GenericTh(name)) match {
          case Some(th) => th
          case None =>
            ScalarTh(params.map(p => spec(p, specMap, onNotFound)), name, pkg, declaredIn)
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
      case ScalarTh(params, _, _, _) =>
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

  def isUnion(level: Level, module: Module, self: TypeHint) = self match {
    case UnionTh(seq) => Some(seq)
    case sth: ScalarTh =>
      typeDecl(level, sth) match {
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

  def findGenerics(self: TypeHint, dest: ArrayBuffer[GenericTh]): Unit = self match {
    case ScalarTh(params, _, _, _) =>
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