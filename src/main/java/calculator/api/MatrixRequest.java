package calculator.api;

public class MatrixRequest {
    private double[][] matrix;
    private double[][] matrixB;
    private String scalar;
    private String operator;

    public MatrixRequest() { /* empty constructor */ }

    public double[][] getMatrix() {return matrix;}
    public void setMatrix(double[][] matrix) {this.matrix = matrix;}

    public double[][] getMatrixB() {return matrixB;}
    public void setMatrixB(double[][] matrixB) {this.matrixB = matrixB;}

    public String getScalar() {return scalar;}
    public void setScalar(String scalar) {this.scalar = scalar;}

    public String getOperator() {return operator;}
    public void setOperator(String operator) {this.operator = operator;}
}
