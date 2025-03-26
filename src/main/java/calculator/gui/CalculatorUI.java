package calculator.gui;

import calculator.Calculator;
import calculator.Expression;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
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
    private Button decimalButton;
    private Button imaginaryButton;

    // Add a field to store the reference to the HBox
    private HBox box;

    public CalculatorUI() {
        calculator = new Calculator();
        
        // Create the display
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.CENTER);
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
        box = createTypeSelector();
        
        // Create the numeric keyboard and operations
        GridPane buttonGrid = createButtonGrid();
        
        // Assemble the interface
        root = new VBox(10);
        root.setPadding(new Insets(10));
        root.getChildren().addAll(displayBox, box, buttonGrid);
    }
    
    private HBox createTypeSelector() {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER_LEFT);
        
        Label label = new Label("Number Type:");
        
        ComboBox<NumberType> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(NumberType.INTEGER, NumberType.RATIONAL, NumberType.REAL, NumberType.COMPLEX);
        typeCombo.setValue(currentNumberType);
        typeCombo.setOnAction(e -> {
            currentNumberType = typeCombo.getValue();
            updateTypeSpecificButtons();
        });
        
        // Adjust the width of the ComboBox to display the text completely
        typeCombo.setPrefWidth(100);
        
        // Fraction button for rational numbers
        fractionButton = new Button("/");
        fractionButton.setPrefSize(40, 30);
        fractionButton.setDisable(currentNumberType != NumberType.RATIONAL);
        fractionButton.setVisible(currentNumberType == NumberType.RATIONAL);
        fractionButton.setOnAction(e -> {
            if (currentNumberType == NumberType.RATIONAL && 
                !currentInput.contains("/") && 
                !currentInput.isEmpty()) {
                currentInput += "/";
                display.setText(currentInput);
            }
        });
        
        // Decimal button for real and complex numbers
        decimalButton = new Button(".");
        decimalButton.setPrefSize(40, 30);
        decimalButton.setDisable(currentNumberType != NumberType.REAL && currentNumberType != NumberType.COMPLEX);
        decimalButton.setVisible(currentNumberType == NumberType.REAL || currentNumberType == NumberType.COMPLEX);
        decimalButton.setOnAction(e -> {
            if ((currentNumberType == NumberType.REAL || currentNumberType == NumberType.COMPLEX) && 
                !currentInput.contains(".")) {
                if (currentInput.isEmpty()) {
                    currentInput = "0.";
                } else {
                    currentInput += ".";
                }
                display.setText(currentInput);
            }
        });
        
        // Imaginary button for complex numbers
        imaginaryButton = new Button("i");
        imaginaryButton.setPrefSize(40, 30);
        imaginaryButton.setDisable(currentNumberType != NumberType.COMPLEX);
        imaginaryButton.setVisible(currentNumberType == NumberType.COMPLEX);
        imaginaryButton.setOnAction(e -> {
            if (currentNumberType == NumberType.COMPLEX) {
                currentInput += "i";
                display.setText(currentInput);
            }
        });
        
        box.getChildren().addAll(label, typeCombo, fractionButton, decimalButton, imaginaryButton);
        return box;
    }
    
    private void updateTypeSpecificButtons() {
        boolean isRational = currentNumberType == NumberType.RATIONAL;
        boolean isReal = currentNumberType == NumberType.REAL;
        boolean isComplex = currentNumberType == NumberType.COMPLEX;
        
        // Enable/disable the specific buttons according to the type
        if (fractionButton != null) {
            fractionButton.setDisable(!isRational);
            fractionButton.setVisible(isRational);
        }
        
        if (decimalButton != null) {
            decimalButton.setDisable(!(isReal || isComplex));
            decimalButton.setVisible(isReal || isComplex);
        }
        
        if (imaginaryButton != null) {
            imaginaryButton.setDisable(!isComplex);
            imaginaryButton.setVisible(isComplex);
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
        
        // Button for the negative sign
        Button negativeButton = new Button("+/-");
        negativeButton.setPrefSize(50, 50);
        negativeButton.setOnAction(e -> toggleNegative());
        grid.add(negativeButton, 0, 4);
        
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
        // If the input is just a negative sign and an operation is pressed
        if (currentInput.equals("-") && operation.equals("-")) {
            return; // Ignore to avoid "--"
        }
        
        // If the input is empty and a "-" is pressed, allow a negative number
        if (currentInput.isEmpty() && operation.equals("-")) {
            currentInput = "-";
            display.setText(currentInput);
            return;
        }
        
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
                    
                case REAL:
                    double value = Double.parseDouble(input);
                    return new RealNumber(value, 6);
                    
                case COMPLEX:
                    return parseComplexNumber(input);
                    
                default:
                    throw new IllegalArgumentException("Unsupported number type: " + currentNumberType);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + input);
        }
    }
    
    private ComplexNumber parseComplexNumber(String input) {
        // If the input is empty, return 0
        if (input.isEmpty()) {
            return new ComplexNumber(0, 0);
        }
        
        // If the input is just "i"
        if (input.equals("i")) {
            return new ComplexNumber(0, 1);
        }
        
        // If the input is just "-i"
        if (input.equals("-i")) {
            return new ComplexNumber(0, -1);
        }
        
        // If the input does not contain "i", it is just a real number
        if (!input.contains("i")) {
            return new ComplexNumber(Double.parseDouble(input), 0);
        }
        
        // If the input ends with "i" without an operator, it is just an imaginary number
        if (input.endsWith("i") && !input.contains("+") && !(input.indexOf("-", 1) > -1)) {
            String imagPart = input.substring(0, input.length() - 1);
            if (imagPart.isEmpty()) {
                return new ComplexNumber(0, 1);
            } else if (imagPart.equals("-")) {
                return new ComplexNumber(0, -1);
            } else {
                return new ComplexNumber(0, Double.parseDouble(imagPart));
            }
        }
        
        // General case: analyze the form a+bi or a-bi
        // Find the last + or - that is not at the beginning of the input
        int lastPlus = input.lastIndexOf("+");
        int lastMinus = input.lastIndexOf("-");
        
        // Ignore the initial - if it exists
        if (lastMinus == 0) {
            lastMinus = input.indexOf("-", 1);
        }
        
        int opIndex = Math.max(lastPlus, lastMinus);
        
        if (opIndex > 0) {
            String realPart = input.substring(0, opIndex);
            String imagPart = input.substring(opIndex, input.length() - 1); // Includes the sign
            
            double real = Double.parseDouble(realPart);
            double imag;
            
            if (imagPart.equals("+")) {
                imag = 1;
            } else if (imagPart.equals("-")) {
                imag = -1;
            } else {
                imag = Double.parseDouble(imagPart);
            }
            
            return new ComplexNumber(real, imag);
        }
        
        // If we get here, try to parse directly
        return new ComplexNumber(0, Double.parseDouble(input.substring(0, input.length() - 1)));
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
        } else if (result instanceof RealNumber) {
            RealNumber realNum = (RealNumber) result;
            display.setText(realNum.toString());
        } else if (result instanceof ComplexNumber) {
            ComplexNumber complexNum = (ComplexNumber) result;
            display.setText(complexNum.toString());
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
    
    // Method to toggle between positive and negative
    private void toggleNegative() {
        if (currentInput.isEmpty()) {
            currentInput = "-";
        } else if (currentInput.equals("-")) {
            currentInput = "";
        } else if (currentInput.startsWith("-")) {
            currentInput = currentInput.substring(1);
        } else {
            currentInput = "-" + currentInput;
        }
        display.setText(currentInput);
    }
    
    public VBox getRoot() {
        return root;
    }
}