package calculator.gui;

import calculator.Calculator;
import calculator.Expression;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.operations.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class CalculatorUI {

    private final VBox root;
    private final TextField display;
    private final Calculator calculator;
    
    private String currentInput = "";
    private String currentOperation = null;
    private Expression firstOperand = null;
    
    // Actual number type
    private NumberType currentNumberType = NumberType.INTEGER;
    
    // Fraction button
    private Button fractionButton;

    public CalculatorUI() {
        calculator = new Calculator();
        
        // Create the display
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setPrefHeight(50);
        
        // Create a horizontal box for the display and the help button
        HBox displayBox = new HBox(5);
        
        // Create the help button
        Button helpButton = new Button("?");
        helpButton.setPrefSize(40, 50);
        helpButton.setOnAction(e -> HelpDialog.showHelp());
        
        // Add the display and the help button to the horizontal box
        displayBox.getChildren().addAll(display, helpButton);
        
        // Configure the display to take all the available space
        HBox.setHgrow(display, javafx.scene.layout.Priority.ALWAYS);
        
        // Create the type selector
        HBox typeSelector = createTypeSelector();
        
        // Create the numeric keyboard and operations
        GridPane buttonGrid = createButtonGrid();
        
        // Assemble the interface
        root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(displayBox, typeSelector, buttonGrid);
    }
    
    private HBox createTypeSelector() {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        
        Label label = new Label("Number Type:");
        
        ComboBox<NumberType> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(NumberType.INTEGER, NumberType.RATIONAL);
        typeCombo.setValue(currentNumberType);
        typeCombo.setOnAction(e -> {
            currentNumberType = typeCombo.getValue();
            updateTypeSpecificButtons();
        });
        
        // Create the fraction button here rather than in the grid
        fractionButton = new Button("/");
        fractionButton.setPrefSize(40, 30);
        fractionButton.setDisable(currentNumberType != NumberType.RATIONAL);
        fractionButton.setOnAction(e -> {
            if (currentNumberType == NumberType.RATIONAL && 
                !currentInput.contains("/") && 
                !currentInput.isEmpty()) {
                currentInput += "/";
                display.setText(currentInput);
            }
        });
        
        // Add the button to the horizontal box
        box.getChildren().addAll(label, typeCombo, fractionButton);
        return box;
    }
    
    private void updateTypeSpecificButtons() {
        // Activate/deactivate the fraction button according to the type
        if (fractionButton != null) {
            fractionButton.setDisable(currentNumberType != NumberType.RATIONAL);
            fractionButton.setVisible(currentNumberType == NumberType.RATIONAL);
        }
        
        // Reset the display if necessary
        if (!currentInput.isEmpty()) {
            clearCalculator();
        }
    }
    
    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(5);
        grid.setVgap(5);
        
        // Numeric buttons
        for (int i = 0; i < 10; i++) {
            final int number = i;
            Button button = new Button(Integer.toString(i));
            button.setPrefSize(50, 50);
            button.setOnAction(e -> handleNumberInput(number));
            
            // Place the buttons in the grid (0 at the bottom)
            if (i == 0) {
                grid.add(button, 1, 3);
            } else {
                grid.add(button, (i - 1) % 3, 2 - (i - 1) / 3);
            }
        }
        
        // Operation buttons
        Button plusButton = createOperationButton("+");
        Button minusButton = createOperationButton("-");
        Button multiplyButton = createOperationButton("*");
        Button divideButton = createOperationButton("/");
        
        grid.add(plusButton, 3, 0);
        grid.add(minusButton, 3, 1);
        grid.add(multiplyButton, 3, 2);
        grid.add(divideButton, 3, 3);
        
        // Equal button
        Button equalsButton = new Button("=");
        equalsButton.setPrefSize(50, 50);
        equalsButton.setOnAction(e -> calculateResult());
        grid.add(equalsButton, 2, 3);
        
        // Clear button
        Button clearButton = new Button("C");
        clearButton.setPrefSize(50, 50);
        clearButton.setOnAction(e -> clearCalculator());
        grid.add(clearButton, 0, 3);
        
        return grid;
    }
    
    private Button createOperationButton(String operation) {
        Button button = new Button(operation);
        button.setPrefSize(50, 50);
        button.setOnAction(e -> handleOperation(operation));
        return button;
    }
    
    private void handleNumberInput(int number) {
        currentInput += number;
        display.setText(currentInput);
    }
    
    private void handleOperation(String operation) {
        if (!currentInput.isEmpty()) {
            if (firstOperand == null) {
                // First operand
                firstOperand = parseInput(currentInput);
                currentOperation = operation;
                currentInput = "";
                display.setText(operation);
            } else {
                // Intermediate calculation if an operation is already in progress
                calculateResult();
                currentOperation = operation;
                display.setText(operation);
            }
        } else if (firstOperand != null) {
            // Change operation
            currentOperation = operation;
            display.setText(operation);
        }
    }
    
    private Expression parseInput(String input) {
        try {
            switch (currentNumberType) {
                case INTEGER:
                    return new MyNumber(Integer.parseInt(input));
                    
                case RATIONAL:
                    if (input.contains("/")) {
                        String[] parts = input.split("/");
                        if (parts.length == 2) {
                            int numerator = Integer.parseInt(parts[0]);
                            int denominator = Integer.parseInt(parts[1]);
                            if (denominator == 0) {
                                throw new ArithmeticException("Division by zero");
                            }
                            return new RationalNumber(numerator, denominator);
                        }
                    }
                    // If there is no "/" or incorrect format, treat as an integer
                    return new RationalNumber(Integer.parseInt(input), 1);
                    
                default:
                    throw new IllegalArgumentException("Unsupported number type: " + currentNumberType);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + input);
        }
    }
    
    private void calculateResult() {
        if (firstOperand != null && !currentInput.isEmpty() && currentOperation != null) {
            try {
                Expression secondOperand = parseInput(currentInput);
                
                List<Expression> params = new ArrayList<>();
                params.add(firstOperand);
                params.add(secondOperand);
                
                Expression operation = switch (currentOperation) {
                    case "+" -> new Plus(params);
                    case "-" -> new Minus(params);
                    case "*" -> new Times(params);
                    case "/" -> new Divides(params);
                    default -> throw new IllegalArgumentException("Unknown operation: " + currentOperation);
                };
                
                Expression result = calculator.eval(operation);
                updateDisplayWithResult(result);
                
                firstOperand = result;
                currentInput = "";
                currentOperation = null;
            } catch (Exception e) {
                display.setText("Error: " + e.getMessage());
                clearCalculator();
            }
        }
    }
    
    private void updateDisplayWithResult(Expression result) {
        if (result instanceof MyNumber) {
            display.setText(Integer.toString(((MyNumber) result).getValue()));
        } else if (result instanceof RationalNumber) {
            RationalNumber rn = (RationalNumber) result;
            if (rn.getDenominator() == 1) {
                display.setText(Integer.toString(rn.getNumerator()));
            } else {
                display.setText(rn.getNumerator() + "/" + rn.getDenominator());
            }
        } else {
            display.setText(result.toString());
        }
    }
    
    private void clearCalculator() {
        currentInput = "";
        currentOperation = null;
        firstOperand = null;
        display.setText("");
    }
    
    public VBox getRoot() {
        return root;
    }
}