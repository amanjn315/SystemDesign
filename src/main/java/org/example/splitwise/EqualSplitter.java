package org.example.splitwise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class EqualSplitter implements Splitter {
    @Override
    public Map<String, Double> calculateShares(double totalAmount, List<String> participants, List<Double> values){
        int numberOfParticipants = participants.size();
        double baseShare = Math.floor(totalAmount * 100 / numberOfParticipants) / 100.0;
        double remainder = totalAmount - (baseShare * numberOfParticipants);

        Map<String, Double> shares = new HashMap<>();
        for(String userId : participants){
            shares.put(userId, baseShare);
        }

        int centRemainder = (int) Math.round(remainder * 100);
        for(int i = 0; i < centRemainder; i++){
            String userId = participants.get(i);
            shares.put(userId, shares.get(userId) + 0.01);
        }

        return shares;
    }
}
