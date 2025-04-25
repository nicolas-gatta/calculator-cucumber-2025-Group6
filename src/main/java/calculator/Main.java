package calculator;

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

	/**
	 * Launches the GUI application
	 * @param args Command line arguments (not used)
	 */
	public static void launchGUI(String[] args) {
		CalculatorApp.main(args);
	}

	/**
	 * Main entry point of the calculator application.
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--gui")) {
			launchGUI(args);
		} else {
			// Votre code existant pour la version console
			demonstrateIntegerOperations();
			demonstrateRealOperations();
			demonstrateRationalOperations();
			demonstrateComplexOperations();
			demonstrateMatrixOperations();
			demonstrateLinearEquation();
		}
	}

}