package entity.item_spending;

/**
 * A simple implementation of the ItemSpending interface
 */
public class CommonItemSpending implements ItemSpending {

    private final double value;
    private String category = null;

    public CommonItemSpending(double value) {
        this.value = value;
    }

    public CommonItemSpending(double value, String category) {
        this.value = value;
        this.category = category;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getCategory() {
        return this.category;
    }
}
