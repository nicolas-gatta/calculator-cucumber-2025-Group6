package visitor;

import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.operations.*;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.Expression;
import calculator.matrix.MatrixExpression;

/** Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

    private String error;
    private Expression result;
    
    /**
     * Default constructor for Evaluator.
     * Initializes a new Evaluator with null error and result.
     */
    public Evaluator() {
        this.error = null;
        this.result = null;
    }

    /** getter method to obtain the result of the evaluation
     *
     * @return an Expression containing the result of the evaluation
     */
    public Expression getResult() {
        if (error != null) {
            return null;
        }
        return result;
    }

    private RealNumber getRealNumber(Expression e) {
        if (e instanceof RealNumber r) {
            return r;
        }
        if (e instanceof MyNumber n) {
            return new RealNumber(n.getValue());
        }
        if (e instanceof RationalNumber rn) {
            return new RealNumber(rn.getValue());
        }
        throw new IllegalArgumentException("Unknown expression type");
    }

    @Override
    public void visit(Operation o) {

        if (o.getArgs().isEmpty()){
            return;
        }

        o.getArgs().get(0).accept(this);
        Expression accumulator = result;

        if (o.getArgs().size() == 1) {

            if (o instanceof MatrixTranspose && accumulator instanceof MatrixExpression acc) {
                result = new MatrixExpression((acc).getMatrix().transpose());
            } else if (o instanceof MatrixIdentity && accumulator instanceof MatrixExpression acc) {
                result = new MatrixExpression((acc).getMatrix().identity());
            } else if (o instanceof MatrixInverted && accumulator instanceof MatrixExpression acc) {
                result = new MatrixExpression((acc).getMatrix().inverse());
            }

        } else if (o.getArgs().size() >= 2) {

            for (int i = 1; i < o.getArgs().size(); i++) {
                o.getArgs().get(i).accept(this);
                Expression current = result;

                // Gestion des opérations entre RealNumber
                if (accumulator instanceof RealNumber accVal && current instanceof RealNumber currVal) {

                    if (o instanceof Plus)
                        accumulator = new RealNumber(accVal.getValue() + currVal.getValue(), ((RealNumber) accumulator).getPrecision());
                    else if (o instanceof Minus)
                        accumulator = new RealNumber(accVal.getValue() - currVal.getValue(), ((RealNumber) accumulator).getPrecision());
                    else if (o instanceof Times)
                        accumulator = new RealNumber(accVal.getValue() * currVal.getValue(), ((RealNumber) accumulator).getPrecision());
                    else if (o instanceof Divides){
                        if (currVal.getValue() == 0) {
                            result = null;
                            return;
                        }
                        accumulator = new RealNumber(accVal.getValue() / currVal.getValue(), ((RealNumber) accumulator).getPrecision());
                    }
                    result = accumulator;
                }

                // Gestion des opérations entre ComplexNumber
                else if (accumulator instanceof ComplexNumber accComp && current instanceof ComplexNumber currComp ) {

                    if (o instanceof Plus)
                        accumulator = accComp.add(currComp);
                    else if (o instanceof Minus)
                        accumulator = accComp.subtract(currComp);
                    else if (o instanceof Times)
                        accumulator = accComp.multiply(currComp);
                    else if (o instanceof Divides)
                        accumulator = accComp.divide(currComp);
                }

                // Gestion des opérations entre RationalNumber
                else if (accumulator instanceof RationalNumber accRat && current instanceof RationalNumber currRat) {

                    if (o instanceof Plus)
                        accumulator = accRat.add(currRat);
                    else if (o instanceof Minus)
                        accumulator = accRat.subtract(currRat);
                    else if (o instanceof Times)
                        accumulator = accRat.multiply(currRat);
                    else if (o instanceof Divides)
                        accumulator = accRat.divide(currRat);
                }

                // Gestion des opérations entre MyNumber
                else if (accumulator instanceof MyNumber accVal && current instanceof MyNumber currVal) {

                    if (o instanceof Plus)
                        accumulator = new MyNumber(accVal.getValue() + currVal.getValue());
                    else if (o instanceof Minus)
                        accumulator = new MyNumber(accVal.getValue() - currVal.getValue());
                    else if (o instanceof Times)
                        accumulator = new MyNumber(accVal.getValue() * currVal.getValue());
                    else if (o instanceof Divides) {
                        if (currVal.getValue() == 0) {
                            result = null;
                            return;
                        }
                        accumulator = new MyNumber(accVal.getValue() / currVal.getValue());
                    }
                }

                else if (accumulator instanceof MyNumber && current instanceof RealNumber ||
                        accumulator instanceof MyNumber && current instanceof RationalNumber ||
                        accumulator instanceof RealNumber && current instanceof MyNumber ||
                        accumulator instanceof RealNumber && current instanceof RationalNumber ||
                        accumulator instanceof RationalNumber && current instanceof MyNumber ||
                        accumulator instanceof RationalNumber && current instanceof RealNumber) {

                    double accVal = getRealNumber(accumulator).getValue();
                    double currVal = getRealNumber(current).getValue();

                    if (o instanceof Plus)
                        accumulator = new RealNumber(accVal + currVal);
                    else if (o instanceof Minus)
                        accumulator = new RealNumber(accVal - currVal);
                    else if (o instanceof Times)
                        accumulator = new RealNumber(accVal * currVal);
                    else if (o instanceof Divides) {
                        if (currVal == 0) {
                            result = null;
                            return;
                        }
                        accumulator = new RealNumber(accVal / currVal);
                    }
                }


                // Gestion des opérations entre MyNumber
                else if (accumulator instanceof MatrixExpression accMat && current instanceof MatrixExpression curMat) {

                    if (o instanceof Plus) {
                        accumulator = new MatrixExpression(accMat.getMatrix().add(curMat.getMatrix()));
                    }else if (o instanceof Minus) {
                        accumulator = new MatrixExpression(accMat.getMatrix().subtract(curMat.getMatrix()));
                    } else if (o instanceof Times) {
                        accumulator = new MatrixExpression(accMat.getMatrix().multiply(curMat.getMatrix()));
                    }
                }

                else if (accumulator instanceof MatrixExpression accMat &&
                        (current instanceof RealNumber || current instanceof MyNumber || current instanceof RationalNumber)) {
                    double currVal = getRealNumber(current).getValue();

                    if (o instanceof Divides) {
                        accumulator = new MatrixExpression(accMat.getMatrix().divide(currVal));
                    } else if (o instanceof Times) {
                        accumulator = new MatrixExpression(accMat.getMatrix().multiply(currVal));
                    }
                }

                else if ((accumulator instanceof RealNumber || accumulator instanceof MyNumber || accumulator instanceof RationalNumber) &&
                        current instanceof MatrixExpression accMat) {
                    double currVal = getRealNumber(accumulator).getValue();

                    if (o instanceof Divides) {
                        accumulator = new MatrixExpression(accMat.getMatrix().divide(currVal));
                    } else if (o instanceof Times) {
                        accumulator = new MatrixExpression(accMat.getMatrix().multiply(currVal));
                    }
                }
            }
            result = accumulator;
        }
    }
    
    

    /** Use the visitor design pattern to visit a number.
     *
     * @param n The number being visited
     */
    @Override
    public void visit(MyNumber n) {
        result = n;
    }

    @Override
    public void visit(RealNumber n) {
        result = n;
    }

    @Override
    public void visit(ComplexNumber c){
        result = c;
    }

    @Override
    public void visit(RationalNumber r){
        result = r;
    }

    @Override
    public void visit(MatrixExpression m){
        result = m;
    }

    @Override
    public void visit(VariableExpression v) {
        result = v;
    }

    @Override
    public void visit(EquationExpression e) {
        result = e;
    }

    @Override
    public void visit(LinearEquationSystemExpression l) {
        result = l.solve();
    }

}
