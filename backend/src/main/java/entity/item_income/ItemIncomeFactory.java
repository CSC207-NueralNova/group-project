package entity.item_income;

/**
 * Factory for creating new ItemIncome.
 */
public interface ItemIncomeFactory {
    /**
     * Creates a new ItemIncome instance with specified value.
     * @param value The value of the transaction
     * @param date The date of the transaction
     * @return A new instance of Item with the specified parameters.
     */
    ItemIncome create(double value, String date);
}
