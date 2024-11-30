package data_access;

import java.util.HashMap;
import java.util.Map;

import entity.monthly_income.MonthlyIncome;
import entity.monthly_spending.MonthlySpending;
import entity.user.User;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.enter_expense.EnterExpenseUserDataAccessInterface;
import use_case.enter_income.EnterIncomeUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface,
        EnterExpenseUserDataAccessInterface,
        EnterIncomeUserDataAccessInterface {

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Map<String, MonthlySpending>> monthlySpendings = new HashMap<>();
    private final Map<String, Map<String, MonthlyIncome>> monthlyIncome = new HashMap<>();


    private String currentUsername;

    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getName(), user);
    }

    @Override
    public User get(String username) {
        return users.get(username);
    }

    @Override
    public void changePassword(User user) {
        // Replace the old entry with the new password
        users.put(user.getName(), user);
    }

    @Override
    public void setCurrentUsername(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return this.currentUsername;
    }

    @Override
    public boolean existsMonthlyIncomeByUsernameAndDate(String username, String date) {
        if (monthlyIncome.containsKey(username)) {
            return monthlyIncome.get(username).containsKey(date);
        } else {
            return false;
        }
    }

    @Override
    public MonthlyIncome getMonthlyIncomeByUsernameAndDate(String username, String date) {
        if (monthlyIncome.containsKey(username)) {
            return monthlyIncome.get(username).get(date);
        }
        return null;
    }

    @Override
    public void writeMonthlyIncome(String username, MonthlyIncome monthlyIncome) {
        // Add an empty hashmap if there is no username
        if (!this.monthlyIncome.containsKey(username)) {
            this.monthlyIncome.put(username, new HashMap<>());
        }
        // Overwrite the existing record for the monthlySpending
        this.monthlyIncome.get(username).put(monthlyIncome.getDate(), monthlyIncome);
    }

    @Override
    public boolean existsMonthlySpendingByUsernameAndDate(String username, String date) {
        if (monthlySpendings.containsKey(username)) {
            return monthlySpendings.get(username).containsKey(date);
        } else {
            return false;
        }
    }

    @Override
    public MonthlySpending getMonthlySpendingByUsernameAndDate(String username, String date) {
        if (monthlySpendings.containsKey(username)) {
            return monthlySpendings.get(username).get(date);
        }
        return null;
    }

    @Override
    public void writeMonthlySpending(String username, MonthlySpending monthlySpending) {
        // Add an empty hashmap if there is no username
        if (!monthlySpendings.containsKey(username)) {
            this.monthlySpendings.put(username, new HashMap<>());
        }
        // Overwrite the existing record for the monthlySpending
        this.monthlySpendings.get(username).put(monthlySpending.getDate(), monthlySpending);
    }
}
