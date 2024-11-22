package entity.item_spending;

/**
 * Representation of a single spending transaction.
 * Stores item value of the transaction.
 */
public interface ItemSpending {

    /**
     * Returns the value in the transaction.
     * @return the value in the transaction.
     */
    double getValue();

    /**
     * Returns the category in the transaction.
     * @return the category in the transaction.
     */
    String getCategory();
}
