// Generated from FeatherweightJavaScript.g4 by ANTLR 4.4
 package edu.sjsu.fwjs.parser; 
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FeatherweightJavaScriptLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__5=1, T__4=2, T__3=3, T__2=4, T__1=5, T__0=6, IF=7, ELSE=8, WHILE=9, 
		FUNCTION=10, VAR=11, PRINT=12, INT=13, BOOL=14, NULL=15, MUL=16, DIV=17, 
		ADD=18, SUB=19, MOD=20, GREATER=21, LESS=22, GREATEREQ=23, LESSEQ=24, 
		EQUAL=25, SEPARATOR=26, ID=27, NEWLINE=28, BLOCK_COMMENT=29, LINE_COMMENT=30, 
		WS=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'"
	};
	public static final String[] ruleNames = {
		"T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "IF", "ELSE", "WHILE", 
		"FUNCTION", "VAR", "PRINT", "INT", "BOOL", "NULL", "MUL", "DIV", "ADD", 
		"SUB", "MOD", "GREATER", "LESS", "GREATEREQ", "LESSEQ", "EQUAL", "SEPARATOR", 
		"ID", "NEWLINE", "BLOCK_COMMENT", "LINE_COMMENT", "WS"
	};


	public FeatherweightJavaScriptLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FeatherweightJavaScript.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2!\u00cf\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\7\16q\n\16\f\16"+
		"\16\16t\13\16\3\16\5\16w\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u0082\n\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23"+
		"\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\33\3\33\3\34\3\34\7\34\u00a4\n\34\f\34\16\34\u00a7"+
		"\13\34\3\35\5\35\u00aa\n\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\7"+
		"\36\u00b4\n\36\f\36\16\36\u00b7\13\36\3\36\3\36\3\36\3\36\3\36\3\37\3"+
		"\37\3\37\3\37\7\37\u00c2\n\37\f\37\16\37\u00c5\13\37\3\37\3\37\3 \6 \u00ca"+
		"\n \r \16 \u00cb\3 \3 \3\u00b5\2!\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!\3\2\b\3\2\63;\3\2\62;\13\2\"\"))"+
		"\60\60CC\\\\aacc||~~\f\2\"\"))\60\60\62;CC\\\\aacc||~~\4\2\f\f\17\17\4"+
		"\2\13\13\"\"\u00d6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5C\3\2\2\2\7E"+
		"\3\2\2\2\tG\3\2\2\2\13I\3\2\2\2\rK\3\2\2\2\17M\3\2\2\2\21P\3\2\2\2\23"+
		"U\3\2\2\2\25[\3\2\2\2\27d\3\2\2\2\31h\3\2\2\2\33v\3\2\2\2\35\u0081\3\2"+
		"\2\2\37\u0083\3\2\2\2!\u0088\3\2\2\2#\u008a\3\2\2\2%\u008c\3\2\2\2\'\u008e"+
		"\3\2\2\2)\u0090\3\2\2\2+\u0092\3\2\2\2-\u0094\3\2\2\2/\u0096\3\2\2\2\61"+
		"\u0099\3\2\2\2\63\u009c\3\2\2\2\65\u009f\3\2\2\2\67\u00a1\3\2\2\29\u00a9"+
		"\3\2\2\2;\u00af\3\2\2\2=\u00bd\3\2\2\2?\u00c9\3\2\2\2AB\7*\2\2B\4\3\2"+
		"\2\2CD\7+\2\2D\6\3\2\2\2EF\7}\2\2F\b\3\2\2\2GH\7.\2\2H\n\3\2\2\2IJ\7\177"+
		"\2\2J\f\3\2\2\2KL\7?\2\2L\16\3\2\2\2MN\7k\2\2NO\7h\2\2O\20\3\2\2\2PQ\7"+
		"g\2\2QR\7n\2\2RS\7u\2\2ST\7g\2\2T\22\3\2\2\2UV\7y\2\2VW\7j\2\2WX\7k\2"+
		"\2XY\7n\2\2YZ\7g\2\2Z\24\3\2\2\2[\\\7h\2\2\\]\7w\2\2]^\7p\2\2^_\7e\2\2"+
		"_`\7v\2\2`a\7k\2\2ab\7q\2\2bc\7p\2\2c\26\3\2\2\2de\7x\2\2ef\7c\2\2fg\7"+
		"t\2\2g\30\3\2\2\2hi\7r\2\2ij\7t\2\2jk\7k\2\2kl\7p\2\2lm\7v\2\2m\32\3\2"+
		"\2\2nr\t\2\2\2oq\t\3\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2sw\3\2"+
		"\2\2tr\3\2\2\2uw\7\62\2\2vn\3\2\2\2vu\3\2\2\2w\34\3\2\2\2xy\7v\2\2yz\7"+
		"t\2\2z{\7w\2\2{\u0082\7g\2\2|}\7h\2\2}~\7c\2\2~\177\7n\2\2\177\u0080\7"+
		"u\2\2\u0080\u0082\7g\2\2\u0081x\3\2\2\2\u0081|\3\2\2\2\u0082\36\3\2\2"+
		"\2\u0083\u0084\7p\2\2\u0084\u0085\7w\2\2\u0085\u0086\7n\2\2\u0086\u0087"+
		"\7n\2\2\u0087 \3\2\2\2\u0088\u0089\7,\2\2\u0089\"\3\2\2\2\u008a\u008b"+
		"\7\61\2\2\u008b$\3\2\2\2\u008c\u008d\7-\2\2\u008d&\3\2\2\2\u008e\u008f"+
		"\7/\2\2\u008f(\3\2\2\2\u0090\u0091\7\'\2\2\u0091*\3\2\2\2\u0092\u0093"+
		"\7@\2\2\u0093,\3\2\2\2\u0094\u0095\7>\2\2\u0095.\3\2\2\2\u0096\u0097\7"+
		"@\2\2\u0097\u0098\7?\2\2\u0098\60\3\2\2\2\u0099\u009a\7>\2\2\u009a\u009b"+
		"\7?\2\2\u009b\62\3\2\2\2\u009c\u009d\7?\2\2\u009d\u009e\7?\2\2\u009e\64"+
		"\3\2\2\2\u009f\u00a0\7=\2\2\u00a0\66\3\2\2\2\u00a1\u00a5\t\4\2\2\u00a2"+
		"\u00a4\t\5\2\2\u00a3\u00a2\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a68\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8\u00aa"+
		"\7\17\2\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2"+
		"\u00ab\u00ac\7\f\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\b\35\2\2\u00ae:\3"+
		"\2\2\2\u00af\u00b0\7\61\2\2\u00b0\u00b1\7,\2\2\u00b1\u00b5\3\2\2\2\u00b2"+
		"\u00b4\13\2\2\2\u00b3\u00b2\3\2\2\2\u00b4\u00b7\3\2\2\2\u00b5\u00b6\3"+
		"\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b8\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b8"+
		"\u00b9\7,\2\2\u00b9\u00ba\7\61\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\b\36"+
		"\2\2\u00bc<\3\2\2\2\u00bd\u00be\7\61\2\2\u00be\u00bf\7\61\2\2\u00bf\u00c3"+
		"\3\2\2\2\u00c0\u00c2\n\6\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3"+
		"\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5\u00c3\3\2"+
		"\2\2\u00c6\u00c7\b\37\2\2\u00c7>\3\2\2\2\u00c8\u00ca\t\7\2\2\u00c9\u00c8"+
		"\3\2\2\2\u00ca\u00cb\3\2\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc"+
		"\u00cd\3\2\2\2\u00cd\u00ce\b \2\2\u00ce@\3\2\2\2\13\2rv\u0081\u00a5\u00a9"+
		"\u00b5\u00c3\u00cb\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}