package use_case.enter_recurrent_income;

/**
 * Input Boundary for actions which are related to entering recurring income.
 */
public interface EnterRecurrentIncomeInputBoundary {
    /**
     * Executes the enter recurring income use case.
     * @param enterRecurrentIncomeInputData the input data
     */
    EnterRecurrentIncomeOutputData execute(EnterRecurrentIncomeInputData enterRecurrentIncomeInputData);
}
