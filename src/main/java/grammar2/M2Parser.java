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
		VERT_LINE=41, LlBegin=42, WS=43, NL=44, COMMENT=45, LlLiteral=46, IntLiteral=47, 
		HexLiteral=48, FloatLiteral=49, BooleanLiteral=50, StringLiteral=51, Id=52, 
		MatchId=53, IrInline=54, LlEnd=55;
	public static final int
		RULE_literal = 0, RULE_id = 1, RULE_expression = 2, RULE_matchDash = 3, 
		RULE_matchId = 4, RULE_matchType = 5, RULE_matchBracketsExpr = 6, RULE_matchExpression = 7, 
		RULE_destruct = 8, RULE_bindVar = 9, RULE_matchOver = 10, RULE_matchCase = 11, 
		RULE_if_stat = 12, RULE_store = 13, RULE_fnArg = 14, RULE_block = 15, 
		RULE_blockBody = 16, RULE_lambdaBlock = 17, RULE_tuple = 18, RULE_scalarTypeHint = 19, 
		RULE_fnTypeHintField = 20, RULE_fnTypeHint = 21, RULE_typeHint = 22, RULE_variable = 23, 
		RULE_scalarType = 24, RULE_typeField = 25, RULE_factorType = 26, RULE_unionType = 27, 
		RULE_type = 28, RULE_function = 29, RULE_import_ = 30, RULE_level1 = 31, 
		RULE_module = 32;
	public static final String[] ruleNames = {
		"literal", "id", "expression", "matchDash", "matchId", "matchType", "matchBracketsExpr", 
		"matchExpression", "destruct", "bindVar", "matchOver", "matchCase", "if_stat", 
		"store", "fnArg", "block", "blockBody", "lambdaBlock", "tuple", "scalarTypeHint", 
		"fnTypeHintField", "fnTypeHint", "typeHint", "variable", "scalarType", 
		"typeField", "factorType", "unionType", "type", "function", "import_", 
		"level1", "module"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", "'.'", "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'then'", 
		"'else'", "'{'", "'${'", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", 
		"':'", "'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'import'", 
		"'with'", "'match'", "'of'", "'_'", "'|'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "THEN", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
		"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
		"MATCH_SELF", "DEF", "IMPORT", "WITH", "MATCH", "OF", "DASH", "VERT_LINE", 
		"LlBegin", "WS", "NL", "COMMENT", "LlLiteral", "IntLiteral", "HexLiteral", 
		"FloatLiteral", "BooleanLiteral", "StringLiteral", "Id", "MatchId", "IrInline", 
		"LlEnd"
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
			setState(66);
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
			setState(68);
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
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
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
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(73);
				match(LB);
				setState(74);
				expression(0);
				setState(75);
				match(RB);
				}
				break;
			case 4:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(77);
				tuple();
				}
				break;
			case 5:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(78);
				block();
				}
				break;
			case 6:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(79);
				lambdaBlock();
				}
				break;
			case 7:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(80);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(81);
				expression(9);
				}
				break;
			case 8:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				match(IF);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(83);
					match(NL);
					}
					}
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(89);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(90);
					match(NL);
					}
					}
					setState(95);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(105);
				switch (_input.LA(1)) {
				case CBO:
					{
					setState(96);
					((ExprIfElseContext)_localctx).then_block = block();
					}
					break;
				case THEN:
					{
					{
					setState(97);
					match(THEN);
					setState(101);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(98);
						match(NL);
						}
						}
						setState(103);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(104);
					((ExprIfElseContext)_localctx).then_stat = if_stat();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(124);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(110);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(107);
						match(NL);
						}
						}
						setState(112);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(113);
					match(ELSE);
					setState(117);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(114);
						match(NL);
						}
						}
						setState(119);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(122);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(120);
						((ExprIfElseContext)_localctx).else_block = block();
						}
						break;
					case 2:
						{
						setState(121);
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
				setState(126);
				match(WHILE);
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(127);
					match(NL);
					}
					}
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(133);
				((ExprWhileContext)_localctx).cond = expression(0);
				setState(137);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(134);
					match(NL);
					}
					}
					setState(139);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(140);
				((ExprWhileContext)_localctx).then_block = block();
				}
				break;
			case 10:
				{
				_localctx = new ExprMatchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				match(MATCH);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(143);
					match(NL);
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(149);
				((ExprMatchContext)_localctx).expr = expression(0);
				setState(153);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(150);
					match(NL);
					}
					}
					setState(155);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(163); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(156);
						matchCase();
						setState(160);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(157);
								match(NL);
								}
								} 
							}
							setState(162);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(165); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(195);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(193);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(169);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(170);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(171);
						expression(9);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(172);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(173);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << Id))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(174);
						expression(8);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(175);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(176);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(177);
						expression(7);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(178);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(179);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQEQ || _la==NOTEQ) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(180);
						expression(6);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(182);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LOGIC_OR || _la==LOGIC_AND) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(183);
						expression(5);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(185);
						match(DOT);
						setState(186);
						((ExprSelfCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << Id))) != 0)) ) {
							((ExprSelfCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(187);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(188);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(189);
						match(DOT);
						setState(190);
						((ExprPropContext)_localctx).op = match(Id);
						}
						break;
					case 8:
						{
						_localctx = new ExprApplyContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(191);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(192);
						tuple();
						}
						break;
					}
					} 
				}
				setState(197);
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
			setState(198);
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
			setState(200);
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
			setState(202);
			id();
			setState(206);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(203);
				match(NL);
				}
				}
				setState(208);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(209);
			match(CON);
			setState(213);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(210);
				match(NL);
				}
				}
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(216);
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
			setState(218);
			match(DOLLAR_CBO);
			setState(219);
			expression(0);
			setState(220);
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
			setState(225);
			switch (_input.LA(1)) {
			case IntLiteral:
			case HexLiteral:
			case FloatLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(222);
				literal();
				}
				break;
			case MATCH_SELF:
			case MatchId:
				enterOuterAlt(_localctx, 2);
				{
				setState(223);
				matchId();
				}
				break;
			case DOLLAR_CBO:
				enterOuterAlt(_localctx, 3);
				{
				setState(224);
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
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(227);
				id();
				setState(228);
				match(EQ);
				}
				break;
			}
			setState(232);
			scalarTypeHint();
			setState(233);
			match(LB);
			setState(248);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DOLLAR_CBO) | (1L << SELF) | (1L << MATCH_SELF) | (1L << DASH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id) | (1L << MatchId))) != 0)) {
				{
				setState(234);
				matchOver();
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(235);
					match(COMMA);
					setState(239);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(236);
						match(NL);
						}
						}
						setState(241);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(242);
					matchOver();
					}
					}
					setState(247);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(250);
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

	public static class BindVarContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public BindVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bindVar; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitBindVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BindVarContext bindVar() throws RecognitionException {
		BindVarContext _localctx = new BindVarContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bindVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			id();
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
		public BindVarContext bindVar() {
			return getRuleContext(BindVarContext.class,0);
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
		enterRule(_localctx, 20, RULE_matchOver);
		try {
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(254);
				matchDash();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(255);
				bindVar();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(256);
				matchExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(257);
				destruct();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(258);
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
		enterRule(_localctx, 22, RULE_matchCase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(OF);
			setState(265);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(262);
				match(NL);
				}
				}
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(268);
			matchOver();
			setState(272);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(269);
				match(NL);
				}
				}
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(289);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(275);
				match(IF);
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(276);
					match(NL);
					}
					}
					setState(281);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(282);
				((MatchCaseContext)_localctx).cond = expression(0);
				setState(286);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(283);
					match(NL);
					}
					}
					setState(288);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(291);
			match(ARROW_RIGHT);
			setState(295);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(292);
				match(NL);
				}
				}
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(298);
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
		enterRule(_localctx, 24, RULE_if_stat);
		try {
			setState(302);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,31,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(300);
				expression(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(301);
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
		enterRule(_localctx, 26, RULE_store);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			id();
			setState(309);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(305);
				match(DOT);
				setState(306);
				id();
				}
				}
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(313);
			_la = _input.LA(1);
			if (_la==LB) {
				{
				setState(312);
				tuple();
				}
			}

			setState(315);
			match(EQ);
			setState(316);
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
		enterRule(_localctx, 28, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(321);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(319);
				match(CON);
				setState(320);
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
		enterRule(_localctx, 30, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			match(CBO);
			setState(334);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(324);
				fnArg();
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(325);
					match(COMMA);
					setState(326);
					fnArg();
					}
					}
					setState(331);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(332);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(339);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(336);
					match(NL);
					}
					} 
				}
				setState(341);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,37,_ctx);
			}
			setState(345);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << VAL) | (1L << VAR) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) {
				{
				{
				setState(342);
				blockBody();
				}
				}
				setState(347);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(351);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(348);
				match(NL);
				}
				}
				setState(353);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(354);
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
		enterRule(_localctx, 32, RULE_blockBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(359);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				{
				setState(356);
				variable();
				}
				break;
			case 2:
				{
				setState(357);
				store();
				}
				break;
			case 3:
				{
				setState(358);
				expression(0);
				}
				break;
			}
			setState(362);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(361);
				match(SEMI);
				}
			}

			setState(367);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,42,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(364);
					match(NL);
					}
					} 
				}
				setState(369);
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
		enterRule(_localctx, 34, RULE_lambdaBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(BACK_SLASH);
			setState(379);
			_la = _input.LA(1);
			if (_la==SELF || _la==Id) {
				{
				setState(371);
				fnArg();
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(372);
					match(COMMA);
					setState(373);
					fnArg();
					}
					}
					setState(378);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(381);
			match(ARROW_RIGHT);
			setState(385);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(382);
				match(NL);
				}
				}
				setState(387);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(390);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(388);
				expression(0);
				}
				break;
			case 2:
				{
				setState(389);
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
		enterRule(_localctx, 36, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			match(LB);
			setState(407);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) {
				{
				setState(393);
				expression(0);
				setState(404);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(394);
					match(COMMA);
					setState(398);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(395);
						match(NL);
						}
						}
						setState(400);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(401);
					expression(0);
					}
					}
					setState(406);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(409);
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
		enterRule(_localctx, 38, RULE_scalarTypeHint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(413);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(411);
				((ScalarTypeHintContext)_localctx).modVar = match(Id);
				setState(412);
				match(DOT);
				}
				break;
			}
			setState(415);
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
		enterRule(_localctx, 40, RULE_fnTypeHintField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(417);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(418);
			match(CON);
			setState(419);
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
		enterRule(_localctx, 42, RULE_fnTypeHint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(421);
			match(LB);
			setState(430);
			_la = _input.LA(1);
			if (_la==SELF || _la==Id) {
				{
				setState(422);
				fnTypeHintField();
				setState(427);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(423);
					match(COMMA);
					setState(424);
					fnTypeHintField();
					}
					}
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(432);
			match(RB);
			setState(433);
			match(ARROW_RIGHT);
			setState(434);
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
		enterRule(_localctx, 44, RULE_typeHint);
		try {
			setState(438);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(436);
				scalarTypeHint();
				}
				break;
			case LB:
				enterOuterAlt(_localctx, 2);
				{
				setState(437);
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
		enterRule(_localctx, 46, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			((VariableContext)_localctx).valVar = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==VAL || _la==VAR) ) {
				((VariableContext)_localctx).valVar = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(444);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(441);
				match(NL);
				}
				}
				setState(446);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(447);
			match(Id);
			setState(450);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(448);
				match(CON);
				setState(449);
				typeHint();
				}
			}

			setState(455);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(452);
				match(NL);
				}
				}
				setState(457);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(458);
			match(EQ);
			setState(462);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(459);
				match(NL);
				}
				}
				setState(464);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(465);
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
		enterRule(_localctx, 48, RULE_scalarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(467);
			match(TYPE);
			setState(468);
			match(Id);
			setState(469);
			match(EQ);
			setState(470);
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
		enterRule(_localctx, 50, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(473);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(472);
				match(SELF);
				}
				break;
			}
			setState(475);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(476);
			match(CON);
			setState(477);
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
		enterRule(_localctx, 52, RULE_factorType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(479);
			match(TYPE);
			setState(480);
			match(Id);
			setState(481);
			match(EQ);
			setState(482);
			match(LB);
			setState(486);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(483);
				match(NL);
				}
				}
				setState(488);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(489);
			typeField();
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(490);
				match(COMMA);
				setState(494);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(491);
					match(NL);
					}
					}
					setState(496);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(497);
				typeField();
				}
				}
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(506);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(503);
				match(NL);
				}
				}
				setState(508);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(509);
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
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public List<ScalarTypeHintContext> scalarTypeHint() {
			return getRuleContexts(ScalarTypeHintContext.class);
		}
		public ScalarTypeHintContext scalarTypeHint(int i) {
			return getRuleContext(ScalarTypeHintContext.class,i);
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
		enterRule(_localctx, 54, RULE_unionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			match(TYPE);
			setState(512);
			match(Id);
			setState(513);
			match(EQ);
			setState(514);
			scalarTypeHint();
			setState(517); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(515);
				match(VERT_LINE);
				setState(516);
				scalarTypeHint();
				}
				}
				setState(519); 
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
		public FactorTypeContext factorType() {
			return getRuleContext(FactorTypeContext.class,0);
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
		enterRule(_localctx, 56, RULE_type);
		try {
			setState(524);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,64,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(521);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(522);
				factorType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(523);
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
		enterRule(_localctx, 58, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(526);
			match(DEF);
			setState(527);
			((FunctionContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << EXCL) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << SELF) | (1L << Id))) != 0)) ) {
				((FunctionContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(530);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(528);
				match(CON);
				setState(529);
				fnTypeHint();
				}
			}

			setState(532);
			match(EQ);
			setState(536);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(533);
				match(NL);
				}
				}
				setState(538);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(546);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				setState(539);
				lambdaBlock();
				}
				break;
			case 2:
				{
				{
				setState(540);
				block();
				setState(541);
				match(CON);
				setState(542);
				typeHint();
				}
				}
				break;
			case 3:
				{
				setState(544);
				match(LlLiteral);
				}
				break;
			case 4:
				{
				setState(545);
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
		enterRule(_localctx, 60, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(548);
			match(IMPORT);
			setState(549);
			((Import_Context)_localctx).Id = match(Id);
			((Import_Context)_localctx).pkgName.add(((Import_Context)_localctx).Id);
			setState(554);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(550);
				match(DOT);
				setState(551);
				((Import_Context)_localctx).Id = match(Id);
				((Import_Context)_localctx).pkgName.add(((Import_Context)_localctx).Id);
				}
				}
				setState(556);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(566);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(557);
				match(WITH);
				setState(558);
				((Import_Context)_localctx).Id = match(Id);
				((Import_Context)_localctx).tid.add(((Import_Context)_localctx).Id);
				setState(563);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(559);
					match(COMMA);
					setState(560);
					((Import_Context)_localctx).Id = match(Id);
					((Import_Context)_localctx).tid.add(((Import_Context)_localctx).Id);
					}
					}
					setState(565);
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
		enterRule(_localctx, 62, RULE_level1);
		try {
			setState(570);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(568);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(569);
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
		enterRule(_localctx, 64, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(575);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(572);
				match(NL);
				}
				}
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(596);
			_la = _input.LA(1);
			if (_la==IMPORT) {
				{
				setState(578);
				import_();
				setState(587);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(580); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(579);
							match(NL);
							}
							}
							setState(582); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(584);
						import_();
						}
						} 
					}
					setState(589);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,74,_ctx);
				}
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(590);
					match(NL);
					}
					}
					setState(595);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(616);
			_la = _input.LA(1);
			if (_la==TYPE || _la==DEF) {
				{
				setState(598);
				level1();
				setState(607);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(600); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(599);
							match(NL);
							}
							}
							setState(602); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(604);
						level1();
						}
						} 
					}
					setState(609);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,78,_ctx);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\39\u026d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\7\4W\n\4\f\4\16\4Z\13\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13\4"+
		"\3\4\3\4\3\4\7\4f\n\4\f\4\16\4i\13\4\3\4\5\4l\n\4\3\4\7\4o\n\4\f\4\16"+
		"\4r\13\4\3\4\3\4\7\4v\n\4\f\4\16\4y\13\4\3\4\3\4\5\4}\n\4\5\4\177\n\4"+
		"\3\4\3\4\7\4\u0083\n\4\f\4\16\4\u0086\13\4\3\4\3\4\7\4\u008a\n\4\f\4\16"+
		"\4\u008d\13\4\3\4\3\4\3\4\3\4\7\4\u0093\n\4\f\4\16\4\u0096\13\4\3\4\3"+
		"\4\7\4\u009a\n\4\f\4\16\4\u009d\13\4\3\4\3\4\7\4\u00a1\n\4\f\4\16\4\u00a4"+
		"\13\4\6\4\u00a6\n\4\r\4\16\4\u00a7\5\4\u00aa\n\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\7\4\u00c4\n\4\f\4\16\4\u00c7\13\4\3\5\3\5\3\6\3\6\3\7\3\7\7\7\u00cf"+
		"\n\7\f\7\16\7\u00d2\13\7\3\7\3\7\7\7\u00d6\n\7\f\7\16\7\u00d9\13\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\5\t\u00e4\n\t\3\n\3\n\3\n\5\n\u00e9\n"+
		"\n\3\n\3\n\3\n\3\n\3\n\7\n\u00f0\n\n\f\n\16\n\u00f3\13\n\3\n\7\n\u00f6"+
		"\n\n\f\n\16\n\u00f9\13\n\5\n\u00fb\n\n\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\5\f\u0106\n\f\3\r\3\r\7\r\u010a\n\r\f\r\16\r\u010d\13\r\3\r\3\r"+
		"\7\r\u0111\n\r\f\r\16\r\u0114\13\r\3\r\3\r\7\r\u0118\n\r\f\r\16\r\u011b"+
		"\13\r\3\r\3\r\7\r\u011f\n\r\f\r\16\r\u0122\13\r\5\r\u0124\n\r\3\r\3\r"+
		"\7\r\u0128\n\r\f\r\16\r\u012b\13\r\3\r\3\r\3\16\3\16\5\16\u0131\n\16\3"+
		"\17\3\17\3\17\7\17\u0136\n\17\f\17\16\17\u0139\13\17\3\17\5\17\u013c\n"+
		"\17\3\17\3\17\3\17\3\20\3\20\3\20\5\20\u0144\n\20\3\21\3\21\3\21\3\21"+
		"\7\21\u014a\n\21\f\21\16\21\u014d\13\21\3\21\3\21\5\21\u0151\n\21\3\21"+
		"\7\21\u0154\n\21\f\21\16\21\u0157\13\21\3\21\7\21\u015a\n\21\f\21\16\21"+
		"\u015d\13\21\3\21\7\21\u0160\n\21\f\21\16\21\u0163\13\21\3\21\3\21\3\22"+
		"\3\22\3\22\5\22\u016a\n\22\3\22\5\22\u016d\n\22\3\22\7\22\u0170\n\22\f"+
		"\22\16\22\u0173\13\22\3\23\3\23\3\23\3\23\7\23\u0179\n\23\f\23\16\23\u017c"+
		"\13\23\5\23\u017e\n\23\3\23\3\23\7\23\u0182\n\23\f\23\16\23\u0185\13\23"+
		"\3\23\3\23\5\23\u0189\n\23\3\24\3\24\3\24\3\24\7\24\u018f\n\24\f\24\16"+
		"\24\u0192\13\24\3\24\7\24\u0195\n\24\f\24\16\24\u0198\13\24\5\24\u019a"+
		"\n\24\3\24\3\24\3\25\3\25\5\25\u01a0\n\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\27\3\27\3\27\3\27\7\27\u01ac\n\27\f\27\16\27\u01af\13\27\5\27\u01b1"+
		"\n\27\3\27\3\27\3\27\3\27\3\30\3\30\5\30\u01b9\n\30\3\31\3\31\7\31\u01bd"+
		"\n\31\f\31\16\31\u01c0\13\31\3\31\3\31\3\31\5\31\u01c5\n\31\3\31\7\31"+
		"\u01c8\n\31\f\31\16\31\u01cb\13\31\3\31\3\31\7\31\u01cf\n\31\f\31\16\31"+
		"\u01d2\13\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\5\33\u01dc\n\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\7\34\u01e7\n\34\f\34\16\34"+
		"\u01ea\13\34\3\34\3\34\3\34\7\34\u01ef\n\34\f\34\16\34\u01f2\13\34\3\34"+
		"\7\34\u01f5\n\34\f\34\16\34\u01f8\13\34\3\34\7\34\u01fb\n\34\f\34\16\34"+
		"\u01fe\13\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\6\35\u0208\n\35\r"+
		"\35\16\35\u0209\3\36\3\36\3\36\5\36\u020f\n\36\3\37\3\37\3\37\3\37\5\37"+
		"\u0215\n\37\3\37\3\37\7\37\u0219\n\37\f\37\16\37\u021c\13\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\5\37\u0225\n\37\3 \3 \3 \3 \7 \u022b\n \f \16"+
		" \u022e\13 \3 \3 \3 \3 \7 \u0234\n \f \16 \u0237\13 \5 \u0239\n \3!\3"+
		"!\5!\u023d\n!\3\"\7\"\u0240\n\"\f\"\16\"\u0243\13\"\3\"\3\"\6\"\u0247"+
		"\n\"\r\"\16\"\u0248\3\"\7\"\u024c\n\"\f\"\16\"\u024f\13\"\3\"\7\"\u0252"+
		"\n\"\f\"\16\"\u0255\13\"\5\"\u0257\n\"\3\"\3\"\6\"\u025b\n\"\r\"\16\""+
		"\u025c\3\"\7\"\u0260\n\"\f\"\16\"\u0263\13\"\3\"\7\"\u0266\n\"\f\"\16"+
		"\"\u0269\13\"\5\"\u026b\n\"\3\"\2\3\6#\2\4\6\b\n\f\16\20\22\24\26\30\32"+
		"\34\36 \"$&(*,.\60\62\64\668:<>@B\2\r\3\2\61\65\4\2##\66\66\3\2\5\6\4"+
		"\2\3\4\66\66\3\2\f\17\3\2\21\22\3\2\32\33\7\2\3\6\f\17\21\22\32\33\66"+
		"\66\4\2$$\67\67\3\2\35\36\b\2\3\7\f\17\21\22\32\33##\66\66\u02b2\2D\3"+
		"\2\2\2\4F\3\2\2\2\6\u00a9\3\2\2\2\b\u00c8\3\2\2\2\n\u00ca\3\2\2\2\f\u00cc"+
		"\3\2\2\2\16\u00dc\3\2\2\2\20\u00e3\3\2\2\2\22\u00e8\3\2\2\2\24\u00fe\3"+
		"\2\2\2\26\u0105\3\2\2\2\30\u0107\3\2\2\2\32\u0130\3\2\2\2\34\u0132\3\2"+
		"\2\2\36\u0140\3\2\2\2 \u0145\3\2\2\2\"\u0169\3\2\2\2$\u0174\3\2\2\2&\u018a"+
		"\3\2\2\2(\u019f\3\2\2\2*\u01a3\3\2\2\2,\u01a7\3\2\2\2.\u01b8\3\2\2\2\60"+
		"\u01ba\3\2\2\2\62\u01d5\3\2\2\2\64\u01db\3\2\2\2\66\u01e1\3\2\2\28\u0201"+
		"\3\2\2\2:\u020e\3\2\2\2<\u0210\3\2\2\2>\u0226\3\2\2\2@\u023c\3\2\2\2B"+
		"\u0241\3\2\2\2DE\t\2\2\2E\3\3\2\2\2FG\t\3\2\2G\5\3\2\2\2HI\b\4\1\2I\u00aa"+
		"\5\2\2\2J\u00aa\5\4\3\2KL\7\n\2\2LM\5\6\4\2MN\7\t\2\2N\u00aa\3\2\2\2O"+
		"\u00aa\5&\24\2P\u00aa\5 \21\2Q\u00aa\5$\23\2RS\7\7\2\2S\u00aa\5\6\4\13"+
		"TX\7\24\2\2UW\7.\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2"+
		"ZX\3\2\2\2[_\5\6\4\2\\^\7.\2\2]\\\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2"+
		"\2`k\3\2\2\2a_\3\2\2\2bl\5 \21\2cg\7\25\2\2df\7.\2\2ed\3\2\2\2fi\3\2\2"+
		"\2ge\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jl\5\32\16\2kb\3\2\2\2kc\3\2"+
		"\2\2l~\3\2\2\2mo\7.\2\2nm\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2qs\3\2"+
		"\2\2rp\3\2\2\2sw\7\26\2\2tv\7.\2\2ut\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2"+
		"\2\2x|\3\2\2\2yw\3\2\2\2z}\5 \21\2{}\5\32\16\2|z\3\2\2\2|{\3\2\2\2}\177"+
		"\3\2\2\2~p\3\2\2\2~\177\3\2\2\2\177\u00aa\3\2\2\2\u0080\u0084\7\34\2\2"+
		"\u0081\u0083\7.\2\2\u0082\u0081\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082"+
		"\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087"+
		"\u008b\5\6\4\2\u0088\u008a\7.\2\2\u0089\u0088\3\2\2\2\u008a\u008d\3\2"+
		"\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u008e\3\2\2\2\u008d"+
		"\u008b\3\2\2\2\u008e\u008f\5 \21\2\u008f\u00aa\3\2\2\2\u0090\u0094\7("+
		"\2\2\u0091\u0093\7.\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0094\3\2"+
		"\2\2\u0097\u009b\5\6\4\2\u0098\u009a\7.\2\2\u0099\u0098\3\2\2\2\u009a"+
		"\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u00a5\3\2"+
		"\2\2\u009d\u009b\3\2\2\2\u009e\u00a2\5\30\r\2\u009f\u00a1\7.\2\2\u00a0"+
		"\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u009e\3\2\2\2\u00a6"+
		"\u00a7\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00aa\3\2"+
		"\2\2\u00a9H\3\2\2\2\u00a9J\3\2\2\2\u00a9K\3\2\2\2\u00a9O\3\2\2\2\u00a9"+
		"P\3\2\2\2\u00a9Q\3\2\2\2\u00a9R\3\2\2\2\u00a9T\3\2\2\2\u00a9\u0080\3\2"+
		"\2\2\u00a9\u0090\3\2\2\2\u00aa\u00c5\3\2\2\2\u00ab\u00ac\f\n\2\2\u00ac"+
		"\u00ad\t\4\2\2\u00ad\u00c4\5\6\4\13\u00ae\u00af\f\t\2\2\u00af\u00b0\t"+
		"\5\2\2\u00b0\u00c4\5\6\4\n\u00b1\u00b2\f\b\2\2\u00b2\u00b3\t\6\2\2\u00b3"+
		"\u00c4\5\6\4\t\u00b4\u00b5\f\7\2\2\u00b5\u00b6\t\7\2\2\u00b6\u00c4\5\6"+
		"\4\b\u00b7\u00b8\f\6\2\2\u00b8\u00b9\t\b\2\2\u00b9\u00c4\5\6\4\7\u00ba"+
		"\u00bb\f\20\2\2\u00bb\u00bc\7\b\2\2\u00bc\u00bd\t\t\2\2\u00bd\u00c4\5"+
		"&\24\2\u00be\u00bf\f\17\2\2\u00bf\u00c0\7\b\2\2\u00c0\u00c4\7\66\2\2\u00c1"+
		"\u00c2\f\16\2\2\u00c2\u00c4\5&\24\2\u00c3\u00ab\3\2\2\2\u00c3\u00ae\3"+
		"\2\2\2\u00c3\u00b1\3\2\2\2\u00c3\u00b4\3\2\2\2\u00c3\u00b7\3\2\2\2\u00c3"+
		"\u00ba\3\2\2\2\u00c3\u00be\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c7\3\2"+
		"\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\7\3\2\2\2\u00c7\u00c5"+
		"\3\2\2\2\u00c8\u00c9\7*\2\2\u00c9\t\3\2\2\2\u00ca\u00cb\t\n\2\2\u00cb"+
		"\13\3\2\2\2\u00cc\u00d0\5\4\3\2\u00cd\u00cf\7.\2\2\u00ce\u00cd\3\2\2\2"+
		"\u00cf\u00d2\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d3"+
		"\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d7\7\37\2\2\u00d4\u00d6\7.\2\2\u00d5"+
		"\u00d4\3\2\2\2\u00d6\u00d9\3\2\2\2\u00d7\u00d5\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00da\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00db\5(\25\2\u00db"+
		"\r\3\2\2\2\u00dc\u00dd\7\30\2\2\u00dd\u00de\5\6\4\2\u00de\u00df\7\31\2"+
		"\2\u00df\17\3\2\2\2\u00e0\u00e4\5\2\2\2\u00e1\u00e4\5\n\6\2\u00e2\u00e4"+
		"\5\16\b\2\u00e3\u00e0\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e2\3\2\2\2"+
		"\u00e4\21\3\2\2\2\u00e5\u00e6\5\4\3\2\u00e6\u00e7\7\20\2\2\u00e7\u00e9"+
		"\3\2\2\2\u00e8\u00e5\3\2\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00eb\5(\25\2\u00eb\u00fa\7\n\2\2\u00ec\u00f7\5\26\f\2\u00ed\u00f1\7"+
		"\13\2\2\u00ee\u00f0\7.\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f4\u00f6\5\26\f\2\u00f5\u00ed\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7"+
		"\u00f5\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2"+
		"\2\2\u00fa\u00ec\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc"+
		"\u00fd\7\t\2\2\u00fd\23\3\2\2\2\u00fe\u00ff\5\4\3\2\u00ff\25\3\2\2\2\u0100"+
		"\u0106\5\b\5\2\u0101\u0106\5\24\13\2\u0102\u0106\5\20\t\2\u0103\u0106"+
		"\5\22\n\2\u0104\u0106\5\f\7\2\u0105\u0100\3\2\2\2\u0105\u0101\3\2\2\2"+
		"\u0105\u0102\3\2\2\2\u0105\u0103\3\2\2\2\u0105\u0104\3\2\2\2\u0106\27"+
		"\3\2\2\2\u0107\u010b\7)\2\2\u0108\u010a\7.\2\2\u0109\u0108\3\2\2\2\u010a"+
		"\u010d\3\2\2\2\u010b\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010e\3\2"+
		"\2\2\u010d\u010b\3\2\2\2\u010e\u0112\5\26\f\2\u010f\u0111\7.\2\2\u0110"+
		"\u010f\3\2\2\2\u0111\u0114\3\2\2\2\u0112\u0110\3\2\2\2\u0112\u0113\3\2"+
		"\2\2\u0113\u0123\3\2\2\2\u0114\u0112\3\2\2\2\u0115\u0119\7\24\2\2\u0116"+
		"\u0118\7.\2\2\u0117\u0116\3\2\2\2\u0118\u011b\3\2\2\2\u0119\u0117\3\2"+
		"\2\2\u0119\u011a\3\2\2\2\u011a\u011c\3\2\2\2\u011b\u0119\3\2\2\2\u011c"+
		"\u0120\5\6\4\2\u011d\u011f\7.\2\2\u011e\u011d\3\2\2\2\u011f\u0122\3\2"+
		"\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0124\3\2\2\2\u0122"+
		"\u0120\3\2\2\2\u0123\u0115\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125\u0129\7 \2\2\u0126\u0128\7.\2\2\u0127\u0126\3\2\2\2\u0128\u012b"+
		"\3\2\2\2\u0129\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a\u012c\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012c\u012d\5\6\4\2\u012d\31\3\2\2\2\u012e\u0131\5\6\4"+
		"\2\u012f\u0131\5\34\17\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0131"+
		"\33\3\2\2\2\u0132\u0137\5\4\3\2\u0133\u0134\7\b\2\2\u0134\u0136\5\4\3"+
		"\2\u0135\u0133\3\2\2\2\u0136\u0139\3\2\2\2\u0137\u0135\3\2\2\2\u0137\u0138"+
		"\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137\3\2\2\2\u013a\u013c\5&\24\2\u013b"+
		"\u013a\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013d\3\2\2\2\u013d\u013e\7\20"+
		"\2\2\u013e\u013f\5\6\4\2\u013f\35\3\2\2\2\u0140\u0143\t\3\2\2\u0141\u0142"+
		"\7\37\2\2\u0142\u0144\5.\30\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2"+
		"\u0144\37\3\2\2\2\u0145\u0150\7\27\2\2\u0146\u014b\5\36\20\2\u0147\u0148"+
		"\7\13\2\2\u0148\u014a\5\36\20\2\u0149\u0147\3\2\2\2\u014a\u014d\3\2\2"+
		"\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014b"+
		"\3\2\2\2\u014e\u014f\7 \2\2\u014f\u0151\3\2\2\2\u0150\u0146\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0155\3\2\2\2\u0152\u0154\7.\2\2\u0153\u0152\3\2"+
		"\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156"+
		"\u015b\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u015a\5\"\22\2\u0159\u0158\3"+
		"\2\2\2\u015a\u015d\3\2\2\2\u015b\u0159\3\2\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\u0161\3\2\2\2\u015d\u015b\3\2\2\2\u015e\u0160\7.\2\2\u015f\u015e\3\2"+
		"\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0164\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0165\7\31\2\2\u0165!\3\2\2\2"+
		"\u0166\u016a\5\60\31\2\u0167\u016a\5\34\17\2\u0168\u016a\5\6\4\2\u0169"+
		"\u0166\3\2\2\2\u0169\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016a\u016c\3\2"+
		"\2\2\u016b\u016d\7\23\2\2\u016c\u016b\3\2\2\2\u016c\u016d\3\2\2\2\u016d"+
		"\u0171\3\2\2\2\u016e\u0170\7.\2\2\u016f\u016e\3\2\2\2\u0170\u0173\3\2"+
		"\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172#\3\2\2\2\u0173\u0171"+
		"\3\2\2\2\u0174\u017d\7\"\2\2\u0175\u017a\5\36\20\2\u0176\u0177\7\13\2"+
		"\2\u0177\u0179\5\36\20\2\u0178\u0176\3\2\2\2\u0179\u017c\3\2\2\2\u017a"+
		"\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2"+
		"\2\2\u017d\u0175\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f"+
		"\u0183\7 \2\2\u0180\u0182\7.\2\2\u0181\u0180\3\2\2\2\u0182\u0185\3\2\2"+
		"\2\u0183\u0181\3\2\2\2\u0183\u0184\3\2\2\2\u0184\u0188\3\2\2\2\u0185\u0183"+
		"\3\2\2\2\u0186\u0189\5\6\4\2\u0187\u0189\5\34\17\2\u0188\u0186\3\2\2\2"+
		"\u0188\u0187\3\2\2\2\u0189%\3\2\2\2\u018a\u0199\7\n\2\2\u018b\u0196\5"+
		"\6\4\2\u018c\u0190\7\13\2\2\u018d\u018f\7.\2\2\u018e\u018d\3\2\2\2\u018f"+
		"\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2"+
		"\2\2\u0192\u0190\3\2\2\2\u0193\u0195\5\6\4\2\u0194\u018c\3\2\2\2\u0195"+
		"\u0198\3\2\2\2\u0196\u0194\3\2\2\2\u0196\u0197\3\2\2\2\u0197\u019a\3\2"+
		"\2\2\u0198\u0196\3\2\2\2\u0199\u018b\3\2\2\2\u0199\u019a\3\2\2\2\u019a"+
		"\u019b\3\2\2\2\u019b\u019c\7\t\2\2\u019c\'\3\2\2\2\u019d\u019e\7\66\2"+
		"\2\u019e\u01a0\7\b\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a1"+
		"\3\2\2\2\u01a1\u01a2\7\66\2\2\u01a2)\3\2\2\2\u01a3\u01a4\t\3\2\2\u01a4"+
		"\u01a5\7\37\2\2\u01a5\u01a6\5.\30\2\u01a6+\3\2\2\2\u01a7\u01b0\7\n\2\2"+
		"\u01a8\u01ad\5*\26\2\u01a9\u01aa\7\13\2\2\u01aa\u01ac\5*\26\2\u01ab\u01a9"+
		"\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae"+
		"\u01b1\3\2\2\2\u01af\u01ad\3\2\2\2\u01b0\u01a8\3\2\2\2\u01b0\u01b1\3\2"+
		"\2\2\u01b1\u01b2\3\2\2\2\u01b2\u01b3\7\t\2\2\u01b3\u01b4\7 \2\2\u01b4"+
		"\u01b5\5.\30\2\u01b5-\3\2\2\2\u01b6\u01b9\5(\25\2\u01b7\u01b9\5,\27\2"+
		"\u01b8\u01b6\3\2\2\2\u01b8\u01b7\3\2\2\2\u01b9/\3\2\2\2\u01ba\u01be\t"+
		"\13\2\2\u01bb\u01bd\7.\2\2\u01bc\u01bb\3\2\2\2\u01bd\u01c0\3\2\2\2\u01be"+
		"\u01bc\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c1\3\2\2\2\u01c0\u01be\3\2"+
		"\2\2\u01c1\u01c4\7\66\2\2\u01c2\u01c3\7\37\2\2\u01c3\u01c5\5.\30\2\u01c4"+
		"\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c9\3\2\2\2\u01c6\u01c8\7."+
		"\2\2\u01c7\u01c6\3\2\2\2\u01c8\u01cb\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9"+
		"\u01ca\3\2\2\2\u01ca\u01cc\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cc\u01d0\7\20"+
		"\2\2\u01cd\u01cf\7.\2\2\u01ce\u01cd\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0"+
		"\u01ce\3\2\2\2\u01d0\u01d1\3\2\2\2\u01d1\u01d3\3\2\2\2\u01d2\u01d0\3\2"+
		"\2\2\u01d3\u01d4\5\6\4\2\u01d4\61\3\2\2\2\u01d5\u01d6\7!\2\2\u01d6\u01d7"+
		"\7\66\2\2\u01d7\u01d8\7\20\2\2\u01d8\u01d9\7\60\2\2\u01d9\63\3\2\2\2\u01da"+
		"\u01dc\7#\2\2\u01db\u01da\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01dd\3\2"+
		"\2\2\u01dd\u01de\t\3\2\2\u01de\u01df\7\37\2\2\u01df\u01e0\5.\30\2\u01e0"+
		"\65\3\2\2\2\u01e1\u01e2\7!\2\2\u01e2\u01e3\7\66\2\2\u01e3\u01e4\7\20\2"+
		"\2\u01e4\u01e8\7\n\2\2\u01e5\u01e7\7.\2\2\u01e6\u01e5\3\2\2\2\u01e7\u01ea"+
		"\3\2\2\2\u01e8\u01e6\3\2\2\2\u01e8\u01e9\3\2\2\2\u01e9\u01eb\3\2\2\2\u01ea"+
		"\u01e8\3\2\2\2\u01eb\u01f6\5\64\33\2\u01ec\u01f0\7\13\2\2\u01ed\u01ef"+
		"\7.\2\2\u01ee\u01ed\3\2\2\2\u01ef\u01f2\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0"+
		"\u01f1\3\2\2\2\u01f1\u01f3\3\2\2\2\u01f2\u01f0\3\2\2\2\u01f3\u01f5\5\64"+
		"\33\2\u01f4\u01ec\3\2\2\2\u01f5\u01f8\3\2\2\2\u01f6\u01f4\3\2\2\2\u01f6"+
		"\u01f7\3\2\2\2\u01f7\u01fc\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f9\u01fb\7."+
		"\2\2\u01fa\u01f9\3\2\2\2\u01fb\u01fe\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fc"+
		"\u01fd\3\2\2\2\u01fd\u01ff\3\2\2\2\u01fe\u01fc\3\2\2\2\u01ff\u0200\7\t"+
		"\2\2\u0200\67\3\2\2\2\u0201\u0202\7!\2\2\u0202\u0203\7\66\2\2\u0203\u0204"+
		"\7\20\2\2\u0204\u0207\5(\25\2\u0205\u0206\7+\2\2\u0206\u0208\5(\25\2\u0207"+
		"\u0205\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u0207\3\2\2\2\u0209\u020a\3\2"+
		"\2\2\u020a9\3\2\2\2\u020b\u020f\5\62\32\2\u020c\u020f\5\66\34\2\u020d"+
		"\u020f\58\35\2\u020e\u020b\3\2\2\2\u020e\u020c\3\2\2\2\u020e\u020d\3\2"+
		"\2\2\u020f;\3\2\2\2\u0210\u0211\7%\2\2\u0211\u0214\t\f\2\2\u0212\u0213"+
		"\7\37\2\2\u0213\u0215\5,\27\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2\2\2"+
		"\u0215\u0216\3\2\2\2\u0216\u021a\7\20\2\2\u0217\u0219\7.\2\2\u0218\u0217"+
		"\3\2\2\2\u0219\u021c\3\2\2\2\u021a\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b"+
		"\u0224\3\2\2\2\u021c\u021a\3\2\2\2\u021d\u0225\5$\23\2\u021e\u021f\5 "+
		"\21\2\u021f\u0220\7\37\2\2\u0220\u0221\5.\30\2\u0221\u0225\3\2\2\2\u0222"+
		"\u0225\7\60\2\2\u0223\u0225\5\6\4\2\u0224\u021d\3\2\2\2\u0224\u021e\3"+
		"\2\2\2\u0224\u0222\3\2\2\2\u0224\u0223\3\2\2\2\u0225=\3\2\2\2\u0226\u0227"+
		"\7&\2\2\u0227\u022c\7\66\2\2\u0228\u0229\7\b\2\2\u0229\u022b\7\66\2\2"+
		"\u022a\u0228\3\2\2\2\u022b\u022e\3\2\2\2\u022c\u022a\3\2\2\2\u022c\u022d"+
		"\3\2\2\2\u022d\u0238\3\2\2\2\u022e\u022c\3\2\2\2\u022f\u0230\7\'\2\2\u0230"+
		"\u0235\7\66\2\2\u0231\u0232\7\13\2\2\u0232\u0234\7\66\2\2\u0233\u0231"+
		"\3\2\2\2\u0234\u0237\3\2\2\2\u0235\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236"+
		"\u0239\3\2\2\2\u0237\u0235\3\2\2\2\u0238\u022f\3\2\2\2\u0238\u0239\3\2"+
		"\2\2\u0239?\3\2\2\2\u023a\u023d\5:\36\2\u023b\u023d\5<\37\2\u023c\u023a"+
		"\3\2\2\2\u023c\u023b\3\2\2\2\u023dA\3\2\2\2\u023e\u0240\7.\2\2\u023f\u023e"+
		"\3\2\2\2\u0240\u0243\3\2\2\2\u0241\u023f\3\2\2\2\u0241\u0242\3\2\2\2\u0242"+
		"\u0256\3\2\2\2\u0243\u0241\3\2\2\2\u0244\u024d\5> \2\u0245\u0247\7.\2"+
		"\2\u0246\u0245\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u0246\3\2\2\2\u0248\u0249"+
		"\3\2\2\2\u0249\u024a\3\2\2\2\u024a\u024c\5> \2\u024b\u0246\3\2\2\2\u024c"+
		"\u024f\3\2\2\2\u024d\u024b\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u0253\3\2"+
		"\2\2\u024f\u024d\3\2\2\2\u0250\u0252\7.\2\2\u0251\u0250\3\2\2\2\u0252"+
		"\u0255\3\2\2\2\u0253\u0251\3\2\2\2\u0253\u0254\3\2\2\2\u0254\u0257\3\2"+
		"\2\2\u0255\u0253\3\2\2\2\u0256\u0244\3\2\2\2\u0256\u0257\3\2\2\2\u0257"+
		"\u026a\3\2\2\2\u0258\u0261\5@!\2\u0259\u025b\7.\2\2\u025a\u0259\3\2\2"+
		"\2\u025b\u025c\3\2\2\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025e"+
		"\3\2\2\2\u025e\u0260\5@!\2\u025f\u025a\3\2\2\2\u0260\u0263\3\2\2\2\u0261"+
		"\u025f\3\2\2\2\u0261\u0262\3\2\2\2\u0262\u0267\3\2\2\2\u0263\u0261\3\2"+
		"\2\2\u0264\u0266\7.\2\2\u0265\u0264\3\2\2\2\u0266\u0269\3\2\2\2\u0267"+
		"\u0265\3\2\2\2\u0267\u0268\3\2\2\2\u0268\u026b\3\2\2\2\u0269\u0267\3\2"+
		"\2\2\u026a\u0258\3\2\2\2\u026a\u026b\3\2\2\2\u026bC\3\2\2\2SX_gkpw|~\u0084"+
		"\u008b\u0094\u009b\u00a2\u00a7\u00a9\u00c3\u00c5\u00d0\u00d7\u00e3\u00e8"+
		"\u00f1\u00f7\u00fa\u0105\u010b\u0112\u0119\u0120\u0123\u0129\u0130\u0137"+
		"\u013b\u0143\u014b\u0150\u0155\u015b\u0161\u0169\u016c\u0171\u017a\u017d"+
		"\u0183\u0188\u0190\u0196\u0199\u019f\u01ad\u01b0\u01b8\u01be\u01c4\u01c9"+
		"\u01d0\u01db\u01e8\u01f0\u01f6\u01fc\u0209\u020e\u0214\u021a\u0224\u022c"+
		"\u0235\u0238\u023c\u0241\u0248\u024d\u0253\u0256\u025c\u0261\u0267\u026a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}