package com.griddynamics.tictactoe.Player;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.Validator.GameValidator;
import com.griddynamics.tictactoe.Validator.InputValidator;

import java.util.Scanner;

public class HumanPlayer implements Player {
    @Override
    public void makeMove(Board board) {
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
            int row = Integer.parseInt(parts[0]); // Adjust for 0-based indexing
            int col = Integer.parseInt(parts[1]); // Adjust for 0-based indexing

            if (!GameValidator.validateCoordinatesRange(row, col)) {
                System.out.println("Coordinates should be from 1 to 3!");
                System.out.print("Enter the coordinates: ");
            } else if (!GameValidator.validateCellAvailability(row, col, board)) {
                System.out.println("This cell is occupied! Choose another one!");
                System.out.print("Enter the coordinates: ");
            } else {
                board.setCellStatus(row, col, 'X');
                break;
            }
        }
    }
}
