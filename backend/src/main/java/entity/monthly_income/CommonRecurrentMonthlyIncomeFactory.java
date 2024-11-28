package entity.monthly_income;

import entity.item_income.CommonItemIncomeFactory;
import entity.item_income.ItemIncome;
import entity.item_income.ItemIncomeFactory;

import java.util.ArrayList;
import java.util.List;

public class CommonRecurrentMonthlyIncomeFactory implements RecurrentMonthlyIncomeFactory {

    private final List<ItemIncome> recurrentItems = new ArrayList<>();
    private final ItemIncomeFactory itemIncomeFactory = new CommonItemIncomeFactory();

    @Override
    public MonthlyIncome create(String date) {
        MonthlyIncome newMonthlyIncome = new CommonMonthlyIncome(date);
        for (ItemIncome itemIncome : recurrentItems) {
            newMonthlyIncome.addItem(itemIncome.getValue());
        }
        return newMonthlyIncome;
    }

    @Override
    public void addRecurrentIncomeItem(double amount) {
        recurrentItems.add(itemIncomeFactory.create(amount));
    }
}
