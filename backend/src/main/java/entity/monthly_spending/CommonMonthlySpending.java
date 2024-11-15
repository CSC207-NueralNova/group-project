package entity.monthly_spending;

import java.util.ArrayList;
import java.util.List;

import entity.item_spending.CommonItemSpendingFactory;
import entity.item_spending.ItemSpending;
import entity.item_spending.ItemSpendingFactory;

/**
 * A bare-bones implementation of the MonthlySpending interface.
 */
public class CommonMonthlySpending implements MonthlySpending{

    private final String date;
    private final List<ItemSpending> items = new ArrayList<>();
    private final ItemSpendingFactory itemFactory = new CommonItemSpendingFactory();

    public CommonMonthlySpending(String date) {
        this.date = date;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public List<ItemSpending> getSpending() {
        return new ArrayList<>(this.items);
    }

    @Override
    public void addItem(String name, double value) {
        this.items.add(this.itemFactory.create(value));
    }
}
