package entity.monthly_spending;

/**
 * Factory for creating CommonMonthlySpending objects.
 */
public class CommonMonthlySpendingFactory implements MonthlySpendingFactory {

    @Override
    public MonthlySpending create(String date) {
        return new CommonMonthlySpending(date);
    }
}
