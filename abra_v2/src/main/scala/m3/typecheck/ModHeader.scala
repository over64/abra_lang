package m3.typecheck

import m3.codegen.IrUtil.Mod
import m3.parse.Ast0.{Def, FnTh, TypeDecl, llVm}

import scala.collection.mutable

/**
  * Created by over on 20.10.17.
  */
case class DefHeader(pkg: String, name: String, lowName: String, th: FnTh, isSelf: Boolean)

case class ModHeader(pkg: String,
                     imports: Seq[(String, ModHeader)],
                     typeImports: Map[String, String],
                     lowCode: Seq[llVm],
                     types: Map[String, TypeDecl],
                     inlineDefs: Map[String, Def],
                     inlineSelfDefs: Map[String, Seq[Def]],
                     headers: mutable.HashMap[String, DefHeader]) {
  def toContext(idSeq: Int, inferStack: mutable.Stack[String], lowMod: Mod, deep: Int) =
    TContext(idSeq, inferStack, lowMod, deep, pkg, imports, typeImports, lowCode, types, inlineDefs, inlineSelfDefs, headers)

  def getHeader(modName: String) =
    imports.find { case (mName, header) => mName == modName }.map(_._2)
}
