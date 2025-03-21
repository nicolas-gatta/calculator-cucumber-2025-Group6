package calculator.numbers;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;

import calculator.IllegalConstruction;
import calculator.numbers.MyNumber;
import calculator.operations.Times;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;

class TestMyNumber {

	private final int value =8;
	private MyNumber number;
	
	@BeforeEach
	void setUp() {
		number = new MyNumber(value);
	}

	@Test
	void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new MyNumber(otherValue),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value); // number is of type MyNumber, while value is of type int, so not equal
		
		try {
			ArrayList<calculator.Expression> list = new ArrayList<>();
			list.add(new MyNumber(5));
			assertNotEquals(new Times(list), number);
		}
		catch (IllegalConstruction e) {
			fail("Should not throw exception with non-empty list");
		}
	}

	@Test
	void testToString() {
		assertEquals(Integer.toString(value), number.toString());
	}

}
