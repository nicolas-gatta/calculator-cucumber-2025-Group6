package visitor;

import calculator.numbers.MyNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.numbers.RationalNumber;
import calculator.operations.Operation;
import calculator.operations.Plus;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Locale;
import java.lang.reflect.Field;

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
    
    @Test
    void testVisitComplexNumber() {
        ComplexNumber n = new ComplexNumber(1234, 5678);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        visitor.visit(n);
        assertEquals("1.234E+03 + 5.678E+03i", visitor.getResult());
    }
    
    @Test
    void testVisitComplexNumberWithNegativeImaginary() {
        ComplexNumber n = new ComplexNumber(1234, -5678);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        visitor.visit(n);
        assertEquals("1.234E+03 + -5.678E+03i", visitor.getResult());
    }
    
    @Test
    void testVisitRationalNumber() {
        RationalNumber n = new RationalNumber(1234, 5678);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(4);
        visitor.visit(n);
        // 1234/5678 = 0.2173...
        assertEquals("2.1733E-01", visitor.getResult());
    }
    
    @Test
    void testVisitOperation() {
        try {
            Operation op = new Plus(Arrays.asList(new MyNumber(1), new MyNumber(2)));
            ScientificNotationVisitor visitor = new ScientificNotationVisitor();
            visitor.visit(op);
            // Operations should just return their toString representation
            assertEquals(op.toString(), visitor.getResult());
        } catch (Exception e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }
    
    @Test
    void testDifferentPrecisions() {
        RealNumber n = new RealNumber(Math.PI, 10);
        
        // Test with precision 1
        ScientificNotationVisitor visitor1 = new ScientificNotationVisitor(1);
        visitor1.visit(n);
        assertEquals("3.1E+00", visitor1.getResult());
        
        // Test with precision 3
        ScientificNotationVisitor visitor3 = new ScientificNotationVisitor(3);
        visitor3.visit(n);
        assertEquals("3.142E+00", visitor3.getResult());
        
        // Test with precision 8
        ScientificNotationVisitor visitor8 = new ScientificNotationVisitor(8);
        visitor8.visit(n);
        assertEquals("3.14159265E+00", visitor8.getResult());
    }
    
    @Test
    void testVeryLargeNumber() {
        RealNumber n = new RealNumber(6.022e23, 4); // Avogadro's number
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(4);
        visitor.visit(n);
        assertEquals("6.0220E+23", visitor.getResult());
    }
    
    @Test
    void testVerySmallNumber() {
        RealNumber n = new RealNumber(1.6e-35, 3); // Planck length scale
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        visitor.visit(n);
        assertEquals("1.600E-35", visitor.getResult());
    }
    
    @Test
    void testZero() {
        RealNumber n = new RealNumber(0.0, 2);
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
        visitor.visit(n);
        assertEquals("0.00E+00", visitor.getResult());
    }

    @Test
    void testLocaleIndependence() {
        // Save the default locale
        Locale defaultLocale = Locale.getDefault();
        try {
            // Set system locale to French (uses comma as decimal separator)
            Locale.setDefault(Locale.FRANCE);
            
            RealNumber n = new RealNumber(1234.56, 2);
            ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
            visitor.visit(n);
            
            // Despite French locale being active, result should use US format (point, not comma)
            assertEquals("1.23E+03", visitor.getResult());
            
            // Set system locale to German (also uses comma)
            Locale.setDefault(Locale.GERMANY);
            visitor.visit(n);
            
            // Should still use US format
            assertEquals("1.23E+03", visitor.getResult());
        } finally {
            // Restore the default locale
            Locale.setDefault(defaultLocale);
        }
    }
    
    @Test
    void testComplexNumberFormatWithDifferentLocales() {
        // Save the default locale
        Locale defaultLocale = Locale.getDefault();
        try {
            // Set system locale to French
            Locale.setDefault(Locale.FRANCE);
            
            ComplexNumber n = new ComplexNumber(1.23, 4.56);
            ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
            visitor.visit(n);
            
            // Should use points, not commas
            assertEquals("1.23E+00 + 4.56E+00i", visitor.getResult());
        } finally {
            // Restore the default locale
            Locale.setDefault(defaultLocale);
        }
    }
    
    @Test
    void testRationalNumberFormatWithDifferentLocales() {
        // Save the default locale
        Locale defaultLocale = Locale.getDefault();
        try {
            // Set system locale to French
            Locale.setDefault(Locale.FRANCE);
            
            RationalNumber n = new RationalNumber(1, 4); // 0.25
            ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
            visitor.visit(n);
            
            // Should use point, not comma
            assertEquals("2.50E-01", visitor.getResult());
        } finally {
            // Restore the default locale
            Locale.setDefault(defaultLocale);
        }
    }
    @Test
    void testUSLocaleConstant() throws Exception {
        // Use reflection to access the private field
        Field localeField = ScientificNotationVisitor.class.getDeclaredField("US_LOCALE");
        localeField.setAccessible(true);
        
        // Get the value of the field from a new instance
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        Locale usLocale = (Locale) localeField.get(visitor);
        
        // Verify it's the US locale
        assertEquals(Locale.US, usLocale);
    }
} 