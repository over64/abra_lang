package m3.typecheck

import m3.Ast0._
import m3.parse.Level
import m3.{Builtin, ThUtil}
import m3.parse.ParseMeta._

import scala.collection.mutable

object Utils {
  implicit class RichDef(self: Def) {
    def params: mutable.ListBuffer[GenericTh] = {
      val dest = mutable.ListBuffer[GenericTh]()
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
