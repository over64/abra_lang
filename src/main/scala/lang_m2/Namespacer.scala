package lang_m2

import lang_m2.Ast0._

import scala.collection.mutable.HashMap
import TypeCheckerUtil._

/**
  * Created by over on 27.09.16.
  */
object Namespacer {
  def mixNamespaces(module: Module, imports: Seq[Namespace]): Namespace = {
    val fullModName = module._package

    val typeMap = HashMap[ScalarTypeHint, Type](predefTypes.toSeq: _*)

    imports.foreach { namespace =>
      namespace.types.foreach {
        case (sth, _type) => typeMap.put(sth, _type)
      }
    }

    module.typeDecls.foreach { td =>
      def typeHintToType(th: TypeHint): Type = th match {
        case sth: ScalarTypeHint => typeMap.getOrElse(sth, throw new CompileEx(sth, CE.TypeNotFound(sth)))
        case FnTypeHint(args, ret) => FnType(args.map { arg =>
          TypeField(false, arg.name, typeHintToType(arg.typeHint))
        }, typeHintToType(ret))
      }
      val newType = td match {
        case ScalarDecl(name, llType) => ScalarType(fullModName, name, llType)
        case FactorDecl(name, declFields) =>
          FactorType(fullModName, name, declFields.map { declField =>
            TypeField(declField.isSelf, declField.name, typeHintToType(declField.th))
          })
      }

      typeMap += ScalarTypeHint(td.name, fullModName) -> newType
    }


    val extensions = HashMap[ScalarTypeHint, HashMap[String, FnContainer]]()
    val functions = HashMap[String, HashMap[String, FnContainer]]()

    imports.foreach { namespace =>
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

    // macro generation
    val localFactorTypes = typeMap.values.filter {
      case ft: FactorType if ft.fullModName == fullModName => true
      case _ => false
    }
    val definedFunctions = module.functions ++ localFactorTypes.flatMap { case ft: FactorType =>
      val tm = typeMap.toMap
      Seq(
        Macro.genConstructor(fullModName, tm, ft),
        Macro.genEquals(fullModName, tm, ft),
        Macro.genNotEquals(fullModName, tm, ft))
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

    new Namespace(fullModName, typeMap.toMap, extensions, functions)
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
        case (fnName, fnCont) => fnCont.fnInfo match {
          case InferedFn(_package, th, lowFn) => byPkg.put(fnName, FnContainer(HeaderFn(_package, th, Ast1.HeaderFn(lowFn.name, lowFn._type))))
          case _ =>
        }
      }
    }

    new Namespace(namespace._package, namespace.types, extensions, functions)
  }
}
