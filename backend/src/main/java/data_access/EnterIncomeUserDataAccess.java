package data_access;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.SetOptions;
import entity.monthly_income.MonthlyIncome;
import entity.item_income.ItemIncome;
import entity.monthly_income.MonthlyIncomeFactory;
import org.springframework.stereotype.Component;
import use_case.enter_income.EnterIncomeUserDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

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
        return "mockUsername"; // Mock or replace with actual implementation
    }

    @Override
    public boolean existsMonthlyIncomeByUsernameAndDate(String username, String date) {
        try {
            return firestore.collection("users")
                    .document(username)
                    .collection("monthlyIncome")
                    .document(date)
                    .get()
                    .get()
                    .exists();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
                return document.toObject(MonthlyIncome.class);
            } else {
                return monthlyIncomeFactory.create(date); // Use factory to create a default instance
            }
        } catch (Exception e) {
            e.printStackTrace();
            return monthlyIncomeFactory.create(date);
        }
    }

    @Override
    public void writeMonthlyIncome(String username, MonthlyIncome monthlyIncome) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("'username' must not be null or empty");
        }
        if (monthlyIncome == null || monthlyIncome.getDate() == null || monthlyIncome.getDate().isEmpty()) {
            throw new IllegalArgumentException("'date' must not be null or empty");
        }

        try {
            for (ItemIncome newItem : monthlyIncome.getIncome()) {
                Map<String, Object> itemData = new HashMap<>();
                itemData.put("value", newItem.getValue());

                firestore.collection("users")
                        .document(username)
                        .collection("monthlyIncome")
                        .document(monthlyIncome.getDate())
                        .set(Map.of("items", FieldValue.arrayUnion(itemData)), SetOptions.merge());
            }
            System.out.println("Successfully appended new income items to Firestore.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to append income items to Firestore.");
        }
    }
}
