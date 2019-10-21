package m3.typecheck

import m3.Ast0._
import m3.Builtin
import m3.parse.Level
import m3.parse.ParseMeta.ParseNodeMetaImplicit

object TypeHintPass {
  def pass(root: Level): Unit =
    root.eachModule((level, mod) => {
      def passTh(th: TypeHint): Unit = th match {
        case sth: ScalarTh =>
          val declaredIn =
            if (Builtin.isDeclaredBuiltIn(sth.name)) Builtin.builtInMod
            else sth.ie match {
              case None =>
                mod.imports.seq.find(imp => imp.withTypes.contains(sth.name))
                  .flatMap(imp => level.findMod(imp.path))
                  .getOrElse(mod)
              case Some(ie) =>
                val imp = mod.imports.seq.find(imp => imp.modName == ie).getOrElse {
                  throw TCE.NoSuchModulePath(sth.location)
                }

                level.findMod(imp.path).getOrElse {
                  throw TCE.NoSuchModulePath(imp.location)
                }
            }

          TCMeta.setSthModule(sth, declaredIn)
          sth.params.foreach(th => passTh(th))
        case StructTh(fields) => fields.seq.foreach(f => passTh(f.typeHint))
        case UnionTh(variants) => variants.foreach(th => passTh(th))
        case FnTh(args, ret) =>
          args.foreach(th => passTh(th))
          passTh(ret)
        case _: GenericTh =>
        case AnyTh =>
      }

      def passSeq(seq: Seq[Expression]): Unit =
        seq.foreach(e => passExpr(e))

      def passExpr(expr: Expression): Unit = expr match {
        case Ret(opt) =>
          opt.foreach(e => passExpr(e))
        case Call(expr, args) =>
          passExpr(expr)
          passSeq(args)
        case SelfCall(_, self, args) =>
          passExpr(self)
          passSeq(args)
        case Cons(sth, args) =>
          passTh(sth)
          passSeq(args)
        case Tuple(seq) => seq.foreach(e => passExpr(e))
        case Prop(from, props) =>
          passExpr(from)
          passExpr(props.head)
        case Store(varTh, to, what) =>
          passTh(varTh)
          passExpr(to.head)
          passExpr(what)
        case Lambda(args, body) =>
          args.foreach(arg => passTh(arg.typeHint))
          body match {
            case AbraCode(seq) => passSeq(seq)
            case _ =>
          }
        case andOr: AndOr =>
          passExpr(andOr.left)
          passExpr(andOr.right)
        case While(cond, doSeq) =>
          passExpr(cond)
          passSeq(doSeq)
        case Break() | Continue() =>
        case If(cond, doSeq, elseSeq) =>
          passExpr(cond)
          passSeq(doSeq)
          passSeq(elseSeq)
        case Unless(expr, isSeq) =>
          passExpr(expr)
          isSeq.foreach { case Is(_, typeRef, doSeq) =>
            passTh(typeRef)
            passSeq(doSeq)
          }
        case _ =>
      }

      def passDef(level: Level, mod: Module, fn: Def): Unit = {
        passTh(fn.retTh)
        fn.lambda.args.foreach(arg => passTh(arg.typeHint))
        fn.lambda.body match {
          case AbraCode(seq) => seq.foreach(e => passExpr(e))
          case _ =>
        }
      }

      def passTypeDecl(level: Level, mod: Module, td: TypeDecl): Unit = td match {
        case StructDecl(_, _, fields) => fields.foreach(f => passTh(f.th))
        case UnionDecl(_, _, variants) => variants.foreach(th => passTh(th))
        case _ =>
      }

      mod.defs.values.foreach(fn => passDef(level, mod, fn))
      mod.selfDefs.values.foreach(defs => defs.foreach(fn => passDef(level, mod, fn)))
      mod.types.values.foreach(td => passTypeDecl(level, mod, td))
    })

}