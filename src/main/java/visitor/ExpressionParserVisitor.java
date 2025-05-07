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
     *
     * @param number the ANTLR-generated context representing a number
     * @return the corresponding {@link Expression} representation of the number
     */
    private Expression numberConverter(ExpressionParserParser.NumberContext number) {
        if (number.RATIONAL() != null) {
            String[] parts = number.RATIONAL().getText().split("/");
            return new RationalNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
        } else if (number.REAL() != null) {
            return new RealNumber(Double.parseDouble(number.REAL().getText()));
        } else if (number.INT() != null){
            return new MyNumber(Integer.parseInt(number.INT().getText()));
        }
        throw new NullPointerException("Number is null");
    }

    /**
     * Converts a number context into a {@link RealNumber}.
     *
     * @param number the ANTLR-generated context representing a number
     * @return the corresponding {@link RealNumber} representation
     */
    private RealNumber numberToRealConverter(ExpressionParserParser.NumberContext number) {
        if (number.RATIONAL() != null) {
            String[] parts = number.RATIONAL().getText().split("/");
            return new RealNumber(Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]));
        } else if (number.REAL() != null) {
            return new RealNumber(Double.parseDouble(number.REAL().getText()));
        } else if (number.INT() != null) {
            return new RealNumber(Integer.parseInt(number.INT().getText()));
        }
        throw new NullPointerException("Number is null");
    }

    /**
     * Builds a binary operation expression using two operands and an operator.
     *
     * @param left     the left-hand operand
     * @param right    the right-hand operand
     * @param op       the operator (e.g., +, -, *, /)
     * @param notation the notation style (e.g., INFIX, PREFIX, POSTFIX)
     * @return the corresponding {@link Expression} representing the operation
     */
    private Expression operationExpr(Expression left, Expression right, String op, Notation notation) {
        return operationListExpr(List.of(left, right), op, notation);
    }

    /**
     * Builds an operation expression from a list of expressions and an operator.
     *
     * @param expressionList the list of expressions to operate on
     * @param op             the operator (e.g., +, -, *, /)
     * @param notation       the notation style (e.g., INFIX, PREFIX, POSTFIX)
     * @return the corresponding {@link Expression} representing the operation
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
     * Visits a parenthesized expression and evaluates the inner expression.
     *
     * @param ctx the context representing the parenthesized expression
     * @return the evaluated {@link Expression} inside the parentheses
     */
    @Override
    public Expression visitParensExpr(ExpressionParserParser.ParensExprContext ctx) {
        return visit(ctx.expr());
    }

    /**
     * Visits a number expression and converts it to its respective {@link Expression} form.
     *
     * @param ctx the context representing the number expression
     * @return the {@link Expression} representation of the number
     */
    @Override
    public Expression visitNumberExpr(ExpressionParserParser.NumberExprContext ctx) {
        return numberConverter(ctx.number());
    }

    /**
     * Visits a complex number expression and constructs a {@link ComplexNumber}.
     *
     * @param ctx the context representing the complex number
     * @return the constructed {@link ComplexNumber}
     */
    @Override
    public Expression visitComplexExpr(ExpressionParserParser.ComplexExprContext ctx) {
        double real = numberToRealConverter(ctx.complex().number()).getValue();
        double imaginary = numberToRealConverter(ctx.complex().imaginary().number()).getValue();

        return new ComplexNumber(real, imaginary);
    }

    /**
     * Visits an Infix Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the operation
     * @return the constructed {@link Operation}
     */
    @Override
    public Expression visitInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx) {
        Expression left = visit(ctx.expr(0));
        Expression right = visit(ctx.expr(1));
        String op = ctx.op.getText();
        return operationExpr(left, right, op, Notation.INFIX);
    }

    /**
     * Visits a Prefix Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the operation
     * @return the constructed {@link Operation}
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
     * Visits a Postfix Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the operation
     * @return the constructed {@link Operation}
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

    /**
     * Visits a Infix Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the operation
     * @return the constructed {@link Operation}
     */
    @Override
    public Expression visitInfixEquationExpr(ExpressionParserParser.InfixEquationExprContext ctx) {
        Expression left = visit(ctx.equationLeftPart(0));
        Expression right = visit(ctx.equationLeftPart(1));
        String op = ctx.op.getText();
        return operationExpr(left, right, op, Notation.INFIX);
    }

    /**
     * Visits a Postfix Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the operation
     * @return the constructed {@link Operation}
     */
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

    /**
     * Visits a Prefix Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the operation
     * @return the constructed {@link Operation}
     */
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
     * Visits Variable expression and constructs a {@link VariableExpression}.
     *
     * @param ctx the context representing the Variable
     * @return the constructed {@link VariableExpression}
     */
    @Override
    public Expression visitVarExpr(ExpressionParserParser.VarExprContext ctx) {
        return visit(ctx.variableNumber());
    }

    /**
     * Visits Variable expression and constructs a {@link VariableExpression}.
     *
     * @param ctx the context representing the Variable
     * @return the constructed {@link VariableExpression}
     */
    @Override
    public Expression visitEquationVariable(ExpressionParserParser.EquationVariableContext ctx) {
        return visit(ctx.variableNumber());
    }

    /**
     * Visits Variable expression and constructs a {@link VariableExpression}.
     *
     * @param ctx the context representing the Variable
     * @return the constructed {@link VariableExpression}
     */
    @Override
    public Expression visitVariableNumber(ExpressionParserParser.VariableNumberContext ctx) {
        String variable = ctx.VARIABLE().getText();

        if(ctx.number() == null){
            return new VariableExpression(variable);
        }
        Expression number = numberConverter(ctx.number());

        return new VariableExpression(number, variable);
    }

    /**
     * Visits Matrix expression and constructs a {@link MatrixExpression}.
     *
     * @param ctx the context representing the Matrix
     * @return the constructed {@link MatrixExpression}
     */
    @Override
    public Expression visitMatrixExpr(ExpressionParserParser.MatrixExprContext ctx) {
        return visit(ctx.matrix());
    }

    /**
     * Visits Matrix expression and constructs a {@link MatrixExpression}.
     *
     * @param ctx the context representing the Matrix
     * @return the constructed {@link MatrixExpression}
     */
    @Override
    public Expression visitMatrix(ExpressionParserParser.MatrixContext ctx) {
        return new MatrixExpression(new Matrix(ctx.getText()));
    }

    /**
     * Build the operation related to the matrix using the correct class {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}
     *
     * @param matrix A matrix
     * @param op An operation
     * @param notation Which kind of notation to use
     * @return the constructed {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}
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
     * Visits a Prefix Operation Matrix expression and constructs a {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}.
     *
     * @param ctx the context representing the matrix operation
     * @return the constructed {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}
     */
    @Override
    public Expression visitMatrixPrefix(ExpressionParserParser.MatrixPrefixContext ctx) {
        return matrixOperator(visit(ctx.matrix()), ctx.matrixOperator().getText(), Notation.PREFIX);
    }

    /**
     * Visits a Prefix Matrix Operation expression and constructs a {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}.
     *
     * @param ctx the context representing the matrix operation
     * @return the constructed {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}
     */
    @Override
    public Expression visitMatrixPostfix(ExpressionParserParser.MatrixPostfixContext ctx) {
        return matrixOperator(visit(ctx.matrix()), ctx.matrixOperator().getText(), Notation.POSTFIX);
    }

    /**
     * Visits a Prefix Matrix Operation expression and constructs a {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}.
     *
     * @param ctx the context representing the matrix operation
     * @return the constructed {@link MatrixTranspose}{@link MatrixIdentity}{@link MatrixInverted}
     */
    @Override
    public Expression visitMatrixFunctionExpr(ExpressionParserParser.MatrixFunctionExprContext ctx) {
        return visit(ctx.matrixFunction());
    }

    /**
     * Visits an Equation Operation expression and constructs a {@link EquationExpression}.
     *
     * @param ctx the context representing the Equation
     * @return the constructed {@link EquationExpression}
     */
    @Override
    public Expression visitEquationExpr(ExpressionParserParser.EquationExprContext ctx) {
        return visit(ctx.equation());
    }

    /**
     * Visits an Equation Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the Equation left part
     * @return the constructed {@link EquationExpression}
     */
    @Override
    public Expression visitEquation(ExpressionParserParser.EquationContext ctx) {
        Expression left = visit(ctx.equationLeftPart());
        Expression right = visit(ctx.equationRightPart());
        return new EquationExpression(new Equation(left, right));
    }

    /**
     * Visits an Equation Operation expression and constructs a {@link Operation}.
     *
     * @param ctx the context representing the Equation left part
     * @return the constructed {@link Operation}
     */
    @Override
    public Expression visitParensEquationExpr(ExpressionParserParser.ParensEquationExprContext ctx) {
        return visit(ctx.equationLeftPart());
    }

    /**
     * Visits an Equation Operation expression and constructs a {@link EquationExpression}.
     *
     * @param ctx the context representing the matrix operation
     * @return the constructed {@link EquationExpression}
     */
    @Override
    public Expression visitEquationRightPart(ExpressionParserParser.EquationRightPartContext ctx) {
        return numberConverter(ctx.number());
    }

    /**
     * Visits an Linear Equation expression and constructs a {@link LinearEquationSystemExpression}.
     *
     * @param ctx the context representing the Linear System
     * @return the constructed {@link LinearEquationSystemExpression}
     */
    @Override
    public Expression visitLinearExpr(ExpressionParserParser.LinearExprContext ctx) {
        List<EquationExpression> equations = ctx.linearEquation().equation().stream()
                .map(e -> (EquationExpression) visit(e))
                .toList();

        return new LinearEquationSystemExpression(equations);
    }
}
