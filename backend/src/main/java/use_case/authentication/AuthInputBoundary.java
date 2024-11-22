package use_case.authentication;

public interface AuthInputBoundary {
    AuthOutputData signUp(AuthInputData inputData);
    AuthOutputData signIn(AuthInputData inputData);
}
