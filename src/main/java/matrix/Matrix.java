package matrix;

public class Matrix {

    private final double[][] values;

    public Matrix(double[][] values){

        if (values.length == 0 || values[0].length == 0){
            throw new IllegalArgumentException("Empty matrix not allowed !!!!");
        }

        this.values = values;
    }

    public Matrix(String matrix){
        this.values = parse(matrix);
    }

    public int rows(){
        return values.length;
    }

    public int columns(){
        return values[0].length;
    }

    public double getValue(int row, int column){
        return values[row][column];
    }

    public Matrix add(Matrix other) {
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

    public Matrix substract(Matrix other) {
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

    public Matrix multiply(Matrix other) {
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

    public Matrix identity() {

        if(rows() != columns()){
            throw new IllegalArgumentException("Matrix must be square to be inverted");
        }

        double result[][] = new double[columns()][rows()];

        for (int row = 0; row < rows(); row++)
            result[row][row] = 1;

        return new Matrix(result);
    }

    public Matrix transpose(){

        double result[][] = new double[columns()][rows()];

        for(int row = 0; row < rows(); row++) {
            for(int column = 0; column < columns(); column++) {
                result[column][row] = getValue(row, column);
            }
        }
        return new Matrix(result);
    }

    public Matrix inverse(){

        if(rows() != columns()){
            throw new IllegalArgumentException("Matrix must be square to be inverted");
        }

        double result[][] = new double[columns()][rows()];

        return new Matrix(result);

    }


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
