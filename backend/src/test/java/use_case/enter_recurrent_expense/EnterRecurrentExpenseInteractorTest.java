package use_case.enter_recurrent_expense;

import data_access.InMemoryUserDataAccessObject;
import entity.monthly_spending.MonthlySpending;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for EnterRecurrentExpenseInteractor.
 */
public class EnterRecurrentExpenseInteractorTest {
    public static String USER = "Paul";
    public static String DATE_TO_STORE_RECURRENT_EXPENSE = "0000";
    InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
    EnterRecurrentExpenseInteractor interactor = new EnterRecurrentExpenseInteractor(userRepository);

    @Test
    void successTest() {
        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(USER, "password");
        userRepository.save(user);
        userRepository.setCurrentUsername(USER);

        assertFalse(userRepository.existsMonthlySpendingByUsernameAndDate(USER, DATE_TO_STORE_RECURRENT_EXPENSE));
    }

    @Test
    void FirstTest() {
        EnterRecurrentExpenseInputData inputData = new EnterRecurrentExpenseInputData(USER, "1124", 9.99, "Miscellaneous");
        EnterRecurrentExpenseOutputData outputData = interactor.execute(inputData);

        assertFalse(outputData.isUseCaseFailed());

        assertTrue(userRepository.existsMonthlySpendingByUsernameAndDate(USER, DATE_TO_STORE_RECURRENT_EXPENSE));
        MonthlySpending monthlySpending = userRepository.getMonthlySpendingByUsernameAndDate(USER, DATE_TO_STORE_RECURRENT_EXPENSE);
        assertEquals(1, monthlySpending.getSpending().size());
        assertEquals(DATE_TO_STORE_RECURRENT_EXPENSE, monthlySpending.getDate());
        assertEquals(9.99, monthlySpending.getSpending().get(0).getValue());
    }

    @Test
    void SecondTest() {
        interactor.execute(new EnterRecurrentExpenseInputData(USER, "1124", 9.99, "Miscellaneous"));

        //Add new expense
        EnterRecurrentExpenseInputData inputData = new EnterRecurrentExpenseInputData(USER, "1124", 100, "Rent");
        EnterRecurrentExpenseOutputData outputData = interactor.execute(inputData);

        assertFalse(outputData.isUseCaseFailed());

        assertTrue(userRepository.existsMonthlySpendingByUsernameAndDate(USER, DATE_TO_STORE_RECURRENT_EXPENSE));
        MonthlySpending monthlySpending = userRepository.getMonthlySpendingByUsernameAndDate(USER, DATE_TO_STORE_RECURRENT_EXPENSE);
        assertEquals(2, monthlySpending.getSpending().size());
        assertEquals(DATE_TO_STORE_RECURRENT_EXPENSE, monthlySpending.getDate());
        assertEquals(100, monthlySpending.getSpending().get(1).getValue());
    }

    @Test
    void failTestIncorrectAmount() {
        EnterRecurrentExpenseInputData inputData = new EnterRecurrentExpenseInputData(USER, "1224", 10.005, "Food");
        EnterRecurrentExpenseOutputData outputData = interactor.execute(inputData);

        assertTrue(outputData.isUseCaseFailed());

        assertFalse(userRepository.existsMonthlyIncomeByUsernameAndDate(USER, DATE_TO_STORE_RECURRENT_EXPENSE));

    }

    @Test
    void validExpenseValueTest() {
        assertTrue(EnterRecurrentExpenseInteractor.validExpenseValue(1));
        assertTrue(EnterRecurrentExpenseInteractor.validExpenseValue(27653));
        assertTrue(EnterRecurrentExpenseInteractor.validExpenseValue(23.01));
        assertTrue(EnterRecurrentExpenseInteractor.validExpenseValue(23.4));

        assertFalse(EnterRecurrentExpenseInteractor.validExpenseValue(0));
        assertFalse(EnterRecurrentExpenseInteractor.validExpenseValue(-1));
        assertFalse(EnterRecurrentExpenseInteractor.validExpenseValue(23.001));
    }
}
