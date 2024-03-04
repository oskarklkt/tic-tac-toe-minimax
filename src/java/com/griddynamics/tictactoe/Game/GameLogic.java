package com.griddynamics.tictactoe.Game;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.OutputMessages;
import com.griddynamics.tictactoe.Player.Difficulty;
import com.griddynamics.tictactoe.Player.*;



public class GameLogic {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        HumanPlayer player = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer(Difficulty.EASY);
        Board board = new Board();
        board.printBoard();
        while (!board.checkDraw() && !board.checkWin(GameConstants.X_SIGN) && !board.checkWin(GameConstants.O_SIGN)) {
            player.makeMove(board);
            board.printBoard();
            if (!board.checkDraw() && !board.checkWin(GameConstants.X_SIGN) && !board.checkWin(GameConstants.O_SIGN)) {
                computer.easyMove(board);
                board.printBoard();
            }
        }
        System.out.println(checkGameStatus(board));
    }

    public static String checkGameStatus(Board board) {
        if (board.checkWin(GameConstants.X_SIGN)) {
            return OutputMessages.X_WINS;
        } else if (board.checkWin(GameConstants.O_SIGN)) {
            return OutputMessages.O_WINS;
        } else if (board.checkDraw()) {
            return OutputMessages.DRAW;
        } else {
            return OutputMessages.GAME_NOT_FINISHED;
        }
    }
}