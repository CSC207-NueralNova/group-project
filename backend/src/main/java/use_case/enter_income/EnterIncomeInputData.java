package use_case.enter_income;

// TODO: make.

/**
 * The Input Data for the EnterIncome Use Case
 */
public class EnterIncomeInputData {
    private final String type;
    private final String date;
    private final double value;

    public EnterIncomeInputData(String type, String date, double value) {
        this.type = type;
        this.date = date;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }
}
