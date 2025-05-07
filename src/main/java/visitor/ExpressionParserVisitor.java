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

/**
 * Visitor implementation for parsing mathematical expressions represented by
 * the ANTLR-generated parse tree of the {@code ExpressionParser.g4} grammar.
 *
 * <p>This visitor traverses the parse tree and converts it into an abstract syntax tree
 * of {@link Expression} objects, including support for basic arithmetic, matrices,
 * complex numbers, variables, and equations.</p>
 */
public class ExpressionParserVisitor extends ExpressionParserBaseVisitor<Expression> {

    /**
     * Converts a number context into the appropriate {@link Expression} subclass.
     */
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

    /**
     * Converts a number context into a {@link RealNumber}.
     */
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

    /**
     * Builds a binary operation expression using two operands and an operator.
     */
    private Expression operationExpr(Expression left, Expression right, String op, Notation notation) {
        return operationListExpr(List.of(left, right), op, notation);
    }

    /**
     * Builds an operation expression from a list of expressions and an operator.
     */
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

    /**
     * Starting point if there is parenthesis
     */
    @Override
    public Expression visitParensExpr(ExpressionParserParser.ParensExprContext ctx) {
        return this.visit(ctx.expr());
    }

    /**
     * Build the number using the correct class Expression
     */
    @Override
    public Expression visitNumberExpr(ExpressionParserParser.NumberExprContext ctx) {
        return numberConverter(ctx.number());
    }

    /**
     * Starting point if there is parenthesis
     */
    @Override
    public Expression visitComplexExpr(ExpressionParserParser.ComplexExprContext ctx) {
        double real = numberToRealConverter(ctx.complex().number()).getValue();
        double imaginary = numberToRealConverter(ctx.complex().imaginary().number()).getValue();

        return new ComplexNumber(real, imaginary);
    }

    /**
     * Build the operation following the infix rule
     */
    @Override
    public Expression visitInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        return operationExpr(left, right, op, Notation.INFIX);
    }

    /**
     * Build the operation following the prefix rule
     */
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

    /**
     * Build the operation following the postfix rule
     */
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
    public Expression visitInfixEquationExpr(ExpressionParserParser.InfixEquationExprContext ctx) {
        Expression left = visit(ctx.equationLeftPart(0));
        Expression right = visit(ctx.equationLeftPart(1));
        String op = ctx.op.getText();
        return operationExpr(left, right, op, Notation.INFIX);
    }

    @Override
    public Expression visitPostfixEquationExpr(ExpressionParserParser.PostfixEquationExprContext ctx) {
        String op = ctx.op.getText();

        if(ctx.equationLeftPart().size() >= 3){

            List<Expression> arguments = ctx.equationLeftPart().stream()
                    .map(this::visit)
                    .toList();
            return operationListExpr(arguments, op, Notation.POSTFIX);
        }

        Expression left = visit(ctx.equationLeftPart(0));
        Expression right = visit(ctx.equationLeftPart(1));

        return operationExpr(left, right, op, Notation.POSTFIX);
    }

    @Override
    public Expression visitPrefixEquationExpr(ExpressionParserParser.PrefixEquationExprContext ctx) {
        String op = ctx.op.getText();

        if(ctx.equationLeftPart().size() >= 3){

            List<Expression> arguments = ctx.equationLeftPart().stream()
                    .map(this::visit)
                    .toList();

            return operationListExpr(arguments, op, Notation.PREFIX);
        }
        Expression left = visit(ctx.equationLeftPart(0));
        Expression right = visit(ctx.equationLeftPart(1));


        return operationExpr(left, right, op, Notation.PREFIX);
    }

    /**
     * Build the number variable using the correct class Expression
     */
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
    public Expression visitEquationVariable(ExpressionParserParser.EquationVariableContext ctx) {
        String variable = ctx.variableNumber().VARIABLE().getText();

        if(ctx.variableNumber().number() == null){
            return new VariableExpression(variable);
        }
        Expression number = numberConverter(ctx.variableNumber().number());

        return new VariableExpression(number, variable);
    }

    /**
     * Build the matrix using the correct class Expression
     */
    @Override
    public Expression visitMatrixExpr(ExpressionParserParser.MatrixExprContext ctx) {
        return new MatrixExpression(new Matrix(ctx.getText()));
    }

    /**
     * Build the matrix using the correct class Expression (Mostly for matrix with transpose, identity and inverted)
     */
    @Override
    public Expression visitMatrix(ExpressionParserParser.MatrixContext ctx) {
        return new MatrixExpression(new Matrix(ctx.getText()));
    }

    /**
     * Build the operation related to the matrix using the correct class Expression
     */
    private Expression matrixOperator(Expression matrix, String op, Notation notation) {
        try{
            return switch (op) {
                case "T^", "^T" -> new MatrixTranspose(List.of(matrix), notation);
                case "I^", "^I" -> new MatrixIdentity(List.of(matrix), notation);
                case "-1^", "^-1" -> new MatrixInverted(List.of(matrix), notation);
                default -> throw new RuntimeException("Unknown operator: " + op);
            };
        }catch (IllegalConstruction e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Build the operation related to the matrix following the prefix rule (Mostly for matrix with transpose, identity and inverted)
     */
    @Override
    public Expression visitMatrixPrefix(ExpressionParserParser.MatrixPrefixContext ctx) {
        return matrixOperator(visit(ctx.matrix()), ctx.matrixOperator().getText(), Notation.PREFIX);
    }

    /**
     * Build the operation related to the matrix following the postfix rule (Mostly for matrix with transpose, identity and inverted)
     */
    @Override
    public Expression visitMatrixPostfix(ExpressionParserParser.MatrixPostfixContext ctx) {
        return matrixOperator(visit(ctx.matrix()), ctx.matrixOperator().getText(), Notation.POSTFIX);
    }

    /**
     * Starting point for the matrix
     */
    @Override
    public Expression visitMatrixFunctionExpr(ExpressionParserParser.MatrixFunctionExprContext ctx) {
        return this.visit(ctx.matrixFunction());
    }

    /**
     * Build the equation that will later compose the Linear Equation System
     */
    @Override
    public Expression visitEquationExpr(ExpressionParserParser.EquationExprContext ctx) {
        return visit(ctx.equation());
    }

    @Override
    public Expression visitEquation(ExpressionParserParser.EquationContext ctx) {
        Expression left = visit(ctx.equationLeftPart());
        Expression right = visit(ctx.equationRightPart());
        return new EquationExpression(new Equation(left, right));
    }

    @Override
    public Expression visitParensEquationExpr(ExpressionParserParser.ParensEquationExprContext ctx) {
        return visit(ctx.equationLeftPart());
    }

    @Override
    public Expression visitEquationRightPart(ExpressionParserParser.EquationRightPartContext ctx) {
        return numberConverter(ctx.number());
    }

    /**
     * Build the linear Esuation System
     */
    @Override
    public Expression visitLinearExpr(ExpressionParserParser.LinearExprContext ctx) {
        List<EquationExpression> equations = ctx.linearEquation().equation().stream()
                .map(e -> (EquationExpression) visit(e))
                .toList();

        return new LinearEquationSystemExpression(equations);
    }
}
