package entity.item;

/**
 * Factory for creating new Items.
 */
public interface ItemFactory {
    /**
     * Creates a new Item instance with specified name, date and the amount.
     * @param name The name of the Item.
     * @param amount The amount of the transaction
     * @return A new instance of Item with the specified parameters.
     */
    Item create(String name, double amount);
}
