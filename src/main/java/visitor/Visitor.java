package visitor;

import calculator.numbers.MyNumber;
import calculator.operations.Operation;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;

import calculator.matrix.MatrixExpression;

/**
 * Visitor design pattern
 */
public abstract class Visitor {

    /**
     * Default constructor for the Visitor class.
     */
    protected Visitor() {
    }

    /**
     * The Visitor can traverse a number (a subtype of Expression)
     *
     * @param n The number being visited
     */
    public abstract void visit(MyNumber n);

    /**
     * The Visitor can traverse an operation (a subtype of Expression)
     *
     * @param o The operation being visited
     */   public abstract void visit(Operation o);

     /**
      * The Visitor can traverse a real number (a subtype of Expression)
      *
      * @param n The real number being visited
      */
     public abstract void visit(RealNumber n);

     /**
      * The Visitor can traverse a complex number (a subtype of Expression)
      *
      * @param n The complex number being visited
      */
     public abstract void visit(ComplexNumber n);

     /**
      * The Visitor can traverse a rational number (a subtype of Expression)
      *
      * @param n The rational number being visited
      */
     public abstract void visit(RationalNumber n);

    /**
     * The Visitor can traverse a Matrix (a subtype of Expression)
     *
     * @param n The matrix being visited
     */
    public abstract void visit(MatrixExpression n);
}
