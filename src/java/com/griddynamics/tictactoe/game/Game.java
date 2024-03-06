package com.griddynamics.tictactoe.game;

import com.griddynamics.tictactoe.board.Board;
import com.griddynamics.tictactoe.exceptions.InputSizeException;
import com.griddynamics.tictactoe.player.*;
import com.griddynamics.tictactoe.validator.InputValidator;
import java.util.Scanner;
import static com.griddynamics.tictactoe.game.GameConstants.*;
import static com.griddynamics.tictactoe.messages.InputMessages.*;
import static com.griddynamics.tictactoe.messages.OutputMessages.*;


public class Game {

    private final Player player1;
    private final Player player2;
    private final Board board;

    public Game(Player player1, Player player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
    }

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
            return;
        }

        String[] commandParts = command.split(WHITESPACE);
        if (commandParts[0].equals(EXIT)) {
            return;
        }

        System.out.print(ASK_FOR_BOARD_SIZE);
        int size = scanner.nextInt();
        scanner.nextLine();

        if (!InputValidator.validateInputSize(size)) {
            throw new InputSizeException();
        }

        Game game = new GameBuilder()
                .setPlayer1(commandParts[1], X_SIGN)
                .setPlayer2(commandParts[2], O_SIGN)
                .setSize(size)
                .build();
        game.play();
    }

    public void play() {
        board.printBoard();
        while (!board.isDraw() && !board.isWin(X_SIGN) && !board.isWin(O_SIGN)) {
            player1.makeMove(board);
            board.printBoard();
            if (!board.isDraw() && !board.isWin(X_SIGN) && !board.isWin(O_SIGN)) {
                player2.makeMove(board);
                board.printBoard();
            }
        }
        System.out.println(Board.checkGameStatus(board));
    }
}