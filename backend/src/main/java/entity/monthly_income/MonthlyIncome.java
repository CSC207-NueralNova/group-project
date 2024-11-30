package entity.monthly_income;

import entity.item_income.ItemIncome;

import java.util.List;

/**
 * The representation of a user's income in a single month.
 */
public interface MonthlyIncome {

    /**
     * Returns the month and year in the format MMYY.
     * @return the month and year in the format MMYY.
     */
    String getDate();

    /**
     * Returns the list of incomes.
     * @return the list of incomes.
     */
    List<ItemIncome> getItems();

    /**
     * Adds an item to the list of incomes
     * @param value the value of the item
     */
    void addItem(double value, String date);

}
