package lang_m2

import lang_m2.Ast1._

import scala.collection.mutable

/**
  * Created by over on 28.10.16.
  */
object IrPasses {
//  def extractCallParams(functions: Seq[Fn]): Seq[Fn] = {
//    var anonVars = 0
//    def nextAnonVar = {
//      anonVars += 1
//      s"anon__$anonVars"
//    }
//
//    def mapCall(call: Call): (Seq[Stat], Call) = call match {
//      case Call(fn, args) =>
//        val start: Seq[(Seq[Stat], Init)] = Seq()
//        val permutated: Seq[(Seq[Stat], Init)] =
//          _type.args.zip(args).foldLeft(start) {
//            case (seq, (arg, argInit)) =>
//              (arg._type, argInit) match {
//                case (struct: Struct, call: Call) =>
//                  val newVar = Var(nextAnonVar, struct)
//                  val (before, newCall) = mapCall(call)
//                  val newStore = Store(lLocal(newVar.name), Seq(), struct, newCall)
//                  val newInit = lLocal(newVar.name)
//                  seq :+ (before :+ newVar :+ newStore, newInit)
//                //                case (fnPtr@FnPointer(args, ret, Some(fnClosure)), global: lGlobal) =>
//                //                  val newVar = Var(nextAnonVar, fnPtr)
//                //                  val newStore = Store(lLocal(newVar.name), Seq(), fnPtr, global)
//                //                  val newInit = lLocal(newVar.name)
//                //                  seq :+ (Seq(newVar, newStore), newInit)
//                case _ => seq :+ (Seq(), argInit)
//              }
//          }
//        val stats = permutated.flatMap {
//          case (seq, init) => seq
//        }
//        val newCallArgs = permutated.map {
//          case (seq, init) => init
//        }
//
//        (stats, Call(fn, _type, newCallArgs))
//    }
//    def mapBoolOr(boolOr: BoolOr): (Seq[Stat], BoolOr) = boolOr match {
//      case BoolOr(left, right) =>
//        val (leftStats, l) = mapInit(left)
//        val (rightStats, r) = mapInit(right)
//        (leftStats ++ rightStats, BoolOr(l, r))
//    }
//    def mapBoolAnd(boolAnd: BoolAnd): (Seq[Stat], BoolAnd) = boolAnd match {
//      case BoolAnd(left, right) =>
//        val (leftStats, l) = mapInit(left)
//        val (rightStats, r) = mapInit(right)
//        (leftStats ++ rightStats, BoolAnd(l, r))
//    }
//    def mapInit(init: Init): (Seq[Stat], Init) = init match {
//      case call: Call => mapCall(call)
//      case b: BoolOr => mapBoolOr(b)
//      case b: BoolAnd => mapBoolAnd(b)
//      case other@_ => (Seq(), other)
//    }
//    def mapBlock(seq: Seq[Stat]): Seq[Stat] =
//      seq.foldLeft(Seq[Stat]()) {
//        case (seq, call: Call) =>
//          val (stats, newCall) = mapCall(call)
//          seq ++ stats :+ newCall
//        case (seq, Store(to, fields, varType, init)) =>
//          val (stats, newInit) = mapInit(init)
//          (seq ++ stats :+ Store(to, fields, varType, newInit))
//        case (seq, boolAnd: BoolAnd) =>
//          val (stats, b) = mapBoolAnd(boolAnd)
//          (seq ++ stats :+ b)
//        case (seq, boolOr: BoolOr) =>
//          val (stats, b) = mapBoolOr(boolOr)
//          (seq ++ stats :+ b)
//        case (seq, Cond(init, _if, _else)) =>
//          val (stats, newInit) = mapInit(init)
//          (seq ++ stats :+ Cond(newInit, mapBlock(_if), mapBlock(_else)))
//        case (seq, While(init, wseq)) =>
//          val (stats, newInit) = mapInit(init)
//          (seq ++ stats :+ While(newInit, mapBlock(wseq)))
//        case (seq, Ret(_type, init)) =>
//          val (stats, newInit) = mapInit(init)
//          (seq ++ stats :+ Ret(_type, newInit))
//        case (seq, other) => seq :+ other
//      }
//
//    functions.map {
//      case Fn(name, _type, Block(vars, seq)) =>
//        Fn(name, _type, Block(vars, mapBlock(seq)))
//      case other: Fn => other
//    }
//  }

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
