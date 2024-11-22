package entity.monthly_spending;

import entity.item_spending.ItemSpending;

import java.util.List;

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

    /**
     * Creates a new list of recurrent MonthlySpending.
     * @param date the date of the spendings.
     * @param recurrentSpending the list of recurrent spendings.
     * @return a new list of recurrent spending.
     */
    MonthlySpending create(String date, List<ItemSpending> recurrentSpending);
}
