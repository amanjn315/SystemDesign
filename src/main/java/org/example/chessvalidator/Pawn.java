package org.example.chessvalidator;

import java.util.List;
import java.util.Objects;

/**
 * @author amanjain
 **/
public class Pawn extends Piece {
    Pawn(String color){
        super("P", color, false);
    }

    @Override
    boolean isValidMove(List<List<Piece>> board, String initialPos, String finalPos) {
        int direction = (Objects.equals(this.color, "W") ? 1 : -1);
        int startRank = (Objects.equals(this.color, "W") ? 1: 6);

        int[] start = calculateCoordinates(initialPos);
        int[] end   = calculateCoordinates(finalPos);

        int startRow = start[0];
        int startCol = start[1];
        int endRow = end[0];
        int endCol = end[1];

        int rowDiff = endRow - startRow;
        int colDiff = endCol - startCol;

        Piece destinationPiece = board.get(endRow).get(endCol);

        // 3. Handle standard forward move (one square)
        if (colDiff == 0 && rowDiff == direction && destinationPiece == null) {
            return true;
        }

        // Handle initial double-square move
        if(startRow == startRank && colDiff == 0 && rowDiff == 2 * direction && destinationPiece == null){
            Piece intermediatePiece = board.get(startRow + direction).get(startCol);
            return intermediatePiece == null;
        }

        // Handle capture move (diagonal)
        if (Math.abs(colDiff) == 1 && rowDiff == direction) {
            // Destination square must contain an opponent's piece.
            return destinationPiece != null && !Objects.equals(destinationPiece.color, this.color);
        }

        return false;
    }
}
