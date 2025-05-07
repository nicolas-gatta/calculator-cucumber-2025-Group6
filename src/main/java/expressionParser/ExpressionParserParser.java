// Generated from C:/Users/Utilisateur/Desktop/Projet/calculator-cucumber-2025-Group6/src/main/java/expressionParser/ExpressionParser.g4 by ANTLR 4.13.2
package expressionParser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import javax.annotation.processing.Generated;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})

@Generated("org.antlr")
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
		RULE_variableNumber = 8, RULE_equationLeftPart = 9, RULE_equationRightPart = 10, 
		RULE_equation = 11, RULE_linearEquation = 12, RULE_expr = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"operator", "number", "imaginary", "complex", "matrix", "row", "matrixOperator", 
			"matrixFunction", "variableNumber", "equationLeftPart", "equationRightPart", 
			"equation", "linearEquation", "expr"
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
			setState(28);
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
			setState(30);
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
			setState(32);
			number();
			setState(33);
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
			setState(36);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__5) {
				{
				setState(35);
				match(T__5);
				}
			}

			setState(38);
			number();
			setState(39);
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
			setState(40);
			imaginary();
			setState(42);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(41);
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
			setState(44);
			match(T__7);
			setState(45);
			row();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(46);
				match(T__8);
				setState(47);
				row();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
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
			setState(55);
			match(T__7);
			setState(56);
			number();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(57);
				match(T__8);
				setState(58);
				number();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
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
			setState(69);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
			case T__11:
				_localctx = new TransposeContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
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
				setState(67);
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
				setState(68);
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
		int _la;
		try {
			setState(88);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
			case T__7:
				_localctx = new MatrixPostfixContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(71);
					match(T__5);
					}
				}

				setState(74);
				matrix();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__6) {
					{
					setState(75);
					match(T__6);
					}
				}

				setState(78);
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
				setState(80);
				matrixOperator();
				setState(82);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(81);
					match(T__5);
					}
				}

				setState(84);
				matrix();
				setState(86);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(85);
					match(T__6);
					}
					break;
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
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 3670016L) != 0)) {
				{
				setState(90);
				number();
				}
			}

			setState(93);
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
	public static class EquationLeftPartContext extends ParserRuleContext {
		public EquationLeftPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equationLeftPart; }
	 
		public EquationLeftPartContext() { }
		public void copyFrom(EquationLeftPartContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class EquationVariableContext extends EquationLeftPartContext {
		public VariableNumberContext variableNumber() {
			return getRuleContext(VariableNumberContext.class,0);
		}
		public EquationVariableContext(EquationLeftPartContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterEquationVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitEquationVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitEquationVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PrefixEquationExprContext extends EquationLeftPartContext {
		public OperatorContext op;
		public List<EquationLeftPartContext> equationLeftPart() {
			return getRuleContexts(EquationLeftPartContext.class);
		}
		public EquationLeftPartContext equationLeftPart(int i) {
			return getRuleContext(EquationLeftPartContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public PrefixEquationExprContext(EquationLeftPartContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPrefixEquationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPrefixEquationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPrefixEquationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class InfixEquationExprContext extends EquationLeftPartContext {
		public OperatorContext op;
		public List<EquationLeftPartContext> equationLeftPart() {
			return getRuleContexts(EquationLeftPartContext.class);
		}
		public EquationLeftPartContext equationLeftPart(int i) {
			return getRuleContext(EquationLeftPartContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public InfixEquationExprContext(EquationLeftPartContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterInfixEquationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitInfixEquationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitInfixEquationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParensEquationExprContext extends EquationLeftPartContext {
		public EquationLeftPartContext equationLeftPart() {
			return getRuleContext(EquationLeftPartContext.class,0);
		}
		public ParensEquationExprContext(EquationLeftPartContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterParensEquationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitParensEquationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitParensEquationExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class PostfixEquationExprContext extends EquationLeftPartContext {
		public OperatorContext op;
		public List<EquationLeftPartContext> equationLeftPart() {
			return getRuleContexts(EquationLeftPartContext.class);
		}
		public EquationLeftPartContext equationLeftPart(int i) {
			return getRuleContext(EquationLeftPartContext.class,i);
		}
		public OperatorContext operator() {
			return getRuleContext(OperatorContext.class,0);
		}
		public PostfixEquationExprContext(EquationLeftPartContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterPostfixEquationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitPostfixEquationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitPostfixEquationExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationLeftPartContext equationLeftPart() throws RecognitionException {
		return equationLeftPart(0);
	}

	private EquationLeftPartContext equationLeftPart(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		EquationLeftPartContext _localctx = new EquationLeftPartContext(_ctx, _parentState);
		EquationLeftPartContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_equationLeftPart, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(125);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				_localctx = new ParensEquationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(96);
				match(T__5);
				setState(97);
				equationLeftPart(0);
				setState(98);
				match(T__6);
				}
				break;
			case 2:
				{
				_localctx = new PrefixEquationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(100);
				((PrefixEquationExprContext)_localctx).op = operator();
				setState(101);
				match(T__5);
				setState(102);
				equationLeftPart(0);
				setState(107);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(103);
					match(T__8);
					setState(104);
					equationLeftPart(0);
					}
					}
					setState(109);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(110);
				match(T__6);
				}
				break;
			case 3:
				{
				_localctx = new PostfixEquationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(112);
				match(T__5);
				setState(113);
				equationLeftPart(0);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(114);
					match(T__8);
					setState(115);
					equationLeftPart(0);
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(121);
				match(T__6);
				setState(122);
				((PostfixEquationExprContext)_localctx).op = operator();
				}
				break;
			case 4:
				{
				_localctx = new EquationVariableContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(124);
				variableNumber();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InfixEquationExprContext(new EquationLeftPartContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_equationLeftPart);
					setState(127);
					if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
					setState(128);
					((InfixEquationExprContext)_localctx).op = operator();
					setState(129);
					equationLeftPart(4);
					}
					} 
				}
				setState(135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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

	@SuppressWarnings("CheckReturnValue")
	public static class EquationRightPartContext extends ParserRuleContext {
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public EquationRightPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_equationRightPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterEquationRightPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitEquationRightPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitEquationRightPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EquationRightPartContext equationRightPart() throws RecognitionException {
		EquationRightPartContext _localctx = new EquationRightPartContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_equationRightPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			number();
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
		public EquationLeftPartContext equationLeftPart() {
			return getRuleContext(EquationLeftPartContext.class,0);
		}
		public EquationRightPartContext equationRightPart() {
			return getRuleContext(EquationRightPartContext.class,0);
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
		enterRule(_localctx, 22, RULE_equation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			equationLeftPart(0);
			setState(139);
			((EquationContext)_localctx).op = match(T__16);
			setState(140);
			equationRightPart();
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
		enterRule(_localctx, 24, RULE_linearEquation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(T__17);
			setState(143);
			match(T__5);
			setState(144);
			equation();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(145);
				match(T__8);
				setState(146);
				equation();
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(152);
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
	public static class EquationExprContext extends ExprContext {
		public EquationContext equation() {
			return getRuleContext(EquationContext.class,0);
		}
		public EquationExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).enterEquationExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExpressionParserListener ) ((ExpressionParserListener)listener).exitEquationExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExpressionParserVisitor ) return ((ExpressionParserVisitor<? extends T>)visitor).visitEquationExpr(this);
			else return visitor.visitChildren(this);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				_localctx = new PrefixOperationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(155);
				((PrefixOperationExprContext)_localctx).op = operator();
				setState(156);
				match(T__5);
				setState(157);
				expr(0);
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(158);
					match(T__8);
					setState(159);
					expr(0);
					}
					}
					setState(164);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(165);
				match(T__6);
				}
				break;
			case 2:
				{
				_localctx = new PostOperationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(167);
				match(T__5);
				setState(168);
				expr(0);
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(169);
					match(T__8);
					setState(170);
					expr(0);
					}
					}
					setState(175);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(176);
				match(T__6);
				setState(177);
				((PostOperationExprContext)_localctx).op = operator();
				}
				break;
			case 3:
				{
				_localctx = new ParensExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(179);
				match(T__5);
				setState(180);
				expr(0);
				setState(181);
				match(T__6);
				}
				break;
			case 4:
				{
				_localctx = new NumberExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(183);
				number();
				}
				break;
			case 5:
				{
				_localctx = new ComplexExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				complex();
				}
				break;
			case 6:
				{
				_localctx = new MatrixExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(185);
				matrix();
				}
				break;
			case 7:
				{
				_localctx = new EquationExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(186);
				equation();
				}
				break;
			case 8:
				{
				_localctx = new LinearExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(187);
				linearEquation();
				}
				break;
			case 9:
				{
				_localctx = new VarExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(188);
				variableNumber();
				}
				break;
			case 10:
				{
				_localctx = new MatrixFunctionExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(189);
				matrixFunction();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(198);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new InfixOperationExprContext(new ExprContext(_parentctx, _parentState));
					pushNewRecursionContext(_localctx, _startState, RULE_expr);
					setState(192);
					if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
					setState(193);
					((InfixOperationExprContext)_localctx).op = operator();
					setState(194);
					expr(10);
					}
					} 
				}
				setState(200);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
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
		case 9:
			return equationLeftPart_sempred((EquationLeftPartContext)_localctx, predIndex);
		case 13:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean equationLeftPart_sempred(EquationLeftPartContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 3);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0017\u00ca\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0003\u0003%\b"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0003\u0003+\b"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u00041\b"+
		"\u0004\n\u0004\f\u00044\t\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005<\b\u0005\n\u0005\f\u0005?\t"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0003"+
		"\u0006F\b\u0006\u0001\u0007\u0003\u0007I\b\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007M\b\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0003\u0007S\b\u0007\u0001\u0007\u0001\u0007\u0003\u0007W\b\u0007\u0003"+
		"\u0007Y\b\u0007\u0001\b\u0003\b\\\b\b\u0001\b\u0001\b\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005"+
		"\tj\b\t\n\t\f\tm\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0005"+
		"\tu\b\t\n\t\f\tx\t\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t~\b\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0005\t\u0084\b\t\n\t\f\t\u0087\t\t\u0001\n"+
		"\u0001\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0005\f\u0094\b\f\n\f\f\f\u0097\t\f\u0001\f"+
		"\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00a1"+
		"\b\r\n\r\f\r\u00a4\t\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r"+
		"\u0005\r\u00ac\b\r\n\r\f\r\u00af\t\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u00bf\b\r\u0001\r\u0001\r\u0001\r\u0001\r\u0005\r\u00c5\b\r"+
		"\n\r\f\r\u00c8\t\r\u0001\r\u0000\u0002\u0012\u001a\u000e\u0000\u0002\u0004"+
		"\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0000\u0006\u0001"+
		"\u0000\u0001\u0004\u0001\u0000\u0013\u0015\u0001\u0000\u0001\u0002\u0001"+
		"\u0000\u000b\f\u0001\u0000\r\u000e\u0001\u0000\u000f\u0010\u00da\u0000"+
		"\u001c\u0001\u0000\u0000\u0000\u0002\u001e\u0001\u0000\u0000\u0000\u0004"+
		" \u0001\u0000\u0000\u0000\u0006$\u0001\u0000\u0000\u0000\b,\u0001\u0000"+
		"\u0000\u0000\n7\u0001\u0000\u0000\u0000\fE\u0001\u0000\u0000\u0000\u000e"+
		"X\u0001\u0000\u0000\u0000\u0010[\u0001\u0000\u0000\u0000\u0012}\u0001"+
		"\u0000\u0000\u0000\u0014\u0088\u0001\u0000\u0000\u0000\u0016\u008a\u0001"+
		"\u0000\u0000\u0000\u0018\u008e\u0001\u0000\u0000\u0000\u001a\u00be\u0001"+
		"\u0000\u0000\u0000\u001c\u001d\u0007\u0000\u0000\u0000\u001d\u0001\u0001"+
		"\u0000\u0000\u0000\u001e\u001f\u0007\u0001\u0000\u0000\u001f\u0003\u0001"+
		"\u0000\u0000\u0000 !\u0003\u0002\u0001\u0000!\"\u0005\u0005\u0000\u0000"+
		"\"\u0005\u0001\u0000\u0000\u0000#%\u0005\u0006\u0000\u0000$#\u0001\u0000"+
		"\u0000\u0000$%\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&\'\u0003"+
		"\u0002\u0001\u0000\'(\u0007\u0002\u0000\u0000(*\u0003\u0004\u0002\u0000"+
		")+\u0005\u0007\u0000\u0000*)\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000"+
		"\u0000+\u0007\u0001\u0000\u0000\u0000,-\u0005\b\u0000\u0000-2\u0003\n"+
		"\u0005\u0000./\u0005\t\u0000\u0000/1\u0003\n\u0005\u00000.\u0001\u0000"+
		"\u0000\u000014\u0001\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001"+
		"\u0000\u0000\u000035\u0001\u0000\u0000\u000042\u0001\u0000\u0000\u0000"+
		"56\u0005\n\u0000\u00006\t\u0001\u0000\u0000\u000078\u0005\b\u0000\u0000"+
		"8=\u0003\u0002\u0001\u00009:\u0005\t\u0000\u0000:<\u0003\u0002\u0001\u0000"+
		";9\u0001\u0000\u0000\u0000<?\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000"+
		"\u0000=>\u0001\u0000\u0000\u0000>@\u0001\u0000\u0000\u0000?=\u0001\u0000"+
		"\u0000\u0000@A\u0005\n\u0000\u0000A\u000b\u0001\u0000\u0000\u0000BF\u0007"+
		"\u0003\u0000\u0000CF\u0007\u0004\u0000\u0000DF\u0007\u0005\u0000\u0000"+
		"EB\u0001\u0000\u0000\u0000EC\u0001\u0000\u0000\u0000ED\u0001\u0000\u0000"+
		"\u0000F\r\u0001\u0000\u0000\u0000GI\u0005\u0006\u0000\u0000HG\u0001\u0000"+
		"\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0001\u0000\u0000\u0000JL\u0003"+
		"\b\u0004\u0000KM\u0005\u0007\u0000\u0000LK\u0001\u0000\u0000\u0000LM\u0001"+
		"\u0000\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0003\f\u0006\u0000OY\u0001"+
		"\u0000\u0000\u0000PR\u0003\f\u0006\u0000QS\u0005\u0006\u0000\u0000RQ\u0001"+
		"\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001\u0000\u0000\u0000"+
		"TV\u0003\b\u0004\u0000UW\u0005\u0007\u0000\u0000VU\u0001\u0000\u0000\u0000"+
		"VW\u0001\u0000\u0000\u0000WY\u0001\u0000\u0000\u0000XH\u0001\u0000\u0000"+
		"\u0000XP\u0001\u0000\u0000\u0000Y\u000f\u0001\u0000\u0000\u0000Z\\\u0003"+
		"\u0002\u0001\u0000[Z\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000"+
		"\\]\u0001\u0000\u0000\u0000]^\u0005\u0016\u0000\u0000^\u0011\u0001\u0000"+
		"\u0000\u0000_`\u0006\t\uffff\uffff\u0000`a\u0005\u0006\u0000\u0000ab\u0003"+
		"\u0012\t\u0000bc\u0005\u0007\u0000\u0000c~\u0001\u0000\u0000\u0000de\u0003"+
		"\u0000\u0000\u0000ef\u0005\u0006\u0000\u0000fk\u0003\u0012\t\u0000gh\u0005"+
		"\t\u0000\u0000hj\u0003\u0012\t\u0000ig\u0001\u0000\u0000\u0000jm\u0001"+
		"\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000"+
		"ln\u0001\u0000\u0000\u0000mk\u0001\u0000\u0000\u0000no\u0005\u0007\u0000"+
		"\u0000o~\u0001\u0000\u0000\u0000pq\u0005\u0006\u0000\u0000qv\u0003\u0012"+
		"\t\u0000rs\u0005\t\u0000\u0000su\u0003\u0012\t\u0000tr\u0001\u0000\u0000"+
		"\u0000ux\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001\u0000"+
		"\u0000\u0000wy\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000yz\u0005"+
		"\u0007\u0000\u0000z{\u0003\u0000\u0000\u0000{~\u0001\u0000\u0000\u0000"+
		"|~\u0003\u0010\b\u0000}_\u0001\u0000\u0000\u0000}d\u0001\u0000\u0000\u0000"+
		"}p\u0001\u0000\u0000\u0000}|\u0001\u0000\u0000\u0000~\u0085\u0001\u0000"+
		"\u0000\u0000\u007f\u0080\n\u0003\u0000\u0000\u0080\u0081\u0003\u0000\u0000"+
		"\u0000\u0081\u0082\u0003\u0012\t\u0004\u0082\u0084\u0001\u0000\u0000\u0000"+
		"\u0083\u007f\u0001\u0000\u0000\u0000\u0084\u0087\u0001\u0000\u0000\u0000"+
		"\u0085\u0083\u0001\u0000\u0000\u0000\u0085\u0086\u0001\u0000\u0000\u0000"+
		"\u0086\u0013\u0001\u0000\u0000\u0000\u0087\u0085\u0001\u0000\u0000\u0000"+
		"\u0088\u0089\u0003\u0002\u0001\u0000\u0089\u0015\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0003\u0012\t\u0000\u008b\u008c\u0005\u0011\u0000\u0000\u008c"+
		"\u008d\u0003\u0014\n\u0000\u008d\u0017\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0005\u0012\u0000\u0000\u008f\u0090\u0005\u0006\u0000\u0000\u0090\u0095"+
		"\u0003\u0016\u000b\u0000\u0091\u0092\u0005\t\u0000\u0000\u0092\u0094\u0003"+
		"\u0016\u000b\u0000\u0093\u0091\u0001\u0000\u0000\u0000\u0094\u0097\u0001"+
		"\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0095\u0096\u0001"+
		"\u0000\u0000\u0000\u0096\u0098\u0001\u0000\u0000\u0000\u0097\u0095\u0001"+
		"\u0000\u0000\u0000\u0098\u0099\u0005\u0007\u0000\u0000\u0099\u0019\u0001"+
		"\u0000\u0000\u0000\u009a\u009b\u0006\r\uffff\uffff\u0000\u009b\u009c\u0003"+
		"\u0000\u0000\u0000\u009c\u009d\u0005\u0006\u0000\u0000\u009d\u00a2\u0003"+
		"\u001a\r\u0000\u009e\u009f\u0005\t\u0000\u0000\u009f\u00a1\u0003\u001a"+
		"\r\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a1\u00a4\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a0\u0001\u0000\u0000\u0000\u00a2\u00a3\u0001\u0000\u0000"+
		"\u0000\u00a3\u00a5\u0001\u0000\u0000\u0000\u00a4\u00a2\u0001\u0000\u0000"+
		"\u0000\u00a5\u00a6\u0005\u0007\u0000\u0000\u00a6\u00bf\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a8\u0005\u0006\u0000\u0000\u00a8\u00ad\u0003\u001a\r\u0000"+
		"\u00a9\u00aa\u0005\t\u0000\u0000\u00aa\u00ac\u0003\u001a\r\u0000\u00ab"+
		"\u00a9\u0001\u0000\u0000\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae"+
		"\u00b0\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0005\u0007\u0000\u0000\u00b1\u00b2\u0003\u0000\u0000\u0000\u00b2"+
		"\u00bf\u0001\u0000\u0000\u0000\u00b3\u00b4\u0005\u0006\u0000\u0000\u00b4"+
		"\u00b5\u0003\u001a\r\u0000\u00b5\u00b6\u0005\u0007\u0000\u0000\u00b6\u00bf"+
		"\u0001\u0000\u0000\u0000\u00b7\u00bf\u0003\u0002\u0001\u0000\u00b8\u00bf"+
		"\u0003\u0006\u0003\u0000\u00b9\u00bf\u0003\b\u0004\u0000\u00ba\u00bf\u0003"+
		"\u0016\u000b\u0000\u00bb\u00bf\u0003\u0018\f\u0000\u00bc\u00bf\u0003\u0010"+
		"\b\u0000\u00bd\u00bf\u0003\u000e\u0007\u0000\u00be\u009a\u0001\u0000\u0000"+
		"\u0000\u00be\u00a7\u0001\u0000\u0000\u0000\u00be\u00b3\u0001\u0000\u0000"+
		"\u0000\u00be\u00b7\u0001\u0000\u0000\u0000\u00be\u00b8\u0001\u0000\u0000"+
		"\u0000\u00be\u00b9\u0001\u0000\u0000\u0000\u00be\u00ba\u0001\u0000\u0000"+
		"\u0000\u00be\u00bb\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000\u0000"+
		"\u0000\u00be\u00bd\u0001\u0000\u0000\u0000\u00bf\u00c6\u0001\u0000\u0000"+
		"\u0000\u00c0\u00c1\n\t\u0000\u0000\u00c1\u00c2\u0003\u0000\u0000\u0000"+
		"\u00c2\u00c3\u0003\u001a\r\n\u00c3\u00c5\u0001\u0000\u0000\u0000\u00c4"+
		"\u00c0\u0001\u0000\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c4\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7"+
		"\u001b\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u0014"+
		"$*2=EHLRVX[kv}\u0085\u0095\u00a2\u00ad\u00be\u00c6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}