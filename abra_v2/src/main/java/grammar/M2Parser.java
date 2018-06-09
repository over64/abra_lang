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
			setState(270);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(268);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
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
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
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
						break;
					}
					} 
				}
				setState(272);
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
			setState(309);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(273);
				match(LB);
				setState(274);
				sp();
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(275);
					expression(0);
					setState(276);
					sp();
					setState(283);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(277);
						match(COMMA);
						setState(278);
						sp();
						setState(279);
						expression(0);
						}
						}
						setState(285);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(288);
				sp();
				setState(289);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(291);
				match(WITH);
				setState(292);
				sp();
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(293);
					expression(0);
					setState(294);
					sp();
					setState(301);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(295);
						match(COMMA);
						setState(296);
						sp();
						setState(297);
						expression(0);
						}
						}
						setState(303);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(306);
				sp();
				setState(307);
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
			setState(311);
			id();
			setState(312);
			sp();
			setState(313);
			match(CON);
			setState(314);
			sp();
			setState(315);
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
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(317);
				id();
				setState(318);
				sp();
				setState(319);
				match(DOT);
				setState(320);
				sp();
				}
			}

			setState(324);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(340);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(325);
				match(BRACKET_LEFT);
				setState(326);
				sp();
				setState(327);
				typeHint();
				setState(335);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(328);
					sp();
					setState(329);
					match(COMMA);
					setState(330);
					sp();
					setState(331);
					typeHint();
					}
					}
					setState(337);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(338);
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
			setState(342);
			match(LB);
			setState(355);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << WS) | (1L << NL) | (1L << COMMENT) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(343);
				sp();
				setState(344);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(352);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(345);
					sp();
					setState(346);
					match(COMMA);
					setState(347);
					sp();
					setState(348);
					((FnThContext)_localctx).typeHint = typeHint();
					((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
					}
					}
					setState(354);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(357);
			match(RB);
			setState(358);
			sp();
			setState(359);
			match(ARROW_RIGHT);
			setState(360);
			sp();
			setState(361);
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
			setState(363);
			match(LB);
			setState(364);
			sp();
			setState(365);
			fieldTh();
			setState(371); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(366);
				sp();
				setState(367);
				match(COMMA);
				setState(368);
				sp();
				setState(369);
				fieldTh();
				}
				}
				setState(373); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(375);
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
			setState(380);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(377);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(378);
				fnTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(379);
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
			setState(382);
			nonUnionTh();
			setState(388); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(383);
					sp();
					setState(384);
					match(VERT_LINE);
					setState(385);
					sp();
					setState(386);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(390); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitTypeHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeHintContext typeHint() throws RecognitionException {
		TypeHintContext _localctx = new TypeHintContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeHint);
		try {
			setState(396);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(392);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(393);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(394);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(395);
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
			setState(398);
			match(IS);
			setState(399);
			sp();
			setState(400);
			match(VarId);
			setState(401);
			sp();
			setState(402);
			match(CON);
			setState(403);
			sp();
			setState(404);
			typeHint();
			setState(405);
			sp();
			setState(406);
			match(DO);
			setState(407);
			sp();
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(408);
				blockBody();
				}
				}
				setState(413);
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
		enterRule(_localctx, 26, RULE_store);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			id();
			setState(425);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(419);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(415);
						match(NL);
						setState(417);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(416);
							match(WS);
							}
						}

						}
					}

					setState(421);
					match(DOT);
					setState(422);
					match(VarId);
					}
					} 
				}
				setState(427);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			setState(428);
			sp();
			setState(434);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(429);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(430);
				match(CON);
				setState(431);
				sp();
				setState(432);
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
			setState(436);
			sp();
			setState(437);
			match(EQ);
			setState(438);
			sp();
			setState(439);
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
		enterRule(_localctx, 28, RULE_ret);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(441);
			match(RETURN);
			setState(442);
			sp();
			setState(444);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(443);
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
		enterRule(_localctx, 30, RULE_while_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(446);
			match(WHILE);
			setState(447);
			sp();
			setState(448);
			((While_statContext)_localctx).cond = expression(0);
			setState(449);
			sp();
			setState(450);
			match(DO);
			setState(451);
			sp();
			setState(455);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(452);
				blockBody();
				}
				}
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(458);
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
		enterRule(_localctx, 32, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			id();
			setState(461);
			sp();
			setState(472);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(462);
				match(CON);
				setState(463);
				sp();
				setState(464);
				typeHint();
				setState(470);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(465);
					sp();
					setState(466);
					match(EQ);
					setState(467);
					sp();
					setState(468);
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
		enterRule(_localctx, 34, RULE_lambda);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			match(LAMBDA);
			setState(490);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(475);
				sp();
				setState(476);
				fnArg();
				setState(477);
				sp();
				setState(484);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(478);
					match(COMMA);
					setState(479);
					sp();
					setState(480);
					fnArg();
					}
					}
					setState(486);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(487);
				sp();
				setState(488);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(492);
			sp();
			setState(496);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(493);
					blockBody();
					}
					} 
				}
				setState(498);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(499);
			sp();
			setState(501);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(500);
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
		enterRule(_localctx, 36, RULE_blockBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(507);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(503);
				store();
				}
				break;
			case 2:
				{
				setState(504);
				while_stat();
				}
				break;
			case 3:
				{
				setState(505);
				expression(0);
				}
				break;
			case 4:
				{
				setState(506);
				ret();
				}
				break;
			}
			setState(509);
			sp();
			setState(511);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(510);
				match(SEMI);
				}
				break;
			}
			setState(513);
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
		enterRule(_localctx, 38, RULE_scalarType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			match(TYPE);
			setState(516);
			sp();
			setState(517);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(530);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(518);
				sp();
				setState(519);
				match(BRACKET_LEFT);
				setState(520);
				((ScalarTypeContext)_localctx).TypeId = match(TypeId);
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
				setState(525);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(521);
					match(COMMA);
					setState(522);
					((ScalarTypeContext)_localctx).TypeId = match(TypeId);
					((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
					}
					}
					setState(527);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(528);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(532);
			sp();
			setState(533);
			match(EQ);
			setState(534);
			sp();
			setState(536);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(535);
				match(REF);
				}
			}

			setState(538);
			sp();
			setState(539);
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
		enterRule(_localctx, 40, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(541);
				match(SELF);
				}
			}

			setState(544);
			sp();
			setState(545);
			match(VarId);
			setState(546);
			sp();
			setState(547);
			match(CON);
			setState(548);
			sp();
			setState(549);
			typeHint();
			setState(555);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(550);
				sp();
				setState(551);
				match(EQ);
				setState(552);
				sp();
				setState(553);
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
		enterRule(_localctx, 42, RULE_structType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
			match(TYPE);
			setState(558);
			sp();
			setState(559);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(572);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(560);
				sp();
				setState(561);
				match(BRACKET_LEFT);
				setState(562);
				((StructTypeContext)_localctx).TypeId = match(TypeId);
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(563);
					match(COMMA);
					setState(564);
					((StructTypeContext)_localctx).TypeId = match(TypeId);
					((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
					}
					}
					setState(569);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(570);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(574);
			sp();
			setState(575);
			match(EQ);
			setState(576);
			sp();
			setState(577);
			match(LB);
			setState(581);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(578);
					match(NL);
					}
					} 
				}
				setState(583);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			}
			setState(584);
			typeField();
			setState(595);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(585);
				match(COMMA);
				setState(589);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(586);
						match(NL);
						}
						} 
					}
					setState(591);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				}
				setState(592);
				typeField();
				}
				}
				setState(597);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(601);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(598);
				match(NL);
				}
				}
				setState(603);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(604);
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
		enterRule(_localctx, 44, RULE_unionType);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(606);
			match(TYPE);
			setState(607);
			sp();
			setState(608);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(609);
			sp();
			setState(620);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(610);
				match(BRACKET_LEFT);
				setState(611);
				((UnionTypeContext)_localctx).TypeId = match(TypeId);
				((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
				setState(616);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(612);
					match(COMMA);
					setState(613);
					((UnionTypeContext)_localctx).TypeId = match(TypeId);
					((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
					}
					}
					setState(618);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(619);
				match(BRACKET_RIGTH);
				}
			}

			setState(622);
			sp();
			setState(623);
			match(EQ);
			setState(624);
			sp();
			setState(625);
			scalarTh();
			setState(631); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(626);
					sp();
					setState(627);
					match(VERT_LINE);
					setState(628);
					sp();
					setState(629);
					scalarTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(633); 
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
		enterRule(_localctx, 46, RULE_type);
		try {
			setState(638);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(635);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(636);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(637);
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
		enterRule(_localctx, 48, RULE_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(640);
			match(DEF);
			setState(641);
			sp();
			setState(642);
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
			setState(643);
			sp();
			setState(654);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(644);
				match(BRACKET_LEFT);
				setState(645);
				match(TypeId);
				setState(650);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(646);
					match(COMMA);
					setState(647);
					match(TypeId);
					}
					}
					setState(652);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(653);
				match(BRACKET_RIGTH);
				}
			}

			setState(656);
			sp();
			setState(657);
			match(EQ);
			setState(658);
			sp();
			setState(673);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(659);
				fnArg();
				setState(660);
				sp();
				setState(667);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(661);
					match(COMMA);
					setState(662);
					sp();
					setState(663);
					fnArg();
					}
					}
					setState(669);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(670);
				sp();
				setState(671);
				match(DO);
				}
				break;
			}
			setState(675);
			sp();
			setState(686);
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
				setState(679);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(676);
					blockBody();
					}
					}
					setState(681);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(682);
				sp();
				setState(683);
				match(DOT);
				}
				}
				break;
			case LlBegin:
				{
				setState(685);
				llvm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(689);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(688);
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
		enterRule(_localctx, 50, RULE_importEntry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(691);
			sp();
			setState(693);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIV) {
				{
				setState(692);
				((ImportEntryContext)_localctx).abs = match(DIV);
				}
			}

			setState(695);
			match(VarId);
			setState(700);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV) {
				{
				{
				setState(696);
				match(DIV);
				setState(697);
				match(VarId);
				}
				}
				setState(702);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(717);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(703);
				sp();
				setState(704);
				match(WITH);
				setState(705);
				sp();
				setState(706);
				match(TypeId);
				setState(707);
				sp();
				setState(714);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(708);
					match(COMMA);
					setState(709);
					sp();
					setState(710);
					match(TypeId);
					}
					}
					setState(716);
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
		enterRule(_localctx, 52, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(719);
			match(IMPORT);
			setState(720);
			importEntry();
			setState(725);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(721);
				match(NL);
				setState(722);
				importEntry();
				}
				}
				setState(727);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(728);
			match(WS);
			setState(729);
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
		enterRule(_localctx, 54, RULE_level1);
		try {
			setState(733);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(731);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(732);
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
		enterRule(_localctx, 56, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(735);
			sp();
			setState(737);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(736);
				import_();
				}
			}

			setState(739);
			sp();
			setState(745);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(740);
					sp();
					setState(741);
					llvm();
					}
					} 
				}
				setState(747);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(748);
			sp();
			setState(754);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(749);
				level1();
				setState(750);
				sp();
				}
				}
				setState(756);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(757);
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
		enterRule(_localctx, 58, RULE_llvmBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(762);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (LLVM_NL - 62)) | (1L << (LLVM_WS - 62)) | (1L << (IrLine - 62)) | (1L << (LL_Dot - 62)))) != 0)) {
				{
				{
				setState(759);
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
				setState(764);
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
		enterRule(_localctx, 60, RULE_llvm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(765);
			match(LlBegin);
			setState(766);
			llvmBody();
			setState(767);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3D\u0304\4\2\t\2\4"+
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
		"\3\5\3\5\3\5\5\5\u0109\n\5\5\5\u010b\n\5\3\5\3\5\7\5\u010f\n\5\f\5\16"+
		"\5\u0112\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u011c\n\6\f\6\16\6\u011f"+
		"\13\6\5\6\u0121\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u012e"+
		"\n\6\f\6\16\6\u0131\13\6\5\6\u0133\n\6\3\6\3\6\3\6\5\6\u0138\n\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u0145\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u0150\n\b\f\b\16\b\u0153\13\b\3\b\3\b\5\b\u0157"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0161\n\t\f\t\16\t\u0164\13\t"+
		"\5\t\u0166\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\6\n\u0176\n\n\r\n\16\n\u0177\3\n\3\n\3\13\3\13\3\13\5\13\u017f\n\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\6\f\u0187\n\f\r\f\16\f\u0188\3\r\3\r\3\r\3\r\5"+
		"\r\u018f\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7"+
		"\16\u019c\n\16\f\16\16\16\u019f\13\16\3\17\3\17\3\17\5\17\u01a4\n\17\5"+
		"\17\u01a6\n\17\3\17\3\17\7\17\u01aa\n\17\f\17\16\17\u01ad\13\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u01b5\n\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\5\20\u01bf\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u01c8"+
		"\n\21\f\21\16\21\u01cb\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\5\22\u01d9\n\22\5\22\u01db\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\7\23\u01e5\n\23\f\23\16\23\u01e8\13\23\3\23\3\23"+
		"\3\23\5\23\u01ed\n\23\3\23\3\23\7\23\u01f1\n\23\f\23\16\23\u01f4\13\23"+
		"\3\23\3\23\5\23\u01f8\n\23\3\24\3\24\3\24\3\24\5\24\u01fe\n\24\3\24\3"+
		"\24\5\24\u0202\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\7\25\u020e\n\25\f\25\16\25\u0211\13\25\3\25\3\25\5\25\u0215\n\25\3\25"+
		"\3\25\3\25\3\25\5\25\u021b\n\25\3\25\3\25\3\25\3\26\5\26\u0221\n\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u022e\n\26"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0238\n\27\f\27\16\27\u023b"+
		"\13\27\3\27\3\27\5\27\u023f\n\27\3\27\3\27\3\27\3\27\3\27\7\27\u0246\n"+
		"\27\f\27\16\27\u0249\13\27\3\27\3\27\3\27\7\27\u024e\n\27\f\27\16\27\u0251"+
		"\13\27\3\27\7\27\u0254\n\27\f\27\16\27\u0257\13\27\3\27\7\27\u025a\n\27"+
		"\f\27\16\27\u025d\13\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\7\30\u0269\n\30\f\30\16\30\u026c\13\30\3\30\5\30\u026f\n\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\6\30\u027a\n\30\r\30\16\30\u027b"+
		"\3\31\3\31\3\31\5\31\u0281\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\7\32\u028b\n\32\f\32\16\32\u028e\13\32\3\32\5\32\u0291\n\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u029c\n\32\f\32\16\32\u029f\13"+
		"\32\3\32\3\32\3\32\5\32\u02a4\n\32\3\32\3\32\7\32\u02a8\n\32\f\32\16\32"+
		"\u02ab\13\32\3\32\3\32\3\32\3\32\5\32\u02b1\n\32\3\32\5\32\u02b4\n\32"+
		"\3\33\3\33\5\33\u02b8\n\33\3\33\3\33\3\33\7\33\u02bd\n\33\f\33\16\33\u02c0"+
		"\13\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u02cb\n\33\f"+
		"\33\16\33\u02ce\13\33\5\33\u02d0\n\33\3\34\3\34\3\34\3\34\7\34\u02d6\n"+
		"\34\f\34\16\34\u02d9\13\34\3\34\3\34\3\34\3\35\3\35\5\35\u02e0\n\35\3"+
		"\36\3\36\5\36\u02e4\n\36\3\36\3\36\3\36\3\36\7\36\u02ea\n\36\f\36\16\36"+
		"\u02ed\13\36\3\36\3\36\3\36\3\36\7\36\u02f3\n\36\f\36\16\36\u02f6\13\36"+
		"\3\36\3\36\3\37\7\37\u02fb\n\37\f\37\16\37\u02fe\13\37\3 \3 \3 \3 \3 "+
		"\2\3\b!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:"+
		"<>\2\16\3\2\64\66\3\2\67<\4\2##==\3\2\64\65\3\2\5\6\4\2\3\4==\3\2\f\17"+
		"\3\2\21\22\3\2\32\33\6\2\3\6\f\17\21\22==\7\2\3\7\f\17\21\22\32\33==\4"+
		"\2@BDD\2\u034f\2C\3\2\2\2\4F\3\2\2\2\6H\3\2\2\2\b\u008f\3\2\2\2\n\u0137"+
		"\3\2\2\2\f\u0139\3\2\2\2\16\u0144\3\2\2\2\20\u0158\3\2\2\2\22\u016d\3"+
		"\2\2\2\24\u017e\3\2\2\2\26\u0180\3\2\2\2\30\u018e\3\2\2\2\32\u0190\3\2"+
		"\2\2\34\u01a0\3\2\2\2\36\u01bb\3\2\2\2 \u01c0\3\2\2\2\"\u01ce\3\2\2\2"+
		"$\u01dc\3\2\2\2&\u01fd\3\2\2\2(\u0205\3\2\2\2*\u0220\3\2\2\2,\u022f\3"+
		"\2\2\2.\u0260\3\2\2\2\60\u0280\3\2\2\2\62\u0282\3\2\2\2\64\u02b5\3\2\2"+
		"\2\66\u02d1\3\2\2\28\u02df\3\2\2\2:\u02e1\3\2\2\2<\u02fc\3\2\2\2>\u02ff"+
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
		"\u008fU\3\2\2\2\u008fV\3\2\2\2\u008fZ\3\2\2\2\u008fx\3\2\2\2\u0090\u0110"+
		"\3\2\2\2\u0091\u0095\f\t\2\2\u0092\u0094\7\64\2\2\u0093\u0092\3\2\2\2"+
		"\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\u0098"+
		"\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u0099\t\6\2\2\u0099\u009a\5\2\2\2\u009a"+
		"\u009b\5\b\5\n\u009b\u010f\3\2\2\2\u009c\u00a0\f\b\2\2\u009d\u009f\7\64"+
		"\2\2\u009e\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\t\7"+
		"\2\2\u00a4\u00a5\5\2\2\2\u00a5\u00a6\5\b\5\t\u00a6\u010f\3\2\2\2\u00a7"+
		"\u00ab\f\7\2\2\u00a8\u00aa\7\64\2\2\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3"+
		"\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad"+
		"\u00ab\3\2\2\2\u00ae\u00af\t\b\2\2\u00af\u00b0\5\2\2\2\u00b0\u00b1\5\b"+
		"\5\b\u00b1\u010f\3\2\2\2\u00b2\u00b6\f\6\2\2\u00b3\u00b5\7\64\2\2\u00b4"+
		"\u00b3\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\t\t\2\2\u00ba"+
		"\u00bb\5\2\2\2\u00bb\u00bc\5\b\5\7\u00bc\u010f\3\2\2\2\u00bd\u00c1\f\5"+
		"\2\2\u00be\u00c0\7\64\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1"+
		"\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00c1\3\2"+
		"\2\2\u00c4\u00c5\t\n\2\2\u00c5\u00c6\5\2\2\2\u00c6\u00c7\5\b\5\6\u00c7"+
		"\u010f\3\2\2\2\u00c8\u00cd\f\16\2\2\u00c9\u00cb\7\65\2\2\u00ca\u00cc\7"+
		"\64\2\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00c9\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d0\7\b"+
		"\2\2\u00d0\u00e1\t\13\2\2\u00d1\u00d2\5\2\2\2\u00d2\u00d3\7\61\2\2\u00d3"+
		"\u00d4\5\2\2\2\u00d4\u00dc\5\30\r\2\u00d5\u00d6\5\2\2\2\u00d6\u00d7\7"+
		"\13\2\2\u00d7\u00d8\5\2\2\2\u00d8\u00d9\5\30\r\2\u00d9\u00db\3\2\2\2\u00da"+
		"\u00d5\3\2\2\2\u00db\u00de\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2"+
		"\2\2\u00dd\u00df\3\2\2\2\u00de\u00dc\3\2\2\2\u00df\u00e0\7\62\2\2\u00e0"+
		"\u00e2\3\2\2\2\u00e1\u00d1\3\2\2\2\u00e1\u00e2\3\2\2\2\u00e2\u00e3\3\2"+
		"\2\2\u00e3\u00e4\5\2\2\2\u00e4\u00e5\5\n\6\2\u00e5\u010f\3\2\2\2\u00e6"+
		"\u00ea\f\r\2\2\u00e7\u00e9\7\64\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3"+
		"\2\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00fc\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ed\u00ee\7\61\2\2\u00ee\u00ef\5\2\2\2\u00ef\u00f7\5"+
		"\30\r\2\u00f0\u00f1\5\2\2\2\u00f1\u00f2\7\13\2\2\u00f2\u00f3\5\2\2\2\u00f3"+
		"\u00f4\5\30\r\2\u00f4\u00f6\3\2\2\2\u00f5\u00f0\3\2\2\2\u00f6\u00f9\3"+
		"\2\2\2\u00f7\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fa\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00fa\u00fb\7\62\2\2\u00fb\u00fd\3\2\2\2\u00fc\u00ed\3"+
		"\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u0101\3\2\2\2\u00fe\u0100\7\64\2\2\u00ff"+
		"\u00fe\3\2\2\2\u0100\u0103\3\2\2\2\u0101\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0104\3\2\2\2\u0103\u0101\3\2\2\2\u0104\u010f\5\n\6\2\u0105"+
		"\u010a\f\f\2\2\u0106\u0108\7\65\2\2\u0107\u0109\7\64\2\2\u0108\u0107\3"+
		"\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u0106\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010d\7\b\2\2\u010d\u010f\7="+
		"\2\2\u010e\u0091\3\2\2\2\u010e\u009c\3\2\2\2\u010e\u00a7\3\2\2\2\u010e"+
		"\u00b2\3\2\2\2\u010e\u00bd\3\2\2\2\u010e\u00c8\3\2\2\2\u010e\u00e6\3\2"+
		"\2\2\u010e\u0105\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110"+
		"\u0111\3\2\2\2\u0111\t\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0114\7\n\2\2"+
		"\u0114\u0120\5\2\2\2\u0115\u0116\5\b\5\2\u0116\u011d\5\2\2\2\u0117\u0118"+
		"\7\13\2\2\u0118\u0119\5\2\2\2\u0119\u011a\5\b\5\2\u011a\u011c\3\2\2\2"+
		"\u011b\u0117\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e"+
		"\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0115\3\2\2\2\u0120"+
		"\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\5\2\2\2\u0123\u0124\7\t"+
		"\2\2\u0124\u0138\3\2\2\2\u0125\u0126\7(\2\2\u0126\u0132\5\2\2\2\u0127"+
		"\u0128\5\b\5\2\u0128\u012f\5\2\2\2\u0129\u012a\7\13\2\2\u012a\u012b\5"+
		"\2\2\2\u012b\u012c\5\b\5\2\u012c\u012e\3\2\2\2\u012d\u0129\3\2\2\2\u012e"+
		"\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0133\3\2"+
		"\2\2\u0131\u012f\3\2\2\2\u0132\u0127\3\2\2\2\u0132\u0133\3\2\2\2\u0133"+
		"\u0134\3\2\2\2\u0134\u0135\5\2\2\2\u0135\u0136\7\b\2\2\u0136\u0138\3\2"+
		"\2\2\u0137\u0113\3\2\2\2\u0137\u0125\3\2\2\2\u0138\13\3\2\2\2\u0139\u013a"+
		"\5\6\4\2\u013a\u013b\5\2\2\2\u013b\u013c\7\37\2\2\u013c\u013d\5\2\2\2"+
		"\u013d\u013e\5\30\r\2\u013e\r\3\2\2\2\u013f\u0140\5\6\4\2\u0140\u0141"+
		"\5\2\2\2\u0141\u0142\7\b\2\2\u0142\u0143\5\2\2\2\u0143\u0145\3\2\2\2\u0144"+
		"\u013f\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0156\7>"+
		"\2\2\u0147\u0148\7\61\2\2\u0148\u0149\5\2\2\2\u0149\u0151\5\30\r\2\u014a"+
		"\u014b\5\2\2\2\u014b\u014c\7\13\2\2\u014c\u014d\5\2\2\2\u014d\u014e\5"+
		"\30\r\2\u014e\u0150\3\2\2\2\u014f\u014a\3\2\2\2\u0150\u0153\3\2\2\2\u0151"+
		"\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152\u0154\3\2\2\2\u0153\u0151\3\2"+
		"\2\2\u0154\u0155\7\62\2\2\u0155\u0157\3\2\2\2\u0156\u0147\3\2\2\2\u0156"+
		"\u0157\3\2\2\2\u0157\17\3\2\2\2\u0158\u0165\7\n\2\2\u0159\u015a\5\2\2"+
		"\2\u015a\u0162\5\30\r\2\u015b\u015c\5\2\2\2\u015c\u015d\7\13\2\2\u015d"+
		"\u015e\5\2\2\2\u015e\u015f\5\30\r\2\u015f\u0161\3\2\2\2\u0160\u015b\3"+
		"\2\2\2\u0161\u0164\3\2\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163"+
		"\u0166\3\2\2\2\u0164\u0162\3\2\2\2\u0165\u0159\3\2\2\2\u0165\u0166\3\2"+
		"\2\2\u0166\u0167\3\2\2\2\u0167\u0168\7\t\2\2\u0168\u0169\5\2\2\2\u0169"+
		"\u016a\7 \2\2\u016a\u016b\5\2\2\2\u016b\u016c\5\30\r\2\u016c\21\3\2\2"+
		"\2\u016d\u016e\7\n\2\2\u016e\u016f\5\2\2\2\u016f\u0175\5\f\7\2\u0170\u0171"+
		"\5\2\2\2\u0171\u0172\7\13\2\2\u0172\u0173\5\2\2\2\u0173\u0174\5\f\7\2"+
		"\u0174\u0176\3\2\2\2\u0175\u0170\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0175"+
		"\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017a\7\t\2\2\u017a"+
		"\23\3\2\2\2\u017b\u017f\5\16\b\2\u017c\u017f\5\20\t\2\u017d\u017f\5\22"+
		"\n\2\u017e\u017b\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017d\3\2\2\2\u017f"+
		"\25\3\2\2\2\u0180\u0186\5\24\13\2\u0181\u0182\5\2\2\2\u0182\u0183\7\60"+
		"\2\2\u0183\u0184\5\2\2\2\u0184\u0185\5\24\13\2\u0185\u0187\3\2\2\2\u0186"+
		"\u0181\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0186\3\2\2\2\u0188\u0189\3\2"+
		"\2\2\u0189\27\3\2\2\2\u018a\u018f\5\16\b\2\u018b\u018f\5\22\n\2\u018c"+
		"\u018f\5\20\t\2\u018d\u018f\5\26\f\2\u018e\u018a\3\2\2\2\u018e\u018b\3"+
		"\2\2\2\u018e\u018c\3\2\2\2\u018e\u018d\3\2\2\2\u018f\31\3\2\2\2\u0190"+
		"\u0191\7,\2\2\u0191\u0192\5\2\2\2\u0192\u0193\7=\2\2\u0193\u0194\5\2\2"+
		"\2\u0194\u0195\7\37\2\2\u0195\u0196\5\2\2\2\u0196\u0197\5\30\r\2\u0197"+
		"\u0198\5\2\2\2\u0198\u0199\7\25\2\2\u0199\u019d\5\2\2\2\u019a\u019c\5"+
		"&\24\2\u019b\u019a\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d"+
		"\u019e\3\2\2\2\u019e\33\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01ab\5\6\4"+
		"\2\u01a1\u01a3\7\65\2\2\u01a2\u01a4\7\64\2\2\u01a3\u01a2\3\2\2\2\u01a3"+
		"\u01a4\3\2\2\2\u01a4\u01a6\3\2\2\2\u01a5\u01a1\3\2\2\2\u01a5\u01a6\3\2"+
		"\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01a8\7\b\2\2\u01a8\u01aa\7=\2\2\u01a9"+
		"\u01a5\3\2\2\2\u01aa\u01ad\3\2\2\2\u01ab\u01a9\3\2\2\2\u01ab\u01ac\3\2"+
		"\2\2\u01ac\u01ae\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ae\u01b4\5\2\2\2\u01af"+
		"\u01b5\5\n\6\2\u01b0\u01b1\7\37\2\2\u01b1\u01b2\5\2\2\2\u01b2\u01b3\5"+
		"\30\r\2\u01b3\u01b5\3\2\2\2\u01b4\u01af\3\2\2\2\u01b4\u01b0\3\2\2\2\u01b4"+
		"\u01b5\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b7\5\2\2\2\u01b7\u01b8\7\20"+
		"\2\2\u01b8\u01b9\5\2\2\2\u01b9\u01ba\5\b\5\2\u01ba\35\3\2\2\2\u01bb\u01bc"+
		"\7+\2\2\u01bc\u01be\5\2\2\2\u01bd\u01bf\5\b\5\2\u01be\u01bd\3\2\2\2\u01be"+
		"\u01bf\3\2\2\2\u01bf\37\3\2\2\2\u01c0\u01c1\7\34\2\2\u01c1\u01c2\5\2\2"+
		"\2\u01c2\u01c3\5\b\5\2\u01c3\u01c4\5\2\2\2\u01c4\u01c5\7\25\2\2\u01c5"+
		"\u01c9\5\2\2\2\u01c6\u01c8\5&\24\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb\3\2"+
		"\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cc\3\2\2\2\u01cb"+
		"\u01c9\3\2\2\2\u01cc\u01cd\7\b\2\2\u01cd!\3\2\2\2\u01ce\u01cf\5\6\4\2"+
		"\u01cf\u01da\5\2\2\2\u01d0\u01d1\7\37\2\2\u01d1\u01d2\5\2\2\2\u01d2\u01d8"+
		"\5\30\r\2\u01d3\u01d4\5\2\2\2\u01d4\u01d5\7\20\2\2\u01d5\u01d6\5\2\2\2"+
		"\u01d6\u01d7\5\b\5\2\u01d7\u01d9\3\2\2\2\u01d8\u01d3\3\2\2\2\u01d8\u01d9"+
		"\3\2\2\2\u01d9\u01db\3\2\2\2\u01da\u01d0\3\2\2\2\u01da\u01db\3\2\2\2\u01db"+
		"#\3\2\2\2\u01dc\u01ec\7&\2\2\u01dd\u01de\5\2\2\2\u01de\u01df\5\"\22\2"+
		"\u01df\u01e6\5\2\2\2\u01e0\u01e1\7\13\2\2\u01e1\u01e2\5\2\2\2\u01e2\u01e3"+
		"\5\"\22\2\u01e3\u01e5\3\2\2\2\u01e4\u01e0\3\2\2\2\u01e5\u01e8\3\2\2\2"+
		"\u01e6\u01e4\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e9\3\2\2\2\u01e8\u01e6"+
		"\3\2\2\2\u01e9\u01ea\5\2\2\2\u01ea\u01eb\7 \2\2\u01eb\u01ed\3\2\2\2\u01ec"+
		"\u01dd\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f2\5\2"+
		"\2\2\u01ef\u01f1\5&\24\2\u01f0\u01ef\3\2\2\2\u01f1\u01f4\3\2\2\2\u01f2"+
		"\u01f0\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f5\3\2\2\2\u01f4\u01f2\3\2"+
		"\2\2\u01f5\u01f7\5\2\2\2\u01f6\u01f8\7\b\2\2\u01f7\u01f6\3\2\2\2\u01f7"+
		"\u01f8\3\2\2\2\u01f8%\3\2\2\2\u01f9\u01fe\5\34\17\2\u01fa\u01fe\5 \21"+
		"\2\u01fb\u01fe\5\b\5\2\u01fc\u01fe\5\36\20\2\u01fd\u01f9\3\2\2\2\u01fd"+
		"\u01fa\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fd\u01fc\3\2\2\2\u01fe\u01ff\3\2"+
		"\2\2\u01ff\u0201\5\2\2\2\u0200\u0202\7\23\2\2\u0201\u0200\3\2\2\2\u0201"+
		"\u0202\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0204\5\2\2\2\u0204\'\3\2\2\2"+
		"\u0205\u0206\7!\2\2\u0206\u0207\5\2\2\2\u0207\u0214\7>\2\2\u0208\u0209"+
		"\5\2\2\2\u0209\u020a\7\61\2\2\u020a\u020f\7>\2\2\u020b\u020c\7\13\2\2"+
		"\u020c\u020e\7>\2\2\u020d\u020b\3\2\2\2\u020e\u0211\3\2\2\2\u020f\u020d"+
		"\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0212\3\2\2\2\u0211\u020f\3\2\2\2\u0212"+
		"\u0213\7\62\2\2\u0213\u0215\3\2\2\2\u0214\u0208\3\2\2\2\u0214\u0215\3"+
		"\2\2\2\u0215\u0216\3\2\2\2\u0216\u0217\5\2\2\2\u0217\u0218\7\20\2\2\u0218"+
		"\u021a\5\2\2\2\u0219\u021b\7.\2\2\u021a\u0219\3\2\2\2\u021a\u021b\3\2"+
		"\2\2\u021b\u021c\3\2\2\2\u021c\u021d\5\2\2\2\u021d\u021e\5> \2\u021e)"+
		"\3\2\2\2\u021f\u0221\7#\2\2\u0220\u021f\3\2\2\2\u0220\u0221\3\2\2\2\u0221"+
		"\u0222\3\2\2\2\u0222\u0223\5\2\2\2\u0223\u0224\7=\2\2\u0224\u0225\5\2"+
		"\2\2\u0225\u0226\7\37\2\2\u0226\u0227\5\2\2\2\u0227\u022d\5\30\r\2\u0228"+
		"\u0229\5\2\2\2\u0229\u022a\7\20\2\2\u022a\u022b\5\2\2\2\u022b\u022c\5"+
		"\b\5\2\u022c\u022e\3\2\2\2\u022d\u0228\3\2\2\2\u022d\u022e\3\2\2\2\u022e"+
		"+\3\2\2\2\u022f\u0230\7!\2\2\u0230\u0231\5\2\2\2\u0231\u023e\7>\2\2\u0232"+
		"\u0233\5\2\2\2\u0233\u0234\7\61\2\2\u0234\u0239\7>\2\2\u0235\u0236\7\13"+
		"\2\2\u0236\u0238\7>\2\2\u0237\u0235\3\2\2\2\u0238\u023b\3\2\2\2\u0239"+
		"\u0237\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u023c\3\2\2\2\u023b\u0239\3\2"+
		"\2\2\u023c\u023d\7\62\2\2\u023d\u023f\3\2\2\2\u023e\u0232\3\2\2\2\u023e"+
		"\u023f\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241\5\2\2\2\u0241\u0242\7\20"+
		"\2\2\u0242\u0243\5\2\2\2\u0243\u0247\7\n\2\2\u0244\u0246\7\65\2\2\u0245"+
		"\u0244\3\2\2\2\u0246\u0249\3\2\2\2\u0247\u0245\3\2\2\2\u0247\u0248\3\2"+
		"\2\2\u0248\u024a\3\2\2\2\u0249\u0247\3\2\2\2\u024a\u0255\5*\26\2\u024b"+
		"\u024f\7\13\2\2\u024c\u024e\7\65\2\2\u024d\u024c\3\2\2\2\u024e\u0251\3"+
		"\2\2\2\u024f\u024d\3\2\2\2\u024f\u0250\3\2\2\2\u0250\u0252\3\2\2\2\u0251"+
		"\u024f\3\2\2\2\u0252\u0254\5*\26\2\u0253\u024b\3\2\2\2\u0254\u0257\3\2"+
		"\2\2\u0255\u0253\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u025b\3\2\2\2\u0257"+
		"\u0255\3\2\2\2\u0258\u025a\7\65\2\2\u0259\u0258\3\2\2\2\u025a\u025d\3"+
		"\2\2\2\u025b\u0259\3\2\2\2\u025b\u025c\3\2\2\2\u025c\u025e\3\2\2\2\u025d"+
		"\u025b\3\2\2\2\u025e\u025f\7\t\2\2\u025f-\3\2\2\2\u0260\u0261\7!\2\2\u0261"+
		"\u0262\5\2\2\2\u0262\u0263\7>\2\2\u0263\u026e\5\2\2\2\u0264\u0265\7\61"+
		"\2\2\u0265\u026a\7>\2\2\u0266\u0267\7\13\2\2\u0267\u0269\7>\2\2\u0268"+
		"\u0266\3\2\2\2\u0269\u026c\3\2\2\2\u026a\u0268\3\2\2\2\u026a\u026b\3\2"+
		"\2\2\u026b\u026d\3\2\2\2\u026c\u026a\3\2\2\2\u026d\u026f\7\62\2\2\u026e"+
		"\u0264\3\2\2\2\u026e\u026f\3\2\2\2\u026f\u0270\3\2\2\2\u0270\u0271\5\2"+
		"\2\2\u0271\u0272\7\20\2\2\u0272\u0273\5\2\2\2\u0273\u0279\5\16\b\2\u0274"+
		"\u0275\5\2\2\2\u0275\u0276\7\60\2\2\u0276\u0277\5\2\2\2\u0277\u0278\5"+
		"\16\b\2\u0278\u027a\3\2\2\2\u0279\u0274\3\2\2\2\u027a\u027b\3\2\2\2\u027b"+
		"\u0279\3\2\2\2\u027b\u027c\3\2\2\2\u027c/\3\2\2\2\u027d\u0281\5(\25\2"+
		"\u027e\u0281\5,\27\2\u027f\u0281\5.\30\2\u0280\u027d\3\2\2\2\u0280\u027e"+
		"\3\2\2\2\u0280\u027f\3\2\2\2\u0281\61\3\2\2\2\u0282\u0283\7%\2\2\u0283"+
		"\u0284\5\2\2\2\u0284\u0285\t\f\2\2\u0285\u0290\5\2\2\2\u0286\u0287\7\61"+
		"\2\2\u0287\u028c\7>\2\2\u0288\u0289\7\13\2\2\u0289\u028b\7>\2\2\u028a"+
		"\u0288\3\2\2\2\u028b\u028e\3\2\2\2\u028c\u028a\3\2\2\2\u028c\u028d\3\2"+
		"\2\2\u028d\u028f\3\2\2\2\u028e\u028c\3\2\2\2\u028f\u0291\7\62\2\2\u0290"+
		"\u0286\3\2\2\2\u0290\u0291\3\2\2\2\u0291\u0292\3\2\2\2\u0292\u0293\5\2"+
		"\2\2\u0293\u0294\7\20\2\2\u0294\u02a3\5\2\2\2\u0295\u0296\5\"\22\2\u0296"+
		"\u029d\5\2\2\2\u0297\u0298\7\13\2\2\u0298\u0299\5\2\2\2\u0299\u029a\5"+
		"\"\22\2\u029a\u029c\3\2\2\2\u029b\u0297\3\2\2\2\u029c\u029f\3\2\2\2\u029d"+
		"\u029b\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u02a0\3\2\2\2\u029f\u029d\3\2"+
		"\2\2\u02a0\u02a1\5\2\2\2\u02a1\u02a2\7\25\2\2\u02a2\u02a4\3\2\2\2\u02a3"+
		"\u0295\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4\u02a5\3\2\2\2\u02a5\u02b0\5\2"+
		"\2\2\u02a6\u02a8\5&\24\2\u02a7\u02a6\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9"+
		"\u02a7\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa\u02ac\3\2\2\2\u02ab\u02a9\3\2"+
		"\2\2\u02ac\u02ad\5\2\2\2\u02ad\u02ae\7\b\2\2\u02ae\u02b1\3\2\2\2\u02af"+
		"\u02b1\5> \2\u02b0\u02a9\3\2\2\2\u02b0\u02af\3\2\2\2\u02b1\u02b3\3\2\2"+
		"\2\u02b2\u02b4\5\30\r\2\u02b3\u02b2\3\2\2\2\u02b3\u02b4\3\2\2\2\u02b4"+
		"\63\3\2\2\2\u02b5\u02b7\5\2\2\2\u02b6\u02b8\7\6\2\2\u02b7\u02b6\3\2\2"+
		"\2\u02b7\u02b8\3\2\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02be\7=\2\2\u02ba\u02bb"+
		"\7\6\2\2\u02bb\u02bd\7=\2\2\u02bc\u02ba\3\2\2\2\u02bd\u02c0\3\2\2\2\u02be"+
		"\u02bc\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf\u02cf\3\2\2\2\u02c0\u02be\3\2"+
		"\2\2\u02c1\u02c2\5\2\2\2\u02c2\u02c3\7(\2\2\u02c3\u02c4\5\2\2\2\u02c4"+
		"\u02c5\7>\2\2\u02c5\u02cc\5\2\2\2\u02c6\u02c7\7\13\2\2\u02c7\u02c8\5\2"+
		"\2\2\u02c8\u02c9\7>\2\2\u02c9\u02cb\3\2\2\2\u02ca\u02c6\3\2\2\2\u02cb"+
		"\u02ce\3\2\2\2\u02cc\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u02d0\3\2"+
		"\2\2\u02ce\u02cc\3\2\2\2\u02cf\u02c1\3\2\2\2\u02cf\u02d0\3\2\2\2\u02d0"+
		"\65\3\2\2\2\u02d1\u02d2\7\'\2\2\u02d2\u02d7\5\64\33\2\u02d3\u02d4\7\65"+
		"\2\2\u02d4\u02d6\5\64\33\2\u02d5\u02d3\3\2\2\2\u02d6\u02d9\3\2\2\2\u02d7"+
		"\u02d5\3\2\2\2\u02d7\u02d8\3\2\2\2\u02d8\u02da\3\2\2\2\u02d9\u02d7\3\2"+
		"\2\2\u02da\u02db\7\64\2\2\u02db\u02dc\7\b\2\2\u02dc\67\3\2\2\2\u02dd\u02e0"+
		"\5\60\31\2\u02de\u02e0\5\62\32\2\u02df\u02dd\3\2\2\2\u02df\u02de\3\2\2"+
		"\2\u02e09\3\2\2\2\u02e1\u02e3\5\2\2\2\u02e2\u02e4\5\66\34\2\u02e3\u02e2"+
		"\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02e5\3\2\2\2\u02e5\u02eb\5\2\2\2\u02e6"+
		"\u02e7\5\2\2\2\u02e7\u02e8\5> \2\u02e8\u02ea\3\2\2\2\u02e9\u02e6\3\2\2"+
		"\2\u02ea\u02ed\3\2\2\2\u02eb\u02e9\3\2\2\2\u02eb\u02ec\3\2\2\2\u02ec\u02ee"+
		"\3\2\2\2\u02ed\u02eb\3\2\2\2\u02ee\u02f4\5\2\2\2\u02ef\u02f0\58\35\2\u02f0"+
		"\u02f1\5\2\2\2\u02f1\u02f3\3\2\2\2\u02f2\u02ef\3\2\2\2\u02f3\u02f6\3\2"+
		"\2\2\u02f4\u02f2\3\2\2\2\u02f4\u02f5\3\2\2\2\u02f5\u02f7\3\2\2\2\u02f6"+
		"\u02f4\3\2\2\2\u02f7\u02f8\7\2\2\3\u02f8;\3\2\2\2\u02f9\u02fb\t\r\2\2"+
		"\u02fa\u02f9\3\2\2\2\u02fb\u02fe\3\2\2\2\u02fc\u02fa\3\2\2\2\u02fc\u02fd"+
		"\3\2\2\2\u02fd=\3\2\2\2\u02fe\u02fc\3\2\2\2\u02ff\u0300\7\63\2\2\u0300"+
		"\u0301\5<\37\2\u0301\u0302\7C\2\2\u0302?\3\2\2\2YCclot\177\u0087\u008a"+
		"\u008f\u0095\u00a0\u00ab\u00b6\u00c1\u00cb\u00cd\u00dc\u00e1\u00ea\u00f7"+
		"\u00fc\u0101\u0108\u010a\u010e\u0110\u011d\u0120\u012f\u0132\u0137\u0144"+
		"\u0151\u0156\u0162\u0165\u0177\u017e\u0188\u018e\u019d\u01a3\u01a5\u01ab"+
		"\u01b4\u01be\u01c9\u01d8\u01da\u01e6\u01ec\u01f2\u01f7\u01fd\u0201\u020f"+
		"\u0214\u021a\u0220\u022d\u0239\u023e\u0247\u024f\u0255\u025b\u026a\u026e"+
		"\u027b\u0280\u028c\u0290\u029d\u02a3\u02a9\u02b0\u02b3\u02b7\u02be\u02cc"+
		"\u02cf\u02d7\u02df\u02e3\u02eb\u02f4\u02fc";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}