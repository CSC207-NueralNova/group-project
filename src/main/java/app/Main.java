package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.JFrame;

// Annotate the class to make it the Spring Boot application entry point
@SpringBootApplication(scanBasePackages = {"app", "web", "use_case", "interface_adapter"})
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     * @param args unused arguments
     */
    public static void main(String[] args) {
        // Start the Spring Boot application in a separate thread
        Thread springThread = new Thread(() -> SpringApplication.run(Main.class, args));
        springThread.start();

        // Initialize and display the JFrame application as before
        final AppBuilder appBuilder = new AppBuilder();
        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addLogoutUseCase()
                .addChangePasswordUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}
