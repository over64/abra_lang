package lang_m2

import Ast0._

/**
  * Created by over on 20.09.16.
  */
sealed trait ImportResult
case class ImportSuccess(types: Seq[Type], functions: Seq[Fn])
case class ImportError(error: String)

trait Importer {
  def doImport(currentPackage: Seq[String], importFor: Import): ImportResult
}

sealed trait MixResult
case class MixSuccess(scope: GlobalScope) extends MixResult
case class MixError(at: AstInfo, error: CompileError) extends MixResult

object ModuleMixer {
  def mix(_package: Seq[String], module: Module, importCallback: (Seq[String], Seq[String], Import) => ImportResult) = {

  }
}
