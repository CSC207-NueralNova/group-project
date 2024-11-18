package use_case.see_list;

// TODO: make.

import use_case.login.LoginOutputData;

/**
 * The output boundary for the See List Use Case.
 */
public interface SeeListOutputBoundary {

    /**
     * Prepares the success view for the See List Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(SeeListOutputData outputData);

    /**
     * Prepares the failure view for the See List Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
