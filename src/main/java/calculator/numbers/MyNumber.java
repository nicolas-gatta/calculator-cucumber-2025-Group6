package calculator.numbers;

import calculator.Expression;
import calculator.Notation;
import calculator.operations.Operation;
import visitor.Visitor;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class MyNumber implements Expression
{
    /** The integer value contained in the MyNumber object */
    private final int value;

    /** Getter method to obtain the value contained in the object
     *
     * @return The integer number contained in the object
     */
    public Integer getValue() { return value; }

    /**
     * Constructor method to create a new MyNumber with a specific value.
     *
     * @param v The integer value to be contained in the object
     */
    public /*constructor*/ MyNumber(int v) { value=v;}

    /**
     * Accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v The visitor object that will process this number
     */
    public void accept(Visitor v) {v.visit(this);}

    /**
     * Convert the number to its string representation using a StringVisitor.
     *
     * @return The string representation of the number
     */
    @Override
    public String toString() {
        return toString(Notation.INFIX); // Default, INFIX
    }

  /** Two MyNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return  A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
      // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
      if (o == null) return false;

      // If the object is compared to itself then return true
      if (o == this) return true;

      // If the object is of another type then return false
      if (!(o instanceof MyNumber)) return false;

      return this.value == ((MyNumber)o).value;
      // Used == since the contained value is a primitive value
      // If it had been a Java object, .equals() would be needed
  }

    /** The method hashCode needs to be overridden it the equals method is overridden;
     * 	otherwise there may be problems when you use your object in hashed collections
     * 	such as HashMap, HashSet, LinkedHashSet.
     *
     * @return	The result of computing the hash.
     */
  @Override
  public int hashCode() {
		return value;
  }
}
