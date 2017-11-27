package m3.codegen

import com.sun.xml.internal.bind.v2.model.core.TypeRef

/**
  * Created by over on 28.09.17.
  */
object Ast2 {
  sealed trait Type {
    val name: String
  }
  case class Field(name: String, ref: TypeRef)
  case class Low(ref: Boolean, name: String, llValue: String) extends Type
  case class Struct(name: String, fields: Seq[Field]) extends Type
  case class Union(name: String, variants: Seq[TypeRef]) extends Type

  sealed trait TypeRef
  case class ScalarRef(name: String) extends TypeRef
  case class StructRef(fields: Seq[Field]) extends TypeRef
  case class UnionRef(variants: Seq[TypeRef]) extends TypeRef

  sealed trait ClosureType {
    val ref: TypeRef
  }
  case class Local(ref: TypeRef) extends ClosureType
  case class Param(ref: TypeRef) extends ClosureType
  case class FnRef(closure: Seq[ClosureType], args: Seq[TypeRef], ret: TypeRef) extends TypeRef

  //  sealed trait Lit extends Storable
  //  case class IConst(v: String) extends Lit
  //  case class SConst(v: String) extends Lit
  //  case class FConst(v: String) extends Lit
  //  case class BConst(v: String) extends Lit
  //  case class TConst(v: String) extends Lit
  case class Id(v: String, props: Seq[String] = Seq.empty) extends Storable

  sealed trait Stat
  sealed trait Storable
  case class Init(dest: Id, src: Storable) extends Stat
  case class Store(dest: Id, src: Storable) extends Stat
  case class Cond(id: Id, onTrue: Seq[Stat], onFalse: Seq[Stat]) extends Stat
  case class Or(id: Id, checkLeft: Seq[Stat], checkRight: Seq[Stat]) extends Stat
  case class And(id: Id, checkLeft: Seq[Stat], checkRight: Seq[Stat]) extends Stat
  case class While(id: Id, head: Seq[Stat], body: Seq[Stat]) extends Stat
  case class Call(id: Id, args: Seq[Id]) extends Stat with Storable
  case class Free(id: Id) extends Stat
  case class Ret(lit: Option[String]) extends Stat

  sealed trait Code
  case class LLCode(value: String) extends Code
  case class AbraCode(vars: Map[String, TypeRef], stats: Seq[Stat]) extends Code
  case class ClosureField(name: String, ctype: ClosureType)
  case class Def(name: String, closure: Seq[ClosureField], args: Seq[Field], ret: TypeRef, code: Code, isAnon: Boolean = false)
}
