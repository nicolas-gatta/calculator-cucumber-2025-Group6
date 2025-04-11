package calculator;

/**
 * Enumeration of the three ways to represent an arithmetic expression as a String.
 * This enum provides constants for PREFIX, INFIX, and POSTFIX notation styles.
 */
public enum Notation { 
    /**
     * Prefix notation, where the operator comes before the operands.
     * Examples: "+(1,2)" or "+ 1 2"
     */
    PREFIX, 
    
    /**
     * Infix notation, where the operator is placed between operands.
     * Example: "1+2"
     * This is the most common notation in everyday mathematics.
     */
    INFIX,
    
    /**
     * Postfix notation, where the operator comes after the operands.
     * Examples: "(1,2)+" or "1 2 +"
     * Also known as Reverse Polish Notation (RPN).
     */
    POSTFIX;
    
    /**
     * Returns a user-friendly description of the notation.
     * 
     * @return A string describing the notation format
     */
    public String getDescription() {
        return switch(this) {
            case PREFIX -> "Prefix Notation (operator before operands)";
            case INFIX -> "Infix Notation (operator between operands)";
            case POSTFIX -> "Postfix Notation (operator after operands)";
        };
    }
}
