package use_case.see_list;

import java.util.List;

public class SeeListInputData {
    private String userId;
    private List<String> dates; // List of months in MMYY format

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getDates() {
        return dates;
    }

    public void setDates(List<String> dates) {
        this.dates = dates;
    }
}