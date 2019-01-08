package m3.codegen

/**
  * Created by over on 28.09.17.
  */
object Ast2 {
  sealed trait Type {
    val name: String
  }
  case class Field(name: String, ref: TypeRef)
  case class Low(pkg: String, ref: Boolean, name: String, llValue: String) extends Type
  case class Struct(pkg: String, name: String, fields: Seq[Field]) extends Type
  case class Union(pkg: String, name: String, variants: Seq[TypeRef]) extends Type

  sealed trait ClosureType {
    val ref: TypeRef
  }
  case class Local(ref: TypeRef) extends ClosureType
  case class Param(ref: TypeRef) extends ClosureType
  case class Fn(name: String, closure: Seq[ClosureType], args: Seq[TypeRef], ret: TypeRef) extends Type

  case class TypeRef(name: String)

  sealed trait Stat
  sealed trait Storable

  case class Id(v: String, props: Seq[String] = Seq.empty) extends Storable
  case class Cons(ref: TypeRef, args: Seq[Id]) extends Stat with Storable
  case class Free(id: Id) extends Stat
  case class Call(id: Id, args: Seq[Id] = Seq.empty) extends Stat with Storable
  case class Ret(lit: Option[String]) extends Stat
  case class Break() extends Stat
  case class Continue() extends Stat
  case class Store(init: Boolean, dest: Id, src: Storable) extends Stat
  case class Or(id: Id, checkLeft: Seq[Stat], checkRight: Seq[Stat]) extends Stat
  case class And(id: Id, checkLeft: Seq[Stat], checkRight: Seq[Stat]) extends Stat
  case class If(id: Id, onTrue: Seq[Stat], onFalse: Seq[Stat]) extends Stat
  case class Is(v: String, ref: TypeRef, seq: Seq[Stat])
  case class When(id: Id, is: Seq[Is], _else: Seq[Stat]) extends Stat
  case class While(id: Id, head: Seq[Stat], body: Seq[Stat]) extends Stat

  sealed trait Code
  case class LLCode(value: String) extends Code
  case class AbraCode(vars: Map[String, TypeRef], stats: Seq[Stat]) extends Code
  case class Def(name: String, ref: TypeRef, closure: Seq[String], args: Seq[String], code: Code, isAnon: Boolean = false)
}
