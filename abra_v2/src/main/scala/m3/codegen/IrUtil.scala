package m3.codegen

import java.io.PrintStream

import m3.codegen.Ast2._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by over on 23.10.17.
  */
object IrUtil {

  case class Mod(var lowCode: ListBuffer[String] = ListBuffer(),
                 types: mutable.HashMap[String, Type] = mutable.HashMap(),
                 defs: mutable.HashMap[String, Def] = mutable.HashMap(),
                 protos: mutable.HashMap[String, TypeRef] = mutable.HashMap()) {

    def addLow(lowCode: String) = {
      // FIXME: vary hacky
      this.lowCode += lowCode
      this.lowCode = this.lowCode.distinct
    }

    def defineType(t: Type) =
      types.put(t.name, t)

    def defineDef(d: Def) =
      defs.put(d.name, d)
  }

  case class IrContext(out: PrintStream,
                       types: mutable.HashMap[String, Type],
                       protos: mutable.HashMap[String, TypeRef],
                       defs: mutable.HashMap[String, Def])

  case class DefContext(val fn: Def, val vars: Map[String, TypeRef],
                        var _nextReg: Int = 0,
                        var _nextBranch: Int = 0) {
    def nextReg() = {
      _nextReg += 1
      _nextReg
    }

    def nextBranch() = {
      _nextBranch += 1
      _nextBranch
    }
  }

  case class WhileContext(headBr: String, endBr: String)

  implicit class RichType(self: Type) {
    def toDecl(types: mutable.HashMap[String, Type]): String = self match {
      case Low(pkg, ref, name, llValue) => llValue
      case Struct(pkg, name, fields) =>
        s"{ ${fields.map(f => f.ref.toValue(types)).mkString(", ")} }"
      case u: Union =>
        u.isNullableUnion(types) match {
          case Some(tref) => tref.toValue(types)
          case None =>
            s"{ i8, ${u.variants.map(v => if (v.isVoid(types)) "{}" else v.toValue(types)).mkString(", ")} }"
        }
      case Fn(name, closure, args, ret) =>
        val argsIr = args.map { argRef =>
          if (argRef.isRegisterFit(types)) argRef.toValue(types)
          else argRef.toPtr(types)
        }

        val realArgsIr = argsIr :+ ("i8*")
        val fnPtr = s"${ret.toValue(types)} (${realArgsIr.mkString(", ")})*"

        s"""{ $fnPtr, i8* }"""
    }

    def isRef(types: mutable.HashMap[String, Type]): Boolean =
      TypeRef(self.name).isRef(types)

    def isUnion = self match {
      case u: Union => true
      case _ => false
    }
  }

  implicit class RichFnType(self: Fn) {
    def toGlobal(types: mutable.HashMap[String, Type], forceClosure: Boolean): String = {
      val argsIr = self.args.map { argRef =>
        if (argRef.isRegisterFit(types)) argRef.toValue(types)
        else argRef.toPtr(types)
      }

      val realArgsIr = if (self.closure.nonEmpty || forceClosure) argsIr :+ "i8*" else argsIr
      s"${self.ret.toValue(types)} (${realArgsIr.mkString(", ")})*"
    }

    def closureDecl(types: mutable.HashMap[String, Type], ptr: Boolean): String = {
      val cl = self.closure.map {
        case Local(ref) => ref.toPtr(types)
        case Param(ref) => if (ref.isRegisterFit(types)) ref.toValue(types) else ref.toPtr(types)
      }.mkString(", ")

      s"{ $cl }" + (if (ptr) "*" else "")

    }

    def toDisclosure(types: mutable.HashMap[String, Type], ptr: Boolean): String = {
      if (self.closure.nonEmpty) throw new RuntimeException("unexpected closure here")

      val fnIrType = Fn("", Seq.empty, self.args :+ TypeRef("String"), self.ret).toDecl(types)
      s"{ ${fnIrType}, i8* }" + (if (ptr) "*" else "")
    }

    def toDisclosureFn(types: mutable.HashMap[String, Type]): String = {
      if (self.closure.nonEmpty) throw new RuntimeException("unexpected closure here")

      Fn("", Seq.empty, self.args :+ TypeRef("String"), self.ret).toDecl(types)
    }
  }

  implicit class RichTypeRef(self: TypeRef) {
    def isRegisterFit(types: mutable.HashMap[String, Type]): Boolean = {
      if (self.isRef(types)) return true

      types(self.name) match {
        case Low(pkg, ref, name, llValue) => true
        case s: Struct => false
        case u: Union => false
        case fn: Fn => true
      }
    }

    def hasSelfRef(types: mutable.HashMap[String, Type], stack: Seq[TypeRef]): Boolean = {
      if (self == stack.head) return true
      if (stack.contains(self)) return false

      types(self.name) match {
        case Low(pkg, ref, name, llValue) => false
        case Struct(pkg, name, fields) =>
          fields.exists(f => f.ref.hasSelfRef(types, stack :+ self))
        case Union(pkg, name, variants) =>
          variants.exists(v => v.hasSelfRef(types, stack :+ self))
        case Fn(name, closure, args, ret) => false
      }
    }


    def isRef(types: mutable.HashMap[String, Type]): Boolean =
      types(self.name) match {
        case Low(pkg, ref, name, llValue) => ref
        case Struct(pkg, name, fields) =>
          fields.exists(f => f.ref.hasSelfRef(types, Seq(self))) ||
            fields.exists(f => f.ref.isRef(types))
        case Union(pkg, name, variants) => false
        case Fn(name, closure, args, ret) => false
      }

    def isFn(types: mutable.HashMap[String, Type]): Boolean = types(self.name).isInstanceOf[Fn]

    def isUnion(types: mutable.HashMap[String, Type]): Boolean = types(self.name).isInstanceOf[Union]

    def isValue(types: mutable.HashMap[String, Type]): Boolean = !self.isRef(types)

    def isVoid(types: mutable.HashMap[String, Type]): Boolean =
      types(self.name) match {
        case Low(pkg, ref, name, llValue) => llValue == "void"
        case _ => false
      }

    def toPtr(types: mutable.HashMap[String, Type]): String = self.toValue(types) + "*"

    def toValue(types: mutable.HashMap[String, Type]): String = {
      types(self.name) match {
        case low@Low(pkg, ref, name, llValue) => if (llValue == "void") llValue else "%\"" + name + "\""
        case Struct(pkg, name, fields) =>
          val refSuffix = if (self.isRef(types)) "*" else ""
          "%\"" + name + "\"" + refSuffix
        case Union(pkg, name, variants) => "%\"" + name + "\""
        case fn: Fn => fn.toDecl(types)
      }
    }

    def isNeedBeforeAfterStore(types: mutable.HashMap[String, Type]) =
      self.isUnion(types) || self.isRef(types)

    def fullName(types: mutable.HashMap[String, Type]): String =
      types(self.name) match {
        case l: Low => l.pkg + "." + self.name
        case s: Struct => s.pkg + "." + self.name
        case u: Union => u.pkg + "." + self.name
        case _ => self.name
      }
  }

  implicit class RichUnion(self: Union) {
    def fieldTagValue(typeRef: TypeRef): Int = self.variants.indexOf(typeRef)

    def fieldIrIndex(typeRef: TypeRef): Int = fieldTagValue(typeRef) + 1

    def isNullableUnion(types: mutable.HashMap[String, Type]): Option[TypeRef] = {
      val tNone = TypeRef("None")
      if (self.variants.length == 2 && self.variants.contains(tNone)) {
        val tref = self.variants.find(t => t != tNone).get
        if (tref.isRef(types)) Some(tref)
        else None
      } else None
    }
  }

  def irDefName(name: String) = s"""@"$name""""

  def irLocalName(name: String) = s"%$name"
}
