package entity.monthly_spending;

import java.util.ArrayList;
import java.util.List;

import entity.item_spending.CommonItemSpendingFactory;
import entity.item_spending.ItemSpending;
import entity.item_spending.ItemSpendingFactory;

/**
 * A bare-bones implementation of the MonthlySpending interface.
 */
public class CommonMonthlySpending implements MonthlySpending {

    private String date;
    private List<ItemSpending> items; // Remove direct initialization

    // Transient field for the factory to avoid serialization
    private transient ItemSpendingFactory itemFactory;

    // No-argument constructor required by Firebase
    public CommonMonthlySpending() {
        this.items = new ArrayList<>();
        this.itemFactory = new CommonItemSpendingFactory(); // Initialize transient fields here
    }

    // Constructor for manual instantiation
    public CommonMonthlySpending(String date) {
        this();
        this.date = date;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public List<ItemSpending> getSpending() {
        return new ArrayList<>(this.items);
    }

    public void setItems(List<ItemSpending> items) {
        this.items = items;
    }

    public void addItem(double value, String category) {
        if (this.itemFactory == null) {
            this.itemFactory = new CommonItemSpendingFactory();
        }
        this.items.add(this.itemFactory.create(value, category));
    }

}
