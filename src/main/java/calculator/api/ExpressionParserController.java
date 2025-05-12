package calculator.api;

import calculator.Calculator;
import expressionParser.StringParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that exposes an endpoint to evaluate arithmetic expressions represented as strings.
 * <p>
 * This controller receives raw string expressions via HTTP POST requests and returns the evaluated result.
 * </p>
 *
 * Example request:
 * <pre>
 * POST /api/parser/evaluate
 * Body: "3 + (4 * 2)"
 * Response: "11"
 * </pre>
 */
@RestController
@RequestMapping("/api/parser")
public class ExpressionParserController {

    /**
     * Default constructor for ExpressionParserController.
     */
    public ExpressionParserController() {
        // Default constructor
    }

    /**
     * Evaluates a string-based arithmetic expression and returns the result as a string.
     *
     * @param expression the arithmetic expression to evaluate, e.g., "3 + (2 * 5)"
     * @return the evaluated result as a string
     * @throws IllegalArgumentException if the expression is invalid or evaluation fails
     */
    @PostMapping(value ="/evaluate", produces = "text/plain")
    public String evalExpression(@RequestBody String expression) {
        return new Calculator().eval(StringParser.parse(expression)).toString();
    }
}
