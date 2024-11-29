package use_case.enter_recurrent_income;


import entity.recurrent_income.CommonRecurrentMonthlyIncomeFactoryFactory;
import entity.recurrent_income.RecurrentMonthlyIncomeFactory;
import entity.recurrent_income.RecurrentMonthlyIncomeFactoryFactory;
import org.springframework.stereotype.Service;

/**
 * The Enter Recurring Income interactor
 */
@Service
public class EnterRecurringIncomeInteractor implements EnterRecurrentIncomeInputBoundary {
    private final EnterRecurringIncomeUserDataAccessInterface userDataAccessObject;
    private final EnterRecurringIncomeOutputBoundary enterRecurringIncomePresenter;
    private final RecurrentMonthlyIncomeFactoryFactory recurrentMonthlyIncomeFactoryFactory = new CommonRecurrentMonthlyIncomeFactoryFactory();

    public EnterRecurringIncomeInteractor(EnterRecurringIncomeUserDataAccessInterface userDataAccessObject,
                                          EnterRecurringIncomeOutputBoundary enterRecurringIncomePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.enterRecurringIncomePresenter = enterRecurringIncomePresenter;
    }

    @Override
    public EnterRecurringIncomeOutputData execute(EnterRecurringIncomeInputData enterRecurringIncomeInputData) {
        double value = enterRecurringIncomeInputData.getValue();
        if (!validIncomeValue(value)) {
            enterRecurringIncomePresenter.prepareFailView(
                    value + " is not a valid value for an income, please enter a positive value with up to two decimal points."
            );
        } else {
            String username = this.userDataAccessObject.getCurrentUsername();
            RecurrentMonthlyIncomeFactory recurrentMonthlyIncomeFactory;

            if (this.userDataAccessObject.existsRecurrentMonthlyIncomeFactoryByUsername(username)) {
                recurrentMonthlyIncomeFactory = this.userDataAccessObject.getRecurrentMonthlyIncomeFactoryByUsername(username);
            } else {
                recurrentMonthlyIncomeFactory = this.recurrentMonthlyIncomeFactoryFactory.create();
            }

            recurrentMonthlyIncomeFactory.addRecurrentIncomeItem(value);
            this.userDataAccessObject.writeRecurringMonthlyIncomeFactory(username, recurrentMonthlyIncomeFactory);

            EnterRecurringIncomeOutputData enterRecurringIncomeOutputData = new EnterRecurringIncomeOutputData(false);
            enterRecurringIncomePresenter.prepareSuccessView(enterRecurringIncomeOutputData);
            return enterRecurringIncomeOutputData;
        }
        return new EnterRecurringIncomeOutputData(true);
    }

    /**
     * Returns whether the income value is valid.
     * Should be over 0 and have no more than two digits after the decimal.
     * @param value The value to check validity of.
     * @return Whether the value is valid.
     */
    static boolean validIncomeValue(double value) {
        if (value <= 0) {
            return false;
        }
        // Ensures there are no more than 2 decimal places.
        String[] splitter = Double.toString(value).split("\\.");
        return splitter[1].length() <= 2;
    }
}
