package calculator.gui;

import javafx.scene.control.Alert;

public class HelpDialog {
    
    public static void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculator Help");
        alert.setHeaderText("How to use the calculator");
        
        String helpText = """
            Simple Calculator Instructions:
            
            1. Enter the first number using the numeric buttons
            2. Click an operation button (+, -, *, /)
            3. Enter the second number
            4. Click = to see the result
            
            Additional features:
            - C button clears the calculator
            - You can continue calculations with the result
            """;
        
        alert.setContentText(helpText);
        alert.showAndWait();
    }
}