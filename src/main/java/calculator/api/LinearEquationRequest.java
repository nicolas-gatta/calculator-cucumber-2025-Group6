package calculator.api;

import java.util.List;

/**
 * DTO (Data Transfer Object) representing a request to solve a system of linear equations.
 * <p>
 * Contains the coefficient matrix, the constants (right-hand side) matrix, and the list of variable names.
 * </p>
 */
public class LinearEquationRequest {
    /** Matrix representing the coefficients of the linear system (Ax = B). */
    private double[][] coefficients;
    /** Matrix representing the constants (right-hand side) of the linear system. */
    private double[][] constants;
    /** List of variable names (e.g., ["x", "y", "z"]). */
    private List<String> variables;

    /**
     * Default constructor.
     */
    public LinearEquationRequest() {
        // Empty constructor
    }

    /**
     * Gets the coefficient matrix.
     * @return the coefficient matrix
     */
    public double[][] getCoefficients() { return coefficients; }
    /**
     * Sets the coefficient matrix.
     * @param coefficients the matrix of coefficients
     */
    public void setCoefficients(double[][] coefficients) { this.coefficients = coefficients; }

    /**
     * Gets the constants matrix.
     * @return the constants matrix
     */
    public double[][] getConstants() { return constants; }
    /**
     * Sets the constants matrix.
     * @param constants the matrix of constants
     */
    public void setConstants(double[][] constants) { this.constants = constants; }

    /**
     * Gets the list of variable names.
     * @return the list of variable names
     */
    public List<String> getVariables() { return variables; }
    /**
     * Sets the variable names.
     * @param variables the list of variable names
     */
    public void setVariables(List<String> variables) { this.variables = variables; }

}
