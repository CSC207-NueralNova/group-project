package use_case.enter_expense;

/**
 * Output Data for the Enter Expense Use Case.
 */
public class EnterExpenseOutputData {

    private final String date;
    private final double amount;
    private final boolean useCaseFailed;

    public EnterExpenseOutputData(String date, double amount, boolean useCaseFailed) {
        this.date = date;
        this.amount = amount;
        this.useCaseFailed = useCaseFailed;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
