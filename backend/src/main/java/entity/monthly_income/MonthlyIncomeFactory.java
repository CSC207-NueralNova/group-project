package entity.monthly_income;

public interface MonthlyIncomeFactory {
    /**
     * Creates a new MonthlySpending.
     * @param date the date of the spendings.
     * @return the new monthly spending
     */
    MonthlyIncome create(String date);
}
