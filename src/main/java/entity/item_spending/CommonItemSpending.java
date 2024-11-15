package entity.item_spending;

/**
 * A simple implementation of the ItemSpending interface
 */
public class CommonItemSpending implements ItemSpending {

    private final double value;

    public CommonItemSpending(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
