import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BudgetTracker {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Step 1: Prompt the user for total income
        System.out.print("Enter your total income: ");
        double totalIncome = input.nextDouble();
        input.nextLine(); // Clear the newline character

        // Step 2: Initialize variables for expenses and categories
        ArrayList<HashMap<String, Double>> expenses = new ArrayList<>();
        String[] categories = {"Food", "Utilities", "Entertainment"};

        // Step 3: Loop to input expenses
        while (true) {
            System.out.print("Enter expense amount (or type 'done' to finish): ");
            String expenseInput = input.nextLine();

            // Check if user is done
            if (expenseInput.equalsIgnoreCase("done")) {
                break;
            }

            try {
                double expenseAmount = Double.parseDouble(expenseInput);

                // Prompt for category and validate
                System.out.print("Enter expense category (Food, Utilities, Entertainment): ");
                String category = input.nextLine();

                // Validate category
                boolean validCategory = false;
                for (String valid : categories) {
                    if (valid.equalsIgnoreCase(category)) {
                        validCategory = true;
                        break;
                    }
                }

                if (!validCategory) {
                    System.out.println("Invalid category. Try again.");
                    continue;
                }

                // Add expense to list
                HashMap<String, Double> expense = new HashMap<>();
                expense.put(category, expenseAmount);
                expenses.add(expense);

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a numeric value.");
            }
        }

        // Step 4: Calculate total expenses and remaining balance
        double totalExpenses = 0;
        HashMap<String, Double> categoryTotals = new HashMap<>();

        for (HashMap<String, Double> expense : expenses) {
            for (String category : expense.keySet()) {
                double amount = expense.get(category);
                totalExpenses += amount;

                // Sum totals by category
                categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + amount);
            }
        }

        double remainingBalance = totalIncome - totalExpenses;

        // Step 5: Display summary
        System.out.println("\nExpense Summary:");
        for (String category : categoryTotals.keySet()) {
            System.out.printf("%s: $%.2f\n", category, categoryTotals.get(category));
        }

        System.out.printf("Total Expenses: $%.2f\n", totalExpenses);
        System.out.printf("Remaining Balance: $%.2f\n", remainingBalance);

        // Step 6: Check for overspending
        if (remainingBalance < 0) {
            System.out.println("Warning: You are overspending!");
        }

        input.close();
    }
}