package interface_adapter.enter_income;

import org.springframework.web.bind.annotation.*;
import use_case.enter_income.EnterIncomeInteractor;
import use_case.enter_income.EnterIncomeInputData;
import use_case.enter_income.EnterIncomeOutputData;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/enter_income")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class EnterIncomeController {

    private final EnterIncomeInteractor enterIncomeInteractor;

    @Autowired
    public EnterIncomeController(EnterIncomeInteractor enterIncomeInteractor) {
        this.enterIncomeInteractor = enterIncomeInteractor;
    }

    // Endpoint to handle enter income request
    @PostMapping("/enter")
    public EnterIncomeOutputData handleEnterIncomeRequest(@RequestBody EnterIncomeInputData inputData) {
        // Return the chatbot response as JSON
        return enterIncomeInteractor.execute(inputData);
    }
}
