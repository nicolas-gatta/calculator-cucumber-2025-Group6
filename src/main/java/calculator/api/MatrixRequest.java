package calculator.api;

/**
 * DTO (Data Transfer Object) used to encapsulate a request for a matrix operation.
 * <p>
 * This class holds input matrices, a scalar value if needed, and the operation to perform.
 * Supported operations include: add, subtract, multiply, scalar, divide, transpose, inverse, identity.
 * </p>
 */
public class MatrixRequest {
    /** The first matrix involved in the operation. */
    private double[][] matrix;
    /** The second matrix used for binary operations like addition or multiplication. */
    private double[][] matrixB;
    /** A scalar value used for scalar multiplication or division. */
    private String scalar;
    /** The operation to perform (e.g., "add", "multiply", "scalar", etc.). */
    private String operator;

    /** Default constructor. */
    public MatrixRequest() { /* empty constructor */ }

    /**
     * Gets the first matrix.
     * @return a 2D array representing the first matrix
     */
    public double[][] getMatrix() {return matrix;}
    /**
     * Sets the first matrix.
     * @param matrix the matrix to set
     */
    public void setMatrix(double[][] matrix) {this.matrix = matrix;}

    /**
     * Gets the second matrix.
     * @return a 2D array representing the second matrix
     */
    public double[][] getMatrixB() {return matrixB;}
    /**
     * Sets the second matrix.
     * @param matrixB the matrix to set
     */
    public void setMatrixB(double[][] matrixB) {this.matrixB = matrixB;}

    /**
     * Gets the scalar value.
     * @return the scalar as a string
     */
    public String getScalar() {return scalar;}
    /**
     * Sets the scalar value.
     * @param scalar the scalar to set
     */
    public void setScalar(String scalar) {this.scalar = scalar;}

    /**
     * Gets the operation type.
     * @return the operator string
     */
    public String getOperator() {return operator;}
    /**
     * Sets the operation type.
     * @param operator the operator to set (e.g., "add", "multiply")
     */
    public void setOperator(String operator) {this.operator = operator;}
}
