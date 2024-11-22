package use_case.see_list;

import entity.item_income.ItemIncome;
import entity.item_spending.ItemSpending;

import java.util.List;

public class SeeListOutputData {

    private final List<ItemSpending> spendings;
    private final List<ItemIncome> income;

    public SeeListOutputData(List<ItemSpending> spendings, List<ItemIncome> income) {
        // TODO: possibly change parameters to current username and date,
        //  then search for spendings and income of the user on that date.
        this.spendings = spendings;
        this.income = income;
    }

    public List<ItemSpending> getSpendings() {
        return spendings;
    }

    public List<ItemIncome> getIncome() {
        return income;
    }

}
