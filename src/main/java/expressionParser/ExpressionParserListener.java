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