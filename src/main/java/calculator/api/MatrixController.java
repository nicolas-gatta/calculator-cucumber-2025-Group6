package calculator.api;

import calculator.Calculator;
import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.RealNumber;
import calculator.operations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * REST controller for handling matrix-related operations.
 * <p>
 * Supports matrix addition, subtraction, multiplication (by another matrix or scalar),
 * division by scalar, transpose, inverse, and identity matrix generation.
 * </p>
 */
@RestController
@RequestMapping("/api/matrix")
public class MatrixController {

    /**
     * Default constructor for MatrixController.
     */
    public MatrixController() {
        // Default constructor
    }

    /** Calculator instance used to evaluate matrix operations. */
    Calculator calculator = new Calculator();

    /**
     * Endpoint to perform a matrix operation based on the provided request.
     *
     * @param request The {@link MatrixRequest} containing matrix data and the operator to apply.
     * @return The result of the matrix operation as a string.
     * @throws IllegalArgumentException if the operation is invalid or the input is malformed.
     */
    @PostMapping(value = "/calculate", produces = "text/plain")
    public String calculate(@RequestBody MatrixRequest request) {
        Expression firstMatrix = parseMatrix(request.getMatrix());
        List<Expression> params = new ArrayList<>();
        try{
            Expression operation = switch (request.getOperator()) {
                case "add" -> {
                    Expression secondMatrix = parseMatrix(request.getMatrixB());
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    if(secondMatrix != null) {
                        params.add(secondMatrix);
                    }
                    yield new Plus(params);
                }
                case "subtract" -> {
                    Expression secondMatrix = parseMatrix(request.getMatrixB());
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    if(secondMatrix != null) {
                        params.add(secondMatrix);
                    }
                    yield new Minus(params);
                }
                case "multiply" -> {
                    Expression secondMatrix = parseMatrix(request.getMatrixB());
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    if(secondMatrix != null) {
                        params.add(secondMatrix);
                    }
                    yield new Times(params);
                }
                case "scalar" -> {
                    Expression scalar = parse(request.getScalar());
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    if(scalar != null) {
                        params.add(scalar);
                    }
                    yield new Times(params);
                }
                case "divide" -> {
                    Expression scalar = parse(request.getScalar());
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    if(scalar != null) {
                        params.add(scalar);
                    }
                    yield new Divides(params);
                }
                case "transpose" -> {
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    yield new MatrixTranspose(params);
                }
                case "inverse" -> {
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    yield new MatrixInverted(params);
                }
                case "identity" -> {
                    if(firstMatrix != null) {
                        params.add(firstMatrix);
                    }
                    yield new MatrixIdentity(params);
                }
                default -> throw new IllegalArgumentException("Invalid operator: " + request.getOperator());
            };

            Expression result = calculator.eval(operation);

            return result.toString();
        }catch (IllegalConstruction e){
            throw new IllegalArgumentException("Illegal construction: " + e.getMessage());
        }
    }

    /**
     * Helper method to convert a raw 2D array into a {@link MatrixExpression}.
     *
     * @param matrix the matrix as a 2D array of doubles
     * @return a MatrixExpression or null if the input is empty
     */
    private Expression parseMatrix(double[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        return new MatrixExpression(new Matrix(matrix));
    }

    /**
     * Helper method to parse a scalar string into a {@link RealNumber}.
     *
     * @param value the scalar value as string
     * @return a RealNumber or null if the input is empty
     */
    private Expression parse(String value){
        if(value == null || value.isEmpty()) {
            return null;
        }
        return new RealNumber(Double.parseDouble(value));
    }
}
