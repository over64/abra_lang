package lang_m2

object Ast1 {
  sealed trait Type {
    val name: String
  }
  case class Scalar(name: String) extends Type
  case class Field(name: String, _type: Type) {
    override def toString = s"${_type.name} %${name}"
  }
  case class Struct(_name: String, fields: Seq[Field]) extends Type {
    override val name: String = s"%struct.${_name}"
  }

  sealed trait FnType extends Type {
    val fnPointer: FnPointer
  }

  case class FnPointer(args: Seq[Field], ret: Type) extends FnType {
    override val name: String = {
      s"${realRet.name} (${
        realArgs.map { arg =>
          arg._type match {
            case struct: Struct => struct.name + "*"
            case other@_ => other.name
          }
        }.mkString(", ")
      })*"
    }

    def realRet = ret match {
      case s: Struct => Scalar("void")
      case _ => ret
    }

    def realArgs = ret match {
      case s: Struct => Seq(Field("ret", s)) ++ args
      case _ => args
    }

    val irArgs = realArgs.map(arg => arg._type match {
      case struct: Struct => s"${arg._type.name}* %${arg.name}"
      case (_: Scalar | _: FnPointer) => s"${arg._type.name} %${arg.name}"
    })
    override val fnPointer: FnPointer = this
  }

  case class ClosureVal(closurable: Closurable, varType: Type) {
    def name: String = closurable match {
      case lLocal(value) => value
      case lParam(value) => value
    }
  }

  case class Closure(typeName: String, fnPointer: FnPointer, vals: Seq[ClosureVal]) extends FnType {
    override val name: String = typeName
  }

  case class Disclosure(typeName: String, fnPointer: FnPointer) extends FnType {
    override val name: String = typeName
  }

  sealed trait Stat
  sealed trait Init

  sealed trait Literal extends Init

  case class lInt(value: String) extends Literal
  case class lFloat(value: String) extends Literal
  case class lString(value: String, enc: HexUtil.EncodeResult) extends Literal

  def escapeFnName(value: String) = value
    .replace("!=", "$noteq")
    .replace("!", "$not")
    .replace("+", "$plus")
    .replace("-", "$minus")
    .replace("/", "$div")
    .replace("*", "$mul")
    .replace("::", "$cons")
    .replace("<", "$less")
    .replace(">", "$more")
    .replace("==", "$eqeq")
    .replace("%", "$mod")
    .replace("||", "$or")
    .replace("&&", "$and")

  sealed trait lId extends Literal
  sealed trait Closurable extends lId {
    val value: String
  }
  case class lLocal(value: String) extends Closurable
  case class lParam(value: String) extends Closurable
  case class lGlobal(value: String) extends lId
  case class lClosure(value: String) extends Closurable

  case class ClosureToDisclosure(from: lLocal, disclosure: Disclosure) extends Init
  case class Access(from: lId, prop: String) extends Init
  case class Store(toVar: lId, fields: Seq[String], init: Init) extends Stat
  case class StoreEnclosure(toVar: lId, init: lGlobal) extends Stat
  case class Call(fn: lId, args: Seq[Init]) extends Init with Stat

  case class BoolAnd(left: Init, right: Init) extends Init with Stat
  case class BoolOr(left: Init, right: Init) extends Init with Stat
  case class Cond(init: Init, _if: Seq[Stat], _else: Seq[Stat]) extends Stat

  case class While(init: Init, seq: Seq[Stat]) extends Stat
  case class Ret(init: Init) extends Stat
  case class RetVoid() extends Stat

  sealed trait FnBody
  case class IrInline(ir: String) extends FnBody
  case class Block(vars: Map[String, Type] = Map(), stats: Seq[Stat]) extends FnBody

  case class Fn(name: String, _type: FnType, body: FnBody)
  case class HeaderFn(name: String, _type: FnPointer)
  case class Module(structs: Seq[Struct] = Seq(), functions: Seq[Fn], headers: Seq[HeaderFn] = Seq())
}
