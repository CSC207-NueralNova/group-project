package use_case.enter_expense;

/**
 * Output Data for the Enter Expense Use Case.
 */
public class EnterExpenseOutputData {
    private final boolean useCaseFailed;
    private final String message; // Add a message field

    public EnterExpenseOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
        this.message = "";
    }

    // overloaded optional
    public EnterExpenseOutputData(boolean useCaseFailed, String message) {
        this.useCaseFailed = useCaseFailed;
        this.message = message;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getMessage() {
        return message;
    }
}
