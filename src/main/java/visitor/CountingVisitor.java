package visitor;

import calculator.numbers.MyNumber;
import calculator.operations.Operation;
import calculator.Expression;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;

/**
 * A visitor that counts various metrics of an expression tree:
 * - depth of the expression tree
 * - number of operations
 * - number of numeric values
 */
public class CountingVisitor extends Visitor {
    private int depth = 0;
    private int operations = 0;
    private int numbers = 0;

    /**
     * Default constructor for CountingVisitor.
     * Initializes all counters to zero.
     */
    public CountingVisitor() {
        depth = 0;
        operations = 0;
        numbers = 0;
    }

    /**
     * Visits a number node in the expression tree.
     * Sets the counters for a leaf node: 1 number, 0 operations, depth 0.
     *
     * @param n The number node being visited
     */
    @Override
    public void visit(MyNumber n) {
        numbers = 1;
        operations = 0;
        depth = 0;
    }

    /**
     * Visits a real number node in the expression tree.
     * Sets the counters for a leaf node: 1 number, 0 operations, depth 0.
     *
     * @param n The real number node being visited
     */
    @Override
    public void visit(RealNumber n) {
        numbers = 1;
        operations = 0;
        depth = 0;
    }

    /**
     * Visits a complex number node in the expression tree.
     * Sets the counters for a leaf node: 1 number, 0 operations, depth 0.
     *
     * @param n The complex number node being visited
     */
    @Override
    public void visit(ComplexNumber n) {
        numbers = 1;
        operations = 0;
        depth = 0;
    }

    /** 
     * Visits a rational number node in the expression tree.
     * Sets the counters for a leaf node: 1 number, 0 operations, depth 0.
     *
     * @param n The rational number node being visited
     */
    @Override
    public void visit(RationalNumber n) {
        numbers = 1;
        operations = 0;
        depth = 0;
    }
    

    /**
     * Visits an operation node in the expression tree.
     * Recursively visits all arguments and accumulates their metrics.
     *
     * @param o The operation node being visited
     */
    @Override
    public void visit(Operation o) {
        int maxDepth = 0;
        int totalOps = 0;
        int totalNums = 0;
        
        for (Expression e : o.getArgs()) {
            e.accept(this);
            maxDepth = Math.max(maxDepth, depth);
            totalOps += operations;
            totalNums += numbers;
        }
        
        depth = 1 + maxDepth;
        operations = 1 + totalOps;
        numbers = totalNums;
    }

    /**
     * Gets the maximum depth of the expression tree.
     * @return The maximum depth of the expression tree
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Gets the total number of operations in the expression tree.
     * @return The total number of operations in the expression tree
     */
    public int getOperations() {
        return operations;
    }

    /**
     * Gets the total number of numeric values in the expression tree.
     * @return The total number of numeric values in the expression tree
     */
    public int getNumbers() {
        return numbers;
    }
}
