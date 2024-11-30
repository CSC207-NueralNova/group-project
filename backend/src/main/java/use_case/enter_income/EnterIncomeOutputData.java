package use_case.enter_income;

/**
 * Output Data for the Enter Income Use Case.
 */
public class EnterIncomeOutputData {
    private final boolean useCaseFailed;

    public EnterIncomeOutputData(boolean useCaseFailed, String s) {
        this.useCaseFailed = useCaseFailed;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
