package use_case.enter_recurrent_income;

import org.springframework.stereotype.Service;

import entity.monthly_income.CommonMonthlyIncomeFactory;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;

/**
 * The Enter Recurrent Income interactor.
 */
@Service
public class EnterRecurrentIncomeInteractor implements EnterRecurrentIncomeInputBoundary {
    private static final String DATE_TO_STORE_RECURRENT_INCOME = "0000";
    private final EnterRecurrentIncomeUserDataAccessInterface userDataAccessObject;
    private final MonthlyIncomeFactory monthlyIncomeFactory = new CommonMonthlyIncomeFactory();

    public EnterRecurrentIncomeInteractor(EnterRecurrentIncomeUserDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public EnterRecurrentIncomeOutputData execute(EnterRecurrentIncomeInputData enterRecurrentIncomeInputData) {
        final double value = enterRecurrentIncomeInputData.getValue();
        final String username = this.userDataAccessObject.getCurrentUsername();
        final MonthlyIncome recurrentIncome;

        final EnterRecurrentIncomeOutputData outputData;

        // Validate the income value
        if (!validIncomeValue(value)) {
            outputData = new EnterRecurrentIncomeOutputData(true);
        }
        else {

            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username,
                    DATE_TO_STORE_RECURRENT_INCOME)) {
                recurrentIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username,
                        DATE_TO_STORE_RECURRENT_INCOME);
            }
            else {
                recurrentIncome = this.monthlyIncomeFactory.create(DATE_TO_STORE_RECURRENT_INCOME);
            }

            recurrentIncome.addItem(value, DATE_TO_STORE_RECURRENT_INCOME);
            this.userDataAccessObject.writeMonthlyIncome(username, recurrentIncome);

            outputData = new EnterRecurrentIncomeOutputData(false);
        }
        return outputData;
    }

    /**
     * Returns whether the income value is valid.
     * Should be over 0 and have no more than two digits after the decimal.
     * @param value The value to check validity of.
     * @return Whether the value is valid.
     */
    static boolean validIncomeValue(double value) {
        final boolean out;
        if (value <= 0) {
            out = false;
        }
        else {
            // Ensures there are no more than 2 decimal places.
            final String[] splitter = Double.toString(value).split("\\.");
            out = splitter[1].length() <= 2;
        }
        return out;
    }
}
