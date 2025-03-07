package visitor;

import calculator.MyNumber;
import calculator.Operation;

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
}
