package m3.parse

import java.math.BigInteger

import grammar.M2Parser._
import grammar.M2ParserVisitor
import m3.parse.Ast0._
import org.antlr.v4.runtime.tree.{AbstractParseTreeVisitor, TerminalNode}
import org.antlr.v4.runtime.{ParserRuleContext, Token}

import scala.collection.JavaConversions._
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

      terminal.getSymbol
    }

    emit(token,
      token.getType match {
        case StringLiteral => // strip embracing commas
          val (text, length) = (token.getText, token.getText.length)
          lString(text.substring(1, length - 1))
        case FloatLiteral => lFloat(token.getText)
        case IntLiteral => lInt(token.getText)
        case HexLiteral => lInt(new BigInteger(token.getText.stripPrefix("0x"), 16).toString())
        case BooleanLiteral => lBoolean(token.getText)
      })
  }

  override def visitId(ctx: IdContext): lId = {
    val realId = if (ctx.VarId() != null) ctx.VarId()
    else ctx.getToken(SELF, 0)
    emit(realId.getSymbol, lId(realId.getSymbol.getText))
  }

  val predefTypes = Seq("Unit", "Boolean", "Int", "Float", "String")

  override def visitScalarTh(ctx: ScalarThContext): ScalarTh = {
    val typeName = ctx.typeName.getText
    //    val pkg =
    //      if (ctx.modVar != null)
    //        importedModules(ctx.modVar.getText)
    //      else if (predefTypes.contains(typeName)) ""
    //      else if (importedTypes.contains(typeName)) importedTypes(typeName)
    //      else _package
    val pkg = ""

    emit(ctx, ScalarTh(ctx.tparams.map { tp => TypeParam(tp.getText) }, typeName, pkg, if (ctx.ptr != null) true else false))
  }


  override def visitFieldTh(ctx: FieldThContext): FieldTh =
    emit(ctx, FieldTh(
      visitId(ctx.id()).value,
      visitTypeHint(ctx.typeHint())))

  override def visitFnTh(ctx: FnThContext): FnTh =
    emit(ctx, FnTh(
      ctx.args.map { f => visitTypeHint(f) },
      visitTypeHint(ctx.ret)))

  override def visitStructTh(ctx: StructThContext): StructTh =
    emit(ctx, StructTh(ctx.typeHint().map(th => visitTypeHint(th))))


  override def visitNonUnionTh(ctx: NonUnionThContext): TypeHint =
    visitChildren(ctx).asInstanceOf[TypeHint]

  override def visitUnionTh(ctx: UnionThContext): UnionTh =
    emit(ctx, UnionTh(ctx.nonUnionTh().map(th => visitNonUnionTh(th))))

  override def visitTypeHint(ctx: TypeHintContext): TypeHint = visitChildren(ctx).asInstanceOf[TypeHint]

  override def visitScalarType(ctx: ScalarTypeContext): ScalarDecl =
    emit(ctx, ScalarDecl(ctx.TypeId().getText, visitLltype(ctx.lltype()).text))

  override def visitTypeField(ctx: TypeFieldContext): FieldDecl =
    emit(ctx, FieldDecl(ctx.getToken(SELF, 0) != null, ctx.VarId().getText, visitTypeHint(ctx.typeHint())))

  override def visitStructType(ctx: StructTypeContext): StructDecl =
    emit(ctx, StructDecl(
      ctx.tparams.map { p => emit(p, TypeParam(p.getText)) },
      ctx.name.getText,
      ctx.typeField().map { f => visitTypeField(f) }
    ))

  override def visitUnionType(ctx: UnionTypeContext): UnionDecl =
    emit(ctx, UnionDecl(
      ctx.tparams.map { p => emit(p, TypeParam(p.getText)) },
      ctx.name.getText,
      ctx.scalarTh().map { sth => visitScalarTh(sth) }
    ))

  override def visitType(ctx: TypeContext): TypeDecl =
    visitChildren(ctx).asInstanceOf[TypeDecl]

  override def visitTuple(ctx: TupleContext): Tuple =
    emit(ctx, Tuple(ctx.expression().map { f => visitExpr(f) }))

  override def visitFnArg(ctx: FnArgContext): FnArg =
    emit(ctx, FnArg(
      visitId(ctx.id()).value,
      Option(ctx.typeHint()).map(th => visitTypeHint(th))))

  override def visitLambda(ctx: LambdaContext): Lambda =
    emit(ctx, Lambda(
      ctx.fnArg().map(fa => visitFnArg(fa)),
      ctx.blockBody().map { b => visitBlockBody(b) }
    ))

  override def visitBlockBody(ctx: BlockBodyContext): Expression =
    if (ctx.expression() != null)
      visitExpr(ctx.expression())
    else if (ctx.store() != null)
      visitStore(ctx.store())
    else if (ctx.variable() != null)
      visitVariable(ctx.variable())
    else visitWhile_stat(ctx.while_stat())

  override def visitHLambda(ctx: HLambdaContext): Lambda = {
    val args =
      if (ctx.fnArg() != null) ctx.fnArg().map { fa => visitFnArg(fa) }
      else Seq()

    emit(ctx, Lambda(args, Seq(visitBlockBody(ctx.blockBody()))))
  }

  override def visitVariable(ctx: VariableContext): Val =
    emit(ctx, Val(
      if (ctx.valVar.getText == "val") false else true,
      ctx.VarId().getText,
      Option(ctx.typeHint()).map(th => visitTypeHint(th)),
      ctx.expression().accept(this).asInstanceOf[Expression]
    ))

  override def visitStore(ctx: StoreContext): Expression = {
    val first = visitId(ctx.id())
    val expr = ctx.expression().accept(this).asInstanceOf[Expression]

    if (ctx.tuple() != null) {
      val indices = visitTuple(ctx.tuple()).seq
      val to: Expression =
        if (ctx.VarId().isEmpty) first
        else {
          val firstProp = Prop(first, lId(ctx.VarId().head.getText))

          ctx.VarId().drop(1).foldLeft(firstProp) {
            case (prop, vid) => Prop(prop, lId(vid.getText))
          }
        }

      emit(ctx, SelfCall("set", to, indices :+ expr))
    } else
      emit(ctx, Store(first +: ctx.VarId().map(vid => lId(vid.getText)), expr))
  }

  override def visitWhile_stat(ctx: While_statContext): While = {
    emit(ctx, While(
      cond = ctx.cond.accept(this).asInstanceOf[Expression],
      _then = ctx.blockBody().map { b => visitBlockBody(b) }
    ))
  }

  def visitExpr(ctx: ExpressionContext) = ctx.accept(this).asInstanceOf[Expression]

  override def visitExprRef(ctx: ExprRefContext): Ref = emit(ctx, Ref(visitExpr(ctx.expression())))

  override def visitExprDeref(ctx: ExprDerefContext): Deref = emit(ctx, Deref(visitExpr(ctx.expression())))

  override def visitExprLiteral(ctx: ExprLiteralContext): Literal = visitLiteral(ctx.literal())

  override def visitExprId(ctx: ExprIdContext): lId = visitId(ctx.id())

  override def visitExprSelfCall(ctx: ExprSelfCallContext): ParseNode =
    emit(ctx, SelfCall(ctx.op.getText, visitExpr(ctx.expression()), visitTuple(ctx.tuple()).seq))

  override def visitExprCall(ctx: ExprCallContext): Expression = {
    val args = visitTuple(ctx.tuple()).seq
    emit(ctx, Call(visitExpr(ctx.expression()), args))
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

  override def visitExprLambda(ctx: ExprLambdaContext): Lambda = visitLambda(ctx.lambda())

  override def visitExprHLambda(ctx: ExprHLambdaContext): Lambda = visitHLambda(ctx.hLambda())

  override def visitIf_stat(ctx: If_statContext): Block =
    if (!ctx.blockBody().isEmpty) Block(ctx.blockBody().map(b => visitBlockBody(b)))
    else if (ctx.expression() != null) Block(Seq(visitExpr(ctx.expression())))
    else Block(Seq(visitStore(ctx.store())))

  override def visitExprIfElse(ctx: ExprIfElseContext): Cond = {
    emit(ctx, Cond(
      ifCond = ctx.cond.accept(this).asInstanceOf[Expression],
      _then = visitIf_stat(ctx.then_stat).seq,
      _else = if (ctx.else_stat != null) visitIf_stat(ctx.else_stat).seq else Seq()
    ))
  }

  override def visitExprProp(ctx: ExprPropContext): Expression =
    emit(ctx, Prop(visitExpr(ctx.expression()), emit(ctx.op, lId(ctx.op.getText))))

  override def visitMatchDash(ctx: MatchDashContext): MatchOver = emit(ctx, Dash)

  override def visitMatchId(ctx: MatchIdContext): lId = {
    val realId = if (ctx.MatchId() != null) ctx.MatchId() else ctx.getToken(MATCH_SELF, 0)
    emit(realId.getSymbol, lId(realId.getSymbol.getText.replaceFirst("\\$", "")))
  }

  override def visitMatchType(ctx: MatchTypeContext): MatchType =
    emit(ctx, MatchType(
      varName = lId(ctx.VarId().getText),
      scalarTypeHint = visitScalarTh(ctx.scalarTh())
    ))

  override def visitMatchBracketsExpr(ctx: MatchBracketsExprContext): Expression = visitExpr(ctx.expression())

  override def visitMatchExpression(ctx: MatchExpressionContext): Expression =
    emit(ctx, visitChildren(ctx).asInstanceOf[Expression])

  override def visitDestruct(ctx: DestructContext) =
    emit(ctx, Destruct(
      varName = if (ctx.id() == null) None else Some(visitId(ctx.id())),
      scalarTypeHint = visitScalarTh(ctx.scalarTh()),
      args = ctx.matchOver().map { mo => visitMatchOver(mo) }
    ))

  override def visitBindVar(ctx: BindVarContext) = BindVar(visitId(ctx.id()))

  override def visitMatchOver(ctx: MatchOverContext) =
    visitChildren(ctx).asInstanceOf[MatchOver]

  override def visitMatchCase(ctx: MatchCaseContext): Case =
    emit(ctx, Case(
      over = visitMatchOver(ctx.matchOver()),
      cond = if (ctx.cond == null) None else Some(visitExpr(ctx.cond)),
      seq = visitIf_stat(ctx.onMatch).seq
    ))

  override def visitExprMatch(ctx: ExprMatchContext) =
    emit(ctx, Match(
      on = visitExpr(ctx.expr),
      cases = ctx.matchCase().map { mc => visitMatchCase(mc) }
    ))

  override def visitFunction(ctx: FunctionContext): Fn = {
    val (lambda, retType) =
      if (ctx.expression() != null)
        (Lambda(Seq(), Seq(visitExpr(ctx.expression()))), None)
      else if (ctx.hLambda() != null)
        (visitHLambda(ctx.hLambda()), None)
      else if (ctx.lambda() != null)
        (visitHLambda(ctx.hLambda()), Some(visitTypeHint(ctx.typeHint())))
      else
        (visitLldef(ctx.lldef()), Some(visitTypeHint(ctx.typeHint())))

    val tparams = ctx.tparams.map { tp => TypeParam(tp.getText) }

    emit(ctx, Fn(tparams, ctx.name.getText, lambda, retType))
  }

  override def visitLevel1(ctx: Level1Context): Level1Declaration =
    if (ctx.`type`() != null)
      visitType(ctx.`type`())
    else visitFunction(ctx.function())

  override def visitModule(ctx: ModuleContext): Module = {
    val all = ctx.level1().map { l1 => visitLevel1(l1) }
    val types = all.filter(_.isInstanceOf[TypeDecl]).map(_.asInstanceOf[TypeDecl])
    val functions = all.filter(_.isInstanceOf[Fn]).map(_.asInstanceOf[Fn])

    emit(ctx, Module(_package, types, functions))
  }

  override def visitLltype(ctx: LltypeContext): Ast0.LlType = Ast0.LlType(ctx.IrInline().getText.trim)

  override def visitLldef(ctx: LldefContext): Ast0.LlDef =
    emit(ctx, Ast0.LlDef(
      ctx.fnArg().map(th => visitFnArg(th)),
      ctx.IrInline().getText))
}