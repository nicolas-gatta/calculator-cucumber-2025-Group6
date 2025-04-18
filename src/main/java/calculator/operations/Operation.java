package calculator.operations;

import calculator.*;
import calculator.numbers.MyNumber;
import calculator.numbers.RationalNumber;
import calculator.numbers.ComplexNumber;
import calculator.matrix.Matrix;
import visitor.Visitor;
import visitor.StringVisitor;
import calculator.IllegalConstruction;

import java.util.ArrayList;
import java.util.List;


/**
 * Operation is an abstract class that represents arithmetic operations,
 * which are a special kind of Expressions, just like numbers are.
 *
 * @see Expression
 * @see MyNumber
 */
public abstract class Operation implements Expression {
	/**
	 * The list of expressions passed as arguments to this arithmetic operation.
	 * This list can be empty but cannot be null.
	 */
	protected List<Expression> args;

	/**
	 * The character used to represent the arithmetic operation (e.g. "+", "*").
	 * This symbol is used in string representations of the operation.
	 */
	protected String symbol;

	/**
	 * The neutral element of the operation (e.g. 1 for *, 0 for +).
	 * This is the value that, when combined with any other value x using
	 * this operation, returns x.
	 */
	protected int neutral;

	/**
	 * The notation used to render operations as strings.
	 * By default, the infix notation will be used.
	 * Can be changed to PREFIX or POSTFIX notation.
	 *
	 * @see Notation
	 */
	public Notation notation = Notation.INFIX;

	private static final double EPSILON = 1e-10;

	/**
	 * It is not allowed to construct an operation with a null list of expressions.
	 * Note that it is allowed to have an EMPTY list of arguments.
	 *
	 * @param elist The list of expressions passed as argument to the arithmetic operation
	 * @throws IllegalConstruction Exception thrown if a null list of expressions is passed as argument
	 */
	protected /*constructor*/ Operation(List<Expression> elist)
			throws IllegalConstruction {
		this(elist, null);
	}

	/**
	 * To construct an operation with a list of expressions as arguments,
	 * as well as the Notation used to represent the operation.
	 *
	 * @param elist The list of expressions passed as argument to the arithmetic operation
	 * @param n     The notation to be used to represent the operation
	 * @throws IllegalConstruction Exception thrown if a null list of expressions is passed as argument
	 */
	protected /*constructor*/ Operation(List<Expression> elist, Notation n) throws IllegalConstruction {
		if (elist == null) {
			throw new IllegalConstruction("Expression list cannot be null");
		}
		if (elist.isEmpty()) {
			throw new IllegalConstruction("Expression list cannot be empty");
		}
		args = new ArrayList<>(elist);
		if (n != null) notation = n;
	}

	/**
	 * getter method to return the number of arguments of an arithmetic operation.
	 *
	 * @return The number of arguments of the arithmetic operation.
	 */
	public List<Expression> getArgs() {
		return args;
	}

	/**
	 * getter method to return the notation of an arithmetic operation.
	 *
	 * @return The notation of the arithmetic operation.
	 */
	public Notation getNotation() {
		return notation;
	}


	/**
	 * Abstract method representing the actual binary arithmetic operation to compute.
	 * Each concrete operation class must implement this method to define its behavior.
	 *
	 * @param left  first argument of the binary operation
	 * @param right second argument of the binary operation
	 * @return result of computing the binary operation
	 */
	public abstract int op(final int left, final int right);

	/**
	 * Gets the symbol of this operation.
	 *
	 * @return The symbol of the arithmetic operation.
	 */
	public String getSymbol() {
		return symbol;
	}

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 *
	 * @param l first argument of the binary operation
	 * @param r second argument of the binary operation
	 * @return result of computing the binary operation
	 */
	public abstract double opReal(final double l, final double r);

	/**
	 * Add more parameters to the existing list of parameters.
	 * The new parameters are appended to the end of the existing list.
	 *
	 * @param params The list of parameters to be added
	 */
	public void addMoreParams(List<Expression> params) {
		args.addAll(params);
	}

	/**
	 * Accept method to implement the visitor design pattern to traverse arithmetic expressions.
	 * Each operation will delegate the visitor to each of its arguments expressions,
	 * and will then pass itself to the visitor object to get processed by the visitor object.
	 *
	 * @param v The visitor object
	 */
	public void accept(Visitor v) {
		for (Expression a : args) {
			a.accept(v);
		}
		v.visit(this);
	}

	/**
	 * Converts the operation to its string representation using a StringVisitor.
	 * The format of the string depends on the notation (PREFIX, INFIX, or POSTFIX)
	 * that was specified when creating the operation.
	 *
	 * @return The string representation of the operation
	 * @see visitor.StringVisitor
	 * @see Notation
	 */
	@Override
	public String toString() {
		StringVisitor sv = new StringVisitor(notation);
		this.accept(sv);
		return sv.getResult();
	}

	/**
	 * Converts the operation to its string representation using a StringVisitor.
	 * The format of the string depends on the notation (PREFIX, INFIX, or POSTFIX)
	 * that was specified when creating the operation.
	 *
	 * @param customNotation custom notation to use when show the operation.
	 * @return The string representation of the operation
	 * @see visitor.StringVisitor
	 * @see Notation
	 */
	public String toString(Notation customNotation) {
		StringVisitor sv = new StringVisitor(customNotation);
		this.accept(sv);
		return sv.getResult();
	}

	/**
	 * Two operation objects are equal if their list of arguments is equal and they correspond to the same operation.
	 *
	 * @param o The object to compare with
	 * @return The result of the equality comparison
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false; // No object should be equal to null

		if (this == o) return true; // If it's the same object, they're obviously equal

		if (getClass() != o.getClass())
			return false; // getClass() instead of instanceof() because an addition is not the same as a multiplication

		Operation other = (Operation) o;
		return this.args.equals(other.getArgs());
	}

	/**
	 * The method hashCode needs to be overridden it the equals method is overridden;
	 * otherwise there may be problems when you use your object in hashed collections
	 * such as HashMap, HashSet, LinkedHashSet.
	 *
	 * @return The result of computing the hash.
	 */
	@Override
	public int hashCode() {
		int result = 5, prime = 31;
		result = prime * result + neutral;
		result = prime * result + symbol.hashCode();
		result = prime * result + args.hashCode();
		return result;
	}

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 * for rational numbers.
	 *
	 * @param l The first RationalNumber
	 * @param r The second RationalNumber
	 * @return The result of the operation as a RationalNumber
	 */
	public abstract RationalNumber opRational(final RationalNumber l, final RationalNumber r);

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 * for complex numbers.
	 *
	 * @param l The first ComplexNumber
	 * @param r The second ComplexNumber
	 * @return The result of the operation as a ComplexNumber
	 */
	public abstract ComplexNumber opComplex(final ComplexNumber l, final ComplexNumber r);

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 * for Matrix.
	 *
	 * @param l The first Matrix
	 * @param r The second Matrix
	 * @return The result of the operation as a Matrix
	 */
	public abstract Matrix opMatrix(Matrix l, Matrix r);

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 * for Matrix.
	 *
	 * @param l The first Matrix
	 * @param r The number
	 * @return The result of the operation as a Matrix
	 */
	public abstract Matrix opMatrix(Matrix l, double r);

	/**
	 * Abstract method representing the actual binary arithmetic operation to compute
	 * for Matrix.
	 *
	 * @param l The Matrix
	 * @return The result of the operation as a Matrix
	 */
	public abstract Matrix opMatrix(Matrix l);

	/**
	 * Checks if two double values are approximately equal.
	 *
	 * @param a First value
	 * @param b Second value
	 * @return true if the values are approximately equal
	 */
	protected boolean approxEqual(double a, double b) {
		return Math.abs(a - b) < EPSILON;
	}
}
