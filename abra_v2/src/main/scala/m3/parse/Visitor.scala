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

  override def visitSp(ctx: SpContext): ParseNode = null

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
        terminal = ctx.NoneLiteral()

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
        case NoneLiteral => lNone()
      })
  }

  def visitLlvmBody(ctx: LlvmBodyContext): llVm = emit(ctx, llVm(ctx.getText.trim))

  def visitLlvm(ctx: LlvmContext): llVm =
    visitLlvmBody(ctx.llvmBody())

  override def visitId(ctx: IdContext): lId = {
    val realId = if (ctx.VarId() != null) ctx.VarId()
    else ctx.getToken(SELF, 0)
    emit(realId.getSymbol, lId(realId.getSymbol.getText))
  }

  override def visitExprTypeId(ctx: ExprTypeIdContext) =
    emit(ctx.TypeId().getSymbol, lId(ctx.TypeId().getText))

  override def visitScalarTh(ctx: ScalarThContext): ScalarTh =
    emit(ctx, ScalarTh(
      params = ctx.typeHint().map(th => visitTypeHint(th)),
      name = ctx.typeName.getText,
      mod = Option(ctx.id()).map(_.getText).toSeq))

  override def visitFieldTh(ctx: FieldThContext): FieldTh =
    emit(ctx, FieldTh(
      visitId(ctx.id()).value,
      visitTypeHint(ctx.typeHint())))

  override def visitFnTh(ctx: FnThContext): FnTh =
    emit(ctx, FnTh(
      Seq.empty,
      ctx.args.map { f => visitTypeHint(f) },
      visitTypeHint(ctx.rett)))

  override def visitStructTh(ctx: StructThContext): StructTh =
    emit(ctx, StructTh(ctx.fieldTh().map(th => visitFieldTh(th))))


  override def visitNonUnionTh(ctx: NonUnionThContext): TypeHint =
    visitChildren(ctx).asInstanceOf[TypeHint]

  override def visitUnionTh(ctx: UnionThContext): UnionTh =
    emit(ctx, UnionTh(ctx.nonUnionTh().map(th => visitNonUnionTh(th))))

  override def visitGenericTh(ctx: GenericThContext): GenericTh =
    emit(ctx, GenericTh(ctx.VarId().getText))

  override def visitTypeHint(ctx: TypeHintContext): TypeHint = visitChildren(ctx).asInstanceOf[TypeHint]

  override def visitScalarType(ctx: ScalarTypeContext): ScalarDecl =
    emit(ctx, ScalarDecl(
      _package,
      if (ctx.REF() != null) true else false,
      ctx.params.map(p => visitGenericTh(p)),
      ctx.tname.getText,
      visitLlvm(ctx.llvm()).code))

  override def visitTypeField(ctx: TypeFieldContext): FieldDecl =
    emit(ctx, FieldDecl(ctx.getToken(SELF, 0) != null, ctx.VarId().getText, visitTypeHint(ctx.typeHint())))

  override def visitStructType(ctx: StructTypeContext): StructDecl = {
    emit(ctx, StructDecl(
      _package,
      ctx.params.map { p => emit(p, visitGenericTh(p)) },
      ctx.name.getText,
      ctx.typeField().map { f => visitTypeField(f) }
    ))
  }

  override def visitUnionType(ctx: UnionTypeContext): UnionDecl = {
    emit(ctx, UnionDecl(
      _package,
      ctx.params.map { p => emit(p, visitGenericTh(p)) },
      ctx.name.getText,
      ctx.nonUnionTh().map { th => visitNonUnionTh(th) }
    ))
  }

  override def visitType(ctx: TypeContext): TypeDecl =
    visitChildren(ctx).asInstanceOf[TypeDecl]

  override def visitTuple(ctx: TupleContext): Tuple =
    emit(ctx, Tuple(ctx.expression().map { f => visitExpr(f) }))

  override def visitFnArg(ctx: FnArgContext): Arg =
    emit(ctx, Arg(
      visitId(ctx.id()).value,
      Option(ctx.typeHint()).map(th => visitTypeHint(th)).getOrElse(AnyTh)))

  override def visitLambda(ctx: LambdaContext): Lambda = {
    val body = AbraCode(ctx.blockBody().map { b => visitBlockBody(b) })
    emit(ctx, Lambda(ctx.fnArg().map(fa => visitFnArg(fa)), body))
  }

  override def visitBlockBody(ctx: BlockBodyContext): Expression =
    if (ctx.expression() != null)
      visitExpr(ctx.expression())
    else if (ctx.store() != null)
      visitStore(ctx.store())
    else if (ctx.break_() != null)
      visitBreak_(ctx.break_())
    else if (ctx.continue_() != null)
      visitContinue_(ctx.continue_())
    else if (ctx.ret() != null)
      visitRet(ctx.ret())
    else visitWhile_stat(ctx.while_stat())

  override def visitStore(ctx: StoreContext): Expression = {
    val first = visitId(ctx.id())
    val expr = ctx.expression().accept(this).asInstanceOf[Expression]

    if (ctx.tuple() != null) {
      val indices = visitTuple(ctx.tuple()).seq
      val to: Expression = Prop(first, ctx.VarId().map(vi => lId(vi.getText)))

      emit(ctx, SelfCall("set", to, indices :+ expr))
    } else
      emit(ctx, Store(
        Option(ctx.typeHint()).map(th => visitTypeHint(th)).getOrElse(AnyTh),
        first +: ctx.VarId().map(vid => lId(vid.getText)), expr))
  }

  override def visitRet(ctx: RetContext): Ret = emit(ctx, Ret(
    Option(ctx.expression()) map (e => visitExpr(e))))

  override def visitBreak_(ctx: Break_Context): Break =
    emit(ctx, Break())

  override def visitContinue_(ctx: Continue_Context): Continue =
    emit(ctx, Continue())

  override def visitWhile_stat(ctx: While_statContext): While = {
    emit(ctx, While(
      cond = ctx.cond.accept(this).asInstanceOf[Expression],
      _do = ctx.blockBody().map { b => visitBlockBody(b) }
    ))
  }

  def visitExpr(ctx: ExpressionContext) = ctx.accept(this).asInstanceOf[Expression]

  override def visitExprLiteral(ctx: ExprLiteralContext): Literal = visitLiteral(ctx.literal())

  override def visitExprId(ctx: ExprIdContext): lId = visitId(ctx.id())

  override def visitExprSelfCall(ctx: ExprSelfCallContext): ParseNode = {
    emit(ctx, SelfCall(ctx.op.getText, visitExpr(ctx.expression()), visitTuple(ctx.tuple()).seq))
  }

  override def visitExprCall(ctx: ExprCallContext): Expression = {
    val args = visitTuple(ctx.tuple()).seq
    emit(ctx, Call(visitExpr(ctx.expression()), args))
  }

  override def visitExprInfixCall(ctx: ExprInfixCallContext): Expression = {
    ctx.op.getText match {
      case "&&" => emit(ctx, And(visitExpr(ctx.expression(0)), visitExpr(ctx.expression(1))))
      case "||" => emit(ctx, Or(visitExpr(ctx.expression(0)), visitExpr(ctx.expression(1))))
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

  override def visitExprIfElse(ctx: ExprIfElseContext): If = {
    emit(ctx, If(
      cond = ctx.cond.accept(this).asInstanceOf[Expression],
      _do = ctx.doStat.map { ds => visitBlockBody(ds) },
      _else = ctx.elseStat.map { ds => visitBlockBody(ds) }
    ))
  }

  override def visitIs(ctx: IsContext) =
    emit(ctx, Is(
      Option(ctx.VarId()).map(vid => lId(vid.getSymbol.getText)),
      visitTypeHint(ctx.typeHint()),
      ctx.blockBody().map(bb => visitBlockBody(bb))
    ))

  override def visitExprUnless(ctx: ExprUnlessContext) =
    emit(ctx, Unless(
      visitExpr(ctx.expr),
      ctx.is().map(i => visitIs(i))
    ))

  override def visitExprProp(ctx: ExprPropContext): Expression =
    emit(ctx, Prop(visitExpr(ctx.expression()), ctx.op.map(op => emit(op, lId(op.getText)))))

  override def visitDef(ctx: DefContext): Def = {
    val args = ctx.fnArg().map(fa => visitFnArg(fa))

    val body =
      if (ctx.llvm() != null) Lambda(args, visitLlvm(ctx.llvm()))
      else Lambda(args, AbraCode(ctx.blockBody().map(bb => visitBlockBody(bb))))

    val retTh = Option(ctx.typeHint()).map { th => visitTypeHint(th) }.getOrElse(AnyTh)
    emit(ctx, Def(ctx.name.getText, body, retTh))
  }


  override def visitLevel1(ctx: Level1Context): Level1Declaration =
    visitChildren(ctx).asInstanceOf[Level1Declaration]

  override def visitModule(ctx: ModuleContext): Module = {
    val imp =
      if (ctx.import_() != null)
        visitImport_(ctx.import_())
      else Import(Seq())

    val lowCode = ctx.llvm().map(l => visitLlvm(l))
    val all = ctx.level1().map { l1 => visitLevel1(l1) }
    val types = all.filter(_.isInstanceOf[TypeDecl]).map(_.asInstanceOf[TypeDecl])
    val functions = all.filter(_.isInstanceOf[Def]).map(_.asInstanceOf[Def])

    emit(ctx, Module(_package, imp, lowCode, types, functions))
  }

  override def visitImportEntry(ctx: ImportEntryContext): ImportEntry = {
    val path = ctx.VarId().map(vi => vi.getText)
      .mkString(start = if (ctx.abs == null) "" else "/", sep = "/", end = "")
    val modName = ctx.VarId().last.getText
    val types = ctx.TypeId().map(ti => ti.getText)
    emit(ctx, ImportEntry(modName, path, types))
  }

  override def visitImport_(ctx: Import_Context): Import =
    emit(ctx, Import(ctx.importEntry().map(ie => visitImportEntry(ie))))

}