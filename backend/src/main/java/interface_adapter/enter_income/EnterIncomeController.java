package interface_adapter.enter_income;

import org.springframework.web.bind.annotation.*;
import use_case.enter_income.EnterIncomeInputBoundary;
import use_case.enter_income.EnterIncomeInputData;
import use_case.enter_income.EnterIncomeOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/enter_income")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class EnterIncomeController {

    private final EnterIncomeInputBoundary enterIncomeInteractor;

    @Autowired
    public EnterIncomeController(EnterIncomeInputBoundary enterIncomeInteractor) {
        this.enterIncomeInteractor = enterIncomeInteractor;
    }

    // Endpoint to handle enter income request
    @PostMapping("/enter")
    public EnterIncomeOutputData handleEnterIncomeRequest(@RequestBody EnterIncomeInputData inputData) {
        // Return the chatbot response as JSON
        String userId = inputData.getUserId(); // Extract the userId
        String date = inputData.getDate();
        double value = inputData.getValue();

        System.out.println("Saving Expense Payload UserId: " + userId + ", Date: " + date + ", Value: " + value);

        return enterIncomeInteractor.execute(inputData);
    }}
