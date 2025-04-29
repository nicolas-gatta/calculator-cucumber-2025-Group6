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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import calculator.matrix.Matrix;
import calculator.linear.LinearEquationSolver;
import calculator.linear.LinearEquationSystemExpression;
import javafx.scene.control.TextInputDialog;

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

    // Linear system components
    private VBox linearSystemPanel;
    private GridPane equationGrid;
    private int numEquations = 2;
    private int numVariables = 2;
    private List<List<TextField>> coefficientFields;
    private List<TextField> constantFields;
    private List<String> variableNames;

    // Add a field to store the calculator content container
    private VBox calculatorContent;
    // Add a field to store the button grid
    private GridPane buttonGrid;

    // Matrix components
    private VBox matrixPanel;
    private GridPane matrixGrid;
    private int matrixRows = 2;
    private int matrixCols = 2;
    private List<List<TextField>> matrixFields;
    private Matrix currentMatrix;
    private Matrix secondMatrix;
    private boolean isSecondMatrix = false;

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
        buttonGrid = createButtonGrid();
        buttonGrid.setPrefWidth(Double.MAX_VALUE);
        buttonGrid.setPadding(new Insets(5));
        
        // Create linear system panel
        linearSystemPanel = createLinearSystemPanel();
        linearSystemPanel.setVisible(false);
        
        // Create matrix panel
        matrixPanel = createMatrixPanel();
        matrixPanel.setVisible(false);
        
        // Create a container for the calculator content that will switch between modes
        calculatorContent = new VBox();
        calculatorContent.getChildren().add(buttonGrid);
        
        // Assemble the interface
        root = new VBox(5);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(displayBox, box, calculatorContent);
        root.setMaxHeight(VBox.USE_PREF_SIZE);
    }
    
    private HBox createTypeSelector() {
        HBox box = new HBox(15);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("type-selector-box");
        
        Label label = new Label("Type:");  // Shortened label text
        label.setMinWidth(50);  // Reduced width
        
        ComboBox<NumberType> typeCombo = new ComboBox<>();
        typeCombo.getItems().addAll(NumberType.INTEGER, NumberType.RATIONAL, NumberType.REAL, 
                                   NumberType.COMPLEX, NumberType.LINEAR_SYSTEM, NumberType.MATRIX);
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
        boolean isLinearSystem = currentNumberType == NumberType.LINEAR_SYSTEM;
        boolean isMatrix = currentNumberType == NumberType.MATRIX;
        
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
        
        // Switch between calculator modes
        calculatorContent.getChildren().clear();
        if (isLinearSystem) {
            calculatorContent.getChildren().add(linearSystemPanel);
            linearSystemPanel.setVisible(true);
            matrixPanel.setVisible(false);
        } else if (isMatrix) {
            calculatorContent.getChildren().add(matrixPanel);
            matrixPanel.setVisible(true);
            linearSystemPanel.setVisible(false);
            isSecondMatrix = false;
            display.setText("");
        } else {
            calculatorContent.getChildren().add(buttonGrid);
            linearSystemPanel.setVisible(false);
            matrixPanel.setVisible(false);
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
    
    private VBox createLinearSystemPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER);
        
        // Controls for number of equations and variables
        HBox controlsBox = new HBox(15);
        controlsBox.setAlignment(Pos.CENTER);
        
        Label eqLabel = new Label("Equations:");
        ComboBox<Integer> eqCombo = new ComboBox<>();
        for (int i = 2; i <= 5; i++) {
            eqCombo.getItems().add(i);
        }
        eqCombo.setValue(numEquations);
        eqCombo.setOnAction(e -> {
            numEquations = eqCombo.getValue();
            updateEquationGrid();
        });
        
        Label varLabel = new Label("Variables:");
        ComboBox<Integer> varCombo = new ComboBox<>();
        for (int i = 2; i <= 5; i++) {
            varCombo.getItems().add(i);
        }
        varCombo.setValue(numVariables);
        varCombo.setOnAction(e -> {
            numVariables = varCombo.getValue();
            updateEquationGrid();
        });
        
        controlsBox.getChildren().addAll(eqLabel, eqCombo, varLabel, varCombo);
        
        // Create the equation grid
        equationGrid = new GridPane();
        equationGrid.setHgap(10);
        equationGrid.setVgap(10);
        equationGrid.setAlignment(Pos.CENTER);
        
        // Initialize coefficient fields
        coefficientFields = new ArrayList<>();
        constantFields = new ArrayList<>();
        variableNames = new ArrayList<>();
        
        // Generate default variable names (x, y, z, w, v)
        String[] defaultVars = {"x", "y", "z", "w", "v"};
        for (int i = 0; i < 5; i++) {
            variableNames.add(defaultVars[i]);
        }
        
        // Create initial grid
        updateEquationGrid();
        
        // Solve button
        Button solveButton = new Button("Solve");
        solveButton.setPrefSize(100, 40);
        solveButton.setOnAction(e -> solveLinearSystem());
        
        // Clear button
        Button clearButton = new Button("Clear");
        clearButton.setPrefSize(100, 40);
        clearButton.setOnAction(e -> clearLinearSystem());
        
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(solveButton, clearButton);
        
        panel.getChildren().addAll(controlsBox, equationGrid, buttonBox);
        return panel;
    }
    
    private void updateEquationGrid() {
        equationGrid.getChildren().clear();
        
        // Update coefficient fields lists
        coefficientFields.clear();
        constantFields.clear();
        
        // Add column headers (variable names)
        for (int j = 0; j < numVariables; j++) {
            Label varLabel = new Label(variableNames.get(j));
            varLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
            equationGrid.add(varLabel, j*2, 0);  // Multiplié par 2 pour laisser de l'espace pour les opérateurs
        }
        
        // Add equals sign and constant column header
        Label equalsLabel = new Label("=");
        equalsLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        equationGrid.add(equalsLabel, numVariables*2, 0);
        
        Label constLabel = new Label("Constant");
        constLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 14));
        equationGrid.add(constLabel, numVariables*2 + 1, 0);
        
        // Create the grid of text fields
        for (int i = 0; i < numEquations; i++) {
            List<TextField> rowFields = new ArrayList<>();
            
            for (int j = 0; j < numVariables; j++) {
                TextField field = new TextField();
                field.setPrefWidth(70);
                field.setMinWidth(70);
                field.setPrefHeight(30);  // Fixed height
                field.setMinHeight(30);   // Minimum height
                field.setMaxHeight(30);   // Maximum height
                field.setPadding(new Insets(0, 5, 0, 5));  // Horizontal padding only
                field.setAlignment(Pos.CENTER);
                
                // Use inline CSS to ensure the cursor is visible
                field.setStyle("-fx-background-insets: 0; -fx-padding: 0 5 0 5; -fx-background-radius: 0;");
                
                equationGrid.add(field, j*2, i + 1);  // Multiplied by 2 to leave space for operators
                rowFields.add(field);
                
                // Add coefficient label if not the last variable
                if (j < numVariables - 1) {
                    Label plusLabel = new Label("+");
                    plusLabel.setFont(Font.font("Segoe UI", 14));
                    GridPane.setHalignment(plusLabel, javafx.geometry.HPos.CENTER);
                    equationGrid.add(plusLabel, j*2 + 1, i + 1);
                }
            }
            
            coefficientFields.add(rowFields);
            
            // Add equals sign
            Label eqLabel = new Label("=");
            eqLabel.setFont(Font.font("Segoe UI", 14));
            GridPane.setHalignment(eqLabel, javafx.geometry.HPos.CENTER);
            equationGrid.add(eqLabel, numVariables*2, i + 1);
            
            // Add constant field
            TextField constField = new TextField();
            constField.setPrefWidth(70);
            constField.setMinWidth(70);
            constField.setPrefHeight(30);  // Height of the constant field
            constField.setMinHeight(30);   // Minimum height of the constant field
            constField.setMaxHeight(30);   // Maximum height of the constant field
            constField.setPadding(new Insets(0, 5, 0, 5));  // Padding horizontal uniquement
            constField.setAlignment(Pos.CENTER);
            
            // Use inline CSS to ensure the cursor is visible
            constField.setStyle("-fx-background-insets: 0; -fx-padding: 0 5 0 5; -fx-background-radius: 0;");
            
            equationGrid.add(constField, numVariables*2 + 1, i + 1);
            constantFields.add(constField);
        }
        
        // Adjust the grid spacing
        equationGrid.setHgap(5);  // Reduced horizontal spacing
        equationGrid.setVgap(10); // Maintained vertical spacing
    }
    
    private void solveLinearSystem() {
        try {
            // Create matrices for the linear system
            double[][] coefficients = new double[numEquations][numVariables];
            double[][] constants = new double[numEquations][1];
            
            // Fill the coefficient matrix
            for (int i = 0; i < numEquations; i++) {
                for (int j = 0; j < numVariables; j++) {
                    String value = coefficientFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        coefficients[i][j] = 0;
                    } else {
                        coefficients[i][j] = Double.parseDouble(value);
                    }
                }
                
                // Fill the constants vector
                String value = constantFields.get(i).getText().trim();
                if (value.isEmpty()) {
                    constants[i][0] = 0;
                } else {
                    constants[i][0] = Double.parseDouble(value);
                }
            }
            
            // Create Matrix objects
            Matrix A = new Matrix(coefficients);
            Matrix b = new Matrix(constants);
            
            // Solve the system
            LinearEquationSystemExpression solution = LinearEquationSolver.solve(A, b, variableNames.subList(0, numVariables));
            
            // Display the result
            display.setText(solution.toString());
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void clearLinearSystem() {
        // Clear all coefficient fields
        for (List<TextField> row : coefficientFields) {
            for (TextField field : row) {
                field.clear();
            }
        }
        
        // Clear all constant fields
        for (TextField field : constantFields) {
            field.clear();
        }
        
        // Clear the display
        display.setText("");
    }
    
    private VBox createMatrixPanel() {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setAlignment(Pos.CENTER);
        
        // Controls for matrix dimensions
        HBox controlsBox = new HBox(15);
        controlsBox.setAlignment(Pos.CENTER);
        
        Label rowsLabel = new Label("Rows:");
        ComboBox<Integer> rowsCombo = new ComboBox<>();
        for (int i = 1; i <= 5; i++) {
            rowsCombo.getItems().add(i);
        }
        rowsCombo.setValue(matrixRows);
        rowsCombo.setOnAction(e -> {
            matrixRows = rowsCombo.getValue();
            updateMatrixGrid();
        });
        
        Label colsLabel = new Label("Columns:");
        ComboBox<Integer> colsCombo = new ComboBox<>();
        for (int i = 1; i <= 5; i++) {
            colsCombo.getItems().add(i);
        }
        colsCombo.setValue(matrixCols);
        colsCombo.setOnAction(e -> {
            matrixCols = colsCombo.getValue();
            updateMatrixGrid();
        });
        
        controlsBox.getChildren().addAll(rowsLabel, rowsCombo, colsLabel, colsCombo);
        
        // Create the matrix grid
        matrixGrid = new GridPane();
        matrixGrid.setHgap(5);
        matrixGrid.setVgap(5);
        matrixGrid.setAlignment(Pos.CENTER);
        
        // Initialize matrix fields
        matrixFields = new ArrayList<>();
        
        // Create initial grid
        updateMatrixGrid();
        
        // First row of operation buttons (basic operations)
        HBox basicOpBox = new HBox(10);
        basicOpBox.setAlignment(Pos.CENTER);
        
        Button addButton = new Button("+");
        addButton.setPrefSize(60, 40);
        addButton.setOnAction(e -> prepareMatrixOperation("add"));
        
        Button subtractButton = new Button("-");
        subtractButton.setPrefSize(60, 40);
        subtractButton.setOnAction(e -> prepareMatrixOperation("subtract"));
        
        Button multiplyButton = new Button("×");
        multiplyButton.setPrefSize(60, 40);
        multiplyButton.setOnAction(e -> prepareMatrixOperation("multiply"));
        
        Button scalarButton = new Button("Scalar");
        scalarButton.setPrefSize(80, 40);
        scalarButton.setOnAction(e -> {
            // Prompt for scalar value
            TextInputDialog dialog = new TextInputDialog("1.0");
            dialog.setTitle("Scalar Multiplication");
            dialog.setHeaderText("Enter scalar value:");
            dialog.setContentText("Value:");
            
            dialog.showAndWait().ifPresent(value -> {
                try {
                    double scalar = Double.parseDouble(value);
                    calculateScalarMultiply(scalar);
                } catch (NumberFormatException ex) {
                    display.setText("Error: Invalid number format");
                }
            });
        });
        
        basicOpBox.getChildren().addAll(addButton, subtractButton, multiplyButton, scalarButton);
        
        // Second row of operation buttons (advanced operations)
        HBox advancedOpBox = new HBox(10);
        advancedOpBox.setAlignment(Pos.CENTER);
        
        Button identityButton = new Button("Identity");
        identityButton.setPrefSize(100, 40);
        identityButton.setOnAction(e -> calculateIdentity());
        
        Button transposeButton = new Button("Transpose");
        transposeButton.setPrefSize(110, 40);
        transposeButton.setOnAction(e -> calculateTranspose());
        
        Button cofactorButton = new Button("Cofactor");
        cofactorButton.setPrefSize(100, 40);
        cofactorButton.setOnAction(e -> calculateCofactor());
        
        advancedOpBox.getChildren().addAll(identityButton, transposeButton, cofactorButton);
        
        // Third row of operation buttons (more advanced operations)
        HBox moreAdvancedOpBox = new HBox(10);
        moreAdvancedOpBox.setAlignment(Pos.CENTER);
        
        Button determinantButton = new Button("Det");
        determinantButton.setPrefSize(60, 40);
        determinantButton.setOnAction(e -> calculateDeterminant());
        
        Button inverseButton = new Button("Inverse");
        inverseButton.setPrefSize(100, 40);
        inverseButton.setOnAction(e -> calculateInverse());
        
        Button adjointButton = new Button("Adjoint");
        adjointButton.setPrefSize(100, 40);
        adjointButton.setOnAction(e -> calculateAdjoint());
        
        moreAdvancedOpBox.getChildren().addAll(determinantButton, inverseButton, adjointButton);
        
        // Action buttons
        HBox actionBox = new HBox(20);
        actionBox.setAlignment(Pos.CENTER);
        
        Button calculateButton = new Button("Calculate");
        calculateButton.setPrefSize(100, 40);
        calculateButton.setOnAction(e -> performMatrixOperation());
        
        Button clearButton = new Button("Clear");
        clearButton.setPrefSize(100, 40);
        clearButton.setOnAction(e -> clearMatrix());
        
        actionBox.getChildren().addAll(calculateButton, clearButton);
        
        panel.getChildren().addAll(controlsBox, matrixGrid, basicOpBox, advancedOpBox, moreAdvancedOpBox, actionBox);
        return panel;
    }
    
    private void updateMatrixGrid() {
        matrixGrid.getChildren().clear();
        
        // Update matrix fields list
        matrixFields.clear();
        
        // Create the grid of text fields
        for (int i = 0; i < matrixRows; i++) {
            List<TextField> rowFields = new ArrayList<>();
            
            for (int j = 0; j < matrixCols; j++) {
                TextField field = new TextField();
                field.setPrefWidth(60);
                field.setMinWidth(60);
                field.setPrefHeight(30);
                field.setMinHeight(30);
                field.setMaxHeight(30);
                field.setPadding(new Insets(0, 5, 0, 5));
                field.setAlignment(Pos.CENTER);
                
                // Use inline CSS to ensure the cursor is visible
                field.setStyle("-fx-background-insets: 0; -fx-padding: 0 5 0 5; -fx-background-radius: 0;");
                
                matrixGrid.add(field, j, i);
                rowFields.add(field);
            }
            
            matrixFields.add(rowFields);
        }
    }
    
    private void prepareMatrixOperation(String operation) {
        try {
            // Create the first matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            currentMatrix = new Matrix(values);
            display.setText("Enter second matrix for " + operation);
            isSecondMatrix = true;
            
            // Store the operation
            currentOperation = operation;
            
            // Clear the grid for the second matrix
            clearMatrixFields();
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void performMatrixOperation() {
        if (!isSecondMatrix) {
            display.setText("Please select an operation first");
            return;
        }
        
        try {
            // Create the second matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            secondMatrix = new Matrix(values);
            
            // Perform the operation
            Matrix result = null;
            
            switch (currentOperation) {
                case "add":
                    result = currentMatrix.add(secondMatrix);
                    break;
                case "subtract":
                    result = currentMatrix.subtract(secondMatrix);
                    break;
                case "multiply":
                    result = currentMatrix.multiply(secondMatrix);
                    break;
                default:
                    display.setText("Unknown operation");
                    return;
            }
            
            // Display the result
            display.setText(result.toString());
            
            // Fill the grid with the result
            fillMatrixWithResult(result);
            
            // Reset for next operation
            isSecondMatrix = false;
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateDeterminant() {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate determinant
            double det = matrix.determinant();
            
            // Display the result
            display.setText("Determinant: " + det);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateInverse() {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate inverse
            Matrix inverse = matrix.inverse();
            
            // Display the result
            display.setText(inverse.toString());
            
            // Fill the grid with the result
            fillMatrixWithResult(inverse);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateTranspose() {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate transpose
            Matrix transpose = matrix.transpose();
            
            // Display the result
            display.setText(transpose.toString());
            
            // Update dimensions for the result
            matrixRows = transpose.rows();
            matrixCols = transpose.columns();
            
            // Update the grid
            updateMatrixGrid();
            
            // Fill the grid with the result
            fillMatrixWithResult(transpose);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateScalarMultiply(double scalar) {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate scalar multiplication
            Matrix result = matrix.multiply(scalar);
            
            // Display the result
            display.setText(result.toString());
            
            // Fill the grid with the result
            fillMatrixWithResult(result);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateIdentity() {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate identity
            Matrix identity = matrix.identity();
            
            // Display the result
            display.setText(identity.toString());
            
            // Fill the grid with the result
            fillMatrixWithResult(identity);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateCofactor() {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate cofactor
            Matrix cofactor = matrix.cofactor();
            
            // Display the result
            display.setText(cofactor.toString());
            
            // Fill the grid with the result
            fillMatrixWithResult(cofactor);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void calculateAdjoint() {
        try {
            // Create the matrix
            double[][] values = new double[matrixRows][matrixCols];
            
            for (int i = 0; i < matrixRows; i++) {
                for (int j = 0; j < matrixCols; j++) {
                    String value = matrixFields.get(i).get(j).getText().trim();
                    if (value.isEmpty()) {
                        values[i][j] = 0;
                    } else {
                        values[i][j] = Double.parseDouble(value);
                    }
                }
            }
            
            Matrix matrix = new Matrix(values);
            
            // Calculate adjoint
            Matrix adjoint = matrix.adjoint();
            
            // Display the result
            display.setText(adjoint.toString());
            
            // Fill the grid with the result
            fillMatrixWithResult(adjoint);
            
        } catch (Exception e) {
            display.setText("Error: " + e.getMessage());
        }
    }
    
    private void fillMatrixWithResult(Matrix result) {
        // Check if we need to update the grid dimensions
        if (result.rows() != matrixRows || result.columns() != matrixCols) {
            matrixRows = result.rows();
            matrixCols = result.columns();
            updateMatrixGrid();
        }
        
        // Fill the fields with the result values
        for (int i = 0; i < result.rows(); i++) {
            for (int j = 0; j < result.columns(); j++) {
                matrixFields.get(i).get(j).setText(String.valueOf(result.getValue(i, j)));
            }
        }
    }
    
    private void clearMatrix() {
        clearMatrixFields();
        display.setText("");
        isSecondMatrix = false;
    }
    
    private void clearMatrixFields() {
        for (List<TextField> row : matrixFields) {
            for (TextField field : row) {
                field.clear();
            }
        }
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