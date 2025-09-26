package org.example.chessvalidator;

import java.util.Arrays;
import java.util.List;

/**
 * @author amanjain
 **/
public abstract class Piece {
    String code;
    boolean canLeap = false;
    String color;

    Piece(String code, String color, boolean canLeap){
        this.code = code;
        this.color = color;
        this.canLeap = canLeap;
    }

    abstract boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos);

    int[] calculateCoordinates(String pos){

        int col = pos.charAt(0) - 'a';
        int row = pos.charAt(1) - '1';
        return new int[]{row, col};
    }

    @Override
    public String toString(){
        return this.color + this.code;
    }
}
