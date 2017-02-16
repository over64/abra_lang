// Generated from /home/over/build/test_lang/grammar/M2Parser.g4 by ANTLR 4.5.3
package grammar2;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link M2Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface M2ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link M2Parser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(M2Parser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(M2Parser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprMatch}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprMatch(M2Parser.ExprMatchContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLiteral}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLiteral(M2Parser.ExprLiteralContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprIfElse}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprIfElse(M2Parser.ExprIfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprLambda}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprLambda(M2Parser.ExprLambdaContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprApply}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprApply(M2Parser.ExprApplyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprParen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(M2Parser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprProp}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprProp(M2Parser.ExprPropContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprTuple}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprTuple(M2Parser.ExprTupleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprWhile}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprWhile(M2Parser.ExprWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprId}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprId(M2Parser.ExprIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprUnaryCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprUnaryCall(M2Parser.ExprUnaryCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprInfixCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprInfixCall(M2Parser.ExprInfixCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprSelfCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprSelfCall(M2Parser.ExprSelfCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprBlock}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprBlock(M2Parser.ExprBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchDash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchDash(M2Parser.MatchDashContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchId}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchId(M2Parser.MatchIdContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchType(M2Parser.MatchTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchBracketsExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchBracketsExpr(M2Parser.MatchBracketsExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchExpression(M2Parser.MatchExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#destruct}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDestruct(M2Parser.DestructContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchOver}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchOver(M2Parser.MatchOverContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#matchCase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchCase(M2Parser.MatchCaseContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#if_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_stat(M2Parser.If_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#store}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStore(M2Parser.StoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#fnArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnArg(M2Parser.FnArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(M2Parser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#blockBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockBody(M2Parser.BlockBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#lambdaBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambdaBlock(M2Parser.LambdaBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(M2Parser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#scalarTypeHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarTypeHint(M2Parser.ScalarTypeHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#fnTypeHintField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnTypeHintField(M2Parser.FnTypeHintFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#fnTypeHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnTypeHint(M2Parser.FnTypeHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#typeHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeHint(M2Parser.TypeHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(M2Parser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarType(M2Parser.ScalarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#typeField}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeField(M2Parser.TypeFieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#factorType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorType(M2Parser.FactorTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(M2Parser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(M2Parser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#import_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImport_(M2Parser.Import_Context ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#level1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLevel1(M2Parser.Level1Context ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#module}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModule(M2Parser.ModuleContext ctx);
}