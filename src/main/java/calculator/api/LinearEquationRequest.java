package calculator.api;

import java.util.List;

public class LinearEquationRequest {
    private double[][] coefficients;
    private double[][] constants;
    private List<String> variables;

    public LinearEquationRequest() {
        // Empty constructor
    }

    public double[][] getCoefficients() { return coefficients; }
    public void setCoefficients(double[][] coefficients) { this.coefficients = coefficients; }
    public double[][] getConstants() { return constants; }
    public void setConstants(double[][] constants) { this.constants = constants; }
    public List<String> getVariables() { return variables; }
    public void setVariables(List<String> variables) { this.variables = variables; }

}
