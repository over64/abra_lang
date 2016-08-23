package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 29.07.16.
  */
trait TypeCheckerUtil {
  def error(info: AstInfo, msg: String) = {
    if (info != null)
      s"at ${info.line}:${info.col + 1} ->\n\t$msg"
    else
      s"at __no sources__ ->\n\t$msg"
  }

  case class InferedFn(name: String, args: Seq[TypeHint], ret: TypeHint, lowFn: Ast1.Fn, val th: FnTypeHint)

  case class InferedExp(th: TypeHint, stats: Seq[Ast1.Stat], init: Option[Ast1.Init])

  case class ValInfo(mutable: Boolean, isParam: Boolean, th: TypeHint)

  case class InferContext(typeMap: Map[String, Type],
                          fnMap: Map[String, Seq[Fn]],
                          varMap: Map[String, ValInfo] = Map(),
                          evaluatedMap: Map[String, Seq[InferedFn]] = Map(),
                          inferStack: Seq[(String, Option[TypeHint])] = Seq()) {
    def addVar(name: String, vi: ValInfo) =
      InferContext(typeMap, fnMap, varMap ++ Map(name -> vi), evaluatedMap, inferStack)

    def stackPush(name: String, firstArgType: Option[TypeHint]) =
      InferContext(typeMap, fnMap, varMap, evaluatedMap, inferStack :+ (name, firstArgType))

    def stackPop =
      InferContext(typeMap, fnMap, varMap, evaluatedMap, inferStack.dropRight(1))

    def addInferedFn(name: String, inferedFn: InferedFn) = {
      val newMap =
        if (!evaluatedMap.contains(name))
          evaluatedMap + (name -> Seq(inferedFn))
        else
          evaluatedMap + (name -> (evaluatedMap(name) :+ inferedFn))

      InferContext(typeMap, fnMap, varMap, newMap, inferStack)
    }

    def isInfered(fnName: String, firstArgType: Option[TypeHint]): Boolean =
      evaluatedMap.get(fnName).map { infFns =>
        infFns.find { infFn => infFn.args.headOption == firstArgType }.map(infFn => true).getOrElse(false)
      }.getOrElse(false)

    def findInfered(fnName: String, firstArgType: Option[TypeHint]): Option[InferedFn] =
      evaluatedMap.get(fnName).map { infFns =>
        infFns.find { infFn => infFn.args.headOption == firstArgType }
      }.getOrElse(None)

    def isInfering(fnName: String, firstArgType: Option[TypeHint]): Boolean = {
      inferStack.find {
        case (_fnName, oth) => _fnName == fnName && (
          (oth, firstArgType) match {
            case (None, None) => true
            case (Some(x), None) => false
            case (None, Some(x)) => false
            case (Some(x), Some(y)) => x.name == y.name
          })
      }.map { x => true }.getOrElse(false)
    }

  }

  class CancelEval extends Exception

  val thUnit = ScalarTypeHint(null, "Unit")
  val thInt = ScalarTypeHint(null, "Int")
  val thFloat = ScalarTypeHint(null, "Float")
  val thBool = ScalarTypeHint(null, "Boolean")
  val thString = ScalarTypeHint(null, "String")

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

  def mapTypeHintToLow(ctx: InferContext, typeHint: TypeHint): Ast1.Type =
    typeHint match {
      case ScalarTypeHint(_, typeName) =>
        ctx.typeMap(typeName) match {
          case ScalarType(_, _, llType) => Ast1.Scalar(llType)
          case FactorType(_, name, fields) => Ast1.Struct(name, fields.map { field =>
            Ast1.Field(field.name, mapTypeHintToLow(ctx, field.typeHint))
          })
        }
      case FnTypeHint(_, seq, ret) =>
        Ast1.FnPointer(seq.map(arg => Ast1.Field(arg.name, mapTypeHintToLow(ctx, arg.typeHint))), mapTypeHintToLow(ctx, ret))
    }

  def inferFnArgs(fn: Fn) =
    fn.typeHint match {
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
            th.seq.zip(args).map {
              case (th, arg) => FnTypeHintField(null, arg.name, th.typeHint)
            }
          case _ => th.seq
        }
      case None =>
        fn.body match {
          case inline: LlInline => throw new Exception(error(fn.info, "expected explicit function type definition"))
          case Block(info, args, _) => // все аргументы должны быть с типами
            args.map {
              arg =>
                val th = arg.typeHint.getOrElse(throw new Exception(error(arg.info, "expected explicit argument type definition")))
                FnTypeHintField(null, arg.name, th)
            }
        }
    }

  def assertTypeEquals(info: AstInfo, t1: TypeHint, t2: TypeHint) =
    if (t1.name != t2.name) throw new Exception(error(info, s"expected expression of type ${t1.name} has ${t2.name}"))

  var annoVars = 0
  var anonFns = 0

  def nextAnonVar = {
    annoVars += 1
    s"anon$annoVars"
  }

  def nextAnonFn = {
    annoVars += 1
    s"anonFn$annoVars"
  }
}
