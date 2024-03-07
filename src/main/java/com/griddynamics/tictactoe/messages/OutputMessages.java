package com.griddynamics.tictactoe.messages;

import com.griddynamics.tictactoe.board.Board;

public class OutputMessages {

  public static final String EASY_MOVE = "Making move level \"easy\"";
  public static final String MEDIUM_MOVE = "Making move level \"medium\"";

  public static final String HARD_MOVE = "Making move level \"hard\"";
  public static final String ASK_FOR_COORDINATES = "Enter the coordinates: ";
  public static final String SHOULD_ENTER_NUMBERS = "You should enter numbers!";
  public static final String COORDINATES_OUT_OF_BOUNDS =
      "Coordinates should be from 1 to %d!".formatted(Board.SIZE);
  public static final String CELL_OCCUPIED = "This cell is occupied! Choose another one!";
  public static final String X_WINS = "X wins";
  public static final String O_WINS = "O wins";
  public static final String DRAW = "Draw";
  public static final String GAME_NOT_FINISHED = "Game not finished";

  public static final String ASK_FOR_COMMAND = "Input command: ";
  public static final String BAD_PARAMETERS = "Bad parameters";

  public static final String ASK_FOR_BOARD_SIZE = "Input Board's size: ";

  public static final String INDEX_OUT_OF_BOUNDS =
      "Those coordinates are out of bounds for board of size %d".formatted(Board.SIZE);
}
