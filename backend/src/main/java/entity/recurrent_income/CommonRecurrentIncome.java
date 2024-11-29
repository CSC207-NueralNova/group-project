package entity.recurrent_income;

import entity.item_income.CommonItemIncomeFactory;
import entity.item_income.ItemIncome;
import entity.item_income.ItemIncomeFactory;

import java.util.ArrayList;
import java.util.List;

public class CommonRecurrentIncome implements RecurrentIncome {

    private final List<ItemIncome> recurrentItems = new ArrayList<>();
    private final ItemIncomeFactory itemIncomeFactory = new CommonItemIncomeFactory();

    @Override
    public List<ItemIncome> getIncome() {
        return new ArrayList<>(recurrentItems);
    }

    @Override
    public void addRecurrentIncomeItem(double amount) {
        recurrentItems.add(itemIncomeFactory.create(amount));
    }
}
