package calculator.operations;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.numbers.RationalNumber;
import calculator.numbers.ComplexNumber;
import calculator.matrix.Matrix;

import java.util.List;

/** This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation
{

  /**
   * Class constructor specifying a number of Expressions to divide.
   *
   * @param elist The list of Expressions to divide
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Divides(List< Expression >, Notation )
   */
  public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

    /**
     * Class constructor specifying a number of Expressions to divide,
     * as well as the notation used to represent the operation.
     *
     * @param elist The list of Expressions to divide
     * @param n The Notation to be used to represent the operation
     * @throws IllegalConstruction  If an empty list of expressions if passed as parameter
     * @see #Divides(List<Expression>)
     * @see Operation#Operation(List<Expression>,Notation)
     */
  public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "/";
	neutral = 1;
  }

    /**
     * The actual computation of the (binary) arithmetic division of two integers
     * @param l The first integer
     * @param r The second integer that should divide the first
     * @return The integer that is the result of the division
     */
  @Override
  public int op(int l, int r) {
    if (r == 0) {
        throw new ArithmeticException("Division by zero");
    }
    return l / r;
  }

    @Override
    public double opReal(double l, double r) {
        if (r == 0.0) {
            throw new ArithmeticException("Division by zero");
        }
        return l / r;
    }

    @Override
    public RationalNumber opRational(RationalNumber l, RationalNumber r) {
        return l.divide(r);
    }

    @Override
    public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
        return l.divide(r);
    }

    @Override
    public Matrix opMatrix(Matrix l, Matrix r) {
        throw new ArithmeticException("Cannot divide two matrix");
    }

    @Override
    public Matrix opMatrix(Matrix l, double r) {
        return l.divide(r);
    }

    @Override
    public Matrix opMatrix(Matrix l) {
        throw new ArithmeticException("Need two matrix to divide");
    }
}
