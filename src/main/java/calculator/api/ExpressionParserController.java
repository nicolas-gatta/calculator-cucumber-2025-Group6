package calculator.api;

import expressionParser.StringParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parser")
public class ExpressionParserController {


    @PostMapping("/evaluate")
    public String evalExpression(@RequestBody String expression) {
        return StringParser.parse(expression).toString();
    }
}
