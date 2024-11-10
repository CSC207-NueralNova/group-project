package entity.monthly_spending;


/**
 *  The representation of a user's spending in a single month.
 */
public interface MonthlySpending {

    /**
     * Returns the month and year in the format MMYY.
     * @return the month and year in the format MMYY.
     */
    String getDate();

    /**
     * Returns the list of spendings.
     * @return the list of spendings.
     */
    ArrayList<Item> getSpending();

    /**
     * Returns the list of recurrent spendings.
     * @return the list of recurrent spendings.
     */
    ArrayList<Item> getRecurrentSpending();

    /**
     * Adds an item to the list of spending.
     */
    void addItem();


}
