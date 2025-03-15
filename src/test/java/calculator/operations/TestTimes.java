package calculator.operations;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.ComplexNumber;
import org.junit.jupiter.api.*;
import visitor.StringVisitor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestTimes {

	private final int value1 = 8;
	private final int value2 = 6;
	private Times op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		  params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		  try { op = new Times(params); }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Times(null));
	}

	@Test
	void testConstructor2() {
		// A Plus expression should not be the same as a Times expression
		try {
			assertNotSame(op, new Plus(new ArrayList<>()));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(op, e);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNull() {
		assertDoesNotThrow(() -> op==null); // Direct way to test if the null case is handled.
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Times e = new Times(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Times(params));
	}
	
	@Test
	void testOpMethod() {
		// Test the op method with normal multiplication
		assertEquals(48, op.op(value1, value2)); // 8 * 6 = 48
		
		// Test with negative numbers
		assertEquals(-48, op.op(-value1, value2)); // -8 * 6 = -48
		assertEquals(-48, op.op(value1, -value2)); // 8 * (-6) = -48
		assertEquals(48, op.op(-value1, -value2)); // -8 * (-6) = 48
		
		// Test with zero
		assertEquals(0, op.op(0, value2)); // 0 * 6 = 0
		assertEquals(0, op.op(value1, 0)); // 8 * 0 = 0
	}
	
	@Test
	void testOpRealMethod() {
		// Test the opReal method with normal multiplication
		assertEquals(48.0, op.opReal(value1, value2), 0.0000001); // 8.0 * 6.0 = 48.0
		
		// Test with negative numbers
		assertEquals(-48.0, op.opReal(-value1, value2), 0.0000001); // -8.0 * 6.0 = -48.0
		assertEquals(-48.0, op.opReal(value1, -value2), 0.0000001); // 8.0 * (-6.0) = -48.0
		assertEquals(48.0, op.opReal(-value1, -value2), 0.0000001); // -8.0 * (-6.0) = 48.0
		
		// Test with zero
		assertEquals(0.0, op.opReal(0.0, value2), 0.0000001); // 0.0 * 6.0 = 0.0
		assertEquals(0.0, op.opReal(value1, 0.0), 0.0000001); // 8.0 * 0.0 = 0.0
	}
	
	@Test
	void testOpRationalMethod() {
		// Test the opRational method with integers
		RationalNumber r1 = new RationalNumber(value1, 1); // 8/1
		RationalNumber r2 = new RationalNumber(value2, 1); // 6/1
		RationalNumber result = op.opRational(r1, r2);
		
		assertEquals(48, result.getNumerator());
		assertEquals(1, result.getDenominator());
		
		// Test with fractions
		RationalNumber r3 = new RationalNumber(1, 2); // 1/2
		RationalNumber r4 = new RationalNumber(1, 3); // 1/3
		result = op.opRational(r3, r4);
		
		assertEquals(1, result.getNumerator());
		assertEquals(6, result.getDenominator());
	}
	
	@Test
	void testOpComplexMethod() {
		// Test the opComplex method with real numbers
		ComplexNumber c1 = new ComplexNumber(value1, 0); // 8 + 0i
		ComplexNumber c2 = new ComplexNumber(value2, 0); // 6 + 0i
		ComplexNumber result = op.opComplex(c1, c2);
		
		assertEquals(48.0, result.getReal(), 0.0000001);
		assertEquals(0.0, result.getImaginary(), 0.0000001);
		
		// Test with complex numbers having imaginary parts
		ComplexNumber c3 = new ComplexNumber(3, 2); // 3 + 2i
		ComplexNumber c4 = new ComplexNumber(1, 4); // 1 + 4i
		result = op.opComplex(c3, c4);
		
		// (3+2i) * (1+4i) = 3 + 12i + 2i + 8i² = 3 + 14i - 8 = -5 + 14i
		assertEquals(-5.0, result.getReal(), 0.0000001);
		assertEquals(14.0, result.getImaginary(), 0.0000001);
	}
	
	@Test
	void testDifferentNotations() {
		// Test string representation with different notations
		try {
			// Create expressions with different notations
			Times infixOp = new Times(params, Notation.INFIX);
			Times prefixOp = new Times(params, Notation.PREFIX);
			Times postfixOp = new Times(params, Notation.POSTFIX);
			
			// Verify that the notation affects the string representation
			StringVisitor sv = new StringVisitor(Notation.INFIX);
			
			infixOp.accept(sv);
			String infixResult = sv.getResult();
			
			sv = new StringVisitor(Notation.PREFIX);
			prefixOp.accept(sv);
			String prefixResult = sv.getResult();
			
			sv = new StringVisitor(Notation.POSTFIX);
			postfixOp.accept(sv);
			String postfixResult = sv.getResult();
			
			// Verify that the results are different
			assertNotEquals(infixResult, prefixResult);
			assertNotEquals(infixResult, postfixResult);
			assertNotEquals(prefixResult, postfixResult);
		}
		catch(IllegalConstruction e) { fail(); }
	}
}
