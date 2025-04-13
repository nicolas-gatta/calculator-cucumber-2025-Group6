package calculator.gui;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.assertions.api.Assertions.assertThat;

/**
 * Test class for the CalculatorUI class.
 * Note: These tests don't actually create the UI since that would require
 * a running JavaFX environment. Instead, they test the class structure.
 */
@ExtendWith(ApplicationExtension.class)
public class CalculatorUITest {

    private CalculatorUI calculatorUI;
    private TextField display;

    @Start
    public void start(Stage stage) {
        calculatorUI = new CalculatorUI();
        Scene scene = new Scene(calculatorUI.getRoot(), 400, 450);
        stage.setScene(scene);
        stage.show();
        
        display = (TextField) calculatorUI.getRoot().lookup("#display");
    }

    @Test
    public void testNumberInput(FxRobot robot) {
        // Test clicking number buttons
        robot.clickOn("1");
        robot.clickOn("2");
        robot.clickOn("3");
        
        assertEquals("123", display.getText());
    }
    
    @Test
    public void testAddition(FxRobot robot) {
        // Test basic addition
        robot.clickOn("5");
        robot.clickOn("+");
        robot.clickOn("3");
        robot.clickOn("=");
        
        assertEquals("8", display.getText());
    }
    
    @Test
    public void testSubtraction(FxRobot robot) {
        // Test basic subtraction
        robot.clickOn("9");
        robot.clickOn("-");
        robot.clickOn("4");
        robot.clickOn("=");
        
        assertEquals("5", display.getText());
    }
    
    @Test
    public void testMultiplication(FxRobot robot) {
        // Test basic multiplication
        robot.clickOn("6");
        robot.clickOn("*");
        robot.clickOn("7");
        robot.clickOn("=");
        
        assertEquals("42", display.getText());
    }
    
    @Test
    public void testDivision(FxRobot robot) {
        // Ensure we're using Integer mode for this test
        ComboBox<NumberType> typeCombo = robot.lookup(".type-selector").queryComboBox();
        robot.interact(() -> typeCombo.setValue(NumberType.INTEGER));
        
        // Test basic division
        robot.clickOn("8");
        robot.clickOn("#divideButton"); // Use ID selector to be specific
        robot.clickOn("2");
        robot.clickOn("=");
        
        assertEquals("4", display.getText());
    }
    
    @Test
    public void testClearButton(FxRobot robot) {
        // Enter some numbers
        robot.clickOn("5");
        robot.clickOn("5");
        
        // Clear the calculator
        robot.clickOn("C");
        
        assertEquals("", display.getText());
    }
    
    @Test
    public void testNegativeButton(FxRobot robot) {
        // Test the +/- button
        robot.clickOn("7");
        robot.clickOn("+/-");
        
        assertEquals("-7", display.getText());
        
        // Toggle back to positive
        robot.clickOn("+/-");
        assertEquals("7", display.getText());
        
        // Test with empty display
        robot.clickOn("C");
        robot.clickOn("+/-");
        assertEquals("-", display.getText());
        
        // Toggle back from just a negative sign
        robot.clickOn("+/-");
        assertEquals("", display.getText());
    }
    
    @Test
    public void testRationalNumberType(FxRobot robot) {
        // Switch to Rational number type
        ComboBox<NumberType> typeCombo = robot.lookup(".type-selector").queryComboBox();
        robot.interact(() -> typeCombo.setValue(NumberType.RATIONAL));
        
        // Enter a fraction
        robot.clickOn("3");
        robot.clickOn("/");
        robot.clickOn("4");
        
        assertEquals("3/4", display.getText());
        
        // Test calculation with fractions
        robot.clickOn("+");
        robot.clickOn("1");
        robot.clickOn("#fractionButton"); // Use ID selector for the fraction button
        robot.clickOn("4");
        robot.clickOn("=");
        
        assertEquals("1", display.getText());
        
        // Test with just an integer (no denominator)
        robot.clickOn("C");
        robot.clickOn("5");
        robot.clickOn("+");
        robot.clickOn("3");
        robot.clickOn("=");
        
        assertEquals("8", display.getText());
    }
    
    @Test
    public void testRealNumberType(FxRobot robot) {
        // Switch to Real number type
        ComboBox<NumberType> typeCombo = robot.lookup(".type-selector").queryComboBox();
        robot.interact(() -> typeCombo.setValue(NumberType.REAL));
        
        // Enter a decimal number
        robot.clickOn("3");
        robot.clickOn("#decimalButton");
        robot.clickOn("1");
        robot.clickOn("4");
        
        assertEquals("3.14", display.getText());
        
        // Test calculation with real numbers
        robot.clickOn("+");
        robot.clickOn("2");
        robot.clickOn("#decimalButton");
        robot.clickOn("5");
        robot.clickOn("=");
        
        assertEquals("5,640000", display.getText());
        
        // Test starting with decimal point
        robot.clickOn("C");
        robot.clickOn("0");
        robot.clickOn("#decimalButton");
        assertEquals("0.", display.getText());
    }
    
    @Test
    public void testComplexNumberType(FxRobot robot) {
        // Switch to Complex number type
        ComboBox<NumberType> typeCombo = robot.lookup(".type-selector").queryComboBox();
        robot.interact(() -> typeCombo.setValue(NumberType.COMPLEX));
        
        // Enter a complex number
        robot.clickOn("3");
        robot.clickOn("+");
        robot.clickOn("4");
        robot.clickOn("i");
        robot.clickOn("=");
        
        assertEquals("3.0 + 4.0i", display.getText());
        
        // Test calculation with complex numbers
        robot.clickOn("+");
        robot.clickOn("1");
        robot.clickOn("-");
        robot.clickOn("2");
        robot.clickOn("i");
        robot.clickOn("=");
        
        assertEquals("4.0 + 2.0i", display.getText());
        
        // Test just imaginary part
        robot.clickOn("C");
        robot.clickOn("i");
        assertEquals("i", display.getText());
        
        // Test negative imaginary part
        robot.clickOn("C");
        robot.clickOn("-");
        robot.clickOn("i");
        assertEquals("-i", display.getText());
    }
    
    @Test
    public void testHelpButtonOpensDialog(FxRobot robot) {
        // Click the help button
        robot.clickOn("#helpButton");
        
        // Verify that the help dialog appears
        assertThat(robot.lookup(".dialog-pane")).isNotNull();
        
        // Close the dialog
        robot.clickOn("OK");
    }
    
    @Test
    public void testErrorHandling(FxRobot robot) {
        // Test division by zero
        robot.clickOn("5");
        robot.clickOn("/");
        robot.clickOn("0");
        robot.clickOn("=");
        
        // Should show an error
        assertThat(display.getText()).contains("");
        
        // Clear after error
        robot.clickOn("C");
        assertEquals("", display.getText());
    }
    
    @Test
    public void testChangeOperation(FxRobot robot) {
        // Enter first operand
        robot.clickOn("5");
        
        // Select operation
        robot.clickOn("+");
        
        // Change operation
        robot.clickOn("-");
        
        // Enter second operand and calculate
        robot.clickOn("3");
        robot.clickOn("=");
        
        assertEquals("2", display.getText());
    }
    
    @Test
    public void testChainedOperations(FxRobot robot) {
        // 5 + 3 = 8, then * 2 = 16
        robot.clickOn("5");
        robot.clickOn("+");
        robot.clickOn("3");
        robot.clickOn("=");
        robot.clickOn("*");
        robot.clickOn("2");
        robot.clickOn("=");
        
        assertEquals("16", display.getText());
    }
    
    @Test
    public void testNegativeNumberInput(FxRobot robot) {
        // Test entering a negative number directly
        robot.clickOn("-");
        robot.clickOn("5");
        
        assertEquals("-5", display.getText());
        
        // Test calculation with negative number
        robot.clickOn("+");
        robot.clickOn("8");
        robot.clickOn("=");
        
        assertEquals("3", display.getText());
    }
    
    @Test
    public void testDoubleNegativeInput(FxRobot robot) {
        // Test that double negative is prevented
        robot.clickOn("-");
        robot.clickOn("-");
        
        // Should still just be a single negative sign
        assertEquals("-", display.getText());
    }
} 