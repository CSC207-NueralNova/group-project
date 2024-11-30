package entity.monthly_income;

import java.util.ArrayList;
import java.util.List;

import entity.item_income.CommonItemIncome;
import entity.item_income.CommonItemIncomeFactory;
import entity.item_income.ItemIncome;
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
        this.items = new ArrayList<>();
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

    @Override
    public List<CommonItemIncome> getIncome() {
        return new ArrayList<>(this.items);
    }

    public void addItem(double value, String date) {
        if (this.itemFactory == null) {
            this.itemFactory = new CommonItemIncomeFactory();
        }
        this.items.add(new CommonItemIncome(value, date));
    }
}
