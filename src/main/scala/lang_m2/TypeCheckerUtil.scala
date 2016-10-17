package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 29.07.16.
  */

object TypeCheckerUtil {
  sealed trait Type {
    val name: String
  }
  case class ScalarType(fullModName: String, name: String, llType: String) extends Type
  case class TypeField(isSelf: Boolean, name: String, ftype: Type) {
    override def hashCode(): Int = ftype.hashCode()

    override def equals(o: scala.Any): Boolean =
      if (!o.isInstanceOf[TypeField]) false
      else o.asInstanceOf[TypeField].ftype == ftype
  }
  case class FactorType(fullModName: String, name: String, fields: Seq[TypeField]) extends Type
  case class FnPointerType(args: Seq[TypeField], ret: Type) extends Type {
    override val name: String = s"(${args.map(_.name).mkString(",")}) -> ${ret.name}"
  }
  case class InferedExp(infType: Type, stats: Seq[Ast1.Stat], init: Option[Ast1.Init])

  val thUnit = ScalarTypeHint("Unit", "")
  val thBool = ScalarTypeHint("Boolean", "")
  val thInt = ScalarTypeHint("Int", "")
  val thFloat = ScalarTypeHint("Float", "")
  val thString = ScalarTypeHint("String", "")

  val tUnit = ScalarType("", "Unit", "void")
  val tBool = ScalarType("", "Boolean", "i1")
  val tInt = ScalarType("", "Int", "i32")
  val tFloat = ScalarType("", "Float", "float")
  val tString = ScalarType("", "String", "i8*")

  val predefTypes = Map(
    thUnit -> tUnit,
    thBool -> tBool,
    thInt -> tInt,
    thFloat -> tFloat,
    thString -> tString
  )

  def typeToTypeHint(etype: Type): TypeHint = etype match {
    case ScalarType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
    case FactorType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
    case FnPointerType(args, ret) => FnTypeHint(args.map { arg =>
      FnTypeHintField(arg.name, typeToTypeHint(arg.ftype))
    }, typeToTypeHint(ret))
  }

  def toLow(etype: Type): Ast1.Type =
    etype match {
      case ScalarType(_, _, llType) => Ast1.Scalar(llType)
      case FactorType(fullModName, name, fields) =>
        Ast1.Struct(fullModName + name, fields.map { field =>
          Ast1.Field(field.name, toLow(field.ftype))
        })
      case FnPointerType(args, ret) =>
        Ast1.FnPointer(args.map { arg =>
          Ast1.Field(arg.name, toLow(arg.ftype))
        }, toLow(ret))
    }


  implicit class TypeHintWrapper(th: TypeHint) {
    def toType(implicit namespace: Namespace): Type = {
      th match {
        case sth: ScalarTypeHint => namespace.types.getOrElse(sth, throw new CompileEx(sth, CE.TypeNotFound(sth)))
        case FnTypeHint(args, ret) => FnPointerType(args.map { arg =>
          TypeField(false, arg.name, arg.typeHint.toType)
        }, ret.toType)
      }
    }
  }

  def assertTypeEquals(node: ParseNode, expected: Type, has: Type): Unit =
    if (expected != has) throw new CompileEx(node, CE.ExprTypeMismatch(expected.name, has.name))

  //FIXME: simplify
  // Fn -> Seq[Type]
  // Fn -> Seq[FnTypeHintField]
  def inferFnArgs(fn: Fn): Seq[FnTypeHintField] = {
    (fn.typeHint, fn.body) match {
      case (Some(FnTypeHint(args, ret)), Block(blockArgs, _)) =>
        // FIXME: check args.length vs blockArgs.length
        args.zip(blockArgs).map {
          case (arg, blockArg) =>
            blockArg.typeHint.map { blockTh =>
              if (arg.typeHint != blockTh) throw new CompileEx(blockArg, CE.ExprTypeMismatch(arg.typeHint.name, blockTh.name))
            }
            FnTypeHintField(blockArg.name, arg.typeHint)
        }
      case (Some(FnTypeHint(args, ret)), LlInline(_)) => args
      case (None, Block(blockArgs, _)) =>
        blockArgs.map { arg =>
          val th = arg.typeHint.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
          FnTypeHintField(arg.name, th)
        }
      case (None, LlInline(_)) => throw new CompileEx(fn, CE.NeedExplicitTypeDefinition())
    }
  }

  def lowLiteral(location: SymbolLocation, literal: String) =
    location match {
      case LocalSymbol => Ast1.lLocal(literal)
      case ParamSymbol => Ast1.lParam(literal)
      case GlobalSymbol => Ast1.lGlobal(literal)
    }
}
