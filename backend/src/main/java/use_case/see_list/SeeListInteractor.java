package use_case.see_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import entity.item_income.ItemIncome;
import entity.item_spending.ItemSpending;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_spending.MonthlySpending;

/**
 * The See List Interactor.
 */
@Service
public class SeeListInteractor implements SeeListInputBoundary {

    private static final String VALUE = "value";
    private static final String DATE = "date";
    private static final String DATE_TO_STORE_RECURRENT_INCOME = "0000";
    private static final String DATE_TO_STORE_RECURRENT_SPENDING = "0000";
    private final SeeListUserDataAccessInterface userDataAccessObject;

    public SeeListInteractor(SeeListUserDataAccessInterface userDataAccessInterface) {
        this.userDataAccessObject = userDataAccessInterface;
    }

    @Override
    public SeeListOutputData execute(SeeListInputData seeListInputData) {
        final String username = seeListInputData.getUserId();
        final List<String> dates = seeListInputData.getDates();
        // System.out.println("Interactor Input: Username=" + username + ", Dates=" + dates);

        final List<Map<String, Object>> allSpending = new ArrayList<>();
        final List<Map<String, Object>> allIncome = new ArrayList<>();

        for (String date : dates) {
            // System.out.println("Processing date: " + date);

            // Fetch spending for the date
            if (this.userDataAccessObject.existsMonthlySpendingByUsernameAndDate(username, date)) {
                final MonthlySpending monthlySpending = this.userDataAccessObject
                        .getMonthlySpendingByUsernameAndDate(username, date);
                for (ItemSpending item : monthlySpending.getSpending()) {
                    final Map<String, Object> spendingItem = new HashMap<>();
                    makeSpendingItem(spendingItem, item, date);
                    allSpending.add(spendingItem);
                }
            }

            // Fetch income for the date
            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(username, date)) {
                final MonthlyIncome monthlyIncome = this.userDataAccessObject
                        .getMonthlyIncomeByUsernameAndDate(username, date);
                for (ItemIncome item : monthlyIncome.getItems()) {
                    final Map<String, Object> incomeItem = new HashMap<>();
                    makeIncomeItem(incomeItem, item, date);
                    allIncome.add(incomeItem);
                }
            }

            // Fetch recurrent income from DATE_TO_STORE_RECURRENT_INCOME
            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(
                    username, DATE_TO_STORE_RECURRENT_INCOME)) {
                final MonthlyIncome monthlyIncome = this.userDataAccessObject
                        .getMonthlyIncomeByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_INCOME);
                for (ItemIncome item : monthlyIncome.getItems()) {
                    final Map<String, Object> incomeItem = new HashMap<>();
                    // This date is not the DATE_TO_STORE_RECURRENT_INCOME to visualize correctly on the front end.
                    makeIncomeItem(incomeItem, item, date);
                    allIncome.add(incomeItem);
                }
            }

            // Fetch recurrent expenses from DATE_TO_STORE_RECURRENT_SPENDING
            if (this.userDataAccessObject.existsMonthlyIncomeByUsernameAndDate(
                    username, DATE_TO_STORE_RECURRENT_SPENDING)) {
                final MonthlySpending monthlySpending = this.userDataAccessObject
                        .getMonthlySpendingByUsernameAndDate(username, DATE_TO_STORE_RECURRENT_SPENDING);
                for (ItemSpending item : monthlySpending.getSpending()) {
                    final Map<String, Object> spendingItem = new HashMap<>();
                    // This date is not the DATE_TO_STORE_RECURRENT_SPENDING to visualize correctly on the front end.
                    makeSpendingItem(spendingItem, item, date);
                    allIncome.add(spendingItem);
                }
            }
        }

        // System.out.println("Total spending: " + allSpending);
        // System.out.println("Total income: " + allIncome);

        // Custom DTO to hold data
        return new SeeListOutputData(allSpending, allIncome);
    }

    /**
     * A helper function for the execute function, populates a Map item for the allSpending list.
     * @param spendingItem the Map to be populated.
     * @param item the spending to be transferred into a HashMap.
     * @param date date of the spending.
     */
    public void makeSpendingItem(Map spendingItem, ItemSpending item, String date) {
        spendingItem.put(VALUE, item.getValue());
        spendingItem.put("category", item.getCategory());
        // Optional: Include the date
        spendingItem.put(DATE, date);
    }

    /**
     * A helper function for the execute function, populates a Map item for the allIncome list.
     * @param incomeItem the Map to be populated.
     * @param item the income to be transferred into a HashMap.
     * @param date date of the income.
     */
    public void makeIncomeItem(Map incomeItem, ItemIncome item, String date) {
        incomeItem.put(VALUE, item.getValue());
        // Optional: Include the date
        incomeItem.put(DATE, date);
    }

}
