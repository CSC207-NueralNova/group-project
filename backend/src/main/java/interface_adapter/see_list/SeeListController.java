package interface_adapter.see_list;

import use_case.see_list.SeeListInputBoundary;
import use_case.see_list.SeeListInputData;

/**
 * The controller for the See List Use Case.
 */
public class SeeListController {

    private final SeeListInputBoundary seeListUseCaseInteractor;

    public SeeListController(SeeListInputBoundary seeListUseCaseInteractor) {
        this.seeListUseCaseInteractor = seeListUseCaseInteractor;
    }

    /**
     * Executes the See List Use Case.
     * @param date the month and year of the items that the user wants to see, in the format MMYY.
     */
    public void execute(String date) {
        final SeeListInputData seeListInputData = new SeeListInputData(date);
        this.seeListUseCaseInteractor.execute(seeListInputData);
    }

}
