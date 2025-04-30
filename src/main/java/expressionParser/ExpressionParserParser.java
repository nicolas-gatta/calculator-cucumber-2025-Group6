// Generated from C:/Users/Utilisateur/Desktop/Projet/calculator-cucumber-2025-Group6/src/main/java/expressionParser/ExpressionParser.g4 by ANTLR 4.13.2
package expressionParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class ExpressionParserParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, INT=19, REAL=20, RATIONAL=21, VARIABLE=22, WS=23;
	public static final int
		RULE_operator = 0, RULE_number = 1, RULE_imaginary = 2, RULE_complex = 3, 
		RULE_matrix = 4, RULE_row = 5, RULE_matrixOperator = 6, RULE_matrixFunction = 7, 
		RULE_variableNumber = 8, RULE_equation = 9, RULE_linearEquation = 10, 
		RULE_expr = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"operator", "number", "imaginary", "complex", "matrix", "row", "matrixOperator", 
			"matrixFunction", "variableNumber", "equation", "linearEquation", "expr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'+'", "'-'", "'*'", "'/'", "'i'", "'('", "')'", "'['", "','", 
			"']'", "'^T'", "'T^'", "'^I'", "'I^'", "'^-1'", "'-1^'", "'='", "'solve'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "INT", "REAL", "RATIONAL", 
			"VARIABLE", "WS"
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

	@Override
	public String getGrammarFileName() { return "ExpressionParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExpressionParserParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OperatorContext extends ParserRuleContext {
		public OperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterOperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitOperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitOperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperatorContext operator() throws RecognitionException {
		OperatorContext _localctx = new OperatorContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_operator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 30L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class NumberContext extends ParserRuleContext {
		public TerminalNode RATIONAL() { return getToken(ExpressionParserParser.RATIONAL, 0); }
		public TerminalNode REAL() { return getToken(ExpressionParserParser.REAL, 0); }
		public TerminalNode INT() { return getToken(ExpressionParserParser.INT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 3670016L) != 0)) ) {
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

	@SuppressWarnings("CheckReturnValue")
	public static class ImaginaryContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ImaginaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_imaginary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterImaginary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitImaginary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitImaginary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ImaginaryContext imaginary() throws RecognitionException {
		ImaginaryContext _localctx = new ImaginaryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_imaginary);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			number();
			setState(29);
			match(T__4);
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

	@SuppressWarnings("CheckReturnValue")
	public static class ComplexContext extends ParserRuleContext {
		public Token op;
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ImaginaryContext imaginary() {
			return getRuleContext(ImaginaryContext.class,0);
		}
		public ComplexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_complex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterComplex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitComplex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitComplex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComplexContext complex() throws RecognitionException {
		ComplexContext _localctx = new ComplexContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_complex);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(31);
				match(T__5);
				}
			}

			setState(34);
			number();
			setState(35);
			((ComplexContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==T__0 || _la==T__1) ) {
				((ComplexContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(36);
			imaginary();
			setState(38);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(37);
				match(T__6);
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

	@SuppressWarnings("CheckReturnValue")
	public static class MatrixContext extends ParserRuleContext {
		public List<RowContext> row() {
			return getRuleContexts(RowContext.class);
		}
		public RowContext row(int i) {
			return getRuleContext(RowContext.class,i);
		}
		public MatrixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matrix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterMatrix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitMatrix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitMatrix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatrixContext matrix() throws RecognitionException {
		MatrixContext _localctx = new MatrixContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_matrix);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__7);
			setState(41);
			row();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(42);
				match(T__8);
				setState(43);
				row();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(T__9);
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

	@SuppressWarnings("CheckReturnValue")
	public static class RowContext extends ParserRuleContext {
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public RowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_row; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterRow(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitRow(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitRow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RowContext row() throws RecognitionException {
		RowContext _localctx = new RowContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_row);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(51);
			match(T__7);
			setState(52);
			number();
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(53);
				match(T__8);
				setState(54);
				number();
				}
				}
				setState(59);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(60);
			match(T__9);
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

	@SuppressWarnings("CheckReturnValue")
	public static class MatrixOperatorContext extends ParserRuleContext {
		public MatrixOperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matrixOperator; }
	 
		public MatrixOperatorContext() { }
		public void copyFrom(MatrixOperatorContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InvertedContext extends MatrixOperatorContext {
		public InvertedContext(MatrixOperatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInverted(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInverted(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInverted(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class TransposeContext extends MatrixOperatorContext {
		public TransposeContext(MatrixOperatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterTranspose(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitTranspose(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitTranspose(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IdentityContext extends MatrixOperatorContext {
		public IdentityContext(MatrixOperatorContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterIdentity(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitIdentity(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitIdentity(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatrixOperatorContext matrixOperator() throws RecognitionException {
		MatrixOperatorContext _localctx = new MatrixOperatorContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_matrixOperator);
		int _la;
		try {
			setState(65);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
			case T__11:
				_localctx = new TransposeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(62);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__12:
			case T__13:
				_localctx = new IdentityContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__13) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case T__14:
			case T__15:
				_localctx = new InvertedContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(64);
				_la = _input.LA(1);
				if ( !(_la==T__14 || _la==T__15) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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

	@SuppressWarnings("CheckReturnValue")
	public static class MatrixFunctionContext extends ParserRuleContext {
		public MatrixFunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matrixFunction; }
	 
		public MatrixFunctionContext() { }
		public void copyFrom(MatrixFunctionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatrixPrefixContext extends MatrixFunctionContext {
		public MatrixOperatorContext matrixOperator() {
			return getRuleContext(MatrixOperatorContext.class,0);
		}
		public MatrixContext matrix() {
			return getRuleContext(MatrixContext.class,0);
		}
		public MatrixPrefixContext(MatrixFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterMatrixPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitMatrixPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitMatrixPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatrixPostfixContext extends MatrixFunctionContext {
		public MatrixContext matrix() {
			return getRuleContext(MatrixContext.class,0);
		}
		public MatrixOperatorContext matrixOperator() {
			return getRuleContext(MatrixOperatorContext.class,0);
		}
		public MatrixPostfixContext(MatrixFunctionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterMatrixPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitMatrixPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitMatrixPostfix(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatrixFunctionContext matrixFunction() throws RecognitionException {
		MatrixFunctionContext _localctx = new MatrixFunctionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_matrixFunction);
		try {
			setState(77);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				_localctx = new MatrixPostfixContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(67);
				match(T__5);
				setState(68);
				matrix();
				setState(69);
				match(T__6);
				setState(70);
				matrixOperator();
				}
				break;
			case T__10:
			case T__11:
			case T__12:
			case T__13:
			case T__14:
			case T__15:
				_localctx = new MatrixPrefixContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				matrixOperator();
				setState(73);
				match(T__5);
				setState(74);
				matrix();
				setState(75);
				match(T__6);
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

	@SuppressWarnings("CheckReturnValue")
	public static class VariableNumberContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(ExpressionParserParser.VARIABLE, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public VariableNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterVariableNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitVariableNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitVariableNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableNumberContext variableNumber() throws RecognitionException {
		VariableNumberContext _localctx = new VariableNumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variableNumber);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3670016L) != 0)) {
				{
				setState(79);
				number();
				}
			}

			setState(82);
			match(VARIABLE);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EquationContext extends ParserRuleContext {
		public Token op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public EquationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitEquation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitEquation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationContext equation() throws RecognitionException {
		EquationContext _localctx = new EquationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_equation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			expr(0);
			setState(85);
			((EquationContext)_localctx).op = match(T__16);
			setState(86);
			expr(0);
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

	@SuppressWarnings("CheckReturnValue")
	public static class LinearEquationContext extends ParserRuleContext {
		public List<EquationContext> equation() {
			return getRuleContexts(EquationContext.class);
		}
		public EquationContext equation(int i) {
			return getRuleContext(EquationContext.class,i);
		}
		public LinearEquationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_linearEquation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterLinearEquation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitLinearEquation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitLinearEquation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LinearEquationContext linearEquation() throws RecognitionException {
		LinearEquationContext _localctx = new LinearEquationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_linearEquation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(T__17);
			setState(89);
			match(T__5);
			setState(90);
			equation();
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(91);
				match(T__8);
				setState(92);
				equation();
				}
				}
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(98);
			match(T__6);
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

	@SuppressWarnings("CheckReturnValue")
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
	@SuppressWarnings("CheckReturnValue")
	public static class InfixOperationExprContext extends ExprContext {
		public OperatorContext op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public InfixOperationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInfixOperationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInfixOperationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInfixOperationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatrixFunctionExprContext extends ExprContext {
		public MatrixFunctionContext matrixFunction() {
			return getRuleContext(MatrixFunctionContext.class,0);
		}
		public MatrixFunctionExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterMatrixFunctionExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitMatrixFunctionExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitMatrixFunctionExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostOperationExprContext extends ExprContext {
		public OperatorContext op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public PostOperationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPostOperationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPostOperationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPostOperationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NumberExprContext extends ExprContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public NumberExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterNumberExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitNumberExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitNumberExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ComplexExprContext extends ExprContext {
		public ComplexContext complex() {
			return getRuleContext(ComplexContext.class,0);
		}
		public ComplexExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterComplexExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitComplexExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitComplexExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensExprContext extends ExprContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterParensExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitParensExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitParensExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class VarExprContext extends ExprContext {
		public VariableNumberContext variableNumber() {
			return getRuleContext(VariableNumberContext.class,0);
		}
		public VarExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterVarExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitVarExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitVarExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LinearExprContext extends ExprContext {
		public LinearEquationContext linearEquation() {
			return getRuleContext(LinearEquationContext.class,0);
		}
		public LinearExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterLinearExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitLinearExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitLinearExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MatrixExprContext extends ExprContext {
		public MatrixContext matrix() {
			return getRuleContext(MatrixContext.class,0);
		}
		public MatrixExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterMatrixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitMatrixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitMatrixExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixOperationExprContext extends ExprContext {
		public OperatorContext op;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public PrefixOperationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPrefixOperationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPrefixOperationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPrefixOperationExpr(this);
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
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				_localctx = new PrefixOperationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(101);
				((PrefixOperationExprContext)_localctx).op = operator();
				setState(102);
				match(T__5);
				setState(103);
				expr(0);
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(104);
					match(T__8);
					setState(105);
					expr(0);
					}
					}
					setState(110);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(111);
				match(T__6);
				}
				break;
			case 2:
				{
				_localctx = new PostOperationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(113);
				match(T__5);
				setState(114);
				expr(0);
				setState(119);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(115);
					match(T__8);
					setState(116);
					expr(0);
					}
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(122);
				match(T__6);
				setState(123);
				((PostOperationExprContext)_localctx).op = operator();
				}
				break;
			case 3:
				{
				_localctx = new ParensExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(125);
				match(T__5);
				setState(126);
				expr(0);
				setState(127);
				match(T__6);
				}
				break;
			case 4:
				{
				_localctx = new NumberExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(129);
				number();
				}
				break;
			case 5:
				{
				_localctx = new ComplexExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				complex();
				}
				break;
			case 6:
				{
				_localctx = new MatrixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(131);
				matrix();
				}
				break;
			case 7:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				variableNumber();
				}
				break;
			case 8:
				{
				_localctx = new LinearExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(133);
				linearEquation();
				}
				break;
			case 9:
				{
				_localctx = new MatrixFunctionExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				matrixFunction();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(143);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InfixOperationExprContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(137);
					if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
					setState(138);
					((InfixOperationExprContext)_localctx).op = operator();
					setState(139);
					expr(9);
					}
					} 
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		case 11:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 8);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017\u0093\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0003\u0003\u0003!\b\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0003\u0003\'\b\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004-\b\u0004\n\u0004\f\u00040\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0005\u00058\b\u0005\n\u0005\f\u0005;\t\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006B\b\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007N\b\u0007\u0001\b\u0003"+
		"\bQ\b\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0005\n^\b\n\n\n\f\na\t\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005"+
		"\u000bk\b\u000b\n\u000b\f\u000bn\t\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000bv\b\u000b\n\u000b"+
		"\f\u000by\t\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u0088\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u008e\b\u000b\n\u000b\f\u000b"+
		"\u0091\t\u000b\u0001\u000b\u0000\u0001\u0016\f\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0000\u0006\u0001\u0000\u0001\u0004"+
		"\u0001\u0000\u0013\u0015\u0001\u0000\u0001\u0002\u0001\u0000\u000b\f\u0001"+
		"\u0000\r\u000e\u0001\u0000\u000f\u0010\u009a\u0000\u0018\u0001\u0000\u0000"+
		"\u0000\u0002\u001a\u0001\u0000\u0000\u0000\u0004\u001c\u0001\u0000\u0000"+
		"\u0000\u0006 \u0001\u0000\u0000\u0000\b(\u0001\u0000\u0000\u0000\n3\u0001"+
		"\u0000\u0000\u0000\fA\u0001\u0000\u0000\u0000\u000eM\u0001\u0000\u0000"+
		"\u0000\u0010P\u0001\u0000\u0000\u0000\u0012T\u0001\u0000\u0000\u0000\u0014"+
		"X\u0001\u0000\u0000\u0000\u0016\u0087\u0001\u0000\u0000\u0000\u0018\u0019"+
		"\u0007\u0000\u0000\u0000\u0019\u0001\u0001\u0000\u0000\u0000\u001a\u001b"+
		"\u0007\u0001\u0000\u0000\u001b\u0003\u0001\u0000\u0000\u0000\u001c\u001d"+
		"\u0003\u0002\u0001\u0000\u001d\u001e\u0005\u0005\u0000\u0000\u001e\u0005"+
		"\u0001\u0000\u0000\u0000\u001f!\u0005\u0006\u0000\u0000 \u001f\u0001\u0000"+
		"\u0000\u0000 !\u0001\u0000\u0000\u0000!\"\u0001\u0000\u0000\u0000\"#\u0003"+
		"\u0002\u0001\u0000#$\u0007\u0002\u0000\u0000$&\u0003\u0004\u0002\u0000"+
		"%\'\u0005\u0007\u0000\u0000&%\u0001\u0000\u0000\u0000&\'\u0001\u0000\u0000"+
		"\u0000\'\u0007\u0001\u0000\u0000\u0000()\u0005\b\u0000\u0000).\u0003\n"+
		"\u0005\u0000*+\u0005\t\u0000\u0000+-\u0003\n\u0005\u0000,*\u0001\u0000"+
		"\u0000\u0000-0\u0001\u0000\u0000\u0000.,\u0001\u0000\u0000\u0000./\u0001"+
		"\u0000\u0000\u0000/1\u0001\u0000\u0000\u00000.\u0001\u0000\u0000\u0000"+
		"12\u0005\n\u0000\u00002\t\u0001\u0000\u0000\u000034\u0005\b\u0000\u0000"+
		"49\u0003\u0002\u0001\u000056\u0005\t\u0000\u000068\u0003\u0002\u0001\u0000"+
		"75\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u00009:\u0001\u0000\u0000\u0000:<\u0001\u0000\u0000\u0000;9\u0001\u0000"+
		"\u0000\u0000<=\u0005\n\u0000\u0000=\u000b\u0001\u0000\u0000\u0000>B\u0007"+
		"\u0003\u0000\u0000?B\u0007\u0004\u0000\u0000@B\u0007\u0005\u0000\u0000"+
		"A>\u0001\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000A@\u0001\u0000\u0000"+
		"\u0000B\r\u0001\u0000\u0000\u0000CD\u0005\u0006\u0000\u0000DE\u0003\b"+
		"\u0004\u0000EF\u0005\u0007\u0000\u0000FG\u0003\f\u0006\u0000GN\u0001\u0000"+
		"\u0000\u0000HI\u0003\f\u0006\u0000IJ\u0005\u0006\u0000\u0000JK\u0003\b"+
		"\u0004\u0000KL\u0005\u0007\u0000\u0000LN\u0001\u0000\u0000\u0000MC\u0001"+
		"\u0000\u0000\u0000MH\u0001\u0000\u0000\u0000N\u000f\u0001\u0000\u0000"+
		"\u0000OQ\u0003\u0002\u0001\u0000PO\u0001\u0000\u0000\u0000PQ\u0001\u0000"+
		"\u0000\u0000QR\u0001\u0000\u0000\u0000RS\u0005\u0016\u0000\u0000S\u0011"+
		"\u0001\u0000\u0000\u0000TU\u0003\u0016\u000b\u0000UV\u0005\u0011\u0000"+
		"\u0000VW\u0003\u0016\u000b\u0000W\u0013\u0001\u0000\u0000\u0000XY\u0005"+
		"\u0012\u0000\u0000YZ\u0005\u0006\u0000\u0000Z_\u0003\u0012\t\u0000[\\"+
		"\u0005\t\u0000\u0000\\^\u0003\u0012\t\u0000][\u0001\u0000\u0000\u0000"+
		"^a\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000"+
		"\u0000`b\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000bc\u0005\u0007"+
		"\u0000\u0000c\u0015\u0001\u0000\u0000\u0000de\u0006\u000b\uffff\uffff"+
		"\u0000ef\u0003\u0000\u0000\u0000fg\u0005\u0006\u0000\u0000gl\u0003\u0016"+
		"\u000b\u0000hi\u0005\t\u0000\u0000ik\u0003\u0016\u000b\u0000jh\u0001\u0000"+
		"\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000lm\u0001"+
		"\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000nl\u0001\u0000\u0000\u0000"+
		"op\u0005\u0007\u0000\u0000p\u0088\u0001\u0000\u0000\u0000qr\u0005\u0006"+
		"\u0000\u0000rw\u0003\u0016\u000b\u0000st\u0005\t\u0000\u0000tv\u0003\u0016"+
		"\u000b\u0000us\u0001\u0000\u0000\u0000vy\u0001\u0000\u0000\u0000wu\u0001"+
		"\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000xz\u0001\u0000\u0000\u0000"+
		"yw\u0001\u0000\u0000\u0000z{\u0005\u0007\u0000\u0000{|\u0003\u0000\u0000"+
		"\u0000|\u0088\u0001\u0000\u0000\u0000}~\u0005\u0006\u0000\u0000~\u007f"+
		"\u0003\u0016\u000b\u0000\u007f\u0080\u0005\u0007\u0000\u0000\u0080\u0088"+
		"\u0001\u0000\u0000\u0000\u0081\u0088\u0003\u0002\u0001\u0000\u0082\u0088"+
		"\u0003\u0006\u0003\u0000\u0083\u0088\u0003\b\u0004\u0000\u0084\u0088\u0003"+
		"\u0010\b\u0000\u0085\u0088\u0003\u0014\n\u0000\u0086\u0088\u0003\u000e"+
		"\u0007\u0000\u0087d\u0001\u0000\u0000\u0000\u0087q\u0001\u0000\u0000\u0000"+
		"\u0087}\u0001\u0000\u0000\u0000\u0087\u0081\u0001\u0000\u0000\u0000\u0087"+
		"\u0082\u0001\u0000\u0000\u0000\u0087\u0083\u0001\u0000\u0000\u0000\u0087"+
		"\u0084\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000\u0087"+
		"\u0086\u0001\u0000\u0000\u0000\u0088\u008f\u0001\u0000\u0000\u0000\u0089"+
		"\u008a\n\b\u0000\u0000\u008a\u008b\u0003\u0000\u0000\u0000\u008b\u008c"+
		"\u0003\u0016\u000b\t\u008c\u008e\u0001\u0000\u0000\u0000\u008d\u0089\u0001"+
		"\u0000\u0000\u0000\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d\u0001"+
		"\u0000\u0000\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0017\u0001"+
		"\u0000\u0000\u0000\u0091\u008f\u0001\u0000\u0000\u0000\f &.9AMP_lw\u0087"+
		"\u008f";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}