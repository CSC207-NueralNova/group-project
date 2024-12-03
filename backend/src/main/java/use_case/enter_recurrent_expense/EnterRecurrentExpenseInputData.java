package use_case.enter_recurrent_expense;

/**
 * The Input Data for the Enter Recurrent Expense Use Case
 */
public class EnterRecurrentExpenseInputData {
    private final String userId;
    private final String date;
    private final double value;
    private final String category;

    // Constructor
    public EnterRecurrentExpenseInputData(String userId, String date, double value, String category) {
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

    public String getCategory() {
        return category;
    }
}