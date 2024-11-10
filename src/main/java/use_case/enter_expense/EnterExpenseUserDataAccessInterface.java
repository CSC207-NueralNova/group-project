package use_case.enter_expense;

import entity.item.Item;

/**
 * DAO for the Enter Expense Use Case.
 */
public interface EnterExpenseUserDataAccessInterface {

    /**
     * Check if the expense is a valid value.
     * @param expenseValue the value to check
     * @return true if the expense is a non-negative value with at most 2 decimal places.
     */
    boolean validExpenseValue(double expenseValue);

    /**
     * Check if the date is a valid value.
     * @param expenseDate the value to check
     * @return true if the expense is a non-negative value with at most 2 decimal places.
     */
    boolean validExpenseDate(String expenseDate);

    /**
     * Saves the expense.
     * @param item the expense to save
     */
    void save(Item item);
}
