package com.griddynamics.tictactoe.game;

import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.exceptions.InputSizeException;
import com.griddynamics.tictactoe.player.*;
import com.griddynamics.tictactoe.validator.InputValidator;
import java.util.Scanner;
import static com.griddynamics.tictactoe.game.GameConstants.*;
import static com.griddynamics.tictactoe.messages.InputMessages.*;
import static com.griddynamics.tictactoe.messages.OutputMessages.*;


public class GameLogic {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.print(ASK_FOR_COMMAND);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (!InputValidator.validateInputCommand(command)) {
            System.out.println(BAD_PARAMETERS);
            start();
        }
        String[] commandParts = command.split(WHITESPACE);
        if (command.split(WHITESPACE)[0].equals(EXIT)) {
            return;
        }
        System.out.print(ASK_FOR_BOARD_SIZE);
        int size = scanner.nextInt();
        if (InputValidator.validateInputSize(size)) {
            Board board = getBoard(commandParts, size);
            System.out.println(Board.checkGameStatus(board));
            System.out.println();
            start();
        } else {
            throw new InputSizeException();
        }

    }

    private static Board getBoard(String[] commandParts, int size) {
        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.createPlayer(commandParts[1], X_SIGN);
        Player player2 = playerFactory.createPlayer(commandParts[2], O_SIGN);
        Board.SIZE = size;
        Board board = new Board();
        board.printBoard();
        while (!board.isDraw() && !board.isWin(X_SIGN) && !board.isWin(O_SIGN)) {
            player1.makeMove(board);
            board.printBoard();
            if (!board.isDraw() && !board.isWin(X_SIGN) && !board.isWin(O_SIGN)) {
                player2.makeMove(board);
                board.printBoard();
            }
        }
        return board;
    }
}