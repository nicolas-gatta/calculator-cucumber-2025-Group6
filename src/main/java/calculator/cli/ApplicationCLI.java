package calculator.cli;

import calculator.Calculator;
import calculator.Expression;
import expressionParser.StringParser;
import unit_converter.ConverterFactory;
import unit_converter.IUnitConverter;

import java.util.Scanner;

/**
 * Command Line Interface for the calculator and converter application.
 * Provides an interactive interface for users to access calculator and unit converter functionalities.
 */
public class ApplicationCLI {

    /**
     * Default constructor for ApplicationCLI.
     * This class only contains static methods and is not intended to be instantiated.
     */
    public ApplicationCLI() {
        throw new IllegalStateException("Illegal stat EnumDisplayUtil");
    }

    public static final String MAIN_HELP_MESSAGE = """
    =================== Application Help ===================
    
    Tools Available:
      - calculator
      - converter

    --------------------------------------------------------
    Special Commands
    --------------------------------------------------------

    - help : Show this help message
    - exit : Quit the application

    ========================================================
    """;

    public static final String CALCULATOR_HELP_MESSAGE = """
    --------------------------------------------------------
    Supported Expression Types (Calculator)
    --------------------------------------------------------

    1. Infix Notation (standard):
       Examples:
         - (1 + 2 + 5) / 5.5

    2. Prefix Notation:
       Examples:
         - /(+(1,2,5), 5.5)

    3. Postfix Notation:
       Examples:
         - ((1,2,5,3)+,5.5)/

    --------------------------------------------------------
    Matrix Operations
    --------------------------------------------------------

    a) Basic Operations:
       - [[1,2,3],[4,5,6]] + [[1,2,3],[4,5,6]]
       - [[1,2,3],[4,5,6]] * 5

    b) Matrix Transposition:
       - ([[1,2,3],[4,5,6]])^T
       - T^([[1,2,3],[4,5,6]])

    c) Identity Matrix:
       - ([[1,2],[4,5]])^I
       - I^([[1,2],[4,5]])

    d) Matrix Inversion:
       - ([[1,2,3],[4,5,6],[7,8,50]])^-1
       - -1^([[1,2,3],[4,5,6],[7,8,50]])

    --------------------------------------------------------
    Linear Equation Solving
    --------------------------------------------------------

    Solve a system of equations:
      Example:
        solve((3x + 3y) = 5, (3x - 4z) = 7, (x - (y + z)) = 10)

    --------------------------------------------------------
    Complex Numbers
    --------------------------------------------------------

    a) Representation:
       - (50 + 20i)


    """;

    public static final String CONVERTER_HELP_MESSAGE = """
    ====================== Converter Help ======================
    
    This is an interactive unit converter.
    
    To use it:
       1. Enter a conversion type (e.g., LENGTH, VOLUME, TEMPERATURE, etc.).
       2. Choose a source unit (e.g., METER).
       3. Choose a target unit (e.g., KILOMETER).
       4. Enter the value to convert (e.g., 42.5).
    
    Available commands:
       - help    Display this help message.
       - exit    Exit the converter.
    
    Notes:
       - Input is case-insensitive (e.g., "meter" or "METER" both work).
       - Use only supported units listed for each converter.
       - For currency, use standard codes (e.g., EUR, USD).
       - For non-numeric converters, just input plain text.
    
    Example:
       Type: LENGTH
       From: meter
       To: kilometer
       Value: 1000
    
       Output: 1000.0000 meter = 1.0000 kilometer
    
    ============================================================
    """;

    public static final String TOOL_CHOOSER_MESSAGE = """
    ========================================================
    
    Cucumber Calculator ready. Type 'help' for instructions.
                
    Choose your tool:
        - calculator : The calculator evaluates arithmetic, matrix, and complex expressions. It supports infix, prefix, and postfix notation, as well as solving linear equations.
        - converter : The converter allows transforming values between different units or formats. Examples include unit conversions (e.g., meters to kilometers) or number base changes.
                    
        - exit : Quit the application
        
    ========================================================
    """;

    public static final String CALCULATOR_MESSAGE = """
    ====================== Calculator ======================
    
    Interactive calculator.
    
    Type 'help' for instructions.
    
    ========================================================  
    """;

    public static final String CONVERTER_MESSAGE = """
    ====================== Converter =======================
    
    Interactive unit converter.
    
    Type 'help' for instructions.
     
    ========================================================
    """;

    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Main entry point for the CLI application.
     * Displays a menu for tool selection and handles user input.
     */
    public static void entryPointCli() {

        while (true) {

            System.out.println(TOOL_CHOOSER_MESSAGE);
            String toolInput = prompt("Choose your tool");

            if (toolInput.equalsIgnoreCase("exit")) {
                System.out.println("Exit Application");
                break;
            } else if (toolInput.equalsIgnoreCase("help")) {
                System.out.println(MAIN_HELP_MESSAGE);
            } else if (toolInput.equalsIgnoreCase("calculator")) {
                calculatorCli();
            } else if (toolInput.equalsIgnoreCase("converter")) {
                converterCli();
            } else {
                System.out.println("Unknown command. Type 'help' or 'exit'.");
            }
        }
        scanner.close();
    }

    private static void calculatorCli(){

        Calculator c = new Calculator();

        while (true){

            System.out.println(CALCULATOR_MESSAGE);
            String exprInput = prompt("Enter your expression");
            if (handleCommand(exprInput, CALCULATOR_HELP_MESSAGE)) break;

            try{
                Expression expr = StringParser.parse(exprInput);
                c.print(expr);
            }catch (Exception e){
                System.out.println("Error: Invalid expression. " + e.getMessage());
            }
        }
    }

    private static void converterCli(){

        IUnitConverter<?> converter;

        while (true){

            System.out.println(CONVERTER_MESSAGE);
            System.out.println("Available converters: " + String.join(", ", ConverterFactory.getConverterListNames()));

            String userInput = prompt("Enter conversion type");
            if (handleCommand(userInput, CONVERTER_HELP_MESSAGE)) break;

            converter = ConverterFactory.getConverter(userInput);
            if(converter == null){
                System.out.println("Unknown conversion type");
                continue;
            }

            System.out.println("Available converter units: " + String.join(", ", converter.getConverterUnitListNames()));

            String fromInput = prompt("Enter source unit");
            if (handleCommand(fromInput, CONVERTER_HELP_MESSAGE)) break;

            String toInput = prompt("Enter target unit");
            if (handleCommand(toInput, CONVERTER_HELP_MESSAGE)) break;

            String valueInput = prompt("Enter value");
            if (handleCommand(valueInput, CONVERTER_HELP_MESSAGE)) break;

            try{
                convertAndDisplay(converter, fromInput, toInput, valueInput);
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }


    private static String prompt(String message) {
        System.out.println(message + " ");
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    /**
     * Handles common commands across different tools.
     *
     * @param input The user input to process
     * @param helpMessage The help message to display if the input is "help"
     * @return true if the command indicates to exit the current tool, false otherwise
     */
    public static boolean handleCommand(String input, String helpMessage){
        if (input.equalsIgnoreCase("exit")) {
            return true;
        } else if (input.equalsIgnoreCase("help")) {
            System.out.println(helpMessage);
            return false;
        } else if (input.isEmpty()) {
            return false;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static void convertAndDisplay(IUnitConverter<?> converter, String fromInput, String toInput, String valueInput) {
        Class<?> valueType = converter.getValueType();

        if(valueType.equals(Double.class)){
            double value = Double.parseDouble(valueInput);
            double result = ((IUnitConverter<Double>) converter).convert(fromInput, toInput, value);
            System.out.printf("Result: %.4f %s = %.4f %s%n", value, fromInput, result, toInput);
        } else if (valueType.equals(String.class)) {
            String result = ((IUnitConverter<String>) converter).convert(fromInput, toInput, valueInput);
            System.out.printf("Result: %s %s = %s %s%n", valueInput, fromInput, result, toInput);
        } else {
            System.out.println("Unsupported value type: " + valueType.getSimpleName());
        }
    }
}
