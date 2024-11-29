package use_case.enter_recurrent_income;


import entity.recurrent_monthly_income_factory.CommonRecurrentMonthlyIncomeFactoryFactory;
import entity.recurrent_monthly_income_factory.RecurrentMonthlyIncomeFactoryFactory;
import org.springframework.stereotype.Service;

/**
 * The Enter Recurring Income interactor
 */
@Service
public class EnterRecurringIncomeInteractor implements EnterRecurrentIncomeInputBoundary {
    private final EnterRecurringIncomeUserDataAccessInterface userDataAccessInterface;
    private final EnterRecurringIncomeOutputBoundary enterRecurringIncomePresenter;
    private final RecurrentMonthlyIncomeFactoryFactory recurrentMonthlyIncomeFactoryFactory = new CommonRecurrentMonthlyIncomeFactoryFactory();

    public EnterRecurringIncomeInteractor();

    @Override
    public EnterRecurringIncomeOutputData execute(EnterRecurringIncomeInputData enterRecurringIncomeInputData) {
        return null;
    }
}
