package use_case.enter_income;

import data_access.InMemoryUserDataAccessObject;
import entity.monthly_income.MonthlyIncome;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.logout.*;

import static org.junit.jupiter.api.Assertions.*;

// TODO: make tests.

class EnterIncomeInteractorTest {

    @Test
    void successTest() {
        EnterIncomeInputData inputData = new EnterIncomeInputData("name", "1124", 10.01);
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // For the success test, we need to add Paul to the data access repository before we log in.
        UserFactory factory = new CommonUserFactory();
        User user = factory.create("Paul", "password");
        userRepository.save(user);
        userRepository.setCurrentUsername("Paul");



        // This creates a successPresenter that tests whether the test case is as we expect.
        EnterIncomeOutputBoundary successPresenter = new EnterIncomeOutputBoundary() {
            @Override
            public void prepareSuccessView(EnterIncomeOutputData outputData) {
                assertTrue(userRepository.existsMonthlyIncomeByUsernameAndDate("Paul", "1124"));
                MonthlyIncome monthlyIncome = userRepository.getMonthlyIncomeByUsernameAndDate("Paul", "1124");
                assertEquals(1, monthlyIncome.getIncome().size());
                assertEquals(10.01, monthlyIncome.getIncome().get(0).getValue());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        EnterIncomeInputBoundary interactor = new EnterIncomeInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void validIncomeDateTest() {
        assertTrue(EnterIncomeInteractor.validIncomeDate("1124"));
        assertTrue(EnterIncomeInteractor.validIncomeDate("0100"));
        assertTrue(EnterIncomeInteractor.validIncomeDate("1290"));

        assertFalse(EnterIncomeInteractor.validIncomeDate("1324"));
        assertFalse(EnterIncomeInteractor.validIncomeDate("0024"));

        assertFalse(EnterIncomeInteractor.validIncomeDate("11124"));
        assertFalse(EnterIncomeInteractor.validIncomeDate("124"));
        assertFalse(EnterIncomeInteractor.validIncomeDate("a124"));
    }

    @Test
    void validIncomeValueTest() {
        assertTrue(EnterIncomeInteractor.validIncomeValue(1));
        assertTrue(EnterIncomeInteractor.validIncomeValue(27653));
        assertTrue(EnterIncomeInteractor.validIncomeValue(23.01));
        assertTrue(EnterIncomeInteractor.validIncomeValue(23.4));

        assertFalse(EnterIncomeInteractor.validIncomeValue(0));
        assertFalse(EnterIncomeInteractor.validIncomeValue(-1));
        assertFalse(EnterIncomeInteractor.validIncomeValue(23.001));
    }
}
