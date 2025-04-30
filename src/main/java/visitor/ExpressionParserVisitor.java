package visitor;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.linear.VariableExpression;
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

    @Override
    public Expression visitNumberExpr(ExpressionParserParser.NumberExprContext ctx) {
        return numberConverter(ctx.number());
    }

    @Override
    public Expression visitVarExpr(ExpressionParserParser.VarExprContext ctx) {
        Expression number = numberConverter(ctx.variableNumber().number());
        String variable = ctx.variableNumber().VARIABLE().getText();

        return new VariableExpression(number, variable);
    }

    @Override
    public Expression visitParensExpr(ExpressionParserParser.ParensExprContext ctx) {
        return this.visit(ctx.expr());
    }

    @Override
    public Expression visitInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        try{
            return switch (op) {
                case "+" -> new Plus(List.of(left, right));
                case "-" -> new Minus(List.of(left, right));
                case "*" -> new Times(List.of(left, right));
                case "/" -> new Divides(List.of(left, right));
                default -> throw new RuntimeException("Unknown operator: " + op);
            };
        }catch (IllegalConstruction e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitPrefixOperationExpr(ExpressionParserParser.PrefixOperationExprContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        try{
            return switch (op) {
                case "+" -> new Plus(List.of(left, right));
                case "-" -> new Minus(List.of(left, right));
                case "*" -> new Times(List.of(left, right));
                case "/" -> new Divides(List.of(left, right));
                default -> throw new RuntimeException("Unknown operator: " + op);
            };
        }catch (IllegalConstruction e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Expression visitPostOperationExpr(ExpressionParserParser.PostOperationExprContext ctx){
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        String op = ctx.op.getText();

        try{
            return switch (op) {
                case "+" -> new Plus(List.of(left, right));
                case "-" -> new Minus(List.of(left, right));
                case "*" -> new Times(List.of(left, right));
                case "/" -> new Divides(List.of(left, right));
                default -> throw new RuntimeException("Unknown operator: " + op);
            };
        }catch (IllegalConstruction e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
