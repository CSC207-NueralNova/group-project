package use_case.enter_income;

// TODO: make.

import entity.item.CommonItemFactory;
import entity.item.Item;
import entity.item.ItemFactory;
import use_case.enter_expense.EnterExpenseInteractor;

public class EnterIncomeInteractor implements EnterIncomeInputBoundary{
    private final EnterIncomeUserDataAccessInterface userDataAccessObject;
    private final EnterIncomeOutputBoundary enterIncomePresenter;
    private final ItemFactory itemFactory = new CommonItemFactory();

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
        } else if (!userDataAccessObject.validType(type)) {
            enterIncomePresenter.prepareFailView(type + " is not a valid type, please enter a valid type.");
        } else if (!userDataAccessObject.validIncomeValue(value)) {
            enterIncomePresenter.prepareFailView(
                    value + " is not a valid value for an income, please enter a positive value with up to two decimal points."
            );
        } else {
            final Item item = this.itemFactory.create(enterIncomeInputData.getType(), enterIncomeInputData.getValue());
            userDataAccessObject.save(item);

            final EnterIncomeOutputData enterIncomeOutputData = new EnterIncomeOutputData(
                    date, type, value, false);
            enterIncomePresenter.prepareSuccessView(enterIncomeOutputData);
        }
    }
}
