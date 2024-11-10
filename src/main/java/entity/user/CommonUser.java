package entity.user;

import entity.monthly_spending.CommonMonthlySpending;
import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;

import java.util.ArrayList;

/**
 * A simple implementation of the User interface.
 */
public class CommonUser implements User {

    private final String name;
    private final String password;
    private final ArrayList<MonthlySpending> monthlySpending = new ArrayList<>();

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
        for (MonthlySpending storedMonthlySpending : monthlySpending) {
            if (storedMonthlySpending.getDate().equals(date)) {
                return storedMonthlySpending;
            }
        }
        return null;
        // TODO: figure out what to do to deal with getting a month that doesn't exist for the user
    }

    @Override
    public void addMonthlySpending(String date) {
        final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();
        final MonthlySpending newMonthlySpending = monthlySpendingFactory.create(date);

        this.monthlySpending.add(newMonthlySpending);
    }
}