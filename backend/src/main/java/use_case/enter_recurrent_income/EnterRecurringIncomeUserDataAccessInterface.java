package use_case.enter_recurrent_income;

import entity.monthly_income.MonthlyIncomeFactory;

/**
 * DAO for the Enter Recurrent Income Use Case.
 */
public interface EnterRecurringIncomeUserDataAccessInterface {
    /**
     * Returns the username of the current user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentUsername();

    /**
     * Checks if the MonthlyIncomeFactory associated with the given user exists.
     * @param username The username to return the income from.
     * @return true if the income exists, false otherwise.
     */
    boolean existsMonthlyIncomeFactoryByUsername(String username);

    /**
     *
     * Returns the MonthlyIncomeFactory for a given user.
     * @param username The username to returns the income from.
     * @return The monthlyIncome associated with the given data.
     */
    MonthlyIncomeFactory getMonthlyIncomeFactoryByUsername(String username);

    /**
     * Writes the monthly income to the user record.
     * Will overwrite.
     * @param monthlyIncomeFactory The MonthlyIncomeFactory to rewrite the records with.
     */
    void writeMonthlyIncomeFactory(String username, MonthlyIncomeFactory monthlyIncomeFactory);
}

