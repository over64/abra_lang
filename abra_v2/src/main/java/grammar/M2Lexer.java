// Generated from /home/over/build/abra_lang/abra_v2/grammar/M2Lexer.g4 by ANTLR 4.7
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
public class M2Lexer extends Lexer {
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
		llvm=1;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "llvm"
	};

	public static final String[] ruleNames = {
		"MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", "MORE_", 
		"MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", "IF", "THEN", 
		"ELSE", "CBO", "DOLLAR_CBO", "CBC", "LOGIC_OR", "LOGIC_AND", "WHILE", 
		"VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", "MATCH_SELF", 
		"DEF", "IMPORT", "WITH", "MATCH", "OF", "DASH", "VERT_LINE", "BRACKET_LEFT", 
		"BRACKET_RIGTH", "AMP", "LlBegin", "LlDef", "WS", "NL", "COMMENT", "IntLiteral", 
		"HexLiteral", "FloatLiteral", "BooleanLiteral", "StringLiteral", "VarId", 
		"TypeId", "MatchId", "IrInline", "LlEnd", "StringElement", "Char", "CharEscapeSeq", 
		"ExponentPart", "Digit", "NonZeroDigit", "HexDigit"
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


	    boolean startLlvm = false;


	public M2Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "M2Lexer.g4"; }

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
		case 29:
			ARROW_RIGHT_action((RuleContext)_localctx, actionIndex);
			break;
		case 45:
			LlDef_action((RuleContext)_localctx, actionIndex);
			break;
		case 58:
			LlEnd_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ARROW_RIGHT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 if(startLlvm) pushMode(llvm); 
			break;
		}
	}
	private void LlDef_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			 startLlvm = true; 
			break;
		}
	}
	private void LlEnd_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			 startLlvm = false; 
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2=\u01c9\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5"+
		"\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3"+
		"#\3#\3#\3#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'"+
		"\3\'\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3.\3.\3."+
		"\3.\3.\7.\u011b\n.\f.\16.\u011e\13.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\7"+
		"/\u012b\n/\f/\16/\u012e\13/\3/\3/\3/\3\60\6\60\u0134\n\60\r\60\16\60\u0135"+
		"\3\60\3\60\3\61\5\61\u013b\n\61\3\61\3\61\3\62\3\62\7\62\u0141\n\62\f"+
		"\62\16\62\u0144\13\62\3\62\3\62\3\63\5\63\u0149\n\63\3\63\3\63\3\63\7"+
		"\63\u014e\n\63\f\63\16\63\u0151\13\63\5\63\u0153\n\63\3\64\3\64\3\64\6"+
		"\64\u0158\n\64\r\64\16\64\u0159\3\65\5\65\u015d\n\65\3\65\6\65\u0160\n"+
		"\65\r\65\16\65\u0161\3\65\3\65\6\65\u0166\n\65\r\65\16\65\u0167\3\65\5"+
		"\65\u016b\n\65\3\65\6\65\u016e\n\65\r\65\16\65\u016f\3\65\3\65\5\65\u0174"+
		"\n\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u017f\n\66\3\67"+
		"\3\67\7\67\u0183\n\67\f\67\16\67\u0186\13\67\3\67\3\67\38\68\u018b\n8"+
		"\r8\168\u018c\38\38\78\u0191\n8\f8\168\u0194\138\39\69\u0197\n9\r9\16"+
		"9\u0198\39\39\79\u019d\n9\f9\169\u01a0\139\3:\3:\3:\3;\6;\u01a6\n;\r;"+
		"\16;\u01a7\3<\3<\3<\3<\3<\3=\3=\3=\5=\u01b2\n=\3>\3>\3?\3?\3?\3@\3@\5"+
		"@\u01bb\n@\3@\6@\u01be\n@\r@\16@\u01bf\3A\3A\5A\u01c4\nA\3B\3B\3C\3C\2"+
		"\2D\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17\36"+
		"\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36<"+
		"\37> @!B\"D#F$H%J&L\'N(P)R*T+V,X-Z.\\/^\60`\61b\62d\63f\64h\65j\66l\67"+
		"n8p9r:t;v<x=z\2|\2~\2\u0080\2\u0082\2\u0084\2\u0086\2\4\2\3\r\5\2\13\f"+
		"\17\17\"\"\4\2\13\13\"\"\4\2\f\f\17\17\4\2ZZzz\5\2C\\aac|\4\2}}\177\177"+
		"\5\2\f\f))^^\t\2))^^ddhhppttvv\4\2GGgg\4\2--//\5\2\62;CHch\2\u01dd\2\4"+
		"\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2"+
		"\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32"+
		"\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2"+
		"&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62"+
		"\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2"+
		">\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3"+
		"\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2"+
		"\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3\2\2\2"+
		"\2d\3\2\2\2\2f\3\2\2\2\2h\3\2\2\2\2j\3\2\2\2\2l\3\2\2\2\2n\3\2\2\2\2p"+
		"\3\2\2\2\2r\3\2\2\2\2t\3\2\2\2\3v\3\2\2\2\3x\3\2\2\2\4\u0088\3\2\2\2\6"+
		"\u008a\3\2\2\2\b\u008c\3\2\2\2\n\u008e\3\2\2\2\f\u0090\3\2\2\2\16\u0092"+
		"\3\2\2\2\20\u0094\3\2\2\2\22\u0096\3\2\2\2\24\u0098\3\2\2\2\26\u009a\3"+
		"\2\2\2\30\u009c\3\2\2\2\32\u009f\3\2\2\2\34\u00a1\3\2\2\2\36\u00a4\3\2"+
		"\2\2 \u00a6\3\2\2\2\"\u00a9\3\2\2\2$\u00ac\3\2\2\2&\u00ae\3\2\2\2(\u00b1"+
		"\3\2\2\2*\u00b6\3\2\2\2,\u00bb\3\2\2\2.\u00bd\3\2\2\2\60\u00c0\3\2\2\2"+
		"\62\u00c2\3\2\2\2\64\u00c5\3\2\2\2\66\u00c8\3\2\2\28\u00ce\3\2\2\2:\u00d2"+
		"\3\2\2\2<\u00d6\3\2\2\2>\u00d8\3\2\2\2@\u00dd\3\2\2\2B\u00e2\3\2\2\2D"+
		"\u00e4\3\2\2\2F\u00e9\3\2\2\2H\u00ef\3\2\2\2J\u00f3\3\2\2\2L\u00fa\3\2"+
		"\2\2N\u00ff\3\2\2\2P\u0105\3\2\2\2R\u0108\3\2\2\2T\u010a\3\2\2\2V\u010c"+
		"\3\2\2\2X\u010e\3\2\2\2Z\u0110\3\2\2\2\\\u0112\3\2\2\2^\u0123\3\2\2\2"+
		"`\u0133\3\2\2\2b\u013a\3\2\2\2d\u013e\3\2\2\2f\u0148\3\2\2\2h\u0154\3"+
		"\2\2\2j\u015c\3\2\2\2l\u017e\3\2\2\2n\u0180\3\2\2\2p\u018a\3\2\2\2r\u0196"+
		"\3\2\2\2t\u01a1\3\2\2\2v\u01a5\3\2\2\2x\u01a9\3\2\2\2z\u01b1\3\2\2\2|"+
		"\u01b3\3\2\2\2~\u01b5\3\2\2\2\u0080\u01b8\3\2\2\2\u0082\u01c3\3\2\2\2"+
		"\u0084\u01c5\3\2\2\2\u0086\u01c7\3\2\2\2\u0088\u0089\7/\2\2\u0089\5\3"+
		"\2\2\2\u008a\u008b\7-\2\2\u008b\7\3\2\2\2\u008c\u008d\7,\2\2\u008d\t\3"+
		"\2\2\2\u008e\u008f\7\61\2\2\u008f\13\3\2\2\2\u0090\u0091\7#\2\2\u0091"+
		"\r\3\2\2\2\u0092\u0093\7\60\2\2\u0093\17\3\2\2\2\u0094\u0095\7+\2\2\u0095"+
		"\21\3\2\2\2\u0096\u0097\7*\2\2\u0097\23\3\2\2\2\u0098\u0099\7.\2\2\u0099"+
		"\25\3\2\2\2\u009a\u009b\7@\2\2\u009b\27\3\2\2\2\u009c\u009d\7@\2\2\u009d"+
		"\u009e\7?\2\2\u009e\31\3\2\2\2\u009f\u00a0\7>\2\2\u00a0\33\3\2\2\2\u00a1"+
		"\u00a2\7>\2\2\u00a2\u00a3\7?\2\2\u00a3\35\3\2\2\2\u00a4\u00a5\7?\2\2\u00a5"+
		"\37\3\2\2\2\u00a6\u00a7\7?\2\2\u00a7\u00a8\7?\2\2\u00a8!\3\2\2\2\u00a9"+
		"\u00aa\7#\2\2\u00aa\u00ab\7?\2\2\u00ab#\3\2\2\2\u00ac\u00ad\7=\2\2\u00ad"+
		"%\3\2\2\2\u00ae\u00af\7k\2\2\u00af\u00b0\7h\2\2\u00b0\'\3\2\2\2\u00b1"+
		"\u00b2\7v\2\2\u00b2\u00b3\7j\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7p\2\2"+
		"\u00b5)\3\2\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7n\2\2\u00b8\u00b9\7u\2"+
		"\2\u00b9\u00ba\7g\2\2\u00ba+\3\2\2\2\u00bb\u00bc\7}\2\2\u00bc-\3\2\2\2"+
		"\u00bd\u00be\7&\2\2\u00be\u00bf\7}\2\2\u00bf/\3\2\2\2\u00c0\u00c1\7\177"+
		"\2\2\u00c1\61\3\2\2\2\u00c2\u00c3\7~\2\2\u00c3\u00c4\7~\2\2\u00c4\63\3"+
		"\2\2\2\u00c5\u00c6\7(\2\2\u00c6\u00c7\7(\2\2\u00c7\65\3\2\2\2\u00c8\u00c9"+
		"\7y\2\2\u00c9\u00ca\7j\2\2\u00ca\u00cb\7k\2\2\u00cb\u00cc\7n\2\2\u00cc"+
		"\u00cd\7g\2\2\u00cd\67\3\2\2\2\u00ce\u00cf\7x\2\2\u00cf\u00d0\7c\2\2\u00d0"+
		"\u00d1\7n\2\2\u00d19\3\2\2\2\u00d2\u00d3\7x\2\2\u00d3\u00d4\7c\2\2\u00d4"+
		"\u00d5\7t\2\2\u00d5;\3\2\2\2\u00d6\u00d7\7<\2\2\u00d7=\3\2\2\2\u00d8\u00d9"+
		"\7/\2\2\u00d9\u00da\7@\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\b\37\2\2\u00dc"+
		"?\3\2\2\2\u00dd\u00de\7v\2\2\u00de\u00df\7{\2\2\u00df\u00e0\7r\2\2\u00e0"+
		"\u00e1\7g\2\2\u00e1A\3\2\2\2\u00e2\u00e3\7^\2\2\u00e3C\3\2\2\2\u00e4\u00e5"+
		"\7u\2\2\u00e5\u00e6\7g\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7h\2\2\u00e8"+
		"E\3\2\2\2\u00e9\u00ea\7&\2\2\u00ea\u00eb\7u\2\2\u00eb\u00ec\7g\2\2\u00ec"+
		"\u00ed\7n\2\2\u00ed\u00ee\7h\2\2\u00eeG\3\2\2\2\u00ef\u00f0\7f\2\2\u00f0"+
		"\u00f1\7g\2\2\u00f1\u00f2\7h\2\2\u00f2I\3\2\2\2\u00f3\u00f4\7k\2\2\u00f4"+
		"\u00f5\7o\2\2\u00f5\u00f6\7r\2\2\u00f6\u00f7\7q\2\2\u00f7\u00f8\7t\2\2"+
		"\u00f8\u00f9\7v\2\2\u00f9K\3\2\2\2\u00fa\u00fb\7y\2\2\u00fb\u00fc\7k\2"+
		"\2\u00fc\u00fd\7v\2\2\u00fd\u00fe\7j\2\2\u00feM\3\2\2\2\u00ff\u0100\7"+
		"o\2\2\u0100\u0101\7c\2\2\u0101\u0102\7v\2\2\u0102\u0103\7e\2\2\u0103\u0104"+
		"\7j\2\2\u0104O\3\2\2\2\u0105\u0106\7q\2\2\u0106\u0107\7h\2\2\u0107Q\3"+
		"\2\2\2\u0108\u0109\7a\2\2\u0109S\3\2\2\2\u010a\u010b\7~\2\2\u010bU\3\2"+
		"\2\2\u010c\u010d\7]\2\2\u010dW\3\2\2\2\u010e\u010f\7_\2\2\u010fY\3\2\2"+
		"\2\u0110\u0111\7(\2\2\u0111[\3\2\2\2\u0112\u0113\7n\2\2\u0113\u0114\7"+
		"n\2\2\u0114\u0115\7v\2\2\u0115\u0116\7{\2\2\u0116\u0117\7r\2\2\u0117\u0118"+
		"\7g\2\2\u0118\u011c\3\2\2\2\u0119\u011b\t\2\2\2\u011a\u0119\3\2\2\2\u011b"+
		"\u011e\3\2\2\2\u011c\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2"+
		"\2\2\u011e\u011c\3\2\2\2\u011f\u0120\7}\2\2\u0120\u0121\3\2\2\2\u0121"+
		"\u0122\b.\3\2\u0122]\3\2\2\2\u0123\u0124\7n\2\2\u0124\u0125\7n\2\2\u0125"+
		"\u0126\7f\2\2\u0126\u0127\7g\2\2\u0127\u0128\7h\2\2\u0128\u012c\3\2\2"+
		"\2\u0129\u012b\t\2\2\2\u012a\u0129\3\2\2\2\u012b\u012e\3\2\2\2\u012c\u012a"+
		"\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012f\3\2\2\2\u012e\u012c\3\2\2\2\u012f"+
		"\u0130\7}\2\2\u0130\u0131\b/\4\2\u0131_\3\2\2\2\u0132\u0134\t\3\2\2\u0133"+
		"\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135\u0133\3\2\2\2\u0135\u0136\3\2"+
		"\2\2\u0136\u0137\3\2\2\2\u0137\u0138\b\60\5\2\u0138a\3\2\2\2\u0139\u013b"+
		"\7\17\2\2\u013a\u0139\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c\3\2\2\2"+
		"\u013c\u013d\7\f\2\2\u013dc\3\2\2\2\u013e\u0142\7%\2\2\u013f\u0141\n\4"+
		"\2\2\u0140\u013f\3\2\2\2\u0141\u0144\3\2\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0143\3\2\2\2\u0143\u0145\3\2\2\2\u0144\u0142\3\2\2\2\u0145\u0146\b\62"+
		"\5\2\u0146e\3\2\2\2\u0147\u0149\7/\2\2\u0148\u0147\3\2\2\2\u0148\u0149"+
		"\3\2\2\2\u0149\u0152\3\2\2\2\u014a\u0153\7\62\2\2\u014b\u014f\5\u0084"+
		"B\2\u014c\u014e\5\u0082A\2\u014d\u014c\3\2\2\2\u014e\u0151\3\2\2\2\u014f"+
		"\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2"+
		"\2\2\u0152\u014a\3\2\2\2\u0152\u014b\3\2\2\2\u0153g\3\2\2\2\u0154\u0155"+
		"\7\62\2\2\u0155\u0157\t\5\2\2\u0156\u0158\5\u0086C\2\u0157\u0156\3\2\2"+
		"\2\u0158\u0159\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015ai"+
		"\3\2\2\2\u015b\u015d\7/\2\2\u015c\u015b\3\2\2\2\u015c\u015d\3\2\2\2\u015d"+
		"\u0173\3\2\2\2\u015e\u0160\5\u0082A\2\u015f\u015e\3\2\2\2\u0160\u0161"+
		"\3\2\2\2\u0161\u015f\3\2\2\2\u0161\u0162\3\2\2\2\u0162\u0163\3\2\2\2\u0163"+
		"\u0165\7\60\2\2\u0164\u0166\5\u0082A\2\u0165\u0164\3\2\2\2\u0166\u0167"+
		"\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u016a\3\2\2\2\u0169"+
		"\u016b\5\u0080@\2\u016a\u0169\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u0174"+
		"\3\2\2\2\u016c\u016e\5\u0082A\2\u016d\u016c\3\2\2\2\u016e\u016f\3\2\2"+
		"\2\u016f\u016d\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0172"+
		"\5\u0080@\2\u0172\u0174\3\2\2\2\u0173\u015f\3\2\2\2\u0173\u016d\3\2\2"+
		"\2\u0174k\3\2\2\2\u0175\u0176\7v\2\2\u0176\u0177\7t\2\2\u0177\u0178\7"+
		"w\2\2\u0178\u017f\7g\2\2\u0179\u017a\7h\2\2\u017a\u017b\7c\2\2\u017b\u017c"+
		"\7n\2\2\u017c\u017d\7u\2\2\u017d\u017f\7g\2\2\u017e\u0175\3\2\2\2\u017e"+
		"\u0179\3\2\2\2\u017fm\3\2\2\2\u0180\u0184\7)\2\2\u0181\u0183\5z=\2\u0182"+
		"\u0181\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182\3\2\2\2\u0184\u0185\3\2"+
		"\2\2\u0185\u0187\3\2\2\2\u0186\u0184\3\2\2\2\u0187\u0188\7)\2\2\u0188"+
		"o\3\2\2\2\u0189\u018b\4c|\2\u018a\u0189\3\2\2\2\u018b\u018c\3\2\2\2\u018c"+
		"\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d\u0192\3\2\2\2\u018e\u0191\t\6"+
		"\2\2\u018f\u0191\5\u0082A\2\u0190\u018e\3\2\2\2\u0190\u018f\3\2\2\2\u0191"+
		"\u0194\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193q\3\2\2\2"+
		"\u0194\u0192\3\2\2\2\u0195\u0197\4C\\\2\u0196\u0195\3\2\2\2\u0197\u0198"+
		"\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199\u019e\3\2\2\2\u019a"+
		"\u019d\t\6\2\2\u019b\u019d\5\u0082A\2\u019c\u019a\3\2\2\2\u019c\u019b"+
		"\3\2\2\2\u019d\u01a0\3\2\2\2\u019e\u019c\3\2\2\2\u019e\u019f\3\2\2\2\u019f"+
		"s\3\2\2\2\u01a0\u019e\3\2\2\2\u01a1\u01a2\7&\2\2\u01a2\u01a3\5p8\2\u01a3"+
		"u\3\2\2\2\u01a4\u01a6\n\7\2\2\u01a5\u01a4\3\2\2\2\u01a6\u01a7\3\2\2\2"+
		"\u01a7\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8w\3\2\2\2\u01a9\u01aa\5"+
		"\60\30\2\u01aa\u01ab\b<\6\2\u01ab\u01ac\3\2\2\2\u01ac\u01ad\b<\7\2\u01ad"+
		"y\3\2\2\2\u01ae\u01b2\5|>\2\u01af\u01b2\5~?\2\u01b0\u01b2\5b\61\2\u01b1"+
		"\u01ae\3\2\2\2\u01b1\u01af\3\2\2\2\u01b1\u01b0\3\2\2\2\u01b2{\3\2\2\2"+
		"\u01b3\u01b4\n\b\2\2\u01b4}\3\2\2\2\u01b5\u01b6\7^\2\2\u01b6\u01b7\t\t"+
		"\2\2\u01b7\177\3\2\2\2\u01b8\u01ba\t\n\2\2\u01b9\u01bb\t\13\2\2\u01ba"+
		"\u01b9\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01bd\3\2\2\2\u01bc\u01be\5\u0082"+
		"A\2\u01bd\u01bc\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01bd\3\2\2\2\u01bf"+
		"\u01c0\3\2\2\2\u01c0\u0081\3\2\2\2\u01c1\u01c4\7\62\2\2\u01c2\u01c4\5"+
		"\u0084B\2\u01c3\u01c1\3\2\2\2\u01c3\u01c2\3\2\2\2\u01c4\u0083\3\2\2\2"+
		"\u01c5\u01c6\4\63;\2\u01c6\u0085\3\2\2\2\u01c7\u01c8\t\f\2\2\u01c8\u0087"+
		"\3\2\2\2 \2\3\u011c\u012c\u0135\u013a\u0142\u0148\u014f\u0152\u0159\u015c"+
		"\u0161\u0167\u016a\u016f\u0173\u017e\u0184\u018c\u0190\u0192\u0198\u019c"+
		"\u019e\u01a7\u01b1\u01ba\u01bf\u01c3\b\3\37\2\7\3\2\3/\3\b\2\2\3<\4\6"+
		"\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}