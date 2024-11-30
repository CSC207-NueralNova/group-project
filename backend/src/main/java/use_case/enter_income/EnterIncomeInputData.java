package use_case.enter_income;

/**
 * The Input Data for the EnterIncome Use Case
 */
public class EnterIncomeInputData {
    private final String name;
    private final String date;
    private final double value;

    public EnterIncomeInputData(String name, String date, double value) {
        this.name = name;
        this.date = date;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getValue() {
        return value;
    }
}
