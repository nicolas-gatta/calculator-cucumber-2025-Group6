package calculator.api;

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling calculator operations in the API.
 * <p>
 * This class exposes a REST API for performing basic arithmetic operations such as addition, subtraction,
 * multiplication, and division on various number types (Integer, Rational, Real, Complex).
 * The API endpoint allows users to send calculation requests in the form of a POST request with a JSON body.
 * </p>
 */
@RestController
@RequestMapping("/api")
public class CalculatorController {

    /**
     * Default constructor for CalculatorController.
     * Initializes a new Calculator instance for performing arithmetic operations.
     */
    public CalculatorController() {
        // Initialize calculator with default settings
        this.calculator = new Calculator();
    }

    // Replace the existing calculator initialization
    private final Calculator calculator;

    /**
     * Endpoint for performing calculations based on the provided operands and operator.
     *
     * @param request the request object containing the operands, operator, and number type.
     * @return the result of the calculation as a string.
     * @throws IllegalArgumentException if the operator is invalid or if there is any issue during the calculation.
     */
    @PostMapping(value ="/calculate", produces = "text/plain")
    public String calculate(@RequestBody CalculationRequest request) {
        // Parse the operands based on the number type (Integer, Rational, Real, Complex)
        Expression firstOperand = parseInput(request.getFirstOperand(), request.getNumberType());
        Expression secondOperand = parseInput(request.getSecondOperand(), request.getNumberType());

        List<Expression> params = new ArrayList<>();
        if(firstOperand != null) {
            params.add(firstOperand);
        }
        if(secondOperand != null) {
            params.add(secondOperand);
        }

        try{
            // Perform the operation based on the operator provided in the request
            Expression operation = switch (request.getOperator()) {
                case "+" -> new Plus(params);
                case "-" -> new Minus(params);
                case "*" -> new Times(params);
                case "/" -> new Divides(params);
                default -> throw new IllegalArgumentException("Invalid operator: " + request.getOperator());
            };

            // Evaluate the operation and return the result as a string
            Expression result = calculator.eval(operation);

            return result.toString();
        }catch (IllegalConstruction e){
            throw new IllegalArgumentException("Illegal construction: " + e.getMessage());
        }

    }

    /**
     * Parses the input string based on the specified number type and returns the corresponding Expression.
     *
     * @param input the string representation of the operand.
     * @param numberType the type of number (e.g., INTEGER, RATIONAL, REAL, COMPLEX).
     * @return the corresponding Expression for the operand.
     * @throws IllegalArgumentException if the number type is invalid.
     */
    private Expression parseInput(String input, String numberType) {
        switch (numberType) {
            case "INTEGER" -> {
                return input == null ? null : (new MyNumber(Integer.parseInt(input)));
            }
            case "RATIONAL" -> {
                if(input == null) {
                    return null;
                }
                if (input.contains("/")) {
                    String[] parts = input.split("/");
                    return new RationalNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                }
                return new RationalNumber(Integer.parseInt(input), 1);
            }
            case "REAL" -> {
                return input == null ? null : (new RealNumber(Double.parseDouble(input), 6));
            }
            case "COMPLEX" -> {
                return input == null ? null : parseComplexNumber(input);
            }
            default -> throw new IllegalArgumentException("Invalid number type: " + numberType);
        }
    }

    /**
     * Parses the input string to create a ComplexNumber.
     *
     * @param input the string representation of the complex number.
     * @return the corresponding ComplexNumber.
     * @throws IllegalArgumentException if the input is invalid for a complex number.
     */
    private ComplexNumber parseComplexNumber(String input) {
        if (input.equals("i")) return new ComplexNumber(0, 1);
        if (input.equals("-i")) return new ComplexNumber(0, -1);
        if (!input.contains("i")) return new ComplexNumber(Double.parseDouble(input), 0);

        input = input.replace(" ", "");

        int plus = input.indexOf("+", 1);
        int minus = input.indexOf("-", 1);
        int splitIndex = plus >= 0 ? plus : minus;

        if (splitIndex >= 0) {
            double real = Double.parseDouble(input.substring(0, splitIndex));
            double imaginary = Double.parseDouble(input.substring(splitIndex, input.length() - 1));
            return new ComplexNumber(real, imaginary);
        } else {
            double imag = Double.parseDouble(input.substring(0, input.length() - 1));
            return new ComplexNumber(0, imag);
        }
    }

}
