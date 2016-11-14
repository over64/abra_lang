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
  sealed trait FnType extends Type {
    val fnPointer: FnPointer
  }
  case class FnPointer(args: Seq[TypeField], ret: Type) extends FnType {
    override val name: String = {
      val sArgs = args.map(arg => s"${arg.name}: ${arg.ftype.name}").mkString(", ")
      val sRet = ret.name
      s"($sArgs) -> $sRet"
    }
    override val fnPointer: FnPointer = this
  }

  case class Closure(name: String, fnPointer: FnPointer, vals: Seq[SymbolInfo]) extends FnType
  case class Disclosure(name: String, fnPointer: FnPointer) extends FnType

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

  def toLow(etype: Type): Ast1.Type =
    etype match {
      case ScalarType(_, _, llType) => Ast1.Scalar(llType)
      case FactorType(fullModName, name, fields) =>
        Ast1.Struct(fullModName + name, fields.map { field =>
          Ast1.Field(field.name, toLow(field.ftype))
        })
      case FnPointer(args, ret) =>
        Ast1.FnPointer(
          args.map { arg => Ast1.Field(arg.name, toLow(arg.ftype)) },
          toLow(ret)
        )
      case Closure(name: String, fnPointer: FnPointer, vals) =>
        Ast1.Closure(name, toLow(fnPointer).asInstanceOf[Ast1.FnPointer], vals.map { si =>
          val lowLocation = lowLiteral(si.location).asInstanceOf[Ast1.Closurable]
          Ast1.ClosureVal(lowLocation, toLow(si.stype))
        })
      case Disclosure(name, fnPointer) => Ast1.Disclosure(name, toLow(fnPointer).asInstanceOf[Ast1.FnPointer])
    }

  var disclosureId = 0
  def nextDisclosureId = {
    disclosureId += 1
    s"tdisclosure$disclosureId"
  }

  def typeToTypeHint(etype: Type): TypeHint = {
    etype match {
      case ScalarType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
      case FactorType(fullModName, name, _) => ScalarTypeHint(name, fullModName)
      case FnPointer(args, ret) =>
        FnTypeHint(args.map { arg =>
          FnTypeHintField(arg.name, typeToTypeHint(arg.ftype))
        }, typeToTypeHint(ret))
      case Closure(name, fnPointer, _) => typeToTypeHint(fnPointer)
      case Disclosure(name, fnPointer) => typeToTypeHint(fnPointer)
    }
  }

  implicit class TypeHintWrapper(th: TypeHint) {
    def toType(isParam: Boolean)(implicit namespace: Namespace): Type = {
      th match {
        case sth: ScalarTypeHint => namespace.types.getOrElse(sth, throw new CompileEx(sth, CE.TypeNotFound(sth)))
        case FnTypeHint(args, ret) =>
          if (isParam)
            Disclosure(nextDisclosureId, th.toType(isParam = false).asInstanceOf[FnPointer])
          else
            FnPointer(args.map { arg =>
              TypeField(false, arg.name, arg.typeHint.toType(isParam = true))
            }, ret.toType(isParam = false))
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

  def lowLiteral(location: SymbolLocation) =
    location match {
      case ClosureSymbol(literal) => Ast1.lClosure(literal)
      case LocalSymbol(literal) => Ast1.lLocal(literal)
      case ParamSymbol(literal) => Ast1.lParam(literal)
      case GlobalSymbol(literal) => Ast1.lGlobal(literal)
    }
}
