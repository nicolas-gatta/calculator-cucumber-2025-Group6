package visitor;

import calculator.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class TestStringVisitor {

    @Test
    void testNumberToString() {
        MyNumber number = new MyNumber(42);
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        number.accept(visitor);
        assertEquals("42", visitor.getResult());
    }

    @Test
    void testOperationToStringInfix() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        op.accept(visitor);
        assertEquals("( 5 + 3 )", visitor.getResult());
    }

    @Test
    void testOperationToStringPrefix() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        StringVisitor visitor = new StringVisitor(Notation.PREFIX);
        op.accept(visitor);
        assertEquals("+ (5, 3)", visitor.getResult());
    }

    @Test
    void testOperationToStringPostfix() throws IllegalConstruction {
        List<Expression> params = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation op = new Plus(params);
        StringVisitor visitor = new StringVisitor(Notation.POSTFIX);
        op.accept(visitor);
        assertEquals("(5, 3) +", visitor.getResult());
    }

    @Test
    void testComplexExpression() throws IllegalConstruction {
        // Create expression: (5 + 3) * 2
        List<Expression> params1 = Arrays.asList(new MyNumber(5), new MyNumber(3));
        Operation plus = new Plus(params1);
        List<Expression> params2 = Arrays.asList(plus, new MyNumber(2));
        Operation times = new Times(params2);

        StringVisitor visitor = new StringVisitor(Notation.INFIX);
        times.accept(visitor);
        assertEquals("( ( 5 + 3 ) * 2 )", visitor.getResult());
    }
} 