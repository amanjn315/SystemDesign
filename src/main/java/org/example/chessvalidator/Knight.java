package org.example.chessvalidator;

import java.util.List;
import java.util.Objects;

/**
 * @author amanjain
 **/
public class Knight extends Piece {
    Knight(String color){
        super("K", color, true);
    }

    @Override
    boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos) {
        int[] start = calculateCoordinates(initialPos);
        int[] end = calculateCoordinates(finalPos);

        int startRow = start[0];
        int startCol = start[1];
        int endRow = end[0];
        int endCol = end[1];

        int rowDiff = Math.abs(endRow - startRow);
        int colDiff = Math.abs(endCol - startCol);

        // 2. A knight move is an "L" shape: 2 squares in one direction and 1 in the other.
        boolean isLShape = (rowDiff == 2 && colDiff == 1) || (rowDiff == 1 && colDiff == 2);

        if (!isLShape) {
            return false;
        }

        // 3. Check the destination square.
        Piece destinationPiece = board.get(endRow).get(endCol);

        // The destination must be empty or contain an opponent's piece.
        if (destinationPiece == null) {
            return true; // Valid move to an empty square.
        } else {
            return !Objects.equals(destinationPiece.color, this.color); // Valid if it's a capture.
        }
    }
}
