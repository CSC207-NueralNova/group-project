package entity.monthly_spending;

import entity.item_spending.CommonItemSpending;
import entity.item_spending.ItemSpending;

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
    List<CommonItemSpending> getSpending();

    /**
     * Adds an item to the list of spendings.
     * @param value the value of the item
     * @param category the category of the item
     */
    void addItem(double value, String category);

    /**
     * Adds an item to the list of spendings.
     * @param value the value of the item
     * @param category the category of the item
     * @param date the date of the item
     */
    void addItem(double value, String category, String date);

}
