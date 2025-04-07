package calculator.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.geometry.Insets;

public class HelpDialog {
    
    public static void showHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Calculator Help");
        alert.setHeaderText("How to use the calculator");
        
        // Create styled content
        VBox content = new VBox(15);
        content.setPadding(new Insets(10));
        
        // Title
        Label titleLabel = new Label("Scientific Calculator Instructions");
        titleLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 16));
        titleLabel.setStyle("-fx-text-fill: #2c3e50;");
        
        // Number types section
        Label typesTitle = new Label("Number Types:");
        typesTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        typesTitle.setStyle("-fx-text-fill: #3498db;");
        
        TextFlow typesText = createTextFlow(
            "• Integer: Whole numbers (e.g., 42, -7)\n" +
            "• Rational: Fractions (e.g., 3/4, -5/2)\n" +
            "• Real: Decimal numbers (e.g., 3.14, -0.5)\n" +
            "• Complex: Numbers with real and imaginary parts (e.g., 3+4i, 5i)"
        );
        
        // Basic usage section
        Label usageTitle = new Label("Basic Usage:");
        usageTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        usageTitle.setStyle("-fx-text-fill: #e67e22;");
        
        TextFlow usageText = createTextFlow(
            "1. Select the number type from the dropdown\n" +
            "2. Enter the first number\n" +
            "3. Click an operation button (+, -, *, /)\n" +
            "4. Enter the second number\n" +
            "5. Click = to see the result"
        );
        
        // Special features section
        Label featuresTitle = new Label("Special Features:");
        featuresTitle.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        featuresTitle.setStyle("-fx-text-fill: #2ecc71;");
        
        TextFlow featuresText = createTextFlow(
            "• +/- button: Toggle between positive and negative\n" +
            "• C button: Clear the calculator\n" +
            "• / button (Rational): Add fraction separator\n" +
            "• . button (Real/Complex): Add decimal point\n" +
            "• i button (Complex): Add imaginary unit"
        );
        
        // Add all elements to the content box
        content.getChildren().addAll(
            titleLabel,
            typesTitle, typesText,
            usageTitle, usageText,
            featuresTitle, featuresText
        );
        
        // Set the content in the dialog
        alert.getDialogPane().setContent(content);
        
        // Style the dialog
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStyleClass().add("help-dialog");
        dialogPane.setStyle(
            "-fx-background-color: #f5f5f5;" +
            "-fx-font-family: 'Segoe UI', Arial, sans-serif;"
        );
        
        // Add OK button
        alert.getButtonTypes().setAll(ButtonType.OK);
        
        // Show the dialog
        alert.showAndWait();
    }
    
    private static TextFlow createTextFlow(String text) {
        TextFlow textFlow = new TextFlow();
        Text textNode = new Text(text);
        textNode.setFont(Font.font("Segoe UI", 12));
        textNode.setStyle("-fx-fill: #34495e;");
        textFlow.getChildren().add(textNode);
        return textFlow;
    }
}