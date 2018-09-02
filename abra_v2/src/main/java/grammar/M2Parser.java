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
		HexLiteral=54, FloatLiteral=55, BooleanLiteral=56, NoneLiteral=57, StringLiteral=58, 
		VarId=59, TypeId=60, MatchId=61, LLVM_NL=62, LLVM_WS=63, IrLine=64, LL_End=65, 
		LL_Dot=66;
	public static final int
		RULE_sp = 0, RULE_literal = 1, RULE_id = 2, RULE_expression = 3, RULE_tuple = 4, 
		RULE_fieldTh = 5, RULE_scalarTh = 6, RULE_fnTh = 7, RULE_structTh = 8, 
		RULE_nonUnionTh = 9, RULE_unionTh = 10, RULE_typeHint = 11, RULE_is = 12, 
		RULE_whenElse = 13, RULE_store = 14, RULE_ret = 15, RULE_while_stat = 16, 
		RULE_fnArg = 17, RULE_lambda = 18, RULE_blockBody = 19, RULE_scalarType = 20, 
		RULE_typeField = 21, RULE_structType = 22, RULE_unionType = 23, RULE_type = 24, 
		RULE_def = 25, RULE_importEntry = 26, RULE_import_ = 27, RULE_level1 = 28, 
		RULE_module = 29, RULE_llvmBody = 30, RULE_llvm = 31;
	public static final String[] ruleNames = {
		"sp", "literal", "id", "expression", "tuple", "fieldTh", "scalarTh", "fnTh", 
		"structTh", "nonUnionTh", "unionTh", "typeHint", "is", "whenElse", "store", 
		"ret", "while_stat", "fnArg", "lambda", "blockBody", "scalarType", "typeField", 
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
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(64);
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
				setState(69);
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
			setState(70);
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
			setState(72);
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
		public WhenElseContext whenElse() {
			return getRuleContext(WhenElseContext.class,0);
		}
		public ExprWnenContext(ExpressionContext ctx) { copyFrom(ctx); }
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
		public List<TerminalNode> WS() { return getTokens(M2Parser.WS); }
		public TerminalNode WS(int i) {
			return getToken(M2Parser.WS, i);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
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
			setState(134);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(75);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(76);
				id();
				}
				break;
			case 3:
				{
				_localctx = new ExprTypeIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				match(TypeId);
				}
				break;
			case 4:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				match(LB);
				setState(79);
				sp();
				setState(80);
				expression(0);
				setState(81);
				sp();
				setState(82);
				match(RB);
				}
				break;
			case 5:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				tuple();
				}
				break;
			case 6:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(85);
				lambda();
				}
				break;
			case 7:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(86);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(87);
				sp();
				setState(88);
				expression(8);
				}
				break;
			case 8:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				match(IF);
				setState(91);
				sp();
				setState(92);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(93);
				sp();
				setState(94);
				match(DO);
				setState(95);
				sp();
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(96);
					((ExprIfElseContext)_localctx).blockBody = blockBody();
					((ExprIfElseContext)_localctx).doStat.add(((ExprIfElseContext)_localctx).blockBody);
					}
					}
					setState(101);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(111);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(102);
					sp();
					setState(103);
					match(ELSE);
					setState(104);
					sp();
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(105);
						((ExprIfElseContext)_localctx).blockBody = blockBody();
						((ExprIfElseContext)_localctx).elseStat.add(((ExprIfElseContext)_localctx).blockBody);
						}
						}
						setState(110);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
					break;
				}
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS || _la==NL) {
					{
					setState(113);
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
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(128);
					whenElse();
					}
				}

				setState(131);
				_la = _input.LA(1);
				if ( !(_la==WS || _la==NL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(132);
				match(DOT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(261);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(136);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(140);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(137);
							match(WS);
							}
							}
							setState(142);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(143);
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
						setState(144);
						sp();
						setState(145);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(147);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(151);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(148);
							match(WS);
							}
							}
							setState(153);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(154);
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
						setState(155);
						sp();
						setState(156);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(158);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(162);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(159);
							match(WS);
							}
							}
							setState(164);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(165);
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
						setState(166);
						sp();
						setState(167);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(169);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(173);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(170);
							match(WS);
							}
							}
							setState(175);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(176);
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
						setState(177);
						sp();
						setState(178);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(180);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(184);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(181);
							match(WS);
							}
							}
							setState(186);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(187);
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
						setState(188);
						sp();
						setState(189);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(196);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(192);
							match(NL);
							setState(194);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(193);
								match(WS);
								}
							}

							}
						}

						setState(198);
						match(DOT);
						setState(199);
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
						setState(216);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
						case 1:
							{
							setState(200);
							sp();
							setState(201);
							match(BRACKET_LEFT);
							setState(202);
							sp();
							setState(203);
							typeHint();
							setState(211);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(204);
								sp();
								setState(205);
								match(COMMA);
								setState(206);
								sp();
								setState(207);
								typeHint();
								}
								}
								setState(213);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(214);
							match(BRACKET_RIGTH);
							}
							break;
						}
						setState(218);
						sp();
						setState(219);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(221);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(225);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(222);
								match(WS);
								}
								} 
							}
							setState(227);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
						}
						setState(243);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==BRACKET_LEFT) {
							{
							setState(228);
							match(BRACKET_LEFT);
							setState(229);
							sp();
							setState(230);
							typeHint();
							setState(238);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(231);
								sp();
								setState(232);
								match(COMMA);
								setState(233);
								sp();
								setState(234);
								typeHint();
								}
								}
								setState(240);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(241);
							match(BRACKET_RIGTH);
							}
						}

						setState(248);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(245);
							match(WS);
							}
							}
							setState(250);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(251);
						tuple();
						}
						break;
					case 8:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(252);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(257);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(253);
							match(NL);
							setState(255);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(254);
								match(WS);
								}
							}

							}
						}

						setState(259);
						match(DOT);
						setState(260);
						((ExprPropContext)_localctx).VarId = match(VarId);
						((ExprPropContext)_localctx).op.add(((ExprPropContext)_localctx).VarId);
						}
						break;
					}
					} 
				}
				setState(265);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
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
			setState(303);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(LB);
				setState(267);
				sp();
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(268);
					expression(0);
					setState(269);
					sp();
					setState(277);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(270);
						match(COMMA);
						setState(271);
						sp();
						setState(272);
						expression(0);
						setState(273);
						sp();
						}
						}
						setState(279);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(282);
				sp();
				setState(283);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				match(WITH);
				setState(286);
				sp();
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(287);
					expression(0);
					setState(288);
					sp();
					setState(295);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(289);
						match(COMMA);
						setState(290);
						sp();
						setState(291);
						expression(0);
						}
						}
						setState(297);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(300);
				sp();
				setState(301);
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
			setState(305);
			id();
			setState(306);
			sp();
			setState(307);
			match(CON);
			setState(308);
			sp();
			setState(309);
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
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(311);
				id();
				setState(312);
				sp();
				setState(313);
				match(DOT);
				setState(314);
				sp();
				}
			}

			setState(318);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(334);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(319);
				match(BRACKET_LEFT);
				setState(320);
				sp();
				setState(321);
				typeHint();
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(322);
					sp();
					setState(323);
					match(COMMA);
					setState(324);
					sp();
					setState(325);
					typeHint();
					}
					}
					setState(331);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(332);
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
			setState(336);
			match(LB);
			setState(349);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << WS) | (1L << NL) | (1L << COMMENT) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(337);
				sp();
				setState(338);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(346);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(339);
					sp();
					setState(340);
					match(COMMA);
					setState(341);
					sp();
					setState(342);
					((FnThContext)_localctx).typeHint = typeHint();
					((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
					}
					}
					setState(348);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(351);
			match(RB);
			setState(352);
			sp();
			setState(353);
			match(ARROW_RIGHT);
			setState(354);
			sp();
			setState(355);
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
			setState(357);
			match(LB);
			setState(358);
			sp();
			setState(359);
			fieldTh();
			setState(365); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(360);
				sp();
				setState(361);
				match(COMMA);
				setState(362);
				sp();
				setState(363);
				fieldTh();
				}
				}
				setState(367); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(369);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitNonUnionTh(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NonUnionThContext nonUnionTh() throws RecognitionException {
		NonUnionThContext _localctx = new NonUnionThContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_nonUnionTh);
		try {
			setState(374);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(371);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(372);
				fnTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(373);
				structTh();
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
			setState(376);
			nonUnionTh();
			setState(382); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(377);
					sp();
					setState(378);
					match(VERT_LINE);
					setState(379);
					sp();
					setState(380);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(384); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitTypeHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeHintContext typeHint() throws RecognitionException {
		TypeHintContext _localctx = new TypeHintContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeHint);
		try {
			setState(390);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(386);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(387);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(388);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(389);
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
			setState(392);
			match(IS);
			setState(393);
			sp();
			setState(394);
			match(VarId);
			setState(395);
			sp();
			setState(396);
			match(CON);
			setState(397);
			sp();
			setState(398);
			typeHint();
			setState(399);
			sp();
			setState(400);
			match(DO);
			setState(401);
			sp();
			setState(405);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(402);
				blockBody();
				}
				}
				setState(407);
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

	public static class WhenElseContext extends ParserRuleContext {
		public BlockBodyContext blockBody;
		public List<BlockBodyContext> elseStat = new ArrayList<BlockBodyContext>();
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public WhenElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whenElse; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitWhenElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhenElseContext whenElse() throws RecognitionException {
		WhenElseContext _localctx = new WhenElseContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_whenElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(408);
			match(ELSE);
			setState(409);
			sp();
			setState(413);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(410);
				((WhenElseContext)_localctx).blockBody = blockBody();
				((WhenElseContext)_localctx).elseStat.add(((WhenElseContext)_localctx).blockBody);
				}
				}
				setState(415);
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
			setState(416);
			id();
			setState(427);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(421);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(417);
						match(NL);
						setState(419);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(418);
							match(WS);
							}
						}

						}
					}

					setState(423);
					match(DOT);
					setState(424);
					match(VarId);
					}
					} 
				}
				setState(429);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			setState(430);
			sp();
			setState(436);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(431);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(432);
				match(CON);
				setState(433);
				sp();
				setState(434);
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
			setState(438);
			sp();
			setState(439);
			match(EQ);
			setState(440);
			sp();
			setState(441);
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
			setState(443);
			match(RETURN);
			setState(444);
			sp();
			setState(446);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(445);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitWhile_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final While_statContext while_stat() throws RecognitionException {
		While_statContext _localctx = new While_statContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_while_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(448);
			match(WHILE);
			setState(449);
			sp();
			setState(450);
			((While_statContext)_localctx).cond = expression(0);
			setState(451);
			sp();
			setState(452);
			match(DO);
			setState(453);
			sp();
			setState(457);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(454);
				blockBody();
				}
				}
				setState(459);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(460);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFnArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnArgContext fnArg() throws RecognitionException {
		FnArgContext _localctx = new FnArgContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			id();
			setState(463);
			sp();
			setState(474);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(464);
				match(CON);
				setState(465);
				sp();
				setState(466);
				typeHint();
				setState(472);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(467);
					sp();
					setState(468);
					match(EQ);
					setState(469);
					sp();
					setState(470);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaContext lambda() throws RecognitionException {
		LambdaContext _localctx = new LambdaContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_lambda);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(476);
			match(LAMBDA);
			setState(492);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(477);
				sp();
				setState(478);
				fnArg();
				setState(479);
				sp();
				setState(486);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(480);
					match(COMMA);
					setState(481);
					sp();
					setState(482);
					fnArg();
					}
					}
					setState(488);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(489);
				sp();
				setState(490);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(494);
			sp();
			setState(498);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(495);
					blockBody();
					}
					} 
				}
				setState(500);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(501);
			sp();
			setState(503);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(502);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitBlockBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockBodyContext blockBody() throws RecognitionException {
		BlockBodyContext _localctx = new BlockBodyContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_blockBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(505);
				store();
				}
				break;
			case 2:
				{
				setState(506);
				while_stat();
				}
				break;
			case 3:
				{
				setState(507);
				expression(0);
				}
				break;
			case 4:
				{
				setState(508);
				ret();
				}
				break;
			}
			setState(511);
			sp();
			setState(513);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(512);
				match(SEMI);
				}
				break;
			}
			setState(515);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitScalarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeContext scalarType() throws RecognitionException {
		ScalarTypeContext _localctx = new ScalarTypeContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_scalarType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			match(TYPE);
			setState(518);
			sp();
			setState(519);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(537);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(520);
				sp();
				setState(521);
				match(BRACKET_LEFT);
				setState(522);
				sp();
				setState(523);
				((ScalarTypeContext)_localctx).TypeId = match(TypeId);
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
				setState(531);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(524);
						sp();
						setState(525);
						match(COMMA);
						setState(526);
						sp();
						setState(527);
						((ScalarTypeContext)_localctx).TypeId = match(TypeId);
						((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
						}
						} 
					}
					setState(533);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				setState(534);
				sp();
				setState(535);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(539);
			sp();
			setState(540);
			match(EQ);
			setState(541);
			sp();
			setState(543);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(542);
				match(REF);
				}
			}

			setState(545);
			sp();
			setState(546);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitTypeField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeFieldContext typeField() throws RecognitionException {
		TypeFieldContext _localctx = new TypeFieldContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(549);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(548);
				match(SELF);
				}
			}

			setState(551);
			sp();
			setState(552);
			match(VarId);
			setState(553);
			sp();
			setState(554);
			match(CON);
			setState(555);
			sp();
			setState(556);
			typeHint();
			setState(562);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(557);
				sp();
				setState(558);
				match(EQ);
				setState(559);
				sp();
				setState(560);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitStructType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructTypeContext structType() throws RecognitionException {
		StructTypeContext _localctx = new StructTypeContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_structType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(564);
			match(TYPE);
			setState(565);
			sp();
			setState(566);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(584);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(567);
				sp();
				setState(568);
				match(BRACKET_LEFT);
				setState(569);
				sp();
				setState(570);
				((StructTypeContext)_localctx).TypeId = match(TypeId);
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
				setState(578);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(571);
						sp();
						setState(572);
						match(COMMA);
						setState(573);
						sp();
						setState(574);
						((StructTypeContext)_localctx).TypeId = match(TypeId);
						((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
						}
						} 
					}
					setState(580);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				}
				setState(581);
				sp();
				setState(582);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(586);
			sp();
			setState(587);
			match(EQ);
			setState(588);
			sp();
			setState(589);
			match(LB);
			setState(593);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(590);
					match(NL);
					}
					} 
				}
				setState(595);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			}
			setState(596);
			typeField();
			setState(607);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(597);
				match(COMMA);
				setState(601);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(598);
						match(NL);
						}
						} 
					}
					setState(603);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				}
				setState(604);
				typeField();
				}
				}
				setState(609);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(613);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(610);
				match(NL);
				}
				}
				setState(615);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(616);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitUnionType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionTypeContext unionType() throws RecognitionException {
		UnionTypeContext _localctx = new UnionTypeContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_unionType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(618);
			match(TYPE);
			setState(619);
			sp();
			setState(620);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(621);
			sp();
			setState(638);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(622);
				match(BRACKET_LEFT);
				setState(623);
				sp();
				setState(624);
				((UnionTypeContext)_localctx).TypeId = match(TypeId);
				((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
				setState(632);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(625);
						sp();
						setState(626);
						match(COMMA);
						setState(627);
						sp();
						setState(628);
						((UnionTypeContext)_localctx).TypeId = match(TypeId);
						((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
						}
						} 
					}
					setState(634);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				}
				setState(635);
				sp();
				setState(636);
				match(BRACKET_RIGTH);
				}
			}

			setState(640);
			sp();
			setState(641);
			match(EQ);
			setState(642);
			sp();
			setState(643);
			scalarTh();
			setState(649); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(644);
					sp();
					setState(645);
					match(VERT_LINE);
					setState(646);
					sp();
					setState(647);
					scalarTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(651); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,68,_ctx);
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
		enterRule(_localctx, 48, RULE_type);
		try {
			setState(656);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(653);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(654);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(655);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_def);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			match(DEF);
			setState(659);
			sp();
			setState(660);
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
			setState(661);
			sp();
			setState(678);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(662);
				match(BRACKET_LEFT);
				setState(663);
				sp();
				setState(664);
				match(TypeId);
				setState(672);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(665);
						sp();
						setState(666);
						match(COMMA);
						setState(667);
						sp();
						setState(668);
						match(TypeId);
						}
						} 
					}
					setState(674);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				}
				setState(675);
				sp();
				setState(676);
				match(BRACKET_RIGTH);
				}
			}

			setState(680);
			sp();
			setState(681);
			match(EQ);
			setState(682);
			sp();
			setState(697);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(683);
				fnArg();
				setState(684);
				sp();
				setState(691);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(685);
					match(COMMA);
					setState(686);
					sp();
					setState(687);
					fnArg();
					}
					}
					setState(693);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(694);
				sp();
				setState(695);
				match(DO);
				}
				break;
			}
			setState(699);
			sp();
			setState(710);
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
				setState(703);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(700);
					blockBody();
					}
					}
					setState(705);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(706);
				sp();
				setState(707);
				match(DOT);
				}
				}
				break;
			case LlBegin:
				{
				setState(709);
				llvm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(713);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(712);
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
		enterRule(_localctx, 52, RULE_importEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(715);
			sp();
			setState(717);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIV) {
				{
				setState(716);
				((ImportEntryContext)_localctx).abs = match(DIV);
				}
			}

			setState(719);
			match(VarId);
			setState(724);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV) {
				{
				{
				setState(720);
				match(DIV);
				setState(721);
				match(VarId);
				}
				}
				setState(726);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(741);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(727);
				sp();
				setState(728);
				match(WITH);
				setState(729);
				sp();
				setState(730);
				match(TypeId);
				setState(731);
				sp();
				setState(738);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(732);
					match(COMMA);
					setState(733);
					sp();
					setState(734);
					match(TypeId);
					}
					}
					setState(740);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitImport_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Import_Context import_() throws RecognitionException {
		Import_Context _localctx = new Import_Context(_ctx, getState());
		enterRule(_localctx, 54, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(743);
			match(IMPORT);
			setState(744);
			importEntry();
			setState(749);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(745);
				match(NL);
				setState(746);
				importEntry();
				}
				}
				setState(751);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(752);
			match(WS);
			setState(753);
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLevel1(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Level1Context level1() throws RecognitionException {
		Level1Context _localctx = new Level1Context(_ctx, getState());
		enterRule(_localctx, 56, RULE_level1);
		try {
			setState(757);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(755);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(756);
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
		enterRule(_localctx, 58, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(759);
			sp();
			setState(761);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(760);
				import_();
				}
			}

			setState(763);
			sp();
			setState(769);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(764);
					sp();
					setState(765);
					llvm();
					}
					} 
				}
				setState(771);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(772);
			sp();
			setState(778);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(773);
				level1();
				setState(774);
				sp();
				}
				}
				setState(780);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(781);
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
		enterRule(_localctx, 60, RULE_llvmBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(786);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (LLVM_NL - 62)) | (1L << (LLVM_WS - 62)) | (1L << (IrLine - 62)) | (1L << (LL_Dot - 62)))) != 0)) {
				{
				{
				setState(783);
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
				setState(788);
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
		enterRule(_localctx, 62, RULE_llvm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(789);
			match(LlBegin);
			setState(790);
			llvmBody();
			setState(791);
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
			return precpred(_ctx, 10);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3D\u031c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\7\2D\n\2\f\2\16\2G\13\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\7\5d\n\5\f\5\16\5g\13\5\3\5\3\5\3\5\3\5\7\5m\n\5\f\5\16\5p\13\5\5\5r"+
		"\n\5\3\5\5\5u\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5~\n\5\r\5\16\5\177\3"+
		"\5\3\5\5\5\u0084\n\5\3\5\3\5\3\5\5\5\u0089\n\5\3\5\3\5\7\5\u008d\n\5\f"+
		"\5\16\5\u0090\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u0098\n\5\f\5\16\5\u009b"+
		"\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a3\n\5\f\5\16\5\u00a6\13\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\7\5\u00ae\n\5\f\5\16\5\u00b1\13\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\5\u00b9\n\5\f\5\16\5\u00bc\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5"+
		"\5\u00c5\n\5\5\5\u00c7\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\7\5\u00d4\n\5\f\5\16\5\u00d7\13\5\3\5\3\5\5\5\u00db\n\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\5\u00e2\n\5\f\5\16\5\u00e5\13\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\7\5\u00ef\n\5\f\5\16\5\u00f2\13\5\3\5\3\5\5\5\u00f6\n\5\3\5\7\5\u00f9"+
		"\n\5\f\5\16\5\u00fc\13\5\3\5\3\5\3\5\3\5\5\5\u0102\n\5\5\5\u0104\n\5\3"+
		"\5\3\5\7\5\u0108\n\5\f\5\16\5\u010b\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\7\6\u0116\n\6\f\6\16\6\u0119\13\6\5\6\u011b\n\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u0128\n\6\f\6\16\6\u012b\13\6\5\6\u012d"+
		"\n\6\3\6\3\6\3\6\5\6\u0132\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\5\b\u013f\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u014a\n\b\f"+
		"\b\16\b\u014d\13\b\3\b\3\b\5\b\u0151\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\7\t\u015b\n\t\f\t\16\t\u015e\13\t\5\t\u0160\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\6\n\u0170\n\n\r\n\16\n\u0171\3\n"+
		"\3\n\3\13\3\13\3\13\5\13\u0179\n\13\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0181"+
		"\n\f\r\f\16\f\u0182\3\r\3\r\3\r\3\r\5\r\u0189\n\r\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0196\n\16\f\16\16\16\u0199\13"+
		"\16\3\17\3\17\3\17\7\17\u019e\n\17\f\17\16\17\u01a1\13\17\3\20\3\20\3"+
		"\20\5\20\u01a6\n\20\5\20\u01a8\n\20\3\20\3\20\7\20\u01ac\n\20\f\20\16"+
		"\20\u01af\13\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u01b7\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\5\21\u01c1\n\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\7\22\u01ca\n\22\f\22\16\22\u01cd\13\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u01db\n\23\5\23\u01dd\n"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\7\24\u01e7\n\24\f\24\16\24"+
		"\u01ea\13\24\3\24\3\24\3\24\5\24\u01ef\n\24\3\24\3\24\7\24\u01f3\n\24"+
		"\f\24\16\24\u01f6\13\24\3\24\3\24\5\24\u01fa\n\24\3\25\3\25\3\25\3\25"+
		"\5\25\u0200\n\25\3\25\3\25\5\25\u0204\n\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0214\n\26\f\26\16\26"+
		"\u0217\13\26\3\26\3\26\3\26\5\26\u021c\n\26\3\26\3\26\3\26\3\26\5\26\u0222"+
		"\n\26\3\26\3\26\3\26\3\27\5\27\u0228\n\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\5\27\u0235\n\27\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\7\30\u0243\n\30\f\30\16\30\u0246\13\30"+
		"\3\30\3\30\3\30\5\30\u024b\n\30\3\30\3\30\3\30\3\30\3\30\7\30\u0252\n"+
		"\30\f\30\16\30\u0255\13\30\3\30\3\30\3\30\7\30\u025a\n\30\f\30\16\30\u025d"+
		"\13\30\3\30\7\30\u0260\n\30\f\30\16\30\u0263\13\30\3\30\7\30\u0266\n\30"+
		"\f\30\16\30\u0269\13\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\7\31\u0279\n\31\f\31\16\31\u027c\13\31\3\31\3"+
		"\31\3\31\5\31\u0281\n\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\6\31\u028c\n\31\r\31\16\31\u028d\3\32\3\32\3\32\5\32\u0293\n\32\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u02a1\n\33"+
		"\f\33\16\33\u02a4\13\33\3\33\3\33\3\33\5\33\u02a9\n\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u02b4\n\33\f\33\16\33\u02b7\13\33"+
		"\3\33\3\33\3\33\5\33\u02bc\n\33\3\33\3\33\7\33\u02c0\n\33\f\33\16\33\u02c3"+
		"\13\33\3\33\3\33\3\33\3\33\5\33\u02c9\n\33\3\33\5\33\u02cc\n\33\3\34\3"+
		"\34\5\34\u02d0\n\34\3\34\3\34\3\34\7\34\u02d5\n\34\f\34\16\34\u02d8\13"+
		"\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\7\34\u02e3\n\34\f\34"+
		"\16\34\u02e6\13\34\5\34\u02e8\n\34\3\35\3\35\3\35\3\35\7\35\u02ee\n\35"+
		"\f\35\16\35\u02f1\13\35\3\35\3\35\3\35\3\36\3\36\5\36\u02f8\n\36\3\37"+
		"\3\37\5\37\u02fc\n\37\3\37\3\37\3\37\3\37\7\37\u0302\n\37\f\37\16\37\u0305"+
		"\13\37\3\37\3\37\3\37\3\37\7\37\u030b\n\37\f\37\16\37\u030e\13\37\3\37"+
		"\3\37\3 \7 \u0313\n \f \16 \u0316\13 \3!\3!\3!\3!\3!\2\3\b\"\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\16\3\2\64\66"+
		"\3\2\67<\4\2##==\3\2\64\65\3\2\5\6\4\2\3\4==\3\2\f\17\3\2\21\22\3\2\32"+
		"\33\6\2\3\6\f\17\21\22==\7\2\3\7\f\17\21\22\32\33==\4\2@BDD\2\u0366\2"+
		"E\3\2\2\2\4H\3\2\2\2\6J\3\2\2\2\b\u0088\3\2\2\2\n\u0131\3\2\2\2\f\u0133"+
		"\3\2\2\2\16\u013e\3\2\2\2\20\u0152\3\2\2\2\22\u0167\3\2\2\2\24\u0178\3"+
		"\2\2\2\26\u017a\3\2\2\2\30\u0188\3\2\2\2\32\u018a\3\2\2\2\34\u019a\3\2"+
		"\2\2\36\u01a2\3\2\2\2 \u01bd\3\2\2\2\"\u01c2\3\2\2\2$\u01d0\3\2\2\2&\u01de"+
		"\3\2\2\2(\u01ff\3\2\2\2*\u0207\3\2\2\2,\u0227\3\2\2\2.\u0236\3\2\2\2\60"+
		"\u026c\3\2\2\2\62\u0292\3\2\2\2\64\u0294\3\2\2\2\66\u02cd\3\2\2\28\u02e9"+
		"\3\2\2\2:\u02f7\3\2\2\2<\u02f9\3\2\2\2>\u0314\3\2\2\2@\u0317\3\2\2\2B"+
		"D\t\2\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\3\3\2\2\2GE\3\2\2\2"+
		"HI\t\3\2\2I\5\3\2\2\2JK\t\4\2\2K\7\3\2\2\2LM\b\5\1\2M\u0089\5\4\3\2N\u0089"+
		"\5\6\4\2O\u0089\7>\2\2PQ\7\n\2\2QR\5\2\2\2RS\5\b\5\2ST\5\2\2\2TU\7\t\2"+
		"\2U\u0089\3\2\2\2V\u0089\5\n\6\2W\u0089\5&\24\2XY\7\7\2\2YZ\5\2\2\2Z["+
		"\5\b\5\n[\u0089\3\2\2\2\\]\7\24\2\2]^\5\2\2\2^_\5\b\5\2_`\5\2\2\2`a\7"+
		"\25\2\2ae\5\2\2\2bd\5(\25\2cb\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fq"+
		"\3\2\2\2ge\3\2\2\2hi\5\2\2\2ij\7\26\2\2jn\5\2\2\2km\5(\25\2lk\3\2\2\2"+
		"mp\3\2\2\2nl\3\2\2\2no\3\2\2\2or\3\2\2\2pn\3\2\2\2qh\3\2\2\2qr\3\2\2\2"+
		"rt\3\2\2\2su\t\5\2\2ts\3\2\2\2tu\3\2\2\2uv\3\2\2\2vw\7\b\2\2w\u0089\3"+
		"\2\2\2xy\7-\2\2yz\5\2\2\2z{\5\b\5\2{}\5\2\2\2|~\5\32\16\2}|\3\2\2\2~\177"+
		"\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0083"+
		"\5\2\2\2\u0082\u0084\5\34\17\2\u0083\u0082\3\2\2\2\u0083\u0084\3\2\2\2"+
		"\u0084\u0085\3\2\2\2\u0085\u0086\t\5\2\2\u0086\u0087\7\b\2\2\u0087\u0089"+
		"\3\2\2\2\u0088L\3\2\2\2\u0088N\3\2\2\2\u0088O\3\2\2\2\u0088P\3\2\2\2\u0088"+
		"V\3\2\2\2\u0088W\3\2\2\2\u0088X\3\2\2\2\u0088\\\3\2\2\2\u0088x\3\2\2\2"+
		"\u0089\u0109\3\2\2\2\u008a\u008e\f\t\2\2\u008b\u008d\7\64\2\2\u008c\u008b"+
		"\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f"+
		"\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\t\6\2\2\u0092\u0093\5\2"+
		"\2\2\u0093\u0094\5\b\5\n\u0094\u0108\3\2\2\2\u0095\u0099\f\b\2\2\u0096"+
		"\u0098\7\64\2\2\u0097\u0096\3\2\2\2\u0098\u009b\3\2\2\2\u0099\u0097\3"+
		"\2\2\2\u0099\u009a\3\2\2\2\u009a\u009c\3\2\2\2\u009b\u0099\3\2\2\2\u009c"+
		"\u009d\t\7\2\2\u009d\u009e\5\2\2\2\u009e\u009f\5\b\5\t\u009f\u0108\3\2"+
		"\2\2\u00a0\u00a4\f\7\2\2\u00a1\u00a3\7\64\2\2\u00a2\u00a1\3\2\2\2\u00a3"+
		"\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a7\3\2"+
		"\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\t\b\2\2\u00a8\u00a9\5\2\2\2\u00a9"+
		"\u00aa\5\b\5\b\u00aa\u0108\3\2\2\2\u00ab\u00af\f\6\2\2\u00ac\u00ae\7\64"+
		"\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00af"+
		"\u00b0\3\2\2\2\u00b0\u00b2\3\2\2\2\u00b1\u00af\3\2\2\2\u00b2\u00b3\t\t"+
		"\2\2\u00b3\u00b4\5\2\2\2\u00b4\u00b5\5\b\5\7\u00b5\u0108\3\2\2\2\u00b6"+
		"\u00ba\f\5\2\2\u00b7\u00b9\7\64\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3"+
		"\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc"+
		"\u00ba\3\2\2\2\u00bd\u00be\t\n\2\2\u00be\u00bf\5\2\2\2\u00bf\u00c0\5\b"+
		"\5\6\u00c0\u0108\3\2\2\2\u00c1\u00c6\f\16\2\2\u00c2\u00c4\7\65\2\2\u00c3"+
		"\u00c5\7\64\2\2\u00c4\u00c3\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\3"+
		"\2\2\2\u00c6\u00c2\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8"+
		"\u00c9\7\b\2\2\u00c9\u00da\t\13\2\2\u00ca\u00cb\5\2\2\2\u00cb\u00cc\7"+
		"\61\2\2\u00cc\u00cd\5\2\2\2\u00cd\u00d5\5\30\r\2\u00ce\u00cf\5\2\2\2\u00cf"+
		"\u00d0\7\13\2\2\u00d0\u00d1\5\2\2\2\u00d1\u00d2\5\30\r\2\u00d2\u00d4\3"+
		"\2\2\2\u00d3\u00ce\3\2\2\2\u00d4\u00d7\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5"+
		"\u00d6\3\2\2\2\u00d6\u00d8\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d8\u00d9\7\62"+
		"\2\2\u00d9\u00db\3\2\2\2\u00da\u00ca\3\2\2\2\u00da\u00db\3\2\2\2\u00db"+
		"\u00dc\3\2\2\2\u00dc\u00dd\5\2\2\2\u00dd\u00de\5\n\6\2\u00de\u0108\3\2"+
		"\2\2\u00df\u00e3\f\r\2\2\u00e0\u00e2\7\64\2\2\u00e1\u00e0\3\2\2\2\u00e2"+
		"\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00f5\3\2"+
		"\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00e7\7\61\2\2\u00e7\u00e8\5\2\2\2\u00e8"+
		"\u00f0\5\30\r\2\u00e9\u00ea\5\2\2\2\u00ea\u00eb\7\13\2\2\u00eb\u00ec\5"+
		"\2\2\2\u00ec\u00ed\5\30\r\2\u00ed\u00ef\3\2\2\2\u00ee\u00e9\3\2\2\2\u00ef"+
		"\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f3\3\2"+
		"\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00f4\7\62\2\2\u00f4\u00f6\3\2\2\2\u00f5"+
		"\u00e6\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00fa\3\2\2\2\u00f7\u00f9\7\64"+
		"\2\2\u00f8\u00f7\3\2\2\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa"+
		"\u00fb\3\2\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u0108\5\n"+
		"\6\2\u00fe\u0103\f\f\2\2\u00ff\u0101\7\65\2\2\u0100\u0102\7\64\2\2\u0101"+
		"\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0104\3\2\2\2\u0103\u00ff\3\2"+
		"\2\2\u0103\u0104\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0106\7\b\2\2\u0106"+
		"\u0108\7=\2\2\u0107\u008a\3\2\2\2\u0107\u0095\3\2\2\2\u0107\u00a0\3\2"+
		"\2\2\u0107\u00ab\3\2\2\2\u0107\u00b6\3\2\2\2\u0107\u00c1\3\2\2\2\u0107"+
		"\u00df\3\2\2\2\u0107\u00fe\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2"+
		"\2\2\u0109\u010a\3\2\2\2\u010a\t\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d"+
		"\7\n\2\2\u010d\u011a\5\2\2\2\u010e\u010f\5\b\5\2\u010f\u0117\5\2\2\2\u0110"+
		"\u0111\7\13\2\2\u0111\u0112\5\2\2\2\u0112\u0113\5\b\5\2\u0113\u0114\5"+
		"\2\2\2\u0114\u0116\3\2\2\2\u0115\u0110\3\2\2\2\u0116\u0119\3\2\2\2\u0117"+
		"\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2"+
		"\2\2\u011a\u010e\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u011d\5\2\2\2\u011d\u011e\7\t\2\2\u011e\u0132\3\2\2\2\u011f\u0120\7("+
		"\2\2\u0120\u012c\5\2\2\2\u0121\u0122\5\b\5\2\u0122\u0129\5\2\2\2\u0123"+
		"\u0124\7\13\2\2\u0124\u0125\5\2\2\2\u0125\u0126\5\b\5\2\u0126\u0128\3"+
		"\2\2\2\u0127\u0123\3\2\2\2\u0128\u012b\3\2\2\2\u0129\u0127\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u012d\3\2\2\2\u012b\u0129\3\2\2\2\u012c\u0121\3\2"+
		"\2\2\u012c\u012d\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\5\2\2\2\u012f"+
		"\u0130\7\b\2\2\u0130\u0132\3\2\2\2\u0131\u010c\3\2\2\2\u0131\u011f\3\2"+
		"\2\2\u0132\13\3\2\2\2\u0133\u0134\5\6\4\2\u0134\u0135\5\2\2\2\u0135\u0136"+
		"\7\37\2\2\u0136\u0137\5\2\2\2\u0137\u0138\5\30\r\2\u0138\r\3\2\2\2\u0139"+
		"\u013a\5\6\4\2\u013a\u013b\5\2\2\2\u013b\u013c\7\b\2\2\u013c\u013d\5\2"+
		"\2\2\u013d\u013f\3\2\2\2\u013e\u0139\3\2\2\2\u013e\u013f\3\2\2\2\u013f"+
		"\u0140\3\2\2\2\u0140\u0150\7>\2\2\u0141\u0142\7\61\2\2\u0142\u0143\5\2"+
		"\2\2\u0143\u014b\5\30\r\2\u0144\u0145\5\2\2\2\u0145\u0146\7\13\2\2\u0146"+
		"\u0147\5\2\2\2\u0147\u0148\5\30\r\2\u0148\u014a\3\2\2\2\u0149\u0144\3"+
		"\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u014e\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u014f\7\62\2\2\u014f\u0151\3"+
		"\2\2\2\u0150\u0141\3\2\2\2\u0150\u0151\3\2\2\2\u0151\17\3\2\2\2\u0152"+
		"\u015f\7\n\2\2\u0153\u0154\5\2\2\2\u0154\u015c\5\30\r\2\u0155\u0156\5"+
		"\2\2\2\u0156\u0157\7\13\2\2\u0157\u0158\5\2\2\2\u0158\u0159\5\30\r\2\u0159"+
		"\u015b\3\2\2\2\u015a\u0155\3\2\2\2\u015b\u015e\3\2\2\2\u015c\u015a\3\2"+
		"\2\2\u015c\u015d\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2\2\2\u015f"+
		"\u0153\3\2\2\2\u015f\u0160\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\7\t"+
		"\2\2\u0162\u0163\5\2\2\2\u0163\u0164\7 \2\2\u0164\u0165\5\2\2\2\u0165"+
		"\u0166\5\30\r\2\u0166\21\3\2\2\2\u0167\u0168\7\n\2\2\u0168\u0169\5\2\2"+
		"\2\u0169\u016f\5\f\7\2\u016a\u016b\5\2\2\2\u016b\u016c\7\13\2\2\u016c"+
		"\u016d\5\2\2\2\u016d\u016e\5\f\7\2\u016e\u0170\3\2\2\2\u016f\u016a\3\2"+
		"\2\2\u0170\u0171\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172"+
		"\u0173\3\2\2\2\u0173\u0174\7\t\2\2\u0174\23\3\2\2\2\u0175\u0179\5\16\b"+
		"\2\u0176\u0179\5\20\t\2\u0177\u0179\5\22\n\2\u0178\u0175\3\2\2\2\u0178"+
		"\u0176\3\2\2\2\u0178\u0177\3\2\2\2\u0179\25\3\2\2\2\u017a\u0180\5\24\13"+
		"\2\u017b\u017c\5\2\2\2\u017c\u017d\7\60\2\2\u017d\u017e\5\2\2\2\u017e"+
		"\u017f\5\24\13\2\u017f\u0181\3\2\2\2\u0180\u017b\3\2\2\2\u0181\u0182\3"+
		"\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\27\3\2\2\2\u0184"+
		"\u0189\5\16\b\2\u0185\u0189\5\22\n\2\u0186\u0189\5\20\t\2\u0187\u0189"+
		"\5\26\f\2\u0188\u0184\3\2\2\2\u0188\u0185\3\2\2\2\u0188\u0186\3\2\2\2"+
		"\u0188\u0187\3\2\2\2\u0189\31\3\2\2\2\u018a\u018b\7,\2\2\u018b\u018c\5"+
		"\2\2\2\u018c\u018d\7=\2\2\u018d\u018e\5\2\2\2\u018e\u018f\7\37\2\2\u018f"+
		"\u0190\5\2\2\2\u0190\u0191\5\30\r\2\u0191\u0192\5\2\2\2\u0192\u0193\7"+
		"\25\2\2\u0193\u0197\5\2\2\2\u0194\u0196\5(\25\2\u0195\u0194\3\2\2\2\u0196"+
		"\u0199\3\2\2\2\u0197\u0195\3\2\2\2\u0197\u0198\3\2\2\2\u0198\33\3\2\2"+
		"\2\u0199\u0197\3\2\2\2\u019a\u019b\7\26\2\2\u019b\u019f\5\2\2\2\u019c"+
		"\u019e\5(\25\2\u019d\u019c\3\2\2\2\u019e\u01a1\3\2\2\2\u019f\u019d\3\2"+
		"\2\2\u019f\u01a0\3\2\2\2\u01a0\35\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2\u01ad"+
		"\5\6\4\2\u01a3\u01a5\7\65\2\2\u01a4\u01a6\7\64\2\2\u01a5\u01a4\3\2\2\2"+
		"\u01a5\u01a6\3\2\2\2\u01a6\u01a8\3\2\2\2\u01a7\u01a3\3\2\2\2\u01a7\u01a8"+
		"\3\2\2\2\u01a8\u01a9\3\2\2\2\u01a9\u01aa\7\b\2\2\u01aa\u01ac\7=\2\2\u01ab"+
		"\u01a7\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2"+
		"\2\2\u01ae\u01b0\3\2\2\2\u01af\u01ad\3\2\2\2\u01b0\u01b6\5\2\2\2\u01b1"+
		"\u01b7\5\n\6\2\u01b2\u01b3\7\37\2\2\u01b3\u01b4\5\2\2\2\u01b4\u01b5\5"+
		"\30\r\2\u01b5\u01b7\3\2\2\2\u01b6\u01b1\3\2\2\2\u01b6\u01b2\3\2\2\2\u01b6"+
		"\u01b7\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b9\5\2\2\2\u01b9\u01ba\7\20"+
		"\2\2\u01ba\u01bb\5\2\2\2\u01bb\u01bc\5\b\5\2\u01bc\37\3\2\2\2\u01bd\u01be"+
		"\7+\2\2\u01be\u01c0\5\2\2\2\u01bf\u01c1\5\b\5\2\u01c0\u01bf\3\2\2\2\u01c0"+
		"\u01c1\3\2\2\2\u01c1!\3\2\2\2\u01c2\u01c3\7\34\2\2\u01c3\u01c4\5\2\2\2"+
		"\u01c4\u01c5\5\b\5\2\u01c5\u01c6\5\2\2\2\u01c6\u01c7\7\25\2\2\u01c7\u01cb"+
		"\5\2\2\2\u01c8\u01ca\5(\25\2\u01c9\u01c8\3\2\2\2\u01ca\u01cd\3\2\2\2\u01cb"+
		"\u01c9\3\2\2\2\u01cb\u01cc\3\2\2\2\u01cc\u01ce\3\2\2\2\u01cd\u01cb\3\2"+
		"\2\2\u01ce\u01cf\7\b\2\2\u01cf#\3\2\2\2\u01d0\u01d1\5\6\4\2\u01d1\u01dc"+
		"\5\2\2\2\u01d2\u01d3\7\37\2\2\u01d3\u01d4\5\2\2\2\u01d4\u01da\5\30\r\2"+
		"\u01d5\u01d6\5\2\2\2\u01d6\u01d7\7\20\2\2\u01d7\u01d8\5\2\2\2\u01d8\u01d9"+
		"\5\b\5\2\u01d9\u01db\3\2\2\2\u01da\u01d5\3\2\2\2\u01da\u01db\3\2\2\2\u01db"+
		"\u01dd\3\2\2\2\u01dc\u01d2\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd%\3\2\2\2"+
		"\u01de\u01ee\7&\2\2\u01df\u01e0\5\2\2\2\u01e0\u01e1\5$\23\2\u01e1\u01e8"+
		"\5\2\2\2\u01e2\u01e3\7\13\2\2\u01e3\u01e4\5\2\2\2\u01e4\u01e5\5$\23\2"+
		"\u01e5\u01e7\3\2\2\2\u01e6\u01e2\3\2\2\2\u01e7\u01ea\3\2\2\2\u01e8\u01e6"+
		"\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01eb\3\2\2\2\u01ea\u01e8\3\2\2\2\u01eb"+
		"\u01ec\5\2\2\2\u01ec\u01ed\7 \2\2\u01ed\u01ef\3\2\2\2\u01ee\u01df\3\2"+
		"\2\2\u01ee\u01ef\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f4\5\2\2\2\u01f1"+
		"\u01f3\5(\25\2\u01f2\u01f1\3\2\2\2\u01f3\u01f6\3\2\2\2\u01f4\u01f2\3\2"+
		"\2\2\u01f4\u01f5\3\2\2\2\u01f5\u01f7\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f7"+
		"\u01f9\5\2\2\2\u01f8\u01fa\7\b\2\2\u01f9\u01f8\3\2\2\2\u01f9\u01fa\3\2"+
		"\2\2\u01fa\'\3\2\2\2\u01fb\u0200\5\36\20\2\u01fc\u0200\5\"\22\2\u01fd"+
		"\u0200\5\b\5\2\u01fe\u0200\5 \21\2\u01ff\u01fb\3\2\2\2\u01ff\u01fc\3\2"+
		"\2\2\u01ff\u01fd\3\2\2\2\u01ff\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201"+
		"\u0203\5\2\2\2\u0202\u0204\7\23\2\2\u0203\u0202\3\2\2\2\u0203\u0204\3"+
		"\2\2\2\u0204\u0205\3\2\2\2\u0205\u0206\5\2\2\2\u0206)\3\2\2\2\u0207\u0208"+
		"\7!\2\2\u0208\u0209\5\2\2\2\u0209\u021b\7>\2\2\u020a\u020b\5\2\2\2\u020b"+
		"\u020c\7\61\2\2\u020c\u020d\5\2\2\2\u020d\u0215\7>\2\2\u020e\u020f\5\2"+
		"\2\2\u020f\u0210\7\13\2\2\u0210\u0211\5\2\2\2\u0211\u0212\7>\2\2\u0212"+
		"\u0214\3\2\2\2\u0213\u020e\3\2\2\2\u0214\u0217\3\2\2\2\u0215\u0213\3\2"+
		"\2\2\u0215\u0216\3\2\2\2\u0216\u0218\3\2\2\2\u0217\u0215\3\2\2\2\u0218"+
		"\u0219\5\2\2\2\u0219\u021a\7\62\2\2\u021a\u021c\3\2\2\2\u021b\u020a\3"+
		"\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021e\5\2\2\2\u021e"+
		"\u021f\7\20\2\2\u021f\u0221\5\2\2\2\u0220\u0222\7.\2\2\u0221\u0220\3\2"+
		"\2\2\u0221\u0222\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0224\5\2\2\2\u0224"+
		"\u0225\5@!\2\u0225+\3\2\2\2\u0226\u0228\7#\2\2\u0227\u0226\3\2\2\2\u0227"+
		"\u0228\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022a\5\2\2\2\u022a\u022b\7="+
		"\2\2\u022b\u022c\5\2\2\2\u022c\u022d\7\37\2\2\u022d\u022e\5\2\2\2\u022e"+
		"\u0234\5\30\r\2\u022f\u0230\5\2\2\2\u0230\u0231\7\20\2\2\u0231\u0232\5"+
		"\2\2\2\u0232\u0233\5\b\5\2\u0233\u0235\3\2\2\2\u0234\u022f\3\2\2\2\u0234"+
		"\u0235\3\2\2\2\u0235-\3\2\2\2\u0236\u0237\7!\2\2\u0237\u0238\5\2\2\2\u0238"+
		"\u024a\7>\2\2\u0239\u023a\5\2\2\2\u023a\u023b\7\61\2\2\u023b\u023c\5\2"+
		"\2\2\u023c\u0244\7>\2\2\u023d\u023e\5\2\2\2\u023e\u023f\7\13\2\2\u023f"+
		"\u0240\5\2\2\2\u0240\u0241\7>\2\2\u0241\u0243\3\2\2\2\u0242\u023d\3\2"+
		"\2\2\u0243\u0246\3\2\2\2\u0244\u0242\3\2\2\2\u0244\u0245\3\2\2\2\u0245"+
		"\u0247\3\2\2\2\u0246\u0244\3\2\2\2\u0247\u0248\5\2\2\2\u0248\u0249\7\62"+
		"\2\2\u0249\u024b\3\2\2\2\u024a\u0239\3\2\2\2\u024a\u024b\3\2\2\2\u024b"+
		"\u024c\3\2\2\2\u024c\u024d\5\2\2\2\u024d\u024e\7\20\2\2\u024e\u024f\5"+
		"\2\2\2\u024f\u0253\7\n\2\2\u0250\u0252\7\65\2\2\u0251\u0250\3\2\2\2\u0252"+
		"\u0255\3\2\2\2\u0253\u0251\3\2\2\2\u0253\u0254\3\2\2\2\u0254\u0256\3\2"+
		"\2\2\u0255\u0253\3\2\2\2\u0256\u0261\5,\27\2\u0257\u025b\7\13\2\2\u0258"+
		"\u025a\7\65\2\2\u0259\u0258\3\2\2\2\u025a\u025d\3\2\2\2\u025b\u0259\3"+
		"\2\2\2\u025b\u025c\3\2\2\2\u025c\u025e\3\2\2\2\u025d\u025b\3\2\2\2\u025e"+
		"\u0260\5,\27\2\u025f\u0257\3\2\2\2\u0260\u0263\3\2\2\2\u0261\u025f\3\2"+
		"\2\2\u0261\u0262\3\2\2\2\u0262\u0267\3\2\2\2\u0263\u0261\3\2\2\2\u0264"+
		"\u0266\7\65\2\2\u0265\u0264\3\2\2\2\u0266\u0269\3\2\2\2\u0267\u0265\3"+
		"\2\2\2\u0267\u0268\3\2\2\2\u0268\u026a\3\2\2\2\u0269\u0267\3\2\2\2\u026a"+
		"\u026b\7\t\2\2\u026b/\3\2\2\2\u026c\u026d\7!\2\2\u026d\u026e\5\2\2\2\u026e"+
		"\u026f\7>\2\2\u026f\u0280\5\2\2\2\u0270\u0271\7\61\2\2\u0271\u0272\5\2"+
		"\2\2\u0272\u027a\7>\2\2\u0273\u0274\5\2\2\2\u0274\u0275\7\13\2\2\u0275"+
		"\u0276\5\2\2\2\u0276\u0277\7>\2\2\u0277\u0279\3\2\2\2\u0278\u0273\3\2"+
		"\2\2\u0279\u027c\3\2\2\2\u027a\u0278\3\2\2\2\u027a\u027b\3\2\2\2\u027b"+
		"\u027d\3\2\2\2\u027c\u027a\3\2\2\2\u027d\u027e\5\2\2\2\u027e\u027f\7\62"+
		"\2\2\u027f\u0281\3\2\2\2\u0280\u0270\3\2\2\2\u0280\u0281\3\2\2\2\u0281"+
		"\u0282\3\2\2\2\u0282\u0283\5\2\2\2\u0283\u0284\7\20\2\2\u0284\u0285\5"+
		"\2\2\2\u0285\u028b\5\16\b\2\u0286\u0287\5\2\2\2\u0287\u0288\7\60\2\2\u0288"+
		"\u0289\5\2\2\2\u0289\u028a\5\16\b\2\u028a\u028c\3\2\2\2\u028b\u0286\3"+
		"\2\2\2\u028c\u028d\3\2\2\2\u028d\u028b\3\2\2\2\u028d\u028e\3\2\2\2\u028e"+
		"\61\3\2\2\2\u028f\u0293\5*\26\2\u0290\u0293\5.\30\2\u0291\u0293\5\60\31"+
		"\2\u0292\u028f\3\2\2\2\u0292\u0290\3\2\2\2\u0292\u0291\3\2\2\2\u0293\63"+
		"\3\2\2\2\u0294\u0295\7%\2\2\u0295\u0296\5\2\2\2\u0296\u0297\t\f\2\2\u0297"+
		"\u02a8\5\2\2\2\u0298\u0299\7\61\2\2\u0299\u029a\5\2\2\2\u029a\u02a2\7"+
		">\2\2\u029b\u029c\5\2\2\2\u029c\u029d\7\13\2\2\u029d\u029e\5\2\2\2\u029e"+
		"\u029f\7>\2\2\u029f\u02a1\3\2\2\2\u02a0\u029b\3\2\2\2\u02a1\u02a4\3\2"+
		"\2\2\u02a2\u02a0\3\2\2\2\u02a2\u02a3\3\2\2\2\u02a3\u02a5\3\2\2\2\u02a4"+
		"\u02a2\3\2\2\2\u02a5\u02a6\5\2\2\2\u02a6\u02a7\7\62\2\2\u02a7\u02a9\3"+
		"\2\2\2\u02a8\u0298\3\2\2\2\u02a8\u02a9\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa"+
		"\u02ab\5\2\2\2\u02ab\u02ac\7\20\2\2\u02ac\u02bb\5\2\2\2\u02ad\u02ae\5"+
		"$\23\2\u02ae\u02b5\5\2\2\2\u02af\u02b0\7\13\2\2\u02b0\u02b1\5\2\2\2\u02b1"+
		"\u02b2\5$\23\2\u02b2\u02b4\3\2\2\2\u02b3\u02af\3\2\2\2\u02b4\u02b7\3\2"+
		"\2\2\u02b5\u02b3\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6\u02b8\3\2\2\2\u02b7"+
		"\u02b5\3\2\2\2\u02b8\u02b9\5\2\2\2\u02b9\u02ba\7\25\2\2\u02ba\u02bc\3"+
		"\2\2\2\u02bb\u02ad\3\2\2\2\u02bb\u02bc\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd"+
		"\u02c8\5\2\2\2\u02be\u02c0\5(\25\2\u02bf\u02be\3\2\2\2\u02c0\u02c3\3\2"+
		"\2\2\u02c1\u02bf\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2\u02c4\3\2\2\2\u02c3"+
		"\u02c1\3\2\2\2\u02c4\u02c5\5\2\2\2\u02c5\u02c6\7\b\2\2\u02c6\u02c9\3\2"+
		"\2\2\u02c7\u02c9\5@!\2\u02c8\u02c1\3\2\2\2\u02c8\u02c7\3\2\2\2\u02c9\u02cb"+
		"\3\2\2\2\u02ca\u02cc\5\30\r\2\u02cb\u02ca\3\2\2\2\u02cb\u02cc\3\2\2\2"+
		"\u02cc\65\3\2\2\2\u02cd\u02cf\5\2\2\2\u02ce\u02d0\7\6\2\2\u02cf\u02ce"+
		"\3\2\2\2\u02cf\u02d0\3\2\2\2\u02d0\u02d1\3\2\2\2\u02d1\u02d6\7=\2\2\u02d2"+
		"\u02d3\7\6\2\2\u02d3\u02d5\7=\2\2\u02d4\u02d2\3\2\2\2\u02d5\u02d8\3\2"+
		"\2\2\u02d6\u02d4\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7\u02e7\3\2\2\2\u02d8"+
		"\u02d6\3\2\2\2\u02d9\u02da\5\2\2\2\u02da\u02db\7(\2\2\u02db\u02dc\5\2"+
		"\2\2\u02dc\u02dd\7>\2\2\u02dd\u02e4\5\2\2\2\u02de\u02df\7\13\2\2\u02df"+
		"\u02e0\5\2\2\2\u02e0\u02e1\7>\2\2\u02e1\u02e3\3\2\2\2\u02e2\u02de\3\2"+
		"\2\2\u02e3\u02e6\3\2\2\2\u02e4\u02e2\3\2\2\2\u02e4\u02e5\3\2\2\2\u02e5"+
		"\u02e8\3\2\2\2\u02e6\u02e4\3\2\2\2\u02e7\u02d9\3\2\2\2\u02e7\u02e8\3\2"+
		"\2\2\u02e8\67\3\2\2\2\u02e9\u02ea\7\'\2\2\u02ea\u02ef\5\66\34\2\u02eb"+
		"\u02ec\7\65\2\2\u02ec\u02ee\5\66\34\2\u02ed\u02eb\3\2\2\2\u02ee\u02f1"+
		"\3\2\2\2\u02ef\u02ed\3\2\2\2\u02ef\u02f0\3\2\2\2\u02f0\u02f2\3\2\2\2\u02f1"+
		"\u02ef\3\2\2\2\u02f2\u02f3\7\64\2\2\u02f3\u02f4\7\b\2\2\u02f49\3\2\2\2"+
		"\u02f5\u02f8\5\62\32\2\u02f6\u02f8\5\64\33\2\u02f7\u02f5\3\2\2\2\u02f7"+
		"\u02f6\3\2\2\2\u02f8;\3\2\2\2\u02f9\u02fb\5\2\2\2\u02fa\u02fc\58\35\2"+
		"\u02fb\u02fa\3\2\2\2\u02fb\u02fc\3\2\2\2\u02fc\u02fd\3\2\2\2\u02fd\u0303"+
		"\5\2\2\2\u02fe\u02ff\5\2\2\2\u02ff\u0300\5@!\2\u0300\u0302\3\2\2\2\u0301"+
		"\u02fe\3\2\2\2\u0302\u0305\3\2\2\2\u0303\u0301\3\2\2\2\u0303\u0304\3\2"+
		"\2\2\u0304\u0306\3\2\2\2\u0305\u0303\3\2\2\2\u0306\u030c\5\2\2\2\u0307"+
		"\u0308\5:\36\2\u0308\u0309\5\2\2\2\u0309\u030b\3\2\2\2\u030a\u0307\3\2"+
		"\2\2\u030b\u030e\3\2\2\2\u030c\u030a\3\2\2\2\u030c\u030d\3\2\2\2\u030d"+
		"\u030f\3\2\2\2\u030e\u030c\3\2\2\2\u030f\u0310\7\2\2\3\u0310=\3\2\2\2"+
		"\u0311\u0313\t\r\2\2\u0312\u0311\3\2\2\2\u0313\u0316\3\2\2\2\u0314\u0312"+
		"\3\2\2\2\u0314\u0315\3\2\2\2\u0315?\3\2\2\2\u0316\u0314\3\2\2\2\u0317"+
		"\u0318\7\63\2\2\u0318\u0319\5> \2\u0319\u031a\7C\2\2\u031aA\3\2\2\2YE"+
		"enqt\177\u0083\u0088\u008e\u0099\u00a4\u00af\u00ba\u00c4\u00c6\u00d5\u00da"+
		"\u00e3\u00f0\u00f5\u00fa\u0101\u0103\u0107\u0109\u0117\u011a\u0129\u012c"+
		"\u0131\u013e\u014b\u0150\u015c\u015f\u0171\u0178\u0182\u0188\u0197\u019f"+
		"\u01a5\u01a7\u01ad\u01b6\u01c0\u01cb\u01da\u01dc\u01e8\u01ee\u01f4\u01f9"+
		"\u01ff\u0203\u0215\u021b\u0221\u0227\u0234\u0244\u024a\u0253\u025b\u0261"+
		"\u0267\u027a\u0280\u028d\u0292\u02a2\u02a8\u02b5\u02bb\u02c1\u02c8\u02cb"+
		"\u02cf\u02d6\u02e4\u02e7\u02ef\u02f7\u02fb\u0303\u030c\u0314";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}