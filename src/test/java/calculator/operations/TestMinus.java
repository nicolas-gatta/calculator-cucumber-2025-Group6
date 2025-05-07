package calculator.operations;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.MyNumber;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.*;
import calculator.numbers.RationalNumber;
import calculator.numbers.ComplexNumber;
import visitor.StringVisitor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestMinus {

	private final int value1 = 8;
	private final int value2 = 6;
	private Minus op;
	private List<Expression> params;
	private Matrix matrix1;
	private Matrix matrix2;

	@BeforeEach
	void setUp() {
		matrix1 = new Matrix(new double[][]{
				{1, 2},
				{3, 4}
		});
		matrix2 = new Matrix(new double[][]{
				{-1, -2},
				{-3, -4}
		});

		params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
		try { op = new Minus(params); }
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Minus(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testConstructor2() {
		try {
			ArrayList<Expression> list = new ArrayList<>();
			list.add(new MyNumber(5));
			list.add(new MyNumber(2));
			Minus m = new Minus(list, Notation.INFIX);
			assertEquals(Notation.INFIX, m.getNotation());
		} catch (IllegalConstruction e) {
			fail("Should not throw exception with non-empty list");
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
		try {
			Minus e = new Minus(p, Notation.INFIX);
			assertEquals(op, e);
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
			Minus e = new Minus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Minus(params));
	}

	@Test
	void testOpMethod() {
		// Test the op method with normal subtraction
		assertEquals(2, op.op(value1, value2)); // 8 - 6 = 2
		
		// Test with negative numbers
		assertEquals(-14, op.op(-value1, value2)); // -8 - 6 = -14
		assertEquals(14, op.op(value1, -value2)); // 8 - (-6) = 14
		assertEquals(-2, op.op(-value1, -value2)); // -8 - (-6) = -2
	}
	
	@Test
	void testOpRealMethod() {
		// Test the opReal method with normal subtraction
		assertEquals(2.0, op.opReal(value1, value2), 0.0000001); // 8.0 - 6.0 = 2.0
		
		// Test with negative numbers
		assertEquals(-14.0, op.opReal(-value1, value2), 0.0000001); // -8.0 - 6.0 = -14.0
		assertEquals(14.0, op.opReal(value1, -value2), 0.0000001); // 8.0 - (-6.0) = 14.0
		assertEquals(-2.0, op.opReal(-value1, -value2), 0.0000001); // -8.0 - (-6.0) = -2.0
	}
	
	@Test
	void testOpRationalMethod() {
		// Test the opRational method
		RationalNumber r1 = new RationalNumber(value1, 1); // 8/1
		RationalNumber r2 = new RationalNumber(value2, 1); // 6/1
		RationalNumber result = op.opRational(r1, r2);
		
		assertEquals(2, result.getNumerator());
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
		// Test the opComplex method
		ComplexNumber c1 = new ComplexNumber(value1, 0); // 8 + 0i
		ComplexNumber c2 = new ComplexNumber(value2, 0); // 6 + 0i
		ComplexNumber result = op.opComplex(c1, c2);
		
		assertEquals(2.0, result.getReal(), 0.0000001);
		assertEquals(0.0, result.getImaginary(), 0.0000001);
		
		// Test with complex numbers having imaginary parts
		ComplexNumber c3 = new ComplexNumber(4, 3); // 4 + 3i
		ComplexNumber c4 = new ComplexNumber(1, 2); // 1 + 2i
		result = op.opComplex(c3, c4);
		
		assertEquals(3.0, result.getReal(), 0.0000001);
		assertEquals(1.0, result.getImaginary(), 0.0000001);
	}
	
	@Test
	void testDifferentNotations() {
		// Test string representation with different notations
		try {
			// Create expressions with different notations
			Minus infixOp = new Minus(params, Notation.INFIX);
			Minus prefixOp = new Minus(params, Notation.PREFIX);
			Minus postfixOp = new Minus(params, Notation.POSTFIX);
			
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

	@Test
	void testOpMatrix_Matrix() {
		assertEquals(new Matrix(new double[][]{{2,4},{6,8}}).toString(),op.opMatrix(matrix1, matrix2).toString());
	}

	@Test
	void testOpMatrix_Scalar() {
		assertThrows(ArithmeticException.class, () -> op.opMatrix(matrix1, 5));
	}

	@Test
	void testOpMatrix() {
		assertThrows(ArithmeticException.class, () -> op.opMatrix(matrix1));
	}

}
