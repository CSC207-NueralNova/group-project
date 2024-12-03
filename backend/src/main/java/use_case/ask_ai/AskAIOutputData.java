package use_case.ask_ai;

public class AskAIOutputData {
    private final String sender;
    private final String response;

    public AskAIOutputData(String sender, String response) {
        this.sender = sender;
        this.response = response;
    }

    public String getSender() {
        return sender;
    }

    public String getResponse() {
        return response;
    }
}