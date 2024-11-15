package use_case.enter_income;

/**
 * Input Boundary for actions which are related to entering income.
 */
public interface EnterIncomeInputBoundary {

    /**
     * Executes the enter income use case.
     * @param enterIncomeInputData the input data
     */
    void execute(EnterIncomeInputData enterIncomeInputData);
}
