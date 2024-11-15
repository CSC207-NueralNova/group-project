package entity.item;

/**
 * Factory for creating CommonItem objects.
 */
public class CommonItemFactory implements ItemFactory {

    @Override
    public Item create(String name, double value) {
        return new CommonItem(name, value);
    }
}
