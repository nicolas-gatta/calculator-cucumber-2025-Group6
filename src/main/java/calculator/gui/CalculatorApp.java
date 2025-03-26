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
        
        // Configure the scene with une plus grande taille
        Scene scene = new Scene(calculatorUI.getRoot(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}