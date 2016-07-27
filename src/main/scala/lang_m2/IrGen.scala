package lang_m2

import Ast1._

class IrGen {
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

  // (resultName, resultType)
  def evalAccess(access: Access): (String, Type) =
    if (access.seq.isEmpty)
      (access.irFrom, access.fromType)
    else {
      val res = nextTmpVar()
      val (resType, indicies) =
        access.seq.foldLeft((access.fromType, Seq[Int](0))) {
          case ((_type, seq), field) =>
            val (f, idx) =
              _type.asInstanceOf[Struct].fields.zipWithIndex.find {
                case (f, idx) => f.name == field
              }.head
            (f._type, seq ++ Seq(idx))
        }
      println(s"\t$res = getelementptr ${access.fromType.name}, ${access.fromType.name}* %${access.from}, ${indicies.map { i => s"i32 $i" } mkString (", ")}")
      (res, resType)
    }

  def joinCallArgs(ptr: FnPtr, args: Seq[Init]): String = {
    val calculatedArgs = args.zip(ptr.signature.args).map {
      case (arg, argType) =>
        genInit(argType._type, arg, needPtr = argType._type.isInstanceOf[Struct])
    }

    calculatedArgs.zip(ptr.signature.args).map {
      case (arg, argType) =>
        s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
    }.mkString(", ")
  }

  def genInit(_type: Type, init: Init, needPtr: Boolean = false): String = init match {
    case lInt(value) =>
      if (needPtr) throw new Exception("not implemented in current ABI") else value
    case lFloat(value) =>
      if (needPtr) throw new Exception("not implemented in current ABI") else value
    case lString(value) =>
      if (needPtr) throw new Exception("not implemented in current ABI") else value
    case lId(varName) =>
      if (needPtr) "%" + varName
      else {
        val tmp = nextTmpVar()
        println(s"\t$tmp = load ${_type.name}, ${_type.name}* %$varName")
        tmp
      }
    case lParam(paramName) =>
      // параметры всегда value
      if (needPtr) {
        val tmp = nextTmpVar()
        println(s"\t$tmp = load ${_type.name}, ${_type.name}* %$paramName")
        tmp
      } else
        "%" + paramName
    case a: Access =>
      val (tmp, _type) = evalAccess(a)
      val next = nextTmpVar()
      println(s"\t$next = load ${_type.name}, ${_type.name}* $tmp")
      next
    case Call(ptr, args) =>
      ptr.signature.ret match {
        case struct@Struct(_name, fields) =>
          val newVar = nextTmpVar(needPercent = false)
          val newArgs = Seq(lId(newVar)) ++ args
          val newSignArgs = Seq(Field("ret", struct)) ++ ptr.signature.args

          println(s"\t%$newVar = alloca ${struct.name}, align 4")

          val calculatedArgs = newArgs.zip(newSignArgs).map {
            case (arg, argType) =>
              genInit(argType._type, arg, needPtr = argType._type.isInstanceOf[Struct])
          }

          val joinedArgs = calculatedArgs.zip(newSignArgs).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          }.mkString(", ")

          println(s"\tcall ${ptr.signature.realRet.name} ${ptr.irName}($joinedArgs)")
          if (needPtr) "%" + newVar
          else {
            val tmp = nextTmpVar()
            println(s"\t$tmp = load ${_type.name}, ${_type.name}* %$newVar")
            tmp
          }
        case _ =>
          val calculatedArgs = (args).zip(ptr.signature.args).map {
            case (arg, argType) =>
              genInit(argType._type, arg, needPtr = argType._type.isInstanceOf[Struct])
          }

          val joinedArgs = calculatedArgs.zip(ptr.signature.args).map {
            case (arg, argType) =>
              s"${if (argType._type.isInstanceOf[Struct]) argType._type.name + "*" else argType._type.name} $arg"
          }.mkString(", ")

          if (ptr.signature.ret == Scalar("void")) {
            println(s"\tcall ${ptr.signature.ret.name} ${ptr.irName}($joinedArgs)")
            return null
          }

          val tmp = nextTmpVar()
          println(s"\t$tmp = call ${ptr.signature.ret.name} ${ptr.irName}($joinedArgs)")
          if (needPtr) throw new Exception("not implemented in current ABI")
          else tmp
      }
  }

  def genStat(seq: Seq[Stat]): Unit =
    seq.foreach {
      case v: Var =>
        println(s"\t${v.irName} = alloca ${v._type.name}, align 4")
      case Store(access, init) =>
        val (res, _type) = evalAccess(access)
        val forStore = genInit(_type, init, needPtr = false)
        println(s"\tstore ${_type.name} $forStore, ${_type.name}* $res")
      case call: Call =>
        genInit(call.ptr.signature.ret, call)
      //      case VoidCall(ptr, args) =>
      //        val joinedArgs = joinCallArgs(ptr, args)
      //        println(s"\tcall ${ptr.signature.ret.name} ${ptr.irName}($joinedArgs)")
      case Cond(init, _if, _else) =>
        val condVar = genInit(Scalar("i1"), init, needPtr = false)
        val (ifLabel, elseLabel, endLabel) = (nextLabel, nextLabel, nextLabel)

        println(s"\tbr i1 $condVar, label %$ifLabel, label %$elseLabel")
        println(s"$ifLabel:")
        genStat(_if)
        println(s"\tbr label %$endLabel")
        println(s"$elseLabel:")
        genStat(_else)
        println(s"\tbr label %$endLabel")
        println(s"$endLabel:")
      case While(init, seq) =>
        val (beginLabel, bodyLabel, endLabel) = (nextLabel, nextLabel, nextLabel)

        println(s"\tbr label %$beginLabel")
        println(s"$beginLabel:")

        val condVar = genInit(Scalar("i1"), init, needPtr = false)
        println(s"\tbr i1 $condVar, label %$bodyLabel, label %$endLabel")
        println(s"$bodyLabel:")

        genStat(seq)

        println(s"\tbr label %$beginLabel")
        println(s"$endLabel:")

      case Ret(_type, init) =>
        val tmp = genInit(_type, init)
        println(s"\tret ${_type.name} $tmp")
      case RetVoid() =>
        println(s"\tret void")
    }

  def genFunction(fn: Fn): Unit = {
    val signature = fn.ptr.signature
    println(s"define ${signature.realRet.name} ${fn.ptr.irName}(${signature.irArgs.mkString(", ")}) {")

    fn.body match {
      case IrInline(ir) => println(s"$ir")
      case Block(seq) =>
        genStat(seq)
    }
    println("}")
  }

  def gen(module: Module): Unit = {
    module.structs.foreach { struct =>
      println(s"${struct.name} = type { ${struct.fields.map { f => f._type.name }.mkString(", ")} }")
    }
    module.functions.foreach { fn => tmpVars = 0; genFunction(fn) }
  }
}