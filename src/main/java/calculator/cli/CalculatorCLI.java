package calculator.cli;

import calculator.Calculator;
import calculator.Expression;
import expressionParser.StringParser;
import unit_converter.IUnitConverter;

import java.util.Scanner;

public class CalculatorCLI {

    private static final String MAIN_HELP_MESSAGE = """
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

    private static final String CALCULATOR_HELP_MESSAGE = """
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

    private static final String CONVERTER_HELP_MESSAGE = """
    CONVERT HELP MESSAGE
    """;

    private static final String TOOL_CHOOSER_MESSAGE = """
    ========================================================
    
    Cucumber Calculator ready. Type 'help' for instructions.
                
    Choose your tool:
        - calculator : The calculator evaluates arithmetic, matrix, and complex expressions. It supports infix, prefix, and postfix notation, as well as solving linear equations.
        - unit converter : The converter allows transforming values between different units or formats. Examples include unit conversions (e.g., meters to kilometers) or number base changes.
                    
        - exit : Quit the application
        
    ========================================================
    """;

    private static final String CALCULATOR_MESSAGE = """
    ====================== Calculator ======================
    
    Type 'help' for instructions.
    
    ========================================================  
    """;

    private static final String CONVERTER_MESSAGE = """
    ====================== Converter =======================
    
    Type 'help' for instructions.
    
    Choose your converter:  LENGTH, VOLUME, TEMPERATURE, AREA, DENSITY, CURRENCY,
                            PRESSURE, SPEED, ENERGY, TIME, FORCE
    
    ========================================================
    """;

    private static final Scanner scanner = new Scanner(System.in);

    public static void entryPointCli() {

        System.out.println(TOOL_CHOOSER_MESSAGE);

        while (true) {
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Exit Application");
                break;
            } else if (userInput.equalsIgnoreCase("help")) {
                System.out.println(MAIN_HELP_MESSAGE);
            } else if (userInput.equalsIgnoreCase("calculator")) {
                calculatorCli();
            } else if (userInput.equalsIgnoreCase("converter")) {
                converterCli();
            } else {
                System.out.println("Unknown command. Type 'help' or 'exit'.");
            }
        }
    }

    private static void calculatorCli(){

        System.out.println(CALCULATOR_MESSAGE);

        Calculator c = new Calculator();

        while (true){
            System.out.println("Enter your expression:");
            String userInput = scanner.nextLine().trim();

            if(userInput.equalsIgnoreCase("exit")){
                System.out.println("Exit calculator");
                break;
            } else if (userInput.equalsIgnoreCase("help")){
                System.out.println(CALCULATOR_HELP_MESSAGE);
            }else if (userInput.isEmpty()) {
                continue;
            }
            else{
                try{
                    Expression expr = StringParser.parse(userInput);
                    c.print(expr);
                }catch (Exception e){
                    System.out.println("Error: Invalid expression. " + e.getMessage());
                }
            }
        }
    }

    private static void converterCli(){

        System.out.println(CONVERTER_MESSAGE);

        IUnitConverter<?> converter;

        while (true){
            System.out.println("Enter conversion type: ");
            String userInput = scanner.nextLine().trim();

            if(userInput.equalsIgnoreCase("exit")){
                System.out.println("Exit converter");
                break;
            } else if (userInput.equalsIgnoreCase("help")){
                System.out.println(CONVERTER_HELP_MESSAGE);
            }else if (userInput.isEmpty()) {
                continue;
            }
            else{
                try{
                    continue;
                }catch(Exception e){
                    System.out.println(e.getMessage()); 
                }
            }
        }
    }
}
