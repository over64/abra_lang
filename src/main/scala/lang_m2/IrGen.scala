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

  def genInitWithValue(functions: Seq[Fn], fn: Fn, _type: Type, init: Init): String = init match {
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

  def genInitWithPtr(functions: Seq[Fn], fn: Fn, _type: Type, init: Init): String = init match {
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


  def genStat(functions: Seq[Fn], fn: Fn, seq: Seq[Stat]): Unit =
    seq.foreach {
      case Store(to, fields, init) =>
        val base = to match {
          case lLocal(value) => "%" + value
          case lClosure(value) => fn._type match {
            case Closure(typeName, _, vals) =>
              val closured = vals.zipWithIndex.find {
                case (cv, _) => cv.name == value
                case (cv, _) => cv.name == value
              }.get
              closured match {
                case ClosureVal(lLocal(vName), varType) =>
                  val ptr = nextTmpVar()
                  out.println(s"\t$ptr = getelementptr %${typeName}, %${typeName}* ${ptr}, i32 0, i32 0")
                case _: lParam => throw new Exception("not implemented in ABI")
              }
            case _ => throw new Exception("not implemented in ABI")
          }
          case _ => throw new Exception("not implemented in ABI")
        }
        val base = genInitWithPtr(functions, fn, varType, to)
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

  def genFunction(functions: Seq[Fn], fn: Fn): Unit = {
    fn match {
      case fnPtr@FnPointer(args, ret) =>
        out.println(s"define ${fnPtr.realRet.name} @${escapeFnName(fn.name)}(${fnPtr.realArgs.mkString(", ")}) {")
      case Closure(typeName, fnPtr, _) =>
        val args = fnPtr.realArgs :+ Field("closure", Scalar(typeName))
        out.println(s"define ${fnPtr.realRet.name} @${fn.name}(${args.mkString(", ")}) {")
      case Disclosure(typeName, fnPointer) => throw new Exception("not implemented in ABI")
    }

    fn.body match {
      case IrInline(ir) => out.println(ir)
      case Block(vars, stats) =>
        vars.foreach {
          case (varName, varType) => zalloc("%" + varName, varType)
        }
        genStat(functions, fn, stats)
    }

    out.println("}")
  }

  def gen(module: Module): Unit = {
    println(module)
    out.println("declare i32 @memcmp(i8*, i8*, i64)")
    out.println("declare void @llvm.memset.p0i8.i64(i8* nocapture, i8, i64, i32, i1)")

    module.headers.foreach { header =>
      val signature = header._type
      out.println(s"declare ${signature.realRet.name} @${escapeFnName(header.name)}(${signature.irArgs.mkString(", ")})")
    }

    module.structs.foreach { struct =>
      out.println(s"${struct.name} = type { ${struct.fields.map { f => f._type.name }.mkString(", ")} }")
    }

    val (consts, functions1) = IrPasses.inferStringConsts(module.functions)


    consts.foreach { const =>
      out.println(s"@.${const.name} = private constant [${const.value.bytesLen} x i8] ${"c\"" + const.value.str + "\""}, align 1")
    }
    functions1.foreach {
      fn => tmpVars = 0; genFunction(functions1, fn)
    }
  }
}