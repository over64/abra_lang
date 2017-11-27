package m3.codegen

/**
  * Created by over on 27.07.17.
  */
object Ast1new {
  sealed trait Type
  case class Virtual(name: String)
  case class Field(name: String, ref: TypeRef)

  case class LowType(ref: Boolean, name: String, llValue: String) extends Type
  case class Struct(ref: Boolean, specs: Seq[Virtual], name: String, fields: Seq[Field]) extends Type
  case class Union(specs: Seq[Virtual], name: String, variants: Seq[TypeRef]) extends Type

  sealed trait TypeRef
  case class ScalarRef(specs: Seq[TypeRef], pkg: Option[String], name: String) extends TypeRef // Scalar type reference
  case class UnionRef() extends TypeRef
  case class StructRef() extends TypeRef
  case class FnRef(args: Seq[TypeRef], ret: TypeRef) // Functional type reference
  case class ClosureRef() extends TypeRef
  case class DisclosureRef() extends TypeRef

  object ScalarRef {
    def lnspec(name: String) = ScalarRef(Seq(), None, name)
  }

  sealed trait Stat
  sealed trait Lit
  case class IConst(v: String) extends Lit
  case class SConst(v: String) extends Lit
  case class FConst(v: String) extends Lit
  case class BConst(v: String) extends Lit
  case class Id(v: String, props: Seq[String]) extends Lit

  case class Var(name: String, `type`: TypeRef) extends Stat
  case class Store(i1: Id, i2: Lit) extends Stat
  case class Cond(i1: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class Or(i: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class And(i: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class While(i: Id, head: Seq[Stat], body: Seq[Stat]) extends Stat
  case object Ret extends Stat

  case class Cons(ref: TypeRef, ret: Id, args: Seq[Lit]) extends Stat
  case class Call(specs: Seq[TypeRef], i: Id, args: Seq[Lit]) extends Stat
  case class SelfCall(specs: Seq[TypeRef], id: Id, self: Lit, args: Seq[Lit]) extends Stat

  sealed trait Code
  case class LLCode(value: String) extends Code
  case class AbraCode(stats: Seq[Stat]) extends Code
  case class Closure(name: String, self: Option[TypeRef], args: Seq[Field], ret: Option[TypeRef], code: Code) extends Stat
  case class Module()
}
