package entity.monthly_spending;

import java.util.ArrayList;
import java.util.List;

import entity.item.Item;

/**
 * A bare-bones implementation of the MonthlySpending interface.
 */
public class CommonMonthlySpending implements MonthlySpending{

    private final String date;
    private final List<Item> items = new ArrayList<>();

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
    public void addItem(Item item) {
        this.items.add(item);
    }
}
