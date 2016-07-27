// Generated from /home/over/build/test_lang/grammar/M2Lexer.g4 by ANTLR 4.5.3
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
		DEF=33, LlBegin=34, WS=35, NL=36, COMMENT=37, LlLiteral=38, IntLiteral=39, 
		FloatLiteral=40, BooleanLiteral=41, StringLiteral=42, Id=43, IrInline=44, 
		LlEnd=45;
	public static final int llvm = 1;
	public static String[] modeNames = {
		"DEFAULT_MODE", "llvm"
	};

	public static final String[] ruleNames = {
		"MINUS", "PLUS", "MUL", "DIV", "EXCL", "DOT", "RB", "LB", "COMMA", "MORE_", 
		"MORE_EQ", "LESS", "LESS_EQ", "EQ", "EQEQ", "NOTEQ", "SEMI", "IF", "THEN", 
		"ELSE", "CBO", "CBC", "LOGIC_OR", "LOGIC_AND", "WHILE", "VAL", "VAR", 
		"CON", "ARROW_RIGHT", "TYPE", "BACK_SLASH", "SELF", "DEF", "LlBegin", 
		"WS", "NL", "COMMENT", "LlLiteral", "IntLiteral", "FloatLiteral", "BooleanLiteral", 
		"StringLiteral", "Id", "IrInline", "LlEnd", "StringElement", "Char", "CharEscapeSeq", 
		"ExponentPart", "Digit", "NonZeroDigit"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2/\u0158\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t"+
		"\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3 \3!\3"+
		"!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\7#\u00d1\n#\f#\16#\u00d4"+
		"\13#\3#\3#\3#\3#\3#\3$\6$\u00dc\n$\r$\16$\u00dd\3$\3$\3%\5%\u00e3\n%\3"+
		"%\3%\3&\3&\7&\u00e9\n&\f&\16&\u00ec\13&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3"+
		"(\3(\3(\7(\u00f9\n(\f(\16(\u00fc\13(\5(\u00fe\n(\3)\6)\u0101\n)\r)\16"+
		")\u0102\3)\3)\6)\u0107\n)\r)\16)\u0108\3)\5)\u010c\n)\3)\6)\u010f\n)\r"+
		")\16)\u0110\3)\3)\5)\u0115\n)\3*\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0120\n*\3"+
		"+\3+\7+\u0124\n+\f+\16+\u0127\13+\3+\3+\3,\6,\u012c\n,\r,\16,\u012d\3"+
		",\3,\7,\u0132\n,\f,\16,\u0135\13,\3-\6-\u0138\n-\r-\16-\u0139\3.\3.\3"+
		".\3.\3.\3/\3/\5/\u0143\n/\3\60\3\60\3\61\3\61\3\61\3\62\3\62\5\62\u014c"+
		"\n\62\3\62\6\62\u014f\n\62\r\62\16\62\u0150\3\63\3\63\5\63\u0155\n\63"+
		"\3\64\3\64\3\u00ea\2\65\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f"+
		"\30\r\32\16\34\17\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64"+
		"\33\66\348\35:\36<\37> @!B\"D#F$H%J&L\'N(P)R*T+V,X-Z.\\/^\2`\2b\2d\2f"+
		"\2h\2\4\2\3\13\5\2\13\f\17\17\"\"\4\2\13\13\"\"\4\2C\\c|\5\2C\\aac|\4"+
		"\2}}\177\177\5\2\f\f))^^\t\2))^^ddhhppttvv\4\2GGgg\4\2--//\u0165\2\4\3"+
		"\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2\2\2\2"+
		"\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2\2\32\3"+
		"\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2\2\2\2&"+
		"\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2\2\2\62"+
		"\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2\2\2\2"+
		">\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2\2J\3"+
		"\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V\3\2\2"+
		"\2\2X\3\2\2\2\3Z\3\2\2\2\3\\\3\2\2\2\4j\3\2\2\2\6l\3\2\2\2\bn\3\2\2\2"+
		"\np\3\2\2\2\fr\3\2\2\2\16t\3\2\2\2\20v\3\2\2\2\22x\3\2\2\2\24z\3\2\2\2"+
		"\26|\3\2\2\2\30~\3\2\2\2\32\u0081\3\2\2\2\34\u0083\3\2\2\2\36\u0086\3"+
		"\2\2\2 \u0088\3\2\2\2\"\u008b\3\2\2\2$\u008e\3\2\2\2&\u0090\3\2\2\2(\u0093"+
		"\3\2\2\2*\u0098\3\2\2\2,\u009d\3\2\2\2.\u009f\3\2\2\2\60\u00a1\3\2\2\2"+
		"\62\u00a4\3\2\2\2\64\u00a7\3\2\2\2\66\u00ad\3\2\2\28\u00b1\3\2\2\2:\u00b5"+
		"\3\2\2\2<\u00b7\3\2\2\2>\u00ba\3\2\2\2@\u00bf\3\2\2\2B\u00c1\3\2\2\2D"+
		"\u00c6\3\2\2\2F\u00ca\3\2\2\2H\u00db\3\2\2\2J\u00e2\3\2\2\2L\u00e6\3\2"+
		"\2\2N\u00f1\3\2\2\2P\u00fd\3\2\2\2R\u0114\3\2\2\2T\u011f\3\2\2\2V\u0121"+
		"\3\2\2\2X\u012b\3\2\2\2Z\u0137\3\2\2\2\\\u013b\3\2\2\2^\u0142\3\2\2\2"+
		"`\u0144\3\2\2\2b\u0146\3\2\2\2d\u0149\3\2\2\2f\u0154\3\2\2\2h\u0156\3"+
		"\2\2\2jk\7/\2\2k\5\3\2\2\2lm\7-\2\2m\7\3\2\2\2no\7,\2\2o\t\3\2\2\2pq\7"+
		"\61\2\2q\13\3\2\2\2rs\7#\2\2s\r\3\2\2\2tu\7\60\2\2u\17\3\2\2\2vw\7+\2"+
		"\2w\21\3\2\2\2xy\7*\2\2y\23\3\2\2\2z{\7.\2\2{\25\3\2\2\2|}\7@\2\2}\27"+
		"\3\2\2\2~\177\7@\2\2\177\u0080\7?\2\2\u0080\31\3\2\2\2\u0081\u0082\7>"+
		"\2\2\u0082\33\3\2\2\2\u0083\u0084\7>\2\2\u0084\u0085\7?\2\2\u0085\35\3"+
		"\2\2\2\u0086\u0087\7?\2\2\u0087\37\3\2\2\2\u0088\u0089\7?\2\2\u0089\u008a"+
		"\7?\2\2\u008a!\3\2\2\2\u008b\u008c\7#\2\2\u008c\u008d\7?\2\2\u008d#\3"+
		"\2\2\2\u008e\u008f\7=\2\2\u008f%\3\2\2\2\u0090\u0091\7k\2\2\u0091\u0092"+
		"\7h\2\2\u0092\'\3\2\2\2\u0093\u0094\7v\2\2\u0094\u0095\7j\2\2\u0095\u0096"+
		"\7g\2\2\u0096\u0097\7p\2\2\u0097)\3\2\2\2\u0098\u0099\7g\2\2\u0099\u009a"+
		"\7n\2\2\u009a\u009b\7u\2\2\u009b\u009c\7g\2\2\u009c+\3\2\2\2\u009d\u009e"+
		"\7}\2\2\u009e-\3\2\2\2\u009f\u00a0\7\177\2\2\u00a0/\3\2\2\2\u00a1\u00a2"+
		"\7~\2\2\u00a2\u00a3\7~\2\2\u00a3\61\3\2\2\2\u00a4\u00a5\7(\2\2\u00a5\u00a6"+
		"\7(\2\2\u00a6\63\3\2\2\2\u00a7\u00a8\7y\2\2\u00a8\u00a9\7j\2\2\u00a9\u00aa"+
		"\7k\2\2\u00aa\u00ab\7n\2\2\u00ab\u00ac\7g\2\2\u00ac\65\3\2\2\2\u00ad\u00ae"+
		"\7x\2\2\u00ae\u00af\7c\2\2\u00af\u00b0\7n\2\2\u00b0\67\3\2\2\2\u00b1\u00b2"+
		"\7x\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7t\2\2\u00b49\3\2\2\2\u00b5\u00b6"+
		"\7<\2\2\u00b6;\3\2\2\2\u00b7\u00b8\7/\2\2\u00b8\u00b9\7@\2\2\u00b9=\3"+
		"\2\2\2\u00ba\u00bb\7v\2\2\u00bb\u00bc\7{\2\2\u00bc\u00bd\7r\2\2\u00bd"+
		"\u00be\7g\2\2\u00be?\3\2\2\2\u00bf\u00c0\7^\2\2\u00c0A\3\2\2\2\u00c1\u00c2"+
		"\7u\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4\7n\2\2\u00c4\u00c5\7h\2\2\u00c5"+
		"C\3\2\2\2\u00c6\u00c7\7f\2\2\u00c7\u00c8\7g\2\2\u00c8\u00c9\7h\2\2\u00c9"+
		"E\3\2\2\2\u00ca\u00cb\7n\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7x\2\2\u00cd"+
		"\u00ce\7o\2\2\u00ce\u00d2\3\2\2\2\u00cf\u00d1\t\2\2\2\u00d0\u00cf\3\2"+
		"\2\2\u00d1\u00d4\3\2\2\2\u00d2\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3"+
		"\u00d5\3\2\2\2\u00d4\u00d2\3\2\2\2\u00d5\u00d6\7}\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\u00d8\b#\2\2\u00d8\u00d9\b#\3\2\u00d9G\3\2\2\2\u00da\u00dc"+
		"\t\3\2\2\u00db\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00db\3\2\2\2\u00dd"+
		"\u00de\3\2\2\2\u00de\u00df\3\2\2\2\u00df\u00e0\b$\3\2\u00e0I\3\2\2\2\u00e1"+
		"\u00e3\7\17\2\2\u00e2\u00e1\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\3"+
		"\2\2\2\u00e4\u00e5\7\f\2\2\u00e5K\3\2\2\2\u00e6\u00ea\7%\2\2\u00e7\u00e9"+
		"\13\2\2\2\u00e8\u00e7\3\2\2\2\u00e9\u00ec\3\2\2\2\u00ea\u00eb\3\2\2\2"+
		"\u00ea\u00e8\3\2\2\2\u00eb\u00ed\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ed\u00ee"+
		"\5J%\2\u00ee\u00ef\3\2\2\2\u00ef\u00f0\b&\3\2\u00f0M\3\2\2\2\u00f1\u00f2"+
		"\5F#\2\u00f2\u00f3\5Z-\2\u00f3\u00f4\5\\.\2\u00f4O\3\2\2\2\u00f5\u00fe"+
		"\7\62\2\2\u00f6\u00fa\5h\64\2\u00f7\u00f9\5f\63\2\u00f8\u00f7\3\2\2\2"+
		"\u00f9\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\u00fe"+
		"\3\2\2\2\u00fc\u00fa\3\2\2\2\u00fd\u00f5\3\2\2\2\u00fd\u00f6\3\2\2\2\u00fe"+
		"Q\3\2\2\2\u00ff\u0101\5f\63\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2\2\2"+
		"\u0102\u0100\3\2\2\2\u0102\u0103\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0106"+
		"\7\60\2\2\u0105\u0107\5f\63\2\u0106\u0105\3\2\2\2\u0107\u0108\3\2\2\2"+
		"\u0108\u0106\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010b\3\2\2\2\u010a\u010c"+
		"\5d\62\2\u010b\u010a\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u0115\3\2\2\2\u010d"+
		"\u010f\5f\63\2\u010e\u010d\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u010e\3\2"+
		"\2\2\u0110\u0111\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\5d\62\2\u0113"+
		"\u0115\3\2\2\2\u0114\u0100\3\2\2\2\u0114\u010e\3\2\2\2\u0115S\3\2\2\2"+
		"\u0116\u0117\7v\2\2\u0117\u0118\7t\2\2\u0118\u0119\7w\2\2\u0119\u0120"+
		"\7g\2\2\u011a\u011b\7h\2\2\u011b\u011c\7c\2\2\u011c\u011d\7n\2\2\u011d"+
		"\u011e\7u\2\2\u011e\u0120\7g\2\2\u011f\u0116\3\2\2\2\u011f\u011a\3\2\2"+
		"\2\u0120U\3\2\2\2\u0121\u0125\7)\2\2\u0122\u0124\5^/\2\u0123\u0122\3\2"+
		"\2\2\u0124\u0127\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"\u0128\3\2\2\2\u0127\u0125\3\2\2\2\u0128\u0129\7)\2\2\u0129W\3\2\2\2\u012a"+
		"\u012c\t\4\2\2\u012b\u012a\3\2\2\2\u012c\u012d\3\2\2\2\u012d\u012b\3\2"+
		"\2\2\u012d\u012e\3\2\2\2\u012e\u0133\3\2\2\2\u012f\u0132\t\5\2\2\u0130"+
		"\u0132\5f\63\2\u0131\u012f\3\2\2\2\u0131\u0130\3\2\2\2\u0132\u0135\3\2"+
		"\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134Y\3\2\2\2\u0135\u0133"+
		"\3\2\2\2\u0136\u0138\n\6\2\2\u0137\u0136\3\2\2\2\u0138\u0139\3\2\2\2\u0139"+
		"\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a[\3\2\2\2\u013b\u013c\5.\27\2"+
		"\u013c\u013d\3\2\2\2\u013d\u013e\b.\4\2\u013e\u013f\b.\3\2\u013f]\3\2"+
		"\2\2\u0140\u0143\5`\60\2\u0141\u0143\5b\61\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0141\3\2\2\2\u0143_\3\2\2\2\u0144\u0145\n\7\2\2\u0145a\3\2\2\2\u0146"+
		"\u0147\7^\2\2\u0147\u0148\t\b\2\2\u0148c\3\2\2\2\u0149\u014b\t\t\2\2\u014a"+
		"\u014c\t\n\2\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014e\3\2"+
		"\2\2\u014d\u014f\5f\63\2\u014e\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150"+
		"\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151e\3\2\2\2\u0152\u0155\7\62\2\2"+
		"\u0153\u0155\5h\64\2\u0154\u0152\3\2\2\2\u0154\u0153\3\2\2\2\u0155g\3"+
		"\2\2\2\u0156\u0157\4\63;\2\u0157i\3\2\2\2\31\2\3\u00d2\u00dd\u00e2\u00ea"+
		"\u00fa\u00fd\u0102\u0108\u010b\u0110\u0114\u011f\u0125\u012d\u0131\u0133"+
		"\u0139\u0142\u014b\u0150\u0154\5\7\3\2\b\2\2\6\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}