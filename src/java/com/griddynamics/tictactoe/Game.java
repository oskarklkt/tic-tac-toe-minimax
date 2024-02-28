package com.griddynamics.tictactoe;

import com.griddynamics.tictactoe.Validator.GameValidator;
import com.griddynamics.tictactoe.Validator.InputValidator;

import java.util.Scanner;

public class Game {
    private static char currentSign = 'X';
    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("...");
        System.out.print("Enter the cells: ");
        String initialState = getInitialStateFromUser();
        Board board = new Board(initialState);
        board.printBoard();
        setCurrentSign(board);
        int[] coordinates = getCoordinatesFromUser(board);
        int row = coordinates[0];
        int col = coordinates[1];
        board.setCellStatus(row , col , currentSign);
        board.printBoard();
        System.out.println(checkGameStatus(board));
    }

    public static String getInitialStateFromUser() {
        Scanner scanner = new Scanner(System.in);
        String cells = scanner.nextLine();
        if (!InputValidator.validateInitialState(cells)) {
            System.out.println("Wrong initial state input, try again!");
            getInitialStateFromUser();
        }
        return cells;
    }

    public static int[] getCoordinatesFromUser(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the coordinates: ");
        while (true) {
            String input = scanner.nextLine();
            String[] parts = input.split("\\s+");
            if (parts.length != 2 || !InputValidator.validateCoordinateInput(input)) {
                System.out.println("You should enter numbers!");
                System.out.print("Enter the coordinates: ");
                continue;
            }
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if (!GameValidator.validateCoordinatesRange(row, col)) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print("Enter the coordinates: ");
            } else if (!GameValidator.validateCellAvailability(row, col, board)) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.print("Enter the coordinates: ");
            } else {
                return new int[]{row, col};
            }
        }
    }

    public static void setCurrentSign(Board board) {
        int xCount = 0;
        int oCount = 0;

        for (char[] row : board.getBoard()) {
            for (int i = 0; i < Board.SIZE; i++) {
                if (row[i] == 'X') {
                    xCount++;
                } else if (row[i] == 'O') {
                    oCount++;
                }
            }
            if (oCount < xCount) {
                currentSign = 'O';
            } else {
                currentSign = 'X';
            }
        }
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