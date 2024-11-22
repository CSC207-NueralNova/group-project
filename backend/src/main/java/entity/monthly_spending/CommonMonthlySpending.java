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
    private List<ItemSpending> recurrentItems = new ArrayList<>();

    public CommonMonthlySpending(String date) {
        this.date = date;
    }

    public CommonMonthlySpending(String date, List<ItemSpending> recurrentItems) {
        this.date = date;
        this.recurrentItems = recurrentItems;
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
    public void addItem(double value) {
        this.items.add(this.itemFactory.create(value));
    }

    @Override
    public List<ItemSpending> getRecurrentSpending() {
        return new ArrayList<>(this.recurrentItems);
    }

    @Override
    public void addRecurrentSpending(ItemSpending item) {
        this.recurrentItems.add(item);
    }
}
