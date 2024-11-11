package entity.item;

/**
 * Representation of a single transaction.
 * It can be either a piece of income or a piece of expense, but the behavior is the same.
 * Stores item name and the amount of money of the transaction.
 */
public interface Item {
    /**
     * Returns the name of the item.
     * @return the name of the item
     */
    String getName();

    /**
     * Returns the amount of money in the transaction.
     * @return the amount of money in the transaction.
     */
    double getAmount();
}
