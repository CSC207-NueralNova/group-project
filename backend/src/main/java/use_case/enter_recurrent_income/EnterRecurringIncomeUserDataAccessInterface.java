package use_case.enter_recurrent_income;

import entity.recurrent_income.RecurrentMonthlyIncomeFactory;

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
     * Checks if the RecurrentMonthlyIncomeFactory associated with the given user exists.
     * @param username The username to return the income from.
     * @return true if the income exists, false otherwise.
     */
    boolean existsRecurrentMonthlyIncomeFactoryByUsername(String username);

    /**
     *
     * Returns the RecurrentMonthlyIncomeFactory for a given user.
     * @param username The username to returns the income from.
     * @return The monthlyIncome associated with the given data.
     */
    RecurrentMonthlyIncomeFactory getRecurrentMonthlyIncomeFactoryByUsername(String username);

    /**
     * Writes the recurring monthly income factory to the user record.
     * Will overwrite.
     * @param recurrentMonthlyIncomeFactory The RecurrentMonthlyIncomeFactory to rewrite the records with.
     */
    void writeRecurringMonthlyIncomeFactory(String username, RecurrentMonthlyIncomeFactory recurrentMonthlyIncomeFactory);
}

