package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.JFrame;

@SpringBootApplication(scanBasePackages = {"app", "web", "use_case", "interface_adapter", "data_access", "entity"})
public class Main {
    public static void main(String[] args) {
        // Check if we are in a Heroku environment or local
        String herokuEnv = System.getenv("HEROKU_ENV");

        if ("true".equalsIgnoreCase(herokuEnv)) {
            String port = System.getenv("PORT");
            System.setProperty("server.port", port != null ? port : "8080");
            SpringApplication.run(Main.class, args);
        } else {
            // Run both Spring Boot and the Swing GUI locally
            Thread springThread = new Thread(() -> SpringApplication.run(Main.class, args));
            springThread.start();

            // Initialize and display the JFrame application
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
}

