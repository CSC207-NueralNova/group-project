package data_access;

import entity.recurrent_income.CommonRecurrentIncomeFactory;
import entity.recurrent_income.RecurrentIncome;
import entity.recurrent_income.RecurrentIncomeFactory;
import org.springframework.stereotype.Component;
import use_case.enter_recurrent_income.EnterRecurrentIncomeUserDataAccessInterface;

@Component
public class EnterRecurrentIncomeUserDataAccess implements EnterRecurrentIncomeUserDataAccessInterface {

    private final RecurrentIncomeFactory recurrentIncomeFactory;

    public EnterRecurrentIncomeUserDataAccess() {
        // Initialize the factory. Replace `CommonRecurrentIncomeFactory` with your actual implementation.
        this.recurrentIncomeFactory = new CommonRecurrentIncomeFactory();
    }

    @Override
    public String getCurrentUsername() {
        // Mock implementation. Replace with actual logic to fetch the username (e.g., from security context).
        return "testUser";
    }

    @Override
    public boolean existsRecurrentIncomeByUsername(String username) {
        // Mock implementation. Replace with actual database/Firebase check.
        return false;
    }

    @Override
    public RecurrentIncome getRecurrentIncomeByUsername(String username) {
        // If the income doesn't exist, use the factory to create a new instance.
        return this.recurrentIncomeFactory.create();
    }

    @Override
    public void writeRecurrentIncome(String username, RecurrentIncome recurrentIncome) {
        // Mock implementation. Replace with actual write logic (e.g., Firebase or database).
        System.out.println("Recurrent income written for user: " + username);
    }
}
