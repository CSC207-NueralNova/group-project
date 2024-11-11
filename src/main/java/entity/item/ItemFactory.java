package entity.item;

/**
 * Factory for creating new Items.
 */
public interface ItemFactory {
    /**
     * Creates a new Item instance with specified name and the value.
     * @param name The name of the Item.
     * @param value The value of the transaction
     * @return A new instance of Item with the specified parameters.
     */
    Item create(String name, double value);
}
