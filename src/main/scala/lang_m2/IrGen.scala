package lang_m2

import java.io.{OutputStream, PrintStream}

import Ast1._
import lang_m2.Ast0.LlInline

import scala.collection.mutable

case class IrGen(val out: OutputStream) {
  implicit class Printer(out: OutputStream) {
    def println(line: String) = {
      out.write(line.getBytes)
      out.write(10) // \n
    }
  }

  var tmpVars = 0
  var labelId = 0

  def nextTmpVar(needPercent: Boolean = true): String = {
    tmpVars += 1
    if (needPercent)
      "%" + tmpVars
    else tmpVars.toString
  }

  def nextLabel: String = {
    labelId += 1
    s"label$labelId"
  }

  def evalGep(baseType: Type, fields: Seq[String]): (Type, Seq[Int]) = {
    fields.foldLeft((baseType, Seq[Int](0))) {
      case ((_type, seq), field) =>
        val (f, idx) =
          _type.asInstanceOf[Struct].fields.zipWithIndex.find {
            case (f, idx) => f.name == field
          }.head
        (f._type, seq ++ Seq(idx))
    }
  }

  def zalloc(varName: String, varType: Type): Unit = {
    val (_size, size, memsetDest) = (nextTmpVar(), nextTmpVar(), nextTmpVar())

    out.println(s"\t$varName = alloca ${varType.name}, align 4")
    out.println(s"\t${_size} = getelementptr ${varType.name}, ${varType.name}* null, i32 1")
    out.println(s"\t$size = ptrtoint ${varType.name}* ${_size} to i64")
    out.println(s"\t$memsetDest = bitcast ${varType.name}* $varName to i8*")
    out.println(s"\tcall void @llvm.memset.p0i8.i64(i8* $memsetDest, i8 0, i64 $size, i32 4, i1 false)")
  }

  def load(from: String, varType: Type) = {
    val next = nextTmpVar()
    out.println(s"\t$next = load ${varType.name}, ${varType.name}* $from")
    next
  }

  def store(from: String, to: String, varType: Type) =
    out.println(s"\tstore ${varType.name} $from, ${varType.name}* $to")

  def loadAndStore(from: String, to: String, varType: Type) = {
    val next = nextTmpVar()
    out.println(s"\t$next = load ${varType.name}, ${varType.name}* $from")
    out.println(s"\tstore ${varType.name} $next, ${varType.name}* $to")
  }

  def genInitWithStore(_type: Type, to: String, init: Init) = init match {
    case lInt(value) => store(value, to, _type)
    case lFloat(value) =>
      val _value =
        if (_type.name == "float") {
          val d = (new java.lang.Float(value)).toDouble
          "0x" + java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(d))
        } else value

      store(_value, to, _type)
    case lString(name, value) =>
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = bitcast [${value.bytesLen} x i8]* @.$name to i8*")
      store(tmp1, to, _type)
    case lGlobal(value) => store("@" + value, to, _type)
    case lLocal(varName) => loadAndStore(s"%$varName", to, _type)
    case lParam(paramName) =>
      val value =
        if (_type.isInstanceOf[Struct]) load(s"%$paramName", _type)
        else s"%$paramName"

      store(value, to, _type)
    case a: Access =>
      val (resType, indicies) = evalGep(a.fromType, Seq(a.prop))
      val base = genInitWithPtr(a.fromType, a.from)

      val res = nextTmpVar()
      out.println(s"\t$res = getelementptr ${a.fromType.name}, ${a.fromType.name}* $base, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
      store(res, to, _type)
    case Call(id, fnPtr, args) =>
      val toCall = id match {
        case lLocal(value) => load("%" + value, fnPtr)
        case lGlobal(value) => "@" + escapeFnName(value)
        case lParam(value) => "%" + value
      }

      fnPtr.ret match {
        case struct@Struct(_name, fields) =>
          val lastArgs = args

          val calculatedArgs = to +: lastArgs.zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg)
            else genInitWithValue(field._type, arg)
          }

          val joinedArgs = calculatedArgs.zip(fnPtr.realArgs).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          }.mkString(", ")

          out.println(s"\tcall ${fnPtr.realRet.name} ${toCall}($joinedArgs)")
        case _ =>
          val calculatedArgs = (args).zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg)
            else genInitWithValue(field._type, arg)
          }

          val joinedArgs = calculatedArgs.zip(fnPtr.args).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          }.mkString(", ")

          if (fnPtr.ret == Scalar("void"))
            throw new Exception("cannot store void value")


          val tmp = nextTmpVar()
          out.println(s"\t$tmp = call ${fnPtr.ret.name} ${toCall}($joinedArgs)")
          store(tmp, to, _type)
      }
    case BoolAnd(left, right) =>
      val (beginLabel, secondLabel, endLabel) = (nextLabel, nextLabel, nextLabel)
      out.println(s"\tbr label %$beginLabel")
      out.println(s"$beginLabel:")
      val leftRes = genInitWithValue(Scalar("i1"), left)
      out.println(s"\tbr i1 $leftRes, label %$secondLabel, label %$endLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right)
      out.println(s"\tbr label %$endLabel")

      out.println(s"$endLabel:")
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = phi i1 [false, %$beginLabel], [$rightRes, %$secondLabel]")

      store(tmp1, to, _type)
    case BoolOr(left, right) =>
      //FIXME: deduplicate code
      val (beginLabel, secondLabel, endLabel) = (nextLabel, nextLabel, nextLabel)
      out.println(s"\tbr label %$beginLabel")
      out.println(s"$beginLabel:")
      val leftRes = genInitWithValue(Scalar("i1"), left)
      out.println(s"\tbr i1 $leftRes, label %$endLabel, label %$secondLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right)
      out.println(s"\tbr label %$endLabel")

      out.println(s"$endLabel:")
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = phi i1 [true, %$beginLabel], [$rightRes, %$secondLabel]")

      store(tmp1, to, _type)
  }

  def genInitWithValue(_type: Type, init: Init): String = init match {
    case lInt(value) => value
    case lFloat(value) =>
      if (_type.name == "float") {
        val d = (new java.lang.Float(value)).toDouble
        "0x" + java.lang.Long.toHexString(java.lang.Double.doubleToLongBits(d))
      } else value
    case lString(name, value) =>
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = bitcast [${value.bytesLen} x i8]* @.$name to i8*")
      tmp1
    case lGlobal(value) => "@" + value
    case lLocal(varName) => load(s"%$varName", _type)
    case lParam(paramName) =>
      if (_type.isInstanceOf[Struct]) load(s"%$paramName", _type)
      else s"%$paramName"
    case a: Access =>
      val (resType, indicies) = evalGep(a.fromType, Seq(a.prop))
      val base = genInitWithPtr(a.fromType, a.from)
      val res = nextTmpVar()

      out.println(s"\t$res = getelementptr ${a.fromType.name}, ${a.fromType.name}* $base, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
      load(res, _type)
    case Call(id, fnPtr, args) =>
      val toCall = id match {
        case lLocal(value) => load("%" + value, fnPtr)
        case lGlobal(value) => "@" + escapeFnName(value)
        case lParam(value) => "%" + value
      }

      fnPtr.ret match {
        case struct@Struct(_name, fields) => throw new Exception("not implemented in ABI")
        case _ =>
          val calculatedArgs = (args).zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg)
            else genInitWithValue(field._type, arg)
          }

          val joinedArgs = calculatedArgs.zip(fnPtr.args).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          }.mkString(", ")

          if (fnPtr.ret == Scalar("void")) {
            out.println(s"\tcall ${fnPtr.ret.name} ${toCall}($joinedArgs)")
            null
          } else {
            val tmp = nextTmpVar()
            out.println(s"\t$tmp = call ${fnPtr.ret.name} ${toCall}($joinedArgs)")
            tmp
          }
      }
    case BoolAnd(left, right) =>
      val (beginLabel, secondLabel, endLabel) = (nextLabel, nextLabel, nextLabel)
      out.println(s"\tbr label %$beginLabel")
      out.println(s"$beginLabel:")
      val leftRes = genInitWithValue(Scalar("i1"), left)
      out.println(s"\tbr i1 $leftRes, label %$secondLabel, label %$endLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right)
      out.println(s"\tbr label %$endLabel")

      out.println(s"$endLabel:")
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = phi i1 [false, %$beginLabel], [$rightRes, %$secondLabel]")

      tmp1
    case BoolOr(left, right) =>
      //FIXME: deduplicate code
      val (beginLabel, secondLabel, endLabel) = (nextLabel, nextLabel, nextLabel)
      out.println(s"\tbr label %$beginLabel")
      out.println(s"$beginLabel:")
      val leftRes = genInitWithValue(Scalar("i1"), left)
      out.println(s"\tbr i1 $leftRes, label %$endLabel, label %$secondLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right)
      out.println(s"\tbr label %$endLabel")

      out.println(s"$endLabel:")
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = phi i1 [true, %$beginLabel], [$rightRes, %$secondLabel]")

      tmp1
  }

  def genInitWithPtr(_type: Type, init: Init): String = init match {
    case lInt(value) => throw new Exception("not implemented in ABI")
    case lFloat(value) => throw new Exception("not implemented in ABI")
    case lString(name, value) => throw new Exception("not implemented in ABI")
    case lGlobal(value) => throw new Exception("not implemented in ABI")
    case lLocal(varName) => s"%$varName"
    case lParam(paramName) =>
      if (_type.isInstanceOf[Struct]) "%" + paramName
      else throw new Exception("not implemented in ABI")
    case a: Access =>
      val (resType, indicies) = evalGep(a.fromType, Seq(a.prop))
      val base = genInitWithPtr(a.fromType, a.from)
      val res = nextTmpVar()
      out.println(s"\t$res = getelementptr ${a.fromType.name}, ${a.fromType.name}* $base, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
      res
    case Call(id, fnPtr, args) => throw new Exception("not implemented in ABI")
    case BoolAnd(left, right) => throw new Exception("not implemented in ABI")
    case BoolOr(left, right) => throw new Exception("not implemented in ABI")
  }


  def genStat(seq: Seq[Stat]): Unit =
    seq.foreach {
      case v: Var =>
        zalloc(v.irName, v._type)
      case Store(to, fields, varType, init) =>
        val base = genInitWithPtr(varType, to)
        val ptr = nextTmpVar()
        val (resType, indicies) = evalGep(varType, fields)
        out.println(s"\t$ptr = getelementptr ${varType.name}, ${varType.name}* ${base}, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
        genInitWithStore(resType, ptr, init)
      case call: Call =>
        genInitWithValue(call._type.ret, call)
      case boolAnd: BoolAnd =>
        genInitWithValue(Scalar("i1"), boolAnd)
      case boolOr: BoolOr =>
        genInitWithValue(Scalar("i1"), boolOr)
      case Cond(init, _if, _else) =>
        val condVar = genInitWithValue(Scalar("i1"), init)
        val (ifLabel, elseLabel, endLabel) = (nextLabel, nextLabel, nextLabel)

        out.println(s"\tbr i1 $condVar, label %$ifLabel, label %$elseLabel")
        out.println(s"$ifLabel:")
        genStat(_if)
        out.println(s"\tbr label %$endLabel")
        out.println(s"$elseLabel:")
        genStat(_else)
        out.println(s"\tbr label %$endLabel")
        out.println(s"$endLabel:")
      case While(init, seq) =>
        val (beginLabel, bodyLabel, endLabel) = (nextLabel, nextLabel, nextLabel)

        out.println(s"\tbr label %$beginLabel")
        out.println(s"$beginLabel:")

        val condVar = genInitWithValue(Scalar("i1"), init)
        out.println(s"\tbr i1 $condVar, label %$bodyLabel, label %$endLabel")
        out.println(s"$bodyLabel:")

        genStat(seq)

        out.println(s"\tbr label %$beginLabel")
        out.println(s"$endLabel:")

      case Ret(_type, init) =>
        out.println(s"\tret ${_type.name} ${genInitWithValue(_type, init)}")
      case RetVoid() =>
        out.println(s"\tret void")
    }

  def genFunction(fn: Fn): Unit = {
    val signature = fn._type
    out.println(s"define ${signature.realRet.name} @${escapeFnName(fn.name)}(${signature.irArgs.mkString(", ")}) {")

    fn.body match {
      case IrInline(ir) => out.println(s"$ir")
      case Block(seq) => genStat(seq)
    }
    out.println("}")
  }

  case class StringConst(name: String, value: HexUtil.EncodeResult)

  def inferStringConsts(functions: Seq[Fn]): (Seq[StringConst], Seq[Fn]) = {
    val consts = mutable.ListBuffer[StringConst]()

    def mapInit(init: Init): Init = init match {
      case ls@lString(name, value) =>
        consts += StringConst(name, value)
        ls
      case Call(name, ptr, args: Seq[Init]) =>
        Call(name, ptr, args.map(arg => mapInit(arg)))
      case some@_ => some
    }

    def mapStat(stat: Stat): Stat = stat match {
      case Store(toVar: lId, fields: Seq[String], varType: Type, init: Init) =>
        Store(toVar, fields, varType, mapInit(init))
      case Call(name, ptr, args: Seq[Init]) =>
        Call(name, ptr, args.map(arg => mapInit(arg)))
      case Cond(init: Init, _if: Seq[Stat], _else: Seq[Stat]) =>
        Cond(mapInit(init), _if.map(stat => mapStat(stat)), _else.map(stat => mapStat(stat)))
      case While(init: Init, seq: Seq[Stat]) =>
        While(mapInit(init), seq.map(stat => mapStat(stat)))
      case Ret(_type: Type, init: Init) =>
        Ret(_type, mapInit(init))
      case v: Var => v
      case rv: RetVoid => rv
      case boolAnd: BoolAnd => boolAnd
      case boolOr: BoolOr => boolOr
    }

    val mapped = functions.map { fn =>
      Fn(fn.name, fn._type, fn.body match {
        case ir: IrInline => ir
        case Block(seq) => Block(seq.map {
          stat => mapStat(stat)
        })
      })
    }
    (consts, mapped)
  }

  def ghoistVars(functions: Seq[Fn]): Seq[Fn] = {
    def ghoistBlockVars(seq: Seq[Stat]): (Seq[Var], Seq[Stat]) = {
      val varsAndStats: Seq[(Seq[Var], Seq[Stat])] = seq.map {
        case v: Var => (Seq(v), Seq())
        case Cond(init, _if, _else) =>
          val __if = ghoistBlockVars(_if)
          val __else = ghoistBlockVars(_else)
          val vars = __if._1 ++ __else._1
          val cond = Cond(init, __if._2, __else._2)
          (vars, Seq(cond))
        case While(init, seq) =>
          val _seq = ghoistBlockVars(seq)
          (_seq._1, Seq(While(init, _seq._2)))
        case other: Stat => (Seq(), Seq(other))
      }
      varsAndStats.foldLeft((Seq[Var](), Seq[Stat]())) {
        case ((vars1, stats1), (vars2, stats2)) => (vars1 ++ vars2, stats1 ++ stats2)
      }
    }

    functions.map {
      case Fn(name, _type, Block(seq)) =>
        val reordered = ghoistBlockVars(seq)
        Fn(name, _type, Block(reordered._1 ++ reordered._2))
      case fn@_ => fn
    }
  }

  def gen(module: Module): Unit = {
    out.println("declare i32 @memcmp(i8*, i8*, i64)")
    out.println("declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1)")

    module.headers.foreach { header =>
      val signature = header._type
      out.println(s"declare ${signature.realRet.name} @${escapeFnName(header.name)}(${signature.irArgs.mkString(", ")})")
    }

    module.structs.foreach { struct =>
      out.println(s"${struct.name} = type { ${struct.fields.map { f => f._type.name }.mkString(", ")} }")
    }

    val (consts, functions1) = inferStringConsts(module.functions)
    val functions = ghoistVars(functions1)

    consts.foreach { const =>
      out.println(s"@.${const.name} = private constant [${const.value.bytesLen} x i8] ${"c\"" + const.value.str + "\""}, align 1")
    }
    functions.foreach {
      fn => tmpVars = 0; genFunction(fn)
    }
  }
}