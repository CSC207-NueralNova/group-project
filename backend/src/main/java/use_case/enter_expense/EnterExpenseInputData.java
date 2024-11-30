package use_case.enter_expense;

/**
 * The input data for the Enter Expense Use Case.
 */
public class EnterExpenseInputData {
    private String userId; // Add userId
    private String date;
    private double value;
    private String category;

    // Constructor
    public EnterExpenseInputData(String userId, String date, double value, String category) {
        this.userId = userId;
        this.date = date;
        this.value = value;
        this.category = category;
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

    public String getCategory() {return category;}
}
