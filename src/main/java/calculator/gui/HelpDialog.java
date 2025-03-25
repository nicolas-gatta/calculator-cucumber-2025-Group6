package calculator.gui;

import javafx.scene.control.Alert;

public class HelpDialog {
    
    public static void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculator Help");
        alert.setHeaderText("How to use the calculator");
        
        String helpText = """
            Simple Calculator Instructions:
            
            1. Select the number type (Integer or Rational)
            2. Enter the first number:
               - For integers: just type the digits
               - For rationals: type numerator/denominator (e.g., 3/4)
            3. Click an operation button (+, -, *, /)
            4. Enter the second number
            5. Click = to see the result
            
            Additional features:
            - C button clears the calculator
            - You can continue calculations with the result
            """;
        
        alert.setContentText(helpText);
        alert.showAndWait();
    }
}