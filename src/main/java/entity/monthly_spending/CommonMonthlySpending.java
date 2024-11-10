package entity.monthly_spending;

import java.util.ArrayList;

/**
 * A bare-bones implementation of the MonthlySpending interface.
 */
public class CommonMonthlySpending implements MonthlySpending{

    private final String date;
    private final ArrayList<Item> items = new ArrayList<Item>();
    private final ArrayList<Item> recurrentItems = new ArrayList<Item>();

    public CommonMonthlySpending(String date) {
        this.date = date;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public ArrayList<Item> getSpending() {
        return items;
    }

    @Override
    public ArrayList<Item> getRecurrentSpending() {
        return recurrentItems;
    }

    @Override
    public void addItem(Item item) {
        if (item.isRecurrent()){
            recurrentItems.add(item);
        }
        else{
            items.add(item);
        }
    }
}
