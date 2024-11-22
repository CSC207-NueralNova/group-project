package use_case.enter_income;

import entity.monthly_income.CommonMonthlyIncomeFactory;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;

/**
 * The Enter Income interactor
 */
public class EnterIncomeInteractor implements EnterIncomeInputBoundary {
    private final EnterIncomeUserDataAccessInterface userDataAccessObject;
    private final EnterIncomeOutputBoundary enterIncomePresenter;
    private final MonthlyIncomeFactory monthlyIncomeFactory = new CommonMonthlyIncomeFactory();


    public EnterIncomeInteractor(EnterIncomeUserDataAccessInterface userDataAccessObject,
                                 EnterIncomeOutputBoundary enterIncomePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.enterIncomePresenter = enterIncomePresenter;
    }

    @Override
    public void execute(EnterIncomeInputData enterIncomeInputData) {
        String date = enterIncomeInputData.getDate();
        double value = enterIncomeInputData.getValue();

        if (!validIncomeDate(date)) {
            enterIncomePresenter.prepareFailView(
                    date + " does not follow the format, please enter the month and year in the format MMYY.");
        }
        else if (!validIncomeValue(value)) {
            enterIncomePresenter.prepareFailView(
                    value + " is not a valid value for an income, please enter a positive value with up to two decimal points."
            );
        }
        else {
            String username = this.userDataAccessObject.getCurrentUsername();
            MonthlyIncome monthlyIncome;

            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, date)) {
                monthlyIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username, date);
            } else {
                monthlyIncome = this.monthlyIncomeFactory.create(date);
            }

            monthlyIncome.addItem(value);
            this.userDataAccessObject.writeMonthlyIncome(username, monthlyIncome);

        }

        EnterIncomeOutputData enterIncomeOutputData = new EnterIncomeOutputData(false);
        enterIncomePresenter.prepareSuccessView(enterIncomeOutputData);
    }

    /**
     * Validates the format of the income date. Has to be in format "MMYY".
     * @param date The date to validate.
     * @return Whether the date is a valid date.
     */
    static boolean validIncomeDate(String date) {
        if (date.length() != 4) {
            return false;
        }
        try {
            int month = Integer.parseInt(date.substring(0, 2));
            Integer.parseInt(date.substring(2, 4));
            if (month < 1 || month > 12) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
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
