package use_case.enter_recurrent_income;

/**
 * The output boundary for the EnterRecurringIncome Use Case
 */
public interface EnterRecurrentIncomeOutputBoundary {
    /**
     * Prepares the success view for the EnterRecurringIncome Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EnterRecurrentIncomeOutputData outputData);

    /**
     * Prepares the failure view for the EnterRecurringIncome Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
