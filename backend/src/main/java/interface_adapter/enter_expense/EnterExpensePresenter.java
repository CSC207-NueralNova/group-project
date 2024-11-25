package interface_adapter.enter_expense;

import org.springframework.stereotype.Service;
import use_case.enter_expense.EnterExpenseOutputBoundary;
import use_case.enter_expense.EnterExpenseOutputData;

@Service
public class EnterExpensePresenter implements EnterExpenseOutputBoundary {
    @Override
    public void prepareSuccessView(EnterExpenseOutputData outputData) {
        // temp code for testing
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // temp code for testing
    }
}
