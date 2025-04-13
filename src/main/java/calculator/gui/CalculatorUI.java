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
import javafx.scene.layout.ColumnConstraints;

import java.util.ArrayList;
import java.util.List;

/**
 * The CalculatorUI class provides the user interface for the calculator application.
 * It handles user input, displays results, and manages the calculator's operations.
 */
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

    /**
     * Constructs a new CalculatorUI.
     * Initializes the calculator interface with all necessary components.
     */
    public CalculatorUI() {
        calculator = new Calculator();
        
        // Create the display
        display = new TextField();
        display.setEditable(false);
        display.setAlignment(Pos.CENTER);
        display.setPrefHeight(60);
        display.setId("display");
        
        // Create a horizontal box for the display, +/- button, and the help button
        HBox displayBox = new HBox(10);
        displayBox.setPrefWidth(Double.MAX_VALUE);
        displayBox.setPadding(new Insets(0, 0, 10, 0));
        
        // Create the +/- button with a different approach to ensure text is visible
        Button negativeButton = new Button();
        negativeButton.setText("+/-");  // Set text explicitly
        negativeButton.setPrefSize(60, 60);  // Even wider
        negativeButton.setMinWidth(60);  // Set minimum width
        negativeButton.setMaxWidth(60);  // Set maximum width
        negativeButton.getStyleClass().addAll("special-button", "negative-button");  // Add specific class
        negativeButton.setOnAction(e -> toggleNegative());
        
        // Create the help button
        Button helpButton = new Button("?");
        helpButton.setPrefSize(60, 60);
        helpButton.setId("helpButton");
        helpButton.setOnAction(e -> HelpDialog.showHelp());
        
        // Add the display, +/- button, and the help button to the horizontal box
        displayBox.getChildren().addAll(display, negativeButton, helpButton);
        
        // Configure the display to take all the available space
        HBox.setHgrow(display, javafx.scene.layout.Priority.ALWAYS);
        
        // Create the type selector
        box = createTypeSelector();
        box.setPadding(new Insets(5, 0, 15, 0));
        
        // Create the numeric keyboard and operations
        GridPane buttonGrid = createButtonGrid();
        buttonGrid.setPrefWidth(Double.MAX_VALUE);
        buttonGrid.setPadding(new Insets(5));
        
        // Assemble the interface
        root = new VBox(5);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(displayBox, box, buttonGrid);
        root.setMaxHeight(VBox.USE_PREF_SIZE);
    }
    
    private HBox createTypeSelector() {
        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("type-selector-box");
        
        Label label = new Label("Type:");  // Shortened label text
        label.setMinWidth(50);  // Reduced width
        
        ComboBox<NumberType> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(NumberType.INTEGER, NumberType.RATIONAL, NumberType.REAL, NumberType.COMPLEX);
        typeCombo.setValue(currentNumberType);
        typeCombo.getStyleClass().add("type-selector");
        typeCombo.setOnAction(e -> {
            currentNumberType = typeCombo.getValue();
            updateTypeSpecificButtons();
        });
        
        // Increased width to show full text
        typeCombo.setPrefWidth(150);
        
        // Fraction button for rational numbers
        fractionButton = new Button("/");
        fractionButton.setPrefSize(45, 35);
        fractionButton.getStyleClass().add("special-button");
        fractionButton.setDisable(currentNumberType != NumberType.RATIONAL);
        fractionButton.setVisible(currentNumberType == NumberType.RATIONAL);
        fractionButton.setId("fractionButton");
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
        decimalButton.setId("decimalButton");
        decimalButton.setPrefSize(45, 35);
        decimalButton.getStyleClass().add("special-button");
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
        imaginaryButton.setPrefSize(45, 35);
        imaginaryButton.getStyleClass().add("special-button");
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
        grid.setHgap(10);  // Increased spacing
        grid.setVgap(10);  // Increased spacing
        grid.setAlignment(Pos.CENTER);
        
        // Make columns take equal space
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(25);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(25);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(25);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(25);
        
        grid.getColumnConstraints().addAll(column1, column2, column3, column4);
        
        // Numeric buttons
        for (int i = 0; i < 10; i++) {
            final int number = i;
            Button button = new Button(Integer.toString(i));
            button.setPrefSize(60, 60);  // Increased size
            button.setMaxWidth(Double.MAX_VALUE);
            button.setMaxHeight(Double.MAX_VALUE);
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
        divideButton.setId("divideButton");
        
        grid.add(plusButton, 3, 0);
        grid.add(minusButton, 3, 1);
        grid.add(multiplyButton, 3, 2);
        grid.add(divideButton, 3, 3);
        
        // Equal button
        Button equalsButton = new Button("=");
        equalsButton.setPrefSize(60, 60);  // Increased size
        equalsButton.setMaxWidth(Double.MAX_VALUE);
        equalsButton.setMaxHeight(Double.MAX_VALUE);
        equalsButton.setId("equalsButton");
        equalsButton.setOnAction(e -> calculateResult());
        grid.add(equalsButton, 2, 3);
        
        // Clear button
        Button clearButton = new Button("C");
        clearButton.setPrefSize(60, 60);  // Increased size
        clearButton.setMaxWidth(Double.MAX_VALUE);
        clearButton.setMaxHeight(Double.MAX_VALUE);
        clearButton.setId("clearButton");
        clearButton.setOnAction(e -> clearCalculator());
        grid.add(clearButton, 0, 3);
        
        return grid;
    }
    
    private Button createOperationButton(String operation) {
        Button button = new Button(operation);
        button.setPrefSize(60, 60);  // Increased size
        button.setMaxWidth(Double.MAX_VALUE);
        button.setMaxHeight(Double.MAX_VALUE);
        button.getStyleClass().add("operation-button");
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
    
    /**
     * Gets the root container of the calculator UI.
     *
     * @return The VBox containing all UI elements
     */
    public VBox getRoot() {
        return root;
    }
}