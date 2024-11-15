package use_case.enter_expense;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: make tests.

class EnterExpenseInteractorTest {

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
