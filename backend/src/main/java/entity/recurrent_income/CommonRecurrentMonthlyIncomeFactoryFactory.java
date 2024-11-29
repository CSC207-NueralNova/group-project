package entity.recurrent_income;

public class CommonRecurrentMonthlyIncomeFactoryFactory implements RecurrentMonthlyIncomeFactoryFactory {
    @Override
    public RecurrentMonthlyIncomeFactory create() {
        return new CommonRecurrentMonthlyIncomeFactory();
    }
}
