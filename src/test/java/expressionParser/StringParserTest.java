package expressionParser;

import calculator.Expression;
import org.antlr.v4.runtime.RecognitionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {


    @Test
    void testIllegalConstructor() {
        assertThrows(IllegalStateException.class, StringParser::new);
    }

    @Test
    void testValidSimpleExpression() {
        String input = "3 + 4";
        Expression result = assertDoesNotThrow(() -> StringParser.parse(input));
        assertNotNull(result);
    }

    @Test
    void testValidComplexExpression() {
        String input = "solve(x + y = 3, x - y = 1)";
        Expression result = assertDoesNotThrow(() -> StringParser.parse(input));
        assertNotNull(result);
    }

    @Test
    void testInvalidParenthesesExpression() {
        String input = "(x + 2 = 5)";
        assertThrows(RecognitionException.class, () -> StringParser.parse(input));
    }

    @Test
    void testInvalidExpression() {
        String input = "3 + " ;
        assertThrows(NullPointerException.class, () -> StringParser.parse(input));
    }

    @Test
    void testEmptyExpression() {
        String input = "";
        assertThrows(RecognitionException.class, () -> StringParser.parse(input));
    }

    @Test
    void testNullExpression() {
        assertThrows(NullPointerException.class, () -> StringParser.parse(null));
    }

    @Test
    void testMalformedExpression() {
        String input = "x + y =";
        assertThrows(NullPointerException.class, () -> StringParser.parse(input));
    }
}