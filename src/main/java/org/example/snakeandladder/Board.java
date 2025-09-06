package org.example.snakeandladder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author amanjain
 **/
public class Board {
    private Map<Integer, Snake> snakes;
    private Map<Integer, Ladder> ladders;
    private List<Player> players;
    private Map<Player, Integer> currentPos;

    public Board(Map<Integer, Snake> snakes, Map<Integer, Ladder> ladders, List<Player> players) {
        this.snakes = snakes;
        this.ladders = ladders;
        this.players = players;
        this.currentPos = new HashMap<>();
        for(Player player : players){
            currentPos.put(player, 0);
        }
    }

    boolean movePlayer (int id, int value){
        if(id >= players.size()){
            throw new IllegalArgumentException("Invalid player id passed.");
        }
        Player player = players.get(id);
        int currPos = currentPos.get(player);
        int finalPos = currPos + value;

        if(finalPos > 100) {
            finalPos = currPos;
        }
        while(snakes.containsKey(finalPos) || ladders.containsKey(finalPos)){
            if(snakes.containsKey(finalPos)){
                finalPos = snakes.get(finalPos).tail;
            } else if (ladders.containsKey(finalPos)){
                finalPos = ladders.get(finalPos).end;
            }
        }

        this.currentPos.put(player, finalPos);
        System.out.printf("%s rolled a %d and moved from %d to %d\n", player.getName(), value, currPos, finalPos);
        if(finalPos == 100) {
            System.out.printf("%s wins the game", player.getName());
            return true;
        }
        return false;
    }
}
