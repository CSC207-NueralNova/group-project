package entity.item;

/**
 * A simple implementation of the Item interface
 */
public class CommonItem implements Item {

    private final String name;
    private final double value;

    public CommonItem(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getValue() {
        return this.value;
    }
}
