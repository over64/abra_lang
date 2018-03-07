// Generated from /home/over/build/abra_lang/abra_v2/grammar/M2Parser.g4 by ANTLR 4.7
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
		HexLiteral=54, FloatLiteral=55, BooleanLiteral=56, StringLiteral=57, VarId=58, 
		TypeId=59, MatchId=60, LLVM_NL=61, LLVM_WS=62, IrLine=63, LL_End=64, LL_Dot=65;
	public static final int
		RULE_sp = 0, RULE_literal = 1, RULE_id = 2, RULE_expression = 3, RULE_tuple = 4, 
		RULE_fieldTh = 5, RULE_scalarTh = 6, RULE_fnTh = 7, RULE_structTh = 8, 
		RULE_nonUnionTh = 9, RULE_unionTh = 10, RULE_typeHint = 11, RULE_is = 12, 
		RULE_store = 13, RULE_ret = 14, RULE_while_stat = 15, RULE_fnArg = 16, 
		RULE_lambda = 17, RULE_blockBody = 18, RULE_scalarType = 19, RULE_typeField = 20, 
		RULE_structType = 21, RULE_unionType = 22, RULE_type = 23, RULE_def = 24, 
		RULE_import_ = 25, RULE_level1 = 26, RULE_module = 27, RULE_llvmBody = 28, 
		RULE_llvm = 29;
	public static final String[] ruleNames = {
		"sp", "literal", "id", "expression", "tuple", "fieldTh", "scalarTh", "fnTh", 
		"structTh", "nonUnionTh", "unionTh", "typeHint", "is", "store", "ret", 
		"while_stat", "fnArg", "lambda", "blockBody", "scalarType", "typeField", 
		"structType", "unionType", "type", "def", "import_", "level1", "module", 
		"llvmBody", "llvm"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", null, "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'do'", "'else'", 
		"'{'", "'$('", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", "':'", 
		"'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'lambda'", "'import'", 
		"'with'", "'match'", "'of'", "'return'", "'is'", "'when'", "'ref'", "'_'", 
		"'|'", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "DO", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
		"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
		"MATCH_SELF", "DEF", "LAMBDA", "IMPORT", "WITH", "MATCH", "OF", "RETURN", 
		"IS", "WHEN", "REF", "DASH", "VERT_LINE", "BRACKET_LEFT", "BRACKET_RIGTH", 
		"LlBegin", "WS", "NL", "COMMENT", "IntLiteral", "HexLiteral", "FloatLiteral", 
		"BooleanLiteral", "StringLiteral", "VarId", "TypeId", "MatchId", "LLVM_NL", 
		"LLVM_WS", "IrLine", "LL_End", "LL_Dot"
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
			setState(63);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(60);
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
				setState(65);
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
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral))) != 0)) ) {
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
			setState(68);
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
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(71);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(72);
				id();
				}
				break;
			case 3:
				{
				_localctx = new ExprTypeIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(TypeId);
				}
				break;
			case 4:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				match(LB);
				setState(75);
				sp();
				setState(76);
				expression(0);
				setState(77);
				sp();
				setState(78);
				match(RB);
				}
				break;
			case 5:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				tuple();
				}
				break;
			case 6:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(81);
				lambda();
				}
				break;
			case 7:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(83);
				sp();
				setState(84);
				expression(8);
				}
				break;
			case 8:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				match(IF);
				setState(87);
				sp();
				setState(88);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(89);
				sp();
				{
				setState(90);
				match(DO);
				setState(91);
				sp();
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(92);
					((ExprIfElseContext)_localctx).blockBody = blockBody();
					((ExprIfElseContext)_localctx).doStat.add(((ExprIfElseContext)_localctx).blockBody);
					}
					}
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				setState(98);
				sp();
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(99);
					match(ELSE);
					setState(100);
					sp();
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(101);
						((ExprIfElseContext)_localctx).blockBody = blockBody();
						((ExprIfElseContext)_localctx).elseStat.add(((ExprIfElseContext)_localctx).blockBody);
						}
						}
						setState(106);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(109);
				match(DOT);
				}
				break;
			case 9:
				{
				_localctx = new ExprWnenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(111);
				match(WHEN);
				setState(112);
				sp();
				setState(113);
				((ExprWnenContext)_localctx).expr = expression(0);
				setState(114);
				sp();
				setState(116); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(115);
					is();
					}
					}
					setState(118); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IS );
				setState(120);
				sp();
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(121);
					match(ELSE);
					setState(122);
					sp();
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(123);
						((ExprWnenContext)_localctx).blockBody = blockBody();
						((ExprWnenContext)_localctx).elseStat.add(((ExprWnenContext)_localctx).blockBody);
						}
						}
						setState(128);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(131);
				match(DOT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(264);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(135);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(139);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(136);
							match(WS);
							}
							}
							setState(141);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(142);
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
						setState(143);
						sp();
						setState(144);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(146);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(150);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(147);
							match(WS);
							}
							}
							setState(152);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(153);
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
						setState(154);
						sp();
						setState(155);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(157);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(161);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(158);
							match(WS);
							}
							}
							setState(163);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(164);
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
						setState(165);
						sp();
						setState(166);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(168);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(172);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(169);
							match(WS);
							}
							}
							setState(174);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(175);
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
						setState(176);
						sp();
						setState(177);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(179);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(183);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(180);
							match(WS);
							}
							}
							setState(185);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(186);
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
						setState(187);
						sp();
						setState(188);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(190);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(195);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(191);
							match(NL);
							setState(193);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(192);
								match(WS);
								}
							}

							}
						}

						setState(197);
						match(DOT);
						setState(198);
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
						setState(215);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
						case 1:
							{
							setState(199);
							sp();
							setState(200);
							match(BRACKET_LEFT);
							setState(201);
							sp();
							setState(202);
							typeHint();
							setState(210);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(203);
								sp();
								setState(204);
								match(COMMA);
								setState(205);
								sp();
								setState(206);
								typeHint();
								}
								}
								setState(212);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(213);
							match(BRACKET_RIGTH);
							}
							break;
						}
						setState(217);
						sp();
						setState(218);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(220);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(224);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(221);
								match(WS);
								}
								} 
							}
							setState(226);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
						}
						setState(242);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==BRACKET_LEFT) {
							{
							setState(227);
							match(BRACKET_LEFT);
							setState(228);
							sp();
							setState(229);
							typeHint();
							setState(237);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(230);
								sp();
								setState(231);
								match(COMMA);
								setState(232);
								sp();
								setState(233);
								typeHint();
								}
								}
								setState(239);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(240);
							match(BRACKET_RIGTH);
							}
						}

						setState(247);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(244);
							match(WS);
							}
							}
							setState(249);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(250);
						tuple();
						}
						break;
					case 8:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(251);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(260); 
						_errHandler.sync(this);
						_alt = 1;
						do {
							switch (_alt) {
							case 1:
								{
								{
								setState(256);
								_errHandler.sync(this);
								_la = _input.LA(1);
								if (_la==NL) {
									{
									setState(252);
									match(NL);
									setState(254);
									_errHandler.sync(this);
									_la = _input.LA(1);
									if (_la==WS) {
										{
										setState(253);
										match(WS);
										}
									}

									}
								}

								setState(258);
								match(DOT);
								setState(259);
								((ExprPropContext)_localctx).VarId = match(VarId);
								((ExprPropContext)_localctx).op.add(((ExprPropContext)_localctx).VarId);
								}
								}
								break;
							default:
								throw new NoViableAltException(this);
							}
							setState(262); 
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
						} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
						}
						break;
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				match(LB);
				setState(270);
				sp();
				setState(282);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(271);
					expression(0);
					setState(272);
					sp();
					setState(279);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(273);
						match(COMMA);
						setState(274);
						sp();
						setState(275);
						expression(0);
						}
						}
						setState(281);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(284);
				sp();
				setState(285);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(287);
				match(WITH);
				setState(288);
				sp();
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(289);
					expression(0);
					setState(290);
					sp();
					setState(297);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(291);
						match(COMMA);
						setState(292);
						sp();
						setState(293);
						expression(0);
						}
						}
						setState(299);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(302);
				sp();
				setState(303);
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
			setState(307);
			id();
			setState(308);
			sp();
			setState(309);
			match(CON);
			setState(310);
			sp();
			setState(311);
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
			setState(318);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(313);
				id();
				setState(314);
				sp();
				setState(315);
				match(DOT);
				setState(316);
				sp();
				}
			}

			setState(320);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(336);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(321);
				match(BRACKET_LEFT);
				setState(322);
				sp();
				setState(323);
				typeHint();
				setState(331);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(324);
					sp();
					setState(325);
					match(COMMA);
					setState(326);
					sp();
					setState(327);
					typeHint();
					}
					}
					setState(333);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(334);
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
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BACK_SLASH) {
				{
				setState(338);
				match(BACK_SLASH);
				setState(339);
				sp();
				setState(340);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(348);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(341);
						sp();
						setState(342);
						match(COMMA);
						setState(343);
						sp();
						setState(344);
						((FnThContext)_localctx).typeHint = typeHint();
						((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
						}
						} 
					}
					setState(350);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
				}
				}
			}

			setState(353);
			sp();
			setState(354);
			match(ARROW_RIGHT);
			setState(355);
			sp();
			setState(356);
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
			setState(358);
			match(LB);
			setState(359);
			sp();
			setState(360);
			fieldTh();
			setState(366); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(361);
				sp();
				setState(362);
				match(COMMA);
				setState(363);
				sp();
				setState(364);
				fieldTh();
				}
				}
				setState(368); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(370);
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
			setState(375);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SELF:
			case VarId:
			case TypeId:
				enterOuterAlt(_localctx, 1);
				{
				setState(372);
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
				setState(373);
				fnTh();
				}
				break;
			case LB:
				enterOuterAlt(_localctx, 3);
				{
				setState(374);
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
			setState(377);
			nonUnionTh();
			setState(383); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(378);
					sp();
					setState(379);
					match(VERT_LINE);
					setState(380);
					sp();
					setState(381);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(385); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
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
			setState(391);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(387);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(388);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(389);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(390);
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
			setState(393);
			match(IS);
			setState(394);
			sp();
			setState(395);
			match(VarId);
			setState(396);
			sp();
			setState(397);
			match(CON);
			setState(398);
			sp();
			setState(399);
			typeHint();
			setState(400);
			sp();
			setState(401);
			match(DO);
			setState(402);
			sp();
			setState(406);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(403);
				blockBody();
				}
				}
				setState(408);
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
			setState(409);
			id();
			setState(420);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(414);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(410);
						match(NL);
						setState(412);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(411);
							match(WS);
							}
						}

						}
					}

					setState(416);
					match(DOT);
					setState(417);
					match(VarId);
					}
					} 
				}
				setState(422);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			setState(423);
			sp();
			setState(429);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(424);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(425);
				match(CON);
				setState(426);
				sp();
				setState(427);
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
			setState(431);
			sp();
			setState(432);
			match(EQ);
			setState(433);
			sp();
			setState(434);
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
			setState(436);
			match(RETURN);
			setState(437);
			sp();
			setState(439);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(438);
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
			setState(441);
			match(WHILE);
			setState(442);
			sp();
			setState(443);
			((While_statContext)_localctx).cond = expression(0);
			setState(444);
			sp();
			setState(445);
			match(DO);
			setState(446);
			sp();
			setState(450);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(447);
				blockBody();
				}
				}
				setState(452);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(453);
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
			setState(455);
			id();
			setState(456);
			sp();
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(457);
				match(CON);
				setState(458);
				sp();
				setState(459);
				typeHint();
				setState(465);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(460);
					sp();
					setState(461);
					match(EQ);
					setState(462);
					sp();
					setState(463);
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
			setState(469);
			match(LAMBDA);
			setState(485);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(470);
				sp();
				setState(471);
				fnArg();
				setState(472);
				sp();
				setState(479);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(473);
					match(COMMA);
					setState(474);
					sp();
					setState(475);
					fnArg();
					}
					}
					setState(481);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(482);
				sp();
				setState(483);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(487);
			sp();
			setState(491);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(488);
					blockBody();
					}
					} 
				}
				setState(493);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(494);
			sp();
			setState(496);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(495);
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
			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(498);
				store();
				}
				break;
			case 2:
				{
				setState(499);
				while_stat();
				}
				break;
			case 3:
				{
				setState(500);
				expression(0);
				}
				break;
			case 4:
				{
				setState(501);
				ret();
				}
				break;
			}
			setState(504);
			sp();
			setState(506);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(505);
				match(SEMI);
				}
				break;
			}
			setState(508);
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
			setState(510);
			match(TYPE);
			setState(511);
			sp();
			setState(512);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(525);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(513);
				sp();
				setState(514);
				match(BRACKET_LEFT);
				setState(515);
				((ScalarTypeContext)_localctx).TypeId = match(TypeId);
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
				setState(520);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(516);
					match(COMMA);
					setState(517);
					((ScalarTypeContext)_localctx).TypeId = match(TypeId);
					((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
					}
					}
					setState(522);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(523);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(527);
			sp();
			setState(528);
			match(EQ);
			setState(529);
			sp();
			setState(531);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(530);
				match(REF);
				}
			}

			setState(533);
			sp();
			setState(534);
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
			setState(537);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(536);
				match(SELF);
				}
			}

			setState(539);
			sp();
			setState(540);
			match(VarId);
			setState(541);
			sp();
			setState(542);
			match(CON);
			setState(543);
			sp();
			setState(544);
			typeHint();
			setState(550);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(545);
				sp();
				setState(546);
				match(EQ);
				setState(547);
				sp();
				setState(548);
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
			setState(552);
			match(TYPE);
			setState(553);
			sp();
			setState(554);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(567);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(555);
				sp();
				setState(556);
				match(BRACKET_LEFT);
				setState(557);
				((StructTypeContext)_localctx).TypeId = match(TypeId);
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
				setState(562);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(558);
					match(COMMA);
					setState(559);
					((StructTypeContext)_localctx).TypeId = match(TypeId);
					((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
					}
					}
					setState(564);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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
			setState(576);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(573);
					match(NL);
					}
					} 
				}
				setState(578);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			}
			setState(579);
			typeField();
			setState(590);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(580);
				match(COMMA);
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
				}
				}
				setState(592);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(593);
				match(NL);
				}
				}
				setState(598);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(599);
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
			enterOuterAlt(_localctx, 1);
			{
			setState(601);
			match(TYPE);
			setState(602);
			sp();
			setState(603);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(604);
			sp();
			setState(615);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(605);
				match(BRACKET_LEFT);
				setState(606);
				((UnionTypeContext)_localctx).TypeId = match(TypeId);
				((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
				setState(611);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(607);
					match(COMMA);
					setState(608);
					((UnionTypeContext)_localctx).TypeId = match(TypeId);
					((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
					}
					}
					setState(613);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(614);
				match(BRACKET_RIGTH);
				}
			}

			setState(617);
			sp();
			setState(618);
			match(EQ);
			setState(619);
			sp();
			setState(620);
			scalarTh();
			setState(621);
			sp();
			setState(626); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(622);
				match(VERT_LINE);
				setState(623);
				sp();
				setState(624);
				scalarTh();
				}
				}
				setState(628); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VERT_LINE );
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
			setState(633);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(630);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(631);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(632);
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
			setState(635);
			match(DEF);
			setState(636);
			sp();
			setState(637);
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
			setState(638);
			sp();
			setState(649);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(639);
				match(BRACKET_LEFT);
				setState(640);
				match(TypeId);
				setState(645);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(641);
					match(COMMA);
					setState(642);
					match(TypeId);
					}
					}
					setState(647);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(648);
				match(BRACKET_RIGTH);
				}
			}

			setState(651);
			sp();
			setState(652);
			match(EQ);
			setState(653);
			sp();
			setState(668);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(654);
				fnArg();
				setState(655);
				sp();
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(656);
					match(COMMA);
					setState(657);
					sp();
					setState(658);
					fnArg();
					}
					}
					setState(664);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(665);
				sp();
				setState(666);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(670);
			sp();
			setState(681);
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
			case StringLiteral:
			case VarId:
			case TypeId:
				{
				{
				setState(674);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(671);
					blockBody();
					}
					}
					setState(676);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(677);
				sp();
				setState(678);
				match(DOT);
				}
				}
				break;
			case LlBegin:
				{
				setState(680);
				llvm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(684);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				{
				setState(683);
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

	public static class Import_Context extends ParserRuleContext {
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
		enterRule(_localctx, 50, RULE_import_);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
			match(IMPORT);
			setState(687);
			sp();
			setState(688);
			match(VarId);
			setState(696);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(689);
					sp();
					setState(690);
					match(DIV);
					setState(691);
					sp();
					setState(692);
					match(VarId);
					}
					} 
				}
				setState(698);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
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
		enterRule(_localctx, 52, RULE_level1);
		try {
			setState(701);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(699);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(700);
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
		enterRule(_localctx, 54, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(708);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(703);
					sp();
					setState(704);
					import_();
					}
					} 
				}
				setState(710);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			}
			setState(711);
			sp();
			setState(717);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(712);
					sp();
					setState(713);
					llvm();
					}
					} 
				}
				setState(719);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,80,_ctx);
			}
			setState(720);
			sp();
			setState(726);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(721);
				level1();
				setState(722);
				sp();
				}
				}
				setState(728);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(729);
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
		enterRule(_localctx, 56, RULE_llvmBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(734);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & ((1L << (LLVM_NL - 61)) | (1L << (LLVM_WS - 61)) | (1L << (IrLine - 61)) | (1L << (LL_Dot - 61)))) != 0)) {
				{
				{
				setState(731);
				_la = _input.LA(1);
				if ( !(((((_la - 61)) & ~0x3f) == 0 && ((1L << (_la - 61)) & ((1L << (LLVM_NL - 61)) | (1L << (LLVM_WS - 61)) | (1L << (IrLine - 61)) | (1L << (LL_Dot - 61)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(736);
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
		enterRule(_localctx, 58, RULE_llvm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(737);
			match(LlBegin);
			setState(738);
			llvmBody();
			setState(739);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3C\u02e8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\7\2@"+
		"\n\2\f\2\16\2C\13\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5`\n\5\f"+
		"\5\16\5c\13\5\3\5\3\5\3\5\3\5\7\5i\n\5\f\5\16\5l\13\5\5\5n\n\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\6\5w\n\5\r\5\16\5x\3\5\3\5\3\5\3\5\7\5\177\n\5\f"+
		"\5\16\5\u0082\13\5\5\5\u0084\n\5\3\5\3\5\5\5\u0088\n\5\3\5\3\5\7\5\u008c"+
		"\n\5\f\5\16\5\u008f\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0097\n\5\f\5\16"+
		"\5\u009a\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a2\n\5\f\5\16\5\u00a5\13"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00ad\n\5\f\5\16\5\u00b0\13\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5\u00b8\n\5\f\5\16\5\u00bb\13\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5\u00c4\n\5\5\5\u00c6\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\7\5\u00d3\n\5\f\5\16\5\u00d6\13\5\3\5\3\5\5\5\u00da\n\5\3\5\3"+
		"\5\3\5\3\5\3\5\7\5\u00e1\n\5\f\5\16\5\u00e4\13\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\7\5\u00ee\n\5\f\5\16\5\u00f1\13\5\3\5\3\5\5\5\u00f5\n\5\3\5"+
		"\7\5\u00f8\n\5\f\5\16\5\u00fb\13\5\3\5\3\5\3\5\3\5\5\5\u0101\n\5\5\5\u0103"+
		"\n\5\3\5\3\5\6\5\u0107\n\5\r\5\16\5\u0108\7\5\u010b\n\5\f\5\16\5\u010e"+
		"\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0118\n\6\f\6\16\6\u011b\13"+
		"\6\5\6\u011d\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u012a"+
		"\n\6\f\6\16\6\u012d\13\6\5\6\u012f\n\6\3\6\3\6\3\6\5\6\u0134\n\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u0141\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u014c\n\b\f\b\16\b\u014f\13\b\3\b\3\b\5\b\u0153"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u015d\n\t\f\t\16\t\u0160\13\t"+
		"\5\t\u0162\n\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n"+
		"\u0171\n\n\r\n\16\n\u0172\3\n\3\n\3\13\3\13\3\13\5\13\u017a\n\13\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\6\f\u0182\n\f\r\f\16\f\u0183\3\r\3\r\3\r\3\r\5\r\u018a"+
		"\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0197"+
		"\n\16\f\16\16\16\u019a\13\16\3\17\3\17\3\17\5\17\u019f\n\17\5\17\u01a1"+
		"\n\17\3\17\3\17\7\17\u01a5\n\17\f\17\16\17\u01a8\13\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\5\17\u01b0\n\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\5\20\u01ba\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u01c3\n\21\f"+
		"\21\16\21\u01c6\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u01d4\n\22\5\22\u01d6\n\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\7\23\u01e0\n\23\f\23\16\23\u01e3\13\23\3\23\3\23\3\23\5"+
		"\23\u01e8\n\23\3\23\3\23\7\23\u01ec\n\23\f\23\16\23\u01ef\13\23\3\23\3"+
		"\23\5\23\u01f3\n\23\3\24\3\24\3\24\3\24\5\24\u01f9\n\24\3\24\3\24\5\24"+
		"\u01fd\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\7\25\u0209"+
		"\n\25\f\25\16\25\u020c\13\25\3\25\3\25\5\25\u0210\n\25\3\25\3\25\3\25"+
		"\3\25\5\25\u0216\n\25\3\25\3\25\3\25\3\26\5\26\u021c\n\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u0229\n\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0233\n\27\f\27\16\27\u0236\13\27"+
		"\3\27\3\27\5\27\u023a\n\27\3\27\3\27\3\27\3\27\3\27\7\27\u0241\n\27\f"+
		"\27\16\27\u0244\13\27\3\27\3\27\3\27\7\27\u0249\n\27\f\27\16\27\u024c"+
		"\13\27\3\27\7\27\u024f\n\27\f\27\16\27\u0252\13\27\3\27\7\27\u0255\n\27"+
		"\f\27\16\27\u0258\13\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\7\30\u0264\n\30\f\30\16\30\u0267\13\30\3\30\5\30\u026a\n\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\6\30\u0275\n\30\r\30\16\30\u0276"+
		"\3\31\3\31\3\31\5\31\u027c\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\7\32\u0286\n\32\f\32\16\32\u0289\13\32\3\32\5\32\u028c\n\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u0297\n\32\f\32\16\32\u029a\13"+
		"\32\3\32\3\32\3\32\5\32\u029f\n\32\3\32\3\32\7\32\u02a3\n\32\f\32\16\32"+
		"\u02a6\13\32\3\32\3\32\3\32\3\32\5\32\u02ac\n\32\3\32\5\32\u02af\n\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u02b9\n\33\f\33\16\33\u02bc"+
		"\13\33\3\34\3\34\5\34\u02c0\n\34\3\35\3\35\3\35\7\35\u02c5\n\35\f\35\16"+
		"\35\u02c8\13\35\3\35\3\35\3\35\3\35\7\35\u02ce\n\35\f\35\16\35\u02d1\13"+
		"\35\3\35\3\35\3\35\3\35\7\35\u02d7\n\35\f\35\16\35\u02da\13\35\3\35\3"+
		"\35\3\36\7\36\u02df\n\36\f\36\16\36\u02e2\13\36\3\37\3\37\3\37\3\37\3"+
		"\37\2\3\b \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<\2\r\3\2\64\66\3\2\67;\4\2##<<\3\2\5\6\4\2\3\4<<\3\2\f\17\3\2\21\22"+
		"\3\2\32\33\6\2\3\6\f\17\21\22<<\6\2\3\7\f\17\21\22<<\4\2?ACC\2\u0330\2"+
		"A\3\2\2\2\4D\3\2\2\2\6F\3\2\2\2\b\u0087\3\2\2\2\n\u0133\3\2\2\2\f\u0135"+
		"\3\2\2\2\16\u0140\3\2\2\2\20\u0161\3\2\2\2\22\u0168\3\2\2\2\24\u0179\3"+
		"\2\2\2\26\u017b\3\2\2\2\30\u0189\3\2\2\2\32\u018b\3\2\2\2\34\u019b\3\2"+
		"\2\2\36\u01b6\3\2\2\2 \u01bb\3\2\2\2\"\u01c9\3\2\2\2$\u01d7\3\2\2\2&\u01f8"+
		"\3\2\2\2(\u0200\3\2\2\2*\u021b\3\2\2\2,\u022a\3\2\2\2.\u025b\3\2\2\2\60"+
		"\u027b\3\2\2\2\62\u027d\3\2\2\2\64\u02b0\3\2\2\2\66\u02bf\3\2\2\28\u02c6"+
		"\3\2\2\2:\u02e0\3\2\2\2<\u02e3\3\2\2\2>@\t\2\2\2?>\3\2\2\2@C\3\2\2\2A"+
		"?\3\2\2\2AB\3\2\2\2B\3\3\2\2\2CA\3\2\2\2DE\t\3\2\2E\5\3\2\2\2FG\t\4\2"+
		"\2G\7\3\2\2\2HI\b\5\1\2I\u0088\5\4\3\2J\u0088\5\6\4\2K\u0088\7=\2\2LM"+
		"\7\n\2\2MN\5\2\2\2NO\5\b\5\2OP\5\2\2\2PQ\7\t\2\2Q\u0088\3\2\2\2R\u0088"+
		"\5\n\6\2S\u0088\5$\23\2TU\7\7\2\2UV\5\2\2\2VW\5\b\5\nW\u0088\3\2\2\2X"+
		"Y\7\24\2\2YZ\5\2\2\2Z[\5\b\5\2[\\\5\2\2\2\\]\7\25\2\2]a\5\2\2\2^`\5&\24"+
		"\2_^\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2dm\5\2\2"+
		"\2ef\7\26\2\2fj\5\2\2\2gi\5&\24\2hg\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2"+
		"\2\2kn\3\2\2\2lj\3\2\2\2me\3\2\2\2mn\3\2\2\2no\3\2\2\2op\7\b\2\2p\u0088"+
		"\3\2\2\2qr\7-\2\2rs\5\2\2\2st\5\b\5\2tv\5\2\2\2uw\5\32\16\2vu\3\2\2\2"+
		"wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2yz\3\2\2\2z\u0083\5\2\2\2{|\7\26\2\2|\u0080"+
		"\5\2\2\2}\177\5&\24\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080\3\2\2\2\u0083{\3\2\2\2"+
		"\u0083\u0084\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\7\b\2\2\u0086\u0088"+
		"\3\2\2\2\u0087H\3\2\2\2\u0087J\3\2\2\2\u0087K\3\2\2\2\u0087L\3\2\2\2\u0087"+
		"R\3\2\2\2\u0087S\3\2\2\2\u0087T\3\2\2\2\u0087X\3\2\2\2\u0087q\3\2\2\2"+
		"\u0088\u010c\3\2\2\2\u0089\u008d\f\t\2\2\u008a\u008c\7\64\2\2\u008b\u008a"+
		"\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0091\t\5\2\2\u0091\u0092\5\2"+
		"\2\2\u0092\u0093\5\b\5\n\u0093\u010b\3\2\2\2\u0094\u0098\f\b\2\2\u0095"+
		"\u0097\7\64\2\2\u0096\u0095\3\2\2\2\u0097\u009a\3\2\2\2\u0098\u0096\3"+
		"\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b\3\2\2\2\u009a\u0098\3\2\2\2\u009b"+
		"\u009c\t\6\2\2\u009c\u009d\5\2\2\2\u009d\u009e\5\b\5\t\u009e\u010b\3\2"+
		"\2\2\u009f\u00a3\f\7\2\2\u00a0\u00a2\7\64\2\2\u00a1\u00a0\3\2\2\2\u00a2"+
		"\u00a5\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2"+
		"\2\2\u00a5\u00a3\3\2\2\2\u00a6\u00a7\t\7\2\2\u00a7\u00a8\5\2\2\2\u00a8"+
		"\u00a9\5\b\5\b\u00a9\u010b\3\2\2\2\u00aa\u00ae\f\6\2\2\u00ab\u00ad\7\64"+
		"\2\2\u00ac\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae"+
		"\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\t\b"+
		"\2\2\u00b2\u00b3\5\2\2\2\u00b3\u00b4\5\b\5\7\u00b4\u010b\3\2\2\2\u00b5"+
		"\u00b9\f\5\2\2\u00b6\u00b8\7\64\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3"+
		"\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bc\u00bd\t\t\2\2\u00bd\u00be\5\2\2\2\u00be\u00bf\5\b"+
		"\5\6\u00bf\u010b\3\2\2\2\u00c0\u00c5\f\16\2\2\u00c1\u00c3\7\65\2\2\u00c2"+
		"\u00c4\7\64\2\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3"+
		"\2\2\2\u00c5\u00c1\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7"+
		"\u00c8\7\b\2\2\u00c8\u00d9\t\n\2\2\u00c9\u00ca\5\2\2\2\u00ca\u00cb\7\61"+
		"\2\2\u00cb\u00cc\5\2\2\2\u00cc\u00d4\5\30\r\2\u00cd\u00ce\5\2\2\2\u00ce"+
		"\u00cf\7\13\2\2\u00cf\u00d0\5\2\2\2\u00d0\u00d1\5\30\r\2\u00d1\u00d3\3"+
		"\2\2\2\u00d2\u00cd\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d4"+
		"\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7\u00d8\7\62"+
		"\2\2\u00d8\u00da\3\2\2\2\u00d9\u00c9\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\5\2\2\2\u00dc\u00dd\5\n\6\2\u00dd\u010b\3\2"+
		"\2\2\u00de\u00e2\f\r\2\2\u00df\u00e1\7\64\2\2\u00e0\u00df\3\2\2\2\u00e1"+
		"\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00f4\3\2"+
		"\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6\7\61\2\2\u00e6\u00e7\5\2\2\2\u00e7"+
		"\u00ef\5\30\r\2\u00e8\u00e9\5\2\2\2\u00e9\u00ea\7\13\2\2\u00ea\u00eb\5"+
		"\2\2\2\u00eb\u00ec\5\30\r\2\u00ec\u00ee\3\2\2\2\u00ed\u00e8\3\2\2\2\u00ee"+
		"\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f2\3\2"+
		"\2\2\u00f1\u00ef\3\2\2\2\u00f2\u00f3\7\62\2\2\u00f3\u00f5\3\2\2\2\u00f4"+
		"\u00e5\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f9\3\2\2\2\u00f6\u00f8\7\64"+
		"\2\2\u00f7\u00f6\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9"+
		"\u00fa\3\2\2\2\u00fa\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u010b\5\n"+
		"\6\2\u00fd\u0106\f\13\2\2\u00fe\u0100\7\65\2\2\u00ff\u0101\7\64\2\2\u0100"+
		"\u00ff\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u0103\3\2\2\2\u0102\u00fe\3\2"+
		"\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\7\b\2\2\u0105"+
		"\u0107\7<\2\2\u0106\u0102\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0106\3\2"+
		"\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0089\3\2\2\2\u010a"+
		"\u0094\3\2\2\2\u010a\u009f\3\2\2\2\u010a\u00aa\3\2\2\2\u010a\u00b5\3\2"+
		"\2\2\u010a\u00c0\3\2\2\2\u010a\u00de\3\2\2\2\u010a\u00fd\3\2\2\2\u010b"+
		"\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d\t\3\2\2\2"+
		"\u010e\u010c\3\2\2\2\u010f\u0110\7\n\2\2\u0110\u011c\5\2\2\2\u0111\u0112"+
		"\5\b\5\2\u0112\u0119\5\2\2\2\u0113\u0114\7\13\2\2\u0114\u0115\5\2\2\2"+
		"\u0115\u0116\5\b\5\2\u0116\u0118\3\2\2\2\u0117\u0113\3\2\2\2\u0118\u011b"+
		"\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011d\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011c\u0111\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e\u011f\5\2\2\2\u011f\u0120\7\t\2\2\u0120\u0134\3\2\2\2\u0121"+
		"\u0122\7(\2\2\u0122\u012e\5\2\2\2\u0123\u0124\5\b\5\2\u0124\u012b\5\2"+
		"\2\2\u0125\u0126\7\13\2\2\u0126\u0127\5\2\2\2\u0127\u0128\5\b\5\2\u0128"+
		"\u012a\3\2\2\2\u0129\u0125\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2"+
		"\2\2\u012b\u012c\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012e"+
		"\u0123\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0131\5\2"+
		"\2\2\u0131\u0132\7\b\2\2\u0132\u0134\3\2\2\2\u0133\u010f\3\2\2\2\u0133"+
		"\u0121\3\2\2\2\u0134\13\3\2\2\2\u0135\u0136\5\6\4\2\u0136\u0137\5\2\2"+
		"\2\u0137\u0138\7\37\2\2\u0138\u0139\5\2\2\2\u0139\u013a\5\30\r\2\u013a"+
		"\r\3\2\2\2\u013b\u013c\5\6\4\2\u013c\u013d\5\2\2\2\u013d\u013e\7\b\2\2"+
		"\u013e\u013f\5\2\2\2\u013f\u0141\3\2\2\2\u0140\u013b\3\2\2\2\u0140\u0141"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0152\7=\2\2\u0143\u0144\7\61\2\2\u0144"+
		"\u0145\5\2\2\2\u0145\u014d\5\30\r\2\u0146\u0147\5\2\2\2\u0147\u0148\7"+
		"\13\2\2\u0148\u0149\5\2\2\2\u0149\u014a\5\30\r\2\u014a\u014c\3\2\2\2\u014b"+
		"\u0146\3\2\2\2\u014c\u014f\3\2\2\2\u014d\u014b\3\2\2\2\u014d\u014e\3\2"+
		"\2\2\u014e\u0150\3\2\2\2\u014f\u014d\3\2\2\2\u0150\u0151\7\62\2\2\u0151"+
		"\u0153\3\2\2\2\u0152\u0143\3\2\2\2\u0152\u0153\3\2\2\2\u0153\17\3\2\2"+
		"\2\u0154\u0155\7\"\2\2\u0155\u0156\5\2\2\2\u0156\u015e\5\30\r\2\u0157"+
		"\u0158\5\2\2\2\u0158\u0159\7\13\2\2\u0159\u015a\5\2\2\2\u015a\u015b\5"+
		"\30\r\2\u015b\u015d\3\2\2\2\u015c\u0157\3\2\2\2\u015d\u0160\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e\3\2"+
		"\2\2\u0161\u0154\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163"+
		"\u0164\5\2\2\2\u0164\u0165\7 \2\2\u0165\u0166\5\2\2\2\u0166\u0167\5\30"+
		"\r\2\u0167\21\3\2\2\2\u0168\u0169\7\n\2\2\u0169\u016a\5\2\2\2\u016a\u0170"+
		"\5\f\7\2\u016b\u016c\5\2\2\2\u016c\u016d\7\13\2\2\u016d\u016e\5\2\2\2"+
		"\u016e\u016f\5\f\7\2\u016f\u0171\3\2\2\2\u0170\u016b\3\2\2\2\u0171\u0172"+
		"\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0174\3\2\2\2\u0174"+
		"\u0175\7\t\2\2\u0175\23\3\2\2\2\u0176\u017a\5\16\b\2\u0177\u017a\5\20"+
		"\t\2\u0178\u017a\5\22\n\2\u0179\u0176\3\2\2\2\u0179\u0177\3\2\2\2\u0179"+
		"\u0178\3\2\2\2\u017a\25\3\2\2\2\u017b\u0181\5\24\13\2\u017c\u017d\5\2"+
		"\2\2\u017d\u017e\7\60\2\2\u017e\u017f\5\2\2\2\u017f\u0180\5\24\13\2\u0180"+
		"\u0182\3\2\2\2\u0181\u017c\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0183\u0184\3\2\2\2\u0184\27\3\2\2\2\u0185\u018a\5\16\b\2\u0186\u018a"+
		"\5\22\n\2\u0187\u018a\5\20\t\2\u0188\u018a\5\26\f\2\u0189\u0185\3\2\2"+
		"\2\u0189\u0186\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u0188\3\2\2\2\u018a\31"+
		"\3\2\2\2\u018b\u018c\7,\2\2\u018c\u018d\5\2\2\2\u018d\u018e\7<\2\2\u018e"+
		"\u018f\5\2\2\2\u018f\u0190\7\37\2\2\u0190\u0191\5\2\2\2\u0191\u0192\5"+
		"\30\r\2\u0192\u0193\5\2\2\2\u0193\u0194\7\25\2\2\u0194\u0198\5\2\2\2\u0195"+
		"\u0197\5&\24\2\u0196\u0195\3\2\2\2\u0197\u019a\3\2\2\2\u0198\u0196\3\2"+
		"\2\2\u0198\u0199\3\2\2\2\u0199\33\3\2\2\2\u019a\u0198\3\2\2\2\u019b\u01a6"+
		"\5\6\4\2\u019c\u019e\7\65\2\2\u019d\u019f\7\64\2\2\u019e\u019d\3\2\2\2"+
		"\u019e\u019f\3\2\2\2\u019f\u01a1\3\2\2\2\u01a0\u019c\3\2\2\2\u01a0\u01a1"+
		"\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\u01a3\7\b\2\2\u01a3\u01a5\7<\2\2\u01a4"+
		"\u01a0\3\2\2\2\u01a5\u01a8\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2"+
		"\2\2\u01a7\u01a9\3\2\2\2\u01a8\u01a6\3\2\2\2\u01a9\u01af\5\2\2\2\u01aa"+
		"\u01b0\5\n\6\2\u01ab\u01ac\7\37\2\2\u01ac\u01ad\5\2\2\2\u01ad\u01ae\5"+
		"\30\r\2\u01ae\u01b0\3\2\2\2\u01af\u01aa\3\2\2\2\u01af\u01ab\3\2\2\2\u01af"+
		"\u01b0\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b2\5\2\2\2\u01b2\u01b3\7\20"+
		"\2\2\u01b3\u01b4\5\2\2\2\u01b4\u01b5\5\b\5\2\u01b5\35\3\2\2\2\u01b6\u01b7"+
		"\7+\2\2\u01b7\u01b9\5\2\2\2\u01b8\u01ba\5\b\5\2\u01b9\u01b8\3\2\2\2\u01b9"+
		"\u01ba\3\2\2\2\u01ba\37\3\2\2\2\u01bb\u01bc\7\34\2\2\u01bc\u01bd\5\2\2"+
		"\2\u01bd\u01be\5\b\5\2\u01be\u01bf\5\2\2\2\u01bf\u01c0\7\25\2\2\u01c0"+
		"\u01c4\5\2\2\2\u01c1\u01c3\5&\24\2\u01c2\u01c1\3\2\2\2\u01c3\u01c6\3\2"+
		"\2\2\u01c4\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2\2\2\u01c6"+
		"\u01c4\3\2\2\2\u01c7\u01c8\7\b\2\2\u01c8!\3\2\2\2\u01c9\u01ca\5\6\4\2"+
		"\u01ca\u01d5\5\2\2\2\u01cb\u01cc\7\37\2\2\u01cc\u01cd\5\2\2\2\u01cd\u01d3"+
		"\5\30\r\2\u01ce\u01cf\5\2\2\2\u01cf\u01d0\7\20\2\2\u01d0\u01d1\5\2\2\2"+
		"\u01d1\u01d2\5\b\5\2\u01d2\u01d4\3\2\2\2\u01d3\u01ce\3\2\2\2\u01d3\u01d4"+
		"\3\2\2\2\u01d4\u01d6\3\2\2\2\u01d5\u01cb\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6"+
		"#\3\2\2\2\u01d7\u01e7\7&\2\2\u01d8\u01d9\5\2\2\2\u01d9\u01da\5\"\22\2"+
		"\u01da\u01e1\5\2\2\2\u01db\u01dc\7\13\2\2\u01dc\u01dd\5\2\2\2\u01dd\u01de"+
		"\5\"\22\2\u01de\u01e0\3\2\2\2\u01df\u01db\3\2\2\2\u01e0\u01e3\3\2\2\2"+
		"\u01e1\u01df\3\2\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e4\3\2\2\2\u01e3\u01e1"+
		"\3\2\2\2\u01e4\u01e5\5\2\2\2\u01e5\u01e6\7 \2\2\u01e6\u01e8\3\2\2\2\u01e7"+
		"\u01d8\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01ed\5\2"+
		"\2\2\u01ea\u01ec\5&\24\2\u01eb\u01ea\3\2\2\2\u01ec\u01ef\3\2\2\2\u01ed"+
		"\u01eb\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f0\3\2\2\2\u01ef\u01ed\3\2"+
		"\2\2\u01f0\u01f2\5\2\2\2\u01f1\u01f3\7\b\2\2\u01f2\u01f1\3\2\2\2\u01f2"+
		"\u01f3\3\2\2\2\u01f3%\3\2\2\2\u01f4\u01f9\5\34\17\2\u01f5\u01f9\5 \21"+
		"\2\u01f6\u01f9\5\b\5\2\u01f7\u01f9\5\36\20\2\u01f8\u01f4\3\2\2\2\u01f8"+
		"\u01f5\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f7\3\2\2\2\u01f9\u01fa\3\2"+
		"\2\2\u01fa\u01fc\5\2\2\2\u01fb\u01fd\7\23\2\2\u01fc\u01fb\3\2\2\2\u01fc"+
		"\u01fd\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff\5\2\2\2\u01ff\'\3\2\2\2"+
		"\u0200\u0201\7!\2\2\u0201\u0202\5\2\2\2\u0202\u020f\7=\2\2\u0203\u0204"+
		"\5\2\2\2\u0204\u0205\7\61\2\2\u0205\u020a\7=\2\2\u0206\u0207\7\13\2\2"+
		"\u0207\u0209\7=\2\2\u0208\u0206\3\2\2\2\u0209\u020c\3\2\2\2\u020a\u0208"+
		"\3\2\2\2\u020a\u020b\3\2\2\2\u020b\u020d\3\2\2\2\u020c\u020a\3\2\2\2\u020d"+
		"\u020e\7\62\2\2\u020e\u0210\3\2\2\2\u020f\u0203\3\2\2\2\u020f\u0210\3"+
		"\2\2\2\u0210\u0211\3\2\2\2\u0211\u0212\5\2\2\2\u0212\u0213\7\20\2\2\u0213"+
		"\u0215\5\2\2\2\u0214\u0216\7.\2\2\u0215\u0214\3\2\2\2\u0215\u0216\3\2"+
		"\2\2\u0216\u0217\3\2\2\2\u0217\u0218\5\2\2\2\u0218\u0219\5<\37\2\u0219"+
		")\3\2\2\2\u021a\u021c\7#\2\2\u021b\u021a\3\2\2\2\u021b\u021c\3\2\2\2\u021c"+
		"\u021d\3\2\2\2\u021d\u021e\5\2\2\2\u021e\u021f\7<\2\2\u021f\u0220\5\2"+
		"\2\2\u0220\u0221\7\37\2\2\u0221\u0222\5\2\2\2\u0222\u0228\5\30\r\2\u0223"+
		"\u0224\5\2\2\2\u0224\u0225\7\20\2\2\u0225\u0226\5\2\2\2\u0226\u0227\5"+
		"\b\5\2\u0227\u0229\3\2\2\2\u0228\u0223\3\2\2\2\u0228\u0229\3\2\2\2\u0229"+
		"+\3\2\2\2\u022a\u022b\7!\2\2\u022b\u022c\5\2\2\2\u022c\u0239\7=\2\2\u022d"+
		"\u022e\5\2\2\2\u022e\u022f\7\61\2\2\u022f\u0234\7=\2\2\u0230\u0231\7\13"+
		"\2\2\u0231\u0233\7=\2\2\u0232\u0230\3\2\2\2\u0233\u0236\3\2\2\2\u0234"+
		"\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0237\3\2\2\2\u0236\u0234\3\2"+
		"\2\2\u0237\u0238\7\62\2\2\u0238\u023a\3\2\2\2\u0239\u022d\3\2\2\2\u0239"+
		"\u023a\3\2\2\2\u023a\u023b\3\2\2\2\u023b\u023c\5\2\2\2\u023c\u023d\7\20"+
		"\2\2\u023d\u023e\5\2\2\2\u023e\u0242\7\n\2\2\u023f\u0241\7\65\2\2\u0240"+
		"\u023f\3\2\2\2\u0241\u0244\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2"+
		"\2\2\u0243\u0245\3\2\2\2\u0244\u0242\3\2\2\2\u0245\u0250\5*\26\2\u0246"+
		"\u024a\7\13\2\2\u0247\u0249\7\65\2\2\u0248\u0247\3\2\2\2\u0249\u024c\3"+
		"\2\2\2\u024a\u0248\3\2\2\2\u024a\u024b\3\2\2\2\u024b\u024d\3\2\2\2\u024c"+
		"\u024a\3\2\2\2\u024d\u024f\5*\26\2\u024e\u0246\3\2\2\2\u024f\u0252\3\2"+
		"\2\2\u0250\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0256\3\2\2\2\u0252"+
		"\u0250\3\2\2\2\u0253\u0255\7\65\2\2\u0254\u0253\3\2\2\2\u0255\u0258\3"+
		"\2\2\2\u0256\u0254\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0259\3\2\2\2\u0258"+
		"\u0256\3\2\2\2\u0259\u025a\7\t\2\2\u025a-\3\2\2\2\u025b\u025c\7!\2\2\u025c"+
		"\u025d\5\2\2\2\u025d\u025e\7=\2\2\u025e\u0269\5\2\2\2\u025f\u0260\7\61"+
		"\2\2\u0260\u0265\7=\2\2\u0261\u0262\7\13\2\2\u0262\u0264\7=\2\2\u0263"+
		"\u0261\3\2\2\2\u0264\u0267\3\2\2\2\u0265\u0263\3\2\2\2\u0265\u0266\3\2"+
		"\2\2\u0266\u0268\3\2\2\2\u0267\u0265\3\2\2\2\u0268\u026a\7\62\2\2\u0269"+
		"\u025f\3\2\2\2\u0269\u026a\3\2\2\2\u026a\u026b\3\2\2\2\u026b\u026c\5\2"+
		"\2\2\u026c\u026d\7\20\2\2\u026d\u026e\5\2\2\2\u026e\u026f\5\16\b\2\u026f"+
		"\u0274\5\2\2\2\u0270\u0271\7\60\2\2\u0271\u0272\5\2\2\2\u0272\u0273\5"+
		"\16\b\2\u0273\u0275\3\2\2\2\u0274\u0270\3\2\2\2\u0275\u0276\3\2\2\2\u0276"+
		"\u0274\3\2\2\2\u0276\u0277\3\2\2\2\u0277/\3\2\2\2\u0278\u027c\5(\25\2"+
		"\u0279\u027c\5,\27\2\u027a\u027c\5.\30\2\u027b\u0278\3\2\2\2\u027b\u0279"+
		"\3\2\2\2\u027b\u027a\3\2\2\2\u027c\61\3\2\2\2\u027d\u027e\7%\2\2\u027e"+
		"\u027f\5\2\2\2\u027f\u0280\t\13\2\2\u0280\u028b\5\2\2\2\u0281\u0282\7"+
		"\61\2\2\u0282\u0287\7=\2\2\u0283\u0284\7\13\2\2\u0284\u0286\7=\2\2\u0285"+
		"\u0283\3\2\2\2\u0286\u0289\3\2\2\2\u0287\u0285\3\2\2\2\u0287\u0288\3\2"+
		"\2\2\u0288\u028a\3\2\2\2\u0289\u0287\3\2\2\2\u028a\u028c\7\62\2\2\u028b"+
		"\u0281\3\2\2\2\u028b\u028c\3\2\2\2\u028c\u028d\3\2\2\2\u028d\u028e\5\2"+
		"\2\2\u028e\u028f\7\20\2\2\u028f\u029e\5\2\2\2\u0290\u0291\5\"\22\2\u0291"+
		"\u0298\5\2\2\2\u0292\u0293\7\13\2\2\u0293\u0294\5\2\2\2\u0294\u0295\5"+
		"\"\22\2\u0295\u0297\3\2\2\2\u0296\u0292\3\2\2\2\u0297\u029a\3\2\2\2\u0298"+
		"\u0296\3\2\2\2\u0298\u0299\3\2\2\2\u0299\u029b\3\2\2\2\u029a\u0298\3\2"+
		"\2\2\u029b\u029c\5\2\2\2\u029c\u029d\7 \2\2\u029d\u029f\3\2\2\2\u029e"+
		"\u0290\3\2\2\2\u029e\u029f\3\2\2\2\u029f\u02a0\3\2\2\2\u02a0\u02ab\5\2"+
		"\2\2\u02a1\u02a3\5&\24\2\u02a2\u02a1\3\2\2\2\u02a3\u02a6\3\2\2\2\u02a4"+
		"\u02a2\3\2\2\2\u02a4\u02a5\3\2\2\2\u02a5\u02a7\3\2\2\2\u02a6\u02a4\3\2"+
		"\2\2\u02a7\u02a8\5\2\2\2\u02a8\u02a9\7\b\2\2\u02a9\u02ac\3\2\2\2\u02aa"+
		"\u02ac\5<\37\2\u02ab\u02a4\3\2\2\2\u02ab\u02aa\3\2\2\2\u02ac\u02ae\3\2"+
		"\2\2\u02ad\u02af\5\30\r\2\u02ae\u02ad\3\2\2\2\u02ae\u02af\3\2\2\2\u02af"+
		"\63\3\2\2\2\u02b0\u02b1\7\'\2\2\u02b1\u02b2\5\2\2\2\u02b2\u02ba\7<\2\2"+
		"\u02b3\u02b4\5\2\2\2\u02b4\u02b5\7\6\2\2\u02b5\u02b6\5\2\2\2\u02b6\u02b7"+
		"\7<\2\2\u02b7\u02b9\3\2\2\2\u02b8\u02b3\3\2\2\2\u02b9\u02bc\3\2\2\2\u02ba"+
		"\u02b8\3\2\2\2\u02ba\u02bb\3\2\2\2\u02bb\65\3\2\2\2\u02bc\u02ba\3\2\2"+
		"\2\u02bd\u02c0\5\60\31\2\u02be\u02c0\5\62\32\2\u02bf\u02bd\3\2\2\2\u02bf"+
		"\u02be\3\2\2\2\u02c0\67\3\2\2\2\u02c1\u02c2\5\2\2\2\u02c2\u02c3\5\64\33"+
		"\2\u02c3\u02c5\3\2\2\2\u02c4\u02c1\3\2\2\2\u02c5\u02c8\3\2\2\2\u02c6\u02c4"+
		"\3\2\2\2\u02c6\u02c7\3\2\2\2\u02c7\u02c9\3\2\2\2\u02c8\u02c6\3\2\2\2\u02c9"+
		"\u02cf\5\2\2\2\u02ca\u02cb\5\2\2\2\u02cb\u02cc\5<\37\2\u02cc\u02ce\3\2"+
		"\2\2\u02cd\u02ca\3\2\2\2\u02ce\u02d1\3\2\2\2\u02cf\u02cd\3\2\2\2\u02cf"+
		"\u02d0\3\2\2\2\u02d0\u02d2\3\2\2\2\u02d1\u02cf\3\2\2\2\u02d2\u02d8\5\2"+
		"\2\2\u02d3\u02d4\5\66\34\2\u02d4\u02d5\5\2\2\2\u02d5\u02d7\3\2\2\2\u02d6"+
		"\u02d3\3\2\2\2\u02d7\u02da\3\2\2\2\u02d8\u02d6\3\2\2\2\u02d8\u02d9\3\2"+
		"\2\2\u02d9\u02db\3\2\2\2\u02da\u02d8\3\2\2\2\u02db\u02dc\7\2\2\3\u02dc"+
		"9\3\2\2\2\u02dd\u02df\t\f\2\2\u02de\u02dd\3\2\2\2\u02df\u02e2\3\2\2\2"+
		"\u02e0\u02de\3\2\2\2\u02e0\u02e1\3\2\2\2\u02e1;\3\2\2\2\u02e2\u02e0\3"+
		"\2\2\2\u02e3\u02e4\7\63\2\2\u02e4\u02e5\5:\36\2\u02e5\u02e6\7B\2\2\u02e6"+
		"=\3\2\2\2UAajmx\u0080\u0083\u0087\u008d\u0098\u00a3\u00ae\u00b9\u00c3"+
		"\u00c5\u00d4\u00d9\u00e2\u00ef\u00f4\u00f9\u0100\u0102\u0108\u010a\u010c"+
		"\u0119\u011c\u012b\u012e\u0133\u0140\u014d\u0152\u015e\u0161\u0172\u0179"+
		"\u0183\u0189\u0198\u019e\u01a0\u01a6\u01af\u01b9\u01c4\u01d3\u01d5\u01e1"+
		"\u01e7\u01ed\u01f2\u01f8\u01fc\u020a\u020f\u0215\u021b\u0228\u0234\u0239"+
		"\u0242\u024a\u0250\u0256\u0265\u0269\u0276\u027b\u0287\u028b\u0298\u029e"+
		"\u02a4\u02ab\u02ae\u02ba\u02bf\u02c6\u02cf\u02d8\u02e0";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}