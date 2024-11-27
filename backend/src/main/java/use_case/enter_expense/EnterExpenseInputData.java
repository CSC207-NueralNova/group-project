package use_case.enter_expense;

/**
 * The input data for the Enter Expense Use Case.
 */
public class EnterExpenseInputData {
    private String userId; // Add userId
    private String date;
    private double value;

    // Constructor
    public EnterExpenseInputData(String userId, String date, double value) {
        this.userId = userId;
        this.date = date;
        this.value = value;
    }

    // Getters
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
