package use_case.authentication;

import data_access.FirebaseDataAccess;

public class AuthInteractor implements AuthInputBoundary {

    private final FirebaseDataAccess firebaseDataAccess;

    public AuthInteractor(FirebaseDataAccess firebaseDataAccess) {
        this.firebaseDataAccess = firebaseDataAccess;
    }

    @Override
    public AuthOutputData signUp(AuthInputData inputData) {
        boolean success = firebaseDataAccess.createUser(inputData.getEmail(), inputData.getPassword());
        String message = success ? "Sign-up successful!" : "Sign-up failed. User may already exist.";
        return new AuthOutputData(success, message);
    }

    @Override
    public AuthOutputData signIn(AuthInputData inputData) {
        boolean success = firebaseDataAccess.verifyToken(inputData.getPassword()); // Token passed as "password"
        String message = success ? "Sign-in successful!" : "Invalid token.";
        return new AuthOutputData(success, message);
    }
}
