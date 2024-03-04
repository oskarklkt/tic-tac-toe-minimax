package com.griddynamics.tictactoe.Player;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.Game.GameConstants;
import com.griddynamics.tictactoe.InputExceptions.InputAttemptsException;
import com.griddynamics.tictactoe.OutputMessages;
import com.griddynamics.tictactoe.Validator.GameValidator;
import com.griddynamics.tictactoe.Validator.InputValidator;

import java.util.Scanner;

public class HumanPlayer implements Player {

    private static final String whiteSpacesRegex = "\\s+";
    private static final int attemptsToProvideCorrectInput = 3;
    @Override
    public void makeMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(OutputMessages.ASK_FOR_COORDINATES);
        int attemptCount = 0;

        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split(whiteSpacesRegex);
            if (parts.length != 2 || !InputValidator.validateCoordinateInput(input)) {
                System.out.println(OutputMessages.SHOULD_ENTER_NUMBERS);
                attemptCount++;

            } else {
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                if (!GameValidator.validateCoordinatesRange(row, col)) {
                    System.out.println(OutputMessages.COORDINATES_OUT_OF_BOUNDS);
                    attemptCount++;
                } else if (!GameValidator.validateCellAvailability(row, col, board)) {
                    System.out.println(OutputMessages.CELL_OCCUPIED);
                    attemptCount++;
                } else {
                    board.setCellStatus(row, col, GameConstants.X_SIGN);
                    break;
                }
            }
            if (attemptCount >= attemptsToProvideCorrectInput) {
                throw new InputAttemptsException();
            } else {
                System.out.print(OutputMessages.ASK_FOR_COORDINATES);
            }
        }
    }
}
