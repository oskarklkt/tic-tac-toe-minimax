package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.messages.OutputMessages;
import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.exceptions.InputAttemptsException;
import com.griddynamics.tictactoe.validator.GameValidator;
import com.griddynamics.tictactoe.validator.InputValidator;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public record HumanPlayer(char sign) implements Player {

  private static final String whiteSpacesRegex = "\\s+";
  private static final int attemptsToProvideCorrectInput = 3;

  @Override
  public char getSign() {
    return sign;
  }

  @Override
  public void makeMove(Board board) {
    Scanner scanner = new Scanner(System.in);
    log.info(OutputMessages.ASK_FOR_COORDINATES);
    int attemptCount = 0;

    while (true) {
      String input = scanner.nextLine();
      String[] parts = input.split(whiteSpacesRegex);
      if (parts.length != 2 || !InputValidator.validateCoordinateInput(input)) {
        log.info(OutputMessages.SHOULD_ENTER_NUMBERS);
        attemptCount++;

      } else {
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);

        if (!GameValidator.validateCoordinatesRange(row, col)) {
          log.info(OutputMessages.COORDINATES_OUT_OF_BOUNDS);
          attemptCount++;
        } else if (!GameValidator.validateCellAvailability(row, col, board)) {
          log.info(OutputMessages.CELL_OCCUPIED);
          attemptCount++;
        } else {
          board.setCellStatus(row, col, sign);
          break;
        }
      }
      if (attemptCount >= attemptsToProvideCorrectInput) {
        throw new InputAttemptsException();
      } else {
        log.info(OutputMessages.ASK_FOR_COORDINATES);
      }
    }
  }
}
