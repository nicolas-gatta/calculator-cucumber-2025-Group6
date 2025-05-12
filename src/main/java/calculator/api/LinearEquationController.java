package calculator.api;

import calculator.linear.LinearEquationSolver;
import calculator.linear.LinearEquationSystemExpression;
import calculator.matrix.Matrix;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * REST controller for solving systems of linear equations.
 * <p>
 * Exposes an API endpoint to solve a system of equations defined by a coefficient matrix and a constants matrix.
 * </p>
 */
@RestController
@RequestMapping("/api/linearEquation")
public class LinearEquationController {

    /**
     * Default constructor for LinearEquationController.
     */
    public LinearEquationController() {
        // Default constructor
    }

    /**
     * Solves a system of linear equations based on the provided request data.
     *
     * @param request the {@link LinearEquationRequest} containing the coefficient matrix, constant matrix, and variable names
     * @return a string representation of the solution
     * @throws ResponseStatusException if an error occurs during the solving process
     */
    @PostMapping(value ="/solve", produces = "text/plain")
    public String solveLinearSystem(@RequestBody LinearEquationRequest request) {
        try {
            Matrix matrixA = new Matrix(request.getCoefficients());
            Matrix matrixB = new Matrix(request.getConstants());

            LinearEquationSystemExpression result = LinearEquationSolver.solve(
                    matrixA, matrixB, request.getVariables()
            );

            return result.toString();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while solving the system: " + e.getMessage());
        }
    }
}
