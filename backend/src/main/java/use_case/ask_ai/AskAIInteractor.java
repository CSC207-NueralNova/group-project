package use_case.ask_ai;

import org.springframework.stereotype.Service;

@Service
public class AskAIInteractor implements AskAIInputBoundary {

    private final AskAIUserDataAccessInterface userDataAccessObject;
    private final AskAIOutputBoundary aiOutputBoundary;

    // Constructor with both dependencies
    public AskAIInteractor(AskAIUserDataAccessInterface userDataAccessObject,
                           AskAIOutputBoundary aiOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.aiOutputBoundary = aiOutputBoundary;
    }

    @Override
    public AskAIOutputData execute(AskAIInputData inputData) {
        // Capture the user's message
        String userMessage = inputData.getMessage();

        // Use the DAO to get a response from the AI
        String responseText;
        try {
            responseText = userDataAccessObject.getResponseFromAI(userMessage);
        } catch (Exception e) {
            responseText = "Sorry, there was an error processing your request. Please try again.";
        }

        // Create the output data, including the username and response
        AskAIOutputData outputData = new AskAIOutputData("AI Bot", responseText);

        // Send the output data to the output boundary
        aiOutputBoundary.presentAIResponse(outputData);

        // Return the output data for the HTTP response
        return outputData;
    }
}

