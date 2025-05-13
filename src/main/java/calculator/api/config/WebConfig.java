package calculator.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS Configuration for the Spring Boot application.
 * <p>
 * This configuration class enables Cross-Origin Resource Sharing (CORS) for the application's API endpoints.
 * It allows frontend applications from specific origins to access resources on the backend.
 * </p>
 * <p>
 * The configuration allows requests from the specified frontend (e.g., http://localhost:3000) to interact with the API
 * at http://localhost:8080. This is essential for applications running on different domains or ports, especially during development.
 * </p>
 * <p>
 * The allowed methods and headers are configured to ensure that the necessary HTTP requests (GET, POST, PUT, DELETE)
 * and headers (Content-Type, Authorization) are permitted. The support for credentials (cookies, HTTP authentication) is also enabled.
 * </p>
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings for the application.
     * <p>
     * This method adds CORS support for the API endpoints starting with "/api".
     * Requests from the frontend at http://localhost:3000 are allowed to access these endpoints.
     * </p>
     *
     * @param registry the CorsRegistry used to define the CORS mappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // Apply CORS to all API endpoints starting with "/api"
                .allowedOrigins("http://localhost:3000")  // Allow requests from the frontend at http://localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                .allowedHeaders("Content-Type", "Authorization")  // Allow specific headers
                .allowCredentials(true);  // Allow cookies and credentials if needed
    }
}
