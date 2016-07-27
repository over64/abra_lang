// Generated from /home/over/build/test_lang/antlr/Hello.g4 by ANTLR 4.5.3
package grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#valDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValDef(HelloParser.ValDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(HelloParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#fnArg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnArg(HelloParser.FnArgContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#fnArgs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnArgs(HelloParser.FnArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#llvmFnDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlvmFnDef(HelloParser.LlvmFnDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#abraFnDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbraFnDef(HelloParser.AbraFnDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#fnDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnDef(HelloParser.FnDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#fnBodyDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFnBodyDef(HelloParser.FnBodyDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#lang}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLang(HelloParser.LangContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#structParamDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructParamDef(HelloParser.StructParamDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#llvmTypeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLlvmTypeDef(HelloParser.LlvmTypeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#abraTypeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAbraTypeDef(HelloParser.AbraTypeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#typeDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDef(HelloParser.TypeDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#access}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccess(HelloParser.AccessContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atomExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtomExp(HelloParser.AtomExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code directCallExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectCallExp(HelloParser.DirectCallExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code priority3Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriority3Expr(HelloParser.Priority3ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpCall}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpCall(HelloParser.ParenExpCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code priority2Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriority2Expr(HelloParser.Priority2ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code accessExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAccessExp(HelloParser.AccessExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code simpleParen}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleParen(HelloParser.SimpleParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code priority1Expr}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPriority1Expr(HelloParser.Priority1ExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code varIdentExp}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarIdentExp(HelloParser.VarIdentExpContext ctx);
}