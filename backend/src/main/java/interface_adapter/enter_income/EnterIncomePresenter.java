package interface_adapter.enter_income;


import org.springframework.stereotype.Service;
import use_case.enter_income.EnterIncomeOutputBoundary;
import use_case.enter_income.EnterIncomeOutputData;

@Service
public class EnterIncomePresenter implements EnterIncomeOutputBoundary {
    @Override
    public void prepareSuccessView(EnterIncomeOutputData outputData) {
        // temp code for testing
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // temp code for testing
    }
}
