package use_case.enter_recurrent_expense;

import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;
import org.springframework.stereotype.Service;

/**
 * The Enter Recurrent Expense Interactor
 */
@Service
public class EnterRecurrentExpenseInteractor implements EnterRecurrentExpenseInputBoundary {
    private final EnterRecurrentExpenseUserDataAccessInterface userDataAccessObject;
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();
    private static final String DATE_TO_STORE_RECURRENT_EXPENSE = "0000";

    public EnterRecurrentExpenseInteractor(EnterRecurrentExpenseUserDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public EnterRecurrentExpenseOutputData execute(EnterRecurrentExpenseInputData enterRecurrentExpenseInputData) {
        double value = enterRecurrentExpenseInputData.getValue();
        String category = enterRecurrentExpenseInputData.getCategory();
        String username = this.userDataAccessObject.getCurrentUsername();
        MonthlySpending recurrentExpense;

        // Validate the expense value
        if (!validExpenseValue(value)) {
            return new EnterRecurrentExpenseOutputData(true,
                    value + " is not a valid value for an expense, please enter a positive value with up to two decimal points.");
        }

        if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_EXPENSE)) {
            recurrentExpense = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_EXPENSE);
        } else {
            recurrentExpense = this.monthlySpendingFactory.create(DATE_TO_STORE_RECURRENT_EXPENSE);
        }

        recurrentExpense.addItem(value, category, DATE_TO_STORE_RECURRENT_EXPENSE);
        this.userDataAccessObject.writeMonthlySpending(username, recurrentExpense);

        // Return success message
        return new EnterRecurrentExpenseOutputData(false, "Expense added successfully!");
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
