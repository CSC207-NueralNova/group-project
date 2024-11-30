package use_case.enter_recurrent_income;

import entity.recurrent_income.CommonRecurrentIncomeFactory;
import entity.recurrent_income.RecurrentIncome;
import entity.recurrent_income.RecurrentIncomeFactory;
import org.springframework.stereotype.Service;

@Service
public class EnterRecurrentIncomeInteractor implements EnterRecurrentIncomeInputBoundary {

    private final EnterRecurrentIncomeUserDataAccessInterface userDataAccessObject;
    private final RecurrentIncomeFactory recurrentIncomeFactory = new CommonRecurrentIncomeFactory();

    public EnterRecurrentIncomeInteractor(EnterRecurrentIncomeUserDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public EnterRecurrentIncomeOutputData execute(EnterRecurrentIncomeInputData enterRecurrentIncomeInputData) {
        double value = enterRecurrentIncomeInputData.getValue();
        String username = this.userDataAccessObject.getCurrentUsername();
        RecurrentIncome recurrentIncome;

        if (this.userDataAccessObject.existsRecurrentIncomeByUsername(username)) {
            recurrentIncome = this.userDataAccessObject.getRecurrentIncomeByUsername(username);
        } else {
            recurrentIncome = this.recurrentIncomeFactory.create();
        }

        recurrentIncome.addRecurrentIncomeItem(value);
        this.userDataAccessObject.writeRecurrentIncome(username, recurrentIncome);

        return new EnterRecurrentIncomeOutputData(false);
    }

    /**
     * Returns whether the income value is valid.
     * Should be over 0 and have no more than two digits after the decimal.
     * @param value The value to check validity of.
     * @return Whether the value is valid.
     */
    static boolean validIncomeValue(double value) {
        if (value <= 0) {
            return false;
        }
        // Ensures there are no more than 2 decimal places.
        String[] splitter = Double.toString(value).split("\\.");
        return splitter[1].length() <= 2;
    }
}
