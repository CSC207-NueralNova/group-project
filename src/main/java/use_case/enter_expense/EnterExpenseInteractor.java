package use_case.enter_expense;

import entity.item.CommonItemFactory;
import entity.item.Item;
import entity.item.ItemFactory;

/**
 * The Enter Expense interactor.
 */
public class EnterExpenseInteractor implements EnterExpenseInputBoundary {
    private final EnterExpenseUserDataAccessInterface userDataAccessObject;
    private final EnterExpenseOutputBoundary enterExpensePresenter;

    public EnterExpenseInteractor(EnterExpenseUserDataAccessInterface userDataAccessObject,
                                  EnterExpenseOutputBoundary enterExpensePresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.enterExpensePresenter = enterExpensePresenter;
    }

    @Override
    public void execute(EnterExpenseInputData enterExpenseInputData) {
        final String date = enterExpenseInputData.getDate();
        final double value = enterExpenseInputData.getValue();

        if (!userDataAccessObject.validExpenseDate(date)) {
            enterExpensePresenter.prepareFailView(
                    date + " does not follow the format, please enter the month and year in the format MMYY.");
        }
        else if (!userDataAccessObject.validExpenseValue(value)) {
            enterExpensePresenter.prepareFailView(
                    value + " is not a valid value for an expense, please enter a positive value with up to two decimal points."
            );
        }
        else {
            final ItemFactory itemFactory = new CommonItemFactory();
            final Item item = itemFactory.create(enterExpenseInputData.getName(), enterExpenseInputData.getValue());
            userDataAccessObject.save(item);

            final EnterExpenseOutputData enterExpenseOutputData = new EnterExpenseOutputData(
                    date, value, false);
            enterExpensePresenter.prepareSuccessView(enterExpenseOutputData);
        }
    }
}
