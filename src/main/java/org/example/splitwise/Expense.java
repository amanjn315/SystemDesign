package org.example.splitwise;

import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class Expense {
    String paidByUserId;
    double totalAmount;
    List<String> participants;
    ExpenseType type;
    Map<String, Double> shares;

    public Expense(String paidByUserId, double totalAmount, List<String> participants, ExpenseType type, Map<String, Double> shares) {
        this.paidByUserId = paidByUserId;
        this.totalAmount = totalAmount;
        this.participants = participants;
        this.type = type;
        this.shares = shares;
    }

    public String getPaidByUserId() {
        return paidByUserId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public List<String> getParticipants() {
        return participants;
    }

    public ExpenseType getType() {
        return type;
    }

    public Map<String, Double> getShares() {
        return shares;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "paidByUserId='" + paidByUserId + '\'' +
                ", totalAmount=" + totalAmount +
                ", type=" + type +
                ", participants=" + participants +
                ", shares=" + shares +
                '}';
    }
}
