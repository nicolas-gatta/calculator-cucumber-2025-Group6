package calculator.api;

import calculator.Calculator;
import calculator.Expression;
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

@RestController
@RequestMapping("/api")
public class CalculatorController {

    Calculator calculator = new Calculator();

    @PostMapping("/calculate")
    public String calculate(@RequestBody CalculationRequest request) {
        try {
            Expression firtsOperand = parseInput(request.getFirstOperand(), request.getNumberType());
            Expression secondOperand = parseInput(request.getSecondOperand(), request.getNumberType());

            List<Expression> params = new ArrayList<>();
            params.add(firtsOperand);
            params.add(secondOperand);

            Expression operation = switch (request.getOperator()) {
                case "+" -> new Plus(params);
                case "-" -> new Minus(params);
                case "*" -> new Times(params);
                case "/" -> new Divides(params);
                default -> throw new IllegalArgumentException("Invalid operator: " + request.getOperator());
            };

            Expression result = calculator.eval(operation);

            return result.toString();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }


    }

    private Expression parseInput(String input, String numberType) {
        switch (numberType) {
            case "INTEGER" -> {
                return new MyNumber(Integer.parseInt(input));
            }
            case "RATIONAL" -> {
                if (input.contains("/")) {
                    String[] parts = input.split("/");
                    return new RationalNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
                }
                return new RationalNumber(Integer.parseInt(input), 1);
            }
            case "REAL" -> {
                return new RealNumber(Double.parseDouble(input), 6);
            }
            case "COMPLEX" -> {
                return parseComplexNumber(input);
            }
            default -> throw new IllegalArgumentException("Invalid number type: " + numberType);
        }
    }

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
