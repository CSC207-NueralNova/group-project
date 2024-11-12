package use_case.ask_ai;

import entity.user.User;

public interface AskAIUserDataAccessInterface {

    /**
     * Finds a user by their username.
     * @param username the username of the user to retrieve.
     * @return the User object if found, null otherwise.
     */
    User findUserByUsername(String username);

    // Additional methods can be added here if needed, e.g., updateUser, saveUser, etc.
}
