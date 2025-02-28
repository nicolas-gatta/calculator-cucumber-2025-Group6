package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.Expression;

public class CountingVisitor extends Visitor {
    private int depth = 0;
    private int operations = 0;
    private int numbers = 0;

    @Override
    public void visit(MyNumber n) {
        numbers = 1;
        operations = 0;
        depth = 0;
    }

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

    public int getDepth() {
        return depth;
    }

    public int getOperations() {
        return operations;
    }

    public int getNumbers() {
        return numbers;
    }
}
