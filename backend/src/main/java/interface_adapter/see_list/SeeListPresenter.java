package interface_adapter.see_list;


import org.springframework.stereotype.Service;
import use_case.see_list.SeeListOutputBoundary;
import use_case.see_list.SeeListOutputData;

@Service
public class SeeListPresenter implements SeeListOutputBoundary {
    @Override
    public void prepareSuccessView(SeeListOutputData outputData) {
        // temp code for testing
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // temp code for testing
    }
}
