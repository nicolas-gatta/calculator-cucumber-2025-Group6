package visitor;

import calculator.*;

/** Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

    private String error;
    private Number result;

    /**
     * Default constructor of the class. Does not initialise anything.
     */
    public Evaluator() {}

    /** The result of the evaluation will be stored in this private variable */
    private double computedValue;

    /** getter method to obtain the result of the evaluation
     *
     * @return a Number object containing the result of the evaluation
     */
    public Number getResult() {
        if (error != null) {
            return null;
        }
        return result;
    }

    /**
     * Gets the error message if any error occurred during evaluation.
     * @return The error message or null if no error occurred.
     */
    public String getError() {
        return error;
    }

    /** Use the visitor design pattern to visit a number.
     *
     * @param n The number being visited
     */
    @Override
    public void visit(MyNumber n) {
        result = n.getValue();
        computedValue = n.getValue();
    }

    /** Use the visitor design pattern to visit an operation
     *
     * @param o The operation being visited
     */
    @Override
    public void visit(Operation o) {
        if (o.args.size() < 2) return;

        // Evaluate first argument
        o.args.get(0).accept(this);
        Number accumulator = result;

        // For operations with more than 2 arguments
        for (int i = 1; i < o.args.size(); i++) {
            o.args.get(i).accept(this);
            Number current = result;

            // Check for division by zero first
            if (o instanceof Divides &&
                (current.doubleValue() == 0 || current.intValue() == 0)) {
                result = null;
                return;
            }

            if (accumulator instanceof Integer && current instanceof Integer) {
                int accInt = ((Integer) accumulator).intValue();
                int currInt = ((Integer) current).intValue();

                if (o instanceof Plus)
                    accumulator = accInt + currInt;
                else if (o instanceof Minus)
                    accumulator = accInt - currInt;
                else if (o instanceof Times)
                    accumulator = accInt * currInt;
                else if (o instanceof Divides)
                    accumulator = accInt / currInt;
            } else {
                double accDouble = accumulator.doubleValue();
                double currDouble = current.doubleValue();

                if (o instanceof Plus)
                    accumulator = accDouble + currDouble;
                else if (o instanceof Minus)
                    accumulator = accDouble - currDouble;
                else if (o instanceof Times)
                    accumulator = accDouble * currDouble;
                else if (o instanceof Divides)
                    accumulator = accDouble / currDouble;
            }
        }

        result = accumulator;
        computedValue = result instanceof Integer ?
            ((Integer)result).doubleValue() : (Double)result;
    }

    @Override
    public void visit(RealNumber n) {
        result = n.getValue();
        computedValue = n.getValue();
    }

}
