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
                 val types: mutable.HashMap[String, Type] = mutable.HashMap(),
                 val defs: mutable.HashMap[String, Def] = mutable.HashMap()) {

    defineType(Low(ref = false, "Nil", "void"))
    defineType(Low(ref = false, "Bool", "i8"))
    defineType(Low(ref = false, "Int", "i32"))
    defineType(Low(ref = false, "Float", "float"))
    defineType(Low(ref = true, "String", "i8*"))

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

  implicit class RichType(self: Type) {
    def toDecl(types: mutable.HashMap[String, Type]): String = self match {
      case Low(ref, name, llValue) => throw new RuntimeException("oops")
      case Struct(name, fields) =>
        s"{ ${fields.map(f => f.ref.toValue(types)).mkString(", ")} }"
      case Union(name, variants) =>
        s"{ i8, ${variants.map(v => if (v.isVoid(types)) "{}" else v.toValue(types)).mkString(", ")} }"
      case Fn(name, closure, args, ret) =>
        val argsIr = args.map { argRef =>
          if (argRef.isRegisterFit(types)) argRef.toValue(types)
          else argRef.toPtr(types)
        }

        val realArgsIr = if (closure.isEmpty) argsIr else argsIr :+ ("%\"" + name + "\"*")
        val fnPtr = s"${ret.toValue(types)} (${realArgsIr.mkString(", ")})*"

        if (closure.isEmpty) fnPtr
        else {
          val cl = closure.map {
            case Local(ref) => ref.toPtr(types)
            case Param(ref) => if (ref.isRegisterFit(types)) ref.toValue(types) else ref.toPtr(types)
          }.mkString(", ")

          s"""{ $fnPtr, $cl }"""
        }
    }

    def isRef(types: mutable.HashMap[String, Type]): Boolean =
      TypeRef(self.name).isRef(types)

    def isUnion = self match {
      case u: Union => true
      case _ => false
    }
  }

  implicit class RichFnType(self: Fn) {
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
        case Low(ref, name, llValue) => true
        case s: Struct => false
        case u: Union => false
        case fn: Fn => if (fn.closure.isEmpty) true else false
      }
    }

    def hasSelfRef(types: mutable.HashMap[String, Type], stack: Seq[TypeRef]): Boolean = {
      if (self == stack.head) return true
      if (stack.contains(self)) return false

      types(self.name) match {
        case Low(ref, name, llValue) => false
        case Struct(name, fields) =>
          println(name)
          fields.exists(f => f.ref.hasSelfRef(types, stack :+ self))
        case Union(name, variants) =>
          variants.exists(v => v.hasSelfRef(types, stack :+ self))
        case Fn(name, closure, args, ret) => false
      }
    }


    def isRef(types: mutable.HashMap[String, Type]): Boolean =
      types(self.name) match {
        case Low(ref, name, llValue) => ref
        case Struct(name, fields) =>
          fields.exists(f => f.ref.hasSelfRef(types, Seq(self))) ||
            fields.exists(f => f.ref.isRef(types))
        case Union(name, variants) => false
        case Fn(name, closure, args, ret) => false
      }

    def isFn(types: mutable.HashMap[String, Type]): Boolean = types(self.name).isInstanceOf[Fn]
    def isUnion(types: mutable.HashMap[String, Type]): Boolean = types(self.name).isInstanceOf[Union]
    def isValue(types: mutable.HashMap[String, Type]): Boolean = !self.isRef(types)

    def isVoid(types: mutable.HashMap[String, Type]): Boolean =
      types(self.name) match {
        case Low(ref, name, llValue) => llValue == "void"
        case _ => false
      }

    def toPtr(types: mutable.HashMap[String, Type]): String = self.toValue(types) + "*"

    def toValue(types: mutable.HashMap[String, Type]): String = {
      val refSuffix = if (self.isRef(types)) "*" else ""
      types(self.name) match {
        case Low(ref, name, llValue) => llValue
        case Struct(name, fields) => "%\"" + name + "\"" + refSuffix
        case Union(name, variants) => "%\"" + name + "\"" + refSuffix
        case fn: Fn =>
          if (fn.closure.isEmpty) fn.toDecl(types)
          else "%\"" + fn.name + "\"" + refSuffix
      }
    }

    def isNeedBeforeAfterStore(types: mutable.HashMap[String, Type]) =
      self.isUnion(types) || self.isRef(types)
  }

  implicit class RichUnion(self: Union) {
    def fieldTagValue(typeRef: TypeRef): Int = self.variants.indexOf(typeRef)
    def fieldIrIndex(typeRef: TypeRef): Int = fieldTagValue(typeRef) + 1
  }

  def irDefName(name: String) = s"""@"$name""""
  def irLocalName(name: String) = s"%$name"
}
