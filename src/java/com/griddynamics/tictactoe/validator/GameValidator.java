package com.griddynamics.tictactoe.validator;

import com.griddynamics.tictactoe.board.Board;

public class GameValidator {
    public static boolean validateCellAvailability(int row, int col, Board board) {
        return board.isCellEmpty(row, col);
    }

    public static boolean validateCoordinatesRange(int row, int col) {
        return row <= Board.SIZE && col <= Board.SIZE;
    }
}
