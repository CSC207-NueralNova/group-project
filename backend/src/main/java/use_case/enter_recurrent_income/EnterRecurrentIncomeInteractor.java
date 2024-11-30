package use_case.enter_recurrent_income;

import entity.monthly_income.CommonMonthlyIncomeFactory;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;


public class EnterRecurrentIncomeInteractor implements EnterRecurrentIncomeInputBoundary {

    private final EnterRecurrentIncomeUserDataAccessInterface userDataAccessObject;
    private final MonthlyIncomeFactory monthlyIncomeFactory = new CommonMonthlyIncomeFactory();
    private static final String DATE_TO_STORE_RECURRENT_INCOME = "0000";

    public EnterRecurrentIncomeInteractor(EnterRecurrentIncomeUserDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public EnterRecurrentIncomeOutputData execute(EnterRecurrentIncomeInputData enterRecurrentIncomeInputData) {
        double value = enterRecurrentIncomeInputData.getValue();
        String username = this.userDataAccessObject.getCurrentUsername();
        MonthlyIncome recurrentIncome;

        if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_INCOME)) {
            recurrentIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_INCOME);
        } else {
            recurrentIncome = this.monthlyIncomeFactory.create(DATE_TO_STORE_RECURRENT_INCOME);
        }

        recurrentIncome.addItem(value, DATE_TO_STORE_RECURRENT_INCOME);
        this.userDataAccessObject.writeMonthlyIncome(username, recurrentIncome);

        return new EnterRecurrentIncomeOutputData(false);
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
