package entity.monthly_spending;

import org.springframework.stereotype.Component;

@Component
public class CommonMonthlySpendingFactory implements MonthlySpendingFactory {

    @Override
    public MonthlySpending create(String date) {
        return new CommonMonthlySpending(date);
    }
}
