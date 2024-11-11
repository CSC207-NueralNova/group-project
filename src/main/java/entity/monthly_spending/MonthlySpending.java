package entity.monthly_spending;

import entity.item.Item;
import java.util.List;

/**
 *  The representation of a user's spending in a single month.
 */
public interface MonthlySpending {

    /**
     * Returns the month and year in the format MMYY.
     * @return the month and year in the format MMYY.
     */
    String getDate();

    /**
     * Returns the list of spendings.
     * @return the list of spendings.
     */
    List<Item> getSpending();

    /**
     * Adds an item to the list of spending.
     */
    void addItem(String name, double value);

}
