package org.example.chessvalidator;

import java.util.List;

/**
 * @author amanjain
 **/
public class King extends Piece {
    King(String color){
        super("K", color, false);
    }

    @Override
    boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos) {
        return false;
    }
}
