package use_case.enter_recurrent_income;

/**
 * Output Data for the Enter Recurring Income Use Case.
 */
public class EnterRecurringIncomeOutputData {
    private final boolean useCaseFailed;

    public EnterRecurringIncomeOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
