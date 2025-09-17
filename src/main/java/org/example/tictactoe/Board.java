package org.example.tictactoe;

/**
 * @author amanjain
 **/
public class Board {
    Character[][] board = new Character[3][3];

    public Board() {
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                board[i][j] = '-';
            }
        }
    }

    boolean isMoveValid(int x, int y){
        if(x < 0 || x >= 3 || y < 0 || y >= 3 || board[x][y] != '-'){
            System.out.println("Invalid Move");
            return false;
        }
        return true;
    }

    boolean addMove(int x, int y, User user){
        board[x][y] = user.getPattern();
        displayBoard();
        // Check for a winning move first
        if (isWinningMove(user)) {
            return true;
        }

        // Then, check for a draw
        if (isBoardFull()) {
            System.out.println("It's a draw!");
            return true;
        }

        return false;
    }

    void displayBoard(){
        System.out.println();
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.printf("%s ", board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false; // Found an empty spot, not a draw
                }
            }
        }
        return true; // All spots are filled, it's a draw
    }

    boolean isWinningMove(User user){
        char pattern = user.getPattern();
        // Horizontal
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == pattern && board[i][1] == pattern && board[i][2] == pattern) {
                System.out.println(user.getName() + " won the game.");
                return true;
            }
        }
        // Vertical
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == pattern && board[1][i] == pattern && board[2][i] == pattern) {
                System.out.println(user.getName() + " won the game.");
                return true;
            }
        }

        // Diagonal
        if (board[0][0] == pattern && board[1][1] == pattern && board[2][2] == pattern) {
            System.out.println(user.getName() + " won the game.");
            return true;
        }

        if (board[2][0] == pattern && board[1][1] == pattern && board[0][2] == pattern) {
            System.out.println(user.getName() + " won the game.");
            return true;
        }
        return false;
    }
}
