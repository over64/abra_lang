package m3._03codegen

import m3.Ast0.Def

object CGMeta {
  def setIntermodInline(fn: Def): Unit =
    fn.meta.put("cg.intermod_inline", ())

  def isIntermodInline(fn: Def): Boolean =
    fn.meta.contains("cg.intermod_inline")
}
