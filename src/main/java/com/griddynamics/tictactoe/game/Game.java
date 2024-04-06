package com.griddynamics.tictactoe.game;

import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.exceptions.InputSizeException;
import com.griddynamics.tictactoe.messages.InputMessages;
import com.griddynamics.tictactoe.messages.OutputMessages;
import com.griddynamics.tictactoe.player.Player;
import com.griddynamics.tictactoe.validator.InputValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Getter
@Slf4j
@AllArgsConstructor
public class Game {

  private final Player player1;
  private final Player player2;
  private final Board board;

  public static void main(String[] args) {
    start();
  }

  public static void start() {
    log.info(OutputMessages.ASK_FOR_COMMAND);
    Scanner scanner = new Scanner(System.in);
    String command = scanner.nextLine();

    if (!InputValidator.validateInputCommand(command)) {
      log.error(OutputMessages.BAD_PARAMETERS);
      start();
      return;
    }

    String[] commandParts = command.split(InputMessages.WHITESPACE);
    if (commandParts[0].equals(InputMessages.EXIT)) {
      return;
    }

    log.info(OutputMessages.ASK_FOR_BOARD_SIZE);
    int size = scanner.nextInt();
    scanner.nextLine();

    if (!InputValidator.validateInputSize(size)) {
      throw new InputSizeException();
    }

    Game game =
        new GameBuilder()
            .setPlayer1(commandParts[1], GameConstants.X_SIGN)
            .setPlayer2(commandParts[2], GameConstants.O_SIGN)
            .setSize(size)
            .build();
    game.play();
  }

  public void play() {
    board.printBoard();
    while (!board.isDraw()
        && !board.isWin(GameConstants.X_SIGN)
        && !board.isWin(GameConstants.O_SIGN)) {
      player1.makeMove(board);
      board.printBoard();
      if (!board.isDraw()
          && !board.isWin(GameConstants.X_SIGN)
          && !board.isWin(GameConstants.O_SIGN)) {
        player2.makeMove(board);
        board.printBoard();
      }
    }
    log.info(Board.checkGameStatus(board));
  }
}
