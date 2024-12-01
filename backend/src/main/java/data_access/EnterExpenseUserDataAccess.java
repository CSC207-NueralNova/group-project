package data_access;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import entity.item_spending.ItemSpending;
import entity.monthly_spending.CommonMonthlySpending;
import entity.monthly_spending.MonthlySpending;
import entity.monthly_spending.MonthlySpendingFactory;
import org.springframework.stereotype.Component;
import use_case.enter_expense.EnterExpenseUserDataAccessInterface;
import com.google.cloud.firestore.FieldValue;
import use_case.enter_recurrent_expense.EnterRecurrentExpenseUserDataAccessInterface;


import java.util.HashMap;
import java.util.Map;

import java.util.concurrent.ExecutionException;

@Component
public class EnterExpenseUserDataAccess implements EnterExpenseUserDataAccessInterface,
                                                    EnterRecurrentExpenseUserDataAccessInterface {

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

            System.out.println("Firestore Document Data: " + document.getData());

            if (document.exists()) {
                // Assuming you have a DTO to map Firebase document fields to MonthlySpending
                CommonMonthlySpending monthlySpending = document.toObject(CommonMonthlySpending.class);
                monthlySpending.setDate(date);
                return monthlySpending;
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
            System.out.println("Trying to add monthly spending dated " + monthlySpending.getDate() + " with items "
                    + monthlySpending.getSpending() + " to user " + username);
            for (ItemSpending newItem : monthlySpending.getSpending()) {
                // Convert the new item to a Map
                Map<String, Object> itemData = new HashMap<>();
                itemData.put("value", newItem.getValue());
                itemData.put("category", newItem.getCategory());

                // Append the new item to the spending array
                firestore.collection("users")
                        .document(username)
                        .collection("monthlySpending")
                        .document(monthlySpending.getDate())
                        .set(Map.of("spending", FieldValue.arrayUnion(itemData)), SetOptions.merge());
            }
            System.out.println("Successfully appended new items to Firestore.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to append items to Firestore.");
        }
    }

}
