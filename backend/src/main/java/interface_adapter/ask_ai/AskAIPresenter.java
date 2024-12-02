package interface_adapter.ask_ai;

import org.springframework.stereotype.Component;
import use_case.ask_ai.AskAIOutputBoundary;
import use_case.ask_ai.AskAIOutputData;

@Component // Make sure Spring can manage this as a bean
public class AskAIPresenter implements AskAIOutputBoundary {
    @Override
    public void presentAIResponse(AskAIOutputData outputData) {
        // Implement your response handling logic here, e.g., logging or processing the output
        System.out.println("AI Response: " + outputData.getResponse());
    }
}
