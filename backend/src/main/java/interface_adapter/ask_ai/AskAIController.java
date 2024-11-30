package interface_adapter.ask_ai;

import org.springframework.web.bind.annotation.*;
import use_case.ask_ai.AskAIInputBoundary;
import use_case.ask_ai.AskAIInputData;
import use_case.ask_ai.AskAIOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/chatbot")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class AskAIController {

    private final AskAIInputBoundary askAIInteractor;

    @Autowired
    public AskAIController(AskAIInputBoundary askAIInteractor) {
        this.askAIInteractor = askAIInteractor;
    }

    // Endpoint to handle chat messages
    @PostMapping("/chat")
    public AskAIOutputData handleChatRequest(@RequestBody AskAIInputData inputData) {
        // Return the chatbot response as JSON
        return askAIInteractor.execute(inputData);
    }
}
