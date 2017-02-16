package lang_m2

import lang_m2.Ast1.{Type, _}

import scala.collection.mutable

/**
  * Created by over on 28.10.16.
  */
object IrPasses {
  def fixedFnPointer(fnPtr: FnPointer): FnPointer = {
    if (fnPtr.ret.isInstanceOf[Struct]) FnPointer(Field("ret", fnPtr.ret) +: fnPtr.args, Scalar("void"))
    else fnPtr
  }

  def fnIdToFnType(fnMap: Map[String, FnType], currentFnType: FnType, currentFnVars: Map[String, Type], id: lId) = id match {
    case lGlobal(value) => fnMap(value)
    case lLocal(value) => currentFnVars(value).asInstanceOf[FnType]
    case lParam(value) => currentFnType.fnPointer.args.find(_.name == value).map(_._type).get.asInstanceOf[FnType]
  }

  def abiFix(functions: Seq[Fn], headers: Seq[Ast1.HeaderFn]): (Seq[Fn], Seq[Ast1.HeaderFn], Map[String, FnType]) = {
    var anonVars = 0
    def nextAnonVar = {
      anonVars += 1
      s"_abi_$anonVars"
    }
    def mapCall(fnMap: Map[String, FnType], currentFnType: FnType, currentFnVars: Map[String, Type], call: Call): (Map[String, Type], Seq[Stat], Call, Init) = {
      val fnType = fnIdToFnType(fnMap, currentFnType, currentFnVars, call.fn)

      val (additionalArgs, additionalVars): (Seq[Init], Map[String, Type]) =
        if (fnType.fnPointer.args.length > call.args.length) {
          val anon = nextAnonVar
          (Seq(lLocal(anon)), Map(anon -> fnType.fnPointer.args.head._type))
        } else (Seq(), Map())

      val (newVars, beforeStats, mappedArgs) =
        (additionalArgs ++ call.args).zip(fnType.fnPointer.args).foldLeft((additionalVars, Seq[Stat](), Seq[Init]())) {
          case ((vars, stats, newArgs), (argInit, argType)) =>
            (argInit, argType._type) match {
              case (call: Call, s: Struct) =>
                val (addVars, beforeStats, newCall, newInit) = mapCall(fnMap, currentFnType, currentFnVars, call)
                (vars ++ addVars, beforeStats ++ stats :+ newCall, newArgs :+ newInit)
              case _ => (vars, stats, newArgs :+ argInit)
            }
        }

      val newCall = Call(call.fn, mappedArgs)

      // real void call
      if (additionalArgs.length == 0)
        (newVars, beforeStats, newCall, newCall)
      else
        (newVars, beforeStats, newCall, mappedArgs.head)
    }

    def mapStats(fnMap: Map[String, FnType], currentFnType: FnType, currentFnVars: Map[String, Type], seq: Seq[Stat]): (Map[String, Type], Seq[Stat]) =
      seq.foldLeft(currentFnVars, Seq[Stat]()) {
        case ((vars, stats), store@Store(toVar, fields, Call(fnId, args))) =>
          val fnType = fnIdToFnType(fnMap, currentFnType, currentFnVars, fnId)
          if (fnType.fnPointer.ret == Scalar("void")) {
            // FIXME: Access
            val (addVars, beforeStats, newCall, _) = mapCall(fnMap, currentFnType, currentFnVars, Call(fnId, toVar +: args))
            (vars ++ addVars, stats ++ beforeStats :+ newCall)
          }
          else (vars, stats :+ store)
        case ((vars, stats), call: Call) =>
          val (addVars, beforeStats, newCall, _) = mapCall(fnMap, currentFnType, currentFnVars, call)
          (vars ++ addVars, stats ++ beforeStats :+ newCall)
        case ((vars, stats), Cond(init, _if, _else)) =>
          val (newIfVars, ifStats) = mapStats(fnMap, currentFnType, currentFnVars, _if)
          val (newElseVars, elseStats) = mapStats(fnMap, currentFnType, currentFnVars, _else)

          (vars ++ newIfVars ++ newElseVars,
            stats :+ Cond(init, ifStats, elseStats))
        case ((vars, stats), While(init, seq)) =>
          val (newVars, newStats) = mapStats(fnMap, currentFnType, currentFnVars, seq)

          (vars ++ newVars,
            stats :+ While(init, newStats))
        case ((vars, stats), other@_) => (vars, stats :+ other)
      }

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
        case Block(vars, stats) =>
          val (newVars, newStats) = mapStats(fnMap, fn._type, vars, stats)
          Block(newVars, newStats)
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
