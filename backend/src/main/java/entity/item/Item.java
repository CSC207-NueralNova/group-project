package entity.item;

/**
 * Representation of a single transaction.
 * It can be either a piece of income or a piece of expense, but the behavior is the same.
 * Stores item name and the value of the transaction.
 */
public interface Item {
    /**
     * Returns the name of the item.
     * @return the name of the item
     */
    String getName();

    /**
     * Returns the value in the transaction.
     * @return the value in the transaction.
     */
    double getValue();
}
