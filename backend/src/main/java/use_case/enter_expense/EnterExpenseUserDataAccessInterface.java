package use_case.enter_expense;

import entity.monthly_spending.MonthlySpending;

/**
 * DAO for the Enter Expense Use Case.
 */
public interface EnterExpenseUserDataAccessInterface {

    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Checks if the MonthlySpending associated with the given user and date exists.
     * @param username The username to returns the spending from.
     * @param date The date of the monthly spending in the "MMYY" format.
     * @return true if the spending exists, false otherwise.
     */
    boolean existsMonthlySpendingByUsernameAndDate(String username, String date);

    /**
     *
     * Returns the MonthlySpending for a given user and a given date in format "MMYY"
     * @param username The username to returns the spending from.
     * @param date The date of the monthly spending in the "MMYY" format.
     * @return The monthlySpending associated with the given data.
     */
    MonthlySpending getMonthlySpendingByUsernameAndDate(String username, String date);

    /**
     * Writes the monthly spending to the user record.
     * Will overwrite.
     * @param monthlySpending The MonthlySpending to rewrite the records with.
     */
    void writeMonthlySpending(String username, MonthlySpending monthlySpending);
}
