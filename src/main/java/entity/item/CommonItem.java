package entity.item;

/**
 * A simple implementation of the Item interface
 */
public class CommonItem implements Item {

    private final String name;
    private final String date;
    private final double amount;

    public CommonItem(String name, String date, double amount) {
        this.name = name;
        this.date = date;
        this.amount = amount;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public double getAmount() {
        return this.amount;
    }
}
