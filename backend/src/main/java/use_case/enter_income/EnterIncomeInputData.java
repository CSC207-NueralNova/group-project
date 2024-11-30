package use_case.enter_income;

/**
 * The Input Data for the EnterIncome Use Case
 */
public class EnterIncomeInputData {
    private final String userId;
    private final String date;
    private final double value;

    public EnterIncomeInputData(String userId, String date, double value) {
        this.userId = userId;
        this.date = date;
        this.value = value;
    }

    public String getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }
}
