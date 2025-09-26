package org.example.chessvalidator;

import java.util.List;

/**
 * @author amanjain
 **/
public class Queen extends Piece {
    Queen(String color){
        super("Q", color, false);
    }

    @Override
    boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos) {
        return false;
    }
}
