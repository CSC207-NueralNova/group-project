package use_case.enter_recurrent_expense;

/**
 * The Output Data for the Enter Recurrent Expense Use Case
 */
public class EnterRecurrentExpenseOutputData {
    private final boolean useCaseFailed;
    private final String message; // Add a message field

    public EnterRecurrentExpenseOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
        this.message = "";
    }

    // overloaded optional
    public EnterRecurrentExpenseOutputData(boolean useCaseFailed, String message) {
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
