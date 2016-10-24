package lang_m2

import java.math.BigInteger

import grammar2.M2Parser._
import grammar2.M2ParserVisitor
import org.antlr.v4.runtime.{ParserRuleContext, Token}
import org.antlr.v4.runtime.tree.{AbstractParseTreeVisitor, TerminalNode}
import Ast0._

import collection.JavaConversions._
import scala.collection.mutable.HashMap

class Visitor(fname: String, _package: String) extends AbstractParseTreeVisitor[ParseNode] with M2ParserVisitor[ParseNode] {
  val sourceMap = new SourceMap()
  val importedModules = new HashMap[String, String]()
  val importedTypes = new HashMap[String, String]()

  def emit[T <: ParseNode](token: Token, node: T): T = {
    sourceMap.add(node, new AstInfo(fname, token.getLine, token.getCharPositionInLine))
    node
  }

  def emit[T <: ParseNode](ctx: ParserRuleContext, node: T): T = emit(ctx.getStart, node)

  override def visitLiteral(ctx: LiteralContext): Literal = {
    val token = {
      var terminal: TerminalNode = null

      if (terminal == null)
        terminal = ctx.BooleanLiteral()
      if (terminal == null)
        terminal = ctx.FloatLiteral()
      if (terminal == null)
        terminal = ctx.IntLiteral()
      if (terminal == null)
        terminal = ctx.HexLiteral()
      if (terminal == null)
        terminal = ctx.StringLiteral()
      if (terminal == null)
        terminal = ctx.Id()
      if (terminal == null)
        terminal = ctx.getToken(SELF, 0)

      terminal.getSymbol
    }

    emit(token,
      token.getType match {
        case Id => lId(token.getText)
        case StringLiteral => // strip embracing commas
          val (text, length) = (token.getText, token.getText.length)
          lString(text.substring(1, length - 1))
        case FloatLiteral => lFloat(token.getText)
        case IntLiteral => lInt(token.getText)
        case HexLiteral => lInt(new BigInteger(token.getText.stripPrefix("0x"), 16).toString())
        case BooleanLiteral => lBoolean(token.getText)
        case SELF => lId(token.getText)
      })
  }

  override def visitRealdId(ctx: RealdIdContext): lId = {
    val realId = if (ctx.Id() != null) ctx.Id()
    else ctx.getToken(SELF, 0)
    emit(realId.getSymbol, lId(realId.getSymbol.getText))
  }

  val predefTypes = Seq("Unit", "Boolean", "Int", "Float", "String")

  override def visitScalarTypeHint(ctx: ScalarTypeHintContext): ScalarTypeHint = {
    val typeName = ctx.typeName.getText
    val pkg =
      if (ctx.modVar != null)
        importedModules(ctx.modVar.getText)
      else if (predefTypes.contains(typeName)) ""
      else if (importedTypes.contains(typeName)) importedTypes(typeName)
      else _package

    emit(ctx, ScalarTypeHint(typeName, pkg))
  }

  override def visitFnTypeHintField(ctx: FnTypeHintFieldContext): FnTypeHintField =
    emit(ctx, FnTypeHintField(
      if (ctx.Id != null) ctx.Id().getText else ctx.getToken(SELF, 0).getText,
      visitTypeHint(ctx.typeHint())))


  override def visitFnTypeHint(ctx: FnTypeHintContext): FnTypeHint =
    emit(ctx, FnTypeHint(
      ctx.fnTypeHintField().map { f => visitFnTypeHintField(f) },
      visitTypeHint(ctx.typeHint())))

  override def visitTypeHint(ctx: TypeHintContext): TypeHint =
    if (ctx.scalarTypeHint() != null)
      visitScalarTypeHint(ctx.scalarTypeHint())
    else visitFnTypeHint(ctx.fnTypeHint())


  val re = """llvm\s*\{\s*(.*)\s*\}\s*""".r

  override def visitScalarType(ctx: ScalarTypeContext): ScalarDecl = {
    val llTypeName = re.replaceFirstIn(ctx.LlLiteral().getText, "$1").trim
    emit(ctx, ScalarDecl(ctx.Id().getText, llTypeName))
  }

  override def visitTypeField(ctx: TypeFieldContext): FieldDecl =
    emit(ctx, FieldDecl(ctx.getToken(SELF, 0) != null, ctx.Id().getText, visitTypeHint(ctx.typeHint())))

  override def visitFactorType(ctx: FactorTypeContext): FactorDecl =
    emit(ctx, FactorDecl(
      ctx.Id().getText,
      ctx.typeField().map { f => visitTypeField(f) }
    ))

  override def visitType(ctx: TypeContext): TypeDecl =
    if (ctx.scalarType() != null)
      visitScalarType(ctx.scalarType())
    else visitFactorType(ctx.factorType())


  override def visitTuple(ctx: TupleContext): Tuple =
    emit(ctx, Tuple(ctx.expression().map { f => visitExpr(f) }))

  override def visitFnArg(ctx: FnArgContext): FnArg =
    emit(ctx, FnArg(
      if (ctx.Id != null) ctx.Id().getText else ctx.getToken(SELF, 0).getText,
      Option(ctx.typeHint()).map(th => visitTypeHint(th))))

  override def visitBlock(ctx: BlockContext): Block =
    emit(ctx, Block(
      ctx.fnArg().map(fa => visitFnArg(fa)),
      ctx.blockBody().map { b => visitBlockBody(b) }
    ))

  override def visitBlockBody(ctx: BlockBodyContext): BlockExpression =
    if (ctx.expression() != null)
      visitExpr(ctx.expression())
    else if (ctx.store() != null)
      visitStore(ctx.store())
    else
      visitVariable(ctx.variable())

  override def visitLambdaBlock(ctx: LambdaBlockContext): Block = {
    val args =
      if (ctx.fnArg() != null) ctx.fnArg().map { fa => visitFnArg(fa) }
      else Seq()

    val body =
      if (ctx.expression() != null) ctx.expression().accept(this).asInstanceOf[Expression]
      else visitStore(ctx.store())

    emit(ctx, Block(args, Seq(body)))
  }

  override def visitVariable(ctx: VariableContext): Val =
    emit(ctx, Val(
      if (ctx.valVar.getText == "val") false else true,
      ctx.Id().getText,
      Option(ctx.typeHint()).map(th => visitTypeHint(th)),
      ctx.expression().accept(this).asInstanceOf[Expression]
    ))

  override def visitStore(ctx: StoreContext): BlockExpression = {
    val expr = ctx.expression().accept(this).asInstanceOf[Expression]

    if (ctx.tuple() != null) {
      val indecies = visitTuple(ctx.tuple()).seq
      val to: Expression = ctx.realdId.drop(1).headOption.map { second =>
        val firstProp = Prop(visitRealdId(ctx.realdId.head), visitRealdId(second))
        ctx.realdId.drop(2).foldLeft(firstProp) {
          case (prop, id) => Prop(prop, visitRealdId(id))
        }
      }.getOrElse(visitRealdId(ctx.realdId.head))

      emit(ctx, SelfCall("set", to, indecies :+ expr))
    } else
      emit(ctx, Store(ctx.realdId().map(id => visitRealdId(id)), expr))
  }

  def visitExpr(ctx: ExpressionContext) = ctx.accept(this).asInstanceOf[Expression]

  override def visitExprLiteral(ctx: ExprLiteralContext): Literal = visitLiteral(ctx.literal())

  override def visitExprSelfCall(ctx: ExprSelfCallContext): ParseNode = {
    visitExpr(ctx.expression()) match {
      case id: lId if importedModules.contains(id.value) =>
        val pkg = importedModules(id.value)
        emit(ctx, ModCall(pkg, ctx.op.getText, visitTuple(ctx.tuple()).seq))
      case expr@_ => emit(ctx, SelfCall(ctx.op.getText, expr, visitTuple(ctx.tuple()).seq))
    }
  }


  override def visitExprApply(ctx: ExprApplyContext): Expression = {
    val args = visitTuple(ctx.tuple()).seq
    val firstArg = visitExpr(ctx.expression())

    val res = firstArg match {
      case lId(id) => Call(fnName = id, args)
      case expr@_ =>
        if (args.isEmpty) ApplyCall(firstArg)
        else GetCall(firstArg, args)
    }

    emit(ctx, res)
  }

  override def visitExprInfixCall(ctx: ExprInfixCallContext): Expression = {
    ctx.op.getText match {
      case "&&" => emit(ctx, BoolAnd(visitExpr(ctx.expression(0)), visitExpr(ctx.expression(1))))
      case "||" => emit(ctx, BoolOr(visitExpr(ctx.expression(0)), visitExpr(ctx.expression(1))))
      case _ => emit(ctx, SelfCall(ctx.op.getText, visitExpr(ctx.expression(0)), Seq(visitExpr(ctx.expression(1)))))
    }
  }

  override def visitExprParen(ctx: ExprParenContext): Expression = visitExpr(ctx.expression())

  override def visitExprTuple(ctx: ExprTupleContext): Tuple = visitTuple(ctx.tuple())

  override def visitExprUnaryCall(ctx: ExprUnaryCallContext): Expression =
    emit(ctx, SelfCall(ctx.op.getText,
      visitExpr(ctx.expression()), Seq()
    ))

  override def visitExprBlock(ctx: ExprBlockContext): Block = visitBlock(ctx.block())

  override def visitExprLambda(ctx: ExprLambdaContext): Block = visitLambdaBlock(ctx.lambdaBlock())

  override def visitIf_stat(ctx: If_statContext): BlockExpression =
    if (ctx.expression() != null) visitExpr(ctx.expression())
    else visitStore(ctx.store())

  override def visitExprIfElse(ctx: ExprIfElseContext): Cond =
    emit(ctx, Cond(
      ifCond = ctx.cond.accept(this).asInstanceOf[Expression],
      _then = (ctx.then_stat, ctx.then_block) match {
        case (stat, null) => emit(stat.getStart, Block(Seq(), Seq(visitIf_stat(stat))))
        case (null, block) => visitBlock(block)
      },
      _else = (ctx.else_stat, ctx.else_block) match {
        case (null, null) => None
        case (stat, null) => Some(emit(stat.getStart, Block(Seq(), Seq(visitIf_stat(stat)))))
        case (null, block) => Some(visitBlock(block))
      }
    ))

  override def visitExprWhile(ctx: ExprWhileContext): While =
    emit(ctx, While(
      cond = ctx.cond.accept(this).asInstanceOf[Expression],
      _then = visitBlock(ctx.then_block)
    ))

  override def visitExprProp(ctx: ExprPropContext): Expression =
    ctx.expression().accept(this).asInstanceOf[Expression] match {
      case lId(value) if importedModules.contains(value) =>
        emit(ctx, ModCall(importedModules(value), ctx.op.getText, Seq()))
      case expr@_ =>
        emit(ctx, Prop(expr, emit(ctx.op, lId(ctx.op.getText))))
    }

  override def visitFunction(ctx: FunctionContext): Fn = {
    val (block, retType) =
      if (ctx.expression() != null)
        (emit(ctx.expression(), Block(Seq(), Seq(visitExpr(ctx.expression())))), None)
      else if (ctx.lambdaBlock() != null)
        (visitLambdaBlock(ctx.lambdaBlock()), None)
      else {
        val body: FnBody =
          if (ctx.block() != null)
            visitBlock(ctx.block())
          else
            emit(ctx.LlLiteral().getSymbol, LlInline(
              ctx.LlLiteral().getText.replace("llvm", "").replace("{", "").replace("}", ""))
            )
        val retType = ctx.typeHint() match {
          case null => None
          case th => Some(visitTypeHint(th))
        }
        (body, retType)
      }

    val typeHint = ctx.fnTypeHint() match {
      case null => None
      case th => Some(visitFnTypeHint(th))
    }

    emit(ctx, Fn(ctx.name.getText, typeHint, block, retType))
  }

  override def visitImport_(ctx: Import_Context): Import = {
    val pkgSeq = ctx.pkgName.map { id => emit(id, lId(id.getText)) }
    val fullModName = pkgSeq.map(_.value).mkString("", ".", ".")
    importedModules.put(pkgSeq.last.value, fullModName)
    ctx.tid.map { importedType =>
      importedTypes += ((importedType.getText, fullModName))
    }
    emit(ctx, Import(pkgSeq))
  }

  override def visitLevel1(ctx: Level1Context): Level1Declaration =
    if (ctx.`type`() != null)
      visitType(ctx.`type`())
    else visitFunction(ctx.function())

  override def visitModule(ctx: ModuleContext): Module = {
    val imports = ctx.import_.map { imp => visitImport_(imp) }

    val all = ctx.level1().map { l1 => visitLevel1(l1) }
    val types = all.filter(_.isInstanceOf[TypeDecl]).map(_.asInstanceOf[TypeDecl])
    val functions = all.filter(_.isInstanceOf[Fn]).map(_.asInstanceOf[Fn])

    emit(ctx, Module(_package, imports, types, functions))
  }
}