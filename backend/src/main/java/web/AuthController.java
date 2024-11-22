import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import use_case.authentication.SignInInputData;
import java.util.Map;

@RestController
@RequestMapping("/firebase")
public class AuthController {

    private final AuthInputBoundary authInteractor;

    public AuthController(AuthInputBoundary authInteractor) {
        this.authInteractor = authInteractor;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Map<String, String> request) {
        AuthInputData inputData = new AuthInputData(request.get("email"), request.get("password"));
        AuthOutputData outputData = authInteractor.signUp(inputData);

        return outputData.isSuccess()
                ? ResponseEntity.ok(outputData.getMessage())
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(outputData.getMessage());
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody Map<String, String> request) {
        AuthInputData inputData = new AuthInputData("", request.get("idToken")); // Pass token as "password"
        AuthOutputData outputData = authInteractor.signIn(inputData);

        return outputData.isSuccess()
                ? ResponseEntity.ok(outputData.getMessage())
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(outputData.getMessage());
    }
}

