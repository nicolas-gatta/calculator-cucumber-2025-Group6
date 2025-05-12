package calculator;

import calculator.api.CalculatorAPIApplication;
import calculator.cli.ApplicationCLI;
import unit_converter.ConverterFactory;
import unit_converter.IUnitConverter;
import unit_converter.enums.ConverterTypeEnum;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static unit_converter.ConverterFactory.getConverter;
import calculator.linear.Equation;
import calculator.linear.EquationExpression;
import calculator.linear.LinearEquationSystemExpression;
import calculator.linear.VariableExpression;
import calculator.numbers.AngleConverter;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.RealNumber;
import calculator.numbers.ComplexNumber;
import calculator.operations.*;

import calculator.operations.Divides;
import calculator.operations.Minus;
import calculator.operations.Plus;
import calculator.operations.Times;
import calculator.gui.CalculatorApp;
import calculator.matrix.Matrix;
import calculator.matrix.MatrixExpression;
import expressionParser.StringParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Software Engineering Lab
 * Faculty of Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * Private constructor to prevent instantiation of this utility class.
	 */
	private Main() {
		// Utility class should not be instantiated
	}

	/**
	 * Creates a list of expressions from the given parameters
	 *
	 * @param expressions The expressions to add to the list
	 * @return A list containing the provided expressions
	 */
	private static List<Expression> createExpressionList(Expression... expressions) {
		List<Expression> params = new ArrayList<>();
		Collections.addAll(params, expressions);
		return params;
	}

	/**
	 * Shows examples of integer operations
	 */
	public static void demonstrateIntegerOperations() {
		Expression e;
		Calculator c = new Calculator();

		try {
			System.out.println("\n=== Integer Operations Examples ===");

			//Use this code for user input CLI when using values converters
//			Scanner userInput = new Scanner(System.in);
//			System.out.println("Enter Converter Type:");
//			String converterName = userInput.nextLine();
//			IUnitConverter<?> converter = ConverterFactory.getConverter(converterName);
//			if(converter == null) System.out.println("Converter not found");


			e = new MyNumber(8);
			c.print(e);

			// Using the helper method to create expression lists
			e = new Plus(createExpressionList(new MyNumber(3), new MyNumber(4), new MyNumber(5)), Notation.PREFIX);
			c.printExpressionDetails(e);

			e = new Minus(createExpressionList(new MyNumber(5), new MyNumber(3)), Notation.INFIX);
			c.print(e);

			// More complex nested expressions
			List<Expression> params3 = createExpressionList(
					new Plus(createExpressionList(new MyNumber(3), new MyNumber(4), new MyNumber(5))),
					new Minus(createExpressionList(new MyNumber(5), new MyNumber(3)))
			);
			e = new Times(params3);
			c.printExpressionDetails(e);

			List<Expression> params4 = createExpressionList(
					new Plus(createExpressionList(new MyNumber(3), new MyNumber(4), new MyNumber(5))),
					new Minus(createExpressionList(new MyNumber(5), new MyNumber(3))),
					new MyNumber(5)
			);

			e = new Divides(params4, Notation.POSTFIX);
			c.print(e);

			List<Expression> params5 = createExpressionList(
					new RealNumber(3.14159, 5),
					new RealNumber(2.71828, 5));
			e = new Plus(params5, Notation.PREFIX);
			c.print(e);
			c.printExpressionDetails(e);
			c.eval(e);

			List<Expression> params6 = createExpressionList(
					new ComplexNumber(5, 6),
					new ComplexNumber(1, 2)
			);
			e = new Plus(params6, Notation.POSTFIX);
			c.printExpressionDetails(e);
			c.eval(e);

		} catch(IllegalConstruction exception) {
			System.err.println("Error: " + exception.getMessage());
		}
	}

	/**
	 * Shows examples of real number operations
	 */
	public static void demonstrateRealOperations() {
		Expression e;
		Calculator c = new Calculator();

		try {
			System.out.println("\n=== Real Number Operations Examples ===");

			// Basic real number operations
			List<Expression> params = createExpressionList(
					new RealNumber(3.14159, 5),
					new RealNumber(2.71828, 5)
			);
			e = new Plus(params);
			System.out.println("Adding π and e:");
			c.print(e);

			// Scientific notation
			System.out.println("Scientific notation example:");
			RealNumber avogadro = new RealNumber(6.022e23, 4);
			c.print(avogadro);

			// Angle conversion
			System.out.println("Angle conversion example:");
			double angleInDegrees = 180.0;
			double angleInRadians = AngleConverter.degreesToRadians(angleInDegrees);
			System.out.println(angleInDegrees + " degrees = " + angleInRadians + " radians");

			// Complex calculation with mixed types
			System.out.println("Mixed type calculation (π * e * 2):");
			List<Expression> params2 = createExpressionList(
					new RealNumber(Math.PI, 6),
					new RealNumber(Math.E, 6),
					new MyNumber(2)
			);
			e = new Times(params2);
			c.print(e);

			// Division with real numbers
			System.out.println("Division of real numbers (1/3):");
			List<Expression> params3 = createExpressionList(
					new RealNumber(1.0, 4),
					new RealNumber(3.0, 4)
			);
			e = new Divides(params3);
			c.print(e);

			// Precision examples
			System.out.println("Different precision examples for π:");
			RealNumber pi = new RealNumber(Math.PI, 3);
			System.out.println("Pi with 3 digits: " + pi);
			pi.setPrecision(10);
			System.out.println("Pi with 10 digits: " + pi);

		} catch(IllegalConstruction exception) {
			System.out.println("Cannot create operations without parameters");
		}
	}

	/**
	 * Shows examples of rational number operations
	 */
	public static void demonstrateRationalOperations() {
		System.out.println("\n=== Rational Number Operations Examples ===");

		// Create some rational numbers
		RationalNumber r1 = new RationalNumber(1, 2); // 1/2
		RationalNumber r2 = new RationalNumber(3, 4); // 3/4
		RationalNumber r3 = new RationalNumber(6, 12); // 6/12
		RationalNumber r4 = new RationalNumber(0, 1); // 0/1

		// Addition
		RationalNumber sum = r1.add(r2);
		System.out.println(r1 + " + " + r2 + " = " + sum); // Should print 5/4

		// Subtraction
		RationalNumber difference = r1.subtract(r2);
		System.out.println(r1 + " - " + r2 + " = " + difference); // Should print -1/4

		// Multiplication
		RationalNumber product = r1.multiply(r2);
		System.out.println(r1 + " * " + r2 + " = " + product); // Should print 3/8

		// Division
		RationalNumber quotient = r1.divide(r2);
		System.out.println(r1 + " / " + r2 + " = " + quotient); // Should print 2/3

		// Simplification
		System.out.println("Simplifying " + r3 + " gives " + r3); // Should print 1/2

		// Division by zero
		try {
			RationalNumber result = r1.divide(r4); // This should throw an exception
		} catch (IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage()); // Should print "Cannot divide by zero."
		}
	}

	/**
	 * Shows examples of complex number operations
	 */
	public static void demonstrateComplexOperations() {
		System.out.println("\n=== Complex Number Operations Examples ===");

		ComplexNumber c1 = new ComplexNumber(3, 4); // 3 + 4i
		ComplexNumber c2 = new ComplexNumber(1, 2); // 1 + 2i

		// Addition
		ComplexNumber sum = c1.add(c2);
		System.out.println("Sum: " + sum); // Should print "Sum: 4.0 + 6.0i"

		// Soustraction
		ComplexNumber difference = c1.subtract(c2);
		System.out.println("Subtraction: " + difference); // Should print "Subtraction: 2.0 + 2.0i"

		// Multiplication
		ComplexNumber product = c1.multiply(c2);
		System.out.println("Product: " + product); // Should print "Product: -5.0 + 10.0i"

		// Division
		ComplexNumber quotient = c1.divide(c2);
		System.out.println("Quotient: " + quotient); // Should print "Quotient: 2.2 + 0.4i"
	}

	public static void demonstrateMatrixOperations() {

		System.out.println("\n=== Matrix Operations Examples ===");

		Calculator c = new Calculator();
		Expression e;

		try {

			MyNumber number = new MyNumber(2);
			RealNumber realNumber = new RealNumber(5);
			RationalNumber rationalNumber = new RationalNumber(6, 2);

			Matrix A = new Matrix("[[1,2],[3,4]]");
			Matrix B = new Matrix("[[5,6],[7,8]]");
			Matrix C = new Matrix("[[9,10,11],[12,13,14],[15,16,17]]");
			Matrix D = new Matrix("[[-50,80,100],[70,10,5],[12,25,80]]");
			Matrix E = new Matrix("[[1,2,3],[4,5,6]]");

			MatrixExpression e1 = new MatrixExpression(A);
			MatrixExpression e2 = new MatrixExpression(B);
			MatrixExpression e3 = new MatrixExpression(C);
			MatrixExpression e4 = new MatrixExpression(D);
			MatrixExpression e5 = new MatrixExpression(E);

			e = new Plus(List.of(e1, e2), Notation.POSTFIX);
			c.print(e);

			e = new Minus(List.of(e1, e2), Notation.PREFIX);
			c.print(e);

			e = new Times(List.of(e1, e2), Notation.POSTFIX);
			c.print(e);

			e = new Times(List.of(e3, e4));
			c.print(e);

			e = new Times(List.of(e5, e3));
			c.print(e);

			e = new Times(List.of(e1, number));
			c.print(e);

			e = new Times(List.of(e1, realNumber));
			c.print(e);

			e = new Times(List.of(e1, rationalNumber));
			c.print(e);

			e = new Times(List.of(number, e1));
			c.print(e);

			e = new Times(List.of(realNumber, e1));
			c.print(e);

			e = new Times(List.of(rationalNumber, e1));
			c.print(e);

			e = new MatrixTranspose(List.of(e1));
			c.print(e);

			e = new MatrixIdentity(List.of(e1));
			c.print(e);

			e = new MatrixInverted(List.of(e1));
			c.print(e);

			e = new MatrixTranspose(List.of(e3));
			c.print(e);

			e = new MatrixIdentity(List.of(e3));
			c.print(e);

		}catch (IllegalConstruction exception){
			System.out.println(exception);
		}

	}

	public static void demonstrateLinearEquation() {

		System.out.println("\n=== Linear Equation Examples ===");

		Calculator c = new Calculator();
		Expression e;

		try {

			Expression left1 = new Plus(List.of(new VariableExpression(new MyNumber(3),"x"),
					new VariableExpression(new MyNumber(3),"y")));

			Expression right1 = new MyNumber(5);

			EquationExpression equation1 = new EquationExpression(new Equation(left1, right1));

			Expression left2 = new Minus(List.of(new VariableExpression(new MyNumber(3),"x"),
					new VariableExpression(new MyNumber(4),"z")));

			Expression right2 = new MyNumber(7);

			EquationExpression equation2 = new EquationExpression(new Equation(left2, right2));

			Expression left3 = new Minus(List.of(new VariableExpression("x"), new Plus(List.of(new VariableExpression("y"),
					new VariableExpression("z")))));

			Expression right3 = new MyNumber(10);

			EquationExpression equation3 = new EquationExpression(new Equation(left3, right3));

			LinearEquationSystemExpression linearEquationSystem = new LinearEquationSystemExpression(List.of(equation1, equation2, equation3));

			System.out.println(linearEquationSystem);

			e = linearEquationSystem;

			c.print(e);

		}catch (IllegalConstruction exception){
			System.out.println(exception);
		}

	}

	public static void demonstrateExpressionParser() {

		System.out.println("\n=== Expression Parser Examples ===");

		Calculator c = new Calculator();

		Expression e;

		e = StringParser.parse("(1+2+5)/5.5");
		c.print(e);

		e = StringParser.parse("/(+(1,2,5),5.5)");
		c.print(e);

		e = StringParser.parse("((1,2,5,3)+,5.5)/");
		c.print(e);

		e = StringParser.parse("[[1,2,3],[4,5,6]] + [[1,2,3],[4,5,6]]");
		c.print(e);

		e = StringParser.parse("[[1,2,3],[4,5,6]] * 5");
		c.print(e);

		e = StringParser.parse("([[1,2,3],[4,5,6]])^T");
		c.print(e);

		e = StringParser.parse("T^([[1,2,3],[4,5,6]])");
		c.print(e);

		e = StringParser.parse("([[1,2],[4,5]])^I");
		c.print(e);

		e = StringParser.parse("I^([[1,2],[4,5]])");
		c.print(e);

		e = StringParser.parse("([[1,2,3],[4,5,6],[7,8,50]])^-1");
		c.print(e);

		e = StringParser.parse("-1^([[1,2,3],[4,5,6],[7,8,50]])");
		c.print(e);

		e = StringParser.parse("solve(( 3x + 3y ) = 5, ( 3x - 4z ) = 7, ( x - ( y + z ) ) = 10))");
		c.print(e);

		e = StringParser.parse("1/2 + 5");
		c.print(e);

		e = StringParser.parse("(50+20i)");
		c.print(e);

		e = StringParser.parse("1");
		c.print(e);

		e = StringParser.parse("1/2");
		c.print(e);

		e = StringParser.parse("50.0");
		c.print(e);
	}

	/**
	 * Launches the GUI application
	 * @param args Command line arguments (not used)
	 */
	private static void launchGUI(String[] args) {
		CalculatorApp.main(args);
	}

	/**
	 * Launches the API application
	 * @param args Command line arguments (not used)
	 */
	private static void launchAPI(String[] args) {
		CalculatorAPIApplication.main(args);
	}

	/**
	 * Launches the Web application
	 * @param args Command line arguments (not used)
	 */
	private static void launchWeb(String[] args){
		CalculatorAPIApplication.main(args);

		File buildFolder = new File("web_gui/build");

		if (buildFolder.exists() && buildFolder.isDirectory()) {
			try {
				File htmlFile = new File("web_gui/build/index.html");
				ProcessBuilder pb;
				if (System.getProperty("os.name").toLowerCase().contains("win")) {
					pb = new ProcessBuilder("cmd.exe", "/c", "start", htmlFile.getAbsolutePath());
				} else {
					pb = new ProcessBuilder("xdg-open", htmlFile.getAbsolutePath());
				}
				pb.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			System.out.println("If you don't have react-scripts install: npm install react-scripts");
			System.out.println("After installing, you can open a terminal in the main directory of the project and run: \n \t- cd ./web_gui\n \t- npm start");
		}
	}

	/**
	 * Launches the CLI application
	 * @param args Command line arguments (not used)
	 */
	private static void launchCLI(String[] args) {
		ApplicationCLI.entryPointCli();
	}

	/**
	 * Main entry point of the calculator application.
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean running = true;
		int choice = 5;

		while (running) {
			System.out.println("===== Application Launcher =====");
			System.out.println("1. Start GUI Application");
			System.out.println("2. Start API Server");
			System.out.println("3. Start Web Application");
			System.out.println("4. Start CLI Application");
			System.out.println("5. Exit");
			System.out.print("Select an option (1-5): ");

			if (!scanner.hasNextInt()) {
				System.out.println("Invalid input. Please enter a number between 1 and 5.");
				scanner.next(); // Clear the invalid input
			}else{
				choice = scanner.nextInt();
				scanner.nextLine(); // Clear the buffer

				if (choice < 1 || choice > 5) {
					System.out.println("Invalid option. Please enter a number between 1 and 5.");
				}
				else{
					running = false;
				}
			}

			switch (choice) {
				case 1:
					System.out.println("Launching GUI Application...");
					launchGUI(args);
					break;
				case 2:
					System.out.println("Launching API Server...");
					launchAPI(args);
					break;
				case 3:
					System.out.println("Launching Web Application...");
					launchWeb(args);
					break;
				case 4:
					System.out.println("Launching CLI Application...");
					launchCLI(args);
					break;
				case 5:
					System.out.println("Exiting application...");
					break;
				default:
					System.out.println("Invalid option. Please enter a number between 1 and 5.");
			}
		}

		scanner.close();
	}
}