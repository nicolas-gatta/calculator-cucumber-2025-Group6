package visitor;


import calculator.MyNumber;
import calculator.Notation;
import calculator.Operation;



/**
 * A visitor that converts arithmetic operations into different notations.
 * Implements the ExpressionVisitor interface.
 */
public class NotationVisitor extends Visitor {

    private final Notation notation;
    private String result = "";

    /**
     * Default constructor of the class.
     * @param n The notation used to render operations as strings
     */
    public NotationVisitor(Notation n){
        this.notation = n;
    }

    /**
     * @param n The number being visited
     */
    public void visit(MyNumber n){
        this.result = n.toString();
    }

    /**
     * @param o The operation being visited
     */
    public void visit(Operation o) {
        switch (notation) {
            case INFIX:
                this.result = "( " + o.getArgs().stream()
                        .map(arg -> {
                            NotationVisitor visitor = new NotationVisitor(this.notation);
                            arg.accept(visitor);
                            return visitor.getResult();
                        })
                        .reduce((a1, a2) -> a1 + " " + o.getSymbol() + " " + a2)
                        .map(result -> result + " )")
                        .orElse(")");
                break;
            case PREFIX:
                this.result = o.getSymbol() + " (" + o.getArgs().stream()
                        .map(arg -> {
                            NotationVisitor visitor = new NotationVisitor(this.notation);
                            arg.accept(visitor);
                            return visitor.getResult();
                        })
                        .reduce((a1, a2) -> a1 + ", " + a2)
                        .map(result -> result + ")")
                        .orElse(")");
                break;
            case POSTFIX:
                this.result = "(" + o.getArgs().stream()
                        .map(arg -> {
                            NotationVisitor visitor = new NotationVisitor(this.notation);
                            arg.accept(visitor);
                            return visitor.getResult();
                        })
                        .reduce((a1, a2) -> a1 + ", " + a2)
                        .map(result -> result + ") " + o.getSymbol())
                        .orElse(") " + o.getSymbol());
                break;
        }
    }


    /**
     * getter method to obtain the value contained in the object
     * @return the result of the operation
     */
    public String getResult(){
        return this.result;
    }
}
