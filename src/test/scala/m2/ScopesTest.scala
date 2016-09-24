package m2

import lang_m2.Ast0._
import lang_m2._
import org.scalatest._

import scala.collection.mutable

/**
  * Created by over on 22.09.16.
  */
case class Scope2(types: Map[String, TypeInfo],
                  functions: mutable.HashMap[String, FnContainer],
                  imports: Map[String, Scope2]) {

  def toLow(typeMap: Map[String, TypeInfo], th: TypeHint): Ast1.Type =
    th match {
      case ScalarTypeHint(typeName) =>
        typeMap.getOrElse(typeName, throw new CompileEx(th, CE.TypeNotFound(typeName)))._type match {
          case ScalarType(_, llType) => Ast1.Scalar(llType)
          case FactorType(name, fields) => Ast1.Struct(name, fields.map { field =>
            Ast1.Field(field.name, toLow(typeMap, field.typeHint))
          })
        }
      case FnTypeHint(seq, ret) =>
        Ast1.FnPointer(seq.map(arg => Ast1.Field(arg.name, toLow(typeMap, arg.typeHint))), toLow(typeMap, ret))
    }

//  def resolveType(th: TypeHint): Type =
//    global.types.getOrElse(th.name, throw new CompileEx(th, CE.TypeNotFound(th.name)))._type
//
//  def findSelfFn(name: String, selfType: TypeHint, inferCallback: RawFn => CallableFn): Option[CallableFn] =
//    global.types.get(selfType.name).flatMap { ti =>
//      ti.selfFunctions.get(name).map(_.fnInfo)
//    }.map {
//      case fn: RawFn => inferCallback(fn)
//      case fn: HeaderFn => fn
//      case fn: InferedFn => fn
//    }
//
//  def findFn(name: String, inferCallback: RawFn => CallableFn): Option[CallableFn] =
//    global.functions.get(name).map { fnContainer =>
//      fnContainer.fnInfo match {
//        case fn: RawFn => inferCallback(fn)
//        case fn: HeaderFn => fn
//        case fn: InferedFn => fn
//      }
//    }
//
//  def inferSelfFn(name: String, selfType: TypeHint, inferedFn: InferedFn) =
//    global.types(selfType.name).selfFunctions(name).fnInfo = inferedFn
//
//  def inferFn(name: String, inferedFn: InferedFn) =
//    global.functions(name).fnInfo = inferedFn
//
//  def inferAnonFn(name: String, inferedFn: InferedFn) =
//    global.anonFunctions += (name -> inferedFn)
}
class ScopesTest extends FunSuite {
  test("simple scopes test") {

  }
}
