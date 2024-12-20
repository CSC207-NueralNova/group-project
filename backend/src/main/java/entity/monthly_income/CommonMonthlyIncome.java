package entity.monthly_income;

import java.util.ArrayList;
import java.util.List;

import entity.item_income.CommonItemIncome;
import entity.item_income.CommonItemIncomeFactory;
import entity.item_income.ItemIncomeFactory;

/**
 * A bare-bones implementation of the MonthlyIncome interface.
 */
public class CommonMonthlyIncome implements MonthlyIncome {

    private String date; // Make non-final for Firestore compatibility
    private List<CommonItemIncome> items; // Non-final for deserialization
    private transient ItemIncomeFactory itemFactory; // Transient to avoid serialization

    // No-argument constructor for Firestore
    public CommonMonthlyIncome() {
        this.setItems(new ArrayList<>());
        this.itemFactory = new CommonItemIncomeFactory();
    }

    // Constructor for manual instantiation
    public CommonMonthlyIncome(String date) {
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
    public List<CommonItemIncome> getIncome() {
        return new ArrayList<>(this.getItems());
    }

    public void setItems(List<CommonItemIncome> income) {
        this.items = income;
    }

    public void addItem(double value, String date) {
        if (this.itemFactory == null) {
            this.itemFactory = new CommonItemIncomeFactory();
        }
        this.getItems().add(new CommonItemIncome(value, date));
    }

    public List<CommonItemIncome> getItems() {
        return items;
    }
}
