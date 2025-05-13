package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.matrix.Matrix;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;

import java.util.List;

/**
 * Operation that calculates the transpose of a matrix.
 * The transpose of a matrix is obtained by flipping the matrix over its main diagonal,
 * switching the row and column indices of each element.
 */
public final class MatrixTranspose extends Operation{

    /**
     * Constructs a MatrixTranspose operation with the given list of expressions.
     * 
     * @param elist the list of expressions to operate on
     * @throws IllegalConstruction if the construction is invalid
     */
    public MatrixTranspose(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Constructs a MatrixTranspose operation with the given list of expressions and notation.
     * 
     * @param elist the list of expressions to operate on
     * @param n the notation to use
     * @throws IllegalConstruction if the construction is invalid
     */
    public MatrixTranspose(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "T";
        neutral = 0;
    }

    @Override
    public int op(int left, int right) {
        throw new ArithmeticException("Transpose Operation only for Matrix, does not support integer operations");
    }

    @Override
    public double opReal(double l, double r) {
        throw new ArithmeticException("Transpose Operation only for Matrix, does not support real operations");
    }

    @Override
    public RationalNumber opRational(RationalNumber l, RationalNumber r) {
        throw new ArithmeticException("Transpose Operation only for Matrix, does not support Rational operations");
    }

    @Override
    public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
        throw new ArithmeticException("Transpose Operation only for Matrix, does not support Complex operations");
    }

    @Override
    public Matrix opMatrix(Matrix l, Matrix r) {
        throw new ArithmeticException("Transpose Operation only for One matrix, does not support two matrices operations");
    }

    @Override
    public Matrix opMatrix(Matrix l, double r) {
        throw new ArithmeticException("Transpose Operation only for One matrix, does not matrix and double operations");
    }

    @Override
    public Matrix opMatrix(Matrix l) {
        return l.transpose();
    }

}
