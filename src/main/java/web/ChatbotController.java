package web;

import org.springframework.web.bind.annotation.*;
import use_case.ask_ai.AskAIInteractor;
import use_case.ask_ai.AskAIInputData;
import use_case.ask_ai.AskAIOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/chatbot")
public class ChatbotController {

    private final AskAIInteractor askAIInteractor;

    @Autowired
    public ChatbotController(AskAIInteractor askAIInteractor) {
        this.askAIInteractor = askAIInteractor;
    }

    // Endpoint to handle chat messages
    @PostMapping("/chat")
    public AskAIOutputData handleChatRequest(@RequestParam("username") String username,
                                             @RequestParam("message") String message) {
        // Prepare input data
        AskAIInputData inputData = new AskAIInputData(username, message);

        // Return the chatbot response as JSON
        return askAIInteractor.execute(inputData);
    }
}
