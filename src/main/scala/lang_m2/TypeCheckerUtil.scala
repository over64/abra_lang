package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 29.07.16.
  */

object TypeCheckerUtil {
  val thUnit = ScalarTypeHint("Unit", "")
  val thBool = ScalarTypeHint("Boolean", "")
  val thInt = ScalarTypeHint("Int", "")
  val thFloat = ScalarTypeHint("Float", "")
  val thString = ScalarTypeHint("String", "")

  val predefTypes = Map(
    thUnit -> ScalarType("Unit", "void"),
    thBool -> ScalarType("Boolean", "i1"),
    thInt -> ScalarType("Int", "i32"),
    thFloat -> ScalarType("Float", "float"),
    thString -> ScalarType("String", "i8*")
  )

  def toLow(typeMap: Map[ScalarTypeHint, Type], th: TypeHint): Ast1.Type =
    th match {
      case sth: ScalarTypeHint =>
        typeMap.getOrElse(sth, throw new CompileEx(th, CE.TypeNotFound(sth.name))) match {
          case ScalarType(_, llType) => Ast1.Scalar(llType)
          case FactorType(name, fields) => Ast1.Struct(sth._package + name, fields.map { field =>
            Ast1.Field(field.name, toLow(typeMap, field.typeHint))
          })
        }
      case FnTypeHint(seq, ret) =>
        Ast1.FnPointer(seq.map(arg => Ast1.Field(arg.name, toLow(typeMap, arg.typeHint))), toLow(typeMap, ret))
    }

  def assertTypeEquals(node: ParseNode, expected: TypeHint, has: TypeHint): Unit =
    if (expected != has) throw new CompileEx(node, CE.ExprTypeMismatch(expected, has))

  //FIXME: simplify
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
