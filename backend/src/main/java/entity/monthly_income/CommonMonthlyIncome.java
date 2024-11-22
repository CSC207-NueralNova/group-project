package entity.monthly_income;

import java.util.ArrayList;
import java.util.List;

import entity.item_income.CommonItemIncomeFactory;
import entity.item_income.ItemIncome;
import entity.item_income.ItemIncomeFactory;


/**
 * A bare-bones implementation of the MonthlyIncome interface.
 */
public class CommonMonthlyIncome implements MonthlyIncome {

    private final String date;
    private final List<ItemIncome> items = new ArrayList<>();
    private final ItemIncomeFactory itemFactory = new CommonItemIncomeFactory();

    public CommonMonthlyIncome(String date) {
        this.date = date;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    public List<ItemIncome> getIncome() {
        return new ArrayList<>(this.items);
    }

    @Override
    public void addItem(double value) {
        this.items.add(this.itemFactory.create(value));
    }
}
