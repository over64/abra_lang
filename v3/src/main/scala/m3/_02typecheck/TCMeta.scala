package m3._02typecheck

import m3.Ast0._

import scala.collection.immutable.ArraySeq
import scala.collection.mutable.Buffer
import scala.collection.mutable.HashMap

sealed trait CallType
case object CallFnPtr extends CallType
sealed trait CallDef extends CallType

sealed trait CallStatic extends CallDef {
  val fn: Def
}
case class SelfCallPolymorphic(fth: FnTh) extends CallDef

sealed trait ThisModule extends CallStatic
sealed trait OtherModule extends CallStatic {
  val module: Module
}

case class CallLocal(fn: Def) extends ThisModule
case class SelfCallLocal(fn: Def) extends ThisModule
case class CallImport(module: Module, fn: Def) extends OtherModule
case class SelfCallImport(module: Module, fn: Def) extends OtherModule

object TCMeta {
  implicit class PolymorphicTCMetaImplicit(self: ParseNode) {
    def valueOrEmpty() =
      self.meta.getOrElseUpdate("typecheck.resolved_self_defs",
        new HashMap[(TypeHint, String), Def]())
        .asInstanceOf[HashMap[(TypeHint, String), (Module, Def)]]


    def addResolvedSelfDef(selfTh: TypeHint, fnName: String, module: Module, fn: Def): Unit = {
      val cont = valueOrEmpty()
      cont.put((selfTh, fnName), (module, fn))
    }

    def getResolvedSelfDefs() =
      valueOrEmpty()
  }

  implicit class ParseNodeTCMetaImplicit(self: ParseNode) {
    def setTypeHint(th: TypeHint): Unit = self.meta.put("typecheck.typeHint", th)

    def getTypeHintOpt[T <: TypeHint]: Option[T] = self.meta.get("typecheck.typeHint").map(m => m.asInstanceOf[T])

    def getTypeHint[T <: TypeHint]: T = getTypeHintOpt.getOrElse {
      val x = 1
      throw new RuntimeException("no typeHint")
    }

    def setCallSpecMap(specMap: HashMap[GenericTh, TypeHint]): Unit =
      self.meta.put("typecheck.call_spec_map", specMap)

    def getCallSpecMap =
      self.meta("typecheck.call_spec_map").asInstanceOf[HashMap[GenericTh, TypeHint]]
  }

  implicit class TypeDeclTCMetaImplicit(self: TypeDecl) {
    def setBuiltinArray(len: Option[Long]): Unit = {
      self.meta.put("builtin.array", true)
      len.foreach { l => self.meta.put("builtin.array.len", l) }
    }

    def isBuiltinArray: Boolean =
      self.meta.contains("builtin.array")

    def getBuiltinArrayLen: Option[Long] =
      self.meta.get("builtin.array.len").map(_.asInstanceOf[Long])
  }

  implicit class StoreTCMetaImplitic(self: Store) {
    def getDeclTh[T <: TypeHint]: Option[T] =
      self.meta.get("typecheck.store.declTypeHint").map(m => m.asInstanceOf[T])

    def setDeclTh(th: TypeHint): Unit =
      self.meta.put("typecheck.store.declTypeHint", th)
  }

  implicit class VarTypeTCMetaImplicit(self: lId) {
    def setVarLocation(vt: VarType): Unit =
      self.meta.put("typecheck.var_type", vt)

    def getVarLocation: VarType =
      try {
        self.meta.get("typecheck.var_type").map(x => x.asInstanceOf[VarType]).get
      } catch {
        case ex: NoSuchElementException =>
          var x = 1
          throw ex;
      }
  }

  implicit class CallTypeTCMetaImplicit(self: ParseNode) {
    def setCallType(ct: CallType): Unit =
      self.meta.put("typecheck.call_type", ct)

    def getCallType: CallType =
      self.meta("typecheck.call_type").asInstanceOf[CallType]
  }

  implicit class ClosureTCMetaImplicit(self: Lambda) {
    def setClosure(closure: ArraySeq[(String, TypeHint, VarType)]): Unit =
      self.meta.put("typecheck.lambda_closure", closure)

    def getClosure =
      self.meta("typecheck.lambda_closure").asInstanceOf[Seq[(String, TypeHint, VarType)]]
  }

  implicit class DefTCMetaImplicit(self: Def) {
    def getEquationsOpt: Option[Equations] = self.meta.get("typecheck.equations").map(m => m.asInstanceOf[Equations])

    def getEquations: Equations = getEquationsOpt.getOrElse {
      val x = 1
      throw new RuntimeException("no equations")
    }

    def setEquations(eq: Equations): Unit = self.meta.put("typecheck.equations", eq)
  }

  implicit class IfBranchMetaImplicit(self: If) {
    def setBranchTh(typeHints: (TypeHint, TypeHint)): Unit =
      self.meta.put("typecheck.if.branchTh", typeHints)

    def getBranchTh: (TypeHint, TypeHint) =
      self.meta("typecheck.if.branchTh").asInstanceOf[(TypeHint, TypeHint)]
  }

  implicit class UnlessMetaImplicit(self: Unless) {
    def setUncovered(variants: Seq[TypeHint]): Unit =
      self.meta.put("typecheck.unless.uncovered", variants)

    def getUncovered: Seq[TypeHint] =
      self.meta("typecheck.unless.uncovered").asInstanceOf[Seq[TypeHint]]
  }

}