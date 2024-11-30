package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import entity.monthly_income.CommonMonthlyIncome;
import entity.monthly_income.MonthlyIncome;
import entity.monthly_spending.CommonMonthlySpending;
import entity.monthly_spending.MonthlySpending;
import org.springframework.stereotype.Component;
import use_case.see_list.SeeListUserDataAccessInterface;


import java.util.concurrent.ExecutionException;
@Component
public class SeeListUserDataAccess implements SeeListUserDataAccessInterface {

    private final Firestore firestore;

    public SeeListUserDataAccess(Firestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public String getCurrentUsername() {
        return "";
    }

    @Override
    public boolean existsMonthlySpendingByUsernameAndDate(String userId, String date) {
        try {
            System.out.println("Checking existence for spending: User=" + userId + ", Date=" + date);
            DocumentSnapshot document = firestore.collection("users")
                    .document(userId)
                    .collection("monthlySpending")
                    .document(date)
                    .get()
                    .get();
            System.out.println("Document exists: " + document.exists());
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false; // Default to false on error
        }
    }

    @Override
    public MonthlySpending getMonthlySpendingByUsernameAndDate(String userId, String date) {
        try {
            System.out.println("Fetching spending: User=" + userId + ", Date=" + date);
            DocumentSnapshot document = firestore.collection("users")
                    .document(userId)
                    .collection("monthlySpending")
                    .document(date)
                    .get()
                    .get();
            if (document.exists()) {
                System.out.println("Spending data: " + document.getData());
                return document.toObject(CommonMonthlySpending.class); // Explicitly use the concrete class
            } else {
                System.out.println("No spending document found for date: " + date);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean existsMonthlyIncomeByUsernameAndDate(String userId, String date) {
        try {
            System.out.println("Checking existence for income: User=" + userId + ", Date=" + date);
            DocumentSnapshot document = firestore.collection("users")
                    .document(userId)
                    .collection("monthlyIncome")
                    .document(date)
                    .get()
                    .get();
            System.out.println("Document exists: " + document.exists());
            return document.exists();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MonthlyIncome getMonthlyIncomeByUsernameAndDate(String userId, String date) {
        try {
            System.out.println("Fetching income: User=" + userId + ", Date=" + date);
            DocumentSnapshot document = firestore.collection("users")
                    .document(userId)
                    .collection("monthlyIncome")
                    .document(date)
                    .get()
                    .get();
            if (document.exists()) {
                System.out.println("Income data: " + document.getData());
                return document.toObject(CommonMonthlyIncome.class); // Deserialize from Firestore
            } else {
                System.out.println("No income document found for date: " + date);
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
