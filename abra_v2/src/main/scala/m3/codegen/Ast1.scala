package m3.codegen

/**
  * Created by over on 12.05.17.
  */

object Ast1 {

  sealed trait Type {
    def name: String
  }

  sealed trait Specializable[T] {
    def apply(typeParams: Type*): T = this.spec(typeParams)
    def spec(typeParams: Seq[Type]): T
    def specDeep(typeParams: Map[Type, Type]): T
  }

  case class Scalar(name: String) extends Type

  case class Field(name: String, _type: Type)

  object Struct {
    def apply(specs: Virtual*)(name: Symbol, fields: Field*): Struct = new Struct(specs, name.name, fields)
    def apply(name: Symbol, fields: Field*): Struct = new Struct(Seq(), name.name, fields)
  }
  case class Struct(specs: Seq[Type], _name: String, fields: Seq[Field]) extends Type with Specializable[Type] {
    override def name: String = s"${_name}[${specs.map(_.name).mkString(", ")}]"

    def specInternal(newSpecs: Seq[Type], specMap: Map[Type, Type]): Type = {
      val newFields = fields.map { f =>
        val newType = f._type match {
          case v: Virtual => specMap(v)
          case s: Specializable[Type] => s.specDeep(specMap)
          case other => other
        }
        Field(f.name, newType)
      }

      Struct(newSpecs, this._name, newFields)
    }
    override def spec(typeParams: Seq[Type]): Type = {
      val specMap = specs.zip(typeParams).toMap
      specInternal(typeParams, specMap)
    }
    override def specDeep(typeParams: Map[Type, Type]): Type = {
      val joined = specs.map(s => (s, typeParams(s)))
      specInternal(joined.map(_._2), joined.toMap)
    }
  }

  object Union {
    def apply(specs: Virtual*)(name: Symbol, variants: Type*): Union = new Union(specs, name.name, variants)
  }
  case class Union(specs: Seq[Type], _name: String, variants: Seq[Type]) extends Type with Specializable[Type] {
    override def name: String = s"${_name}[${specs.map(_.name).mkString(", ")}]"

    def specInternal(newSpecs: Seq[Type], specMap: Map[Type, Type]): Type = {
      val newVariants = variants.map { variant =>
        variant match {
          case v: Virtual => specMap(v)
          case s: Specializable[Type] => s.specDeep(specMap)
          case other => other
        }
      }

      Union(newSpecs, this._name, newVariants)
    }
    override def spec(typeParams: Seq[Type]): Type = {
      val specMap = specs.zip(typeParams).toMap
      specInternal(typeParams, specMap)
    }
    override def specDeep(typeParams: Map[Type, Type]): Type = {
      val joined = specs.map(s => (s, typeParams(s)))
      specInternal(joined.map(_._2), joined.toMap)
    }
  }

  case class FnPtr(ret: Type, args: Type*) extends Type with Specializable[Type] {
    override def name: String = s"(${args.map(_.name).mkString(", ")}) -> ${ret.name}"
    override def spec(typeParams: Seq[Type]): Type = ???
    override def specDeep(typeParams: Map[Type, Type]): Type = {
      val newArgs = (ret +: args).map {
        case v: Virtual => typeParams(v)
        case s: Specializable[Type] => s.specDeep(typeParams)
        case other => other
      }

      new FnPtr(newArgs.head, newArgs.drop(1): _*)
    }
  }

  object Virtual {
    def apply(name: Symbol): Virtual = new Virtual(name.name)
  }
  case class Virtual(name: String) extends Type

  sealed trait Lit {
    def irVal: String
  }
  case class Const(v: String) extends Lit {
    override def irVal: String = v
  }

  case class Id(v: String, props: Seq[String]) extends Lit {
    def ~>(p: Symbol) = Id(this.v, this.props :+ p.name)
    override def irVal: String = (v +: props).mkString(".")
  }
  sealed trait Stat
  case class Cons(_type: Type, ret: Id, args: Lit*) extends Stat
  case class Call(specs: Type*)(val i: Id, val args: Lit*) extends Stat with Specializable[Call] {
    override def spec(typeParams: Seq[Type]): Call = {
      //new Call(typeParams: _*)(this.i, this.args)
      null
    }
    override def specDeep(typeParams: Map[Type, Type]): Call = {
      val newSpecs = specs.map(s => typeParams(s))
      //new Call(newSpecs: _*)(this.i, this.args)
      null
    }
  }
  case class Closure(specs: Type*)(val id: Id, val ret: Type, val args: Seq[Field], val code: Seq[Stat]) extends Stat with Specializable[Closure] {
    def specInternal(newSpecs: Seq[Type], specMap: Map[Type, Type]): Closure = {
      val newRet = ret match {
        case v: Virtual => specMap(v)
        case s: Specializable[Type] => s.specDeep(specMap)
        case other => other
      }

      val newArgs = args.map { field =>
        val newType = field._type match {
          case v: Virtual => specMap(v)
          case s: Specializable[Type] => s.specDeep(specMap)
          case other => other
        }
        Field(field.name, newType)
      }

      val newCode = code.map { stat =>
        stat match {
          case c: Closure => c.specDeep(specMap)
          case call: Call => call.specDeep(specMap)
          case other => other
        }
      }

      new Closure(newSpecs: _*)(this.id, newRet, newArgs, newCode)
    }

    override def spec(typeParams: Seq[Type]): Closure = {
      val specMap = specs.zip(typeParams).toMap
      specInternal(typeParams, specMap)
    }
    override def specDeep(typeParams: Map[Type, Type]): Closure = {
      val joined = specs.map(s => (s, typeParams(s)))
      specInternal(joined.map(_._2), joined.toMap)
    }
  }
  case class Store(i1: Id, i2: Id) extends Stat
  case class Cond(i1: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class Or(i: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class And(i: Id, l: Seq[Stat], r: Seq[Stat]) extends Stat
  case class While(i: Id, head: Seq[Stat], body: Seq[Stat]) extends Stat
  case object Ret extends Stat

  case class Module(typeMap: Map[String, Type], closures: Map[String, Closure])
  case class TRef(pkg: Option[String], name: String)(specs: TRef*)

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
