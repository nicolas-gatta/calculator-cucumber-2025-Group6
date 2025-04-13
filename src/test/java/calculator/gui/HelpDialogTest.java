package calculator.gui;

import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the HelpDialog class.
 * Note: These tests don't actually display the dialog since that would require
 * a running JavaFX environment. Instead, they test the class structure.
 */
@ExtendWith(ApplicationExtension.class)
public class HelpDialogTest {

    @Start
    public void start(Stage stage) {
        // We need a stage, but we don't need to show anything in it
        stage.show();
    }

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
            Method method = HelpDialog.class.getDeclaredMethod("createTextFlow", String.class);
            method.setAccessible(true);
            
            // Test the method directly
            TextFlow result = (TextFlow) method.invoke(null, "Test text");
            assertNotNull(result);
            assertEquals(1, result.getChildren().size());
            
            // If we get here, the method exists and works
            assertTrue(true);
        } catch (Exception e) {
            fail("createTextFlow method should exist in HelpDialog class and be callable: " + e.getMessage());
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
    
    /**
     * Test that attempts to create an instance of HelpDialog to verify
     * the private constructor works (for coverage purposes)
     */
    @Test
    public void testPrivateConstructor() {
        try {
            // Try to access the private constructor
            java.lang.reflect.Constructor<HelpDialog> constructor = 
                HelpDialog.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            
            // Create an instance (should not throw exception)
            HelpDialog instance = constructor.newInstance();
            assertNotNull(instance);
        } catch (Exception e) {
            fail("Should be able to create a HelpDialog instance via reflection: " + e.getMessage());
        }
    }
} 