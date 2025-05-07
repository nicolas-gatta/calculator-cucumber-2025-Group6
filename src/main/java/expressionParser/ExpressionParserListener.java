// Generated from C:/Users/Utilisateur/Desktop/Projet/calculator-cucumber-2025-Group6/src/main/java/expressionParser/ExpressionParser.g4 by ANTLR 4.13.2
package expressionParser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ExpressionParserParser}.
 */
public interface ExpressionParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#operator}.
	 * @param ctx the parse tree
	 */
	void enterOperator(ExpressionParserParser.OperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#operator}.
	 * @param ctx the parse tree
	 */
	void exitOperator(ExpressionParserParser.OperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(ExpressionParserParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(ExpressionParserParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#imaginary}.
	 * @param ctx the parse tree
	 */
	void enterImaginary(ExpressionParserParser.ImaginaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#imaginary}.
	 * @param ctx the parse tree
	 */
	void exitImaginary(ExpressionParserParser.ImaginaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#complex}.
	 * @param ctx the parse tree
	 */
	void enterComplex(ExpressionParserParser.ComplexContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#complex}.
	 * @param ctx the parse tree
	 */
	void exitComplex(ExpressionParserParser.ComplexContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#matrix}.
	 * @param ctx the parse tree
	 */
	void enterMatrix(ExpressionParserParser.MatrixContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#matrix}.
	 * @param ctx the parse tree
	 */
	void exitMatrix(ExpressionParserParser.MatrixContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#row}.
	 * @param ctx the parse tree
	 */
	void enterRow(ExpressionParserParser.RowContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#row}.
	 * @param ctx the parse tree
	 */
	void exitRow(ExpressionParserParser.RowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Transpose}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 */
	void enterTranspose(ExpressionParserParser.TransposeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Transpose}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 */
	void exitTranspose(ExpressionParserParser.TransposeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Identity}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 */
	void enterIdentity(ExpressionParserParser.IdentityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Identity}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 */
	void exitIdentity(ExpressionParserParser.IdentityContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Inverted}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 */
	void enterInverted(ExpressionParserParser.InvertedContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Inverted}
	 * labeled alternative in {@link ExpressionParserParser#matrixOperator}.
	 * @param ctx the parse tree
	 */
	void exitInverted(ExpressionParserParser.InvertedContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MatrixPostfix}
	 * labeled alternative in {@link ExpressionParserParser#matrixFunction}.
	 * @param ctx the parse tree
	 */
	void enterMatrixPostfix(ExpressionParserParser.MatrixPostfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MatrixPostfix}
	 * labeled alternative in {@link ExpressionParserParser#matrixFunction}.
	 * @param ctx the parse tree
	 */
	void exitMatrixPostfix(ExpressionParserParser.MatrixPostfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MatrixPrefix}
	 * labeled alternative in {@link ExpressionParserParser#matrixFunction}.
	 * @param ctx the parse tree
	 */
	void enterMatrixPrefix(ExpressionParserParser.MatrixPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MatrixPrefix}
	 * labeled alternative in {@link ExpressionParserParser#matrixFunction}.
	 * @param ctx the parse tree
	 */
	void exitMatrixPrefix(ExpressionParserParser.MatrixPrefixContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#variableNumber}.
	 * @param ctx the parse tree
	 */
	void enterVariableNumber(ExpressionParserParser.VariableNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#variableNumber}.
	 * @param ctx the parse tree
	 */
	void exitVariableNumber(ExpressionParserParser.VariableNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EquationVariable}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void enterEquationVariable(ExpressionParserParser.EquationVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EquationVariable}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void exitEquationVariable(ExpressionParserParser.EquationVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void enterPrefixEquationExpr(ExpressionParserParser.PrefixEquationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void exitPrefixEquationExpr(ExpressionParserParser.PrefixEquationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void enterInfixEquationExpr(ExpressionParserParser.InfixEquationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void exitInfixEquationExpr(ExpressionParserParser.InfixEquationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParensEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void enterParensEquationExpr(ExpressionParserParser.ParensEquationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParensEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void exitParensEquationExpr(ExpressionParserParser.ParensEquationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostfixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void enterPostfixEquationExpr(ExpressionParserParser.PostfixEquationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostfixEquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#equationLeftPart}.
	 * @param ctx the parse tree
	 */
	void exitPostfixEquationExpr(ExpressionParserParser.PostfixEquationExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#equationRightPart}.
	 * @param ctx the parse tree
	 */
	void enterEquationRightPart(ExpressionParserParser.EquationRightPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#equationRightPart}.
	 * @param ctx the parse tree
	 */
	void exitEquationRightPart(ExpressionParserParser.EquationRightPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#equation}.
	 * @param ctx the parse tree
	 */
	void enterEquation(ExpressionParserParser.EquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#equation}.
	 * @param ctx the parse tree
	 */
	void exitEquation(ExpressionParserParser.EquationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ExpressionParserParser#linearEquation}.
	 * @param ctx the parse tree
	 */
	void enterLinearEquation(ExpressionParserParser.LinearEquationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ExpressionParserParser#linearEquation}.
	 * @param ctx the parse tree
	 */
	void exitLinearEquation(ExpressionParserParser.LinearEquationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEquationExpr(ExpressionParserParser.EquationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EquationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEquationExpr(ExpressionParserParser.EquationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code InfixOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code InfixOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInfixOperationExpr(ExpressionParserParser.InfixOperationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MatrixFunctionExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMatrixFunctionExpr(ExpressionParserParser.MatrixFunctionExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MatrixFunctionExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMatrixFunctionExpr(ExpressionParserParser.MatrixFunctionExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PostOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPostOperationExpr(ExpressionParserParser.PostOperationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PostOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPostOperationExpr(ExpressionParserParser.PostOperationExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNumberExpr(ExpressionParserParser.NumberExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NumberExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNumberExpr(ExpressionParserParser.NumberExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ComplexExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterComplexExpr(ExpressionParserParser.ComplexExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ComplexExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitComplexExpr(ExpressionParserParser.ComplexExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParensExpr(ExpressionParserParser.ParensExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParensExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParensExpr(ExpressionParserParser.ParensExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LinearExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLinearExpr(ExpressionParserParser.LinearExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LinearExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLinearExpr(ExpressionParserParser.LinearExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVarExpr(ExpressionParserParser.VarExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VarExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVarExpr(ExpressionParserParser.VarExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MatrixExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMatrixExpr(ExpressionParserParser.MatrixExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MatrixExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMatrixExpr(ExpressionParserParser.MatrixExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code PrefixOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixOperationExpr(ExpressionParserParser.PrefixOperationExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PrefixOperationExpr}
	 * labeled alternative in {@link ExpressionParserParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixOperationExpr(ExpressionParserParser.PrefixOperationExprContext ctx);
}