package use_case.see_list;

import entity.item_income.ItemIncome;
import entity.item_spending.ItemSpending;

import java.util.List;

/**
 * Output data for the See List use case.
 */
public class SeeListOutputData {
    private final List<ItemSpending> spending; // Aggregated spending data
    private final List<ItemIncome> income;   // Aggregated income data

    public SeeListOutputData(List<ItemSpending> spending, List<ItemIncome> income) {
        this.spending = spending;
        this.income = income;
    }

    public List<ItemSpending> getSpending() {
        return spending;
    }

    public List<ItemIncome> getIncome() {
        return income;
    }
}
