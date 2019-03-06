// Generated from /home/over/build/abra_lang/abra_v2/grammar/M2Parser.g4 by ANTLR 4.7.2
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
		OF=40, RETURN=41, BREAK=42, CONTINUE=43, IS=44, UNLESS=45, REF=46, DASH=47, 
		VERT_LINE=48, BRACKET_LEFT=49, BRACKET_RIGTH=50, LlBegin=51, WS=52, NL=53, 
		COMMENT=54, IntLiteral=55, HexLiteral=56, FloatLiteral=57, BooleanLiteral=58, 
		NoneLiteral=59, StringLiteral=60, VarId=61, TypeId=62, MatchId=63, LLVM_NL=64, 
		LLVM_WS=65, IrLine=66, LL_End=67, LL_Dot=68;
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
			"'import'", "'with'", "'match'", "'of'", "'return'", "'break'", "'continue'", 
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
			"MATCH_SELF", "DEF", "LAMBDA", "IMPORT", "WITH", "MATCH", "OF", "RETURN", 
			"BREAK", "CONTINUE", "IS", "UNLESS", "REF", "DASH", "VERT_LINE", "BRACKET_LEFT", 
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterSp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitSp(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitLiteral(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitId(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprCons(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprCons(this);
		}
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
		public TerminalNode WS() { return getToken(M2Parser.WS, 0); }
		public TerminalNode NL() { return getToken(M2Parser.NL, 0); }
		public List<IsContext> is() {
			return getRuleContexts(IsContext.class);
		}
		public IsContext is(int i) {
			return getRuleContext(IsContext.class,i);
		}
		public ExprUnlessContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprUnless(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprUnless(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprLiteral(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprIfElse(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprLambda(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprParen(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprCall(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprProp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprProp(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprTuple(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprId(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprUnaryCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprUnaryCall(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprInfixCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprInfixCall(this);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprSelfCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprSelfCall(this);
		}
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
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
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
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
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
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(228);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
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
						setState(197);
						match(DOT);
						}
						break;
					case 7:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(199);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(204);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(200);
							match(NL);
							setState(202);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(201);
								match(WS);
								}
							}

							}
						}

						setState(206);
						match(DOT);
						setState(207);
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
						setState(208);
						sp();
						setState(209);
						tuple();
						}
						break;
					case 8:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(211);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(215);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(212);
							match(WS);
							}
							}
							setState(217);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(218);
						tuple();
						}
						break;
					case 9:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(219);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(224);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(220);
							match(NL);
							setState(222);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(221);
								match(WS);
								}
							}

							}
						}

						setState(226);
						match(DOT);
						setState(227);
						((ExprPropContext)_localctx).VarId = match(VarId);
						((ExprPropContext)_localctx).op.add(((ExprPropContext)_localctx).VarId);
						}
						break;
					}
					} 
				}
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterTuple(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitTuple(this);
		}
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
			setState(270);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(233);
				match(LB);
				setState(234);
				sp();
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(235);
					expression(0);
					setState(236);
					sp();
					setState(244);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(237);
						match(COMMA);
						setState(238);
						sp();
						setState(239);
						expression(0);
						setState(240);
						sp();
						}
						}
						setState(246);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(249);
				sp();
				setState(250);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				match(WITH);
				setState(253);
				sp();
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(254);
					expression(0);
					setState(255);
					sp();
					setState(262);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(256);
						match(COMMA);
						setState(257);
						sp();
						setState(258);
						expression(0);
						}
						}
						setState(264);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(267);
				sp();
				setState(268);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterFieldTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitFieldTh(this);
		}
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
			setState(272);
			id();
			setState(273);
			sp();
			setState(274);
			match(CON);
			setState(275);
			sp();
			setState(276);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterScalarTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitScalarTh(this);
		}
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
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(278);
				id();
				setState(279);
				sp();
				setState(280);
				match(DOT);
				setState(281);
				sp();
				}
			}

			setState(285);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(286);
				match(BRACKET_LEFT);
				setState(287);
				sp();
				setState(288);
				typeHint();
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(289);
					sp();
					setState(290);
					match(COMMA);
					setState(291);
					sp();
					setState(292);
					typeHint();
					}
					}
					setState(298);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(299);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterFnTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitFnTh(this);
		}
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
			setState(303);
			match(LB);
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << WS) | (1L << NL) | (1L << COMMENT) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(304);
				sp();
				setState(305);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(313);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(306);
					sp();
					setState(307);
					match(COMMA);
					setState(308);
					sp();
					setState(309);
					((FnThContext)_localctx).typeHint = typeHint();
					((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
					}
					}
					setState(315);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(318);
			match(RB);
			setState(319);
			sp();
			setState(320);
			match(ARROW_RIGHT);
			setState(321);
			sp();
			setState(322);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterStructTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitStructTh(this);
		}
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
			setState(324);
			match(LB);
			setState(325);
			sp();
			setState(326);
			fieldTh();
			setState(332); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(327);
				sp();
				setState(328);
				match(COMMA);
				setState(329);
				sp();
				setState(330);
				fieldTh();
				}
				}
				setState(334); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(336);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterNonUnionTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitNonUnionTh(this);
		}
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
			setState(348);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(338);
				match(LB);
				setState(339);
				sp();
				setState(340);
				unionTh();
				setState(341);
				sp();
				setState(342);
				match(RB);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(344);
				scalarTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(345);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(346);
				structTh();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(347);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterUnionTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitUnionTh(this);
		}
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
			setState(350);
			nonUnionTh();
			setState(356); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(351);
					sp();
					setState(352);
					match(VERT_LINE);
					setState(353);
					sp();
					setState(354);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(358); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterGenericTh(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitGenericTh(this);
		}
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
			setState(360);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterTypeHint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitTypeHint(this);
		}
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
			setState(367);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(362);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(363);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(364);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(365);
				unionTh();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(366);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterIs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitIs(this);
		}
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
			setState(369);
			match(IS);
			setState(370);
			sp();
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				{
				setState(371);
				match(VarId);
				setState(372);
				sp();
				setState(373);
				match(CON);
				setState(374);
				sp();
				}
				break;
			}
			setState(378);
			typeHint();
			setState(379);
			sp();
			setState(380);
			match(DO);
			setState(381);
			sp();
			setState(385);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(382);
				blockBody();
				}
				}
				setState(387);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterStore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitStore(this);
		}
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
			setState(388);
			id();
			setState(399);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(393);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(389);
						match(NL);
						setState(391);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(390);
							match(WS);
							}
						}

						}
					}

					setState(395);
					match(DOT);
					setState(396);
					match(VarId);
					}
					} 
				}
				setState(401);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
			}
			setState(402);
			sp();
			setState(408);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(403);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(404);
				match(CON);
				setState(405);
				sp();
				setState(406);
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
			setState(410);
			sp();
			setState(411);
			match(EQ);
			setState(412);
			sp();
			setState(413);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterRet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitRet(this);
		}
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
			setState(415);
			match(RETURN);
			setState(416);
			sp();
			setState(418);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(417);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterBreak_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitBreak_(this);
		}
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
			setState(420);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterContinue_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitContinue_(this);
		}
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
			setState(422);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterWhile_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitWhile_stat(this);
		}
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
			setState(424);
			match(WHILE);
			setState(425);
			sp();
			setState(426);
			((While_statContext)_localctx).cond = expression(0);
			setState(427);
			sp();
			setState(428);
			match(DO);
			setState(429);
			sp();
			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(430);
				blockBody();
				}
				}
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(436);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterFnArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitFnArg(this);
		}
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
			setState(438);
			id();
			setState(439);
			sp();
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(440);
				match(CON);
				setState(441);
				sp();
				setState(442);
				typeHint();
				setState(448);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,42,_ctx) ) {
				case 1:
					{
					setState(443);
					sp();
					setState(444);
					match(EQ);
					setState(445);
					sp();
					setState(446);
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
		public TerminalNode LAMBDA() { return getToken(M2Parser.LAMBDA, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public TerminalNode ARROW_RIGHT() { return getToken(M2Parser.ARROW_RIGHT, 0); }
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public LambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambda; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterLambda(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitLambda(this);
		}
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
			setState(452);
			match(LAMBDA);
			setState(468);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(453);
				sp();
				setState(454);
				fnArg();
				setState(455);
				sp();
				setState(462);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(456);
					match(COMMA);
					setState(457);
					sp();
					setState(458);
					fnArg();
					}
					}
					setState(464);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(465);
				sp();
				setState(466);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(470);
			sp();
			setState(474);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(471);
					blockBody();
					}
					} 
				}
				setState(476);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
			}
			setState(477);
			sp();
			setState(479);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(478);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterBlockBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitBlockBody(this);
		}
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
			setState(487);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
			case 1:
				{
				setState(481);
				store();
				}
				break;
			case 2:
				{
				setState(482);
				break_();
				}
				break;
			case 3:
				{
				setState(483);
				continue_();
				}
				break;
			case 4:
				{
				setState(484);
				while_stat();
				}
				break;
			case 5:
				{
				setState(485);
				expression(0);
				}
				break;
			case 6:
				{
				setState(486);
				ret();
				}
				break;
			}
			setState(489);
			sp();
			setState(491);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				{
				setState(490);
				match(SEMI);
				}
				break;
			}
			setState(493);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterScalarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitScalarType(this);
		}
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
			setState(495);
			match(TYPE);
			setState(496);
			sp();
			setState(497);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(515);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(498);
				sp();
				setState(499);
				match(BRACKET_LEFT);
				setState(500);
				sp();
				setState(501);
				((ScalarTypeContext)_localctx).genericTh = genericTh();
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).genericTh);
				setState(509);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(502);
						sp();
						setState(503);
						match(COMMA);
						setState(504);
						sp();
						setState(505);
						((ScalarTypeContext)_localctx).genericTh = genericTh();
						((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).genericTh);
						}
						} 
					}
					setState(511);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
				}
				setState(512);
				sp();
				setState(513);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(517);
			sp();
			setState(518);
			match(EQ);
			setState(519);
			sp();
			setState(521);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(520);
				match(REF);
				}
			}

			setState(523);
			sp();
			setState(524);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterTypeField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitTypeField(this);
		}
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
			setState(527);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(526);
				match(SELF);
				}
			}

			setState(529);
			sp();
			setState(530);
			match(VarId);
			setState(531);
			sp();
			setState(532);
			match(CON);
			setState(533);
			sp();
			setState(534);
			typeHint();
			setState(540);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(535);
				sp();
				setState(536);
				match(EQ);
				setState(537);
				sp();
				setState(538);
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
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterStructType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitStructType(this);
		}
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
			setState(542);
			match(TYPE);
			setState(543);
			sp();
			setState(544);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(562);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(545);
				sp();
				setState(546);
				match(BRACKET_LEFT);
				setState(547);
				sp();
				setState(548);
				((StructTypeContext)_localctx).genericTh = genericTh();
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).genericTh);
				setState(556);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(549);
						sp();
						setState(550);
						match(COMMA);
						setState(551);
						sp();
						setState(552);
						((StructTypeContext)_localctx).genericTh = genericTh();
						((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).genericTh);
						}
						} 
					}
					setState(558);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				setState(559);
				sp();
				setState(560);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(564);
			sp();
			setState(565);
			match(EQ);
			setState(566);
			sp();
			setState(567);
			match(LB);
			setState(571);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(568);
					match(NL);
					}
					} 
				}
				setState(573);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,57,_ctx);
			}
			setState(574);
			typeField();
			setState(585);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(575);
				match(COMMA);
				setState(579);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(576);
						match(NL);
						}
						} 
					}
					setState(581);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
				}
				setState(582);
				typeField();
				}
				}
				setState(587);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(591);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(588);
				match(NL);
				}
				}
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(594);
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
		public List<NonUnionThContext> nonUnionTh() {
			return getRuleContexts(NonUnionThContext.class);
		}
		public NonUnionThContext nonUnionTh(int i) {
			return getRuleContext(NonUnionThContext.class,i);
		}
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public TerminalNode BRACKET_LEFT() { return getToken(M2Parser.BRACKET_LEFT, 0); }
		public TerminalNode BRACKET_RIGTH() { return getToken(M2Parser.BRACKET_RIGTH, 0); }
		public List<TerminalNode> VERT_LINE() { return getTokens(M2Parser.VERT_LINE); }
		public TerminalNode VERT_LINE(int i) {
			return getToken(M2Parser.VERT_LINE, i);
		}
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
		public UnionTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unionType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterUnionType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitUnionType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitUnionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionTypeContext unionType() throws RecognitionException {
		UnionTypeContext _localctx = new UnionTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_unionType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(596);
			match(TYPE);
			setState(597);
			sp();
			setState(598);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(599);
			sp();
			setState(616);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(600);
				match(BRACKET_LEFT);
				setState(601);
				sp();
				setState(602);
				((UnionTypeContext)_localctx).genericTh = genericTh();
				((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).genericTh);
				setState(610);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(603);
						sp();
						setState(604);
						match(COMMA);
						setState(605);
						sp();
						setState(606);
						((UnionTypeContext)_localctx).genericTh = genericTh();
						((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).genericTh);
						}
						} 
					}
					setState(612);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				}
				setState(613);
				sp();
				setState(614);
				match(BRACKET_RIGTH);
				}
			}

			setState(618);
			sp();
			setState(619);
			match(EQ);
			setState(620);
			sp();
			setState(621);
			nonUnionTh();
			setState(627); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(622);
					sp();
					setState(623);
					match(VERT_LINE);
					setState(624);
					sp();
					setState(625);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(629); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitType(this);
		}
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
			setState(634);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(631);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(632);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(633);
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
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitDef(this);
		}
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
			setState(636);
			match(DEF);
			setState(637);
			sp();
			setState(638);
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
			setState(639);
			sp();
			setState(640);
			match(EQ);
			setState(641);
			sp();
			setState(656);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				{
				setState(642);
				fnArg();
				setState(643);
				sp();
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(644);
					match(COMMA);
					setState(645);
					sp();
					setState(646);
					fnArg();
					}
					}
					setState(652);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(653);
				sp();
				setState(654);
				match(DO);
				}
				break;
			}
			setState(658);
			sp();
			setState(669);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case EXCL:
			case DOT:
			case LB:
			case IF:
			case WHILE:
			case SELF:
			case LAMBDA:
			case WITH:
			case RETURN:
			case BREAK:
			case CONTINUE:
			case WS:
			case NL:
			case COMMENT:
			case IntLiteral:
			case HexLiteral:
			case FloatLiteral:
			case BooleanLiteral:
			case NoneLiteral:
			case StringLiteral:
			case VarId:
			case TypeId:
				{
				{
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << BREAK) | (1L << CONTINUE) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(659);
					blockBody();
					}
					}
					setState(664);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(665);
				sp();
				setState(666);
				match(DOT);
				}
				}
				break;
			case LlBegin:
				{
				setState(668);
				llvm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(672);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(671);
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
		public Token abs;
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
		public List<TerminalNode> DIV() { return getTokens(M2Parser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(M2Parser.DIV, i);
		}
		public TerminalNode WITH() { return getToken(M2Parser.WITH, 0); }
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(M2Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(M2Parser.COMMA, i);
		}
		public ImportEntryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importEntry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterImportEntry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitImportEntry(this);
		}
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
			enterOuterAlt(_localctx, 1);
			{
			setState(674);
			sp();
			setState(676);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIV) {
				{
				setState(675);
				((ImportEntryContext)_localctx).abs = match(DIV);
				}
			}

			setState(678);
			match(VarId);
			setState(683);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV) {
				{
				{
				setState(679);
				match(DIV);
				setState(680);
				match(VarId);
				}
				}
				setState(685);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(700);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(686);
				sp();
				setState(687);
				match(WITH);
				setState(688);
				sp();
				setState(689);
				match(TypeId);
				setState(690);
				sp();
				setState(697);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(691);
					match(COMMA);
					setState(692);
					sp();
					setState(693);
					match(TypeId);
					}
					}
					setState(699);
					_errHandler.sync(this);
					_la = _input.LA(1);
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
		public TerminalNode WS() { return getToken(M2Parser.WS, 0); }
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public Import_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_import_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterImport_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitImport_(this);
		}
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
			setState(702);
			match(IMPORT);
			setState(703);
			importEntry();
			setState(708);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(704);
				match(NL);
				setState(705);
				importEntry();
				}
				}
				setState(710);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(711);
			match(WS);
			setState(712);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterLevel1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitLevel1(this);
		}
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
			setState(716);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(714);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(715);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterModule(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitModule(this);
		}
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
			setState(718);
			sp();
			setState(720);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(719);
				import_();
				}
			}

			setState(722);
			sp();
			setState(728);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(723);
					sp();
					setState(724);
					llvm();
					}
					} 
				}
				setState(730);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			}
			setState(731);
			sp();
			setState(737);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(732);
				level1();
				setState(733);
				sp();
				}
				}
				setState(739);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(740);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterLlvmBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitLlvmBody(this);
		}
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
			setState(745);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LLVM_NL - 64)) | (1L << (LLVM_WS - 64)) | (1L << (IrLine - 64)) | (1L << (LL_Dot - 64)))) != 0)) {
				{
				{
				setState(742);
				_la = _input.LA(1);
				if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (LLVM_NL - 64)) | (1L << (LLVM_WS - 64)) | (1L << (IrLine - 64)) | (1L << (LL_Dot - 64)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(747);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterLlvm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitLlvm(this);
		}
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
			setState(748);
			match(LlBegin);
			setState(749);
			llvmBody();
			setState(750);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3F\u02f3\4\2\t\2\4"+
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
		"\5\16\5\u00c4\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cd\n\5\5\5\u00cf\n\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00d8\n\5\f\5\16\5\u00db\13\5\3\5\3\5\3\5"+
		"\3\5\5\5\u00e1\n\5\5\5\u00e3\n\5\3\5\3\5\7\5\u00e7\n\5\f\5\16\5\u00ea"+
		"\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00f5\n\6\f\6\16\6\u00f8"+
		"\13\6\5\6\u00fa\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0107"+
		"\n\6\f\6\16\6\u010a\13\6\5\6\u010c\n\6\3\6\3\6\3\6\5\6\u0111\n\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u011e\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u0129\n\b\f\b\16\b\u012c\13\b\3\b\3\b\5\b\u0130"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u013a\n\t\f\t\16\t\u013d\13\t"+
		"\5\t\u013f\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\6\n\u014f\n\n\r\n\16\n\u0150\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\5\13\u015f\n\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0167\n"+
		"\f\r\f\16\f\u0168\3\r\3\r\3\16\3\16\3\16\3\16\3\16\5\16\u0172\n\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u017b\n\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u0182\n\17\f\17\16\17\u0185\13\17\3\20\3\20\3\20\5\20\u018a\n\20"+
		"\5\20\u018c\n\20\3\20\3\20\7\20\u0190\n\20\f\20\16\20\u0193\13\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\5\20\u019b\n\20\3\20\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\5\21\u01a5\n\21\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\7\24\u01b2\n\24\f\24\16\24\u01b5\13\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\5\25\u01c3\n\25\5\25\u01c5\n"+
		"\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u01cf\n\26\f\26\16\26"+
		"\u01d2\13\26\3\26\3\26\3\26\5\26\u01d7\n\26\3\26\3\26\7\26\u01db\n\26"+
		"\f\26\16\26\u01de\13\26\3\26\3\26\5\26\u01e2\n\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u01ea\n\27\3\27\3\27\5\27\u01ee\n\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u01fe\n\30"+
		"\f\30\16\30\u0201\13\30\3\30\3\30\3\30\5\30\u0206\n\30\3\30\3\30\3\30"+
		"\3\30\5\30\u020c\n\30\3\30\3\30\3\30\3\31\5\31\u0212\n\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u021f\n\31\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u022d\n\32\f\32"+
		"\16\32\u0230\13\32\3\32\3\32\3\32\5\32\u0235\n\32\3\32\3\32\3\32\3\32"+
		"\3\32\7\32\u023c\n\32\f\32\16\32\u023f\13\32\3\32\3\32\3\32\7\32\u0244"+
		"\n\32\f\32\16\32\u0247\13\32\3\32\7\32\u024a\n\32\f\32\16\32\u024d\13"+
		"\32\3\32\7\32\u0250\n\32\f\32\16\32\u0253\13\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u0263\n\33\f\33"+
		"\16\33\u0266\13\33\3\33\3\33\3\33\5\33\u026b\n\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\6\33\u0276\n\33\r\33\16\33\u0277\3\34\3\34\3"+
		"\34\5\34\u027d\n\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\7\35\u028b\n\35\f\35\16\35\u028e\13\35\3\35\3\35\3\35\5\35"+
		"\u0293\n\35\3\35\3\35\7\35\u0297\n\35\f\35\16\35\u029a\13\35\3\35\3\35"+
		"\3\35\3\35\5\35\u02a0\n\35\3\35\5\35\u02a3\n\35\3\36\3\36\5\36\u02a7\n"+
		"\36\3\36\3\36\3\36\7\36\u02ac\n\36\f\36\16\36\u02af\13\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\7\36\u02ba\n\36\f\36\16\36\u02bd\13"+
		"\36\5\36\u02bf\n\36\3\37\3\37\3\37\3\37\7\37\u02c5\n\37\f\37\16\37\u02c8"+
		"\13\37\3\37\3\37\3\37\3 \3 \5 \u02cf\n \3!\3!\5!\u02d3\n!\3!\3!\3!\3!"+
		"\7!\u02d9\n!\f!\16!\u02dc\13!\3!\3!\3!\3!\7!\u02e2\n!\f!\16!\u02e5\13"+
		"!\3!\3!\3\"\7\"\u02ea\n\"\f\"\16\"\u02ed\13\"\3#\3#\3#\3#\3#\2\3\b$\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BD\2\16"+
		"\3\2\668\3\29>\4\2##??\3\2\66\67\3\2\5\6\4\2\3\4??\3\2\f\17\3\2\21\22"+
		"\3\2\32\33\6\2\3\6\f\17\21\22??\7\2\3\7\f\17\21\22\32\33??\4\2BDFF\2\u0339"+
		"\2I\3\2\2\2\4L\3\2\2\2\6N\3\2\2\2\b\u0084\3\2\2\2\n\u0110\3\2\2\2\f\u0112"+
		"\3\2\2\2\16\u011d\3\2\2\2\20\u0131\3\2\2\2\22\u0146\3\2\2\2\24\u015e\3"+
		"\2\2\2\26\u0160\3\2\2\2\30\u016a\3\2\2\2\32\u0171\3\2\2\2\34\u0173\3\2"+
		"\2\2\36\u0186\3\2\2\2 \u01a1\3\2\2\2\"\u01a6\3\2\2\2$\u01a8\3\2\2\2&\u01aa"+
		"\3\2\2\2(\u01b8\3\2\2\2*\u01c6\3\2\2\2,\u01e9\3\2\2\2.\u01f1\3\2\2\2\60"+
		"\u0211\3\2\2\2\62\u0220\3\2\2\2\64\u0256\3\2\2\2\66\u027c\3\2\2\28\u027e"+
		"\3\2\2\2:\u02a4\3\2\2\2<\u02c0\3\2\2\2>\u02ce\3\2\2\2@\u02d0\3\2\2\2B"+
		"\u02eb\3\2\2\2D\u02ee\3\2\2\2FH\t\2\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2"+
		"IJ\3\2\2\2J\3\3\2\2\2KI\3\2\2\2LM\t\3\2\2M\5\3\2\2\2NO\t\4\2\2O\7\3\2"+
		"\2\2PQ\b\5\1\2Q\u0085\5\4\3\2R\u0085\5\6\4\2ST\7\n\2\2TU\5\2\2\2UV\5\b"+
		"\5\2VW\5\2\2\2WX\7\t\2\2X\u0085\3\2\2\2Y\u0085\5\n\6\2Z^\5\16\b\2[]\7"+
		"\66\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2"+
		"ab\5\n\6\2b\u0085\3\2\2\2c\u0085\5*\26\2de\7\7\2\2ef\5\2\2\2fg\5\b\5\t"+
		"g\u0085\3\2\2\2hi\7\24\2\2ij\5\2\2\2jk\5\b\5\2kl\5\2\2\2lm\7\25\2\2mq"+
		"\5\2\2\2np\5,\27\2on\3\2\2\2ps\3\2\2\2qo\3\2\2\2qr\3\2\2\2r}\3\2\2\2s"+
		"q\3\2\2\2tu\5\2\2\2uv\7\26\2\2vz\5\2\2\2wy\5,\27\2xw\3\2\2\2y|\3\2\2\2"+
		"zx\3\2\2\2z{\3\2\2\2{~\3\2\2\2|z\3\2\2\2}t\3\2\2\2}~\3\2\2\2~\u0080\3"+
		"\2\2\2\177\u0081\t\5\2\2\u0080\177\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082"+
		"\3\2\2\2\u0082\u0083\7\b\2\2\u0083\u0085\3\2\2\2\u0084P\3\2\2\2\u0084"+
		"R\3\2\2\2\u0084S\3\2\2\2\u0084Y\3\2\2\2\u0084Z\3\2\2\2\u0084c\3\2\2\2"+
		"\u0084d\3\2\2\2\u0084h\3\2\2\2\u0085\u00e8\3\2\2\2\u0086\u008a\f\b\2\2"+
		"\u0087\u0089\7\66\2\2\u0088\u0087\3\2\2\2\u0089\u008c\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d"+
		"\u008e\t\6\2\2\u008e\u008f\5\2\2\2\u008f\u0090\5\b\5\t\u0090\u00e7\3\2"+
		"\2\2\u0091\u0095\f\7\2\2\u0092\u0094\7\66\2\2\u0093\u0092\3\2\2\2\u0094"+
		"\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0098\u0099\t\7\2\2\u0099\u009a\5\2\2\2\u009a"+
		"\u009b\5\b\5\b\u009b\u00e7\3\2\2\2\u009c\u00a0\f\6\2\2\u009d\u009f\7\66"+
		"\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\t\b"+
		"\2\2\u00a4\u00a5\5\2\2\2\u00a5\u00a6\5\b\5\7\u00a6\u00e7\3\2\2\2\u00a7"+
		"\u00ab\f\5\2\2\u00a8\u00aa\7\66\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3"+
		"\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ae\u00af\t\t\2\2\u00af\u00b0\5\2\2\2\u00b0\u00b1\5\b"+
		"\5\6\u00b1\u00e7\3\2\2\2\u00b2\u00b6\f\4\2\2\u00b3\u00b5\7\66\2\2\u00b4"+
		"\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\t\n\2\2\u00ba"+
		"\u00bb\5\2\2\2\u00bb\u00bc\5\b\5\5\u00bc\u00e7\3\2\2\2\u00bd\u00be\f\17"+
		"\2\2\u00be\u00bf\5\2\2\2\u00bf\u00c0\7/\2\2\u00c0\u00c2\5\2\2\2\u00c1"+
		"\u00c3\5\34\17\2\u00c2\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c2\3"+
		"\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\t\5\2\2\u00c7"+
		"\u00c8\7\b\2\2\u00c8\u00e7\3\2\2\2\u00c9\u00ce\f\16\2\2\u00ca\u00cc\7"+
		"\67\2\2\u00cb\u00cd\7\66\2\2\u00cc\u00cb\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd"+
		"\u00cf\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d1\7\b\2\2\u00d1\u00d2\t\13\2\2\u00d2\u00d3\5\2\2\2\u00d3"+
		"\u00d4\5\n\6\2\u00d4\u00e7\3\2\2\2\u00d5\u00d9\f\f\2\2\u00d6\u00d8\7\66"+
		"\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00e7\5\n"+
		"\6\2\u00dd\u00e2\f\13\2\2\u00de\u00e0\7\67\2\2\u00df\u00e1\7\66\2\2\u00e0"+
		"\u00df\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00de\3\2"+
		"\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\7\b\2\2\u00e5"+
		"\u00e7\7?\2\2\u00e6\u0086\3\2\2\2\u00e6\u0091\3\2\2\2\u00e6\u009c\3\2"+
		"\2\2\u00e6\u00a7\3\2\2\2\u00e6\u00b2\3\2\2\2\u00e6\u00bd\3\2\2\2\u00e6"+
		"\u00c9\3\2\2\2\u00e6\u00d5\3\2\2\2\u00e6\u00dd\3\2\2\2\u00e7\u00ea\3\2"+
		"\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\t\3\2\2\2\u00ea\u00e8"+
		"\3\2\2\2\u00eb\u00ec\7\n\2\2\u00ec\u00f9\5\2\2\2\u00ed\u00ee\5\b\5\2\u00ee"+
		"\u00f6\5\2\2\2\u00ef\u00f0\7\13\2\2\u00f0\u00f1\5\2\2\2\u00f1\u00f2\5"+
		"\b\5\2\u00f2\u00f3\5\2\2\2\u00f3\u00f5\3\2\2\2\u00f4\u00ef\3\2\2\2\u00f5"+
		"\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00fa\3\2"+
		"\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00ed\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fc\5\2\2\2\u00fc\u00fd\7\t\2\2\u00fd\u0111\3\2"+
		"\2\2\u00fe\u00ff\7(\2\2\u00ff\u010b\5\2\2\2\u0100\u0101\5\b\5\2\u0101"+
		"\u0108\5\2\2\2\u0102\u0103\7\13\2\2\u0103\u0104\5\2\2\2\u0104\u0105\5"+
		"\b\5\2\u0105\u0107\3\2\2\2\u0106\u0102\3\2\2\2\u0107\u010a\3\2\2\2\u0108"+
		"\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010c\3\2\2\2\u010a\u0108\3\2"+
		"\2\2\u010b\u0100\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\u010e\5\2\2\2\u010e\u010f\7\b\2\2\u010f\u0111\3\2\2\2\u0110\u00eb\3\2"+
		"\2\2\u0110\u00fe\3\2\2\2\u0111\13\3\2\2\2\u0112\u0113\5\6\4\2\u0113\u0114"+
		"\5\2\2\2\u0114\u0115\7\37\2\2\u0115\u0116\5\2\2\2\u0116\u0117\5\32\16"+
		"\2\u0117\r\3\2\2\2\u0118\u0119\5\6\4\2\u0119\u011a\5\2\2\2\u011a\u011b"+
		"\7\b\2\2\u011b\u011c\5\2\2\2\u011c\u011e\3\2\2\2\u011d\u0118\3\2\2\2\u011d"+
		"\u011e\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u012f\7@\2\2\u0120\u0121\7\63"+
		"\2\2\u0121\u0122\5\2\2\2\u0122\u012a\5\32\16\2\u0123\u0124\5\2\2\2\u0124"+
		"\u0125\7\13\2\2\u0125\u0126\5\2\2\2\u0126\u0127\5\32\16\2\u0127\u0129"+
		"\3\2\2\2\u0128\u0123\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\u012d\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012e\7\64"+
		"\2\2\u012e\u0130\3\2\2\2\u012f\u0120\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\17\3\2\2\2\u0131\u013e\7\n\2\2\u0132\u0133\5\2\2\2\u0133\u013b\5\32\16"+
		"\2\u0134\u0135\5\2\2\2\u0135\u0136\7\13\2\2\u0136\u0137\5\2\2\2\u0137"+
		"\u0138\5\32\16\2\u0138\u013a\3\2\2\2\u0139\u0134\3\2\2\2\u013a\u013d\3"+
		"\2\2\2\u013b\u0139\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013f\3\2\2\2\u013d"+
		"\u013b\3\2\2\2\u013e\u0132\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0141\7\t\2\2\u0141\u0142\5\2\2\2\u0142\u0143\7 \2\2\u0143"+
		"\u0144\5\2\2\2\u0144\u0145\5\32\16\2\u0145\21\3\2\2\2\u0146\u0147\7\n"+
		"\2\2\u0147\u0148\5\2\2\2\u0148\u014e\5\f\7\2\u0149\u014a\5\2\2\2\u014a"+
		"\u014b\7\13\2\2\u014b\u014c\5\2\2\2\u014c\u014d\5\f\7\2\u014d\u014f\3"+
		"\2\2\2\u014e\u0149\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u014e\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0153\7\t\2\2\u0153\23\3\2\2"+
		"\2\u0154\u0155\7\n\2\2\u0155\u0156\5\2\2\2\u0156\u0157\5\26\f\2\u0157"+
		"\u0158\5\2\2\2\u0158\u0159\7\t\2\2\u0159\u015f\3\2\2\2\u015a\u015f\5\16"+
		"\b\2\u015b\u015f\5\20\t\2\u015c\u015f\5\22\n\2\u015d\u015f\5\30\r\2\u015e"+
		"\u0154\3\2\2\2\u015e\u015a\3\2\2\2\u015e\u015b\3\2\2\2\u015e\u015c\3\2"+
		"\2\2\u015e\u015d\3\2\2\2\u015f\25\3\2\2\2\u0160\u0166\5\24\13\2\u0161"+
		"\u0162\5\2\2\2\u0162\u0163\7\62\2\2\u0163\u0164\5\2\2\2\u0164\u0165\5"+
		"\24\13\2\u0165\u0167\3\2\2\2\u0166\u0161\3\2\2\2\u0167\u0168\3\2\2\2\u0168"+
		"\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169\27\3\2\2\2\u016a\u016b\7?\2\2"+
		"\u016b\31\3\2\2\2\u016c\u0172\5\16\b\2\u016d\u0172\5\22\n\2\u016e\u0172"+
		"\5\20\t\2\u016f\u0172\5\26\f\2\u0170\u0172\5\30\r\2\u0171\u016c\3\2\2"+
		"\2\u0171\u016d\3\2\2\2\u0171\u016e\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0170"+
		"\3\2\2\2\u0172\33\3\2\2\2\u0173\u0174\7.\2\2\u0174\u017a\5\2\2\2\u0175"+
		"\u0176\7?\2\2\u0176\u0177\5\2\2\2\u0177\u0178\7\37\2\2\u0178\u0179\5\2"+
		"\2\2\u0179\u017b\3\2\2\2\u017a\u0175\3\2\2\2\u017a\u017b\3\2\2\2\u017b"+
		"\u017c\3\2\2\2\u017c\u017d\5\32\16\2\u017d\u017e\5\2\2\2\u017e\u017f\7"+
		"\25\2\2\u017f\u0183\5\2\2\2\u0180\u0182\5,\27\2\u0181\u0180\3\2\2\2\u0182"+
		"\u0185\3\2\2\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184\35\3\2\2"+
		"\2\u0185\u0183\3\2\2\2\u0186\u0191\5\6\4\2\u0187\u0189\7\67\2\2\u0188"+
		"\u018a\7\66\2\2\u0189\u0188\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c\3"+
		"\2\2\2\u018b\u0187\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"\u018e\7\b\2\2\u018e\u0190\7?\2\2\u018f\u018b\3\2\2\2\u0190\u0193\3\2"+
		"\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0194\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0194\u019a\5\2\2\2\u0195\u019b\5\n\6\2\u0196\u0197\7\37"+
		"\2\2\u0197\u0198\5\2\2\2\u0198\u0199\5\32\16\2\u0199\u019b\3\2\2\2\u019a"+
		"\u0195\3\2\2\2\u019a\u0196\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019c\3\2"+
		"\2\2\u019c\u019d\5\2\2\2\u019d\u019e\7\20\2\2\u019e\u019f\5\2\2\2\u019f"+
		"\u01a0\5\b\5\2\u01a0\37\3\2\2\2\u01a1\u01a2\7+\2\2\u01a2\u01a4\5\2\2\2"+
		"\u01a3\u01a5\5\b\5\2\u01a4\u01a3\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5!\3"+
		"\2\2\2\u01a6\u01a7\7,\2\2\u01a7#\3\2\2\2\u01a8\u01a9\7-\2\2\u01a9%\3\2"+
		"\2\2\u01aa\u01ab\7\34\2\2\u01ab\u01ac\5\2\2\2\u01ac\u01ad\5\b\5\2\u01ad"+
		"\u01ae\5\2\2\2\u01ae\u01af\7\25\2\2\u01af\u01b3\5\2\2\2\u01b0\u01b2\5"+
		",\27\2\u01b1\u01b0\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b3"+
		"\u01b4\3\2\2\2\u01b4\u01b6\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6\u01b7\7\b"+
		"\2\2\u01b7\'\3\2\2\2\u01b8\u01b9\5\6\4\2\u01b9\u01c4\5\2\2\2\u01ba\u01bb"+
		"\7\37\2\2\u01bb\u01bc\5\2\2\2\u01bc\u01c2\5\32\16\2\u01bd\u01be\5\2\2"+
		"\2\u01be\u01bf\7\20\2\2\u01bf\u01c0\5\2\2\2\u01c0\u01c1\5\b\5\2\u01c1"+
		"\u01c3\3\2\2\2\u01c2\u01bd\3\2\2\2\u01c2\u01c3\3\2\2\2\u01c3\u01c5\3\2"+
		"\2\2\u01c4\u01ba\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5)\3\2\2\2\u01c6\u01d6"+
		"\7&\2\2\u01c7\u01c8\5\2\2\2\u01c8\u01c9\5(\25\2\u01c9\u01d0\5\2\2\2\u01ca"+
		"\u01cb\7\13\2\2\u01cb\u01cc\5\2\2\2\u01cc\u01cd\5(\25\2\u01cd\u01cf\3"+
		"\2\2\2\u01ce\u01ca\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0\u01ce\3\2\2\2\u01d0"+
		"\u01d1\3\2\2\2\u01d1\u01d3\3\2\2\2\u01d2\u01d0\3\2\2\2\u01d3\u01d4\5\2"+
		"\2\2\u01d4\u01d5\7 \2\2\u01d5\u01d7\3\2\2\2\u01d6\u01c7\3\2\2\2\u01d6"+
		"\u01d7\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01dc\5\2\2\2\u01d9\u01db\5,"+
		"\27\2\u01da\u01d9\3\2\2\2\u01db\u01de\3\2\2\2\u01dc\u01da\3\2\2\2\u01dc"+
		"\u01dd\3\2\2\2\u01dd\u01df\3\2\2\2\u01de\u01dc\3\2\2\2\u01df\u01e1\5\2"+
		"\2\2\u01e0\u01e2\7\b\2\2\u01e1\u01e0\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2"+
		"+\3\2\2\2\u01e3\u01ea\5\36\20\2\u01e4\u01ea\5\"\22\2\u01e5\u01ea\5$\23"+
		"\2\u01e6\u01ea\5&\24\2\u01e7\u01ea\5\b\5\2\u01e8\u01ea\5 \21\2\u01e9\u01e3"+
		"\3\2\2\2\u01e9\u01e4\3\2\2\2\u01e9\u01e5\3\2\2\2\u01e9\u01e6\3\2\2\2\u01e9"+
		"\u01e7\3\2\2\2\u01e9\u01e8\3\2\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\5\2"+
		"\2\2\u01ec\u01ee\7\23\2\2\u01ed\u01ec\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee"+
		"\u01ef\3\2\2\2\u01ef\u01f0\5\2\2\2\u01f0-\3\2\2\2\u01f1\u01f2\7!\2\2\u01f2"+
		"\u01f3\5\2\2\2\u01f3\u0205\7@\2\2\u01f4\u01f5\5\2\2\2\u01f5\u01f6\7\63"+
		"\2\2\u01f6\u01f7\5\2\2\2\u01f7\u01ff\5\30\r\2\u01f8\u01f9\5\2\2\2\u01f9"+
		"\u01fa\7\13\2\2\u01fa\u01fb\5\2\2\2\u01fb\u01fc\5\30\r\2\u01fc\u01fe\3"+
		"\2\2\2\u01fd\u01f8\3\2\2\2\u01fe\u0201\3\2\2\2\u01ff\u01fd\3\2\2\2\u01ff"+
		"\u0200\3\2\2\2\u0200\u0202\3\2\2\2\u0201\u01ff\3\2\2\2\u0202\u0203\5\2"+
		"\2\2\u0203\u0204\7\64\2\2\u0204\u0206\3\2\2\2\u0205\u01f4\3\2\2\2\u0205"+
		"\u0206\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0208\5\2\2\2\u0208\u0209\7\20"+
		"\2\2\u0209\u020b\5\2\2\2\u020a\u020c\7\60\2\2\u020b\u020a\3\2\2\2\u020b"+
		"\u020c\3\2\2\2\u020c\u020d\3\2\2\2\u020d\u020e\5\2\2\2\u020e\u020f\5D"+
		"#\2\u020f/\3\2\2\2\u0210\u0212\7#\2\2\u0211\u0210\3\2\2\2\u0211\u0212"+
		"\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0214\5\2\2\2\u0214\u0215\7?\2\2\u0215"+
		"\u0216\5\2\2\2\u0216\u0217\7\37\2\2\u0217\u0218\5\2\2\2\u0218\u021e\5"+
		"\32\16\2\u0219\u021a\5\2\2\2\u021a\u021b\7\20\2\2\u021b\u021c\5\2\2\2"+
		"\u021c\u021d\5\b\5\2\u021d\u021f\3\2\2\2\u021e\u0219\3\2\2\2\u021e\u021f"+
		"\3\2\2\2\u021f\61\3\2\2\2\u0220\u0221\7!\2\2\u0221\u0222\5\2\2\2\u0222"+
		"\u0234\7@\2\2\u0223\u0224\5\2\2\2\u0224\u0225\7\63\2\2\u0225\u0226\5\2"+
		"\2\2\u0226\u022e\5\30\r\2\u0227\u0228\5\2\2\2\u0228\u0229\7\13\2\2\u0229"+
		"\u022a\5\2\2\2\u022a\u022b\5\30\r\2\u022b\u022d\3\2\2\2\u022c\u0227\3"+
		"\2\2\2\u022d\u0230\3\2\2\2\u022e\u022c\3\2\2\2\u022e\u022f\3\2\2\2\u022f"+
		"\u0231\3\2\2\2\u0230\u022e\3\2\2\2\u0231\u0232\5\2\2\2\u0232\u0233\7\64"+
		"\2\2\u0233\u0235\3\2\2\2\u0234\u0223\3\2\2\2\u0234\u0235\3\2\2\2\u0235"+
		"\u0236\3\2\2\2\u0236\u0237\5\2\2\2\u0237\u0238\7\20\2\2\u0238\u0239\5"+
		"\2\2\2\u0239\u023d\7\n\2\2\u023a\u023c\7\67\2\2\u023b\u023a\3\2\2\2\u023c"+
		"\u023f\3\2\2\2\u023d\u023b\3\2\2\2\u023d\u023e\3\2\2\2\u023e\u0240\3\2"+
		"\2\2\u023f\u023d\3\2\2\2\u0240\u024b\5\60\31\2\u0241\u0245\7\13\2\2\u0242"+
		"\u0244\7\67\2\2\u0243\u0242\3\2\2\2\u0244\u0247\3\2\2\2\u0245\u0243\3"+
		"\2\2\2\u0245\u0246\3\2\2\2\u0246\u0248\3\2\2\2\u0247\u0245\3\2\2\2\u0248"+
		"\u024a\5\60\31\2\u0249\u0241\3\2\2\2\u024a\u024d\3\2\2\2\u024b\u0249\3"+
		"\2\2\2\u024b\u024c\3\2\2\2\u024c\u0251\3\2\2\2\u024d\u024b\3\2\2\2\u024e"+
		"\u0250\7\67\2\2\u024f\u024e\3\2\2\2\u0250\u0253\3\2\2\2\u0251\u024f\3"+
		"\2\2\2\u0251\u0252\3\2\2\2\u0252\u0254\3\2\2\2\u0253\u0251\3\2\2\2\u0254"+
		"\u0255\7\t\2\2\u0255\63\3\2\2\2\u0256\u0257\7!\2\2\u0257\u0258\5\2\2\2"+
		"\u0258\u0259\7@\2\2\u0259\u026a\5\2\2\2\u025a\u025b\7\63\2\2\u025b\u025c"+
		"\5\2\2\2\u025c\u0264\5\30\r\2\u025d\u025e\5\2\2\2\u025e\u025f\7\13\2\2"+
		"\u025f\u0260\5\2\2\2\u0260\u0261\5\30\r\2\u0261\u0263\3\2\2\2\u0262\u025d"+
		"\3\2\2\2\u0263\u0266\3\2\2\2\u0264\u0262\3\2\2\2\u0264\u0265\3\2\2\2\u0265"+
		"\u0267\3\2\2\2\u0266\u0264\3\2\2\2\u0267\u0268\5\2\2\2\u0268\u0269\7\64"+
		"\2\2\u0269\u026b\3\2\2\2\u026a\u025a\3\2\2\2\u026a\u026b\3\2\2\2\u026b"+
		"\u026c\3\2\2\2\u026c\u026d\5\2\2\2\u026d\u026e\7\20\2\2\u026e\u026f\5"+
		"\2\2\2\u026f\u0275\5\24\13\2\u0270\u0271\5\2\2\2\u0271\u0272\7\62\2\2"+
		"\u0272\u0273\5\2\2\2\u0273\u0274\5\24\13\2\u0274\u0276\3\2\2\2\u0275\u0270"+
		"\3\2\2\2\u0276\u0277\3\2\2\2\u0277\u0275\3\2\2\2\u0277\u0278\3\2\2\2\u0278"+
		"\65\3\2\2\2\u0279\u027d\5.\30\2\u027a\u027d\5\62\32\2\u027b\u027d\5\64"+
		"\33\2\u027c\u0279\3\2\2\2\u027c\u027a\3\2\2\2\u027c\u027b\3\2\2\2\u027d"+
		"\67\3\2\2\2\u027e\u027f\7%\2\2\u027f\u0280\5\2\2\2\u0280\u0281\t\f\2\2"+
		"\u0281\u0282\5\2\2\2\u0282\u0283\7\20\2\2\u0283\u0292\5\2\2\2\u0284\u0285"+
		"\5(\25\2\u0285\u028c\5\2\2\2\u0286\u0287\7\13\2\2\u0287\u0288\5\2\2\2"+
		"\u0288\u0289\5(\25\2\u0289\u028b\3\2\2\2\u028a\u0286\3\2\2\2\u028b\u028e"+
		"\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2\2\2\u028d\u028f\3\2\2\2\u028e"+
		"\u028c\3\2\2\2\u028f\u0290\5\2\2\2\u0290\u0291\7\25\2\2\u0291\u0293\3"+
		"\2\2\2\u0292\u0284\3\2\2\2\u0292\u0293\3\2\2\2\u0293\u0294\3\2\2\2\u0294"+
		"\u029f\5\2\2\2\u0295\u0297\5,\27\2\u0296\u0295\3\2\2\2\u0297\u029a\3\2"+
		"\2\2\u0298\u0296\3\2\2\2\u0298\u0299\3\2\2\2\u0299\u029b\3\2\2\2\u029a"+
		"\u0298\3\2\2\2\u029b\u029c\5\2\2\2\u029c\u029d\7\b\2\2\u029d\u02a0\3\2"+
		"\2\2\u029e\u02a0\5D#\2\u029f\u0298\3\2\2\2\u029f\u029e\3\2\2\2\u02a0\u02a2"+
		"\3\2\2\2\u02a1\u02a3\5\32\16\2\u02a2\u02a1\3\2\2\2\u02a2\u02a3\3\2\2\2"+
		"\u02a39\3\2\2\2\u02a4\u02a6\5\2\2\2\u02a5\u02a7\7\6\2\2\u02a6\u02a5\3"+
		"\2\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8\u02ad\7?\2\2\u02a9"+
		"\u02aa\7\6\2\2\u02aa\u02ac\7?\2\2\u02ab\u02a9\3\2\2\2\u02ac\u02af\3\2"+
		"\2\2\u02ad\u02ab\3\2\2\2\u02ad\u02ae\3\2\2\2\u02ae\u02be\3\2\2\2\u02af"+
		"\u02ad\3\2\2\2\u02b0\u02b1\5\2\2\2\u02b1\u02b2\7(\2\2\u02b2\u02b3\5\2"+
		"\2\2\u02b3\u02b4\7@\2\2\u02b4\u02bb\5\2\2\2\u02b5\u02b6\7\13\2\2\u02b6"+
		"\u02b7\5\2\2\2\u02b7\u02b8\7@\2\2\u02b8\u02ba\3\2\2\2\u02b9\u02b5\3\2"+
		"\2\2\u02ba\u02bd\3\2\2\2\u02bb\u02b9\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc"+
		"\u02bf\3\2\2\2\u02bd\u02bb\3\2\2\2\u02be\u02b0\3\2\2\2\u02be\u02bf\3\2"+
		"\2\2\u02bf;\3\2\2\2\u02c0\u02c1\7\'\2\2\u02c1\u02c6\5:\36\2\u02c2\u02c3"+
		"\7\67\2\2\u02c3\u02c5\5:\36\2\u02c4\u02c2\3\2\2\2\u02c5\u02c8\3\2\2\2"+
		"\u02c6\u02c4\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c9\3\2\2\2\u02c8\u02c6"+
		"\3\2\2\2\u02c9\u02ca\7\66\2\2\u02ca\u02cb\7\b\2\2\u02cb=\3\2\2\2\u02cc"+
		"\u02cf\5\66\34\2\u02cd\u02cf\58\35\2\u02ce\u02cc\3\2\2\2\u02ce\u02cd\3"+
		"\2\2\2\u02cf?\3\2\2\2\u02d0\u02d2\5\2\2\2\u02d1\u02d3\5<\37\2\u02d2\u02d1"+
		"\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02da\5\2\2\2\u02d5"+
		"\u02d6\5\2\2\2\u02d6\u02d7\5D#\2\u02d7\u02d9\3\2\2\2\u02d8\u02d5\3\2\2"+
		"\2\u02d9\u02dc\3\2\2\2\u02da\u02d8\3\2\2\2\u02da\u02db\3\2\2\2\u02db\u02dd"+
		"\3\2\2\2\u02dc\u02da\3\2\2\2\u02dd\u02e3\5\2\2\2\u02de\u02df\5> \2\u02df"+
		"\u02e0\5\2\2\2\u02e0\u02e2\3\2\2\2\u02e1\u02de\3\2\2\2\u02e2\u02e5\3\2"+
		"\2\2\u02e3\u02e1\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02e6\3\2\2\2\u02e5"+
		"\u02e3\3\2\2\2\u02e6\u02e7\7\2\2\3\u02e7A\3\2\2\2\u02e8\u02ea\t\r\2\2"+
		"\u02e9\u02e8\3\2\2\2\u02ea\u02ed\3\2\2\2\u02eb\u02e9\3\2\2\2\u02eb\u02ec"+
		"\3\2\2\2\u02ecC\3\2\2\2\u02ed\u02eb\3\2\2\2\u02ee\u02ef\7\65\2\2\u02ef"+
		"\u02f0\5B\"\2\u02f0\u02f1\7E\2\2\u02f1E\3\2\2\2RI^qz}\u0080\u0084\u008a"+
		"\u0095\u00a0\u00ab\u00b6\u00c4\u00cc\u00ce\u00d9\u00e0\u00e2\u00e6\u00e8"+
		"\u00f6\u00f9\u0108\u010b\u0110\u011d\u012a\u012f\u013b\u013e\u0150\u015e"+
		"\u0168\u0171\u017a\u0183\u0189\u018b\u0191\u019a\u01a4\u01b3\u01c2\u01c4"+
		"\u01d0\u01d6\u01dc\u01e1\u01e9\u01ed\u01ff\u0205\u020b\u0211\u021e\u022e"+
		"\u0234\u023d\u0245\u024b\u0251\u0264\u026a\u0277\u027c\u028c\u0292\u0298"+
		"\u029f\u02a2\u02a6\u02ad\u02bb\u02be\u02c6\u02ce\u02d2\u02da\u02e3\u02eb";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}