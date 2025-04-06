package visitor;

import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.operations.*;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.Expression;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;

/** Evaluation is a concrete visitor that serves to
 * compute and evaluate the results of arithmetic expressions.
 */
public class Evaluator extends Visitor {

    private String error;
    private Expression result;

    /**
     * Default constructor of the class. Does not initialise anything.
     */
    public Evaluator() {}

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

    @Override
    public void visit(Operation o) {

        if (o.getArgs().size() < 1){
            return;
        }

        Expression accumulator = result;

        if (o.getArgs().size() == 1) {

            if (o instanceof MatrixTranspose && accumulator instanceof MatrixExpression) {
                result = new MatrixExpression(((MatrixExpression) accumulator).getMatrix().transpose());
            } else if (o instanceof MatrixIdentity && accumulator instanceof MatrixExpression) {
                result = new MatrixExpression(((MatrixExpression) accumulator).getMatrix().identity());
            } else if (o instanceof MatrixInverted && accumulator instanceof MatrixExpression) {
                result = new MatrixExpression(((MatrixExpression) accumulator).getMatrix().inverse());
            }

        } else if (o.getArgs().size() >= 2) {
            o.getArgs().get(0).accept(this);


            for (int i = 1; i < o.getArgs().size(); i++) {
                o.getArgs().get(i).accept(this);
                Expression current = result;

                // Gestion de la division par zéro
                if (o instanceof Divides && current instanceof RealNumber && ((RealNumber) current).getValue() == 0) {
                    result = null;
                    return;
                }

                // Gestion des opérations entre RealNumber
                if (accumulator instanceof RealNumber && current instanceof RealNumber) {
                    double accVal = ((RealNumber) accumulator).getValue();
                    double currVal = ((RealNumber) current).getValue();

                    if (o instanceof Plus)
                        accumulator = new RealNumber(accVal + currVal, ((RealNumber) accumulator).getPrecision());
                    else if (o instanceof Minus)
                        accumulator = new RealNumber(accVal - currVal, ((RealNumber) accumulator).getPrecision());
                    else if (o instanceof Times)
                        accumulator = new RealNumber(accVal * currVal, ((RealNumber) accumulator).getPrecision());
                    else if (o instanceof Divides)
                        accumulator = new RealNumber(accVal / currVal, ((RealNumber) accumulator).getPrecision());
                }
                // Gestion des opérations entre ComplexNumber
                else if (accumulator instanceof ComplexNumber || current instanceof ComplexNumber) {
                    ComplexNumber accComp = (ComplexNumber) accumulator;
                    ComplexNumber currComp = (ComplexNumber) current;

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
                else if (accumulator instanceof RationalNumber || current instanceof RationalNumber) {
                    RationalNumber accRat = (RationalNumber) accumulator;
                    RationalNumber currRat = (RationalNumber) current;

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
                else if (accumulator instanceof MyNumber && current instanceof MyNumber) {
                    int accVal = ((MyNumber) accumulator).getValue();
                    int currVal = ((MyNumber) current).getValue();

                    if (o instanceof Plus)
                        accumulator = new MyNumber(accVal + currVal);
                    else if (o instanceof Minus)
                        accumulator = new MyNumber(accVal - currVal);
                    else if (o instanceof Times)
                        accumulator = new MyNumber(accVal * currVal);
                    else if (o instanceof Divides) {
                        if (currVal == 0) {
                            result = null;
                            return;
                        }
                        accumulator = new MyNumber(accVal / currVal);
                    }
                }

                // Gestion des opérations entre MyNumber
                else if (accumulator instanceof MatrixExpression && current instanceof MatrixExpression) {
                    Matrix accVal = ((MatrixExpression) accumulator).getMatrix();
                    Matrix currVal = ((MatrixExpression) current).getMatrix();

                    if (o instanceof Plus) {
                        accumulator = new MatrixExpression(accVal.add(currVal));
                    }else if (o instanceof Minus) {
                        accumulator = new MatrixExpression(accVal.subtract(currVal));
                    } else if (o instanceof Times) {
                        accumulator = new MatrixExpression(accVal.multiply(currVal));
                    }
                }

                else if (accumulator instanceof MatrixExpression && current instanceof RealNumber) {
                    Matrix accVal = ((MatrixExpression) accumulator).getMatrix();
                    double currVal = ((RealNumber) current).getValue();

                    if (o instanceof Divides) {
                        accumulator = new MatrixExpression(accVal.divide(currVal));
                    } else if (o instanceof Times) {
                        accumulator = new MatrixExpression(accVal.multiply(currVal));
                    }
                }

                else if (accumulator instanceof RealNumber && current instanceof MatrixExpression) {
                    double accVal = ((RealNumber) current).getValue();
                    Matrix currVal = ((MatrixExpression) accumulator).getMatrix();

                    if (o instanceof Divides) {
                        accumulator = new MatrixExpression(currVal.divide(accVal));
                    } else if (o instanceof Times) {
                        accumulator = new MatrixExpression(currVal.multiply(accVal));
                    }
                }
            }

        }
        result = accumulator;
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

}
