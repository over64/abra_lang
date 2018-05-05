// Generated from /home/over/build/abra_lang/abra_v2/grammar/M2Parser.g4 by ANTLR 4.7
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
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINUS=1, PLUS=2, MUL=3, DIV=4, EXCL=5, DOT=6, RB=7, LB=8, COMMA=9, MORE_=10, 
		MORE_EQ=11, LESS=12, LESS_EQ=13, EQ=14, EQEQ=15, NOTEQ=16, SEMI=17, IF=18, 
		DO=19, ELSE=20, CBO=21, DOLLAR_CBO=22, CBC=23, LOGIC_OR=24, LOGIC_AND=25, 
		WHILE=26, VAL=27, VAR=28, CON=29, ARROW_RIGHT=30, TYPE=31, BACK_SLASH=32, 
		SELF=33, MATCH_SELF=34, DEF=35, LAMBDA=36, IMPORT=37, WITH=38, MATCH=39, 
		OF=40, RETURN=41, IS=42, WHEN=43, REF=44, DASH=45, VERT_LINE=46, BRACKET_LEFT=47, 
		BRACKET_RIGTH=48, LlBegin=49, WS=50, NL=51, COMMENT=52, IntLiteral=53, 
		HexLiteral=54, FloatLiteral=55, BooleanLiteral=56, NoneLiteral=57, StringLiteral=58, 
		VarId=59, TypeId=60, MatchId=61, LLVM_NL=62, LLVM_WS=63, IrLine=64, LL_End=65, 
		LL_Dot=66;
	public static final int
		RULE_sp = 0, RULE_literal = 1, RULE_id = 2, RULE_expression = 3, RULE_tuple = 4, 
		RULE_fieldTh = 5, RULE_scalarTh = 6, RULE_fnTh = 7, RULE_structTh = 8, 
		RULE_nonUnionTh = 9, RULE_unionTh = 10, RULE_typeHint = 11, RULE_is = 12, 
		RULE_store = 13, RULE_ret = 14, RULE_while_stat = 15, RULE_fnArg = 16, 
		RULE_lambda = 17, RULE_blockBody = 18, RULE_scalarType = 19, RULE_typeField = 20, 
		RULE_structType = 21, RULE_unionType = 22, RULE_type = 23, RULE_def = 24, 
		RULE_importEntry = 25, RULE_import_ = 26, RULE_level1 = 27, RULE_module = 28, 
		RULE_llvmBody = 29, RULE_llvm = 30;
	public static final String[] ruleNames = {
		"sp", "literal", "id", "expression", "tuple", "fieldTh", "scalarTh", "fnTh", 
		"structTh", "nonUnionTh", "unionTh", "typeHint", "is", "store", "ret", 
		"while_stat", "fnArg", "lambda", "blockBody", "scalarType", "typeField", 
		"structType", "unionType", "type", "def", "importEntry", "import_", "level1", 
		"module", "llvmBody", "llvm"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", null, "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'do'", "'else'", 
		"'{'", "'$('", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", "':'", 
		"'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'lambda'", "'import'", 
		"'with'", "'match'", "'of'", "'return'", "'is'", "'when'", "'ref'", "'_'", 
		"'|'", "'['", "']'", null, null, null, null, null, null, null, null, "'none'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "DO", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
		"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
		"MATCH_SELF", "DEF", "LAMBDA", "IMPORT", "WITH", "MATCH", "OF", "RETURN", 
		"IS", "WHEN", "REF", "DASH", "VERT_LINE", "BRACKET_LEFT", "BRACKET_RIGTH", 
		"LlBegin", "WS", "NL", "COMMENT", "IntLiteral", "HexLiteral", "FloatLiteral", 
		"BooleanLiteral", "NoneLiteral", "StringLiteral", "VarId", "TypeId", "MatchId", 
		"LLVM_NL", "LLVM_WS", "IrLine", "LL_End", "LL_Dot"
	};
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
			setState(65);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(62);
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
				setState(67);
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
			setState(68);
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
			setState(70);
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
	public static class ExprWnenContext extends ExpressionContext {
		public ExpressionContext expr;
		public BlockBodyContext blockBody;
		public List<BlockBodyContext> elseStat = new ArrayList<BlockBodyContext>();
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
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
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public ExprWnenContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprWnen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprWnen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprWnen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprTypeIdContext extends ExpressionContext {
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public ExprTypeIdContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).enterExprTypeId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof M2ParserListener ) ((M2ParserListener)listener).exitExprTypeId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprTypeId(this);
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
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
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
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
		public List<TerminalNode> DOT() { return getTokens(M2Parser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(M2Parser.DOT, i);
		}
		public List<TerminalNode> VarId() { return getTokens(M2Parser.VarId); }
		public TerminalNode VarId(int i) {
			return getToken(M2Parser.VarId, i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
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
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TerminalNode NL() { return getToken(M2Parser.NL, 0); }
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
		}
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
			setState(141);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(73);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				id();
				}
				break;
			case 3:
				{
				_localctx = new ExprTypeIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(75);
				match(TypeId);
				}
				break;
			case 4:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(76);
				match(LB);
				setState(77);
				sp();
				setState(78);
				expression(0);
				setState(79);
				sp();
				setState(80);
				match(RB);
				}
				break;
			case 5:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				tuple();
				}
				break;
			case 6:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				lambda();
				}
				break;
			case 7:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(85);
				sp();
				setState(86);
				expression(8);
				}
				break;
			case 8:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				match(IF);
				setState(89);
				sp();
				setState(90);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(91);
				sp();
				setState(92);
				match(DO);
				setState(93);
				sp();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(94);
					((ExprIfElseContext)_localctx).blockBody = blockBody();
					((ExprIfElseContext)_localctx).doStat.add(((ExprIfElseContext)_localctx).blockBody);
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(100);
					sp();
					setState(101);
					match(ELSE);
					setState(102);
					sp();
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(103);
						((ExprIfElseContext)_localctx).blockBody = blockBody();
						((ExprIfElseContext)_localctx).elseStat.add(((ExprIfElseContext)_localctx).blockBody);
						}
						}
						setState(108);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(112); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(111);
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
					setState(114); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WS || _la==NL );
				setState(116);
				match(DOT);
				}
				break;
			case 9:
				{
				_localctx = new ExprWnenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(118);
				match(WHEN);
				setState(119);
				sp();
				setState(120);
				((ExprWnenContext)_localctx).expr = expression(0);
				setState(121);
				sp();
				setState(123); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(122);
					is();
					}
					}
					setState(125); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IS );
				setState(127);
				sp();
				setState(136);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(128);
					match(ELSE);
					setState(129);
					sp();
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(130);
						((ExprWnenContext)_localctx).blockBody = blockBody();
						((ExprWnenContext)_localctx).elseStat.add(((ExprWnenContext)_localctx).blockBody);
						}
						}
						setState(135);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(138);
				_la = _input.LA(1);
				if ( !(_la==WS || _la==NL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(139);
				match(DOT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(274);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(272);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(143);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
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
						if ( !(_la==MUL || _la==DIV) ) {
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
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(154);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
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
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << VarId))) != 0)) ) {
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
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
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
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ))) != 0)) ) {
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
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(176);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
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
						if ( !(_la==EQEQ || _la==NOTEQ) ) {
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
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(191);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(188);
							match(WS);
							}
							}
							setState(193);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(194);
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
						setState(195);
						sp();
						setState(196);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(198);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(203);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(199);
							match(NL);
							setState(201);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(200);
								match(WS);
								}
							}

							}
						}

						setState(205);
						match(DOT);
						setState(206);
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
						setState(223);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
						case 1:
							{
							setState(207);
							sp();
							setState(208);
							match(BRACKET_LEFT);
							setState(209);
							sp();
							setState(210);
							typeHint();
							setState(218);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(211);
								sp();
								setState(212);
								match(COMMA);
								setState(213);
								sp();
								setState(214);
								typeHint();
								}
								}
								setState(220);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(221);
							match(BRACKET_RIGTH);
							}
							break;
						}
						setState(225);
						sp();
						setState(226);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(228);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(232);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(229);
								match(WS);
								}
								} 
							}
							setState(234);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
						}
						setState(250);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==BRACKET_LEFT) {
							{
							setState(235);
							match(BRACKET_LEFT);
							setState(236);
							sp();
							setState(237);
							typeHint();
							setState(245);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(238);
								sp();
								setState(239);
								match(COMMA);
								setState(240);
								sp();
								setState(241);
								typeHint();
								}
								}
								setState(247);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(248);
							match(BRACKET_RIGTH);
							}
						}

						setState(255);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(252);
							match(WS);
							}
							}
							setState(257);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(258);
						tuple();
						}
						break;
					case 8:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(259);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(268); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(264);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==NL) {
									{
									setState(260);
									match(NL);
									setState(262);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la==WS) {
										{
										setState(261);
										match(WS);
										}
									}

									}
								}

								setState(266);
								match(DOT);
								setState(267);
								((ExprPropContext)_localctx).VarId = match(VarId);
								((ExprPropContext)_localctx).op.add(((ExprPropContext)_localctx).VarId);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(270); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(276);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
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
			setState(313);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(277);
				match(LB);
				setState(278);
				sp();
				setState(290);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(279);
					expression(0);
					setState(280);
					sp();
					setState(287);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(281);
						match(COMMA);
						setState(282);
						sp();
						setState(283);
						expression(0);
						}
						}
						setState(289);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(292);
				sp();
				setState(293);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				match(WITH);
				setState(296);
				sp();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(297);
					expression(0);
					setState(298);
					sp();
					setState(305);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(299);
						match(COMMA);
						setState(300);
						sp();
						setState(301);
						expression(0);
						}
						}
						setState(307);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(310);
				sp();
				setState(311);
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
			setState(315);
			id();
			setState(316);
			sp();
			setState(317);
			match(CON);
			setState(318);
			sp();
			setState(319);
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
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
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
			setState(326);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(321);
				id();
				setState(322);
				sp();
				setState(323);
				match(DOT);
				setState(324);
				sp();
				}
			}

			setState(328);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(344);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(329);
				match(BRACKET_LEFT);
				setState(330);
				sp();
				setState(331);
				typeHint();
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(332);
					sp();
					setState(333);
					match(COMMA);
					setState(334);
					sp();
					setState(335);
					typeHint();
					}
					}
					setState(341);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(342);
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BACK_SLASH) {
				{
				setState(346);
				match(BACK_SLASH);
				setState(347);
				sp();
				setState(348);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(356);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(349);
						sp();
						setState(350);
						match(COMMA);
						setState(351);
						sp();
						setState(352);
						((FnThContext)_localctx).typeHint = typeHint();
						((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
						}
						} 
					}
					setState(358);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,35,_ctx);
				}
				}
			}

			setState(361);
			sp();
			setState(362);
			match(ARROW_RIGHT);
			setState(363);
			sp();
			setState(364);
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
			setState(366);
			match(LB);
			setState(367);
			sp();
			setState(368);
			fieldTh();
			setState(374); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(369);
				sp();
				setState(370);
				match(COMMA);
				setState(371);
				sp();
				setState(372);
				fieldTh();
				}
				}
				setState(376); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(378);
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
		public ScalarThContext scalarTh() {
			return getRuleContext(ScalarThContext.class,0);
		}
		public FnThContext fnTh() {
			return getRuleContext(FnThContext.class,0);
		}
		public StructThContext structTh() {
			return getRuleContext(StructThContext.class,0);
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
			setState(383);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELF:
			case VarId:
			case TypeId:
				enterOuterAlt(_localctx, 1);
				{
				setState(380);
				scalarTh();
				}
				break;
			case ARROW_RIGHT:
			case BACK_SLASH:
			case WS:
			case NL:
			case COMMENT:
				enterOuterAlt(_localctx, 2);
				{
				setState(381);
				fnTh();
				}
				break;
			case LB:
				enterOuterAlt(_localctx, 3);
				{
				setState(382);
				structTh();
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
			setState(385);
			nonUnionTh();
			setState(391); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(386);
					sp();
					setState(387);
					match(VERT_LINE);
					setState(388);
					sp();
					setState(389);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(393); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
		enterRule(_localctx, 22, RULE_typeHint);
		try {
			setState(399);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(395);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(396);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(397);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(398);
				unionTh();
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
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
		enterRule(_localctx, 24, RULE_is);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			match(IS);
			setState(402);
			sp();
			setState(403);
			match(VarId);
			setState(404);
			sp();
			setState(405);
			match(CON);
			setState(406);
			sp();
			setState(407);
			typeHint();
			setState(408);
			sp();
			setState(409);
			match(DO);
			setState(410);
			sp();
			setState(414);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(411);
				blockBody();
				}
				}
				setState(416);
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
		enterRule(_localctx, 26, RULE_store);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			id();
			setState(428);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(422);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(418);
						match(NL);
						setState(420);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(419);
							match(WS);
							}
						}

						}
					}

					setState(424);
					match(DOT);
					setState(425);
					match(VarId);
					}
					} 
				}
				setState(430);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,44,_ctx);
			}
			setState(431);
			sp();
			setState(437);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(432);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(433);
				match(CON);
				setState(434);
				sp();
				setState(435);
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
			setState(439);
			sp();
			setState(440);
			match(EQ);
			setState(441);
			sp();
			setState(442);
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
		enterRule(_localctx, 28, RULE_ret);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			match(RETURN);
			setState(445);
			sp();
			setState(447);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(446);
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

	public static class While_statContext extends ParserRuleContext {
		public ExpressionContext cond;
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
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
		enterRule(_localctx, 30, RULE_while_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(449);
			match(WHILE);
			setState(450);
			sp();
			setState(451);
			((While_statContext)_localctx).cond = expression(0);
			setState(452);
			sp();
			setState(453);
			match(DO);
			setState(454);
			sp();
			setState(458);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(455);
				blockBody();
				}
				}
				setState(460);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(461);
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
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
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
		enterRule(_localctx, 32, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			id();
			setState(464);
			sp();
			setState(475);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(465);
				match(CON);
				setState(466);
				sp();
				setState(467);
				typeHint();
				setState(473);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,48,_ctx) ) {
				case 1:
					{
					setState(468);
					sp();
					setState(469);
					match(EQ);
					setState(470);
					sp();
					setState(471);
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
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
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
		enterRule(_localctx, 34, RULE_lambda);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(477);
			match(LAMBDA);
			setState(493);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(478);
				sp();
				setState(479);
				fnArg();
				setState(480);
				sp();
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(481);
					match(COMMA);
					setState(482);
					sp();
					setState(483);
					fnArg();
					}
					}
					setState(489);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(490);
				sp();
				setState(491);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(495);
			sp();
			setState(499);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(496);
					blockBody();
					}
					} 
				}
				setState(501);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,52,_ctx);
			}
			setState(502);
			sp();
			setState(504);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(503);
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
		public While_statContext while_stat() {
			return getRuleContext(While_statContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RetContext ret() {
			return getRuleContext(RetContext.class,0);
		}
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
		enterRule(_localctx, 36, RULE_blockBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(510);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(506);
				store();
				}
				break;
			case 2:
				{
				setState(507);
				while_stat();
				}
				break;
			case 3:
				{
				setState(508);
				expression(0);
				}
				break;
			case 4:
				{
				setState(509);
				ret();
				}
				break;
			}
			setState(512);
			sp();
			setState(514);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
			case 1:
				{
				setState(513);
				match(SEMI);
				}
				break;
			}
			setState(516);
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
		public Token TypeId;
		public List<Token> params = new ArrayList<Token>();
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public LlvmContext llvm() {
			return getRuleContext(LlvmContext.class,0);
		}
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
		}
		public TerminalNode REF() { return getToken(M2Parser.REF, 0); }
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
		enterRule(_localctx, 38, RULE_scalarType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			match(TYPE);
			setState(519);
			sp();
			setState(520);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(533);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(521);
				sp();
				setState(522);
				match(BRACKET_LEFT);
				setState(523);
				((ScalarTypeContext)_localctx).TypeId = match(TypeId);
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
				setState(528);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(524);
					match(COMMA);
					setState(525);
					((ScalarTypeContext)_localctx).TypeId = match(TypeId);
					((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
					}
					}
					setState(530);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(531);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(535);
			sp();
			setState(536);
			match(EQ);
			setState(537);
			sp();
			setState(539);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(538);
				match(REF);
				}
			}

			setState(541);
			sp();
			setState(542);
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
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
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
		enterRule(_localctx, 40, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(545);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(544);
				match(SELF);
				}
			}

			setState(547);
			sp();
			setState(548);
			match(VarId);
			setState(549);
			sp();
			setState(550);
			match(CON);
			setState(551);
			sp();
			setState(552);
			typeHint();
			setState(558);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,60,_ctx) ) {
			case 1:
				{
				setState(553);
				sp();
				setState(554);
				match(EQ);
				setState(555);
				sp();
				setState(556);
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
		public Token TypeId;
		public List<Token> params = new ArrayList<Token>();
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TypeFieldContext> typeField() {
			return getRuleContexts(TypeFieldContext.class);
		}
		public TypeFieldContext typeField(int i) {
			return getRuleContext(TypeFieldContext.class,i);
		}
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
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
		enterRule(_localctx, 42, RULE_structType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(560);
			match(TYPE);
			setState(561);
			sp();
			setState(562);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(575);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,62,_ctx) ) {
			case 1:
				{
				setState(563);
				sp();
				setState(564);
				match(BRACKET_LEFT);
				setState(565);
				((StructTypeContext)_localctx).TypeId = match(TypeId);
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
				setState(570);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(566);
					match(COMMA);
					setState(567);
					((StructTypeContext)_localctx).TypeId = match(TypeId);
					((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
					}
					}
					setState(572);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(573);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(577);
			sp();
			setState(578);
			match(EQ);
			setState(579);
			sp();
			setState(580);
			match(LB);
			setState(584);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(581);
					match(NL);
					}
					} 
				}
				setState(586);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
			setState(587);
			typeField();
			setState(598);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(588);
				match(COMMA);
				setState(592);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(589);
						match(NL);
						}
						} 
					}
					setState(594);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
				}
				setState(595);
				typeField();
				}
				}
				setState(600);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(604);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(601);
				match(NL);
				}
				}
				setState(606);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(607);
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
		public Token TypeId;
		public List<Token> params = new ArrayList<Token>();
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<ScalarThContext> scalarTh() {
			return getRuleContexts(ScalarThContext.class);
		}
		public ScalarThContext scalarTh(int i) {
			return getRuleContext(ScalarThContext.class,i);
		}
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
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
		enterRule(_localctx, 44, RULE_unionType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			match(TYPE);
			setState(610);
			sp();
			setState(611);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(612);
			sp();
			setState(623);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(613);
				match(BRACKET_LEFT);
				setState(614);
				((UnionTypeContext)_localctx).TypeId = match(TypeId);
				((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
				setState(619);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(615);
					match(COMMA);
					setState(616);
					((UnionTypeContext)_localctx).TypeId = match(TypeId);
					((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
					}
					}
					setState(621);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(622);
				match(BRACKET_RIGTH);
				}
			}

			setState(625);
			sp();
			setState(626);
			match(EQ);
			setState(627);
			sp();
			setState(628);
			scalarTh();
			setState(634); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(629);
					sp();
					setState(630);
					match(VERT_LINE);
					setState(631);
					sp();
					setState(632);
					scalarTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(636); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
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
		enterRule(_localctx, 46, RULE_type);
		try {
			setState(641);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,70,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(638);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(639);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(640);
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
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public LlvmContext llvm() {
			return getRuleContext(LlvmContext.class,0);
		}
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
		}
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode DOT() { return getToken(M2Parser.DOT, 0); }
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
		enterRule(_localctx, 48, RULE_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(643);
			match(DEF);
			setState(644);
			sp();
			setState(645);
			((DefContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << EXCL) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << VarId))) != 0)) ) {
				((DefContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(646);
			sp();
			setState(657);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(647);
				match(BRACKET_LEFT);
				setState(648);
				match(TypeId);
				setState(653);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(649);
					match(COMMA);
					setState(650);
					match(TypeId);
					}
					}
					setState(655);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(656);
				match(BRACKET_RIGTH);
				}
			}

			setState(659);
			sp();
			setState(660);
			match(EQ);
			setState(661);
			sp();
			setState(676);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				{
				setState(662);
				fnArg();
				setState(663);
				sp();
				setState(670);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(664);
					match(COMMA);
					setState(665);
					sp();
					setState(666);
					fnArg();
					}
					}
					setState(672);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(673);
				sp();
				setState(674);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(678);
			sp();
			setState(689);
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
			case WHEN:
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
				setState(682);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(679);
					blockBody();
					}
					}
					setState(684);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(685);
				sp();
				setState(686);
				match(DOT);
				}
				}
				break;
			case LlBegin:
				{
				setState(688);
				llvm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(692);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(691);
				typeHint();
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
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
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
		enterRule(_localctx, 50, RULE_importEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(694);
			sp();
			setState(696);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIV) {
				{
				setState(695);
				((ImportEntryContext)_localctx).abs = match(DIV);
				}
			}

			setState(698);
			match(VarId);
			setState(703);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV) {
				{
				{
				setState(699);
				match(DIV);
				setState(700);
				match(VarId);
				}
				}
				setState(705);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(720);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(706);
				sp();
				setState(707);
				match(WITH);
				setState(708);
				sp();
				setState(709);
				match(TypeId);
				setState(710);
				sp();
				setState(717);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(711);
					match(COMMA);
					setState(712);
					sp();
					setState(713);
					match(TypeId);
					}
					}
					setState(719);
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
		enterRule(_localctx, 52, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(722);
			match(IMPORT);
			setState(723);
			importEntry();
			setState(728);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(724);
				match(NL);
				setState(725);
				importEntry();
				}
				}
				setState(730);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(731);
			match(WS);
			setState(732);
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
		enterRule(_localctx, 54, RULE_level1);
		try {
			setState(736);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(734);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(735);
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
		public List<Import_Context> import_() {
			return getRuleContexts(Import_Context.class);
		}
		public Import_Context import_(int i) {
			return getRuleContext(Import_Context.class,i);
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
		enterRule(_localctx, 56, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(743);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(738);
					sp();
					setState(739);
					import_();
					}
					} 
				}
				setState(745);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(746);
			sp();
			setState(752);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(747);
					sp();
					setState(748);
					llvm();
					}
					} 
				}
				setState(754);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,85,_ctx);
			}
			setState(755);
			sp();
			setState(761);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(756);
				level1();
				setState(757);
				sp();
				}
				}
				setState(763);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(764);
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
		enterRule(_localctx, 58, RULE_llvmBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(769);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (LLVM_NL - 62)) | (1L << (LLVM_WS - 62)) | (1L << (IrLine - 62)) | (1L << (LL_Dot - 62)))) != 0)) {
				{
				{
				setState(766);
				_la = _input.LA(1);
				if ( !(((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (LLVM_NL - 62)) | (1L << (LLVM_WS - 62)) | (1L << (IrLine - 62)) | (1L << (LL_Dot - 62)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(771);
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
		enterRule(_localctx, 60, RULE_llvm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(772);
			match(LlBegin);
			setState(773);
			llvmBody();
			setState(774);
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
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		case 3:
			return precpred(_ctx, 4);
		case 4:
			return precpred(_ctx, 3);
		case 5:
			return precpred(_ctx, 12);
		case 6:
			return precpred(_ctx, 11);
		case 7:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3D\u030b\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\7\2B\n\2\f\2\16\2E\13\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5b\n"+
		"\5\f\5\16\5e\13\5\3\5\3\5\3\5\3\5\7\5k\n\5\f\5\16\5n\13\5\5\5p\n\5\3\5"+
		"\6\5s\n\5\r\5\16\5t\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5~\n\5\r\5\16\5\177"+
		"\3\5\3\5\3\5\3\5\7\5\u0086\n\5\f\5\16\5\u0089\13\5\5\5\u008b\n\5\3\5\3"+
		"\5\3\5\5\5\u0090\n\5\3\5\3\5\7\5\u0094\n\5\f\5\16\5\u0097\13\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\7\5\u009f\n\5\f\5\16\5\u00a2\13\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\7\5\u00aa\n\5\f\5\16\5\u00ad\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00b5"+
		"\n\5\f\5\16\5\u00b8\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00c0\n\5\f\5\16"+
		"\5\u00c3\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00cc\n\5\5\5\u00ce\n\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00db\n\5\f\5\16\5\u00de"+
		"\13\5\3\5\3\5\5\5\u00e2\n\5\3\5\3\5\3\5\3\5\3\5\7\5\u00e9\n\5\f\5\16\5"+
		"\u00ec\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00f6\n\5\f\5\16\5\u00f9"+
		"\13\5\3\5\3\5\5\5\u00fd\n\5\3\5\7\5\u0100\n\5\f\5\16\5\u0103\13\5\3\5"+
		"\3\5\3\5\3\5\5\5\u0109\n\5\5\5\u010b\n\5\3\5\3\5\6\5\u010f\n\5\r\5\16"+
		"\5\u0110\7\5\u0113\n\5\f\5\16\5\u0116\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\7\6\u0120\n\6\f\6\16\6\u0123\13\6\5\6\u0125\n\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0132\n\6\f\6\16\6\u0135\13\6\5\6\u0137"+
		"\n\6\3\6\3\6\3\6\5\6\u013c\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\5\b\u0149\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0154\n\b\f"+
		"\b\16\b\u0157\13\b\3\b\3\b\5\b\u015b\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\t\u0165\n\t\f\t\16\t\u0168\13\t\5\t\u016a\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0179\n\n\r\n\16\n\u017a\3\n\3\n"+
		"\3\13\3\13\3\13\5\13\u0182\n\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u018a\n\f"+
		"\r\f\16\f\u018b\3\r\3\r\3\r\3\r\5\r\u0192\n\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u019f\n\16\f\16\16\16\u01a2\13\16"+
		"\3\17\3\17\3\17\5\17\u01a7\n\17\5\17\u01a9\n\17\3\17\3\17\7\17\u01ad\n"+
		"\17\f\17\16\17\u01b0\13\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u01b8\n"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\5\20\u01c2\n\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\7\21\u01cb\n\21\f\21\16\21\u01ce\13\21\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u01dc\n\22"+
		"\5\22\u01de\n\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\7\23\u01e8\n"+
		"\23\f\23\16\23\u01eb\13\23\3\23\3\23\3\23\5\23\u01f0\n\23\3\23\3\23\7"+
		"\23\u01f4\n\23\f\23\16\23\u01f7\13\23\3\23\3\23\5\23\u01fb\n\23\3\24\3"+
		"\24\3\24\3\24\5\24\u0201\n\24\3\24\3\24\5\24\u0205\n\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0211\n\25\f\25\16\25\u0214\13"+
		"\25\3\25\3\25\5\25\u0218\n\25\3\25\3\25\3\25\3\25\5\25\u021e\n\25\3\25"+
		"\3\25\3\25\3\26\5\26\u0224\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\5\26\u0231\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\7\27\u023b\n\27\f\27\16\27\u023e\13\27\3\27\3\27\5\27\u0242\n\27\3\27"+
		"\3\27\3\27\3\27\3\27\7\27\u0249\n\27\f\27\16\27\u024c\13\27\3\27\3\27"+
		"\3\27\7\27\u0251\n\27\f\27\16\27\u0254\13\27\3\27\7\27\u0257\n\27\f\27"+
		"\16\27\u025a\13\27\3\27\7\27\u025d\n\27\f\27\16\27\u0260\13\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u026c\n\30\f\30\16\30"+
		"\u026f\13\30\3\30\5\30\u0272\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\6\30\u027d\n\30\r\30\16\30\u027e\3\31\3\31\3\31\5\31\u0284\n"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u028e\n\32\f\32\16\32"+
		"\u0291\13\32\3\32\5\32\u0294\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\7\32\u029f\n\32\f\32\16\32\u02a2\13\32\3\32\3\32\3\32\5\32\u02a7"+
		"\n\32\3\32\3\32\7\32\u02ab\n\32\f\32\16\32\u02ae\13\32\3\32\3\32\3\32"+
		"\3\32\5\32\u02b4\n\32\3\32\5\32\u02b7\n\32\3\33\3\33\5\33\u02bb\n\33\3"+
		"\33\3\33\3\33\7\33\u02c0\n\33\f\33\16\33\u02c3\13\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\7\33\u02ce\n\33\f\33\16\33\u02d1\13\33\5"+
		"\33\u02d3\n\33\3\34\3\34\3\34\3\34\7\34\u02d9\n\34\f\34\16\34\u02dc\13"+
		"\34\3\34\3\34\3\34\3\35\3\35\5\35\u02e3\n\35\3\36\3\36\3\36\7\36\u02e8"+
		"\n\36\f\36\16\36\u02eb\13\36\3\36\3\36\3\36\3\36\7\36\u02f1\n\36\f\36"+
		"\16\36\u02f4\13\36\3\36\3\36\3\36\3\36\7\36\u02fa\n\36\f\36\16\36\u02fd"+
		"\13\36\3\36\3\36\3\37\7\37\u0302\n\37\f\37\16\37\u0305\13\37\3 \3 \3 "+
		"\3 \3 \2\3\b!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>\2\16\3\2\64\66\3\2\67<\4\2##==\3\2\64\65\3\2\5\6\4\2\3\4==\3\2"+
		"\f\17\3\2\21\22\3\2\32\33\6\2\3\6\f\17\21\22==\6\2\3\7\f\17\21\22==\4"+
		"\2@BDD\2\u0357\2C\3\2\2\2\4F\3\2\2\2\6H\3\2\2\2\b\u008f\3\2\2\2\n\u013b"+
		"\3\2\2\2\f\u013d\3\2\2\2\16\u0148\3\2\2\2\20\u0169\3\2\2\2\22\u0170\3"+
		"\2\2\2\24\u0181\3\2\2\2\26\u0183\3\2\2\2\30\u0191\3\2\2\2\32\u0193\3\2"+
		"\2\2\34\u01a3\3\2\2\2\36\u01be\3\2\2\2 \u01c3\3\2\2\2\"\u01d1\3\2\2\2"+
		"$\u01df\3\2\2\2&\u0200\3\2\2\2(\u0208\3\2\2\2*\u0223\3\2\2\2,\u0232\3"+
		"\2\2\2.\u0263\3\2\2\2\60\u0283\3\2\2\2\62\u0285\3\2\2\2\64\u02b8\3\2\2"+
		"\2\66\u02d4\3\2\2\28\u02e2\3\2\2\2:\u02e9\3\2\2\2<\u0303\3\2\2\2>\u0306"+
		"\3\2\2\2@B\t\2\2\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\3\3\2\2\2"+
		"EC\3\2\2\2FG\t\3\2\2G\5\3\2\2\2HI\t\4\2\2I\7\3\2\2\2JK\b\5\1\2K\u0090"+
		"\5\4\3\2L\u0090\5\6\4\2M\u0090\7>\2\2NO\7\n\2\2OP\5\2\2\2PQ\5\b\5\2QR"+
		"\5\2\2\2RS\7\t\2\2S\u0090\3\2\2\2T\u0090\5\n\6\2U\u0090\5$\23\2VW\7\7"+
		"\2\2WX\5\2\2\2XY\5\b\5\nY\u0090\3\2\2\2Z[\7\24\2\2[\\\5\2\2\2\\]\5\b\5"+
		"\2]^\5\2\2\2^_\7\25\2\2_c\5\2\2\2`b\5&\24\2a`\3\2\2\2be\3\2\2\2ca\3\2"+
		"\2\2cd\3\2\2\2do\3\2\2\2ec\3\2\2\2fg\5\2\2\2gh\7\26\2\2hl\5\2\2\2ik\5"+
		"&\24\2ji\3\2\2\2kn\3\2\2\2lj\3\2\2\2lm\3\2\2\2mp\3\2\2\2nl\3\2\2\2of\3"+
		"\2\2\2op\3\2\2\2pr\3\2\2\2qs\t\5\2\2rq\3\2\2\2st\3\2\2\2tr\3\2\2\2tu\3"+
		"\2\2\2uv\3\2\2\2vw\7\b\2\2w\u0090\3\2\2\2xy\7-\2\2yz\5\2\2\2z{\5\b\5\2"+
		"{}\5\2\2\2|~\5\32\16\2}|\3\2\2\2~\177\3\2\2\2\177}\3\2\2\2\177\u0080\3"+
		"\2\2\2\u0080\u0081\3\2\2\2\u0081\u008a\5\2\2\2\u0082\u0083\7\26\2\2\u0083"+
		"\u0087\5\2\2\2\u0084\u0086\5&\24\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008b\3\2\2\2\u0089"+
		"\u0087\3\2\2\2\u008a\u0082\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2"+
		"\2\2\u008c\u008d\t\5\2\2\u008d\u008e\7\b\2\2\u008e\u0090\3\2\2\2\u008f"+
		"J\3\2\2\2\u008fL\3\2\2\2\u008fM\3\2\2\2\u008fN\3\2\2\2\u008fT\3\2\2\2"+
		"\u008fU\3\2\2\2\u008fV\3\2\2\2\u008fZ\3\2\2\2\u008fx\3\2\2\2\u0090\u0114"+
		"\3\2\2\2\u0091\u0095\f\t\2\2\u0092\u0094\7\64\2\2\u0093\u0092\3\2\2\2"+
		"\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098"+
		"\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099\t\6\2\2\u0099\u009a\5\2\2\2\u009a"+
		"\u009b\5\b\5\n\u009b\u0113\3\2\2\2\u009c\u00a0\f\b\2\2\u009d\u009f\7\64"+
		"\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\t\7"+
		"\2\2\u00a4\u00a5\5\2\2\2\u00a5\u00a6\5\b\5\t\u00a6\u0113\3\2\2\2\u00a7"+
		"\u00ab\f\7\2\2\u00a8\u00aa\7\64\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3"+
		"\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ae\u00af\t\b\2\2\u00af\u00b0\5\2\2\2\u00b0\u00b1\5\b"+
		"\5\b\u00b1\u0113\3\2\2\2\u00b2\u00b6\f\6\2\2\u00b3\u00b5\7\64\2\2\u00b4"+
		"\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\t\t\2\2\u00ba"+
		"\u00bb\5\2\2\2\u00bb\u00bc\5\b\5\7\u00bc\u0113\3\2\2\2\u00bd\u00c1\f\5"+
		"\2\2\u00be\u00c0\7\64\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2"+
		"\2\2\u00c4\u00c5\t\n\2\2\u00c5\u00c6\5\2\2\2\u00c6\u00c7\5\b\5\6\u00c7"+
		"\u0113\3\2\2\2\u00c8\u00cd\f\16\2\2\u00c9\u00cb\7\65\2\2\u00ca\u00cc\7"+
		"\64\2\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00c9\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\7\b"+
		"\2\2\u00d0\u00e1\t\13\2\2\u00d1\u00d2\5\2\2\2\u00d2\u00d3\7\61\2\2\u00d3"+
		"\u00d4\5\2\2\2\u00d4\u00dc\5\30\r\2\u00d5\u00d6\5\2\2\2\u00d6\u00d7\7"+
		"\13\2\2\u00d7\u00d8\5\2\2\2\u00d8\u00d9\5\30\r\2\u00d9\u00db\3\2\2\2\u00da"+
		"\u00d5\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\62\2\2\u00e0"+
		"\u00e2\3\2\2\2\u00e1\u00d1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e4\5\2\2\2\u00e4\u00e5\5\n\6\2\u00e5\u0113\3\2\2\2\u00e6"+
		"\u00ea\f\r\2\2\u00e7\u00e9\7\64\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3"+
		"\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00fc\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00ee\7\61\2\2\u00ee\u00ef\5\2\2\2\u00ef\u00f7\5"+
		"\30\r\2\u00f0\u00f1\5\2\2\2\u00f1\u00f2\7\13\2\2\u00f2\u00f3\5\2\2\2\u00f3"+
		"\u00f4\5\30\r\2\u00f4\u00f6\3\2\2\2\u00f5\u00f0\3\2\2\2\u00f6\u00f9\3"+
		"\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00fa\u00fb\7\62\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00ed\3"+
		"\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0101\3\2\2\2\u00fe\u0100\7\64\2\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u0113\5\n\6\2\u0105"+
		"\u010e\f\13\2\2\u0106\u0108\7\65\2\2\u0107\u0109\7\64\2\2\u0108\u0107"+
		"\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\7\b\2\2\u010d\u010f\7="+
		"\2\2\u010e\u010a\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2\2\2\u0110"+
		"\u0111\3\2\2\2\u0111\u0113\3\2\2\2\u0112\u0091\3\2\2\2\u0112\u009c\3\2"+
		"\2\2\u0112\u00a7\3\2\2\2\u0112\u00b2\3\2\2\2\u0112\u00bd\3\2\2\2\u0112"+
		"\u00c8\3\2\2\2\u0112\u00e6\3\2\2\2\u0112\u0105\3\2\2\2\u0113\u0116\3\2"+
		"\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\t\3\2\2\2\u0116\u0114"+
		"\3\2\2\2\u0117\u0118\7\n\2\2\u0118\u0124\5\2\2\2\u0119\u011a\5\b\5\2\u011a"+
		"\u0121\5\2\2\2\u011b\u011c\7\13\2\2\u011c\u011d\5\2\2\2\u011d\u011e\5"+
		"\b\5\2\u011e\u0120\3\2\2\2\u011f\u011b\3\2\2\2\u0120\u0123\3\2\2\2\u0121"+
		"\u011f\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2"+
		"\2\2\u0124\u0119\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"\u0127\5\2\2\2\u0127\u0128\7\t\2\2\u0128\u013c\3\2\2\2\u0129\u012a\7("+
		"\2\2\u012a\u0136\5\2\2\2\u012b\u012c\5\b\5\2\u012c\u0133\5\2\2\2\u012d"+
		"\u012e\7\13\2\2\u012e\u012f\5\2\2\2\u012f\u0130\5\b\5\2\u0130\u0132\3"+
		"\2\2\2\u0131\u012d\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u012b\3\2"+
		"\2\2\u0136\u0137\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\5\2\2\2\u0139"+
		"\u013a\7\b\2\2\u013a\u013c\3\2\2\2\u013b\u0117\3\2\2\2\u013b\u0129\3\2"+
		"\2\2\u013c\13\3\2\2\2\u013d\u013e\5\6\4\2\u013e\u013f\5\2\2\2\u013f\u0140"+
		"\7\37\2\2\u0140\u0141\5\2\2\2\u0141\u0142\5\30\r\2\u0142\r\3\2\2\2\u0143"+
		"\u0144\5\6\4\2\u0144\u0145\5\2\2\2\u0145\u0146\7\b\2\2\u0146\u0147\5\2"+
		"\2\2\u0147\u0149\3\2\2\2\u0148\u0143\3\2\2\2\u0148\u0149\3\2\2\2\u0149"+
		"\u014a\3\2\2\2\u014a\u015a\7>\2\2\u014b\u014c\7\61\2\2\u014c\u014d\5\2"+
		"\2\2\u014d\u0155\5\30\r\2\u014e\u014f\5\2\2\2\u014f\u0150\7\13\2\2\u0150"+
		"\u0151\5\2\2\2\u0151\u0152\5\30\r\2\u0152\u0154\3\2\2\2\u0153\u014e\3"+
		"\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156"+
		"\u0158\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u0159\7\62\2\2\u0159\u015b\3"+
		"\2\2\2\u015a\u014b\3\2\2\2\u015a\u015b\3\2\2\2\u015b\17\3\2\2\2\u015c"+
		"\u015d\7\"\2\2\u015d\u015e\5\2\2\2\u015e\u0166\5\30\r\2\u015f\u0160\5"+
		"\2\2\2\u0160\u0161\7\13\2\2\u0161\u0162\5\2\2\2\u0162\u0163\5\30\r\2\u0163"+
		"\u0165\3\2\2\2\u0164\u015f\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0164\3\2"+
		"\2\2\u0166\u0167\3\2\2\2\u0167\u016a\3\2\2\2\u0168\u0166\3\2\2\2\u0169"+
		"\u015c\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016c\5\2"+
		"\2\2\u016c\u016d\7 \2\2\u016d\u016e\5\2\2\2\u016e\u016f\5\30\r\2\u016f"+
		"\21\3\2\2\2\u0170\u0171\7\n\2\2\u0171\u0172\5\2\2\2\u0172\u0178\5\f\7"+
		"\2\u0173\u0174\5\2\2\2\u0174\u0175\7\13\2\2\u0175\u0176\5\2\2\2\u0176"+
		"\u0177\5\f\7\2\u0177\u0179\3\2\2\2\u0178\u0173\3\2\2\2\u0179\u017a\3\2"+
		"\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017c\3\2\2\2\u017c"+
		"\u017d\7\t\2\2\u017d\23\3\2\2\2\u017e\u0182\5\16\b\2\u017f\u0182\5\20"+
		"\t\2\u0180\u0182\5\22\n\2\u0181\u017e\3\2\2\2\u0181\u017f\3\2\2\2\u0181"+
		"\u0180\3\2\2\2\u0182\25\3\2\2\2\u0183\u0189\5\24\13\2\u0184\u0185\5\2"+
		"\2\2\u0185\u0186\7\60\2\2\u0186\u0187\5\2\2\2\u0187\u0188\5\24\13\2\u0188"+
		"\u018a\3\2\2\2\u0189\u0184\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189\3\2"+
		"\2\2\u018b\u018c\3\2\2\2\u018c\27\3\2\2\2\u018d\u0192\5\16\b\2\u018e\u0192"+
		"\5\22\n\2\u018f\u0192\5\20\t\2\u0190\u0192\5\26\f\2\u0191\u018d\3\2\2"+
		"\2\u0191\u018e\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0190\3\2\2\2\u0192\31"+
		"\3\2\2\2\u0193\u0194\7,\2\2\u0194\u0195\5\2\2\2\u0195\u0196\7=\2\2\u0196"+
		"\u0197\5\2\2\2\u0197\u0198\7\37\2\2\u0198\u0199\5\2\2\2\u0199\u019a\5"+
		"\30\r\2\u019a\u019b\5\2\2\2\u019b\u019c\7\25\2\2\u019c\u01a0\5\2\2\2\u019d"+
		"\u019f\5&\24\2\u019e\u019d\3\2\2\2\u019f\u01a2\3\2\2\2\u01a0\u019e\3\2"+
		"\2\2\u01a0\u01a1\3\2\2\2\u01a1\33\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a3\u01ae"+
		"\5\6\4\2\u01a4\u01a6\7\65\2\2\u01a5\u01a7\7\64\2\2\u01a6\u01a5\3\2\2\2"+
		"\u01a6\u01a7\3\2\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a4\3\2\2\2\u01a8\u01a9"+
		"\3\2\2\2\u01a9\u01aa\3\2\2\2\u01aa\u01ab\7\b\2\2\u01ab\u01ad\7=\2\2\u01ac"+
		"\u01a8\3\2\2\2\u01ad\u01b0\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2"+
		"\2\2\u01af\u01b1\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b1\u01b7\5\2\2\2\u01b2"+
		"\u01b8\5\n\6\2\u01b3\u01b4\7\37\2\2\u01b4\u01b5\5\2\2\2\u01b5\u01b6\5"+
		"\30\r\2\u01b6\u01b8\3\2\2\2\u01b7\u01b2\3\2\2\2\u01b7\u01b3\3\2\2\2\u01b7"+
		"\u01b8\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\5\2\2\2\u01ba\u01bb\7\20"+
		"\2\2\u01bb\u01bc\5\2\2\2\u01bc\u01bd\5\b\5\2\u01bd\35\3\2\2\2\u01be\u01bf"+
		"\7+\2\2\u01bf\u01c1\5\2\2\2\u01c0\u01c2\5\b\5\2\u01c1\u01c0\3\2\2\2\u01c1"+
		"\u01c2\3\2\2\2\u01c2\37\3\2\2\2\u01c3\u01c4\7\34\2\2\u01c4\u01c5\5\2\2"+
		"\2\u01c5\u01c6\5\b\5\2\u01c6\u01c7\5\2\2\2\u01c7\u01c8\7\25\2\2\u01c8"+
		"\u01cc\5\2\2\2\u01c9\u01cb\5&\24\2\u01ca\u01c9\3\2\2\2\u01cb\u01ce\3\2"+
		"\2\2\u01cc\u01ca\3\2\2\2\u01cc\u01cd\3\2\2\2\u01cd\u01cf\3\2\2\2\u01ce"+
		"\u01cc\3\2\2\2\u01cf\u01d0\7\b\2\2\u01d0!\3\2\2\2\u01d1\u01d2\5\6\4\2"+
		"\u01d2\u01dd\5\2\2\2\u01d3\u01d4\7\37\2\2\u01d4\u01d5\5\2\2\2\u01d5\u01db"+
		"\5\30\r\2\u01d6\u01d7\5\2\2\2\u01d7\u01d8\7\20\2\2\u01d8\u01d9\5\2\2\2"+
		"\u01d9\u01da\5\b\5\2\u01da\u01dc\3\2\2\2\u01db\u01d6\3\2\2\2\u01db\u01dc"+
		"\3\2\2\2\u01dc\u01de\3\2\2\2\u01dd\u01d3\3\2\2\2\u01dd\u01de\3\2\2\2\u01de"+
		"#\3\2\2\2\u01df\u01ef\7&\2\2\u01e0\u01e1\5\2\2\2\u01e1\u01e2\5\"\22\2"+
		"\u01e2\u01e9\5\2\2\2\u01e3\u01e4\7\13\2\2\u01e4\u01e5\5\2\2\2\u01e5\u01e6"+
		"\5\"\22\2\u01e6\u01e8\3\2\2\2\u01e7\u01e3\3\2\2\2\u01e8\u01eb\3\2\2\2"+
		"\u01e9\u01e7\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ec\3\2\2\2\u01eb\u01e9"+
		"\3\2\2\2\u01ec\u01ed\5\2\2\2\u01ed\u01ee\7 \2\2\u01ee\u01f0\3\2\2\2\u01ef"+
		"\u01e0\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f5\5\2"+
		"\2\2\u01f2\u01f4\5&\24\2\u01f3\u01f2\3\2\2\2\u01f4\u01f7\3\2\2\2\u01f5"+
		"\u01f3\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01f8\3\2\2\2\u01f7\u01f5\3\2"+
		"\2\2\u01f8\u01fa\5\2\2\2\u01f9\u01fb\7\b\2\2\u01fa\u01f9\3\2\2\2\u01fa"+
		"\u01fb\3\2\2\2\u01fb%\3\2\2\2\u01fc\u0201\5\34\17\2\u01fd\u0201\5 \21"+
		"\2\u01fe\u0201\5\b\5\2\u01ff\u0201\5\36\20\2\u0200\u01fc\3\2\2\2\u0200"+
		"\u01fd\3\2\2\2\u0200\u01fe\3\2\2\2\u0200\u01ff\3\2\2\2\u0201\u0202\3\2"+
		"\2\2\u0202\u0204\5\2\2\2\u0203\u0205\7\23\2\2\u0204\u0203\3\2\2\2\u0204"+
		"\u0205\3\2\2\2\u0205\u0206\3\2\2\2\u0206\u0207\5\2\2\2\u0207\'\3\2\2\2"+
		"\u0208\u0209\7!\2\2\u0209\u020a\5\2\2\2\u020a\u0217\7>\2\2\u020b\u020c"+
		"\5\2\2\2\u020c\u020d\7\61\2\2\u020d\u0212\7>\2\2\u020e\u020f\7\13\2\2"+
		"\u020f\u0211\7>\2\2\u0210\u020e\3\2\2\2\u0211\u0214\3\2\2\2\u0212\u0210"+
		"\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0215\3\2\2\2\u0214\u0212\3\2\2\2\u0215"+
		"\u0216\7\62\2\2\u0216\u0218\3\2\2\2\u0217\u020b\3\2\2\2\u0217\u0218\3"+
		"\2\2\2\u0218\u0219\3\2\2\2\u0219\u021a\5\2\2\2\u021a\u021b\7\20\2\2\u021b"+
		"\u021d\5\2\2\2\u021c\u021e\7.\2\2\u021d\u021c\3\2\2\2\u021d\u021e\3\2"+
		"\2\2\u021e\u021f\3\2\2\2\u021f\u0220\5\2\2\2\u0220\u0221\5> \2\u0221)"+
		"\3\2\2\2\u0222\u0224\7#\2\2\u0223\u0222\3\2\2\2\u0223\u0224\3\2\2\2\u0224"+
		"\u0225\3\2\2\2\u0225\u0226\5\2\2\2\u0226\u0227\7=\2\2\u0227\u0228\5\2"+
		"\2\2\u0228\u0229\7\37\2\2\u0229\u022a\5\2\2\2\u022a\u0230\5\30\r\2\u022b"+
		"\u022c\5\2\2\2\u022c\u022d\7\20\2\2\u022d\u022e\5\2\2\2\u022e\u022f\5"+
		"\b\5\2\u022f\u0231\3\2\2\2\u0230\u022b\3\2\2\2\u0230\u0231\3\2\2\2\u0231"+
		"+\3\2\2\2\u0232\u0233\7!\2\2\u0233\u0234\5\2\2\2\u0234\u0241\7>\2\2\u0235"+
		"\u0236\5\2\2\2\u0236\u0237\7\61\2\2\u0237\u023c\7>\2\2\u0238\u0239\7\13"+
		"\2\2\u0239\u023b\7>\2\2\u023a\u0238\3\2\2\2\u023b\u023e\3\2\2\2\u023c"+
		"\u023a\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u023f\3\2\2\2\u023e\u023c\3\2"+
		"\2\2\u023f\u0240\7\62\2\2\u0240\u0242\3\2\2\2\u0241\u0235\3\2\2\2\u0241"+
		"\u0242\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0244\5\2\2\2\u0244\u0245\7\20"+
		"\2\2\u0245\u0246\5\2\2\2\u0246\u024a\7\n\2\2\u0247\u0249\7\65\2\2\u0248"+
		"\u0247\3\2\2\2\u0249\u024c\3\2\2\2\u024a\u0248\3\2\2\2\u024a\u024b\3\2"+
		"\2\2\u024b\u024d\3\2\2\2\u024c\u024a\3\2\2\2\u024d\u0258\5*\26\2\u024e"+
		"\u0252\7\13\2\2\u024f\u0251\7\65\2\2\u0250\u024f\3\2\2\2\u0251\u0254\3"+
		"\2\2\2\u0252\u0250\3\2\2\2\u0252\u0253\3\2\2\2\u0253\u0255\3\2\2\2\u0254"+
		"\u0252\3\2\2\2\u0255\u0257\5*\26\2\u0256\u024e\3\2\2\2\u0257\u025a\3\2"+
		"\2\2\u0258\u0256\3\2\2\2\u0258\u0259\3\2\2\2\u0259\u025e\3\2\2\2\u025a"+
		"\u0258\3\2\2\2\u025b\u025d\7\65\2\2\u025c\u025b\3\2\2\2\u025d\u0260\3"+
		"\2\2\2\u025e\u025c\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0261\3\2\2\2\u0260"+
		"\u025e\3\2\2\2\u0261\u0262\7\t\2\2\u0262-\3\2\2\2\u0263\u0264\7!\2\2\u0264"+
		"\u0265\5\2\2\2\u0265\u0266\7>\2\2\u0266\u0271\5\2\2\2\u0267\u0268\7\61"+
		"\2\2\u0268\u026d\7>\2\2\u0269\u026a\7\13\2\2\u026a\u026c\7>\2\2\u026b"+
		"\u0269\3\2\2\2\u026c\u026f\3\2\2\2\u026d\u026b\3\2\2\2\u026d\u026e\3\2"+
		"\2\2\u026e\u0270\3\2\2\2\u026f\u026d\3\2\2\2\u0270\u0272\7\62\2\2\u0271"+
		"\u0267\3\2\2\2\u0271\u0272\3\2\2\2\u0272\u0273\3\2\2\2\u0273\u0274\5\2"+
		"\2\2\u0274\u0275\7\20\2\2\u0275\u0276\5\2\2\2\u0276\u027c\5\16\b\2\u0277"+
		"\u0278\5\2\2\2\u0278\u0279\7\60\2\2\u0279\u027a\5\2\2\2\u027a\u027b\5"+
		"\16\b\2\u027b\u027d\3\2\2\2\u027c\u0277\3\2\2\2\u027d\u027e\3\2\2\2\u027e"+
		"\u027c\3\2\2\2\u027e\u027f\3\2\2\2\u027f/\3\2\2\2\u0280\u0284\5(\25\2"+
		"\u0281\u0284\5,\27\2\u0282\u0284\5.\30\2\u0283\u0280\3\2\2\2\u0283\u0281"+
		"\3\2\2\2\u0283\u0282\3\2\2\2\u0284\61\3\2\2\2\u0285\u0286\7%\2\2\u0286"+
		"\u0287\5\2\2\2\u0287\u0288\t\f\2\2\u0288\u0293\5\2\2\2\u0289\u028a\7\61"+
		"\2\2\u028a\u028f\7>\2\2\u028b\u028c\7\13\2\2\u028c\u028e\7>\2\2\u028d"+
		"\u028b\3\2\2\2\u028e\u0291\3\2\2\2\u028f\u028d\3\2\2\2\u028f\u0290\3\2"+
		"\2\2\u0290\u0292\3\2\2\2\u0291\u028f\3\2\2\2\u0292\u0294\7\62\2\2\u0293"+
		"\u0289\3\2\2\2\u0293\u0294\3\2\2\2\u0294\u0295\3\2\2\2\u0295\u0296\5\2"+
		"\2\2\u0296\u0297\7\20\2\2\u0297\u02a6\5\2\2\2\u0298\u0299\5\"\22\2\u0299"+
		"\u02a0\5\2\2\2\u029a\u029b\7\13\2\2\u029b\u029c\5\2\2\2\u029c\u029d\5"+
		"\"\22\2\u029d\u029f\3\2\2\2\u029e\u029a\3\2\2\2\u029f\u02a2\3\2\2\2\u02a0"+
		"\u029e\3\2\2\2\u02a0\u02a1\3\2\2\2\u02a1\u02a3\3\2\2\2\u02a2\u02a0\3\2"+
		"\2\2\u02a3\u02a4\5\2\2\2\u02a4\u02a5\7 \2\2\u02a5\u02a7\3\2\2\2\u02a6"+
		"\u0298\3\2\2\2\u02a6\u02a7\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8\u02b3\5\2"+
		"\2\2\u02a9\u02ab\5&\24\2\u02aa\u02a9\3\2\2\2\u02ab\u02ae\3\2\2\2\u02ac"+
		"\u02aa\3\2\2\2\u02ac\u02ad\3\2\2\2\u02ad\u02af\3\2\2\2\u02ae\u02ac\3\2"+
		"\2\2\u02af\u02b0\5\2\2\2\u02b0\u02b1\7\b\2\2\u02b1\u02b4\3\2\2\2\u02b2"+
		"\u02b4\5> \2\u02b3\u02ac\3\2\2\2\u02b3\u02b2\3\2\2\2\u02b4\u02b6\3\2\2"+
		"\2\u02b5\u02b7\5\30\r\2\u02b6\u02b5\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7"+
		"\63\3\2\2\2\u02b8\u02ba\5\2\2\2\u02b9\u02bb\7\6\2\2\u02ba\u02b9\3\2\2"+
		"\2\u02ba\u02bb\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc\u02c1\7=\2\2\u02bd\u02be"+
		"\7\6\2\2\u02be\u02c0\7=\2\2\u02bf\u02bd\3\2\2\2\u02c0\u02c3\3\2\2\2\u02c1"+
		"\u02bf\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2\u02d2\3\2\2\2\u02c3\u02c1\3\2"+
		"\2\2\u02c4\u02c5\5\2\2\2\u02c5\u02c6\7(\2\2\u02c6\u02c7\5\2\2\2\u02c7"+
		"\u02c8\7>\2\2\u02c8\u02cf\5\2\2\2\u02c9\u02ca\7\13\2\2\u02ca\u02cb\5\2"+
		"\2\2\u02cb\u02cc\7>\2\2\u02cc\u02ce\3\2\2\2\u02cd\u02c9\3\2\2\2\u02ce"+
		"\u02d1\3\2\2\2\u02cf\u02cd\3\2\2\2\u02cf\u02d0\3\2\2\2\u02d0\u02d3\3\2"+
		"\2\2\u02d1\u02cf\3\2\2\2\u02d2\u02c4\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3"+
		"\65\3\2\2\2\u02d4\u02d5\7\'\2\2\u02d5\u02da\5\64\33\2\u02d6\u02d7\7\65"+
		"\2\2\u02d7\u02d9\5\64\33\2\u02d8\u02d6\3\2\2\2\u02d9\u02dc\3\2\2\2\u02da"+
		"\u02d8\3\2\2\2\u02da\u02db\3\2\2\2\u02db\u02dd\3\2\2\2\u02dc\u02da\3\2"+
		"\2\2\u02dd\u02de\7\64\2\2\u02de\u02df\7\b\2\2\u02df\67\3\2\2\2\u02e0\u02e3"+
		"\5\60\31\2\u02e1\u02e3\5\62\32\2\u02e2\u02e0\3\2\2\2\u02e2\u02e1\3\2\2"+
		"\2\u02e39\3\2\2\2\u02e4\u02e5\5\2\2\2\u02e5\u02e6\5\66\34\2\u02e6\u02e8"+
		"\3\2\2\2\u02e7\u02e4\3\2\2\2\u02e8\u02eb\3\2\2\2\u02e9\u02e7\3\2\2\2\u02e9"+
		"\u02ea\3\2\2\2\u02ea\u02ec\3\2\2\2\u02eb\u02e9\3\2\2\2\u02ec\u02f2\5\2"+
		"\2\2\u02ed\u02ee\5\2\2\2\u02ee\u02ef\5> \2\u02ef\u02f1\3\2\2\2\u02f0\u02ed"+
		"\3\2\2\2\u02f1\u02f4\3\2\2\2\u02f2\u02f0\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3"+
		"\u02f5\3\2\2\2\u02f4\u02f2\3\2\2\2\u02f5\u02fb\5\2\2\2\u02f6\u02f7\58"+
		"\35\2\u02f7\u02f8\5\2\2\2\u02f8\u02fa\3\2\2\2\u02f9\u02f6\3\2\2\2\u02fa"+
		"\u02fd\3\2\2\2\u02fb\u02f9\3\2\2\2\u02fb\u02fc\3\2\2\2\u02fc\u02fe\3\2"+
		"\2\2\u02fd\u02fb\3\2\2\2\u02fe\u02ff\7\2\2\3\u02ff;\3\2\2\2\u0300\u0302"+
		"\t\r\2\2\u0301\u0300\3\2\2\2\u0302\u0305\3\2\2\2\u0303\u0301\3\2\2\2\u0303"+
		"\u0304\3\2\2\2\u0304=\3\2\2\2\u0305\u0303\3\2\2\2\u0306\u0307\7\63\2\2"+
		"\u0307\u0308\5<\37\2\u0308\u0309\7C\2\2\u0309?\3\2\2\2ZCclot\177\u0087"+
		"\u008a\u008f\u0095\u00a0\u00ab\u00b6\u00c1\u00cb\u00cd\u00dc\u00e1\u00ea"+
		"\u00f7\u00fc\u0101\u0108\u010a\u0110\u0112\u0114\u0121\u0124\u0133\u0136"+
		"\u013b\u0148\u0155\u015a\u0166\u0169\u017a\u0181\u018b\u0191\u01a0\u01a6"+
		"\u01a8\u01ae\u01b7\u01c1\u01cc\u01db\u01dd\u01e9\u01ef\u01f5\u01fa\u0200"+
		"\u0204\u0212\u0217\u021d\u0223\u0230\u023c\u0241\u024a\u0252\u0258\u025e"+
		"\u026d\u0271\u027e\u0283\u028f\u0293\u02a0\u02a6\u02ac\u02b3\u02b6\u02ba"+
		"\u02c1\u02cf\u02d2\u02da\u02e2\u02e9\u02f2\u02fb\u0303";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}