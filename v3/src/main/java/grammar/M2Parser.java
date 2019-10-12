// Generated from /home/over/build/eva_lang/v3/grammar/M2Parser.g4 by ANTLR 4.7.2
package grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class M2Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINUS=1, PLUS=2, MUL=3, DIV=4, EXCL=5, DOT=6, RB=7, LB=8, COMMA=9, MORE_=10, 
		MORE_EQ=11, LESS=12, LESS_EQ=13, EQ=14, EQEQ=15, NOTEQ=16, SEMI=17, IF=18, 
		DO=19, ELSE=20, CBO=21, DOLLAR_CBO=22, CBC=23, LOGIC_OR=24, LOGIC_AND=25, 
		WHILE=26, VAL=27, VAR=28, CON=29, ARROW_RIGHT=30, TYPE=31, BACK_SLASH=32, 
		SELF=33, MATCH_SELF=34, DEF=35, LAMBDA=36, IMPORT=37, WITH=38, MATCH=39, 
		RETURN=40, BREAK=41, CONTINUE=42, IS=43, UNLESS=44, REF=45, DASH=46, VERT_LINE=47, 
		BRACKET_LEFT=48, BRACKET_RIGTH=49, LlBegin=50, WS=51, NL=52, COMMENT=53, 
		IntLiteral=54, HexLiteral=55, FloatLiteral=56, BooleanLiteral=57, NoneLiteral=58, 
		StringLiteral=59, VarId=60, TypeId=61, MatchId=62, LLVM_NL=63, LLVM_WS=64, 
		IrLine=65, LL_End=66, LL_Dot=67;
	public static final int
		RULE_sp = 0, RULE_literal = 1, RULE_id = 2, RULE_expression = 3, RULE_tuple = 4, 
		RULE_fieldTh = 5, RULE_scalarTh = 6, RULE_fnTh = 7, RULE_structTh = 8, 
		RULE_nonUnionTh = 9, RULE_unionTh = 10, RULE_genericTh = 11, RULE_typeHint = 12, 
		RULE_is = 13, RULE_store = 14, RULE_ret = 15, RULE_break_ = 16, RULE_continue_ = 17, 
		RULE_while_stat = 18, RULE_fnArg = 19, RULE_lambda = 20, RULE_blockBody = 21, 
		RULE_scalarType = 22, RULE_typeField = 23, RULE_structType = 24, RULE_unionType = 25, 
		RULE_type = 26, RULE_def = 27, RULE_importEntry = 28, RULE_import_ = 29, 
		RULE_level1 = 30, RULE_module = 31, RULE_llvmBody = 32, RULE_llvm = 33;
	private static String[] makeRuleNames() {
		return new String[] {
			"sp", "literal", "id", "expression", "tuple", "fieldTh", "scalarTh", 
			"fnTh", "structTh", "nonUnionTh", "unionTh", "genericTh", "typeHint", 
			"is", "store", "ret", "break_", "continue_", "while_stat", "fnArg", "lambda", 
			"blockBody", "scalarType", "typeField", "structType", "unionType", "type", 
			"def", "importEntry", "import_", "level1", "module", "llvmBody", "llvm"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'+'", "'*'", "'/'", "'!'", null, "')'", "'('", "','", "'>'", 
			"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'do'", 
			"'else'", "'{'", "'$('", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", 
			"':'", "'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'lambda'", 
			"'import'", "'with'", "'match'", "'return'", "'break'", "'continue'", 
			"'is'", "'unless'", "'ref'", "'_'", "'|'", "'['", "']'", null, null, 
			null, null, null, null, null, null, "'none'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
			"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
			"IF", "DO", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
			"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
			"MATCH_SELF", "DEF", "LAMBDA", "IMPORT", "WITH", "MATCH", "RETURN", "BREAK", 
			"CONTINUE", "IS", "UNLESS", "REF", "DASH", "VERT_LINE", "BRACKET_LEFT", 
			"BRACKET_RIGTH", "LlBegin", "WS", "NL", "COMMENT", "IntLiteral", "HexLiteral", 
			"FloatLiteral", "BooleanLiteral", "NoneLiteral", "StringLiteral", "VarId", 
			"TypeId", "MatchId", "LLVM_NL", "LLVM_WS", "IrLine", "LL_End", "LL_Dot"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "M2Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public M2Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class SpContext extends ParserRuleContext {
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public List<TerminalNode> COMMENT() { return getTokens(M2Parser.COMMENT); }
		public TerminalNode COMMENT(int i) {
			return getToken(M2Parser.COMMENT, i);
		}
		public SpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitSp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpContext sp() throws RecognitionException {
		SpContext _localctx = new SpContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_sp);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(68);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(73);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode IntLiteral() { return getToken(M2Parser.IntLiteral, 0); }
		public TerminalNode HexLiteral() { return getToken(M2Parser.HexLiteral, 0); }
		public TerminalNode FloatLiteral() { return getToken(M2Parser.FloatLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(M2Parser.BooleanLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(M2Parser.StringLiteral, 0); }
		public TerminalNode NoneLiteral() { return getToken(M2Parser.NoneLiteral, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdContext extends ParserRuleContext {
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode SELF() { return getToken(M2Parser.SELF, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==VarId) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ExprConsContext extends ExpressionContext {
		public ScalarThContext scalarTh() {
			return getRuleContext(ScalarThContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public ExprConsContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprCons(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprUnlessContext extends ExpressionContext {
		public ExpressionContext expr;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode UNLESS() { return getToken(M2Parser.UNLESS, 0); }
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<IsContext> is() {
			return getRuleContexts(IsContext.class);
		}
		public IsContext is(int i) {
			return getRuleContext(IsContext.class,i);
		}
		public TerminalNode NL() { return getToken(M2Parser.NL, 0); }
		public TerminalNode WS() { return getToken(M2Parser.WS, 0); }
		public ExprUnlessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprUnless(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLiteralContext extends ExpressionContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public ExprLiteralContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprLiteral(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIfElseContext extends ExpressionContext {
		public ExpressionContext cond;
		public BlockBodyContext blockBody;
		public List<BlockBodyContext> doStat = new ArrayList<BlockBodyContext>();
		public List<BlockBodyContext> elseStat = new ArrayList<BlockBodyContext>();
		public TerminalNode IF() { return getToken(M2Parser.IF, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode DO() { return getToken(M2Parser.DO, 0); }
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(M2Parser.ELSE, 0); }
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public TerminalNode NL() { return getToken(M2Parser.NL, 0); }
		public TerminalNode WS() { return getToken(M2Parser.WS, 0); }
		public ExprIfElseContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLambdaContext extends ExpressionContext {
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public ExprLambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprLambda(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprParenContext extends ExpressionContext {
		public TerminalNode LB() { return getToken(M2Parser.LB, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RB() { return getToken(M2Parser.RB, 0); }
		public ExprParenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprParen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprCallContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public ExprCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPropContext extends ExpressionContext {
		public Token VarId;
		public List<Token> op = new ArrayList<Token>();
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode NL() { return getToken(M2Parser.NL, 0); }
		public TerminalNode WS() { return getToken(M2Parser.WS, 0); }
		public ExprPropContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprProp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprTupleContext extends ExpressionContext {
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public ExprTupleContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprTuple(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprIdContext extends ExpressionContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExprIdContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprId(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprUnaryCallContext extends ExpressionContext {
		public Token op;
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode EXCL() { return getToken(M2Parser.EXCL, 0); }
		public ExprUnaryCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprUnaryCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprInfixCallContext extends ExpressionContext {
		public Token op;
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public TerminalNode MUL() { return getToken(M2Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(M2Parser.DIV, 0); }
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public TerminalNode PLUS() { return getToken(M2Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(M2Parser.MINUS, 0); }
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode MORE_() { return getToken(M2Parser.MORE_, 0); }
		public TerminalNode LESS() { return getToken(M2Parser.LESS, 0); }
		public TerminalNode LESS_EQ() { return getToken(M2Parser.LESS_EQ, 0); }
		public TerminalNode MORE_EQ() { return getToken(M2Parser.MORE_EQ, 0); }
		public TerminalNode EQEQ() { return getToken(M2Parser.EQEQ, 0); }
		public TerminalNode NOTEQ() { return getToken(M2Parser.NOTEQ, 0); }
		public TerminalNode LOGIC_OR() { return getToken(M2Parser.LOGIC_OR, 0); }
		public TerminalNode LOGIC_AND() { return getToken(M2Parser.LOGIC_AND, 0); }
		public ExprInfixCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprInfixCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprSelfCallContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode MUL() { return getToken(M2Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(M2Parser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(M2Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(M2Parser.MINUS, 0); }
		public TerminalNode MORE_() { return getToken(M2Parser.MORE_, 0); }
		public TerminalNode LESS() { return getToken(M2Parser.LESS, 0); }
		public TerminalNode LESS_EQ() { return getToken(M2Parser.LESS_EQ, 0); }
		public TerminalNode MORE_EQ() { return getToken(M2Parser.MORE_EQ, 0); }
		public TerminalNode EQEQ() { return getToken(M2Parser.EQEQ, 0); }
		public TerminalNode NOTEQ() { return getToken(M2Parser.NOTEQ, 0); }
		public TerminalNode NL() { return getToken(M2Parser.NL, 0); }
		public TerminalNode WS() { return getToken(M2Parser.WS, 0); }
		public ExprSelfCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprSelfCall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(79);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				id();
				}
				break;
			case 3:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				match(LB);
				setState(82);
				sp();
				setState(83);
				expression(0);
				setState(84);
				sp();
				setState(85);
				match(RB);
				}
				break;
			case 4:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(87);
				tuple();
				}
				break;
			case 5:
				{
				_localctx = new ExprConsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				scalarTh();
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==WS) {
					{
					{
					setState(89);
					match(WS);
					}
					}
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(95);
				tuple();
				}
				break;
			case 6:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(97);
				lambda();
				}
				break;
			case 7:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(99);
				sp();
				setState(100);
				expression(7);
				}
				break;
			case 8:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(102);
				match(IF);
				setState(103);
				sp();
				setState(104);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(105);
				sp();
				setState(106);
				match(DO);
				setState(107);
				sp();
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << WHILE) | (1L << SELF) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(108);
					((ExprIfElseContext)_localctx).blockBody = blockBody();
					((ExprIfElseContext)_localctx).doStat.add(((ExprIfElseContext)_localctx).blockBody);
					}
					}
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(123);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(114);
					sp();
					setState(115);
					match(ELSE);
					setState(116);
					sp();
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << WHILE) | (1L << SELF) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(117);
						((ExprIfElseContext)_localctx).blockBody = blockBody();
						((ExprIfElseContext)_localctx).elseStat.add(((ExprIfElseContext)_localctx).blockBody);
						}
						}
						setState(122);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS || _la==NL) {
					{
					setState(125);
					_la = _input.LA(1);
					if ( !(_la==WS || _la==NL) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(128);
				match(DOT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(232);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(230);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(132);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(136);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(133);
							match(WS);
							}
							}
							setState(138);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(139);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(140);
						sp();
						setState(141);
						expression(7);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(143);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(147);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(144);
							match(WS);
							}
							}
							setState(149);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(150);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << VarId))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(151);
						sp();
						setState(152);
						expression(6);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(154);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(158);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(155);
							match(WS);
							}
							}
							setState(160);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(161);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(162);
						sp();
						setState(163);
						expression(5);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(169);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(166);
							match(WS);
							}
							}
							setState(171);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(172);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQEQ || _la==NOTEQ) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(173);
						sp();
						setState(174);
						expression(4);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(176);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(180);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(177);
							match(WS);
							}
							}
							setState(182);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(183);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LOGIC_OR || _la==LOGIC_AND) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(184);
						sp();
						setState(185);
						expression(3);
						}
						break;
					case 6:
						{
						_localctx = new ExprUnlessContext(new ExpressionContext(_parentctx, _parentState));
						((ExprUnlessContext)_localctx).expr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(188);
						sp();
						setState(189);
						match(UNLESS);
						setState(190);
						sp();
						setState(192); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(191);
							is();
							}
							}
							setState(194); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==IS );
						setState(197);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS || _la==NL) {
							{
							setState(196);
							_la = _input.LA(1);
							if ( !(_la==WS || _la==NL) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
						}

						setState(199);
						match(DOT);
						}
						break;
					case 7:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(201);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(206);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(202);
							match(NL);
							setState(204);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(203);
								match(WS);
								}
							}

							}
						}

						setState(208);
						match(DOT);
						setState(209);
						((ExprSelfCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << VarId))) != 0)) ) {
							((ExprSelfCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(210);
						sp();
						setState(211);
						tuple();
						}
						break;
					case 8:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(213);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(217);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(214);
							match(WS);
							}
							}
							setState(219);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(220);
						tuple();
						}
						break;
					case 9:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(221);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(226);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(222);
							match(NL);
							setState(224);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(223);
								match(WS);
								}
							}

							}
						}

						setState(228);
						match(DOT);
						setState(229);
						((ExprPropContext)_localctx).VarId = match(VarId);
						((ExprPropContext)_localctx).op.add(((ExprPropContext)_localctx).VarId);
						}
						break;
					}
					} 
				}
				setState(234);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class TupleContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(M2Parser.LB, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode RB() { return getToken(M2Parser.RB, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public TerminalNode WITH() { return getToken(M2Parser.WITH, 0); }
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public TupleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tuple; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitTuple(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupleContext tuple() throws RecognitionException {
		TupleContext _localctx = new TupleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tuple);
		int _la;
		try {
			setState(272);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(235);
				match(LB);
				setState(236);
				sp();
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << SELF) | (1L << WITH) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(237);
					expression(0);
					setState(238);
					sp();
					setState(246);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(239);
						match(COMMA);
						setState(240);
						sp();
						setState(241);
						expression(0);
						setState(242);
						sp();
						}
						}
						setState(248);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(251);
				sp();
				setState(252);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				match(WITH);
				setState(255);
				sp();
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << SELF) | (1L << WITH) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(256);
					expression(0);
					setState(257);
					sp();
					setState(264);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(258);
						match(COMMA);
						setState(259);
						sp();
						setState(260);
						expression(0);
						}
						}
						setState(266);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(269);
				sp();
				setState(270);
				match(DOT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldThContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode CON() { return getToken(M2Parser.CON, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public FieldThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFieldTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldThContext fieldTh() throws RecognitionException {
		FieldThContext _localctx = new FieldThContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fieldTh);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			id();
			setState(275);
			sp();
			setState(276);
			match(CON);
			setState(277);
			sp();
			setState(278);
			typeHint();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarThContext extends ParserRuleContext {
		public Token typeName;
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public TerminalNode BRACKET_LEFT() { return getToken(M2Parser.BRACKET_LEFT, 0); }
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
		}
		public TerminalNode BRACKET_RIGTH() { return getToken(M2Parser.BRACKET_RIGTH, 0); }
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public ScalarThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitScalarTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarThContext scalarTh() throws RecognitionException {
		ScalarThContext _localctx = new ScalarThContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_scalarTh);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(280);
				id();
				setState(281);
				sp();
				setState(282);
				match(DOT);
				setState(283);
				sp();
				}
			}

			setState(287);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(288);
				match(BRACKET_LEFT);
				setState(289);
				sp();
				setState(290);
				typeHint();
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(291);
					sp();
					setState(292);
					match(COMMA);
					setState(293);
					sp();
					setState(294);
					typeHint();
					}
					}
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(301);
				match(BRACKET_RIGTH);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FnThContext extends ParserRuleContext {
		public TypeHintContext typeHint;
		public List<TypeHintContext> args = new ArrayList<TypeHintContext>();
		public TypeHintContext rett;
		public TerminalNode LB() { return getToken(M2Parser.LB, 0); }
		public TerminalNode RB() { return getToken(M2Parser.RB, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode ARROW_RIGHT() { return getToken(M2Parser.ARROW_RIGHT, 0); }
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public FnThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFnTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnThContext fnTh() throws RecognitionException {
		FnThContext _localctx = new FnThContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fnTh);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(305);
			match(LB);
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << WS) | (1L << NL) | (1L << COMMENT) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(306);
				sp();
				setState(307);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(308);
					sp();
					setState(309);
					match(COMMA);
					setState(310);
					sp();
					setState(311);
					((FnThContext)_localctx).typeHint = typeHint();
					((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
					}
					}
					setState(317);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(320);
			match(RB);
			setState(321);
			sp();
			setState(322);
			match(ARROW_RIGHT);
			setState(323);
			sp();
			setState(324);
			((FnThContext)_localctx).rett = typeHint();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructThContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(M2Parser.LB, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<FieldThContext> fieldTh() {
			return getRuleContexts(FieldThContext.class);
		}
		public FieldThContext fieldTh(int i) {
			return getRuleContext(FieldThContext.class,i);
		}
		public TerminalNode RB() { return getToken(M2Parser.RB, 0); }
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public StructThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitStructTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructThContext structTh() throws RecognitionException {
		StructThContext _localctx = new StructThContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_structTh);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(LB);
			setState(327);
			sp();
			setState(328);
			fieldTh();
			setState(334); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(329);
				sp();
				setState(330);
				match(COMMA);
				setState(331);
				sp();
				setState(332);
				fieldTh();
				}
				}
				setState(336); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(338);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonUnionThContext extends ParserRuleContext {
		public TerminalNode LB() { return getToken(M2Parser.LB, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public UnionThContext unionTh() {
			return getRuleContext(UnionThContext.class,0);
		}
		public TerminalNode RB() { return getToken(M2Parser.RB, 0); }
		public ScalarThContext scalarTh() {
			return getRuleContext(ScalarThContext.class,0);
		}
		public FnThContext fnTh() {
			return getRuleContext(FnThContext.class,0);
		}
		public StructThContext structTh() {
			return getRuleContext(StructThContext.class,0);
		}
		public GenericThContext genericTh() {
			return getRuleContext(GenericThContext.class,0);
		}
		public NonUnionThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonUnionTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitNonUnionTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonUnionThContext nonUnionTh() throws RecognitionException {
		NonUnionThContext _localctx = new NonUnionThContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_nonUnionTh);
		try {
			setState(350);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,32,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(340);
				match(LB);
				setState(341);
				sp();
				setState(342);
				unionTh();
				setState(343);
				sp();
				setState(344);
				match(RB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(346);
				scalarTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(347);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(348);
				structTh();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(349);
				genericTh();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionThContext extends ParserRuleContext {
		public List<NonUnionThContext> nonUnionTh() {
			return getRuleContexts(NonUnionThContext.class);
		}
		public NonUnionThContext nonUnionTh(int i) {
			return getRuleContext(NonUnionThContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> VERT_LINE() { return getTokens(M2Parser.VERT_LINE); }
		public TerminalNode VERT_LINE(int i) {
			return getToken(M2Parser.VERT_LINE, i);
		}
		public UnionThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitUnionTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionThContext unionTh() throws RecognitionException {
		UnionThContext _localctx = new UnionThContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_unionTh);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(352);
			nonUnionTh();
			setState(358); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(353);
					sp();
					setState(354);
					match(VERT_LINE);
					setState(355);
					sp();
					setState(356);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(360); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,33,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericThContext extends ParserRuleContext {
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public GenericThContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericTh; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitGenericTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GenericThContext genericTh() throws RecognitionException {
		GenericThContext _localctx = new GenericThContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_genericTh);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(362);
			match(VarId);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeHintContext extends ParserRuleContext {
		public ScalarThContext scalarTh() {
			return getRuleContext(ScalarThContext.class,0);
		}
		public StructThContext structTh() {
			return getRuleContext(StructThContext.class,0);
		}
		public FnThContext fnTh() {
			return getRuleContext(FnThContext.class,0);
		}
		public UnionThContext unionTh() {
			return getRuleContext(UnionThContext.class,0);
		}
		public GenericThContext genericTh() {
			return getRuleContext(GenericThContext.class,0);
		}
		public TypeHintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeHint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitTypeHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeHintContext typeHint() throws RecognitionException {
		TypeHintContext _localctx = new TypeHintContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_typeHint);
		try {
			setState(369);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(364);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(365);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(366);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(367);
				unionTh();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(368);
				genericTh();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IsContext extends ParserRuleContext {
		public TerminalNode IS() { return getToken(M2Parser.IS, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode DO() { return getToken(M2Parser.DO, 0); }
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode CON() { return getToken(M2Parser.CON, 0); }
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public IsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_is; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitIs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IsContext is() throws RecognitionException {
		IsContext _localctx = new IsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_is);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(371);
			match(IS);
			setState(372);
			sp();
			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(373);
				match(VarId);
				setState(374);
				sp();
				setState(375);
				match(CON);
				setState(376);
				sp();
				}
				break;
			}
			setState(380);
			typeHint();
			setState(381);
			sp();
			setState(382);
			match(DO);
			setState(383);
			sp();
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << WHILE) | (1L << SELF) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(384);
				blockBody();
				}
				}
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StoreContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(M2Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(M2Parser.DOT, i);
		}
		public List<TerminalNode> VarId() { return getTokens(M2Parser.VarId); }
		public TerminalNode VarId(int i) {
			return getToken(M2Parser.VarId, i);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode CON() { return getToken(M2Parser.CON, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public StoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_store; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitStore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StoreContext store() throws RecognitionException {
		StoreContext _localctx = new StoreContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_store);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			id();
			setState(401);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(395);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(391);
						match(NL);
						setState(393);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(392);
							match(WS);
							}
						}

						}
					}

					setState(397);
					match(DOT);
					setState(398);
					match(VarId);
					}
					} 
				}
				setState(403);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
			}
			setState(404);
			sp();
			setState(410);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(405);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(406);
				match(CON);
				setState(407);
				sp();
				setState(408);
				typeHint();
				}
				}
				break;
			case EQ:
			case WS:
			case NL:
			case COMMENT:
				break;
			default:
				break;
			}
			setState(412);
			sp();
			setState(413);
			match(EQ);
			setState(414);
			sp();
			setState(415);
			expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RetContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(M2Parser.RETURN, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ret; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitRet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RetContext ret() throws RecognitionException {
		RetContext _localctx = new RetContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_ret);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			match(RETURN);
			setState(418);
			sp();
			setState(420);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				{
				setState(419);
				expression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Break_Context extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(M2Parser.BREAK, 0); }
		public Break_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_break_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitBreak_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Break_Context break_() throws RecognitionException {
		Break_Context _localctx = new Break_Context(_ctx, getState());
		enterRule(_localctx, 32, RULE_break_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			match(BREAK);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Continue_Context extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(M2Parser.CONTINUE, 0); }
		public Continue_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continue_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitContinue_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Continue_Context continue_() throws RecognitionException {
		Continue_Context _localctx = new Continue_Context(_ctx, getState());
		enterRule(_localctx, 34, RULE_continue_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(424);
			match(CONTINUE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class While_statContext extends ParserRuleContext {
		public ExpressionContext cond;
		public TerminalNode WHILE() { return getToken(M2Parser.WHILE, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode DO() { return getToken(M2Parser.DO, 0); }
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public While_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_while_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitWhile_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statContext while_stat() throws RecognitionException {
		While_statContext _localctx = new While_statContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_while_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(WHILE);
			setState(427);
			sp();
			setState(428);
			((While_statContext)_localctx).cond = expression(0);
			setState(429);
			sp();
			setState(430);
			match(DO);
			setState(431);
			sp();
			setState(435);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << WHILE) | (1L << SELF) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(432);
				blockBody();
				}
				}
				setState(437);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(438);
			match(DOT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FnArgContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode CON() { return getToken(M2Parser.CON, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FnArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnArg; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFnArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnArgContext fnArg() throws RecognitionException {
		FnArgContext _localctx = new FnArgContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			id();
			setState(441);
			sp();
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(442);
				match(CON);
				setState(443);
				sp();
				setState(444);
				typeHint();
				setState(450);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
				case 1:
					{
					setState(445);
					sp();
					setState(446);
					match(EQ);
					setState(447);
					sp();
					setState(448);
					expression(0);
					}
					break;
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LambdaContext extends ParserRuleContext {
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode LOGIC_OR() { return getToken(M2Parser.LOGIC_OR, 0); }
		public List<TerminalNode> VERT_LINE() { return getTokens(M2Parser.VERT_LINE); }
		public TerminalNode VERT_LINE(int i) {
			return getToken(M2Parser.VERT_LINE, i);
		}
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_lambda);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LOGIC_OR:
				{
				setState(454);
				match(LOGIC_OR);
				}
				break;
			case VERT_LINE:
				{
				setState(455);
				match(VERT_LINE);
				setState(468);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
				case 1:
					{
					setState(456);
					sp();
					setState(457);
					fnArg();
					setState(458);
					sp();
					setState(465);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(459);
						match(COMMA);
						setState(460);
						sp();
						setState(461);
						fnArg();
						}
						}
						setState(467);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(470);
				sp();
				setState(471);
				match(VERT_LINE);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(475);
			sp();
			setState(479);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(476);
					blockBody();
					}
					} 
				}
				setState(481);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
			}
			setState(482);
			sp();
			setState(484);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(483);
				match(DOT);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockBodyContext extends ParserRuleContext {
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public StoreContext store() {
			return getRuleContext(StoreContext.class,0);
		}
		public Break_Context break_() {
			return getRuleContext(Break_Context.class,0);
		}
		public Continue_Context continue_() {
			return getRuleContext(Continue_Context.class,0);
		}
		public While_statContext while_stat() {
			return getRuleContext(While_statContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
		public TerminalNode SEMI() { return getToken(M2Parser.SEMI, 0); }
		public BlockBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitBlockBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockBodyContext blockBody() throws RecognitionException {
		BlockBodyContext _localctx = new BlockBodyContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_blockBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(486);
				store();
				}
				break;
			case 2:
				{
				setState(487);
				break_();
				}
				break;
			case 3:
				{
				setState(488);
				continue_();
				}
				break;
			case 4:
				{
				setState(489);
				while_stat();
				}
				break;
			case 5:
				{
				setState(490);
				expression(0);
				}
				break;
			case 6:
				{
				setState(491);
				ret();
				}
				break;
			}
			setState(494);
			sp();
			setState(496);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(495);
				match(SEMI);
				}
				break;
			}
			setState(498);
			sp();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScalarTypeContext extends ParserRuleContext {
		public Token tname;
		public GenericThContext genericTh;
		public List<GenericThContext> params = new ArrayList<GenericThContext>();
		public TerminalNode TYPE() { return getToken(M2Parser.TYPE, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public LlvmContext llvm() {
			return getRuleContext(LlvmContext.class,0);
		}
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public TerminalNode BRACKET_LEFT() { return getToken(M2Parser.BRACKET_LEFT, 0); }
		public TerminalNode BRACKET_RIGTH() { return getToken(M2Parser.BRACKET_RIGTH, 0); }
		public TerminalNode REF() { return getToken(M2Parser.REF, 0); }
		public List<GenericThContext> genericTh() {
			return getRuleContexts(GenericThContext.class);
		}
		public GenericThContext genericTh(int i) {
			return getRuleContext(GenericThContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public ScalarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitScalarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeContext scalarType() throws RecognitionException {
		ScalarTypeContext _localctx = new ScalarTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_scalarType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			match(TYPE);
			setState(501);
			sp();
			setState(502);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(520);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(503);
				sp();
				setState(504);
				match(BRACKET_LEFT);
				setState(505);
				sp();
				setState(506);
				((ScalarTypeContext)_localctx).genericTh = genericTh();
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).genericTh);
				setState(514);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(507);
						sp();
						setState(508);
						match(COMMA);
						setState(509);
						sp();
						setState(510);
						((ScalarTypeContext)_localctx).genericTh = genericTh();
						((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).genericTh);
						}
						} 
					}
					setState(516);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
				}
				setState(517);
				sp();
				setState(518);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(522);
			sp();
			setState(523);
			match(EQ);
			setState(524);
			sp();
			setState(526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(525);
				match(REF);
				}
			}

			setState(528);
			sp();
			setState(529);
			llvm();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeFieldContext extends ParserRuleContext {
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode CON() { return getToken(M2Parser.CON, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode SELF() { return getToken(M2Parser.SELF, 0); }
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TypeFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitTypeField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeFieldContext typeField() throws RecognitionException {
		TypeFieldContext _localctx = new TypeFieldContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(531);
				match(SELF);
				}
			}

			setState(534);
			sp();
			setState(535);
			match(VarId);
			setState(536);
			sp();
			setState(537);
			match(CON);
			setState(538);
			sp();
			setState(539);
			typeHint();
			setState(545);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(540);
				sp();
				setState(541);
				match(EQ);
				setState(542);
				sp();
				setState(543);
				expression(0);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StructTypeContext extends ParserRuleContext {
		public Token name;
		public GenericThContext genericTh;
		public List<GenericThContext> params = new ArrayList<GenericThContext>();
		public TerminalNode TYPE() { return getToken(M2Parser.TYPE, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public TerminalNode LB() { return getToken(M2Parser.LB, 0); }
		public List<TypeFieldContext> typeField() {
			return getRuleContexts(TypeFieldContext.class);
		}
		public TypeFieldContext typeField(int i) {
			return getRuleContext(TypeFieldContext.class,i);
		}
		public TerminalNode RB() { return getToken(M2Parser.RB, 0); }
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public TerminalNode BRACKET_LEFT() { return getToken(M2Parser.BRACKET_LEFT, 0); }
		public TerminalNode BRACKET_RIGTH() { return getToken(M2Parser.BRACKET_RIGTH, 0); }
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public List<GenericThContext> genericTh() {
			return getRuleContexts(GenericThContext.class);
		}
		public GenericThContext genericTh(int i) {
			return getRuleContext(GenericThContext.class,i);
		}
		public StructTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitStructType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_structType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(547);
			match(TYPE);
			setState(548);
			sp();
			setState(549);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(567);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(550);
				sp();
				setState(551);
				match(BRACKET_LEFT);
				setState(552);
				sp();
				setState(553);
				((StructTypeContext)_localctx).genericTh = genericTh();
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).genericTh);
				setState(561);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(554);
						sp();
						setState(555);
						match(COMMA);
						setState(556);
						sp();
						setState(557);
						((StructTypeContext)_localctx).genericTh = genericTh();
						((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).genericTh);
						}
						} 
					}
					setState(563);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
				}
				setState(564);
				sp();
				setState(565);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(569);
			sp();
			setState(570);
			match(EQ);
			setState(571);
			sp();
			setState(572);
			match(LB);
			setState(573);
			sp();
			setState(574);
			typeField();
			setState(581);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(575);
				match(COMMA);
				setState(576);
				sp();
				setState(577);
				typeField();
				}
				}
				setState(583);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(584);
			sp();
			setState(585);
			match(RB);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionTypeContext extends ParserRuleContext {
		public Token name;
		public TerminalNode TYPE() { return getToken(M2Parser.TYPE, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public List<NonUnionThContext> nonUnionTh() {
			return getRuleContexts(NonUnionThContext.class);
		}
		public NonUnionThContext nonUnionTh(int i) {
			return getRuleContext(NonUnionThContext.class,i);
		}
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public List<TerminalNode> VERT_LINE() { return getTokens(M2Parser.VERT_LINE); }
		public TerminalNode VERT_LINE(int i) {
			return getToken(M2Parser.VERT_LINE, i);
		}
		public UnionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitUnionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionTypeContext unionType() throws RecognitionException {
		UnionTypeContext _localctx = new UnionTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_unionType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(587);
			match(TYPE);
			setState(588);
			sp();
			setState(589);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(590);
			sp();
			setState(591);
			match(EQ);
			setState(592);
			sp();
			setState(593);
			nonUnionTh();
			setState(599); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(594);
					sp();
					setState(595);
					match(VERT_LINE);
					setState(596);
					sp();
					setState(597);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(601); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public StructTypeContext structType() {
			return getRuleContext(StructTypeContext.class,0);
		}
		public UnionTypeContext unionType() {
			return getRuleContext(UnionTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_type);
		try {
			setState(606);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(603);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(604);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(605);
				unionType();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefContext extends ParserRuleContext {
		public Token name;
		public TerminalNode DEF() { return getToken(M2Parser.DEF, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode EQ() { return getToken(M2Parser.EQ, 0); }
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode EXCL() { return getToken(M2Parser.EXCL, 0); }
		public TerminalNode MUL() { return getToken(M2Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(M2Parser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(M2Parser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(M2Parser.MINUS, 0); }
		public TerminalNode MORE_() { return getToken(M2Parser.MORE_, 0); }
		public TerminalNode LESS() { return getToken(M2Parser.LESS, 0); }
		public TerminalNode LESS_EQ() { return getToken(M2Parser.LESS_EQ, 0); }
		public TerminalNode MORE_EQ() { return getToken(M2Parser.MORE_EQ, 0); }
		public TerminalNode EQEQ() { return getToken(M2Parser.EQEQ, 0); }
		public TerminalNode NOTEQ() { return getToken(M2Parser.NOTEQ, 0); }
		public TerminalNode LOGIC_OR() { return getToken(M2Parser.LOGIC_OR, 0); }
		public TerminalNode LOGIC_AND() { return getToken(M2Parser.LOGIC_AND, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public LlvmContext llvm() {
			return getRuleContext(LlvmContext.class,0);
		}
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public TerminalNode DO() { return getToken(M2Parser.DO, 0); }
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(608);
			match(DEF);
			setState(609);
			sp();
			setState(610);
			((DefContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << EXCL) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << VarId))) != 0)) ) {
				((DefContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(611);
			sp();
			setState(612);
			match(EQ);
			setState(613);
			sp();
			setState(656);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				{
				setState(628);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
				case 1:
					{
					setState(614);
					fnArg();
					setState(615);
					sp();
					setState(622);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(616);
						match(COMMA);
						setState(617);
						sp();
						setState(618);
						fnArg();
						}
						}
						setState(624);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(625);
					sp();
					setState(626);
					match(DO);
					}
					break;
				}
				setState(630);
				sp();
				{
				{
				setState(634);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << LOGIC_OR) | (1L << WHILE) | (1L << SELF) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << VERT_LINE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(631);
					blockBody();
					}
					}
					setState(636);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(637);
				sp();
				setState(638);
				match(DOT);
				}
				}
				}
				}
				break;
			case 2:
				{
				{
				setState(653);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SELF || _la==VarId) {
					{
					setState(640);
					fnArg();
					setState(641);
					sp();
					setState(648);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(642);
						match(COMMA);
						setState(643);
						sp();
						setState(644);
						fnArg();
						}
						}
						setState(650);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(651);
					sp();
					}
				}

				setState(655);
				llvm();
				}
				}
				break;
			}
			setState(659);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(658);
				typeHint();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportEntryContext extends ParserRuleContext {
		public Token local;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> VarId() { return getTokens(M2Parser.VarId); }
		public TerminalNode VarId(int i) {
			return getToken(M2Parser.VarId, i);
		}
		public List<TerminalNode> DOT() { return getTokens(M2Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(M2Parser.DOT, i);
		}
		public TerminalNode WITH() { return getToken(M2Parser.WITH, 0); }
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
		}
		public ImportEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importEntry; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitImportEntry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImportEntryContext importEntry() throws RecognitionException {
		ImportEntryContext _localctx = new ImportEntryContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_importEntry);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(661);
			sp();
			setState(663);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(662);
				((ImportEntryContext)_localctx).local = match(DOT);
				}
			}

			setState(665);
			match(VarId);
			setState(670);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(666);
				match(DOT);
				setState(667);
				match(VarId);
				}
				}
				setState(672);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(686);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,72,_ctx) ) {
			case 1:
				{
				setState(673);
				sp();
				setState(674);
				match(WITH);
				setState(675);
				sp();
				setState(676);
				match(TypeId);
				setState(677);
				sp();
				setState(683);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(678);
						sp();
						setState(679);
						match(TypeId);
						}
						} 
					}
					setState(685);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Import_Context extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(M2Parser.IMPORT, 0); }
		public List<ImportEntryContext> importEntry() {
			return getRuleContexts(ImportEntryContext.class);
		}
		public ImportEntryContext importEntry(int i) {
			return getRuleContext(ImportEntryContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public Import_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitImport_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_Context import_() throws RecognitionException {
		Import_Context _localctx = new Import_Context(_ctx, getState());
		enterRule(_localctx, 58, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(688);
			match(IMPORT);
			setState(689);
			importEntry();
			setState(694);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(690);
				match(COMMA);
				setState(691);
				importEntry();
				}
				}
				setState(696);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Level1Context extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DefContext def() {
			return getRuleContext(DefContext.class,0);
		}
		public Level1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_level1; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLevel1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level1Context level1() throws RecognitionException {
		Level1Context _localctx = new Level1Context(_ctx, getState());
		enterRule(_localctx, 60, RULE_level1);
		try {
			setState(699);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(697);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(698);
				def();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModuleContext extends ParserRuleContext {
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode EOF() { return getToken(M2Parser.EOF, 0); }
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
		public List<LlvmContext> llvm() {
			return getRuleContexts(LlvmContext.class);
		}
		public LlvmContext llvm(int i) {
			return getRuleContext(LlvmContext.class,i);
		}
		public List<Level1Context> level1() {
			return getRuleContexts(Level1Context.class);
		}
		public Level1Context level1(int i) {
			return getRuleContext(Level1Context.class,i);
		}
		public ModuleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_module; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitModule(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModuleContext module() throws RecognitionException {
		ModuleContext _localctx = new ModuleContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(701);
			sp();
			setState(703);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(702);
				import_();
				}
			}

			setState(705);
			sp();
			setState(711);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(706);
					sp();
					setState(707);
					llvm();
					}
					} 
				}
				setState(713);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			}
			setState(714);
			sp();
			setState(720);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(715);
				level1();
				setState(716);
				sp();
				}
				}
				setState(722);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(723);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LlvmBodyContext extends ParserRuleContext {
		public List<TerminalNode> LLVM_NL() { return getTokens(M2Parser.LLVM_NL); }
		public TerminalNode LLVM_NL(int i) {
			return getToken(M2Parser.LLVM_NL, i);
		}
		public List<TerminalNode> LLVM_WS() { return getTokens(M2Parser.LLVM_WS); }
		public TerminalNode LLVM_WS(int i) {
			return getToken(M2Parser.LLVM_WS, i);
		}
		public List<TerminalNode> IrLine() { return getTokens(M2Parser.IrLine); }
		public TerminalNode IrLine(int i) {
			return getToken(M2Parser.IrLine, i);
		}
		public List<TerminalNode> LL_Dot() { return getTokens(M2Parser.LL_Dot); }
		public TerminalNode LL_Dot(int i) {
			return getToken(M2Parser.LL_Dot, i);
		}
		public LlvmBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llvmBody; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLlvmBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LlvmBodyContext llvmBody() throws RecognitionException {
		LlvmBodyContext _localctx = new LlvmBodyContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_llvmBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(728);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (LLVM_NL - 63)) | (1L << (LLVM_WS - 63)) | (1L << (IrLine - 63)) | (1L << (LL_Dot - 63)))) != 0)) {
				{
				{
				setState(725);
				_la = _input.LA(1);
				if ( !(((((_la - 63)) & ~0x3f) == 0 && ((1L << (_la - 63)) & ((1L << (LLVM_NL - 63)) | (1L << (LLVM_WS - 63)) | (1L << (IrLine - 63)) | (1L << (LL_Dot - 63)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(730);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LlvmContext extends ParserRuleContext {
		public TerminalNode LlBegin() { return getToken(M2Parser.LlBegin, 0); }
		public LlvmBodyContext llvmBody() {
			return getRuleContext(LlvmBodyContext.class,0);
		}
		public TerminalNode LL_End() { return getToken(M2Parser.LL_End, 0); }
		public LlvmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llvm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLlvm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LlvmContext llvm() throws RecognitionException {
		LlvmContext _localctx = new LlvmContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_llvm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(731);
			match(LlBegin);
			setState(732);
			llvmBody();
			setState(733);
			match(LL_End);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 3:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		case 1:
			return precpred(_ctx, 5);
		case 2:
			return precpred(_ctx, 4);
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		case 5:
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 10);
		case 8:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3E\u02e2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\7\2H\n\2\f\2\16\2K\13\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5]\n\5\f\5\16\5`\13\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5p\n\5\f\5\16\5s"+
		"\13\5\3\5\3\5\3\5\3\5\7\5y\n\5\f\5\16\5|\13\5\5\5~\n\5\3\5\5\5\u0081\n"+
		"\5\3\5\3\5\5\5\u0085\n\5\3\5\3\5\7\5\u0089\n\5\f\5\16\5\u008c\13\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\7\5\u0094\n\5\f\5\16\5\u0097\13\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\7\5\u009f\n\5\f\5\16\5\u00a2\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5"+
		"\u00aa\n\5\f\5\16\5\u00ad\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00b5\n\5\f"+
		"\5\16\5\u00b8\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5\u00c3\n\5\r"+
		"\5\16\5\u00c4\3\5\5\5\u00c8\n\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cf\n\5\5\5"+
		"\u00d1\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00da\n\5\f\5\16\5\u00dd\13"+
		"\5\3\5\3\5\3\5\3\5\5\5\u00e3\n\5\5\5\u00e5\n\5\3\5\3\5\7\5\u00e9\n\5\f"+
		"\5\16\5\u00ec\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00f7\n\6\f"+
		"\6\16\6\u00fa\13\6\5\6\u00fc\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6\u0109\n\6\f\6\16\6\u010c\13\6\5\6\u010e\n\6\3\6\3\6\3\6\5\6"+
		"\u0113\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u0120\n\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u012b\n\b\f\b\16\b\u012e\13\b\3"+
		"\b\3\b\5\b\u0132\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u013c\n\t\f\t"+
		"\16\t\u013f\13\t\5\t\u0141\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\6\n\u0151\n\n\r\n\16\n\u0152\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u0161\n\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\6\f\u0169\n\f\r\f\16\f\u016a\3\r\3\r\3\16\3\16\3\16\3\16\3\16\5\16"+
		"\u0174\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u017d\n\17\3\17\3"+
		"\17\3\17\3\17\3\17\7\17\u0184\n\17\f\17\16\17\u0187\13\17\3\20\3\20\3"+
		"\20\5\20\u018c\n\20\5\20\u018e\n\20\3\20\3\20\7\20\u0192\n\20\f\20\16"+
		"\20\u0195\13\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u019d\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\5\21\u01a7\n\21\3\22\3\22\3\23\3\23\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u01b4\n\24\f\24\16\24\u01b7\13\24"+
		"\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u01c5"+
		"\n\25\5\25\u01c7\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26"+
		"\u01d2\n\26\f\26\16\26\u01d5\13\26\5\26\u01d7\n\26\3\26\3\26\3\26\5\26"+
		"\u01dc\n\26\3\26\3\26\7\26\u01e0\n\26\f\26\16\26\u01e3\13\26\3\26\3\26"+
		"\5\26\u01e7\n\26\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u01ef\n\27\3\27\3"+
		"\27\5\27\u01f3\n\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\7\30\u0203\n\30\f\30\16\30\u0206\13\30\3\30\3\30"+
		"\3\30\5\30\u020b\n\30\3\30\3\30\3\30\3\30\5\30\u0211\n\30\3\30\3\30\3"+
		"\30\3\31\5\31\u0217\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\5\31\u0224\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\7\32\u0232\n\32\f\32\16\32\u0235\13\32\3\32\3\32\3\32"+
		"\5\32\u023a\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32"+
		"\u0246\n\32\f\32\16\32\u0249\13\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\6\33\u025a\n\33\r\33\16\33\u025b"+
		"\3\34\3\34\3\34\5\34\u0261\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\7\35\u026f\n\35\f\35\16\35\u0272\13\35\3\35\3\35"+
		"\3\35\5\35\u0277\n\35\3\35\3\35\7\35\u027b\n\35\f\35\16\35\u027e\13\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u0289\n\35\f\35\16"+
		"\35\u028c\13\35\3\35\3\35\5\35\u0290\n\35\3\35\5\35\u0293\n\35\3\35\5"+
		"\35\u0296\n\35\3\36\3\36\5\36\u029a\n\36\3\36\3\36\3\36\7\36\u029f\n\36"+
		"\f\36\16\36\u02a2\13\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u02ac"+
		"\n\36\f\36\16\36\u02af\13\36\5\36\u02b1\n\36\3\37\3\37\3\37\3\37\7\37"+
		"\u02b7\n\37\f\37\16\37\u02ba\13\37\3 \3 \5 \u02be\n \3!\3!\5!\u02c2\n"+
		"!\3!\3!\3!\3!\7!\u02c8\n!\f!\16!\u02cb\13!\3!\3!\3!\3!\7!\u02d1\n!\f!"+
		"\16!\u02d4\13!\3!\3!\3\"\7\"\u02d9\n\"\f\"\16\"\u02dc\13\"\3#\3#\3#\3"+
		"#\3#\2\3\b$\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BD\2\16\3\2\65\67\3\28=\4\2##>>\3\2\65\66\3\2\5\6\4\2\3\4>>\3\2\f"+
		"\17\3\2\21\22\3\2\32\33\6\2\3\6\f\17\21\22>>\7\2\3\7\f\17\21\22\32\33"+
		">>\4\2ACEE\2\u0327\2I\3\2\2\2\4L\3\2\2\2\6N\3\2\2\2\b\u0084\3\2\2\2\n"+
		"\u0112\3\2\2\2\f\u0114\3\2\2\2\16\u011f\3\2\2\2\20\u0133\3\2\2\2\22\u0148"+
		"\3\2\2\2\24\u0160\3\2\2\2\26\u0162\3\2\2\2\30\u016c\3\2\2\2\32\u0173\3"+
		"\2\2\2\34\u0175\3\2\2\2\36\u0188\3\2\2\2 \u01a3\3\2\2\2\"\u01a8\3\2\2"+
		"\2$\u01aa\3\2\2\2&\u01ac\3\2\2\2(\u01ba\3\2\2\2*\u01db\3\2\2\2,\u01ee"+
		"\3\2\2\2.\u01f6\3\2\2\2\60\u0216\3\2\2\2\62\u0225\3\2\2\2\64\u024d\3\2"+
		"\2\2\66\u0260\3\2\2\28\u0262\3\2\2\2:\u0297\3\2\2\2<\u02b2\3\2\2\2>\u02bd"+
		"\3\2\2\2@\u02bf\3\2\2\2B\u02da\3\2\2\2D\u02dd\3\2\2\2FH\t\2\2\2GF\3\2"+
		"\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2J\3\3\2\2\2KI\3\2\2\2LM\t\3\2\2M\5\3"+
		"\2\2\2NO\t\4\2\2O\7\3\2\2\2PQ\b\5\1\2Q\u0085\5\4\3\2R\u0085\5\6\4\2ST"+
		"\7\n\2\2TU\5\2\2\2UV\5\b\5\2VW\5\2\2\2WX\7\t\2\2X\u0085\3\2\2\2Y\u0085"+
		"\5\n\6\2Z^\5\16\b\2[]\7\65\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2"+
		"\2_a\3\2\2\2`^\3\2\2\2ab\5\n\6\2b\u0085\3\2\2\2c\u0085\5*\26\2de\7\7\2"+
		"\2ef\5\2\2\2fg\5\b\5\tg\u0085\3\2\2\2hi\7\24\2\2ij\5\2\2\2jk\5\b\5\2k"+
		"l\5\2\2\2lm\7\25\2\2mq\5\2\2\2np\5,\27\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2"+
		"qr\3\2\2\2r}\3\2\2\2sq\3\2\2\2tu\5\2\2\2uv\7\26\2\2vz\5\2\2\2wy\5,\27"+
		"\2xw\3\2\2\2y|\3\2\2\2zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}t\3\2\2"+
		"\2}~\3\2\2\2~\u0080\3\2\2\2\177\u0081\t\5\2\2\u0080\177\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\b\2\2\u0083\u0085\3\2"+
		"\2\2\u0084P\3\2\2\2\u0084R\3\2\2\2\u0084S\3\2\2\2\u0084Y\3\2\2\2\u0084"+
		"Z\3\2\2\2\u0084c\3\2\2\2\u0084d\3\2\2\2\u0084h\3\2\2\2\u0085\u00ea\3\2"+
		"\2\2\u0086\u008a\f\b\2\2\u0087\u0089\7\65\2\2\u0088\u0087\3\2\2\2\u0089"+
		"\u008c\3\2\2\2\u008a\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2"+
		"\2\2\u008c\u008a\3\2\2\2\u008d\u008e\t\6\2\2\u008e\u008f\5\2\2\2\u008f"+
		"\u0090\5\b\5\t\u0090\u00e9\3\2\2\2\u0091\u0095\f\7\2\2\u0092\u0094\7\65"+
		"\2\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099\t\7"+
		"\2\2\u0099\u009a\5\2\2\2\u009a\u009b\5\b\5\b\u009b\u00e9\3\2\2\2\u009c"+
		"\u00a0\f\6\2\2\u009d\u009f\7\65\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3"+
		"\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a4\t\b\2\2\u00a4\u00a5\5\2\2\2\u00a5\u00a6\5\b"+
		"\5\7\u00a6\u00e9\3\2\2\2\u00a7\u00ab\f\5\2\2\u00a8\u00aa\7\65\2\2\u00a9"+
		"\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2"+
		"\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\t\t\2\2\u00af"+
		"\u00b0\5\2\2\2\u00b0\u00b1\5\b\5\6\u00b1\u00e9\3\2\2\2\u00b2\u00b6\f\4"+
		"\2\2\u00b3\u00b5\7\65\2\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2"+
		"\2\2\u00b9\u00ba\t\n\2\2\u00ba\u00bb\5\2\2\2\u00bb\u00bc\5\b\5\5\u00bc"+
		"\u00e9\3\2\2\2\u00bd\u00be\f\17\2\2\u00be\u00bf\5\2\2\2\u00bf\u00c0\7"+
		".\2\2\u00c0\u00c2\5\2\2\2\u00c1\u00c3\5\34\17\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3\2"+
		"\2\2\u00c6\u00c8\t\5\2\2\u00c7\u00c6\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c9\3\2\2\2\u00c9\u00ca\7\b\2\2\u00ca\u00e9\3\2\2\2\u00cb\u00d0\f\16"+
		"\2\2\u00cc\u00ce\7\66\2\2\u00cd\u00cf\7\65\2\2\u00ce\u00cd\3\2\2\2\u00ce"+
		"\u00cf\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00cc\3\2\2\2\u00d0\u00d1\3\2"+
		"\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d3\7\b\2\2\u00d3\u00d4\t\13\2\2\u00d4"+
		"\u00d5\5\2\2\2\u00d5\u00d6\5\n\6\2\u00d6\u00e9\3\2\2\2\u00d7\u00db\f\f"+
		"\2\2\u00d8\u00da\7\65\2\2\u00d9\u00d8\3\2\2\2\u00da\u00dd\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00de\3\2\2\2\u00dd\u00db\3\2"+
		"\2\2\u00de\u00e9\5\n\6\2\u00df\u00e4\f\13\2\2\u00e0\u00e2\7\66\2\2\u00e1"+
		"\u00e3\7\65\2\2\u00e2\u00e1\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e5\3"+
		"\2\2\2\u00e4\u00e0\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e7\7\b\2\2\u00e7\u00e9\7>\2\2\u00e8\u0086\3\2\2\2\u00e8\u0091\3\2"+
		"\2\2\u00e8\u009c\3\2\2\2\u00e8\u00a7\3\2\2\2\u00e8\u00b2\3\2\2\2\u00e8"+
		"\u00bd\3\2\2\2\u00e8\u00cb\3\2\2\2\u00e8\u00d7\3\2\2\2\u00e8\u00df\3\2"+
		"\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb"+
		"\t\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee\7\n\2\2\u00ee\u00fb\5\2\2\2"+
		"\u00ef\u00f0\5\b\5\2\u00f0\u00f8\5\2\2\2\u00f1\u00f2\7\13\2\2\u00f2\u00f3"+
		"\5\2\2\2\u00f3\u00f4\5\b\5\2\u00f4\u00f5\5\2\2\2\u00f5\u00f7\3\2\2\2\u00f6"+
		"\u00f1\3\2\2\2\u00f7\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2"+
		"\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00ef\3\2\2\2\u00fb"+
		"\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\5\2\2\2\u00fe\u00ff\7\t"+
		"\2\2\u00ff\u0113\3\2\2\2\u0100\u0101\7(\2\2\u0101\u010d\5\2\2\2\u0102"+
		"\u0103\5\b\5\2\u0103\u010a\5\2\2\2\u0104\u0105\7\13\2\2\u0105\u0106\5"+
		"\2\2\2\u0106\u0107\5\b\5\2\u0107\u0109\3\2\2\2\u0108\u0104\3\2\2\2\u0109"+
		"\u010c\3\2\2\2\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010e\3\2"+
		"\2\2\u010c\u010a\3\2\2\2\u010d\u0102\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u010f\3\2\2\2\u010f\u0110\5\2\2\2\u0110\u0111\7\b\2\2\u0111\u0113\3\2"+
		"\2\2\u0112\u00ed\3\2\2\2\u0112\u0100\3\2\2\2\u0113\13\3\2\2\2\u0114\u0115"+
		"\5\6\4\2\u0115\u0116\5\2\2\2\u0116\u0117\7\37\2\2\u0117\u0118\5\2\2\2"+
		"\u0118\u0119\5\32\16\2\u0119\r\3\2\2\2\u011a\u011b\5\6\4\2\u011b\u011c"+
		"\5\2\2\2\u011c\u011d\7\b\2\2\u011d\u011e\5\2\2\2\u011e\u0120\3\2\2\2\u011f"+
		"\u011a\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0131\7?"+
		"\2\2\u0122\u0123\7\62\2\2\u0123\u0124\5\2\2\2\u0124\u012c\5\32\16\2\u0125"+
		"\u0126\5\2\2\2\u0126\u0127\7\13\2\2\u0127\u0128\5\2\2\2\u0128\u0129\5"+
		"\32\16\2\u0129\u012b\3\2\2\2\u012a\u0125\3\2\2\2\u012b\u012e\3\2\2\2\u012c"+
		"\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u012c\3\2"+
		"\2\2\u012f\u0130\7\63\2\2\u0130\u0132\3\2\2\2\u0131\u0122\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\17\3\2\2\2\u0133\u0140\7\n\2\2\u0134\u0135\5\2\2"+
		"\2\u0135\u013d\5\32\16\2\u0136\u0137\5\2\2\2\u0137\u0138\7\13\2\2\u0138"+
		"\u0139\5\2\2\2\u0139\u013a\5\32\16\2\u013a\u013c\3\2\2\2\u013b\u0136\3"+
		"\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e"+
		"\u0141\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0134\3\2\2\2\u0140\u0141\3\2"+
		"\2\2\u0141\u0142\3\2\2\2\u0142\u0143\7\t\2\2\u0143\u0144\5\2\2\2\u0144"+
		"\u0145\7 \2\2\u0145\u0146\5\2\2\2\u0146\u0147\5\32\16\2\u0147\21\3\2\2"+
		"\2\u0148\u0149\7\n\2\2\u0149\u014a\5\2\2\2\u014a\u0150\5\f\7\2\u014b\u014c"+
		"\5\2\2\2\u014c\u014d\7\13\2\2\u014d\u014e\5\2\2\2\u014e\u014f\5\f\7\2"+
		"\u014f\u0151\3\2\2\2\u0150\u014b\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0150"+
		"\3\2\2\2\u0152\u0153\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\7\t\2\2\u0155"+
		"\23\3\2\2\2\u0156\u0157\7\n\2\2\u0157\u0158\5\2\2\2\u0158\u0159\5\26\f"+
		"\2\u0159\u015a\5\2\2\2\u015a\u015b\7\t\2\2\u015b\u0161\3\2\2\2\u015c\u0161"+
		"\5\16\b\2\u015d\u0161\5\20\t\2\u015e\u0161\5\22\n\2\u015f\u0161\5\30\r"+
		"\2\u0160\u0156\3\2\2\2\u0160\u015c\3\2\2\2\u0160\u015d\3\2\2\2\u0160\u015e"+
		"\3\2\2\2\u0160\u015f\3\2\2\2\u0161\25\3\2\2\2\u0162\u0168\5\24\13\2\u0163"+
		"\u0164\5\2\2\2\u0164\u0165\7\61\2\2\u0165\u0166\5\2\2\2\u0166\u0167\5"+
		"\24\13\2\u0167\u0169\3\2\2\2\u0168\u0163\3\2\2\2\u0169\u016a\3\2\2\2\u016a"+
		"\u0168\3\2\2\2\u016a\u016b\3\2\2\2\u016b\27\3\2\2\2\u016c\u016d\7>\2\2"+
		"\u016d\31\3\2\2\2\u016e\u0174\5\16\b\2\u016f\u0174\5\22\n\2\u0170\u0174"+
		"\5\20\t\2\u0171\u0174\5\26\f\2\u0172\u0174\5\30\r\2\u0173\u016e\3\2\2"+
		"\2\u0173\u016f\3\2\2\2\u0173\u0170\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0172"+
		"\3\2\2\2\u0174\33\3\2\2\2\u0175\u0176\7-\2\2\u0176\u017c\5\2\2\2\u0177"+
		"\u0178\7>\2\2\u0178\u0179\5\2\2\2\u0179\u017a\7\37\2\2\u017a\u017b\5\2"+
		"\2\2\u017b\u017d\3\2\2\2\u017c\u0177\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017e\u017f\5\32\16\2\u017f\u0180\5\2\2\2\u0180\u0181\7"+
		"\25\2\2\u0181\u0185\5\2\2\2\u0182\u0184\5,\27\2\u0183\u0182\3\2\2\2\u0184"+
		"\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2\2\2\u0186\35\3\2\2"+
		"\2\u0187\u0185\3\2\2\2\u0188\u0193\5\6\4\2\u0189\u018b\7\66\2\2\u018a"+
		"\u018c\7\65\2\2\u018b\u018a\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018e\3"+
		"\2\2\2\u018d\u0189\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u018f\3\2\2\2\u018f"+
		"\u0190\7\b\2\2\u0190\u0192\7>\2\2\u0191\u018d\3\2\2\2\u0192\u0195\3\2"+
		"\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2\2\2\u0195"+
		"\u0193\3\2\2\2\u0196\u019c\5\2\2\2\u0197\u019d\5\n\6\2\u0198\u0199\7\37"+
		"\2\2\u0199\u019a\5\2\2\2\u019a\u019b\5\32\16\2\u019b\u019d\3\2\2\2\u019c"+
		"\u0197\3\2\2\2\u019c\u0198\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019e\3\2"+
		"\2\2\u019e\u019f\5\2\2\2\u019f\u01a0\7\20\2\2\u01a0\u01a1\5\2\2\2\u01a1"+
		"\u01a2\5\b\5\2\u01a2\37\3\2\2\2\u01a3\u01a4\7*\2\2\u01a4\u01a6\5\2\2\2"+
		"\u01a5\u01a7\5\b\5\2\u01a6\u01a5\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7!\3"+
		"\2\2\2\u01a8\u01a9\7+\2\2\u01a9#\3\2\2\2\u01aa\u01ab\7,\2\2\u01ab%\3\2"+
		"\2\2\u01ac\u01ad\7\34\2\2\u01ad\u01ae\5\2\2\2\u01ae\u01af\5\b\5\2\u01af"+
		"\u01b0\5\2\2\2\u01b0\u01b1\7\25\2\2\u01b1\u01b5\5\2\2\2\u01b2\u01b4\5"+
		",\27\2\u01b3\u01b2\3\2\2\2\u01b4\u01b7\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b5"+
		"\u01b6\3\2\2\2\u01b6\u01b8\3\2\2\2\u01b7\u01b5\3\2\2\2\u01b8\u01b9\7\b"+
		"\2\2\u01b9\'\3\2\2\2\u01ba\u01bb\5\6\4\2\u01bb\u01c6\5\2\2\2\u01bc\u01bd"+
		"\7\37\2\2\u01bd\u01be\5\2\2\2\u01be\u01c4\5\32\16\2\u01bf\u01c0\5\2\2"+
		"\2\u01c0\u01c1\7\20\2\2\u01c1\u01c2\5\2\2\2\u01c2\u01c3\5\b\5\2\u01c3"+
		"\u01c5\3\2\2\2\u01c4\u01bf\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2"+
		"\2\2\u01c6\u01bc\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7)\3\2\2\2\u01c8\u01dc"+
		"\7\32\2\2\u01c9\u01d6\7\61\2\2\u01ca\u01cb\5\2\2\2\u01cb\u01cc\5(\25\2"+
		"\u01cc\u01d3\5\2\2\2\u01cd\u01ce\7\13\2\2\u01ce\u01cf\5\2\2\2\u01cf\u01d0"+
		"\5(\25\2\u01d0\u01d2\3\2\2\2\u01d1\u01cd\3\2\2\2\u01d2\u01d5\3\2\2\2\u01d3"+
		"\u01d1\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d7\3\2\2\2\u01d5\u01d3\3\2"+
		"\2\2\u01d6\u01ca\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8"+
		"\u01d9\5\2\2\2\u01d9\u01da\7\61\2\2\u01da\u01dc\3\2\2\2\u01db\u01c8\3"+
		"\2\2\2\u01db\u01c9\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01e1\5\2\2\2\u01de"+
		"\u01e0\5,\27\2\u01df\u01de\3\2\2\2\u01e0\u01e3\3\2\2\2\u01e1\u01df\3\2"+
		"\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e4\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e4"+
		"\u01e6\5\2\2\2\u01e5\u01e7\7\b\2\2\u01e6\u01e5\3\2\2\2\u01e6\u01e7\3\2"+
		"\2\2\u01e7+\3\2\2\2\u01e8\u01ef\5\36\20\2\u01e9\u01ef\5\"\22\2\u01ea\u01ef"+
		"\5$\23\2\u01eb\u01ef\5&\24\2\u01ec\u01ef\5\b\5\2\u01ed\u01ef\5 \21\2\u01ee"+
		"\u01e8\3\2\2\2\u01ee\u01e9\3\2\2\2\u01ee\u01ea\3\2\2\2\u01ee\u01eb\3\2"+
		"\2\2\u01ee\u01ec\3\2\2\2\u01ee\u01ed\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0"+
		"\u01f2\5\2\2\2\u01f1\u01f3\7\23\2\2\u01f2\u01f1\3\2\2\2\u01f2\u01f3\3"+
		"\2\2\2\u01f3\u01f4\3\2\2\2\u01f4\u01f5\5\2\2\2\u01f5-\3\2\2\2\u01f6\u01f7"+
		"\7!\2\2\u01f7\u01f8\5\2\2\2\u01f8\u020a\7?\2\2\u01f9\u01fa\5\2\2\2\u01fa"+
		"\u01fb\7\62\2\2\u01fb\u01fc\5\2\2\2\u01fc\u0204\5\30\r\2\u01fd\u01fe\5"+
		"\2\2\2\u01fe\u01ff\7\13\2\2\u01ff\u0200\5\2\2\2\u0200\u0201\5\30\r\2\u0201"+
		"\u0203\3\2\2\2\u0202\u01fd\3\2\2\2\u0203\u0206\3\2\2\2\u0204\u0202\3\2"+
		"\2\2\u0204\u0205\3\2\2\2\u0205\u0207\3\2\2\2\u0206\u0204\3\2\2\2\u0207"+
		"\u0208\5\2\2\2\u0208\u0209\7\63\2\2\u0209\u020b\3\2\2\2\u020a\u01f9\3"+
		"\2\2\2\u020a\u020b\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u020d\5\2\2\2\u020d"+
		"\u020e\7\20\2\2\u020e\u0210\5\2\2\2\u020f\u0211\7/\2\2\u0210\u020f\3\2"+
		"\2\2\u0210\u0211\3\2\2\2\u0211\u0212\3\2\2\2\u0212\u0213\5\2\2\2\u0213"+
		"\u0214\5D#\2\u0214/\3\2\2\2\u0215\u0217\7#\2\2\u0216\u0215\3\2\2\2\u0216"+
		"\u0217\3\2\2\2\u0217\u0218\3\2\2\2\u0218\u0219\5\2\2\2\u0219\u021a\7>"+
		"\2\2\u021a\u021b\5\2\2\2\u021b\u021c\7\37\2\2\u021c\u021d\5\2\2\2\u021d"+
		"\u0223\5\32\16\2\u021e\u021f\5\2\2\2\u021f\u0220\7\20\2\2\u0220\u0221"+
		"\5\2\2\2\u0221\u0222\5\b\5\2\u0222\u0224\3\2\2\2\u0223\u021e\3\2\2\2\u0223"+
		"\u0224\3\2\2\2\u0224\61\3\2\2\2\u0225\u0226\7!\2\2\u0226\u0227\5\2\2\2"+
		"\u0227\u0239\7?\2\2\u0228\u0229\5\2\2\2\u0229\u022a\7\62\2\2\u022a\u022b"+
		"\5\2\2\2\u022b\u0233\5\30\r\2\u022c\u022d\5\2\2\2\u022d\u022e\7\13\2\2"+
		"\u022e\u022f\5\2\2\2\u022f\u0230\5\30\r\2\u0230\u0232\3\2\2\2\u0231\u022c"+
		"\3\2\2\2\u0232\u0235\3\2\2\2\u0233\u0231\3\2\2\2\u0233\u0234\3\2\2\2\u0234"+
		"\u0236\3\2\2\2\u0235\u0233\3\2\2\2\u0236\u0237\5\2\2\2\u0237\u0238\7\63"+
		"\2\2\u0238\u023a\3\2\2\2\u0239\u0228\3\2\2\2\u0239\u023a\3\2\2\2\u023a"+
		"\u023b\3\2\2\2\u023b\u023c\5\2\2\2\u023c\u023d\7\20\2\2\u023d\u023e\5"+
		"\2\2\2\u023e\u023f\7\n\2\2\u023f\u0240\5\2\2\2\u0240\u0247\5\60\31\2\u0241"+
		"\u0242\7\13\2\2\u0242\u0243\5\2\2\2\u0243\u0244\5\60\31\2\u0244\u0246"+
		"\3\2\2\2\u0245\u0241\3\2\2\2\u0246\u0249\3\2\2\2\u0247\u0245\3\2\2\2\u0247"+
		"\u0248\3\2\2\2\u0248\u024a\3\2\2\2\u0249\u0247\3\2\2\2\u024a\u024b\5\2"+
		"\2\2\u024b\u024c\7\t\2\2\u024c\63\3\2\2\2\u024d\u024e\7!\2\2\u024e\u024f"+
		"\5\2\2\2\u024f\u0250\7?\2\2\u0250\u0251\5\2\2\2\u0251\u0252\7\20\2\2\u0252"+
		"\u0253\5\2\2\2\u0253\u0259\5\24\13\2\u0254\u0255\5\2\2\2\u0255\u0256\7"+
		"\61\2\2\u0256\u0257\5\2\2\2\u0257\u0258\5\24\13\2\u0258\u025a\3\2\2\2"+
		"\u0259\u0254\3\2\2\2\u025a\u025b\3\2\2\2\u025b\u0259\3\2\2\2\u025b\u025c"+
		"\3\2\2\2\u025c\65\3\2\2\2\u025d\u0261\5.\30\2\u025e\u0261\5\62\32\2\u025f"+
		"\u0261\5\64\33\2\u0260\u025d\3\2\2\2\u0260\u025e\3\2\2\2\u0260\u025f\3"+
		"\2\2\2\u0261\67\3\2\2\2\u0262\u0263\7%\2\2\u0263\u0264\5\2\2\2\u0264\u0265"+
		"\t\f\2\2\u0265\u0266\5\2\2\2\u0266\u0267\7\20\2\2\u0267\u0292\5\2\2\2"+
		"\u0268\u0269\5(\25\2\u0269\u0270\5\2\2\2\u026a\u026b\7\13\2\2\u026b\u026c"+
		"\5\2\2\2\u026c\u026d\5(\25\2\u026d\u026f\3\2\2\2\u026e\u026a\3\2\2\2\u026f"+
		"\u0272\3\2\2\2\u0270\u026e\3\2\2\2\u0270\u0271\3\2\2\2\u0271\u0273\3\2"+
		"\2\2\u0272\u0270\3\2\2\2\u0273\u0274\5\2\2\2\u0274\u0275\7\25\2\2\u0275"+
		"\u0277\3\2\2\2\u0276\u0268\3\2\2\2\u0276\u0277\3\2\2\2\u0277\u0278\3\2"+
		"\2\2\u0278\u027c\5\2\2\2\u0279\u027b\5,\27\2\u027a\u0279\3\2\2\2\u027b"+
		"\u027e\3\2\2\2\u027c\u027a\3\2\2\2\u027c\u027d\3\2\2\2\u027d\u027f\3\2"+
		"\2\2\u027e\u027c\3\2\2\2\u027f\u0280\5\2\2\2\u0280\u0281\7\b\2\2\u0281"+
		"\u0293\3\2\2\2\u0282\u0283\5(\25\2\u0283\u028a\5\2\2\2\u0284\u0285\7\13"+
		"\2\2\u0285\u0286\5\2\2\2\u0286\u0287\5(\25\2\u0287\u0289\3\2\2\2\u0288"+
		"\u0284\3\2\2\2\u0289\u028c\3\2\2\2\u028a\u0288\3\2\2\2\u028a\u028b\3\2"+
		"\2\2\u028b\u028d\3\2\2\2\u028c\u028a\3\2\2\2\u028d\u028e\5\2\2\2\u028e"+
		"\u0290\3\2\2\2\u028f\u0282\3\2\2\2\u028f\u0290\3\2\2\2\u0290\u0291\3\2"+
		"\2\2\u0291\u0293\5D#\2\u0292\u0276\3\2\2\2\u0292\u028f\3\2\2\2\u0293\u0295"+
		"\3\2\2\2\u0294\u0296\5\32\16\2\u0295\u0294\3\2\2\2\u0295\u0296\3\2\2\2"+
		"\u02969\3\2\2\2\u0297\u0299\5\2\2\2\u0298\u029a\7\b\2\2\u0299\u0298\3"+
		"\2\2\2\u0299\u029a\3\2\2\2\u029a\u029b\3\2\2\2\u029b\u02a0\7>\2\2\u029c"+
		"\u029d\7\b\2\2\u029d\u029f\7>\2\2\u029e\u029c\3\2\2\2\u029f\u02a2\3\2"+
		"\2\2\u02a0\u029e\3\2\2\2\u02a0\u02a1\3\2\2\2\u02a1\u02b0\3\2\2\2\u02a2"+
		"\u02a0\3\2\2\2\u02a3\u02a4\5\2\2\2\u02a4\u02a5\7(\2\2\u02a5\u02a6\5\2"+
		"\2\2\u02a6\u02a7\7?\2\2\u02a7\u02ad\5\2\2\2\u02a8\u02a9\5\2\2\2\u02a9"+
		"\u02aa\7?\2\2\u02aa\u02ac\3\2\2\2\u02ab\u02a8\3\2\2\2\u02ac\u02af\3\2"+
		"\2\2\u02ad\u02ab\3\2\2\2\u02ad\u02ae\3\2\2\2\u02ae\u02b1\3\2\2\2\u02af"+
		"\u02ad\3\2\2\2\u02b0\u02a3\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1;\3\2\2\2"+
		"\u02b2\u02b3\7\'\2\2\u02b3\u02b8\5:\36\2\u02b4\u02b5\7\13\2\2\u02b5\u02b7"+
		"\5:\36\2\u02b6\u02b4\3\2\2\2\u02b7\u02ba\3\2\2\2\u02b8\u02b6\3\2\2\2\u02b8"+
		"\u02b9\3\2\2\2\u02b9=\3\2\2\2\u02ba\u02b8\3\2\2\2\u02bb\u02be\5\66\34"+
		"\2\u02bc\u02be\58\35\2\u02bd\u02bb\3\2\2\2\u02bd\u02bc\3\2\2\2\u02be?"+
		"\3\2\2\2\u02bf\u02c1\5\2\2\2\u02c0\u02c2\5<\37\2\u02c1\u02c0\3\2\2\2\u02c1"+
		"\u02c2\3\2\2\2\u02c2\u02c3\3\2\2\2\u02c3\u02c9\5\2\2\2\u02c4\u02c5\5\2"+
		"\2\2\u02c5\u02c6\5D#\2\u02c6\u02c8\3\2\2\2\u02c7\u02c4\3\2\2\2\u02c8\u02cb"+
		"\3\2\2\2\u02c9\u02c7\3\2\2\2\u02c9\u02ca\3\2\2\2\u02ca\u02cc\3\2\2\2\u02cb"+
		"\u02c9\3\2\2\2\u02cc\u02d2\5\2\2\2\u02cd\u02ce\5> \2\u02ce\u02cf\5\2\2"+
		"\2\u02cf\u02d1\3\2\2\2\u02d0\u02cd\3\2\2\2\u02d1\u02d4\3\2\2\2\u02d2\u02d0"+
		"\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d5\3\2\2\2\u02d4\u02d2\3\2\2\2\u02d5"+
		"\u02d6\7\2\2\3\u02d6A\3\2\2\2\u02d7\u02d9\t\r\2\2\u02d8\u02d7\3\2\2\2"+
		"\u02d9\u02dc\3\2\2\2\u02da\u02d8\3\2\2\2\u02da\u02db\3\2\2\2\u02dbC\3"+
		"\2\2\2\u02dc\u02da\3\2\2\2\u02dd\u02de\7\64\2\2\u02de\u02df\5B\"\2\u02df"+
		"\u02e0\7D\2\2\u02e0E\3\2\2\2QI^qz}\u0080\u0084\u008a\u0095\u00a0\u00ab"+
		"\u00b6\u00c4\u00c7\u00ce\u00d0\u00db\u00e2\u00e4\u00e8\u00ea\u00f8\u00fb"+
		"\u010a\u010d\u0112\u011f\u012c\u0131\u013d\u0140\u0152\u0160\u016a\u0173"+
		"\u017c\u0185\u018b\u018d\u0193\u019c\u01a6\u01b5\u01c4\u01c6\u01d3\u01d6"+
		"\u01db\u01e1\u01e6\u01ee\u01f2\u0204\u020a\u0210\u0216\u0223\u0233\u0239"+
		"\u0247\u025b\u0260\u0270\u0276\u027c\u028a\u028f\u0292\u0295\u0299\u02a0"+
		"\u02ad\u02b0\u02b8\u02bd\u02c1\u02c9\u02d2\u02da";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}