package calculator.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

/**
 * Global exception handler for the Calculator API.
 * <p>
 * This class handles exceptions thrown by any controller in the application and returns appropriate HTTP responses.
 * </p>
 *
 * It ensures:
 * <ul>
 *     <li>Clear error messages for invalid inputs</li>
 *     <li>Consistent response formats for exceptions</li>
 *     <li>Proper use of HTTP status codes</li>
 * </ul>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Default constructor for GlobalExceptionHandler.
     */
    public GlobalExceptionHandler() {
        // Default constructor
    }

    /**
     * Handles {@link IllegalArgumentException} thrown in the application.
     *
     * @param ex the exception thrown
     * @return a {@link ResponseEntity} with HTTP 400 status and the error message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Error: " + ex.getMessage());
    }

    /**
     * Handles any generic {@link Exception} that is not otherwise caught.
     *
     * @param ex the exception thrown
     * @return a {@link ResponseEntity} with HTTP 500 status and the error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Unexpected error: " + ex.getMessage());
    }

    /**
     * Handles {@link ResponseStatusException} which may be thrown manually with specific status codes.
     *
     * @param ex the exception thrown
     * @return a {@link ResponseEntity} with the status and reason provided in the exception
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ex.getReason());
    }
}
