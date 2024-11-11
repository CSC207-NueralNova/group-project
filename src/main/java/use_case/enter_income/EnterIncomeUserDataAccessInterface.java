package use_case.enter_income;

import entity.item.Item;

// TODO: make.

public interface EnterIncomeUserDataAccessInterface {
    /**
     * Check if the type is a valid value
     * @param type the value to check
     * @return true if the type is valid
     */
    boolean validType(String type);

    /**
     * Check if the income is a valid value
     * @param incomeValue the value to check
     * @return true if the income is a non-negative value with at most 2 decimal places.
     */
    boolean validIncomeValue(double incomeValue);

    /**
     * Check if the date is a valid value.
     * @param incomeDate the value to check
     * @return true if the expense is a non-negative value with at most 2 decimal places.
     */
    boolean validIncomeDate(String incomeDate);

    /**
     * Saves the income.
     * @param item the income to save
     */
    void save(Item item);
}
