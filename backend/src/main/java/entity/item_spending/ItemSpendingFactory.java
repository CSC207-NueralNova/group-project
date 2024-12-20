package entity.item_spending;

/**
 * Factory for creating new ItemSpending.
 */
public interface ItemSpendingFactory {
    /**
     * Creates a new ItemSpending instance with specified value.
     * @param value The value of the transaction
     * @return A new instance of Item with the specified parameters.
     */
    ItemSpending create(double value);

    /**
     * Creates a new ItemSpending instance with specified value.
     * @param value The value of the transaction
     * @param category The category of the transaction
     * @return A new instance of Item with the specified parameters.
     */
    ItemSpending create(double value, String category);
}
