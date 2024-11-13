package entity.item_spending;

/**
 * Factory for creating CommonItemSpending objects.
 */
public class CommonItemSpendingFactory implements ItemSpendingFactory {

    @Override
    public ItemSpending create(double value) {
        return new CommonItemSpending(value);
    }
}
