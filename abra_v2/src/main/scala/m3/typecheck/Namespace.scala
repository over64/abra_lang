package m3.typecheck

import m3.codegen.Ast2
import m3.parse.Ast0.{TypeHint, _}
import m3.typecheck.Util._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * Created by over on 20.10.17.
  */
case class DefSpec(params: Seq[TypeHint], th: FnTh, lowName: String)

case class DefCont(fn: Def, specs: mutable.ListBuffer[DefSpec])

case class InvokeKind(isCached: Boolean, isLow: Boolean)