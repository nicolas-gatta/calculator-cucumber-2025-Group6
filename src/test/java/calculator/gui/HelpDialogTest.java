package calculator.gui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the HelpDialog class.
 * Note: These tests don't actually display the dialog since that would require
 * a running JavaFX environment. Instead, they test the class structure.
 */
public class HelpDialogTest {

    /**
     * Tests that the HelpDialog class exists and can be referenced.
     * This is a simple structural test.
     */
    @Test
    public void testHelpDialogExists() {
        // Simply referencing the class is enough to verify it exists
        assertNotNull(HelpDialog.class);
    }
    
    /**
     * Tests that the createTextFlow method exists by using reflection.
     * This verifies the structure of the class without calling the method.
     */
    @Test
    public void testCreateTextFlowMethodExists() {
        try {
            // Check if the method exists using reflection
            HelpDialog.class.getDeclaredMethod("createTextFlow", String.class);
            // If we get here, the method exists
            assertTrue(true);
        } catch (NoSuchMethodException e) {
            fail("createTextFlow method should exist in HelpDialog class");
        }
    }
    
    /**
     * Tests that the showHelp method exists by using reflection.
     * This verifies the structure of the class without calling the method.
     */
    @Test
    public void testShowHelpMethodExists() {
        try {
            // Check if the method exists using reflection
            HelpDialog.class.getDeclaredMethod("showHelp");
            // If we get here, the method exists
            assertTrue(true);
        } catch (NoSuchMethodException e) {
            fail("showHelp method should exist in HelpDialog class");
        }
    }
} 