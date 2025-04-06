package calculator;

/**
 * Exception that will be used when an incorrectly constructed arithmetic expression is encountered.
 * This exception provides several constructors to handle different error scenarios.
 */
public class IllegalConstruction extends Exception {
    
    /**
     * Constructs a new IllegalConstruction exception with null as its detail message.
     * The cause is not initialized.
     */
    public IllegalConstruction() {
        super();
    }
    
    /**
     * Constructs a new IllegalConstruction exception with the specified detail message.
     * The cause is not initialized.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public IllegalConstruction(String message) {
        super(message);
    }
    
    /**
     * Constructs a new IllegalConstruction exception with the specified cause.
     * The detail message is set to (cause==null ? null : cause.toString()).
     *
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public IllegalConstruction(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructs a new IllegalConstruction exception with the specified detail message and cause.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     * @param cause the cause (which is saved for later retrieval by the getCause() method)
     */
    public IllegalConstruction(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * Returns a string representation of this exception including the error message
     * and the stack trace.
     *
     * @return a string representation of the exception
     */
    @Override
    public String toString() {
        return "IllegalConstruction: " + getMessage();
    }
}
