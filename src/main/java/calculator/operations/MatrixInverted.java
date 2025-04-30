package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.matrix.Matrix;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;

import java.util.List;

public final class MatrixInverted extends Operation{

    public MatrixInverted(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public MatrixInverted(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "-1";
        neutral = 0;
    }

    @Override
    public int op(int left, int right) {
        throw new ArithmeticException("Operation only for Matrix");
    }

    @Override
    public double opReal(double l, double r) {
        throw new ArithmeticException("Operation only for Matrix");
    }

    @Override
    public RationalNumber opRational(RationalNumber l, RationalNumber r) {
        throw new ArithmeticException("Operation only for Matrix");
    }

    @Override
    public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
        throw new ArithmeticException("Operation only for Matrix");
    }

    @Override
    public Matrix opMatrix(Matrix l, Matrix r) {
        throw new ArithmeticException("Operation only for ONE Matrix");
    }

    @Override
    public Matrix opMatrix(Matrix l, double r) {
        throw new ArithmeticException("Operation only for Matrix");
    }

    @Override
    public Matrix opMatrix(Matrix l) {
        return l.inverse();
    }

}
