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

@RestController
@RequestMapping("/api/linearEquation")
public class LinearEquationController {

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
