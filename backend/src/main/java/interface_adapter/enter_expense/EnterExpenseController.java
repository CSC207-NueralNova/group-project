package interface_adapter.enter_expense;

import org.springframework.web.bind.annotation.*;
import use_case.enter_expense.EnterExpenseInputBoundary;
import use_case.enter_expense.EnterExpenseInputData;
import use_case.enter_expense.EnterExpenseOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/enter_income")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class EnterExpenseController {

    private final EnterExpenseInputBoundary enterExpenseInteractor;

    @Autowired
    public EnterExpenseController(EnterExpenseInputBoundary enterExpenseInteractor) {
        this.enterExpenseInteractor = enterExpenseInteractor;
    }

    // Endpoint to handle chat messages
    @PostMapping("/enter")
    public EnterExpenseOutputData handleChatRequest(@RequestBody EnterExpenseInputData inputData) {
        // Return the chatbot response as JSON
        return enterExpenseInteractor.execute(inputData);
    }
}
