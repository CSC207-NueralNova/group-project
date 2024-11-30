package use_case.enter_income;

import entity.monthly_income.CommonMonthlyIncomeFactory;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;
import org.springframework.stereotype.Service;

/**
 * The Enter Income interactor
 */
@Service
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
    public EnterIncomeOutputData execute(EnterIncomeInputData enterIncomeInputData) {
        String date = enterIncomeInputData.getDate();
        double value = enterIncomeInputData.getValue();

        // Validate the date format
        if (!validIncomeDate(date)) {
            return new EnterIncomeOutputData(true, date + " does not follow the format MMYY. Please enter a valid date.");
        }

        // Validate the income value
        if (!validIncomeValue(value)) {
            return new EnterIncomeOutputData(true, value + " is not a valid value for an income. Please enter a positive value with up to two decimal points.");
        }

        // Retrieve username and prepare to save income
        String username = this.userDataAccessObject.getCurrentUsername();
        MonthlyIncome monthlyIncome;

        // Check if an income record for this user and date already exists
        if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, date)) {
            monthlyIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username, date);
        } else {
            monthlyIncome = this.monthlyIncomeFactory.create(date);
        }

        // Add the income value to the monthly income record
        monthlyIncome.addItem(value);

        // Save the updated monthly income record
        this.userDataAccessObject.writeMonthlyIncome(username, monthlyIncome);

        // Return a success message
        return new EnterIncomeOutputData(false, "Income added successfully!");
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
