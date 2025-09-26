package org.example.chessvalidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author amanjain
 **/
public class Board {
    List<List<Piece>> board = new ArrayList<>();

    Board(){
        // Initialise the board
        for(int i = 0; i < 8; i++){
            List<Piece> row = new ArrayList<>(Collections.nCopies(8, null));
            board.add(row);
        }

        // Initialise for White pieces
        board.get(0).add(0, new Rook("W"));
        board.get(0).add(1, new Knight("W"));
        board.get(0).add(2, new Bishop("W"));
        board.get(0).add(3, new Queen("W"));
        board.get(0).add(4, new King("W"));
        board.get(0).add(5, new Bishop("W"));
        board.get(0).add(6, new Knight("W"));
        board.get(0).add(7, new Rook("W"));

        for(int i = 0; i < 8; i++){
            board.get(1).add(i, new Pawn("W"));
        }

        // Initialise for Black pieces
        board.get(7).add(0, new Rook("B"));
        board.get(7).add(1, new Knight("B"));
        board.get(7).add(2, new Bishop("B"));
        board.get(7).add(3, new Queen("B"));
        board.get(7).add(4, new King("B"));
        board.get(7).add(5, new Bishop("B"));
        board.get(7).add(6, new Knight("B"));
        board.get(7).add(7, new Rook("B"));

        for(int i = 0; i < 8; i++){
            board.get(6).add(i, new Pawn("B"));
        }
    }

    void makeMove(String initialPos, String finalPos){
        int[] start = calculateCoordinates(initialPos);
        int row = start[0];
        int col = start[1];
        Piece piece = board.get(row).get(col);
        if(piece == null){
            System.out.println("Invalid Move!");
        } else if(piece.isValidMove(board, initialPos, finalPos)){
            int[] end = calculateCoordinates(finalPos);
            board.get(end[0]).set(end[1], board.get(row).get(col));
            board.get(row).set(col, null);
            printBoard();
        } else {
            System.out.println("Invalid Move");
        }
    }

    void printBoard(){
        for(int i = 7; i >= 0; i--){
            for(int j = 0; j < 8; j++){
                if(board.get(i).get(j) != null){
                    System.out.printf("%s ", board.get(i).get(j).toString());
                } else {
                    System.out.print("-- ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    int[] calculateCoordinates(String pos){

        int col = pos.charAt(0) - 'a';
        int row = pos.charAt(1) - '1';
        return new int[]{row, col};
    }
}
