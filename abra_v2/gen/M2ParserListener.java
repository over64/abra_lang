// Generated from /home/over/build/abra_lang/abra_v2/grammar/M2Parser.g4 by ANTLR 4.7
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link M2Parser}.
 */
public interface M2ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link M2Parser#sp}.
	 * @param ctx the parse tree
	 */
	void enterSp(M2Parser.SpContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#sp}.
	 * @param ctx the parse tree
	 */
	void exitSp(M2Parser.SpContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(M2Parser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(M2Parser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(M2Parser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(M2Parser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprWnen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprWnen(M2Parser.ExprWnenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprWnen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprWnen(M2Parser.ExprWnenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprTypeId}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprTypeId(M2Parser.ExprTypeIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprTypeId}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprTypeId(M2Parser.ExprTypeIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLiteral}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLiteral(M2Parser.ExprLiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLiteral}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLiteral(M2Parser.ExprLiteralContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprIfElse}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprIfElse(M2Parser.ExprIfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprIfElse}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprIfElse(M2Parser.ExprIfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprLambda}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprLambda(M2Parser.ExprLambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprLambda}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprLambda(M2Parser.ExprLambdaContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprParen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprParen(M2Parser.ExprParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprParen}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprParen(M2Parser.ExprParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprCall(M2Parser.ExprCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprCall(M2Parser.ExprCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprProp}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprProp(M2Parser.ExprPropContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprProp}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprProp(M2Parser.ExprPropContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprTuple}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprTuple(M2Parser.ExprTupleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprTuple}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprTuple(M2Parser.ExprTupleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprId}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprId(M2Parser.ExprIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprId}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprId(M2Parser.ExprIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprUnaryCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprUnaryCall(M2Parser.ExprUnaryCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprUnaryCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprUnaryCall(M2Parser.ExprUnaryCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprInfixCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprInfixCall(M2Parser.ExprInfixCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprInfixCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprInfixCall(M2Parser.ExprInfixCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprSelfCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExprSelfCall(M2Parser.ExprSelfCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprSelfCall}
	 * labeled alternative in {@link M2Parser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExprSelfCall(M2Parser.ExprSelfCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#tuple}.
	 * @param ctx the parse tree
	 */
	void enterTuple(M2Parser.TupleContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#tuple}.
	 * @param ctx the parse tree
	 */
	void exitTuple(M2Parser.TupleContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#fieldTh}.
	 * @param ctx the parse tree
	 */
	void enterFieldTh(M2Parser.FieldThContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#fieldTh}.
	 * @param ctx the parse tree
	 */
	void exitFieldTh(M2Parser.FieldThContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#scalarTh}.
	 * @param ctx the parse tree
	 */
	void enterScalarTh(M2Parser.ScalarThContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#scalarTh}.
	 * @param ctx the parse tree
	 */
	void exitScalarTh(M2Parser.ScalarThContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#fnTh}.
	 * @param ctx the parse tree
	 */
	void enterFnTh(M2Parser.FnThContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#fnTh}.
	 * @param ctx the parse tree
	 */
	void exitFnTh(M2Parser.FnThContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#structTh}.
	 * @param ctx the parse tree
	 */
	void enterStructTh(M2Parser.StructThContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#structTh}.
	 * @param ctx the parse tree
	 */
	void exitStructTh(M2Parser.StructThContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#nonUnionTh}.
	 * @param ctx the parse tree
	 */
	void enterNonUnionTh(M2Parser.NonUnionThContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#nonUnionTh}.
	 * @param ctx the parse tree
	 */
	void exitNonUnionTh(M2Parser.NonUnionThContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#unionTh}.
	 * @param ctx the parse tree
	 */
	void enterUnionTh(M2Parser.UnionThContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#unionTh}.
	 * @param ctx the parse tree
	 */
	void exitUnionTh(M2Parser.UnionThContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#typeHint}.
	 * @param ctx the parse tree
	 */
	void enterTypeHint(M2Parser.TypeHintContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#typeHint}.
	 * @param ctx the parse tree
	 */
	void exitTypeHint(M2Parser.TypeHintContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#is}.
	 * @param ctx the parse tree
	 */
	void enterIs(M2Parser.IsContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#is}.
	 * @param ctx the parse tree
	 */
	void exitIs(M2Parser.IsContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#store}.
	 * @param ctx the parse tree
	 */
	void enterStore(M2Parser.StoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#store}.
	 * @param ctx the parse tree
	 */
	void exitStore(M2Parser.StoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#ret}.
	 * @param ctx the parse tree
	 */
	void enterRet(M2Parser.RetContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#ret}.
	 * @param ctx the parse tree
	 */
	void exitRet(M2Parser.RetContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#while_stat}.
	 * @param ctx the parse tree
	 */
	void enterWhile_stat(M2Parser.While_statContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#while_stat}.
	 * @param ctx the parse tree
	 */
	void exitWhile_stat(M2Parser.While_statContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#fnArg}.
	 * @param ctx the parse tree
	 */
	void enterFnArg(M2Parser.FnArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#fnArg}.
	 * @param ctx the parse tree
	 */
	void exitFnArg(M2Parser.FnArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#lambda}.
	 * @param ctx the parse tree
	 */
	void enterLambda(M2Parser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#lambda}.
	 * @param ctx the parse tree
	 */
	void exitLambda(M2Parser.LambdaContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#blockBody}.
	 * @param ctx the parse tree
	 */
	void enterBlockBody(M2Parser.BlockBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#blockBody}.
	 * @param ctx the parse tree
	 */
	void exitBlockBody(M2Parser.BlockBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#scalarType}.
	 * @param ctx the parse tree
	 */
	void enterScalarType(M2Parser.ScalarTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#scalarType}.
	 * @param ctx the parse tree
	 */
	void exitScalarType(M2Parser.ScalarTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#typeField}.
	 * @param ctx the parse tree
	 */
	void enterTypeField(M2Parser.TypeFieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#typeField}.
	 * @param ctx the parse tree
	 */
	void exitTypeField(M2Parser.TypeFieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#structType}.
	 * @param ctx the parse tree
	 */
	void enterStructType(M2Parser.StructTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#structType}.
	 * @param ctx the parse tree
	 */
	void exitStructType(M2Parser.StructTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#unionType}.
	 * @param ctx the parse tree
	 */
	void enterUnionType(M2Parser.UnionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#unionType}.
	 * @param ctx the parse tree
	 */
	void exitUnionType(M2Parser.UnionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(M2Parser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(M2Parser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(M2Parser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(M2Parser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#importEntry}.
	 * @param ctx the parse tree
	 */
	void enterImportEntry(M2Parser.ImportEntryContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#importEntry}.
	 * @param ctx the parse tree
	 */
	void exitImportEntry(M2Parser.ImportEntryContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#import_}.
	 * @param ctx the parse tree
	 */
	void enterImport_(M2Parser.Import_Context ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#import_}.
	 * @param ctx the parse tree
	 */
	void exitImport_(M2Parser.Import_Context ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#level1}.
	 * @param ctx the parse tree
	 */
	void enterLevel1(M2Parser.Level1Context ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#level1}.
	 * @param ctx the parse tree
	 */
	void exitLevel1(M2Parser.Level1Context ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#module}.
	 * @param ctx the parse tree
	 */
	void enterModule(M2Parser.ModuleContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#module}.
	 * @param ctx the parse tree
	 */
	void exitModule(M2Parser.ModuleContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#llvmBody}.
	 * @param ctx the parse tree
	 */
	void enterLlvmBody(M2Parser.LlvmBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#llvmBody}.
	 * @param ctx the parse tree
	 */
	void exitLlvmBody(M2Parser.LlvmBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link M2Parser#llvm}.
	 * @param ctx the parse tree
	 */
	void enterLlvm(M2Parser.LlvmContext ctx);
	/**
	 * Exit a parse tree produced by {@link M2Parser#llvm}.
	 * @param ctx the parse tree
	 */
	void exitLlvm(M2Parser.LlvmContext ctx);
}