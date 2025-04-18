package calculator.operations;

import calculator.*;
import calculator.numbers.RationalNumber;
import calculator.numbers.ComplexNumber;
import calculator.matrix.Matrix;

import java.util.List;

/** This class represents the arithmetic operation "-".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 */
public final class Minus extends Operation
  {

   /**
    * Class constructor specifying a number of Expressions to subtract.
    *
    * @param elist The list of Expressions to subtract
    * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
    * @see #Minus(List< Expression >, Notation )
    */
   public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
     this(elist, null);
   }

   /**
    * Class constructor specifying a number of Expressions to subtract,
    * as well as the Notation used to represent the operation.
    *
    * @param elist The list of Expressions to subtract
    * @param n The Notation to be used to represent the operation
    * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
    * @see #Minus(List<Expression>)
    * @see Operation#Operation(List<Expression>,Notation)
    */
   public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
     super(elist,n);
     symbol = "-";
     neutral = 0;
   }

     /**
      * The actual computation of the (binary) arithmetic subtraction of two integers
      * @param l The first integer
      * @param r The second integer that should be subtracted from the first
      * @return The integer that is the result of the subtraction
      */
   public int op(int l, int r) {
     return (l-r);
   }

   @Override
   public double opReal(double l, double r) {
     return l - r;
   }

   @Override
   public RationalNumber opRational(RationalNumber l, RationalNumber r) {
     return l.subtract(r);
   }

   @Override
   public ComplexNumber opComplex(ComplexNumber l, ComplexNumber r) {
     return l.subtract(r);
   }

   @Override
   public Matrix opMatrix(Matrix l, Matrix r) {
    return l.subtract(r);
   }

   @Override
   public Matrix opMatrix(Matrix l, double r) {
     throw new ArithmeticException("Cannot subtract a scalar to a matrix");
   }

  @Override
  public Matrix opMatrix(Matrix l) {
      throw new ArithmeticException("Need two matrix to subtract");
  }
 }
