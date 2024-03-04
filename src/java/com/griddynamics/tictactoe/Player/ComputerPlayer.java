package com.griddynamics.tictactoe.Player;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.Game.GameConstants;
import com.griddynamics.tictactoe.OutputMessages;
import com.griddynamics.tictactoe.Validator.GameValidator;

import java.util.Random;

public class ComputerPlayer implements Player {

    private final Difficulty difficultyLevel;

    public ComputerPlayer(Difficulty difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void makeMove(Board board) {
        if (this.difficultyLevel == Difficulty.EASY) {
            easyMove(board);
        }
    }

    public void easyMove(Board board) {
        System.out.println(OutputMessages.EASY_MOVE);
        Random random = new Random();
        int row = random.nextInt(Board.SIZE) + 1;
        int col = random.nextInt(Board.SIZE) + 1;
        if (GameValidator.validateCoordinatesRange(row, col) && GameValidator.validateCellAvailability(row, col, board)) {
            board.setCellStatus(row, col, GameConstants.O_SIGN);
        } else {
            easyMove(board);
        }
    }
}
