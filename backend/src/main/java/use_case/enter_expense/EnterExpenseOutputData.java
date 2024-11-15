package use_case.enter_expense;

/**
 * Output Data for the Enter Expense Use Case.
 */
public class EnterExpenseOutputData {
    private final boolean useCaseFailed;

    public EnterExpenseOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
