package entity.recurrent_monthly_income_factory;

public class CommonRecurrentMonthlyIncomeFactoryFactory implements RecurrentMonthlyIncomeFactoryFactory {
    @Override
    public RecurrentMonthlyIncomeFactory create() {
        return new CommonRecurrentMonthlyIncomeFactory();
    }
}
