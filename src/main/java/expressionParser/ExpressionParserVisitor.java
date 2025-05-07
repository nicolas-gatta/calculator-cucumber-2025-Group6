// Generated from C:/Users/Utilisateur/Desktop/Projet/calculator-cucumber-2025-Group6/src/main/java/expressionParser/ExpressionParser.g4 by ANTLR 4.13.2
package expressionParser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ExpressionParserParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ExpressionParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperator(ExpressionParserParser.OperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(ExpressionParserParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#imaginary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImaginary(ExpressionParserParser.ImaginaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#complex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplex(ExpressionParserParser.ComplexContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#matrix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrix(ExpressionParserParser.MatrixContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#row}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRow(ExpressionParserParser.RowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Transpose}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTranspose(ExpressionParserParser.TransposeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Identity}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentity(ExpressionParserParser.IdentityContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Inverted}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInverted(ExpressionParserParser.InvertedContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MatrixPostfix}
	 * labeled alternative in {@link ExpressionParserParser#matrixFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrixPostfix(ExpressionParserParser.MatrixPostfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MatrixPrefix}
	 * labeled alternative in {@link ExpressionParserParser#matrixFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrixPrefix(ExpressionParserParser.MatrixPrefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#variableNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableNumber(ExpressionParserParser.VariableNumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EquationVariable}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquationVariable(ExpressionParserParser.EquationVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixEquationExpr(ExpressionParserParser.PrefixEquationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixEquationExpr(ExpressionParserParser.InfixEquationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParensEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensEquationExpr(ExpressionParserParser.ParensEquationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostfixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixEquationExpr(ExpressionParserParser.PostfixEquationExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#equationRightPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquationRightPart(ExpressionParserParser.EquationRightPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#equation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquation(ExpressionParserParser.EquationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ExpressionParserParser#linearEquation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinearEquation(ExpressionParserParser.LinearEquationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquationExpr(ExpressionParserParser.EquationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code InfixOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MatrixFunctionExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrixFunctionExpr(ExpressionParserParser.MatrixFunctionExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PostOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostOperationExpr(ExpressionParserParser.PostOperationExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberExpr(ExpressionParserParser.NumberExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ComplexExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexExpr(ExpressionParserParser.ComplexExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParensExpr(ExpressionParserParser.ParensExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LinearExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLinearExpr(ExpressionParserParser.LinearExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarExpr(ExpressionParserParser.VarExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MatrixExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatrixExpr(ExpressionParserParser.MatrixExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PrefixOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixOperationExpr(ExpressionParserParser.PrefixOperationExprContext ctx);
}