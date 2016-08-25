package lang_m2

import lang_m2.Ast0._

/**
  * Created by over on 29.07.16.
  */
sealed trait TypeCheckResult
case class TypeCheckSuccess(module: Ast1.Module) extends TypeCheckResult
case class TypeCheckFail(at: AstInfo, error: CompileError) extends TypeCheckResult

trait TypeCheckerUtil {
  class CompileEx(val node: ParseNode, val error: CompileError) extends Exception

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

  val thUnit = ScalarTypeHint("Unit")
  val thInt = ScalarTypeHint("Int")
  val thFloat = ScalarTypeHint("Float")
  val thBool = ScalarTypeHint("Boolean")
  val thString = ScalarTypeHint("String")

  def assertTypeDefined(typeHint: TypeHint, typeMap: Map[String, Type]): Unit = {
    typeHint match {
      case self@ScalarTypeHint(name) =>
        if (typeMap.get(name) == None) throw new CompileEx(self, CE.TypeNotFound(name))
      case FnTypeHint(seq, ret) =>
        seq.foreach(th => assertTypeDefined(th.typeHint, typeMap))
        assertTypeDefined(ret, typeMap)
    }
  }

  def assertTypeEquals(node: ParseNode, expected: TypeHint, has: TypeHint): Unit =
    if (expected != has) throw new CompileEx(node, CE.ExprTypeMismatch(expected, has))

  def checkTypeMap(typeMap: Map[String, Type]) = {
    typeMap.foreach {
      case (name, _type) =>
        _type match {
          case FactorType(name, fields) =>
            fields.foreach { field => assertTypeDefined(field.typeHint, typeMap) }
          case _ =>
        }
    }
  }

  def mapTypeHintToLow(ctx: InferContext, typeHint: TypeHint): Ast1.Type =
    typeHint match {
      case ScalarTypeHint(typeName) =>
        ctx.typeMap(typeName) match {
          case ScalarType(_, llType) => Ast1.Scalar(llType)
          case FactorType(name, fields) => Ast1.Struct(name, fields.map { field =>
            Ast1.Field(field.name, mapTypeHintToLow(ctx, field.typeHint))
          })
        }
      case FnTypeHint(seq, ret) =>
        Ast1.FnPointer(seq.map(arg => Ast1.Field(arg.name, mapTypeHintToLow(ctx, arg.typeHint))), mapTypeHintToLow(ctx, ret))
    }

  def inferFnArgs(fn: Fn) =
    fn.typeHint match {
      case Some(th) =>
        fn.body match {
          case Block(args, _) =>
            th.seq.zip(args).foreach {
              case (protoHint, blockArg) =>
                blockArg.typeHint.map { th =>
                  if (th.name != protoHint.typeHint.name)
                    throw new CompileEx(th, CE.ExprTypeMismatch(protoHint.typeHint, th))
                }
            }
            th.seq.zip(args).map {
              case (th, arg) => FnTypeHintField(arg.name, th.typeHint)
            }
          case _ => th.seq
        }
      case None =>
        fn.body match {
          case inline: LlInline => throw new CompileEx(fn, CE.NeedExplicitTypeDefinition())
          case Block(args, _) => // все аргументы должны быть с типами
            args.map {
              arg =>
                val th = arg.typeHint.getOrElse(throw new CompileEx(fn, CE.NeedExplicitTypeDefinition()))
                FnTypeHintField(arg.name, th)
            }
        }
    }

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
