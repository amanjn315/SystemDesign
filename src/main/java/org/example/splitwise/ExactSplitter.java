package org.example.splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class ExactSplitter implements Splitter{
    public Map<String, Double> calculateShares(double totalAmount, List<String> participants, List<Double> values){
        if(participants.size() != values.size()){
            throw new IllegalArgumentException("Number of participants and exact amounts do not match");
        }

        double sumOfValues = values.stream().mapToDouble(Double::doubleValue).sum();

        if(Math.abs(sumOfValues - totalAmount) > 0.01){
            throw new IllegalArgumentException("Sum of exact amount does not match the total amount.");
        }

        Map<String, Double> shares = new HashMap<>();
        for(int i = 0; i < participants.size(); i++){
            shares.put(participants.get(i), values.get(i));
        }

        return shares;
    }
}
