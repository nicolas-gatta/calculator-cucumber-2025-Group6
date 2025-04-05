package calculator;

import matrix.Matrix;
import matrix.MatrixExpression;
import matrix.MatrixOperation;

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
	 * This is the main method of the application.
	 * It provides examples of how to use it to construct and evaluate arithmetic expressions.
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	public static void main(String[] args) {

		Expression e;
		Calculator c = new Calculator();

		try{

			e = new MyNumber(8);
			c.print(e);
			c.eval(e);

			List<Expression> params = new ArrayList<>();
			Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
			e = new Plus(params,Notation.PREFIX);
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
			e = new Divides(params4,Notation.POSTFIX);
			c.print(e);
			c.eval(e);

			Matrix A = new Matrix("[[1,2],[3,4]]");
			Matrix B = new Matrix("[[5,6],[7,8]]");

			MatrixExpression e1 = new MatrixExpression(A);
			MatrixExpression e2 = new MatrixExpression(B);

			System.out.println("A = " + e1);
			System.out.println("B = " + e2);

			MatrixOperation add = new MatrixOperation(List.of(e1, e2), "+");
			System.out.println("A + B = " + add.compute());

			MatrixOperation substract = new MatrixOperation(List.of(e1, e2), "-");
			System.out.println("A - B = " + substract.compute());

			MatrixOperation multiply = new MatrixOperation(List.of(e1, e2), "*");
			System.out.println("A * B = " + multiply.compute());



		}

		catch(IllegalConstruction exception) {
			System.out.println("cannot create operations without parameters");
		}
	}

}