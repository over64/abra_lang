package lang_m2

import grammar2.M2Parser._
import grammar2.M2ParserVisitor
import org.antlr.v4.runtime.Token
import org.antlr.v4.runtime.tree.{TerminalNode, AbstractParseTreeVisitor}
import Ast0._
import collection.JavaConversions._

class Visitor extends AbstractParseTreeVisitor[ParseNode] with M2ParserVisitor[ParseNode] {
  def astInfo(token: Token) = AstInfo(token.getLine, token.getCharPositionInLine)

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
        terminal = ctx.StringLiteral()
      if (terminal == null)
        terminal = ctx.Id()
      if (terminal == null)
        terminal = ctx.getToken(SELF, 0)

      terminal.getSymbol
    }

    val info = astInfo(token)

    token.getType match {
      case Id => lId(info, token.getText)
      case StringLiteral => // strip embracing commas
        val (text, length) = (token.getText, token.getText.length)
        lString(info, text.substring(1, length - 1))
      case FloatLiteral => lFloat(info, token.getText)
      case IntLiteral => lInt(info, token.getText)
      case BooleanLiteral => lBoolean(info, token.getText)
      case SELF => lId(info, token.getText)
    }
  }

  override def visitRealdId(ctx: RealdIdContext): lId = {
    val realId = if (ctx.Id() != null) ctx.Id()
    else ctx.getToken(SELF, 0)
    lId(astInfo(realId.getSymbol), realId.getSymbol.getText)
  }

  override def visitScalarTypeHint(ctx: ScalarTypeHintContext): ScalarTypeHint =
    ScalarTypeHint(astInfo(ctx.getStart), ctx.Id().getText)

  override def visitFnTypeHintField(ctx: FnTypeHintFieldContext): FnTypeHintField =
    FnTypeHintField(
      astInfo(ctx.getStart),
      if (ctx.Id != null) ctx.Id().getText else ctx.getToken(SELF, 0).getText,
      visitTypeHint(ctx.typeHint()))


  override def visitFnTypeHint(ctx: FnTypeHintContext): FnTypeHint =
    FnTypeHint(
      astInfo(ctx.getStart),
      ctx.fnTypeHintField().map { f => visitFnTypeHintField(f) },
      visitTypeHint(ctx.typeHint())
    )

  override def visitTypeHint(ctx: TypeHintContext): TypeHint =
    if (ctx.scalarTypeHint() != null)
      visitScalarTypeHint(ctx.scalarTypeHint())
    else visitFnTypeHint(ctx.fnTypeHint())


  val re = """llvm\s*\{\s*(.*)\s*\}\s*""".r

  override def visitScalarType(ctx: ScalarTypeContext): ScalarType = {
    val llTypeName = re.replaceFirstIn(ctx.LlLiteral().getText, "$1").trim
    ScalarType(astInfo(ctx.getStart), ctx.Id().getText, llTypeName)
  }

  override def visitTypeField(ctx: TypeFieldContext): TypeField =
    TypeField(astInfo(ctx.getStart), ctx.getToken(SELF, 0) != null, ctx.Id().getText, visitTypeHint(ctx.typeHint()))

  override def visitFactorType(ctx: FactorTypeContext): FactorType =
    FactorType(
      astInfo(ctx.getStart),
      ctx.Id().getText,
      ctx.typeField().map { f => visitTypeField(f) }
    )

  override def visitType(ctx: TypeContext): Type =
    if (ctx.scalarType() != null)
      visitScalarType(ctx.scalarType())
    else visitFactorType(ctx.factorType())


  override def visitTuple(ctx: TupleContext): Tuple =
    Tuple(
      astInfo(ctx.getStart),
      ctx.expression().map { f => visitExpr(f) })

  override def visitFnArg(ctx: FnArgContext): FnArg =
    FnArg(
      astInfo(ctx.start),
      if (ctx.Id != null) ctx.Id().getText else ctx.getToken(SELF, 0).getText,
      Option(ctx.typeHint()).map(th => visitTypeHint(th)))

  override def visitBlock(ctx: BlockContext): Block =
    Block(astInfo(ctx.getStart),
      ctx.fnArg().map(fa => visitFnArg(fa)),
      ctx.blockBody().map { b => visitBlockBody(b) }
    )

  override def visitBlockBody(ctx: BlockBodyContext): BlockExpression =
    if (ctx.expression() != null)
      visitExpr(ctx.expression())
    else if (ctx.store() != null)
      visitStore(ctx.store())
    else
      visitVariable(ctx.variable())

  override def visitLambdaBlock(ctx: LambdaBlockContext): Block =
    Block(astInfo(ctx.getStart),
      if (ctx.fnArg() != null)
        ctx.fnArg().map { fa =>
          visitFnArg(fa)
        }
      else Seq(),
      Seq(ctx.expression().accept(this).asInstanceOf[Expression]))

  override def visitVariable(ctx: VariableContext): Val =
    Val(
      astInfo(ctx.getStart),
      if (ctx.valVar.getText == "val") false else true,
      ctx.Id().getText,
      Option(ctx.typeHint()).map(th => visitTypeHint(th)),
      ctx.expression().accept(this).asInstanceOf[Expression]
    )

  override def visitStore(ctx: StoreContext): Store =
    Store(
      astInfo(ctx.getStart),
      ctx.realdId().map(id => visitRealdId(id)),
      ctx.expression().accept(this).asInstanceOf[Expression])

  def visitExpr(ctx: ExpressionContext) = ctx.accept(this).asInstanceOf[Expression]

  override def visitExprLiteral(ctx: ExprLiteralContext): Literal = visitLiteral(ctx.literal())

  override def visitExprApply(ctx: ExprApplyContext): Call = {
    val oldArgs = visitTuple(ctx.tuple())
    val firstArg = visitExpr(ctx.expression())
    firstArg match {
      case Prop(from, prop) =>
        val all = Seq(from) ++ Seq(prop)
        all.last match {
          case id: lId =>
            Call(astInfo(ctx.getStart), id.value, Tuple(oldArgs.info, all.dropRight(1) ++ oldArgs.seq))
          case _ =>
            Call(astInfo(ctx.getStart), "apply", Tuple(oldArgs.info, all ++ oldArgs.seq))
        }
      case id: lId =>
        Call(astInfo(ctx.getStart), id.value, Tuple(oldArgs.info, oldArgs.seq))

      case other@_ =>
        Call(astInfo(ctx.getStart), "apply", Tuple(oldArgs.info, Seq(firstArg) ++ oldArgs.seq))
    }
  }

  override def visitExprInfixCall(ctx: ExprInfixCallContext): Call =
    Call(astInfo(ctx.getStart), ctx.op.getText, Tuple(astInfo(ctx.getStart), ctx.expression().map { e => visitExpr(e) }))


  override def visitExprParen(ctx: ExprParenContext): Expression = visitExpr(ctx.expression())

  override def visitExprTuple(ctx: ExprTupleContext): Tuple = visitTuple(ctx.tuple())

  override def visitExprUnaryCall(ctx: ExprUnaryCallContext): Call =
    Call(astInfo(ctx.getStart), ctx.op.getText,
      Tuple(astInfo(ctx.expression().getStart), Seq(visitExpr(ctx.expression())))
    )

  override def visitExprBlock(ctx: ExprBlockContext): Block = visitBlock(ctx.block())

  override def visitExprLambda(ctx: ExprLambdaContext): Block = visitLambdaBlock(ctx.lambdaBlock())

  override def visitExprIfElse(ctx: ExprIfElseContext): Cond =
    Cond(
      astInfo(ctx.getStart),
      ifCond = ctx.cond.accept(this).asInstanceOf[Expression],
      _then = (ctx.then_expr, ctx.then_block) match {
        case (expr, null) => Block(astInfo(expr.getStart), Seq(), Seq(visitExpr(expr)))
        case (null, block) => visitBlock(block)
      },
      _else = (ctx.else_expr, ctx.else_block) match {
        case (null, null) => None
        case (expr, null) => Some(Block(astInfo(expr.getStart), Seq(), Seq(visitExpr(expr))))
        case (null, block) => Some(visitBlock(block))
      }
    )

  override def visitExprWhile(ctx: ExprWhileContext): While =
    While(
      astInfo(ctx.getStart),
      cond = ctx.cond.accept(this).asInstanceOf[Expression],
      _then = visitBlock(ctx.then_block)
    )

  override def visitFunction(ctx: FunctionContext): Fn = {
    val (block, retType) =
      if (ctx.lambdaBlock() != null)
        (visitLambdaBlock(ctx.lambdaBlock()), None)
      else {
        val body: FnBody =
          if (ctx.block() != null)
            visitBlock(ctx.block())
          else
            LlInline(astInfo(ctx.LlLiteral().getSymbol),
              ctx.LlLiteral().getText.replace("llvm", "").replace("{", "").replace("}", ""))
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

    Fn(astInfo(ctx.getStart), ctx.name.getText, typeHint, block, retType)
  }

  override def visitLevel1(ctx: Level1Context): Level1Declaration =
    if (ctx.`type`() != null)
      visitType(ctx.`type`())
    else visitFunction(ctx.function())

  override def visitModule(ctx: ModuleContext): Module =
    Module(astInfo(ctx.getStart), ctx.level1().map { l1 => visitLevel1(l1) })

  override def visitExprProp(ctx: ExprPropContext): Prop = {
    Prop(ctx.expression().accept(this).asInstanceOf[Expression], lId(astInfo(ctx.op), ctx.op.getText))
  }
}