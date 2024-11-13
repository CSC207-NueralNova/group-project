package use_case.ask_ai;

public interface AskAIInputBoundary {
    AskAIOutputData execute(AskAIInputData inputData); // Return type updated to AskAIOutputData
}
