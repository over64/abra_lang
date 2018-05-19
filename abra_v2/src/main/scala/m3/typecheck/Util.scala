package m3.typecheck

import m3.codegen.Ast2
import m3.parse.Ast0._

import scala.collection.mutable

/**
  * Created by over on 23.10.17.
  */
object Util {
  val thInt = ScalarTh(params = Seq.empty, "Int", None)
  val thFloat = ScalarTh(params = Seq.empty, "Float", None)
  val thBool = ScalarTh(params = Seq.empty, "Bool", None)
  val thString = ScalarTh(params = Seq.empty, "String", None)
  val thNil = ScalarTh(params = Seq.empty, "None", None)

  val adviceBool = ScalarAdvice(Seq.empty, "Bool", pkg = None)

  sealed trait ThAdvice

  case class ScalarAdvice(params: Seq[Option[ThAdvice]], name: String, pkg: Option[String]) extends ThAdvice

  case class FnAdvice(args: Seq[Option[ThAdvice]], ret: Option[ThAdvice]) extends ThAdvice

  case class StructAdvice(fields: Seq[(String, Option[ThAdvice])]) extends ThAdvice

  case class UnionAdvice(variants: Seq[Option[ThAdvice]]) extends ThAdvice

  implicit class RichAdvice(self: ThAdvice) {
    def toTh: TypeHint = self match {
      case ScalarAdvice(params, name, pkg) =>
        ScalarTh(params.map(p => p.get.toTh), name, pkg)
      case StructAdvice(fields) =>
        StructTh(fields.map {
          case (name, advice) => FieldTh(name, advice.get.toTh)
        })
      case UnionAdvice(variants) =>
        UnionTh(variants.map(v => v.get.toTh))
      case FnAdvice(args, ret) =>
        FnTh(Seq.empty, args.map(arg => arg.get.toTh), ret.get.toTh)
    }

    def toThOpt =
      try {
        Some(toTh)
      } catch {
        case ex: java.util.NoSuchElementException => None
      }
  }

  def makeSpecMap(gen: Seq[GenericType], params: Seq[TypeHint]) = {
    if (gen.length != params.length)
      throw new RuntimeException("params length mismatch")

    (gen zip params).toMap
  }

  implicit class RichExpression(self: Expression) {
    def spec(specMap: Map[GenericType, TypeHint], ctx: TContext, namespace: Namespace): Expression = self match {
      case l: Literal => l
      case p: Prop => p
      case Tuple(seq) => Tuple(seq.map(e => e.spec(specMap, ctx, namespace)))
      case SelfCall(params, fnName, self, args) =>
        SelfCall(
          params.map(p => p.spec(specMap)),
          fnName,
          self.spec(specMap, ctx, namespace),
          args.map(arg => arg.spec(specMap, ctx, namespace)))
      case Call(params, expr, args) =>
        Call(
          params.map(p => p.spec(specMap)),
          expr.spec(specMap, ctx, namespace),
          args.map(arg => arg.spec(specMap, ctx, namespace)))
      case Lambda(args, body) =>
        Lambda(
          args.map(arg => Arg(arg.name, arg.typeHint.map(th => th.spec(specMap)))),
          body match {
            case l: llVm =>
              var code = l.code
              specMap.foreach { case (sth, th) =>
                val lowScalarRef = th.toLow(ctx, namespace) // FIXME: no cast?
              val replName = ctx.lowMod.types(lowScalarRef.name) match {
                case Ast2.Low(ref, name, llValue) => name
                case _ => lowScalarRef.name
              }
                code = code.replace(sth.name, replName)
              }
              llVm(code)
            case AbraCode(seq) => AbraCode(seq.map(e => e.spec(specMap, ctx, namespace)))
          })
      case And(left, right) => And(left.spec(specMap, ctx, namespace), right.spec(specMap, ctx, namespace))
      case Or(left, right) => Or(left.spec(specMap, ctx, namespace), right.spec(specMap, ctx, namespace))
      case If(ifCond, _do, _else) =>
        If(
          ifCond.spec(specMap, ctx, namespace),
          _do.map(e => e.spec(specMap, ctx, namespace)),
          _else.map(e => e.spec(specMap, ctx, namespace)))
      case While(cond, _then) =>
        While(cond.spec(specMap, ctx, namespace), _then.map(e => e.spec(specMap, ctx, namespace)))
      case When(expr, isSeq, elseSeq) =>
        When(expr.spec(specMap, ctx, namespace),
          isSeq.map(is =>
            Is(is.vName, is.typeRef.spec(specMap), is._do.map(expr => expr.spec(specMap, ctx, namespace)))),
          elseSeq.map(expr => expr.spec(specMap, ctx, namespace)))
      case Store(th, to, what) =>
        Store(th.map(th => th.spec(specMap)), to, what.spec(specMap, ctx, namespace))
      case Ret(what) => Ret(what.map(e => e.spec(specMap, ctx, namespace)))
    }
  }

  implicit class RichDef(self: Def) {
    def isSelf: Boolean =
      self.lambda.args.headOption.map(arg => arg.name == "self").getOrElse(false)

    def isGeneric: Boolean =
      self.params.nonEmpty

    def isNotGeneric: Boolean = !isGeneric

    def isLow: Boolean =
      self.lambda.body.isInstanceOf[llVm]

    def lowName(ctx: TContext, namespace: Namespace) = {
      val fnPart =
        if (self.isSelf)
          self.lambda.args(0).typeHint.get.toLow(ctx, namespace).name + "." + self.name
        else self.name

      if (fnPart == "main") fnPart
      else namespace.pkg + "." + fnPart
    }

    def typeHint: Option[FnTh] = {
      if (self.lambda.args.forall(arg => arg.typeHint != None) && self.retTh != None)
        Some(FnTh(Seq.empty, self.lambda.args.map(arg => arg.typeHint.get), self.retTh.get))
      else None
    }

    def spec(params: Seq[TypeHint], ctx: TContext, namespace: Namespace): Def = {
      val specMap = makeSpecMap(self.params, params)
      val skip = if (self.isSelf)
        self.lambda.args(0).typeHint.get match {
          case sth: ScalarTh => sth.params.length
          case _ => 0
        }
      else 0

      val neededParams = params.drop(skip)
      val newName = self.name + (if (neededParams.nonEmpty) s"[${neededParams.drop(skip).map(p => p.toGenericName(namespace)).mkString(", ")}]" else "")

      Def(
        params = Seq.empty,
        name = newName,
        lambda = self.lambda.spec(specMap, ctx, namespace).asInstanceOf[Lambda],
        retTh = self.retTh.map(th => th.spec(specMap)))
    }
  }

  implicit class RichLowDecl(self: ScalarDecl) {
    def spec(params: Seq[TypeHint], ctx: TContext, namespace: Namespace) = {
      val specMap = makeSpecMap(self.params, params)
      val lowName = ScalarTh(params, self.name, mod = None).toGenericName(namespace)
      var llType = self.llType

      specMap.foreach {
        case (sth, th) =>
          val lowScalarRef = th.toLow(ctx, namespace)
          val replName = ctx.lowMod.types(lowScalarRef.name) match {
            case Ast2.Low(ref, name, llValue) => llValue
            case _ => "%" + lowScalarRef.name
          }

          llType = llType.replace("%" + sth.name, replName)
      }

      ctx.lowMod.defineType(Ast2.Low(self.ref, lowName, llType))
      Ast2.TypeRef(lowName)
    }
  }

  implicit class RichStructDecl(self: StructDecl) {
    def spec(params: Seq[TypeHint], ctx: TContext, namespace: Namespace) = {
      val specMap = makeSpecMap(self.params, params)
      val lowName = ScalarTh(params, self.name, mod = None).toGenericName(namespace)

      ctx.lowMod.types.get(lowName) match {
        case Some(lowType) => Ast2.TypeRef(lowName)
        case None =>
          // avoid stack overflow
          ctx.lowMod.defineType(Ast2.Struct(lowName, Seq.empty))

          ctx.lowMod.defineType(Ast2.Struct(lowName, self.fields.map { field =>
            Ast2.Field(field.name, field.th.spec(specMap).toLow(ctx, namespace))
          }))

          Ast2.TypeRef(lowName)
      }
    }
  }

  implicit class RichUnionDecl(self: UnionDecl) {
    def spec(params: Seq[TypeHint], ctx: TContext, namespace: Namespace) = {
      val specMap = makeSpecMap(self.params, params)
      val lowName = ScalarTh(params, self.name, mod = None).toGenericName(namespace)
      ctx.lowMod.defineType(
        Ast2.Union(lowName, self.variants.map { th =>
          th.spec(specMap).toLow(ctx, namespace)
        }))
      Ast2.TypeRef(lowName)
    }
  }

  implicit class RichTypeHint(self: TypeHint) {
    def toAdviceOpt(specMap: mutable.HashMap[GenericType, TypeHint]): Option[ThAdvice] =
      self match {
        case ScalarTh(params, name, pkg) =>
          specMap.get(GenericType(name)) match {
            case Some(th: ScalarTh) if th.name.contains("*") => None
            case Some(th) => th.toAdviceOpt(specMap) //???
            case None => Some(ScalarAdvice(params.map(p => p.toAdviceOpt(specMap)), name, pkg))
          }
        case StructTh(fields) =>
          Some(StructAdvice(fields.map { field =>
            (field.name, field.typeHint.toAdviceOpt(specMap))
          }))
        case UnionTh(variants) =>
          Some(UnionAdvice(variants.map { th =>
            th.toAdviceOpt(specMap)
          }))
        case FnTh(closure, args, ret) =>
          Some(FnAdvice(args.map(arg => arg.toAdviceOpt(specMap)), ret.toAdviceOpt(specMap)))
      }

    def spec(specMap: Map[GenericType, TypeHint]): TypeHint =
      self match {
        case ScalarTh(params, name, pkg) =>
          specMap.get(GenericType(name)) match {
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
      }

    def toGenericName(namespace: Namespace): String = {
      self match {
        case ScalarTh(params, name, pkg) =>
          if (params.isEmpty) name
          else s"$name[${params.map(p => p.toGenericName(namespace)).mkString(", ")}]"
        case StructTh(fields) =>
          s"(${fields.map(_.typeHint.toGenericName(namespace)).mkString(", ")})"
        case UnionTh(variants) =>
          variants.map(_.toGenericName(namespace)).mkString(" | ")
        case FnTh(closure, args, ret) =>
          s"\\${args.map(_.toGenericName(namespace)).mkString(", ")} -> ${ret.toGenericName(namespace)}"
      }
    }

    def toLow(ctx: TContext, namespace: Namespace): Ast2.TypeRef = {
      self match {
        case ScalarTh(params, name, pkg) =>
          namespace.findType(name, true) match {
            case sd: ScalarDecl => sd.spec(params, ctx, namespace)
            case struct: StructDecl => struct.spec(params, ctx, namespace)
            case ud: UnionDecl => ud.spec(params, ctx, namespace)
          }
        case StructTh(fields) =>
          val lowFields = fields.map(f => Ast2.Field(f.name, f.typeHint.toLow(ctx, namespace)))
          val lowName = s"(${lowFields.map(_.ref.name).mkString(", ")})"
          if (ctx.lowMod.types.get(lowName) == None)
            ctx.lowMod.defineType(Ast2.Struct(lowName, lowFields))
          Ast2.TypeRef(lowName)
        case UnionTh(variants) =>
          val lowVariants = variants.map(v => v.toLow(ctx, namespace))
          val lowName = s"${lowVariants.map(_.name).mkString(" | ")}"
          if (ctx.lowMod.types.get(lowName) == None)
            ctx.lowMod.defineType(Ast2.Union(lowName, lowVariants))
          Ast2.TypeRef(lowName)
        case FnTh(closure, args, ret) =>
          val lowClosure = closure.map {
            case CLocal(th) => Ast2.Local(th.toLow(ctx, namespace))
            case CParam(th) => Ast2.Param(th.toLow(ctx, namespace))
          }
          val lowArgs = args.map(arg => arg.toLow(ctx, namespace))
          val lowRet = ret.toLow(ctx, namespace)

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

  def downToLow(ctx: TContext, namespace: Namespace, seq: Seq[(String, VarInfo)]): Map[String, Ast2.TypeRef] = seq.map {
    case (vName, vi) =>
      vi.location match {
        case Local => (vName, vi.th.toLow(ctx, namespace))
        case Param => throw new Exception(("Internal compiler error"))
      }
  }.toMap
}
