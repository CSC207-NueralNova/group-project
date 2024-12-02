package interface_adapter.enter_recurrent_income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import use_case.enter_recurrent_income.EnterRecurrentIncomeInputBoundary;
import use_case.enter_recurrent_income.EnterRecurrentIncomeInputData;
import use_case.enter_recurrent_income.EnterRecurrentIncomeOutputData;

@RestController
@RequestMapping("/api/enter_recurrent_income")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class EnterRecurrentIncomeController {

    private final EnterRecurrentIncomeInputBoundary enterIncomeInteractor;

    @Autowired
    public EnterRecurrentIncomeController(EnterRecurrentIncomeInputBoundary enterRecurrentIncomeInteractor) {
        this.enterIncomeInteractor = enterRecurrentIncomeInteractor;
    }

    // Endpoint to handle enter recurrent income request
    @PostMapping("/enter")
    public EnterRecurrentIncomeOutputData handleEnterIncomeRequest(@RequestBody EnterRecurrentIncomeInputData inputData) {
        return enterIncomeInteractor.execute(inputData);
    }
}