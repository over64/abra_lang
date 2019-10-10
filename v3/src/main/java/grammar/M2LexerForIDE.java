// Generated from /home/over/build/abra_lang/v3/grammar/M2LexerForIDE.g4 by ANTLR 4.7.2
package grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class M2LexerForIDE extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINUS=1, PLUS=2, MUL=3, DIV=4, EXCL=5, DOT=6, RB=7, LB=8, COMMA=9, MORE_=10, 
		MORE_EQ=11, LESS=12, LESS_EQ=13, EQ=14, EQEQ=15, NOTEQ=16, SEMI=17, IF=18, 
		DO=19, ELSE=20, CBO=21, DOLLAR_CBO=22, CBC=23, LOGIC_OR=24, LOGIC_AND=25, 
		WHILE=26, VAL=27, VAR=28, CON=29, ARROW_RIGHT=30, TYPE=31, BACK_SLASH=32, 
		SELF=33, MATCH_SELF=34, DEF=35, LAMBDA=36, IMPORT=37, WITH=38, MATCH=39, 
		RETURN=40, BREAK=41, CONTINUE=42, IS=43, UNLESS=44, REF=45, DASH=46, VERT_LINE=47, 
		BRACKET_LEFT=48, BRACKET_RIGTH=49, LlBegin=50, WS=51, NL=52, COMMENT=53, 
		IntLiteral=54, HexLiteral=55, FloatLiteral=56, BooleanLiteral=57, NoneLiteral=58, 
		StringLiteral=59, VarId=60, TypeId=61, MatchId=62, LLVM_NL=63, LLVM_WS=64, 
		IrLine=65, LL_End=66, LL_Dot=67;
	public static final int
		llvm=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "llvm"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", "MORE_", 
			"MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", "IF", "DO", 
			"ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", "WHILE", 
			"VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", "MATCH_SELF", 
			"DEF", "LAMBDA", "IMPORT", "WITH", "MATCH", "RETURN", "BREAK", "CONTINUE", 
			"IS", "UNLESS", "REF", "DASH", "VERT_LINE", "BRACKET_LEFT", "BRACKET_RIGTH", 
			"LlBegin", "WS", "NL", "COMMENT", "IntLiteral", "HexLiteral", "FloatLiteral", 
			"BooleanLiteral", "NoneLiteral", "StringLiteral", "VarId", "TypeId", 
			"MatchId", "LLVM_NL", "LLVM_WS", "IrLine", "LL_End", "LL_Dot", "StringElement", 
			"Char", "CharEscapeSeq", "ExponentPart", "Digit", "NonZeroDigit", "HexDigit"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'-'", "'+'", "'*'", "'/'", "'!'", null, "')'", "'('", "','", "'>'", 
			"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'do'", 
			"'else'", "'{'", "'$('", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", 
			"':'", "'->'", "'type'", "'\\'", "'self'", "'$self'", "'def'", "'lambda'", 
			"'import'", "'with'", "'match'", "'return'", "'break'", "'continue'", 
			"'is'", "'unless'", "'ref'", "'_'", "'|'", "'['", "']'", null, null, 
			null, null, null, null, null, null, "'none'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
			"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
			"IF", "DO", "ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", 
			"WHILE", "VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", 
			"MATCH_SELF", "DEF", "LAMBDA", "IMPORT", "WITH", "MATCH", "RETURN", "BREAK", 
			"CONTINUE", "IS", "UNLESS", "REF", "DASH", "VERT_LINE", "BRACKET_LEFT", 
			"BRACKET_RIGTH", "LlBegin", "WS", "NL", "COMMENT", "IntLiteral", "HexLiteral", 
			"FloatLiteral", "BooleanLiteral", "NoneLiteral", "StringLiteral", "VarId", 
			"TypeId", "MatchId", "LLVM_NL", "LLVM_WS", "IrLine", "LL_End", "LL_Dot"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	    boolean start_llvm_ws = false;


	public M2LexerForIDE(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "M2LexerForIDE.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 49:
			LlBegin_action((RuleContext)_localctx, actionIndex);
			break;
		case 62:
			LLVM_NL_action((RuleContext)_localctx, actionIndex);
			break;
		case 63:
			LLVM_WS_action((RuleContext)_localctx, actionIndex);
			break;
		case 64:
			IrLine_action((RuleContext)_localctx, actionIndex);
			break;
		case 65:
			LL_End_action((RuleContext)_localctx, actionIndex);
			break;
		case 66:
			LL_Dot_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void LlBegin_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 start_llvm_ws = true; 
			break;
		}
	}
	private void LLVM_NL_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 start_llvm_ws = true; 
			break;
		}
	}
	private void LLVM_WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 start_llvm_ws = true; 
			break;
		}
	}
	private void IrLine_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			 start_llvm_ws = false; 
			break;
		}
	}
	private void LL_End_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			 popMode(); 
			break;
		}
	}
	private void LL_Dot_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			 
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 65:
			return LL_End_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean LL_End_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return  start_llvm_ws ==  true ;
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2E\u01fa\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4"+
		"I\tI\4J\tJ\4K\tK\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3"+
		"\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\31\3"+
		"\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!"+
		"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3"+
		"%\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3)\3)\3)"+
		"\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+\3+\3,\3,\3,\3-"+
		"\3-\3-\3-\3-\3-\3-\3.\3.\3.\3.\3/\3/\3\60\3\60\3\61\3\61\3\62\3\62\3\63"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\6\64\u0151\n\64"+
		"\r\64\16\64\u0152\3\65\5\65\u0156\n\65\3\65\3\65\3\66\3\66\3\66\3\66\7"+
		"\66\u015e\n\66\f\66\16\66\u0161\13\66\3\67\5\67\u0164\n\67\3\67\3\67\3"+
		"\67\7\67\u0169\n\67\f\67\16\67\u016c\13\67\5\67\u016e\n\67\38\38\38\6"+
		"8\u0173\n8\r8\168\u0174\39\59\u0178\n9\39\69\u017b\n9\r9\169\u017c\39"+
		"\39\69\u0181\n9\r9\169\u0182\39\59\u0186\n9\39\69\u0189\n9\r9\169\u018a"+
		"\39\39\59\u018f\n9\3:\3:\3:\3:\3:\3:\3:\3:\3:\5:\u019a\n:\3;\3;\3;\3;"+
		"\3;\3<\3<\7<\u01a3\n<\f<\16<\u01a6\13<\3<\3<\3=\6=\u01ab\n=\r=\16=\u01ac"+
		"\3=\3=\7=\u01b1\n=\f=\16=\u01b4\13=\3>\6>\u01b7\n>\r>\16>\u01b8\3>\3>"+
		"\7>\u01bd\n>\f>\16>\u01c0\13>\3?\3?\3?\3@\5@\u01c6\n@\3@\3@\3@\3A\6A\u01cc"+
		"\nA\rA\16A\u01cd\3A\3A\3B\6B\u01d3\nB\rB\16B\u01d4\3B\3B\3C\3C\3C\3C\3"+
		"D\3D\3D\3E\3E\3E\5E\u01e3\nE\3F\3F\3G\3G\3G\3H\3H\5H\u01ec\nH\3H\6H\u01ef"+
		"\nH\rH\16H\u01f0\3I\3I\5I\u01f5\nI\3J\3J\3K\3K\2\2L\4\3\6\4\b\5\n\6\f"+
		"\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36\20 \21\"\22$\23&\24("+
		"\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36<\37> @!B\"D#F$H%J&L\'"+
		"N(P)R*T+V,X-Z.\\/^\60`\61b\62d\63f\64h\65j\66l\67n8p9r:t;v<x=z>|?~@\u0080"+
		"A\u0082B\u0084C\u0086D\u0088E\u008a\2\u008c\2\u008e\2\u0090\2\u0092\2"+
		"\u0094\2\u0096\2\4\2\3\13\4\2\13\13\"\"\4\2\f\f\17\17\4\2ZZzz\6\2\13\f"+
		"\17\17\"\"\60\60\5\2\f\f))^^\t\2))^^ddhhppttvv\4\2GGgg\4\2--//\5\2\62"+
		";CHch\5\u027b\2c\2|\2\u00b7\2\u00b7\2\u00e1\2\u00f8\2\u00fa\2\u0101\2"+
		"\u0103\2\u0103\2\u0105\2\u0105\2\u0107\2\u0107\2\u0109\2\u0109\2\u010b"+
		"\2\u010b\2\u010d\2\u010d\2\u010f\2\u010f\2\u0111\2\u0111\2\u0113\2\u0113"+
		"\2\u0115\2\u0115\2\u0117\2\u0117\2\u0119\2\u0119\2\u011b\2\u011b\2\u011d"+
		"\2\u011d\2\u011f\2\u011f\2\u0121\2\u0121\2\u0123\2\u0123\2\u0125\2\u0125"+
		"\2\u0127\2\u0127\2\u0129\2\u0129\2\u012b\2\u012b\2\u012d\2\u012d\2\u012f"+
		"\2\u012f\2\u0131\2\u0131\2\u0133\2\u0133\2\u0135\2\u0135\2\u0137\2\u0137"+
		"\2\u0139\2\u013a\2\u013c\2\u013c\2\u013e\2\u013e\2\u0140\2\u0140\2\u0142"+
		"\2\u0142\2\u0144\2\u0144\2\u0146\2\u0146\2\u0148\2\u0148\2\u014a\2\u014b"+
		"\2\u014d\2\u014d\2\u014f\2\u014f\2\u0151\2\u0151\2\u0153\2\u0153\2\u0155"+
		"\2\u0155\2\u0157\2\u0157\2\u0159\2\u0159\2\u015b\2\u015b\2\u015d\2\u015d"+
		"\2\u015f\2\u015f\2\u0161\2\u0161\2\u0163\2\u0163\2\u0165\2\u0165\2\u0167"+
		"\2\u0167\2\u0169\2\u0169\2\u016b\2\u016b\2\u016d\2\u016d\2\u016f\2\u016f"+
		"\2\u0171\2\u0171\2\u0173\2\u0173\2\u0175\2\u0175\2\u0177\2\u0177\2\u0179"+
		"\2\u0179\2\u017c\2\u017c\2\u017e\2\u017e\2\u0180\2\u0182\2\u0185\2\u0185"+
		"\2\u0187\2\u0187\2\u018a\2\u018a\2\u018e\2\u018f\2\u0194\2\u0194\2\u0197"+
		"\2\u0197\2\u019b\2\u019d\2\u01a0\2\u01a0\2\u01a3\2\u01a3\2\u01a5\2\u01a5"+
		"\2\u01a7\2\u01a7\2\u01aa\2\u01aa\2\u01ac\2\u01ad\2\u01af\2\u01af\2\u01b2"+
		"\2\u01b2\2\u01b6\2\u01b6\2\u01b8\2\u01b8\2\u01bb\2\u01bc\2\u01bf\2\u01c1"+
		"\2\u01c8\2\u01c8\2\u01cb\2\u01cb\2\u01ce\2\u01ce\2\u01d0\2\u01d0\2\u01d2"+
		"\2\u01d2\2\u01d4\2\u01d4\2\u01d6\2\u01d6\2\u01d8\2\u01d8\2\u01da\2\u01da"+
		"\2\u01dc\2\u01dc\2\u01de\2\u01df\2\u01e1\2\u01e1\2\u01e3\2\u01e3\2\u01e5"+
		"\2\u01e5\2\u01e7\2\u01e7\2\u01e9\2\u01e9\2\u01eb\2\u01eb\2\u01ed\2\u01ed"+
		"\2\u01ef\2\u01ef\2\u01f1\2\u01f2\2\u01f5\2\u01f5\2\u01f7\2\u01f7\2\u01fb"+
		"\2\u01fb\2\u01fd\2\u01fd\2\u01ff\2\u01ff\2\u0201\2\u0201\2\u0203\2\u0203"+
		"\2\u0205\2\u0205\2\u0207\2\u0207\2\u0209\2\u0209\2\u020b\2\u020b\2\u020d"+
		"\2\u020d\2\u020f\2\u020f\2\u0211\2\u0211\2\u0213\2\u0213\2\u0215\2\u0215"+
		"\2\u0217\2\u0217\2\u0219\2\u0219\2\u021b\2\u021b\2\u021d\2\u021d\2\u021f"+
		"\2\u021f\2\u0221\2\u0221\2\u0223\2\u0223\2\u0225\2\u0225\2\u0227\2\u0227"+
		"\2\u0229\2\u0229\2\u022b\2\u022b\2\u022d\2\u022d\2\u022f\2\u022f\2\u0231"+
		"\2\u0231\2\u0233\2\u0233\2\u0235\2\u023b\2\u023e\2\u023e\2\u0241\2\u0242"+
		"\2\u0244\2\u0244\2\u0249\2\u0249\2\u024b\2\u024b\2\u024d\2\u024d\2\u024f"+
		"\2\u024f\2\u0251\2\u0295\2\u0297\2\u02b1\2\u0373\2\u0373\2\u0375\2\u0375"+
		"\2\u0379\2\u0379\2\u037d\2\u037f\2\u0392\2\u0392\2\u03ae\2\u03d0\2\u03d2"+
		"\2\u03d3\2\u03d7\2\u03d9\2\u03db\2\u03db\2\u03dd\2\u03dd\2\u03df\2\u03df"+
		"\2\u03e1\2\u03e1\2\u03e3\2\u03e3\2\u03e5\2\u03e5\2\u03e7\2\u03e7\2\u03e9"+
		"\2\u03e9\2\u03eb\2\u03eb\2\u03ed\2\u03ed\2\u03ef\2\u03ef\2\u03f1\2\u03f5"+
		"\2\u03f7\2\u03f7\2\u03fa\2\u03fa\2\u03fd\2\u03fe\2\u0432\2\u0461\2\u0463"+
		"\2\u0463\2\u0465\2\u0465\2\u0467\2\u0467\2\u0469\2\u0469\2\u046b\2\u046b"+
		"\2\u046d\2\u046d\2\u046f\2\u046f\2\u0471\2\u0471\2\u0473\2\u0473\2\u0475"+
		"\2\u0475\2\u0477\2\u0477\2\u0479\2\u0479\2\u047b\2\u047b\2\u047d\2\u047d"+
		"\2\u047f\2\u047f\2\u0481\2\u0481\2\u0483\2\u0483\2\u048d\2\u048d\2\u048f"+
		"\2\u048f\2\u0491\2\u0491\2\u0493\2\u0493\2\u0495\2\u0495\2\u0497\2\u0497"+
		"\2\u0499\2\u0499\2\u049b\2\u049b\2\u049d\2\u049d\2\u049f\2\u049f\2\u04a1"+
		"\2\u04a1\2\u04a3\2\u04a3\2\u04a5\2\u04a5\2\u04a7\2\u04a7\2\u04a9\2\u04a9"+
		"\2\u04ab\2\u04ab\2\u04ad\2\u04ad\2\u04af\2\u04af\2\u04b1\2\u04b1\2\u04b3"+
		"\2\u04b3\2\u04b5\2\u04b5\2\u04b7\2\u04b7\2\u04b9\2\u04b9\2\u04bb\2\u04bb"+
		"\2\u04bd\2\u04bd\2\u04bf\2\u04bf\2\u04c1\2\u04c1\2\u04c4\2\u04c4\2\u04c6"+
		"\2\u04c6\2\u04c8\2\u04c8\2\u04ca\2\u04ca\2\u04cc\2\u04cc\2\u04ce\2\u04ce"+
		"\2\u04d0\2\u04d1\2\u04d3\2\u04d3\2\u04d5\2\u04d5\2\u04d7\2\u04d7\2\u04d9"+
		"\2\u04d9\2\u04db\2\u04db\2\u04dd\2\u04dd\2\u04df\2\u04df\2\u04e1\2\u04e1"+
		"\2\u04e3\2\u04e3\2\u04e5\2\u04e5\2\u04e7\2\u04e7\2\u04e9\2\u04e9\2\u04eb"+
		"\2\u04eb\2\u04ed\2\u04ed\2\u04ef\2\u04ef\2\u04f1\2\u04f1\2\u04f3\2\u04f3"+
		"\2\u04f5\2\u04f5\2\u04f7\2\u04f7\2\u04f9\2\u04f9\2\u04fb\2\u04fb\2\u04fd"+
		"\2\u04fd\2\u04ff\2\u04ff\2\u0501\2\u0501\2\u0503\2\u0503\2\u0505\2\u0505"+
		"\2\u0507\2\u0507\2\u0509\2\u0509\2\u050b\2\u050b\2\u050d\2\u050d\2\u050f"+
		"\2\u050f\2\u0511\2\u0511\2\u0513\2\u0513\2\u0515\2\u0515\2\u0517\2\u0517"+
		"\2\u0519\2\u0519\2\u051b\2\u051b\2\u051d\2\u051d\2\u051f\2\u051f\2\u0521"+
		"\2\u0521\2\u0523\2\u0523\2\u0525\2\u0525\2\u0527\2\u0527\2\u0529\2\u0529"+
		"\2\u052b\2\u052b\2\u052d\2\u052d\2\u052f\2\u052f\2\u0531\2\u0531\2\u0563"+
		"\2\u0589\2\u13fa\2\u13ff\2\u1c82\2\u1c8a\2\u1d02\2\u1d2d\2\u1d6d\2\u1d79"+
		"\2\u1d7b\2\u1d9c\2\u1e03\2\u1e03\2\u1e05\2\u1e05\2\u1e07\2\u1e07\2\u1e09"+
		"\2\u1e09\2\u1e0b\2\u1e0b\2\u1e0d\2\u1e0d\2\u1e0f\2\u1e0f\2\u1e11\2\u1e11"+
		"\2\u1e13\2\u1e13\2\u1e15\2\u1e15\2\u1e17\2\u1e17\2\u1e19\2\u1e19\2\u1e1b"+
		"\2\u1e1b\2\u1e1d\2\u1e1d\2\u1e1f\2\u1e1f\2\u1e21\2\u1e21\2\u1e23\2\u1e23"+
		"\2\u1e25\2\u1e25\2\u1e27\2\u1e27\2\u1e29\2\u1e29\2\u1e2b\2\u1e2b\2\u1e2d"+
		"\2\u1e2d\2\u1e2f\2\u1e2f\2\u1e31\2\u1e31\2\u1e33\2\u1e33\2\u1e35\2\u1e35"+
		"\2\u1e37\2\u1e37\2\u1e39\2\u1e39\2\u1e3b\2\u1e3b\2\u1e3d\2\u1e3d\2\u1e3f"+
		"\2\u1e3f\2\u1e41\2\u1e41\2\u1e43\2\u1e43\2\u1e45\2\u1e45\2\u1e47\2\u1e47"+
		"\2\u1e49\2\u1e49\2\u1e4b\2\u1e4b\2\u1e4d\2\u1e4d\2\u1e4f\2\u1e4f\2\u1e51"+
		"\2\u1e51\2\u1e53\2\u1e53\2\u1e55\2\u1e55\2\u1e57\2\u1e57\2\u1e59\2\u1e59"+
		"\2\u1e5b\2\u1e5b\2\u1e5d\2\u1e5d\2\u1e5f\2\u1e5f\2\u1e61\2\u1e61\2\u1e63"+
		"\2\u1e63\2\u1e65\2\u1e65\2\u1e67\2\u1e67\2\u1e69\2\u1e69\2\u1e6b\2\u1e6b"+
		"\2\u1e6d\2\u1e6d\2\u1e6f\2\u1e6f\2\u1e71\2\u1e71\2\u1e73\2\u1e73\2\u1e75"+
		"\2\u1e75\2\u1e77\2\u1e77\2\u1e79\2\u1e79\2\u1e7b\2\u1e7b\2\u1e7d\2\u1e7d"+
		"\2\u1e7f\2\u1e7f\2\u1e81\2\u1e81\2\u1e83\2\u1e83\2\u1e85\2\u1e85\2\u1e87"+
		"\2\u1e87\2\u1e89\2\u1e89\2\u1e8b\2\u1e8b\2\u1e8d\2\u1e8d\2\u1e8f\2\u1e8f"+
		"\2\u1e91\2\u1e91\2\u1e93\2\u1e93\2\u1e95\2\u1e95\2\u1e97\2\u1e9f\2\u1ea1"+
		"\2\u1ea1\2\u1ea3\2\u1ea3\2\u1ea5\2\u1ea5\2\u1ea7\2\u1ea7\2\u1ea9\2\u1ea9"+
		"\2\u1eab\2\u1eab\2\u1ead\2\u1ead\2\u1eaf\2\u1eaf\2\u1eb1\2\u1eb1\2\u1eb3"+
		"\2\u1eb3\2\u1eb5\2\u1eb5\2\u1eb7\2\u1eb7\2\u1eb9\2\u1eb9\2\u1ebb\2\u1ebb"+
		"\2\u1ebd\2\u1ebd\2\u1ebf\2\u1ebf\2\u1ec1\2\u1ec1\2\u1ec3\2\u1ec3\2\u1ec5"+
		"\2\u1ec5\2\u1ec7\2\u1ec7\2\u1ec9\2\u1ec9\2\u1ecb\2\u1ecb\2\u1ecd\2\u1ecd"+
		"\2\u1ecf\2\u1ecf\2\u1ed1\2\u1ed1\2\u1ed3\2\u1ed3\2\u1ed5\2\u1ed5\2\u1ed7"+
		"\2\u1ed7\2\u1ed9\2\u1ed9\2\u1edb\2\u1edb\2\u1edd\2\u1edd\2\u1edf\2\u1edf"+
		"\2\u1ee1\2\u1ee1\2\u1ee3\2\u1ee3\2\u1ee5\2\u1ee5\2\u1ee7\2\u1ee7\2\u1ee9"+
		"\2\u1ee9\2\u1eeb\2\u1eeb\2\u1eed\2\u1eed\2\u1eef\2\u1eef\2\u1ef1\2\u1ef1"+
		"\2\u1ef3\2\u1ef3\2\u1ef5\2\u1ef5\2\u1ef7\2\u1ef7\2\u1ef9\2\u1ef9\2\u1efb"+
		"\2\u1efb\2\u1efd\2\u1efd\2\u1eff\2\u1eff\2\u1f01\2\u1f09\2\u1f12\2\u1f17"+
		"\2\u1f22\2\u1f29\2\u1f32\2\u1f39\2\u1f42\2\u1f47\2\u1f52\2\u1f59\2\u1f62"+
		"\2\u1f69\2\u1f72\2\u1f7f\2\u1f82\2\u1f89\2\u1f92\2\u1f99\2\u1fa2\2\u1fa9"+
		"\2\u1fb2\2\u1fb6\2\u1fb8\2\u1fb9\2\u1fc0\2\u1fc0\2\u1fc4\2\u1fc6\2\u1fc8"+
		"\2\u1fc9\2\u1fd2\2\u1fd5\2\u1fd8\2\u1fd9\2\u1fe2\2\u1fe9\2\u1ff4\2\u1ff6"+
		"\2\u1ff8\2\u1ff9\2\u210c\2\u210c\2\u2110\2\u2111\2\u2115\2\u2115\2\u2131"+
		"\2\u2131\2\u2136\2\u2136\2\u213b\2\u213b\2\u213e\2\u213f\2\u2148\2\u214b"+
		"\2\u2150\2\u2150\2\u2186\2\u2186\2\u2c32\2\u2c60\2\u2c63\2\u2c63\2\u2c67"+
		"\2\u2c68\2\u2c6a\2\u2c6a\2\u2c6c\2\u2c6c\2\u2c6e\2\u2c6e\2\u2c73\2\u2c73"+
		"\2\u2c75\2\u2c76\2\u2c78\2\u2c7d\2\u2c83\2\u2c83\2\u2c85\2\u2c85\2\u2c87"+
		"\2\u2c87\2\u2c89\2\u2c89\2\u2c8b\2\u2c8b\2\u2c8d\2\u2c8d\2\u2c8f\2\u2c8f"+
		"\2\u2c91\2\u2c91\2\u2c93\2\u2c93\2\u2c95\2\u2c95\2\u2c97\2\u2c97\2\u2c99"+
		"\2\u2c99\2\u2c9b\2\u2c9b\2\u2c9d\2\u2c9d\2\u2c9f\2\u2c9f\2\u2ca1\2\u2ca1"+
		"\2\u2ca3\2\u2ca3\2\u2ca5\2\u2ca5\2\u2ca7\2\u2ca7\2\u2ca9\2\u2ca9\2\u2cab"+
		"\2\u2cab\2\u2cad\2\u2cad\2\u2caf\2\u2caf\2\u2cb1\2\u2cb1\2\u2cb3\2\u2cb3"+
		"\2\u2cb5\2\u2cb5\2\u2cb7\2\u2cb7\2\u2cb9\2\u2cb9\2\u2cbb\2\u2cbb\2\u2cbd"+
		"\2\u2cbd\2\u2cbf\2\u2cbf\2\u2cc1\2\u2cc1\2\u2cc3\2\u2cc3\2\u2cc5\2\u2cc5"+
		"\2\u2cc7\2\u2cc7\2\u2cc9\2\u2cc9\2\u2ccb\2\u2ccb\2\u2ccd\2\u2ccd\2\u2ccf"+
		"\2\u2ccf\2\u2cd1\2\u2cd1\2\u2cd3\2\u2cd3\2\u2cd5\2\u2cd5\2\u2cd7\2\u2cd7"+
		"\2\u2cd9\2\u2cd9\2\u2cdb\2\u2cdb\2\u2cdd\2\u2cdd\2\u2cdf\2\u2cdf\2\u2ce1"+
		"\2\u2ce1\2\u2ce3\2\u2ce3\2\u2ce5\2\u2ce6\2\u2cee\2\u2cee\2\u2cf0\2\u2cf0"+
		"\2\u2cf5\2\u2cf5\2\u2d02\2\u2d27\2\u2d29\2\u2d29\2\u2d2f\2\u2d2f\2\ua643"+
		"\2\ua643\2\ua645\2\ua645\2\ua647\2\ua647\2\ua649\2\ua649\2\ua64b\2\ua64b"+
		"\2\ua64d\2\ua64d\2\ua64f\2\ua64f\2\ua651\2\ua651\2\ua653\2\ua653\2\ua655"+
		"\2\ua655\2\ua657\2\ua657\2\ua659\2\ua659\2\ua65b\2\ua65b\2\ua65d\2\ua65d"+
		"\2\ua65f\2\ua65f\2\ua661\2\ua661\2\ua663\2\ua663\2\ua665\2\ua665\2\ua667"+
		"\2\ua667\2\ua669\2\ua669\2\ua66b\2\ua66b\2\ua66d\2\ua66d\2\ua66f\2\ua66f"+
		"\2\ua683\2\ua683\2\ua685\2\ua685\2\ua687\2\ua687\2\ua689\2\ua689\2\ua68b"+
		"\2\ua68b\2\ua68d\2\ua68d\2\ua68f\2\ua68f\2\ua691\2\ua691\2\ua693\2\ua693"+
		"\2\ua695\2\ua695\2\ua697\2\ua697\2\ua699\2\ua699\2\ua69b\2\ua69b\2\ua69d"+
		"\2\ua69d\2\ua725\2\ua725\2\ua727\2\ua727\2\ua729\2\ua729\2\ua72b\2\ua72b"+
		"\2\ua72d\2\ua72d\2\ua72f\2\ua72f\2\ua731\2\ua733\2\ua735\2\ua735\2\ua737"+
		"\2\ua737\2\ua739\2\ua739\2\ua73b\2\ua73b\2\ua73d\2\ua73d\2\ua73f\2\ua73f"+
		"\2\ua741\2\ua741\2\ua743\2\ua743\2\ua745\2\ua745\2\ua747\2\ua747\2\ua749"+
		"\2\ua749\2\ua74b\2\ua74b\2\ua74d\2\ua74d\2\ua74f\2\ua74f\2\ua751\2\ua751"+
		"\2\ua753\2\ua753\2\ua755\2\ua755\2\ua757\2\ua757\2\ua759\2\ua759\2\ua75b"+
		"\2\ua75b\2\ua75d\2\ua75d\2\ua75f\2\ua75f\2\ua761\2\ua761\2\ua763\2\ua763"+
		"\2\ua765\2\ua765\2\ua767\2\ua767\2\ua769\2\ua769\2\ua76b\2\ua76b\2\ua76d"+
		"\2\ua76d\2\ua76f\2\ua76f\2\ua771\2\ua771\2\ua773\2\ua77a\2\ua77c\2\ua77c"+
		"\2\ua77e\2\ua77e\2\ua781\2\ua781\2\ua783\2\ua783\2\ua785\2\ua785\2\ua787"+
		"\2\ua787\2\ua789\2\ua789\2\ua78e\2\ua78e\2\ua790\2\ua790\2\ua793\2\ua793"+
		"\2\ua795\2\ua797\2\ua799\2\ua799\2\ua79b\2\ua79b\2\ua79d\2\ua79d\2\ua79f"+
		"\2\ua79f\2\ua7a1\2\ua7a1\2\ua7a3\2\ua7a3\2\ua7a5\2\ua7a5\2\ua7a7\2\ua7a7"+
		"\2\ua7a9\2\ua7a9\2\ua7ab\2\ua7ab\2\ua7b7\2\ua7b7\2\ua7b9\2\ua7b9\2\ua7fc"+
		"\2\ua7fc\2\uab32\2\uab5c\2\uab62\2\uab67\2\uab72\2\uabc1\2\ufb02\2\ufb08"+
		"\2\ufb15\2\ufb19\2\uff43\2\uff5c\2\u042a\3\u0451\3\u04da\3\u04fd\3\u0cc2"+
		"\3\u0cf4\3\u18c2\3\u18e1\3\ud41c\3\ud435\3\ud450\3\ud456\3\ud458\3\ud469"+
		"\3\ud484\3\ud49d\3\ud4b8\3\ud4bb\3\ud4bd\3\ud4bd\3\ud4bf\3\ud4c5\3\ud4c7"+
		"\3\ud4d1\3\ud4ec\3\ud505\3\ud520\3\ud539\3\ud554\3\ud56d\3\ud588\3\ud5a1"+
		"\3\ud5bc\3\ud5d5\3\ud5f0\3\ud609\3\ud624\3\ud63d\3\ud658\3\ud671\3\ud68c"+
		"\3\ud6a7\3\ud6c4\3\ud6dc\3\ud6de\3\ud6e3\3\ud6fe\3\ud716\3\ud718\3\ud71d"+
		"\3\ud738\3\ud750\3\ud752\3\ud757\3\ud772\3\ud78a\3\ud78c\3\ud791\3\ud7ac"+
		"\3\ud7c4\3\ud7c6\3\ud7cb\3\ud7cd\3\ud7cd\3\ue924\3\ue945\3\u0088\2C\2"+
		"\\\2a\2a\2c\2|\2\u00b7\2\u00b7\2\u00c2\2\u00d8\2\u00da\2\u00f8\2\u00fa"+
		"\2\u01bc\2\u01be\2\u01c1\2\u01c6\2\u01c6\2\u01c8\2\u01c9\2\u01cb\2\u01cc"+
		"\2\u01ce\2\u01f3\2\u01f5\2\u0295\2\u0297\2\u02b1\2\u0372\2\u0375\2\u0378"+
		"\2\u0379\2\u037d\2\u037f\2\u0381\2\u0381\2\u0388\2\u0388\2\u038a\2\u038c"+
		"\2\u038e\2\u038e\2\u0390\2\u03a3\2\u03a5\2\u03f7\2\u03f9\2\u0483\2\u048c"+
		"\2\u0531\2\u0533\2\u0558\2\u0563\2\u0589\2\u10a2\2\u10c7\2\u10c9\2\u10c9"+
		"\2\u10cf\2\u10cf\2\u13a2\2\u13f7\2\u13fa\2\u13ff\2\u1c82\2\u1c8a\2\u1d02"+
		"\2\u1d2d\2\u1d6d\2\u1d79\2\u1d7b\2\u1d9c\2\u1e02\2\u1f17\2\u1f1a\2\u1f1f"+
		"\2\u1f22\2\u1f47\2\u1f4a\2\u1f4f\2\u1f52\2\u1f59\2\u1f5b\2\u1f5b\2\u1f5d"+
		"\2\u1f5d\2\u1f5f\2\u1f5f\2\u1f61\2\u1f7f\2\u1f82\2\u1f89\2\u1f92\2\u1f99"+
		"\2\u1fa2\2\u1fa9\2\u1fb2\2\u1fb6\2\u1fb8\2\u1fbd\2\u1fc0\2\u1fc0\2\u1fc4"+
		"\2\u1fc6\2\u1fc8\2\u1fcd\2\u1fd2\2\u1fd5\2\u1fd8\2\u1fdd\2\u1fe2\2\u1fee"+
		"\2\u1ff4\2\u1ff6\2\u1ff8\2\u1ffd\2\u2104\2\u2104\2\u2109\2\u2109\2\u210c"+
		"\2\u2115\2\u2117\2\u2117\2\u211b\2\u211f\2\u2126\2\u2126\2\u2128\2\u2128"+
		"\2\u212a\2\u212a\2\u212c\2\u212f\2\u2131\2\u2136\2\u213b\2\u213b\2\u213e"+
		"\2\u2141\2\u2147\2\u214b\2\u2150\2\u2150\2\u2185\2\u2186\2\u2c02\2\u2c30"+
		"\2\u2c32\2\u2c60\2\u2c62\2\u2c7d\2\u2c80\2\u2ce6\2\u2ced\2\u2cf0\2\u2cf4"+
		"\2\u2cf5\2\u2d02\2\u2d27\2\u2d29\2\u2d29\2\u2d2f\2\u2d2f\2\ua642\2\ua66f"+
		"\2\ua682\2\ua69d\2\ua724\2\ua771\2\ua773\2\ua789\2\ua78d\2\ua790\2\ua792"+
		"\2\ua7b0\2\ua7b2\2\ua7b9\2\ua7fc\2\ua7fc\2\uab32\2\uab5c\2\uab62\2\uab67"+
		"\2\uab72\2\uabc1\2\ufb02\2\ufb08\2\ufb15\2\ufb19\2\uff23\2\uff3c\2\uff43"+
		"\2\uff5c\2\u0402\3\u0451\3\u04b2\3\u04d5\3\u04da\3\u04fd\3\u0c82\3\u0cb4"+
		"\3\u0cc2\3\u0cf4\3\u18a2\3\u18e1\3\ud402\3\ud456\3\ud458\3\ud49e\3\ud4a0"+
		"\3\ud4a1\3\ud4a4\3\ud4a4\3\ud4a7\3\ud4a8\3\ud4ab\3\ud4ae\3\ud4b0\3\ud4bb"+
		"\3\ud4bd\3\ud4bd\3\ud4bf\3\ud4c5\3\ud4c7\3\ud507\3\ud509\3\ud50c\3\ud50f"+
		"\3\ud516\3\ud518\3\ud51e\3\ud520\3\ud53b\3\ud53d\3\ud540\3\ud542\3\ud546"+
		"\3\ud548\3\ud548\3\ud54c\3\ud552\3\ud554\3\ud6a7\3\ud6aa\3\ud6c2\3\ud6c4"+
		"\3\ud6dc\3\ud6de\3\ud6fc\3\ud6fe\3\ud716\3\ud718\3\ud736\3\ud738\3\ud750"+
		"\3\ud752\3\ud770\3\ud772\3\ud78a\3\ud78c\3\ud7aa\3\ud7ac\3\ud7c4\3\ud7c6"+
		"\3\ud7cd\3\ue902\3\ue945\3\u0275\2C\2\\\2\u00c2\2\u00d8\2\u00da\2\u00e0"+
		"\2\u0102\2\u0102\2\u0104\2\u0104\2\u0106\2\u0106\2\u0108\2\u0108\2\u010a"+
		"\2\u010a\2\u010c\2\u010c\2\u010e\2\u010e\2\u0110\2\u0110\2\u0112\2\u0112"+
		"\2\u0114\2\u0114\2\u0116\2\u0116\2\u0118\2\u0118\2\u011a\2\u011a\2\u011c"+
		"\2\u011c\2\u011e\2\u011e\2\u0120\2\u0120\2\u0122\2\u0122\2\u0124\2\u0124"+
		"\2\u0126\2\u0126\2\u0128\2\u0128\2\u012a\2\u012a\2\u012c\2\u012c\2\u012e"+
		"\2\u012e\2\u0130\2\u0130\2\u0132\2\u0132\2\u0134\2\u0134\2\u0136\2\u0136"+
		"\2\u0138\2\u0138\2\u013b\2\u013b\2\u013d\2\u013d\2\u013f\2\u013f\2\u0141"+
		"\2\u0141\2\u0143\2\u0143\2\u0145\2\u0145\2\u0147\2\u0147\2\u0149\2\u0149"+
		"\2\u014c\2\u014c\2\u014e\2\u014e\2\u0150\2\u0150\2\u0152\2\u0152\2\u0154"+
		"\2\u0154\2\u0156\2\u0156\2\u0158\2\u0158\2\u015a\2\u015a\2\u015c\2\u015c"+
		"\2\u015e\2\u015e\2\u0160\2\u0160\2\u0162\2\u0162\2\u0164\2\u0164\2\u0166"+
		"\2\u0166\2\u0168\2\u0168\2\u016a\2\u016a\2\u016c\2\u016c\2\u016e\2\u016e"+
		"\2\u0170\2\u0170\2\u0172\2\u0172\2\u0174\2\u0174\2\u0176\2\u0176\2\u0178"+
		"\2\u0178\2\u017a\2\u017b\2\u017d\2\u017d\2\u017f\2\u017f\2\u0183\2\u0184"+
		"\2\u0186\2\u0186\2\u0188\2\u0189\2\u018b\2\u018d\2\u0190\2\u0193\2\u0195"+
		"\2\u0196\2\u0198\2\u019a\2\u019e\2\u019f\2\u01a1\2\u01a2\2\u01a4\2\u01a4"+
		"\2\u01a6\2\u01a6\2\u01a8\2\u01a9\2\u01ab\2\u01ab\2\u01ae\2\u01ae\2\u01b0"+
		"\2\u01b1\2\u01b3\2\u01b5\2\u01b7\2\u01b7\2\u01b9\2\u01ba\2\u01be\2\u01be"+
		"\2\u01c6\2\u01c6\2\u01c9\2\u01c9\2\u01cc\2\u01cc\2\u01cf\2\u01cf\2\u01d1"+
		"\2\u01d1\2\u01d3\2\u01d3\2\u01d5\2\u01d5\2\u01d7\2\u01d7\2\u01d9\2\u01d9"+
		"\2\u01db\2\u01db\2\u01dd\2\u01dd\2\u01e0\2\u01e0\2\u01e2\2\u01e2\2\u01e4"+
		"\2\u01e4\2\u01e6\2\u01e6\2\u01e8\2\u01e8\2\u01ea\2\u01ea\2\u01ec\2\u01ec"+
		"\2\u01ee\2\u01ee\2\u01f0\2\u01f0\2\u01f3\2\u01f3\2\u01f6\2\u01f6\2\u01f8"+
		"\2\u01fa\2\u01fc\2\u01fc\2\u01fe\2\u01fe\2\u0200\2\u0200\2\u0202\2\u0202"+
		"\2\u0204\2\u0204\2\u0206\2\u0206\2\u0208\2\u0208\2\u020a\2\u020a\2\u020c"+
		"\2\u020c\2\u020e\2\u020e\2\u0210\2\u0210\2\u0212\2\u0212\2\u0214\2\u0214"+
		"\2\u0216\2\u0216\2\u0218\2\u0218\2\u021a\2\u021a\2\u021c\2\u021c\2\u021e"+
		"\2\u021e\2\u0220\2\u0220\2\u0222\2\u0222\2\u0224\2\u0224\2\u0226\2\u0226"+
		"\2\u0228\2\u0228\2\u022a\2\u022a\2\u022c\2\u022c\2\u022e\2\u022e\2\u0230"+
		"\2\u0230\2\u0232\2\u0232\2\u0234\2\u0234\2\u023c\2\u023d\2\u023f\2\u0240"+
		"\2\u0243\2\u0243\2\u0245\2\u0248\2\u024a\2\u024a\2\u024c\2\u024c\2\u024e"+
		"\2\u024e\2\u0250\2\u0250\2\u0372\2\u0372\2\u0374\2\u0374\2\u0378\2\u0378"+
		"\2\u0381\2\u0381\2\u0388\2\u0388\2\u038a\2\u038c\2\u038e\2\u038e\2\u0390"+
		"\2\u0391\2\u0393\2\u03a3\2\u03a5\2\u03ad\2\u03d1\2\u03d1\2\u03d4\2\u03d6"+
		"\2\u03da\2\u03da\2\u03dc\2\u03dc\2\u03de\2\u03de\2\u03e0\2\u03e0\2\u03e2"+
		"\2\u03e2\2\u03e4\2\u03e4\2\u03e6\2\u03e6\2\u03e8\2\u03e8\2\u03ea\2\u03ea"+
		"\2\u03ec\2\u03ec\2\u03ee\2\u03ee\2\u03f0\2\u03f0\2\u03f6\2\u03f6\2\u03f9"+
		"\2\u03f9\2\u03fb\2\u03fc\2\u03ff\2\u0431\2\u0462\2\u0462\2\u0464\2\u0464"+
		"\2\u0466\2\u0466\2\u0468\2\u0468\2\u046a\2\u046a\2\u046c\2\u046c\2\u046e"+
		"\2\u046e\2\u0470\2\u0470\2\u0472\2\u0472\2\u0474\2\u0474\2\u0476\2\u0476"+
		"\2\u0478\2\u0478\2\u047a\2\u047a\2\u047c\2\u047c\2\u047e\2\u047e\2\u0480"+
		"\2\u0480\2\u0482\2\u0482\2\u048c\2\u048c\2\u048e\2\u048e\2\u0490\2\u0490"+
		"\2\u0492\2\u0492\2\u0494\2\u0494\2\u0496\2\u0496\2\u0498\2\u0498\2\u049a"+
		"\2\u049a\2\u049c\2\u049c\2\u049e\2\u049e\2\u04a0\2\u04a0\2\u04a2\2\u04a2"+
		"\2\u04a4\2\u04a4\2\u04a6\2\u04a6\2\u04a8\2\u04a8\2\u04aa\2\u04aa\2\u04ac"+
		"\2\u04ac\2\u04ae\2\u04ae\2\u04b0\2\u04b0\2\u04b2\2\u04b2\2\u04b4\2\u04b4"+
		"\2\u04b6\2\u04b6\2\u04b8\2\u04b8\2\u04ba\2\u04ba\2\u04bc\2\u04bc\2\u04be"+
		"\2\u04be\2\u04c0\2\u04c0\2\u04c2\2\u04c3\2\u04c5\2\u04c5\2\u04c7\2\u04c7"+
		"\2\u04c9\2\u04c9\2\u04cb\2\u04cb\2\u04cd\2\u04cd\2\u04cf\2\u04cf\2\u04d2"+
		"\2\u04d2\2\u04d4\2\u04d4\2\u04d6\2\u04d6\2\u04d8\2\u04d8\2\u04da\2\u04da"+
		"\2\u04dc\2\u04dc\2\u04de\2\u04de\2\u04e0\2\u04e0\2\u04e2\2\u04e2\2\u04e4"+
		"\2\u04e4\2\u04e6\2\u04e6\2\u04e8\2\u04e8\2\u04ea\2\u04ea\2\u04ec\2\u04ec"+
		"\2\u04ee\2\u04ee\2\u04f0\2\u04f0\2\u04f2\2\u04f2\2\u04f4\2\u04f4\2\u04f6"+
		"\2\u04f6\2\u04f8\2\u04f8\2\u04fa\2\u04fa\2\u04fc\2\u04fc\2\u04fe\2\u04fe"+
		"\2\u0500\2\u0500\2\u0502\2\u0502\2\u0504\2\u0504\2\u0506\2\u0506\2\u0508"+
		"\2\u0508\2\u050a\2\u050a\2\u050c\2\u050c\2\u050e\2\u050e\2\u0510\2\u0510"+
		"\2\u0512\2\u0512\2\u0514\2\u0514\2\u0516\2\u0516\2\u0518\2\u0518\2\u051a"+
		"\2\u051a\2\u051c\2\u051c\2\u051e\2\u051e\2\u0520\2\u0520\2\u0522\2\u0522"+
		"\2\u0524\2\u0524\2\u0526\2\u0526\2\u0528\2\u0528\2\u052a\2\u052a\2\u052c"+
		"\2\u052c\2\u052e\2\u052e\2\u0530\2\u0530\2\u0533\2\u0558\2\u10a2\2\u10c7"+
		"\2\u10c9\2\u10c9\2\u10cf\2\u10cf\2\u13a2\2\u13f7\2\u1e02\2\u1e02\2\u1e04"+
		"\2\u1e04\2\u1e06\2\u1e06\2\u1e08\2\u1e08\2\u1e0a\2\u1e0a\2\u1e0c\2\u1e0c"+
		"\2\u1e0e\2\u1e0e\2\u1e10\2\u1e10\2\u1e12\2\u1e12\2\u1e14\2\u1e14\2\u1e16"+
		"\2\u1e16\2\u1e18\2\u1e18\2\u1e1a\2\u1e1a\2\u1e1c\2\u1e1c\2\u1e1e\2\u1e1e"+
		"\2\u1e20\2\u1e20\2\u1e22\2\u1e22\2\u1e24\2\u1e24\2\u1e26\2\u1e26\2\u1e28"+
		"\2\u1e28\2\u1e2a\2\u1e2a\2\u1e2c\2\u1e2c\2\u1e2e\2\u1e2e\2\u1e30\2\u1e30"+
		"\2\u1e32\2\u1e32\2\u1e34\2\u1e34\2\u1e36\2\u1e36\2\u1e38\2\u1e38\2\u1e3a"+
		"\2\u1e3a\2\u1e3c\2\u1e3c\2\u1e3e\2\u1e3e\2\u1e40\2\u1e40\2\u1e42\2\u1e42"+
		"\2\u1e44\2\u1e44\2\u1e46\2\u1e46\2\u1e48\2\u1e48\2\u1e4a\2\u1e4a\2\u1e4c"+
		"\2\u1e4c\2\u1e4e\2\u1e4e\2\u1e50\2\u1e50\2\u1e52\2\u1e52\2\u1e54\2\u1e54"+
		"\2\u1e56\2\u1e56\2\u1e58\2\u1e58\2\u1e5a\2\u1e5a\2\u1e5c\2\u1e5c\2\u1e5e"+
		"\2\u1e5e\2\u1e60\2\u1e60\2\u1e62\2\u1e62\2\u1e64\2\u1e64\2\u1e66\2\u1e66"+
		"\2\u1e68\2\u1e68\2\u1e6a\2\u1e6a\2\u1e6c\2\u1e6c\2\u1e6e\2\u1e6e\2\u1e70"+
		"\2\u1e70\2\u1e72\2\u1e72\2\u1e74\2\u1e74\2\u1e76\2\u1e76\2\u1e78\2\u1e78"+
		"\2\u1e7a\2\u1e7a\2\u1e7c\2\u1e7c\2\u1e7e\2\u1e7e\2\u1e80\2\u1e80\2\u1e82"+
		"\2\u1e82\2\u1e84\2\u1e84\2\u1e86\2\u1e86\2\u1e88\2\u1e88\2\u1e8a\2\u1e8a"+
		"\2\u1e8c\2\u1e8c\2\u1e8e\2\u1e8e\2\u1e90\2\u1e90\2\u1e92\2\u1e92\2\u1e94"+
		"\2\u1e94\2\u1e96\2\u1e96\2\u1ea0\2\u1ea0\2\u1ea2\2\u1ea2\2\u1ea4\2\u1ea4"+
		"\2\u1ea6\2\u1ea6\2\u1ea8\2\u1ea8\2\u1eaa\2\u1eaa\2\u1eac\2\u1eac\2\u1eae"+
		"\2\u1eae\2\u1eb0\2\u1eb0\2\u1eb2\2\u1eb2\2\u1eb4\2\u1eb4\2\u1eb6\2\u1eb6"+
		"\2\u1eb8\2\u1eb8\2\u1eba\2\u1eba\2\u1ebc\2\u1ebc\2\u1ebe\2\u1ebe\2\u1ec0"+
		"\2\u1ec0\2\u1ec2\2\u1ec2\2\u1ec4\2\u1ec4\2\u1ec6\2\u1ec6\2\u1ec8\2\u1ec8"+
		"\2\u1eca\2\u1eca\2\u1ecc\2\u1ecc\2\u1ece\2\u1ece\2\u1ed0\2\u1ed0\2\u1ed2"+
		"\2\u1ed2\2\u1ed4\2\u1ed4\2\u1ed6\2\u1ed6\2\u1ed8\2\u1ed8\2\u1eda\2\u1eda"+
		"\2\u1edc\2\u1edc\2\u1ede\2\u1ede\2\u1ee0\2\u1ee0\2\u1ee2\2\u1ee2\2\u1ee4"+
		"\2\u1ee4\2\u1ee6\2\u1ee6\2\u1ee8\2\u1ee8\2\u1eea\2\u1eea\2\u1eec\2\u1eec"+
		"\2\u1eee\2\u1eee\2\u1ef0\2\u1ef0\2\u1ef2\2\u1ef2\2\u1ef4\2\u1ef4\2\u1ef6"+
		"\2\u1ef6\2\u1ef8\2\u1ef8\2\u1efa\2\u1efa\2\u1efc\2\u1efc\2\u1efe\2\u1efe"+
		"\2\u1f00\2\u1f00\2\u1f0a\2\u1f11\2\u1f1a\2\u1f1f\2\u1f2a\2\u1f31\2\u1f3a"+
		"\2\u1f41\2\u1f4a\2\u1f4f\2\u1f5b\2\u1f5b\2\u1f5d\2\u1f5d\2\u1f5f\2\u1f5f"+
		"\2\u1f61\2\u1f61\2\u1f6a\2\u1f71\2\u1fba\2\u1fbd\2\u1fca\2\u1fcd\2\u1fda"+
		"\2\u1fdd\2\u1fea\2\u1fee\2\u1ffa\2\u1ffd\2\u2104\2\u2104\2\u2109\2\u2109"+
		"\2\u210d\2\u210f\2\u2112\2\u2114\2\u2117\2\u2117\2\u211b\2\u211f\2\u2126"+
		"\2\u2126\2\u2128\2\u2128\2\u212a\2\u212a\2\u212c\2\u212f\2\u2132\2\u2135"+
		"\2\u2140\2\u2141\2\u2147\2\u2147\2\u2185\2\u2185\2\u2c02\2\u2c30\2\u2c62"+
		"\2\u2c62\2\u2c64\2\u2c66\2\u2c69\2\u2c69\2\u2c6b\2\u2c6b\2\u2c6d\2\u2c6d"+
		"\2\u2c6f\2\u2c72\2\u2c74\2\u2c74\2\u2c77\2\u2c77\2\u2c80\2\u2c82\2\u2c84"+
		"\2\u2c84\2\u2c86\2\u2c86\2\u2c88\2\u2c88\2\u2c8a\2\u2c8a\2\u2c8c\2\u2c8c"+
		"\2\u2c8e\2\u2c8e\2\u2c90\2\u2c90\2\u2c92\2\u2c92\2\u2c94\2\u2c94\2\u2c96"+
		"\2\u2c96\2\u2c98\2\u2c98\2\u2c9a\2\u2c9a\2\u2c9c\2\u2c9c\2\u2c9e\2\u2c9e"+
		"\2\u2ca0\2\u2ca0\2\u2ca2\2\u2ca2\2\u2ca4\2\u2ca4\2\u2ca6\2\u2ca6\2\u2ca8"+
		"\2\u2ca8\2\u2caa\2\u2caa\2\u2cac\2\u2cac\2\u2cae\2\u2cae\2\u2cb0\2\u2cb0"+
		"\2\u2cb2\2\u2cb2\2\u2cb4\2\u2cb4\2\u2cb6\2\u2cb6\2\u2cb8\2\u2cb8\2\u2cba"+
		"\2\u2cba\2\u2cbc\2\u2cbc\2\u2cbe\2\u2cbe\2\u2cc0\2\u2cc0\2\u2cc2\2\u2cc2"+
		"\2\u2cc4\2\u2cc4\2\u2cc6\2\u2cc6\2\u2cc8\2\u2cc8\2\u2cca\2\u2cca\2\u2ccc"+
		"\2\u2ccc\2\u2cce\2\u2cce\2\u2cd0\2\u2cd0\2\u2cd2\2\u2cd2\2\u2cd4\2\u2cd4"+
		"\2\u2cd6\2\u2cd6\2\u2cd8\2\u2cd8\2\u2cda\2\u2cda\2\u2cdc\2\u2cdc\2\u2cde"+
		"\2\u2cde\2\u2ce0\2\u2ce0\2\u2ce2\2\u2ce2\2\u2ce4\2\u2ce4\2\u2ced\2\u2ced"+
		"\2\u2cef\2\u2cef\2\u2cf4\2\u2cf4\2\ua642\2\ua642\2\ua644\2\ua644\2\ua646"+
		"\2\ua646\2\ua648\2\ua648\2\ua64a\2\ua64a\2\ua64c\2\ua64c\2\ua64e\2\ua64e"+
		"\2\ua650\2\ua650\2\ua652\2\ua652\2\ua654\2\ua654\2\ua656\2\ua656\2\ua658"+
		"\2\ua658\2\ua65a\2\ua65a\2\ua65c\2\ua65c\2\ua65e\2\ua65e\2\ua660\2\ua660"+
		"\2\ua662\2\ua662\2\ua664\2\ua664\2\ua666\2\ua666\2\ua668\2\ua668\2\ua66a"+
		"\2\ua66a\2\ua66c\2\ua66c\2\ua66e\2\ua66e\2\ua682\2\ua682\2\ua684\2\ua684"+
		"\2\ua686\2\ua686\2\ua688\2\ua688\2\ua68a\2\ua68a\2\ua68c\2\ua68c\2\ua68e"+
		"\2\ua68e\2\ua690\2\ua690\2\ua692\2\ua692\2\ua694\2\ua694\2\ua696\2\ua696"+
		"\2\ua698\2\ua698\2\ua69a\2\ua69a\2\ua69c\2\ua69c\2\ua724\2\ua724\2\ua726"+
		"\2\ua726\2\ua728\2\ua728\2\ua72a\2\ua72a\2\ua72c\2\ua72c\2\ua72e\2\ua72e"+
		"\2\ua730\2\ua730\2\ua734\2\ua734\2\ua736\2\ua736\2\ua738\2\ua738\2\ua73a"+
		"\2\ua73a\2\ua73c\2\ua73c\2\ua73e\2\ua73e\2\ua740\2\ua740\2\ua742\2\ua742"+
		"\2\ua744\2\ua744\2\ua746\2\ua746\2\ua748\2\ua748\2\ua74a\2\ua74a\2\ua74c"+
		"\2\ua74c\2\ua74e\2\ua74e\2\ua750\2\ua750\2\ua752\2\ua752\2\ua754\2\ua754"+
		"\2\ua756\2\ua756\2\ua758\2\ua758\2\ua75a\2\ua75a\2\ua75c\2\ua75c\2\ua75e"+
		"\2\ua75e\2\ua760\2\ua760\2\ua762\2\ua762\2\ua764\2\ua764\2\ua766\2\ua766"+
		"\2\ua768\2\ua768\2\ua76a\2\ua76a\2\ua76c\2\ua76c\2\ua76e\2\ua76e\2\ua770"+
		"\2\ua770\2\ua77b\2\ua77b\2\ua77d\2\ua77d\2\ua77f\2\ua780\2\ua782\2\ua782"+
		"\2\ua784\2\ua784\2\ua786\2\ua786\2\ua788\2\ua788\2\ua78d\2\ua78d\2\ua78f"+
		"\2\ua78f\2\ua792\2\ua792\2\ua794\2\ua794\2\ua798\2\ua798\2\ua79a\2\ua79a"+
		"\2\ua79c\2\ua79c\2\ua79e\2\ua79e\2\ua7a0\2\ua7a0\2\ua7a2\2\ua7a2\2\ua7a4"+
		"\2\ua7a4\2\ua7a6\2\ua7a6\2\ua7a8\2\ua7a8\2\ua7aa\2\ua7aa\2\ua7ac\2\ua7b0"+
		"\2\ua7b2\2\ua7b6\2\ua7b8\2\ua7b8\2\uff23\2\uff3c\2\u0402\3\u0429\3\u04b2"+
		"\3\u04d5\3\u0c82\3\u0cb4\3\u18a2\3\u18c1\3\ud402\3\ud41b\3\ud436\3\ud44f"+
		"\3\ud46a\3\ud483\3\ud49e\3\ud49e\3\ud4a0\3\ud4a1\3\ud4a4\3\ud4a4\3\ud4a7"+
		"\3\ud4a8\3\ud4ab\3\ud4ae\3\ud4b0\3\ud4b7\3\ud4d2\3\ud4eb\3\ud506\3\ud507"+
		"\3\ud509\3\ud50c\3\ud50f\3\ud516\3\ud518\3\ud51e\3\ud53a\3\ud53b\3\ud53d"+
		"\3\ud540\3\ud542\3\ud546\3\ud548\3\ud548\3\ud54c\3\ud552\3\ud56e\3\ud587"+
		"\3\ud5a2\3\ud5bb\3\ud5d6\3\ud5ef\3\ud60a\3\ud623\3\ud63e\3\ud657\3\ud672"+
		"\3\ud68b\3\ud6aa\3\ud6c2\3\ud6e4\3\ud6fc\3\ud71e\3\ud736\3\ud758\3\ud770"+
		"\3\ud792\3\ud7aa\3\ud7cc\3\ud7cc\3\ue902\3\ue923\3\u020e\2\4\3\2\2\2\2"+
		"\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2"+
		"\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2"+
		"\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2"+
		"\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2"+
		"\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2"+
		"\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L"+
		"\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2"+
		"\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2\2d\3\2\2"+
		"\2\2f\3\2\2\2\2h\3\2\2\2\2j\3\2\2\2\2l\3\2\2\2\2n\3\2\2\2\2p\3\2\2\2\2"+
		"r\3\2\2\2\2t\3\2\2\2\2v\3\2\2\2\2x\3\2\2\2\2z\3\2\2\2\2|\3\2\2\2\2~\3"+
		"\2\2\2\3\u0080\3\2\2\2\3\u0082\3\2\2\2\3\u0084\3\2\2\2\3\u0086\3\2\2\2"+
		"\3\u0088\3\2\2\2\4\u0098\3\2\2\2\6\u009a\3\2\2\2\b\u009c\3\2\2\2\n\u009e"+
		"\3\2\2\2\f\u00a0\3\2\2\2\16\u00a2\3\2\2\2\20\u00a4\3\2\2\2\22\u00a6\3"+
		"\2\2\2\24\u00a8\3\2\2\2\26\u00aa\3\2\2\2\30\u00ac\3\2\2\2\32\u00af\3\2"+
		"\2\2\34\u00b1\3\2\2\2\36\u00b4\3\2\2\2 \u00b6\3\2\2\2\"\u00b9\3\2\2\2"+
		"$\u00bc\3\2\2\2&\u00be\3\2\2\2(\u00c1\3\2\2\2*\u00c4\3\2\2\2,\u00c9\3"+
		"\2\2\2.\u00cb\3\2\2\2\60\u00ce\3\2\2\2\62\u00d0\3\2\2\2\64\u00d3\3\2\2"+
		"\2\66\u00d6\3\2\2\28\u00dc\3\2\2\2:\u00e0\3\2\2\2<\u00e4\3\2\2\2>\u00e6"+
		"\3\2\2\2@\u00e9\3\2\2\2B\u00ee\3\2\2\2D\u00f0\3\2\2\2F\u00f5\3\2\2\2H"+
		"\u00fb\3\2\2\2J\u00ff\3\2\2\2L\u0106\3\2\2\2N\u010d\3\2\2\2P\u0112\3\2"+
		"\2\2R\u0118\3\2\2\2T\u011f\3\2\2\2V\u0125\3\2\2\2X\u012e\3\2\2\2Z\u0131"+
		"\3\2\2\2\\\u0138\3\2\2\2^\u013c\3\2\2\2`\u013e\3\2\2\2b\u0140\3\2\2\2"+
		"d\u0142\3\2\2\2f\u0144\3\2\2\2h\u0150\3\2\2\2j\u0155\3\2\2\2l\u0159\3"+
		"\2\2\2n\u0163\3\2\2\2p\u016f\3\2\2\2r\u0177\3\2\2\2t\u0199\3\2\2\2v\u019b"+
		"\3\2\2\2x\u01a0\3\2\2\2z\u01aa\3\2\2\2|\u01b6\3\2\2\2~\u01c1\3\2\2\2\u0080"+
		"\u01c5\3\2\2\2\u0082\u01cb\3\2\2\2\u0084\u01d2\3\2\2\2\u0086\u01d8\3\2"+
		"\2\2\u0088\u01dc\3\2\2\2\u008a\u01e2\3\2\2\2\u008c\u01e4\3\2\2\2\u008e"+
		"\u01e6\3\2\2\2\u0090\u01e9\3\2\2\2\u0092\u01f4\3\2\2\2\u0094\u01f6\3\2"+
		"\2\2\u0096\u01f8\3\2\2\2\u0098\u0099\7/\2\2\u0099\5\3\2\2\2\u009a\u009b"+
		"\7-\2\2\u009b\7\3\2\2\2\u009c\u009d\7,\2\2\u009d\t\3\2\2\2\u009e\u009f"+
		"\7\61\2\2\u009f\13\3\2\2\2\u00a0\u00a1\7#\2\2\u00a1\r\3\2\2\2\u00a2\u00a3"+
		"\7\60\2\2\u00a3\17\3\2\2\2\u00a4\u00a5\7+\2\2\u00a5\21\3\2\2\2\u00a6\u00a7"+
		"\7*\2\2\u00a7\23\3\2\2\2\u00a8\u00a9\7.\2\2\u00a9\25\3\2\2\2\u00aa\u00ab"+
		"\7@\2\2\u00ab\27\3\2\2\2\u00ac\u00ad\7@\2\2\u00ad\u00ae\7?\2\2\u00ae\31"+
		"\3\2\2\2\u00af\u00b0\7>\2\2\u00b0\33\3\2\2\2\u00b1\u00b2\7>\2\2\u00b2"+
		"\u00b3\7?\2\2\u00b3\35\3\2\2\2\u00b4\u00b5\7?\2\2\u00b5\37\3\2\2\2\u00b6"+
		"\u00b7\7?\2\2\u00b7\u00b8\7?\2\2\u00b8!\3\2\2\2\u00b9\u00ba\7#\2\2\u00ba"+
		"\u00bb\7?\2\2\u00bb#\3\2\2\2\u00bc\u00bd\7=\2\2\u00bd%\3\2\2\2\u00be\u00bf"+
		"\7k\2\2\u00bf\u00c0\7h\2\2\u00c0\'\3\2\2\2\u00c1\u00c2\7f\2\2\u00c2\u00c3"+
		"\7q\2\2\u00c3)\3\2\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7"+
		"\7u\2\2\u00c7\u00c8\7g\2\2\u00c8+\3\2\2\2\u00c9\u00ca\7}\2\2\u00ca-\3"+
		"\2\2\2\u00cb\u00cc\7&\2\2\u00cc\u00cd\7*\2\2\u00cd/\3\2\2\2\u00ce\u00cf"+
		"\7\177\2\2\u00cf\61\3\2\2\2\u00d0\u00d1\7~\2\2\u00d1\u00d2\7~\2\2\u00d2"+
		"\63\3\2\2\2\u00d3\u00d4\7(\2\2\u00d4\u00d5\7(\2\2\u00d5\65\3\2\2\2\u00d6"+
		"\u00d7\7y\2\2\u00d7\u00d8\7j\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7n\2\2"+
		"\u00da\u00db\7g\2\2\u00db\67\3\2\2\2\u00dc\u00dd\7x\2\2\u00dd\u00de\7"+
		"c\2\2\u00de\u00df\7n\2\2\u00df9\3\2\2\2\u00e0\u00e1\7x\2\2\u00e1\u00e2"+
		"\7c\2\2\u00e2\u00e3\7t\2\2\u00e3;\3\2\2\2\u00e4\u00e5\7<\2\2\u00e5=\3"+
		"\2\2\2\u00e6\u00e7\7/\2\2\u00e7\u00e8\7@\2\2\u00e8?\3\2\2\2\u00e9\u00ea"+
		"\7v\2\2\u00ea\u00eb\7{\2\2\u00eb\u00ec\7r\2\2\u00ec\u00ed\7g\2\2\u00ed"+
		"A\3\2\2\2\u00ee\u00ef\7^\2\2\u00efC\3\2\2\2\u00f0\u00f1\7u\2\2\u00f1\u00f2"+
		"\7g\2\2\u00f2\u00f3\7n\2\2\u00f3\u00f4\7h\2\2\u00f4E\3\2\2\2\u00f5\u00f6"+
		"\7&\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7n\2\2\u00f9"+
		"\u00fa\7h\2\2\u00faG\3\2\2\2\u00fb\u00fc\7f\2\2\u00fc\u00fd\7g\2\2\u00fd"+
		"\u00fe\7h\2\2\u00feI\3\2\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7c\2\2\u0101"+
		"\u0102\7o\2\2\u0102\u0103\7d\2\2\u0103\u0104\7f\2\2\u0104\u0105\7c\2\2"+
		"\u0105K\3\2\2\2\u0106\u0107\7k\2\2\u0107\u0108\7o\2\2\u0108\u0109\7r\2"+
		"\2\u0109\u010a\7q\2\2\u010a\u010b\7t\2\2\u010b\u010c\7v\2\2\u010cM\3\2"+
		"\2\2\u010d\u010e\7y\2\2\u010e\u010f\7k\2\2\u010f\u0110\7v\2\2\u0110\u0111"+
		"\7j\2\2\u0111O\3\2\2\2\u0112\u0113\7o\2\2\u0113\u0114\7c\2\2\u0114\u0115"+
		"\7v\2\2\u0115\u0116\7e\2\2\u0116\u0117\7j\2\2\u0117Q\3\2\2\2\u0118\u0119"+
		"\7t\2\2\u0119\u011a\7g\2\2\u011a\u011b\7v\2\2\u011b\u011c\7w\2\2\u011c"+
		"\u011d\7t\2\2\u011d\u011e\7p\2\2\u011eS\3\2\2\2\u011f\u0120\7d\2\2\u0120"+
		"\u0121\7t\2\2\u0121\u0122\7g\2\2\u0122\u0123\7c\2\2\u0123\u0124\7m\2\2"+
		"\u0124U\3\2\2\2\u0125\u0126\7e\2\2\u0126\u0127\7q\2\2\u0127\u0128\7p\2"+
		"\2\u0128\u0129\7v\2\2\u0129\u012a\7k\2\2\u012a\u012b\7p\2\2\u012b\u012c"+
		"\7w\2\2\u012c\u012d\7g\2\2\u012dW\3\2\2\2\u012e\u012f\7k\2\2\u012f\u0130"+
		"\7u\2\2\u0130Y\3\2\2\2\u0131\u0132\7w\2\2\u0132\u0133\7p\2\2\u0133\u0134"+
		"\7n\2\2\u0134\u0135\7g\2\2\u0135\u0136\7u\2\2\u0136\u0137\7u\2\2\u0137"+
		"[\3\2\2\2\u0138\u0139\7t\2\2\u0139\u013a\7g\2\2\u013a\u013b\7h\2\2\u013b"+
		"]\3\2\2\2\u013c\u013d\7a\2\2\u013d_\3\2\2\2\u013e\u013f\7~\2\2\u013fa"+
		"\3\2\2\2\u0140\u0141\7]\2\2\u0141c\3\2\2\2\u0142\u0143\7_\2\2\u0143e\3"+
		"\2\2\2\u0144\u0145\7p\2\2\u0145\u0146\7c\2\2\u0146\u0147\7v\2\2\u0147"+
		"\u0148\7k\2\2\u0148\u0149\7x\2\2\u0149\u014a\7g\2\2\u014a\u014b\3\2\2"+
		"\2\u014b\u014c\b\63\2\2\u014c\u014d\3\2\2\2\u014d\u014e\b\63\3\2\u014e"+
		"g\3\2\2\2\u014f\u0151\t\2\2\2\u0150\u014f\3\2\2\2\u0151\u0152\3\2\2\2"+
		"\u0152\u0150\3\2\2\2\u0152\u0153\3\2\2\2\u0153i\3\2\2\2\u0154\u0156\7"+
		"\17\2\2\u0155\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0158\7\f\2\2\u0158k\3\2\2\2\u0159\u015a\7/\2\2\u015a\u015b\7/\2\2\u015b"+
		"\u015f\3\2\2\2\u015c\u015e\n\3\2\2\u015d\u015c\3\2\2\2\u015e\u0161\3\2"+
		"\2\2\u015f\u015d\3\2\2\2\u015f\u0160\3\2\2\2\u0160m\3\2\2\2\u0161\u015f"+
		"\3\2\2\2\u0162\u0164\7/\2\2\u0163\u0162\3\2\2\2\u0163\u0164\3\2\2\2\u0164"+
		"\u016d\3\2\2\2\u0165\u016e\7\62\2\2\u0166\u016a\5\u0094J\2\u0167\u0169"+
		"\5\u0092I\2\u0168\u0167\3\2\2\2\u0169\u016c\3\2\2\2\u016a\u0168\3\2\2"+
		"\2\u016a\u016b\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016d\u0165"+
		"\3\2\2\2\u016d\u0166\3\2\2\2\u016eo\3\2\2\2\u016f\u0170\7\62\2\2\u0170"+
		"\u0172\t\4\2\2\u0171\u0173\5\u0096K\2\u0172\u0171\3\2\2\2\u0173\u0174"+
		"\3\2\2\2\u0174\u0172\3\2\2\2\u0174\u0175\3\2\2\2\u0175q\3\2\2\2\u0176"+
		"\u0178\7/\2\2\u0177\u0176\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u018e\3\2"+
		"\2\2\u0179\u017b\5\u0092I\2\u017a\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c"+
		"\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\3\2\2\2\u017e\u0180\7\60"+
		"\2\2\u017f\u0181\5\u0092I\2\u0180\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182"+
		"\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183\u0185\3\2\2\2\u0184\u0186\5\u0090"+
		"H\2\u0185\u0184\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u018f\3\2\2\2\u0187"+
		"\u0189\5\u0092I\2\u0188\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u0188"+
		"\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018d\5\u0090H"+
		"\2\u018d\u018f\3\2\2\2\u018e\u017a\3\2\2\2\u018e\u0188\3\2\2\2\u018fs"+
		"\3\2\2\2\u0190\u0191\7v\2\2\u0191\u0192\7t\2\2\u0192\u0193\7w\2\2\u0193"+
		"\u019a\7g\2\2\u0194\u0195\7h\2\2\u0195\u0196\7c\2\2\u0196\u0197\7n\2\2"+
		"\u0197\u0198\7u\2\2\u0198\u019a\7g\2\2\u0199\u0190\3\2\2\2\u0199\u0194"+
		"\3\2\2\2\u019au\3\2\2\2\u019b\u019c\7p\2\2\u019c\u019d\7q\2\2\u019d\u019e"+
		"\7p\2\2\u019e\u019f\7g\2\2\u019fw\3\2\2\2\u01a0\u01a4\7)\2\2\u01a1\u01a3"+
		"\5\u008aE\2\u01a2\u01a1\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3\2\2"+
		"\2\u01a4\u01a5\3\2\2\2\u01a5\u01a7\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7\u01a8"+
		"\7)\2\2\u01a8y\3\2\2\2\u01a9\u01ab\t\13\2\2\u01aa\u01a9\3\2\2\2\u01ab"+
		"\u01ac\3\2\2\2\u01ac\u01aa\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01b2\3\2"+
		"\2\2\u01ae\u01b1\t\f\2\2\u01af\u01b1\5\u0092I\2\u01b0\u01ae\3\2\2\2\u01b0"+
		"\u01af\3\2\2\2\u01b1\u01b4\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b2\u01b3\3\2"+
		"\2\2\u01b3{\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b5\u01b7\t\r\2\2\u01b6\u01b5"+
		"\3\2\2\2\u01b7\u01b8\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9"+
		"\u01be\3\2\2\2\u01ba\u01bd\t\f\2\2\u01bb\u01bd\5\u0092I\2\u01bc\u01ba"+
		"\3\2\2\2\u01bc\u01bb\3\2\2\2\u01bd\u01c0\3\2\2\2\u01be\u01bc\3\2\2\2\u01be"+
		"\u01bf\3\2\2\2\u01bf}\3\2\2\2\u01c0\u01be\3\2\2\2\u01c1\u01c2\7&\2\2\u01c2"+
		"\u01c3\5z=\2\u01c3\177\3\2\2\2\u01c4\u01c6\7\17\2\2\u01c5\u01c4\3\2\2"+
		"\2\u01c5\u01c6\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7\u01c8\7\f\2\2\u01c8\u01c9"+
		"\b@\4\2\u01c9\u0081\3\2\2\2\u01ca\u01cc\t\2\2\2\u01cb\u01ca\3\2\2\2\u01cc"+
		"\u01cd\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01cf\3\2"+
		"\2\2\u01cf\u01d0\bA\5\2\u01d0\u0083\3\2\2\2\u01d1\u01d3\n\5\2\2\u01d2"+
		"\u01d1\3\2\2\2\u01d3\u01d4\3\2\2\2\u01d4\u01d2\3\2\2\2\u01d4\u01d5\3\2"+
		"\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d7\bB\6\2\u01d7\u0085\3\2\2\2\u01d8"+
		"\u01d9\6C\2\2\u01d9\u01da\7\60\2\2\u01da\u01db\bC\7\2\u01db\u0087\3\2"+
		"\2\2\u01dc\u01dd\7\60\2\2\u01dd\u01de\bD\b\2\u01de\u0089\3\2\2\2\u01df"+
		"\u01e3\5\u008cF\2\u01e0\u01e3\5\u008eG\2\u01e1\u01e3\5j\65\2\u01e2\u01df"+
		"\3\2\2\2\u01e2\u01e0\3\2\2\2\u01e2\u01e1\3\2\2\2\u01e3\u008b\3\2\2\2\u01e4"+
		"\u01e5\n\6\2\2\u01e5\u008d\3\2\2\2\u01e6\u01e7\7^\2\2\u01e7\u01e8\t\7"+
		"\2\2\u01e8\u008f\3\2\2\2\u01e9\u01eb\t\b\2\2\u01ea\u01ec\t\t\2\2\u01eb"+
		"\u01ea\3\2\2\2\u01eb\u01ec\3\2\2\2\u01ec\u01ee\3\2\2\2\u01ed\u01ef\5\u0092"+
		"I\2\u01ee\u01ed\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01ee\3\2\2\2\u01f0"+
		"\u01f1\3\2\2\2\u01f1\u0091\3\2\2\2\u01f2\u01f5\7\62\2\2\u01f3\u01f5\5"+
		"\u0094J\2\u01f4\u01f2\3\2\2\2\u01f4\u01f3\3\2\2\2\u01f5\u0093\3\2\2\2"+
		"\u01f6\u01f7\4\63;\2\u01f7\u0095\3\2\2\2\u01f8\u01f9\t\n\2\2\u01f9\u0097"+
		"\3\2\2\2 \2\3\u0152\u0155\u015f\u0163\u016a\u016d\u0174\u0177\u017c\u0182"+
		"\u0185\u018a\u018e\u0199\u01a4\u01ac\u01b0\u01b2\u01b8\u01bc\u01be\u01c5"+
		"\u01cd\u01d4\u01e2\u01eb\u01f0\u01f4\t\3\63\2\7\3\2\3@\3\3A\4\3B\5\3C"+
		"\6\3D\7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}