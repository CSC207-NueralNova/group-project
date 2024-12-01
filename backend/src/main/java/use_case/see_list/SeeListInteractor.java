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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The See List Interactor.
 */
@Service
public class SeeListInteractor implements SeeListInputBoundary {
    private final SeeListUserDataAccessInterface userDataAccessObject;
    private final MonthlySpendingFactory monthlySpendingFactory = new CommonMonthlySpendingFactory();
    private final MonthlyIncomeFactory monthlyIncomeFactory = new CommonMonthlyIncomeFactory();
    private static final String DATE_TO_STORE_RECURRENT_EXPENSE = "0000";

    public SeeListInteractor(SeeListUserDataAccessInterface userDataAccessInterface) {
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public SeeListOutputData execute(SeeListInputData seeListInputData) {
        String username = seeListInputData.getUserId();
        List<String> dates = seeListInputData.getDates();
        System.out.println("Interactor Input: Username=" + username + ", Dates=" + dates);

        List<Map<String, Object>> allSpending = new ArrayList<>();
        List<Map<String, Object>> allIncome = new ArrayList<>();

        for (String date : dates) {
            System.out.println("Processing date: " + date);

            // Fetch spending for the date
            if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, date)) {
                MonthlySpending monthlySpending = this.userDataAccessObject.getMonthlySpendingByUsernameAndDate(username, date);
                for (ItemSpending item : monthlySpending.getSpending()) {
                    Map<String, Object> spendingItem = new HashMap<>();
                    spendingItem.put("value", item.getValue());
                    spendingItem.put("category", item.getCategory());
                    spendingItem.put("date", date); // Optional: Include the date
                    allSpending.add(spendingItem);
                }
            }

            // Fetch recurrent expense from DATE_TO_STORE_RECURRENT_INCOME
            if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_EXPENSE)) {
                MonthlySpending monthlySpending = this.userDataAccessObject
                        .getMonthlySpendingByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_EXPENSE);
                for (ItemSpending item : monthlySpending.getSpending()) {
                    Map<String, Object> spendingItem = new HashMap<>();
                    spendingItem.put("value", item.getValue());
                    spendingItem.put("category", item.getCategory());
                    spendingItem.put("date", date); // Optional: Include the date
                    allSpending.add(spendingItem);
                }
            }

            // Fetch income for the date
            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, date)) {
                MonthlyIncome monthlyIncome = this.userDataAccessObject.getMonthlyIncomeByUsernameAndDate(username, date);
                for (ItemIncome item : monthlyIncome.getItems()) {
                    Map<String, Object> incomeItem = new HashMap<>();
                    incomeItem.put("value", item.getValue());
                    incomeItem.put("date", date); // Optional: Include the date
                    allIncome.add(incomeItem);
                }
            }
        }

        System.out.println("Total spending: " + allSpending);
        System.out.println("Total income: " + allIncome);

        return new SeeListOutputData(allSpending, allIncome); // Custom DTO to hold data
    }


}
