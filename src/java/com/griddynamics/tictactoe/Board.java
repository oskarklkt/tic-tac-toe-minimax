package com.griddynamics.tictactoe;

import com.griddynamics.tictactoe.Game.GameConstants;

public class Board {
    private char[][] board;
    public static final int SIZE = 3;

    public Board() {
        init();
    }

    public void init() {
        this.board = new char[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                this.board[row][col] = GameConstants.emptySign;
            }
        }
    }

    public void setCellStatus(int row, int col, char symbol) {
        board[row - 1][col - 1] = symbol;
    }

    public boolean isCellEmpty(int row, int col) {
        return board[row][col] == GameConstants.emptySign;
    }

    public boolean isBoardFullyOccupied() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (isCellEmpty(row, col)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < 3 * Board.SIZE; i++) {
            System.out.print('-');
        }
        System.out.println();
        for (char[] row : board) {
            System.out.print("| ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println("|");
        }
        for (int i = 0; i < 3 * Board.SIZE; i++) {
            System.out.print('-');
        }
        System.out.println();
    }

    public boolean checkWin(char symbol) {
        return checkWinRow(symbol) || checkWinColumn(symbol) || checkWinDiagonal(symbol) ||checkWinAntiDiagonal(symbol);
    }

    private boolean checkWinRow(char symbol) {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean winRow = true;
            for (int j = 0; j < Board.SIZE; j++) {
                if (board[i][j] != symbol) {
                    winRow = false;
                    break;
                }
            }
            if (winRow) return true;
        }
        return false;
    }

    private boolean checkWinColumn(char symbol) {
        for (int i = 0; i < Board.SIZE; i++) {
            boolean winCol = true;
            for (int j = 0; j < Board.SIZE; j++) {
                if (board[j][i] != symbol) {
                    winCol = false;
                    break;
                }
            }
            if (winCol) return true;
        }
        return false;
    }

    private boolean checkWinDiagonal(char symbol) {
        for (int i = 0; i < Board.SIZE; i++) {
            if (board[i][i] != symbol) {
                return false;
            }
        }
        return true;
    }

    private boolean checkWinAntiDiagonal(char symbol) {
        for (int i = 0; i < Board.SIZE; i++) {
            if (board[i][Board.SIZE - i - 1] != symbol) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDraw() {
        return isBoardFullyOccupied() && !checkWin(GameConstants.xSign) && !checkWin(GameConstants.oSign);
    }
}