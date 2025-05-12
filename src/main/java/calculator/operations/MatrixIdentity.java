package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.matrix.Matrix;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;

import java.util.List;

public final class MatrixIdentity extends Operation{

    public MatrixIdentity(List<Expression> elist) throws IllegalConstruction {
        this(elist, null);
    }

    public MatrixIdentity(List<Expression> elist, Notation n) throws IllegalConstruction {
        super(elist, n);
        symbol = "I";
        neutral = 0;
    }

    @Override
    public int op(int left, int right) {
        throw new ArithmeticException("Identity Operation only for Matrix, does not support integer operations");
    }

    @Override
    public double opReal(double l, double r) {
        throw new ArithmeticException("Identity Operation only for Matrix, does not support real operations");
    }

    @Override
    public RationalNumber opRational(RationalNumber l, RationalNumber r) {
        throw new ArithmeticException("Identity Operation only for Matrix, does not support Rational operations");
    }

    @Override
    public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
        throw new ArithmeticException("Identity Operation only for Matrix, does not support Complex operations");
    }

    @Override
    public Matrix opMatrix(Matrix l, Matrix r) {
        throw new ArithmeticException("Identity Operation only for One matrix, does not support two matrices operations");
    }

    @Override
    public Matrix opMatrix(Matrix l, double r) {
        throw new ArithmeticException("Identity Operation only for One matrix, does not matrix and double operations");
    }

    @Override
    public Matrix opMatrix(Matrix l) {
        return l.identity();
    }
}
