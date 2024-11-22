package data_access;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class FirebaseUserAccessObject implements FirebaseDataAccess {

    private final FirebaseAuth firebaseAuth;

    public FirebaseUserAccessObject() {
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public boolean createUser(String email, String password) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(email)
                    .setPassword(password);
            firebaseAuth.createUser(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean verifyToken(String idToken) {
        try {
            FirebaseToken decodedToken = firebaseAuth.verifyIdToken(idToken);
            return decodedToken != null; // Valid token
        } catch (Exception e) {
            return false;
        }
    }
}