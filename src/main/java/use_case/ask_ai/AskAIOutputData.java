package use_case.ask_ai;


public class AskAIOutputData {
    private final String username;
    private final String advice;

    public AskAIOutputData(String username, String advice) {
        this.username = username;
        this.advice = advice;
    }

    public String getUsername() {
        return username;
    }

    public String getAdvice() {
        return advice;
    }
}
