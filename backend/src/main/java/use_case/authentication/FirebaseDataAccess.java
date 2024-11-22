package data_access;

public interface FirebaseDataAccess {
    boolean createUser(String email, String password);
    boolean authenticateUser(String email, String password);
}
