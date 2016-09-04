package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 29.07.16.
  */
sealed trait TypeCheckResult
case class TypeCheckSuccess(module: Ast1.Module) extends TypeCheckResult
case class TypeCheckFail(at: AstInfo, error: CompileError) extends TypeCheckResult

case class InferedFn(th: FnTypeHint, lowFn: Ast1.Fn)
case class InferedExp(th: TypeHint, stats: Seq[Ast1.Stat], init: Option[Ast1.Init])

class CompileEx(val node: ParseNode, val error: CompileError) extends Exception

object TypeCheckerUtil {

  def assertTypeDefined(typeHint: TypeHint, typeMap: Map[String, Type]): Unit =
    typeHint match {
      case self@ScalarTypeHint(name) =>
        if (typeMap.get(name) == None) throw new CompileEx(self, CE.TypeNotFound(name))
      case FnTypeHint(seq, ret) =>
        seq.foreach(th => assertTypeDefined(th.typeHint, typeMap))
        assertTypeDefined(ret, typeMap)
    }

  def assertTypeEquals(node: ParseNode, expected: TypeHint, has: TypeHint): Unit =
    if (expected != has) throw new CompileEx(node, CE.ExprTypeMismatch(expected, has))

  def inferFnArgs(fn: Fn) =
    fn.typeHint match {
      case Some(th) =>
        fn.body match {
          case Block(args, _) =>
            th.seq.zip(args).foreach {
              case (protoHint, blockArg) =>
                blockArg.typeHint.map { th =>
                  if (th.name != protoHint.typeHint.name)
                    throw new CompileEx(th, CE.ExprTypeMismatch(protoHint.typeHint, th))
                }
            }
            th.seq.zip(args).map {
              case (th, arg) => FnTypeHintField(arg.name, th.typeHint)
            }
          case _ => th.seq
        }
      case None =>
        fn.body match {
          case inline: LlInline => throw new CompileEx(fn, CE.NeedExplicitTypeDefinition())
          case Block(args, _) => // все аргументы должны быть с типами
            args.map { arg =>
              val th = arg.typeHint.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
              FnTypeHintField(arg.name, th)
            }
        }
    }

  def lowLiteral(location: SymbolLocation, literal: String) =
    location match {
      case LocalSymbol => Ast1.lLocal(literal)
      case ParamSymbol => Ast1.lParam(literal)
      case GlobalSymbol => Ast1.lGlobal(literal)
    }
}
