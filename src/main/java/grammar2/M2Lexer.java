// Generated from /home/over/build/abra_lang/grammar/M2Lexer.g4 by ANTLR 4.5.3
package grammar2;
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
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		MINUS=1, PLUS=2, MUL=3, DIV=4, EXCL=5, DOT=6, RB=7, LB=8, COMMA=9, MORE_=10, 
		MORE_EQ=11, LESS=12, LESS_EQ=13, EQ=14, EQEQ=15, NOTEQ=16, SEMI=17, IF=18, 
		THEN=19, ELSE=20, CBO=21, CBC=22, LOGIC_OR=23, LOGIC_AND=24, WHILE=25, 
		VAL=26, VAR=27, CON=28, ARROW_RIGHT=29, TYPE=30, BACK_SLASH=31, SELF=32, 
		DEF=33, IMPORT=34, WITH=35, LlBegin=36, WS=37, NL=38, COMMENT=39, LlLiteral=40, 
		IntLiteral=41, HexLiteral=42, FloatLiteral=43, BooleanLiteral=44, StringLiteral=45, 
		Id=46, IrInline=47, LlEnd=48;
	public static final int llvm = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "llvm"
	};

	public static final String[] ruleNames = {
		"MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", "MORE_", 
		"MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", "IF", "THEN", 
		"ELSE", "CBO", "CBC", "LOGIC_OR", "LOGIC_AND", "WHILE", "VAL", "VAR", 
		"CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", "DEF", "IMPORT", "WITH", 
		"LlBegin", "WS", "NL", "COMMENT", "LlLiteral", "IntLiteral", "HexLiteral", 
		"FloatLiteral", "BooleanLiteral", "StringLiteral", "Id", "IrInline", "LlEnd", 
		"StringElement", "Char", "CharEscapeSeq", "ExponentPart", "Digit", "NonZeroDigit", 
		"HexDigit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'-'", "'+'", "'*'", "'/'", "'!'", "'.'", "')'", "'('", "','", "'>'", 
		"'>='", "'<'", "'<='", "'='", "'=='", "'!='", "';'", "'if'", "'then'", 
		"'else'", "'{'", "'}'", "'||'", "'&&'", "'while'", "'val'", "'var'", "':'", 
		"'->'", "'type'", "'\\'", "'self'", "'def'", "'import'", "'with'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", 
		"MORE_", "MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", 
		"IF", "THEN", "ELSE", "CBO", "CBC", "LOGIC_OR", "LOGIC_AND", "WHILE", 
		"VAL", "VAR", "CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", "DEF", 
		"IMPORT", "WITH", "LlBegin", "WS", "NL", "COMMENT", "LlLiteral", "IntLiteral", 
		"HexLiteral", "FloatLiteral", "BooleanLiteral", "StringLiteral", "Id", 
		"IrInline", "LlEnd"
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
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u0178\b\1\b\1"+
		"\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t"+
		"\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4"+
		"\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4"+
		"\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4"+
		" \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4"+
		"+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4"+
		"\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\3\2\3\2\3\3\3\3\3\4\3\4\3"+
		"\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26"+
		"\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3"+
		"#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\7%\u00e5\n%\f%\16%\u00e8"+
		"\13%\3%\3%\3%\3%\3&\6&\u00ef\n&\r&\16&\u00f0\3&\3&\3\'\5\'\u00f6\n\'\3"+
		"\'\3\'\3(\3(\7(\u00fc\n(\f(\16(\u00ff\13(\3(\3(\3)\3)\3)\3)\3*\5*\u0108"+
		"\n*\3*\3*\3*\7*\u010d\n*\f*\16*\u0110\13*\5*\u0112\n*\3+\3+\3+\6+\u0117"+
		"\n+\r+\16+\u0118\3,\5,\u011c\n,\3,\6,\u011f\n,\r,\16,\u0120\3,\3,\6,\u0125"+
		"\n,\r,\16,\u0126\3,\5,\u012a\n,\3,\6,\u012d\n,\r,\16,\u012e\3,\3,\5,\u0133"+
		"\n,\3-\3-\3-\3-\3-\3-\3-\3-\3-\5-\u013e\n-\3.\3.\7.\u0142\n.\f.\16.\u0145"+
		"\13.\3.\3.\3/\6/\u014a\n/\r/\16/\u014b\3/\3/\7/\u0150\n/\f/\16/\u0153"+
		"\13/\3\60\6\60\u0156\n\60\r\60\16\60\u0157\3\61\3\61\3\61\3\61\3\62\3"+
		"\62\3\62\5\62\u0161\n\62\3\63\3\63\3\64\3\64\3\64\3\65\3\65\5\65\u016a"+
		"\n\65\3\65\6\65\u016d\n\65\r\65\16\65\u016e\3\66\3\66\5\66\u0173\n\66"+
		"\3\67\3\67\38\38\2\29\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30"+
		"\r\32\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33"+
		"\66\348\35:\36<\37> @!B\"D#F$H%J&L\'N(P)R*T+V,X-Z.\\/^\60`\61b\62d\2f"+
		"\2h\2j\2l\2n\2p\2\4\2\3\16\5\2\13\f\17\17\"\"\4\2\13\13\"\"\4\2\f\f\17"+
		"\17\4\2ZZzz\4\2C\\c|\5\2C\\aac|\4\2}}\177\177\5\2\f\f))^^\t\2))^^ddhh"+
		"ppttvv\4\2GGgg\4\2--//\5\2\62;CHch\u0188\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3"+
		"\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2\20\3\2\2\2\2\22\3\2\2\2"+
		"\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3\2\2\2\2\34\3\2\2\2\2\36"+
		"\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3"+
		"\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62\3\2\2\2\2\64\3\2\2\2\2"+
		"\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2>\3\2\2\2\2@\3\2\2\2\2B"+
		"\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3\2\2\2\2L\3\2\2\2\2N\3\2"+
		"\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2"+
		"\2\\\3\2\2\2\2^\3\2\2\2\3`\3\2\2\2\3b\3\2\2\2\4r\3\2\2\2\6t\3\2\2\2\b"+
		"v\3\2\2\2\nx\3\2\2\2\fz\3\2\2\2\16|\3\2\2\2\20~\3\2\2\2\22\u0080\3\2\2"+
		"\2\24\u0082\3\2\2\2\26\u0084\3\2\2\2\30\u0086\3\2\2\2\32\u0089\3\2\2\2"+
		"\34\u008b\3\2\2\2\36\u008e\3\2\2\2 \u0090\3\2\2\2\"\u0093\3\2\2\2$\u0096"+
		"\3\2\2\2&\u0098\3\2\2\2(\u009b\3\2\2\2*\u00a0\3\2\2\2,\u00a5\3\2\2\2."+
		"\u00a7\3\2\2\2\60\u00a9\3\2\2\2\62\u00ac\3\2\2\2\64\u00af\3\2\2\2\66\u00b5"+
		"\3\2\2\28\u00b9\3\2\2\2:\u00bd\3\2\2\2<\u00bf\3\2\2\2>\u00c2\3\2\2\2@"+
		"\u00c7\3\2\2\2B\u00c9\3\2\2\2D\u00ce\3\2\2\2F\u00d2\3\2\2\2H\u00d9\3\2"+
		"\2\2J\u00de\3\2\2\2L\u00ee\3\2\2\2N\u00f5\3\2\2\2P\u00f9\3\2\2\2R\u0102"+
		"\3\2\2\2T\u0111\3\2\2\2V\u0113\3\2\2\2X\u0132\3\2\2\2Z\u013d\3\2\2\2\\"+
		"\u013f\3\2\2\2^\u0149\3\2\2\2`\u0155\3\2\2\2b\u0159\3\2\2\2d\u0160\3\2"+
		"\2\2f\u0162\3\2\2\2h\u0164\3\2\2\2j\u0167\3\2\2\2l\u0172\3\2\2\2n\u0174"+
		"\3\2\2\2p\u0176\3\2\2\2rs\7/\2\2s\5\3\2\2\2tu\7-\2\2u\7\3\2\2\2vw\7,\2"+
		"\2w\t\3\2\2\2xy\7\61\2\2y\13\3\2\2\2z{\7#\2\2{\r\3\2\2\2|}\7\60\2\2}\17"+
		"\3\2\2\2~\177\7+\2\2\177\21\3\2\2\2\u0080\u0081\7*\2\2\u0081\23\3\2\2"+
		"\2\u0082\u0083\7.\2\2\u0083\25\3\2\2\2\u0084\u0085\7@\2\2\u0085\27\3\2"+
		"\2\2\u0086\u0087\7@\2\2\u0087\u0088\7?\2\2\u0088\31\3\2\2\2\u0089\u008a"+
		"\7>\2\2\u008a\33\3\2\2\2\u008b\u008c\7>\2\2\u008c\u008d\7?\2\2\u008d\35"+
		"\3\2\2\2\u008e\u008f\7?\2\2\u008f\37\3\2\2\2\u0090\u0091\7?\2\2\u0091"+
		"\u0092\7?\2\2\u0092!\3\2\2\2\u0093\u0094\7#\2\2\u0094\u0095\7?\2\2\u0095"+
		"#\3\2\2\2\u0096\u0097\7=\2\2\u0097%\3\2\2\2\u0098\u0099\7k\2\2\u0099\u009a"+
		"\7h\2\2\u009a\'\3\2\2\2\u009b\u009c\7v\2\2\u009c\u009d\7j\2\2\u009d\u009e"+
		"\7g\2\2\u009e\u009f\7p\2\2\u009f)\3\2\2\2\u00a0\u00a1\7g\2\2\u00a1\u00a2"+
		"\7n\2\2\u00a2\u00a3\7u\2\2\u00a3\u00a4\7g\2\2\u00a4+\3\2\2\2\u00a5\u00a6"+
		"\7}\2\2\u00a6-\3\2\2\2\u00a7\u00a8\7\177\2\2\u00a8/\3\2\2\2\u00a9\u00aa"+
		"\7~\2\2\u00aa\u00ab\7~\2\2\u00ab\61\3\2\2\2\u00ac\u00ad\7(\2\2\u00ad\u00ae"+
		"\7(\2\2\u00ae\63\3\2\2\2\u00af\u00b0\7y\2\2\u00b0\u00b1\7j\2\2\u00b1\u00b2"+
		"\7k\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7g\2\2\u00b4\65\3\2\2\2\u00b5\u00b6"+
		"\7x\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7n\2\2\u00b8\67\3\2\2\2\u00b9\u00ba"+
		"\7x\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7t\2\2\u00bc9\3\2\2\2\u00bd\u00be"+
		"\7<\2\2\u00be;\3\2\2\2\u00bf\u00c0\7/\2\2\u00c0\u00c1\7@\2\2\u00c1=\3"+
		"\2\2\2\u00c2\u00c3\7v\2\2\u00c3\u00c4\7{\2\2\u00c4\u00c5\7r\2\2\u00c5"+
		"\u00c6\7g\2\2\u00c6?\3\2\2\2\u00c7\u00c8\7^\2\2\u00c8A\3\2\2\2\u00c9\u00ca"+
		"\7u\2\2\u00ca\u00cb\7g\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7h\2\2\u00cd"+
		"C\3\2\2\2\u00ce\u00cf\7f\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7h\2\2\u00d1"+
		"E\3\2\2\2\u00d2\u00d3\7k\2\2\u00d3\u00d4\7o\2\2\u00d4\u00d5\7r\2\2\u00d5"+
		"\u00d6\7q\2\2\u00d6\u00d7\7t\2\2\u00d7\u00d8\7v\2\2\u00d8G\3\2\2\2\u00d9"+
		"\u00da\7y\2\2\u00da\u00db\7k\2\2\u00db\u00dc\7v\2\2\u00dc\u00dd\7j\2\2"+
		"\u00ddI\3\2\2\2\u00de\u00df\7n\2\2\u00df\u00e0\7n\2\2\u00e0\u00e1\7x\2"+
		"\2\u00e1\u00e2\7o\2\2\u00e2\u00e6\3\2\2\2\u00e3\u00e5\t\2\2\2\u00e4\u00e3"+
		"\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7}\2\2\u00ea\u00eb\3\2"+
		"\2\2\u00eb\u00ec\b%\2\2\u00ecK\3\2\2\2\u00ed\u00ef\t\3\2\2\u00ee\u00ed"+
		"\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1"+
		"\u00f2\3\2\2\2\u00f2\u00f3\b&\3\2\u00f3M\3\2\2\2\u00f4\u00f6\7\17\2\2"+
		"\u00f5\u00f4\3\2\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8"+
		"\7\f\2\2\u00f8O\3\2\2\2\u00f9\u00fd\7%\2\2\u00fa\u00fc\n\4\2\2\u00fb\u00fa"+
		"\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe"+
		"\u0100\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100\u0101\b(\3\2\u0101Q\3\2\2\2\u0102"+
		"\u0103\5J%\2\u0103\u0104\5`\60\2\u0104\u0105\5b\61\2\u0105S\3\2\2\2\u0106"+
		"\u0108\7/\2\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u0109\3\2"+
		"\2\2\u0109\u0112\7\62\2\2\u010a\u010e\5n\67\2\u010b\u010d\5l\66\2\u010c"+
		"\u010b\3\2\2\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2"+
		"\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0107\3\2\2\2\u0111"+
		"\u010a\3\2\2\2\u0112U\3\2\2\2\u0113\u0114\7\62\2\2\u0114\u0116\t\5\2\2"+
		"\u0115\u0117\5p8\2\u0116\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\u0116"+
		"\3\2\2\2\u0118\u0119\3\2\2\2\u0119W\3\2\2\2\u011a\u011c\7/\2\2\u011b\u011a"+
		"\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011e\3\2\2\2\u011d\u011f\5l\66\2\u011e"+
		"\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u0121\3\2"+
		"\2\2\u0121\u0122\3\2\2\2\u0122\u0124\7\60\2\2\u0123\u0125\5l\66\2\u0124"+
		"\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127\u0129\3\2\2\2\u0128\u012a\5j\65\2\u0129\u0128\3\2\2\2\u0129"+
		"\u012a\3\2\2\2\u012a\u0133\3\2\2\2\u012b\u012d\5l\66\2\u012c\u012b\3\2"+
		"\2\2\u012d\u012e\3\2\2\2\u012e\u012c\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0130\3\2\2\2\u0130\u0131\5j\65\2\u0131\u0133\3\2\2\2\u0132\u011b\3\2"+
		"\2\2\u0132\u012c\3\2\2\2\u0133Y\3\2\2\2\u0134\u0135\7v\2\2\u0135\u0136"+
		"\7t\2\2\u0136\u0137\7w\2\2\u0137\u013e\7g\2\2\u0138\u0139\7h\2\2\u0139"+
		"\u013a\7c\2\2\u013a\u013b\7n\2\2\u013b\u013c\7u\2\2\u013c\u013e\7g\2\2"+
		"\u013d\u0134\3\2\2\2\u013d\u0138\3\2\2\2\u013e[\3\2\2\2\u013f\u0143\7"+
		")\2\2\u0140\u0142\5d\62\2\u0141\u0140\3\2\2\2\u0142\u0145\3\2\2\2\u0143"+
		"\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144\u0146\3\2\2\2\u0145\u0143\3\2"+
		"\2\2\u0146\u0147\7)\2\2\u0147]\3\2\2\2\u0148\u014a\t\6\2\2\u0149\u0148"+
		"\3\2\2\2\u014a\u014b\3\2\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c"+
		"\u0151\3\2\2\2\u014d\u0150\t\7\2\2\u014e\u0150\5l\66\2\u014f\u014d\3\2"+
		"\2\2\u014f\u014e\3\2\2\2\u0150\u0153\3\2\2\2\u0151\u014f\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152_\3\2\2\2\u0153\u0151\3\2\2\2\u0154\u0156\n\b\2\2"+
		"\u0155\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158"+
		"\3\2\2\2\u0158a\3\2\2\2\u0159\u015a\5.\27\2\u015a\u015b\3\2\2\2\u015b"+
		"\u015c\b\61\4\2\u015cc\3\2\2\2\u015d\u0161\5f\63\2\u015e\u0161\5h\64\2"+
		"\u015f\u0161\5N\'\2\u0160\u015d\3\2\2\2\u0160\u015e\3\2\2\2\u0160\u015f"+
		"\3\2\2\2\u0161e\3\2\2\2\u0162\u0163\n\t\2\2\u0163g\3\2\2\2\u0164\u0165"+
		"\7^\2\2\u0165\u0166\t\n\2\2\u0166i\3\2\2\2\u0167\u0169\t\13\2\2\u0168"+
		"\u016a\t\f\2\2\u0169\u0168\3\2\2\2\u0169\u016a\3\2\2\2\u016a\u016c\3\2"+
		"\2\2\u016b\u016d\5l\66\2\u016c\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e"+
		"\u016c\3\2\2\2\u016e\u016f\3\2\2\2\u016fk\3\2\2\2\u0170\u0173\7\62\2\2"+
		"\u0171\u0173\5n\67\2\u0172\u0170\3\2\2\2\u0172\u0171\3\2\2\2\u0173m\3"+
		"\2\2\2\u0174\u0175\4\63;\2\u0175o\3\2\2\2\u0176\u0177\t\r\2\2\u0177q\3"+
		"\2\2\2\34\2\3\u00e6\u00f0\u00f5\u00fd\u0107\u010e\u0111\u0118\u011b\u0120"+
		"\u0126\u0129\u012e\u0132\u013d\u0143\u014b\u014f\u0151\u0157\u0160\u0169"+
		"\u016e\u0172\5\7\3\2\b\2\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}