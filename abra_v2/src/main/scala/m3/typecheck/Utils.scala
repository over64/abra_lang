package m3.typecheck

import m3.parse.Ast0._
import m3.parse.Level
import m3.parse.ParseMeta._

import scala.collection.mutable

object Utils {
  implicit class ThExtension(self: TypeHint) {
    def assertCorrect(ctx: PassContext, params: Seq[GenericTh]): Unit =
      self match {
        case sth: ScalarTh =>
          val (_, _, decl) = resolveType(ctx.level, ctx.module, sth)

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
        case ScalarTh(params, name, pkg) =>
          specMap.get(GenericTh(name)) match {
            case Some(th) => th
            case None =>
              ScalarTh(params.map(p => p.spec(specMap, onNotFound)), name, pkg)
          }
        case StructTh(fields) =>
          StructTh(fields.map { field =>
            FieldTh(field.name, field.typeHint.spec(specMap, onNotFound))
          })
        case UnionTh(variants) =>
          UnionTh(variants.map { th =>
            th.spec(specMap, onNotFound)
          })
        case FnTh(closure, args, ret) =>
          FnTh(closure, args.map(arg => arg.spec(specMap, onNotFound)), ret.spec(specMap, onNotFound))
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
        case FnTh(closure, args, ret) =>
          args.exists(a => a.containsAny) || ret.containsAny
        case gth: GenericTh => false
        case AnyTh => true
      }

    def isArray: Boolean = self match {
      case sth: ScalarTh => Builtin.isArrayThName(sth.name)
      case _ => false
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
      case FnTh(closure, args, ret) =>
        args.foreach(a => a.findGenerics(dest))
        ret.findGenerics(dest)
      case gth: GenericTh => dest += gth
      case AnyTh =>
    }

    def moveToMod(ieSeq: Seq[ImportEntry]): TypeHint = {
      if (ieSeq.isEmpty) return self

      val moved = self match {
        case sth@ScalarTh(params, name, pkg) =>
          if (Builtin.isDeclaredBuiltIn(name) || ieSeq.head.withTypes.contains(name))
            sth
          else
            ScalarTh(params, name, ieSeq.map(_.modName) ++ pkg)

        case StructTh(fields) =>
          StructTh(fields.map(f => FieldTh(f.name, f.typeHint.moveToMod(ieSeq))))
        case UnionTh(variants) =>
          UnionTh(variants.map(v => v.moveToMod(ieSeq)))
        case FnTh(closure, args, ret) =>
          FnTh(Seq.empty, args.map(a => a.moveToMod(ieSeq)), ret.moveToMod(ieSeq))
        case gth: GenericTh => gth
        case AnyTh => AnyTh
      }

      self.getLocation.foreach { location => moved.setLocation(location) }
      moved
    }
  }

  implicit class RichDef(self: Def) {
    def params: mutable.ListBuffer[GenericTh] = {
      val dest = mutable.ListBuffer[GenericTh]()
      FnTh(Seq.empty, self.lambda.args.map(_.typeHint), self.retTh)
        .findGenerics(dest)
      dest
    }

    def isGeneric: Boolean = params.nonEmpty
  }


  def resolveType(level: Level, module: Module, th: ScalarTh): (Seq[ImportEntry], Module, TypeDecl) = {
    val (origSeq, origModule) = th.mod.foldLeft((Seq.empty[ImportEntry], module)) {
      case ((ieSeq, mod), modName) =>
        val ieOpt = mod.imports.seq.toArray.find(ie => ie.modName == modName)
        val ie = ieOpt match {
          case Some(x) => x
          case None =>
            throw TCE.NoSuchModulePath(th.location)
        }
        (ieSeq :+ ie, level.findMod(ie.path).get)
    }

    val withoutMod = ScalarTh(th.params, th.name, Seq.empty)
    th.getLocation.foreach(location => withoutMod.setLocation(location))

    origModule.imports.seq
      .flatMap(ie => ie.withTypes.map(tName => (ie, tName)))
      .find { case (ie, tName) => tName == th.name }
    match {
      case Some((ie, _)) =>
        val mod = level.findMod(ie.path).getOrElse(throw TCE.NoSuchModulePath(ie.location))
        val (ieSeq, declMod, td) = resolveType(level, mod, withoutMod)
        ((origSeq :+ ie) ++ ieSeq, declMod, td)
      case None =>
        (origSeq, origModule, origModule.types.getOrElse(th.name, Builtin.resolveBuiltinType(th)))
    }
  }

  def makeSpecMap(gen: Seq[GenericTh], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    mutable.HashMap((gen zip params): _*)
  }
}
