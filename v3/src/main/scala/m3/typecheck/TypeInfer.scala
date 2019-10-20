package m3.typecheck

import m3.parse.Ast0.{GenericTh, Module, TypeHint}
import m3.parse.{AstInfo, Level}

import scala.collection.mutable

class TypeInfer(val level: Level, module: Module,
                val onSpec: (TypeInfer, GenericTh, TypeHint) => Unit = (self, gth, th) => {}) {

  var specMap = mutable.HashMap[GenericTh, TypeHint]()

  def withTransaction(lambda: () => Unit): Unit = {
    val backupSpecMap = mutable.HashMap[GenericTh, TypeHint](specMap.toSeq: _*)

    try {
      lambda()
    } catch {
      case ex: Exception =>
        specMap = backupSpecMap
        throw ex;
    }
  }

  def infer(location: Seq[AstInfo], expected: TypeHint, has: TypeHint): Unit = {
    new TypeChecker(
      level, module,
      genericSpec = (adv, th) => {
        specMap.get(adv) match {
          case Some(specified) => Specified(specified)
          case None =>
            specMap.put(adv, th); onSpec(this, adv, th); NewSpec
        }
      },
      withTransaction).check(location, expected, has)
  }
}
