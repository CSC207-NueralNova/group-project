package entity.recurrent_income;

public interface RecurrentIncomeFactory {

    /**
     * Creates a new RecurrentIncome for storing recurrent incomes of one user.
     * @return the new recurring income record.
     */
    RecurrentIncome create();
}
