package entity.item;

/**
 * Factory for creating CommonItem objects.
 */
public class CommonItemFactory implements ItemFactory {

    @Override
    public Item create(String name, double amount) {
        return new CommonItem(name, amount);
    }
}