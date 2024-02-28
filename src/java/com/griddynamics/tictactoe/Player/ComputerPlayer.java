package com.griddynamics.tictactoe.Player;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.Difficulty;
import com.griddynamics.tictactoe.Validator.GameValidator;

import java.util.Random;

public class ComputerPlayer implements Player{

    Difficulty difficultyLevel;

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
        System.out.println("Making move level \"easy\"");
        Random random = new Random();
        int row = random.nextInt(3) + 1;
        int col = random.nextInt(3) + 1;
        if (GameValidator.validateCoordinatesRange(row, col) && GameValidator.validateCellAvailability(row, col, board)) {
            board.setCellStatus(row, col, 'O');
        } else {
            easyMove(board);
        }
    }
}
