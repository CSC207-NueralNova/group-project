package use_case.ask_ai;

import entity.user.User;

/**
 * The Ask AI Interactor.
 */

public class AskAIInteractor implements AskAIInputBoundary {
    private final AskAIUserDataAccessInterface userDataAccessObject;
//    private final AskAIOutputBoundary aiOutputBoundary;

    public AskAIInteractor(AskAIUserDataAccessInterface userDataAccessObject,
                           AskAIOutputBoundary aiOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
//        this.aiOutputBoundary = aiOutputBoundary;
    }

    public void execute(AskAIInputData inputData) {
        // Query the current user from the data access object based on username
        User user = userDataAccessObject.findUserByUsername(inputData.getUsername());
//
//        if (user == null) {
//            aiOutputBoundary.presentError("User not found");
//            return;
//        }

        // Generate financial advice for the user
        String advice = generateFinancialAdvice(user);

        // Prepare the output data to be presented by the output boundary
//        AskAIOutputData outputData = new AskAIOutputData(user.getName(), advice);
//        aiOutputBoundary.presentAIResponse(outputData);
    }

    private String generateFinancialAdvice(User user) {
        // Placeholder for AI advice generation logic
        return "Here’s your personalized financial advice, based on your spending data.";
    }
}
