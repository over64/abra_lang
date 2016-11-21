package lang_m2

import lang_m2.Ast1._

import scala.collection.mutable

/**
  * Created by over on 28.10.16.
  */
object IrPasses {
  def fixedFnPointer(fnPtr: FnPointer): FnPointer = {
    if (fnPtr.ret.isInstanceOf[Struct]) FnPointer(Field("ret", fnPtr.ret) +: fnPtr.args, Scalar("void"))
    else fnPtr
  }

  def mapCall(fnMap: Map[String, FnType], call: Call): (Call, Map[String, Type]) = {
    val fn = fnMap(call.fn.value)
    val additionalArgs =
      if (fn.fnPointer.args.length > call.args.length) Seq(lLocal("_abi_"))
      else Seq()

  }

  def mapStats(fnMap: Map[String, FnType], seq: Seq[Stat]): Seq[Stat] = seq.map {
    case store@Store(toVar, fields, Call(fnId, args)) =>
      //FIXME: fix Access!
      if (fnMap(fnId.value).fnPointer.ret == Scalar("void"))
        Call(fnId, toVar +: args)
      else store
    case other@_ => other
  }

  def abiFix(functions: Seq[Fn], headers: Seq[Ast1.HeaderFn]): (Seq[Fn], Seq[Ast1.HeaderFn], Map[String, FnType]) = {
    val fixedHeaders = headers.map { header =>
      Ast1.HeaderFn(header.name, fixedFnPointer(header._type))
    }

    val fixedPrototypes = functions.map { fn =>
      val fixedFnType =
        fn._type match {
          case fnPtr: FnPointer => fixedFnPointer(fnPtr)
          case Closure(typeName, fnPtr, vals) => Closure(typeName, fixedFnPointer(fnPtr), vals)
          case Disclosure(fnPtr) => Disclosure(fixedFnPointer(fnPtr))
        }
      Fn(fn.name, fixedFnType, fn.body)
    }

    val fnMap =
      fixedHeaders.map { header =>
        (header.name, header._type)
      }.toMap[String, FnType] ++
        fixedPrototypes.map { fn =>
          (fn.name, fn._type)
        }.toMap

    val fixedBodies = fixedPrototypes.map { fn =>
      val fixedBody = fn.body match {
        case ir: IrInline => ir
        case Block(vars, stats) => Block(vars, mapStats(fnMap, stats))
      }
      Fn(fn.name, fn._type, fixedBody)
    }

    (fixedBodies, fixedHeaders, fnMap)
  }

  case class StringConst(name: String, value: HexUtil.EncodeResult)

  def inferStringConsts(functions: Seq[Fn]): (Seq[StringConst], Seq[Fn]) = {
    val consts = mutable.ListBuffer[StringConst]()

    def mapInit(init: Init): Init = init match {
      case ls@lString(name, value) =>
        consts += StringConst(name, value)
        ls
      case Call(name, args: Seq[Init]) =>
        Call(name, args.map(arg => mapInit(arg)))
      case some@_ => some
    }

    def mapStat(stat: Stat): Stat = stat match {
      case Store(toVar: lId, fields: Seq[String], init: Init) =>
        Store(toVar, fields, mapInit(init))
      case se: StoreEnclosure => se
      case Call(name, args: Seq[Init]) =>
        Call(name, args.map(arg => mapInit(arg)))
      case Cond(init: Init, _if: Seq[Stat], _else: Seq[Stat]) =>
        Cond(mapInit(init), _if.map(stat => mapStat(stat)), _else.map(stat => mapStat(stat)))
      case While(init: Init, seq: Seq[Stat]) =>
        While(mapInit(init), seq.map(stat => mapStat(stat)))
      case Ret(init: Init) =>
        Ret(mapInit(init))
      case rv: RetVoid => rv
      case boolAnd: BoolAnd => boolAnd
      case boolOr: BoolOr => boolOr
    }

    val mapped = functions.map { fn =>
      Fn(fn.name, fn._type, fn.body match {
        case ir: IrInline => ir
        case Block(vars, seq) => Block(vars, seq.map {
          stat => mapStat(stat)
        })
      })
    }
    (consts, mapped)
  }
}
