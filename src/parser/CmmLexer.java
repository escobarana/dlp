// Generated from C:/Users/anaes/OneDrive/Escritorio/labDLP/src/parser\Cmm.g4 by ANTLR 4.9.1
package parser;

    import ast.*;
    import ast.definitions.*;
    import ast.expressions.*;
    import ast.statements.*;
    import ast.types.*;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CmmLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, INT_CONSTANT=37, ID=38, 
		REAL_CONSTANT=39, ONELINE_COMMENT=40, MULTIPLELINE_COMMENT=41, CHAR_CONSTANT=42, 
		BLANKS=43;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "LETTER", "DIGIT", "REAL_CONSTANT_FLOAT", 
			"REAL_CONSTANT_MANTISSA", "CHAR_CONSTANT_ASCII", "CHAR_CONSTANT_SPECIAL", 
			"INT_CONSTANT", "ID", "REAL_CONSTANT", "ONELINE_COMMENT", "MULTIPLELINE_COMMENT", 
			"CHAR_CONSTANT", "BLANKS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "';'", "'('", "')'", "'int'", "'double'", "'char'", "'['", 
			"']'", "'struct'", "'{'", "'}'", "'void'", "'main'", "'write'", "'read'", 
			"'return'", "'='", "'if'", "'else'", "'while'", "'.'", "'-'", "'*'", 
			"'/'", "'%'", "'+'", "'>'", "'>='", "'<'", "'<='", "'!='", "'=='", "'!'", 
			"'&&'", "'||'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "INT_CONSTANT", "ID", "REAL_CONSTANT", "ONELINE_COMMENT", "MULTIPLELINE_COMMENT", 
			"CHAR_CONSTANT", "BLANKS"
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


	public CmmLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cmm.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2-\u014d\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\3\3\3\3"+
		"\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34"+
		"\3\34\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\""+
		"\3#\3#\3$\3$\3$\3%\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\7(\u00e4\n(\f(\16(\u00e7"+
		"\13(\3(\3(\3(\7(\u00ec\n(\f(\16(\u00ef\13(\5(\u00f1\n(\3)\3)\5)\u00f5"+
		"\n)\3)\3)\5)\u00f9\n)\3)\3)\3*\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3+\3+"+
		"\5+\u010b\n+\3,\3,\7,\u010f\n,\f,\16,\u0112\13,\3,\5,\u0115\n,\3-\3-\5"+
		"-\u0119\n-\3-\3-\3-\7-\u011e\n-\f-\16-\u0121\13-\3.\3.\5.\u0125\n.\3/"+
		"\3/\3/\3/\7/\u012b\n/\f/\16/\u012e\13/\3/\5/\u0131\n/\3/\3/\3\60\3\60"+
		"\3\60\3\60\7\60\u0139\n\60\f\60\16\60\u013c\13\60\3\60\3\60\3\60\3\60"+
		"\3\60\3\61\3\61\3\61\3\61\3\61\5\61\u0148\n\61\3\62\3\62\3\62\3\62\4\u012c"+
		"\u013a\2\63\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33"+
		"\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67"+
		"\359\36;\37= ?!A\"C#E$G%I&K\2M\2O\2Q\2S\2U\2W\'Y([)]*_+a,c-\3\2\b\4\2"+
		"C\\c|\3\2\62;\4\2GGgg\3\2\63;\3\3\f\f\5\2\13\f\17\17\"\"\2\u0157\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61"+
		"\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2"+
		"\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I"+
		"\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2"+
		"\2\2\2c\3\2\2\2\3e\3\2\2\2\5g\3\2\2\2\7i\3\2\2\2\tk\3\2\2\2\13m\3\2\2"+
		"\2\rq\3\2\2\2\17x\3\2\2\2\21}\3\2\2\2\23\177\3\2\2\2\25\u0081\3\2\2\2"+
		"\27\u0088\3\2\2\2\31\u008a\3\2\2\2\33\u008c\3\2\2\2\35\u0091\3\2\2\2\37"+
		"\u0096\3\2\2\2!\u009c\3\2\2\2#\u00a1\3\2\2\2%\u00a8\3\2\2\2\'\u00aa\3"+
		"\2\2\2)\u00ad\3\2\2\2+\u00b2\3\2\2\2-\u00b8\3\2\2\2/\u00ba\3\2\2\2\61"+
		"\u00bc\3\2\2\2\63\u00be\3\2\2\2\65\u00c0\3\2\2\2\67\u00c2\3\2\2\29\u00c4"+
		"\3\2\2\2;\u00c6\3\2\2\2=\u00c9\3\2\2\2?\u00cb\3\2\2\2A\u00ce\3\2\2\2C"+
		"\u00d1\3\2\2\2E\u00d4\3\2\2\2G\u00d6\3\2\2\2I\u00d9\3\2\2\2K\u00dc\3\2"+
		"\2\2M\u00de\3\2\2\2O\u00f0\3\2\2\2Q\u00f4\3\2\2\2S\u00fc\3\2\2\2U\u010a"+
		"\3\2\2\2W\u0114\3\2\2\2Y\u0118\3\2\2\2[\u0124\3\2\2\2]\u0126\3\2\2\2_"+
		"\u0134\3\2\2\2a\u0147\3\2\2\2c\u0149\3\2\2\2ef\7.\2\2f\4\3\2\2\2gh\7="+
		"\2\2h\6\3\2\2\2ij\7*\2\2j\b\3\2\2\2kl\7+\2\2l\n\3\2\2\2mn\7k\2\2no\7p"+
		"\2\2op\7v\2\2p\f\3\2\2\2qr\7f\2\2rs\7q\2\2st\7w\2\2tu\7d\2\2uv\7n\2\2"+
		"vw\7g\2\2w\16\3\2\2\2xy\7e\2\2yz\7j\2\2z{\7c\2\2{|\7t\2\2|\20\3\2\2\2"+
		"}~\7]\2\2~\22\3\2\2\2\177\u0080\7_\2\2\u0080\24\3\2\2\2\u0081\u0082\7"+
		"u\2\2\u0082\u0083\7v\2\2\u0083\u0084\7t\2\2\u0084\u0085\7w\2\2\u0085\u0086"+
		"\7e\2\2\u0086\u0087\7v\2\2\u0087\26\3\2\2\2\u0088\u0089\7}\2\2\u0089\30"+
		"\3\2\2\2\u008a\u008b\7\177\2\2\u008b\32\3\2\2\2\u008c\u008d\7x\2\2\u008d"+
		"\u008e\7q\2\2\u008e\u008f\7k\2\2\u008f\u0090\7f\2\2\u0090\34\3\2\2\2\u0091"+
		"\u0092\7o\2\2\u0092\u0093\7c\2\2\u0093\u0094\7k\2\2\u0094\u0095\7p\2\2"+
		"\u0095\36\3\2\2\2\u0096\u0097\7y\2\2\u0097\u0098\7t\2\2\u0098\u0099\7"+
		"k\2\2\u0099\u009a\7v\2\2\u009a\u009b\7g\2\2\u009b \3\2\2\2\u009c\u009d"+
		"\7t\2\2\u009d\u009e\7g\2\2\u009e\u009f\7c\2\2\u009f\u00a0\7f\2\2\u00a0"+
		"\"\3\2\2\2\u00a1\u00a2\7t\2\2\u00a2\u00a3\7g\2\2\u00a3\u00a4\7v\2\2\u00a4"+
		"\u00a5\7w\2\2\u00a5\u00a6\7t\2\2\u00a6\u00a7\7p\2\2\u00a7$\3\2\2\2\u00a8"+
		"\u00a9\7?\2\2\u00a9&\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7h\2\2\u00ac"+
		"(\3\2\2\2\u00ad\u00ae\7g\2\2\u00ae\u00af\7n\2\2\u00af\u00b0\7u\2\2\u00b0"+
		"\u00b1\7g\2\2\u00b1*\3\2\2\2\u00b2\u00b3\7y\2\2\u00b3\u00b4\7j\2\2\u00b4"+
		"\u00b5\7k\2\2\u00b5\u00b6\7n\2\2\u00b6\u00b7\7g\2\2\u00b7,\3\2\2\2\u00b8"+
		"\u00b9\7\60\2\2\u00b9.\3\2\2\2\u00ba\u00bb\7/\2\2\u00bb\60\3\2\2\2\u00bc"+
		"\u00bd\7,\2\2\u00bd\62\3\2\2\2\u00be\u00bf\7\61\2\2\u00bf\64\3\2\2\2\u00c0"+
		"\u00c1\7\'\2\2\u00c1\66\3\2\2\2\u00c2\u00c3\7-\2\2\u00c38\3\2\2\2\u00c4"+
		"\u00c5\7@\2\2\u00c5:\3\2\2\2\u00c6\u00c7\7@\2\2\u00c7\u00c8\7?\2\2\u00c8"+
		"<\3\2\2\2\u00c9\u00ca\7>\2\2\u00ca>\3\2\2\2\u00cb\u00cc\7>\2\2\u00cc\u00cd"+
		"\7?\2\2\u00cd@\3\2\2\2\u00ce\u00cf\7#\2\2\u00cf\u00d0\7?\2\2\u00d0B\3"+
		"\2\2\2\u00d1\u00d2\7?\2\2\u00d2\u00d3\7?\2\2\u00d3D\3\2\2\2\u00d4\u00d5"+
		"\7#\2\2\u00d5F\3\2\2\2\u00d6\u00d7\7(\2\2\u00d7\u00d8\7(\2\2\u00d8H\3"+
		"\2\2\2\u00d9\u00da\7~\2\2\u00da\u00db\7~\2\2\u00dbJ\3\2\2\2\u00dc\u00dd"+
		"\t\2\2\2\u00ddL\3\2\2\2\u00de\u00df\t\3\2\2\u00dfN\3\2\2\2\u00e0\u00e1"+
		"\5W,\2\u00e1\u00e5\7\60\2\2\u00e2\u00e4\5M\'\2\u00e3\u00e2\3\2\2\2\u00e4"+
		"\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00f1\3\2"+
		"\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\7\60\2\2\u00e9\u00ed\5W,\2\u00ea"+
		"\u00ec\5M\'\2\u00eb\u00ea\3\2\2\2\u00ec\u00ef\3\2\2\2\u00ed\u00eb\3\2"+
		"\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed\3\2\2\2\u00f0"+
		"\u00e0\3\2\2\2\u00f0\u00e8\3\2\2\2\u00f1P\3\2\2\2\u00f2\u00f5\5O(\2\u00f3"+
		"\u00f5\5W,\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3\3\2\2\2\u00f5\u00f6\3\2\2"+
		"\2\u00f6\u00f8\t\4\2\2\u00f7\u00f9\7/\2\2\u00f8\u00f7\3\2\2\2\u00f8\u00f9"+
		"\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u00fb\5W,\2\u00fbR\3\2\2\2\u00fc\u00fd"+
		"\7)\2\2\u00fd\u00fe\7^\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100\5W,\2\u0100"+
		"\u0101\7)\2\2\u0101T\3\2\2\2\u0102\u0103\7)\2\2\u0103\u0104\7^\2\2\u0104"+
		"\u0105\7p\2\2\u0105\u010b\7)\2\2\u0106\u0107\7)\2\2\u0107\u0108\7^\2\2"+
		"\u0108\u0109\7v\2\2\u0109\u010b\7)\2\2\u010a\u0102\3\2\2\2\u010a\u0106"+
		"\3\2\2\2\u010bV\3\2\2\2\u010c\u0110\t\5\2\2\u010d\u010f\5M\'\2\u010e\u010d"+
		"\3\2\2\2\u010f\u0112\3\2\2\2\u0110\u010e\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0115\3\2\2\2\u0112\u0110\3\2\2\2\u0113\u0115\7\62\2\2\u0114\u010c\3"+
		"\2\2\2\u0114\u0113\3\2\2\2\u0115X\3\2\2\2\u0116\u0119\5K&\2\u0117\u0119"+
		"\7a\2\2\u0118\u0116\3\2\2\2\u0118\u0117\3\2\2\2\u0119\u011f\3\2\2\2\u011a"+
		"\u011e\5K&\2\u011b\u011e\5M\'\2\u011c\u011e\7a\2\2\u011d\u011a\3\2\2\2"+
		"\u011d\u011b\3\2\2\2\u011d\u011c\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d"+
		"\3\2\2\2\u011f\u0120\3\2\2\2\u0120Z\3\2\2\2\u0121\u011f\3\2\2\2\u0122"+
		"\u0125\5O(\2\u0123\u0125\5Q)\2\u0124\u0122\3\2\2\2\u0124\u0123\3\2\2\2"+
		"\u0125\\\3\2\2\2\u0126\u0127\7\61\2\2\u0127\u0128\7\61\2\2\u0128\u012c"+
		"\3\2\2\2\u0129\u012b\13\2\2\2\u012a\u0129\3\2\2\2\u012b\u012e\3\2\2\2"+
		"\u012c\u012d\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u0130\3\2\2\2\u012e\u012c"+
		"\3\2\2\2\u012f\u0131\t\6\2\2\u0130\u012f\3\2\2\2\u0131\u0132\3\2\2\2\u0132"+
		"\u0133\b/\2\2\u0133^\3\2\2\2\u0134\u0135\7\61\2\2\u0135\u0136\7,\2\2\u0136"+
		"\u013a\3\2\2\2\u0137\u0139\13\2\2\2\u0138\u0137\3\2\2\2\u0139\u013c\3"+
		"\2\2\2\u013a\u013b\3\2\2\2\u013a\u0138\3\2\2\2\u013b\u013d\3\2\2\2\u013c"+
		"\u013a\3\2\2\2\u013d\u013e\7,\2\2\u013e\u013f\7\61\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0141\b\60\2\2\u0141`\3\2\2\2\u0142\u0143\7)\2\2\u0143\u0144"+
		"\13\2\2\2\u0144\u0148\7)\2\2\u0145\u0148\5S*\2\u0146\u0148\5U+\2\u0147"+
		"\u0142\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0146\3\2\2\2\u0148b\3\2\2\2"+
		"\u0149\u014a\t\7\2\2\u014a\u014b\3\2\2\2\u014b\u014c\b\62\2\2\u014cd\3"+
		"\2\2\2\23\2\u00e5\u00ed\u00f0\u00f4\u00f8\u010a\u0110\u0114\u0118\u011d"+
		"\u011f\u0124\u012c\u0130\u013a\u0147\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}