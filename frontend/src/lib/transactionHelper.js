// Transform data to transactions format
export function transformDataToTransactions(income, spending) {
    // Map income data
    const userIncome = income.map((income, index) => ({
        id: `income-${index + 1}`, // Generate unique IDs for income
        name: "Income", // Default name for income transactions
        category: "Income", // Default category for income
        date: income.date, // Date from the backend
        amount: income.value, // Positive amount for income
    }));

    // Map spending data
    const userSpending = spending.map((spending, index) => ({
        id: `spending-${index + 1}`, // Generate unique IDs for spending
        name: spending.category || "Expense", // Use category as name or default to "Expense"
        category: spending.category || "Uncategorized", // Ensure a fallback for category
        date: spending.date, // Date from the backend
        amount: -Math.abs(spending.value), // Negative amount for spending
    }));

    // Combine income and spending into a single array
    const transactions = [...userIncome, ...userSpending];

    // Sort by date in ascending order
    transactions.sort((a, b) => new Date(a.date) - new Date(b.date));

    return transactions;
}
