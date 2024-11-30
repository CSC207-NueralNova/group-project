package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;
import org.springframework.stereotype.Component;
import use_case.enter_expense.EnterExpenseUserDataAccessInterface;

import java.util.concurrent.ExecutionException;

@Component
public class EnterExpenseUserDataAccess implements EnterExpenseUserDataAccessInterface {

    private final Firestore firestore;
    private final MonthlySpendingFactory monthlySpendingFactory;

    public EnterExpenseUserDataAccess(Firestore firestore, MonthlySpendingFactory monthlySpendingFactory) {
        this.firestore = firestore;
        this.monthlySpendingFactory = monthlySpendingFactory;
    }

    @Override
    public String getCurrentUsername() {
        return "null feature";
    }

    @Override
    public boolean existsMonthlySpendingByUsernameAndDate(String username, String date) {
        try {
            DocumentSnapshot document = firestore.collection("users")
                    .document(username)
                    .collection("monthlySpending")
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
    public MonthlySpending getMonthlySpendingByUsernameAndDate(String username, String date) {
        try {
            DocumentSnapshot document = firestore.collection("users")
                    .document(username)
                    .collection("monthlySpending")
                    .document(date)
                    .get()
                    .get();

            if (document.exists()) {
                // Assuming you have a DTO to map Firebase document fields to MonthlySpending
                return document.toObject(MonthlySpending.class);
            } else {
                // If no document exists, create a new instance using the factory
                return monthlySpendingFactory.create(date);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return monthlySpendingFactory.create(date); // Return a default instance on error
        }
    }

    @Override
    public void writeMonthlySpending(String username, MonthlySpending monthlySpending) {
        try {
            ApiFuture<WriteResult> writeResult = firestore.collection("users")
                    .document(username)
                    .collection("monthlySpending")
                    .document(monthlySpending.getDate())
                    .set(monthlySpending);

            System.out.println("Write timestamp: " + writeResult.get().getUpdateTime());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.out.println("Failed to write MonthlySpending to Firestore.");
        }
    }
}
