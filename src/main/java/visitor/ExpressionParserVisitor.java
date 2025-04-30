package visitor;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.linear.Equation;
import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import calculator.numbers.ComplexNumber;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.operations.*;
import expressionParser.ExpressionParserBaseVisitor;
import expressionParser.ExpressionParserParser;

import java.util.List;

public class ExpressionParserVisitor extends ExpressionParserBaseVisitor<Expression> {

    private Expression numberConverter(ExpressionParserParser.NumberContext number) {
        if (number.RATIONAL() != null) {
            String[] parts = number.RATIONAL().getText().split("/");
            return new RationalNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else if (number.REAL() != null) {
            return new RealNumber(Double.parseDouble(number.REAL().getText()));
        } else if (number.INT() != null){
            return new MyNumber(Integer.parseInt(number.INT().getText()));
        }else{
            return null;
        }
    }

    private RealNumber numberToRealConverter(ExpressionParserParser.NumberContext number) {
        if (number.RATIONAL() != null) {
            String[] parts = number.RATIONAL().getText().split("/");
            return new RealNumber(Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]));
        } else if (number.REAL() != null) {
            return new RealNumber(Double.parseDouble(number.REAL().getText()));
        } else if (number.INT() != null){
            return new RealNumber(Integer.parseInt(number.INT().getText()));
        }else{
            return null;
        }
    }

    private Expression operationExpr(Expression left, Expression right, String op, Notation notation) {
        return operationListExpr(List.of(left, right), op, notation);
    }

    private Expression operationListExpr(List<Expression> expressionList, String op, Notation notation) {
        try{
            return switch (op) {
                case "+" -> new Plus(expressionList, notation);
                case "-" -> new Minus(expressionList, notation);
                case "*" -> new Times(expressionList, notation);
                case "/" -> new Divides(expressionList, notation);
                default -> throw new RuntimeException("Unknown operator: " + op);
            };
        }catch (IllegalConstruction e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitParensExpr(ExpressionParserParser.ParensExprContext ctx) {
        return this.visit(ctx.expr());
    }

    @Override
    public Expression visitNumberExpr(ExpressionParserParser.NumberExprContext ctx) {
        return numberConverter(ctx.number());
    }

    @Override
    public Expression visitComplexExpr(ExpressionParserParser.ComplexExprContext ctx) {
        double real = numberToRealConverter(ctx.complex().number()).getValue();
        double imaginary = numberToRealConverter(ctx.complex().imaginary().number()).getValue();

        return new ComplexNumber(real, imaginary);
    }

    @Override
    public Expression visitInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        return operationExpr(left, right, op, Notation.INFIX);
    }

    @Override
    public Expression visitPrefixOperationExpr(ExpressionParserParser.PrefixOperationExprContext ctx) {
        String op = ctx.op.getText();

        if(ctx.expr().size() >= 3){

            List<Expression> arguments = ctx.expr().stream()
                    .map(this::visit)
                    .toList();

            return operationListExpr(arguments, op, Notation.PREFIX);
        }
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));


        return operationExpr(left, right, op, Notation.PREFIX);
    }

    @Override
    public Expression visitPostOperationExpr(ExpressionParserParser.PostOperationExprContext ctx){
        String op = ctx.op.getText();

        if(ctx.expr().size() >= 3){

            List<Expression> arguments = ctx.expr().stream()
                    .map(this::visit)
                    .toList();
            return operationListExpr(arguments, op, Notation.POSTFIX);
        }

        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));

        return operationExpr(left, right, op, Notation.POSTFIX);
    }

    @Override
    public Expression visitVarExpr(ExpressionParserParser.VarExprContext ctx) {
        String variable = ctx.variableNumber().VARIABLE().getText();

        if(ctx.variableNumber().number() == null){
            return new VariableExpression(variable);
        }
        Expression number = numberConverter(ctx.variableNumber().number());

        return new VariableExpression(number, variable);
    }

    @Override
    public Expression visitMatrixExpr(ExpressionParserParser.MatrixExprContext ctx) {
        return new MatrixExpression(new Matrix(ctx.getText()));
    }

    @Override
    public Expression visitEquation(ExpressionParserParser.EquationContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        return new EquationExpression(new Equation(left, right));
    }


    @Override
    public Expression visitLinearExpr(ExpressionParserParser.LinearExprContext ctx) {
        List<EquationExpression> equations = ctx.linearEquation().equation().stream()
                .map(e -> (EquationExpression) visit(e))
                .toList();

        return new LinearEquationSystemExpression(equations);
    }
}
