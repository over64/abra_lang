package lang_m2

import lang_m2.Ast0._

import scala.collection.mutable.HashMap
import TypeCheckerUtil._

/**
  * Created by over on 27.09.16.
  */
object Namespacer {
  def mkNamespace(module: Module) = {
    val fullModName = module._package
    val types = module.types.map { td =>
      ScalarTypeHint(td.name, fullModName) -> td
    }.toMap

    val typeMap: Map[ScalarTypeHint, Type] = predefTypes ++ types
    val functions = HashMap[String, HashMap[String, FnContainer]]()
    val extensions = HashMap[ScalarTypeHint, HashMap[String, FnContainer]]()

    val definedFunctions = module.functions ++ module.types.filter(_.isInstanceOf[FactorType]).flatMap { case ft: FactorType =>
      Seq(Macro.genConstructor(fullModName, typeMap, ft), Macro.genEquals(fullModName, typeMap, ft), Macro.genNotEquals(fullModName, typeMap, ft))
    } :+ Macro.booleanNot()

    // FIXME: validate duplications
    definedFunctions.foreach { fn =>
      val firstArg = TypeCheckerUtil.inferFnArgs(fn).headOption

      firstArg match {
        case Some(FnTypeHintField(name, th)) if name == "self" =>
          th match {
            case sth: ScalarTypeHint =>
              val byPkg = extensions.getOrElse(sth, {
                val hm = HashMap[String, FnContainer]()
                extensions.put(sth, hm)
                hm
              })
              byPkg.put(fn.name, FnContainer(RawFn(fullModName, fn)))
            case fth: FnTypeHint => throw new CompileEx(fth, CE.FnlTypeNotEntensible())
          }
        case _ =>
          val byPkg = functions.getOrElse(fullModName, {
            val hm = HashMap[String, FnContainer]()
            functions.put(fullModName, hm)
            hm
          })
          byPkg.put(fn.name, FnContainer(RawFn(fullModName, fn)))
      }
    }
    new Namespace(fullModName, typeMap, extensions, functions)
  }

  def mixNamespaces(to: Namespace, imports: Seq[Namespace]): Namespace = {
    val typeMap = HashMap[ScalarTypeHint, Type]()
    val extensions = HashMap[ScalarTypeHint, HashMap[String, FnContainer]]()
    val functions = HashMap[String, HashMap[String, FnContainer]]()

    (imports :+ to).foreach { namespace =>
      namespace.types.foreach {
        case (sth, _type) => typeMap.put(sth, _type)
      }
      namespace.extensions.map {
        case (sth, fnMap) =>
          val byPkg = extensions.getOrElse(sth, {
            val hm = HashMap[String, FnContainer]()
            extensions.put(sth, hm)
            hm
          })
          fnMap.foreach {
            case (fnName, fnCont) => byPkg.put(fnName, fnCont)
          }
      }

      namespace.functions.map {
        case (pkg, fnMap) =>
          val byPkg = functions.getOrElse(pkg, {
            val hm = HashMap[String, FnContainer]()
            functions.put(pkg, hm)
            hm
          })
          fnMap.foreach {
            case (fnName, fnCont) => byPkg.put(fnName, fnCont)
          }
      }
    }

    new Namespace(to._package, typeMap.toMap, extensions, functions)
  }

  def dumpHeader(namespace: Namespace) = {
    val extensions = HashMap[ScalarTypeHint, HashMap[String, FnContainer]]()
    val functions = HashMap[String, HashMap[String, FnContainer]]()

    namespace.extensions.map {
      case (sth, fnMap) =>
        val byPkg = extensions.getOrElse(sth, {
          val hm = HashMap[String, FnContainer]()
          extensions.put(sth, hm)
          hm
        })
        fnMap.foreach {
          case (fnName, fnCont) => fnCont.fnInfo match {
            case InferedFn(_package, th, lowFn) => byPkg.put(fnName, FnContainer(HeaderFn(_package, th, Ast1.HeaderFn(lowFn.name, lowFn._type))))
            case _ =>
          }
        }
    }

    namespace.functions.get(namespace._package).map { fnMap =>
      val byPkg = functions.getOrElse(namespace._package, {
        val hm = HashMap[String, FnContainer]()
        functions.put(namespace._package, hm)
        hm
      })
      fnMap.foreach {
        case (fnName, fnCont) => fnCont.fnInfo match  {
          case InferedFn(_package, th, lowFn) => byPkg.put(fnName, FnContainer(HeaderFn(_package, th, Ast1.HeaderFn(lowFn.name, lowFn._type))))
          case _ =>
        }
      }
    }

    new Namespace(namespace._package, namespace.types, extensions, functions)
  }
}
