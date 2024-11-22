package entity.monthly_income;

/**
 * Factory for creating CommonMonthlyIncome objects.
 */
public class CommonMonthlyIncomeFactory implements MonthlyIncomeFactory{

    @Override
    public MonthlyIncome create(String date) {
        return new CommonMonthlyIncome(date);
    }
}
