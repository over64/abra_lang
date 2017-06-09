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
		THEN=19, ELSE=20, CBO=21, DOLLAR_CBO=22, CBC=23, LOGIC_OR=24, LOGIC_AND=25, 
		WHILE=26, VAL=27, VAR=28, CON=29, ARROW_RIGHT=30, TYPE=31, BACK_SLASH=32, 
		SELF=33, MATCH_SELF=34, DEF=35, IMPORT=36, WITH=37, MATCH=38, OF=39, DASH=40, 
		VERT_LINE=41, BRACKET_LEFT=42, BRACKET_RIGTH=43, AMP=44, LlBegin=45, LlDef=46, 
		WS=47, NL=48, COMMENT=49, IntLiteral=50, HexLiteral=51, FloatLiteral=52, 
		BooleanLiteral=53, StringLiteral=54, VarId=55, TypeId=56, MatchId=57, 
		IrInline=58, LlEnd=59;
	public static final int
		RULE_literal = 0, RULE_id = 1, RULE_expression = 2, RULE_if_stat = 3, 
		RULE_tuple = 4, RULE_fieldTh = 5, RULE_scalarTh = 6, RULE_fnTh = 7, RULE_structTh = 8, 
		RULE_nonUnionTh = 9, RULE_unionTh = 10, RULE_typeHint = 11, RULE_matchDash = 12, 
		RULE_bindVar = 13, RULE_matchId = 14, RULE_matchBracketsExpr = 15, RULE_matchExpression = 16, 
		RULE_destruct = 17, RULE_matchType = 18, RULE_matchOver = 19, RULE_matchCase = 20, 
		RULE_variable = 21, RULE_store = 22, RULE_while_stat = 23, RULE_fnArg = 24, 
		RULE_lambda = 25, RULE_blockBody = 26, RULE_hLambda = 27, RULE_scalarType = 28, 
		RULE_typeField = 29, RULE_structType = 30, RULE_unionType = 31, RULE_type = 32, 
		RULE_function = 33, RULE_level1 = 34, RULE_module = 35, RULE_lltype = 36, 
		RULE_lldef = 37;
	public static final String[] ruleNames = {
		"literal", "id", "expression", "if_stat", "tuple", "fieldTh", "scalarTh", 
		"fnTh", "structTh", "nonUnionTh", "unionTh", "typeHint", "matchDash", 
		"bindVar", "matchId", "matchBracketsExpr", "matchExpression", "destruct", 
		"matchType", "matchOver", "matchCase", "variable", "store", "while_stat", 
		"fnArg", "lambda", "blockBody", "hLambda", "scalarType", "typeField", 
		"structType", "unionType", "type", "function", "level1", "module", "lltype", 
		"lldef"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", "'.'", "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'then'", 
		"'else'", "'{'", "'${'", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", 
		"':'", "'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'import'", 
		"'with'", "'match'", "'of'", "'_'", "'|'", "'['", "']'", "'&'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "THEN", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
		"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
		"MATCH_SELF", "DEF", "IMPORT", "WITH", "MATCH", "OF", "DASH", "VERT_LINE", 
		"BRACKET_LEFT", "BRACKET_RIGTH", "AMP", "LlBegin", "LlDef", "WS", "NL", 
		"COMMENT", "IntLiteral", "HexLiteral", "FloatLiteral", "BooleanLiteral", 
		"StringLiteral", "VarId", "TypeId", "MatchId", "IrInline", "LlEnd"
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
			setState(76);
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
			setState(78);
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
	public static class ExprDerefContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprDerefContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprDeref(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprHLambdaContext extends ExpressionContext {
		public HLambdaContext hLambda() {
			return getRuleContext(HLambdaContext.class,0);
		}
		public ExprHLambdaContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprHLambda(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprRefContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprRefContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprRef(this);
			else return visitor.visitChildren(this);
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
		public If_statContext then_stat;
		public If_statContext else_stat;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
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
		public ExprCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitExprCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ExprPropContext extends ExpressionContext {
		public Token op;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
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
		public TupleContext tuple() {
			return getRuleContext(TupleContext.class,0);
		}
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
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
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new ExprLiteralContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(81);
				literal();
				}
				break;
			case 2:
				{
				_localctx = new ExprIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(82);
				id();
				}
				break;
			case 3:
				{
				_localctx = new ExprParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(83);
				match(LB);
				setState(84);
				expression(0);
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
				_localctx = new ExprRefContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(88);
				match(AMP);
				setState(89);
				expression(15);
				}
				break;
			case 6:
				{
				_localctx = new ExprDerefContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(90);
				match(MUL);
				setState(91);
				expression(14);
				}
				break;
			case 7:
				{
				_localctx = new ExprLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				lambda();
				}
				break;
			case 8:
				{
				_localctx = new ExprHLambdaContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(93);
				hLambda();
				}
				break;
			case 9:
				{
				_localctx = new ExprUnaryCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(94);
				((ExprUnaryCallContext)_localctx).op = match(EXCL);
				setState(95);
				expression(8);
				}
				break;
			case 10:
				{
				_localctx = new ExprIfElseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(96);
				match(IF);
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(97);
					match(NL);
					}
					}
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(103);
				((ExprIfElseContext)_localctx).cond = expression(0);
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
				{
				setState(110);
				match(THEN);
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
				((ExprIfElseContext)_localctx).then_stat = if_stat();
				}
				setState(133);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(122);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(119);
						match(NL);
						}
						}
						setState(124);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(125);
					match(ELSE);
					setState(129);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(126);
						match(NL);
						}
						}
						setState(131);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(132);
					((ExprIfElseContext)_localctx).else_stat = if_stat();
					}
					break;
				}
				}
				break;
			case 11:
				{
				_localctx = new ExprMatchContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				match(MATCH);
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(136);
					match(NL);
					}
					}
					setState(141);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(142);
				((ExprMatchContext)_localctx).expr = expression(0);
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
				setState(156); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(149);
						matchCase();
						setState(153);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
						while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
							if ( _alt==1 ) {
								{
								{
								setState(150);
								match(NL);
								}
								} 
							}
							setState(155);
							_errHandler.sync(this);
							_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
						}
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(158); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(188);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(186);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
					case 1:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(162);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(163);
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
						setState(164);
						expression(8);
						}
						break;
					case 2:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(165);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(166);
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
						setState(167);
						expression(7);
						}
						break;
					case 3:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(168);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(169);
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
						setState(170);
						expression(6);
						}
						break;
					case 4:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(171);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
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
						expression(5);
						}
						break;
					case 5:
						{
						_localctx = new ExprInfixCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(174);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(175);
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
						setState(176);
						expression(4);
						}
						break;
					case 6:
						{
						_localctx = new ExprSelfCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(177);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(178);
						match(DOT);
						setState(179);
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
						setState(180);
						tuple();
						}
						break;
					case 7:
						{
						_localctx = new ExprPropContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(181);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(182);
						match(DOT);
						setState(183);
						((ExprPropContext)_localctx).op = match(VarId);
						}
						break;
					case 8:
						{
						_localctx = new ExprCallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(184);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(185);
						tuple();
						}
						break;
					}
					} 
				}
				setState(190);
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

	public static class If_statContext extends ParserRuleContext {
		public List<BlockBodyContext> blockBody() {
			return getRuleContexts(BlockBodyContext.class);
		}
		public BlockBodyContext blockBody(int i) {
			return getRuleContext(BlockBodyContext.class,i);
		}
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
		enterRule(_localctx, 6, RULE_if_stat);
		int _la;
		try {
			setState(201);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(191);
				match(CBO);
				setState(195);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << VAL) | (1L << VAR) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << AMP) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId))) != 0)) {
					{
					{
					setState(192);
					blockBody();
					}
					}
					setState(197);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(198);
				match(CBC);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(199);
				expression(0);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(200);
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
		enterRule(_localctx, 8, RULE_tuple);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(LB);
			setState(218);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << AMP) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId))) != 0)) {
				{
				setState(204);
				expression(0);
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(205);
					match(COMMA);
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
					expression(0);
					}
					}
					setState(217);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(220);
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

	public static class FieldThContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
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
			setState(222);
			id();
			setState(223);
			match(CON);
			setState(224);
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
		public Token ptr;
		public Token typeName;
		public Token TypeId;
		public List<Token> tparams = new ArrayList<Token>();
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
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
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==MUL) {
				{
				setState(226);
				((ScalarThContext)_localctx).ptr = match(MUL);
				}
			}

			setState(229);
			((ScalarThContext)_localctx).typeName = match(TypeId);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(230);
				match(BRACKET_LEFT);
				setState(231);
				((ScalarThContext)_localctx).TypeId = match(TypeId);
				((ScalarThContext)_localctx).tparams.add(((ScalarThContext)_localctx).TypeId);
				setState(236);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(232);
					match(COMMA);
					setState(233);
					((ScalarThContext)_localctx).TypeId = match(TypeId);
					((ScalarThContext)_localctx).tparams.add(((ScalarThContext)_localctx).TypeId);
					}
					}
					setState(238);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(239);
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
		public TypeHintContext ret;
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
			setState(242);
			match(LB);
			setState(251);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << LB) | (1L << TypeId))) != 0)) {
				{
				setState(243);
				((FnThContext)_localctx).typeHint = typeHint();
				((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
				setState(248);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(244);
					match(COMMA);
					setState(245);
					((FnThContext)_localctx).typeHint = typeHint();
					((FnThContext)_localctx).args.add(((FnThContext)_localctx).typeHint);
					}
					}
					setState(250);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(253);
			match(RB);
			setState(254);
			match(ARROW_RIGHT);
			setState(255);
			((FnThContext)_localctx).ret = typeHint();
			}
		}
		catch (RecognitionException re) {
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
		public List<TypeHintContext> typeHint() {
			return getRuleContexts(TypeHintContext.class);
		}
		public TypeHintContext typeHint(int i) {
			return getRuleContext(TypeHintContext.class,i);
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
			setState(257);
			match(LB);
			setState(258);
			typeHint();
			setState(263);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(259);
				match(COMMA);
				setState(260);
				typeHint();
				}
				}
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(266);
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
			setState(271);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				fnTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
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
			setState(273);
			nonUnionTh();
			setState(276); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(274);
					match(VERT_LINE);
					setState(275);
					nonUnionTh();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(278); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
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
		public FnThContext fnTh() {
			return getRuleContext(FnThContext.class,0);
		}
		public StructThContext structTh() {
			return getRuleContext(StructThContext.class,0);
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
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(280);
				scalarTh();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				fnTh();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(282);
				structTh();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(283);
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
		enterRule(_localctx, 24, RULE_matchDash);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
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
		enterRule(_localctx, 26, RULE_bindVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
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
		enterRule(_localctx, 28, RULE_matchId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_la = _input.LA(1);
			if ( !(_la==MATCH_SELF || _la==MatchId) ) {
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
		enterRule(_localctx, 30, RULE_matchBracketsExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
			match(DOLLAR_CBO);
			setState(293);
			expression(0);
			setState(294);
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
		enterRule(_localctx, 32, RULE_matchExpression);
		try {
			setState(299);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IntLiteral:
			case HexLiteral:
			case FloatLiteral:
			case BooleanLiteral:
			case StringLiteral:
				enterOuterAlt(_localctx, 1);
				{
				setState(296);
				literal();
				}
				break;
			case MATCH_SELF:
			case MatchId:
				enterOuterAlt(_localctx, 2);
				{
				setState(297);
				matchId();
				}
				break;
			case DOLLAR_CBO:
				enterOuterAlt(_localctx, 3);
				{
				setState(298);
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
		public ScalarThContext scalarTh() {
			return getRuleContext(ScalarThContext.class,0);
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
		enterRule(_localctx, 34, RULE_destruct);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(301);
				id();
				setState(302);
				match(EQ);
				}
			}

			setState(306);
			scalarTh();
			setState(307);
			match(LB);
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << DOLLAR_CBO) | (1L << SELF) | (1L << MATCH_SELF) | (1L << DASH) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId) | (1L << TypeId) | (1L << MatchId))) != 0)) {
				{
				setState(308);
				matchOver();
				setState(319);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(309);
					match(COMMA);
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NL) {
						{
						{
						setState(310);
						match(NL);
						}
						}
						setState(315);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(316);
					matchOver();
					}
					}
					setState(321);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(324);
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

	public static class MatchTypeContext extends ParserRuleContext {
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public ScalarThContext scalarTh() {
			return getRuleContext(ScalarThContext.class,0);
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
		enterRule(_localctx, 36, RULE_matchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(VarId);
			setState(330);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(327);
				match(NL);
				}
				}
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(333);
			match(CON);
			setState(337);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(334);
				match(NL);
				}
				}
				setState(339);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(340);
			scalarTh();
			}
		}
		catch (RecognitionException re) {
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
		enterRule(_localctx, 38, RULE_matchOver);
		try {
			setState(347);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(342);
				matchDash();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(343);
				bindVar();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(344);
				matchExpression();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(345);
				destruct();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(346);
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
		public If_statContext onMatch;
		public MatchOverContext matchOver() {
			return getRuleContext(MatchOverContext.class,0);
		}
		public If_statContext if_stat() {
			return getRuleContext(If_statContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 40, RULE_matchCase);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(349);
			match(OF);
			setState(353);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(350);
				match(NL);
				}
				}
				setState(355);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(356);
			matchOver();
			setState(360);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(357);
				match(NL);
				}
				}
				setState(362);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IF) {
				{
				setState(363);
				match(IF);
				setState(367);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(364);
					match(NL);
					}
					}
					setState(369);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(370);
				((MatchCaseContext)_localctx).cond = expression(0);
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
				}
			}

			setState(379);
			match(ARROW_RIGHT);
			setState(383);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(380);
				match(NL);
				}
				}
				setState(385);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(386);
			((MatchCaseContext)_localctx).onMatch = if_stat();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
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
		enterRule(_localctx, 42, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			((VariableContext)_localctx).valVar = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==VAL || _la==VAR) ) {
				((VariableContext)_localctx).valVar = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
			setState(395);
			match(VarId);
			setState(398);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(396);
				match(CON);
				setState(397);
				typeHint();
				}
			}

			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(400);
				match(NL);
				}
				}
				setState(405);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(406);
			match(EQ);
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(407);
				match(NL);
				}
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class StoreContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> VarId() { return getTokens(M2Parser.VarId); }
		public TerminalNode VarId(int i) {
			return getToken(M2Parser.VarId, i);
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
		enterRule(_localctx, 44, RULE_store);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(415);
			id();
			setState(420);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOT) {
				{
				{
				setState(416);
				match(DOT);
				setState(417);
				match(VarId);
				}
				}
				setState(422);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(424);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LB) {
				{
				setState(423);
				tuple();
				}
			}

			setState(426);
			match(EQ);
			setState(427);
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

	public static class While_statContext extends ParserRuleContext {
		public ExpressionContext cond;
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
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
		enterRule(_localctx, 46, RULE_while_stat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429);
			match(WHILE);
			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(430);
				match(NL);
				}
				}
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(436);
			((While_statContext)_localctx).cond = expression(0);
			setState(440);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(437);
				match(NL);
				}
				}
				setState(442);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(443);
			match(CBO);
			setState(447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(444);
				match(NL);
				}
				}
				setState(449);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(453);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << VAL) | (1L << VAR) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << AMP) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId))) != 0)) {
				{
				{
				setState(450);
				blockBody();
				}
				}
				setState(455);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(456);
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

	public static class FnArgContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
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
		enterRule(_localctx, 48, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(458);
			id();
			setState(461);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CON) {
				{
				setState(459);
				match(CON);
				setState(460);
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

	public static class LambdaContext extends ParserRuleContext {
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
		enterRule(_localctx, 50, RULE_lambda);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(463);
			match(CBO);
			setState(474);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
			case 1:
				{
				setState(464);
				fnArg();
				setState(469);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(465);
					match(COMMA);
					setState(466);
					fnArg();
					}
					}
					setState(471);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(472);
				match(ARROW_RIGHT);
				}
				break;
			}
			setState(479);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(476);
					match(NL);
					}
					} 
				}
				setState(481);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			setState(485);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MUL) | (1L << EXCL) | (1L << LB) | (1L << IF) | (1L << CBO) | (1L << WHILE) | (1L << VAL) | (1L << VAR) | (1L << BACK_SLASH) | (1L << SELF) | (1L << MATCH) | (1L << AMP) | (1L << IntLiteral) | (1L << HexLiteral) | (1L << FloatLiteral) | (1L << BooleanLiteral) | (1L << StringLiteral) | (1L << VarId))) != 0)) {
				{
				{
				setState(482);
				blockBody();
				}
				}
				setState(487);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(491);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(488);
				match(NL);
				}
				}
				setState(493);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(494);
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
		public While_statContext while_stat() {
			return getRuleContext(While_statContext.class,0);
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
		enterRule(_localctx, 52, RULE_blockBody);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(500);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(496);
				variable();
				}
				break;
			case 2:
				{
				setState(497);
				store();
				}
				break;
			case 3:
				{
				setState(498);
				while_stat();
				}
				break;
			case 4:
				{
				setState(499);
				expression(0);
				}
				break;
			}
			setState(503);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(502);
				match(SEMI);
				}
				break;
			}
			setState(508);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(505);
					match(NL);
					}
					} 
				}
				setState(510);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,59,_ctx);
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

	public static class HLambdaContext extends ParserRuleContext {
		public BlockBodyContext blockBody() {
			return getRuleContext(BlockBodyContext.class,0);
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
		public HLambdaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hLambda; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitHLambda(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HLambdaContext hLambda() throws RecognitionException {
		HLambdaContext _localctx = new HLambdaContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_hLambda);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			match(BACK_SLASH);
			setState(520);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(512);
				fnArg();
				setState(517);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(513);
					match(COMMA);
					setState(514);
					fnArg();
					}
					}
					setState(519);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(522);
			match(ARROW_RIGHT);
			setState(526);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(523);
				match(NL);
				}
				}
				setState(528);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(529);
			blockBody();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode TypeId() { return getToken(M2Parser.TypeId, 0); }
		public LltypeContext lltype() {
			return getRuleContext(LltypeContext.class,0);
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
		enterRule(_localctx, 56, RULE_scalarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(531);
			match(TYPE);
			setState(532);
			match(TypeId);
			setState(533);
			match(EQ);
			setState(534);
			lltype();
			}
		}
		catch (RecognitionException re) {
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
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
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
		enterRule(_localctx, 58, RULE_typeField);
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
			match(VarId);
			setState(540);
			match(CON);
			setState(541);
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

	public static class StructTypeContext extends ParserRuleContext {
		public Token name;
		public Token TypeId;
		public List<Token> tparams = new ArrayList<Token>();
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
		enterRule(_localctx, 60, RULE_structType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(543);
			match(TYPE);
			setState(544);
			((StructTypeContext)_localctx).name = match(TypeId);
			setState(555);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(545);
				match(BRACKET_LEFT);
				setState(546);
				((StructTypeContext)_localctx).TypeId = match(TypeId);
				((StructTypeContext)_localctx).tparams.add(((StructTypeContext)_localctx).TypeId);
				setState(551);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(547);
					match(COMMA);
					setState(548);
					((StructTypeContext)_localctx).TypeId = match(TypeId);
					((StructTypeContext)_localctx).tparams.add(((StructTypeContext)_localctx).TypeId);
					}
					}
					setState(553);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(554);
				match(BRACKET_RIGTH);
				}
			}

			setState(557);
			match(EQ);
			setState(558);
			match(LB);
			setState(562);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(559);
				match(NL);
				}
				}
				setState(564);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(565);
			typeField();
			setState(576);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(566);
				match(COMMA);
				setState(570);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(567);
					match(NL);
					}
					}
					setState(572);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(573);
				typeField();
				}
				}
				setState(578);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(582);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(579);
				match(NL);
				}
				}
				setState(584);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		public Token TypeId;
		public List<Token> tparams = new ArrayList<Token>();
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
		enterRule(_localctx, 62, RULE_unionType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(587);
			match(TYPE);
			setState(588);
			((UnionTypeContext)_localctx).name = match(TypeId);
			setState(599);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(589);
				match(BRACKET_LEFT);
				setState(590);
				((UnionTypeContext)_localctx).TypeId = match(TypeId);
				((UnionTypeContext)_localctx).tparams.add(((UnionTypeContext)_localctx).TypeId);
				setState(595);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(591);
					match(COMMA);
					setState(592);
					((UnionTypeContext)_localctx).TypeId = match(TypeId);
					((UnionTypeContext)_localctx).tparams.add(((UnionTypeContext)_localctx).TypeId);
					}
					}
					setState(597);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(598);
				match(BRACKET_RIGTH);
				}
			}

			setState(601);
			match(EQ);
			setState(602);
			scalarTh();
			setState(605); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(603);
				match(VERT_LINE);
				setState(604);
				scalarTh();
				}
				}
				setState(607); 
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
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_type);
		try {
			setState(612);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(609);
				scalarType();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(610);
				structType();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(611);
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
		public Token TypeId;
		public List<Token> tparams = new ArrayList<Token>();
		public TerminalNode VarId() { return getToken(M2Parser.VarId, 0); }
		public HLambdaContext hLambda() {
			return getRuleContext(HLambdaContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(M2Parser.NL); }
		public TerminalNode NL(int i) {
			return getToken(M2Parser.NL, i);
		}
		public List<TerminalNode> TypeId() { return getTokens(M2Parser.TypeId); }
		public TerminalNode TypeId(int i) {
			return getToken(M2Parser.TypeId, i);
		}
		public LambdaContext lambda() {
			return getRuleContext(LambdaContext.class,0);
		}
		public TypeHintContext typeHint() {
			return getRuleContext(TypeHintContext.class,0);
		}
		public LldefContext lldef() {
			return getRuleContext(LldefContext.class,0);
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
		enterRule(_localctx, 66, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(614);
			match(DEF);
			setState(615);
			((FunctionContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << PLUS) | (1L << MUL) | (1L << DIV) | (1L << EXCL) | (1L << MORE_) | (1L << MORE_EQ) | (1L << LESS) | (1L << LESS_EQ) | (1L << EQEQ) | (1L << NOTEQ) | (1L << VarId))) != 0)) ) {
				((FunctionContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==BRACKET_LEFT) {
				{
				setState(616);
				match(BRACKET_LEFT);
				setState(617);
				((FunctionContext)_localctx).TypeId = match(TypeId);
				((FunctionContext)_localctx).tparams.add(((FunctionContext)_localctx).TypeId);
				setState(622);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(618);
					match(COMMA);
					setState(619);
					((FunctionContext)_localctx).TypeId = match(TypeId);
					((FunctionContext)_localctx).tparams.add(((FunctionContext)_localctx).TypeId);
					}
					}
					setState(624);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(625);
				match(BRACKET_RIGTH);
				}
			}

			setState(628);
			match(EQ);
			setState(632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(629);
				match(NL);
				}
				}
				setState(634);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(645);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(635);
				hLambda();
				}
				break;
			case 2:
				{
				{
				setState(636);
				lambda();
				setState(637);
				match(CON);
				setState(638);
				typeHint();
				}
				}
				break;
			case 3:
				{
				{
				setState(640);
				lldef();
				setState(641);
				match(CON);
				setState(642);
				typeHint();
				}
				}
				break;
			case 4:
				{
				setState(644);
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
		enterRule(_localctx, 68, RULE_level1);
		try {
			setState(649);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYPE:
				enterOuterAlt(_localctx, 1);
				{
				setState(647);
				type();
				}
				break;
			case DEF:
				enterOuterAlt(_localctx, 2);
				{
				setState(648);
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
		enterRule(_localctx, 70, RULE_module);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(654);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(651);
				match(NL);
				}
				}
				setState(656);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(675);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TYPE || _la==DEF) {
				{
				setState(657);
				level1();
				setState(666);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(659); 
						_errHandler.sync(this);
						_la = _input.LA(1);
						do {
							{
							{
							setState(658);
							match(NL);
							}
							}
							setState(661); 
							_errHandler.sync(this);
							_la = _input.LA(1);
						} while ( _la==NL );
						setState(663);
						level1();
						}
						} 
					}
					setState(668);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				}
				setState(672);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(669);
					match(NL);
					}
					}
					setState(674);
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

	public static class LltypeContext extends ParserRuleContext {
		public TerminalNode LlBegin() { return getToken(M2Parser.LlBegin, 0); }
		public TerminalNode IrInline() { return getToken(M2Parser.IrInline, 0); }
		public TerminalNode LlEnd() { return getToken(M2Parser.LlEnd, 0); }
		public LltypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lltype; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLltype(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LltypeContext lltype() throws RecognitionException {
		LltypeContext _localctx = new LltypeContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_lltype);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			match(LlBegin);
			setState(678);
			match(IrInline);
			setState(679);
			match(LlEnd);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LldefContext extends ParserRuleContext {
		public TerminalNode LlDef() { return getToken(M2Parser.LlDef, 0); }
		public TerminalNode IrInline() { return getToken(M2Parser.IrInline, 0); }
		public TerminalNode LlEnd() { return getToken(M2Parser.LlEnd, 0); }
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public LldefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lldef; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof M2ParserVisitor ) return ((M2ParserVisitor<? extends T>)visitor).visitLldef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LldefContext lldef() throws RecognitionException {
		LldefContext _localctx = new LldefContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_lldef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(681);
			match(LlDef);
			setState(690);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SELF || _la==VarId) {
				{
				setState(682);
				fnArg();
				setState(687);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(683);
					match(COMMA);
					setState(684);
					fnArg();
					}
					}
					setState(689);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(692);
			match(ARROW_RIGHT);
			setState(693);
			match(IrInline);
			setState(694);
			match(LlEnd);
			}
		}
		catch (RecognitionException re) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3=\u02bb\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4e\n"+
		"\4\f\4\16\4h\13\4\3\4\3\4\7\4l\n\4\f\4\16\4o\13\4\3\4\3\4\7\4s\n\4\f\4"+
		"\16\4v\13\4\3\4\3\4\3\4\7\4{\n\4\f\4\16\4~\13\4\3\4\3\4\7\4\u0082\n\4"+
		"\f\4\16\4\u0085\13\4\3\4\5\4\u0088\n\4\3\4\3\4\7\4\u008c\n\4\f\4\16\4"+
		"\u008f\13\4\3\4\3\4\7\4\u0093\n\4\f\4\16\4\u0096\13\4\3\4\3\4\7\4\u009a"+
		"\n\4\f\4\16\4\u009d\13\4\6\4\u009f\n\4\r\4\16\4\u00a0\5\4\u00a3\n\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u00bd\n\4\f\4\16\4\u00c0\13\4\3\5\3\5\7\5"+
		"\u00c4\n\5\f\5\16\5\u00c7\13\5\3\5\3\5\3\5\5\5\u00cc\n\5\3\6\3\6\3\6\3"+
		"\6\7\6\u00d2\n\6\f\6\16\6\u00d5\13\6\3\6\7\6\u00d8\n\6\f\6\16\6\u00db"+
		"\13\6\5\6\u00dd\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\5\b\u00e6\n\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\b\u00ed\n\b\f\b\16\b\u00f0\13\b\3\b\5\b\u00f3\n\b\3\t\3"+
		"\t\3\t\3\t\7\t\u00f9\n\t\f\t\16\t\u00fc\13\t\5\t\u00fe\n\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\7\n\u0108\n\n\f\n\16\n\u010b\13\n\3\n\3\n\3\13\3"+
		"\13\3\13\5\13\u0112\n\13\3\f\3\f\3\f\6\f\u0117\n\f\r\f\16\f\u0118\3\r"+
		"\3\r\3\r\3\r\5\r\u011f\n\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\5\22\u012e\n\22\3\23\3\23\3\23\5\23\u0133\n\23\3"+
		"\23\3\23\3\23\3\23\3\23\7\23\u013a\n\23\f\23\16\23\u013d\13\23\3\23\7"+
		"\23\u0140\n\23\f\23\16\23\u0143\13\23\5\23\u0145\n\23\3\23\3\23\3\24\3"+
		"\24\7\24\u014b\n\24\f\24\16\24\u014e\13\24\3\24\3\24\7\24\u0152\n\24\f"+
		"\24\16\24\u0155\13\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\5\25\u015e\n"+
		"\25\3\26\3\26\7\26\u0162\n\26\f\26\16\26\u0165\13\26\3\26\3\26\7\26\u0169"+
		"\n\26\f\26\16\26\u016c\13\26\3\26\3\26\7\26\u0170\n\26\f\26\16\26\u0173"+
		"\13\26\3\26\3\26\7\26\u0177\n\26\f\26\16\26\u017a\13\26\5\26\u017c\n\26"+
		"\3\26\3\26\7\26\u0180\n\26\f\26\16\26\u0183\13\26\3\26\3\26\3\27\3\27"+
		"\7\27\u0189\n\27\f\27\16\27\u018c\13\27\3\27\3\27\3\27\5\27\u0191\n\27"+
		"\3\27\7\27\u0194\n\27\f\27\16\27\u0197\13\27\3\27\3\27\7\27\u019b\n\27"+
		"\f\27\16\27\u019e\13\27\3\27\3\27\3\30\3\30\3\30\7\30\u01a5\n\30\f\30"+
		"\16\30\u01a8\13\30\3\30\5\30\u01ab\n\30\3\30\3\30\3\30\3\31\3\31\7\31"+
		"\u01b2\n\31\f\31\16\31\u01b5\13\31\3\31\3\31\7\31\u01b9\n\31\f\31\16\31"+
		"\u01bc\13\31\3\31\3\31\7\31\u01c0\n\31\f\31\16\31\u01c3\13\31\3\31\7\31"+
		"\u01c6\n\31\f\31\16\31\u01c9\13\31\3\31\3\31\3\32\3\32\3\32\5\32\u01d0"+
		"\n\32\3\33\3\33\3\33\3\33\7\33\u01d6\n\33\f\33\16\33\u01d9\13\33\3\33"+
		"\3\33\5\33\u01dd\n\33\3\33\7\33\u01e0\n\33\f\33\16\33\u01e3\13\33\3\33"+
		"\7\33\u01e6\n\33\f\33\16\33\u01e9\13\33\3\33\7\33\u01ec\n\33\f\33\16\33"+
		"\u01ef\13\33\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u01f7\n\34\3\34\5\34\u01fa"+
		"\n\34\3\34\7\34\u01fd\n\34\f\34\16\34\u0200\13\34\3\35\3\35\3\35\3\35"+
		"\7\35\u0206\n\35\f\35\16\35\u0209\13\35\5\35\u020b\n\35\3\35\3\35\7\35"+
		"\u020f\n\35\f\35\16\35\u0212\13\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\5\37\u021c\n\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \7 \u0228\n"+
		" \f \16 \u022b\13 \3 \5 \u022e\n \3 \3 \3 \7 \u0233\n \f \16 \u0236\13"+
		" \3 \3 \3 \7 \u023b\n \f \16 \u023e\13 \3 \7 \u0241\n \f \16 \u0244\13"+
		" \3 \7 \u0247\n \f \16 \u024a\13 \3 \3 \3!\3!\3!\3!\3!\3!\7!\u0254\n!"+
		"\f!\16!\u0257\13!\3!\5!\u025a\n!\3!\3!\3!\3!\6!\u0260\n!\r!\16!\u0261"+
		"\3\"\3\"\3\"\5\"\u0267\n\"\3#\3#\3#\3#\3#\3#\7#\u026f\n#\f#\16#\u0272"+
		"\13#\3#\5#\u0275\n#\3#\3#\7#\u0279\n#\f#\16#\u027c\13#\3#\3#\3#\3#\3#"+
		"\3#\3#\3#\3#\3#\5#\u0288\n#\3$\3$\5$\u028c\n$\3%\7%\u028f\n%\f%\16%\u0292"+
		"\13%\3%\3%\6%\u0296\n%\r%\16%\u0297\3%\7%\u029b\n%\f%\16%\u029e\13%\3"+
		"%\7%\u02a1\n%\f%\16%\u02a4\13%\5%\u02a6\n%\3&\3&\3&\3&\3\'\3\'\3\'\3\'"+
		"\7\'\u02b0\n\'\f\'\16\'\u02b3\13\'\5\'\u02b5\n\'\3\'\3\'\3\'\3\'\3\'\2"+
		"\3\6(\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>"+
		"@BDFHJL\2\r\3\2\648\4\2##99\3\2\5\6\4\2\3\499\3\2\f\17\3\2\21\22\3\2\32"+
		"\33\6\2\3\6\f\17\21\2299\4\2$$;;\3\2\35\36\6\2\3\7\f\17\21\2299\2\u0306"+
		"\2N\3\2\2\2\4P\3\2\2\2\6\u00a2\3\2\2\2\b\u00cb\3\2\2\2\n\u00cd\3\2\2\2"+
		"\f\u00e0\3\2\2\2\16\u00e5\3\2\2\2\20\u00f4\3\2\2\2\22\u0103\3\2\2\2\24"+
		"\u0111\3\2\2\2\26\u0113\3\2\2\2\30\u011e\3\2\2\2\32\u0120\3\2\2\2\34\u0122"+
		"\3\2\2\2\36\u0124\3\2\2\2 \u0126\3\2\2\2\"\u012d\3\2\2\2$\u0132\3\2\2"+
		"\2&\u0148\3\2\2\2(\u015d\3\2\2\2*\u015f\3\2\2\2,\u0186\3\2\2\2.\u01a1"+
		"\3\2\2\2\60\u01af\3\2\2\2\62\u01cc\3\2\2\2\64\u01d1\3\2\2\2\66\u01f6\3"+
		"\2\2\28\u0201\3\2\2\2:\u0215\3\2\2\2<\u021b\3\2\2\2>\u0221\3\2\2\2@\u024d"+
		"\3\2\2\2B\u0266\3\2\2\2D\u0268\3\2\2\2F\u028b\3\2\2\2H\u0290\3\2\2\2J"+
		"\u02a7\3\2\2\2L\u02ab\3\2\2\2NO\t\2\2\2O\3\3\2\2\2PQ\t\3\2\2Q\5\3\2\2"+
		"\2RS\b\4\1\2S\u00a3\5\2\2\2T\u00a3\5\4\3\2UV\7\n\2\2VW\5\6\4\2WX\7\t\2"+
		"\2X\u00a3\3\2\2\2Y\u00a3\5\n\6\2Z[\7.\2\2[\u00a3\5\6\4\21\\]\7\5\2\2]"+
		"\u00a3\5\6\4\20^\u00a3\5\64\33\2_\u00a3\58\35\2`a\7\7\2\2a\u00a3\5\6\4"+
		"\nbf\7\24\2\2ce\7\62\2\2dc\3\2\2\2eh\3\2\2\2fd\3\2\2\2fg\3\2\2\2gi\3\2"+
		"\2\2hf\3\2\2\2im\5\6\4\2jl\7\62\2\2kj\3\2\2\2lo\3\2\2\2mk\3\2\2\2mn\3"+
		"\2\2\2np\3\2\2\2om\3\2\2\2pt\7\25\2\2qs\7\62\2\2rq\3\2\2\2sv\3\2\2\2t"+
		"r\3\2\2\2tu\3\2\2\2uw\3\2\2\2vt\3\2\2\2wx\5\b\5\2x\u0087\3\2\2\2y{\7\62"+
		"\2\2zy\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\177\3\2\2\2~|\3\2\2\2\177"+
		"\u0083\7\26\2\2\u0080\u0082\7\62\2\2\u0081\u0080\3\2\2\2\u0082\u0085\3"+
		"\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0086\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0086\u0088\5\b\5\2\u0087|\3\2\2\2\u0087\u0088\3\2\2\2"+
		"\u0088\u00a3\3\2\2\2\u0089\u008d\7(\2\2\u008a\u008c\7\62\2\2\u008b\u008a"+
		"\3\2\2\2\u008c\u008f\3\2\2\2\u008d\u008b\3\2\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u0090\3\2\2\2\u008f\u008d\3\2\2\2\u0090\u0094\5\6\4\2\u0091\u0093\7\62"+
		"\2\2\u0092\u0091\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094"+
		"\u0095\3\2\2\2\u0095\u009e\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u009b\5*"+
		"\26\2\u0098\u009a\7\62\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b"+
		"\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009f\3\2\2\2\u009d\u009b\3\2"+
		"\2\2\u009e\u0097\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0"+
		"\u00a1\3\2\2\2\u00a1\u00a3\3\2\2\2\u00a2R\3\2\2\2\u00a2T\3\2\2\2\u00a2"+
		"U\3\2\2\2\u00a2Y\3\2\2\2\u00a2Z\3\2\2\2\u00a2\\\3\2\2\2\u00a2^\3\2\2\2"+
		"\u00a2_\3\2\2\2\u00a2`\3\2\2\2\u00a2b\3\2\2\2\u00a2\u0089\3\2\2\2\u00a3"+
		"\u00be\3\2\2\2\u00a4\u00a5\f\t\2\2\u00a5\u00a6\t\4\2\2\u00a6\u00bd\5\6"+
		"\4\n\u00a7\u00a8\f\b\2\2\u00a8\u00a9\t\5\2\2\u00a9\u00bd\5\6\4\t\u00aa"+
		"\u00ab\f\7\2\2\u00ab\u00ac\t\6\2\2\u00ac\u00bd\5\6\4\b\u00ad\u00ae\f\6"+
		"\2\2\u00ae\u00af\t\7\2\2\u00af\u00bd\5\6\4\7\u00b0\u00b1\f\5\2\2\u00b1"+
		"\u00b2\t\b\2\2\u00b2\u00bd\5\6\4\6\u00b3\u00b4\f\17\2\2\u00b4\u00b5\7"+
		"\b\2\2\u00b5\u00b6\t\t\2\2\u00b6\u00bd\5\n\6\2\u00b7\u00b8\f\16\2\2\u00b8"+
		"\u00b9\7\b\2\2\u00b9\u00bd\79\2\2\u00ba\u00bb\f\r\2\2\u00bb\u00bd\5\n"+
		"\6\2\u00bc\u00a4\3\2\2\2\u00bc\u00a7\3\2\2\2\u00bc\u00aa\3\2\2\2\u00bc"+
		"\u00ad\3\2\2\2\u00bc\u00b0\3\2\2\2\u00bc\u00b3\3\2\2\2\u00bc\u00b7\3\2"+
		"\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be"+
		"\u00bf\3\2\2\2\u00bf\7\3\2\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c5\7\27\2"+
		"\2\u00c2\u00c4\5\66\34\2\u00c3\u00c2\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\u00c8\3\2\2\2\u00c7\u00c5\3\2"+
		"\2\2\u00c8\u00cc\7\31\2\2\u00c9\u00cc\5\6\4\2\u00ca\u00cc\5.\30\2\u00cb"+
		"\u00c1\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00ca\3\2\2\2\u00cc\t\3\2\2\2"+
		"\u00cd\u00dc\7\n\2\2\u00ce\u00d9\5\6\4\2\u00cf\u00d3\7\13\2\2\u00d0\u00d2"+
		"\7\62\2\2\u00d1\u00d0\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2"+
		"\u00d3\u00d4\3\2\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d8"+
		"\5\6\4\2\u00d7\u00cf\3\2\2\2\u00d8\u00db\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9"+
		"\u00da\3\2\2\2\u00da\u00dd\3\2\2\2\u00db\u00d9\3\2\2\2\u00dc\u00ce\3\2"+
		"\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\7\t\2\2\u00df"+
		"\13\3\2\2\2\u00e0\u00e1\5\4\3\2\u00e1\u00e2\7\37\2\2\u00e2\u00e3\5\30"+
		"\r\2\u00e3\r\3\2\2\2\u00e4\u00e6\7\5\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6"+
		"\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00f2\7:\2\2\u00e8\u00e9\7,\2\2\u00e9"+
		"\u00ee\7:\2\2\u00ea\u00eb\7\13\2\2\u00eb\u00ed\7:\2\2\u00ec\u00ea\3\2"+
		"\2\2\u00ed\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef"+
		"\u00f1\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f1\u00f3\7-\2\2\u00f2\u00e8\3\2"+
		"\2\2\u00f2\u00f3\3\2\2\2\u00f3\17\3\2\2\2\u00f4\u00fd\7\n\2\2\u00f5\u00fa"+
		"\5\30\r\2\u00f6\u00f7\7\13\2\2\u00f7\u00f9\5\30\r\2\u00f8\u00f6\3\2\2"+
		"\2\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fe"+
		"\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00f5\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u0100\7\t\2\2\u0100\u0101\7 \2\2\u0101\u0102\5\30"+
		"\r\2\u0102\21\3\2\2\2\u0103\u0104\7\n\2\2\u0104\u0109\5\30\r\2\u0105\u0106"+
		"\7\13\2\2\u0106\u0108\5\30\r\2\u0107\u0105\3\2\2\2\u0108\u010b\3\2\2\2"+
		"\u0109\u0107\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010c\3\2\2\2\u010b\u0109"+
		"\3\2\2\2\u010c\u010d\7\t\2\2\u010d\23\3\2\2\2\u010e\u0112\5\16\b\2\u010f"+
		"\u0112\5\20\t\2\u0110\u0112\5\22\n\2\u0111\u010e\3\2\2\2\u0111\u010f\3"+
		"\2\2\2\u0111\u0110\3\2\2\2\u0112\25\3\2\2\2\u0113\u0116\5\24\13\2\u0114"+
		"\u0115\7+\2\2\u0115\u0117\5\24\13\2\u0116\u0114\3\2\2\2\u0117\u0118\3"+
		"\2\2\2\u0118\u0116\3\2\2\2\u0118\u0119\3\2\2\2\u0119\27\3\2\2\2\u011a"+
		"\u011f\5\16\b\2\u011b\u011f\5\20\t\2\u011c\u011f\5\22\n\2\u011d\u011f"+
		"\5\26\f\2\u011e\u011a\3\2\2\2\u011e\u011b\3\2\2\2\u011e\u011c\3\2\2\2"+
		"\u011e\u011d\3\2\2\2\u011f\31\3\2\2\2\u0120\u0121\7*\2\2\u0121\33\3\2"+
		"\2\2\u0122\u0123\5\4\3\2\u0123\35\3\2\2\2\u0124\u0125\t\n\2\2\u0125\37"+
		"\3\2\2\2\u0126\u0127\7\30\2\2\u0127\u0128\5\6\4\2\u0128\u0129\7\31\2\2"+
		"\u0129!\3\2\2\2\u012a\u012e\5\2\2\2\u012b\u012e\5\36\20\2\u012c\u012e"+
		"\5 \21\2\u012d\u012a\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012c\3\2\2\2\u012e"+
		"#\3\2\2\2\u012f\u0130\5\4\3\2\u0130\u0131\7\20\2\2\u0131\u0133\3\2\2\2"+
		"\u0132\u012f\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135"+
		"\5\16\b\2\u0135\u0144\7\n\2\2\u0136\u0141\5(\25\2\u0137\u013b\7\13\2\2"+
		"\u0138\u013a\7\62\2\2\u0139\u0138\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139"+
		"\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\3\2\2\2\u013d\u013b\3\2\2\2\u013e"+
		"\u0140\5(\25\2\u013f\u0137\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2"+
		"\2\2\u0141\u0142\3\2\2\2\u0142\u0145\3\2\2\2\u0143\u0141\3\2\2\2\u0144"+
		"\u0136\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\7\t"+
		"\2\2\u0147%\3\2\2\2\u0148\u014c\79\2\2\u0149\u014b\7\62\2\2\u014a\u0149"+
		"\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\u014f\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0153\7\37\2\2\u0150\u0152\7"+
		"\62\2\2\u0151\u0150\3\2\2\2\u0152\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153"+
		"\u0154\3\2\2\2\u0154\u0156\3\2\2\2\u0155\u0153\3\2\2\2\u0156\u0157\5\16"+
		"\b\2\u0157\'\3\2\2\2\u0158\u015e\5\32\16\2\u0159\u015e\5\34\17\2\u015a"+
		"\u015e\5\"\22\2\u015b\u015e\5$\23\2\u015c\u015e\5&\24\2\u015d\u0158\3"+
		"\2\2\2\u015d\u0159\3\2\2\2\u015d\u015a\3\2\2\2\u015d\u015b\3\2\2\2\u015d"+
		"\u015c\3\2\2\2\u015e)\3\2\2\2\u015f\u0163\7)\2\2\u0160\u0162\7\62\2\2"+
		"\u0161\u0160\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164"+
		"\3\2\2\2\u0164\u0166\3\2\2\2\u0165\u0163\3\2\2\2\u0166\u016a\5(\25\2\u0167"+
		"\u0169\7\62\2\2\u0168\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3"+
		"\2\2\2\u016a\u016b\3\2\2\2\u016b\u017b\3\2\2\2\u016c\u016a\3\2\2\2\u016d"+
		"\u0171\7\24\2\2\u016e\u0170\7\62\2\2\u016f\u016e\3\2\2\2\u0170\u0173\3"+
		"\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172\3\2\2\2\u0172\u0174\3\2\2\2\u0173"+
		"\u0171\3\2\2\2\u0174\u0178\5\6\4\2\u0175\u0177\7\62\2\2\u0176\u0175\3"+
		"\2\2\2\u0177\u017a\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179"+
		"\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017b\u016d\3\2\2\2\u017b\u017c\3\2"+
		"\2\2\u017c\u017d\3\2\2\2\u017d\u0181\7 \2\2\u017e\u0180\7\62\2\2\u017f"+
		"\u017e\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2"+
		"\2\2\u0182\u0184\3\2\2\2\u0183\u0181\3\2\2\2\u0184\u0185\5\b\5\2\u0185"+
		"+\3\2\2\2\u0186\u018a\t\13\2\2\u0187\u0189\7\62\2\2\u0188\u0187\3\2\2"+
		"\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018d"+
		"\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u0190\79\2\2\u018e\u018f\7\37\2\2\u018f"+
		"\u0191\5\30\r\2\u0190\u018e\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0195\3"+
		"\2\2\2\u0192\u0194\7\62\2\2\u0193\u0192\3\2\2\2\u0194\u0197\3\2\2\2\u0195"+
		"\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196\u0198\3\2\2\2\u0197\u0195\3\2"+
		"\2\2\u0198\u019c\7\20\2\2\u0199\u019b\7\62\2\2\u019a\u0199\3\2\2\2\u019b"+
		"\u019e\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019d\3\2\2\2\u019d\u019f\3\2"+
		"\2\2\u019e\u019c\3\2\2\2\u019f\u01a0\5\6\4\2\u01a0-\3\2\2\2\u01a1\u01a6"+
		"\5\4\3\2\u01a2\u01a3\7\b\2\2\u01a3\u01a5\79\2\2\u01a4\u01a2\3\2\2\2\u01a5"+
		"\u01a8\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2\u01a7\u01aa\3\2"+
		"\2\2\u01a8\u01a6\3\2\2\2\u01a9\u01ab\5\n\6\2\u01aa\u01a9\3\2\2\2\u01aa"+
		"\u01ab\3\2\2\2\u01ab\u01ac\3\2\2\2\u01ac\u01ad\7\20\2\2\u01ad\u01ae\5"+
		"\6\4\2\u01ae/\3\2\2\2\u01af\u01b3\7\34\2\2\u01b0\u01b2\7\62\2\2\u01b1"+
		"\u01b0\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1\3\2\2\2\u01b3\u01b4\3\2"+
		"\2\2\u01b4\u01b6\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6\u01ba\5\6\4\2\u01b7"+
		"\u01b9\7\62\2\2\u01b8\u01b7\3\2\2\2\u01b9\u01bc\3\2\2\2\u01ba\u01b8\3"+
		"\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bd\3\2\2\2\u01bc\u01ba\3\2\2\2\u01bd"+
		"\u01c1\7\27\2\2\u01be\u01c0\7\62\2\2\u01bf\u01be\3\2\2\2\u01c0\u01c3\3"+
		"\2\2\2\u01c1\u01bf\3\2\2\2\u01c1\u01c2\3\2\2\2\u01c2\u01c7\3\2\2\2\u01c3"+
		"\u01c1\3\2\2\2\u01c4\u01c6\5\66\34\2\u01c5\u01c4\3\2\2\2\u01c6\u01c9\3"+
		"\2\2\2\u01c7\u01c5\3\2\2\2\u01c7\u01c8\3\2\2\2\u01c8\u01ca\3\2\2\2\u01c9"+
		"\u01c7\3\2\2\2\u01ca\u01cb\7\31\2\2\u01cb\61\3\2\2\2\u01cc\u01cf\5\4\3"+
		"\2\u01cd\u01ce\7\37\2\2\u01ce\u01d0\5\30\r\2\u01cf\u01cd\3\2\2\2\u01cf"+
		"\u01d0\3\2\2\2\u01d0\63\3\2\2\2\u01d1\u01dc\7\27\2\2\u01d2\u01d7\5\62"+
		"\32\2\u01d3\u01d4\7\13\2\2\u01d4\u01d6\5\62\32\2\u01d5\u01d3\3\2\2\2\u01d6"+
		"\u01d9\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d8\3\2\2\2\u01d8\u01da\3\2"+
		"\2\2\u01d9\u01d7\3\2\2\2\u01da\u01db\7 \2\2\u01db\u01dd\3\2\2\2\u01dc"+
		"\u01d2\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01e1\3\2\2\2\u01de\u01e0\7\62"+
		"\2\2\u01df\u01de\3\2\2\2\u01e0\u01e3\3\2\2\2\u01e1\u01df\3\2\2\2\u01e1"+
		"\u01e2\3\2\2\2\u01e2\u01e7\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e4\u01e6\5\66"+
		"\34\2\u01e5\u01e4\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2\2\2\u01e7"+
		"\u01e8\3\2\2\2\u01e8\u01ed\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01ec\7\62"+
		"\2\2\u01eb\u01ea\3\2\2\2\u01ec\u01ef\3\2\2\2\u01ed\u01eb\3\2\2\2\u01ed"+
		"\u01ee\3\2\2\2\u01ee\u01f0\3\2\2\2\u01ef\u01ed\3\2\2\2\u01f0\u01f1\7\31"+
		"\2\2\u01f1\65\3\2\2\2\u01f2\u01f7\5,\27\2\u01f3\u01f7\5.\30\2\u01f4\u01f7"+
		"\5\60\31\2\u01f5\u01f7\5\6\4\2\u01f6\u01f2\3\2\2\2\u01f6\u01f3\3\2\2\2"+
		"\u01f6\u01f4\3\2\2\2\u01f6\u01f5\3\2\2\2\u01f7\u01f9\3\2\2\2\u01f8\u01fa"+
		"\7\23\2\2\u01f9\u01f8\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fe\3\2\2\2"+
		"\u01fb\u01fd\7\62\2\2\u01fc\u01fb\3\2\2\2\u01fd\u0200\3\2\2\2\u01fe\u01fc"+
		"\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\67\3\2\2\2\u0200\u01fe\3\2\2\2\u0201"+
		"\u020a\7\"\2\2\u0202\u0207\5\62\32\2\u0203\u0204\7\13\2\2\u0204\u0206"+
		"\5\62\32\2\u0205\u0203\3\2\2\2\u0206\u0209\3\2\2\2\u0207\u0205\3\2\2\2"+
		"\u0207\u0208\3\2\2\2\u0208\u020b\3\2\2\2\u0209\u0207\3\2\2\2\u020a\u0202"+
		"\3\2\2\2\u020a\u020b\3\2\2\2\u020b\u020c\3\2\2\2\u020c\u0210\7 \2\2\u020d"+
		"\u020f\7\62\2\2\u020e\u020d\3\2\2\2\u020f\u0212\3\2\2\2\u0210\u020e\3"+
		"\2\2\2\u0210\u0211\3\2\2\2\u0211\u0213\3\2\2\2\u0212\u0210\3\2\2\2\u0213"+
		"\u0214\5\66\34\2\u02149\3\2\2\2\u0215\u0216\7!\2\2\u0216\u0217\7:\2\2"+
		"\u0217\u0218\7\20\2\2\u0218\u0219\5J&\2\u0219;\3\2\2\2\u021a\u021c\7#"+
		"\2\2\u021b\u021a\3\2\2\2\u021b\u021c\3\2\2\2\u021c\u021d\3\2\2\2\u021d"+
		"\u021e\79\2\2\u021e\u021f\7\37\2\2\u021f\u0220\5\30\r\2\u0220=\3\2\2\2"+
		"\u0221\u0222\7!\2\2\u0222\u022d\7:\2\2\u0223\u0224\7,\2\2\u0224\u0229"+
		"\7:\2\2\u0225\u0226\7\13\2\2\u0226\u0228\7:\2\2\u0227\u0225\3\2\2\2\u0228"+
		"\u022b\3\2\2\2\u0229\u0227\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022c\3\2"+
		"\2\2\u022b\u0229\3\2\2\2\u022c\u022e\7-\2\2\u022d\u0223\3\2\2\2\u022d"+
		"\u022e\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0230\7\20\2\2\u0230\u0234\7"+
		"\n\2\2\u0231\u0233\7\62\2\2\u0232\u0231\3\2\2\2\u0233\u0236\3\2\2\2\u0234"+
		"\u0232\3\2\2\2\u0234\u0235\3\2\2\2\u0235\u0237\3\2\2\2\u0236\u0234\3\2"+
		"\2\2\u0237\u0242\5<\37\2\u0238\u023c\7\13\2\2\u0239\u023b\7\62\2\2\u023a"+
		"\u0239\3\2\2\2\u023b\u023e\3\2\2\2\u023c\u023a\3\2\2\2\u023c\u023d\3\2"+
		"\2\2\u023d\u023f\3\2\2\2\u023e\u023c\3\2\2\2\u023f\u0241\5<\37\2\u0240"+
		"\u0238\3\2\2\2\u0241\u0244\3\2\2\2\u0242\u0240\3\2\2\2\u0242\u0243\3\2"+
		"\2\2\u0243\u0248\3\2\2\2\u0244\u0242\3\2\2\2\u0245\u0247\7\62\2\2\u0246"+
		"\u0245\3\2\2\2\u0247\u024a\3\2\2\2\u0248\u0246\3\2\2\2\u0248\u0249\3\2"+
		"\2\2\u0249\u024b\3\2\2\2\u024a\u0248\3\2\2\2\u024b\u024c\7\t\2\2\u024c"+
		"?\3\2\2\2\u024d\u024e\7!\2\2\u024e\u0259\7:\2\2\u024f\u0250\7,\2\2\u0250"+
		"\u0255\7:\2\2\u0251\u0252\7\13\2\2\u0252\u0254\7:\2\2\u0253\u0251\3\2"+
		"\2\2\u0254\u0257\3\2\2\2\u0255\u0253\3\2\2\2\u0255\u0256\3\2\2\2\u0256"+
		"\u0258\3\2\2\2\u0257\u0255\3\2\2\2\u0258\u025a\7-\2\2\u0259\u024f\3\2"+
		"\2\2\u0259\u025a\3\2\2\2\u025a\u025b\3\2\2\2\u025b\u025c\7\20\2\2\u025c"+
		"\u025f\5\16\b\2\u025d\u025e\7+\2\2\u025e\u0260\5\16\b\2\u025f\u025d\3"+
		"\2\2\2\u0260\u0261\3\2\2\2\u0261\u025f\3\2\2\2\u0261\u0262\3\2\2\2\u0262"+
		"A\3\2\2\2\u0263\u0267\5:\36\2\u0264\u0267\5> \2\u0265\u0267\5@!\2\u0266"+
		"\u0263\3\2\2\2\u0266\u0264\3\2\2\2\u0266\u0265\3\2\2\2\u0267C\3\2\2\2"+
		"\u0268\u0269\7%\2\2\u0269\u0274\t\f\2\2\u026a\u026b\7,\2\2\u026b\u0270"+
		"\7:\2\2\u026c\u026d\7\13\2\2\u026d\u026f\7:\2\2\u026e\u026c\3\2\2\2\u026f"+
		"\u0272\3\2\2\2\u0270\u026e\3\2\2\2\u0270\u0271\3\2\2\2\u0271\u0273\3\2"+
		"\2\2\u0272\u0270\3\2\2\2\u0273\u0275\7-\2\2\u0274\u026a\3\2\2\2\u0274"+
		"\u0275\3\2\2\2\u0275\u0276\3\2\2\2\u0276\u027a\7\20\2\2\u0277\u0279\7"+
		"\62\2\2\u0278\u0277\3\2\2\2\u0279\u027c\3\2\2\2\u027a\u0278\3\2\2\2\u027a"+
		"\u027b\3\2\2\2\u027b\u0287\3\2\2\2\u027c\u027a\3\2\2\2\u027d\u0288\58"+
		"\35\2\u027e\u027f\5\64\33\2\u027f\u0280\7\37\2\2\u0280\u0281\5\30\r\2"+
		"\u0281\u0288\3\2\2\2\u0282\u0283\5L\'\2\u0283\u0284\7\37\2\2\u0284\u0285"+
		"\5\30\r\2\u0285\u0288\3\2\2\2\u0286\u0288\5\6\4\2\u0287\u027d\3\2\2\2"+
		"\u0287\u027e\3\2\2\2\u0287\u0282\3\2\2\2\u0287\u0286\3\2\2\2\u0288E\3"+
		"\2\2\2\u0289\u028c\5B\"\2\u028a\u028c\5D#\2\u028b\u0289\3\2\2\2\u028b"+
		"\u028a\3\2\2\2\u028cG\3\2\2\2\u028d\u028f\7\62\2\2\u028e\u028d\3\2\2\2"+
		"\u028f\u0292\3\2\2\2\u0290\u028e\3\2\2\2\u0290\u0291\3\2\2\2\u0291\u02a5"+
		"\3\2\2\2\u0292\u0290\3\2\2\2\u0293\u029c\5F$\2\u0294\u0296\7\62\2\2\u0295"+
		"\u0294\3\2\2\2\u0296\u0297\3\2\2\2\u0297\u0295\3\2\2\2\u0297\u0298\3\2"+
		"\2\2\u0298\u0299\3\2\2\2\u0299\u029b\5F$\2\u029a\u0295\3\2\2\2\u029b\u029e"+
		"\3\2\2\2\u029c\u029a\3\2\2\2\u029c\u029d\3\2\2\2\u029d\u02a2\3\2\2\2\u029e"+
		"\u029c\3\2\2\2\u029f\u02a1\7\62\2\2\u02a0\u029f\3\2\2\2\u02a1\u02a4\3"+
		"\2\2\2\u02a2\u02a0\3\2\2\2\u02a2\u02a3\3\2\2\2\u02a3\u02a6\3\2\2\2\u02a4"+
		"\u02a2\3\2\2\2\u02a5\u0293\3\2\2\2\u02a5\u02a6\3\2\2\2\u02a6I\3\2\2\2"+
		"\u02a7\u02a8\7/\2\2\u02a8\u02a9\7<\2\2\u02a9\u02aa\7=\2\2\u02aaK\3\2\2"+
		"\2\u02ab\u02b4\7\60\2\2\u02ac\u02b1\5\62\32\2\u02ad\u02ae\7\13\2\2\u02ae"+
		"\u02b0\5\62\32\2\u02af\u02ad\3\2\2\2\u02b0\u02b3\3\2\2\2\u02b1\u02af\3"+
		"\2\2\2\u02b1\u02b2\3\2\2\2\u02b2\u02b5\3\2\2\2\u02b3\u02b1\3\2\2\2\u02b4"+
		"\u02ac\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5\u02b6\3\2\2\2\u02b6\u02b7\7 "+
		"\2\2\u02b7\u02b8\7<\2\2\u02b8\u02b9\7=\2\2\u02b9M\3\2\2\2Xfmt|\u0083\u0087"+
		"\u008d\u0094\u009b\u00a0\u00a2\u00bc\u00be\u00c5\u00cb\u00d3\u00d9\u00dc"+
		"\u00e5\u00ee\u00f2\u00fa\u00fd\u0109\u0111\u0118\u011e\u012d\u0132\u013b"+
		"\u0141\u0144\u014c\u0153\u015d\u0163\u016a\u0171\u0178\u017b\u0181\u018a"+
		"\u0190\u0195\u019c\u01a6\u01aa\u01b3\u01ba\u01c1\u01c7\u01cf\u01d7\u01dc"+
		"\u01e1\u01e7\u01ed\u01f6\u01f9\u01fe\u0207\u020a\u0210\u021b\u0229\u022d"+
		"\u0234\u023c\u0242\u0248\u0255\u0259\u0261\u0266\u0270\u0274\u027a\u0287"+
		"\u028b\u0290\u0297\u029c\u02a2\u02a5\u02b1\u02b4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}