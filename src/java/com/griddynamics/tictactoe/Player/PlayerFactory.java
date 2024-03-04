package com.griddynamics.tictactoe.Player;

public class PlayerFactory {

    public Player createPlayer(String type, char sign) {
        return switch (type) {
            case "easy" -> new ComputerPlayer(Difficulty.EASY, sign);
            case "medium" -> new ComputerPlayer(Difficulty.MEDIUM, sign);
            case "hard" -> new ComputerPlayer(Difficulty.HARD, sign);
            case "user" -> new HumanPlayer(sign);
            default -> {
                System.out.println("Bad parameters");
                yield null;
            }
        };
    }
}
