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
    List<ItemIncome> getIncome();

    /**
     * Adds an item to the list of spending
     * @param name the name of the item
     * @param value the value of the item
     */
    void addItem(String name, double value);
}
