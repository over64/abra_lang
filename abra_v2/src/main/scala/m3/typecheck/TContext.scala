package m3.typecheck

import m3.codegen.IrUtil
import Util._
import m3.codegen.IrUtil.Mod
import m3.parse.Ast0._

import scala.collection.mutable

object TContext {
  def forTest(types: Seq[TypeDecl], defs: Map[String, Def]): TContext =
    TContext(0, mutable.Stack[String](), Mod(), 0, "test", Seq.empty, Map(), Seq(),
      types.map(t => (t.name, t)).toMap, defs, Map(), mutable.HashMap())
}

case class TContext(var idSeq: Int,
                    inferStack: mutable.Stack[String],
                    lowMod: IrUtil.Mod,
                    deep: Int,
                    pkg: String,
                    imports: Seq[(String, ModHeader)],
                    typeImports: Map[String, String],
                    lowCode: Seq[llVm],
                    types: Map[String, TypeDecl],
                    defs: Map[String, Def],
                    selfDefs: Map[String, Seq[Def]],
                    headers: mutable.HashMap[String, DefHeader]) {

  val nextAnonId = new (() => Int) {
    override def apply(): Int = {
      idSeq += 1
      idSeq
    }
  }

  def toHeader: ModHeader =
    ModHeader(pkg, imports, typeImports, lowCode, types,
      defs.filter { case (_, d) => d.isGeneric },
      selfDefs
        .map { case (name, defs) => (name, defs.filter(d => d.isGeneric)) }
        .filter { case (_, defs) => defs.nonEmpty },
      headers)

  def findImport(modName: String) =
    imports.find { case (mName, _) => mName == modName }.map(_._2)

  def findTypeOpt(name: String, mod: Seq[String]): Option[(ModHeader, TypeDecl)] =
    mod match {
      case Seq() =>
        typeImports.get(name) match {
          case None => types.get(name).map(t => (this.toHeader, t))
          case Some(modName) =>
            val imp = findImport(modName).get
            imp.types.get(name).map(t => (imp, t))
        }
      case modName :: tail =>
        findImport(modName) match {
          case None =>
            throw new RuntimeException(s"no such module $modName")
          case Some(header) => header.toContext(idSeq, inferStack, lowMod, deep).findTypeOpt(name, tail)
        }
    }

  def findType(name: String, mod: Seq[String]): (ModHeader, TypeDecl) = {
    def tthrow = () => {
      throw new RuntimeException(s"no such type ${mod.mkString("", ".", ".") + name}")
    }

    findTypeOpt(name, mod).getOrElse(tthrow())
  }
}