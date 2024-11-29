package use_case.enter_recurrent_income;

/**
 * Output Data for the Enter Recurring Income Use Case.
 */
public class EnterRecurrentIncomeOutputData {
    private final boolean useCaseFailed;

    public EnterRecurrentIncomeOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
