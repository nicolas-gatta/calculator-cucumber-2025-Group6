package calculator.operations;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.numbers.MyNumber;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

class TestOperation {

	private Operation o;
	private Operation o2;

	@BeforeEach
	void setUp() throws Exception {
		List<Expression> params1 = Arrays.asList(new MyNumber(3), new MyNumber(4), new MyNumber(5));
		List<Expression> params2 = Arrays.asList(new MyNumber(5), new MyNumber(4));
		List<Expression> params3 = Arrays.asList(new Plus(params1), new Minus(params2), new MyNumber(7));
		o = new Divides(params3);
		o2 = new Divides(params3);
	}

	@Test
	void testEquals() {
		assertEquals(o,o2);
	}

	@Test
	void testCountDepth() {
		assertEquals(2, o.countDepth());
	}

	@Test
	void testCountOps() {
		assertEquals(3, o.countOps());
	}

	@Test
	void testCountNbs() {
		assertEquals(Integer.valueOf(6), o.countNbs());
	}

	@Test
	void testConstructorWithEmptyListThrowsException() {
		List<Expression> emptyList = List.of();
		assertThrows(IllegalConstruction.class, () -> new Divides(emptyList));
	}

	@Test
	void testConstructorWithNullListThrowsException() {
		assertThrows(IllegalConstruction.class, () -> new Divides(null));
	}

	@Test
	void testAddMoreParams() throws Exception {
		int initialSize = o.getArgs().size();
		o.addMoreParams(List.of(new MyNumber(9), new MyNumber(10)));
		assertEquals(initialSize + 2, o.getArgs().size());
	}

	@Test
	void testGetSymbol() {
		assertEquals("/", o.getSymbol()); // pour Divides
	}

	@Test
	void testGetNotation() {
		assertEquals(Notation.INFIX, o.getNotation());
	}


	@Test
	void testToStringInfix() {
		String result = o.toString(Notation.INFIX);
		assertNotNull(result);
		assertTrue(result.contains("/")); // ou autre selon la notation
	}

	@Test
	void testToStringPrefix() {
		String result = o.toString(Notation.PREFIX);
		assertNotNull(result);
		assertTrue(result.startsWith("/")); // attendu pour notation préfixe
	}

	@Test
	void testNotEqualsDifferentClass() {
		assertNotEquals(o, new Object());
	}

	@Test
	void testEqualsNull() {
		assertNotEquals(o, null);
	}

	@Test
	void testHashCodeConsistency() {
		assertEquals(o.hashCode(), o2.hashCode());
	}


}
