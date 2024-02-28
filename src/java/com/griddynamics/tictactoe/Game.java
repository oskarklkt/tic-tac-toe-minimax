package com.griddynamics.tictactoe;

import com.griddynamics.tictactoe.Player.*;



public class Game {
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        HumanPlayer player = new HumanPlayer();
        ComputerPlayer computer = new ComputerPlayer(Difficulty.EASY);
        Board board = new Board();
        board.printBoard();
        while (!board.checkDraw() && !board.checkWin('X') && !board.checkWin('O')) {
            player.makeMove(board);
            board.printBoard();
            if (!board.checkDraw() && !board.checkWin('X') && !board.checkWin('O')) {
                computer.easyMove(board);
                board.printBoard();
            }
        }
        System.out.println(checkGameStatus(board));
    }

    public static String checkGameStatus(Board board) {
        if (board.checkWin('X')) {
            return "X wins";
        } else if (board.checkWin('O')) {
            return "O wins";
        } else if (board.checkDraw()) {
            return "Draw";
        } else {
            return "Game not finished";
        }
    }
}