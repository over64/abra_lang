// Generated from /home/over/build/test_lang/antlr/Hello.g4 by ANTLR 4.5.3
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
public class HelloParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T_KW_VAL=17, 
		T_KW_VAR=18, T_KW_DEF=19, T_KW_TYPE=20, T_PERCENT=21, T_BL=22, T_AAA=23, 
		T_Q1=24, T_BS_XXX=25, T_BS_XXXX=26, BIN_OP=27, VAR_IDENT=28, TYPE_IDENT=29, 
		WS=30, NL=31, UINT=32;
	public static final int
		RULE_valDef = 0, RULE_varDef = 1, RULE_fnArg = 2, RULE_fnArgs = 3, RULE_llvmFnDef = 4, 
		RULE_abraFnDef = 5, RULE_fnDef = 6, RULE_fnBodyDef = 7, RULE_lang = 8, 
		RULE_structParamDef = 9, RULE_llvmTypeDef = 10, RULE_abraTypeDef = 11, 
		RULE_typeDef = 12, RULE_access = 13, RULE_expr = 14;
	public static final String[] ruleNames = {
		"valDef", "varDef", "fnArg", "fnArgs", "llvmFnDef", "abraFnDef", "fnDef", 
		"fnBodyDef", "lang", "structParamDef", "llvmTypeDef", "abraTypeDef", "typeDef", 
		"access", "expr"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "'='", "','", "'->'", "'+'", "'-'", "'*'", "'/'", "'::'", 
		"'=='", "'llvm'", "'{'", "'}'", "'('", "')'", "'.'", "'val'", "'var'", 
		"'def'", "'type'", "'%'", "'['", "']'", "'\"'", "'\\'", "'@'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "T_KW_VAL", "T_KW_VAR", "T_KW_DEF", "T_KW_TYPE", 
		"T_PERCENT", "T_BL", "T_AAA", "T_Q1", "T_BS_XXX", "T_BS_XXXX", "BIN_OP", 
		"VAR_IDENT", "TYPE_IDENT", "WS", "NL", "UINT"
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
	public String getGrammarFileName() { return "Hello.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HelloParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ValDefContext extends ParserRuleContext {
		public Token name;
		public Token ttype;
		public ExprContext init;
		public TerminalNode T_KW_VAL() { return getToken(HelloParser.T_KW_VAL, 0); }
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public ValDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterValDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitValDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitValDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValDefContext valDef() throws RecognitionException {
		ValDefContext _localctx = new ValDefContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_valDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T_KW_VAL);
			setState(34);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(31);
				match(NL);
				}
				}
				setState(36);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(37);
			((ValDefContext)_localctx).name = match(VAR_IDENT);
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(38);
				match(NL);
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(T__0);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(45);
				match(NL);
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			((ValDefContext)_localctx).ttype = match(TYPE_IDENT);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(52);
				match(NL);
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58);
			match(T__1);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(59);
				match(NL);
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			((ValDefContext)_localctx).init = expr(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarDefContext extends ParserRuleContext {
		public Token name;
		public Token ttype;
		public ExprContext init;
		public TerminalNode T_KW_VAR() { return getToken(HelloParser.T_KW_VAR, 0); }
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_varDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T_KW_VAR);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(68);
				match(NL);
				}
				}
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(74);
			((VarDefContext)_localctx).name = match(VAR_IDENT);
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(75);
				match(NL);
				}
				}
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(81);
			match(T__0);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(82);
				match(NL);
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(88);
			((VarDefContext)_localctx).ttype = match(TYPE_IDENT);
			setState(92);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(89);
				match(NL);
				}
				}
				setState(94);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(95);
			match(T__1);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(96);
				match(NL);
				}
				}
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(102);
			((VarDefContext)_localctx).init = expr(0);
			}
		}
		catch (RecognitionException re) {
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
		public Token name;
		public Token ttype;
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public FnArgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnArg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFnArg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFnArg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFnArg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnArgContext fnArg() throws RecognitionException {
		FnArgContext _localctx = new FnArgContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_fnArg);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			((FnArgContext)_localctx).name = match(VAR_IDENT);
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(105);
				match(NL);
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(111);
			match(T__0);
			setState(115);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(112);
				match(NL);
				}
				}
				setState(117);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(118);
			((FnArgContext)_localctx).ttype = match(TYPE_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FnArgsContext extends ParserRuleContext {
		public FnArgContext first;
		public FnArgContext fnArg;
		public List<FnArgContext> rest = new ArrayList<FnArgContext>();
		public List<FnArgContext> fnArg() {
			return getRuleContexts(FnArgContext.class);
		}
		public FnArgContext fnArg(int i) {
			return getRuleContext(FnArgContext.class,i);
		}
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public FnArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnArgs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFnArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFnArgs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFnArgs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnArgsContext fnArgs() throws RecognitionException {
		FnArgsContext _localctx = new FnArgsContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fnArgs);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			((FnArgsContext)_localctx).first = fnArg();
			setState(124);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(121);
					match(NL);
					}
					} 
				}
				setState(126);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(127);
				match(T__2);
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(128);
					match(NL);
					}
					}
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(134);
				((FnArgsContext)_localctx).fnArg = fnArg();
				((FnArgsContext)_localctx).rest.add(((FnArgsContext)_localctx).fnArg);
				}
				}
				setState(139);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(143);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(140);
				match(NL);
				}
				}
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(146);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LlvmFnDefContext extends ParserRuleContext {
		public Token name;
		public FnArgsContext args;
		public Token s12;
		public List<Token> body = new ArrayList<Token>();
		public Token s13;
		public Token _tset197;
		public Token ret;
		public TerminalNode T_KW_DEF() { return getToken(HelloParser.T_KW_DEF, 0); }
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public FnArgsContext fnArgs() {
			return getRuleContext(FnArgsContext.class,0);
		}
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public LlvmFnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llvmFnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterLlvmFnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitLlvmFnDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitLlvmFnDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LlvmFnDefContext llvmFnDef() throws RecognitionException {
		LlvmFnDefContext _localctx = new LlvmFnDefContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_llvmFnDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(T_KW_DEF);
			setState(149);
			((LlvmFnDefContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << VAR_IDENT))) != 0)) ) {
				((LlvmFnDefContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
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
			setState(156);
			match(T__1);
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(157);
				match(NL);
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(163);
			match(T__10);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(164);
				match(NL);
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(170);
			match(T__11);
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(171);
					match(NL);
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(177);
				((LlvmFnDefContext)_localctx).args = fnArgs();
				}
				break;
			}
			setState(181); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(180);
				((LlvmFnDefContext)_localctx)._tset197 = _input.LT(1);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==T__11 || _la==T__12) ) {
					((LlvmFnDefContext)_localctx)._tset197 = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				((LlvmFnDefContext)_localctx).body.add(((LlvmFnDefContext)_localctx)._tset197);
				}
				}
				setState(183); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T_KW_VAL) | (1L << T_KW_VAR) | (1L << T_KW_DEF) | (1L << T_KW_TYPE) | (1L << T_PERCENT) | (1L << T_BL) | (1L << T_AAA) | (1L << T_Q1) | (1L << T_BS_XXX) | (1L << T_BS_XXXX) | (1L << BIN_OP) | (1L << VAR_IDENT) | (1L << TYPE_IDENT) | (1L << WS) | (1L << NL) | (1L << UINT))) != 0) );
			setState(185);
			match(T__12);
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(186);
					match(NL);
					}
					}
					setState(191);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(192);
				match(T__0);
				setState(193);
				((LlvmFnDefContext)_localctx).ret = match(TYPE_IDENT);
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

	public static class AbraFnDefContext extends ParserRuleContext {
		public Token name;
		public FnArgsContext args;
		public FnBodyDefContext body;
		public Token ret;
		public TerminalNode T_KW_DEF() { return getToken(HelloParser.T_KW_DEF, 0); }
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public FnArgsContext fnArgs() {
			return getRuleContext(FnArgsContext.class,0);
		}
		public FnBodyDefContext fnBodyDef() {
			return getRuleContext(FnBodyDefContext.class,0);
		}
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public AbraFnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abraFnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAbraFnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAbraFnDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAbraFnDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbraFnDefContext abraFnDef() throws RecognitionException {
		AbraFnDefContext _localctx = new AbraFnDefContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_abraFnDef);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			match(T_KW_DEF);
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(197);
				match(NL);
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203);
			((AbraFnDefContext)_localctx).name = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << VAR_IDENT))) != 0)) ) {
				((AbraFnDefContext)_localctx).name = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(204);
				match(NL);
				}
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(210);
			match(T__1);
			setState(214);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(211);
				match(NL);
				}
				}
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(217);
			match(T__11);
			setState(221);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(218);
					match(NL);
					}
					} 
				}
				setState(223);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			setState(225);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				{
				setState(224);
				((AbraFnDefContext)_localctx).args = fnArgs();
				}
				break;
			}
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(227);
					match(NL);
					}
					} 
				}
				setState(232);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,29,_ctx);
			}
			setState(234);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				{
				setState(233);
				((AbraFnDefContext)_localctx).body = fnBodyDef();
				}
				break;
			}
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
			match(T__12);
			setState(251);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				{
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NL) {
					{
					{
					setState(243);
					match(NL);
					}
					}
					setState(248);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(249);
				match(T__0);
				setState(250);
				((AbraFnDefContext)_localctx).ret = match(TYPE_IDENT);
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

	public static class FnDefContext extends ParserRuleContext {
		public AbraFnDefContext abra;
		public LlvmFnDefContext llvm;
		public AbraFnDefContext abraFnDef() {
			return getRuleContext(AbraFnDefContext.class,0);
		}
		public LlvmFnDefContext llvmFnDef() {
			return getRuleContext(LlvmFnDefContext.class,0);
		}
		public FnDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFnDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFnDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFnDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnDefContext fnDef() throws RecognitionException {
		FnDefContext _localctx = new FnDefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fnDef);
		try {
			setState(255);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(253);
				((FnDefContext)_localctx).abra = abraFnDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(254);
				((FnDefContext)_localctx).llvm = llvmFnDef();
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

	public static class FnBodyDefContext extends ParserRuleContext {
		public ValDefContext valDef;
		public List<ValDefContext> vals = new ArrayList<ValDefContext>();
		public VarDefContext varDef;
		public List<VarDefContext> vars = new ArrayList<VarDefContext>();
		public ExprContext expr;
		public List<ExprContext> expessions = new ArrayList<ExprContext>();
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public List<ValDefContext> valDef() {
			return getRuleContexts(ValDefContext.class);
		}
		public ValDefContext valDef(int i) {
			return getRuleContext(ValDefContext.class,i);
		}
		public List<VarDefContext> varDef() {
			return getRuleContexts(VarDefContext.class);
		}
		public VarDefContext varDef(int i) {
			return getRuleContext(VarDefContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public FnBodyDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fnBodyDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterFnBodyDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitFnBodyDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitFnBodyDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FnBodyDefContext fnBodyDef() throws RecognitionException {
		FnBodyDefContext _localctx = new FnBodyDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fnBodyDef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(261); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					setState(261);
					switch (_input.LA(1)) {
					case T_KW_VAL:
						{
						setState(257);
						((FnBodyDefContext)_localctx).valDef = valDef();
						((FnBodyDefContext)_localctx).vals.add(((FnBodyDefContext)_localctx).valDef);
						}
						break;
					case T_KW_VAR:
						{
						setState(258);
						((FnBodyDefContext)_localctx).varDef = varDef();
						((FnBodyDefContext)_localctx).vars.add(((FnBodyDefContext)_localctx).varDef);
						}
						break;
					case T__4:
					case T__5:
					case T__6:
					case T__7:
					case T__8:
					case T__9:
					case T__13:
					case VAR_IDENT:
					case UINT:
						{
						setState(259);
						((FnBodyDefContext)_localctx).expr = expr(0);
						((FnBodyDefContext)_localctx).expessions.add(((FnBodyDefContext)_localctx).expr);
						}
						break;
					case NL:
						{
						setState(260);
						match(NL);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(263); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,36,_ctx);
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

	public static class LangContext extends ParserRuleContext {
		public TypeDefContext typeDef;
		public List<TypeDefContext> types = new ArrayList<TypeDefContext>();
		public FnDefContext fnDef;
		public List<FnDefContext> functions = new ArrayList<FnDefContext>();
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public List<TypeDefContext> typeDef() {
			return getRuleContexts(TypeDefContext.class);
		}
		public TypeDefContext typeDef(int i) {
			return getRuleContext(TypeDefContext.class,i);
		}
		public List<FnDefContext> fnDef() {
			return getRuleContexts(FnDefContext.class);
		}
		public FnDefContext fnDef(int i) {
			return getRuleContext(FnDefContext.class,i);
		}
		public LangContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lang; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterLang(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitLang(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitLang(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LangContext lang() throws RecognitionException {
		LangContext _localctx = new LangContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_lang);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(270);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T_KW_DEF) | (1L << T_KW_TYPE) | (1L << NL))) != 0)) {
				{
				setState(268);
				switch (_input.LA(1)) {
				case T_KW_TYPE:
					{
					setState(265);
					((LangContext)_localctx).typeDef = typeDef();
					((LangContext)_localctx).types.add(((LangContext)_localctx).typeDef);
					}
					break;
				case NL:
					{
					setState(266);
					match(NL);
					}
					break;
				case T_KW_DEF:
					{
					setState(267);
					((LangContext)_localctx).fnDef = fnDef();
					((LangContext)_localctx).functions.add(((LangContext)_localctx).fnDef);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(272);
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

	public static class StructParamDefContext extends ParserRuleContext {
		public Token name;
		public Token ttype;
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public StructParamDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structParamDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterStructParamDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitStructParamDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitStructParamDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructParamDefContext structParamDef() throws RecognitionException {
		StructParamDefContext _localctx = new StructParamDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_structParamDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			((StructParamDefContext)_localctx).name = match(VAR_IDENT);
			setState(274);
			match(T__0);
			setState(275);
			((StructParamDefContext)_localctx).ttype = match(TYPE_IDENT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LlvmTypeDefContext extends ParserRuleContext {
		public Token name;
		public Token s12;
		public List<Token> body = new ArrayList<Token>();
		public Token s13;
		public Token _tset422;
		public TerminalNode T_KW_TYPE() { return getToken(HelloParser.T_KW_TYPE, 0); }
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public List<TerminalNode> NL() { return getTokens(HelloParser.NL); }
		public TerminalNode NL(int i) {
			return getToken(HelloParser.NL, i);
		}
		public LlvmTypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_llvmTypeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterLlvmTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitLlvmTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitLlvmTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LlvmTypeDefContext llvmTypeDef() throws RecognitionException {
		LlvmTypeDefContext _localctx = new LlvmTypeDefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_llvmTypeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(T_KW_TYPE);
			setState(278);
			((LlvmTypeDefContext)_localctx).name = match(TYPE_IDENT);
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(279);
				match(NL);
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(285);
			match(T__1);
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
			match(T__10);
			setState(296);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NL) {
				{
				{
				setState(293);
				match(NL);
				}
				}
				setState(298);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(299);
			match(T__11);
			setState(301); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(300);
				((LlvmTypeDefContext)_localctx)._tset422 = _input.LT(1);
				_la = _input.LA(1);
				if ( _la <= 0 || (_la==T__11 || _la==T__12) ) {
					((LlvmTypeDefContext)_localctx)._tset422 = (Token)_errHandler.recoverInline(this);
				} else {
					consume();
				}
				((LlvmTypeDefContext)_localctx).body.add(((LlvmTypeDefContext)_localctx)._tset422);
				}
				}
				setState(303); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << T_KW_VAL) | (1L << T_KW_VAR) | (1L << T_KW_DEF) | (1L << T_KW_TYPE) | (1L << T_PERCENT) | (1L << T_BL) | (1L << T_AAA) | (1L << T_Q1) | (1L << T_BS_XXX) | (1L << T_BS_XXXX) | (1L << BIN_OP) | (1L << VAR_IDENT) | (1L << TYPE_IDENT) | (1L << WS) | (1L << NL) | (1L << UINT))) != 0) );
			setState(305);
			match(T__12);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AbraTypeDefContext extends ParserRuleContext {
		public Token name;
		public StructParamDefContext first;
		public StructParamDefContext structParamDef;
		public List<StructParamDefContext> rest = new ArrayList<StructParamDefContext>();
		public TerminalNode T_KW_TYPE() { return getToken(HelloParser.T_KW_TYPE, 0); }
		public TerminalNode TYPE_IDENT() { return getToken(HelloParser.TYPE_IDENT, 0); }
		public List<StructParamDefContext> structParamDef() {
			return getRuleContexts(StructParamDefContext.class);
		}
		public StructParamDefContext structParamDef(int i) {
			return getRuleContext(StructParamDefContext.class,i);
		}
		public AbraTypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_abraTypeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAbraTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAbraTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAbraTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AbraTypeDefContext abraTypeDef() throws RecognitionException {
		AbraTypeDefContext _localctx = new AbraTypeDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_abraTypeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(T_KW_TYPE);
			setState(308);
			((AbraTypeDefContext)_localctx).name = match(TYPE_IDENT);
			setState(309);
			match(T__1);
			setState(310);
			match(T__13);
			setState(311);
			((AbraTypeDefContext)_localctx).first = structParamDef();
			setState(316);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(312);
				match(T__2);
				setState(313);
				((AbraTypeDefContext)_localctx).structParamDef = structParamDef();
				((AbraTypeDefContext)_localctx).rest.add(((AbraTypeDefContext)_localctx).structParamDef);
				}
				}
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(319);
			match(T__14);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefContext extends ParserRuleContext {
		public LlvmTypeDefContext llvm;
		public AbraTypeDefContext abra;
		public LlvmTypeDefContext llvmTypeDef() {
			return getRuleContext(LlvmTypeDefContext.class,0);
		}
		public AbraTypeDefContext abraTypeDef() {
			return getRuleContext(AbraTypeDefContext.class,0);
		}
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterTypeDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitTypeDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitTypeDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_typeDef);
		try {
			setState(323);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(321);
				((TypeDefContext)_localctx).llvm = llvmTypeDef();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(322);
				((TypeDefContext)_localctx).abra = abraTypeDef();
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

	public static class AccessContext extends ParserRuleContext {
		public Token id1;
		public Token VAR_IDENT;
		public List<Token> accList = new ArrayList<Token>();
		public Token s5;
		public Token s6;
		public Token s7;
		public Token s8;
		public Token s9;
		public Token s10;
		public Token _tset526;
		public List<TerminalNode> VAR_IDENT() { return getTokens(HelloParser.VAR_IDENT); }
		public TerminalNode VAR_IDENT(int i) {
			return getToken(HelloParser.VAR_IDENT, i);
		}
		public TerminalNode UINT() { return getToken(HelloParser.UINT, 0); }
		public AccessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_access; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAccess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAccess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAccess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AccessContext access() throws RecognitionException {
		AccessContext _localctx = new AccessContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_access);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(325);
			((AccessContext)_localctx).id1 = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << VAR_IDENT) | (1L << UINT))) != 0)) ) {
				((AccessContext)_localctx).id1 = (Token)_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(330);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(326);
					match(T__15);
					setState(327);
					((AccessContext)_localctx)._tset526 = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << VAR_IDENT))) != 0)) ) {
						((AccessContext)_localctx)._tset526 = (Token)_errHandler.recoverInline(this);
					} else {
						consume();
					}
					((AccessContext)_localctx).accList.add(((AccessContext)_localctx)._tset526);
					}
					} 
				}
				setState(332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
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

	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AtomExpContext extends ExprContext {
		public TerminalNode UINT() { return getToken(HelloParser.UINT, 0); }
		public AtomExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAtomExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAtomExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAtomExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DirectCallExpContext extends ExprContext {
		public AccessContext acc;
		public ExprContext first;
		public ExprContext expr;
		public List<ExprContext> rest = new ArrayList<ExprContext>();
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public DirectCallExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterDirectCallExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitDirectCallExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitDirectCallExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Priority3ExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Priority3ExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterPriority3Expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitPriority3Expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitPriority3Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ParenExpCallContext extends ExprContext {
		public ExprContext cont;
		public Token VAR_IDENT;
		public List<Token> accList = new ArrayList<Token>();
		public Token s5;
		public Token s6;
		public Token s7;
		public Token s8;
		public Token s9;
		public Token s10;
		public Token _tset125;
		public ExprContext first;
		public ExprContext expr;
		public List<ExprContext> rest = new ArrayList<ExprContext>();
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> VAR_IDENT() { return getTokens(HelloParser.VAR_IDENT); }
		public TerminalNode VAR_IDENT(int i) {
			return getToken(HelloParser.VAR_IDENT, i);
		}
		public ParenExpCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterParenExpCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitParenExpCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitParenExpCall(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Priority2ExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public Priority2ExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterPriority2Expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitPriority2Expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitPriority2Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AccessExpContext extends ExprContext {
		public AccessContext access() {
			return getRuleContext(AccessContext.class,0);
		}
		public AccessExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterAccessExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitAccessExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitAccessExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SimpleParenContext extends ExprContext {
		public ExprContext cont;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public SimpleParenContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterSimpleParen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitSimpleParen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitSimpleParen(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class Priority1ExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public Priority1ExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterPriority1Expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitPriority1Expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitPriority1Expr(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarIdentExpContext extends ExprContext {
		public TerminalNode VAR_IDENT() { return getToken(HelloParser.VAR_IDENT, 0); }
		public VarIdentExpContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).enterVarIdentExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HelloListener ) ((HelloListener)listener).exitVarIdentExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof HelloVisitor ) return ((HelloVisitor<? extends T>)visitor).visitVarIdentExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,52,_ctx) ) {
			case 1:
				{
				_localctx = new AtomExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(334);
				match(UINT);
				}
				break;
			case 2:
				{
				_localctx = new DirectCallExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(335);
				((DirectCallExpContext)_localctx).acc = access();
				setState(336);
				match(T__13);
				setState(345);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__13) | (1L << VAR_IDENT) | (1L << UINT))) != 0)) {
					{
					setState(337);
					((DirectCallExpContext)_localctx).first = expr(0);
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__2) {
						{
						{
						setState(338);
						match(T__2);
						setState(339);
						((DirectCallExpContext)_localctx).expr = expr(0);
						((DirectCallExpContext)_localctx).rest.add(((DirectCallExpContext)_localctx).expr);
						}
						}
						setState(344);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(347);
				match(T__14);
				}
				break;
			case 3:
				{
				_localctx = new VarIdentExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(349);
				match(VAR_IDENT);
				}
				break;
			case 4:
				{
				_localctx = new AccessExpContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(350);
				access();
				}
				break;
			case 5:
				{
				_localctx = new ParenExpCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(351);
				match(T__13);
				setState(352);
				((ParenExpCallContext)_localctx).cont = expr(0);
				setState(353);
				match(T__14);
				setState(356); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(354);
						match(T__15);
						setState(355);
						((ParenExpCallContext)_localctx)._tset125 = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << VAR_IDENT))) != 0)) ) {
							((ParenExpCallContext)_localctx)._tset125 = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						((ParenExpCallContext)_localctx).accList.add(((ParenExpCallContext)_localctx)._tset125);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(358); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,48,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(372);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
				case 1:
					{
					setState(360);
					match(T__13);
					setState(369);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__13) | (1L << VAR_IDENT) | (1L << UINT))) != 0)) {
						{
						setState(361);
						((ParenExpCallContext)_localctx).first = expr(0);
						setState(366);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==T__2) {
							{
							{
							setState(362);
							match(T__2);
							setState(363);
							((ParenExpCallContext)_localctx).expr = expr(0);
							((ParenExpCallContext)_localctx).rest.add(((ParenExpCallContext)_localctx).expr);
							}
							}
							setState(368);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(371);
					match(T__14);
					}
					break;
				}
				}
				break;
			case 6:
				{
				_localctx = new SimpleParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(374);
				match(T__13);
				setState(375);
				((SimpleParenContext)_localctx).cont = expr(0);
				setState(376);
				match(T__14);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(391);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(389);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,53,_ctx) ) {
					case 1:
						{
						_localctx = new Priority1ExprContext(new ExprContext(_parentctx, _parentState));
						((Priority1ExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(380);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(381);
						((Priority1ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__6 || _la==T__7) ) {
							((Priority1ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(382);
						((Priority1ExprContext)_localctx).right = expr(4);
						}
						break;
					case 2:
						{
						_localctx = new Priority2ExprContext(new ExprContext(_parentctx, _parentState));
						((Priority2ExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(383);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(384);
						((Priority2ExprContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__4) | (1L << T__5) | (1L << T__8) | (1L << VAR_IDENT))) != 0)) ) {
							((Priority2ExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						} else {
							consume();
						}
						setState(385);
						((Priority2ExprContext)_localctx).right = expr(3);
						}
						break;
					case 3:
						{
						_localctx = new Priority3ExprContext(new ExprContext(_parentctx, _parentState));
						((Priority3ExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(386);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(387);
						((Priority3ExprContext)_localctx).op = match(T__9);
						setState(388);
						((Priority3ExprContext)_localctx).right = expr(2);
						}
						break;
					}
					} 
				}
				setState(393);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 14:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		case 1:
			return precpred(_ctx, 2);
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\"\u018d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\7\2#\n\2\f\2"+
		"\16\2&\13\2\3\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\7\2\61\n\2\f\2\16"+
		"\2\64\13\2\3\2\3\2\7\28\n\2\f\2\16\2;\13\2\3\2\3\2\7\2?\n\2\f\2\16\2B"+
		"\13\2\3\2\3\2\3\3\3\3\7\3H\n\3\f\3\16\3K\13\3\3\3\3\3\7\3O\n\3\f\3\16"+
		"\3R\13\3\3\3\3\3\7\3V\n\3\f\3\16\3Y\13\3\3\3\3\3\7\3]\n\3\f\3\16\3`\13"+
		"\3\3\3\3\3\7\3d\n\3\f\3\16\3g\13\3\3\3\3\3\3\4\3\4\7\4m\n\4\f\4\16\4p"+
		"\13\4\3\4\3\4\7\4t\n\4\f\4\16\4w\13\4\3\4\3\4\3\5\3\5\7\5}\n\5\f\5\16"+
		"\5\u0080\13\5\3\5\3\5\7\5\u0084\n\5\f\5\16\5\u0087\13\5\3\5\7\5\u008a"+
		"\n\5\f\5\16\5\u008d\13\5\3\5\7\5\u0090\n\5\f\5\16\5\u0093\13\5\3\5\3\5"+
		"\3\6\3\6\3\6\7\6\u009a\n\6\f\6\16\6\u009d\13\6\3\6\3\6\7\6\u00a1\n\6\f"+
		"\6\16\6\u00a4\13\6\3\6\3\6\7\6\u00a8\n\6\f\6\16\6\u00ab\13\6\3\6\3\6\7"+
		"\6\u00af\n\6\f\6\16\6\u00b2\13\6\3\6\5\6\u00b5\n\6\3\6\6\6\u00b8\n\6\r"+
		"\6\16\6\u00b9\3\6\3\6\7\6\u00be\n\6\f\6\16\6\u00c1\13\6\3\6\3\6\5\6\u00c5"+
		"\n\6\3\7\3\7\7\7\u00c9\n\7\f\7\16\7\u00cc\13\7\3\7\3\7\7\7\u00d0\n\7\f"+
		"\7\16\7\u00d3\13\7\3\7\3\7\7\7\u00d7\n\7\f\7\16\7\u00da\13\7\3\7\3\7\7"+
		"\7\u00de\n\7\f\7\16\7\u00e1\13\7\3\7\5\7\u00e4\n\7\3\7\7\7\u00e7\n\7\f"+
		"\7\16\7\u00ea\13\7\3\7\5\7\u00ed\n\7\3\7\7\7\u00f0\n\7\f\7\16\7\u00f3"+
		"\13\7\3\7\3\7\7\7\u00f7\n\7\f\7\16\7\u00fa\13\7\3\7\3\7\5\7\u00fe\n\7"+
		"\3\b\3\b\5\b\u0102\n\b\3\t\3\t\3\t\3\t\6\t\u0108\n\t\r\t\16\t\u0109\3"+
		"\n\3\n\3\n\7\n\u010f\n\n\f\n\16\n\u0112\13\n\3\13\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\7\f\u011b\n\f\f\f\16\f\u011e\13\f\3\f\3\f\7\f\u0122\n\f\f\f\16"+
		"\f\u0125\13\f\3\f\3\f\7\f\u0129\n\f\f\f\16\f\u012c\13\f\3\f\3\f\6\f\u0130"+
		"\n\f\r\f\16\f\u0131\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u013d\n\r"+
		"\f\r\16\r\u0140\13\r\3\r\3\r\3\16\3\16\5\16\u0146\n\16\3\17\3\17\3\17"+
		"\7\17\u014b\n\17\f\17\16\17\u014e\13\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\7\20\u0157\n\20\f\20\16\20\u015a\13\20\5\20\u015c\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\6\20\u0167\n\20\r\20\16\20\u0168\3"+
		"\20\3\20\3\20\3\20\7\20\u016f\n\20\f\20\16\20\u0172\13\20\5\20\u0174\n"+
		"\20\3\20\5\20\u0177\n\20\3\20\3\20\3\20\3\20\5\20\u017d\n\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0188\n\20\f\20\16\20\u018b\13"+
		"\20\3\20\2\3\36\21\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\7\4\2\7\f"+
		"\36\36\3\2\16\17\5\2\7\f\36\36\"\"\3\2\t\n\5\2\7\b\13\13\36\36\u01bc\2"+
		" \3\2\2\2\4E\3\2\2\2\6j\3\2\2\2\bz\3\2\2\2\n\u0096\3\2\2\2\f\u00c6\3\2"+
		"\2\2\16\u0101\3\2\2\2\20\u0107\3\2\2\2\22\u0110\3\2\2\2\24\u0113\3\2\2"+
		"\2\26\u0117\3\2\2\2\30\u0135\3\2\2\2\32\u0145\3\2\2\2\34\u0147\3\2\2\2"+
		"\36\u017c\3\2\2\2 $\7\23\2\2!#\7!\2\2\"!\3\2\2\2#&\3\2\2\2$\"\3\2\2\2"+
		"$%\3\2\2\2%\'\3\2\2\2&$\3\2\2\2\'+\7\36\2\2(*\7!\2\2)(\3\2\2\2*-\3\2\2"+
		"\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2-+\3\2\2\2.\62\7\3\2\2/\61\7!\2\2\60/"+
		"\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62"+
		"\3\2\2\2\659\7\37\2\2\668\7!\2\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29"+
		":\3\2\2\2:<\3\2\2\2;9\3\2\2\2<@\7\4\2\2=?\7!\2\2>=\3\2\2\2?B\3\2\2\2@"+
		">\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\5\36\20\2D\3\3\2\2\2EI\7\24"+
		"\2\2FH\7!\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2"+
		"\2\2LP\7\36\2\2MO\7!\2\2NM\3\2\2\2OR\3\2\2\2PN\3\2\2\2PQ\3\2\2\2QS\3\2"+
		"\2\2RP\3\2\2\2SW\7\3\2\2TV\7!\2\2UT\3\2\2\2VY\3\2\2\2WU\3\2\2\2WX\3\2"+
		"\2\2XZ\3\2\2\2YW\3\2\2\2Z^\7\37\2\2[]\7!\2\2\\[\3\2\2\2]`\3\2\2\2^\\\3"+
		"\2\2\2^_\3\2\2\2_a\3\2\2\2`^\3\2\2\2ae\7\4\2\2bd\7!\2\2cb\3\2\2\2dg\3"+
		"\2\2\2ec\3\2\2\2ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\5\36\20\2i\5\3\2\2\2"+
		"jn\7\36\2\2km\7!\2\2lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2oq\3\2\2\2"+
		"pn\3\2\2\2qu\7\3\2\2rt\7!\2\2sr\3\2\2\2tw\3\2\2\2us\3\2\2\2uv\3\2\2\2"+
		"vx\3\2\2\2wu\3\2\2\2xy\7\37\2\2y\7\3\2\2\2z~\5\6\4\2{}\7!\2\2|{\3\2\2"+
		"\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u008b\3\2\2\2\u0080~\3\2"+
		"\2\2\u0081\u0085\7\5\2\2\u0082\u0084\7!\2\2\u0083\u0082\3\2\2\2\u0084"+
		"\u0087\3\2\2\2\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2"+
		"\2\2\u0087\u0085\3\2\2\2\u0088\u008a\5\6\4\2\u0089\u0081\3\2\2\2\u008a"+
		"\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0091\3\2"+
		"\2\2\u008d\u008b\3\2\2\2\u008e\u0090\7!\2\2\u008f\u008e\3\2\2\2\u0090"+
		"\u0093\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0094\3\2"+
		"\2\2\u0093\u0091\3\2\2\2\u0094\u0095\7\6\2\2\u0095\t\3\2\2\2\u0096\u0097"+
		"\7\25\2\2\u0097\u009b\t\2\2\2\u0098\u009a\7!\2\2\u0099\u0098\3\2\2\2\u009a"+
		"\u009d\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2"+
		"\2\2\u009d\u009b\3\2\2\2\u009e\u00a2\7\4\2\2\u009f\u00a1\7!\2\2\u00a0"+
		"\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2"+
		"\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a9\7\r\2\2\u00a6"+
		"\u00a8\7!\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2\u00a9\u00a7\3\2"+
		"\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ac"+
		"\u00b0\7\16\2\2\u00ad\u00af\7!\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2"+
		"\2\2\u00b0\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b4\3\2\2\2\u00b2"+
		"\u00b0\3\2\2\2\u00b3\u00b5\5\b\5\2\u00b4\u00b3\3\2\2\2\u00b4\u00b5\3\2"+
		"\2\2\u00b5\u00b7\3\2\2\2\u00b6\u00b8\n\3\2\2\u00b7\u00b6\3\2\2\2\u00b8"+
		"\u00b9\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\u00c4\7\17\2\2\u00bc\u00be\7!\2\2\u00bd\u00bc\3\2\2\2\u00be"+
		"\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0\u00c2\3\2"+
		"\2\2\u00c1\u00bf\3\2\2\2\u00c2\u00c3\7\3\2\2\u00c3\u00c5\7\37\2\2\u00c4"+
		"\u00bf\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\13\3\2\2\2\u00c6\u00ca\7\25\2"+
		"\2\u00c7\u00c9\7!\2\2\u00c8\u00c7\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd"+
		"\u00d1\t\2\2\2\u00ce\u00d0\7!\2\2\u00cf\u00ce\3\2\2\2\u00d0\u00d3\3\2"+
		"\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\u00d4\3\2\2\2\u00d3"+
		"\u00d1\3\2\2\2\u00d4\u00d8\7\4\2\2\u00d5\u00d7\7!\2\2\u00d6\u00d5\3\2"+
		"\2\2\u00d7\u00da\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d8\u00d9\3\2\2\2\u00d9"+
		"\u00db\3\2\2\2\u00da\u00d8\3\2\2\2\u00db\u00df\7\16\2\2\u00dc\u00de\7"+
		"!\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3\2\2\2\u00df"+
		"\u00e0\3\2\2\2\u00e0\u00e3\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2\u00e4\5\b"+
		"\5\2\u00e3\u00e2\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e8\3\2\2\2\u00e5"+
		"\u00e7\7!\2\2\u00e6\u00e5\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8\u00e6\3\2"+
		"\2\2\u00e8\u00e9\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00e8\3\2\2\2\u00eb"+
		"\u00ed\5\20\t\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00f1\3"+
		"\2\2\2\u00ee\u00f0\7!\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f3\3\2\2\2\u00f1"+
		"\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f4\3\2\2\2\u00f3\u00f1\3\2"+
		"\2\2\u00f4\u00fd\7\17\2\2\u00f5\u00f7\7!\2\2\u00f6\u00f5\3\2\2\2\u00f7"+
		"\u00fa\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb\3\2"+
		"\2\2\u00fa\u00f8\3\2\2\2\u00fb\u00fc\7\3\2\2\u00fc\u00fe\7\37\2\2\u00fd"+
		"\u00f8\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\r\3\2\2\2\u00ff\u0102\5\f\7\2"+
		"\u0100\u0102\5\n\6\2\u0101\u00ff\3\2\2\2\u0101\u0100\3\2\2\2\u0102\17"+
		"\3\2\2\2\u0103\u0108\5\2\2\2\u0104\u0108\5\4\3\2\u0105\u0108\5\36\20\2"+
		"\u0106\u0108\7!\2\2\u0107\u0103\3\2\2\2\u0107\u0104\3\2\2\2\u0107\u0105"+
		"\3\2\2\2\u0107\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u0107\3\2\2\2\u0109"+
		"\u010a\3\2\2\2\u010a\21\3\2\2\2\u010b\u010f\5\32\16\2\u010c\u010f\7!\2"+
		"\2\u010d\u010f\5\16\b\2\u010e\u010b\3\2\2\2\u010e\u010c\3\2\2\2\u010e"+
		"\u010d\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2"+
		"\2\2\u0111\23\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0114\7\36\2\2\u0114\u0115"+
		"\7\3\2\2\u0115\u0116\7\37\2\2\u0116\25\3\2\2\2\u0117\u0118\7\26\2\2\u0118"+
		"\u011c\7\37\2\2\u0119\u011b\7!\2\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2"+
		"\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e"+
		"\u011c\3\2\2\2\u011f\u0123\7\4\2\2\u0120\u0122\7!\2\2\u0121\u0120\3\2"+
		"\2\2\u0122\u0125\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124"+
		"\u0126\3\2\2\2\u0125\u0123\3\2\2\2\u0126\u012a\7\r\2\2\u0127\u0129\7!"+
		"\2\2\u0128\u0127\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\u012d\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012f\7\16"+
		"\2\2\u012e\u0130\n\3\2\2\u012f\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\7\17"+
		"\2\2\u0134\27\3\2\2\2\u0135\u0136\7\26\2\2\u0136\u0137\7\37\2\2\u0137"+
		"\u0138\7\4\2\2\u0138\u0139\7\20\2\2\u0139\u013e\5\24\13\2\u013a\u013b"+
		"\7\5\2\2\u013b\u013d\5\24\13\2\u013c\u013a\3\2\2\2\u013d\u0140\3\2\2\2"+
		"\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e"+
		"\3\2\2\2\u0141\u0142\7\21\2\2\u0142\31\3\2\2\2\u0143\u0146\5\26\f\2\u0144"+
		"\u0146\5\30\r\2\u0145\u0143\3\2\2\2\u0145\u0144\3\2\2\2\u0146\33\3\2\2"+
		"\2\u0147\u014c\t\4\2\2\u0148\u0149\7\22\2\2\u0149\u014b\t\2\2\2\u014a"+
		"\u0148\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2"+
		"\2\2\u014d\35\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\b\20\1\2\u0150\u017d"+
		"\7\"\2\2\u0151\u0152\5\34\17\2\u0152\u015b\7\20\2\2\u0153\u0158\5\36\20"+
		"\2\u0154\u0155\7\5\2\2\u0155\u0157\5\36\20\2\u0156\u0154\3\2\2\2\u0157"+
		"\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015c\3\2"+
		"\2\2\u015a\u0158\3\2\2\2\u015b\u0153\3\2\2\2\u015b\u015c\3\2\2\2\u015c"+
		"\u015d\3\2\2\2\u015d\u015e\7\21\2\2\u015e\u017d\3\2\2\2\u015f\u017d\7"+
		"\36\2\2\u0160\u017d\5\34\17\2\u0161\u0162\7\20\2\2\u0162\u0163\5\36\20"+
		"\2\u0163\u0166\7\21\2\2\u0164\u0165\7\22\2\2\u0165\u0167\t\2\2\2\u0166"+
		"\u0164\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2"+
		"\2\2\u0169\u0176\3\2\2\2\u016a\u0173\7\20\2\2\u016b\u0170\5\36\20\2\u016c"+
		"\u016d\7\5\2\2\u016d\u016f\5\36\20\2\u016e\u016c\3\2\2\2\u016f\u0172\3"+
		"\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0174\3\2\2\2\u0172"+
		"\u0170\3\2\2\2\u0173\u016b\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0175\3\2"+
		"\2\2\u0175\u0177\7\21\2\2\u0176\u016a\3\2\2\2\u0176\u0177\3\2\2\2\u0177"+
		"\u017d\3\2\2\2\u0178\u0179\7\20\2\2\u0179\u017a\5\36\20\2\u017a\u017b"+
		"\7\21\2\2\u017b\u017d\3\2\2\2\u017c\u014f\3\2\2\2\u017c\u0151\3\2\2\2"+
		"\u017c\u015f\3\2\2\2\u017c\u0160\3\2\2\2\u017c\u0161\3\2\2\2\u017c\u0178"+
		"\3\2\2\2\u017d\u0189\3\2\2\2\u017e\u017f\f\5\2\2\u017f\u0180\t\5\2\2\u0180"+
		"\u0188\5\36\20\6\u0181\u0182\f\4\2\2\u0182\u0183\t\6\2\2\u0183\u0188\5"+
		"\36\20\5\u0184\u0185\f\3\2\2\u0185\u0186\7\f\2\2\u0186\u0188\5\36\20\4"+
		"\u0187\u017e\3\2\2\2\u0187\u0181\3\2\2\2\u0187\u0184\3\2\2\2\u0188\u018b"+
		"\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\37\3\2\2\2\u018b"+
		"\u0189\3\2\2\29$+\629@IPW^enu~\u0085\u008b\u0091\u009b\u00a2\u00a9\u00b0"+
		"\u00b4\u00b9\u00bf\u00c4\u00ca\u00d1\u00d8\u00df\u00e3\u00e8\u00ec\u00f1"+
		"\u00f8\u00fd\u0101\u0107\u0109\u010e\u0110\u011c\u0123\u012a\u0131\u013e"+
		"\u0145\u014c\u0158\u015b\u0168\u0170\u0173\u0176\u017c\u0187\u0189";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}