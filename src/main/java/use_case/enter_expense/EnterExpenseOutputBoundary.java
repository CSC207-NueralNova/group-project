package use_case.enter_expense;

/**
 * The output boundary for the Enter Expense Use Case.
 */
public interface EnterExpenseOutputBoundary {
    /**
     * Prepares the success view for the Enter Expense Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EnterExpenseOutputData outputData);

    /**
     * Prepares the failure view for the Enter Expense Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
