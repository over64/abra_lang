// Generated from /home/over/build/test_lang/antlr/Hello.g4 by ANTLR 4.5.3
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#valDef}.
	 * @param ctx the parse tree
	 */
	void enterValDef(HelloParser.ValDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#valDef}.
	 * @param ctx the parse tree
	 */
	void exitValDef(HelloParser.ValDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(HelloParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(HelloParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#fnArg}.
	 * @param ctx the parse tree
	 */
	void enterFnArg(HelloParser.FnArgContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#fnArg}.
	 * @param ctx the parse tree
	 */
	void exitFnArg(HelloParser.FnArgContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#fnArgs}.
	 * @param ctx the parse tree
	 */
	void enterFnArgs(HelloParser.FnArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#fnArgs}.
	 * @param ctx the parse tree
	 */
	void exitFnArgs(HelloParser.FnArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#llvmFnDef}.
	 * @param ctx the parse tree
	 */
	void enterLlvmFnDef(HelloParser.LlvmFnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#llvmFnDef}.
	 * @param ctx the parse tree
	 */
	void exitLlvmFnDef(HelloParser.LlvmFnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#abraFnDef}.
	 * @param ctx the parse tree
	 */
	void enterAbraFnDef(HelloParser.AbraFnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#abraFnDef}.
	 * @param ctx the parse tree
	 */
	void exitAbraFnDef(HelloParser.AbraFnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#fnDef}.
	 * @param ctx the parse tree
	 */
	void enterFnDef(HelloParser.FnDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#fnDef}.
	 * @param ctx the parse tree
	 */
	void exitFnDef(HelloParser.FnDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#fnBodyDef}.
	 * @param ctx the parse tree
	 */
	void enterFnBodyDef(HelloParser.FnBodyDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#fnBodyDef}.
	 * @param ctx the parse tree
	 */
	void exitFnBodyDef(HelloParser.FnBodyDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#lang}.
	 * @param ctx the parse tree
	 */
	void enterLang(HelloParser.LangContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#lang}.
	 * @param ctx the parse tree
	 */
	void exitLang(HelloParser.LangContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#structParamDef}.
	 * @param ctx the parse tree
	 */
	void enterStructParamDef(HelloParser.StructParamDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#structParamDef}.
	 * @param ctx the parse tree
	 */
	void exitStructParamDef(HelloParser.StructParamDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#llvmTypeDef}.
	 * @param ctx the parse tree
	 */
	void enterLlvmTypeDef(HelloParser.LlvmTypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#llvmTypeDef}.
	 * @param ctx the parse tree
	 */
	void exitLlvmTypeDef(HelloParser.LlvmTypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#abraTypeDef}.
	 * @param ctx the parse tree
	 */
	void enterAbraTypeDef(HelloParser.AbraTypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#abraTypeDef}.
	 * @param ctx the parse tree
	 */
	void exitAbraTypeDef(HelloParser.AbraTypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void enterTypeDef(HelloParser.TypeDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#typeDef}.
	 * @param ctx the parse tree
	 */
	void exitTypeDef(HelloParser.TypeDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#access}.
	 * @param ctx the parse tree
	 */
	void enterAccess(HelloParser.AccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#access}.
	 * @param ctx the parse tree
	 */
	void exitAccess(HelloParser.AccessContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAtomExp(HelloParser.AtomExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAtomExp(HelloParser.AtomExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code directCallExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterDirectCallExp(HelloParser.DirectCallExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directCallExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitDirectCallExp(HelloParser.DirectCallExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code priority3Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPriority3Expr(HelloParser.Priority3ExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code priority3Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPriority3Expr(HelloParser.Priority3ExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpCall}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpCall(HelloParser.ParenExpCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpCall}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpCall(HelloParser.ParenExpCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code priority2Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPriority2Expr(HelloParser.Priority2ExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code priority2Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPriority2Expr(HelloParser.Priority2ExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code accessExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAccessExp(HelloParser.AccessExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code accessExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAccessExp(HelloParser.AccessExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code simpleParen}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSimpleParen(HelloParser.SimpleParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code simpleParen}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSimpleParen(HelloParser.SimpleParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code priority1Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPriority1Expr(HelloParser.Priority1ExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code priority1Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPriority1Expr(HelloParser.Priority1ExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code varIdentExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarIdentExp(HelloParser.VarIdentExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code varIdentExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarIdentExp(HelloParser.VarIdentExpContext ctx);
}