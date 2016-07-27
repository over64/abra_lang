package lang_new

import lang_new.Ast1._


class TypeChecker(root: Module) {
  val llReturnable = Seq("i32")
  val llPassable = Seq("void", "i32")

  def queryForType(name: TypeIdent): Option[TypeDef] =
    root.seq.find {
      case t: TypeDef => t.name == name
      case _ => false
    }.map { td => td.asInstanceOf[TypeDef] }

  def escapeFunctionName(fnName: FnIdent) =
    "@" + fnName.name
      .replace("+", "$plus")
      .replace("-", "$minus")
      .replace("/", "$div")
      .replace("*", "$mul")
      .replace("::", "$cons")

  def inferLlvmTypeName(typeDef: TypeDef, pointer: Boolean = false) =
    typeDef match {
      case abraType: AbraTypeDef =>
        s"%struct.${abraType.name}${if (pointer) "*" else ""}"
      case ll: LlvmTypeDef => ll.body
    }


  // (retType, argAdditional)
  // если ret is None => ret = void
  // если ret - llvmReturnable (void, i32) => сигнатура не меняется
  // иначе ret* - первый параметр функции
  def inferFunctionRet(fn: Fn): (String, Option[String]) = {
    fn.ret match {
      case None => ("void", None)
      case Some(typeName) =>
        val retType = queryForType(typeName).getOrElse(throw new Exception(s"type with name $typeName not found"))
        retType match {
          case LlvmTypeDef(_, _, llvmTypeName) =>
            if (llvmTypeName == "void") ("void", None)
            else if (llReturnable.contains(llvmTypeName)) (llvmTypeName, None)
            else ("void", Some(llvmTypeName))
          case a: AbraTypeDef => ("void", Some(inferLlvmTypeName(a, true)))
        }
    }
  }

  // если параметр llvmPassable (void, int) => параметр иначе параметр*
  def inferFunctionArgs(fn: Fn): Seq[Ast1.LlField] = {
    fn.params.map { param =>
      val paramType = queryForType(param.ttype).getOrElse(throw new Exception(s"type with name ${param.ttype} not found"))
      paramType match {
        case LlvmTypeDef(_, _, llvmTypeName) =>
          if (llPassable.contains(llvmTypeName)) Ast1.LlField("%" + param.name.name, llvmTypeName)
          else Ast1.LlField("%" + param.name.name, llvmTypeName + "*")
        case abra: AbraTypeDef => Ast1.LlField("%" + param.name.name, inferLlvmTypeName(abra, true))
      }
    }
  }

  def inferFnArgsAndRet(fn: Fn): (String, Seq[Ast1.LlField]) = {
    val (llFnRet, additionalArg) = inferFunctionRet(fn)
    val args = inferFunctionArgs(fn)
    val inferedArgs = additionalArg match {
      case None => args
      case Some(arg) => Seq(Ast1.LlField("%ret", arg)) ++ args
    }
    (llFnRet, inferedArgs)
  }

  def getFieldWithIndex(ttype: AbraTypeDef, fieldName: String): (Param, Int) = {
    val idx = ttype.fields.map { f => f.name.name }.indexOf(fieldName)
    if (idx < 0)
      throw new RuntimeException(s"field with name $fieldName of type ${ttype.name} not found")
    (ttype.fields(idx), idx)
  }

  var llTmpIdSeq = 0

  def nextLlTmpId = {
    llTmpIdSeq += 1
    s"%$llTmpIdSeq"
  }

  def findVarType(varIdent: VarIdent, symTab: scala.collection.mutable.Map[VarIdent, TypeIdent]) =
    symTab.map { case (k, v) => (k.toString, v) }.getOrElse(varIdent.name, throw new Exception(s"var with name $varIdent not found"))

  def evalAccess(access: Access, typeMap: Map[TypeIdent, TypeDef], symTab: scala.collection.mutable.Map[VarIdent, TypeIdent])
  : (TypeDef, Ast1.LLlGep) = {
    val first = access.seq.head.asInstanceOf[VarIdent]
    val firstTypeIdent = findVarType(first, symTab)
    val firstType = typeMap.getOrElse(firstTypeIdent, throw new RuntimeException(s"type with name $firstTypeIdent not found"))


    val (lastType, gepFields) = access.seq.drop(1).foldLeft((firstType, List[Int]())) {
      case ((ttype, reduced), f) =>
        val abraType = ttype match {
          case l: LlvmTypeDef => throw new RuntimeException(s"Cannot access field $f of type ${ttype.name}")
          case a: AbraTypeDef => a
        }

        val (param, idx) = getFieldWithIndex(abraType, f.asInstanceOf[VarIdent].name)
        val newType = typeMap.getOrElse(param.ttype, throw new RuntimeException(s"type with name $firstTypeIdent not found"))
        (newType, reduced ++ List(idx))
    }

    (lastType, Ast1.LLlGep(nextLlTmpId, inferLlvmTypeName(lastType), "%" + first.name, inferLlvmTypeName(firstType), Seq(0) ++ gepFields))
  }

  // вызывающий должен запушить аргумент!
  // где делать arg inference?
  def evalFnCall(call: FnCall, typeMap: Map[TypeIdent, TypeDef], symTab: scala.collection.mutable.Map[VarIdent, TypeIdent], fnTab: Map[FnIdent, Fn]): LlCall = {
    val fnToCall = fnTab.getOrElse(call.fn, throw new Exception(s"function with name ${call.fn} not found"))

    val argsAndBefore: Seq[(Ast1.LlCallArg, Seq[Ast1.LlFnBody])] =
      fnToCall.params.zip(call.args).map {
        case (param, callArg) =>
          val paramType = typeMap.getOrElse(param.ttype, throw new Exception(s"type with name ${param.ttype} not found"))
          callArg match {
            case Uint(_, i) =>
              paramType match {
                case LlvmTypeDef(_, _, body) if body == "i32" => (Ast1.LlCallArg("i32", i.toString), Seq())
              }
            case vi@VarIdent(_, name) =>
              println(symTab)
              val varTypeIdent = findVarType(vi, symTab)
              val varType = typeMap.getOrElse(varTypeIdent, throw new Exception(s"type with name $name not found"))
              if (varType != paramType) throw new Exception("expected another arg type")
              varType match {
                case LlvmTypeDef(_, _, body) if llPassable.contains(body) =>
                  val loaded = nextLlTmpId
                  (Ast1.LlCallArg(body, loaded), Seq(LlLoad(loaded, body, "%" + name)))
                case _ => (Ast1.LlCallArg(inferLlvmTypeName(varType), s"%$name"), Seq())
              }
            case acc@Access(_, seq) =>
              val (ttype, gep) = evalAccess(acc, typeMap, symTab)
              if (ttype != paramType) throw new Exception("expected another arg type")
              ttype match {
                case LlvmTypeDef(_, _, body) if llReturnable.contains(body) =>
                  //                  val alloca = LlAlloca(nextLlTmpId, body)
                  //                  val store = LlStore(body, gep.resultName, alloca.resultName)
                  val load = LlLoad(nextLlTmpId, body, gep.resultName)
                  (Ast1.LlCallArg(inferLlvmTypeName(ttype), load.resultName), Seq(gep, load))
                case _ => (Ast1.LlCallArg(inferLlvmTypeName(ttype), gep.resultName), Seq(gep))

              }
            case fnCall@FnCall(_, fn, args) =>
              val llCall = evalFnCall(fnCall, typeMap, symTab, fnTab)
              (Ast1.LlCallArg(llCall.retType, llCall.resultName), Seq(llCall))
          }
      }
    val args = argsAndBefore.map(_._1)
    val before = argsAndBefore.flatMap(_._2)

    val (retType, additionalArg) = inferFunctionRet(fnToCall)
    val (inferedArgs, inferedBefore) = additionalArg match {
      case None => (args, before)
      case Some(argType) =>
        val alloca = LlAlloca(nextLlTmpId, argType.replace("*", ""))
        (Seq(LlCallArg(argType, alloca.resultName)) ++ args, before ++ Seq(alloca))
    }

    LlCall(
      if (retType == "void")
        if (additionalArg.isDefined) inferedArgs.head.value
        else ""
      else nextLlTmpId,
      escapeFunctionName(fnToCall.name), retType, inferedBefore, inferedArgs)
  }

  def assertTypeDefined(typeName: TypeIdent, typeMap: Map[TypeIdent, TypeDef]): TypeDef =
    typeMap.getOrElse(typeName, throw new Exception(s"type with name $typeName not found"))

  def assertTypesEquals(left: TypeIdent, right: TypeIdent): Unit = {

  }

  def assertTypesEquals(left: TypeIdent, right: TypeDef): Unit = {

  }

  def assertLlTypeEquals(left: TypeIdent, llvm: String): Unit = {

  }

  def evalFnBody(body: Seq[FnBody], typeMap: Map[TypeIdent, TypeDef], fnTab: Map[FnIdent, Fn])
  : Seq[Ast1.LlFnBody] = {
    val symTab = scala.collection.mutable.HashMap.empty[VarIdent, TypeIdent]

    body.flatMap {
      case fnCall: FnCall => Seq(evalFnCall(fnCall, typeMap, symTab, fnTab))
      case Uint(_, i) => Seq(LlIConst(i))
      case a: Access => Seq(evalAccess(a, typeMap, symTab)._2)
      case vi: VarIdent => Seq(evalAccess(Access(0, Seq(vi)), typeMap, symTab)._2)
      case vd: ValDef =>
        val varType = assertTypeDefined(vd.ttype, typeMap)
        val init = vd.init match {
          case Uint(_, i) =>
            assertLlTypeEquals(vd.ttype, "i32")
            val alloca = LlAlloca("%" + vd.name, inferLlvmTypeName(varType))
            Seq(alloca, LlStore(inferLlvmTypeName(varType), i.toString, alloca.resultName))
          case call: FnCall =>
            val fnToCall = fnTab.getOrElse(call.fn, throw new Exception(s"function with name ${call.fn} not found"))
            fnToCall.ret match {
              case None => s"expected ${vd.ttype} has void"
              case Some(ret) => assertTypesEquals(vd.ttype, ret)
            }
            val evaluatedCall = evalFnCall(call, typeMap, symTab, fnTab)

            if (llReturnable.contains(evaluatedCall.retType)) {
              val alloca = LlAlloca("%" + vd.name, inferLlvmTypeName(varType))
              val store = LlStore(inferLlvmTypeName(varType), evaluatedCall.resultName, alloca.resultName)
              Seq(alloca, evaluatedCall, store)
            } else {
              // So hot, bro!
              llTmpIdSeq -= 1
              val alloca = LlAlloca("%" + vd.name, inferLlvmTypeName(varType))
              val before = evaluatedCall.before.dropRight(1).toSeq ++ Seq(alloca)
              val args = Seq(LlCallArg(inferLlvmTypeName(varType, true), alloca.resultName)) ++ evaluatedCall.args.drop(1)
              Seq(LlCall(evaluatedCall.resultName, evaluatedCall.fnName, evaluatedCall.retType, before, args))
            }

          //          case vi@VarIdent(_, name) =>
          //            val varType = symTab.getOrElse(vi, throw new Exception(s"var with name $name not found"))
          //            assertTypesEquals(vd.ttype, varType)
          //          case a: Access =>
        }
        symTab.put(vd.name, vd.ttype)
        init
    }
  }

  def checkAndTransform(): Ast1.LlModule = {
    val typeDefinitions = root.seq.filter(_.isInstanceOf[TypeDef]).map(_.asInstanceOf[TypeDef])
    val typeMap = Map[TypeIdent, TypeDef](typeDefinitions.map { td =>
      (td.name, td)
    }: _*)

    val fnDefinitions = root.seq.filter(_.isInstanceOf[Fn]).map(_.asInstanceOf[Fn])
    val fnTab = Map[FnIdent, Fn](fnDefinitions.map { fn =>
      (fn.name, fn)
    }: _*)

    Ast1.LlModule(root.seq
      .filter { x =>
        !x.isInstanceOf[LlvmTypeDef]
      }
      .map {
        case AbraTypeDef(_, name, fields) =>
          Ast1.LlStructType(s"%struct.$name", fields.map { param =>
            val paramType = queryForType(param.ttype)
            paramType match {
              case None => throw new Exception(s"Type with name ${param.ttype} not found")
              case Some(AbraTypeDef(_, name, _)) => Ast1.LlField(param.name.name, s"%struct.$name")
              case Some(LlvmTypeDef(_, _, body)) => Ast1.LlField(param.name.name, body)
            }
          })
        case fn@LlvmFn(_, name, params, body, ret) =>
          val (llFnRet, inferedArgs) = inferFnArgsAndRet(fn)
          Ast1.LlFn(escapeFunctionName(name), llFnRet, inferedArgs, Seq(Ast1.LlInlineStatement(body)))
        case fn@AbraFn(_, name, params, body, ret) =>
          //check for return value
          //          ret.map { ret =>
          //            body.seq.lastOption.map {
          //              case fnCall: FnCall =>
          //                val fnToCall = fnTab.getOrElse(fnCall.fn, throw new Exception(s"function with name ${fnCall.fn} found"))
          //                fnToCall.ret match {
          //                  case None => throw new Exception(s"in function $name: expected return value - has none")
          //                  case Some(x) => if (x != ret) throw new Exception(s"in function $name: expected return value of $ret - has $x")
          //                }
          //              case vi: VarIdent =>
          //                symT
          //
          //            }
          //          }

          val (llFnRet, inferedArgs) = inferFnArgsAndRet(fn)
          val llBody = evalFnBody(body, typeMap, fnTab)
          val fixedBody = llBody.lastOption match {
            case None => llBody
            case Some(last) =>
              last match {
                case LLlGep(resName, retType, _, _, _) =>
                  llBody ++ Seq(LlLoad(nextLlTmpId, retType, resName))
                case _ => llBody
              }
          }
          Ast1.LlFn(escapeFunctionName(name), llFnRet, inferedArgs, fixedBody)
      })
  }
}
