package use_case.ask_ai;

public class AskAIInputData {
    private final String username;
    private final String message;

    public AskAIInputData(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }
}
