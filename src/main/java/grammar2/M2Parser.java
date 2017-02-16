// Generated from /home/over/build/test_lang/grammar/M2Parser.g4 by ANTLR 4.5.3
package grammar2;
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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINUS=1, PLUS=2, MUL=3, DIV=4, EXCL=5, DOT=6, RB=7, LB=8, COMMA=9, MORE_=10, 
		MORE_EQ=11, LESS=12, LESS_EQ=13, EQ=14, EQEQ=15, NOTEQ=16, SEMI=17, IF=18, 
		THEN=19, ELSE=20, CBO=21, DOLLAR_CBO=22, CBC=23, LOGIC_OR=24, LOGIC_AND=25, 
		WHILE=26, VAL=27, VAR=28, CON=29, ARROW_RIGHT=30, TYPE=31, BACK_SLASH=32, 
		SELF=33, MATCH_SELF=34, DEF=35, IMPORT=36, WITH=37, MATCH=38, OF=39, DASH=40, 
		LlBegin=41, WS=42, NL=43, COMMENT=44, LlLiteral=45, IntLiteral=46, HexLiteral=47, 
		FloatLiteral=48, BooleanLiteral=49, StringLiteral=50, Id=51, MatchId=52, 
		IrInline=53, LlEnd=54;
	public static final int
		RULE_literal = 0, RULE_id = 1, RULE_expression = 2, RULE_matchDash = 3, 
		RULE_matchId = 4, RULE_matchType = 5, RULE_matchBracketsExpr = 6, RULE_matchExpression = 7, 
		RULE_destruct = 8, RULE_matchOver = 9, RULE_matchCase = 10, RULE_if_stat = 11, 
		RULE_store = 12, RULE_fnArg = 13, RULE_block = 14, RULE_blockBody = 15, 
		RULE_lambdaBlock = 16, RULE_tuple = 17, RULE_scalarTypeHint = 18, RULE_fnTypeHintField = 19, 
		RULE_fnTypeHint = 20, RULE_typeHint = 21, RULE_variable = 22, RULE_scalarType = 23, 
		RULE_typeField = 24, RULE_factorType = 25, RULE_type = 26, RULE_function = 27, 
		RULE_import_ = 28, RULE_level1 = 29, RULE_module = 30;
	public static final String[] ruleNames = {
		"literal", "id", "expression", "matchDash", "matchId", "matchType", "matchBracketsExpr", 
		"matchExpression", "destruct", "matchOver", "matchCase", "if_stat", "store", 
		"fnArg", "block", "blockBody", "lambdaBlock", "tuple", "scalarTypeHint", 
		"fnTypeHintField", "fnTypeHint", "typeHint", "variable", "scalarType", 
		"typeField", "factorType", "type", "function", "import_", "level1", "module"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", "'.'", "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'then'", 
		"'else'", "'{'", "'${'", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", 
		"':'", "'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'import'", 
		"'with'", "'match'", "'of'", "'_'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "THEN", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
		"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
		"MATCH_SELF", "DEF", "IMPORT", "WITH", "MATCH", "OF", "DASH", "LlBegin", 
		"WS", "NL", "COMMENT", "LlLiteral", "IntLiteral", "HexLiteral", "FloatLiteral", 
		"BooleanLiteral", "StringLiteral", "Id", "MatchId", "IrInline", "LlEnd"
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
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
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
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
		enterRule(_localctx, 2, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
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
	public static class ExprMatchContext extends ExpressionContext {
		public ExpressionContext expr;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<MatchCaseContext> matchCase() {
			return getRuleContexts(MatchCaseContext.class);
		}
		public MatchCaseContext matchCase(int i) {
			return getRuleContext(MatchCaseContext.class,i);
		}
		public ExprMatchContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprMatch(this);
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
		public BlockContext then_block;
		public If_statContext then_stat;
		public BlockContext else_block;
		public If_statContext else_stat;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public List<If_statContext> if_stat() {
			return getRuleContexts(If_statContext.class);
		}
		public If_statContext if_stat(int i) {
			return getRuleContext(If_statContext.class,i);
		}
		public ExprIfElseContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprIfElse(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprLambdaContext extends ExpressionContext {
		public LambdaBlockContext lambdaBlock() {
			return getRuleContext(LambdaBlockContext.class,0);
		}
		public ExprLambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprLambda(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprApplyContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public ExprApplyContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprApply(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprParenContext extends ExpressionContext {
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
	public static class ExprPropContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
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
	public static class ExprWhileContext extends ExpressionContext {
		public ExpressionContext cond;
		public BlockContext then_block;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public ExprWhileContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprWhile(this);
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
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
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
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public ExprSelfCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprSelfCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprBlockContext extends ExpressionContext {
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ExprBlockContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprBlock(this);
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(67);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(68);
				id();
				}
				break;
			case 3:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(69);
				match(LB);
				setState(70);
				expression(0);
				setState(71);
				match(RB);
				}
				break;
			case 4:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				tuple();
				}
				break;
			case 5:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(74);
				block();
				}
				break;
			case 6:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(75);
				lambdaBlock();
				}
				break;
			case 7:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(76);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(77);
				expression(9);
				}
				break;
			case 8:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				match(IF);
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(79);
					match(NL);
					}
					}
					setState(84);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(85);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(86);
					match(NL);
					}
					}
					setState(91);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(101);
				switch (_input.LA(1)) {
				case CBO:
					{
					setState(92);
					((ExprIfElseContext)_localctx).then_block = block();
					}
					break;
				case THEN:
					{
					{
					setState(93);
					match(THEN);
					setState(97);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(94);
						match(NL);
						}
						}
						setState(99);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(100);
					((ExprIfElseContext)_localctx).then_stat = if_stat();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(120);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(106);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(103);
						match(NL);
						}
						}
						setState(108);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(109);
					match(ELSE);
					setState(113);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(110);
						match(NL);
						}
						}
						setState(115);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(118);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(116);
						((ExprIfElseContext)_localctx).else_block = block();
						}
						break;
					case 2:
						{
						setState(117);
						((ExprIfElseContext)_localctx).else_stat = if_stat();
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 9:
				{
				_localctx = new ExprWhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				match(WHILE);
				setState(126);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(123);
					match(NL);
					}
					}
					setState(128);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(129);
				((ExprWhileContext)_localctx).cond = expression(0);
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(130);
					match(NL);
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(136);
				((ExprWhileContext)_localctx).then_block = block();
				}
				break;
			case 10:
				{
				_localctx = new ExprMatchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(MATCH);
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(139);
					match(NL);
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				((ExprMatchContext)_localctx).expr = expression(0);
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(146);
					match(NL);
					}
					}
					setState(151);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(159); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(152);
						matchCase();
						setState(156);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(153);
								match(NL);
								}
								} 
							}
							setState(158);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(161); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(191);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(189);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(166);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(167);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(168);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(169);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << Id))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(170);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(171);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(172);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(173);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(174);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(175);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQEQ || _la==NOTEQ) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(176);
						expression(6);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(177);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(178);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LOGIC_OR || _la==LOGIC_AND) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(179);
						expression(5);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(180);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(181);
						match(DOT);
						setState(182);
						((ExprSelfCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << Id))) != 0)) ) {
							((ExprSelfCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(183);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(185);
						match(DOT);
						setState(186);
						((ExprPropContext)_localctx).op = match(Id);
						}
						break;
					case 8:
						{
						_localctx = new ExprApplyContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(187);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(188);
						tuple();
						}
						break;
					}
					} 
				}
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
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

	public static class MatchDashContext extends ParserRuleContext {
		public MatchDashContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchDash; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchDash(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchDashContext matchDash() throws RecognitionException {
		MatchDashContext _localctx = new MatchDashContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_matchDash);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(DASH);
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

	public static class MatchIdContext extends ParserRuleContext {
		public TerminalNode MatchId() { return getToken(M2Parser.MatchId, 0); }
		public MatchIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchId; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchIdContext matchId() throws RecognitionException {
		MatchIdContext _localctx = new MatchIdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_matchId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			_la = _input.LA(1);
			if ( !(_la==MATCH_SELF || _la==MatchId) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static class MatchTypeContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ScalarTypeHintContext scalarTypeHint() {
			return getRuleContext(ScalarTypeHintContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public MatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchTypeContext matchType() throws RecognitionException {
		MatchTypeContext _localctx = new MatchTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_matchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			id();
			setState(202);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(199);
				match(NL);
				}
				}
				setState(204);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(205);
			match(CON);
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(206);
				match(NL);
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(212);
			scalarTypeHint();
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

	public static class MatchBracketsExprContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public MatchBracketsExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchBracketsExpr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchBracketsExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchBracketsExprContext matchBracketsExpr() throws RecognitionException {
		MatchBracketsExprContext _localctx = new MatchBracketsExprContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_matchBracketsExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(214);
			match(DOLLAR_CBO);
			setState(215);
			expression(0);
			setState(216);
			match(CBC);
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

	public static class MatchExpressionContext extends ParserRuleContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public MatchIdContext matchId() {
			return getRuleContext(MatchIdContext.class,0);
		}
		public MatchBracketsExprContext matchBracketsExpr() {
			return getRuleContext(MatchBracketsExprContext.class,0);
		}
		public MatchExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchExpressionContext matchExpression() throws RecognitionException {
		MatchExpressionContext _localctx = new MatchExpressionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_matchExpression);
		try {
			setState(221);
			switch (_input.LA(1)) {
			case IntLiteral:
			case HexLiteral:
			case FloatLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(218);
				literal();
				}
				break;
			case MATCH_SELF:
			case MatchId:
				enterOuterAlt(_localctx, 2);
				{
				setState(219);
				matchId();
				}
				break;
			case DOLLAR_CBO:
				enterOuterAlt(_localctx, 3);
				{
				setState(220);
				matchBracketsExpr();
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

	public static class DestructContext extends ParserRuleContext {
		public ScalarTypeHintContext scalarTypeHint() {
			return getRuleContext(ScalarTypeHintContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public List<MatchOverContext> matchOver() {
			return getRuleContexts(MatchOverContext.class);
		}
		public MatchOverContext matchOver(int i) {
			return getRuleContext(MatchOverContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public DestructContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_destruct; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitDestruct(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DestructContext destruct() throws RecognitionException {
		DestructContext _localctx = new DestructContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_destruct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(223);
				id();
				setState(224);
				match(EQ);
				}
				break;
			}
			setState(228);
			scalarTypeHint();
			setState(229);
			match(LB);
			setState(244);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOLLAR_CBO) | (1L << SELF) | (1L << MATCH_SELF) | (1L << DASH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id) | (1L << MatchId))) != 0)) {
				{
				setState(230);
				matchOver();
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(231);
					match(COMMA);
					setState(235);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(232);
						match(NL);
						}
						}
						setState(237);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(238);
					matchOver();
					}
					}
					setState(243);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(246);
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

	public static class MatchOverContext extends ParserRuleContext {
		public MatchDashContext matchDash() {
			return getRuleContext(MatchDashContext.class,0);
		}
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public MatchExpressionContext matchExpression() {
			return getRuleContext(MatchExpressionContext.class,0);
		}
		public DestructContext destruct() {
			return getRuleContext(DestructContext.class,0);
		}
		public MatchTypeContext matchType() {
			return getRuleContext(MatchTypeContext.class,0);
		}
		public MatchOverContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchOver; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchOver(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchOverContext matchOver() throws RecognitionException {
		MatchOverContext _localctx = new MatchOverContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_matchOver);
		try {
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				matchDash();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(249);
				id();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(250);
				matchExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(251);
				destruct();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(252);
				matchType();
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

	public static class MatchCaseContext extends ParserRuleContext {
		public ExpressionContext cond;
		public ExpressionContext onMatch;
		public MatchOverContext matchOver() {
			return getRuleContext(MatchOverContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public MatchCaseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchCase; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitMatchCase(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchCaseContext matchCase() throws RecognitionException {
		MatchCaseContext _localctx = new MatchCaseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_matchCase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			match(OF);
			setState(259);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(256);
				match(NL);
				}
				}
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(262);
			matchOver();
			setState(266);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(263);
				match(NL);
				}
				}
				setState(268);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(283);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(269);
				match(IF);
				setState(273);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(270);
					match(NL);
					}
					}
					setState(275);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(276);
				((MatchCaseContext)_localctx).cond = expression(0);
				setState(280);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(277);
					match(NL);
					}
					}
					setState(282);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(285);
			match(ARROW_RIGHT);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(286);
				match(NL);
				}
				}
				setState(291);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(292);
			((MatchCaseContext)_localctx).onMatch = expression(0);
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

	public static class If_statContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StoreContext store() {
			return getRuleContext(StoreContext.class,0);
		}
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitIf_stat(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_if_stat);
		try {
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(294);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(295);
				store();
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

	public static class StoreContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
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
		enterRule(_localctx, 24, RULE_store);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			id();
			setState(303);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(299);
				match(DOT);
				setState(300);
				id();
				}
				}
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(307);
			_la = _input.LA(1);
			if (_la==LB) {
				{
				setState(306);
				tuple();
				}
			}

			setState(309);
			match(EQ);
			setState(310);
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

	public static class FnArgContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
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
		enterRule(_localctx, 26, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(315);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(313);
				match(CON);
				setState(314);
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

	public static class BlockContext extends ParserRuleContext {
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			match(CBO);
			setState(328);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(318);
				fnArg();
				setState(323);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(319);
					match(COMMA);
					setState(320);
					fnArg();
					}
					}
					setState(325);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(326);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(333);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(330);
					match(NL);
					}
					} 
				}
				setState(335);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			setState(339);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << VAL) | (1L << VAR) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) {
				{
				{
				setState(336);
				blockBody();
				}
				}
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(342);
				match(NL);
				}
				}
				setState(347);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(348);
			match(CBC);
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public StoreContext store() {
			return getRuleContext(StoreContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
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
		enterRule(_localctx, 30, RULE_blockBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(353);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(350);
				variable();
				}
				break;
			case 2:
				{
				setState(351);
				store();
				}
				break;
			case 3:
				{
				setState(352);
				expression(0);
				}
				break;
			}
			setState(356);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(355);
				match(SEMI);
				}
			}

			setState(361);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(358);
					match(NL);
					}
					} 
				}
				setState(363);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
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

	public static class LambdaBlockContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StoreContext store() {
			return getRuleContext(StoreContext.class,0);
		}
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public LambdaBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lambdaBlock; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLambdaBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LambdaBlockContext lambdaBlock() throws RecognitionException {
		LambdaBlockContext _localctx = new LambdaBlockContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_lambdaBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			match(BACK_SLASH);
			setState(373);
			_la = _input.LA(1);
			if (_la==SELF || _la==Id) {
				{
				setState(365);
				fnArg();
				setState(370);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(366);
					match(COMMA);
					setState(367);
					fnArg();
					}
					}
					setState(372);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(375);
			match(ARROW_RIGHT);
			setState(379);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(376);
				match(NL);
				}
				}
				setState(381);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(384);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(382);
				expression(0);
				}
				break;
			case 2:
				{
				setState(383);
				store();
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

	public static class TupleContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
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
		enterRule(_localctx, 34, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			match(LB);
			setState(401);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) {
				{
				setState(387);
				expression(0);
				setState(398);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(388);
					match(COMMA);
					setState(392);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(389);
						match(NL);
						}
						}
						setState(394);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(395);
					expression(0);
					}
					}
					setState(400);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(403);
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

	public static class ScalarTypeHintContext extends ParserRuleContext {
		public Token modVar;
		public Token typeName;
		public List<TerminalNode> Id() { return getTokens(M2Parser.Id); }
		public TerminalNode Id(int i) {
			return getToken(M2Parser.Id, i);
		}
		public ScalarTypeHintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarTypeHint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitScalarTypeHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeHintContext scalarTypeHint() throws RecognitionException {
		ScalarTypeHintContext _localctx = new ScalarTypeHintContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_scalarTypeHint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(405);
				((ScalarTypeHintContext)_localctx).modVar = match(Id);
				setState(406);
				match(DOT);
				}
				break;
			}
			setState(409);
			((ScalarTypeHintContext)_localctx).typeName = match(Id);
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

	public static class FnTypeHintFieldContext extends ParserRuleContext {
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public FnTypeHintFieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnTypeHintField; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFnTypeHintField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnTypeHintFieldContext fnTypeHintField() throws RecognitionException {
		FnTypeHintFieldContext _localctx = new FnTypeHintFieldContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_fnTypeHintField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(411);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(412);
			match(CON);
			setState(413);
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

	public static class FnTypeHintContext extends ParserRuleContext {
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public List<FnTypeHintFieldContext> fnTypeHintField() {
			return getRuleContexts(FnTypeHintFieldContext.class);
		}
		public FnTypeHintFieldContext fnTypeHintField(int i) {
			return getRuleContext(FnTypeHintFieldContext.class,i);
		}
		public FnTypeHintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnTypeHint; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFnTypeHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnTypeHintContext fnTypeHint() throws RecognitionException {
		FnTypeHintContext _localctx = new FnTypeHintContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_fnTypeHint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			match(LB);
			setState(424);
			_la = _input.LA(1);
			if (_la==SELF || _la==Id) {
				{
				setState(416);
				fnTypeHintField();
				setState(421);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(417);
					match(COMMA);
					setState(418);
					fnTypeHintField();
					}
					}
					setState(423);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(426);
			match(RB);
			setState(427);
			match(ARROW_RIGHT);
			setState(428);
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

	public static class TypeHintContext extends ParserRuleContext {
		public ScalarTypeHintContext scalarTypeHint() {
			return getRuleContext(ScalarTypeHintContext.class,0);
		}
		public FnTypeHintContext fnTypeHint() {
			return getRuleContext(FnTypeHintContext.class,0);
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
		enterRule(_localctx, 42, RULE_typeHint);
		try {
			setState(432);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(430);
				scalarTypeHint();
				}
				break;
			case LB:
				enterOuterAlt(_localctx, 2);
				{
				setState(431);
				fnTypeHint();
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

	public static class VariableContext extends ParserRuleContext {
		public Token valVar;
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(434);
			((VariableContext)_localctx).valVar = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==VAL || _la==VAR) ) {
				((VariableContext)_localctx).valVar = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(435);
				match(NL);
				}
				}
				setState(440);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(441);
			match(Id);
			setState(444);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(442);
				match(CON);
				setState(443);
				typeHint();
				}
			}

			setState(449);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(446);
				match(NL);
				}
				}
				setState(451);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(452);
			match(EQ);
			setState(456);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(453);
				match(NL);
				}
				}
				setState(458);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(459);
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

	public static class ScalarTypeContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public TerminalNode LlLiteral() { return getToken(M2Parser.LlLiteral, 0); }
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
		enterRule(_localctx, 46, RULE_scalarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(461);
			match(TYPE);
			setState(462);
			match(Id);
			setState(463);
			match(EQ);
			setState(464);
			match(LlLiteral);
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
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
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
		enterRule(_localctx, 48, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(466);
				match(SELF);
				}
				break;
			}
			setState(469);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(470);
			match(CON);
			setState(471);
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

	public static class FactorTypeContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public List<TypeFieldContext> typeField() {
			return getRuleContexts(TypeFieldContext.class);
		}
		public TypeFieldContext typeField(int i) {
			return getRuleContext(TypeFieldContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public FactorTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factorType; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFactorType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorTypeContext factorType() throws RecognitionException {
		FactorTypeContext _localctx = new FactorTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_factorType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			match(TYPE);
			setState(474);
			match(Id);
			setState(475);
			match(EQ);
			setState(476);
			match(LB);
			setState(480);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(477);
				match(NL);
				}
				}
				setState(482);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(483);
			typeField();
			setState(494);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(484);
				match(COMMA);
				setState(488);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(485);
					match(NL);
					}
					}
					setState(490);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(491);
				typeField();
				}
				}
				setState(496);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(497);
				match(NL);
				}
				}
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(503);
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

	public static class TypeContext extends ParserRuleContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public FactorTypeContext factorType() {
			return getRuleContext(FactorTypeContext.class,0);
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
			setState(507);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,63,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(505);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(506);
				factorType();
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

	public static class FunctionContext extends ParserRuleContext {
		public Token name;
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public LambdaBlockContext lambdaBlock() {
			return getRuleContext(LambdaBlockContext.class,0);
		}
		public TerminalNode LlLiteral() { return getToken(M2Parser.LlLiteral, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FnTypeHintContext fnTypeHint() {
			return getRuleContext(FnTypeHintContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			match(DEF);
			setState(510);
			((FunctionContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << EXCL) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << SELF) | (1L << Id))) != 0)) ) {
				((FunctionContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(513);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(511);
				match(CON);
				setState(512);
				fnTypeHint();
				}
			}

			setState(515);
			match(EQ);
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(516);
				match(NL);
				}
				}
				setState(521);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(529);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
			case 1:
				{
				setState(522);
				lambdaBlock();
				}
				break;
			case 2:
				{
				{
				setState(523);
				block();
				setState(524);
				match(CON);
				setState(525);
				typeHint();
				}
				}
				break;
			case 3:
				{
				setState(527);
				match(LlLiteral);
				}
				break;
			case 4:
				{
				setState(528);
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

	public static class Import_Context extends ParserRuleContext {
		public Token Id;
		public List<Token> pkgName = new ArrayList<Token>();
		public List<Token> tid = new ArrayList<Token>();
		public List<TerminalNode> Id() { return getTokens(M2Parser.Id); }
		public TerminalNode Id(int i) {
			return getToken(M2Parser.Id, i);
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
		enterRule(_localctx, 56, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			match(IMPORT);
			setState(532);
			((Import_Context)_localctx).Id = match(Id);
			((Import_Context)_localctx).pkgName.add(((Import_Context)_localctx).Id);
			setState(537);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(533);
				match(DOT);
				setState(534);
				((Import_Context)_localctx).Id = match(Id);
				((Import_Context)_localctx).pkgName.add(((Import_Context)_localctx).Id);
				}
				}
				setState(539);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(549);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(540);
				match(WITH);
				setState(541);
				((Import_Context)_localctx).Id = match(Id);
				((Import_Context)_localctx).tid.add(((Import_Context)_localctx).Id);
				setState(546);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(542);
					match(COMMA);
					setState(543);
					((Import_Context)_localctx).Id = match(Id);
					((Import_Context)_localctx).tid.add(((Import_Context)_localctx).Id);
					}
					}
					setState(548);
					_errHandler.sync(this);
					_la = _input.LA(1);
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

	public static class Level1Context extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
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
		enterRule(_localctx, 58, RULE_level1);
		try {
			setState(553);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(551);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(552);
				function();
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
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<Import_Context> import_() {
			return getRuleContexts(Import_Context.class);
		}
		public Import_Context import_(int i) {
			return getRuleContext(Import_Context.class,i);
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
		enterRule(_localctx, 60, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(558);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(555);
				match(NL);
				}
				}
				setState(560);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(579);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(561);
				import_();
				setState(570);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(563); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(562);
							match(NL);
							}
							}
							setState(565); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(567);
						import_();
						}
						} 
					}
					setState(572);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,73,_ctx);
				}
				setState(576);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(573);
					match(NL);
					}
					}
					setState(578);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(599);
			_la = _input.LA(1);
			if (_la==TYPE || _la==DEF) {
				{
				setState(581);
				level1();
				setState(590);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(583); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(582);
							match(NL);
							}
							}
							setState(585); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(587);
						level1();
						}
						} 
					}
					setState(592);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,77,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		case 1:
			return precpred(_ctx, 7);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 5);
		case 4:
			return precpred(_ctx, 4);
		case 5:
			return precpred(_ctx, 14);
		case 6:
			return precpred(_ctx, 13);
		case 7:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\38\u025c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7"+
		"\4S\n\4\f\4\16\4V\13\4\3\4\3\4\7\4Z\n\4\f\4\16\4]\13\4\3\4\3\4\3\4\7\4"+
		"b\n\4\f\4\16\4e\13\4\3\4\5\4h\n\4\3\4\7\4k\n\4\f\4\16\4n\13\4\3\4\3\4"+
		"\7\4r\n\4\f\4\16\4u\13\4\3\4\3\4\5\4y\n\4\5\4{\n\4\3\4\3\4\7\4\177\n\4"+
		"\f\4\16\4\u0082\13\4\3\4\3\4\7\4\u0086\n\4\f\4\16\4\u0089\13\4\3\4\3\4"+
		"\3\4\3\4\7\4\u008f\n\4\f\4\16\4\u0092\13\4\3\4\3\4\7\4\u0096\n\4\f\4\16"+
		"\4\u0099\13\4\3\4\3\4\7\4\u009d\n\4\f\4\16\4\u00a0\13\4\6\4\u00a2\n\4"+
		"\r\4\16\4\u00a3\5\4\u00a6\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u00c0\n\4"+
		"\f\4\16\4\u00c3\13\4\3\5\3\5\3\6\3\6\3\7\3\7\7\7\u00cb\n\7\f\7\16\7\u00ce"+
		"\13\7\3\7\3\7\7\7\u00d2\n\7\f\7\16\7\u00d5\13\7\3\7\3\7\3\b\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\5\t\u00e0\n\t\3\n\3\n\3\n\5\n\u00e5\n\n\3\n\3\n\3\n\3\n"+
		"\3\n\7\n\u00ec\n\n\f\n\16\n\u00ef\13\n\3\n\7\n\u00f2\n\n\f\n\16\n\u00f5"+
		"\13\n\5\n\u00f7\n\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\5\13\u0100\n\13\3"+
		"\f\3\f\7\f\u0104\n\f\f\f\16\f\u0107\13\f\3\f\3\f\7\f\u010b\n\f\f\f\16"+
		"\f\u010e\13\f\3\f\3\f\7\f\u0112\n\f\f\f\16\f\u0115\13\f\3\f\3\f\7\f\u0119"+
		"\n\f\f\f\16\f\u011c\13\f\5\f\u011e\n\f\3\f\3\f\7\f\u0122\n\f\f\f\16\f"+
		"\u0125\13\f\3\f\3\f\3\r\3\r\5\r\u012b\n\r\3\16\3\16\3\16\7\16\u0130\n"+
		"\16\f\16\16\16\u0133\13\16\3\16\5\16\u0136\n\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\5\17\u013e\n\17\3\20\3\20\3\20\3\20\7\20\u0144\n\20\f\20\16\20"+
		"\u0147\13\20\3\20\3\20\5\20\u014b\n\20\3\20\7\20\u014e\n\20\f\20\16\20"+
		"\u0151\13\20\3\20\7\20\u0154\n\20\f\20\16\20\u0157\13\20\3\20\7\20\u015a"+
		"\n\20\f\20\16\20\u015d\13\20\3\20\3\20\3\21\3\21\3\21\5\21\u0164\n\21"+
		"\3\21\5\21\u0167\n\21\3\21\7\21\u016a\n\21\f\21\16\21\u016d\13\21\3\22"+
		"\3\22\3\22\3\22\7\22\u0173\n\22\f\22\16\22\u0176\13\22\5\22\u0178\n\22"+
		"\3\22\3\22\7\22\u017c\n\22\f\22\16\22\u017f\13\22\3\22\3\22\5\22\u0183"+
		"\n\22\3\23\3\23\3\23\3\23\7\23\u0189\n\23\f\23\16\23\u018c\13\23\3\23"+
		"\7\23\u018f\n\23\f\23\16\23\u0192\13\23\5\23\u0194\n\23\3\23\3\23\3\24"+
		"\3\24\5\24\u019a\n\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\7\26\u01a6\n\26\f\26\16\26\u01a9\13\26\5\26\u01ab\n\26\3\26\3\26\3\26"+
		"\3\26\3\27\3\27\5\27\u01b3\n\27\3\30\3\30\7\30\u01b7\n\30\f\30\16\30\u01ba"+
		"\13\30\3\30\3\30\3\30\5\30\u01bf\n\30\3\30\7\30\u01c2\n\30\f\30\16\30"+
		"\u01c5\13\30\3\30\3\30\7\30\u01c9\n\30\f\30\16\30\u01cc\13\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\31\3\32\5\32\u01d6\n\32\3\32\3\32\3\32\3\32\3\33"+
		"\3\33\3\33\3\33\3\33\7\33\u01e1\n\33\f\33\16\33\u01e4\13\33\3\33\3\33"+
		"\3\33\7\33\u01e9\n\33\f\33\16\33\u01ec\13\33\3\33\7\33\u01ef\n\33\f\33"+
		"\16\33\u01f2\13\33\3\33\7\33\u01f5\n\33\f\33\16\33\u01f8\13\33\3\33\3"+
		"\33\3\34\3\34\5\34\u01fe\n\34\3\35\3\35\3\35\3\35\5\35\u0204\n\35\3\35"+
		"\3\35\7\35\u0208\n\35\f\35\16\35\u020b\13\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\5\35\u0214\n\35\3\36\3\36\3\36\3\36\7\36\u021a\n\36\f\36\16"+
		"\36\u021d\13\36\3\36\3\36\3\36\3\36\7\36\u0223\n\36\f\36\16\36\u0226\13"+
		"\36\5\36\u0228\n\36\3\37\3\37\5\37\u022c\n\37\3 \7 \u022f\n \f \16 \u0232"+
		"\13 \3 \3 \6 \u0236\n \r \16 \u0237\3 \7 \u023b\n \f \16 \u023e\13 \3"+
		" \7 \u0241\n \f \16 \u0244\13 \5 \u0246\n \3 \3 \6 \u024a\n \r \16 \u024b"+
		"\3 \7 \u024f\n \f \16 \u0252\13 \3 \7 \u0255\n \f \16 \u0258\13 \5 \u025a"+
		"\n \3 \2\3\6!\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64"+
		"\668:<>\2\r\3\2\60\64\4\2##\65\65\3\2\5\6\4\2\3\4\65\65\3\2\f\17\3\2\21"+
		"\22\3\2\32\33\7\2\3\6\f\17\21\22\32\33\65\65\4\2$$\66\66\3\2\35\36\b\2"+
		"\3\7\f\17\21\22\32\33##\65\65\u02a1\2@\3\2\2\2\4B\3\2\2\2\6\u00a5\3\2"+
		"\2\2\b\u00c4\3\2\2\2\n\u00c6\3\2\2\2\f\u00c8\3\2\2\2\16\u00d8\3\2\2\2"+
		"\20\u00df\3\2\2\2\22\u00e4\3\2\2\2\24\u00ff\3\2\2\2\26\u0101\3\2\2\2\30"+
		"\u012a\3\2\2\2\32\u012c\3\2\2\2\34\u013a\3\2\2\2\36\u013f\3\2\2\2 \u0163"+
		"\3\2\2\2\"\u016e\3\2\2\2$\u0184\3\2\2\2&\u0199\3\2\2\2(\u019d\3\2\2\2"+
		"*\u01a1\3\2\2\2,\u01b2\3\2\2\2.\u01b4\3\2\2\2\60\u01cf\3\2\2\2\62\u01d5"+
		"\3\2\2\2\64\u01db\3\2\2\2\66\u01fd\3\2\2\28\u01ff\3\2\2\2:\u0215\3\2\2"+
		"\2<\u022b\3\2\2\2>\u0230\3\2\2\2@A\t\2\2\2A\3\3\2\2\2BC\t\3\2\2C\5\3\2"+
		"\2\2DE\b\4\1\2E\u00a6\5\2\2\2F\u00a6\5\4\3\2GH\7\n\2\2HI\5\6\4\2IJ\7\t"+
		"\2\2J\u00a6\3\2\2\2K\u00a6\5$\23\2L\u00a6\5\36\20\2M\u00a6\5\"\22\2NO"+
		"\7\7\2\2O\u00a6\5\6\4\13PT\7\24\2\2QS\7-\2\2RQ\3\2\2\2SV\3\2\2\2TR\3\2"+
		"\2\2TU\3\2\2\2UW\3\2\2\2VT\3\2\2\2W[\5\6\4\2XZ\7-\2\2YX\3\2\2\2Z]\3\2"+
		"\2\2[Y\3\2\2\2[\\\3\2\2\2\\g\3\2\2\2][\3\2\2\2^h\5\36\20\2_c\7\25\2\2"+
		"`b\7-\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2df\3\2\2\2ec\3\2\2\2"+
		"fh\5\30\r\2g^\3\2\2\2g_\3\2\2\2hz\3\2\2\2ik\7-\2\2ji\3\2\2\2kn\3\2\2\2"+
		"lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2os\7\26\2\2pr\7-\2\2qp\3\2\2\2"+
		"ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tx\3\2\2\2us\3\2\2\2vy\5\36\20\2wy\5\30"+
		"\r\2xv\3\2\2\2xw\3\2\2\2y{\3\2\2\2zl\3\2\2\2z{\3\2\2\2{\u00a6\3\2\2\2"+
		"|\u0080\7\34\2\2}\177\7-\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2"+
		"\2\u0080\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0087"+
		"\5\6\4\2\u0084\u0086\7-\2\2\u0085\u0084\3\2\2\2\u0086\u0089\3\2\2\2\u0087"+
		"\u0085\3\2\2\2\u0087\u0088\3\2\2\2\u0088\u008a\3\2\2\2\u0089\u0087\3\2"+
		"\2\2\u008a\u008b\5\36\20\2\u008b\u00a6\3\2\2\2\u008c\u0090\7(\2\2\u008d"+
		"\u008f\7-\2\2\u008e\u008d\3\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2"+
		"\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093"+
		"\u0097\5\6\4\2\u0094\u0096\7-\2\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u00a1\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u009a\u009e\5\26\f\2\u009b\u009d\7-\2\2\u009c\u009b\3\2"+
		"\2\2\u009d\u00a0\3\2\2\2\u009e\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1\u009a\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5"+
		"D\3\2\2\2\u00a5F\3\2\2\2\u00a5G\3\2\2\2\u00a5K\3\2\2\2\u00a5L\3\2\2\2"+
		"\u00a5M\3\2\2\2\u00a5N\3\2\2\2\u00a5P\3\2\2\2\u00a5|\3\2\2\2\u00a5\u008c"+
		"\3\2\2\2\u00a6\u00c1\3\2\2\2\u00a7\u00a8\f\n\2\2\u00a8\u00a9\t\4\2\2\u00a9"+
		"\u00c0\5\6\4\13\u00aa\u00ab\f\t\2\2\u00ab\u00ac\t\5\2\2\u00ac\u00c0\5"+
		"\6\4\n\u00ad\u00ae\f\b\2\2\u00ae\u00af\t\6\2\2\u00af\u00c0\5\6\4\t\u00b0"+
		"\u00b1\f\7\2\2\u00b1\u00b2\t\7\2\2\u00b2\u00c0\5\6\4\b\u00b3\u00b4\f\6"+
		"\2\2\u00b4\u00b5\t\b\2\2\u00b5\u00c0\5\6\4\7\u00b6\u00b7\f\20\2\2\u00b7"+
		"\u00b8\7\b\2\2\u00b8\u00b9\t\t\2\2\u00b9\u00c0\5$\23\2\u00ba\u00bb\f\17"+
		"\2\2\u00bb\u00bc\7\b\2\2\u00bc\u00c0\7\65\2\2\u00bd\u00be\f\16\2\2\u00be"+
		"\u00c0\5$\23\2\u00bf\u00a7\3\2\2\2\u00bf\u00aa\3\2\2\2\u00bf\u00ad\3\2"+
		"\2\2\u00bf\u00b0\3\2\2\2\u00bf\u00b3\3\2\2\2\u00bf\u00b6\3\2\2\2\u00bf"+
		"\u00ba\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2"+
		"\2\2\u00c1\u00c2\3\2\2\2\u00c2\7\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5"+
		"\7*\2\2\u00c5\t\3\2\2\2\u00c6\u00c7\t\n\2\2\u00c7\13\3\2\2\2\u00c8\u00cc"+
		"\5\4\3\2\u00c9\u00cb\7-\2\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc"+
		"\u00ca\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cf\3\2\2\2\u00ce\u00cc\3\2"+
		"\2\2\u00cf\u00d3\7\37\2\2\u00d0\u00d2\7-\2\2\u00d1\u00d0\3\2\2\2\u00d2"+
		"\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2"+
		"\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\5&\24\2\u00d7\r\3\2\2\2\u00d8\u00d9"+
		"\7\30\2\2\u00d9\u00da\5\6\4\2\u00da\u00db\7\31\2\2\u00db\17\3\2\2\2\u00dc"+
		"\u00e0\5\2\2\2\u00dd\u00e0\5\n\6\2\u00de\u00e0\5\16\b\2\u00df\u00dc\3"+
		"\2\2\2\u00df\u00dd\3\2\2\2\u00df\u00de\3\2\2\2\u00e0\21\3\2\2\2\u00e1"+
		"\u00e2\5\4\3\2\u00e2\u00e3\7\20\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e1\3"+
		"\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\5&\24\2\u00e7"+
		"\u00f6\7\n\2\2\u00e8\u00f3\5\24\13\2\u00e9\u00ed\7\13\2\2\u00ea\u00ec"+
		"\7-\2\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00f0\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0\u00f2\5\24"+
		"\13\2\u00f1\u00e9\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3"+
		"\u00f4\3\2\2\2\u00f4\u00f7\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00e8\3\2"+
		"\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\t\2\2\u00f9"+
		"\23\3\2\2\2\u00fa\u0100\5\b\5\2\u00fb\u0100\5\4\3\2\u00fc\u0100\5\20\t"+
		"\2\u00fd\u0100\5\22\n\2\u00fe\u0100\5\f\7\2\u00ff\u00fa\3\2\2\2\u00ff"+
		"\u00fb\3\2\2\2\u00ff\u00fc\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u00fe\3\2"+
		"\2\2\u0100\25\3\2\2\2\u0101\u0105\7)\2\2\u0102\u0104\7-\2\2\u0103\u0102"+
		"\3\2\2\2\u0104\u0107\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106"+
		"\u0108\3\2\2\2\u0107\u0105\3\2\2\2\u0108\u010c\5\24\13\2\u0109\u010b\7"+
		"-\2\2\u010a\u0109\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u011d\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0113\7\24"+
		"\2\2\u0110\u0112\7-\2\2\u0111\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113"+
		"\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113\3\2"+
		"\2\2\u0116\u011a\5\6\4\2\u0117\u0119\7-\2\2\u0118\u0117\3\2\2\2\u0119"+
		"\u011c\3\2\2\2\u011a\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011e\3\2"+
		"\2\2\u011c\u011a\3\2\2\2\u011d\u010f\3\2\2\2\u011d\u011e\3\2\2\2\u011e"+
		"\u011f\3\2\2\2\u011f\u0123\7 \2\2\u0120\u0122\7-\2\2\u0121\u0120\3\2\2"+
		"\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0126"+
		"\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u0127\5\6\4\2\u0127\27\3\2\2\2\u0128"+
		"\u012b\5\6\4\2\u0129\u012b\5\32\16\2\u012a\u0128\3\2\2\2\u012a\u0129\3"+
		"\2\2\2\u012b\31\3\2\2\2\u012c\u0131\5\4\3\2\u012d\u012e\7\b\2\2\u012e"+
		"\u0130\5\4\3\2\u012f\u012d\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2"+
		"\2\2\u0131\u0132\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0134"+
		"\u0136\5$\23\2\u0135\u0134\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\3\2"+
		"\2\2\u0137\u0138\7\20\2\2\u0138\u0139\5\6\4\2\u0139\33\3\2\2\2\u013a\u013d"+
		"\t\3\2\2\u013b\u013c\7\37\2\2\u013c\u013e\5,\27\2\u013d\u013b\3\2\2\2"+
		"\u013d\u013e\3\2\2\2\u013e\35\3\2\2\2\u013f\u014a\7\27\2\2\u0140\u0145"+
		"\5\34\17\2\u0141\u0142\7\13\2\2\u0142\u0144\5\34\17\2\u0143\u0141\3\2"+
		"\2\2\u0144\u0147\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146"+
		"\u0148\3\2\2\2\u0147\u0145\3\2\2\2\u0148\u0149\7 \2\2\u0149\u014b\3\2"+
		"\2\2\u014a\u0140\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u014f\3\2\2\2\u014c"+
		"\u014e\7-\2\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f\u014d\3\2"+
		"\2\2\u014f\u0150\3\2\2\2\u0150\u0155\3\2\2\2\u0151\u014f\3\2\2\2\u0152"+
		"\u0154\5 \21\2\u0153\u0152\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2"+
		"\2\2\u0155\u0156\3\2\2\2\u0156\u015b\3\2\2\2\u0157\u0155\3\2\2\2\u0158"+
		"\u015a\7-\2\2\u0159\u0158\3\2\2\2\u015a\u015d\3\2\2\2\u015b\u0159\3\2"+
		"\2\2\u015b\u015c\3\2\2\2\u015c\u015e\3\2\2\2\u015d\u015b\3\2\2\2\u015e"+
		"\u015f\7\31\2\2\u015f\37\3\2\2\2\u0160\u0164\5.\30\2\u0161\u0164\5\32"+
		"\16\2\u0162\u0164\5\6\4\2\u0163\u0160\3\2\2\2\u0163\u0161\3\2\2\2\u0163"+
		"\u0162\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u0167\7\23\2\2\u0166\u0165\3"+
		"\2\2\2\u0166\u0167\3\2\2\2\u0167\u016b\3\2\2\2\u0168\u016a\7-\2\2\u0169"+
		"\u0168\3\2\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2"+
		"\2\2\u016c!\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u0177\7\"\2\2\u016f\u0174"+
		"\5\34\17\2\u0170\u0171\7\13\2\2\u0171\u0173\5\34\17\2\u0172\u0170\3\2"+
		"\2\2\u0173\u0176\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175"+
		"\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0177\u016f\3\2\2\2\u0177\u0178\3\2"+
		"\2\2\u0178\u0179\3\2\2\2\u0179\u017d\7 \2\2\u017a\u017c\7-\2\2\u017b\u017a"+
		"\3\2\2\2\u017c\u017f\3\2\2\2\u017d\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u0182\3\2\2\2\u017f\u017d\3\2\2\2\u0180\u0183\5\6\4\2\u0181\u0183\5\32"+
		"\16\2\u0182\u0180\3\2\2\2\u0182\u0181\3\2\2\2\u0183#\3\2\2\2\u0184\u0193"+
		"\7\n\2\2\u0185\u0190\5\6\4\2\u0186\u018a\7\13\2\2\u0187\u0189\7-\2\2\u0188"+
		"\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2"+
		"\2\2\u018b\u018d\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018f\5\6\4\2\u018e"+
		"\u0186\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2"+
		"\2\2\u0191\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0185\3\2\2\2\u0193"+
		"\u0194\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u0196\7\t\2\2\u0196%\3\2\2\2"+
		"\u0197\u0198\7\65\2\2\u0198\u019a\7\b\2\2\u0199\u0197\3\2\2\2\u0199\u019a"+
		"\3\2\2\2\u019a\u019b\3\2\2\2\u019b\u019c\7\65\2\2\u019c\'\3\2\2\2\u019d"+
		"\u019e\t\3\2\2\u019e\u019f\7\37\2\2\u019f\u01a0\5,\27\2\u01a0)\3\2\2\2"+
		"\u01a1\u01aa\7\n\2\2\u01a2\u01a7\5(\25\2\u01a3\u01a4\7\13\2\2\u01a4\u01a6"+
		"\5(\25\2\u01a5\u01a3\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7"+
		"\u01a8\3\2\2\2\u01a8\u01ab\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\u01a2\3\2"+
		"\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01ad\7\t\2\2\u01ad"+
		"\u01ae\7 \2\2\u01ae\u01af\5,\27\2\u01af+\3\2\2\2\u01b0\u01b3\5&\24\2\u01b1"+
		"\u01b3\5*\26\2\u01b2\u01b0\3\2\2\2\u01b2\u01b1\3\2\2\2\u01b3-\3\2\2\2"+
		"\u01b4\u01b8\t\13\2\2\u01b5\u01b7\7-\2\2\u01b6\u01b5\3\2\2\2\u01b7\u01ba"+
		"\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01bb\3\2\2\2\u01ba"+
		"\u01b8\3\2\2\2\u01bb\u01be\7\65\2\2\u01bc\u01bd\7\37\2\2\u01bd\u01bf\5"+
		",\27\2\u01be\u01bc\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c3\3\2\2\2\u01c0"+
		"\u01c2\7-\2\2\u01c1\u01c0\3\2\2\2\u01c2\u01c5\3\2\2\2\u01c3\u01c1\3\2"+
		"\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c6\3\2\2\2\u01c5\u01c3\3\2\2\2\u01c6"+
		"\u01ca\7\20\2\2\u01c7\u01c9\7-\2\2\u01c8\u01c7\3\2\2\2\u01c9\u01cc\3\2"+
		"\2\2\u01ca\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\u01cd\3\2\2\2\u01cc"+
		"\u01ca\3\2\2\2\u01cd\u01ce\5\6\4\2\u01ce/\3\2\2\2\u01cf\u01d0\7!\2\2\u01d0"+
		"\u01d1\7\65\2\2\u01d1\u01d2\7\20\2\2\u01d2\u01d3\7/\2\2\u01d3\61\3\2\2"+
		"\2\u01d4\u01d6\7#\2\2\u01d5\u01d4\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d7"+
		"\3\2\2\2\u01d7\u01d8\t\3\2\2\u01d8\u01d9\7\37\2\2\u01d9\u01da\5,\27\2"+
		"\u01da\63\3\2\2\2\u01db\u01dc\7!\2\2\u01dc\u01dd\7\65\2\2\u01dd\u01de"+
		"\7\20\2\2\u01de\u01e2\7\n\2\2\u01df\u01e1\7-\2\2\u01e0\u01df\3\2\2\2\u01e1"+
		"\u01e4\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e3\3\2\2\2\u01e3\u01e5\3\2"+
		"\2\2\u01e4\u01e2\3\2\2\2\u01e5\u01f0\5\62\32\2\u01e6\u01ea\7\13\2\2\u01e7"+
		"\u01e9\7-\2\2\u01e8\u01e7\3\2\2\2\u01e9\u01ec\3\2\2\2\u01ea\u01e8\3\2"+
		"\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ed"+
		"\u01ef\5\62\32\2\u01ee\u01e6\3\2\2\2\u01ef\u01f2\3\2\2\2\u01f0\u01ee\3"+
		"\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f6\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f3"+
		"\u01f5\7-\2\2\u01f4\u01f3\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6\u01f4\3\2"+
		"\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f9"+
		"\u01fa\7\t\2\2\u01fa\65\3\2\2\2\u01fb\u01fe\5\60\31\2\u01fc\u01fe\5\64"+
		"\33\2\u01fd\u01fb\3\2\2\2\u01fd\u01fc\3\2\2\2\u01fe\67\3\2\2\2\u01ff\u0200"+
		"\7%\2\2\u0200\u0203\t\f\2\2\u0201\u0202\7\37\2\2\u0202\u0204\5*\26\2\u0203"+
		"\u0201\3\2\2\2\u0203\u0204\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0209\7\20"+
		"\2\2\u0206\u0208\7-\2\2\u0207\u0206\3\2\2\2\u0208\u020b\3\2\2\2\u0209"+
		"\u0207\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u0213\3\2\2\2\u020b\u0209\3\2"+
		"\2\2\u020c\u0214\5\"\22\2\u020d\u020e\5\36\20\2\u020e\u020f\7\37\2\2\u020f"+
		"\u0210\5,\27\2\u0210\u0214\3\2\2\2\u0211\u0214\7/\2\2\u0212\u0214\5\6"+
		"\4\2\u0213\u020c\3\2\2\2\u0213\u020d\3\2\2\2\u0213\u0211\3\2\2\2\u0213"+
		"\u0212\3\2\2\2\u02149\3\2\2\2\u0215\u0216\7&\2\2\u0216\u021b\7\65\2\2"+
		"\u0217\u0218\7\b\2\2\u0218\u021a\7\65\2\2\u0219\u0217\3\2\2\2\u021a\u021d"+
		"\3\2\2\2\u021b\u0219\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u0227\3\2\2\2\u021d"+
		"\u021b\3\2\2\2\u021e\u021f\7\'\2\2\u021f\u0224\7\65\2\2\u0220\u0221\7"+
		"\13\2\2\u0221\u0223\7\65\2\2\u0222\u0220\3\2\2\2\u0223\u0226\3\2\2\2\u0224"+
		"\u0222\3\2\2\2\u0224\u0225\3\2\2\2\u0225\u0228\3\2\2\2\u0226\u0224\3\2"+
		"\2\2\u0227\u021e\3\2\2\2\u0227\u0228\3\2\2\2\u0228;\3\2\2\2\u0229\u022c"+
		"\5\66\34\2\u022a\u022c\58\35\2\u022b\u0229\3\2\2\2\u022b\u022a\3\2\2\2"+
		"\u022c=\3\2\2\2\u022d\u022f\7-\2\2\u022e\u022d\3\2\2\2\u022f\u0232\3\2"+
		"\2\2\u0230\u022e\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0245\3\2\2\2\u0232"+
		"\u0230\3\2\2\2\u0233\u023c\5:\36\2\u0234\u0236\7-\2\2\u0235\u0234\3\2"+
		"\2\2\u0236\u0237\3\2\2\2\u0237\u0235\3\2\2\2\u0237\u0238\3\2\2\2\u0238"+
		"\u0239\3\2\2\2\u0239\u023b\5:\36\2\u023a\u0235\3\2\2\2\u023b\u023e\3\2"+
		"\2\2\u023c\u023a\3\2\2\2\u023c\u023d\3\2\2\2\u023d\u0242\3\2\2\2\u023e"+
		"\u023c\3\2\2\2\u023f\u0241\7-\2\2\u0240\u023f\3\2\2\2\u0241\u0244\3\2"+
		"\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2\2\2\u0243\u0246\3\2\2\2\u0244"+
		"\u0242\3\2\2\2\u0245\u0233\3\2\2\2\u0245\u0246\3\2\2\2\u0246\u0259\3\2"+
		"\2\2\u0247\u0250\5<\37\2\u0248\u024a\7-\2\2\u0249\u0248\3\2\2\2\u024a"+
		"\u024b\3\2\2\2\u024b\u0249\3\2\2\2\u024b\u024c\3\2\2\2\u024c\u024d\3\2"+
		"\2\2\u024d\u024f\5<\37\2\u024e\u0249\3\2\2\2\u024f\u0252\3\2\2\2\u0250"+
		"\u024e\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0256\3\2\2\2\u0252\u0250\3\2"+
		"\2\2\u0253\u0255\7-\2\2\u0254\u0253\3\2\2\2\u0255\u0258\3\2\2\2\u0256"+
		"\u0254\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u025a\3\2\2\2\u0258\u0256\3\2"+
		"\2\2\u0259\u0247\3\2\2\2\u0259\u025a\3\2\2\2\u025a?\3\2\2\2RT[cglsxz\u0080"+
		"\u0087\u0090\u0097\u009e\u00a3\u00a5\u00bf\u00c1\u00cc\u00d3\u00df\u00e4"+
		"\u00ed\u00f3\u00f6\u00ff\u0105\u010c\u0113\u011a\u011d\u0123\u012a\u0131"+
		"\u0135\u013d\u0145\u014a\u014f\u0155\u015b\u0163\u0166\u016b\u0174\u0177"+
		"\u017d\u0182\u018a\u0190\u0193\u0199\u01a7\u01aa\u01b2\u01b8\u01be\u01c3"+
		"\u01ca\u01d5\u01e2\u01ea\u01f0\u01f6\u01fd\u0203\u0209\u0213\u021b\u0224"+
		"\u0227\u022b\u0230\u0237\u023c\u0242\u0245\u024b\u0250\u0256\u0259";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}