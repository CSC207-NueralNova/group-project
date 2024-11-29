package use_case.enter_recurrent_income;

import entity.recurrent_income.RecurrentIncome;

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
     * Checks if the RecurrentIncome associated with the given user exists.
     * @param username The username to return the income from.
     * @return true if the income exists, false otherwise.
     */
    boolean existsRecurrentIncomeByUsername(String username);

    /**
     *
     * Returns the RecurrentIncome for a given user.
     * @param username The username to returns the income from.
     * @return The monthlyIncome associated with the given data.
     */
    RecurrentIncome getRecurrentIncomeByUsername(String username);

    /**
     * Writes the recurrent monthly income to the user record.
     * Will overwrite.
     * @param recurrentIncome The RecurrentIncome to rewrite the records with.
     */
    void writeRecurrentIncome(String username, RecurrentIncome recurrentIncome);
}

