package use_case.enter_expense;

/**
 * The Enter Expense Use Case.
 */
public interface EnterExpenseInputBoundary {

    /**
     * Execute the Enter Expense Use Case.
     * @param enterExpenseInputData the input data for this use case
     */
    void execute(EnterExpenseInputData enterExpenseInputData);
}
