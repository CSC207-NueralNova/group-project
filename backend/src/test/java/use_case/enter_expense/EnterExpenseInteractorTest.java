package use_case.enter_expense;

import data_access.InMemoryUserDataAccessObject;
import entity.monthly_spending.MonthlySpending;
import entity.user.CommonUserFactory;
import entity.user.User;
import entity.user.UserFactory;
import org.junit.jupiter.api.Test;
import use_case.logout.*;

import static org.junit.jupiter.api.Assertions.*;

// TODO: make tests.

class EnterExpenseInteractorTest {

//    @Test
//    void successTest() {
//        EnterExpenseInputData inputData = new EnterExpenseInputData("name", "1124", 10.01, "food");
//        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
//
//        // For the success test, we need to add Paul to the data access repository before we log in.
//        UserFactory factory = new CommonUserFactory();
//        User user = factory.create("Paul", "password");
//        userRepository.save(user);
//        userRepository.setCurrentUsername("Paul");
//
//
//
//        // This creates a successPresenter that tests whether the test case is as we expect.
//        EnterExpenseOutputBoundary successPresenter = new EnterExpenseOutputBoundary() {
//            @Override
//            public void prepareSuccessView(EnterExpenseOutputData outputData) {
//                assertTrue(userRepository.existsMonthlySpendingByUsernameAndDate("Paul", "1124"));
//                MonthlySpending monthlySpending = userRepository.getMonthlySpendingByUsernameAndDate("Paul", "1124");
//                assertEquals(1, monthlySpending.getSpending().size());
//                assertEquals(10.01, monthlySpending.getSpending().get(0).getValue());
//            }
//
//            @Override
//            public void prepareFailView(String error) {
//                fail("Use case failure is unexpected.");
//            }
//        };
//
//        EnterExpenseInputBoundary interactor = new EnterExpenseInteractor(userRepository, successPresenter);
//        interactor.execute(inputData);
//    }

    @Test
    void validExpenseDateTest() {
        assertTrue(EnterExpenseInteractor.validExpenseDate("1124"));
        assertTrue(EnterExpenseInteractor.validExpenseDate("0100"));
        assertTrue(EnterExpenseInteractor.validExpenseDate("1290"));

        assertFalse(EnterExpenseInteractor.validExpenseDate("1324"));
        assertFalse(EnterExpenseInteractor.validExpenseDate("0024"));

        assertFalse(EnterExpenseInteractor.validExpenseDate("11124"));
        assertFalse(EnterExpenseInteractor.validExpenseDate("124"));
        assertFalse(EnterExpenseInteractor.validExpenseDate("a124"));
    }

    @Test
    void validExpenseValueTest() {
        assertTrue(EnterExpenseInteractor.validExpenseValue(1));
        assertTrue(EnterExpenseInteractor.validExpenseValue(27653));
        assertTrue(EnterExpenseInteractor.validExpenseValue(23.01));
        assertTrue(EnterExpenseInteractor.validExpenseValue(23.4));

        assertFalse(EnterExpenseInteractor.validExpenseValue(0));
        assertFalse(EnterExpenseInteractor.validExpenseValue(-1));
        assertFalse(EnterExpenseInteractor.validExpenseValue(23.001));
    }
}
