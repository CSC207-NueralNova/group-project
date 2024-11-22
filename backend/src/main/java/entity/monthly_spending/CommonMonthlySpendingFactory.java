package entity.monthly_spending;

import entity.item_spending.ItemSpending;

import java.util.List;

/**
 * Factory for creating CommonMonthlySpending objects.
 */
public class CommonMonthlySpendingFactory implements MonthlySpendingFactory {

    @Override
    public MonthlySpending create(String date) {
        return new CommonMonthlySpending(date);
    }

    @Override
    public MonthlySpending create(String date, List<ItemSpending> recurrentSpending) {
        return new CommonMonthlySpending(date, recurrentSpending);
    }
}
