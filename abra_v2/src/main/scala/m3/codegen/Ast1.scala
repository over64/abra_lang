package m3.codegen

/**
  * Created by over on 12.05.17.
  */

object Ast1 {

  sealed trait Type {
    def apply(typeParams: Type*): Spec = Spec(this, typeParams)
  }
  case class Scalar(name: String) extends Type
  case class Field(name: String, _type: Type)
  case class Struct(specs: Seq[Virtual], name: String, fields: Seq[Field]) extends Type
  case class Union(specs: Seq[Virtual], name: String, variants: Seq[Type]) extends Type
  case class FnPtr(specs: Virtual*)(ret: Type, args: Type*) extends Type
  case class Virtual(name: String) extends Type

  case class Spec(_type: Type, typeParams: Seq[Type]) extends Type
  sealed trait Lit
  case class Const(v: String) extends Lit

  case class Id(v: String, props: Seq[String]) extends Lit {
    def ~>(p: Symbol) = Id(this.v, this.props :+ p.name)
  }
  sealed trait Stat
  case class Call(specs: Type*)(val i: Id, val args: Lit*) extends Stat
  case class Closure(specs: Virtual*)(val id: Id, val ret: Type, val args: Seq[Field], val code: Seq[Stat]) extends Stat
  case class Store(i1: Id, i2: Id) extends Stat
  case class Cond(i1: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class Or(i: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class And(i: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class While(i: Id, head: Seq[Stat], body: Seq[Stat]) extends Stat
  case object Ret extends Stat

  case class Module(structs: Seq[Struct], unions: Seq[Union], closures: Seq[Closure])
  object FnPtr {
    def apply(ret: Type, args: Type*): FnPtr = new FnPtr()(ret, args: _*)
  }
  object Struct {
    def apply(specs: Virtual*)(name: Symbol, fields: Field*): Struct = new Struct(specs, name.name, fields)
  }
  object Virtual {
    def apply(name: Symbol): Virtual = new Virtual(name.name)
  }

  implicit class RichSymbol(s: Symbol) {
    def is(_type: Type) = Field(s.name, _type)
    def virt = Virtual(s.name)
  }
  object Call {
    def apply(i: Id, args: Lit*): Call = Call()(i, args: _*)
  }

  object Closure {
    def apply(id: Id, ret: Type, args: Seq[Field], code: Seq[Stat]): Closure =
      Closure()(id, ret, args, code)
  }
  // implicits
  implicit def symbolToId(s: Symbol): Id = Id(s.name, Seq.empty)
  implicit def intToConst(i: Int): Const = Const(i.toString)
  implicit def boolToConst(b: Boolean): Const = Const(b.toString)
  implicit def stringToConst(s: String): Const = Const(s.toString)
  implicit def floatToConst(f: Float): Const = Const(f.toString)
}
