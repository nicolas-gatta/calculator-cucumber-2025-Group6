package calculator.api;

import calculator.Calculator;
import calculator.Expression;
import expressionParser.StringParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parser")
public class ExpressionParserController {


    @PostMapping(value ="/evaluate", produces = "text/plain")
    public String evalExpression(@RequestBody String expression) {
        return new Calculator().eval(StringParser.parse(expression)).toString();
    }
}
