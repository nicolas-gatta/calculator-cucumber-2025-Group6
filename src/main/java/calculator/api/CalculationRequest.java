package calculator.api;

/**
 * Represents a request for a basic calculation operation.
 * <p>
 * This class is used to encapsulate all required inputs for a calculation,
 * such as the operands, the operator, and the number type to be used
 * (e.g., integer, rational, real, etc.).
 * </p>
 */
public class CalculationRequest {

    /** First operand of the operation, represented as a String. */
    private String firstOperand;

    /** Operator for the operation (e.g., "+", "-", "*", "/"). */
    private String operator;

    /** Second operand of the operation, represented as a String. */
    private String secondOperand;

    /** Type of number system to use (e.g., "integer", "rational", "real"). */
    private String numberType;

    /**
     * Default constructor.
     * <p>
     * Required for frameworks such as Spring when deserializing from JSON.
     * </p>
     */
    public CalculationRequest() {
        // empty constructor
    }

    /** @return the first operand as a String */
    public String getFirstOperand() { return firstOperand; }

    /**
     * Sets the first operand.
     *
     * @param firstOperand the first operand as a String
     */
    public void setFirstOperand(String firstOperand) { this.firstOperand = firstOperand; }

    /** @return the operator as a String (e.g., "+", "-", "*", "/") */
    public String getOperator() { return operator; }
    /**
     * Sets the operator.
     *
     * @param operator the operator as a String (e.g., "+", "-", "*", "/")
     */
    public void setOperator(String operator) { this.operator = operator; }

    /** @return the second operand as a String */
    public String getSecondOperand() { return secondOperand; }
    /**
     * Sets the second operand.
     *
     * @param secondOperand the second operand as a String
     */
    public void setSecondOperand(String secondOperand) { this.secondOperand = secondOperand; }

    /** @return the number type as a String (e.g., "integer", "rational", "real") */
    public String getNumberType() { return numberType; }
    /**
     * Sets the type of number system.
     *
     * @param numberType the number type as a String (e.g., "integer", "rational", "real")
     */
    public void setNumberType(String numberType) { this.numberType = numberType; }

}
