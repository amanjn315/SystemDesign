package org.example.chessvalidator;

import java.util.List;

/**
 * @author amanjain
 **/
public class Rook extends Piece {

    Rook(String color){
        super("R", color, false);
    }

    @Override
    boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos) {
        return false;
    }
}
