package use_case.see_list;

import entity.item_income.ItemIncome;
import entity.item_spending.ItemSpending;
import entity.monthly_spending.CommonMonthlySpendingFactory;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;

import entity.monthly_income.CommonMonthlyIncomeFactory;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The See List Interactor.
 */
@Service
public class SeeListInteractor implements SeeListInputBoundary {
    private final SeeListUserDataAccessInterface userDataAccessObject;
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();
    private final MonthlyIncomeFactory monthlyIncomeFactory = new CommonMonthlyIncomeFactory();

    public SeeListInteractor(SeeListUserDataAccessInterface userDataAccessInterface) {
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public SeeListOutputData execute(SeeListInputData seeListInputData) {
        List<String> dates = seeListInputData.getDates(); // List of months to query
        String username = this.userDataAccessObject.getCurrentUsername();

        List<ItemSpending> allSpending = new ArrayList<>();
        List<ItemIncome> allIncome = new ArrayList<>();

        for (String date : dates) {
            // Handle spending
            MonthlySpending monthlySpending;
            if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, date)) {
                monthlySpending = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(username, date);
            } else {
                monthlySpending = this.monthlySpendingFactory.create(date);
            }
            allSpending.addAll(monthlySpending.getSpending());

            // Handle income
            MonthlyIncome monthlyIncome;
            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, date)) {
                monthlyIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username, date);
            } else {
                monthlyIncome = this.monthlyIncomeFactory.create(date);
            }
            allIncome.addAll(monthlyIncome.getItems());
        }

        // Return aggregated spending and income data
        return new SeeListOutputData(allSpending, allIncome);
    }
}
