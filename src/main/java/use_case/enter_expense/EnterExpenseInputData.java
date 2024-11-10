package use_case.enter_expense;

/**
 * The input data for the Enter Expense Use Case.
 */
public class EnterExpenseInputData {

    private final String date;
    private final double amount;

    public EnterExpenseInputData(String date, double amount) {
        this.date = date;
        this.amount = amount;
    }

    String getDate() {
        return date;
    }

    double getAmount() {
        return amount;
    }
}
