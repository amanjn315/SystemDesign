package org.example.snakeandladder;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author amanjain
 **/
public class BoardService {
    public static void main(String args[]){
        Board board;

        Scanner scanner = new Scanner(System.in);
        int numOfSnakes = scanner.nextInt();
        Map<Integer, Snake> snakes = new HashMap<>();
        for(int i = 0; i < numOfSnakes; i++){
            int head = scanner.nextInt();
            int tail = scanner.nextInt();
            Snake snake = new Snake(head, tail);
            snakes.put(head, snake);
        }

        int numOfLadder = scanner.nextInt();
        Map<Integer, Ladder> ladders = new HashMap<>();
        for(int i = 0; i < numOfLadder; i++){
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            Ladder ladder = new Ladder(start, end);
            ladders.put(start, ladder);
        }

        int numOfPlayers = scanner.nextInt();
        scanner.nextLine();
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < numOfPlayers; i++){
            String name = scanner.nextLine();
            Player player = new Player(i, name);
            players.add(player);
        }
        scanner.close();

        board = new Board(snakes, ladders, players);

        int currPlayer = 0;
        List<Integer> numOfPlayersInContest = new ArrayList<>();
        for(int i = 0; i < numOfPlayers; i++){
            numOfPlayersInContest.add(i);
        }
        while(true){
            int diceRollOne = ThreadLocalRandom.current().nextInt(1, 7);
            int diceRollTwo = ThreadLocalRandom.current().nextInt(1, 7);
            if(board.movePlayer(numOfPlayersInContest.get(currPlayer), diceRollOne + diceRollTwo)){
                numOfPlayersInContest.remove(currPlayer);
                if(numOfPlayersInContest.size() == 1) break;
            }

            currPlayer += 1;
            currPlayer %= numOfPlayersInContest.size();
        }
    }
}
