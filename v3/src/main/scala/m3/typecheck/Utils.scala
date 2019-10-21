package m3.typecheck

import m3.Ast0._
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

  def typeDecl(th: ScalarTh): (Module, TypeDecl) = {
    val declaredIn = TCMeta.getSthModule(th)
    if (Builtin.isDeclaredBuiltIn(th.name))
      (declaredIn, Builtin.resolveBuiltinType(th))
    else {
      val td = declaredIn.types.getOrElse(th.name, {
        var x = 1
        throw TCE.NoSuchType(th.location, Seq.empty, th.name)
      })
      (declaredIn, td)
    }
  }
}
