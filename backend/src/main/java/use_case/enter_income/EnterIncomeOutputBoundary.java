package use_case.enter_income;

/**
 * The output boundary for the EnterIncome Use Case
 */
public interface EnterIncomeOutputBoundary {
    /**
     * Prepares the success view for the EnterIncome Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(EnterIncomeOutputData outputData);

    /**
     * Prepares the failure view for the EnterIncome Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
