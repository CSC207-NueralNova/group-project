package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_income.MonthlyIncomeFactory;
import org.springframework.stereotype.Component;
import use_case.enter_income.EnterIncomeUserDataAccessInterface;

import java.util.concurrent.ExecutionException;

@Component
public class EnterIncomeUserDataAccess implements EnterIncomeUserDataAccessInterface {

    private final Firestore firestore;
    private final MonthlyIncomeFactory monthlyIncomeFactory;

    public EnterIncomeUserDataAccess(Firestore firestore, MonthlyIncomeFactory monthlyIncomeFactory) {
        this.firestore = firestore;
        this.monthlyIncomeFactory = monthlyIncomeFactory;
    }

    @Override
    public String getCurrentUsername() {
        // Placeholder implementation
        return "null feature";
    }

    @Override
    public boolean existsMonthlyIncomeByUsernameAndDate(String username, String date) {
        try {
            DocumentSnapshot document = firestore.collection("users")
                    .document(username)
                    .collection("monthlyIncome") // Subcollection for incomes
                    .document(date)
                    .get()
                    .get();
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false; // Default to false on error
        }
    }

    @Override
    public MonthlyIncome getMonthlyIncomeByUsernameAndDate(String username, String date) {
        try {
            DocumentSnapshot document = firestore.collection("users")
                    .document(username)
                    .collection("monthlyIncome")
                    .document(date)
                    .get()
                    .get();

            if (document.exists()) {
                // Convert Firebase document to MonthlyIncome
                return document.toObject(MonthlyIncome.class);
            } else {
                // Use factory to create a new instance if no document exists
                return monthlyIncomeFactory.create(date);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return monthlyIncomeFactory.create(date); // Return default instance on error
        }
    }

    @Override
    public void writeMonthlyIncome(String username, MonthlyIncome monthlyIncome) {
        try {
            System.out.println("Saving MonthlyIncome: " + monthlyIncome.getDate());
            System.out.println("Items: " + monthlyIncome); // Debug log to verify items

            ApiFuture<WriteResult> writeResult = firestore.collection("users")
                    .document(username)
                    .collection("monthlyIncome")
                    .document(monthlyIncome.getDate())
                    .set(monthlyIncome);

            System.out.println("Write timestamp: " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println("Failed to write MonthlyIncome to Firestore.");
        }
    }
}
