package entity.user;

import entity.monthly_spending.MonthlySpending;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Returns the specified monthly spending of the user.
     * @return the specified monthly spending of the user.
     */
    MonthlySpending getMonthlySpending(String date);

    /**
     * Adds a new MonthlySpending to the user.
     */
    void addMonthlySpending(String date);
}

