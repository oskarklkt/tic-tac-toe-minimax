package com.griddynamics.tictactoe.Validator;

import com.griddynamics.tictactoe.Board;

public class GameValidator {
    public static boolean validateCellAvailability(int row, int col, Board board) {
        return board.isCellEmpty(row - 1, col - 1);
    }

    public static boolean validateCoordinatesRange(int row, int col) {
        return row <= Board.SIZE && col <= Board.SIZE;
    }
}
