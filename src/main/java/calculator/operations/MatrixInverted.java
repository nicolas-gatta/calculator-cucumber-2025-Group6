package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.matrix.Matrix;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;

import java.util.List;

/**
 * Operation that calculates the inverse of a matrix.
 * The inverse of a matrix A is a matrix A^-1 such that A * A^-1 = I, where I is the identity matrix.
 */
public final class MatrixInverted extends Operation{

    /**
     * Constructs a MatrixInverted operation with the given list of expressions.
     * 
     * @param elist the list of expressions to operate on
     * @throws IllegalConstruction if the construction is invalid
     */
    public MatrixInverted(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    /**
     * Constructs a MatrixInverted operation with the given list of expressions and notation.
     * 
     * @param elist the list of expressions to operate on
     * @param n the notation to use
     * @throws IllegalConstruction if the construction is invalid
     */
    public MatrixInverted(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "-1";
        neutral = 0;
    }

    @Override
    public int op(int left, int right) {
        throw new ArithmeticException("Inverted Operation only for Matrix, does not support integer operations");
    }

    @Override
    public double opReal(double l, double r) {
        throw new ArithmeticException("Inverted Operation only for Matrix, does not support real operations");
    }

    @Override
    public RationalNumber opRational(RationalNumber l, RationalNumber r) {
        throw new ArithmeticException("Inverted Operation only for Matrix, does not support Rational operations");
    }

    @Override
    public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
        throw new ArithmeticException("Inverted Operation only for Matrix, does not support Complex operations");
    }

    @Override
    public Matrix opMatrix(Matrix l, Matrix r) {
        throw new ArithmeticException("Inverted Operation only for One matrix, does not support two matrices operations");
    }

    @Override
    public Matrix opMatrix(Matrix l, double r) {
        throw new ArithmeticException("Inverted Operation only for One matrix, does not matrix and double operations");
    }

    @Override
    public Matrix opMatrix(Matrix l) {
        return l.inverse();
    }

}
