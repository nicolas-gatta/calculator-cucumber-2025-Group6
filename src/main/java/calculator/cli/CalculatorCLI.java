package calculator.cli;

import java.util.Scanner;

public class CalculatorCLI {

    private static final String HELP_MESSAGE = """
    ==================== Calculator Help ====================
    
    Tools Available:
      - calculator
      - converter

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
        solve(
          (3x + 3y) = 5,
          (3x - 4z) = 7,
          (x - (y + z)) = 10
        )

    --------------------------------------------------------
    Complex Numbers
    --------------------------------------------------------

    a) Representation:
       - (50 + 20i)

    --------------------------------------------------------
    Special Commands
    --------------------------------------------------------

    - help : Show this help message
    - exit : Quit the application

    ========================================================
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



    public static void entryPointCli(){
        Scanner scanner = new Scanner(System.in);

        System.out.println(TOOL_CHOOSER_MESSAGE);

        while(true){
            String userInput = scanner.nextLine().trim();

            if(userInput.equalsIgnoreCase("exit")){
                System.out.println("Exit calculator");
                break;
            }
            else if(userInput.equalsIgnoreCase("help")) {
                System.out.println(HELP_MESSAGE);
            }
            else if(userInput.equalsIgnoreCase("calculator")) {
                System.out.println("Enter calculator");
            }
            else if(userInput.equalsIgnoreCase("converter")){
                System.out.println("Enter converter");
            }
            else{
                System.out.println("Exit anyway");
                break;
            }
        }
    }

    private static void calculatorCli(){

    }

    private static void converterCli(){

    }
}
