package calculator.numbers;
import calculator.Expression;
import visitor.Visitor;
/**
 * Class representing a rational number as a fraction of two integers.
 */
public class RationalNumber implements Expression {
    private final int numerator;
    private final int denominator;

    /**
     * Constructs a new RationalNumber.
     * @param numerator The numerator of the fraction.
     * @param denominator The denominator of the fraction (must not be zero).
     */
    public RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        
        // If denominator is negative, make it positive and negate the numerator
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        
        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    // Method to calculate the greatest common divisor
    private int gcd(int a, int b) {
        return b == 0 ? Math.abs(a) : gcd(b, a % b);
    }

    /**
     * Calculates the decimal value of this rational number.
     * @return The decimal representation of this rational number as a double.
     */
    public double getValue() {
        return (double) numerator / denominator;
    }

    /**
     * Gets the numerator of the rational number.
     * @return The numerator.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * Gets the denominator of the rational number.
     * @return The denominator.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Adds two rational numbers.
     * @param other The other rational number to add.
     * @return The result of the addition as a new RationalNumber.
     */
    public RationalNumber add(RationalNumber other) {
        int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    /**
     * Subtracts another rational number from this one.
     * @param other The other rational number to subtract.
     * @return The result of the subtraction as a new RationalNumber.
     */
    public RationalNumber subtract(RationalNumber other) {
        int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalNumber(newNumerator, newDenominator);
    }

    /**
     * Multiplies this rational number by another.
     * @param other The other rational number to multiply.
     * @return The result of the multiplication as a new RationalNumber.
     */
    public RationalNumber multiply(RationalNumber other) {
        return new RationalNumber(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    /**
     * Divides this rational number by another.
     * @param other The other rational number to divide by.
     * @return The result of the division as a new RationalNumber.
     * @throws IllegalArgumentException if the other rational number's numerator is zero.
     */
    public RationalNumber divide(RationalNumber other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        return new RationalNumber(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        RationalNumber other = (RationalNumber) obj;
        // Compare numerators and denominators after simplification
        return this.numerator == other.numerator && this.denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

     /**
     * Accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v The visitor object that will process this number
     */
    public void accept(Visitor v) {v.visit(this);}
} 