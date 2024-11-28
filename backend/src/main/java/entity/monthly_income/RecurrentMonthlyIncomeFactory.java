package entity.monthly_income;

/**
 * Factory for creating MonthlyIncomes while storing the recurrent income items
 */
public interface RecurrentMonthlyIncomeFactory {
    /**
     * Creates a new MonthlyIncome containing the recurrent items.
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
