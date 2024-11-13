package web; // or config if you create a new package for it

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Adjust the path as needed for your API endpoints
                        .allowedOrigins("http://localhost:5173") // Allow SvelteKit dev server
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // Allowed methods
            }
        };
    }
}
