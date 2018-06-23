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
			setState(139);
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
				if (_la==WS || _la==NL) {
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
				match(DOT);
				}
				break;
			case 9:
				{
				_localctx = new ExprWnenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(116);
				match(WHEN);
				setState(117);
				sp();
				setState(118);
				((ExprWnenContext)_localctx).expr = expression(0);
				setState(119);
				sp();
				setState(121); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(120);
					is();
					}
					}
					setState(123); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==IS );
				setState(125);
				sp();
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(126);
					match(ELSE);
					setState(127);
					sp();
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
						{
						{
						setState(128);
						((ExprWnenContext)_localctx).blockBody = blockBody();
						((ExprWnenContext)_localctx).elseStat.add(((ExprWnenContext)_localctx).blockBody);
						}
						}
						setState(133);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(136);
				_la = _input.LA(1);
				if ( !(_la==WS || _la==NL) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(137);
				match(DOT);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(268);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(266);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(141);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(145);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(142);
							match(WS);
							}
							}
							setState(147);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(148);
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
						setState(149);
						sp();
						setState(150);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(152);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(156);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(153);
							match(WS);
							}
							}
							setState(158);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(159);
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
						setState(160);
						sp();
						setState(161);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(163);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(167);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(164);
							match(WS);
							}
							}
							setState(169);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(170);
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
						setState(171);
						sp();
						setState(172);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(174);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(178);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(175);
							match(WS);
							}
							}
							setState(180);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(181);
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
						setState(182);
						sp();
						setState(183);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(185);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(189);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(186);
							match(WS);
							}
							}
							setState(191);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(192);
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
						setState(193);
						sp();
						setState(194);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(196);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(201);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(197);
							match(NL);
							setState(199);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(198);
								match(WS);
								}
							}

							}
						}

						setState(203);
						match(DOT);
						setState(204);
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
						setState(221);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
						case 1:
							{
							setState(205);
							sp();
							setState(206);
							match(BRACKET_LEFT);
							setState(207);
							sp();
							setState(208);
							typeHint();
							setState(216);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(209);
								sp();
								setState(210);
								match(COMMA);
								setState(211);
								sp();
								setState(212);
								typeHint();
								}
								}
								setState(218);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(219);
							match(BRACKET_RIGTH);
							}
							break;
						}
						setState(223);
						sp();
						setState(224);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(226);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(230);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(227);
								match(WS);
								}
								} 
							}
							setState(232);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
						}
						setState(248);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==BRACKET_LEFT) {
							{
							setState(233);
							match(BRACKET_LEFT);
							setState(234);
							sp();
							setState(235);
							typeHint();
							setState(243);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
								{
								{
								setState(236);
								sp();
								setState(237);
								match(COMMA);
								setState(238);
								sp();
								setState(239);
								typeHint();
								}
								}
								setState(245);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(246);
							match(BRACKET_RIGTH);
							}
						}

						setState(253);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==WS) {
							{
							{
							setState(250);
							match(WS);
							}
							}
							setState(255);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(256);
						tuple();
						}
						break;
					case 8:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(257);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(262);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==NL) {
							{
							setState(258);
							match(NL);
							setState(260);
							_errHandler.sync(this);
							_la = _input.LA(1);
							if (_la==WS) {
								{
								setState(259);
								match(WS);
								}
							}

							}
						}

						setState(264);
						match(DOT);
						setState(265);
						((ExprPropContext)_localctx).VarId = match(VarId);
						((ExprPropContext)_localctx).op.add(((ExprPropContext)_localctx).VarId);
						}
						break;
					}
					} 
				}
				setState(270);
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
			setState(308);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				match(LB);
				setState(272);
				sp();
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(273);
					expression(0);
					setState(274);
					sp();
					setState(282);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(275);
						match(COMMA);
						setState(276);
						sp();
						setState(277);
						expression(0);
						setState(278);
						sp();
						}
						}
						setState(284);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(287);
				sp();
				setState(288);
				match(RB);
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 2);
				{
				setState(290);
				match(WITH);
				setState(291);
				sp();
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					setState(292);
					expression(0);
					setState(293);
					sp();
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(294);
						match(COMMA);
						setState(295);
						sp();
						setState(296);
						expression(0);
						}
						}
						setState(302);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(305);
				sp();
				setState(306);
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
			setState(310);
			id();
			setState(311);
			sp();
			setState(312);
			match(CON);
			setState(313);
			sp();
			setState(314);
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
			setState(321);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(316);
				id();
				setState(317);
				sp();
				setState(318);
				match(DOT);
				setState(319);
				sp();
				}
			}

			setState(323);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(324);
				match(BRACKET_LEFT);
				setState(325);
				sp();
				setState(326);
				typeHint();
				setState(334);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(327);
					sp();
					setState(328);
					match(COMMA);
					setState(329);
					sp();
					setState(330);
					typeHint();
					}
					}
					setState(336);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(337);
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
			setState(341);
			match(LB);
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << WS) | (1L << NL) | (1L << COMMENT) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(342);
				sp();
				setState(343);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0)) {
					{
					{
					setState(344);
					sp();
					setState(345);
					match(COMMA);
					setState(346);
					sp();
					setState(347);
					((FnThContext)_localctx).typeHint = typeHint();
					((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
					}
					}
					setState(353);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(356);
			match(RB);
			setState(357);
			sp();
			setState(358);
			match(ARROW_RIGHT);
			setState(359);
			sp();
			setState(360);
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
			setState(362);
			match(LB);
			setState(363);
			sp();
			setState(364);
			fieldTh();
			setState(370); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(365);
				sp();
				setState(366);
				match(COMMA);
				setState(367);
				sp();
				setState(368);
				fieldTh();
				}
				}
				setState(372); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << COMMA) | (1L << WS) | (1L << NL) | (1L << COMMENT))) != 0) );
			setState(374);
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
			setState(379);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(376);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(377);
				fnTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(378);
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
			setState(381);
			nonUnionTh();
			setState(387); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(382);
					sp();
					setState(383);
					match(VERT_LINE);
					setState(384);
					sp();
					setState(385);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(389); 
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
			setState(395);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(391);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(392);
				structTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(393);
				fnTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(394);
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
			setState(397);
			match(IS);
			setState(398);
			sp();
			setState(399);
			match(VarId);
			setState(400);
			sp();
			setState(401);
			match(CON);
			setState(402);
			sp();
			setState(403);
			typeHint();
			setState(404);
			sp();
			setState(405);
			match(DO);
			setState(406);
			sp();
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(407);
				blockBody();
				}
				}
				setState(412);
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
			setState(413);
			id();
			setState(424);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(418);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==NL) {
						{
						setState(414);
						match(NL);
						setState(416);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(415);
							match(WS);
							}
						}

						}
					}

					setState(420);
					match(DOT);
					setState(421);
					match(VarId);
					}
					} 
				}
				setState(426);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,43,_ctx);
			}
			setState(427);
			sp();
			setState(433);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LB:
			case WITH:
				{
				setState(428);
				tuple();
				}
				break;
			case CON:
				{
				{
				setState(429);
				match(CON);
				setState(430);
				sp();
				setState(431);
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
			setState(435);
			sp();
			setState(436);
			match(EQ);
			setState(437);
			sp();
			setState(438);
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
			setState(440);
			match(RETURN);
			setState(441);
			sp();
			setState(443);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,45,_ctx) ) {
			case 1:
				{
				setState(442);
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
			setState(445);
			match(WHILE);
			setState(446);
			sp();
			setState(447);
			((While_statContext)_localctx).cond = expression(0);
			setState(448);
			sp();
			setState(449);
			match(DO);
			setState(450);
			sp();
			setState(454);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				{
				setState(451);
				blockBody();
				}
				}
				setState(456);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(457);
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
			setState(459);
			id();
			setState(460);
			sp();
			setState(471);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(461);
				match(CON);
				setState(462);
				sp();
				setState(463);
				typeHint();
				setState(469);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
				case 1:
					{
					setState(464);
					sp();
					setState(465);
					match(EQ);
					setState(466);
					sp();
					setState(467);
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
			setState(473);
			match(LAMBDA);
			setState(489);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(474);
				sp();
				setState(475);
				fnArg();
				setState(476);
				sp();
				setState(483);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(477);
					match(COMMA);
					setState(478);
					sp();
					setState(479);
					fnArg();
					}
					}
					setState(485);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(486);
				sp();
				setState(487);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(491);
			sp();
			setState(495);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(492);
					blockBody();
					}
					} 
				}
				setState(497);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,51,_ctx);
			}
			setState(498);
			sp();
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				setState(499);
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
			setState(506);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(502);
				store();
				}
				break;
			case 2:
				{
				setState(503);
				while_stat();
				}
				break;
			case 3:
				{
				setState(504);
				expression(0);
				}
				break;
			case 4:
				{
				setState(505);
				ret();
				}
				break;
			}
			setState(508);
			sp();
			setState(510);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
			case 1:
				{
				setState(509);
				match(SEMI);
				}
				break;
			}
			setState(512);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(514);
			match(TYPE);
			setState(515);
			sp();
			setState(516);
			((ScalarTypeContext)_localctx).tname = match(TypeId);
			setState(534);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,56,_ctx) ) {
			case 1:
				{
				setState(517);
				sp();
				setState(518);
				match(BRACKET_LEFT);
				setState(519);
				sp();
				setState(520);
				((ScalarTypeContext)_localctx).TypeId = match(TypeId);
				((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
				setState(528);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(521);
						sp();
						setState(522);
						match(COMMA);
						setState(523);
						sp();
						setState(524);
						((ScalarTypeContext)_localctx).TypeId = match(TypeId);
						((ScalarTypeContext)_localctx).params.add(((ScalarTypeContext)_localctx).TypeId);
						}
						} 
					}
					setState(530);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				setState(531);
				sp();
				setState(532);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(536);
			sp();
			setState(537);
			match(EQ);
			setState(538);
			sp();
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==REF) {
				{
				setState(539);
				match(REF);
				}
			}

			setState(542);
			sp();
			setState(543);
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
			setState(546);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF) {
				{
				setState(545);
				match(SELF);
				}
			}

			setState(548);
			sp();
			setState(549);
			match(VarId);
			setState(550);
			sp();
			setState(551);
			match(CON);
			setState(552);
			sp();
			setState(553);
			typeHint();
			setState(559);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
			case 1:
				{
				setState(554);
				sp();
				setState(555);
				match(EQ);
				setState(556);
				sp();
				setState(557);
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
			setState(561);
			match(TYPE);
			setState(562);
			sp();
			setState(563);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(581);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				{
				setState(564);
				sp();
				setState(565);
				match(BRACKET_LEFT);
				setState(566);
				sp();
				setState(567);
				((StructTypeContext)_localctx).TypeId = match(TypeId);
				((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
				setState(575);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(568);
						sp();
						setState(569);
						match(COMMA);
						setState(570);
						sp();
						setState(571);
						((StructTypeContext)_localctx).TypeId = match(TypeId);
						((StructTypeContext)_localctx).params.add(((StructTypeContext)_localctx).TypeId);
						}
						} 
					}
					setState(577);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				}
				setState(578);
				sp();
				setState(579);
				match(BRACKET_RIGTH);
				}
				break;
			}
			setState(583);
			sp();
			setState(584);
			match(EQ);
			setState(585);
			sp();
			setState(586);
			match(LB);
			setState(590);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(587);
					match(NL);
					}
					} 
				}
				setState(592);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			}
			setState(593);
			typeField();
			setState(604);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(594);
				match(COMMA);
				setState(598);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(595);
						match(NL);
						}
						} 
					}
					setState(600);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
				}
				setState(601);
				typeField();
				}
				}
				setState(606);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(610);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(607);
				match(NL);
				}
				}
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(613);
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
			setState(615);
			match(TYPE);
			setState(616);
			sp();
			setState(617);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(618);
			sp();
			setState(635);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(619);
				match(BRACKET_LEFT);
				setState(620);
				sp();
				setState(621);
				((UnionTypeContext)_localctx).TypeId = match(TypeId);
				((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
				setState(629);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(622);
						sp();
						setState(623);
						match(COMMA);
						setState(624);
						sp();
						setState(625);
						((UnionTypeContext)_localctx).TypeId = match(TypeId);
						((UnionTypeContext)_localctx).params.add(((UnionTypeContext)_localctx).TypeId);
						}
						} 
					}
					setState(631);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,66,_ctx);
				}
				setState(632);
				sp();
				setState(633);
				match(BRACKET_RIGTH);
				}
			}

			setState(637);
			sp();
			setState(638);
			match(EQ);
			setState(639);
			sp();
			setState(640);
			scalarTh();
			setState(646); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(641);
					sp();
					setState(642);
					match(VERT_LINE);
					setState(643);
					sp();
					setState(644);
					scalarTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(648); 
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
			setState(653);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(650);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(651);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(652);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(655);
			match(DEF);
			setState(656);
			sp();
			setState(657);
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
			setState(658);
			sp();
			setState(675);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(659);
				match(BRACKET_LEFT);
				setState(660);
				sp();
				setState(661);
				match(TypeId);
				setState(669);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(662);
						sp();
						setState(663);
						match(COMMA);
						setState(664);
						sp();
						setState(665);
						match(TypeId);
						}
						} 
					}
					setState(671);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				}
				setState(672);
				sp();
				setState(673);
				match(BRACKET_RIGTH);
				}
			}

			setState(677);
			sp();
			setState(678);
			match(EQ);
			setState(679);
			sp();
			setState(694);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(680);
				fnArg();
				setState(681);
				sp();
				setState(688);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(682);
					match(COMMA);
					setState(683);
					sp();
					setState(684);
					fnArg();
					}
					}
					setState(690);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(691);
				sp();
				setState(692);
				match(DO);
				}
				break;
			}
			setState(696);
			sp();
			setState(707);
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
				setState(700);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << WHILE) | (1L << SELF) | (1L << LAMBDA) | (1L << WITH) | (1L << RETURN) | (1L << WHEN) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << NoneLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId))) != 0)) {
					{
					{
					setState(697);
					blockBody();
					}
					}
					setState(702);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(703);
				sp();
				setState(704);
				match(DOT);
				}
				}
				break;
			case LlBegin:
				{
				setState(706);
				llvm();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(710);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LB) | (1L << SELF) | (1L << VarId) | (1L << TypeId))) != 0)) {
				{
				setState(709);
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
			setState(712);
			sp();
			setState(714);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIV) {
				{
				setState(713);
				((ImportEntryContext)_localctx).abs = match(DIV);
				}
			}

			setState(716);
			match(VarId);
			setState(721);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DIV) {
				{
				{
				setState(717);
				match(DIV);
				setState(718);
				match(VarId);
				}
				}
				setState(723);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(738);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(724);
				sp();
				setState(725);
				match(WITH);
				setState(726);
				sp();
				setState(727);
				match(TypeId);
				setState(728);
				sp();
				setState(735);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(729);
					match(COMMA);
					setState(730);
					sp();
					setState(731);
					match(TypeId);
					}
					}
					setState(737);
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
			setState(740);
			match(IMPORT);
			setState(741);
			importEntry();
			setState(746);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(742);
				match(NL);
				setState(743);
				importEntry();
				}
				}
				setState(748);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(749);
			match(WS);
			setState(750);
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
			setState(754);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(752);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(753);
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
			setState(756);
			sp();
			setState(758);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(757);
				import_();
				}
			}

			setState(760);
			sp();
			setState(766);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(761);
					sp();
					setState(762);
					llvm();
					}
					} 
				}
				setState(768);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			}
			setState(769);
			sp();
			setState(775);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TYPE || _la==DEF) {
				{
				{
				setState(770);
				level1();
				setState(771);
				sp();
				}
				}
				setState(777);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(778);
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
			setState(783);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 62)) & ~0x3f) == 0 && ((1L << (_la - 62)) & ((1L << (LLVM_NL - 62)) | (1L << (LLVM_WS - 62)) | (1L << (IrLine - 62)) | (1L << (LL_Dot - 62)))) != 0)) {
				{
				{
				setState(780);
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
				setState(785);
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
			setState(786);
			match(LlBegin);
			setState(787);
			llvmBody();
			setState(788);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3D\u0319\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\7\2B\n\2\f\2\16\2E\13\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5b\n"+
		"\5\f\5\16\5e\13\5\3\5\3\5\3\5\3\5\7\5k\n\5\f\5\16\5n\13\5\5\5p\n\5\3\5"+
		"\5\5s\n\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\6\5|\n\5\r\5\16\5}\3\5\3\5\3\5\3"+
		"\5\7\5\u0084\n\5\f\5\16\5\u0087\13\5\5\5\u0089\n\5\3\5\3\5\3\5\5\5\u008e"+
		"\n\5\3\5\3\5\7\5\u0092\n\5\f\5\16\5\u0095\13\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\7\5\u009d\n\5\f\5\16\5\u00a0\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00a8\n"+
		"\5\f\5\16\5\u00ab\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00b3\n\5\f\5\16\5"+
		"\u00b6\13\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00be\n\5\f\5\16\5\u00c1\13\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5\u00ca\n\5\5\5\u00cc\n\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00d9\n\5\f\5\16\5\u00dc\13\5\3\5\3"+
		"\5\5\5\u00e0\n\5\3\5\3\5\3\5\3\5\3\5\7\5\u00e7\n\5\f\5\16\5\u00ea\13\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5\u00f4\n\5\f\5\16\5\u00f7\13\5\3\5"+
		"\3\5\5\5\u00fb\n\5\3\5\7\5\u00fe\n\5\f\5\16\5\u0101\13\5\3\5\3\5\3\5\3"+
		"\5\5\5\u0107\n\5\5\5\u0109\n\5\3\5\3\5\7\5\u010d\n\5\f\5\16\5\u0110\13"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u011b\n\6\f\6\16\6\u011e\13"+
		"\6\5\6\u0120\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u012d"+
		"\n\6\f\6\16\6\u0130\13\6\5\6\u0132\n\6\3\6\3\6\3\6\5\6\u0137\n\6\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\5\b\u0144\n\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u014f\n\b\f\b\16\b\u0152\13\b\3\b\3\b\5\b\u0156"+
		"\n\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u0160\n\t\f\t\16\t\u0163\13\t"+
		"\5\t\u0165\n\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\6\n\u0175\n\n\r\n\16\n\u0176\3\n\3\n\3\13\3\13\3\13\5\13\u017e\n\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\6\f\u0186\n\f\r\f\16\f\u0187\3\r\3\r\3\r\3\r\5"+
		"\r\u018e\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7"+
		"\16\u019b\n\16\f\16\16\16\u019e\13\16\3\17\3\17\3\17\5\17\u01a3\n\17\5"+
		"\17\u01a5\n\17\3\17\3\17\7\17\u01a9\n\17\f\17\16\17\u01ac\13\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\5\17\u01b4\n\17\3\17\3\17\3\17\3\17\3\17\3\20"+
		"\3\20\3\20\5\20\u01be\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u01c7"+
		"\n\21\f\21\16\21\u01ca\13\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\5\22\u01d8\n\22\5\22\u01da\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\7\23\u01e4\n\23\f\23\16\23\u01e7\13\23\3\23\3\23"+
		"\3\23\5\23\u01ec\n\23\3\23\3\23\7\23\u01f0\n\23\f\23\16\23\u01f3\13\23"+
		"\3\23\3\23\5\23\u01f7\n\23\3\24\3\24\3\24\3\24\5\24\u01fd\n\24\3\24\3"+
		"\24\5\24\u0201\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\7\25\u0211\n\25\f\25\16\25\u0214\13\25\3\25\3\25"+
		"\3\25\5\25\u0219\n\25\3\25\3\25\3\25\3\25\5\25\u021f\n\25\3\25\3\25\3"+
		"\25\3\26\5\26\u0225\n\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\5\26\u0232\n\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\7\27\u0240\n\27\f\27\16\27\u0243\13\27\3\27\3\27\3\27"+
		"\5\27\u0248\n\27\3\27\3\27\3\27\3\27\3\27\7\27\u024f\n\27\f\27\16\27\u0252"+
		"\13\27\3\27\3\27\3\27\7\27\u0257\n\27\f\27\16\27\u025a\13\27\3\27\7\27"+
		"\u025d\n\27\f\27\16\27\u0260\13\27\3\27\7\27\u0263\n\27\f\27\16\27\u0266"+
		"\13\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\7\30\u0276\n\30\f\30\16\30\u0279\13\30\3\30\3\30\3\30\5\30\u027e"+
		"\n\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\6\30\u0289\n\30\r\30"+
		"\16\30\u028a\3\31\3\31\3\31\5\31\u0290\n\31\3\32\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\7\32\u029e\n\32\f\32\16\32\u02a1\13"+
		"\32\3\32\3\32\3\32\5\32\u02a6\n\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\7\32\u02b1\n\32\f\32\16\32\u02b4\13\32\3\32\3\32\3\32\5\32"+
		"\u02b9\n\32\3\32\3\32\7\32\u02bd\n\32\f\32\16\32\u02c0\13\32\3\32\3\32"+
		"\3\32\3\32\5\32\u02c6\n\32\3\32\5\32\u02c9\n\32\3\33\3\33\5\33\u02cd\n"+
		"\33\3\33\3\33\3\33\7\33\u02d2\n\33\f\33\16\33\u02d5\13\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\7\33\u02e0\n\33\f\33\16\33\u02e3\13"+
		"\33\5\33\u02e5\n\33\3\34\3\34\3\34\3\34\7\34\u02eb\n\34\f\34\16\34\u02ee"+
		"\13\34\3\34\3\34\3\34\3\35\3\35\5\35\u02f5\n\35\3\36\3\36\5\36\u02f9\n"+
		"\36\3\36\3\36\3\36\3\36\7\36\u02ff\n\36\f\36\16\36\u0302\13\36\3\36\3"+
		"\36\3\36\3\36\7\36\u0308\n\36\f\36\16\36\u030b\13\36\3\36\3\36\3\37\7"+
		"\37\u0310\n\37\f\37\16\37\u0313\13\37\3 \3 \3 \3 \3 \2\3\b!\2\4\6\b\n"+
		"\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\16\3\2\64\66"+
		"\3\2\67<\4\2##==\3\2\64\65\3\2\5\6\4\2\3\4==\3\2\f\17\3\2\21\22\3\2\32"+
		"\33\6\2\3\6\f\17\21\22==\7\2\3\7\f\17\21\22\32\33==\4\2@BDD\2\u0364\2"+
		"C\3\2\2\2\4F\3\2\2\2\6H\3\2\2\2\b\u008d\3\2\2\2\n\u0136\3\2\2\2\f\u0138"+
		"\3\2\2\2\16\u0143\3\2\2\2\20\u0157\3\2\2\2\22\u016c\3\2\2\2\24\u017d\3"+
		"\2\2\2\26\u017f\3\2\2\2\30\u018d\3\2\2\2\32\u018f\3\2\2\2\34\u019f\3\2"+
		"\2\2\36\u01ba\3\2\2\2 \u01bf\3\2\2\2\"\u01cd\3\2\2\2$\u01db\3\2\2\2&\u01fc"+
		"\3\2\2\2(\u0204\3\2\2\2*\u0224\3\2\2\2,\u0233\3\2\2\2.\u0269\3\2\2\2\60"+
		"\u028f\3\2\2\2\62\u0291\3\2\2\2\64\u02ca\3\2\2\2\66\u02e6\3\2\2\28\u02f4"+
		"\3\2\2\2:\u02f6\3\2\2\2<\u0311\3\2\2\2>\u0314\3\2\2\2@B\t\2\2\2A@\3\2"+
		"\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2D\3\3\2\2\2EC\3\2\2\2FG\t\3\2\2G\5\3"+
		"\2\2\2HI\t\4\2\2I\7\3\2\2\2JK\b\5\1\2K\u008e\5\4\3\2L\u008e\5\6\4\2M\u008e"+
		"\7>\2\2NO\7\n\2\2OP\5\2\2\2PQ\5\b\5\2QR\5\2\2\2RS\7\t\2\2S\u008e\3\2\2"+
		"\2T\u008e\5\n\6\2U\u008e\5$\23\2VW\7\7\2\2WX\5\2\2\2XY\5\b\5\nY\u008e"+
		"\3\2\2\2Z[\7\24\2\2[\\\5\2\2\2\\]\5\b\5\2]^\5\2\2\2^_\7\25\2\2_c\5\2\2"+
		"\2`b\5&\24\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2do\3\2\2\2ec\3\2\2"+
		"\2fg\5\2\2\2gh\7\26\2\2hl\5\2\2\2ik\5&\24\2ji\3\2\2\2kn\3\2\2\2lj\3\2"+
		"\2\2lm\3\2\2\2mp\3\2\2\2nl\3\2\2\2of\3\2\2\2op\3\2\2\2pr\3\2\2\2qs\t\5"+
		"\2\2rq\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\7\b\2\2u\u008e\3\2\2\2vw\7-\2\2w"+
		"x\5\2\2\2xy\5\b\5\2y{\5\2\2\2z|\5\32\16\2{z\3\2\2\2|}\3\2\2\2}{\3\2\2"+
		"\2}~\3\2\2\2~\177\3\2\2\2\177\u0088\5\2\2\2\u0080\u0081\7\26\2\2\u0081"+
		"\u0085\5\2\2\2\u0082\u0084\5&\24\2\u0083\u0082\3\2\2\2\u0084\u0087\3\2"+
		"\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0088\u0080\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2"+
		"\2\2\u008a\u008b\t\5\2\2\u008b\u008c\7\b\2\2\u008c\u008e\3\2\2\2\u008d"+
		"J\3\2\2\2\u008dL\3\2\2\2\u008dM\3\2\2\2\u008dN\3\2\2\2\u008dT\3\2\2\2"+
		"\u008dU\3\2\2\2\u008dV\3\2\2\2\u008dZ\3\2\2\2\u008dv\3\2\2\2\u008e\u010e"+
		"\3\2\2\2\u008f\u0093\f\t\2\2\u0090\u0092\7\64\2\2\u0091\u0090\3\2\2\2"+
		"\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094\u0096"+
		"\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\t\6\2\2\u0097\u0098\5\2\2\2\u0098"+
		"\u0099\5\b\5\n\u0099\u010d\3\2\2\2\u009a\u009e\f\b\2\2\u009b\u009d\7\64"+
		"\2\2\u009c\u009b\3\2\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e"+
		"\u009f\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a2\t\7"+
		"\2\2\u00a2\u00a3\5\2\2\2\u00a3\u00a4\5\b\5\t\u00a4\u010d\3\2\2\2\u00a5"+
		"\u00a9\f\7\2\2\u00a6\u00a8\7\64\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3"+
		"\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab"+
		"\u00a9\3\2\2\2\u00ac\u00ad\t\b\2\2\u00ad\u00ae\5\2\2\2\u00ae\u00af\5\b"+
		"\5\b\u00af\u010d\3\2\2\2\u00b0\u00b4\f\6\2\2\u00b1\u00b3\7\64\2\2\u00b2"+
		"\u00b1\3\2\2\2\u00b3\u00b6\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b7\u00b8\t\t\2\2\u00b8"+
		"\u00b9\5\2\2\2\u00b9\u00ba\5\b\5\7\u00ba\u010d\3\2\2\2\u00bb\u00bf\f\5"+
		"\2\2\u00bc\u00be\7\64\2\2\u00bd\u00bc\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf"+
		"\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c2\u00c3\t\n\2\2\u00c3\u00c4\5\2\2\2\u00c4\u00c5\5\b\5\6\u00c5"+
		"\u010d\3\2\2\2\u00c6\u00cb\f\16\2\2\u00c7\u00c9\7\65\2\2\u00c8\u00ca\7"+
		"\64\2\2\u00c9\u00c8\3\2\2\2\u00c9\u00ca\3\2\2\2\u00ca\u00cc\3\2\2\2\u00cb"+
		"\u00c7\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\7\b"+
		"\2\2\u00ce\u00df\t\13\2\2\u00cf\u00d0\5\2\2\2\u00d0\u00d1\7\61\2\2\u00d1"+
		"\u00d2\5\2\2\2\u00d2\u00da\5\30\r\2\u00d3\u00d4\5\2\2\2\u00d4\u00d5\7"+
		"\13\2\2\u00d5\u00d6\5\2\2\2\u00d6\u00d7\5\30\r\2\u00d7\u00d9\3\2\2\2\u00d8"+
		"\u00d3\3\2\2\2\u00d9\u00dc\3\2\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2"+
		"\2\2\u00db\u00dd\3\2\2\2\u00dc\u00da\3\2\2\2\u00dd\u00de\7\62\2\2\u00de"+
		"\u00e0\3\2\2\2\u00df\u00cf\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e1\3\2"+
		"\2\2\u00e1\u00e2\5\2\2\2\u00e2\u00e3\5\n\6\2\u00e3\u010d\3\2\2\2\u00e4"+
		"\u00e8\f\r\2\2\u00e5\u00e7\7\64\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3"+
		"\2\2\2\u00e8\u00e6\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00fa\3\2\2\2\u00ea"+
		"\u00e8\3\2\2\2\u00eb\u00ec\7\61\2\2\u00ec\u00ed\5\2\2\2\u00ed\u00f5\5"+
		"\30\r\2\u00ee\u00ef\5\2\2\2\u00ef\u00f0\7\13\2\2\u00f0\u00f1\5\2\2\2\u00f1"+
		"\u00f2\5\30\r\2\u00f2\u00f4\3\2\2\2\u00f3\u00ee\3\2\2\2\u00f4\u00f7\3"+
		"\2\2\2\u00f5\u00f3\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f8\3\2\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f8\u00f9\7\62\2\2\u00f9\u00fb\3\2\2\2\u00fa\u00eb\3"+
		"\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00ff\3\2\2\2\u00fc\u00fe\7\64\2\2\u00fd"+
		"\u00fc\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100\3\2"+
		"\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u010d\5\n\6\2\u0103"+
		"\u0108\f\f\2\2\u0104\u0106\7\65\2\2\u0105\u0107\7\64\2\2\u0106\u0105\3"+
		"\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0104\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\7\b\2\2\u010b\u010d\7="+
		"\2\2\u010c\u008f\3\2\2\2\u010c\u009a\3\2\2\2\u010c\u00a5\3\2\2\2\u010c"+
		"\u00b0\3\2\2\2\u010c\u00bb\3\2\2\2\u010c\u00c6\3\2\2\2\u010c\u00e4\3\2"+
		"\2\2\u010c\u0103\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e"+
		"\u010f\3\2\2\2\u010f\t\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\7\n\2\2"+
		"\u0112\u011f\5\2\2\2\u0113\u0114\5\b\5\2\u0114\u011c\5\2\2\2\u0115\u0116"+
		"\7\13\2\2\u0116\u0117\5\2\2\2\u0117\u0118\5\b\5\2\u0118\u0119\5\2\2\2"+
		"\u0119\u011b\3\2\2\2\u011a\u0115\3\2\2\2\u011b\u011e\3\2\2\2\u011c\u011a"+
		"\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011f"+
		"\u0113\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\5\2"+
		"\2\2\u0122\u0123\7\t\2\2\u0123\u0137\3\2\2\2\u0124\u0125\7(\2\2\u0125"+
		"\u0131\5\2\2\2\u0126\u0127\5\b\5\2\u0127\u012e\5\2\2\2\u0128\u0129\7\13"+
		"\2\2\u0129\u012a\5\2\2\2\u012a\u012b\5\b\5\2\u012b\u012d\3\2\2\2\u012c"+
		"\u0128\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012f\u0132\3\2\2\2\u0130\u012e\3\2\2\2\u0131\u0126\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\5\2\2\2\u0134\u0135\7\b"+
		"\2\2\u0135\u0137\3\2\2\2\u0136\u0111\3\2\2\2\u0136\u0124\3\2\2\2\u0137"+
		"\13\3\2\2\2\u0138\u0139\5\6\4\2\u0139\u013a\5\2\2\2\u013a\u013b\7\37\2"+
		"\2\u013b\u013c\5\2\2\2\u013c\u013d\5\30\r\2\u013d\r\3\2\2\2\u013e\u013f"+
		"\5\6\4\2\u013f\u0140\5\2\2\2\u0140\u0141\7\b\2\2\u0141\u0142\5\2\2\2\u0142"+
		"\u0144\3\2\2\2\u0143\u013e\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0145\3\2"+
		"\2\2\u0145\u0155\7>\2\2\u0146\u0147\7\61\2\2\u0147\u0148\5\2\2\2\u0148"+
		"\u0150\5\30\r\2\u0149\u014a\5\2\2\2\u014a\u014b\7\13\2\2\u014b\u014c\5"+
		"\2\2\2\u014c\u014d\5\30\r\2\u014d\u014f\3\2\2\2\u014e\u0149\3\2\2\2\u014f"+
		"\u0152\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0153\3\2"+
		"\2\2\u0152\u0150\3\2\2\2\u0153\u0154\7\62\2\2\u0154\u0156\3\2\2\2\u0155"+
		"\u0146\3\2\2\2\u0155\u0156\3\2\2\2\u0156\17\3\2\2\2\u0157\u0164\7\n\2"+
		"\2\u0158\u0159\5\2\2\2\u0159\u0161\5\30\r\2\u015a\u015b\5\2\2\2\u015b"+
		"\u015c\7\13\2\2\u015c\u015d\5\2\2\2\u015d\u015e\5\30\r\2\u015e\u0160\3"+
		"\2\2\2\u015f\u015a\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161"+
		"\u0162\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0158\3\2"+
		"\2\2\u0164\u0165\3\2\2\2\u0165\u0166\3\2\2\2\u0166\u0167\7\t\2\2\u0167"+
		"\u0168\5\2\2\2\u0168\u0169\7 \2\2\u0169\u016a\5\2\2\2\u016a\u016b\5\30"+
		"\r\2\u016b\21\3\2\2\2\u016c\u016d\7\n\2\2\u016d\u016e\5\2\2\2\u016e\u0174"+
		"\5\f\7\2\u016f\u0170\5\2\2\2\u0170\u0171\7\13\2\2\u0171\u0172\5\2\2\2"+
		"\u0172\u0173\5\f\7\2\u0173\u0175\3\2\2\2\u0174\u016f\3\2\2\2\u0175\u0176"+
		"\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\3\2\2\2\u0178"+
		"\u0179\7\t\2\2\u0179\23\3\2\2\2\u017a\u017e\5\16\b\2\u017b\u017e\5\20"+
		"\t\2\u017c\u017e\5\22\n\2\u017d\u017a\3\2\2\2\u017d\u017b\3\2\2\2\u017d"+
		"\u017c\3\2\2\2\u017e\25\3\2\2\2\u017f\u0185\5\24\13\2\u0180\u0181\5\2"+
		"\2\2\u0181\u0182\7\60\2\2\u0182\u0183\5\2\2\2\u0183\u0184\5\24\13\2\u0184"+
		"\u0186\3\2\2\2\u0185\u0180\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u0185\3\2"+
		"\2\2\u0187\u0188\3\2\2\2\u0188\27\3\2\2\2\u0189\u018e\5\16\b\2\u018a\u018e"+
		"\5\22\n\2\u018b\u018e\5\20\t\2\u018c\u018e\5\26\f\2\u018d\u0189\3\2\2"+
		"\2\u018d\u018a\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018c\3\2\2\2\u018e\31"+
		"\3\2\2\2\u018f\u0190\7,\2\2\u0190\u0191\5\2\2\2\u0191\u0192\7=\2\2\u0192"+
		"\u0193\5\2\2\2\u0193\u0194\7\37\2\2\u0194\u0195\5\2\2\2\u0195\u0196\5"+
		"\30\r\2\u0196\u0197\5\2\2\2\u0197\u0198\7\25\2\2\u0198\u019c\5\2\2\2\u0199"+
		"\u019b\5&\24\2\u019a\u0199\3\2\2\2\u019b\u019e\3\2\2\2\u019c\u019a\3\2"+
		"\2\2\u019c\u019d\3\2\2\2\u019d\33\3\2\2\2\u019e\u019c\3\2\2\2\u019f\u01aa"+
		"\5\6\4\2\u01a0\u01a2\7\65\2\2\u01a1\u01a3\7\64\2\2\u01a2\u01a1\3\2\2\2"+
		"\u01a2\u01a3\3\2\2\2\u01a3\u01a5\3\2\2\2\u01a4\u01a0\3\2\2\2\u01a4\u01a5"+
		"\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a7\7\b\2\2\u01a7\u01a9\7=\2\2\u01a8"+
		"\u01a4\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa\u01a8\3\2\2\2\u01aa\u01ab\3\2"+
		"\2\2\u01ab\u01ad\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ad\u01b3\5\2\2\2\u01ae"+
		"\u01b4\5\n\6\2\u01af\u01b0\7\37\2\2\u01b0\u01b1\5\2\2\2\u01b1\u01b2\5"+
		"\30\r\2\u01b2\u01b4\3\2\2\2\u01b3\u01ae\3\2\2\2\u01b3\u01af\3\2\2\2\u01b3"+
		"\u01b4\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b6\5\2\2\2\u01b6\u01b7\7\20"+
		"\2\2\u01b7\u01b8\5\2\2\2\u01b8\u01b9\5\b\5\2\u01b9\35\3\2\2\2\u01ba\u01bb"+
		"\7+\2\2\u01bb\u01bd\5\2\2\2\u01bc\u01be\5\b\5\2\u01bd\u01bc\3\2\2\2\u01bd"+
		"\u01be\3\2\2\2\u01be\37\3\2\2\2\u01bf\u01c0\7\34\2\2\u01c0\u01c1\5\2\2"+
		"\2\u01c1\u01c2\5\b\5\2\u01c2\u01c3\5\2\2\2\u01c3\u01c4\7\25\2\2\u01c4"+
		"\u01c8\5\2\2\2\u01c5\u01c7\5&\24\2\u01c6\u01c5\3\2\2\2\u01c7\u01ca\3\2"+
		"\2\2\u01c8\u01c6\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01cb\3\2\2\2\u01ca"+
		"\u01c8\3\2\2\2\u01cb\u01cc\7\b\2\2\u01cc!\3\2\2\2\u01cd\u01ce\5\6\4\2"+
		"\u01ce\u01d9\5\2\2\2\u01cf\u01d0\7\37\2\2\u01d0\u01d1\5\2\2\2\u01d1\u01d7"+
		"\5\30\r\2\u01d2\u01d3\5\2\2\2\u01d3\u01d4\7\20\2\2\u01d4\u01d5\5\2\2\2"+
		"\u01d5\u01d6\5\b\5\2\u01d6\u01d8\3\2\2\2\u01d7\u01d2\3\2\2\2\u01d7\u01d8"+
		"\3\2\2\2\u01d8\u01da\3\2\2\2\u01d9\u01cf\3\2\2\2\u01d9\u01da\3\2\2\2\u01da"+
		"#\3\2\2\2\u01db\u01eb\7&\2\2\u01dc\u01dd\5\2\2\2\u01dd\u01de\5\"\22\2"+
		"\u01de\u01e5\5\2\2\2\u01df\u01e0\7\13\2\2\u01e0\u01e1\5\2\2\2\u01e1\u01e2"+
		"\5\"\22\2\u01e2\u01e4\3\2\2\2\u01e3\u01df\3\2\2\2\u01e4\u01e7\3\2\2\2"+
		"\u01e5\u01e3\3\2\2\2\u01e5\u01e6\3\2\2\2\u01e6\u01e8\3\2\2\2\u01e7\u01e5"+
		"\3\2\2\2\u01e8\u01e9\5\2\2\2\u01e9\u01ea\7 \2\2\u01ea\u01ec\3\2\2\2\u01eb"+
		"\u01dc\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01f1\5\2"+
		"\2\2\u01ee\u01f0\5&\24\2\u01ef\u01ee\3\2\2\2\u01f0\u01f3\3\2\2\2\u01f1"+
		"\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f4\3\2\2\2\u01f3\u01f1\3\2"+
		"\2\2\u01f4\u01f6\5\2\2\2\u01f5\u01f7\7\b\2\2\u01f6\u01f5\3\2\2\2\u01f6"+
		"\u01f7\3\2\2\2\u01f7%\3\2\2\2\u01f8\u01fd\5\34\17\2\u01f9\u01fd\5 \21"+
		"\2\u01fa\u01fd\5\b\5\2\u01fb\u01fd\5\36\20\2\u01fc\u01f8\3\2\2\2\u01fc"+
		"\u01f9\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fb\3\2\2\2\u01fd\u01fe\3\2"+
		"\2\2\u01fe\u0200\5\2\2\2\u01ff\u0201\7\23\2\2\u0200\u01ff\3\2\2\2\u0200"+
		"\u0201\3\2\2\2\u0201\u0202\3\2\2\2\u0202\u0203\5\2\2\2\u0203\'\3\2\2\2"+
		"\u0204\u0205\7!\2\2\u0205\u0206\5\2\2\2\u0206\u0218\7>\2\2\u0207\u0208"+
		"\5\2\2\2\u0208\u0209\7\61\2\2\u0209\u020a\5\2\2\2\u020a\u0212\7>\2\2\u020b"+
		"\u020c\5\2\2\2\u020c\u020d\7\13\2\2\u020d\u020e\5\2\2\2\u020e\u020f\7"+
		">\2\2\u020f\u0211\3\2\2\2\u0210\u020b\3\2\2\2\u0211\u0214\3\2\2\2\u0212"+
		"\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0215\3\2\2\2\u0214\u0212\3\2"+
		"\2\2\u0215\u0216\5\2\2\2\u0216\u0217\7\62\2\2\u0217\u0219\3\2\2\2\u0218"+
		"\u0207\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021a\3\2\2\2\u021a\u021b\5\2"+
		"\2\2\u021b\u021c\7\20\2\2\u021c\u021e\5\2\2\2\u021d\u021f\7.\2\2\u021e"+
		"\u021d\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0220\3\2\2\2\u0220\u0221\5\2"+
		"\2\2\u0221\u0222\5> \2\u0222)\3\2\2\2\u0223\u0225\7#\2\2\u0224\u0223\3"+
		"\2\2\2\u0224\u0225\3\2\2\2\u0225\u0226\3\2\2\2\u0226\u0227\5\2\2\2\u0227"+
		"\u0228\7=\2\2\u0228\u0229\5\2\2\2\u0229\u022a\7\37\2\2\u022a\u022b\5\2"+
		"\2\2\u022b\u0231\5\30\r\2\u022c\u022d\5\2\2\2\u022d\u022e\7\20\2\2\u022e"+
		"\u022f\5\2\2\2\u022f\u0230\5\b\5\2\u0230\u0232\3\2\2\2\u0231\u022c\3\2"+
		"\2\2\u0231\u0232\3\2\2\2\u0232+\3\2\2\2\u0233\u0234\7!\2\2\u0234\u0235"+
		"\5\2\2\2\u0235\u0247\7>\2\2\u0236\u0237\5\2\2\2\u0237\u0238\7\61\2\2\u0238"+
		"\u0239\5\2\2\2\u0239\u0241\7>\2\2\u023a\u023b\5\2\2\2\u023b\u023c\7\13"+
		"\2\2\u023c\u023d\5\2\2\2\u023d\u023e\7>\2\2\u023e\u0240\3\2\2\2\u023f"+
		"\u023a\3\2\2\2\u0240\u0243\3\2\2\2\u0241\u023f\3\2\2\2\u0241\u0242\3\2"+
		"\2\2\u0242\u0244\3\2\2\2\u0243\u0241\3\2\2\2\u0244\u0245\5\2\2\2\u0245"+
		"\u0246\7\62\2\2\u0246\u0248\3\2\2\2\u0247\u0236\3\2\2\2\u0247\u0248\3"+
		"\2\2\2\u0248\u0249\3\2\2\2\u0249\u024a\5\2\2\2\u024a\u024b\7\20\2\2\u024b"+
		"\u024c\5\2\2\2\u024c\u0250\7\n\2\2\u024d\u024f\7\65\2\2\u024e\u024d\3"+
		"\2\2\2\u024f\u0252\3\2\2\2\u0250\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251"+
		"\u0253\3\2\2\2\u0252\u0250\3\2\2\2\u0253\u025e\5*\26\2\u0254\u0258\7\13"+
		"\2\2\u0255\u0257\7\65\2\2\u0256\u0255\3\2\2\2\u0257\u025a\3\2\2\2\u0258"+
		"\u0256\3\2\2\2\u0258\u0259\3\2\2\2\u0259\u025b\3\2\2\2\u025a\u0258\3\2"+
		"\2\2\u025b\u025d\5*\26\2\u025c\u0254\3\2\2\2\u025d\u0260\3\2\2\2\u025e"+
		"\u025c\3\2\2\2\u025e\u025f\3\2\2\2\u025f\u0264\3\2\2\2\u0260\u025e\3\2"+
		"\2\2\u0261\u0263\7\65\2\2\u0262\u0261\3\2\2\2\u0263\u0266\3\2\2\2\u0264"+
		"\u0262\3\2\2\2\u0264\u0265\3\2\2\2\u0265\u0267\3\2\2\2\u0266\u0264\3\2"+
		"\2\2\u0267\u0268\7\t\2\2\u0268-\3\2\2\2\u0269\u026a\7!\2\2\u026a\u026b"+
		"\5\2\2\2\u026b\u026c\7>\2\2\u026c\u027d\5\2\2\2\u026d\u026e\7\61\2\2\u026e"+
		"\u026f\5\2\2\2\u026f\u0277\7>\2\2\u0270\u0271\5\2\2\2\u0271\u0272\7\13"+
		"\2\2\u0272\u0273\5\2\2\2\u0273\u0274\7>\2\2\u0274\u0276\3\2\2\2\u0275"+
		"\u0270\3\2\2\2\u0276\u0279\3\2\2\2\u0277\u0275\3\2\2\2\u0277\u0278\3\2"+
		"\2\2\u0278\u027a\3\2\2\2\u0279\u0277\3\2\2\2\u027a\u027b\5\2\2\2\u027b"+
		"\u027c\7\62\2\2\u027c\u027e\3\2\2\2\u027d\u026d\3\2\2\2\u027d\u027e\3"+
		"\2\2\2\u027e\u027f\3\2\2\2\u027f\u0280\5\2\2\2\u0280\u0281\7\20\2\2\u0281"+
		"\u0282\5\2\2\2\u0282\u0288\5\16\b\2\u0283\u0284\5\2\2\2\u0284\u0285\7"+
		"\60\2\2\u0285\u0286\5\2\2\2\u0286\u0287\5\16\b\2\u0287\u0289\3\2\2\2\u0288"+
		"\u0283\3\2\2\2\u0289\u028a\3\2\2\2\u028a\u0288\3\2\2\2\u028a\u028b\3\2"+
		"\2\2\u028b/\3\2\2\2\u028c\u0290\5(\25\2\u028d\u0290\5,\27\2\u028e\u0290"+
		"\5.\30\2\u028f\u028c\3\2\2\2\u028f\u028d\3\2\2\2\u028f\u028e\3\2\2\2\u0290"+
		"\61\3\2\2\2\u0291\u0292\7%\2\2\u0292\u0293\5\2\2\2\u0293\u0294\t\f\2\2"+
		"\u0294\u02a5\5\2\2\2\u0295\u0296\7\61\2\2\u0296\u0297\5\2\2\2\u0297\u029f"+
		"\7>\2\2\u0298\u0299\5\2\2\2\u0299\u029a\7\13\2\2\u029a\u029b\5\2\2\2\u029b"+
		"\u029c\7>\2\2\u029c\u029e\3\2\2\2\u029d\u0298\3\2\2\2\u029e\u02a1\3\2"+
		"\2\2\u029f\u029d\3\2\2\2\u029f\u02a0\3\2\2\2\u02a0\u02a2\3\2\2\2\u02a1"+
		"\u029f\3\2\2\2\u02a2\u02a3\5\2\2\2\u02a3\u02a4\7\62\2\2\u02a4\u02a6\3"+
		"\2\2\2\u02a5\u0295\3\2\2\2\u02a5\u02a6\3\2\2\2\u02a6\u02a7\3\2\2\2\u02a7"+
		"\u02a8\5\2\2\2\u02a8\u02a9\7\20\2\2\u02a9\u02b8\5\2\2\2\u02aa\u02ab\5"+
		"\"\22\2\u02ab\u02b2\5\2\2\2\u02ac\u02ad\7\13\2\2\u02ad\u02ae\5\2\2\2\u02ae"+
		"\u02af\5\"\22\2\u02af\u02b1\3\2\2\2\u02b0\u02ac\3\2\2\2\u02b1\u02b4\3"+
		"\2\2\2\u02b2\u02b0\3\2\2\2\u02b2\u02b3\3\2\2\2\u02b3\u02b5\3\2\2\2\u02b4"+
		"\u02b2\3\2\2\2\u02b5\u02b6\5\2\2\2\u02b6\u02b7\7\25\2\2\u02b7\u02b9\3"+
		"\2\2\2\u02b8\u02aa\3\2\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02ba\3\2\2\2\u02ba"+
		"\u02c5\5\2\2\2\u02bb\u02bd\5&\24\2\u02bc\u02bb\3\2\2\2\u02bd\u02c0\3\2"+
		"\2\2\u02be\u02bc\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf\u02c1\3\2\2\2\u02c0"+
		"\u02be\3\2\2\2\u02c1\u02c2\5\2\2\2\u02c2\u02c3\7\b\2\2\u02c3\u02c6\3\2"+
		"\2\2\u02c4\u02c6\5> \2\u02c5\u02be\3\2\2\2\u02c5\u02c4\3\2\2\2\u02c6\u02c8"+
		"\3\2\2\2\u02c7\u02c9\5\30\r\2\u02c8\u02c7\3\2\2\2\u02c8\u02c9\3\2\2\2"+
		"\u02c9\63\3\2\2\2\u02ca\u02cc\5\2\2\2\u02cb\u02cd\7\6\2\2\u02cc\u02cb"+
		"\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02d3\7=\2\2\u02cf"+
		"\u02d0\7\6\2\2\u02d0\u02d2\7=\2\2\u02d1\u02cf\3\2\2\2\u02d2\u02d5\3\2"+
		"\2\2\u02d3\u02d1\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02e4\3\2\2\2\u02d5"+
		"\u02d3\3\2\2\2\u02d6\u02d7\5\2\2\2\u02d7\u02d8\7(\2\2\u02d8\u02d9\5\2"+
		"\2\2\u02d9\u02da\7>\2\2\u02da\u02e1\5\2\2\2\u02db\u02dc\7\13\2\2\u02dc"+
		"\u02dd\5\2\2\2\u02dd\u02de\7>\2\2\u02de\u02e0\3\2\2\2\u02df\u02db\3\2"+
		"\2\2\u02e0\u02e3\3\2\2\2\u02e1\u02df\3\2\2\2\u02e1\u02e2\3\2\2\2\u02e2"+
		"\u02e5\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e4\u02d6\3\2\2\2\u02e4\u02e5\3\2"+
		"\2\2\u02e5\65\3\2\2\2\u02e6\u02e7\7\'\2\2\u02e7\u02ec\5\64\33\2\u02e8"+
		"\u02e9\7\65\2\2\u02e9\u02eb\5\64\33\2\u02ea\u02e8\3\2\2\2\u02eb\u02ee"+
		"\3\2\2\2\u02ec\u02ea\3\2\2\2\u02ec\u02ed\3\2\2\2\u02ed\u02ef\3\2\2\2\u02ee"+
		"\u02ec\3\2\2\2\u02ef\u02f0\7\64\2\2\u02f0\u02f1\7\b\2\2\u02f1\67\3\2\2"+
		"\2\u02f2\u02f5\5\60\31\2\u02f3\u02f5\5\62\32\2\u02f4\u02f2\3\2\2\2\u02f4"+
		"\u02f3\3\2\2\2\u02f59\3\2\2\2\u02f6\u02f8\5\2\2\2\u02f7\u02f9\5\66\34"+
		"\2\u02f8\u02f7\3\2\2\2\u02f8\u02f9\3\2\2\2\u02f9\u02fa\3\2\2\2\u02fa\u0300"+
		"\5\2\2\2\u02fb\u02fc\5\2\2\2\u02fc\u02fd\5> \2\u02fd\u02ff\3\2\2\2\u02fe"+
		"\u02fb\3\2\2\2\u02ff\u0302\3\2\2\2\u0300\u02fe\3\2\2\2\u0300\u0301\3\2"+
		"\2\2\u0301\u0303\3\2\2\2\u0302\u0300\3\2\2\2\u0303\u0309\5\2\2\2\u0304"+
		"\u0305\58\35\2\u0305\u0306\5\2\2\2\u0306\u0308\3\2\2\2\u0307\u0304\3\2"+
		"\2\2\u0308\u030b\3\2\2\2\u0309\u0307\3\2\2\2\u0309\u030a\3\2\2\2\u030a"+
		"\u030c\3\2\2\2\u030b\u0309\3\2\2\2\u030c\u030d\7\2\2\3\u030d;\3\2\2\2"+
		"\u030e\u0310\t\r\2\2\u030f\u030e\3\2\2\2\u0310\u0313\3\2\2\2\u0311\u030f"+
		"\3\2\2\2\u0311\u0312\3\2\2\2\u0312=\3\2\2\2\u0313\u0311\3\2\2\2\u0314"+
		"\u0315\7\63\2\2\u0315\u0316\5<\37\2\u0316\u0317\7C\2\2\u0317?\3\2\2\2"+
		"YCclor}\u0085\u0088\u008d\u0093\u009e\u00a9\u00b4\u00bf\u00c9\u00cb\u00da"+
		"\u00df\u00e8\u00f5\u00fa\u00ff\u0106\u0108\u010c\u010e\u011c\u011f\u012e"+
		"\u0131\u0136\u0143\u0150\u0155\u0161\u0164\u0176\u017d\u0187\u018d\u019c"+
		"\u01a2\u01a4\u01aa\u01b3\u01bd\u01c8\u01d7\u01d9\u01e5\u01eb\u01f1\u01f6"+
		"\u01fc\u0200\u0212\u0218\u021e\u0224\u0231\u0241\u0247\u0250\u0258\u025e"+
		"\u0264\u0277\u027d\u028a\u028f\u029f\u02a5\u02b2\u02b8\u02be\u02c5\u02c8"+
		"\u02cc\u02d3\u02e1\u02e4\u02ec\u02f4\u02f8\u0300\u0309\u0311";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}