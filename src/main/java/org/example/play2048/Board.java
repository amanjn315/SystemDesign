package org.example.play2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author amanjain
 **/
public class Board {
    List<List<Integer>> board;

    public Board(){
        board = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                row.add(0);
            }
            board.add(row);
        }

        // Add the initial two tiles using your robust method
        insertTwoAtRandomPosition();
        insertTwoAtRandomPosition();
    }

    void slideRight(){
        int size = board.size();
        for(int i = 0; i < size; i++){
            slideRowRight(i);
        }
        insertTwoAtRandomPosition();
    }

    void slideLeft(){
        int size = board.size();
        for(int i = 0; i < size; i++){
            slideRowLeft(i);
        }
        insertTwoAtRandomPosition();
    }

    void slideUp(){
        int size = board.size();
        for(int i = 0; i < size; i++){
            slideColumnUp(i);
        }
        insertTwoAtRandomPosition();
    }

    void slideDown(){
        int size = board.size();
        for(int i = 0; i < size; i++){
            slideColumnDown(i);
        }
        insertTwoAtRandomPosition();
    }

    void insertTwoAtRandomPosition(){
        List<List<Integer>> emptySpots = new ArrayList<>();
        int size = board.size();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(board.get(i).get(j) == 0){
                    emptySpots.add(List.of(i, j));
                }
            }
        }
        Random random = new Random();
        int randomIdx = random.nextInt(emptySpots.size());
        int x = emptySpots.get(randomIdx).get(0);
        int y = emptySpots.get(randomIdx).get(1);
        board.get(x).set(y, 2);
    }

    private void slideRowLeft(int idx) {
        List<Integer> row = board.get(idx);
        List<Integer> processedRow = processLine(row);
        board.set(idx, processedRow);
    }

    private void slideRowRight(int idx){
        List<Integer> row = board.get(idx);
        List<Integer> reversedRow = reverseList(row);
        List<Integer> processedRow = processLine(reversedRow);
        board.set(idx, reverseList(processedRow));
    }

    private void slideColumnUp(int idx){
        // 1. Extract the column into a temporary list
        List<Integer> column = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            column.add(board.get(i).get(idx));
        }

        // 2. Process the extracted column
        List<Integer> processedColumn = processLine(column);

        // 3. Place the processed column back onto the board
        for (int i = 0; i < 4; i++) {
            board.get(i).set(idx, processedColumn.get(i));
        }
    }

    private void slideColumnDown(int idx){
        // 1. Extract the column
        List<Integer> column = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            column.add(board.get(i).get(idx));
        }

        // 2. Reverse, process, and reverse back
        List<Integer> reversedColumn = reverseList(column);
        List<Integer> processedColumn = processLine(reversedColumn);
        List<Integer> finalColumn = reverseList(processedColumn);

        // 3. Place it back on the board
        for (int i = 0; i < 4; i++) {
            board.get(i).set(idx, finalColumn.get(i));
        }
    }

    // Add this helper for reversing lists
    private <T> List<T> reverseList(List<T> list) {
        List<T> reversed = new ArrayList<>(list);
        java.util.Collections.reverse(reversed);
        return reversed;
    }

    private List<Integer> processLine(List<Integer> line) {
        // 1. Filter out zeros to pack the numbers
        List<Integer> packedLine = new ArrayList<>();
        for (Integer num : line) {
            if (num != 0) {
                packedLine.add(num);
            }
        }

        // 2. Merge adjacent identical numbers
        for (int i = 0; i < packedLine.size() - 1; i++) {
            if (packedLine.get(i).equals(packedLine.get(i + 1))) {
                packedLine.set(i, packedLine.get(i) * 2);
                packedLine.set(i + 1, 0); // Set the second tile to 0
            }
        }

        // 3. Filter out zeros created during the merge
        List<Integer> mergedLine = new ArrayList<>();
        for (Integer num : packedLine) {
            if (num != 0) {
                mergedLine.add(num);
            }
        }

        // 4. Pad with zeros to ensure the line has 4 elements
        while (mergedLine.size() < 4) {
            mergedLine.add(0);
        }

        return mergedLine;
    }

    void displayBoard(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board.get(i).get(j) == 0){
                    System.out.print("- ");
                } else {
                    System.out.printf("%d ", board.get(i).get(j));
                }
            }
            System.out.println();
        }
    }
}
