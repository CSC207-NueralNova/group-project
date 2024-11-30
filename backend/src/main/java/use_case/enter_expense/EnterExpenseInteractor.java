package use_case.enter_expense;

import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;
import org.springframework.stereotype.Service;

/**
 * The Enter Expense interactor.
 */
@Service
public class EnterExpenseInteractor implements EnterExpenseInputBoundary {
    private final EnterExpenseUserDataAccessInterface userDataAccessObject;
    private final EnterExpenseOutputBoundary enterExpensePresenter;
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();

    public EnterExpenseInteractor(EnterExpenseUserDataAccessInterface userDataAccessObject,
                                  EnterExpenseOutputBoundary enterExpensePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.enterExpensePresenter = enterExpensePresenter;
    }

    @Override
    public EnterExpenseOutputData execute(EnterExpenseInputData enterExpenseInputData) {
        String date = enterExpenseInputData.getDate();
        double value = enterExpenseInputData.getValue();
        String category = enterExpenseInputData.getCategory();

        // Validate the expense date
        if (!validExpenseDate(date)) {
            return new EnterExpenseOutputData(true,
                    date + " does not follow the format, please enter the month and year in the format MMYY.");
        }

        // Validate the expense value
        if (!validExpenseValue(value)) {
            return new EnterExpenseOutputData(true,
                    value + " is not a valid value for an expense, please enter a positive value with up to two decimal points.");
        }

        // Proceed with processing the expense
        String uid = enterExpenseInputData.getUserId();
        MonthlySpending monthlySpending;

        if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(uid, date)) {
            monthlySpending = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(uid, date);
        } else {
            monthlySpending = this.monthlySpendingFactory.create(date);
        }

        monthlySpending.addItem(value, category);
        this.userDataAccessObject.writeMonthlySpending(uid, monthlySpending);

        // Return success message
        return new EnterExpenseOutputData(false, "Expense added successfully!");
    }


    /**
     * Validates the format of the expense date. Has to be in format "MMYY".
      * @param date The date to validate.
     * @return Whether the date is a valid date.
     */
    static boolean validExpenseDate(String date) {
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
     * Returns whether the expense value is valid.
     * Should be over 0 and have no more than two digits after the decimal.
     * @param value The value to check validity of.
     * @return Whether the value is valid.
     */
    static boolean validExpenseValue(double value) {
        if (value <= 0) {
            return false;
        }
        // Ensures there are no more than 2 decimal places.
        String[] splitter = Double.toString(value).split("\\.");
        return splitter[1].length() <= 2;
    }
}
