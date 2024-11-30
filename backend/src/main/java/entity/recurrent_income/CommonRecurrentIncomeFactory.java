package entity.recurrent_income;

public class CommonRecurrentIncomeFactory implements RecurrentIncomeFactory {
    @Override
    public RecurrentIncome create() {
        return new CommonRecurrentIncome();
    }
}
