package entity.item_income;

/**
 * A simple implementation of the ItemIncome interface
 */
public class CommonItemIncome implements ItemIncome {

    private final double value;

    public CommonItemIncome(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
