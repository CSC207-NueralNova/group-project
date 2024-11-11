package use_case.enter_income;

// TODO: make.

import entity.item.Item;
import use_case.enter_expense.EnterExpenseInteractor;

public class EnterIncomeInteractor implements EnterIncomeInputBoundary{
    private final EnterIncomeUserDataAccessInterface userDataAccessObject;
    private final EnterIncomeOutputBoundary enterIncomePresenter;

    public EnterIncomeInteractor(EnterIncomeUserDataAccessInterface userDataAccessObject,
                                 EnterIncomeOutputBoundary enterIncomePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.enterIncomePresenter = enterIncomePresenter;
    }

    @Override
    public void execute(EnterIncomeInputData enterIncomeInputData) {
        final String date = enterIncomeInputData.getDate();
        final String type = enterIncomeInputData.getType();
        final double value = enterIncomeInputData.getValue();

        if (!userDataAccessObject.validIncomeDate(date)) {
            enterIncomePresenter.prepareFailView(
                    date + " does not follow the format, please enter the month and year in the format MMYY."
            );
        } else if (!userDataAccessObject.validIncomeValue(value)) {
            enterIncomePresenter.prepareFailView(
                    value + " is not a valid value for an income, please enter a positive value with up to two decimal points."
            );
        } else {
            final Item item = ... // TODO: create Item w/ date type & value
            userDataAccessObject.save(item);

            final EnterIncomeOutputData enterIncomeOutputData = new EnterIncomeOutputData(
                    date, type, value, false);
            enterIncomePresenter.prepareSuccessView(enterIncomeOutputData);
        }
    }
}
