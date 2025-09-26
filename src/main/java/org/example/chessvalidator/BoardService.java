package org.example.chessvalidator;

/**
 * @author amanjain
 **/
public class BoardService {
    public static void main(String[] args){
        Board board = new Board();
        System.out.println(board);
        board.printBoard();
        board.makeMove("a2", "a3");
        board.makeMove("a7", "a5");
        board.makeMove("b1", "c3");
    }
}
