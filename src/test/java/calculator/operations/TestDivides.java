package calculator.operations;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.numbers.MyNumber;
import org.junit.jupiter.api.*;
import calculator.numbers.RationalNumber;	
import calculator.numbers.ComplexNumber;
import calculator.numbers.RealNumber;
import visitor.StringVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.ArithmeticException;

class TestDivides {

	private final int value1 = 8;
	private final int value2 = 6;
	private Divides op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		  params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		  try {
		  	op = new Divides(params);
			op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
		  }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
	}

	@Test
	void testConstructor2() {
		try {

			ArrayList<Expression> list = new ArrayList<>();
			list.add(new MyNumber(5));
			list.add(new MyNumber(2));
			Divides d = new Divides(list, Notation.INFIX);
			assertEquals(Notation.INFIX, d.getNotation());
		} catch (IllegalConstruction e) {
			fail("Should not throw exception with non-empty list");
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Divides d = new Divides(p, Notation.INFIX);
			assertEquals(op, d);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@SuppressWarnings("ConstantConditions")
	@Test
	void testNull() {
		assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Divides e = new Divides(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Divides(params));
	}

	@Test
	void testOpMethod() {
		// Test the op method with normal division
		assertEquals(1, op.op(value1, value2)); // 8 / 6 = 1 (integer division)
		
		// Test with negative numbers
		assertEquals(-1, op.op(-value1, value2)); // -8 / 6 = -1
		assertEquals(-1, op.op(value1, -value2)); // 8 / -6 = -1
		assertEquals(1, op.op(-value1, -value2)); // -8 / -6 = 1
	}
	
	@Test
	void testOpMethodWithZero() {
		try {
			ArrayList<Expression> list = new ArrayList<>();
			list.add(new MyNumber(5));
			list.add(new MyNumber(0));
			Divides d = new Divides(list);
			
			ArithmeticException exception = assertThrows(ArithmeticException.class, 
				() -> d.op(5, 0));
			assertEquals("Division by zero", exception.getMessage());
		} catch (IllegalConstruction e) {
			fail("Should not throw exception with non-empty list");
		}
	}
	
	@Test
	void testOpRealMethod() {
		// Test the opReal method with normal division
		assertEquals(1.3333333333333333, op.opReal(value1, value2), 0.0000001); // 8.0 / 6.0 = 1.333...
		
		// Test with negative numbers
		assertEquals(-1.3333333333333333, op.opReal(-value1, value2), 0.0000001);
		assertEquals(-1.3333333333333333, op.opReal(value1, -value2), 0.0000001);
		assertEquals(1.3333333333333333, op.opReal(-value1, -value2), 0.0000001);
	}
	
	@Test
	void testOpRealMethodWithZero() {
		try {
			ArrayList<Expression> list = new ArrayList<>();
			list.add(new RealNumber(5.0, 2));
			list.add(new RealNumber(0.0, 2));
			Divides d = new Divides(list);
			
			ArithmeticException exception = assertThrows(ArithmeticException.class, 
				() -> d.opReal(5.0, 0.0));
			assertEquals("Division by zero", exception.getMessage());
		} catch (IllegalConstruction e) {
			fail("Should not throw exception with non-empty list");
		}
	}
	
	@Test
	void testOpRationalMethod() {
		// Test the opRational method
		RationalNumber r1 = new RationalNumber(value1, 1); // 8/1
		RationalNumber r2 = new RationalNumber(value2, 1); // 6/1
		RationalNumber result = op.opRational(r1, r2);
		
		assertEquals(4, result.getNumerator());
		assertEquals(3, result.getDenominator());
	}
	
	@Test
	void testOpComplexMethod() {
		// Test the opComplex method
		ComplexNumber c1 = new ComplexNumber(value1, 0); // 8 + 0i
		ComplexNumber c2 = new ComplexNumber(value2, 0); // 6 + 0i
		ComplexNumber result = op.opComplex(c1, c2);
		
		assertEquals(1.3333333333333333, result.getReal(), 0.0000001);
		assertEquals(0.0, result.getImaginary(), 0.0000001);
	}
	
	@Test
	void testOpComplexMethodWithImaginary() {
		// Test with complex numbers having imaginary parts
		ComplexNumber c1 = new ComplexNumber(4, 2); // 4 + 2i
		ComplexNumber c2 = new ComplexNumber(2, 1); // 2 + i
		ComplexNumber result = op.opComplex(c1, c2);
		
		// (4+2i)/(2+i) = (4+2i)(2-i)/(2+i)(2-i) = (8-4i+4i-2i²)/(4+1) = (8+0+2)/(5) = 2
		assertEquals(2.0, result.getReal(), 0.0000001);
		assertEquals(0.0, result.getImaginary(), 0.0000001);
	}
	
	@Test
	void testDifferentNotations() {
		// Test string representation with different notations
		try {
			// Create expressions with different notations
			Divides infixOp = new Divides(params, Notation.INFIX);
			Divides prefixOp = new Divides(params, Notation.PREFIX);
			Divides postfixOp = new Divides(params, Notation.POSTFIX);
			
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
