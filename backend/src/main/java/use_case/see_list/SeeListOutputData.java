package use_case.see_list;

import entity.item_income.ItemIncome;
import entity.item_spending.ItemSpending;

import java.util.List;
import java.util.Map;

/**
 * Output data for the See List use case.
 */
public class SeeListOutputData {
    private List<Map<String, Object>> spending;
    private List<Map<String, Object>> income;

    public SeeListOutputData(List<Map<String, Object>> spending, List<Map<String, Object>> income) {
        this.spending = spending;
        this.income = income;
    }

    public List<Map<String, Object>> getSpending() {
        return spending;
    }

    public List<Map<String, Object>> getIncome() {
        return income;
    }

    @Override
    public String toString() {
        return "SeeListOutputData{" +
                "spending=" + spending +
                ", income=" + income +
                '}';
    }
}

