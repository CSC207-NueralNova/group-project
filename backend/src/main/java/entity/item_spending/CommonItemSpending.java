package entity.item_spending;

/**
 * A simple implementation of the ItemSpending interface.
 */
public class CommonItemSpending implements ItemSpending {

    private double value;
    private String category;
    private String date;

    // No-argument constructor for Firestore deserialization
    public CommonItemSpending() {}

    // Constructor with only value
    public CommonItemSpending(double value) {
        this.value = value;
        this.category = "Uncategorized"; // Default category
    }

    // Constructor with value and category
    public CommonItemSpending(double value, String category) {
        this.value = value;
        this.category = category;
    }

    // Constructor with value and category
    public CommonItemSpending(double value, String category, String date) {
        this.value = value;
        this.category = category;
        this.date = date;
    }

    @Override
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

