package lang_m2

import java.io.OutputStream

import lang_m2.Ast0.LlInline
import lang_m2.Ast1._

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
    varType match {
      case struct: Struct =>
        val (_size, size, memsetDest) = (nextTmpVar(), nextTmpVar(), nextTmpVar())
        out.println(s"\t$varName = alloca ${varType.name}, align 4")
        out.println(s"\t${_size} = getelementptr ${varType.name}, ${varType.name}* null, i32 1")
        out.println(s"\t$size = ptrtoint ${varType.name}* ${_size} to i64")
        out.println(s"\t$memsetDest = bitcast ${varType.name}* $varName to i8*")
        out.println(s"\tcall void @llvm.memset.p0i8.i64(i8* $memsetDest, i8 0, i64 $size, i32 4, i1 false)")
      case _ =>
        out.println(s"\t$varName = alloca ${varType.name}")
    }
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

  def genInitWithStore(_type: Type, to: String, init: Init, closure: Option[FnClosure]) = init match {
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
    case lClosure(value) =>
      val _closure = closure.get
      val (closuredVal, index) = _closure.vals.zipWithIndex.find {
        case (cval, index) => cval.closurable.value == value
      }.get
      closuredVal.closurable match {
        case lLocal(value) =>
          val (ptr, ptrToVal) = (nextTmpVar(), nextTmpVar())
          out.println(s"\t$ptr = getelementptr %${_closure.closureType}, %${_closure.closureType}* %1, i32 0, i32 ${index + 1}")
          out.println(s"\t$ptrToVal = load ${_type.name}*, ${_type.name}** $ptr")
          val loadedVal = load(ptrToVal, _type)
          store(loadedVal, to, _type)
        case lParam(value) =>
          val ptr = nextTmpVar()
          out.println(s"\t$ptr = getelementptr %${_closure.closureType}, %${_closure.closureType}* %1, i32 0, i32 ${index + 1}")
          val loadedVal =
            if (_type.isInstanceOf[Struct]) {
              val ptrToVal = nextTmpVar()
              out.println(s"\t$ptrToVal = load ${_type.name}*, ${_type.name}** $ptr")
              load(ptrToVal, _type)
            } else
              load(ptr, _type)

          store(loadedVal, to, _type)
      }

    case a: Access =>
      val (resType, indicies) = evalGep(a.fromType, Seq(a.prop))
      val base = genInitWithPtr(a.fromType, a.from, closure)

      val res = nextTmpVar()
      out.println(s"\t$res = getelementptr ${a.fromType.name}, ${a.fromType.name}* $base, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
      store(res, to, _type)
    case Call(id, fnPtr, args) =>
      val toCall = id match {
        case lLocal(value) => load("%" + value, fnPtr)
        case lGlobal(value) => "@" + escapeFnName(value)
        case lParam(value) => "%" + value
      }

      val closureArgs = fnPtr.closure match {
        case Some(fnClosure) =>
          val tmp = nextTmpVar()
          out.println(s"\t$tmp = bitcast ${fnPtr.name} $toCall to i8*")
          Seq(s"i8* $tmp")
        case None => Seq()
      }

      fnPtr.ret match {
        case struct@Struct(_name, fields) =>
          val lastArgs = args

          val calculatedArgs = to +: lastArgs.zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg, closure)
            else genInitWithValue(field._type, arg, closure)
          }

          val joinedArgs = (calculatedArgs.zip(fnPtr.realArgs).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          } ++ closureArgs).mkString(", ")

          out.println(s"\tcall ${fnPtr.realRet.name} ${toCall}($joinedArgs)")
        case _ =>
          val calculatedArgs = (args).zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg, closure)
            else genInitWithValue(field._type, arg, closure)
          }

          val joinedArgs = (calculatedArgs.zip(fnPtr.args).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          } ++ closureArgs).mkString(", ")

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
      val leftRes = genInitWithValue(Scalar("i1"), left, closure)
      out.println(s"\tbr i1 $leftRes, label %$secondLabel, label %$endLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right, closure)
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
      val leftRes = genInitWithValue(Scalar("i1"), left, closure)
      out.println(s"\tbr i1 $leftRes, label %$endLabel, label %$secondLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right, closure)
      out.println(s"\tbr label %$endLabel")

      out.println(s"$endLabel:")
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = phi i1 [true, %$beginLabel], [$rightRes, %$secondLabel]")

      store(tmp1, to, _type)
  }

  def genInitWithValue(_type: Type, init: Init, closure: Option[FnClosure]): String = init match {
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
    case lLocal(varName) =>
      load(s"%$varName", _type)
    case lParam(paramName) =>
      if (_type.isInstanceOf[Struct]) load(s"%$paramName", _type)
      else s"%$paramName"
    case lClosure(value) =>
      val _closure = closure.get
      val (closuredVal, index) = _closure.vals.zipWithIndex.find {
        case (cval, index) => cval.closurable.value == value
      }.get
      closuredVal.closurable match {
        case lLocal(value) =>
          val (ptr, ptrToVal) = (nextTmpVar(), nextTmpVar())
          out.println(s"\t$ptr = getelementptr %${_closure.closureType}, %${_closure.closureType}* %1, i32 0, i32 ${index + 1}")
          out.println(s"\t$ptrToVal = load ${_type.name}*, ${_type.name}** $ptr")
          load(ptrToVal, _type)
        case lParam(value) =>
          val ptr = nextTmpVar()
          out.println(s"\t$ptr = getelementptr %${_closure.closureType}, %${_closure.closureType}* %1, i32 0, i32 ${index + 1}")
          val loadedVal =
            if (_type.isInstanceOf[Struct]) {
              val ptrToVal = nextTmpVar()
              out.println(s"\t$ptrToVal = load ${_type.name}*, ${_type.name}** $ptr")
              load(ptrToVal, _type)
            } else
              load(ptr, _type)

          loadedVal
      }
    case a: Access =>
      val (resType, indicies) = evalGep(a.fromType, Seq(a.prop))
      val base = genInitWithPtr(a.fromType, a.from, closure)
      val res = nextTmpVar()

      out.println(s"\t$res = getelementptr ${a.fromType.name}, ${a.fromType.name}* $base, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
      load(res, _type)
    case Call(id, fnPtr, args) =>
      val (toCall, closureArgs) = id match {
        case lLocal(value) =>
          fnPtr.closure match {
            case Some(closure) =>
              val ptr = nextTmpVar()
              out.println(s"\t$ptr = getelementptr %${closure.closureType}, %${closure.closureType}* %$value, i32 0, i32 0")
              val loadedFnPtr = load(s"$ptr", FnPointer(fnPtr.args :+ Field("closure", Scalar("i8*")), fnPtr.ret))
              val closurePtr = nextTmpVar()
              out.println(s"\t$closurePtr = bitcast %${closure.closureType}* %$value to i8*")

              (loadedFnPtr, Seq(s"i8* $closurePtr"))
            case _ => (load(s"%$value", fnPtr), Seq())
          }
        case lGlobal(value) => ("@" + escapeFnName(value), Seq())
        case lParam(value) => ("%" + value, Seq())
      }

      fnPtr.ret match {
        case struct@Struct(_name, fields) => throw new Exception("not implemented in ABI")
        case _ =>
          val calculatedArgs = (args).zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg, closure)
            else genInitWithValue(field._type, arg, closure)
          }

          val joinedArgs = (calculatedArgs.zip(fnPtr.args).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          } ++ closureArgs).mkString(", ")

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
      val leftRes = genInitWithValue(Scalar("i1"), left, closure)
      out.println(s"\tbr i1 $leftRes, label %$secondLabel, label %$endLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right, closure)
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
      val leftRes = genInitWithValue(Scalar("i1"), left, closure)
      out.println(s"\tbr i1 $leftRes, label %$endLabel, label %$secondLabel")

      out.println(s"$secondLabel:")
      val rightRes = genInitWithValue(Scalar("i1"), right, closure)
      out.println(s"\tbr label %$endLabel")

      out.println(s"$endLabel:")
      val tmp1 = nextTmpVar()
      out.println(s"\t$tmp1 = phi i1 [true, %$beginLabel], [$rightRes, %$secondLabel]")

      tmp1
  }

  def genInitWithPtr(_type: Type, init: Init, closure: Option[FnClosure]): String = init match {
    case lInt(value) => throw new Exception("not implemented in ABI")
    case lFloat(value) => throw new Exception("not implemented in ABI")
    case lString(name, value) => throw new Exception("not implemented in ABI")
    case lGlobal(value) => throw new Exception("not implemented in ABI")
    case lLocal(varName) => s"%$varName"
    case lParam(paramName) =>
      if (_type.isInstanceOf[Struct]) "%" + paramName
      else throw new Exception("not implemented in ABI")
    case lClosure(value) =>
      val _closure = closure.get
      val (closuredVal, index) = _closure.vals.zipWithIndex.find {
        case (cval, index) => cval.closurable.value == value
      }.get
      closuredVal.closurable match {
        case lLocal(value) =>
          val (ptr, ptrToVal) = (nextTmpVar(), nextTmpVar())
          out.println(s"\t$ptr = getelementptr %${_closure.closureType}, %${_closure.closureType}* %1, i32 0, i32 ${index + 1}")
          out.println(s"\t$ptrToVal = load ${_type.name}*, ${_type.name}** $ptr")
          ptrToVal
        case lParam(value) =>
          val ptr = nextTmpVar()
          out.println(s"\t$ptr = getelementptr %${_closure.closureType}, %${_closure.closureType}* %1, i32 0, i32 ${index + 1}")

          if (_type.isInstanceOf[Struct]) {
            val ptrToVal = nextTmpVar()
            out.println(s"\t$ptrToVal = load ${_type.name}*, ${_type.name}** $ptr")
            ptrToVal
          } else
            ptr
      }
    case Call(id, fnPtr, args) =>
      val toCall = id match {
        case lLocal(value) => load("%" + value, fnPtr)
        case lGlobal(value) => "@" + escapeFnName(value)
        case lParam(value) => "%" + value
      }

      fnPtr.ret match {
        case struct@Struct(_name, fields) =>
          val to = nextTmpVar()
          zalloc(to, fnPtr.ret)
          val lastArgs = args

          val calculatedArgs = to +: lastArgs.zip(fnPtr.args).map { case (arg, field) =>
            if (field._type.isInstanceOf[Struct]) genInitWithPtr(field._type, arg, closure)
            else genInitWithValue(field._type, arg, closure)
          }

          val joinedArgs = calculatedArgs.zip(fnPtr.realArgs).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          }.mkString(", ")

          out.println(s"\tcall ${fnPtr.realRet.name} ${toCall}($joinedArgs)")
          to
        case _ => throw new Exception("not implemented in ABI")
      }
    case BoolAnd(left, right) => throw new Exception("not implemented in ABI")
    case BoolOr(left, right) => throw new Exception("not implemented in ABI")
  }


  def genStat(seq: Seq[Stat], closure: Option[FnClosure]): Unit =
    seq.foreach {
      case v: Var =>
        zalloc(v.irName, v._type)
      case Store(to, fields, varType, init) =>
        val base = genInitWithPtr(varType, to, closure)
        val (ptr, resType) =
          if (fields.length == 0) (base, varType)
          else {
            val ptr = nextTmpVar()
            val (resType, indicies) = evalGep(varType, fields)
            out.println(s"\t$ptr = getelementptr ${varType.name}, ${varType.name}* ${base}, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
            (ptr, resType)
          }
        resType match {
          case FnPointer(args, ret, Some(FnClosure(closureType, vals))) if init.isInstanceOf[lGlobal] =>
            val fnPtr = nextTmpVar()
            out.println(s"\t$fnPtr = getelementptr %${closureType}, %${closureType}* ${ptr}, i32 0, i32 0")
            genInitWithStore(FnPointer(args :+ Field("closure", Scalar("i8*")), ret), fnPtr, init, closure)
            vals.zipWithIndex.foreach {
              case (cval, index) =>
                val valPtr = nextTmpVar()
                out.println(s"\t$valPtr = getelementptr %${closureType}, %${closureType}* ${ptr}, i32 0, i32 ${index + 1}")
                cval.closurable match {
                  case lLocal(value) =>
                    val closuredPtr = genInitWithPtr(cval.varType, lLocal(value), closure)
                    store(closuredPtr, valPtr, Scalar(cval.irType)) //FIXME

                }
            }
          case _ =>
            genInitWithStore(resType, ptr, init, closure)
        }
      case call: Call =>
        genInitWithValue(call._type.ret, call, closure)
      case boolAnd: BoolAnd =>
        genInitWithValue(Scalar("i1"), boolAnd, closure)
      case boolOr: BoolOr =>
        genInitWithValue(Scalar("i1"), boolOr, closure)
      case Cond(init, _if, _else) =>
        val condVar = genInitWithValue(Scalar("i1"), init, closure)
        val (ifLabel, elseLabel, endLabel) = (nextLabel, nextLabel, nextLabel)

        out.println(s"\tbr i1 $condVar, label %$ifLabel, label %$elseLabel")
        out.println(s"$ifLabel:")
        genStat(_if, closure)
        out.println(s"\tbr label %$endLabel")
        out.println(s"$elseLabel:")
        genStat(_else, closure)
        out.println(s"\tbr label %$endLabel")
        out.println(s"$endLabel:")
      case While(init, seq) =>
        val (beginLabel, bodyLabel, endLabel) = (nextLabel, nextLabel, nextLabel)

        out.println(s"\tbr label %$beginLabel")
        out.println(s"$beginLabel:")

        val condVar = genInitWithValue(Scalar("i1"), init, closure)
        out.println(s"\tbr i1 $condVar, label %$bodyLabel, label %$endLabel")
        out.println(s"$bodyLabel:")

        genStat(seq, closure)

        out.println(s"\tbr label %$beginLabel")
        out.println(s"$endLabel:")

      case Ret(_type, init) =>
        out.println(s"\tret ${_type.name} ${genInitWithValue(_type, init, closure)}")
      case RetVoid() =>
        out.println(s"\tret void")
    }

  def genFunction(fn: Fn): Unit = {
    val signature = fn._type
    signature.closure.map { closure =>
      val fields = signature.callName +: closure.vals.map(_.irType)
      out.println(s"%${closure.closureType} = type { ${fields.mkString(", ")} }")
      val args = signature.irArgs :+ s"i8* %closure"
      out.println(s"define ${signature.realRet.name} @${escapeFnName(fn.name)}(${args.mkString(", ")}) {")
      val one = nextTmpVar()
      out.println(s"\t$one = bitcast i8* %closure to %${closure.closureType}*")
    }.getOrElse {
      out.println(s"define ${signature.realRet.name} @${escapeFnName(fn.name)}(${signature.irArgs.mkString(", ")}) {")
    }

    fn.body match {
      case IrInline(ir) => out.println(s"$ir")
      case Block(seq) => genStat(seq, signature.closure)
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

  def extractCallParams(functions: Seq[Fn]): Seq[Fn] = {
    var anonVars = 0
    def nextAnonVar = {
      anonVars += 1
      s"anon__$anonVars"
    }

    def mapCall(call: Call): (Seq[Stat], Call) = call match {
      case Call(fn, _type, args) =>
        val start: Seq[(Seq[Stat], Init)] = Seq()
        val permutated: Seq[(Seq[Stat], Init)] =
          _type.args.zip(args).foldLeft(start) {
            case (seq, (arg, argInit)) =>
              (arg._type, argInit) match {
                case (struct: Struct, call: Call) =>
                  val newVar = Var(nextAnonVar, struct)
                  val (before, newCall) = mapCall(call)
                  val newStore = Store(lLocal(newVar.name), Seq(), struct, newCall)
                  val newInit = lLocal(newVar.name)
                  seq :+ (before :+ newVar :+ newStore, newInit)
                //                case (fnPtr@FnPointer(args, ret, Some(fnClosure)), global: lGlobal) =>
                //                  val newVar = Var(nextAnonVar, fnPtr)
                //                  val newStore = Store(lLocal(newVar.name), Seq(), fnPtr, global)
                //                  val newInit = lLocal(newVar.name)
                //                  seq :+ (Seq(newVar, newStore), newInit)
                case _ => seq :+ (Seq(), argInit)
              }
          }
        val stats = permutated.flatMap {
          case (seq, init) => seq
        }
        val newCallArgs = permutated.map {
          case (seq, init) => init
        }

        (stats, Call(fn, _type, newCallArgs))
    }
    def mapBoolOr(boolOr: BoolOr): (Seq[Stat], BoolOr) = boolOr match {
      case BoolOr(left, right) =>
        val (leftStats, l) = mapInit(left)
        val (rightStats, r) = mapInit(right)
        (leftStats ++ rightStats, BoolOr(l, r))
    }
    def mapBoolAnd(boolAnd: BoolAnd): (Seq[Stat], BoolAnd) = boolAnd match {
      case BoolAnd(left, right) =>
        val (leftStats, l) = mapInit(left)
        val (rightStats, r) = mapInit(right)
        (leftStats ++ rightStats, BoolAnd(l, r))
    }
    def mapInit(init: Init): (Seq[Stat], Init) = init match {
      case call: Call => mapCall(call)
      case b: BoolOr => mapBoolOr(b)
      case b: BoolAnd => mapBoolAnd(b)
      case other@_ => (Seq(), other)
    }
    def mapBlock(seq: Seq[Stat]): Seq[Stat] =
      seq.foldLeft(Seq[Stat]()) {
        case (seq, call: Call) =>
          val (stats, newCall) = mapCall(call)
          seq ++ stats :+ newCall
        case (seq, Store(to, fields, varType, init)) =>
          val (stats, newInit) = mapInit(init)
          (seq ++ stats :+ Store(to, fields, varType, newInit))
        case (seq, boolAnd: BoolAnd) =>
          val (stats, b) = mapBoolAnd(boolAnd)
          (seq ++ stats :+ b)
        case (seq, boolOr: BoolOr) =>
          val (stats, b) = mapBoolOr(boolOr)
          (seq ++ stats :+ b)
        case (seq, Cond(init, _if, _else)) =>
          val (stats, newInit) = mapInit(init)
          (seq ++ stats :+ Cond(newInit, mapBlock(_if), mapBlock(_else)))
        case (seq, While(init, wseq)) =>
          val (stats, newInit) = mapInit(init)
          (seq ++ stats :+ While(newInit, mapBlock(wseq)))
        case (seq, Ret(_type, init)) =>
          val (stats, newInit) = mapInit(init)
          (seq ++ stats :+ Ret(_type, newInit))
        case (seq, other) => seq :+ other
      }

    functions.map {
      case Fn(name, _type, Block(seq)) =>
        Fn(name, _type, Block(mapBlock(seq)))
      case other: Fn => other
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
    val functions = ghoistVars(extractCallParams(functions1))

    consts.foreach { const =>
      out.println(s"@.${const.name} = private constant [${const.value.bytesLen} x i8] ${"c\"" + const.value.str + "\""}, align 1")
    }
    functions.foreach {
      fn => tmpVars = 0; genFunction(fn)
    }
  }
}