package use_case.ask_ai;

public class AskAIInputData {
    private final String username;
    private final String password;

    public AskAIInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
