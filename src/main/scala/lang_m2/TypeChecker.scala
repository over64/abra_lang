package lang_m2

import Ast0._
import lang_m2.Ast1.{GlobalFnPtr, Struct}

class TypeChecker {
  def error(info: AstInfo, msg: String) =
    s"at ${info.line}:${info.col + 1} ->\n\t$msg"

  def assertTypeDefined(typeHint: TypeHint, typeMap: Map[String, Type]): Unit = {
    typeHint match {
      case ScalarTypeHint(info, name) =>
        if (typeMap.get(name) == None) throw new Exception(error(info, "type with name $name not found"))
      case FnTypeHint(_, seq, ret) =>
        seq.foreach(th => assertTypeDefined(th.typeHint, typeMap))
        assertTypeDefined(ret, typeMap)
    }
  }

  def checkTypeMap(typeMap: Map[String, Type]) = {
    typeMap.foreach {
      case (name, _type) =>
        _type match {
          case FactorType(info, name, fields) =>
            fields.foreach { field => assertTypeDefined(field.typeHint, typeMap) }
          case _ =>
        }
    }
  }

  def mapTypeHintToLow(typeMap: Map[String, Type], typeHint: TypeHint): Ast1.Type =
    typeHint match {
      case ScalarTypeHint(_, typeName) =>
        typeMap(typeName) match {
          case ScalarType(_, _, llType) => Ast1.Scalar(llType)
          case FactorType(_, name, fields) => Ast1.Struct(name, fields.map { field =>
            Ast1.Field(field.name, mapTypeHintToLow(typeMap, field.typeHint))
          })
        }
      case FnTypeHint(_, seq, ret) =>
        Ast1.FnPointer(seq.map(arg => mapTypeHintToLow(typeMap, arg.typeHint)), mapTypeHintToLow(typeMap, ret))
    }

  def inferGlobalFnArgsType(fn: Fn): Seq[TypeHint] = {
    val args: Seq[TypeHint] = fn.typeHint match {
      case Some(th) =>
        fn.body match {
          case Block(_, args, _) =>
            th.seq.zip(args).foreach {
              case (protoHint, blockArg) =>
                blockArg.typeHint.map { th =>
                  if (th.name != protoHint.typeHint.name)
                    throw new Exception(error(th.info, s"expected arg of type ${protoHint.typeHint.name} has ${th.name}"))
                }
            }
          case _ =>
        }
        th.seq.map { field => field.typeHint }
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) => // все аргументы должны быть с типами
            args.map { arg =>
              arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
            }
        }
    }
  }

  def inferGlobalFnRetType(typeMap: Map[String, Type], fnMap: Map[String, Seq[Fn]], fn: Fn): TypeHint =
    fn.typeHint match {
      case Some(th) =>
        fn.retTypeHint match {
          case Some(rth) =>
            if (rth.name != th.name)
              throw new Exception(error(rth.info, s"expected ret of type ${th.name} has ${rth.name}"))
          case _ =>
        }
        th
      case None =>
        fn.retTypeHint match {
          case Some(rth) => rth
          case None =>
            fn.body match {
              case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
              case Block(info, _, seq) =>
                seq.lastOption.map {
                  case lInt(info, value) => ScalarTypeHint(info, "Int")
                  case Call(info, fnName, args) =>
                    val functions = fnMap.get(fnName).getOrElse(throw new Exception(error(info, s"could not find function with name $fnName")))
                    functions.find { fn =>
                      val infArgs = inferGlobalFnArgsType(fn)
                      val infRet = inferGlobalFnRetType(fn)
                    }

                }.getOrElse(ScalarTypeHint(info, "Unit"))
            }
        }
    }

  def evalExpression(typeMap: Map[String, Type],
                     fnMap: Map[String, Seq[Fn]],
                     varMap: Map[String, Type],
                     expectedType: Option[TypeHint],
                     expression: Expression): (TypeHint, Ast1.Init) = {
    expectedType.map { et =>
      if (typeMap.get(et.name) == None) throw new Exception(error(et.info, s"could not find type with name ${et.name}"))
    }

    expression match {
      case lInt(info, value) =>
        expectedType.map { et =>
          et match {
            case sth@ScalarTypeHint(info2, typeName) =>
              typeMap(typeName) match {
                case ScalarType(_, name, ll) =>
                  if (ll.matches("i\\d+") && ll != "i1") (sth, Ast1.lInt(value))
                  else throw new Exception(error(info2, s"cannot assign integer value to ${name}"))
                case factor: FactorType => throw new Exception(error(info2, s"cannot assign integer value to ${factor.name}"))
              }
            case fnType: FnTypeHint => throw new Exception(error(fnType.info, s"cannot assign integer value to ${fnType.name}"))
          }
        }.getOrElse {
          val integerType = typeMap.values.find {
            case ScalarType(_, name, llType) => llType.matches("i\\d+") && llType != "i1"
            case _ => false
          }
          integerType.map { it => (ScalarTypeHint(null, it.name), Ast1.lInt(value)) }
            .getOrElse(throw new Exception(error(info, "cannot find integer type for integer literal (type Int = llvm { i32 } for example)")))
        }
      case Call(info, fnName, args) =>
        val evaluatedArgs: Seq[(TypeHint, Ast1.Init)] = args.seq.map { arg =>
          evalExpression(typeMap, fnMap, varMap, None, arg)
        }

        val argTypes = evaluatedArgs.map(_._2)
        val functions = fnMap.get(fnName).getOrElse(throw new Exception(error(info, s"could not find function with name $fnName")))
        functions.find { fn =>
          fn.
        }

        (ScalarTypeHint(null, "Int"), Ast1.Call(null, evaluatedArgs.map(_._2)))
    }
  }

  def evalBlockExpr(typeMap: Map[String, Type],
                    fnMap: Map[String, Seq[Fn]],
                    varMap: Map[String, Type],
                    expressions: List[BlockExpression]): Seq[Ast1.Stat] =
    expressions match {
      case head :: tail =>
        head match {
          case v: Val =>
            val (typeHint, lowInit) = evalExpression(typeMap, fnMap, varMap, v.typeHint, v.init)
            val lowType = mapTypeHintToLow(typeMap, typeHint)
            Seq(
              Ast1.Var(v.name, lowType),
              Ast1.Store(Ast1.Access(v.name, lowType, Seq()), lowInit)
            ) ++ evalBlockExpr(typeMap, fnMap, varMap, tail)
          case store: Store => Seq() ++ evalBlockExpr(typeMap, fnMap, varMap, tail)
          case w: While => Seq() ++ evalBlockExpr(typeMap, fnMap, varMap, tail)
          case call: Call => Seq() ++ evalBlockExpr(typeMap, fnMap, varMap, tail)
          case access: Access => Seq() ++ evalBlockExpr(typeMap, fnMap, varMap, tail)
          case other@_ => println(s"drop $other"); evalBlockExpr(typeMap, fnMap, varMap, tail)
        }
      case Nil => Seq()
    }

  //  def inferGlobalFnType(typeMap: TypeMap, fn: Fn): FnTypeHint = {
  //    fn.body match {
  //      case inline: LlInline =>
  //        (fn.typeHint, fn.retTypeHint) match {
  //          case (None, None) =>
  //        }
  //    }
  //  }

  def fnToFnPtr(typeMap: Map[String, Type], fn: Fn): Ast1.FnPtr = {
    val args: Seq[Ast1.Field] = fn.typeHint match {
      case Some(th) =>
        fn.body match {
          case Block(_, args, _) =>
            th.seq.zip(args).foreach {
              case (protoHint, blockArg) =>
                blockArg.typeHint.map { th =>
                  if (th.name != protoHint.typeHint.name)
                    throw new Exception(error(th.info, s"expected arg of type ${protoHint.typeHint.name} has ${th.name}"))
                }
            }
          case _ =>
        }
        th.seq.map { field => Ast1.Field(field.name, mapTypeHintToLow(typeMap, field.typeHint)) }
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) => // все аргументы должны быть с типами
            args.map { arg =>
              val th = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
              Ast1.Field(arg.name, mapTypeHintToLow(typeMap, th))
            }
        }
    }
    val ret: Ast1.Type = fn.typeHint match {
      case Some(th) =>
        fn.retTypeHint match {
          case Some(rth) =>
            if (rth.name != th.name)
              throw new Exception(error(rth.info, s"expected ret of type ${th.name} has ${rth.name}"))
          case _ =>
        }
        mapTypeHintToLow(typeMap, th.ret)
      case None =>
        fn.retTypeHint match {
          case Some(rth) =>
            mapTypeHintToLow(typeMap, rth)
          case None => throw new Exception(error(fn.info, "expected explicit return type definition"))
        }
    }
    val lowFnName = fn.typeHint match {
      case Some(th) => th.seq.headOption.map {
        first => if (first.name == "self") fn.name + s"_for_${first.typeHint.name}" else fn.name
      }.getOrElse(fn.name)
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) =>
            args.headOption.map { arg =>
              val _type = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
              if (arg.name == "self") fn.name + s"_for_${_type.name}" else fn.name
            }.getOrElse(fn.name)
        }
    }

    GlobalFnPtr(lowFnName, Ast1.Signature(args, ret))
  }

  def transform(src: Module): Ast1.Module = {
    val typeDefs = src.seq.filter { el => el.isInstanceOf[Type] }.map(_.asInstanceOf[Type])
    val typeMap = typeDefs.map(td => (td.name, td)).toMap
    checkTypeMap(typeMap)

    val structs = typeMap.values.filter(_.isInstanceOf[FactorType]).map { td =>
      val factorType = td.asInstanceOf[FactorType]
      mapTypeHintToLow(typeMap, ScalarTypeHint(null, factorType.name)).asInstanceOf[Struct]
    }.toSeq

    val functions = src.seq.filter { el => el.isInstanceOf[Fn] }.map(_.asInstanceOf[Fn])
    val fnMap = functions.groupBy(fn => fn.name)

    Ast1.Module(structs, functions.map { fn =>
      val body = fn.body match {
        case LlInline(_, value) => Ast1.IrInline(value)
        case Block(info, args, seq) =>
          Ast1.Block(evalBlockExpr(typeMap, fnMap, varMap = Map(), seq.toList))
      }
      Ast1.Fn(fnToFnPtr(typeMap, fn), body)
    })
  }
}
