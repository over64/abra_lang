package m3.typecheck

import m3.parse.Ast0._

//sealed trait CallType
//case object LocalModDef extends CallType
//case object ModDef extends CallType
//case object
object TCMeta {
  implicit class ParseNodeTCMetaImplicit(self: ParseNode) {
    def setTypeHint(th: TypeHint): Unit = self.meta.put("typecheck.typeHint", th)

    def getTypeHintOpt[T <: TypeHint]: Option[T] = self.meta.get("typecheck.typeHint").map(m => m.asInstanceOf[T])

    def getTypeHint[T <: TypeHint]: T = getTypeHintOpt.getOrElse {
      throw new RuntimeException("no typeHint")
    }
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
    def getDeclTh[T <: TypeHint]: T = self.meta.get("typecheck.store.declTypeHint").map(m => m.asInstanceOf[T]).get

    def setDeclTh(th: TypeHint): Unit = self.meta.put("typecheck.store.declTypeHint", th)
  }

  implicit class VarTypeTCMetaImplicit(self: lId) {
    def setVarLocation(vt: VarType): Unit =
      self.meta.put("typecheck.var_type", vt)

    def getVarLocation: VarType =
      self.meta.get("typecheck.var_type").asInstanceOf[VarType]
  }

  implicit class CallTypeTCMetaImplicit(self: lId) {
    def setCallType(vt: VarType): Unit =
      self.meta.put("typecheck.var_type", vt)

    def getCallType: VarType =
      self.meta.get("typecheck.var_type").asInstanceOf[VarType]
  }

  implicit class DefTCMetaImplicit(self: Def) {
    def getEquationsOpt: Option[Equations] = self.meta.get("typecheck.equations").map(m => m.asInstanceOf[Equations])

    def getEquations: Equations = getEquationsOpt.getOrElse {
      val x = 1
      throw new RuntimeException("no equations")
    }

    def setEquations(eq: Equations): Unit = self.meta.put("typecheck.equations", eq)
  }
}
