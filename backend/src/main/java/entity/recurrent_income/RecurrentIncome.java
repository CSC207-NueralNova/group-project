package entity.recurrent_income;

import entity.item_income.ItemIncome;

import java.util.List;

/**
 * This entity stores recurrent income data for one user.
 */
public interface RecurrentIncome {

    /**
     * Returns the list of the recurrent incomes.
     * @return the list of the recurrent incomes.
     */
    List<ItemIncome> getIncome();

    /**
     * Adds a recurrent income item to the recurrent item list.
     * @param amount the recurrent income item to add.
     */
    void addRecurrentIncomeItem(double amount);
}
