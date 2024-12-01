package use_case.enter_recurrent_expense;

/**
 * The Input Boundary for the Enter Recurrent Expense Use Case
 */
public interface EnterRecurrentExpenseInputBoundary {

    /**
     * Executes the enter recurring income use case.
     * @param enterRecurrentExpenseInputData the input data
     */
    EnterRecurrentExpenseOutputData execute(EnterRecurrentExpenseInputData enterRecurrentExpenseInputData);
}
