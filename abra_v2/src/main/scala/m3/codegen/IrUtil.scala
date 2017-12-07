package m3.codegen

import java.io.PrintStream

import m3.codegen.Ast2._

import scala.collection.mutable

/**
  * Created by over on 23.10.17.
  */
object IrUtil {
  case class IrContext(out: PrintStream,
                       types: mutable.HashMap[String, Type],
                       defs: mutable.HashMap[String, Def])

  case class DefContext(val fn: Def, val vars: Map[String, TypeRef]) {
    val nextReg = new (() => Int) {
      var anonSeq = 0
      override def apply(): Int = {
        anonSeq += 1
        anonSeq
      }
    }

    val nextBranch = new (() => Int) {
      var anonSeq = 0
      override def apply(): Int = {
        anonSeq += 1
        anonSeq
      }
    }
  }

  implicit class RichType(self: Type) {
    def toDecl(types: mutable.HashMap[String, Type]): String = self match {
      case Low(ref, name, llValue) => throw new RuntimeException("oops")
      case Struct(name, fields) =>
        s"""%"$name" = type """ +
          s"{ ${fields.map(f => f.ref.toValue(types)).mkString(", ")} }"
      case Union(name, variants) =>
        s"""%"$name" = type """ +
          s"{ i8, ${variants.filter(v => !v.isVoid(types)).map(v => v.toValue(types)).mkString(", ")} }"
      case Fn(name, closure, args, ret) =>
        s"""%"$name" = type ${ret.toValue(types)} (${args.map(a => a.toValue(types)).mkString(", ")})*"""
    }
  }

  implicit class RichTypeRef(self: TypeRef) {
    def isRegisterFit(types: mutable.HashMap[String, Type]): Boolean = {
      if (self.isRef(types)) return true

      types(self.name) match {
        case Low(ref, name, llValue) => true
        case s: Struct => false
        case u: Union => false
        case fn: Fn => true
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

    def isUnion(types: mutable.HashMap[String, Type]): Boolean =
      types(self.name) match {
        case Union(name, variants) => true
        case _ => false
      }

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
        case Fn(name, closure, args, ret) => "%\"" + name + "\"" + refSuffix
      }
    }
  }

  def irDefName(name: String) = s"""@"$name""""
  def irLocalName(name: String) = s"%$name"
}
