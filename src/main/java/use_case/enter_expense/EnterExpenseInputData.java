package use_case.enter_expense;

/**
 * The input data for the Enter Expense Use Case.
 */
public class EnterExpenseInputData {

    private final String date;
    private final double value;

    public EnterExpenseInputData(String date, double value) {
        this.date = date;
        this.value = value;
    }

    String getDate() {
        return date;
    }

    double getValue() {
        return value;
    }
}
