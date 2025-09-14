package org.example.play2048;

import java.util.Scanner;

/**
 * @author amanjain
 **/
public class BoardService {
    public static void main(String[] args){
        Board board = new Board();
        board.displayBoard();
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Enter move: 0 (Left), 1 (Right), 2 (Up), 3 (Down), or -1 to Exit");
            int input = sc.nextInt();
            if (input == -1) {
                System.out.println("Thanks for playing!");
                break; // Exit the loop
            }
            switch (input){
                case 0:
                    board.slideLeft();
                    break;
                case 1:
                    board.slideRight();
                    break;
                case 2:
                    board.slideUp();
                    break;
                case 3:
                    board.slideDown();
                    break;
                default:
                    System.out.println("Invalid input. Please try again.");
                    continue;
            }
            board.displayBoard();
        }
        sc.close();
    }
}
