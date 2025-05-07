package calculator.api;

public class CalculationRequest {
    private String firstOperand;
    private String operator;
    private String secondOperand;
    private String numberType;

    public CalculationRequest() {
        // empty constructor
    }

    public String getFirstOperand() { return firstOperand; }
    public void setFirstOperand(String firstOperand) { this.firstOperand = firstOperand; }

    public String getOperator() { return operator; }
    public void setOperator(String operator) { this.operator = operator; }

    public String getSecondOperand() { return secondOperand; }
    public void setSecondOperand(String secondOperand) { this.secondOperand = secondOperand; }

    public String getNumberType() { return numberType; }
    public void setNumberType(String numberType) { this.numberType = numberType; }

}
