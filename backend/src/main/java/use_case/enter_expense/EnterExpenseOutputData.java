package use_case.enter_expense;

/**
 * Output Data for the Enter Expense Use Case.
 */
public class EnterExpenseOutputData {

    private final String date;
    private final double value;
    private final boolean useCaseFailed;

    public EnterExpenseOutputData(String date, double value, boolean useCaseFailed) {
        this.date = date;
        this.value = value;
        this.useCaseFailed = useCaseFailed;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
