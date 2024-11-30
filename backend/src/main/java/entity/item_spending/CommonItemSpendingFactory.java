package entity.item_spending;

/**
 * Factory for creating CommonItemSpending objects.
 */
public class CommonItemSpendingFactory implements ItemSpendingFactory {

    @Override
    public ItemSpending create(double value) {
        // Uses the constructor with only value
        return new CommonItemSpending(value);
    }

    @Override
    public ItemSpending create(double value, String category) {
        // Uses the constructor with both value and category
        return new CommonItemSpending(value, category);
    }}
