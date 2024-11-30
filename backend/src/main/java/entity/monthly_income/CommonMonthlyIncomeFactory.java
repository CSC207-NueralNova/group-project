package entity.monthly_income;

import org.springframework.stereotype.Component;

/**
 * Factory for creating CommonMonthlyIncome objects.
 */
@Component
public class CommonMonthlyIncomeFactory implements MonthlyIncomeFactory{

    @Override
    public MonthlyIncome create(String date) {
        return new CommonMonthlyIncome(date);
    }
}
