package calculator.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Calculator API application.
 * <p>
 * This class contains the main method that launches the Spring Boot application.
 * The @SpringBootApplication annotation marks this class as the configuration
 * class for Spring Boot, enabling component scanning, auto-configuration,
 * and property support.
 * </p>
 * <p>
 * To run the application, execute this class, which will trigger the
 * SpringApplication to start the application context and launch the API.
 * </p>
 */
@SpringBootApplication
public class CalculatorAPIApplication {

    /**
     * The main method that serves as the entry point for the application.
     * <p>
     * This method launches the Spring Boot application by calling the
     * SpringApplication.run() method, which sets up the application context
     * and starts the embedded web server.
     * </p>
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(CalculatorAPIApplication.class, args);
    }
}
