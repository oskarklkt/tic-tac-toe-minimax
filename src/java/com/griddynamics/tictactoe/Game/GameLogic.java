package com.griddynamics.tictactoe.Game;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.OutputMessages;
import com.griddynamics.tictactoe.Player.Difficulty;
import com.griddynamics.tictactoe.InputExceptions.InputAttemptsException;
import com.griddynamics.tictactoe.Player.*;



public class GameLogic {
    public static void main(String[] args) throws InputAttemptsException {
        start();
    }

    public static void start() throws InputAttemptsException {
        HumanPlayer player = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer(Difficulty.EASY);
        Board board = new Board();
        board.printBoard();
        while (!board.checkDraw() && !board.checkWin(GameConstants.xSign) && !board.checkWin(GameConstants.oSign)) {
            player.makeMove(board);
            board.printBoard();
            if (!board.checkDraw() && !board.checkWin(GameConstants.xSign) && !board.checkWin(GameConstants.oSign)) {
                computer.easyMove(board);
                board.printBoard();
            }
        }
        System.out.println(checkGameStatus(board));
    }

    public static String checkGameStatus(Board board) {
        if (board.checkWin(GameConstants.xSign)) {
            return OutputMessages.xWins;
        } else if (board.checkWin(GameConstants.oSign)) {
            return OutputMessages.oWins;
        } else if (board.checkDraw()) {
            return OutputMessages.draw;
        } else {
            return OutputMessages.gameNotFinished;
        }
    }
}