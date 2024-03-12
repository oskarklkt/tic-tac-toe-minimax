package com.griddynamics.tictactoe.player;

import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.messages.OutputMessages;
import com.griddynamics.tictactoe.validator.GameValidator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

import static com.griddynamics.tictactoe.game.GameConstants.*;

@Slf4j
public class ComputerPlayer implements Player {

  private final Difficulty difficultyLevel;
  @Getter
  public final char sign;

  @Getter
  public final char oppositeSign;

  public ComputerPlayer(Difficulty difficultyLevel, char sign) {
    this.difficultyLevel = difficultyLevel;
    this.sign = sign;
    this.oppositeSign = sign == X_SIGN ? O_SIGN : X_SIGN;
  }

  public Difficulty getDifficulty() {
    return difficultyLevel;
  }

  @Override
  public void makeMove(Board board) {
    if (this.difficultyLevel == Difficulty.EASY) {
      easyMove(board);
    } else if (this.difficultyLevel == Difficulty.MEDIUM) {
      if (!isPossibleMediumMove(board)) {
        randomMove(board);
      }
    } else if (this.difficultyLevel == Difficulty.HARD) {
      hardMove(board);
    }
  }

  public void easyMove(Board board) {
    log.info(OutputMessages.EASY_MOVE);
    randomMove(board);
  }

  private void randomMove(Board board) {
    Random random = new Random();
    int row = random.nextInt(Board.SIZE) + 1;
    int col = random.nextInt(Board.SIZE) + 1;
    if (GameValidator.validateCoordinatesRange(row, col)
        && GameValidator.validateCellAvailability(row, col, board)) {
      board.setCellStatus(row, col, sign);
    } else {
      randomMove(board);
    }
  }

  private boolean isPossibleMoveColumnOrRow(
      Board board, char playerSign, int index, boolean checkRow) {
    int count = 0;
    for (int i = 0; i < Board.SIZE; i++) {
      char cellStatus = checkRow ? board.getCellStatus(index, i) : board.getCellStatus(i, index);
      if (cellStatus == playerSign) {
        count++;
      } else if (cellStatus != EMPTY_SIGN) {
        return false;
      }
    }

    if (count == Board.SIZE - 1) {
      for (int i = 0; i < Board.SIZE; i++) {
        char cellStatus = checkRow ? board.getCellStatus(index, i) : board.getCellStatus(i, index);
        if (cellStatus == EMPTY_SIGN) {
          if (checkRow) {
            board.setCellStatus(index + 1, i + 1, sign);
          } else {
            board.setCellStatus(i + 1, index + 1, sign);
          }
          return true;
        }
      }
    }
    return false;
  }

  private boolean isPossibleMoveMainDiagonal(Board board, char playerSign) {
    int count = 0;
    for (int i = 0; i < Board.SIZE; i++) {
      if (board.getCellStatus(i, i) == playerSign) {
        count++;
      } else if (board.getCellStatus(i, i) != EMPTY_SIGN) {
        return false;
      }
    }
    if (count == Board.SIZE - 1) {
      for (int i = 0; i < Board.SIZE; i++) {
        if (board.getCellStatus(i, i) == EMPTY_SIGN) {
          board.setCellStatus(i + 1, i + 1, sign);
          return true;
        }
      }
    }
    return false;
  }

  private boolean isPossibleMoveAntiDiagonal(Board board, char playerSign) {
    int count = 0;
    for (int i = 0; i < Board.SIZE; i++) {
      if (board.getCellStatus(i, Board.SIZE - 1 - i) == playerSign) {
        count++;
      } else if (board.getCellStatus(i, Board.SIZE - 1 - i) != EMPTY_SIGN) {
        return false;
      }
    }
    if (count == Board.SIZE - 1) {
      for (int i = 0; i < Board.SIZE; i++) {
        if (board.getCellStatus(i, Board.SIZE - 1 - i) == EMPTY_SIGN) {
          board.setCellStatus(i + 1, Board.SIZE - i, sign);
          return true;
        }
      }
    }
    return false;
  }

  private boolean isPossibleWin(Board board) {
    for (int i = 0; i < Board.SIZE; i++) {
      if (isPossibleMoveColumnOrRow(board, sign, i, true)
          || isPossibleMoveColumnOrRow(board, sign, i, false)) {
        return true;
      }
    }
    return isPossibleMoveMainDiagonal(board, sign) || isPossibleMoveAntiDiagonal(board, sign);
  }

  private boolean isPossibleBlock(Board board) {
    for (int i = 0; i < Board.SIZE; i++) {
      if (isPossibleMoveColumnOrRow(board, oppositeSign, i, true)
          || isPossibleMoveColumnOrRow(board, oppositeSign, i, false)) {
        return true;
      }
    }
    return isPossibleMoveMainDiagonal(board, oppositeSign)
        || isPossibleMoveAntiDiagonal(board, oppositeSign);
  }

  private boolean isPossibleMediumMove(Board board) {
    System.out.println(OutputMessages.MEDIUM_MOVE);
    return isPossibleWin(board) || isPossibleBlock(board);
  }

  public int minimax(Board board, boolean isMaximizing, int alpha, int beta) {
    if (board.isWin(sign)) {
      return 1;
    } else if (board.isWin(oppositeSign)) {
      return -1;
    } else if (board.isDraw()) {
      return 0;
    }

    int bestScore;
    if (isMaximizing) {
      bestScore = Integer.MIN_VALUE;
      outer:
      for (int i = 0; i < Board.SIZE; i++) {
        for (int j = 0; j < Board.SIZE; j++) {
          if (board.getCellStatus(i, j) == EMPTY_SIGN) {
            board.setCellStatus(i + 1, j + 1, sign);
            int score = minimax(board, false, alpha, beta);
            board.clearCell(i, j);
            bestScore = Math.max(score, bestScore);
            alpha = Math.max(bestScore, alpha);
            if (beta <= alpha) {
              break outer;
            }
          }
        }
      }
    } else {
      bestScore = Integer.MAX_VALUE;
      outer:
      for (int i = 0; i < Board.SIZE; i++) {
        for (int j = 0; j < Board.SIZE; j++) {
          if (board.getCellStatus(i, j) == EMPTY_SIGN) {
            board.setCellStatus(i + 1, j + 1, oppositeSign);
            int score = minimax(board, true, alpha, beta);
            board.clearCell(i, j);
            bestScore = Math.min(score, bestScore);
            beta = Math.min(bestScore, beta);
            if (beta <= alpha) {
              break outer;
            }
          }
        }
      }
    }
    return bestScore;
  }

  public void hardMove(Board board) {
    System.out.println(OutputMessages.HARD_MOVE);
    int bestScore = Integer.MIN_VALUE;
    int[] move = new int[2];
    for (int i = 0; i < Board.SIZE; i++) {
      for (int j = 0; j < Board.SIZE; j++) {
        if (board.getCellStatus(i, j) == EMPTY_SIGN) {
          board.setCellStatus(i + 1, j + 1, sign);
          int score = minimax(board, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
          board.clearCell(i, j);
          if (score > bestScore) {
            bestScore = score;
            move[0] = i + 1;
            move[1] = j + 1;
          }
        }
      }
    }
    board.setCellStatus(move[0], move[1], sign);
  }

}
