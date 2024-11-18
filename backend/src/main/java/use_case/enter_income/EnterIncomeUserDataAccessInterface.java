package use_case.enter_income;

import entity.item.Item;
import entity.monthly_income.MonthlyIncome;

// TODO: make.

public interface EnterIncomeUserDataAccessInterface {
    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Checks if the MonthlyIncome associated with the given user and date exists.
     * @param username The username to return the income from.
     * @param date The date of the monthly income in the "MMYY" format.
     * @return true if the income exists, false otherwise.
     */
    boolean existsMonthlyIncomeByUsernameAndDate(String username, String date);

    /**
     *
     * Returns the MonthlyIncome for a given user and a given date in format "MMYY"
     * @param username The username to returns the income from.
     * @param date The date of the monthly income in the "MMYY" format.
     * @return The monthlyIncome associated with the given data.
     */
    MonthlyIncome getMonthlyIncomeByUsernameAndDate(String username, String date);

    /**
     * Writes the monthly income to the user record.
     * Will overwrite.
     * @param monthlyIncome The MonthlyIncome to rewrite the records with.
     */
    void writeMonthlyIncome(String username, MonthlyIncome monthlyIncome);
}
