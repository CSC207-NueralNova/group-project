package entity.item_income;

/**
 * Factory for creating CommonItemIncome objects.
 */
public class CommonItemIncomeFactory implements ItemIncomeFactory {

    @Override
    public ItemIncome create(double value) {
        return new CommonItemIncome(value);
    }
}
