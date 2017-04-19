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
		VERT_LINE=41, BRACKET_LEFT=42, BRACKET_RIGTH=43, AMP=44, LlBegin=45, WS=46, 
		NL=47, COMMENT=48, LlLiteral=49, IntLiteral=50, HexLiteral=51, FloatLiteral=52, 
		BooleanLiteral=53, StringLiteral=54, Id=55, MatchId=56, IrInline=57, LlEnd=58;
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
		"BRACKET_RIGTH", "AMP", "LlBegin", "WS", "NL", "COMMENT", "LlLiteral", 
		"IntLiteral", "HexLiteral", "FloatLiteral", "BooleanLiteral", "StringLiteral", 
		"Id", "MatchId", "IrInline", "LlEnd", "StringElement", "Char", "CharEscapeSeq", 
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
		"BRACKET_LEFT", "BRACKET_RIGTH", "AMP", "LlBegin", "WS", "NL", "COMMENT", 
		"LlLiteral", "IntLiteral", "HexLiteral", "FloatLiteral", "BooleanLiteral", 
		"StringLiteral", "Id", "MatchId", "IrInline", "LlEnd"
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
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u01ab\b\1\b\1\4"+
		"\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n"+
		"\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t"+
		"=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3"+
		"\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\16"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\3\23"+
		"\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27"+
		"\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3\37\3 \3 \3 \3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3"+
		"$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3"+
		"(\3(\3(\3)\3)\3*\3*\3+\3+\3,\3,\3-\3-\3.\3.\3.\3.\3.\3.\7.\u0115\n.\f"+
		".\16.\u0118\13.\3.\3.\3.\3.\3/\6/\u011f\n/\r/\16/\u0120\3/\3/\3\60\5\60"+
		"\u0126\n\60\3\60\3\60\3\61\3\61\7\61\u012c\n\61\f\61\16\61\u012f\13\61"+
		"\3\61\3\61\3\62\3\62\3\62\3\62\3\63\5\63\u0138\n\63\3\63\3\63\3\63\7\63"+
		"\u013d\n\63\f\63\16\63\u0140\13\63\5\63\u0142\n\63\3\64\3\64\3\64\6\64"+
		"\u0147\n\64\r\64\16\64\u0148\3\65\5\65\u014c\n\65\3\65\6\65\u014f\n\65"+
		"\r\65\16\65\u0150\3\65\3\65\6\65\u0155\n\65\r\65\16\65\u0156\3\65\5\65"+
		"\u015a\n\65\3\65\6\65\u015d\n\65\r\65\16\65\u015e\3\65\3\65\5\65\u0163"+
		"\n\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u016e\n\66\3\67"+
		"\3\67\7\67\u0172\n\67\f\67\16\67\u0175\13\67\3\67\3\67\38\68\u017a\n8"+
		"\r8\168\u017b\38\38\78\u0180\n8\f8\168\u0183\138\39\39\39\3:\6:\u0189"+
		"\n:\r:\16:\u018a\3;\3;\3;\3;\3<\3<\3<\5<\u0194\n<\3=\3=\3>\3>\3>\3?\3"+
		"?\5?\u019d\n?\3?\6?\u01a0\n?\r?\16?\u01a1\3@\3@\5@\u01a6\n@\3A\3A\3B\3"+
		"B\2\2C\4\3\6\4\b\5\n\6\f\7\16\b\20\t\22\n\24\13\26\f\30\r\32\16\34\17"+
		"\36\20 \21\"\22$\23&\24(\25*\26,\27.\30\60\31\62\32\64\33\66\348\35:\36"+
		"<\37> @!B\"D#F$H%J&L\'N(P)R*T+V,X-Z.\\/^\60`\61b\62d\63f\64h\65j\66l\67"+
		"n8p9r:t;v<x\2z\2|\2~\2\u0080\2\u0082\2\u0084\2\4\2\3\16\5\2\13\f\17\17"+
		"\"\"\4\2\13\13\"\"\4\2\f\f\17\17\4\2ZZzz\4\2C\\c|\5\2C\\aac|\4\2}}\177"+
		"\177\5\2\f\f))^^\t\2))^^ddhhppttvv\4\2GGgg\4\2--//\5\2\62;CHch\2\u01bb"+
		"\2\4\3\2\2\2\2\6\3\2\2\2\2\b\3\2\2\2\2\n\3\2\2\2\2\f\3\2\2\2\2\16\3\2"+
		"\2\2\2\20\3\2\2\2\2\22\3\2\2\2\2\24\3\2\2\2\2\26\3\2\2\2\2\30\3\2\2\2"+
		"\2\32\3\2\2\2\2\34\3\2\2\2\2\36\3\2\2\2\2 \3\2\2\2\2\"\3\2\2\2\2$\3\2"+
		"\2\2\2&\3\2\2\2\2(\3\2\2\2\2*\3\2\2\2\2,\3\2\2\2\2.\3\2\2\2\2\60\3\2\2"+
		"\2\2\62\3\2\2\2\2\64\3\2\2\2\2\66\3\2\2\2\28\3\2\2\2\2:\3\2\2\2\2<\3\2"+
		"\2\2\2>\3\2\2\2\2@\3\2\2\2\2B\3\2\2\2\2D\3\2\2\2\2F\3\2\2\2\2H\3\2\2\2"+
		"\2J\3\2\2\2\2L\3\2\2\2\2N\3\2\2\2\2P\3\2\2\2\2R\3\2\2\2\2T\3\2\2\2\2V"+
		"\3\2\2\2\2X\3\2\2\2\2Z\3\2\2\2\2\\\3\2\2\2\2^\3\2\2\2\2`\3\2\2\2\2b\3"+
		"\2\2\2\2d\3\2\2\2\2f\3\2\2\2\2h\3\2\2\2\2j\3\2\2\2\2l\3\2\2\2\2n\3\2\2"+
		"\2\2p\3\2\2\2\2r\3\2\2\2\3t\3\2\2\2\3v\3\2\2\2\4\u0086\3\2\2\2\6\u0088"+
		"\3\2\2\2\b\u008a\3\2\2\2\n\u008c\3\2\2\2\f\u008e\3\2\2\2\16\u0090\3\2"+
		"\2\2\20\u0092\3\2\2\2\22\u0094\3\2\2\2\24\u0096\3\2\2\2\26\u0098\3\2\2"+
		"\2\30\u009a\3\2\2\2\32\u009d\3\2\2\2\34\u009f\3\2\2\2\36\u00a2\3\2\2\2"+
		" \u00a4\3\2\2\2\"\u00a7\3\2\2\2$\u00aa\3\2\2\2&\u00ac\3\2\2\2(\u00af\3"+
		"\2\2\2*\u00b4\3\2\2\2,\u00b9\3\2\2\2.\u00bb\3\2\2\2\60\u00be\3\2\2\2\62"+
		"\u00c0\3\2\2\2\64\u00c3\3\2\2\2\66\u00c6\3\2\2\28\u00cc\3\2\2\2:\u00d0"+
		"\3\2\2\2<\u00d4\3\2\2\2>\u00d6\3\2\2\2@\u00d9\3\2\2\2B\u00de\3\2\2\2D"+
		"\u00e0\3\2\2\2F\u00e5\3\2\2\2H\u00eb\3\2\2\2J\u00ef\3\2\2\2L\u00f6\3\2"+
		"\2\2N\u00fb\3\2\2\2P\u0101\3\2\2\2R\u0104\3\2\2\2T\u0106\3\2\2\2V\u0108"+
		"\3\2\2\2X\u010a\3\2\2\2Z\u010c\3\2\2\2\\\u010e\3\2\2\2^\u011e\3\2\2\2"+
		"`\u0125\3\2\2\2b\u0129\3\2\2\2d\u0132\3\2\2\2f\u0141\3\2\2\2h\u0143\3"+
		"\2\2\2j\u0162\3\2\2\2l\u016d\3\2\2\2n\u016f\3\2\2\2p\u0179\3\2\2\2r\u0184"+
		"\3\2\2\2t\u0188\3\2\2\2v\u018c\3\2\2\2x\u0193\3\2\2\2z\u0195\3\2\2\2|"+
		"\u0197\3\2\2\2~\u019a\3\2\2\2\u0080\u01a5\3\2\2\2\u0082\u01a7\3\2\2\2"+
		"\u0084\u01a9\3\2\2\2\u0086\u0087\7/\2\2\u0087\5\3\2\2\2\u0088\u0089\7"+
		"-\2\2\u0089\7\3\2\2\2\u008a\u008b\7,\2\2\u008b\t\3\2\2\2\u008c\u008d\7"+
		"\61\2\2\u008d\13\3\2\2\2\u008e\u008f\7#\2\2\u008f\r\3\2\2\2\u0090\u0091"+
		"\7\60\2\2\u0091\17\3\2\2\2\u0092\u0093\7+\2\2\u0093\21\3\2\2\2\u0094\u0095"+
		"\7*\2\2\u0095\23\3\2\2\2\u0096\u0097\7.\2\2\u0097\25\3\2\2\2\u0098\u0099"+
		"\7@\2\2\u0099\27\3\2\2\2\u009a\u009b\7@\2\2\u009b\u009c\7?\2\2\u009c\31"+
		"\3\2\2\2\u009d\u009e\7>\2\2\u009e\33\3\2\2\2\u009f\u00a0\7>\2\2\u00a0"+
		"\u00a1\7?\2\2\u00a1\35\3\2\2\2\u00a2\u00a3\7?\2\2\u00a3\37\3\2\2\2\u00a4"+
		"\u00a5\7?\2\2\u00a5\u00a6\7?\2\2\u00a6!\3\2\2\2\u00a7\u00a8\7#\2\2\u00a8"+
		"\u00a9\7?\2\2\u00a9#\3\2\2\2\u00aa\u00ab\7=\2\2\u00ab%\3\2\2\2\u00ac\u00ad"+
		"\7k\2\2\u00ad\u00ae\7h\2\2\u00ae\'\3\2\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1"+
		"\7j\2\2\u00b1\u00b2\7g\2\2\u00b2\u00b3\7p\2\2\u00b3)\3\2\2\2\u00b4\u00b5"+
		"\7g\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7g\2\2\u00b8"+
		"+\3\2\2\2\u00b9\u00ba\7}\2\2\u00ba-\3\2\2\2\u00bb\u00bc\7&\2\2\u00bc\u00bd"+
		"\7}\2\2\u00bd/\3\2\2\2\u00be\u00bf\7\177\2\2\u00bf\61\3\2\2\2\u00c0\u00c1"+
		"\7~\2\2\u00c1\u00c2\7~\2\2\u00c2\63\3\2\2\2\u00c3\u00c4\7(\2\2\u00c4\u00c5"+
		"\7(\2\2\u00c5\65\3\2\2\2\u00c6\u00c7\7y\2\2\u00c7\u00c8\7j\2\2\u00c8\u00c9"+
		"\7k\2\2\u00c9\u00ca\7n\2\2\u00ca\u00cb\7g\2\2\u00cb\67\3\2\2\2\u00cc\u00cd"+
		"\7x\2\2\u00cd\u00ce\7c\2\2\u00ce\u00cf\7n\2\2\u00cf9\3\2\2\2\u00d0\u00d1"+
		"\7x\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7t\2\2\u00d3;\3\2\2\2\u00d4\u00d5"+
		"\7<\2\2\u00d5=\3\2\2\2\u00d6\u00d7\7/\2\2\u00d7\u00d8\7@\2\2\u00d8?\3"+
		"\2\2\2\u00d9\u00da\7v\2\2\u00da\u00db\7{\2\2\u00db\u00dc\7r\2\2\u00dc"+
		"\u00dd\7g\2\2\u00ddA\3\2\2\2\u00de\u00df\7^\2\2\u00dfC\3\2\2\2\u00e0\u00e1"+
		"\7u\2\2\u00e1\u00e2\7g\2\2\u00e2\u00e3\7n\2\2\u00e3\u00e4\7h\2\2\u00e4"+
		"E\3\2\2\2\u00e5\u00e6\7&\2\2\u00e6\u00e7\7u\2\2\u00e7\u00e8\7g\2\2\u00e8"+
		"\u00e9\7n\2\2\u00e9\u00ea\7h\2\2\u00eaG\3\2\2\2\u00eb\u00ec\7f\2\2\u00ec"+
		"\u00ed\7g\2\2\u00ed\u00ee\7h\2\2\u00eeI\3\2\2\2\u00ef\u00f0\7k\2\2\u00f0"+
		"\u00f1\7o\2\2\u00f1\u00f2\7r\2\2\u00f2\u00f3\7q\2\2\u00f3\u00f4\7t\2\2"+
		"\u00f4\u00f5\7v\2\2\u00f5K\3\2\2\2\u00f6\u00f7\7y\2\2\u00f7\u00f8\7k\2"+
		"\2\u00f8\u00f9\7v\2\2\u00f9\u00fa\7j\2\2\u00faM\3\2\2\2\u00fb\u00fc\7"+
		"o\2\2\u00fc\u00fd\7c\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7e\2\2\u00ff\u0100"+
		"\7j\2\2\u0100O\3\2\2\2\u0101\u0102\7q\2\2\u0102\u0103\7h\2\2\u0103Q\3"+
		"\2\2\2\u0104\u0105\7a\2\2\u0105S\3\2\2\2\u0106\u0107\7~\2\2\u0107U\3\2"+
		"\2\2\u0108\u0109\7]\2\2\u0109W\3\2\2\2\u010a\u010b\7_\2\2\u010bY\3\2\2"+
		"\2\u010c\u010d\7(\2\2\u010d[\3\2\2\2\u010e\u010f\7n\2\2\u010f\u0110\7"+
		"n\2\2\u0110\u0111\7x\2\2\u0111\u0112\7o\2\2\u0112\u0116\3\2\2\2\u0113"+
		"\u0115\t\2\2\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116\u0114\3\2"+
		"\2\2\u0116\u0117\3\2\2\2\u0117\u0119\3\2\2\2\u0118\u0116\3\2\2\2\u0119"+
		"\u011a\7}\2\2\u011a\u011b\3\2\2\2\u011b\u011c\b.\2\2\u011c]\3\2\2\2\u011d"+
		"\u011f\t\3\2\2\u011e\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120\u011e\3\2"+
		"\2\2\u0120\u0121\3\2\2\2\u0121\u0122\3\2\2\2\u0122\u0123\b/\3\2\u0123"+
		"_\3\2\2\2\u0124\u0126\7\17\2\2\u0125\u0124\3\2\2\2\u0125\u0126\3\2\2\2"+
		"\u0126\u0127\3\2\2\2\u0127\u0128\7\f\2\2\u0128a\3\2\2\2\u0129\u012d\7"+
		"%\2\2\u012a\u012c\n\4\2\2\u012b\u012a\3\2\2\2\u012c\u012f\3\2\2\2\u012d"+
		"\u012b\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u0130\3\2\2\2\u012f\u012d\3\2"+
		"\2\2\u0130\u0131\b\61\3\2\u0131c\3\2\2\2\u0132\u0133\5\\.\2\u0133\u0134"+
		"\5t:\2\u0134\u0135\5v;\2\u0135e\3\2\2\2\u0136\u0138\7/\2\2\u0137\u0136"+
		"\3\2\2\2\u0137\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0142\7\62\2\2"+
		"\u013a\u013e\5\u0082A\2\u013b\u013d\5\u0080@\2\u013c\u013b\3\2\2\2\u013d"+
		"\u0140\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0142\3\2"+
		"\2\2\u0140\u013e\3\2\2\2\u0141\u0137\3\2\2\2\u0141\u013a\3\2\2\2\u0142"+
		"g\3\2\2\2\u0143\u0144\7\62\2\2\u0144\u0146\t\5\2\2\u0145\u0147\5\u0084"+
		"B\2\u0146\u0145\3\2\2\2\u0147\u0148\3\2\2\2\u0148\u0146\3\2\2\2\u0148"+
		"\u0149\3\2\2\2\u0149i\3\2\2\2\u014a\u014c\7/\2\2\u014b\u014a\3\2\2\2\u014b"+
		"\u014c\3\2\2\2\u014c\u014e\3\2\2\2\u014d\u014f\5\u0080@\2\u014e\u014d"+
		"\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u014e\3\2\2\2\u0150\u0151\3\2\2\2\u0151"+
		"\u0152\3\2\2\2\u0152\u0154\7\60\2\2\u0153\u0155\5\u0080@\2\u0154\u0153"+
		"\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0154\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0159\3\2\2\2\u0158\u015a\5~?\2\u0159\u0158\3\2\2\2\u0159\u015a\3\2\2"+
		"\2\u015a\u0163\3\2\2\2\u015b\u015d\5\u0080@\2\u015c\u015b\3\2\2\2\u015d"+
		"\u015e\3\2\2\2\u015e\u015c\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\3\2"+
		"\2\2\u0160\u0161\5~?\2\u0161\u0163\3\2\2\2\u0162\u014b\3\2\2\2\u0162\u015c"+
		"\3\2\2\2\u0163k\3\2\2\2\u0164\u0165\7v\2\2\u0165\u0166\7t\2\2\u0166\u0167"+
		"\7w\2\2\u0167\u016e\7g\2\2\u0168\u0169\7h\2\2\u0169\u016a\7c\2\2\u016a"+
		"\u016b\7n\2\2\u016b\u016c\7u\2\2\u016c\u016e\7g\2\2\u016d\u0164\3\2\2"+
		"\2\u016d\u0168\3\2\2\2\u016em\3\2\2\2\u016f\u0173\7)\2\2\u0170\u0172\5"+
		"x<\2\u0171\u0170\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2\2\2\u0173"+
		"\u0174\3\2\2\2\u0174\u0176\3\2\2\2\u0175\u0173\3\2\2\2\u0176\u0177\7)"+
		"\2\2\u0177o\3\2\2\2\u0178\u017a\t\6\2\2\u0179\u0178\3\2\2\2\u017a\u017b"+
		"\3\2\2\2\u017b\u0179\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u0181\3\2\2\2\u017d"+
		"\u0180\t\7\2\2\u017e\u0180\5\u0080@\2\u017f\u017d\3\2\2\2\u017f\u017e"+
		"\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182"+
		"q\3\2\2\2\u0183\u0181\3\2\2\2\u0184\u0185\7&\2\2\u0185\u0186\5p8\2\u0186"+
		"s\3\2\2\2\u0187\u0189\n\b\2\2\u0188\u0187\3\2\2\2\u0189\u018a\3\2\2\2"+
		"\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018bu\3\2\2\2\u018c\u018d\5"+
		"\60\30\2\u018d\u018e\3\2\2\2\u018e\u018f\b;\4\2\u018fw\3\2\2\2\u0190\u0194"+
		"\5z=\2\u0191\u0194\5|>\2\u0192\u0194\5`\60\2\u0193\u0190\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0193\u0192\3\2\2\2\u0194y\3\2\2\2\u0195\u0196\n\t\2\2"+
		"\u0196{\3\2\2\2\u0197\u0198\7^\2\2\u0198\u0199\t\n\2\2\u0199}\3\2\2\2"+
		"\u019a\u019c\t\13\2\2\u019b\u019d\t\f\2\2\u019c\u019b\3\2\2\2\u019c\u019d"+
		"\3\2\2\2\u019d\u019f\3\2\2\2\u019e\u01a0\5\u0080@\2\u019f\u019e\3\2\2"+
		"\2\u01a0\u01a1\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1\u01a2\3\2\2\2\u01a2\177"+
		"\3\2\2\2\u01a3\u01a6\7\62\2\2\u01a4\u01a6\5\u0082A\2\u01a5\u01a3\3\2\2"+
		"\2\u01a5\u01a4\3\2\2\2\u01a6\u0081\3\2\2\2\u01a7\u01a8\4\63;\2\u01a8\u0083"+
		"\3\2\2\2\u01a9\u01aa\t\r\2\2\u01aa\u0085\3\2\2\2\34\2\3\u0116\u0120\u0125"+
		"\u012d\u0137\u013e\u0141\u0148\u014b\u0150\u0156\u0159\u015e\u0162\u016d"+
		"\u0173\u017b\u017f\u0181\u018a\u0193\u019c\u01a1\u01a5\5\7\3\2\b\2\2\6"+
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