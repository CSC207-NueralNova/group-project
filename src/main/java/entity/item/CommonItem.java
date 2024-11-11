package entity.item;

/**
 * A simple implementation of the Item interface
 */
public class CommonItem implements Item {

    private final String name;
    private final double amount;

    public CommonItem(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }
}
