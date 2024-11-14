package com.example.connectfour;

public class ConnectFourGame {
    // Member variable for the board (7 rows x 6 columns)
    private int[][] board;

    // Custom constructor to instantiate the board array
    public ConnectFourGame() {
        board = new int[7][6];
        newGame();
    }

    // Method to start a new game by initializing all board elements to zero
    public void newGame() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
    }

    // Method to return the current state of the board as a string
    public String getState() {
        StringBuilder state = new StringBuilder();
        for (int[] row : board) {
            for (int cell : row) {
                state.append(cell);
            }
        }
        return state.toString();
    }
}
