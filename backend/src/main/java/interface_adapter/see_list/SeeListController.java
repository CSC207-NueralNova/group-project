package interface_adapter.see_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import use_case.see_list.SeeListInputBoundary;
import use_case.see_list.SeeListInputData;
import use_case.see_list.SeeListOutputData;

/**
 * The controller for the See List Use Case.
 */
@RestController
@RequestMapping("/api/see_list")
@CrossOrigin(origins = "http://localhost:5173")  // Allow CORS requests from the frontend
public class SeeListController {

    private final SeeListInputBoundary seeListUseCaseInteractor;

    @Autowired
    public SeeListController(SeeListInputBoundary seeListUseCaseInteractor) {
        this.seeListUseCaseInteractor = seeListUseCaseInteractor;
    }

    /**
     * Executes the See List Use Case.
     * @param seeListInputData the input data for this request.
     * Contains month and year of the items that the user wants to see, in the format MMYY.
     */
    // Endpoint to handle see list requests
    @PostMapping("/see")
    public SeeListOutputData handleSeeListRequest(@RequestBody SeeListInputData seeListInputData) {
        return this.seeListUseCaseInteractor.execute(seeListInputData);
    }

}
