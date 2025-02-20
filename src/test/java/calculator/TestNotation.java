package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import visitor.NotationVisitor;

import java.util.Arrays;
import java.util.List;


class TestNotation {

	private List<Expression> params;
	private List<Expression> nestedParams;

    /* This is an auxilary method to avoid code duplication.
     */
	void testNotation(String s,Operation o,Notation n) {
		assertEquals(s, o.toString(n));
		o.notation = n;
		assertEquals(s, o.toString());
	}

	/* This is an auxilary method to avoid code duplication.
     */
	void testNotations(String symbol,int value1,int value2,Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
	}

	@ParameterizedTest
	@ValueSource(strings = {"*", "+", "/", "-"})
	void testOutput(String symbol) {
		int value1 = 8;
		int value2 = 6;
		Operation op = null;
		//List<Expression> params = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		try {
			//construct another type of operation depending on the input value
			//of the parameterised test
			switch (symbol) {
				case "+"	->	op = new Plus(params);
				case "-"	->	op = new Minus(params);
				case "*"	->	op = new Times(params);
				case "/"	->	op = new Divides(params);
				default		->	fail();
			}
		} catch (IllegalConstruction e) {
			fail();
		}
		testNotations(symbol, value1, value2, op);
	}

	@BeforeEach
	void setUp() {
		int value1 = 3;
		int value2 = 4;
		int value3 = 5;
		params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		nestedParams = Arrays.asList(new MyNumber(value3), createOperation(params));
	}

	private Operation createOperation(List<Expression> expressions) {
		try {
			return new Plus(expressions);
		} catch (IllegalConstruction e) {
			fail();
			return null;
		}
	}

	private void notationTest(Operation op, Notation notation, String expected) {
		NotationVisitor visitor = new NotationVisitor(notation);
		op.accept(visitor);
		assertEquals(expected, visitor.getResult());
	}

	@Test
	void testSimpleExpressionInfix() {
		notationTest(createOperation(params), Notation.INFIX, "( 3 + 4 )");
	}

	@Test
	void testSimpleExpressionPrefix() {
		notationTest(createOperation(params), Notation.PREFIX, "+ (3, 4)");
	}

	@Test
	void testSimpleExpressionPostfix() {
		notationTest(createOperation(params), Notation.POSTFIX, "(3, 4) +");
	}

	@Test
	void testNestedExpressionInfix() {
		notationTest(createOperation(nestedParams), Notation.INFIX, "( 5 + ( 3 + 4 ) )");
	}

	@Test
	void testNestedExpressionPrefix() {
		notationTest(createOperation(nestedParams), Notation.PREFIX, "+ (5, + (3, 4))");
	}

	@Test
	void testNestedExpressionPostfix() {
		notationTest(createOperation(nestedParams), Notation.POSTFIX, "(5, (3, 4) +) +");
	}
}
