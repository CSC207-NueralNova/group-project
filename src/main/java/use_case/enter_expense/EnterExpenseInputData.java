package use_case.enter_expense;

/**
 * The input data for the Enter Expense Use Case.
 */
public class EnterExpenseInputData {

    private final String name;
    private final String date;
    private final double value;

    public EnterExpenseInputData(String name, String date, double value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }

    String getName() {
        return name;
    }

    String getDate() {
        return date;
    }

    double getValue() {
        return value;
    }
}
