// Generated from /home/over/build/abra_lang/abra_v2/grammar/M2Parser.g4 by ANTLR 4.7
package grammar;
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
	 * Visit a parse tree produced by {@link M2Parser#sp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSp(M2Parser.SpContext ctx);
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
	 * Visit a parse tree produced by the {@code exprParen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprParen(M2Parser.ExprParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprCall(M2Parser.ExprCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code exprWnen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprWnen(M2Parser.ExprWnenContext ctx);
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
	 * Visit a parse tree produced by {@link M2Parser#tuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTuple(M2Parser.TupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#fieldTh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldTh(M2Parser.FieldThContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#scalarTh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarTh(M2Parser.ScalarThContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#fnTh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnTh(M2Parser.FnThContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#structTh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructTh(M2Parser.StructThContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#nonUnionTh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNonUnionTh(M2Parser.NonUnionThContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#unionTh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionTh(M2Parser.UnionThContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#typeHint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeHint(M2Parser.TypeHintContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#is}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs(M2Parser.IsContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#store}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStore(M2Parser.StoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#ret}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRet(M2Parser.RetContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#while_stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_stat(M2Parser.While_statContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#fnArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnArg(M2Parser.FnArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#lambda}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLambda(M2Parser.LambdaContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#blockBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockBody(M2Parser.BlockBodyContext ctx);
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
	 * Visit a parse tree produced by {@link M2Parser#structType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructType(M2Parser.StructTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#unionType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnionType(M2Parser.UnionTypeContext ctx);
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
	/**
	 * Visit a parse tree produced by {@link M2Parser#llvmBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlvmBody(M2Parser.LlvmBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link M2Parser#llvm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlvm(M2Parser.LlvmContext ctx);
}