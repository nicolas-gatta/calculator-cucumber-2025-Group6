package calculator.matrix;

/**
 * Class that represents a matrix and provides various operations
 */
public class Matrix {

    private final double[][] values;


    /**
     * Constructs a matrix from a 2D array of values.
     *
     * @param values the 2D array representing matrix values
     * @throws IllegalArgumentException if the matrix is empty or has no columns
     */
    public Matrix(double[][] values){

        if (values.length == 0 || values[0].length == 0){
            throw new IllegalArgumentException("Empty matrix not allowed !!!!");
        }

        this.values = values;
    }

    /**
     * Constructs a matrix from a string representation.
     * Example: "[[1,2],[3,4]]"
     *
     * @param matrix the matrix in string format
     * @throws IllegalArgumentException if the format is invalid or inconsistent
     */
    public Matrix(String matrix){
        this.values = parse(matrix);
    }

    /**
     * The number of rows in the matrix
     *
     * @return the number of rows in the matrix
     */
    public int rows(){
        return values.length;
    }

    /**
     * The number of columns in the matrix
     *
     * @return the number of columns in the matrix
     */
    public int columns(){
        return values[0].length;
    }

    /**
     * Gets the value at a specific position.
     *
     * @param row the row index
     * @param column the column index
     * @return the value at (row, column)
     */
    public double getValue(int row, int column){
        return values[row][column];
    }

    /**
     * Adds the current matrix with another matrix.
     *
     * @param other the matrix to add
     * @return the result of addition
     * @throws IllegalArgumentException if the matrices do not have the same dimensions
     */
    public Matrix add(Matrix other) {

        if(rows() != other.rows() || columns()!= other.columns()){
            throw new IllegalArgumentException("Matrix must have the same number of columns and rows to execute the addition");
        }

        int num_row = rows();
        int num_column = columns();
        double[][] result = new double[num_row][num_column];

        for (int row = 0; row < num_row; row++) {
            for (int column = 0; column < num_column; column++) {
                result[row][column] = getValue(row, column) + other.getValue(row, column);
            }
        }
        return new Matrix(result);
    }

    /**
     * Substract the current matrix with another matrix.
     *
     * @param other the matrix to add
     * @return the result of substraction
     * @throws IllegalArgumentException if the matrices do not have the same dimensions
     */
    public Matrix subtract(Matrix other) {

        if(rows() != other.rows() || columns()!= other.columns()){
            throw new IllegalArgumentException("Matrix must have the same number of columns and rows to execute the subtraction");
        }

        int num_row = rows();
        int num_column = columns();
        double[][] result = new double[num_row][num_column];

        for (int row = 0; row < num_row; row++) {
            for (int column = 0; column < num_column; column++) {
                result[row][column] = getValue(row, column) - other.getValue(row, column);
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies the current matrix with another matrix.
     *
     * @param other the matrix to multiply with
     * @return the result of matrix multiplication
     * @throws IllegalArgumentException if the column count of this matrix doesn't match the row count of the other
     */
    public Matrix multiply(Matrix other) {

        if(columns() != other.rows()){
            throw new IllegalArgumentException("Matrix A should have the same number of column as the number of row in the Matrix B");
        }

        int num_row_a = rows();
        int num_column_a = columns();
        int num_column_o = other.columns();
        double[][] result = new double[num_row_a][num_column_o];

        for (int row_a = 0; row_a < num_row_a; row_a++) {
            for (int column_o = 0; column_o < num_column_o; column_o++){
                for (int column_a = 0; column_a < num_column_a; column_a++) {
                    result[row_a][column_o] += getValue(row_a, column_a) * other.getValue(column_a, column_o);
                }
            }
        }

        return new Matrix(result);
    }

    /**
     * Multiplies each element of the matrix by a scalar.
     *
     * @param number the scalar multiplier
     * @return the scaled matrix
     */
    public Matrix multiply(double number) {

        int num_row = rows();
        int num_column = columns();
        double[][] result = new double[num_row][num_column];

        for (int row = 0; row < num_row; row++) {
            for (int column = 0; column < num_column; column++){
                result[row][column] += getValue(row, column) * number;
            }
        }
        return new Matrix(result);
    }


    /**
     * Divides each element of the matrix by a scalar.
     *
     * @param number the scalar divisor
     * @return the scaled matrix
     */
    public Matrix divide(double number) {

        int num_row = rows();
        int num_column = columns();
        double[][] result = new double[num_row][num_column];

        for (int row = 0; row < num_row; row++) {
            for (int column = 0; column < num_column; column++){
                result[row][column] += getValue(row, column) * number;
            }
        }
        return new Matrix(result);
    }

    /**
     * Generates the identity of the matrix (must be square).
     *
     * @return the identity matrix
     * @throws IllegalArgumentException if the matrix is not square
     */
    public Matrix identity() {

        if(rows() != columns()){
            throw new IllegalArgumentException("Matrix must be square to be inverted");
        }

        double result[][] = new double[columns()][rows()];

        for (int row = 0; row < rows(); row++)
            result[row][row] = 1;

        return new Matrix(result);
    }

    /**
     * Generate the transposition of the matrix
     *
     * @return the transpose of the matrix
     */
    public Matrix transpose(){

        double result[][] = new double[columns()][rows()];

        for(int row = 0; row < rows(); row++) {
            for(int column = 0; column < columns(); column++) {
                result[column][row] = getValue(row, column);
            }
        }
        return new Matrix(result);
    }

    /**
     * Calculates the cofactor matrix of the current matrix.
     *
     * @return the cofactor matrix
     */
    public Matrix cofactor()  {
        int num_row = rows();
        int num_column = columns();

        double[][] result = new double [num_row][num_column];
        for (int row = 0; row < num_row; row++) {
            for (int column = 0; column < num_column; column++) {
                result[row][column] = (row % 2 == 0 ? 1 : -1) *  (column % 2 == 0 ? 1 : -1) * createSubMatrix(row, column).determinant();
                if(result[row][column] == 0.0){
                    result[row][column] = 0.0;
                }
            }
        }

        return new Matrix(result);
    }


    /**
     * Creates a submatrix excluding the specified row and column.
     *
     * @param excludingRow the row to exclude
     * @param excludingColumn the column to exclude
     * @return the resulting submatrix
     */
    public Matrix createSubMatrix(int excludingRow, int excludingColumn) {
        int numRows = rows();
        int numCols = columns();

        double[][] subMatrix = new double[numRows - 1][numCols - 1];

        int real_row = 0;
        for (int row = 0; row < numRows; row++) {
            if (row != excludingRow){
                int real_column = 0;
                for (int column = 0; column < numCols; column++) {
                    if (column != excludingColumn){
                        subMatrix[real_row][real_column] = getValue(row, column);
                        real_column++;
                    }
                }
                real_row++;
            }

        }

        return new Matrix(subMatrix);
    }

    /**
     * Computes the determinant of the matrix (must be square).
     *
     * @return the determinant value
     * @throws IllegalArgumentException if the matrix is not square
     */
    public double determinant() {

        if (rows() != columns()){
            throw new IllegalArgumentException("matrix need to be square.");
        }

        if (rows() == 1) {
            return getValue(0, 0);
        }

        if (rows() == 2) {
            return (getValue(0, 0) * getValue(1, 1)) -
                    ( getValue(0, 1) * getValue(1, 0));
        }

        double result = 0.0;

        for (int column = 0; column < columns(); column++) {
            result += (column % 2 == 0 ? 1 : -1) * getValue(0, column) * createSubMatrix(0, column).determinant();
        }
        return result;
    }

    /**
     * Calculates the inverse of the matrix (using cofactor and transpose).
     *
     * @return the inverse matrix
     * @throws IllegalArgumentException if the matrix is not square
     */
    public Matrix inverse(){

        if(rows() != columns()){
            throw new IllegalArgumentException("Matrix must be square to be inverted");
        }

        return (cofactor().transpose());

    }

    /**
     * Convert the matrix into the string representation
     *
     * @return a string representation of the matrix in the format [[a,b],[c,d]]
     */
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("[");

        int num_row = rows();

        int num_column = columns();

        for (int row = 0; row < num_row; row++) {

            stringBuilder.append("[");

            for (int column = 0; column < num_column; column++) {

                stringBuilder.append(values[row][column]);

                if (column < num_column - 1){
                    stringBuilder.append(",");
                }
            }

            stringBuilder.append("]");

            if (row < num_row - 1){
                stringBuilder.append(",");
            }

        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    private double[][] parse(String input){

        if (!input.startsWith("[[") || !input.endsWith("]]")){
            throw new IllegalArgumentException("Invalid Format for a Matrix, should be: [[1,2],[3,4]]");
        }

        input = input.substring(1, input.length() - 1);

        String[] rows = input.split("(?<=]),");

        if (rows.length == 0){
            throw new IllegalArgumentException("Matrix cannot be empty");
        }

        int num_column = -1;

        double [][] values = new double[rows.length][];

        for (int index_r = 0; index_r < rows.length; index_r++) {
            String[] columns = rows[index_r].replaceAll("\\[|\\]", "").split(",");

            if (num_column == -1 && columns.length != 0) {
                num_column = columns.length;
            }else if (num_column != columns.length){
                throw new IllegalArgumentException("Column should be the same size !!");
            }

            values[index_r] = new double[num_column];

            for (int index_c = 0; index_c < num_column; index_c++) {
                values[index_r][index_c] = Double.parseDouble(columns[index_c]);
            }
        }
        return values;
    }

}
