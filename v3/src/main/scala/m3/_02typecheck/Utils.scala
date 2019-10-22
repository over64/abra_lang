package m3._02typecheck

import m3.Ast0._
import m3._01parse.ParseMeta._
import m3.{Builtin, Level, ThUtil}

import scala.collection.mutable.ArrayBuffer

object Utils {
  implicit class RichDef(self: Def) {
    def params: ArrayBuffer[GenericTh] = {
      val dest = ArrayBuffer[GenericTh]()
      ThUtil.findGenerics(FnTh(self.lambda.args.map(_.typeHint), self.retTh), dest)
      dest
    }

    def isGeneric: Boolean = params.nonEmpty
  }

  def typeDecl(level: Level, th: ScalarTh): (Module, TypeDecl) = {
    if (Builtin.isDeclaredBuiltIn(th.name))
      (Builtin.builtInMod, Builtin.resolveBuiltinType(th))
    else {
      val mod = level.findMod(th.declaredIn).get
      val td = mod.types.getOrElse(th.name, {
        throw TCE.NoSuchType(th.location, Seq.empty, th.name)
      })

      (mod, td)
    }
  }
}
