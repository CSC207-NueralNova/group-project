package entity.monthly_spending;

import java.util.ArrayList;
import java.util.List;

import entity.item.CommonItemFactory;
import entity.item.Item;
import entity.item.ItemFactory;

/**
 * A bare-bones implementation of the MonthlySpending interface.
 */
public class CommonMonthlySpending implements MonthlySpending{

    private final String date;
    private final List<Item> items = new ArrayList<>();
    private final ItemFactory itemFactory = new CommonItemFactory();

    public CommonMonthlySpending(String date) {
        this.date = date;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public List<Item> getSpending() {
        return new ArrayList<>(this.items);
    }

    @Override
    public void addItem(String name, double value) {
        this.items.add(this.itemFactory.create(name, value));
    }
}
