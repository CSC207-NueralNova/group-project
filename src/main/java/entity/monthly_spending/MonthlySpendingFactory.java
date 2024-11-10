package entity.monthly_spending;

/**
 * Factory for creating MonthlySpendings.
 */
public interface MonthlySpendingFactory {
    /**
     * Creates a new MonthlySpending.
     * @param date the date of the spendings.
     * @return the new monthly spending
     */
    MonthlySpending create(String date);
}
