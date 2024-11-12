package use_case.enter_expense;

import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;

/**
 * The Enter Expense interactor.
 */
public class EnterExpenseInteractor implements EnterExpenseInputBoundary {
    private final EnterExpenseUserDataAccessInterface userDataAccessObject;
    private final EnterExpenseOutputBoundary enterExpensePresenter;
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();

    public EnterExpenseInteractor(EnterExpenseUserDataAccessInterface userDataAccessObject,
                                  EnterExpenseOutputBoundary enterExpensePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.enterExpensePresenter = enterExpensePresenter;
    }

    @Override
    public void execute(EnterExpenseInputData enterExpenseInputData) {
        String date = enterExpenseInputData.getDate();
        String name = enterExpenseInputData.getName();
        double value = enterExpenseInputData.getValue();

        if (!this.validExpenseDate(date)) {
            enterExpensePresenter.prepareFailView(
                    date + " does not follow the format, please enter the month and year in the format MMYY.");
        }
        else if (!this.validExpenseValue(value)) {
            enterExpensePresenter.prepareFailView(
                    value + " is not a valid value for an expense, please enter a positive value with up to two decimal points."
            );
        }
        else {
            String username = this.userDataAccessObject.getCurrentUsername();
            MonthlySpending monthlySpending;

            if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, date)) {
                monthlySpending = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(username, date);
            } else {
                monthlySpending = this.monthlySpendingFactory.create(date);
            }

            monthlySpending.addItem(name, value);
            this.userDataAccessObject.writeMonthlySpending(username, monthlySpending);

        }

            EnterExpenseOutputData enterExpenseOutputData = new EnterExpenseOutputData(
                    date, value, false);
            enterExpensePresenter.prepareSuccessView(enterExpenseOutputData);
        }

    private boolean validExpenseDate(String date) {
        if (date.length() != 4) {
            return false;
        }
        try {
            int month = Integer.parseInt(date.substring(0, 2));
            Integer.parseInt(date.substring(2, 4));
            if (month < 1 || month > 12) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private boolean validExpenseValue(double value) {
        if (value <= 0) {
            return false;
        }
//        TODO implement checks for length after decimal point.
        return true;
    }
}
