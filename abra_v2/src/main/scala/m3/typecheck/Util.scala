package m3.typecheck

import m3.codegen.Ast2
import m3.parse.Ast0._
import m3.typecheck.Invoker.checkAndInfer

import scala.collection.mutable

/**
  * Created by over on 23.10.17.
  */
object Util {
  val thInt = ScalarTh(params = Seq.empty, "Int", Seq.empty)
  val thFloat = ScalarTh(params = Seq.empty, "Float", Seq.empty)
  val thBool = ScalarTh(params = Seq.empty, "Bool", Seq.empty)
  val thString = ScalarTh(params = Seq.empty, "String", Seq.empty)
  val thNil = ScalarTh(params = Seq.empty, "None", Seq.empty)
  val thUndef = ScalarTh(params = Seq.empty, "Undef", Seq.empty)

  def makeSpecMap(gen: Seq[GenericTh], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    mutable.HashMap((gen zip params): _*)
  }

  implicit class RichExpression(self: Expression) {
    def spec(specMap: mutable.HashMap[GenericTh, TypeHint], ctx: TContext): Expression = self match {
      case l: Literal => l
      case p: Prop => p
      case Tuple(seq) => Tuple(seq.map(e => e.spec(specMap, ctx)))
      case SelfCall(fnName, self, args) =>
        SelfCall(
          fnName,
          self.spec(specMap, ctx),
          args.map(arg => arg.spec(specMap, ctx)))
      case Cons(sth, args) =>
        Cons(
          sth.spec(specMap).asInstanceOf[ScalarTh],
          args.map(arg => arg.spec(specMap, ctx)))
      case Call(expr, args) =>
        Call(
          expr.spec(specMap, ctx),
          args.map(arg => arg.spec(specMap, ctx)))
      case Lambda(args, body) =>
        Lambda(
          args.map(arg => Arg(arg.name, arg.typeHint.spec(specMap))),
          body match {
            case l: llVm =>
              var code = l.code
              specMap.foreach { case (gth, th) =>
                val lowScalarRef = th.toLow(ctx) // FIXME: no cast?
              val replTypeName = ctx.lowMod.types(lowScalarRef.name) match {
                case Ast2.Low(ctx.pkg, ref, name, llValue) => name
                case _ => lowScalarRef.name
              }
                val replSelfDefName = ctx.lowMod.types(lowScalarRef.name) match {
                  case l: Ast2.Low => l.pkg + "." + replTypeName
                  case s: Ast2.Struct => s.pkg + "." + replTypeName
                  case u: Ast2.Union => u.pkg + "." + replTypeName
                  case _ => replTypeName
                }

                code = code.replaceAll("@" + gth.typeName + ".([a-z0-9A-Z]+)", "@\"" + replSelfDefName + ".$1\"")
                code = code.replaceAll("([^0-9a-zA-Z@])" + gth.typeName + "([^0-9a-zA-Z])", "$1" + replTypeName + "$2")
              }
              llVm(code)
            case AbraCode(seq) => AbraCode(seq.map(e => e.spec(specMap, ctx)))
          })
      case And(left, right) => And(left.spec(specMap, ctx), right.spec(specMap, ctx))
      case Or(left, right) => Or(left.spec(specMap, ctx), right.spec(specMap, ctx))
      case If(ifCond, _do, _else) =>
        If(
          ifCond.spec(specMap, ctx),
          _do.map(e => e.spec(specMap, ctx)),
          _else.map(e => e.spec(specMap, ctx)))
      case While(cond, _then) =>
        While(cond.spec(specMap, ctx), _then.map(e => e.spec(specMap, ctx)))
      case Unless(expr, isSeq) =>
        Unless(expr.spec(specMap, ctx),
          isSeq.map(is =>
            Is(is.vName, is.typeRef.spec(specMap), is._do.map(expr => expr.spec(specMap, ctx)))))
      case Store(th, to, what) =>
        Store(th.spec(specMap), to, what.spec(specMap, ctx))
      case Ret(what) => Ret(what.map(e => e.spec(specMap, ctx)))
    }
  }

  implicit class RichDef(self: Def) {
    def isSelf: Boolean =
      self.lambda.args.headOption.exists(arg => arg.name == "self")

    def params: Seq[GenericTh] = {
      def fillGenericSeq(seq: mutable.ListBuffer[GenericTh], th: TypeHint): Unit = {
        th match {
          case sth: ScalarTh => sth.params.foreach(th => fillGenericSeq(seq, th))
          case sth: StructTh => sth.seq.foreach(field => fillGenericSeq(seq, field.typeHint))
          case uth: UnionTh => uth.seq.foreach(th => fillGenericSeq(seq, th))
          case fth: FnTh =>
            fth.args.foreach(th => fillGenericSeq(seq, th))
            fillGenericSeq(seq, fth.ret)
          case gth: GenericTh => seq += gth
          case AnyTh =>
        }
      }

      val genericSeq = mutable.ListBuffer[GenericTh]()
      self.lambda.args.foreach(arg => fillGenericSeq(genericSeq, arg.typeHint))
      fillGenericSeq(genericSeq, self.retTh)
      genericSeq
    }

    def isGeneric: Boolean =
      self.params.nonEmpty

    def isNotGeneric: Boolean = !isGeneric

    def isLow: Boolean =
      self.lambda.body.isInstanceOf[llVm]

    def lowName(ctx: TContext) = {
      val fnPart =
        if (self.isSelf)
          self.lambda.args.head.typeHint.toLow(ctx).name + "." + self.name
        else self.name

      if (fnPart == "main") fnPart
      else ctx.pkg + "." + fnPart
    }

    def typeHint: Option[FnTh] = {
      if (self.lambda.args.forall(arg => arg.typeHint != AnyTh) && self.retTh != AnyTh)
        Some(FnTh(Seq.empty, self.lambda.args.map(arg => arg.typeHint), self.retTh))
      else None
    }

    def spec(specMap: mutable.HashMap[GenericTh, TypeHint], ctx: TContext): Def = {
      val skip = if (self.isSelf)
        self.lambda.args(0).typeHint match {
          case sth: ScalarTh => sth.params.length
          case _ => 0
        }
      else 0

      val neededParams = params.map(p => specMap(p)).drop(skip)
      val newName = self.name + (if (neededParams.nonEmpty) s"[${neededParams.drop(skip).map(p => p.toGenericName).mkString(", ")}]" else "")

      Def(
        name = newName,
        lambda = self.lambda.spec(specMap, ctx).asInstanceOf[Lambda],
        retTh = self.retTh.spec(specMap))
    }
  }

  implicit class RichLowDecl(self: ScalarDecl) {
    def toLow(params: Seq[TypeHint], ctx: TContext) = {
      val specMap = makeSpecMap(self.params, params)
      val lowName = ScalarTh(params, self.name, mod = Seq.empty).toGenericName
      var llType = self.llType

      specMap.foreach {
        case (gth, th) =>
          val lowScalarRef = th.toLow(ctx)
          val replName = ctx.lowMod.types(lowScalarRef.name) match {
            case Ast2.Low(ctx.pkg, ref, name, llValue) => llValue
            case _ => "%" + lowScalarRef.name
          }

          llType = llType.replace("%" + gth.typeName, replName)
      }

      ctx.lowMod.defineType(Ast2.Low(ctx.pkg, self.ref, lowName, llType))
      Ast2.TypeRef(lowName)
    }
  }

  implicit class RichStructDecl(self: StructDecl) {
    def toLow(params: Seq[TypeHint], ctx: TContext) = {
      val specMap = makeSpecMap(self.params, params)
      val lowName = ScalarTh(params, self.name, mod = Seq.empty).toGenericName

      ctx.lowMod.types.get(lowName) match {
        case Some(lowType) => Ast2.TypeRef(lowName)
        case None =>
          // avoid stack overflow
          ctx.lowMod.defineType(Ast2.Struct(ctx.pkg, lowName, Seq.empty))

          ctx.lowMod.defineType(Ast2.Struct(ctx.pkg, lowName, self.fields.map { field =>
            Ast2.Field(field.name, field.th.spec(specMap).toLow(ctx))
          }))

          Ast2.TypeRef(lowName)
      }
    }
  }

  implicit class RichUnionDecl(self: UnionDecl) {
    def toLow(params: Seq[TypeHint], ctx: TContext) = {
      val specMap = makeSpecMap(self.params, params)
      val lowName = ScalarTh(params, self.name, mod = Seq.empty).toGenericName
      ctx.lowMod.defineType(
        Ast2.Union(ctx.pkg, lowName, self.variants.map { th =>
          th.spec(specMap).toLow(ctx)
        }))
      Ast2.TypeRef(lowName)
    }
  }

  def isEqualSeq(ctx: TContext, specMap: mutable.HashMap[GenericTh, TypeHint], expected: Seq[TypeHint], has: Seq[TypeHint]): Boolean = {
    if (expected.length != has.length) return false
    (expected zip has).forall { case (e, h) => e.isEqual(ctx, specMap, h) }
  }

  implicit class RichTypeHint(self: TypeHint) {
    def isEqual(ctx: TContext, specMap: mutable.HashMap[GenericTh, TypeHint], other: TypeHint): Boolean =
      (self, other) match {
        case (th, AnyTh) => true
        case (adv: ScalarTh, th: ScalarTh) =>
          if (adv.name != th.name) false
          else isEqualSeq(ctx, specMap, adv.params, th.params)
        case (adv: UnionTh, th: UnionTh) =>
          isEqualSeq(ctx, specMap, adv.seq, th.seq)
        case (adv: StructTh, th: StructTh) =>
          isEqualSeq(ctx, specMap, adv.seq.map(_.typeHint), th.seq.map(_.typeHint))
        case (adv: FnTh, th: FnTh) =>
          isEqualSeq(ctx, specMap, adv.args, th.args) && checkAndInfer(ctx, specMap, adv.ret, th.ret)
        case (adv: GenericTh, th) =>
          specMap.get(adv) match {
            case None => specMap.put(adv, th); true
            case _ => false
          }
        case (adv: GenericTh, th: GenericTh) => adv.typeName == th.typeName
        case (adv, th: GenericTh) =>
          specMap.get(th) match {
            case None => specMap.put(th, adv); true
            case _ => false
          }
        case (AnyTh, th) => true
        case (adv, th) => false
      }

    def assertNotAny(errorCallback: => String) =
      self match {
        case AnyTh =>
          throw new RuntimeException(errorCallback)
        case th => th
      }

    // FIXME: keep original type
    def spec(specMap: mutable.HashMap[GenericTh, TypeHint]): TypeHint =
      self match {
        case ScalarTh(params, name, pkg) =>
          specMap.get(GenericTh(name)) match {
            case Some(th) => th
            case None => ScalarTh(params.map(p => p.spec(specMap)), name, pkg)
          }
        case StructTh(fields) =>
          StructTh(fields.map { field =>
            FieldTh(field.name, field.typeHint.spec(specMap))
          })
        case UnionTh(variants) =>
          UnionTh(variants.map { th =>
            th.spec(specMap)
          })
        case FnTh(closure, args, ret) =>
          FnTh(closure, args.map(arg => arg.spec(specMap)), ret.spec(specMap))
        case gth: GenericTh => specMap.getOrElse(gth, gth)
        case AnyTh => AnyTh
      }

    def moveToMod(modName: String): TypeHint =
      self match {
        case ScalarTh(params, name, mod) =>
          ScalarTh(params.map(p => p.moveToMod(modName)), name, modName +: mod)
        case StructTh(seq) =>
          StructTh(seq.map { case FieldTh(fname, th) => FieldTh(fname, th.moveToMod(modName)) })
        case UnionTh(seq) =>
          UnionTh(seq.map(th => th.moveToMod(modName)))
        case FnTh(closure, args, ret) =>
          FnTh(closure,
            args.map(th => th.moveToMod(modName)),
            ret.moveToMod(modName))
        case th => th
        //throw new RuntimeException(s"Unexpected $th move to mod")
      }

    def moveToModSeq(modNames: Seq[String]): TypeHint =
      self match {
        case ScalarTh(params, name, mod) =>
          ScalarTh(params.map(p => p.moveToModSeq(modNames)), name, modNames ++ mod)
        case StructTh(seq) =>
          StructTh(seq.map { case FieldTh(fname, th) => FieldTh(fname, th.moveToModSeq(modNames)) })
        case UnionTh(seq) =>
          UnionTh(seq.map(th => th.moveToModSeq(modNames)))
        case FnTh(closure, args, ret) =>
          FnTh(closure,
            args.map(th => th.moveToModSeq(modNames)),
            ret.moveToModSeq(modNames))
        case th => th
      }

    def swapMod(removeFrom: String, moveTo: String): TypeHint =
      self match {
        case sth@ScalarTh(params, name, mod) =>
          if (mod.headOption == Some(removeFrom))
            ScalarTh(params.map(p => p.swapMod(removeFrom, moveTo)), name, mod.drop(1))
          else
            ScalarTh(params.map(p => p.swapMod(removeFrom, moveTo)), name, moveTo +: mod)
        case StructTh(seq) =>
          StructTh(seq.map { case FieldTh(fname, th) => FieldTh(fname, th.swapMod(removeFrom, moveTo)) })
        case UnionTh(seq) =>
          UnionTh(seq.map(th => th.swapMod(removeFrom, moveTo)))
        case FnTh(closure, args, ret) =>
          FnTh(closure,
            args.map(th => th.swapMod(removeFrom, moveTo)),
            ret.swapMod(removeFrom, moveTo))
        case th => th
      }

    def toGenericName: String = {
      self match {
        case ScalarTh(params, name, mod) =>
          if (params.isEmpty) name
          else s"$name[${params.map(p => p.toGenericName).mkString(", ")}]"
        case StructTh(fields) =>
          s"(${fields.map(_.typeHint.toGenericName).mkString(", ")})"
        case UnionTh(variants) =>
          variants.map(_.toGenericName).mkString(" | ")
        case FnTh(closure, args, ret) =>
          s"\\${args.map(_.toGenericName).mkString(", ")} -> ${ret.toGenericName}"
      }
    }

    def toLow(ctx: TContext): Ast2.TypeRef = {
      self match {
        case ScalarTh(params, name, pkg) =>
          val (mod, decl) = ctx.findType(name, pkg)

          if (ctx.inferStack.headOption != Some("acquire_release_eval_no_stack_overflow_stub"))
            Seq("acquire", "release").foreach { ackOrRelease =>
              mod.inlineSelfDefs.get(ackOrRelease).flatMap(defs =>
                defs.find(d => Invoker.isSelfApplicable(ctx, d.lambda.args.head.typeHint, self))) match {
                case Some(d) =>
                  ctx.inferStack.push("acquire_release_eval_no_stack_overflow_stub")
                  Invoker.evalFromMod(ctx, mod, d, Seq(self).toIterator, AnyTh)
                  ctx.inferStack.pop()
                case None =>
              }
            }

          //FIXME: why no nextCtx for scalarDecl?
          val nextCtx = mod.toContext(ctx.pkg, ctx.idSeq, ctx.inferStack, ctx.lowMod, ctx.deep)
          decl match {
            case sd: ScalarDecl => sd.toLow(params, ctx)
            case struct: StructDecl => struct.toLow(params, nextCtx)
            case ud: UnionDecl => ud.toLow(params, nextCtx)
          }
        case StructTh(fields) =>
          val lowFields = fields.map(f => Ast2.Field(f.name, f.typeHint.toLow(ctx)))
          val lowName = s"(${lowFields.map(_.ref.name).mkString(", ")})"
          if (ctx.lowMod.types.get(lowName) == None)
            ctx.lowMod.defineType(Ast2.Struct(ctx.pkg, lowName, lowFields))
          Ast2.TypeRef(lowName)
        case UnionTh(variants) =>
          val lowVariants = variants.map(v => v.toLow(ctx))
          val lowName = s"${lowVariants.map(_.name).mkString(" | ")}"
          if (ctx.lowMod.types.get(lowName) == None)
            ctx.lowMod.defineType(Ast2.Union(ctx.pkg, lowName, lowVariants))
          Ast2.TypeRef(lowName)
        case FnTh(closure, args, ret) =>
          val lowClosure = closure.map {
            case CLocal(th) => Ast2.Local(th.toLow(ctx))
            case CParam(th) => Ast2.Param(th.toLow(ctx))
          }
          val lowArgs = args.map(arg => arg.toLow(ctx))
          val lowRet = ret.toLow(ctx)

          val closurePart =
            lowClosure.map {
              case Ast2.Local(ref) => ref.name + "@local"
              case Ast2.Param(ref) => ref.name + "@param"
            }.mkString(", ")
          val argsPart = lowArgs.map(_.name).mkString(", ")
          val retPart = lowRet.name

          val lowName = s"""<$closurePart>\\$argsPart -> $retPart"""
          if (ctx.lowMod.types.get(lowName) == None)
            ctx.lowMod.defineType(Ast2.Fn(lowName, lowClosure, lowArgs, lowRet))
          Ast2.TypeRef(lowName)
        case _ =>
          throw new RuntimeException(s"Internal compiler error: $self cannot be lowered.")
      }
    }
  }

  def upToClosure(seq: Seq[(String, VarInfo)]) = seq.map {
    case (vName, vi) =>
      vi.location match {
        case Local => (vName, CLocal(vi.th))
        case Param => (vName, CParam(vi.th))
      }
  }

  def downToLow(ctx: TContext, seq: Seq[(String, VarInfo)]): Map[String, Ast2.TypeRef] = seq.map {
    case (vName, vi) =>
      vi.location match {
        case Local => (vName, vi.lowTh)
        case Param => throw new Exception(("Internal compiler error"))
      }
  }.toMap
}
