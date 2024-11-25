package use_case.see_list;

import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;

import entity.monthly_income.CommonMonthlyIncomeFactory;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;
import org.springframework.stereotype.Service;

/**
 * The See List Interactor.
 */
@Service
public class SeeListInteractor implements SeeListInputBoundary {
    private final SeeListUserDataAccessInterface userDataAccessObject;
    private final SeeListOutputBoundary seeListPresenter;
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();
    private final MonthlyIncomeFactory monthlyIncomeFactory = new CommonMonthlyIncomeFactory();

    public SeeListInteractor(SeeListUserDataAccessInterface userDataAccessInterface,
                             SeeListOutputBoundary seeListOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.seeListPresenter = seeListOutputBoundary;
    }

    @Override
    public SeeListOutputData execute(SeeListInputData seeListInputData) {
        String date = seeListInputData.getDate();
        String username = this.userDataAccessObject.getCurrentUsername();

        MonthlySpending monthlySpending;
        if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, date)) {
            monthlySpending = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(username, date);
        } else {
            monthlySpending = this.monthlySpendingFactory.create(date);
        }

        MonthlyIncome monthlyIncome;
        if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, date)) {
            monthlyIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username, date);
        } else {
            monthlyIncome = this.monthlyIncomeFactory.create(date);
        }

        // if monthly data doesn't exist for that month, present empty lists

        return new SeeListOutputData(monthlySpending.getSpending(), monthlyIncome.getIncome());
    }
}
