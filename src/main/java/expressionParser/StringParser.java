package expressionParser;

import calculator.Expression;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.tree.ParseTree;
import visitor.ExpressionParserVisitor;

/**
 * Utility class for parsing mathematical expressions from strings into {@link Expression} objects
 * using the ANTLR-generated parser and a custom visitor.
 *
 * <p>This class provides a single static method {@link #parse(String)} that takes a string
 * representation of an expression (e.g., "3 + 4", "(x + 2 = 5)", or "solve(x + y = 3, x - y = 1)")
 * and returns the corresponding {@link Expression} tree.</p>
 */

public class StringParser {

    /**
     * Constructor to prevent instantiation of the {@code StringParser} utility class.
     * <p>
     * This constructor throws an {@link IllegalStateException} if called,
     * indicating that {@code StringParser} is intended to be used statically.
     * </p>
     *
     * @throws IllegalStateException if an attempt is made to instantiate the class.
     */
    public StringParser() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Parses a string into an {@link Expression} tree using the ANTLR lexer, parser,
     * and a custom visitor.
     *
     * @param input the string representing a mathematical expression
     * @return the corresponding {@link Expression} object tree
     *
     * @throws org.antlr.v4.runtime.RecognitionException if the input cannot be parsed
     * @throws NullPointerException if the visitor fails to generate an expression
     */
    public static Expression parse(String input){

        if (input == null){
            throw new NullPointerException("Input cannot be null");
        }

        ExpressionParserLexer lexer = new ExpressionParserLexer(CharStreams.fromString(input));

        ExpressionParserParser parser = new ExpressionParserParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.expr();

        ExpressionParserVisitor visitor = new ExpressionParserVisitor();

        Expression expression = visitor.visit(tree);

        if (expression == null){
            throw new RecognitionException("Invalid Expression", parser, parser.getInputStream(), parser.getContext());
        }

        return expression;
    }
}
