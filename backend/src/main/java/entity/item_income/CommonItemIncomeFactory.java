package entity.item_income;

/**
 * Factory for creating CommonItemIncome objects.
 */
public class CommonItemIncomeFactory implements ItemIncomeFactory {
    @Override
    public CommonItemIncome create(double value, String date) {
        return new CommonItemIncome(value, date);
    }
}
