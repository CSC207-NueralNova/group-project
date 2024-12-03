package interface_adapter.enter_recurrent_expense;

import org.springframework.web.bind.annotation.*;
import use_case.enter_recurrent_expense.EnterRecurrentExpenseInputBoundary;
import use_case.enter_recurrent_expense.EnterRecurrentExpenseInputData;
import use_case.enter_recurrent_expense.EnterRecurrentExpenseOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/enter_recurrent_expense")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class EnterRecurrentExpenseController {

    private final EnterRecurrentExpenseInputBoundary enterRecurrentExpenseInteractor;

    @Autowired
    public EnterRecurrentExpenseController(EnterRecurrentExpenseInputBoundary enterRecurrentExpenseInteractor) {
        this.enterRecurrentExpenseInteractor = enterRecurrentExpenseInteractor;
    }

    // Endpoint to handle enter recurrent expense requests
    @PostMapping("/enter")
    public EnterRecurrentExpenseOutputData handleEnterExpenseRequest(@RequestBody EnterRecurrentExpenseInputData inputData) {
        String userId = inputData.getUserId(); // Extract the userId
        String date = inputData.getDate();
        double value = inputData.getValue();
        String category = inputData.getCategory();

        // Log for debugging
        System.out.println();
        System.out.println("Trying to save recExp for UserId: " + userId + ", Date: " + date + ", Value: " + value + ", Category: " + category);

        return enterRecurrentExpenseInteractor.execute(inputData);
    }
}
