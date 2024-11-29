package use_case.enter_recurrent_income;

/**
 * The Input Data for the EnterRecurringIncome Use Case
 */
public class EnterRecurrentIncomeInputData {
    private final String name;
    private final String date;
    private final double value;

    public EnterRecurrentIncomeInputData(String name, String date, double value) {
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
