package entity.monthly_spending;

import entity.item_spending.CommonItemSpending;
import entity.item_spending.ItemSpending;

import java.util.ArrayList;
import java.util.List;

/**
 * A bare-bones implementation of the MonthlySpending interface.
 */
public class CommonMonthlySpending implements MonthlySpending {

    private String date;
    private List<CommonItemSpending> spending; // Use concrete class here

    // Required no-argument constructor for Firestore
    public CommonMonthlySpending() {
        this.spending = new ArrayList<>();
    }

    // Custom constructor for manual instantiation
    public CommonMonthlySpending(String date) {
        this.date = date;
        this.spending = new ArrayList<>();
    }

    @Override
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public List<CommonItemSpending> getSpending() {
        return new ArrayList<>(spending);
    }

    public void setSpending(List<CommonItemSpending> spending) {
        this.spending = spending;
    }

    @Override
    public void addItem(double value, String category) {
        this.spending.add(new CommonItemSpending(value, category));
    }

    @Override
    public void addItem(double value, String category, String date) {
        this.spending.add(new CommonItemSpending(value, category, date));

    }
}

