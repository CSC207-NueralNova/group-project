package entity.monthly_income;

/**
 * Factory for creating MonthlyIncomes.
 */
public interface MonthlyIncomeFactory {
    /**
     * Creates a new MonthlyIncome.
     * @param date the date of the income.
     * @return the new monthly income
     */
    MonthlyIncome create(String date);
}
