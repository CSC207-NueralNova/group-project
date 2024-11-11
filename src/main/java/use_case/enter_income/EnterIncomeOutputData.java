package use_case.enter_income;

//TODO: revise.
/**
 * Output Data for the Enter Income Use Case.
 */
public class EnterIncomeOutputData {
    private final String date;
    private final String type;
    private final double value;
    private final boolean useCaseFailed;

    public EnterIncomeOutputData(String date, String type, double value, boolean useCaseFailed) {
        this.date = date;
        this.type = type;
        this.value = value;
        this.useCaseFailed = useCaseFailed;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
