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
  case class FnPointer(args: Seq[Type], ret: Type) extends Type {
    override val name: String = s"${ret.name} (${args.map { a => a.name }.mkString(" ,")})*"
  }

  sealed trait Stat
  sealed trait Init
  sealed trait CanStore

  sealed trait Literal extends Init {
    val value: String
  }
  case class lInt(value: String) extends Literal
  case class lFloat(value: String) extends Literal
  case class lString(value: String, enc: HexUtil.EncodeResult) extends Literal
  case class lId(value: String) extends Literal with CanStore {
    def irId = "%" + value
  }

  case class lParam(value: String) extends Literal with CanStore {
    def irId = "%" + value
  }

  case class Var(name: String, _type: Type) extends Stat {
    def irName = "%" + name
  }

  sealed trait FnPtr extends Init {
    val name: String
    val signature: Signature

    def escape(str: String) = str
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

    def irName: String
  }
  case class VarFnPtr(name: String, signature: Signature) extends FnPtr {
    override def irName = "%" + escape(name)
  }
  case class GlobalFnPtr(name: String, signature: Signature) extends FnPtr {
    override def irName = "@" + escape(name)
  }

  case class Access(from: Init, fromType: Type, prop: String) extends Init
  case class Store(toVar: CanStore, fields: Seq[String], varType: Type, init: Init) extends Stat
  case class Call(ptr: FnPtr, args: Seq[Init]) extends Init with Stat
  case class Cond(init: Init, _if: Seq[Stat], _else: Seq[Stat]) extends Stat
  case class While(init: Init, seq: Seq[Stat]) extends Stat
  case class Ret(_type: Type, init: Init) extends Stat
  case class RetVoid() extends Stat

  sealed trait FnBody
  case class IrInline(ir: String) extends FnBody
  case class Block(seq: Seq[Stat]) extends FnBody

  case class Signature(args: Seq[Field], ret: Type) {
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
  }

  case class Fn(ptr: FnPtr, body: FnBody)
  case class Module(structs: Seq[Struct], functions: Seq[Fn])
}
