package org.example.splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class PercentSplitter implements Splitter {
    @Override
    public Map<String, Double> calculateShares(double totalAmount, List<String> participants, List<Double> values) {
        if (participants.size() != values.size()) {
            throw new IllegalArgumentException("Number of participants and percentages do not match.");
        }

        double sumOfValues = values.stream().mapToDouble(Double::doubleValue).sum();

        // Use a small tolerance for floating-point comparison
        if (Math.abs(sumOfValues - 100) > 0.01) {
            throw new IllegalArgumentException("Sum of percentages does not equal 100.");
        }

        Map<String, Double> shares = new HashMap<>();
        for (int i = 0; i < participants.size(); i++) {
            double share = (values.get(i) * totalAmount) / 100.00;
            shares.put(participants.get(i), values.get(i));
        }

        return shares;
    }
}
