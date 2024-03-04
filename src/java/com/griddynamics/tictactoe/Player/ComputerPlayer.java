package com.griddynamics.tictactoe.Player;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.Messages.OutputMessages;
import com.griddynamics.tictactoe.Validator.GameValidator;

import java.util.Random;

import static com.griddynamics.tictactoe.Game.GameConstants.*;

public class ComputerPlayer implements Player {

    private final Difficulty difficultyLevel;
    public final char sign;

    public final char oppositeSign;

    public ComputerPlayer(Difficulty difficultyLevel, char sign) {
        this.difficultyLevel = difficultyLevel;
        this.sign = sign;
        this.oppositeSign = sign == X_SIGN ? O_SIGN : X_SIGN;
    }

    @Override
    public void makeMove(Board board) {
        if (this.difficultyLevel == Difficulty.EASY) {
            easyMove(board);
        } else if (this.difficultyLevel == Difficulty.MEDIUM) {
            if (!mediumMove(board)) {
                randomMove(board);
            }
        }
    }

    public void easyMove(Board board) {
        System.out.println(OutputMessages.EASY_MOVE);
        randomMove(board);
    }

    private void randomMove(Board board) {
        Random random = new Random();
        int row = random.nextInt(Board.SIZE) + 1;
        int col = random.nextInt(Board.SIZE) + 1;
        if (GameValidator.validateCoordinatesRange(row, col) && GameValidator.validateCellAvailability(row, col, board)) {
            board.setCellStatus(row, col, sign);
        } else {
            randomMove(board);
        }
    }


    private boolean checkRow(Board board, char playerSign, int row) {
        int count = 0;
        for (int col = 0; col < Board.SIZE; col++) {
            if (board.getCellStatus(row, col) == playerSign) {
                count++;
            } else if (board.getCellStatus(row, col) != EMPTY_SIGN) {
                return false;
            }
        }
        if (count == Board.SIZE - 1) {
            for (int col = 0; col < Board.SIZE; col++) {
                if (board.getCellStatus(row, col) == EMPTY_SIGN) {
                    board.setCellStatus(row + 1, col + 1, sign);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkColumn(Board board, char playerSign, int col) {
        int count = 0;
        for (int row = 0; row < Board.SIZE; row++) {
            if (board.getCellStatus(row, col) == playerSign) {
                count++;
            } else if (board.getCellStatus(row, col) != EMPTY_SIGN) {
                return false;
            }
        }
        if (count == Board.SIZE - 1) {
            for (int row = 0; row < Board.SIZE; row++) {
                if (board.getCellStatus(row, col) == EMPTY_SIGN) {
                    board.setCellStatus(row + 1, col + 1, sign);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkMainDiagonal(Board board, char playerSign) {
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

    private boolean checkAntiDiagonal(Board board, char playerSign) {
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

    private boolean tryWin(Board board) {
        for (int i = 0; i < Board.SIZE; i++) {
            if (checkRow(board, sign, i) || checkColumn(board, sign, i)) {
                return true;
            }
        }
        return checkMainDiagonal(board, sign) || checkAntiDiagonal(board, sign);
    }

    private boolean tryBlock(Board board) {
        for (int i = 0; i < Board.SIZE; i++) {
            if (checkRow(board, oppositeSign, i) || checkColumn(board, oppositeSign, i)) {
                return true;
            }
        }
        return checkMainDiagonal(board, oppositeSign) || checkAntiDiagonal(board, oppositeSign);
    }

    private boolean mediumMove(Board board) {
        System.out.println(OutputMessages.MEDIUM_MOVE);
        return tryWin(board) || tryBlock(board);
    }



}
