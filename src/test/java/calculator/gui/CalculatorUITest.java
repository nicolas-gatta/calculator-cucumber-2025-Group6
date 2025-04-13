package calculator.gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Test class for the CalculatorUI class.
 * Note: These tests don't actually create the UI since that would require
 * a running JavaFX environment. Instead, they test the class structure.
 */
public class CalculatorUITest {

    /**
     * Tests that the CalculatorUI class exists.
     * This is a simple structural test.
     */
    @Test
    public void testCalculatorUIExists() {
        // Simply referencing the class is enough to verify it exists
        assertNotNull(CalculatorUI.class);
    }
    
    /**
     * Tests that the required fields exist in the CalculatorUI class.
     */
    @Test
    public void testRequiredFieldsExist() {
        // Check for essential fields
        boolean hasRootField = false;
        boolean hasDisplayField = false;
        boolean hasCalculatorField = false;
        boolean hasCurrentNumberTypeField = false;
        
        for (Field field : CalculatorUI.class.getDeclaredFields()) {
            String fieldName = field.getName();
            if (fieldName.equals("root")) {
                hasRootField = true;
            } else if (fieldName.equals("display")) {
                hasDisplayField = true;
            } else if (fieldName.equals("calculator")) {
                hasCalculatorField = true;
            } else if (fieldName.equals("currentNumberType")) {
                hasCurrentNumberTypeField = true;
            }
        }
        
        assertTrue(hasRootField, "root field should exist in CalculatorUI class");
        assertTrue(hasDisplayField, "display field should exist in CalculatorUI class");
        assertTrue(hasCalculatorField, "calculator field should exist in CalculatorUI class");
        assertTrue(hasCurrentNumberTypeField, "currentNumberType field should exist in CalculatorUI class");
    }
    
    /**
     * Tests that the required methods exist in the CalculatorUI class.
     */
    @Test
    public void testRequiredMethodsExist() {
        boolean hasGetRootMethod = false;
        boolean hasCreateTypeSelectorMethod = false;
        boolean hasCreateButtonGridMethod = false;
        
        for (Method method : CalculatorUI.class.getDeclaredMethods()) {
            String methodName = method.getName();
            if (methodName.equals("getRoot")) {
                hasGetRootMethod = true;
            } else if (methodName.equals("createTypeSelector")) {
                hasCreateTypeSelectorMethod = true;
            } else if (methodName.equals("createButtonGrid")) {
                hasCreateButtonGridMethod = true;
            }
        }
        
        assertTrue(hasGetRootMethod, "getRoot method should exist in CalculatorUI class");
        assertTrue(hasCreateTypeSelectorMethod, "createTypeSelector method should exist in CalculatorUI class");
        assertTrue(hasCreateButtonGridMethod, "createButtonGrid method should exist in CalculatorUI class");
    }
    
    /**
     * Tests that the constructor exists and has the expected signature.
     */
    @Test
    public void testConstructorExists() {
        try {
            // Check if the constructor exists
            CalculatorUI.class.getDeclaredConstructor();
            // If we get here, the constructor exists
            assertTrue(true);
        } catch (NoSuchMethodException e) {
            fail("Default constructor should exist in CalculatorUI class");
        }
    }
} 