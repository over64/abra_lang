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
		THEN=19, ELSE=20, CBO=21, CBC=22, LOGIC_OR=23, LOGIC_AND=24, WHILE=25, 
		VAL=26, VAR=27, CON=28, ARROW_RIGHT=29, TYPE=30, BACK_SLASH=31, SELF=32, 
		DEF=33, LlBegin=34, WS=35, NL=36, COMMENT=37, LlLiteral=38, IntLiteral=39, 
		FloatLiteral=40, BooleanLiteral=41, StringLiteral=42, Id=43, IrInline=44, 
		LlEnd=45;
	public static final int
		RULE_literal = 0, RULE_realdId = 1, RULE_expression = 2, RULE_store = 3, 
		RULE_fnArg = 4, RULE_block = 5, RULE_blockBody = 6, RULE_lambdaBlock = 7, 
		RULE_tuple = 8, RULE_scalarTypeHint = 9, RULE_fnTypeHintField = 10, RULE_fnTypeHint = 11, 
		RULE_typeHint = 12, RULE_variable = 13, RULE_scalarType = 14, RULE_typeField = 15, 
		RULE_factorType = 16, RULE_type = 17, RULE_function = 18, RULE_import_ = 19, 
		RULE_level1 = 20, RULE_module = 21;
	public static final String[] ruleNames = {
		"literal", "realdId", "expression", "store", "fnArg", "block", "blockBody", 
		"lambdaBlock", "tuple", "scalarTypeHint", "fnTypeHintField", "fnTypeHint", 
		"typeHint", "variable", "scalarType", "typeField", "factorType", "type", 
		"function", "import_", "level1", "module"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", "'.'", "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'then'", 
		"'else'", "'{'", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", "':'", 
		"'->'", "'type'", "'\\'", "'self'", "'def'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "THEN", "ELSE", "CBO", "CBC", "LOGIC_OR", "LOGIC_AND", "WHILE", 
		"VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", "DEF", 
		"LlBegin", "WS", "NL", "COMMENT", "LlLiteral", "IntLiteral", "FloatLiteral", 
		"BooleanLiteral", "StringLiteral", "Id", "IrInline", "LlEnd"
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
		public TerminalNode FloatLiteral() { return getToken(M2Parser.FloatLiteral, 0); }
		public TerminalNode BooleanLiteral() { return getToken(M2Parser.BooleanLiteral, 0); }
		public TerminalNode StringLiteral() { return getToken(M2Parser.StringLiteral, 0); }
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
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
			setState(44);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SELF) | (1L << IntLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) ) {
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

	public static class RealdIdContext extends ParserRuleContext {
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
		public RealdIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_realdId; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitRealdId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RealdIdContext realdId() throws RecognitionException {
		RealdIdContext _localctx = new RealdIdContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_realdId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
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
	public static class ExprIfElseContext extends ExpressionContext {
		public ExpressionContext cond;
		public ExpressionContext then_expr;
		public BlockContext then_block;
		public ExpressionContext else_expr;
		public BlockContext else_block;
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
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
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
			setState(119);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(49);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(50);
				match(LB);
				setState(51);
				expression(0);
				setState(52);
				match(RB);
				}
				break;
			case 3:
				{
				_localctx = new ExprTupleContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(54);
				tuple();
				}
				break;
			case 4:
				{
				_localctx = new ExprBlockContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(55);
				block();
				}
				break;
			case 5:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(56);
				lambdaBlock();
				}
				break;
			case 6:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(57);
				((ExprUnaryCallContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MINUS || _la==EXCL) ) {
					((ExprUnaryCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(58);
				expression(8);
				}
				break;
			case 7:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(59);
				match(IF);
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(60);
					match(NL);
					}
					}
					setState(65);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(66);
				((ExprIfElseContext)_localctx).cond = expression(0);
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(67);
					match(NL);
					}
					}
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(82);
				switch (_input.LA(1)) {
				case THEN:
					{
					{
					setState(73);
					match(THEN);
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(74);
						match(NL);
						}
						}
						setState(79);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(80);
					((ExprIfElseContext)_localctx).then_expr = expression(0);
					}
					}
					break;
				case CBO:
					{
					setState(81);
					((ExprIfElseContext)_localctx).then_block = block();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(101);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(87);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(84);
						match(NL);
						}
						}
						setState(89);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(90);
					match(ELSE);
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(91);
						match(NL);
						}
						}
						setState(96);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(99);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
					case 1:
						{
						setState(97);
						((ExprIfElseContext)_localctx).else_expr = expression(0);
						}
						break;
					case 2:
						{
						setState(98);
						((ExprIfElseContext)_localctx).else_block = block();
						}
						break;
					}
					}
					break;
				}
				}
				break;
			case 8:
				{
				_localctx = new ExprWhileContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(103);
				match(WHILE);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(104);
					match(NL);
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(110);
				((ExprWhileContext)_localctx).cond = expression(0);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(111);
					match(NL);
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(117);
				((ExprWhileContext)_localctx).then_block = block();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(145);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(121);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(122);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==MUL || _la==DIV) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(123);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(124);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(125);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << Id))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(126);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(127);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(128);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ))) != 0)) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(129);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(130);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(131);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==EQEQ || _la==NOTEQ) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(132);
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(133);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(134);
						((ExprInfixCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==LOGIC_OR || _la==LOGIC_AND) ) {
							((ExprInfixCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(135);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(136);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(137);
						match(DOT);
						setState(138);
						((ExprSelfCallContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << Id))) != 0)) ) {
							((ExprSelfCallContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(139);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(140);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(141);
						match(DOT);
						setState(142);
						((ExprPropContext)_localctx).op = match(Id);
						}
						break;
					case 8:
						{
						_localctx = new ExprApplyContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(143);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(144);
						tuple();
						}
						break;
					}
					} 
				}
				setState(149);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
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

	public static class StoreContext extends ParserRuleContext {
		public List<RealdIdContext> realdId() {
			return getRuleContexts(RealdIdContext.class);
		}
		public RealdIdContext realdId(int i) {
			return getRuleContext(RealdIdContext.class,i);
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
		enterRule(_localctx, 6, RULE_store);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			realdId();
			setState(155);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(151);
				match(DOT);
				setState(152);
				realdId();
				}
				}
				setState(157);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(159);
			_la = _input.LA(1);
			if (_la==LB) {
				{
				setState(158);
				tuple();
				}
			}

			setState(161);
			match(EQ);
			setState(162);
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
		enterRule(_localctx, 8, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(164);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(167);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(165);
				match(CON);
				setState(166);
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
		enterRule(_localctx, 10, RULE_block);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(CBO);
			setState(180);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				{
				setState(170);
				fnArg();
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(171);
					match(COMMA);
					setState(172);
					fnArg();
					}
					}
					setState(177);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(178);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(185);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(182);
					match(NL);
					}
					} 
				}
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(204);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << VAL) | (1L << VAR) | (1L << BACK_SLASH) | (1L << SELF) | (1L << IntLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) {
				{
				setState(188);
				blockBody();
				setState(201);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(196);
						switch (_input.LA(1)) {
						case MINUS:
						case EXCL:
						case LB:
						case IF:
						case CBO:
						case WHILE:
						case VAL:
						case VAR:
						case BACK_SLASH:
						case SELF:
						case NL:
						case IntLiteral:
						case FloatLiteral:
						case BooleanLiteral:
						case StringLiteral:
						case Id:
							{
							setState(192);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==NL) {
								{
								{
								setState(189);
								match(NL);
								}
								}
								setState(194);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
							break;
						case SEMI:
							{
							setState(195);
							match(SEMI);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(198);
						blockBody();
						}
						} 
					}
					setState(203);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				}
			}

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
		enterRule(_localctx, 12, RULE_blockBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(214);
				variable();
				}
				break;
			case 2:
				{
				setState(215);
				store();
				}
				break;
			case 3:
				{
				setState(216);
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
		enterRule(_localctx, 14, RULE_lambdaBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(BACK_SLASH);
			setState(228);
			_la = _input.LA(1);
			if (_la==SELF || _la==Id) {
				{
				setState(220);
				fnArg();
				setState(225);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(221);
					match(COMMA);
					setState(222);
					fnArg();
					}
					}
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(230);
			match(ARROW_RIGHT);
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(231);
				match(NL);
				}
				}
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(239);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(237);
				expression(0);
				}
				break;
			case 2:
				{
				setState(238);
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
		enterRule(_localctx, 16, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(241);
			match(LB);
			setState(250);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << BACK_SLASH) | (1L << SELF) | (1L << IntLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << Id))) != 0)) {
				{
				setState(242);
				expression(0);
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(243);
					match(COMMA);
					setState(244);
					expression(0);
					}
					}
					setState(249);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(252);
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
		public TerminalNode Id() { return getToken(M2Parser.Id, 0); }
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
		enterRule(_localctx, 18, RULE_scalarTypeHint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(Id);
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 20, RULE_fnTypeHintField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(257);
			match(CON);
			setState(258);
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
		enterRule(_localctx, 22, RULE_fnTypeHint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(LB);
			setState(269);
			_la = _input.LA(1);
			if (_la==SELF || _la==Id) {
				{
				setState(261);
				fnTypeHintField();
				setState(266);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(262);
					match(COMMA);
					setState(263);
					fnTypeHintField();
					}
					}
					setState(268);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(271);
			match(RB);
			setState(272);
			match(ARROW_RIGHT);
			setState(273);
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
		enterRule(_localctx, 24, RULE_typeHint);
		try {
			setState(277);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(275);
				scalarTypeHint();
				}
				break;
			case LB:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
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
		enterRule(_localctx, 26, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
			((VariableContext)_localctx).valVar = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==VAL || _la==VAR) ) {
				((VariableContext)_localctx).valVar = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(283);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(280);
				match(NL);
				}
				}
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(286);
			match(Id);
			setState(289);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(287);
				match(CON);
				setState(288);
				typeHint();
				}
			}

			setState(294);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(291);
				match(NL);
				}
				}
				setState(296);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(297);
			match(EQ);
			setState(301);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(298);
				match(NL);
				}
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(304);
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
		enterRule(_localctx, 28, RULE_scalarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(TYPE);
			setState(307);
			match(Id);
			setState(308);
			match(EQ);
			setState(309);
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
		enterRule(_localctx, 30, RULE_typeField);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(312);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(311);
				match(SELF);
				}
				break;
			}
			setState(314);
			_la = _input.LA(1);
			if ( !(_la==SELF || _la==Id) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(315);
			match(CON);
			setState(316);
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
		enterRule(_localctx, 32, RULE_factorType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(318);
			match(TYPE);
			setState(319);
			match(Id);
			setState(320);
			match(EQ);
			setState(321);
			match(LB);
			setState(322);
			typeField();
			setState(327);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(323);
				match(COMMA);
				setState(324);
				typeField();
				}
				}
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(330);
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
		enterRule(_localctx, 34, RULE_type);
		try {
			setState(334);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(332);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(333);
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
		enterRule(_localctx, 36, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(336);
			match(DEF);
			setState(337);
			((FunctionContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << EXCL) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << LOGIC_OR) | (1L << LOGIC_AND) | (1L << SELF) | (1L << Id))) != 0)) ) {
				((FunctionContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(340);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(338);
				match(CON);
				setState(339);
				fnTypeHint();
				}
			}

			setState(342);
			match(EQ);
			setState(346);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(343);
				match(NL);
				}
				}
				setState(348);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(356);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,43,_ctx) ) {
			case 1:
				{
				setState(349);
				lambdaBlock();
				}
				break;
			case 2:
				{
				{
				setState(350);
				block();
				setState(351);
				match(CON);
				setState(352);
				typeHint();
				}
				}
				break;
			case 3:
				{
				setState(354);
				match(LlLiteral);
				}
				break;
			case 4:
				{
				setState(355);
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
		enterRule(_localctx, 38, RULE_import_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(358);
			match(Id);
			setState(363);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(359);
				match(DOT);
				setState(360);
				match(Id);
				}
				}
				setState(365);
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
		public Import_Context import_() {
			return getRuleContext(Import_Context.class,0);
		}
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
		enterRule(_localctx, 40, RULE_level1);
		try {
			setState(369);
			switch (_input.LA(1)) {
			case Id:
				enterOuterAlt(_localctx, 1);
				{
				setState(366);
				import_();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 2);
				{
				setState(367);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 3);
				{
				setState(368);
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
		enterRule(_localctx, 42, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(371);
				match(NL);
				}
				}
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(395);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TYPE) | (1L << DEF) | (1L << Id))) != 0)) {
				{
				setState(377);
				level1();
				setState(386);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(379); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(378);
							match(NL);
							}
							}
							setState(381); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(383);
						level1();
						}
						} 
					}
					setState(388);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				}
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
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 11);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3/\u0190\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\3\3\3\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4@\n\4\f\4\16\4C\13"+
		"\4\3\4\3\4\7\4G\n\4\f\4\16\4J\13\4\3\4\3\4\7\4N\n\4\f\4\16\4Q\13\4\3\4"+
		"\3\4\5\4U\n\4\3\4\7\4X\n\4\f\4\16\4[\13\4\3\4\3\4\7\4_\n\4\f\4\16\4b\13"+
		"\4\3\4\3\4\5\4f\n\4\5\4h\n\4\3\4\3\4\7\4l\n\4\f\4\16\4o\13\4\3\4\3\4\7"+
		"\4s\n\4\f\4\16\4v\13\4\3\4\3\4\5\4z\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"\u0094\n\4\f\4\16\4\u0097\13\4\3\5\3\5\3\5\7\5\u009c\n\5\f\5\16\5\u009f"+
		"\13\5\3\5\5\5\u00a2\n\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6\u00aa\n\6\3\7\3\7"+
		"\3\7\3\7\7\7\u00b0\n\7\f\7\16\7\u00b3\13\7\3\7\3\7\5\7\u00b7\n\7\3\7\7"+
		"\7\u00ba\n\7\f\7\16\7\u00bd\13\7\3\7\3\7\7\7\u00c1\n\7\f\7\16\7\u00c4"+
		"\13\7\3\7\5\7\u00c7\n\7\3\7\7\7\u00ca\n\7\f\7\16\7\u00cd\13\7\5\7\u00cf"+
		"\n\7\3\7\7\7\u00d2\n\7\f\7\16\7\u00d5\13\7\3\7\3\7\3\b\3\b\3\b\5\b\u00dc"+
		"\n\b\3\t\3\t\3\t\3\t\7\t\u00e2\n\t\f\t\16\t\u00e5\13\t\5\t\u00e7\n\t\3"+
		"\t\3\t\7\t\u00eb\n\t\f\t\16\t\u00ee\13\t\3\t\3\t\5\t\u00f2\n\t\3\n\3\n"+
		"\3\n\3\n\7\n\u00f8\n\n\f\n\16\n\u00fb\13\n\5\n\u00fd\n\n\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\7\r\u010b\n\r\f\r\16\r\u010e\13"+
		"\r\5\r\u0110\n\r\3\r\3\r\3\r\3\r\3\16\3\16\5\16\u0118\n\16\3\17\3\17\7"+
		"\17\u011c\n\17\f\17\16\17\u011f\13\17\3\17\3\17\3\17\5\17\u0124\n\17\3"+
		"\17\7\17\u0127\n\17\f\17\16\17\u012a\13\17\3\17\3\17\7\17\u012e\n\17\f"+
		"\17\16\17\u0131\13\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\5\21\u013b"+
		"\n\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\7\22\u0148"+
		"\n\22\f\22\16\22\u014b\13\22\3\22\3\22\3\23\3\23\5\23\u0151\n\23\3\24"+
		"\3\24\3\24\3\24\5\24\u0157\n\24\3\24\3\24\7\24\u015b\n\24\f\24\16\24\u015e"+
		"\13\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0167\n\24\3\25\3\25\3"+
		"\25\7\25\u016c\n\25\f\25\16\25\u016f\13\25\3\26\3\26\3\26\5\26\u0174\n"+
		"\26\3\27\7\27\u0177\n\27\f\27\16\27\u017a\13\27\3\27\3\27\6\27\u017e\n"+
		"\27\r\27\16\27\u017f\3\27\7\27\u0183\n\27\f\27\16\27\u0186\13\27\3\27"+
		"\7\27\u0189\n\27\f\27\16\27\u018c\13\27\5\27\u018e\n\27\3\27\2\3\6\30"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,\2\r\4\2\"\")-\4\2\"\""+
		"--\4\2\3\3\7\7\3\2\5\6\4\2\3\4--\3\2\f\17\3\2\21\22\3\2\31\32\7\2\3\6"+
		"\f\17\21\22\31\32--\3\2\34\35\b\2\3\7\f\17\21\22\31\32\"\"--\u01bc\2."+
		"\3\2\2\2\4\60\3\2\2\2\6y\3\2\2\2\b\u0098\3\2\2\2\n\u00a6\3\2\2\2\f\u00ab"+
		"\3\2\2\2\16\u00db\3\2\2\2\20\u00dd\3\2\2\2\22\u00f3\3\2\2\2\24\u0100\3"+
		"\2\2\2\26\u0102\3\2\2\2\30\u0106\3\2\2\2\32\u0117\3\2\2\2\34\u0119\3\2"+
		"\2\2\36\u0134\3\2\2\2 \u013a\3\2\2\2\"\u0140\3\2\2\2$\u0150\3\2\2\2&\u0152"+
		"\3\2\2\2(\u0168\3\2\2\2*\u0173\3\2\2\2,\u0178\3\2\2\2./\t\2\2\2/\3\3\2"+
		"\2\2\60\61\t\3\2\2\61\5\3\2\2\2\62\63\b\4\1\2\63z\5\2\2\2\64\65\7\n\2"+
		"\2\65\66\5\6\4\2\66\67\7\t\2\2\67z\3\2\2\28z\5\22\n\29z\5\f\7\2:z\5\20"+
		"\t\2;<\t\4\2\2<z\5\6\4\n=A\7\24\2\2>@\7&\2\2?>\3\2\2\2@C\3\2\2\2A?\3\2"+
		"\2\2AB\3\2\2\2BD\3\2\2\2CA\3\2\2\2DH\5\6\4\2EG\7&\2\2FE\3\2\2\2GJ\3\2"+
		"\2\2HF\3\2\2\2HI\3\2\2\2IT\3\2\2\2JH\3\2\2\2KO\7\25\2\2LN\7&\2\2ML\3\2"+
		"\2\2NQ\3\2\2\2OM\3\2\2\2OP\3\2\2\2PR\3\2\2\2QO\3\2\2\2RU\5\6\4\2SU\5\f"+
		"\7\2TK\3\2\2\2TS\3\2\2\2Ug\3\2\2\2VX\7&\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2"+
		"\2\2YZ\3\2\2\2Z\\\3\2\2\2[Y\3\2\2\2\\`\7\26\2\2]_\7&\2\2^]\3\2\2\2_b\3"+
		"\2\2\2`^\3\2\2\2`a\3\2\2\2ae\3\2\2\2b`\3\2\2\2cf\5\6\4\2df\5\f\7\2ec\3"+
		"\2\2\2ed\3\2\2\2fh\3\2\2\2gY\3\2\2\2gh\3\2\2\2hz\3\2\2\2im\7\33\2\2jl"+
		"\7&\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3\2\2\2np\3\2\2\2om\3\2\2\2pt"+
		"\5\6\4\2qs\7&\2\2rq\3\2\2\2sv\3\2\2\2tr\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt"+
		"\3\2\2\2wx\5\f\7\2xz\3\2\2\2y\62\3\2\2\2y\64\3\2\2\2y8\3\2\2\2y9\3\2\2"+
		"\2y:\3\2\2\2y;\3\2\2\2y=\3\2\2\2yi\3\2\2\2z\u0095\3\2\2\2{|\f\t\2\2|}"+
		"\t\5\2\2}\u0094\5\6\4\n~\177\f\b\2\2\177\u0080\t\6\2\2\u0080\u0094\5\6"+
		"\4\t\u0081\u0082\f\7\2\2\u0082\u0083\t\7\2\2\u0083\u0094\5\6\4\b\u0084"+
		"\u0085\f\6\2\2\u0085\u0086\t\b\2\2\u0086\u0094\5\6\4\7\u0087\u0088\f\5"+
		"\2\2\u0088\u0089\t\t\2\2\u0089\u0094\5\6\4\6\u008a\u008b\f\17\2\2\u008b"+
		"\u008c\7\b\2\2\u008c\u008d\t\n\2\2\u008d\u0094\5\22\n\2\u008e\u008f\f"+
		"\16\2\2\u008f\u0090\7\b\2\2\u0090\u0094\7-\2\2\u0091\u0092\f\r\2\2\u0092"+
		"\u0094\5\22\n\2\u0093{\3\2\2\2\u0093~\3\2\2\2\u0093\u0081\3\2\2\2\u0093"+
		"\u0084\3\2\2\2\u0093\u0087\3\2\2\2\u0093\u008a\3\2\2\2\u0093\u008e\3\2"+
		"\2\2\u0093\u0091\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2\2\2\u0095"+
		"\u0096\3\2\2\2\u0096\7\3\2\2\2\u0097\u0095\3\2\2\2\u0098\u009d\5\4\3\2"+
		"\u0099\u009a\7\b\2\2\u009a\u009c\5\4\3\2\u009b\u0099\3\2\2\2\u009c\u009f"+
		"\3\2\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a1\3\2\2\2\u009f"+
		"\u009d\3\2\2\2\u00a0\u00a2\5\22\n\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3"+
		"\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a4\7\20\2\2\u00a4\u00a5\5\6\4\2\u00a5"+
		"\t\3\2\2\2\u00a6\u00a9\t\3\2\2\u00a7\u00a8\7\36\2\2\u00a8\u00aa\5\32\16"+
		"\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\13\3\2\2\2\u00ab\u00b6"+
		"\7\27\2\2\u00ac\u00b1\5\n\6\2\u00ad\u00ae\7\13\2\2\u00ae\u00b0\5\n\6\2"+
		"\u00af\u00ad\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2"+
		"\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b5\7\37\2\2"+
		"\u00b5\u00b7\3\2\2\2\u00b6\u00ac\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00bb"+
		"\3\2\2\2\u00b8\u00ba\7&\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00ce\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00be\u00cb\5\16\b\2\u00bf\u00c1\7&\2\2\u00c0\u00bf\3\2\2\2\u00c1"+
		"\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2\2\2\u00c3\u00c7\3\2"+
		"\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c7\7\23\2\2\u00c6\u00c2\3\2\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00ca\5\16\b\2\u00c9\u00c6\3"+
		"\2\2\2\u00ca\u00cd\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00ce\u00be\3\2\2\2\u00ce\u00cf\3\2"+
		"\2\2\u00cf\u00d3\3\2\2\2\u00d0\u00d2\7&\2\2\u00d1\u00d0\3\2\2\2\u00d2"+
		"\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2"+
		"\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\7\30\2\2\u00d7\r\3\2\2\2\u00d8\u00dc"+
		"\5\34\17\2\u00d9\u00dc\5\b\5\2\u00da\u00dc\5\6\4\2\u00db\u00d8\3\2\2\2"+
		"\u00db\u00d9\3\2\2\2\u00db\u00da\3\2\2\2\u00dc\17\3\2\2\2\u00dd\u00e6"+
		"\7!\2\2\u00de\u00e3\5\n\6\2\u00df\u00e0\7\13\2\2\u00e0\u00e2\5\n\6\2\u00e1"+
		"\u00df\3\2\2\2\u00e2\u00e5\3\2\2\2\u00e3\u00e1\3\2\2\2\u00e3\u00e4\3\2"+
		"\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e6\u00de\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8\u00ec\7\37\2\2\u00e9\u00eb\7"+
		"&\2\2\u00ea\u00e9\3\2\2\2\u00eb\u00ee\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00f1\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ef\u00f2\5\6"+
		"\4\2\u00f0\u00f2\5\b\5\2\u00f1\u00ef\3\2\2\2\u00f1\u00f0\3\2\2\2\u00f2"+
		"\21\3\2\2\2\u00f3\u00fc\7\n\2\2\u00f4\u00f9\5\6\4\2\u00f5\u00f6\7\13\2"+
		"\2\u00f6\u00f8\5\6\4\2\u00f7\u00f5\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7"+
		"\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc"+
		"\u00f4\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\7\t"+
		"\2\2\u00ff\23\3\2\2\2\u0100\u0101\7-\2\2\u0101\25\3\2\2\2\u0102\u0103"+
		"\t\3\2\2\u0103\u0104\7\36\2\2\u0104\u0105\5\32\16\2\u0105\27\3\2\2\2\u0106"+
		"\u010f\7\n\2\2\u0107\u010c\5\26\f\2\u0108\u0109\7\13\2\2\u0109\u010b\5"+
		"\26\f\2\u010a\u0108\3\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c"+
		"\u010d\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010f\u0107\3\2"+
		"\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111\u0112\7\t\2\2\u0112"+
		"\u0113\7\37\2\2\u0113\u0114\5\32\16\2\u0114\31\3\2\2\2\u0115\u0118\5\24"+
		"\13\2\u0116\u0118\5\30\r\2\u0117\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118"+
		"\33\3\2\2\2\u0119\u011d\t\13\2\2\u011a\u011c\7&\2\2\u011b\u011a\3\2\2"+
		"\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u0120"+
		"\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0123\7-\2\2\u0121\u0122\7\36\2\2\u0122"+
		"\u0124\5\32\16\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0128\3"+
		"\2\2\2\u0125\u0127\7&\2\2\u0126\u0125\3\2\2\2\u0127\u012a\3\2\2\2\u0128"+
		"\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012b\3\2\2\2\u012a\u0128\3\2"+
		"\2\2\u012b\u012f\7\20\2\2\u012c\u012e\7&\2\2\u012d\u012c\3\2\2\2\u012e"+
		"\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0132\3\2"+
		"\2\2\u0131\u012f\3\2\2\2\u0132\u0133\5\6\4\2\u0133\35\3\2\2\2\u0134\u0135"+
		"\7 \2\2\u0135\u0136\7-\2\2\u0136\u0137\7\20\2\2\u0137\u0138\7(\2\2\u0138"+
		"\37\3\2\2\2\u0139\u013b\7\"\2\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2"+
		"\2\u013b\u013c\3\2\2\2\u013c\u013d\t\3\2\2\u013d\u013e\7\36\2\2\u013e"+
		"\u013f\5\32\16\2\u013f!\3\2\2\2\u0140\u0141\7 \2\2\u0141\u0142\7-\2\2"+
		"\u0142\u0143\7\20\2\2\u0143\u0144\7\n\2\2\u0144\u0149\5 \21\2\u0145\u0146"+
		"\7\13\2\2\u0146\u0148\5 \21\2\u0147\u0145\3\2\2\2\u0148\u014b\3\2\2\2"+
		"\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014c\3\2\2\2\u014b\u0149"+
		"\3\2\2\2\u014c\u014d\7\t\2\2\u014d#\3\2\2\2\u014e\u0151\5\36\20\2\u014f"+
		"\u0151\5\"\22\2\u0150\u014e\3\2\2\2\u0150\u014f\3\2\2\2\u0151%\3\2\2\2"+
		"\u0152\u0153\7#\2\2\u0153\u0156\t\f\2\2\u0154\u0155\7\36\2\2\u0155\u0157"+
		"\5\30\r\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0158\3\2\2\2"+
		"\u0158\u015c\7\20\2\2\u0159\u015b\7&\2\2\u015a\u0159\3\2\2\2\u015b\u015e"+
		"\3\2\2\2\u015c\u015a\3\2\2\2\u015c\u015d\3\2\2\2\u015d\u0166\3\2\2\2\u015e"+
		"\u015c\3\2\2\2\u015f\u0167\5\20\t\2\u0160\u0161\5\f\7\2\u0161\u0162\7"+
		"\36\2\2\u0162\u0163\5\32\16\2\u0163\u0167\3\2\2\2\u0164\u0167\7(\2\2\u0165"+
		"\u0167\5\6\4\2\u0166\u015f\3\2\2\2\u0166\u0160\3\2\2\2\u0166\u0164\3\2"+
		"\2\2\u0166\u0165\3\2\2\2\u0167\'\3\2\2\2\u0168\u016d\7-\2\2\u0169\u016a"+
		"\7\b\2\2\u016a\u016c\7-\2\2\u016b\u0169\3\2\2\2\u016c\u016f\3\2\2\2\u016d"+
		"\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e)\3\2\2\2\u016f\u016d\3\2\2\2"+
		"\u0170\u0174\5(\25\2\u0171\u0174\5$\23\2\u0172\u0174\5&\24\2\u0173\u0170"+
		"\3\2\2\2\u0173\u0171\3\2\2\2\u0173\u0172\3\2\2\2\u0174+\3\2\2\2\u0175"+
		"\u0177\7&\2\2\u0176\u0175\3\2\2\2\u0177\u017a\3\2\2\2\u0178\u0176\3\2"+
		"\2\2\u0178\u0179\3\2\2\2\u0179\u018d\3\2\2\2\u017a\u0178\3\2\2\2\u017b"+
		"\u0184\5*\26\2\u017c\u017e\7&\2\2\u017d\u017c\3\2\2\2\u017e\u017f\3\2"+
		"\2\2\u017f\u017d\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u0183\5*\26\2\u0182\u017d\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182\3\2"+
		"\2\2\u0184\u0185\3\2\2\2\u0185\u018a\3\2\2\2\u0186\u0184\3\2\2\2\u0187"+
		"\u0189\7&\2\2\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2"+
		"\2\2\u018a\u018b\3\2\2\2\u018b\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018d"+
		"\u017b\3\2\2\2\u018d\u018e\3\2\2\2\u018e-\3\2\2\2\65AHOTY`egmty\u0093"+
		"\u0095\u009d\u00a1\u00a9\u00b1\u00b6\u00bb\u00c2\u00c6\u00cb\u00ce\u00d3"+
		"\u00db\u00e3\u00e6\u00ec\u00f1\u00f9\u00fc\u010c\u010f\u0117\u011d\u0123"+
		"\u0128\u012f\u013a\u0149\u0150\u0156\u015c\u0166\u016d\u0173\u0178\u017f"+
		"\u0184\u018a\u018d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}