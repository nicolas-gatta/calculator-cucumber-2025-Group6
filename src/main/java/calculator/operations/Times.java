package calculator.operations;

import calculator.*;
import calculator.numbers.RationalNumber;
import calculator.numbers.ComplexNumber;
import calculator.matrix.Matrix;

import java.util.List;

/** This class represents the arithmetic multiplication operation "*".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Plus
 * @see Divides
 */
public final class Times extends Operation
 {
  /**
   * Class constructor specifying a number of Expressions to multiply.
   *
   * @param elist The list of Expressions to multiply
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List< Expression >, Notation )
   */
  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to multiply,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to multiply
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "*";
  	neutral = 1;
  }

  /**
   * The actual computation of the (binary) arithmetic multiplication of two integers
   * @param l The first integer
   * @param r The second integer that should be multiplied with the first
   * @return The integer that is the result of the multiplication
   */
  public int op(int l, int r)
    { return (l*r); }

  @Override
  public double opReal(double l, double r) {
    return l * r;
  }

  @Override
  public RationalNumber opRational(RationalNumber l, RationalNumber r) {
    return l.multiply(r);
  }

  @Override
  public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
    return l.multiply(r);
  }

  @Override
  public Matrix opMatrix(Matrix l, Matrix r) {
   return l.multiply(r);
  }

  public Matrix opMatrix(Matrix l, double r) {
   return l.multiply(r);
  }

  @Override
  public Matrix opMatrix(Matrix l) {
   throw new ArithmeticException("Need two matrix to multiply");
  }
}
