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
    void testVisitOperationSetsResultToString() throws Exception {
        // Create an operation
        calculator.operations.Operation op = new calculator.operations.Plus(
            Arrays.asList(new MyNumber(1), new MyNumber(2)));
        
        // Create a visitor
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        
        // Visit the operation
        visitor.visit(op);
        
        // Verify the result is the operation's toString
        assertEquals(op.toString(), visitor.getResult());
        
        // Also verify that the result field is set correctly using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String resultFieldValue = (String) resultField.get(visitor);
        
        assertEquals(op.toString(), resultFieldValue, 
                    "The result field should be set to the operation's toString value");
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

    @Test
    void testMatrixExpression() {
        // Create a matrix for the test
        calculator.matrix.Matrix matrix = new calculator.matrix.Matrix("[[1,2],[3,4]]");
        calculator.matrix.MatrixExpression matrixExpr = new calculator.matrix.MatrixExpression(matrix);
        
        // Test the visit of a matrix expression
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        visitor.visit(matrixExpr);
        
        // Verify that the result is the string representation of the matrix
        assertEquals(matrixExpr.toString(), visitor.getResult());
    }

    @Test
    void testConstructorWithDefaultPrecision() {
        // Test the default constructor
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        
        // Verify that the default precision is 6 by testing a number
        MyNumber n = new MyNumber(123);
        visitor.visit(n);
        
        // The format should use 6 significant digits
        assertEquals("1.230000E+02", visitor.getResult());
    }

    @Test
    void testConstructorWithCustomPrecision() {
        // Test the constructor with custom precision
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        
        // Verify that the precision is 3 by testing a number
        MyNumber n = new MyNumber(123);
        visitor.visit(n);
        
        // The format should use 3 significant digits
        assertEquals("1.230E+02", visitor.getResult());
    }

    @Test
    void testFormatWithUSLocale() {
        // Save the default locale
        Locale defaultLocale = Locale.getDefault();
        try {
            // Set a locale that uses a comma as the decimal separator
            Locale.setDefault(Locale.FRANCE);
            
            // Create a visitor and test a number
            ScientificNotationVisitor visitor = new ScientificNotationVisitor(2);
            MyNumber n = new MyNumber(123);
            visitor.visit(n);
            
            // Verify that the format uses the point (US_LOCALE) and not the comma
            assertEquals("1.23E+02", visitor.getResult());
            
        } finally {
            // Restore the default locale
            Locale.setDefault(defaultLocale);
        }
    }

    @Test
    void testDefaultConstructorSetsPrecisionToSix() throws Exception {
        // Create a visitor with the default constructor
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        
        // Use reflection to access the private precision field
        Field precisionField = ScientificNotationVisitor.class.getDeclaredField("precision");
        precisionField.setAccessible(true);
        
        // Get the actual value of the precision field
        int actualPrecision = (int) precisionField.get(visitor);
        
        // Verify that the default precision is 6
        assertEquals(6, actualPrecision, "Default precision should be 6");
    }

    @Test
    void testParameterizedConstructorSetsPrecisionCorrectly() throws Exception {
        // Create a visitor with a specific precision
        int expectedPrecision = 4;
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(expectedPrecision);
        
        // Use reflection to access the private precision field
        Field precisionField = ScientificNotationVisitor.class.getDeclaredField("precision");
        precisionField.setAccessible(true);
        
        // Get the actual value of the precision field
        int actualPrecision = (int) precisionField.get(visitor);
        
        // Verify that the precision is set to the expected value
        assertEquals(expectedPrecision, actualPrecision, 
                    "Precision should be set to the value provided in the constructor");
    }

    @Test
    void testVisitMyNumberFormatsCorrectly() throws Exception {
        // Create a visitor and a MyNumber
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        MyNumber n = new MyNumber(12345);
        
        // Visit the number
        visitor.visit(n);
        
        // Get the result
        String result = visitor.getResult();
        
        // Verify the result format
        assertEquals("1.235E+04", result);
        
        // Also verify that the result field is set correctly using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String resultFieldValue = (String) resultField.get(visitor);
        
        assertEquals("1.235E+04", resultFieldValue);
    }

    @Test
    void testVisitRealNumberFormatsCorrectly() throws Exception {
        // Create a visitor and a RealNumber
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        RealNumber n = new RealNumber(12345.678, 5);
        
        // Visit the number
        visitor.visit(n);
        
        // Verify the result format
        assertEquals("1.235E+04", visitor.getResult());
        
        // Also verify that the result field is set correctly using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String resultFieldValue = (String) resultField.get(visitor);
        
        assertEquals("1.235E+04", resultFieldValue, 
                    "The result field should be set to the formatted value");
    }

    @Test
    void testVisitComplexNumberFormatsCorrectly() throws Exception {
        // Create a visitor and a ComplexNumber
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        ComplexNumber n = new ComplexNumber(12.34, 56.78);
        
        // Visit the number
        visitor.visit(n);
        
        // Verify the result format
        assertEquals("1.234E+01 + 5.678E+01i", visitor.getResult());
        
        // Also verify that the result field is set correctly using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String resultFieldValue = (String) resultField.get(visitor);
        
        assertEquals("1.234E+01 + 5.678E+01i", resultFieldValue, 
                    "The result field should be set to the formatted complex value");
    }

    @Test
    void testVisitRationalNumberFormatsCorrectly() throws Exception {
        // Create a visitor and a RationalNumber
        ScientificNotationVisitor visitor = new ScientificNotationVisitor(3);
        RationalNumber n = new RationalNumber(1, 3); // 0.333...
        
        // Visit the number
        visitor.visit(n);
        
        // Verify the result format
        assertEquals("3.333E-01", visitor.getResult());
        
        // Also verify that the result field is set correctly using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String resultFieldValue = (String) resultField.get(visitor);
        
        assertEquals("3.333E-01", resultFieldValue, 
                    "The result field should be set to the formatted rational value");
    }

    @Test
    void testVisitMatrixExpressionFormatsCorrectly() throws Exception {
        // Create a visitor and a MatrixExpression
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        calculator.matrix.Matrix matrix = new calculator.matrix.Matrix("[[1,2],[3,4]]");
        calculator.matrix.MatrixExpression matrixExpr = new calculator.matrix.MatrixExpression(matrix);
        
        // Visit the matrix expression
        visitor.visit(matrixExpr);
        
        // Verify the result is the matrix's toString
        assertEquals(matrixExpr.toString(), visitor.getResult());
        
        // Also verify that the result field is set correctly using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String resultFieldValue = (String) resultField.get(visitor);
        
        assertEquals(matrixExpr.toString(), resultFieldValue, 
                    "The result field should be set to the matrix's toString value");
    }

    @Test
    void testGetResultMethod() throws Exception {
        // Create a visitor
        ScientificNotationVisitor visitor = new ScientificNotationVisitor();
        
        // Set the result field using reflection
        Field resultField = ScientificNotationVisitor.class.getDeclaredField("result");
        resultField.setAccessible(true);
        String expectedResult = "Test Result";
        resultField.set(visitor, expectedResult);
        
        // Call the getResult method
        String actualResult = visitor.getResult();
        
        // Verify that getResult returns the value of the result field
        assertEquals(expectedResult, actualResult, 
                    "getResult() should return the value of the result field");
    }
} 