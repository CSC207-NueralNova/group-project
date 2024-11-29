package entity.recurrent_income;

import entity.monthly_income.MonthlyIncome;

/**
 * Factory for creating RecurringIncomes while storing the recurrent income items
 */
public interface RecurrentIncome {
    /**
     * Creates a new RecurringIncome containing the recurrent items.
     * @param date the date of the income.
     * @return the new monthly income
     */
    MonthlyIncome create(String date);

    /**
     * Adds a recurrent income item to the recurrent item list.
     * @param amount the recurrent income item to add.
     */
    void addRecurrentIncomeItem(double amount);
}
