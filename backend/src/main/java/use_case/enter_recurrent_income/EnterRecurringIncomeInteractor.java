package use_case.enter_recurrent_income;


import entity.recurrent_income.CommonRecurrentIncomeFactory;
import entity.recurrent_income.RecurrentIncome;
import entity.recurrent_income.RecurrentIncomeFactory;
import org.springframework.stereotype.Service;

/**
 * The Enter Recurring Income interactor
 */
@Service
public class EnterRecurringIncomeInteractor implements EnterRecurrentIncomeInputBoundary {
    private final EnterRecurringIncomeUserDataAccessInterface userDataAccessObject;
    private final EnterRecurringIncomeOutputBoundary enterRecurringIncomePresenter;
    private final RecurrentIncomeFactory recurrentIncomeFactory = new CommonRecurrentIncomeFactory();

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
            RecurrentIncome recurrentIncome;

            if (this.userDataAccessObject.existsRecurrentIncomeByUsername(username)) {
                recurrentIncome = this.userDataAccessObject.getRecurrentIncomeByUsername(username);
            } else {
                recurrentIncome = this.recurrentIncomeFactory.create();
            }

            recurrentIncome.addRecurrentIncomeItem(value);
            this.userDataAccessObject.writeRecurrentIncome(username, recurrentIncome);

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
