package interface_adapter.enter_expense;

import org.springframework.web.bind.annotation.*;
import use_case.enter_expense.EnterExpenseInputBoundary;
import use_case.enter_expense.EnterExpenseInputData;
import use_case.enter_expense.EnterExpenseOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/enter_expense")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class EnterExpenseController {

    private final EnterExpenseInputBoundary enterExpenseInteractor;

    @Autowired
    public EnterExpenseController(EnterExpenseInputBoundary enterExpenseInteractor) {
        this.enterExpenseInteractor = enterExpenseInteractor;
    }

    // Endpoint to handle enter expense requests
    @PostMapping("/enter")
    public EnterExpenseOutputData handleEnterExpenseRequest(@RequestBody EnterExpenseInputData inputData) {
        String userId = inputData.getUserId(); // Extract the userId
        String date = inputData.getDate();
        double value = inputData.getValue();
        String category = inputData.getCategory();

        // Log for debugging
        System.out.println("Trying to save UserId: " + userId + ", Date: " + date + ", Value: " + value + ", Category: " + category);

        return enterExpenseInteractor.execute(inputData);
    }
}
