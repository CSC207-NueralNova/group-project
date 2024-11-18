package use_case.see_list;

// TODO: make.

import entity.item_income.ItemIncome;
import entity.item_spending.ItemSpending;
import entity.monthly_spending.MonthlySpending;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginUserDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

public class SeeListInteractor implements SeeListInputBoundary {
    private final SeeListUserDataAccessInterface userDataAccessObject;
    private final SeeListOutputBoundary seeListPresenter;

    public SeeListInteractor(SeeListUserDataAccessInterface userDataAccessInterface,
                             SeeListOutputBoundary seeListOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.seeListPresenter = seeListOutputBoundary;
    }

    @Override
    public void execute(SeeListInputData seeListInputData) {
        String date = seeListInputData.getDate();
        String username = this.userDataAccessObject.getCurrentUsername();

        List<ItemSpending> itemSpendingList = new ArrayList<>();
        List<ItemIncome> itemIncomeList = new ArrayList<>();
        if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, date)) {
            MonthlySpending monthlySpending = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(username, date);
            itemSpendingList = monthlySpending.getSpending();
            // TODO: add in the monthly income when that gets implemented.
        }
        // if monthly data doesn't exist for that month, present empty lists
        SeeListOutputData output = new SeeListOutputData(itemSpendingList, itemIncomeList);
        seeListPresenter.prepareSuccessView(output);
    }
}
