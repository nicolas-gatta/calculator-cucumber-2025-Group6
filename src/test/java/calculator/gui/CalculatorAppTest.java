package calculator.gui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.testfx.assertions.api.Assertions.assertThat;

/**
 * Test class for the CalculatorApp class.
 * Note: These tests don't actually launch the JavaFX application since that would require
 * a running JavaFX environment. Instead, they test the class structure.
 */
@ExtendWith(ApplicationExtension.class)
public class CalculatorAppTest {

    private CalculatorApp app;

    @Start
    public void start(Stage stage) {
        app = new CalculatorApp();
        app.start(stage);
    }

    @Test
    public void testAppInitialization(FxRobot robot) {
        // Test that the app initializes correctly
        assertNotNull(app);
        
        // Test that the UI elements are present
        assertThat(robot.lookup("#display").queryAs(javafx.scene.control.TextField.class)).isNotNull();
        assertThat(robot.lookup("#clearButton").queryAs(javafx.scene.control.Button.class)).isNotNull();
        assertThat(robot.lookup("#equalsButton").queryAs(javafx.scene.control.Button.class)).isNotNull();
        assertThat(robot.lookup("#helpButton").queryAs(javafx.scene.control.Button.class)).isNotNull();
    }
    
} 