package use_case.enter_income;

/**
 * Output Data for the Enter Income Use Case.
 */
public class EnterIncomeOutputData {
    private final boolean useCaseFailed;
    private final String message; // Add a message field

    public EnterIncomeOutputData(boolean useCaseFailed) {
        this.useCaseFailed = useCaseFailed;
        this.message = "";
    }

    public EnterIncomeOutputData(boolean useCaseFailed, String message) {
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