package com.griddynamics.tictactoe.board;

import com.griddynamics.tictactoe.game.GameConstants;
import com.griddynamics.tictactoe.messages.OutputMessages;
import com.griddynamics.tictactoe.validator.GameValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Board {
  private char[][] board;
  public static int SIZE;

  public Board() {
    init();
  }

  public void init() {
    this.board = new char[SIZE][SIZE];
    for (int row = 0; row < SIZE; row++) {
      for (int col = 0; col < SIZE; col++) {
        this.board[row][col] = GameConstants.EMPTY_SIGN;
      }
    }
  }

  public void setCellStatus(int row, int col, char symbol) {
    board[row - 1][col - 1] = symbol;
  }

  public boolean isCellEmpty(int row, int col) {
    return board[row - 1][col - 1] == GameConstants.EMPTY_SIGN;
  }

  public boolean isBoardFullyOccupied() {
    for (int row = 1; row <= SIZE; row++) {
      for (int col = 1; col <= SIZE; col++) {
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

  public boolean isWin(char symbol) {
    return isWinRow(symbol)
        || isWinColumn(symbol)
        || isWinDiagonal(symbol)
        || isWinAntiDiagonal(symbol);
  }

  private boolean isWinRow(char symbol) {
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

  private boolean isWinColumn(char symbol) {
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

  private boolean isWinDiagonal(char symbol) {
    for (int i = 0; i < Board.SIZE; i++) {
      if (board[i][i] != symbol) {
        return false;
      }
    }
    return true;
  }

  private boolean isWinAntiDiagonal(char symbol) {
    for (int i = 0; i < Board.SIZE; i++) {
      if (board[i][Board.SIZE - i - 1] != symbol) {
        return false;
      }
    }
    return true;
  }

  public boolean isDraw() {
    return isBoardFullyOccupied() && !isWin(GameConstants.X_SIGN) && !isWin(GameConstants.O_SIGN);
  }

  public char getCellStatus(int row, int col) {
    if (GameValidator.validateCoordinatesRange(row, col)) {
      return this.board[row][col];
    } else {throw new IndexOutOfBoundsException(OutputMessages.INDEX_OUT_OF_BOUNDS);}
  }

  public static String checkGameStatus(Board board) {
    if (board.isWin(GameConstants.X_SIGN)) {
      return OutputMessages.X_WINS;
    } else if (board.isWin(GameConstants.O_SIGN)) {
      return OutputMessages.O_WINS;
    } else if (board.isDraw()) {
      return OutputMessages.DRAW;
    } else {
      return OutputMessages.GAME_NOT_FINISHED;
    }
  }

  public void clearCell(int row, int col) {
    board[row][col] = GameConstants.EMPTY_SIGN;
  }
}
