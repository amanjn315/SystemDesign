package org.example.tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author amanjain
 **/
public class BoardService {
    public static void main(String[] args){
        Board board = new Board();
        List<User> users = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        users.add(new User(0, sc.next().charAt(0), sc.next()));
        users.add(new User(1, sc.next().charAt(0), sc.next()));
        sc.nextLine();

        int currentPlayer = 0;
        while(true){
            String s = sc.nextLine();
            if(s.equals("exit")) {
                System.out.println("Exiting game.");
                return;
            }
            String[] sArr = s.split(" ");

            if (sArr.length != 2) {
                System.out.println("Invalid input format. Please enter two numbers separated by a space.");
                continue;
            }

            int x = Integer.parseInt(sArr[0]) - 1;
            int y = Integer.parseInt(sArr[1]) - 1;

            if(!board.isMoveValid(x, y)){
                continue;
            }

            if(board.addMove(x, y, users.get(currentPlayer))){
                return;
            }

            currentPlayer++;
            currentPlayer %= 2;
        }
    }
}
