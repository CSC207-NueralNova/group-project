package entity.user;

import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final List<MonthlySpending> listOfMonthlySpendings = new ArrayList<>();
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();

    public CommonUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public MonthlySpending getMonthlySpending(String date) {
        for (MonthlySpending storedMonthlySpending : this.listOfMonthlySpendings) {
            if (storedMonthlySpending.getDate().equals(date)) {
                return storedMonthlySpending;
            }
        }
        throw new IllegalArgumentException("Monthly spending not found for the given date.");
    }

    @Override
    public void addMonthlySpending(String date) {
        final MonthlySpending newMonthlySpending = this.monthlySpendingFactory.create(date);

        this.listOfMonthlySpendings.add(newMonthlySpending);
    }
}