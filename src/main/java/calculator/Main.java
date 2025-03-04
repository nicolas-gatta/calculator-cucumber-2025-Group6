package calculator;

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
	 * Default constructor for Main class.
	 */
	public Main() {
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
			c.eval(e);

			List<Expression> params = new ArrayList<>();
			Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
			e = new Plus(params, Notation.PREFIX);
			c.printExpressionDetails(e);
			c.eval(e);

			List<Expression> params2 = new ArrayList<>();
			Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
			e = new Minus(params2, Notation.INFIX);
			c.print(e);
			c.eval(e);

			List<Expression> params3 = new ArrayList<>();
			Collections.addAll(params3, new Plus(params), new Minus(params2));
			e = new Times(params3);
			c.printExpressionDetails(e);
			c.eval(e);

			List<Expression> params4 = new ArrayList<>();
			Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(5));
			e = new Divides(params4, Notation.POSTFIX);
			c.print(e);
			c.eval(e);
		} catch(IllegalConstruction exception) {
			System.out.println("Cannot create operations without parameters");
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
			List<Expression> params = new ArrayList<>();
			Collections.addAll(params, new RealNumber(3.14159, 5), new RealNumber(2.71828, 5));
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
			List<Expression> params2 = new ArrayList<>();
			Collections.addAll(params2, 
				new RealNumber(Math.PI, 6),
				new RealNumber(Math.E, 6),
				new MyNumber(2)
			);
			e = new Times(params2);
			c.print(e);

			// Division with real numbers
			System.out.println("Division of real numbers (1/3):");
			List<Expression> params3 = new ArrayList<>();
			Collections.addAll(params3, new RealNumber(1.0, 4), new RealNumber(3.0, 4));
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
	 * Main entry point of the calculator application.
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		//demonstrateIntegerOperations();
		demonstrateRealOperations();
	}

}