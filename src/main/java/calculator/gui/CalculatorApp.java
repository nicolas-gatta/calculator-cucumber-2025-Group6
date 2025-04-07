package calculator.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Calculator.
 * This class initializes and launches the JavaFX application.
 */
public class CalculatorApp extends Application {

    /**
     * Constructs a new CalculatorApp instance.
     * This is the default constructor for the application.
     */
    public CalculatorApp() {
        // Default constructor
    }

    /**
     * Starts the JavaFX application.
     * Sets up the primary stage with the calculator UI.
     *
     * @param primaryStage The primary stage for this application
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");
        
        // Create the user interface
        CalculatorUI calculatorUI = new CalculatorUI();
        
        // Configure the scene with optimized size
        Scene scene = new Scene(calculatorUI.getRoot(), 400, 450);
        
        // Add CSS styling
        scene.getStylesheets().add(getClass().getResource("/calculator.css").toExternalForm());
        
        primaryStage.setScene(scene);
        
        // Set minimum window size
        primaryStage.setMinWidth(400);
        primaryStage.setMinHeight(420);
        
        // Make the stage resize to fit content
        primaryStage.sizeToScene();
        
        primaryStage.show();
    }

    /**
     * Main method that launches the application.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}