package m3.typecheck

import m3.parse.Ast0._
import m3.parse.ParseMeta._

import scala.collection.mutable

object Utils {
  implicit class ThExtension(self: TypeHint) {
    //FIXME: move to TypeHintPass
    def assertCorrect(ctx: PassContext, params: Seq[GenericTh]): Unit =
      self match {
        case sth: ScalarTh =>
          val (_, decl) = typeDecl(sth)

          if (decl.params.length != sth.params.length)
            throw TCE.ParamsCountMismatch(sth.location)

          sth.params.foreach(p => p.assertCorrect(ctx, params))
        case sth: StructTh =>
          val fieldNames = sth.seq.map(_.name)
          if (fieldNames.length != fieldNames.toSet.size)
            throw TCE.FieldNameNotUnique(sth.location)
          sth.seq.foreach(f => f.typeHint.assertCorrect(ctx, params))
        case uth: UnionTh =>
          if (uth.seq.length != uth.seq.toSet.size)
            throw TCE.UnionMembersNotUnique(uth.location)
          uth.seq.foreach(th => th.assertCorrect(ctx, params))
        case fth: FnTh =>
          fth.args.foreach(th => th.assertCorrect(ctx, params))
          fth.ret.assertCorrect(ctx, params)
        case gth: GenericTh =>
          if (!params.contains(gth))
            throw TCE.NoSuchParameter(gth.location, gth)
        case AnyTh =>
      }

    def spec(specMap: mutable.HashMap[GenericTh, TypeHint],
             onNotFound: GenericTh => TypeHint = gth => AnyTh): TypeHint = {
      val res = self match {
        case oldTh@ScalarTh(params, name, pkg) =>
          specMap.get(GenericTh(name)) match {
            case Some(th) => th
            case None =>

              val newTh = ScalarTh(params.map(p => p.spec(specMap, onNotFound)), name, pkg)
              TCMeta.setSthModule(newTh, TCMeta.getSthModule(oldTh))
              newTh
          }
        case StructTh(fields) =>
          StructTh(fields.map { field =>
            FieldTh(field.name, field.typeHint.spec(specMap, onNotFound))
          })
        case UnionTh(variants) =>
          UnionTh(variants.map { th =>
            th.spec(specMap, onNotFound)
          })
        case FnTh(args, ret) =>
          FnTh(args.map(arg => arg.spec(specMap, onNotFound)), ret.spec(specMap, onNotFound))
        case gth: GenericTh =>
          // make deep spec???
          specMap.getOrElse(gth, onNotFound(gth))
        case AnyTh => AnyTh
      }

      self.getLocation.foreach(loc => res.setLocation(self.location))
      res
    }

    def containsAny: Boolean =
      self match {
        case ScalarTh(params, name, pkg) =>
          params.exists(p => p.containsAny)
        case StructTh(fields) =>
          fields.exists(f => f.typeHint.containsAny)
        case UnionTh(variants) =>
          variants.exists(v => v.containsAny)
        case FnTh(args, ret) =>
          args.exists(a => a.containsAny) || ret.containsAny
        case gth: GenericTh => false
        case AnyTh => true
      }

    def isUnion = self match {
      case UnionTh(seq) => Some(seq)
      case sth: ScalarTh =>
        typeDecl(sth) match {
          case (m, ud: UnionDecl) =>
            val sm = makeSpecMap(ud.params, sth.params)
            Some(ud.variants.map(v => v.spec(sm)))
          case _ => None
        }
      case _ => None
    }

    def isArray: Boolean = self match {
      case sth: ScalarTh => Builtin.isArrayThName(sth.name)
      case _ => false
    }

    def getArrayLen: Long =
      try {
        self.asInstanceOf[ScalarTh].name
          .replace("Array", "").toLong
      } catch {
        case ex: NumberFormatException =>
          var x = 1
          throw ex
      }

    def isBuiltin = self match {
      case sth: ScalarTh =>
    }

    def findGenerics(dest: mutable.ListBuffer[GenericTh]): Unit = self match {
      case ScalarTh(params, name, pkg) =>
        params.foreach(p => p.findGenerics(dest))
      case StructTh(fields) =>
        fields.foreach(f => f.typeHint.findGenerics(dest))
      case UnionTh(variants) =>
        variants.foreach(v => v.findGenerics(dest))
      case FnTh(args, ret) =>
        args.foreach(a => a.findGenerics(dest))
        ret.findGenerics(dest)
      case gth: GenericTh => if (!dest.contains(gth)) dest += gth
      case AnyTh =>
    }
  }

  implicit class RichDef(self: Def) {
    def params: mutable.ListBuffer[GenericTh] = {
      val dest = mutable.ListBuffer[GenericTh]()
      FnTh(self.lambda.args.map(_.typeHint), self.retTh)
        .findGenerics(dest)
      dest
    }

    def isGeneric: Boolean = params.nonEmpty
  }


  def typeDecl(th: ScalarTh): (Module, TypeDecl) = {
    val declaredIn = TCMeta.getSthModule(th)
    if (Builtin.isDeclaredBuiltIn(th.name))
      (declaredIn, Builtin.resolveBuiltinType(th))
    else {
      val td = declaredIn.types.getOrElse(th.name, {
        var x = 1
        throw TCE.NoSuchType(th.location, Seq.empty, th.name)
      })
      (declaredIn, td)
    }
  }

  def makeSpecMap(gen: Seq[GenericTh], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    mutable.HashMap((gen zip params): _*)
  }
}
