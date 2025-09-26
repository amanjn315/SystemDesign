package org.example.chessvalidator;

import java.util.List;

/**
 * @author amanjain
 **/
public class Bishop extends Piece{
    Bishop(String color){
        super("B", color, false);
    }


    @Override
    boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos) {
        return false;
    }
}
