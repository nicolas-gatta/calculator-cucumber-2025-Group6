package expressionParser;

import calculator.Expression;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import visitor.ExpressionParserVisitor;

public class StringParser {

    public static Expression parse(String input){

        ExpressionParserLexer lexer = new ExpressionParserLexer(CharStreams.fromString(input));

        ExpressionParserParser parser = new ExpressionParserParser(new CommonTokenStream(lexer));

        ParseTree tree = parser.expr();

        ExpressionParserVisitor visitor = new ExpressionParserVisitor();

        return visitor.visit(tree);
    }
}
