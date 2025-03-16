package calculator.numbers;

import calculator.Expression;
import visitor.Visitor;
/**
 * Class representing a complex number in the form a + bi.
 */
public class ComplexNumber implements Expression {
    private final double real;
    private final double imaginary;


    /**
     * Constructs a new ComplexNumber.
     * @param real The real part of the complex number.
     * @param imaginary The imaginary part of the complex number.
     */
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Returns the real part of the complex number.
     * @return The real part of the complex number.
     */
    public double getReal() {
        return real;
    }

    /**
     * Returns the imaginary part of the complex number.
     * @return The imaginary part of the complex number.
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * Adds another complex number to this one.
     * @param other The other complex number to add.
     * @return The result of the addition as a new ComplexNumber.
     */
    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    /**
     * Subtracts another complex number from this one.
     * @param other The other complex number to subtract.
     * @return The result of the subtraction as a new ComplexNumber.
     */
    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    /**
     * Multiplies this complex number by another.
     * @param other The other complex number to multiply.
     * @return The result of the multiplication as a new ComplexNumber.
     */
    public ComplexNumber multiply(ComplexNumber other) {
        double realPart = this.real * other.real - this.imaginary * other.imaginary;
        double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    /**
     * Divides this complex number by another.
     * @param other The other complex number to divide by.
     * @return The result of the division as a new ComplexNumber.
     * @throws IllegalArgumentException if the other complex number is zero.
     */
    public ComplexNumber divide(ComplexNumber other) {
        if (other.real == 0 && other.imaginary == 0) {
            throw new IllegalArgumentException("Cannot divide by zero.");
        }
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double realPart = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double imaginaryPart = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    @Override
    public String toString() {
        if (imaginary >= 0) {
            return real + " + " + imaginary + "i";
        } else {
            return real + " - " + (-imaginary) + "i";
        }
    }

     /**
     * Accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v The visitor object that will process this number
     */
    public void accept(Visitor v) {v.visit(this);}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ComplexNumber other = (ComplexNumber) obj;
        // Use a small epsilon for floating point comparison
        double epsilon = 1e-10;
        return Math.abs(this.real - other.real) < epsilon && 
               Math.abs(this.imaginary - other.imaginary) < epsilon;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(real) * 31 + Double.hashCode(imaginary);
    }
}
