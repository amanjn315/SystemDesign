package org.example.splitwise;

import java.util.Arrays;
import java.util.List;

/**
 * @author amanjain
 **/
public class Main {
    public static void main(String[] args) {
        // Create initial users for the application.
        User user1 = new User("u1", "User1", "u1@example.com", "1234567890");
        User user2 = new User("u2", "User2", "u2@example.com", "9876543210");
        User user3 = new User("u3", "User3", "u3@example.com", "5555555555");
        User user4 = new User("u4", "User4", "u4@example.com", "1111111111");

        List<User> users = Arrays.asList(user1, user2, user3, user4);
        ExpenseManager expenseManager = new ExpenseManager(users);

        System.out.println("Processing: SHOW");
        expenseManager.showBalances();
        System.out.println();

        System.out.println("Processing: SHOW u1");
        expenseManager.showBalances("u1");
        System.out.println();

        System.out.println("Processing: EXPENSE u1 1000 4 u1 u2 u3 u4 EQUAL");
        expenseManager.addExpense("u1", 1000.0, ExpenseType.EQUAL, Arrays.asList("u1", "u2", "u3", "u4"), null);
        System.out.println();

        System.out.println("Processing: SHOW u4");
        expenseManager.showBalances("u4");
        System.out.println();

        System.out.println("Processing: SHOW u1");
        expenseManager.showBalances("u1");
        System.out.println();

        System.out.println("Processing: EXPENSE u1 1250 2 u2 u3 EXACT 370 880");
        expenseManager.addExpense("u1", 1250.0, ExpenseType.EXACT, Arrays.asList("u2", "u3"), Arrays.asList(370.0, 880.0));
        System.out.println();

        System.out.println("Processing: SHOW");
        expenseManager.showBalances();
        System.out.println();

        System.out.println("Processing: EXPENSE u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20");
        expenseManager.addExpense("u4", 1200.0, ExpenseType.PERCENT, Arrays.asList("u1", "u2", "u3", "u4"), Arrays.asList(40.0, 20.0, 20.0, 20.0));
        System.out.println();

        System.out.println("Processing: SHOW u1");
        expenseManager.showBalances("u1");
        System.out.println();

        System.out.println("Processing: SHOW");
        expenseManager.showBalances();
    }
}
