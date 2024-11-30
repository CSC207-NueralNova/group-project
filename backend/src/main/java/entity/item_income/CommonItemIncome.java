package entity.item_income;

/**
 * A simple implementation of the ItemIncome interface
 */
public class CommonItemIncome implements ItemIncome {

    private double value; // Remove final for Firestore compatibility
    private String date;

    // No-argument constructor for Firestore
    public CommonItemIncome() {}

    // Constructor for manual instantiation
    public CommonItemIncome(double value, String date) {
        this.value = value;
        this.date = date;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
