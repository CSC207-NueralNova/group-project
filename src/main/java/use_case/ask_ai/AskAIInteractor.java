package use_case.ask_ai;

import entity.user.User;
import org.springframework.stereotype.Service;

@Service
public class AskAIInteractor implements AskAIInputBoundary {
    private final AskAIUserDataAccessInterface userDataAccessObject;
    private final AskAIOutputBoundary aiOutputBoundary;

    public AskAIInteractor(AskAIUserDataAccessInterface userDataAccessObject,
                           AskAIOutputBoundary aiOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.aiOutputBoundary = aiOutputBoundary;
    }

    @Override
    public AskAIOutputData execute(AskAIInputData inputData) {
        User user = userDataAccessObject.findUserByUsername(inputData.getUsername());

        if (user == null) {
            AskAIOutputData outputData = new AskAIOutputData(inputData.getUsername(), "User not found");
            aiOutputBoundary.presentAIResponse(outputData);
            return outputData;
        }

        String advice = generateFinancialAdvice(user);
        AskAIOutputData outputData = new AskAIOutputData(user.getName(), advice);
        aiOutputBoundary.presentAIResponse(outputData);
        return outputData;
    }

    private String generateFinancialAdvice(User user) {
        return "Here’s your personalized financial advice, based on your spending data.";
    }
}
