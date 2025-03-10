package visitor;

import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestScientificNotationVisitor {

    @Test
    void testVisitMyNumber() {
        MyNumber n = new MyNumber(1234);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
        visitor.visit(n);
        assertEquals("1.23E+03", visitor.getResult());
    }

    @Test
    void testVisitRealNumber() {
        RealNumber n = new RealNumber(0.000123, 2);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
        visitor.visit(n);
        assertEquals("1.23E-04", visitor.getResult());
    }

    @Test
    void testDefaultPrecision() {
        RealNumber n = new RealNumber(123456.789, 6);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        visitor.visit(n);
        assertEquals("1.234568E+05", visitor.getResult());
    }
} 