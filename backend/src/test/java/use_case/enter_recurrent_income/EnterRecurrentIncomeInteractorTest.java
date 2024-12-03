package use_case.enter_recurrent_income;

import data_access.InMemoryUserDataAccessObject;
import entity.monthly_income.MonthlyIncome;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The unit tests for the EnterRecurrentIncomeInteractor
 */
public class EnterRecurrentIncomeInteractorTest {
    private static InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
    private static EnterRecurrentIncomeInteractor interactor = new EnterRecurrentIncomeInteractor(userRepository);
    private static final String DATE_TO_STORE_RECURRENT_INCOME = EnterRecurrentIncomeInteractor.DATE_TO_STORE_RECURRENT_INCOME;
    private static final String USER_ID = "Paul";

    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserDataAccessObject();
        interactor = new EnterRecurrentIncomeInteractor(userRepository);

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create(USER_ID, "password");
        userRepository.save(user);
        userRepository.setCurrentUsername(USER_ID);
    }

    @Test
    void successTestFirstRecord() {
        EnterRecurrentIncomeInputData inputData = new EnterRecurrentIncomeInputData(USER_ID, "name", "1124", 10);
        EnterRecurrentIncomeOutputData outputData = interactor.execute(inputData);

        // Make sure use case did not fail
        assertFalse(outputData.isUseCaseFailed());

        assertTrue(userRepository.existsMonthlyIncomeByUsernameAndDate(USER_ID, DATE_TO_STORE_RECURRENT_INCOME));
        MonthlyIncome monthlyIncome = userRepository.getMonthlyIncomeByUsernameAndDate(USER_ID, DATE_TO_STORE_RECURRENT_INCOME);
        assertEquals(1, monthlyIncome.getIncome().size());
        assertEquals(DATE_TO_STORE_RECURRENT_INCOME, monthlyIncome.getDate());
        assertEquals(DATE_TO_STORE_RECURRENT_INCOME, monthlyIncome.getIncome().get(0).getDate());
        assertEquals(10, monthlyIncome.getIncome().get(0).getValue());
    }

    @Test
    void successTestSecondRecord() {
        interactor.execute(new EnterRecurrentIncomeInputData(USER_ID, "name1", "1124", 10));

        EnterRecurrentIncomeInputData inputData = new EnterRecurrentIncomeInputData(USER_ID, "name", "1124", 10.01);
        EnterRecurrentIncomeOutputData outputData = interactor.execute(inputData);

        // Make sure use case did not fail
        assertFalse(outputData.isUseCaseFailed());

        assertTrue(userRepository.existsMonthlyIncomeByUsernameAndDate(USER_ID, DATE_TO_STORE_RECURRENT_INCOME));
        MonthlyIncome monthlyIncome = userRepository.getMonthlyIncomeByUsernameAndDate(USER_ID, DATE_TO_STORE_RECURRENT_INCOME);
        assertEquals(2, monthlyIncome.getIncome().size());
        assertEquals(DATE_TO_STORE_RECURRENT_INCOME, monthlyIncome.getDate());
        assertEquals(DATE_TO_STORE_RECURRENT_INCOME, monthlyIncome.getIncome().get(1).getDate());
        assertEquals(10.01, monthlyIncome.getIncome().get(1).getValue());

    }

    @Test
    void failTestIncorrectAmount() {
        EnterRecurrentIncomeInputData inputData = new EnterRecurrentIncomeInputData(USER_ID, "name", "1124", 18.005);
        EnterRecurrentIncomeOutputData outputData = interactor.execute(inputData);

        // Make sure use case did fail
        assertTrue(outputData.isUseCaseFailed());

        assertFalse(userRepository.existsMonthlyIncomeByUsernameAndDate(USER_ID, DATE_TO_STORE_RECURRENT_INCOME));

    }

    @Test
    void validRecurrentIncomeValueTest() {
        assertTrue(EnterRecurrentIncomeInteractor.validIncomeValue(1));
        assertTrue(EnterRecurrentIncomeInteractor.validIncomeValue(27653));
        assertTrue(EnterRecurrentIncomeInteractor.validIncomeValue(23.01));
        assertTrue(EnterRecurrentIncomeInteractor.validIncomeValue(23.4));

        assertFalse(EnterRecurrentIncomeInteractor.validIncomeValue(0));
        assertFalse(EnterRecurrentIncomeInteractor.validIncomeValue(-1));
        assertFalse(EnterRecurrentIncomeInteractor.validIncomeValue(23.001));
    }

}
