package calculator;

import calculator.numbers.MyNumber;
import calculator.operations.Operation;
import visitor.Visitor;
import visitor.CountingVisitor;

/**
 * Expression is an abstract class that represents arithmetic expressions.
 * It has two concrete subclasses Operation and MyNumber.
 *
 * @see Operation
 * @see MyNumber
 */
public interface Expression {

   /**
    * accept is a method needed to implement the visitor design pattern
    *
    * @param v The visitor object being passed as a parameter
    */
   void accept(Visitor v);

   /**
    * Counts the depth of nested expressions in an arithmetic expression.
    * This method uses a CountingVisitor to traverse the expression tree
    * and compute its maximum depth.
    * For a number, the depth is 0.
    * For an operation, the depth is 1 plus the maximum depth of its arguments.
    *
    * @return The depth of an arithmetic expression
    * @see visitor.CountingVisitor
    */
   default int countDepth() {
      CountingVisitor cv = new CountingVisitor();
      this.accept(cv);
      return cv.getDepth();
   }

   /**
    * Counts the number of operations recursively contained in an arithmetic expression.
    * This method uses a CountingVisitor to traverse the expression tree
    * and count all operations.
    * For a number, the count is 0.
    * For an operation, the count is 1 plus the sum of its arguments' operations.
    *
    * @return The number of operations contained in an arithmetic expression
    * @see visitor.CountingVisitor
    */
   default int countOps() {
      CountingVisitor cv = new CountingVisitor();
      this.accept(cv);
      return cv.getOperations();
   }

   /**
    * Counts the number of values recursively contained in an arithmetic expression.
    * This method uses a CountingVisitor to traverse the expression tree
    * and count all number values.
    * For a number, the count is 1.
    * For an operation, the count is the sum of its arguments' numbers.
    *
    * @return The number of values contained in an arithmetic expression
    * @see visitor.CountingVisitor
    */
   default int countNbs() {
      CountingVisitor cv = new CountingVisitor();
      this.accept(cv);
      return cv.getNumbers();
   }
}
