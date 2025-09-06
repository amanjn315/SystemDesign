package org.example.splitwise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class ExpenseManager {
    private final Map<String, User> users;
    private final List<Expense> expenses;
    private final Map<String, Map<String, Double>> balances;

    public ExpenseManager(List<User> users){
        this.users = new HashMap<>();
        for(User user : users){
            this.users.put(user.getUserId(), user);
        }
        this.expenses = new ArrayList<>();
        this.balances = new HashMap<>();
    }

    public void addExpense(String paidByUserId, double totalAmount, ExpenseType type, List<String> participants, List<Double> values) {
        // Validate if all participants exist in the user database
        for (String participantId : participants) {
            if (!users.containsKey(participantId)) {
                System.out.println("Invalid user ID: " + participantId);
                return;
            }
        }

        // Use the appropriate splitter based on the expense type
        Splitter splitter;
        switch (type) {
            case EQUAL:
                splitter = new EqualSplitter();
                break;
            case EXACT:
                splitter = new ExactSplitter();
                break;
            case PERCENT:
                splitter = new PercentSplitter();
                break;
            default:
                System.out.println("Invalid expense type.");
                return;
        }

        Map<String, Double> shares;
        try {
            shares = splitter.calculateShares(totalAmount, participants, values);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            return;
        }

        Expense expense = new Expense(paidByUserId, totalAmount, participants, type, shares);
        expenses.add(expense);

        for(Map.Entry<String, Double> entry : shares.entrySet()){
            String participantid = entry.getKey();
            Double shareAmount = entry.getValue();

            if(!participantid.equals(paidByUserId)){
                updateBalance(participantid, paidByUserId, shareAmount);
            }
        }
    }

    void showBalances(String userId){
        if (!users.containsKey(userId)) {
            System.out.println("User not found.");
            return;
        }

        boolean hasBalances = false;
        if(balances.containsKey(userId)){
            for(Map.Entry<String, Double> entry : balances.get(userId).entrySet()){
                String creditorId = entry.getKey();
                Double amount = entry.getValue();
                if(amount > 0.01){
                    System.out.printf("%s owes %s: %.2f%n", userId, creditorId, amount);
                    hasBalances = true;
                }
            }
        }

        // Show who owes the user
        for (String debtorId : users.keySet()) {
            if (balances.containsKey(debtorId) && balances.get(debtorId).containsKey(userId)) {
                Double amount = balances.get(debtorId).get(userId);
                if (amount > 0.01) {
                    System.out.printf("%s owes %s: %.2f%n", debtorId, userId, amount);
                    hasBalances = true;
                }
            }
        }

        if (!hasBalances) {
            System.out.println("No balances");
        }
    }

    void showBalances(){
        boolean hasBalances = false;
        for (String debtorId : users.keySet()) {
            if (balances.containsKey(debtorId)) {
                for (Map.Entry<String, Double> entry : balances.get(debtorId).entrySet()) {
                    String creditorId = entry.getKey();
                    Double amount = entry.getValue();
                    if (amount > 0.01) { // Check for non-zero balance
                        System.out.printf("%s owes %s: %.2f%n", debtorId, creditorId, amount);
                        hasBalances = true;
                    }
                }
            }
        }
        if (!hasBalances) {
            System.out.println("No balances");
        }
    }

    private void updateBalance(String debtorId, String creditorId, double amount) {
        if(balances.containsKey(creditorId) && balances.get(creditorId).containsKey(debtorId)){
            double existingDebt = balances.get(creditorId).get(debtorId);

            if(existingDebt > amount){
                balances.get(creditorId).put(debtorId, existingDebt - amount);
            } else {
                balances.get(creditorId).remove(debtorId);
                if(amount > existingDebt){
                    balances.putIfAbsent(debtorId, new HashMap<>());
                    balances.get(debtorId).put(creditorId, amount - existingDebt);
                }
            }
        } else {
            balances.putIfAbsent(debtorId, new HashMap<>());
            balances.get(debtorId).merge(creditorId, amount, Double::sum);
        }
    }

}
