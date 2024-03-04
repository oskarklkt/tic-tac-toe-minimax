package com.griddynamics.tictactoe.Game;

import com.griddynamics.tictactoe.Board;
import com.griddynamics.tictactoe.Messages.OutputMessages;
import com.griddynamics.tictactoe.Player.*;
import com.griddynamics.tictactoe.Validator.InputValidator;
import java.util.Scanner;
import static com.griddynamics.tictactoe.Game.GameConstants.*;
import static com.griddynamics.tictactoe.Messages.InputMessages.*;
import static com.griddynamics.tictactoe.Messages.OutputMessages.*;


public class GameLogic {

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        PlayerFactory playerFactory = new PlayerFactory();
        System.out.print(ASK_FOR_COMMAND);
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (!InputValidator.validateInputCommand(command)) {
            System.out.println(BAD_PARAMETERS);
            start();
        }
        String[] commandParts = command.split(WHITESPACE);
        if (command.split(WHITESPACE)[0].equals(EXIT)) {
            System.exit(0);
        }
        Player player1 = playerFactory.createPlayer(commandParts[1], X_SIGN);
        Player player2 = playerFactory.createPlayer(commandParts[2], O_SIGN);
        Board board = new Board();
        board.printBoard();
        while (!board.checkDraw() && !board.checkWin(X_SIGN) && !board.checkWin(O_SIGN)) {
            player1.makeMove(board);
            board.printBoard();
            if (!board.checkDraw() && !board.checkWin(X_SIGN) && !board.checkWin(O_SIGN)) {
                player2.makeMove(board);
                board.printBoard();
            }
        }
        System.out.println(checkGameStatus(board));
        System.out.println();
        start();
    }

    public static String checkGameStatus(Board board) {
        if (board.checkWin(X_SIGN)) {
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