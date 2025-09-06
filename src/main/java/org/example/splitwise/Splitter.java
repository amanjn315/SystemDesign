package org.example.splitwise;

import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public interface Splitter {
    Map<String, Double> calculateShares(double totalAmount, List<String> participants, List<Double> values);
}
