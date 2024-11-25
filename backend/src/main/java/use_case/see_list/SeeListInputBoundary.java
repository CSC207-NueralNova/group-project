package use_case.see_list;

/**
 * Input Boundary for actions which are related to seeing the current user's expense/income data.
 */
public interface SeeListInputBoundary {

    /**
     * Executes the see list use case.
     * @param seeListInputData the input data
     */
    void execute(SeeListInputData seeListInputData);

}
