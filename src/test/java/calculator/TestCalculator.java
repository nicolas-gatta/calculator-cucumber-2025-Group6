package calculator;

import calculator.numbers.MyNumber;
import calculator.operations.Plus;
import calculator.operations.Minus;
import calculator.operations.Times;
import calculator.operations.Divides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import visitor.Evaluator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TestCalculator {

    private Calculator calculator;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        // Redirect System.out to capture printed output
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testConstructor() {
        // Test that calculator can be instantiated
        assertNotNull(calculator);
    }

    @Test
    void testEval() throws IllegalConstruction {
        // Test simple number evaluation
        MyNumber num = new MyNumber(42);
        Expression result = calculator.eval(num);
        assertEquals(num, result);

        // Test addition evaluation
        Expression addition = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(3)));
        result = calculator.eval(addition);
        assertEquals(new MyNumber(8), result);

        // Test subtraction evaluation
        Expression subtraction = new Minus(Arrays.asList(new MyNumber(10), new MyNumber(4)));
        result = calculator.eval(subtraction);
        assertEquals(new MyNumber(6), result);

        // Test multiplication evaluation
        Expression multiplication = new Times(Arrays.asList(new MyNumber(6), new MyNumber(7)));
        result = calculator.eval(multiplication);
        assertEquals(new MyNumber(42), result);

        // Test division evaluation
        Expression division = new Divides(Arrays.asList(new MyNumber(20), new MyNumber(5)));
        result = calculator.eval(division);
        assertEquals(new MyNumber(4), result);

        // Test complex expression evaluation
        // (10 + 5) * 2
        Expression complex = new Times(
            Arrays.asList(
                new Plus(Arrays.asList(new MyNumber(10), new MyNumber(5))),
                new MyNumber(2)
            )
        );
        result = calculator.eval(complex);
        assertEquals(new MyNumber(30), result);
    }

    @Test
    void testPrint() throws IllegalConstruction {
        // Reset output stream before test
        outContent.reset();
        
        // Test printing a simple number
        MyNumber num = new MyNumber(42);
        calculator.print(num);
        
        String expectedOutput = "The result of evaluating expression 42\nis: 42.\n\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void testPrintExpressionDetails() throws IllegalConstruction {
        // Reset output stream before test
        outContent.reset();
        
        // Create a complex expression: (5 + 3) * (10 - 2)
        Expression e1 = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(3)));
        Expression e2 = new Minus(Arrays.asList(new MyNumber(10), new MyNumber(2)));
        Expression complex = new Times(Arrays.asList(e1, e2));
        
        calculator.printExpressionDetails(complex);
        
        // Expected output should contain the evaluation result (64) and expression details
        String output = outContent.toString();
        
        assertTrue(output.contains("The result of evaluating expression"));
        assertTrue(output.contains("is: 64."));
        assertTrue(output.contains("It contains 2 levels of nested expressions"));
        assertTrue(output.contains("3 operations"));
        assertTrue(output.contains("4 numbers"));
    }

    @Test
    void testEvaluatorCreation() throws IllegalConstruction {
        // Test that the calculator correctly creates an Evaluator
        MyNumber num = new MyNumber(42);
        Expression result = calculator.eval(num);
        
        // The result should be the same as directly using an Evaluator
        Evaluator evaluator = new Evaluator();
        num.accept(evaluator);
        Expression directResult = evaluator.getResult();
        
        assertEquals(directResult, result);
    }

    @Test
    void testNestedExpressionEvaluation() throws IllegalConstruction {
        // Test deeply nested expression: ((2 + 3) * (4 - 1)) / (5 + 1)
        Expression e1 = new Plus(Arrays.asList(new MyNumber(2), new MyNumber(3)));  // 2 + 3 = 5
        Expression e2 = new Minus(Arrays.asList(new MyNumber(4), new MyNumber(1))); // 4 - 1 = 3
        Expression e3 = new Times(Arrays.asList(e1, e2));  // 5 * 3 = 15
        Expression e4 = new Plus(Arrays.asList(new MyNumber(5), new MyNumber(1)));  // 5 + 1 = 6
        Expression complex = new Divides(Arrays.asList(e3, e4));  // 15 / 6 = 2 (integer division)
        
        Expression result = calculator.eval(complex);
        assertEquals(new MyNumber(2), result);
    }

    // Clean up after tests
    @org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }
} 