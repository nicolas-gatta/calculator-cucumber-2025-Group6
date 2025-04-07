package calculator.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple Calculator");
        
        // Create the user interface
        CalculatorUI calculatorUI = new CalculatorUI();
        
        // Configure the scene with wider size
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

    public static void main(String[] args) {
        launch(args);
    }
}