package use_case.enter_recurrent_expense;

import use_case.enter_expense.EnterExpenseOutputData;

/**
 * The Output Boundary for the Enter Recurrent Expense Use Case
 */
public interface EnterRecurrentExpenseOutputBoundary {

    /**
     * Prepares the success view for the Enter Recurrent Expense Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EnterRecurrentExpenseOutputData outputData);

    /**
     * Prepares the failure view for the Enter Recurrent Expense Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
